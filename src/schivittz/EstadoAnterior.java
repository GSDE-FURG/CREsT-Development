/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schivittz;

import com.bpodgursky.jbool_expressions.Expression;
import com.bpodgursky.jbool_expressions.parsers.ExprParser;
import com.bpodgursky.jbool_expressions.rules.RuleSet;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Rafael Schivittz
 */
public class EstadoAnterior {
    
    private ArrayList<Nodo> listaNodosPMOS;
    private ArrayList<Nodo> listaNodosNMOS;
    private ArrayList<Transistores>[][] grafoPMOS;
    private ArrayList<Transistores>[][] grafoNMOS;

    ArrayList<Transistores> Ttransistores;
    ArrayList<Entrada> entradas;
    ArrayList<Nodo> listaNodos;
    BigDecimal[] prob_vetor;
    int combinacao;
    private int[][] ITM;
    
    BigDecimal SOFo;
    
    public EstadoAnterior(){
        this.Ttransistores = new ArrayList<Transistores>();
        this.entradas = new ArrayList<Entrada>();
        this.listaNodos = new ArrayList<Nodo>();
        this.SOFo = new BigDecimal("0");
    }
    
    public void setITM(int[][] ITM) {
        this.ITM = ITM;
        this.prob_vetor = new BigDecimal[this.ITM.length];
    }
    
    
    public BigDecimal analisaEstadoAnterior(ArrayList<Transistores> listaT, ArrayList<Nodo> listaN, ArrayList<Entrada> listaE, BigDecimal[] vetor, int combinacaoAtual){
        //para cada combinação de entrada, vai analisar se o conjunto de transistores pode mascarar a falha
        this.prob_vetor = vetor;
        for(int iterator=0; iterator<this.ITM.length;iterator++){
            
            if(this.ITM[iterator][1] == this.ITM[combinacaoAtual][1]){
                System.out.println("Combinação " + combinacaoAtual + " e " + iterator + " possuem a mesma saída, analisando mascaramento...");
                
                this.Ttransistores.clear();
                for (Transistores t : listaT) {
                    this.Ttransistores.add(t);
                }
                    this.entradas.clear();
                    for (Entrada e : listaE) {
                        this.entradas.add(e);
                    }

                this.listaNodos.clear();
                for (Nodo n : listaN) {
                    this.listaNodos.add(n);
                }
                
                this.combinacao = combinacaoAtual;

                this.criaMatrizesPMOSNMOS(Ttransistores, listaNodos);
                this.atualizaTransistores(iterator);
                this.simplificaGrafo(iterator, Ttransistores);
                this.atualizaGrafo(0, Ttransistores);
                this.atualizaGrafo(1, Ttransistores);

                this.analisePTM_SOF(combinacao);

                
            }
            else{
                System.out.println("Combinação " + combinacaoAtual + " e " + iterator + " não possuem a mesma saída, erro é computado");
                this.SOFo = SOFo.add(prob_vetor[iterator]);
            }
        }
        
        return SOFo;
    }
    
    private void atualizaTransistores(int combinacao) {
        for (Transistores t : Ttransistores) {
            if (t.getSinalControle().isEmpty()) {
                Ttransistores.remove(t);
            }

        }
        for (int i = 0; i < entradas.size(); i++) {
            for (int j = 0; j < Ttransistores.size(); j++) {
                if (Ttransistores.get(j).getSinalControle().equals(entradas.get(i).getNome())) {
                    Ttransistores.get(j).setValor(entradas.get(i).getValor(combinacao));
                }
            }
        }
    }

