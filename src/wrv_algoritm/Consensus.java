/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrv_algoritm;

import java.util.List;
import java.util.Map;

/**
 * a implementação da interface deve, a partir de uma lista de vetores de entrada,
 * retornar um map contendo a posição de um bit descoberto em um InputVector e
 * o valor do bit (0 ou 1)
 * @author Marcio
 */

public interface Consensus {
    
    Map<Integer,Character> execute(List<ScoreVector> scoreVectors);
    
}
