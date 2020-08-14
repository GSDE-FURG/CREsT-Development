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
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 *
 * @author Rafael Schivittz
 */
public class Analise_SOF {

    private BigDecimal[][] PTM_SOF;
    private BigDecimal[] prob_vetor;
    private ArrayList<Nodo> listaNodosPMOS;
    private ArrayList<Nodo> listaNodosNMOS;
    private ArrayList<Transistores>[][] grafoPMOS;
    private ArrayList<Transistores>[][] grafoNMOS;
    String saida = "";

    ArrayList<Transistores> Ttransistores;
    ArrayList<Transistores> listaTransistores;
    ArrayList<Entrada> entradas;
    ArrayList<Nodo> listaNodos;
    int combinacao;
    private int[][] ITM;

    BigDecimal SOFo;
    private BigDecimal confiabilidadePorta;

    public String getSaida() {
        return saida;
    }

    public BigDecimal getConfiabilidadePorta() {
        return confiabilidadePorta;
    }
    

    public Analise_SOF() {
        this.Ttransistores = new ArrayList<Transistores>();
        this.entradas = new ArrayList<Entrada>();
        this.listaNodos = new ArrayList<Nodo>();
        this.listaTransistores = new ArrayList<Transistores>();

    }

    public void setITM(int[][] ITM) {
        this.ITM = ITM;
        this.PTM_SOF = new BigDecimal[this.ITM.length][2];
        this.prob_vetor = new BigDecimal[this.ITM.length];

        for (int i = 0; i < this.ITM.length; i++) {
            this.prob_vetor[i] = new BigDecimal("0");
        }
    }

    public void startPTM_SOF(ArrayList<Transistores> transistores, ArrayList<Entrada> ent, ArrayList<Nodo> ln, int comb, int seletor) {
        this.Ttransistores.clear();
        this.listaTransistores.clear();
        for (Transistores t : transistores) {
            this.Ttransistores.add(t);
            this.listaTransistores.add(t);
        }
        //this.imprimetransistores();
//        this.transistores = transistores;
        if (seletor == 0) {
            this.entradas.clear();
            for (Entrada e : ent) {
                this.entradas.add(e);
            }
        }

        if ((comb == 0) && (seletor == 0)) {
            calculaProb_Vetores(entradas);
        }

        if (seletor == 0) {
            for (Nodo n : ln) {
                this.listaNodos.add(n);
            }
        }
        this.combinacao = comb;

        this.criaMatrizesPMOSNMOS(Ttransistores, listaNodos);
        this.atualizaTransistores(combinacao);
        System.out.println("\nCombinação " + combinacao);
        this.simplificaGrafo(combinacao, Ttransistores);
        this.atualizaGrafo(0, Ttransistores);
        this.atualizaGrafo(1, Ttransistores);

        this.mostraTabela(0);
        this.mostraTabela(1);
        this.analisePTM_SOF(combinacao, seletor);

    }

    public void atualizaTransistores(int combinacao) {
        for (Transistores t : Ttransistores) {
            if (t.getSinalControle().isEmpty()) {
                System.out.println("removi transistor " + t.getNome());
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

    public void analisePTM_SOF(int i, int seletor) {
        this.atualizaTransistores(i);
        ArrayList<Transistores> transistor = new ArrayList<Transistores>();
        transistor.clear();
        for (Transistores t : Ttransistores) {
            transistor.add(t);
        }
        if (this.ITM[i][1] == 1) {
            System.out.println("saída 1 na simulação lógica");
            //System.out.println(listaNodosPMOS.size());
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
                                System.out.println("removi " + listaNodosPMOS.get(a).getNome() + " por não ligar nada");
                                listaNodosPMOS.remove(a);
                            } else if (in == 99) {
                                transistor.remove(grafoPMOS[a][out].get(0));
                                grafoPMOS[a][out].remove(0);
                                System.out.println(listaNodosPMOS.get(a).getNome() + " removido por não ter nodo de entrada");
                                listaNodosPMOS.remove(a);
                            } else if (out == 99) {
                                //nodo só é ligado a 1 outro nodo?
                                transistor.remove(grafoPMOS[a][in].get(0));
                                grafoPMOS[a][in].remove(0);
                                System.out.println(listaNodosPMOS.get(a).getNome() + " removido por não ter nodo de saída");
                                listaNodosPMOS.remove(a);
                            }
                            this.atualizaGrafo(0, transistor);
                            this.mostraTabela(0);

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
                            this.mostraTabela(0);

                        }
                    }
                }
            }
            //ultima verificação, por garantia
            this.procuraAssociacaoParalelo(0, transistor);
            this.mostraTabela(0);

