/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ops;

import datastructures.InputVector;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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
public class SPROpsChuloMedio {
    
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
                    
                    BigDecimal[][] matrix;
                    
                    // Coloquei este IF para tratar portas sem entrada (geradores de 0 e 1)
                    if(pGate.getpInputs().isEmpty()) {
                        BigDecimal[][] ptmMatrix = pGate.getReliabilityMatrix();
                        matrix = new BigDecimal[2][2];
                        matrix[0][0] = ptmMatrix[0][0];
                        matrix[1][1] = ptmMatrix[0][1];
                        matrix[0][1] = BigDecimal.ZERO;
                        matrix[1][0] = BigDecimal.ZERO;
                    } else {                    
                        matrix = pGate.getpInputs().get(0).getProbMatrix();
                        if(pGate.getpInputs().size() > 1) { 
                            for (int k = 1; k < pGate.getpInputs().size(); k++) {
                                matrix = getKronecker(matrix, pGate.getpInputs().get(k).getProbMatrix());                           
                            }
                        }
                        matrix = getMultipliedMatrix(matrix, pGate.getReliabilityMatrix());

                        matrix = getSignalMatrix(matrix, pGate.getType().getItm());
                        
                        BigDecimal difference = CommonOps.getSignalMatrixDifference(matrix, 20);
                        
                        if (difference.compareTo(BigDecimal.ONE) != 0) {
                            matrix = CommonOps.getSignalMatrixDistributedError(matrix, difference, 50);                            
                        }
                    }
                    
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
            
            //value = value.multiply(correct0.add(correct1)).setScale(20, RoundingMode.HALF_DOWN);
            //value.setScale(20, RoundingMode.HALF_DOWN);
            
