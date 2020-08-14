/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ops;

import com.sun.xml.internal.bind.v2.TODO;
import datastructures.CellLibrary;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

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
public class SPRMultiPassV2BigDecimalOps {
    
    static int[][] index = {{0,0},{0,1},{1,0},{1,1}};
    public static int COUNTER = 0;
    public static BigDecimal VALUECOUNTER = BigDecimal.ZERO;
        
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
    
    public static BigDecimal getSPRReliability(ProbCircuit pCircuit, ArrayList<ProbSignal> fanouts) {
                
        BigDecimal value = BigDecimal.ONE;
        BigDecimal flag;
        int[] indexes;
        int a;
        int b;
        
        
        
        for (int i = 0; i < pCircuit.getProbGateLevels().size(); i++) {
            ProbGateLevel pGateLevel = pCircuit.getProbGateLevels().get(i);
            
            for (int j = 0; j < pGateLevel.getGates().size(); j++) {
                
                if(pGateLevel.getGates().get(j) instanceof ProbGate) {

                    ProbGate pGate = (ProbGate)pGateLevel.getGates().get(j);                  

                    ProbSignal aSignal = pGate.getpInputs().get(0); 
                                                            
                    BigDecimal[][] matrix = aSignal.getProbMatrix();
                    
                    BigDecimal[][] fooMatrix;                    
                    
                    int aState = aSignal.getCurrentState();
                    
                    
                    if(aState == 4) {
                        
                        if(pGate.getpInputs().size() > 1) { 
                            
                            for (int k = 1; k < pGate.getpInputs().size(); k++) {
                                ProbSignal bSignal = pGate.getpInputs().get(k);
                                int bState = bSignal.getCurrentState();
                                
                                if(bState == 4) {
                                    matrix = getKronecker(matrix, bSignal.getProbMatrix());
                                } else {                                                                        
                                    indexes = index[bState];
                                    a = indexes[0];
                                    b = indexes[1];
                                    flag = bSignal.getProbMatrix()[a][b];
                                                                        
                                    
                                    if(flag.compareTo(BigDecimal.ZERO) == 0) {                                        
                                        return new BigDecimal(fanouts.indexOf(bSignal)).negate();
                                    }

                                    fooMatrix = new BigDecimal[][]{{BigDecimal.ZERO,BigDecimal.ZERO},{BigDecimal.ZERO,BigDecimal.ZERO}};
                                    fooMatrix[a][b] = BigDecimal.ONE;
                                    matrix = getKronecker(matrix, fooMatrix);
                                }                                                                                           
                            }
                        }
                    } else {                                                
                        indexes = index[aState];                        
                        a = indexes[0];
                        b = indexes[1];
                        
                        //flag = aSignal.getProbMatrixFloat[a][b];                                    
                        flag = aSignal.getProbMatrix()[a][b];
                        
                        
                        
                        
                        
                        if(flag.compareTo(BigDecimal.ZERO) == 0) {
                            //return -1f;
                            return new BigDecimal(fanouts.indexOf(aSignal)).negate();
                        }
                        
                        fooMatrix = new BigDecimal[][]{{BigDecimal.ZERO,BigDecimal.ZERO},{BigDecimal.ZERO,BigDecimal.ZERO}};
                        fooMatrix[a][b] = BigDecimal.ONE;
                        matrix = fooMatrix;
                                                                        
                        if(pGate.getpInputs().size() > 1) { 
                            
                            for (int k = 1; k < pGate.getpInputs().size(); k++) {
                                ProbSignal bSignal = pGate.getpInputs().get(k);
                                int bState = bSignal.getCurrentState();
                                
                                if(bState == 4) {
                                    matrix = getKronecker(matrix, bSignal.getProbMatrix());
                                } else {                                    
                                    indexes = index[bState];
                                    a = indexes[0];
                                    b = indexes[1];
                                    flag = bSignal.getProbMatrix()[a][b];                                                                                                            
                                    
                                    if(flag.compareTo(BigDecimal.ZERO) == 0) {
                                        //return -1f;
                                        //return new BigDecimal(fanouts.indexOf(bSignal)).multiply(new BigDecimal("-1"));
                                        return new BigDecimal(fanouts.indexOf(bSignal)).negate();
                                    }
                                    
                                    fooMatrix = new BigDecimal[][]{{BigDecimal.ZERO,BigDecimal.ZERO},{BigDecimal.ZERO,BigDecimal.ZERO}};
                                    fooMatrix[a][b] = BigDecimal.ONE;
                                    matrix = getKronecker(matrix, fooMatrix);
                                }                                                                                           
                            }
                        }                        
                    }                   
                    matrix = getMultipliedMatrix(matrix, pGate.getType().getPTM());
                                     
                    matrix = getSignalMatrix(matrix, pGate.getType().getItm());

                    pGate.getpOutputs().get(0).setProbMatrix(matrix);
                }                                
            }           
        }
                
        
        for (ProbSignal pSignal : pCircuit.getProbOutputs()) {
            
            
            BigDecimal correct0 = pSignal.getProbMatrix()[0][0];
            BigDecimal correct1 = pSignal.getProbMatrix()[1][1];            
            value = value.multiply(correct0.add(correct1));            
        }
        
        for (ProbSignal fanout : fanouts) {
            indexes = index[fanout.getCurrentState()];
            a = indexes[0];
            b = indexes[1];
                        
            value = value.multiply(fanout.getProbMatrix()[a][b]);
        }
        
        
        COUNTER++;        
        VALUECOUNTER = VALUECOUNTER.add(value);
        
        return value;        
                       
    }        
    
