/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schivittz;

import java.util.ArrayList;

/**
 *
 * @author Rafael Schivittz
 */
class Nodo {

    private String nome;
    private ArrayList<Transistores> listaDeTransistores;
    private int valor;
    private boolean verificado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void addTransistor(Transistores t) {
        this.listaDeTransistores.add(t);
    }

    public ArrayList<Transistores> getListaDeTransistores() {
        return listaDeTransistores;
    }

    public void setListaDeTransistores(ArrayList<Transistores> listaDeTransistores) {
        this.listaDeTransistores = listaDeTransistores;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

}