            if (seletor == 0) {
                System.out.println("Confiabilidade de " + grafoPMOS[0][1].get(0).getNome() + " = " + grafoPMOS[0][1].get(0).getConfiabilidade());
            } else {
                if (this.listaNodosPMOS.size() == 0) {
                    System.out.println("A falha causa um erro neste estado");
                } else {
                    System.out.println("Confiabilidade de " + grafoPMOS[0][1].get(0).getNome() + " = " + grafoPMOS[0][1].get(0).getConfiabilidade());
                }
            }
            if (seletor == 0) {// se for zero, realiza o calculo
                //          AQUI VAI SER REALIZADA A ANÁLISE PARA CADA CASO NO PROCESSO calcula_SOFo
                String prob_erro = grafoPMOS[0][1].get(0).getNome();
                prob_erro = prob_erro.replace("+", "|");
                prob_erro = prob_erro.replace("*", "&");

                Expression<String> nonStandard;
                nonStandard = ExprParser.parse(prob_erro);
                //System.out.println(nonStandard);

                Expression<String> sopForm = RuleSet.toSop(nonStandard);
                String s;
                s = sopForm.toString().replace("|", "+");
                s = s.replace("&", "*");
                s = s.replace("(", "");
                s = s.replace(")", "");
                s = s.replace(" ", "");
                //System.out.println(sopForm);
                //System.out.println(s);

                calculaSOFo(s, i);

                //this.PTM_SOF[i][1] = grafoPMOS[0][1].get(0).getConfiabilidade();
                //this.PTM_SOF[i][0] = new BigDecimal("1").subtract(this.PTM_SOF[i][1]);
                
                BigDecimal erro = new BigDecimal("0");
                erro = erro.add(new BigDecimal("1").subtract(grafoPMOS[0][1].get(0).getConfiabilidade()));
                //System.out.println("erro da combinação " + erro);
                erro = erro.multiply(this.SOFo);
                //System.out.println("erro combinado com SOFo " + erro);
                
                this.PTM_SOF[i][0] = erro;
                this.PTM_SOF[i][1] = new BigDecimal("1").subtract(erro);
            }

        } else {
            System.out.println("saida 0 na simulação lógica");
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
                                System.out.println("removi " + listaNodosNMOS.get(a).getNome() + " por não ligar nada");
                                listaNodosNMOS.remove(a);
                            } else if (in == 99) {
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
                        this.mostraTabela(1);
                    }
                }
            }
            //ultima verificação, por garantia
            this.procuraAssociacaoParalelo(1, transistor);
            this.mostraTabela(1);

            if (seletor == 0) {
                System.out.println("Confiabilidade de " + grafoNMOS[0][1].get(0).getNome() + " = " + grafoNMOS[0][1].get(0).getConfiabilidade());
            } else {
                if (this.listaNodosNMOS.size() == 0) {
                    System.out.println("A falha causa um erro neste estado");
                } else {
                    System.out.println("Confiabilidade de " + grafoNMOS[0][1].get(0).getNome() + " = " + grafoNMOS[0][1].get(0).getConfiabilidade());
                }
            }
            if (seletor == 0) {
                String prob_erro = grafoNMOS[0][1].get(0).getNome();
                prob_erro = prob_erro.replace("+", "|");
                prob_erro = prob_erro.replace("*", "&");
                Expression<String> nonStandard;
                nonStandard = ExprParser.parse(prob_erro);
                //System.out.println(nonStandard);

                Expression<String> sopForm = RuleSet.toSop(nonStandard);
                String s;
                s = sopForm.toString().replace("|", "+");
                s = s.replace("&", "*");
                s = s.replace("(", "");
                s = s.replace(")", "");
                s = s.replace(" ", "");
                //System.out.println(sopForm);
                //System.out.println(s);

                calculaSOFo(s, i);

                this.PTM_SOF[i][0] = new BigDecimal("0");
                //this.PTM_SOF[i][0] = this.PTM_SOF[i][0].add(grafoNMOS[0][1].get(0).getConfiabilidade());
                this.PTM_SOF[i][1] = new BigDecimal("0");
                //this.PTM_SOF[i][1] = this.PTM_SOF[i][1].subtract(this.PTM_SOF[i][0]);
                
                BigDecimal erro = new BigDecimal("0");
                erro = erro.add(new BigDecimal("1").subtract(grafoNMOS[0][1].get(0).getConfiabilidade()));
                //System.out.println("erro da combinação " + erro);
                erro = erro.multiply(this.SOFo);
                //System.out.println("erro combinado com SOFo " + erro);
                
                this.PTM_SOF[i][1] = this.PTM_SOF[i][1].add(erro);
                this.PTM_SOF[i][0] = this.PTM_SOF[i][0].add(new BigDecimal("1").subtract(erro));
                
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

    public void mostraPTM_SOF() {
        System.out.println("----------------PTM do circuito com SOF----------------");
        System.out.println("\t 0\t\t\t 1");
        for (int i = 0; i <= combinacao; i++) {
            String combi = Integer.toString(i, 2);
            while (combi.length() < this.entradas.size()) {
                combi = "0" + combi;
            }
            saida += PTM_SOF[i][0] + "\t " + PTM_SOF[i][1] + "\n";
            System.out.println(combi + "\t|" + PTM_SOF[i][0] + "\t\t\t " + PTM_SOF[i][1] + "|");
        }
        System.out.println("");
    }

    void imprimetransistores() {
        for (Transistores transistores1 : Ttransistores) {
            System.out.print(transistores1.getNome() + "\t");
            System.out.println(transistores1.getConfiabilidade());
        }
        System.out.println("");
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
                    System.out.println("removi " + transistoresnovos.get(i).getNome());
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
                    System.out.println("removi " + transistoresnovos.get(i).getNome());
                    transistoresnovos.remove(i);
                }
            }

            this.Ttransistores.clear();
            this.Ttransistores = transistoresnovos;
        }
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
        BigDecimal maisSensivel = new BigDecimal("1");
        int combinacao=0;
        ArrayList<String> posicoes = new ArrayList<String>();
        for (int i = 0; i < this.ITM.length; i++) {
            if (this.ITM[i][0] == 1) {
                conf = conf.add(this.PTM_SOF[i][0].multiply(this.prob_vetor[i]));
                if(maisSensivel.compareTo(this.PTM_SOF[i][0])==1){
                    maisSensivel = new BigDecimal(this.PTM_SOF[i][0].toString());
                    posicoes.clear();
                    posicoes.add(String.valueOf(i));
                }
                else if(maisSensivel.compareTo(this.PTM_SOF[i][0])==0){
                    posicoes.add(String.valueOf(i));
                }
            } else {
                conf = conf.add(this.PTM_SOF[i][1].multiply(this.prob_vetor[i]));
                if(maisSensivel.compareTo(this.PTM_SOF[i][1])==1){
                    maisSensivel = new BigDecimal(this.PTM_SOF[i][1].toString());
                    posicoes.clear();
                    posicoes.add(String.valueOf(i));
                }
                else if(maisSensivel.compareTo(this.PTM_SOF[i][0])==0){
                    posicoes.add(String.valueOf(i));
                }
            }
        }
        //System.out.println(conf);
        //conf = conf.divide(new BigDecimal(this.ITM.length));
        System.out.println("Confiabilidade da porta: " + conf);
        saida += "Confiabilidade da porta: " + conf + "\n";
        confiabilidadePorta = new BigDecimal(conf.toString());
        saida += "Vetor(es) mais sensível(eis) = " + posicoes.toString();
        //for (int i = 0; i < entradas.size(); i++) {
        //        saida += this.entradas.get(i).getValor(combinacao);
        //    }
        saida +="\t com confiabilidade = " + maisSensivel.setScale(4, RoundingMode.UP).toString()+"\n";

    }

    //i -- é a combinação atual a ser analisada
    //a string s -- é a equação em forma de soma de produtos, para analisar o estado anterior da porta
    private void calculaSOFo(String s, int i) {
        BigDecimal erro = new BigDecimal("0");
        s = s.trim();
        SOFo = new BigDecimal("0");
        System.out.println("Analisando estado anterior para " + s);
        int quantidadeElementos;
        if (!s.contains("+")) {
            s = s + "+";
        }
        String[] equacao = s.split(Pattern.quote("+"));
        quantidadeElementos = equacao.length;
        for (int j = 0; j < quantidadeElementos; j++) {
            //para cada OU na equação...
            ArrayList<Transistores> transistor = new ArrayList<Transistores>();
            transistor.clear();
            if (!equacao[j].contains("*")) {
                equacao[j] = equacao[j] + "*";
            }
            String[] equacaoAND = equacao[j].split(Pattern.quote("*"));
            for (Transistores t : listaTransistores) {
                boolean verificador = false;
                for (String equacaoAND1 : equacaoAND) {
                    if (t.getNome().equals(equacaoAND1)) {
                        verificador = true;
                        //System.out.println(t.getNome() + "não será considerado");
                    }
                }
                if (verificador == false) {
                    transistor.add(t);
                }
            }
//                    System.out.print("imprimindo os transistores que são passados: ");
//                    for (Transistores t : transistor) {
//                        System.out.print(t.getNome()+"\t");
//                    }
//                    System.out.println("");
//                    System.out.print("imprimindo os transistores da lista: ");
//                    for (Transistores t : listaTransistores) {
//                        System.out.print(t.getNome()+"\t");
//                    }
            System.out.println("");
            System.out.println("\nEquacão " + Arrays.toString(equacaoAND));
            EstadoAnterior estado = new EstadoAnterior();
            estado.setITM(ITM);
            erro = erro.add(estado.analisaEstadoAnterior(transistor, listaNodos, entradas, prob_vetor, i));
            System.out.println("erro = "+ erro);
            System.out.println(quantidadeElementos);

        }
        BigDecimal dividendo = new BigDecimal(String.valueOf(quantidadeElementos));
        this.SOFo = SOFo.add(erro.divide(dividendo, 10, RoundingMode.HALF_EVEN));
        System.out.println("SOFo = " + SOFo);
        if (s.endsWith("+")) {
            System.out.println("\n-----------------------------------fim da analise para " + s.substring(0, s.length() - 1) + "---------------------------------------------------");
        } else {
            System.out.println("\n-----------------------------------fim da analise para " + s.substring(0, s.length()) + "---------------------------------------------------");
        }

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
