/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ops;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import levelDatastructures.DepthGate;
import levelDatastructures.GateLevel;
import levelDatastructures.InterLevel;
import levelDatastructures.LevelCircuit;
import signalProbability.ProbCircuit;

/**
 *
 * @author matheus
 */
public class SerialPTMOps {
    
    public static BigDecimal getGateLevelMatrixIndex(GateLevel gLevel, String x, String y, BigDecimal prob) {

        ArrayList<Object> gates = gLevel.getGates();
        
        BigDecimal value = BigDecimal.ONE;
        
        BigDecimal totalX = new BigDecimal("2");
        BigDecimal totalY = new BigDecimal("2");
                
        BigDecimal xIndex = new BigDecimal(x).add(BigDecimal.ONE);
        BigDecimal yIndex = new BigDecimal(y).add(BigDecimal.ONE);
        
        totalX = totalX.pow(gLevel.getIn());
        totalY = totalY.pow(gLevel.getOut());
        
        if(xIndex.compareTo(totalX) == 1 || yIndex.compareTo(totalY) == 1) {
            System.err.println("BAD INDEX NUMBERfasfasfasfasf");
            System.err.println("TotalX"+totalX);
            System.err.println("TotalY"+totalY);
            System.err.println("xIndex"+xIndex);
            System.err.println("yIndex"+yIndex);
            System.err.println("GateLevel"+gLevel.getLevel()+"-->"+x+"x"+y);
        } else {        
            
            BigDecimal xMinLimit;
            BigDecimal xMaxLimit;
            BigDecimal yMinLimit;
            BigDecimal yMaxLimit;

            BigDecimal aux;           

            xMinLimit = BigDecimal.ONE;
            yMinLimit = BigDecimal.ONE;

            xMaxLimit = totalX;
            yMaxLimit = totalY;

            for (int i = 0; i < gates.size(); i++) {

                int xGateMatrix = 2;
                int yGateMatrix = 2;                        

                if(gates.get(i) instanceof DepthGate) {

                    DepthGate depthGate = (DepthGate)gates.get(i);

                    xGateMatrix = PTMOps.PowInt(xGateMatrix, depthGate.getGate().getInputs().size());
                    yGateMatrix = PTMOps.PowInt(yGateMatrix, depthGate.getGate().getOutputs().size());

                }

                aux = xMaxLimit.subtract(xMinLimit);
                aux = aux.add(BigDecimal.ONE);
                aux = aux.divide(new BigDecimal(xGateMatrix));
                aux = aux.subtract(BigDecimal.ONE);

                int xGateIndex = 0;
                int yGateIndex = 0;

                for (int j = 0; j < xGateMatrix; j++) {                                
                    xMaxLimit = xMinLimit.add(aux);                                

                    if((xIndex.compareTo(xMinLimit) == 1 || xIndex.compareTo(xMinLimit) == 0) && (xIndex.compareTo(xMaxLimit) == -1) || (xIndex.compareTo(xMaxLimit) == 0)) {
                        //xGateIndex = j+1;                    
                        xGateIndex = j;                    
                        break;
                    }                                    
                    xMinLimit = xMaxLimit.add(BigDecimal.ONE);
                }

                aux = yMaxLimit.subtract(yMinLimit);
                aux = aux.add(BigDecimal.ONE);
                aux = aux.divide(new BigDecimal(yGateMatrix));
                aux = aux.subtract(BigDecimal.ONE);

                for (int j = 0; j < yGateMatrix; j++) {
                    yMaxLimit = yMinLimit.add(aux);

                    if((yIndex.compareTo(yMinLimit) == 1 || yIndex.compareTo(yMinLimit) == 0) && (yIndex.compareTo(yMaxLimit) == -1) || (yIndex.compareTo(yMaxLimit) == 0)) {
                        //yGateIndex = j+1;
                        yGateIndex = j;
                        break;
                    }                                    
                    yMinLimit = yMaxLimit.add(BigDecimal.ONE);
                }

                //output += ("("+gates.get(i)+"("+xGateIndex+"x"+yGateIndex+")" + getPTMGateIndex(gates.get(i), xGateIndex, yGateIndex, .9) + "*");
                value = value.multiply(getPTMGateIndex(gates.get(i), xGateIndex, yGateIndex, prob));
                //System.out.println(value);
            }

            //System.out.println(output);
            return value;
        }
        
        return null;
    }
    
