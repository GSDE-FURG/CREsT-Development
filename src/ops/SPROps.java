/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ops;

import java.math.BigDecimal;
import java.math.RoundingMode;
import static ops.CommonOps.getKronecker;
import static ops.CommonOps.getMultipliedMatrix;

import signalProbability.ProbCircuit;
import signalProbability.ProbGate;
import signalProbability.ProbGateLevel;
import signalProbability.ProbSignal;

/**
 *
 * @author matheus
 */
public class SPROps {
    
    public static BigDecimal[][] getSignalMatrixM(BigDecimal[][] matrix, BigDecimal[][] itm) {
        
        BigDecimal[][] signalMatrix = new BigDecimal[2][2];
        
        BigDecimal correct0 = BigDecimal.ZERO;
        BigDecimal incorrect0 = BigDecimal.ZERO;
        BigDecimal correct1 = BigDecimal.ZERO;
        BigDecimal incorrect1 = BigDecimal.ZERO;
        
        for (int i = 0; i < itm.length; i++) {
            BigDecimal value = itm[i][0];
            if(value.equals(BigDecimal.ONE)) {
                correct0 = correct0.add(matrix[i][0]);
            } else {
                incorrect0 = incorrect0.add(matrix[i][0]);
            }
        }
        
        for (int i = 0; i < itm.length; i++) {
            BigDecimal value = itm[i][1];
            if(value.equals(BigDecimal.ONE)) {
                correct1 = correct1.add(matrix[i][1]);
            } else {
                incorrect1 = incorrect1.add(matrix[i][1]);
            }
        }
        
        signalMatrix[0][0] = correct0;
        signalMatrix[0][1] = incorrect1;
        signalMatrix[1][0] = incorrect0;
        signalMatrix[1][1] = correct1;
        
        return signalMatrix;
    }
    
    
    public static BigDecimal[][] getSignalMatrix(BigDecimal[][] matrix, int[] itm) {
        
        BigDecimal[][] signalMatrix = new BigDecimal[2][2];
        
        BigDecimal correct0 = BigDecimal.ZERO;
        BigDecimal incorrect0 = BigDecimal.ZERO;
        BigDecimal correct1 = BigDecimal.ZERO;
        BigDecimal incorrect1 = BigDecimal.ZERO;
        
        for (int i = 1; i < itm.length; i++) {
            if(itm[i] == 0) {
                correct0 = correct0.add(matrix[i-1][0]);
                incorrect1 = incorrect1.add(matrix[i-1][1]);
            } else {
                incorrect0 = incorrect0.add(matrix[i-1][0]);
                correct1 = correct1.add(matrix[i-1][1]);
            }
        }
        
        signalMatrix[0][0] = correct0;
        signalMatrix[0][1] = incorrect1;
        signalMatrix[1][0] = incorrect0;
        signalMatrix[1][1] = correct1;
        
        return signalMatrix;
    }
    
    public static BigDecimal getSPRReliability(ProbCircuit pCircuit) {
        
        BigDecimal value = BigDecimal.ONE;
        
        for (int i = 0; i < pCircuit.getProbGateLevels().size(); i++) {
            ProbGateLevel pGateLevel = pCircuit.getProbGateLevels().get(i);
            
            for (int j = 0; j < pGateLevel.getGates().size(); j++) {
                
                if(pGateLevel.getGates().get(j) instanceof ProbGate) {

                    ProbGate pGate = (ProbGate)pGateLevel.getGates().get(j);

                    BigDecimal[][] matrix = pGate.getpInputs().get(0).getProbMatrix();
                
                    if(pGate.getpInputs().size() > 1) { 
                        
                        for (int k = 1; k < pGate.getpInputs().size(); k++) {                            
                            matrix = getKronecker(matrix, pGate.getpInputs().get(k).getProbMatrix());                           
                        }
                    }

                    matrix = getMultipliedMatrix(matrix, pGate.getReliabilityMatrix());
                                     
                    matrix = getSignalMatrix(matrix, pGate.getType().getItm());

                    pGate.getpOutputs().get(0).setProbMatrix(matrix);

                    //matrixPrint(pGate.getpOutputs().get(0).getProbMatrix());
                }                                
            }           
        }
        
        
        
        for (ProbSignal pSignal : pCircuit.getProbOutputs()) {
            
            //BigDecimal correct0 = pSignal.getProbMatrix()[0][0].setScale(20, RoundingMode.HALF_UP);
            //BigDecimal correct1 = pSignal.getProbMatrix()[1][1].setScale(20, RoundingMode.HALF_UP);
            
            BigDecimal correct0 = pSignal.getProbMatrix()[0][0];
            BigDecimal correct1 = pSignal.getProbMatrix()[1][1];
            
            value = value.multiply(correct0.add(correct1)).setScale(20, RoundingMode.HALF_UP);
            value.setScale(20, RoundingMode.HALF_UP);
        }
        
        return value;
        //matrixPrint(pCircuit.getProbOutputs().get(0).getProbMatrix());
        //matrixPrint(pCircuit.getProbOutputs().get(1).getProbMatrix());
                       
    }
    

    
}
