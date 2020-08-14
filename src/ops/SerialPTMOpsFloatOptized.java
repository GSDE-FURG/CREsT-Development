/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ops;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import signalProbability.ProbCircuit;
import signalProbability.ProbGateLevel;

import static ops.CommonOps.powLong;
import signalProbability.ProbGate;
import signalProbability.ProbInterLevel;

/**
 *
 * @author matheus
 */

public class SerialPTMOpsFloatOptized {
    
    
    
    public static float getGateLevelMatrixIndex(ProbGateLevel pGateLevel, long x, long y) {

        ArrayList<Object> gates = pGateLevel.getGates();
        
        float value = 1f;
        
        
        
        long totalX = powLong(2, (long)pGateLevel.getIn());
        long totalY = powLong(2, (long)pGateLevel.getOut());        
                
        long xIndex = x + 1;
        long yIndex = y + 1;        
        
        if(xIndex > totalX || yIndex > totalY) {
            System.err.println("BAD INDEX NUMBERfasfasfasfasf");
            System.err.println("TotalX"+totalX);
            System.err.println("TotalY"+totalY);
            System.err.println("xIndex"+xIndex);
            System.err.println("yIndex"+yIndex);
            System.err.println("GateLevel"+pGateLevel.getLevel()+"-->"+x+"x"+y);
        } else {        
            
            long xMinLimit;
            long xMaxLimit;
            long yMinLimit;
            long yMaxLimit;

            long aux;           

            xMinLimit = 1;
            yMinLimit = 1;

            xMaxLimit = totalX;
            yMaxLimit = totalY;

            for (int i = 0; i < gates.size(); i++) {

                int xGateMatrix = 2;
                int yGateMatrix = 2;                        

                if(gates.get(i) instanceof ProbGate) {

                    ProbGate pGate = (ProbGate)gates.get(i);                                       
                    xGateMatrix = PTMOps.PowInt(xGateMatrix, pGate.getpInputs().size());
                    yGateMatrix = PTMOps.PowInt(yGateMatrix, pGate.getpOutputs().size());

                }
                
                aux = xMaxLimit - xMinLimit;                                                
                aux = aux + 1;                                
                aux = aux / xGateMatrix;                
                aux = aux - 1;

                int xGateIndex = 0;
                int yGateIndex = 0;

                for (int j = 0; j < xGateMatrix; j++) {                                
                    xMaxLimit = xMinLimit + aux;                                

                    if((xIndex > xMinLimit || xIndex == xMinLimit) && (xIndex < xMaxLimit) || (xIndex == xMaxLimit)) {                    
                        xGateIndex = j;                    
                        break;
                    }
                    
                    xMinLimit = xMaxLimit + 1;
                }

                aux = yMaxLimit - yMinLimit;
                aux = aux + 1;
                aux = aux / yGateMatrix;
                aux = aux - 1;

                for (int j = 0; j < yGateMatrix; j++) {
                    
                    yMaxLimit = yMinLimit + aux;

                    if((yIndex > yMinLimit || yIndex == yMinLimit) && ((yIndex < yMaxLimit) || yIndex  == yMaxLimit)) {
                        //yGateIndex = j+1;
                        yGateIndex = j;
                        break;
                    }
                    
                    yMinLimit = yMaxLimit + 1;
                }
                
                value = value * getPTMGateIndex(gates.get(i), xGateIndex, yGateIndex);
                //System.out.println(value);
            }

            //System.out.println(output);
            return value;
        }
        
        return -1f;
    }
    