    private void analisePTM_SOF(int i) {
        this.atualizaTransistores(i);
        ArrayList<Transistores> transistor = new ArrayList<Transistores>();
        transistor.clear();
        for (Transistores t : Ttransistores) {
            transistor.add(t);
        }
        if (this.ITM[i][1] == 1) {
            //saída foi um para essa combinação, analiso o plano PULL-UP
            while (this.listaNodosPMOS.size() > 2) {
                this.procuraAssociacaoParalelo(0, transistor);
                for (int a = 0; a < listaNodosPMOS.size(); a++) {
                    int contador = 0;
                    for (int b = 0; b < listaNodosPMOS.size(); b++) {
                        contador += grafoPMOS[a][b].size();
                    }
                    if ((contador <= 2) && (a != 0) && (a != 1)) {
                        //NODO PODE SER REMOVIDO
                        //o nodo que eu sou é o nodo a
                        int in = 99;
                        int out = 99;
                        for (int elemento = 0; elemento < listaNodosPMOS.size(); elemento++) {
                            if (elemento != a) {
                                if ((!grafoPMOS[a][elemento].isEmpty()) && (in == 99)) {
                                    in = elemento;
                                } else {
                                    if ((!grafoPMOS[a][elemento].isEmpty()) && (out == 99)) {
                                        out = elemento;
                                    }
                                }
                            }
                        }
                        if ((in == 99) || (out == 99)) {
                            if (((in == 99) && (out == 99))) {
                                listaNodosPMOS.remove(a);
                            } else if (in == 99) {
                                transistor.remove(grafoPMOS[a][out].get(0));
                                grafoPMOS[a][out].remove(0);
                                listaNodosPMOS.remove(a);
                            } else if (out == 99) {
                                //nodo só é ligado a 1 outro nodo?
                                transistor.remove(grafoPMOS[a][in].get(0));
                                grafoPMOS[a][in].remove(0);
                                listaNodosPMOS.remove(a);
                            }
                            this.atualizaGrafo(0, transistor);
                            //this.mostraTabela(0);
                            
                        } else {
                            BigDecimal x = new BigDecimal("0");
                            BigDecimal parte2 = new BigDecimal("0");
                            String nome = "";

                            x = x.add(grafoPMOS[a][in].get(0).getConfiabilidade());
                            parte2 = parte2.add(grafoPMOS[a][out].get(0).getConfiabilidade());
                            x = x.multiply(parte2);
                            nome = "(" + grafoPMOS[a][in].get(0).getNome() + " + " + grafoPMOS[a][out].get(0).getNome() + ")";

                            transistor.remove(grafoPMOS[a][in].get(0));
                            grafoPMOS[a][in].remove(0);
                            transistor.remove(grafoPMOS[a][out].get(0));
                            grafoPMOS[a][out].remove(0);

                            Transistores t = new Transistores();
                            t.setConfiabilidade(x);
                            t.setNome(nome);
                            t.setTipo("PMOS");
                            t.setNodo(listaNodosPMOS.get(in), listaNodosPMOS.get(out));
                            transistor.add(t);
                            grafoPMOS[in][out].add(t);
                            grafoPMOS[out][in].add(t);

                            System.out.println(listaNodosPMOS.get(a).getNome() + " removido");
                            listaNodosPMOS.remove(a);
                            this.atualizaGrafo(0, transistor);
                            //this.mostraTabela(0);

                        }
                    }
                }
            }
            //ultima verificação, por garantia
            this.procuraAssociacaoParalelo(0, transistor);
            //this.mostraTabela(0);
            
            if(this.listaNodosPMOS.size()==0){
                    System.out.println("A falha causa um erro neste estado");
                    this.SOFo = SOFo.add(prob_vetor[i]);
            }
            else{
                if(listaNodosPMOS.get(1).getNome().equals("OUT")){
                    if(grafoPMOS[0][1].isEmpty()){
                        System.out.println("A falha causa um erro neste estado");
                        this.SOFo = SOFo.add(prob_vetor[i]);
                    }
                    else{
                        System.out.println("O erro é observado se " + grafoPMOS[0][1].get(0).getNome() + " apresentar falha, com probabilidade = " + (new BigDecimal("1").subtract(grafoPMOS[0][1].get(0).getConfiabilidade())));
                        BigDecimal valor = new BigDecimal("0");
                        valor = valor.add((new BigDecimal("1").subtract(grafoPMOS[0][1].get(0).getConfiabilidade())));
                        //System.out.println(valor);
                        valor = valor.multiply(prob_vetor[i]);
                        //System.out.println(valor);
                        this.SOFo = SOFo.add(valor);
                    }
                }
                else{
                    System.out.println("A falha causa um erro neste estado");
                    this.SOFo = SOFo.add(prob_vetor[i]);
                }
            }
                
        
        } else {
            //a saída foi 0, analiso o plano PULL-DOWN
            while (this.listaNodosNMOS.size() > 2) {
                this.procuraAssociacaoParalelo(1, transistor);
                for (int a = 0; a < listaNodosNMOS.size(); a++) {
                    int contador = 0;
                    for (int b = 0; b < listaNodosNMOS.size(); b++) {
                        contador += grafoNMOS[a][b].size();
                    }
                    if ((contador <= 2) && (a != 0) && (a != 1)) {
                        //NODO PODE SER REMOVIDO
                        //o nodo que eu sou é o nodo a
                        int in = 99;
                        int out = 99;
                        for (int elemento = 0; elemento < listaNodosNMOS.size(); elemento++) {
                            if (elemento != a) {
                                if ((!grafoNMOS[a][elemento].isEmpty()) && (in == 99)) {
                                    in = elemento;

                                } else {
                                    if ((!grafoNMOS[a][elemento].isEmpty()) && (out == 99)) {
                                        out = elemento;

                                    }
                                }
                            }
                        }
                        if (((in == 99) || (out == 99))) {
                            if (((in == 99) && (out == 99))) {
                                listaNodosNMOS.remove(a);
                            } else if (in == 99) {
                                transistor.remove(grafoNMOS[a][out].get(0));
                                grafoNMOS[a][out].remove(0);
                                listaNodosNMOS.remove(a);
                            } else if (out == 99) {
                                //nodo só é ligado a 1 outro nodo?
                                transistor.remove(grafoNMOS[a][in].get(0));
                                grafoNMOS[a][in].remove(0);
                                listaNodosNMOS.remove(a);
                            }
                        } 
                        else {
                            BigDecimal x = new BigDecimal("0");
                            BigDecimal parte1 = new BigDecimal("0");
                            String nome = "";

                            x = x.add(grafoNMOS[a][in].get(0).getConfiabilidade());
                            parte1 = parte1.add(grafoNMOS[a][out].get(0).getConfiabilidade());
                            x = x.multiply(parte1);
                            nome = "(" + grafoNMOS[a][in].get(0).getNome() + " + " + grafoNMOS[a][out].get(0).getNome() + ")";

                            transistor.remove(grafoNMOS[a][in].get(0));
                            grafoNMOS[a][in].remove(0);
                            transistor.remove(grafoNMOS[a][out].get(0));
                            grafoNMOS[a][out].remove(0);

                            Transistores t = new Transistores();
                            t.setConfiabilidade(x);
                            t.setNome(nome);
                            t.setNodo(listaNodosNMOS.get(in), listaNodosNMOS.get(out));
                            t.setTipo("NMOS");
                            grafoNMOS[in][out].add(t);
                            grafoNMOS[out][in].add(t);
                            transistor.add(t);

                            System.out.println(listaNodosNMOS.get(a).getNome() + " removido");
                            listaNodosNMOS.remove(a);
                        }
                        this.atualizaGrafo(1, transistor);
                        //System.out.println("atualizado");
                        //this.mostraTabela(1);
                    }
                }
            }
            //ultima verificação, por garantia
            this.procuraAssociacaoParalelo(1, transistor);
            //this.mostraTabela(1);

            if(this.listaNodosNMOS.size()==0){
                    System.out.println("A falha causa um erro neste estado");
                    this.SOFo = SOFo.add(prob_vetor[i]);
            }
            else{
                if(listaNodosNMOS.get(1).getNome().equals("OUT")){
                    if(grafoNMOS[0][1].isEmpty()){
                        System.out.println("A falha causa um erro neste estado");
                        this.SOFo = SOFo.add(prob_vetor[i]);
                    }
                    else{
                        System.out.println("O erro é observado se " + grafoNMOS[0][1].get(0).getNome() + " apresentar falha, com probabilidade = " + (new BigDecimal("1").subtract(grafoNMOS[0][1].get(0).getConfiabilidade())));
                        BigDecimal valor = new BigDecimal("0");
                        valor = valor.add((new BigDecimal("1").subtract(grafoNMOS[0][1].get(0).getConfiabilidade())));
                        //System.out.println(valor);
                        valor = valor.multiply(prob_vetor[i]);
                        //System.out.println(valor);
                        this.SOFo = SOFo.add(valor);
                    }
                }
                else{
                    System.out.println("A falha causa um erro neste estado");
                    this.SOFo = SOFo.add(prob_vetor[i]);
                }
            }

        }
    }