    public static int getInterconnectionMatrixIndex(InterLevel iLevel, String x, String y) {
        
        int inSignals = iLevel.getInSingnals().size();
        int outSignals = iLevel.getOutSingnals().size();
        
        BigInteger bigX = new BigInteger(x);
        BigInteger bigY = new BigInteger(y);
        String padding1 = "";
        String padding2 = "";
        String binaryIn = bigX.toString(2);
        String binaryOut = bigY.toString(2);
        String aux = "";
        

        for (int i = 0; i < inSignals; i++) {
            padding1 += "0";
        }                
        
        for (int i = 0; i < outSignals; i++) {
            padding2 += "0";
        }    
        
        binaryIn = (padding1 + binaryIn).substring(binaryIn.length());
        binaryOut = (padding2 + binaryOut).substring(binaryOut.length());
        
        Map<String, String> dict = new LinkedHashMap<>();
            
        for (int j = 0; j < inSignals; j++) {                
            dict.put(iLevel.getInSingnals().get(j).getId(), Character.toString(binaryIn.charAt(j)));                
        }
        
        for (int j = 0; j < outSignals; j++) {
                aux = aux + dict.get(iLevel.getOutSingnals().get(j).getId());
        }
        
        if(aux.equals(binaryOut)) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public static BigDecimal getMultiplicationMatrixIndex(Object levelA, Object levelB, String x, String y, BigDecimal prob) {
        
        if (levelA instanceof InterLevel) {
            return getMultiplicationMatrixIndex((InterLevel)levelA, (GateLevel)levelB, x, y, prob);
        } else {
            if (levelB instanceof InterLevel) {
                return getMultiplicationMatrixIndex((GateLevel)levelA, (InterLevel)levelB, x, y, prob);
            } else {
                return getMultiplicationMatrixIndex((GateLevel)levelA, (GateLevel)levelB, x, y, prob);
            }
        }
    }
    
    public static BigDecimal getMultiplicationMatrixIndex(InterLevel levelA,  GateLevel levelB, String x, String y, BigDecimal prob) {
        
        BigDecimal value = BigDecimal.ZERO;
        
        if(levelA.getOutSingnals().size() != levelB.getIn()) {
            System.err.println("Bad matrix orders for multiplication");
        } else {
            BigDecimal limit = new BigDecimal("2").pow(levelB.getIn());
            BigDecimal bigCounter = BigDecimal.ZERO;

            while(bigCounter.compareTo(limit) == -1) {
                
                if(getInterconnectionMatrixIndex(levelA, x, (bigCounter.toString())) == 1) {
                    value = value.add(getGateLevelMatrixIndex(levelB, bigCounter.toString(), y, prob));
                    break;
                }
                
                bigCounter = bigCounter.add(BigDecimal.ONE);
            }
        }                
        
        return value;
    }
    
    public static BigDecimal getMultiplicationMatrixIndex(GateLevel levelA,  InterLevel levelB, String x, String y, BigDecimal prob) {
        
        BigDecimal value = BigDecimal.ZERO;
        
        if(levelA.getOut() != levelB.getInSingnals().size()) {
            System.err.println("Bad matrix orders for multiplication");
        } else {
            BigDecimal limit = new BigDecimal("2").pow(levelA.getOut());
            BigDecimal bigCounter = BigDecimal.ZERO;

            while(bigCounter.compareTo(limit) == -1) {
                
                if(getInterconnectionMatrixIndex(levelB, (bigCounter.toString()), y) == 1) {
                    value = value.add(getGateLevelMatrixIndex(levelA, x, bigCounter.toString(), prob));
                }
                bigCounter = bigCounter.add(BigDecimal.ONE);
            }
        }                
        
        return value;
    }
    
    public static BigDecimal getMultiplicationMatrixIndex(GateLevel levelA, GateLevel levelB, String x, String y, BigDecimal prob) {
        
        BigDecimal value = BigDecimal.ZERO;
        
        if(levelA.getOut() != levelB.getIn()) {
            System.err.println("Bad matrix orders for multiplication");
        } else {
            BigDecimal limit = new BigDecimal("2").pow(levelA.getOut());
            BigDecimal bigCounter = BigDecimal.ZERO;

            while(bigCounter.compareTo(limit) == -1) {
                
                BigDecimal aux;
                
                aux = getGateLevelMatrixIndex(levelA, x, (bigCounter.toString()), prob);
                aux = aux.multiply(getGateLevelMatrixIndex(levelB, bigCounter.toString(), y, prob));
                
                value = value.add(aux);
                
                bigCounter = bigCounter.add(BigDecimal.ONE);
            }
        }                
        
        return value;
    }
    
    public static BigDecimal getMultiplicationMatrixIndex(ArrayList<Object> levels, String x, String y, BigDecimal prob) {
        
        if(levels.size() == 1) {
            return getGateLevelMatrixIndex((GateLevel)levels.get(0), x, y, prob);
        } else if(levels.size() == 2) {
            return getMultiplicationMatrixIndex(levels.get(0), levels.get(1), x, y, prob);
        } else {
            BigDecimal value = BigDecimal.ZERO;
            BigDecimal limit;
            Object lastLevel = levels.get(levels.size()-1);
            ArrayList<Object> auxList = new ArrayList<>(levels.subList(0, levels.size()-1));
            
            if( lastLevel instanceof GateLevel) {
                limit = new BigDecimal("2").pow(((GateLevel) lastLevel).getIn());
            } else {
                limit = new BigDecimal("2").pow(((InterLevel)lastLevel).getInSingnals().size());
            }

            BigDecimal bigCounter = BigDecimal.ZERO;

            while(bigCounter.compareTo(limit) == -1) {

                BigDecimal aux = BigDecimal.ZERO;

                if (lastLevel instanceof InterLevel) {
                    if(getInterconnectionMatrixIndex((InterLevel)lastLevel, bigCounter.toString(), y) == 1) {
                        aux = getMultiplicationMatrixIndex(auxList, x, (bigCounter.toString()), prob);
                    }
                } else {
                    
                    aux = getMultiplicationMatrixIndex(auxList, x, (bigCounter.toString()), prob);
                    aux = aux.multiply(getGateLevelMatrixIndex((GateLevel)lastLevel, bigCounter.toString(), y, prob));
                }
                
                value = value.add(aux);
                bigCounter = bigCounter.add(BigDecimal.ONE);
            } 

            return value;
        }
    }  
    
    public static BigDecimal getPTMGateIndex(Object gate, int x, int y, BigDecimal prob) {
        
        if(gate instanceof DepthGate) {
            DepthGate dGate = (DepthGate)gate;
            return dGate.getGate().getType().getPTMIndex(x, y);
        } else {
            if(x == y) {
                return BigDecimal.ONE;
            } else {
                return BigDecimal.ZERO;
            }
        }                               
    }
    
    public static BigDecimal getPTMCircuitIndex(LevelCircuit lcirc, String x, String y, BigDecimal prob) {
        
        ArrayList<Object> levels = new ArrayList<>();
        
        for (int i = 0; i < lcirc.getGateLevels().size(); i++) {
            InterLevel iLevel = lcirc.getInterLevels().get(i);
            
            if(!(iLevel.getInSingnals().equals(iLevel.getOutSingnals()))) {
                levels.add(iLevel);
            }
            levels.add(lcirc.getGateLevels().get(i));
        }
        
        return getMultiplicationMatrixIndex(levels, x, y, prob);
    }
    
    public static BigDecimal getPTMReliability(LevelCircuit lCircuit, int[] itm, BigDecimal prob) {
        BigDecimal result = BigDecimal.ZERO;
        
        for (int i = 1; i < itm.length; i++) {
            result = result.add(getPTMCircuitIndex(lCircuit, Integer.toString(i-1), Integer.toString(itm[i]), prob));
            
            //System.out.println(i + " de " + itm.length);
        }
        
        result = result.divide(new BigDecimal(itm.length - 1));
        
        return result;
    }
    
}