    public static int getInterconnectionMatrixIndex(ProbInterLevel pInterLevel, long x, long y) {                                        
        
        int x1 = (int) x;
        int y1 = (int) y;
        if(pInterLevel.getInterLevelMatrixIndex(x1, y1)) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public static float getMultiplicationMatrixIndex(Object levelA, Object levelB, long x, long y) {
        
        if (levelA instanceof ProbInterLevel) {
            return getMultiplicationMatrixIndex((ProbInterLevel)levelA, (ProbGateLevel)levelB, x, y);
        } else {
            if (levelB instanceof ProbInterLevel) {
                return getMultiplicationMatrixIndex((ProbGateLevel)levelA, (ProbInterLevel)levelB, x, y);
            } else {
                return getMultiplicationMatrixIndex((ProbGateLevel)levelA, (ProbGateLevel)levelB, x, y);
            }
        }
    }
    
    public static float getMultiplicationMatrixIndex(ProbInterLevel levelA,  ProbGateLevel levelB, long x, long y) {
        
        float value = 0f;        
        
        if(levelA.getOutSignals().size() != levelB.getIn()) {
            System.err.println("Bad matrix orders for multiplicationpiojpiojniponmjipo");
            System.err.println(levelA.getOutSignals().size() + " != " + levelB.getIn());
            System.err.println(" GATES SIZE" + levelB.getGates().size());
        } else {

            int probGateLevelLine = levelA.getSignalCombinations()[(int)x];
            
            value = getGateLevelMatrixIndex(levelB, probGateLevelLine, y);
            
//            long limit = powLong(2, levelB.getIn());
//            long bigCounter = 0;
//
//            while(bigCounter < limit) {
//                
//                if(getInterconnectionMatrixIndex(levelA, x, bigCounter) == 1) {
//                    
//                    value = value + getGateLevelMatrixIndex(levelB, bigCounter, y);
//                    break;
//                }
//                
//                bigCounter += 1;
//            }
        }                
        
        return value;
    }
    
    public static float getMultiplicationMatrixIndex(ProbGateLevel levelA,  ProbInterLevel levelB, long x, long y) {
        
        float value = 0f;
        
        if(levelA.getOut() != levelB.getInSignals().size()) {
            System.err.println("Bad matrix orders for multiplication");
        } else {
            long limit = powLong(2, levelA.getOut());            
            long bigCounter = 0;

            while(bigCounter < limit) {
                
                if(getInterconnectionMatrixIndex(levelB, bigCounter, y) == 1) {
                    value = value + getGateLevelMatrixIndex(levelA, x, bigCounter);
                }
                bigCounter += 1;
            }
        }                
        
        return value;
    }
    
    public static float getMultiplicationMatrixIndex(ProbGateLevel levelA, ProbGateLevel levelB, long x, long y) {
        
        float value = 0f;
        
        if(levelA.getOut() != levelB.getIn()) {
            System.err.println("Bad matrix orders for multiplication");
        } else {
            long limit = powLong(2, levelA.getOut());
            long bigCounter = 0;

            while(bigCounter < limit) {
                
                float aux;
                
                aux = getGateLevelMatrixIndex(levelA, x, bigCounter);
                aux = aux * getGateLevelMatrixIndex(levelB, bigCounter, y);
                
                value = value + aux;
                
                bigCounter += 1;
            }
        }                
        
        return value;
    }
    
    public static float getMultiplicationMatrixIndex(ArrayList<Object> levels, long x, long y) {
        
        if(levels.size() == 1) {
            return getGateLevelMatrixIndex((ProbGateLevel)levels.get(0), x, y);
        } else if(levels.size() == 2) {
            return getMultiplicationMatrixIndex(levels.get(0), levels.get(1), x, y);
        } else {
            float value = 0f;
            long limit;
            Object lastLevel = levels.get(levels.size()-1);
            ArrayList<Object> auxList = new ArrayList<>(levels.subList(0, levels.size()-1));
            
            if( lastLevel instanceof ProbGateLevel) {
                limit = powLong(2, ((ProbGateLevel)lastLevel).getIn());
            } else {
                limit = powLong(2,((ProbInterLevel)lastLevel).getInSignals().size());
            }

            long bigCounter = 0;

            while(bigCounter < limit) {

                float aux = 0f;

                if (lastLevel instanceof ProbInterLevel) {
                    if(getInterconnectionMatrixIndex((ProbInterLevel)lastLevel, bigCounter, y) == 1) {
                        aux = getMultiplicationMatrixIndex(auxList, x, bigCounter);
                    }
                } else {
                    
                    aux = getMultiplicationMatrixIndex(auxList, x, bigCounter);
                    aux = aux * getGateLevelMatrixIndex((ProbGateLevel)lastLevel, bigCounter, y);
                }
                
                value = value + aux;
                bigCounter += 1;
            } 

            return value;
        }
    }  
    
    public static float getPTMGateIndex(Object gate, int x, int y) {
        
        if(gate instanceof ProbGate) {
            ProbGate pGate = (ProbGate)gate;
            return pGate.getType().getPTM2Index(x, y);
        } else {
            if(x == y) {
                return 1f;
            } else {
                return 0f;
            }
        }                               
    }
    
    public static float getPTMCircuitIndex(ProbCircuit pCircuit, long x, long y) {
        
        ArrayList<Object> levels = new ArrayList<>();
        
        for (int i = 0; i < pCircuit.getProbGateLevels().size(); i++) {
            ProbInterLevel pInterLevel = pCircuit.getProbInterLevels().get(i);
            
            if(!(pInterLevel.getInSignals().equals(pInterLevel.getOutSignals()))) {
                pInterLevel.setSignalCombinations();
                levels.add(pInterLevel);
            }
            
            levels.add(pCircuit.getProbGateLevels().get(i));
        }
        
        return getMultiplicationMatrixIndex(levels, x, y);
    }        
    
    public static float getCircuitReliability(ProbCircuit pCircuit, int[] itm) {
        float result = 0f;
        long timeAverage = 0;
        for (int i = 1; i < itm.length; i++) {
            long time = System.currentTimeMillis();
            result = result +  getPTMCircuitIndex(pCircuit, i-1, itm[i]);
            
            timeAverage = timeAverage + (System.currentTimeMillis()-time);
            System.out.println("Index " + i + " time = " + (System.currentTimeMillis()-time) + " and timeAverage = " + (timeAverage / i));
        }
        
        result = result / powLong(2, pCircuit.getProbInputs().size());
        
        return result;
    }
    
}
