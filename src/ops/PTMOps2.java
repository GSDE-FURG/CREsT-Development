/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ops;


import java.math.BigDecimal;


import java.util.ArrayList;
import java.util.Arrays;

import java.util.LinkedHashMap;
import java.util.Map;

import signalProbability.ProbCircuit;
import signalProbability.ProbGate;
import signalProbability.ProbGateLevel;
import signalProbability.ProbInterLevel;
import signalProbability.ProbSignal;


/**
 *
 * @author matheus
 */
public class PTMOps2 {
    
    private static final int[] WIRE = new int[]{2, 0, 1};
    
    public static BigDecimal[][] getWireMatrix() {        
        
        BigDecimal[][] wireMatrix = new BigDecimal[][]{{BigDecimal.ONE, BigDecimal.ZERO},{BigDecimal.ZERO, BigDecimal.ONE}};
        
        return wireMatrix;
    }
    
    public static BigDecimal[][] getGateLevelMatrix(ProbGateLevel gateLevel) {
        
       BigDecimal[][] gateLevelMatrix;                

        if(gateLevel.getGates().get(0) instanceof ProbGate) {
            ProbGate pGate = (ProbGate)gateLevel.getGates().get(0);
            gateLevelMatrix = pGate.getReliabilityMatrix();
        } else {
            gateLevelMatrix = getWireMatrix();
        }        
        
        
        for (int i = 1; i < gateLevel.getGates().size(); i++) {
            
            
            Object gate = gateLevel.getGates().get(i);
            if (gate instanceof ProbGate) {                
                ProbGate pGate = (ProbGate)gateLevel.getGates().get(i);                
                gateLevelMatrix = CommonOps.getKronecker(gateLevelMatrix, pGate.getReliabilityMatrix());
            } else {                
                gateLevelMatrix = CommonOps.getKronecker(gateLevelMatrix, getWireMatrix());                                                
            }
            
        }
        
        return gateLevelMatrix;
    }
    
    public static BigDecimal getCircuitReliabilityByPTM(ProbCircuit pCircuit) {
        
        BigDecimal value = new BigDecimal("0");
        BigDecimal[][] ptmMatrix;
        
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

            ptmMatrix = CommonOps.getMultipliedMatrix(ptmMatrix, getGateLevelMatrix(pGateLevel));
            

        }
        
        for (int i = 1; i < itm.length; i++) {

            value = value.add(ptmMatrix[i-1][itm[i]]);
        }
        
        value = value.divide(new BigDecimal((itm.length - 1)));
        
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
    
    public static BigDecimal[][] getInterLevelMultiplication(ProbInterLevel pInterLevel, BigDecimal[][] matrix) {
        
        int[] interLevelVector = getInterconnection(pInterLevel);
        
        BigDecimal[][] result = new BigDecimal[interLevelVector.length - 1][matrix[0].length];
        
        for (int i = 0; i < result[0].length; i++) {
            for (int j = 0; j < result.length; j++) {
                result[j][i] = matrix[interLevelVector[j+1]][i];
            }            
        }
        
        return result;
    }
    
    public static BigDecimal[][] getInterLevelMultiplication(BigDecimal[][] matrix, ProbInterLevel pInterLevel) {
        
        int[] interLevelVector = getInterconnection(pInterLevel);
        //int[] interLevelVector = new int[]{4,2,2};
        
        BigDecimal[][] result = new BigDecimal[matrix.length][interLevelVector[0]];
        
        for(BigDecimal[] bigVector : result) {
            Arrays.fill(bigVector, BigDecimal.ZERO);
        }
        
        for (int i = 1; i < interLevelVector.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[j][interLevelVector[i]] = result[j][interLevelVector[i]].add(matrix[j][i-1]);
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
           
}
