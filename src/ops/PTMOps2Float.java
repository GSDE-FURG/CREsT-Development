/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ops;



import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.Map;

import signalProbability.ProbCircuit;
import signalProbability.ProbGate;
import signalProbability.ProbGateLevel;
import signalProbability.ProbInterLevel;
import signalProbability.ProbSignal;

import static ops.CommonOps.getKronecker;
import static ops.CommonOps.getMultipliedMatrix;


/**
 *
 * @author matheus
 */
public class PTMOps2Float {
    
    private static final int[] WIRE = new int[]{2, 0, 1};
    
    public static float[][] getWireMatrix() {        
        
        float[][] wireMatrix = new float[][]{{1f, 0f},{0f, 1f}};
        
        return wireMatrix;
    }
    
    public static float[][] getGateLevelMatrix(ProbGateLevel gateLevel) {
        
       float[][] gateLevelMatrix;                

        if(gateLevel.getGates().get(0) instanceof ProbGate) {
            ProbGate pGate = (ProbGate)gateLevel.getGates().get(0);
            gateLevelMatrix = pGate.getReliabilityMatrix2();
        } else {
            gateLevelMatrix = getWireMatrix();
        }        
        
        for (int i = 1; i < gateLevel.getGates().size(); i++) {
            Object gate = gateLevel.getGates().get(i);
            if (gate instanceof ProbGate) {

                ProbGate pGate = (ProbGate)gateLevel.getGates().get(i);                
                gateLevelMatrix = getKronecker(gateLevelMatrix, pGate.getReliabilityMatrix2());
            } else {

                gateLevelMatrix = getKronecker(gateLevelMatrix, getWireMatrix());                                                
            }

        }
        
        return gateLevelMatrix;
    }
    
    public static float getCircuitReliabilityByPTM(ProbCircuit pCircuit) {

        
        float value = 0f;
        float[][] ptmMatrix;
        
        int[] itm = getCircuitITM(pCircuit);        

        
        ProbInterLevel firstPInterLevel = pCircuit.getProbInterLevels().get(0);
        ProbGateLevel firstGateLevel = pCircuit.getProbGateLevels().get(0);

        
        if(!firstPInterLevel.getInSignals().equals(firstPInterLevel.getOutSignals())) {  

            ptmMatrix = getInterLevelMultiplication(firstPInterLevel, getGateLevelMatrix(firstGateLevel));

        } else {
            ptmMatrix = getGateLevelMatrix(firstGateLevel);
        }
        
        for (int i = 1; i < pCircuit.getProbGateLevels().size(); i++) {
            ProbInterLevel pInterLevel = pCircuit.getProbInterLevels().get(i);
            ProbGateLevel pGateLevel = pCircuit.getProbGateLevels().get(i);
            
            if(!pInterLevel.getInSignals().equals(pInterLevel.getOutSignals())) {                
                ptmMatrix = getInterLevelMultiplication(ptmMatrix, pInterLevel);                
            }
            
            ptmMatrix = getMultipliedMatrix(ptmMatrix, getGateLevelMatrix(pGateLevel));
        }
        
        for (int i = 1; i < itm.length; i++) {
            value += ptmMatrix[i-1][itm[i]];
        }
        
        value = value / (itm.length - 1);
        
        return value;
    }
    
    public static int[] getCircuitITM(ProbCircuit pCircuit) {
        int[] result;
        
        ProbInterLevel firstPInterLevel = pCircuit.getProbInterLevels().get(0);
        ProbGateLevel firstGateLevel = pCircuit.getProbGateLevels().get(0);
        
        if(!firstPInterLevel.getInSignals().equals(firstPInterLevel.getOutSignals())) {
            
            result = getInterconnection(firstPInterLevel);
            
            result = getItmMultiplication(result, getGateLevelItm(firstGateLevel));

            
        } else {
            
            result = getGateLevelItm(firstGateLevel);
        }
        
        for (int i = 1; i < pCircuit.getProbGateLevels().size(); i++) {
            
            ProbInterLevel pInterLevel = pCircuit.getProbInterLevels().get(i);
            ProbGateLevel pGateLevel = pCircuit.getProbGateLevels().get(i);
            
            if(!pInterLevel.getInSignals().equals(pInterLevel.getOutSignals())) {
                
                result = getItmMultiplication(result, getInterconnection(pInterLevel));
                
            }
            
            result = getItmMultiplication(result, getGateLevelItm(pGateLevel));                        
        }
        
        return result;
    }
    
    public static int[] getInterconnection(ProbInterLevel pInterLevel) {
        
        ArrayList<ProbSignal> inSignals = pInterLevel.getInSignals();
        ArrayList<ProbSignal> outSignals = pInterLevel.getOutSignals();
        
        //System.out.println("inSignals: " + inSignals);
        //System.out.println("outSignals: " + outSignals);
        
        int[] interArray = new int[PTMOps.PowInt(2, inSignals.size()) + 1];
        
        interArray[0] = PTMOps.PowInt(2, outSignals.size());
        
        Map<String, Integer> dict = new LinkedHashMap<>();
        int flag = 0;
        
        int[] mapping = new int[outSignals.size()];
        
        for (ProbSignal inSignal : inSignals) {
            dict.put(inSignal.getId(), flag++);
        }
        
        for (int i = 0; i < outSignals.size(); i++) {
            
            mapping[i] = dict.get(outSignals.get(i).getId());
            
            
        }
 
        
        String result = "";
        for (int i = 0; i < interArray.length - 1; i++) {
            
            String numBits = "%"+inSignals.size()+"s";               
        
            String binary = String.format(numBits, Integer.toBinaryString(i)).replace(' ', '0');
            
            
            char[] charArray = binary.toCharArray();
            
            
            for (int r : mapping) {
                result = result + charArray[r];
            }
            
            interArray[i+1] = Integer.parseInt(result, 2);

            result = "";
        }
        
        return interArray;
    }
    
