/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schivittz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.pow;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author Rafael Schivittz
 */
public class Grafo {

    private ArrayList<Transistores> transistores;
    private ArrayList<Entrada> entradas;
    private ArrayList<Nodo> listaNodos;
    private ArrayList<Transistores>[][] grafo;
    private int marcados;
    private int numComb;
    private int[][] ITM;
    private int[][] saidaSimulaçãoLogica;
    private ArrayList<String> caminhosPMOS;
    private ArrayList<String> caminhosNMOS;
    private BigDecimal[] q;
    private String saida;
    private BigDecimal confiabilidadedaPorta;

    public int getMarcados() {
        return marcados;
    }
    
    public int getNumComb() {
        return this.numComb;
    }

    public void setMarcados(int marcados) {
        this.marcados = marcados;
    }

    public String getSaida() {
        return saida;
    }

    public Grafo(ArrayList<Transistores> listaTransistores, ArrayList<Entrada> listaEntradas, ArrayList<Nodo> listaNodos, BigDecimal q) {

        this.transistores = listaTransistores;
        this.entradas = listaEntradas;
        this.listaNodos = listaNodos;
        this.marcados = 2;
        for (int t = 0; t < listaTransistores.size(); t++) {
            listaTransistores.get(t).setConfiabilidade(q);
        }

        this.numComb = (int) Math.pow(2, listaEntradas.size());
        this.criaVetorEntradas();
        this.ITM = new int[numComb][2];
        this.saidaSimulaçãoLogica = new int[numComb][(listaNodos.size() - 2)];
        this.q = new BigDecimal[numComb];

        //aqui dentro do FOR é realizada a simulação lógica e o preenchimento da ITM
        //this.criaMatrizesPMOSNMOS(transistores, listaNodos);
        for (int comb = 0; comb < numComb; comb++) {
            //int comb=0;
            this.atualizaTransistores(comb);
            this.criaMatriz(transistores, listaNodos);
            this.validacaoLogica(comb);
            this.ReiniciaNodos();
        }

        this.mostraTabela();
        System.out.println("");
        //mostraSaidaSimulacaoLogica();
        //this.mostraITM();

        this.caminhosPMOS = new ArrayList();
        this.caminhosNMOS = new ArrayList();
        this.criaCaminhoPMOS();
        this.criaCaminhoNMOS();

        

        //this.analisePTM_SOnF(comb);
        //this.mostraqCaminhos();
        //this.mostraCaminhos();

    }

    public void criaVetorEntradas() {

        int valor;
        for (int i = 0; i < entradas.size(); i++) {
            entradas.get(i).setCombinacoes(numComb);
        }
        for (int contador = 0; contador < numComb; contador++) {
            //preenche o valor das entradas com base na combinação
            for (int i = 0; i < entradas.size(); i++) {
                valor = (contador & (1 << i));
                if (valor != 0) {
                    valor = 1;
                } else {
                    valor = 0;
                }

                entradas.get((entradas.size() - 1) - i).setValor(valor, contador);
            }
        }

    }

    private void criaMatriz(ArrayList<Transistores> transistores, ArrayList<Nodo> listaNodos) {

        grafo = new ArrayList[listaNodos.size()][listaNodos.size()];
        //inicializa
        for (int j = 0; j < listaNodos.size(); j++) {
            for (int k = 0; k < listaNodos.size(); k++) {
                grafo[j][k] = new ArrayList<Transistores>();
            }
        }

        for (int i = 0; i < listaNodos.size(); i++) {
            for (int j = 0; j < listaNodos.size(); j++) {
                if (i == j) { // não compara os iguais
                } else {
                    for (int k = 0; k < transistores.size(); k++) {
                        if (transistores.get(k).ligaOsNodos(listaNodos.get(i), listaNodos.get(j))) {
                            //System.out.println("Arco "+transistores.get(k).getNome()+" sendo verificado ");
                            grafo[i][j].add(transistores.get(k));
                        }
                    }
                }
            }
        }
    }