    /**
     * This method pre-process the SPR-MP, takes fanouts from pCircuit and
     * starts the recursive process
     * Note: there is be considering 100% circuits fanouts
     * @param pCircuit - Circuits that will be processed
     * @return Reliability value based on SPR-MP method
     */
    public static BigDecimal getSPRMultiPassReliaiblity(ProbCircuit pCircuit) {
                        
        BigDecimal value;
        
        ArrayList<ProbSignal> fanouts = pCircuit.getFanouts();
        ArrayList<ProbSignal> newFanouts = new ArrayList<>();
        
        for (int i = 0; i < (fanouts.size()); i++) {
            newFanouts.add(fanouts.get(i));            
        }
        
        ProbSignal pSignal = newFanouts.get(0);
        
        value = getMultiPass(pCircuit, newFanouts, pSignal, 0);
        
        return value;
    }
    
    /**
     * This method pre-process the SPR-MP, takes fanouts from pCircuit and
     * starts the recursive process
     * Note: there is be considering 100% circuits fanouts
     * @param pCircuit - Circuits that will be processed
     * @param fanouts  - Fqanouts to considered
     * @return Reliability value based on SPR-MP method
     */
    public static BigDecimal getSPRMultiPassReliaiblity(ProbCircuit pCircuit, ArrayList<ProbSignal> fanouts) {
                        
        BigDecimal value;                
        
        ProbSignal pSignal = fanouts.get(0);
        
        ArrayList<ProbSignal> newFanouts = new ArrayList<>();        
        for (int i = 0; i < (fanouts.size()); i++) {
            newFanouts.add(fanouts.get(i));            
        }
        
        value = getMultiPass(pCircuit, newFanouts, pSignal, 0);
        
        return value;
    }
    
    
    /**
     * This method pre-process the SPR-MP, takes fanouts from pCircuit and
     * starts the recursive process
     * Note: there is be considering 100% circuits fanouts
     * @param pCircuit - Circuits that will be processed
     * @param fanout  - Faanout to considered
     * @return Reliability value based on SPR-MP method
     */
    public static BigDecimal getSPRMultiPassReliaiblity(ProbCircuit pCircuit, ProbSignal fanout) {
                        
        BigDecimal value;                
        
        ArrayList<ProbSignal> newFanouts = new ArrayList<>();        

        newFanouts.add(fanout);            
                
        value = getMultiPass(pCircuit, newFanouts, fanout, 0);
        
        return value;
    }
    
    
    /**
     * 
     * This method pre-process the SPR-MP, takes fanouts from pCircuit and
     * starts the recursive process    
     * @param pCircuit Circuits that will be processed
     * @param cellLib TODO
     * @param reliability TODO
     * @return Reliability value based on SPR-MP method
     */
    public static BigDecimal getSPRMultiPassReliaiblity(ProbCircuit pCircuit, CellLibrary cellLib, String reliability) {
                
        cellLib.setPTMCells2(Float.valueOf(reliability));
        
        BigDecimal value;
        
        ArrayList<ProbSignal> fanouts = pCircuit.getFanouts();
        ArrayList<ProbSignal> newFanouts = new ArrayList<>();
        
        //for (int i = 0; i < (fanouts.size() / 4); i++) {
        for (int i = 0; i < (fanouts.size()); i++) {
            newFanouts.add(fanouts.get(i));            
        }
        
        ProbSignal pSignal = newFanouts.get(0);
        
        value = getMultiPass(pCircuit, newFanouts, pSignal, 0);
        
        return value;
    }
    
