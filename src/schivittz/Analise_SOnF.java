/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schivittz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Rafael Schivittz
 */
public class Analise_SOnF {

    private BigDecimal[][] PTM_SOnF;
    private BigDecimal[] prob_vetor;
    private ArrayList<Nodo> listaNodosPMOS;
    private ArrayList<Nodo> listaNodosNMOS;
    private ArrayList<Transistores>[][] grafoPMOS;
    private ArrayList<Transistores>[][] grafoNMOS;

    ArrayList<Transistores> transistores;
    ArrayList<Entrada> entradas;
    ArrayList<Nodo> listaNodos;
    int combinacao;
    private int[][] ITM;
    
    private BigDecimal confiabilidadePorta;
    private String saida ="";

    public BigDecimal getConfiabilidadePorta() {
        return confiabilidadePorta;
    }

    public void setConfiabilidadePorta(BigDecimal confiabilidadePorta) {
        this.confiabilidadePorta = confiabilidadePorta;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public Analise_SOnF() {
        this.transistores = new ArrayList<Transistores>();
        this.entradas = new ArrayList<Entrada>();
        this.listaNodos = new ArrayList<Nodo>();
        //this.combinacao = numComb;
        //this.criaMatrizesPMOSNMOS(transistores, listaNodos);

    }

    public void setITM(int[][] ITM) {
        this.ITM = ITM;
        this.PTM_SOnF = new BigDecimal[this.ITM.length][2];
        this.prob_vetor = new BigDecimal[this.ITM.length];

        for (int i = 0; i < this.ITM.length; i++) {
            this.prob_vetor[i] = new BigDecimal("0");
        }
    }

    public void startPTM_SOnF(ArrayList<Transistores> transistores, ArrayList<Entrada> entradas, ArrayList<Nodo> listaNodos, int comb) {
        //this.imprimetransistores();
//        this.transistores = transistores;


//this.setITM(matriz);
        this.transistores = transistores;
        this.entradas = entradas;
        this.listaNodos = listaNodos;
        this.combinacao = comb;

        if ((comb == 0)) {
            calculaProb_Vetores(entradas);
        }  
        
        this.criaMatrizesPMOSNMOS(transistores, listaNodos);

        this.atualizaTransistores(combinacao);
        System.out.println("\nCombinação " + combinacao);
        this.simplificaGrafo(combinacao, listaNodosPMOS, listaNodosNMOS);
        this.atualizaGrafo(0, transistores);
        this.atualizaGrafo(1, transistores);
        this.mostraTabela(0);
        this.mostraTabela(1);
        this.analisePTM_SOnF(combinacao);

    }

    public BigDecimal[][] getPTM_SOnF() {
        return PTM_SOnF;
    }

    public void atualizaTransistores(int combinacao) {
        for (Transistores t : transistores) {
            if (t.getSinalControle().isEmpty()) {
                System.out.println("removi transistor " + t.getNome());
                transistores.remove(t);
            }

        }
        for (int i = 0; i < entradas.size(); i++) {
            for (int j = 0; j < transistores.size(); j++) {
                if (transistores.get(j).getSinalControle().equals(entradas.get(i).getNome())) {
                    transistores.get(j).setValor(entradas.get(i).getValor(combinacao));
                }
            }
        }
    }

    public void analisePTM_SOnF(int i) {
        this.atualizaTransistores(i);
        ArrayList<Transistores> transistor = new ArrayList<Transistores>();
        transistor.clear();
        for (Transistores t : transistores) {
            transistor.add(t);
        }
        if (this.ITM[i][0] == 1) {
            System.out.println("saída 0 na simulação lógica");
            System.out.println(listaNodosPMOS.size());
            //saída foi zero para essa combinação, analiso o plano PULL-UP
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
                        BigDecimal x = new BigDecimal("1");
                        BigDecimal parte1 = new BigDecimal("1");
                        BigDecimal parte2 = new BigDecimal("1");
                        String nome = "";

                        parte1 = parte1.subtract(grafoPMOS[a][in].get(0).getConfiabilidade());
                        parte2 = parte2.subtract(grafoPMOS[a][out].get(0).getConfiabilidade());
                        x = x.subtract(parte1.multiply(parte2));
                        nome = "(" + grafoPMOS[a][in].get(0).getNome() + " * " + grafoPMOS[a][out].get(0).getNome() + ")";

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
                        this.mostraTabela(0);

                    }
                }
            }
            //ultima verificação, por garantia
            this.procuraAssociacaoParalelo(0, transistor);
            this.mostraTabela(0);
            this.PTM_SOnF[i][0] = grafoPMOS[0][1].get(0).getConfiabilidade();
            this.PTM_SOnF[i][1] = new BigDecimal("1").subtract(this.PTM_SOnF[i][0]);
            System.out.println("Confiabilidade de " + grafoPMOS[0][1].get(0).getNome() + " = " + grafoPMOS[0][1].get(0).getConfiabilidade());
        } else {
            System.out.println("saida 1 na simulação lógica");
            //a saída foi 1, analiso o plano PULL-DOWN
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
                        if ((in == 99) || (out == 99)) {
                            if (in == 99) {
                                transistor.remove(grafoNMOS[a][out].get(0));
                                grafoNMOS[a][out].remove(0);
                                System.out.println(listaNodosNMOS.get(a).getNome() + " removido");
                                listaNodosNMOS.remove(a);
                            } else if (out == 99) {
                                //nodo só é ligado a 1 outro nodo?
                                transistor.remove(grafoNMOS[a][in].get(0));
                                grafoNMOS[a][in].remove(0);
                                System.out.println(listaNodosNMOS.get(a).getNome() + " removido");
                                listaNodosNMOS.remove(a);
                            }
                        } else {
                            //System.out.println(listaNodosNMOS.get(a).getNome());
                            //System.out.println("in = "+in);
                            //System.out.println("out = "+out);
                            BigDecimal x = new BigDecimal("1");
                            BigDecimal parte1 = new BigDecimal("1");
                            BigDecimal parte2 = new BigDecimal("1");
                            String nome = "";

                            parte1 = parte1.subtract(grafoNMOS[a][in].get(0).getConfiabilidade());
                            parte2 = parte2.subtract(grafoNMOS[a][out].get(0).getConfiabilidade());
                            x = x.subtract(parte1.multiply(parte2));
                            nome = "(" + grafoNMOS[a][in].get(0).getNome() + " * " + grafoNMOS[a][out].get(0).getNome() + ")";

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
                        this.mostraTabela(1);
                    }
                }
            }
            //ultima verificação, por garantia
            this.procuraAssociacaoParalelo(1, transistor);
            this.mostraTabela(1);
            this.PTM_SOnF[i][1] = new BigDecimal("0");
            this.PTM_SOnF[i][1] = this.PTM_SOnF[i][1].add(grafoNMOS[0][1].get(0).getConfiabilidade());
            this.PTM_SOnF[i][0] = new BigDecimal("1");
            this.PTM_SOnF[i][0] = this.PTM_SOnF[i][0].subtract(this.PTM_SOnF[i][1]);
            System.out.println("Confiabilidade de " + grafoNMOS[0][1].get(0).getNome() + " = " + grafoNMOS[0][1].get(0).getConfiabilidade());
        }

        //}
        //this.criaPTM();
        //this.mostraPTM_SOnF();
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
                        //System.out.print("Juntei os transistores: ");
                        while (grafoPMOS[a][b].size() >= 1) {
                            if ((x.compareTo(BigDecimal.ZERO)) == 0) {
                                x = x.add(grafoPMOS[a][b].get(0).getConfiabilidade());
                                nome = grafoPMOS[a][b].get(0).getNome();
                            } else {
                                x = x.multiply(grafoPMOS[a][b].get(0).getConfiabilidade());
                                nome = nome + " + " + grafoPMOS[a][b].get(0).getNome();
                            }
                            transistor.remove(grafoPMOS[a][b].get(0));
                            //System.out.print(grafoPMOS[a][b].get(0).getNome()+" ");
                            grafoPMOS[a][b].remove(0);
                        }
                        //System.out.println("");
                        Transistores t = new Transistores();
                        t.setConfiabilidade(x);
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
            System.out.println("Procura por associação paralela");
            this.mostraTabela(0);
        } else {
            for (int a = 0; a < listaNodosNMOS.size(); a++) {
                for (int b = 0; b < listaNodosNMOS.size(); b++) {
                    if (grafoNMOS[a][b].size() > 1) {
                        BigDecimal x = new BigDecimal("0");
                        String nome = "";
                        //System.out.print("Juntei os transistores: ");
                        while (grafoNMOS[a][b].size() >= 1) {
                            if ((x.compareTo(BigDecimal.ZERO)) == 0) {
                                x = x.add(grafoNMOS[a][b].get(0).getConfiabilidade());
                                nome = grafoNMOS[a][b].get(0).getNome();
                            } else {
                                x = x.multiply(grafoNMOS[a][b].get(0).getConfiabilidade());
                                nome = nome + " + " + grafoNMOS[a][b].get(0).getNome();
                            }
                            transistor.remove(grafoNMOS[a][b].get(0));
                            //System.out.print(grafoNMOS[a][b].get(0).getNome()+" ");
                            grafoNMOS[a][b].remove(0);
                        }
                        //System.out.println("");
                        Transistores t = new Transistores();
                        t.setConfiabilidade(x);
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
            System.out.println("Procura por associação paralela");
            this.mostraTabela(1);
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
                System.out.println("");
            }
        }
    }

    public void mostraPTM_SOnF() {
        System.out.println("----------------PTM do circuito com SOnF----------------");
        System.out.println("\t 0\t\t\t 1");
        for (int i = 0; i <= combinacao; i++) {
            String combi = Integer.toString(i, 2);
            while (combi.length() < this.entradas.size()) {
                combi = "0" + combi;
            }
            saida += PTM_SOnF[i][0] + "\t " + PTM_SOnF[i][1] + "\n";
            System.out.println(combi + "\t|" + PTM_SOnF[i][0] + "\t\t\t " + PTM_SOnF[i][1] + "|");
        }
        System.out.println("");
    }

    void imprimetransistores() {
        for (Transistores transistores1 : transistores) {
            System.out.print(transistores1.getNome() + "\t");
        }
        System.out.println("");
    }

    private void simplificaGrafo(int a, ArrayList<Nodo> listaPMOS, ArrayList<Nodo> listaNMOS) {
        ArrayList<Nodo> PMOS = new ArrayList<Nodo>();
        ArrayList<Nodo> NMOS = new ArrayList<Nodo>();

        for (Nodo nodo : listaPMOS) {
            Nodo e = new Nodo();
            e.setListaDeTransistores(nodo.getListaDeTransistores());
            e.setNome(nodo.getNome());
            e.setValor(nodo.getValor());
            e.setVerificado(nodo.isVerificado());
            PMOS.add(e);
        }
        for (Nodo nodo : listaNMOS) {
            Nodo e = new Nodo();
            e.setListaDeTransistores(nodo.getListaDeTransistores());
            e.setNome(nodo.getNome());
            e.setValor(nodo.getValor());
            e.setVerificado(nodo.isVerificado());
            NMOS.add(e);
        }
        if (this.ITM[a][0] == 1) {
            System.out.println("PMOS analise");
            //analisa plano pull up
            for (Transistores transistor : transistores) {
                if ((transistor.getValor() == 0) && (transistor.getTipo().equals("PMOS"))) {
                    //esta conduzindo
                    int endereco1 = this.procuraNodo(transistor.getNodo1().getNome(), PMOS);
                    int endereco2 = this.procuraNodo(transistor.getNodo2().getNome(), PMOS);
                    //System.out.println("endereco1 = "+endereco1);
                    //System.out.println("endereco2 = "+endereco2);
                    if (endereco1 == endereco2) {
                        //nada
                    } else {
                        if (((endereco1 == 0) && (endereco2 == 1)) || ((endereco1 == 1) && (endereco2 == 0))) {
                            //também não faz nada
                        } else if ((endereco1 == 0) || (endereco1 == 1)) {
                            PMOS.get(endereco1).setNome(PMOS.get(endereco1).getNome() + PMOS.get(endereco2).getNome());
                            //System.out.println(PMOS.get(endereco2).getNome()+" removido");
                            PMOS.remove(endereco2);
                            //transistor.setNodo(NMOS.get(endereco1), NMOS.get(endereco1));
                        } else if (((endereco2) == 0) || (endereco2 == 1)) {
                            PMOS.get(endereco2).setNome(PMOS.get(endereco2).getNome() + PMOS.get(endereco1).getNome());
                            //System.out.println(PMOS.get(endereco1).getNome()+" removido");
                            PMOS.remove(endereco1);
                            //transistor.setNodo(NMOS.get(endereco2), NMOS.get(endereco2));
                        } else {
                            PMOS.get(endereco1).setNome(PMOS.get(endereco1).getNome() + PMOS.get(endereco2).getNome());
                            //System.out.println(PMOS.get(endereco2).getNome()+" removido");
                            PMOS.remove(endereco2);
                        }
                    }
                }
            }
            listaNodosPMOS.clear();
            listaNodosPMOS = PMOS;
        } else {
            //analisa plano pull down
            System.out.println("NMOS analise");
            for (Transistores transistor : transistores) {
                if ((transistor.getValor() == 1) && (transistor.getTipo().equals("NMOS"))) {
                    //esta conduzindo
                    int endereco1 = this.procuraNodo(transistor.getNodo1().getNome(), NMOS);
                    int endereco2 = this.procuraNodo(transistor.getNodo2().getNome(), NMOS);
                    if (endereco1 == endereco2) {
                        //nada
                    } else {
                        if (((endereco1 == 0) && (endereco2 == 1)) || ((endereco1 == 1) && (endereco2 == 0))) {
                            //também não faz nada
                        } else if ((endereco1 == 0) || (endereco1 == 1)) {
                            NMOS.get(endereco1).setNome(NMOS.get(endereco1).getNome() + NMOS.get(endereco2).getNome());
                            System.out.println(NMOS.get(endereco2).getNome() + " removido");
                            NMOS.remove(endereco2);
                            //transistor.setNodo(NMOS.get(endereco1), NMOS.get(endereco1));
                        } else if (((endereco2) == 0) || (endereco2 == 1)) {
                            NMOS.get(endereco2).setNome(NMOS.get(endereco2).getNome() + NMOS.get(endereco1).getNome());
                            System.out.println(NMOS.get(endereco1).getNome() + " removido");
                            NMOS.remove(endereco1);
                            //transistor.setNodo(NMOS.get(endereco2), NMOS.get(endereco2));
                        } else {
                            NMOS.get(endereco1).setNome(NMOS.get(endereco1).getNome() + NMOS.get(endereco2).getNome());
                            System.out.println(NMOS.get(endereco2).getNome() + " removido");
                            NMOS.remove(endereco2);
                        }
                    }
                }
            }
            listaNodosNMOS.clear();
            listaNodosNMOS = NMOS;
        }

        System.out.println("PMOS");
        for (int i = 0; i < PMOS.size(); i++) {
            System.out.print(PMOS.get(i).getNome() + "  ");
        }
        System.out.println("");

        System.out.println("NMOS");
        for (int i = 0; i < NMOS.size(); i++) {
            System.out.print(NMOS.get(i).getNome() + "  ");
        }
        System.out.println("");
    }

    public int procuraNodo(String s, ArrayList<Nodo> lista) {
        int posi = -1;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNome().contains(s)) {
                posi = i;
            }
        }
        return posi;
    }

    void calculaPTMfinal() {
        
        BigDecimal conf = new BigDecimal("0");
        for (int i = 0; i < this.ITM.length; i++) {
            if (this.ITM[i][0] == 1) {
                conf = conf.add(this.PTM_SOnF[i][0].multiply(this.prob_vetor[i]));
            } else {
                conf = conf.add(this.PTM_SOnF[i][1].multiply(this.prob_vetor[i]));
            }
        }
        //System.out.println(conf);
        //conf = conf.divide(new BigDecimal(this.ITM.length));
        System.out.println("Confiabilidade da porta: " + conf);
        saida += "Confiabilidade da porta: " + conf + "\n";
        confiabilidadePorta = new BigDecimal(conf.toString());   
    }
    
    private void calculaProb_Vetores(ArrayList<Entrada> entradas) {
        int numComb = (int) Math.pow(2, this.entradas.size());
        for (int comb = 0; comb < numComb; comb++) {
            for (int i = 0; i < entradas.size(); i++) {
                if (prob_vetor[comb].compareTo(new BigDecimal("0")) == 0) {
                    if (entradas.get(i).getValor(comb) == 1) {
                        prob_vetor[comb] = prob_vetor[comb].add(new BigDecimal(String.valueOf(this.entradas.get(i).getProbabilidadeSerUm())));
                    } else {
                        double valor = 1 - this.entradas.get(i).getProbabilidadeSerUm();
                        prob_vetor[comb] = prob_vetor[comb].add(new BigDecimal(String.valueOf(valor)));
                    }
                } else { //a probabilidade não está vazia mais.
                    if (entradas.get(i).getValor(comb) == 1) {
                        prob_vetor[comb] = prob_vetor[comb].multiply(new BigDecimal(String.valueOf(this.entradas.get(i).getProbabilidadeSerUm())));
                    } else {
                        double valor = 1 - this.entradas.get(i).getProbabilidadeSerUm();
                        prob_vetor[comb] = prob_vetor[comb].multiply(new BigDecimal(String.valueOf(valor)));
                    }
                }
            }
        }
        for (int comb = 0; comb < numComb; comb++) {
            System.out.print("Combinação \t");
            for (int i = 0; i < entradas.size(); i++) {
                System.out.print(this.entradas.get(i).getValor(comb));
            }
            System.out.println("\t " + prob_vetor[comb]);
        }

    }

}
