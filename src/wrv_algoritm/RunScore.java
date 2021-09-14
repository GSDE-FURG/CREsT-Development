/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrv_algoritm;

import java.math.BigDecimal;
import java.util.List;
import signalProbability.ProbGate;

/**
 * a implementação da interface RunScore representa um método para calcular os escores dos vetores
 * @author Marcio
 */
public interface RunScore {
    
    ScoreVector execute(InputVector inputVector);
    void setImprovementGates(List<ProbGate> improvementGates, BigDecimal q);
}