    private static BigDecimal getMultiPass(ProbCircuit pCircuit, ArrayList<ProbSignal> fanouts, ProbSignal pSignal, int currentIndex) {
                
        BigDecimal value = BigDecimal.ZERO;
        BigDecimal flag;
        int nextIndex = currentIndex+1;
        int[] states = pSignal.getStates();
                   
        
        if(nextIndex == fanouts.size()) {
            
            for (int j = 0; j < states.length; j++) {                                
                pSignal.setCurrentState(states[j]);
                flag = getSPRReliability(pCircuit, fanouts);
                
                
                if(flag.compareTo(BigDecimal.ZERO) > 0) {                    
                    value = value.add(flag);
                } else {
                    int foo = flag.intValue();
                    foo = foo * -1;
                    if(fanouts.indexOf(pSignal) != foo) {                        
                        return flag;
                    }                    
                }
            }
        } else {
            ProbSignal next = fanouts.get(nextIndex);
            for (int j = 0; j < states.length; j++) {
                pSignal.setCurrentState(states[j]);
                flag = getMultiPass(pCircuit, fanouts, next, nextIndex);
                
                if(flag.compareTo(BigDecimal.ZERO) > 0) {
                    value = value.add(flag);
                } else {
                    int foo = flag.intValue();
                    foo = foo * -1;
                    if(fanouts.indexOf(pSignal) != foo) {                        
                        return flag;
                    }  
                }                               
            }
        }
        
        
        return value;
    }
    


    public static String getTotalPasses(ProbCircuit pCircuit) {
        
        BigInteger bigPasses;
        String passes;
        
        int insFan = 0;
        int middleFan = 0;
        int totalFan = pCircuit.getFanouts().size();
        
        NumberFormat formatter = new DecimalFormat("0.###E0", DecimalFormatSymbols.getInstance(Locale.ROOT));        
                
        Iterator<ProbSignal> iterator = pCircuit.getFanouts().iterator();

        while (iterator.hasNext()) {
            ProbSignal next = iterator.next();
            if(next.getOrigin() == null) {
                insFan++;
            }
        }
        
        middleFan = totalFan - insFan;
        
        
        System.out.println("ENTRADAS: " + insFan);
        System.out.println("MEIO: " + middleFan);
                
        bigPasses = new BigInteger("2").pow(insFan).multiply(new BigInteger("4").pow(middleFan));
        
        passes = formatter.format(bigPasses);
        
        passes = passes.replace("E", " E+");
        
        System.out.println("Total Fanouts: " + pCircuit.getFanouts().size());
        System.out.println("Total Fanouts Entrada: " + insFan);
        System.out.println("Total Fanouts Meio: " + middleFan);
        System.out.println("Total Passes: 2^" + insFan + " * 4^" + middleFan);
        System.out.println("Total Passes: "+ passes);
        System.out.println("");
        
        return passes;
    }
}