    private void criaMatrizesPMOSNMOS(ArrayList<Transistores> transistores, ArrayList<Nodo> listaNodos) {

        int x = 0; //definir valor 1 para mostrar o debug e 0 para remover.

        listaNodosPMOS = new ArrayList();
        listaNodosNMOS = new ArrayList();
        //monta a lista dos nodos PMOS e NMOS
        for (int i = 0; i < listaNodos.size(); i++) {
            for (int j = 0; j < transistores.size(); j++) {
                if ((transistores.get(j).getNodo1().getNome().equals(listaNodos.get(i).getNome())) || (transistores.get(j).getNodo2().getNome().equals(listaNodos.get(i).getNome()))) {
                    if ((transistores.get(j).getTipo().equals("PMOS"))) {
                        //preenche o nodo na lista dos PMOS
                        if (!listaNodosPMOS.contains(listaNodos.get(i))) {
                            listaNodosPMOS.add(listaNodos.get(i));
                        }
                    } else {
                        if (!listaNodosNMOS.contains(listaNodos.get(i))) {
                            listaNodosNMOS.add(listaNodos.get(i));
                        }
                    }
                }
            }
        }

        grafoPMOS = new ArrayList[listaNodosPMOS.size()][listaNodosPMOS.size()];
        grafoNMOS = new ArrayList[listaNodosNMOS.size()][listaNodosNMOS.size()];
        //inicializa
        if (x == 1) {
            System.out.print("Lista de nodos PMOS: ");
        }
        for (int j = 0; j < listaNodosPMOS.size(); j++) {
            for (int k = 0; k < listaNodosPMOS.size(); k++) {
                grafoPMOS[j][k] = new ArrayList<Transistores>();
            }
            if (x == 1) {
                System.out.print(listaNodosPMOS.get(j).getNome() + " ");
            }
        }
        if (x == 1) {
            System.out.print("\nLista de nodos NMOS: ");
        }
        for (int j = 0; j < listaNodosNMOS.size(); j++) {
            for (int k = 0; k < listaNodosNMOS.size(); k++) {
                grafoNMOS[j][k] = new ArrayList<Transistores>();
            }
            if (x == 1) {
                System.out.print(listaNodosNMOS.get(j).getNome() + " ");
            }
        }

        for (int i = 0; i < listaNodosPMOS.size(); i++) {
            for (int j = 0; j < listaNodosPMOS.size(); j++) {
                if (i == j) { // não compara os iguais
                } else {
                    for (int k = 0; k < transistores.size(); k++) {
                        if (transistores.get(k).ligaOsNodos(listaNodosPMOS.get(i), listaNodosPMOS.get(j))) {
                            //System.out.println("Arco "+transistores.get(k).getNome()+" sendo verificado ");
                            grafoPMOS[i][j].add(transistores.get(k));
                        }
                    }
                }
            }
        }

        for (int i = 0; i < listaNodosNMOS.size(); i++) {
            for (int j = 0; j < listaNodosNMOS.size(); j++) {
                if (i == j) { // não compara os iguais
                } else {
                    for (int k = 0; k < transistores.size(); k++) {
                        if (transistores.get(k).ligaOsNodos(listaNodosNMOS.get(i), listaNodosNMOS.get(j))) {
                            //System.out.println("Arco "+transistores.get(k).getNome()+" sendo verificado ");
                            grafoNMOS[i][j].add(transistores.get(k));
                        }
                    }
                }
            }
        }
    }