    public void mostraTabela() {
        for (Nodo nodo1 : listaNodos) {
            System.out.print(nodo1.getNome() + "\t\t");
        }
        System.out.println("");
        for (int i = 0; i < listaNodos.size(); i++) {
            for (int j = 0; j < listaNodos.size(); j++) {
                if (grafo[i][j].isEmpty()) {
                    System.out.print("0");
                } else {
                    System.out.print(grafo[i][j].get(0).getNome());
                    for (int k = 1; k < grafo[i][j].size(); k++) {
                        System.out.print("," + grafo[i][j].get(k).getNome());
                    }
                }
                System.out.print("\t\t");
            }
            System.out.println("");
        }

    }

    public void atualizaTransistores(int combinacao) {
        for (int i = 0; i < entradas.size(); i++) {
            for (int j = 0; j < transistores.size(); j++) {
                if (transistores.get(j).getSinalControle().equals(entradas.get(i).getNome())) {
                    transistores.get(j).setValor(entradas.get(i).getValor(combinacao));
                }
            }
        }
    }

    public Transistores getTransistor(int i, int j, int k) {
        if (grafo[i][j].get(k) == null) {
            return null;
        } else {
            return grafo[i][j].get(k);
        }
    }

    public void validacaoLogica(int comb) {

//        Aqui a variável segurança garante que o loop dentro não será infinito, realizando um total de 10 execuções
//        Os arcos são verificados e os nodos são atualizados a cada ciclo de execução
        int seguranca = -1;
        do {
            for (int i = 0; i < listaNodos.size(); i++) {
                for (int j = i + 1; j < listaNodos.size(); j++) {
                    for (int k = 0; k < grafo[i][j].size(); k++) {

                        if (getTransistor(i, j, k) != null) {

                            if (getTransistor(i, j, k).ligaOsNodos(listaNodos.get(i), listaNodos.get(j))) {

                                if ((listaNodos.get(i).isVerificado() == true) && (listaNodos.get(j).isVerificado() == true)) {
                                } else {

                                    if ((listaNodos.get(i).isVerificado() == true)) {
                                        if ((getTransistor(i, j, k).getTipo().equals("PMOS")) && (getTransistor(i, j, k).getValor() == 0)) {
                                            listaNodos.get(j).setValor(listaNodos.get(i).getValor());
                                            listaNodos.get(j).setVerificado(true);
                                            marcados = marcados + 1;
                                        }
                                        if ((getTransistor(i, j, k).getTipo().equals("NMOS")) && (getTransistor(i, j, k).getValor() == 1)) {
                                            listaNodos.get(j).setValor(listaNodos.get(i).getValor());
                                            listaNodos.get(j).setVerificado(true);
                                            marcados = marcados + 1;
                                        }
                                    } else {
                                        if ((listaNodos.get(j).isVerificado() == true)) {
                                            if ((getTransistor(i, j, k).getTipo().equals("PMOS")) && (getTransistor(i, j, k).getValor() == 0)) {
                                                listaNodos.get(i).setValor(listaNodos.get(j).getValor());
                                                listaNodos.get(i).setVerificado(true);
                                                marcados = marcados + 1;
                                            }
                                            if ((getTransistor(i, j, k).getTipo().equals("NMOS")) && (getTransistor(i, j, k).getValor() == 1)) {
                                                listaNodos.get(i).setValor(listaNodos.get(j).getValor());
                                                listaNodos.get(i).setVerificado(true);
                                                marcados = marcados + 1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            seguranca++;
        } while ((this.getMarcados() < listaNodos.size()) && (seguranca < 10));
        for (int k = 2; k < listaNodos.size(); k++) {
            this.saidaSimulaçãoLogica[comb][k - 2] = listaNodos.get(k).getValor();
        }
//        Aqui a comparação para verificar se a saída é 0 ou 1, 
//        preenchendo a cada combinação de entrada a matriz ITM do circuito
        if (listaNodos.get(2).getValor() == 0) {
            ITM[comb][0] = 1;
            ITM[comb][1] = 0;
        } else {
            ITM[comb][0] = 0;
            ITM[comb][1] = 1;
        }
        //mostraSaidaSimulacaoLogica();
        marcados = 2;
    }

    public void ReiniciaNodos() {
        if (listaNodos == null) {
            System.out.println("Lista sem elementos...");
        } else {
            for (int i = 2; i < listaNodos.size(); i++) {
                listaNodos.get(i).setValor(99);
                listaNodos.get(i).setVerificado(false);
            }
        }
    }

    private void mostraSaidaSimulacaoLogica() {

        System.out.println("-----SAIDA DA SIMULAÇÃO LÓGICA-----");
        System.out.print("Combinação\t ");
        for (int n = 2; n < listaNodos.size(); n++) {
            System.out.print(this.listaNodos.get(n).getNome() + "\t ");
        }
        System.out.println("");
        for (int i = 0; i < numComb; i++) {
            String combi = Integer.toString(i, 2);
            while (combi.length() < this.entradas.size()) {
                combi = "0" + combi;
            }
            System.out.print("\t" + combi + "\t ");
            for (int j = 0; j < saidaSimulaçãoLogica[i].length; j++) {
                if (this.saidaSimulaçãoLogica[i][j] == 99) {
                    System.out.print("Z\t ");
                } else {
                    System.out.print(this.saidaSimulaçãoLogica[i][j] + "\t ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public void mostraITM() {
        System.out.println("----------------ITM do circuito----------------");
        System.out.println("\t 0\t 1");
        for (int i = 0; i < numComb; i++) {
            String combi = Integer.toString(i, 2);
            while (combi.length() < this.entradas.size()) {
                combi = "0" + combi;
            }
            System.out.println(combi + "\t|" + ITM[i][0] + "\t " + ITM[i][1] + "|");
        }
        System.out.println("");
    }

    //PROCURA POR CAMINHOS
    public void criaCaminhoPMOS() {
        int i = 0;
        for (int j = 2; j < listaNodos.size(); j++) {
            for (int k = 0; k < grafo[i][j].size(); k++) {

                if (getTransistor(i, j, k).getTipo().equals("PMOS")) {
                    if (getTransistor(i, j, k).ligaOsNodos(listaNodos.get(i), listaNodos.get(j))) {

                        String caminho = getTransistor(i, j, k).getNome() + "-";
                        //System.out.println("mandando o caminho comecando por "+caminho);
                        verificaPossibilidadesPMOS(caminho, i, j, k);
                    }
                }
            }
        }
    }

    public void criaCaminhoNMOS() {
        int i = 1;
        for (int j = 2; j < listaNodos.size(); j++) {
            for (int k = 0; k < grafo[i][j].size(); k++) {

                if (getTransistor(i, j, k).getTipo().equals("NMOS")) {
                    if (getTransistor(i, j, k).ligaOsNodos(listaNodos.get(i), listaNodos.get(j))) {

                        //System.out.println("arco "+getArco(i,j,k).getNome());
                        String caminho = getTransistor(i, j, k).getNome() + "-";
                        //System.out.println("mandando o caminho comecando por "+caminho);
                        verificaPossibilidadesNMOS(caminho, i, j, k);
                    }
                }
            }
        }
    }

    public void verificaPossibilidadesPMOS(String caminho, int i, int j, int k) {

        if (j == 2) { //j=2 significa ligado em OUT
            //System.out.println("entrei aqui uma vez na vida");
            caminhosPMOS.add(caminho);
        } else {
            //System.out.println("entrei no else das possibilidades"+ i+" "+j);
            i = j; //vai pro próximo nodo e examina suas possibilidades
            for (j = 2; j < listaNodos.size(); j++) {
                //System.out.println("comparando "+i+" com "+j);
                if (grafo[i][j].size() >= 1) {
                    for (int it = 0; it < grafo[i][j].size(); it++) {
                        //System.out.println("entrei no arco "+getArco(i,j,k).getNome());
                        if (getTransistor(i, j, it).getTipo().equals("PMOS")) {
                            if ((getTransistor(i, j, it).ligaOsNodos(listaNodos.get(i), listaNodos.get(j)))) {// && (matriz[i][j][teste] == 0)) {
                                if (caminho.contains(getTransistor(i, j, it).getNome())) {
                                } else {
                                    //caminho = caminho + getArco(i,j,teste).getNome();
                                    //System.out.println("caminho é "+caminho);
                                    verificaPossibilidadesPMOS(caminho + getTransistor(i, j, it).getNome()+ "-", i, j, it);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void verificaPossibilidadesNMOS(String caminho, int i, int j, int k) {

        if (j == 2) { //j=2 significa ligado em GND
            caminhosNMOS.add(caminho);
        } else {
            i = j; //vai pro próximo nodo e examina suas possibilidades
            for (j = 2; j < listaNodos.size(); j++) {
                if (grafo[i][j].size() >= 1) {
                    for (int it = 0; it < grafo[i][j].size(); it++) {
                        if (getTransistor(i, j, it).getTipo().equals("NMOS")) {
                            if ((getTransistor(i, j, it).ligaOsNodos(listaNodos.get(i), listaNodos.get(j)))) {
                                if (caminho.contains(getTransistor(i, j, it).getNome())) {
                                } else {
                                    verificaPossibilidadesNMOS(caminho + getTransistor(i, j, it).getNome()+"-", i, j, it);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void mostraCaminhos() {
        for (int i = 0; i < caminhosPMOS.size(); i++) {
            System.out.println(caminhosPMOS.get(i));
        }
        System.out.println("----NMOS----");
        for (int i = 0; i < caminhosNMOS.size(); i++) {
            System.out.println(caminhosNMOS.get(i));
        }
    }
    
    public BigDecimal[][] analiseSOnF(){
        
        Analise_SOnF a = new Analise_SOnF();
        
        a.setITM(ITM);
        
        for (int comb = 0; comb < numComb; comb++) {
            //Analise_SOnF a = new Analise_SOnF(comb);
            a.startPTM_SOnF(transistores, this.entradas, listaNodos, comb);
            if(comb==numComb-1){
//                a.mostraPTM_SOnF();
                a.calculaPTMfinal();
            }
        }
        
        //a.mostraPTM_SOnF();
        saida = a.getSaida();
        confiabilidadedaPorta = new BigDecimal(a.getConfiabilidadePorta().toString());
        return a.getPTM_SOnF();
    }
    
    public void analiseSOF(){
        Analise_SOF b = new Analise_SOF();
        b.setITM(ITM);
        //System.out.println("SOnF");
        for (int comb = 0; comb < numComb; comb++) {
            //Analise_SOnF a = new Analise_SOnF(comb);
            b.startPTM_SOF(transistores, this.entradas, listaNodos, comb, 0);
            if (comb == numComb - 1) {
//                b.mostraPTM_SOF();
                b.calculaPTMfinal();
            }
        }
        b.mostraPTM_SOF();
        saida = b.getSaida();
        confiabilidadedaPorta = new BigDecimal(b.getConfiabilidadePorta().toString());
    }

    public BigDecimal getConfiabilidadedaPorta() {
        return confiabilidadedaPorta;
    }

    public void criaArquivoSaida(String s, String porta, String conf) throws IOException {
        String path = "PTM_"+porta+"_"+ (new BigDecimal(conf).multiply(new BigDecimal("100"))).toString() + ".txt";
        File file = new File(path);
	long begin = System.currentTimeMillis();
        PrintWriter pw = new PrintWriter (file);
	
	pw.println("Arquivo gravado em : " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()));
        pw.print(s.replace("\n",System.getProperty("line.separator")));
	pw.close();
        //System.out.println("Arquivo gravado em: " + path);
    }
}