            value = value.multiply(correct0.add(correct1));
            value = value.setScale(13, RoundingMode.HALF_UP);

        }
        
        return value;                     
    }
    
    
    public static BigDecimal getSPRReliability(ProbCircuit pCircuit, int scale) {
        
        for (int i = 0; i < pCircuit.getProbGateLevels().size(); i++) {
            ProbGateLevel pGateLevel = pCircuit.getProbGateLevels().get(i);
            
            for (int j = 0; j < pGateLevel.getGates().size(); j++) {
                
                if(pGateLevel.getGates().get(j) instanceof ProbGate) {

                    ProbGate pGate = (ProbGate)pGateLevel.getGates().get(j);
                    
                    BigDecimal[][] matrix;
                    
                    // Coloquei este IF para tratar portas sem entrada (geradores de 0 e 1)
                    if(pGate.getpInputs().isEmpty()) {
                        BigDecimal[][] ptmMatrix = pGate.getReliabilityMatrix();
                        matrix = new BigDecimal[2][2];
                        matrix[0][0] = ptmMatrix[0][0];
                        matrix[1][1] = ptmMatrix[0][1];
                        matrix[0][1] = BigDecimal.ZERO;
                        matrix[1][0] = BigDecimal.ZERO;
                    } else {                    
                        matrix = pGate.getpInputs().get(0).getProbMatrix();
                        if(pGate.getpInputs().size() > 1) { 
                            for (int k = 1; k < pGate.getpInputs().size(); k++) {
                                matrix = getKronecker(matrix, pGate.getpInputs().get(k).getProbMatrix());                           
                            }
                        }
                        matrix = getMultipliedMatrix(matrix, pGate.getReliabilityMatrix());

                        matrix = getSignalMatrix(matrix, pGate.getType().getItm());
                        
                        BigDecimal difference = CommonOps.getSignalMatrixDifference(matrix, scale);
                        
                        if (difference.compareTo(BigDecimal.ZERO) != 0) {                        
                            BigDecimal[][] original_matrix = matrix;
                            matrix = CommonOps.getSignalMatrixDistributedError(matrix, difference, scale);
                            if(pGateLevel.getLevel() == 1) {
                                System.out.println(pGate.getpInputs() + " ===> " + pGate + " ===> " + pGate.getType() + " ===> " + difference);
                                CommonOps.matrixPrint(original_matrix);
                            }
                        }
                    }                    
                    pGate.getpOutputs().get(0).setProbMatrix(matrix);
                }                                
            }
        }                        
        return getJointOutputReliability(pCircuit.getProbOutputs(), scale);                     
    }
    
    
    public static BigDecimal getSPRReliability(ProbCircuit pCircuit, InputVector inputV, int scale) {
        
        /*
        * Setting the InputVector on ProbCircuit
        */
        inputV.setBits(pCircuit.getInputs().size());
        String vector = inputV.getBinaryString();
                
        
        for (int i = 0; i < vector.length(); i++) {
            ProbSignal probSignal = pCircuit.getProbInputs().get(i);
            if (vector.charAt(i) == '1') {
                probSignal.setProbMatrix(new BigDecimal[][]{
                    {BigDecimal.ZERO, BigDecimal.ZERO},
                    {BigDecimal.ZERO, BigDecimal.ONE}
                });
            } else {
                probSignal.setProbMatrix(new BigDecimal[][]{
                    {BigDecimal.ONE, BigDecimal.ZERO},
                    {BigDecimal.ZERO, BigDecimal.ZERO}
                });
            }
        }
        
        return getSPRReliability(pCircuit, scale);                
    }

    public static BigDecimal getSPRReliability(ProbCircuit pCircuit, InputVector inputV) {

        /*
         * Setting the InputVector on ProbCircuit
         */
        inputV.setBits(pCircuit.getInputs().size());
        String vector = inputV.getBinaryString();


        for (int i = 0; i < vector.length(); i++) {
            ProbSignal probSignal = pCircuit.getProbInputs().get(i);
            if (vector.charAt(i) == '1') {
                probSignal.setProbMatrix(new BigDecimal[][]{
                        {BigDecimal.ZERO, BigDecimal.ZERO},
                        {BigDecimal.ZERO, BigDecimal.ONE}
                });
            } else {
                probSignal.setProbMatrix(new BigDecimal[][]{
                        {BigDecimal.ONE, BigDecimal.ZERO},
                        {BigDecimal.ZERO, BigDecimal.ZERO}
                });
            }
        }

        return getSPRReliability(pCircuit);
    }
    
    
    
    public static BigDecimal getSPRReliabilityDEBUGMODE(ProbCircuit pCircuit, int scale) {
        
        ArrayList<String> debug = new ArrayList();
        //int gateCounter = 0;
        
        for (int i = 0; i < pCircuit.getProbGateLevels().size(); i++) {
            ProbGateLevel pGateLevel = pCircuit.getProbGateLevels().get(i);
            BigDecimal[] abaixo = new BigDecimal[2];
            BigDecimal[] acima = new BigDecimal[2];
            
            acima[0] = null;
            acima[1] = null;
            abaixo[0] = null;
            abaixo[1] = null;
            
            //gateCounter = gateCounter + pGateLevel.getProbGates().size();
            
            // contador de sinais corrigidos por arredondamento
            int signalCounter = 0;
            
            for (int j = 0; j < pGateLevel.getGates().size(); j++) {
                
                if(pGateLevel.getGates().get(j) instanceof ProbGate) {

                    ProbGate pGate = (ProbGate)pGateLevel.getGates().get(j);
                    
                    BigDecimal[][] matrix;
                    
                    // Coloquei este IF para tratar portas sem entrada (geradores de 0 e 1)
                    if(pGate.getpInputs().isEmpty()) {
                        BigDecimal[][] ptmMatrix = pGate.getReliabilityMatrix();
                        matrix = new BigDecimal[2][2];
                        matrix[0][0] = ptmMatrix[0][0];
                        matrix[1][1] = ptmMatrix[0][1];
                        matrix[0][1] = BigDecimal.ZERO;
                        matrix[1][0] = BigDecimal.ZERO;
                    } else {                    
                        matrix = pGate.getpInputs().get(0).getProbMatrix();
                        if(pGate.getpInputs().size() > 1) { 
                            for (int k = 1; k < pGate.getpInputs().size(); k++) {
                                matrix = getKronecker(matrix, pGate.getpInputs().get(k).getProbMatrix());                           
                            }
                        }
                        matrix = getMultipliedMatrix(matrix, pGate.getReliabilityMatrix());

                        matrix = getSignalMatrix(matrix, pGate.getType().getItm());
                        
                        BigDecimal difference = CommonOps.getSignalMatrixDifference(matrix, scale);
                        
                        /*
                        if(i == 9) {
                            System.out.println(difference);
                        }
                        */
                        /*
                        if (difference.compareTo(BigDecimal.ZERO) != 0) {                        
                            matrix = CommonOps.getSignalMatrixDistributedError(matrix, difference, scale);
                            signalCounter = signalCounter + 1;
                        }
                        */
                        if (difference.compareTo(BigDecimal.ZERO) < 0) {                        
                            matrix = CommonOps.getSignalMatrixDistributedError(matrix, difference, scale);
                            signalCounter = signalCounter + 1;
                            
                            if(acima[0] != null) {
                                if(acima[0].compareTo(difference) > 0) {
                                    acima[0] = difference;
                                }
                            } else {
                                acima[0] = difference;
                            }
                            
                            if(acima[1] != null) {
                                if(acima[1].compareTo(difference) < 0) {
                                    acima[1] = difference;
                                }
                            } else {
                                acima[1] = difference;
                            }
                        }
                        
                        if (difference.compareTo(BigDecimal.ZERO) > 0) {                        
                            matrix = CommonOps.getSignalMatrixDistributedError(matrix, difference, scale);
                            signalCounter = signalCounter + 1;
                            
                            if(abaixo[0] != null) {
                                if(abaixo[0].compareTo(difference) > 0) {
                                    abaixo[0] = difference;
                                }
                            } else {
                                abaixo[0] = difference;
                            }
                            
                            if(abaixo[1] != null) {
                                if(abaixo[1].compareTo(difference) < 0) {
                                    abaixo[1] = difference;
                                }
                            } else {
                                abaixo[1] = difference;
                            }
                        }
                    }
                    
                    pGate.getpOutputs().get(0).setProbMatrix(matrix);
                    //matrixPrint(pGate.getpOutputs().get(0).getProbMatrix());
                }                                
            }
            
            //BigDecimal partialReliability = getJointOutputReliability(pGateLevel.getProbOutputs(), scale);
            //BigDecimal intrinsica = new BigDecimal(reliability).pow(gateCounter);
            //BigDecimal differnceIntrinsica = partialReliability.divide(intrinsica, scale, RoundingMode.HALF_UP);
            debug.add("" + signalCounter + "/" + pGateLevel.getProbGates().size() + 
                      " ==> (acima) [" + acima[0] + "," + acima[1] + "]" +
                      " ==> (abaixo) [" + abaixo[0] + "," + abaixo[1] + "]"
                      //" ==> (INTR)" + intrinsica +
                     // " ==> (SPR/INTR)" + differnceIntrinsica
            );
        }                
        
        for (int i = 0; i < debug.size(); i++) {
            System.out.println("Level " + i + " ==> " + debug.get(i));
        }
        
        return getJointOutputReliability(pCircuit.getProbOutputs(), scale);                     
    }
    
    private static BigDecimal getJointOutputReliability(ArrayList<ProbSignal> pSignals, int scale) {
        
        BigDecimal value = BigDecimal.ONE;
        
        for (ProbSignal pSignal : pSignals) {            
            
            BigDecimal correct0 = pSignal.getProbMatrix()[0][0];
            BigDecimal correct1 = pSignal.getProbMatrix()[1][1];
            
            value = value.multiply(correct0.add(correct1));
            value = value.setScale(scale, RoundingMode.HALF_UP);

        }
        
        return value;
    }


    
}
