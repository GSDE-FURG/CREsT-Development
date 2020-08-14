/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schivittz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.math.BigDecimal;

/**
 *
 * @author Rafael Schivittz
 */
public class LeArquivo {

    private ArrayList<Transistores> listaTransistores = new ArrayList();
    private ArrayList<Entrada> listaEntradas = new ArrayList();
    private ArrayList<Nodo> listaNodos = new ArrayList();
    private BigDecimal confiabilidade;

    public ArrayList<Transistores> getListaTransistores() {
        return listaTransistores;
    }

    public void setListaTransistores(ArrayList<Transistores> listaTransistores) {
        this.listaTransistores = listaTransistores;
    }

    public ArrayList<Entrada> getListaEntradas() {
        return listaEntradas;
    }

    public void setListaEntradas(ArrayList<Entrada> listaEntradas) {
        this.listaEntradas = listaEntradas;
    }

    public ArrayList<Nodo> getListaNodos() {
        return listaNodos;
    }

    public void setListaNodos(ArrayList<Nodo> listaNodos) {
        this.listaNodos = listaNodos;
    }

    public BigDecimal getConfiabilidade() {
        return confiabilidade;
    }

    public void setConfiabilidade(BigDecimal confiabilidade) {
        this.confiabilidade = confiabilidade;
    }

    public void mostraTudo() {
        System.out.println("Confiabilidade da tecnologia = " + this.confiabilidade);
        for (Iterator<Transistores> iterator = listaTransistores.iterator(); iterator.hasNext();) {
            Transistores next = iterator.next();
            //System.out.println("Transistor " + next.getNome() + " Controlado por " + next.getSinalControle() + " Liga o nodo " + next.getNodo1().getNome() + " ao nodo " + next.getNodo2().getNome() + " tipo " + next.getTipo());
        }

        for (Iterator<Nodo> iterator = listaNodos.iterator(); iterator.hasNext();) {
            Nodo next = iterator.next();
            //System.out.println(next.getNome() + " valor " + next.getValor());
        }
        System.out.print(listaEntradas.size() + " entradas: \t");
        for (Iterator<Entrada> iterator = listaEntradas.iterator(); iterator.hasNext();) {
            Entrada next = iterator.next();
            System.out.print(next.getNome() + "\t");
        }
        System.out.println("\n");
    }

    public void Ler(String localArquivo, int verificador) throws IOException {

        try {
            System.out.println(localArquivo);
            FileReader arq = new FileReader(localArquivo);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = "";

            String[] stringDividida = null;

            while (linha != null) {
                //System.out.printf("%s\n", linha);
                linha = lerArq.readLine();

                //PARTE FINAL DA LEITURA DO ARQUIVO --------- TERMINA TUDO AQUI
                if (linha.contains(".ends")){
                    arq.close();
                    break;
                } 
                else {
                    String[] l = linha.split(" ");
                    Nodo nodo;
                    switch (l[0]) {
                        case "supply=":
                            stringDividida = linha.split("= ");
                            nodo = new Nodo();
                            nodo.setValor(1);
                            nodo.setNome(stringDividida[1]);
                            nodo.setVerificado(true);

                            listaNodos.add(nodo);
                            break;

                        case "ground=":
                            stringDividida = linha.split("= ");
                            nodo = new Nodo();
                            nodo.setValor(0);
                            nodo.setNome(stringDividida[1]);
                            nodo.setVerificado(true);

                            listaNodos.add(nodo);
                            break;

                        case "saida=":
                            stringDividida = linha.split("= ");
                            nodo = new Nodo();
                            nodo.setValor(99);
                            nodo.setNome(stringDividida[1]);

                            listaNodos.add(nodo);
                            break;

                        case "confiabilidade=":
                            if(verificador==0) stringDividida = linha.split("= ");
                            if(verificador==0) confiabilidade = new BigDecimal(stringDividida[1]);
                            break;
                            
                        case "entradas=":
                            int numsplit = 0;
                            stringDividida = linha.split(" ");
                            numsplit = stringDividida.length;
                            for (int i = 1; i < numsplit; i++) {
                                AddNovaEntrada(stringDividida[i]);

                            }
                            break;

                        default:
                            stringDividida = linha.split(" ");
                            AddTransistor(stringDividida);
                    } 
                }
            }
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
        
        //}    
    }

    public void AddNovaEntrada(String stringDividida) {

        if(stringDividida.contains("-")){
            String[] dividido = stringDividida.split("-");
            Entrada x = new Entrada();
            x.setNome(dividido[0]);
            double valor = Double.parseDouble(dividido[1]);
            valor = valor/100;
            x.setProbabilidadeSerUm(valor);

            listaEntradas.add(x);
        }
        else{
            //String[] dividido = stringDividida.split("-");
            Entrada x = new Entrada();
            x.setNome(stringDividida);
            double valor = Double.parseDouble("0");
            valor = valor/100;
            x.setProbabilidadeSerUm(valor);

            listaEntradas.add(x);
        }
        
    }

    private void AddTransistor(String[] stringDividida) {
        Transistores ntransistor = new Transistores();
        if ((stringDividida[5].contains("P")) || (stringDividida[5].contains("p"))) {
            ntransistor.setTipo("PMOS");
        } else {
            ntransistor.setTipo("NMOS");
        }
        ntransistor.setNodo(AddNovoNodo(stringDividida[1]), AddNovoNodo(stringDividida[3]));
        ntransistor.setNome(stringDividida[0].substring(1, stringDividida[0].length()));
        ntransistor.setSinalControle(stringDividida[2]);

        boolean teste = false;
        for (int i = 0; i < listaTransistores.size(); i++) {
            if ((listaTransistores.get(i).getNome().equals(ntransistor.getNome())) && (listaTransistores.get(i).getTipo().equals(ntransistor.getTipo()))) {
                listaTransistores.get(i).setNodo(ntransistor.getNodo1(), ntransistor.getNodo2());
                teste = true;
            }
        }
        if (teste == false) {
            listaTransistores.add(ntransistor);
        }
    }

    private Nodo AddNovoNodo(String nodo1) {
        Nodo nodo;
        nodo = new Nodo();
        nodo.setNome(nodo1);
        nodo.setValor(99);

        boolean value = false;
        for (int i = 0; i < listaNodos.size(); i++) {
            if (listaNodos.get(i).getNome().equals(nodo.getNome())) {
                //System.out.println("Nodo "+listaNodos.get(i).getNome() + " igual a "+nodo.getNome());
                value = true;
            }
        }
        if (value == false) {
            //System.out.println("Adicionei nodo " + nodo1.getNome());
            listaNodos.add(nodo);
        }

        return nodo;

    }
    
    public void InsereProbEntradas(String prob){
            for(int i=0;i<listaEntradas.size();i++){
                double valor = Double.parseDouble(prob);
                valor = valor/100;
                this.listaEntradas.get(i).setProbabilidadeSerUm(valor);
            }
    }

}
