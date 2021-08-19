/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ops;

import static ops.CommonOps.getKronecker;
import static ops.CommonOps.getMultipliedMatrix;

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
public class SPROpsFloat {
        
    public static float[][] getSignalMatrix(float[][] matrix, int[] itm) {
        
        float[][] signalMatrix = new float[2][2];
        
        float correct0 = 0f;
        float incorrect0 = 0f;
        float correct1 = 0f;
        float incorrect1 = 0f;
        
        for (int i = 1; i < itm.length; i++) {
            if(itm[i] == 0) {
                correct0 = correct0 + matrix[i-1][0];
                incorrect1 = incorrect1 + matrix[i-1][1];
            } else {
                incorrect0 = incorrect0 + matrix[i-1][0];
                correct1 = correct1 + matrix[i-1][1];
            }
        }
        
        signalMatrix[0][0] = correct0;
        signalMatrix[0][1] = incorrect1;
        signalMatrix[1][0] = incorrect0;
        signalMatrix[1][1] = correct1;
        
        return signalMatrix;
    }
    
    public static float getSPRReliability(ProbCircuit pCircuit) {
        
        float value = 1f;
        
        for (int i = 0; i < pCircuit.getProbGateLevels().size(); i++) {
            ProbGateLevel pGateLevel = pCircuit.getProbGateLevels().get(i);
            
            for (int j = 0; j < pGateLevel.getGates().size(); j++) {
                
                if(pGateLevel.getGates().get(j) instanceof ProbGate) {

                    ProbGate pGate = (ProbGate)pGateLevel.getGates().get(j);                   

                    float[][] matrix = pGate.getpInputs().get(0).getProbMatrixFloat();
                
                    if(pGate.getpInputs().size() > 1) { 
                        
                        for (int k = 1; k < pGate.getpInputs().size(); k++) {

                            matrix = getKronecker(matrix, pGate.getpInputs().get(k).getProbMatrixFloat());                           
                        }
                    }

                    matrix = getMultipliedMatrix(matrix, pGate.getType().getPtm2());
                                     
                    matrix = getSignalMatrix(matrix, pGate.getType().getItm());

                    pGate.getpOutputs().get(0).setProbMatrixFloat(matrix);
                }                                
            }           
        }
        
        
        
        for (ProbSignal pSignal : pCircuit.getProbOutputs()) {
            
            
            float correct0 = pSignal.getProbMatrixFloat()[0][0];
            float correct1 = pSignal.getProbMatrixFloat()[1][1];
            value = value * (correct0 + correct1);            
        }
        
        return value;        
                       
    }
    

    
}