    public static int[] getItmKronecker(int[] itm1, int[] itm2) {
        int[] result = new int[(itm1.length - 1) * (itm2.length -1) + 1];
        int flag = 1;
        
        result[0] = itm1[0] * itm2[0];
        
        for (int i = 1; i < itm1.length; i++) {
            for (int j = 1; j < itm2.length; j++) {
                result[flag++] = itm1[i] * itm2[0] + itm2[j];
            }
        }                
        
        return result;
    }
    
    public static int[] getItmMultiplication(int[] itm1, int[] itm2) {
        int[] result = new int[itm1.length];
        result[0] = itm2[0];
        
        for (int i = 1; i < itm1.length; i++) {
            result[i] = itm2[itm1[i] +1];
        }
        
        return result;
    }
    
    public static float[][] getInterLevelMultiplication(ProbInterLevel pInterLevel, float[][] matrix) {
        
        int[] interLevelVector = getInterconnection(pInterLevel);
        
        float[][] result = new float[interLevelVector.length - 1][matrix[0].length];
        
        for (int i = 0; i < result[0].length; i++) {
            for (int j = 0; j < result.length; j++) {
                result[j][i] = matrix[interLevelVector[j+1]][i];
            }            
        }
        
        return result;
    }
    
    public static float[][] getInterLevelMultiplication(float[][] matrix, ProbInterLevel pInterLevel) {
        
        int[] interLevelVector = getInterconnection(pInterLevel);
        //int[] interLevelVector = new int[]{4,2,2};
        
        float[][] result = new float[matrix.length][interLevelVector[0]];
        
        /*
        for(float[] bigVector : result) {
            Arrays.fill(bigVector, float.ZERO);
        }
        */
        for (int i = 1; i < interLevelVector.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[j][interLevelVector[i]] += matrix[j][i-1];
            }
        }
        
        return result;
    }
    
    public static int[] getGateLevelItm(ProbGateLevel pGateLevel) {
        int[] result;
        int size = pGateLevel.getGates().size();
        int start = size - 1;
                
        Object obj = pGateLevel.getGates().get(0);
        
        if(obj instanceof ProbGate) {
            result = ((ProbGate)obj).getType().getItm();
            
        } else {
            result = WIRE;
        }

        for (int i = 1; i < size; i++) {
            Object gate = pGateLevel.getGates().get(i);

            if(gate instanceof ProbGate) {
                result = getItmKronecker(result, ((ProbGate)gate).getType().getItm() );
            } else {
                result = getItmKronecker(result, WIRE);
            }
        }
        
        return result;
    }
    
    
    
    public static float getCircuitReliabilityByPTMSource(ProbCircuit pCircuit) {

        
        float value = 0f;
        float[][] ptmMatrix;
        
        int[] itm = getCircuitITM(pCircuit);        

        
        ProbInterLevel firstPInterLevel = pCircuit.getProbInterLevels().get(0);
        ProbGateLevel firstGateLevel = pCircuit.getProbGateLevels().get(0);
        firstPInterLevel.getInSignals().get(0).setProbMatrixFloat(new float[][]{{0.5f,0f},{0f,0.5f}});
        
        ptmMatrix = firstPInterLevel.getInSignals().get(0).getProbMatrixFloat();
        
        for (int i = 1; i < firstPInterLevel.getInSignals().size(); i++) {
            ProbSignal pSignal = firstPInterLevel.getInSignals().get(i);
            pSignal.setProbMatrixFloat(new float[][]{{0.5f,0f},{0f,0.5f}});
            
            ptmMatrix = getKronecker(ptmMatrix, pSignal.getProbMatrixFloat());

        }
        
        
        
        if(!firstPInterLevel.getInSignals().equals(firstPInterLevel.getOutSignals())) {   
            ptmMatrix = getInterLevelMultiplication(ptmMatrix, firstPInterLevel);
        }
        
        ptmMatrix = getMultipliedMatrix(ptmMatrix, getGateLevelMatrix(firstGateLevel));
        
        
        
        for (int i = 1; i < pCircuit.getProbGateLevels().size(); i++) {
            ProbInterLevel pInterLevel = pCircuit.getProbInterLevels().get(i);
            ProbGateLevel pGateLevel = pCircuit.getProbGateLevels().get(i);
            
            if(!pInterLevel.getInSignals().equals(pInterLevel.getOutSignals())) {                
                ptmMatrix = getInterLevelMultiplication(ptmMatrix, pInterLevel);                
            }
            
            ptmMatrix = getMultipliedMatrix(ptmMatrix, getGateLevelMatrix(pGateLevel));
            
           // System.out.println("NÂO ESTOUREI AINDA... LEVEL: " + i);
        }
        
        for (int i = 1; i < itm.length; i++) {
            value += ptmMatrix[i-1][itm[i]];
        }
        
        return value;
    }
}
