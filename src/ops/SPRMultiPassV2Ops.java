/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ops;

import datastructures.CellLibrary;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
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
public class SPRMultiPassV2Ops {
    
    static int[][] index = {{0,0},{0,1},{1,0},{1,1}};
    public static int COUNTER = 0;
    public static float VALUECOUNTER = 0f;
        
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
    
    public static float getSPRReliability(ProbCircuit pCircuit, ArrayList<ProbSignal> fanouts) {
        
        float value = 1f;
        float flag;
        int[] indexes;
        int a;
        int b;
        
        
        
        for (int i = 0; i < pCircuit.getProbGateLevels().size(); i++) {
            ProbGateLevel pGateLevel = pCircuit.getProbGateLevels().get(i);
            
            for (int j = 0; j < pGateLevel.getGates().size(); j++) {
                
                if(pGateLevel.getGates().get(j) instanceof ProbGate) {

                    ProbGate pGate = (ProbGate)pGateLevel.getGates().get(j);
                    
                    //MEXER AQUI.... 
                    ProbSignal aSignal = pGate.getpInputs().get(0);                    
                    float[][] matrix = aSignal.getProbMatrixFloat();                    
                    float[][] fooMatrix;                    
                    
                    int aState = aSignal.getCurrentState();
                    
                    
                    if(aState == 4) {
                        
                        if(pGate.getpInputs().size() > 1) { 
                            
                            for (int k = 1; k < pGate.getpInputs().size(); k++) {
                                ProbSignal bSignal = pGate.getpInputs().get(k);
                                int bState = bSignal.getCurrentState();
                                
                                if(bState == 4) {
                                    matrix = getKronecker(matrix, bSignal.getProbMatrixFloat());
                                } else {                                                                        
                                    indexes = index[bState];
                                    a = indexes[0];
                                    b = indexes[1];
                                    flag = bSignal.getProbMatrixFloat()[a][b];
                                    
                                    
                                    //if(flag == 0f || flag < 0.001f) {
                                    if(flag == 0f) {
                                        return fanouts.indexOf(bSignal) * -1;
                                    }
                                    
                                    fooMatrix = new float[][]{{0f,0f},{0f,0f}};
                                    fooMatrix[a][b] = 1f;
                                    matrix = getKronecker(matrix, fooMatrix);
                                }                                                                                           
                            }
                        }
                    } else {                                                
                        indexes = index[aState];                        
                        a = indexes[0];
                        b = indexes[1];
                        
                        flag = aSignal.getProbMatrixFloat()[a][b];                                    
                        //if(flag == 0f || flag < 0.001f) {
                        
                        if(flag == 0f) {
                            //return -1f;                            
                            return fanouts.indexOf(aSignal) * -1;
                        }
                        
                        fooMatrix = new float[][]{{0f,0f},{0f,0f}};
                        fooMatrix[a][b] = 1f;
                        matrix = fooMatrix;
                                                                        
                        if(pGate.getpInputs().size() > 1) { 
                            
                            for (int k = 1; k < pGate.getpInputs().size(); k++) {
                                ProbSignal bSignal = pGate.getpInputs().get(k);
                                int bState = bSignal.getCurrentState();
                                
                                if(bState == 4) {
                                    matrix = getKronecker(matrix, bSignal.getProbMatrixFloat());
                                } else {                                    
                                    indexes = index[bState];
                                    a = indexes[0];
                                    b = indexes[1];
                                    flag = bSignal.getProbMatrixFloat()[a][b];
                                    
                                    //if(flag == 0f || flag < 0.001f) {
                                    if(flag == 0f) {
                                        //return -1f;
                                        return fanouts.indexOf(bSignal) * -1;
                                    }
                                    
                                    fooMatrix = new float[][]{{0f,0f},{0f,0f}};
                                    fooMatrix[a][b] = 1f;
                                    matrix = getKronecker(matrix, fooMatrix);
                                }                                                                                           
                            }
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
        
        for (ProbSignal fanout : fanouts) {
            indexes = index[fanout.getCurrentState()];
            a = indexes[0];
            b = indexes[1];
            
            value = value * fanout.getProbMatrixFloat()[a][b];
        }
        
        
        COUNTER++;
        VALUECOUNTER+=value;
        
        return value;        
                       
    }        
    
    /**
     * This method pre-process the SPR-MP, takes fanouts from pCircuit and
     * starts the recursive process
     * Note: there is be considering 100% circuits fanouts
     * @param pCircuit - Circuits that will be processed
     * @return Reliability value based on SPR-MP method
     */
    public static float getSPRMultiPassReliaiblity(ProbCircuit pCircuit) {
                        
        float value;
        
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
    public static float getSPRMultiPassReliaiblity(ProbCircuit pCircuit, ArrayList<ProbSignal> fanouts) {
                        
        float value;                
        
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
     * @param fanout - Fanout to considered
     * @return Reliability value based on SPR-MP method
     */
    public static float getSPRMultiPassReliaiblity(ProbCircuit pCircuit, ProbSignal fanout) {
                        
        float value;                
        
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
     * @param cellLib
     * @param reliability
     * @return Reliability value based on SPR-MP method
     */
    public static float getSPRMultiPassReliaiblity(ProbCircuit pCircuit, CellLibrary cellLib, String reliability) {
        
        cellLib.setPTMCells2(Float.valueOf(reliability));
        
        float value;
        
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
    
    private static float getMultiPass(ProbCircuit pCircuit, ArrayList<ProbSignal> fanouts, ProbSignal pSignal, int currentIndex) {
        
        float value = 0f;
        float flag;
        int nextIndex = currentIndex+1;
        int[] states = pSignal.getStates();
                   
        
        if(nextIndex == fanouts.size()) {
            
            for (int j = 0; j < states.length; j++) {                                
                pSignal.setCurrentState(states[j]);
                flag = getSPRReliability(pCircuit, fanouts);
                
                
                if(flag > 0) {                    
                    value += flag;
                } else {
                    if(fanouts.indexOf(pSignal) != (flag*-1)) {
                        return flag;
                    }                    
                }
            }
        } else {
            ProbSignal next = fanouts.get(nextIndex);
            for (int j = 0; j < states.length; j++) {
                pSignal.setCurrentState(states[j]);
                flag = getMultiPass(pCircuit, fanouts, next, nextIndex);
                
                if(flag > 0) {
                    value += flag;
                } else {
                    if(fanouts.indexOf(pSignal) != (flag*-1)) {
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