    private void procuraAssociacaoParalelo(int plano, ArrayList<Transistores> transistores) {
        //plano = 0 para PMOS / plano = 1 para NMOS
        ArrayList<Transistores> transistor = new ArrayList<Transistores>();
        transistor.clear();
        for (Transistores t : transistores) {
            transistor.add(t);
        }

        if (plano == 0) {
            for (int a = 0; a < listaNodosPMOS.size(); a++) {
                for (int b = 0; b < listaNodosPMOS.size(); b++) {
                    if (grafoPMOS[a][b].size() > 1) {
                        BigDecimal x = new BigDecimal("0");
                        String nome = "";
                        while (grafoPMOS[a][b].size() >= 1) {
                            if ((x.compareTo(BigDecimal.ZERO)) == 0) {
                                x = x.add(new BigDecimal("1"));
                                x = x.subtract(grafoPMOS[a][b].get(0).getConfiabilidade());
                                nome = grafoPMOS[a][b].get(0).getNome();
                            } else {
                                BigDecimal parte2 = new BigDecimal("1").subtract(grafoPMOS[a][b].get(0).getConfiabilidade());
                                x = x.multiply(parte2);
                                nome = nome + " * " + grafoPMOS[a][b].get(0).getNome();
                            }
                            transistor.remove(grafoPMOS[a][b].get(0));
                            //System.out.print(grafoPMOS[a][b].get(0).getNome()+" ");
                            grafoPMOS[a][b].remove(0);
                        }
                        BigDecimal confiabilidade = new BigDecimal("1").subtract(x);
                        //System.out.println("");
                        Transistores t = new Transistores();
                        t.setConfiabilidade(confiabilidade);
                        t.setNome("(" + nome + ")");
                        t.setNodo(listaNodosPMOS.get(a), listaNodosPMOS.get(b));
                        grafoPMOS[a][b].add(t);
                        boolean verificador = false;
                        for (Transistores transistores1 : transistor) {
                            if (transistores1.getNome().equals(t.getNome())) {
                                verificador = true;
                            }
                        }
                        if (!verificador) {
                            transistor.add(t);
                        }
                    }
                }
            }
        } else {
            for (int a = 0; a < listaNodosNMOS.size(); a++) {
                for (int b = 0; b < listaNodosNMOS.size(); b++) {
                    if (grafoNMOS[a][b].size() > 1) {
                        BigDecimal x = new BigDecimal("0");
                        String nome = "";
                        //System.out.print("Juntei os transistores: ");
                        while (grafoNMOS[a][b].size() >= 1) {
                            if ((x.compareTo(BigDecimal.ZERO)) == 0) {
                                x = x.add(new BigDecimal("1"));
                                x = x.subtract(grafoNMOS[a][b].get(0).getConfiabilidade());
                                nome = grafoNMOS[a][b].get(0).getNome();
                            } else {
                                BigDecimal parte2 = new BigDecimal("1").subtract(grafoNMOS[a][b].get(0).getConfiabilidade());
                                x = x.multiply(parte2);
                                nome = nome + " * " + grafoNMOS[a][b].get(0).getNome();
                            }
                            transistor.remove(grafoNMOS[a][b].get(0));
                            //System.out.print(grafoNMOS[a][b].get(0).getNome()+" ");
                            grafoNMOS[a][b].remove(0);
                        }
                        BigDecimal confiabilidade = new BigDecimal("1").subtract(x);
                        //System.out.println("");
                        Transistores t = new Transistores();
                        t.setConfiabilidade(confiabilidade);
                        t.setNome("(" + nome + ")");
                        t.setNodo(listaNodosNMOS.get(a), listaNodosNMOS.get(b));
                        grafoNMOS[a][b].add(t);
                        boolean verificador = false;
                        for (Transistores transistores1 : transistor) {
                            if (transistores1.getNome().equals(t.getNome())) {
                                verificador = true;
                            }
                        }
                        if (!verificador) {
                            transistor.add(t);
                        }
                    }
                }
            }
        }
    }

