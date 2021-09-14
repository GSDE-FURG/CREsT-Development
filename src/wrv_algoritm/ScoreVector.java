/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrv_algoritm;

import java.math.BigDecimal;

/**
 * a classe ScoreVector representa um InputVector e o valor de escore associado a este vetor
 *
 * @author Marcio
 */
public class ScoreVector {
    
    private final InputVector inputVector;
    private final BigDecimal score;

    public ScoreVector(InputVector inputVector, BigDecimal score) {
        this.inputVector = inputVector;
        this.score = score;
    }

    public InputVector getInputVector() {
        return inputVector;
    }

    public BigDecimal getScore() {
        return score;
    }   
}
