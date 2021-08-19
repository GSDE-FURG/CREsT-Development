/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schivittz;

import java.math.BigDecimal;

/**
 *
 * @author Rafael Schivittz
 */
class Transistores {

    private String nome;
    private Nodo nodo1;
    private Nodo nodo2;
    private String sinalControle;
    private int valor;
    private BigDecimal confiabilidade;

    public BigDecimal getConfiabilidade() {
        return confiabilidade;
    }

    public void setConfiabilidade(BigDecimal confiabilidade) {
        this.confiabilidade = confiabilidade;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    private String tipo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Nodo getNodo1() {
        return nodo1;
    }

    public void setNodo1(Nodo nodo1) {
        this.nodo1 = nodo1;
    }

    public Nodo getNodo2() {
        return nodo2;
    }

    public void setNodo2(Nodo nodo2) {
        this.nodo2 = nodo2;
    }

    public String getSinalControle() {
        return sinalControle;
    }

    public void setSinalControle(String sinalControle) {
        this.sinalControle = sinalControle;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    void setNodo(Nodo nodo1, Nodo nodo2) {
        this.nodo1 = nodo1;
        this.nodo2 = nodo2;
    }

    public boolean ligaOsNodos(Nodo n1, Nodo n2) {
        boolean value = false;
        if ((nodo1.getNome().equals(n1.getNome()) && (nodo2.getNome().equals(n2.getNome()))) || (((nodo1.getNome().equals(n2.getNome())) && (nodo2.getNome().equals(n1.getNome()))))) {
            value = true;
        }
        return value;
    }

}