    private void atualizaGrafo(int plano, ArrayList<Transistores> transistores) {
        if (plano == 0) {
            grafoPMOS = new ArrayList[listaNodosPMOS.size()][listaNodosPMOS.size()];
            //inicializa
            for (int j = 0; j < listaNodosPMOS.size(); j++) {
                for (int k = 0; k < listaNodosPMOS.size(); k++) {
                    grafoPMOS[j][k] = new ArrayList<Transistores>();
                }
            }
            for (Transistores transistor : transistores) {
                int endereco1 = this.procuraNodo(transistor.getNodo1().getNome(), listaNodosPMOS);
                int endereco2 = this.procuraNodo(transistor.getNodo2().getNome(), listaNodosPMOS);

                if ((endereco1 == endereco2) || (endereco1 == -1) || (endereco2 == -1)) {
                } else {
                    grafoPMOS[endereco1][endereco2].add(transistor);
                    grafoPMOS[endereco2][endereco1].add(transistor);
                }
            }
        } else {
            grafoNMOS = new ArrayList[listaNodosNMOS.size()][listaNodosNMOS.size()];
            //inicializa
            for (int j = 0; j < listaNodosNMOS.size(); j++) {
                for (int k = 0; k < listaNodosNMOS.size(); k++) {
                    grafoNMOS[j][k] = new ArrayList<Transistores>();
                }
            }

            for (Transistores transistor : transistores) {
                int endereco1 = this.procuraNodo(transistor.getNodo1().getNome(), listaNodosNMOS);
                int endereco2 = this.procuraNodo(transistor.getNodo2().getNome(), listaNodosNMOS);

                if ((endereco1 == endereco2) || (endereco1 == -1) || (endereco2 == -1)) {
                } else {
                    grafoNMOS[endereco1][endereco2].add(transistor);
                    grafoNMOS[endereco2][endereco1].add(transistor);
                }
            }
        }
    }
    
