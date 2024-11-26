/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schivittz;

/**
 *
 * @author Rafael Schivittz
 */
public class Entrada {

    private String nome;
    private int[] valor;
    private double probabilidadeSerUm;

    public double getProbabilidadeSerUm() {
        return probabilidadeSerUm;
    }

    public void setProbabilidadeSerUm(double probabilidadeSerUm) {
        this.probabilidadeSerUm = probabilidadeSerUm;
    }

    public void setCombinacoes(int combinacao) {
        valor = new int[combinacao];
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getValor(int i) {
        return valor[i];
    }

    public void setValor(int valor, int i) {
        if ((valor == 1) || (valor == 0)) {
            this.valor[i] = valor;
        } else {
            System.out.println("Valor inválido");
        }
    }

}