    private void simplificaGrafo(int a, ArrayList<Transistores> transistores) {
        ArrayList<Transistores> transistoresnovos = new ArrayList<Transistores>();
        for (Transistores t : transistores) {
            transistoresnovos.add(t);
        }
        if (this.ITM[a][1] == 1) { //se saída for 1
            //System.out.println("PMOS analise");
            //analisa plano pull up
            boolean verificador = true;
            int i = 0;
            while (verificador == true) {
                verificador = false;
                for (Transistores transistor : transistoresnovos) {
                    if ((transistor.getValor() == 1) && (transistor.getTipo().equals("PMOS"))) {
                        verificador = true;
                        i = transistoresnovos.indexOf(transistor);
                    }
                }
                if (verificador == true) {
                    //System.out.println("removi " + transistoresnovos.get(i).getNome());
                    transistoresnovos.remove(i);
                }
            }
            this.Ttransistores.clear();
            this.Ttransistores = transistoresnovos;
        } else {
            //analisa plano pull down
            //System.out.println("NMOS analise");
            boolean verificador = true;
            int i = 0;
            while (verificador == true) {
                verificador = false;
                for (Transistores transistor : transistoresnovos) {
                    if ((transistor.getValor() == 0) && (transistor.getTipo().equals("NMOS"))) {
                        verificador = true;
                        i = transistoresnovos.indexOf(transistor);
                    }
                }
                if (verificador == true) {
                    //System.out.println("removi " + transistoresnovos.get(i).getNome());
                    transistoresnovos.remove(i);
                }
            }

            this.Ttransistores.clear();
            this.Ttransistores = transistoresnovos;
        }
    }
    
    private int procuraNodo(String s, ArrayList<Nodo> lista) {
        int posi = -1;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNome().contains(s)) {
                posi = i;
            }
        }
        return posi;
    }
    
    private void mostraTabela(int plano) {
        if (plano == 0) {
            for (Nodo nodo1 : listaNodosPMOS) {
                System.out.print(nodo1.getNome() + "\t\t");
            }
            System.out.println("");
            for (int i = 0; i < listaNodosPMOS.size(); i++) {
                for (int j = 0; j < listaNodosPMOS.size(); j++) {
                    if (grafoPMOS[i][j].isEmpty()) {
                        System.out.print("0");
                    } else {
                        System.out.print(grafoPMOS[i][j].get(0).getNome());
                        for (int k = 1; k < grafoPMOS[i][j].size(); k++) {
                            System.out.print("," + grafoPMOS[i][j].get(k).getNome());
                        }
                    }
                    System.out.print("\t\t");
                }
                System.out.println("");
            }
        } else {
            for (Nodo nodo1 : listaNodosNMOS) {
                System.out.print(nodo1.getNome() + "\t\t");
            }
            System.out.println("");
            for (int i = 0; i < listaNodosNMOS.size(); i++) {
                for (int j = 0; j < listaNodosNMOS.size(); j++) {
                    if (grafoNMOS[i][j].isEmpty()) {
                        System.out.print("0");
                    } else {
                        System.out.print(grafoNMOS[i][j].get(0).getNome());
                        for (int k = 1; k < grafoNMOS[i][j].size(); k++) {
                            System.out.print("," + grafoNMOS[i][j].get(k).getNome());
                        }
                    }
                    System.out.print("\t\t");
                }
                System.out.println("");
            }
        }
    }
    
}
