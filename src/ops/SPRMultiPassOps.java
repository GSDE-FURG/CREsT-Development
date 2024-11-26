/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ops;

import datastructures.CellLibrary;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

import static ops.CommonOps.getKronecker;
import static ops.CommonOps.getMultipliedMatrix;
import static ops.CommonOps.matrixPrint;

import static ops.CommonOps.getKronecker;
import static ops.CommonOps.getMultipliedMatrix;
import static ops.CommonOps.matrixPrint;

import static writers.WriteFile.WriteContent;

import signalProbability.ProbCircuit;
import signalProbability.ProbGate;
import signalProbability.ProbGateLevel;
import signalProbability.ProbSignal;
import writers.WriteFile;

/**
 *
 * @author matheus
 */
public class SPRMultiPassOps {
    
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
                                    fooMatrix = new float[][]{{0f,0f},{0f,0f}};
                                    indexes = index[bState];
                                    a = indexes[0];
                                    b = indexes[1];
                                    
                                    //fooMatrix[a][b] = bSignal.getProbMatrixFloat()[a][b];
                                    fooMatrix[a][b] = 1f;
                                    matrix = getKronecker(matrix, fooMatrix);
                                }                                                                                           
                            }
                        }
                    } else {
                        
                        fooMatrix = new float[][]{{0f,0f},{0f,0f}};
                        indexes = index[aState];
                        
                        a = indexes[0];
                        b = indexes[1];
                        //fooMatrix[a][b] = aSignal.getProbMatrixFloat()[a][b];
                        fooMatrix[a][b] = 1f;
                        matrix = fooMatrix;
                                                                        
                        if(pGate.getpInputs().size() > 1) { 
                            
                            for (int k = 1; k < pGate.getpInputs().size(); k++) {
                                ProbSignal bSignal = pGate.getpInputs().get(k);
                                int bState = bSignal.getCurrentState();
                                
                                if(bState == 4) {
                                    matrix = getKronecker(matrix, bSignal.getProbMatrixFloat());
                                } else {
                                    fooMatrix = new float[][]{{0f,0f},{0f,0f}};
                                    indexes = index[bState];
                                    a = indexes[0];
                                    b = indexes[1];
                                    
                                    //fooMatrix[a][b] = bSignal.getProbMatrixFloat()[a][b];
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
        
        System.out.println("PASS # " + COUNTER);
        //WriteContent("PASS # " + COUNTER);
        System.out.println(pCircuit.getProbSignals());                
        
        WriteFile.WriteContent("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
        WriteFile.WriteContent("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
        WriteFile.WriteContent("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
        
        WriteFile.WriteContent("\\begin{table}\n" + "   \\begin{center}\n");
        
        WriteFile.WriteContent("\\begin{tabular}{");
                    
                    WriteFile.WriteContent("|p{1cm}");
                    
                    for (int k = 0; k < pCircuit.getProbSignals().size(); k++) {
                        if(k>4) {
                            WriteFile.WriteContent("|p{2.7cm}");
                        } else {
                            WriteFile.WriteContent("|p{1cm}");
                        }
                    }
                    
                    WriteFile.WriteContent("|}\n" + "\\hline\n");
                    
                    WriteFile.WriteContent("\\textbf{PASS} &");
                    
                    for (int k = 0; k < pCircuit.getProbSignals().size(); k++) {
                        ProbSignal fooSignal = pCircuit.getProbSignals().get(k);
                        
                        WriteFile.WriteContent("\\centering  \\textbf{" + fooSignal.getId() + "} ");
                        
                        if(k < pCircuit.getProbSignals().size() - 1) {
                            WriteFile.WriteContent(" & ");
                        }
                        
                        //System.out.println(fooSignal.getId() + ": ");
                        //matrixPrint(fooSignal.getProbMatrixFloat());
                        //System.out.println("#####");
                        
                    }
                    
                    WriteFile.WriteContent("\\tabularnewline\n\\hline\n");
                    
                    WriteFile.WriteContent("\\centering\\\n" +
"\\newline \n\\textbf{" +COUNTER +  "}&");
                    
                    for (int k = 0; k < pCircuit.getProbSignals().size(); k++) {
                        ProbSignal fooSignal = pCircuit.getProbSignals().get(k);
                        
                        float s0 = fooSignal.getProbMatrixFloat()[0][0];
                        float s1 = fooSignal.getProbMatrixFloat()[0][1];
                        float s2 = fooSignal.getProbMatrixFloat()[1][0];
                        float s3 = fooSignal.getProbMatrixFloat()[1][1];
                        
                                                
                        String value0 = "\\color{black}{\\scalebox{.9}{\\textbf{" + s0 + "}}}";
                        String value1 = "\\color{black}{\\scalebox{.9}{\\textbf{" + s1 + "}}}";
                        String value2 = "\\color{black}{\\scalebox{.9}{\\textbf{" + s2 + "}}}";
                        String value3 = "\\color{black}{\\scalebox{.9}{\\textbf{" + s3 + "}}}";                        
                        
                        for (int i = 0; i < fanouts.size(); i++) {
                            if(fooSignal.equals(fanouts.get(i))) {
                                switch(fooSignal.getCurrentState()) {
                                    case 0:
                                        value0 = "\\color{red}{\\scalebox{.9}{\\textbf{" + s0 + "}}}";
                                        break;
                                    case 1:
                                        value1 = "\\color{red}{\\scalebox{.9}{\\textbf{" + s1 + "}}}";
                                        break;
                                    case 2:
                                        value2 = "\\color{red}{\\scalebox{.9}{\\textbf{" + s2 + "}}}";
                                        break;
                                    case 3:
                                        value3 = "\\color{red}{\\scalebox{.9}{\\textbf{" + s3 + "}}}";
                                        break;
                                }
                            }
                        }
                        
                        
                        WriteFile.WriteContent("\\tiny{\n" +
                                                "\\[\n" +
                                                "\\setlength\\arraycolsep{2pt}\n" +
                                                "\\begin{bmatrix}\n" +
                                                "    " + value0 + " &  " + value1 + "\\\\\n" +
                                                "    " + value2 + " &  " + value3 + "\\\\\n" +                                                
                                                "\\end{bmatrix}\n" +
                                                "\\]\n" +
                                                "}");
                        
                        if(k < pCircuit.getProbSignals().size() - 1) {
                            WriteFile.WriteContent(" & ");
                        }
                        
                        System.out.println(fooSignal.getId() + ": ");
                        matrixPrint(fooSignal.getProbMatrixFloat());
                        System.out.println("#####");
                        
                    }
                    
                    
                    WriteFile.WriteContent("\\tabularnewline\n");
                    
                    WriteFile.WriteContent("\\hline\n");
                    
                    WriteFile.WriteContent("\\fbox{\\parbox{\\textwidth}{\\textbf{\\quad \\qquad PASS RELIABITY = " + value + "}}}\n");
                    
                    WriteFile.WriteContent("\\fbox{\\parbox{\\textwidth}{\\textbf{\\quad \\qquad CUMULATIVE MULTIPASS RELIABITY = " + VALUECOUNTER + "}}}\n");
                    
                    WriteFile.WriteContent("\\end{tabular}\n");
        
                    WriteContent("    \\end{center}\n" +"\\end{table}");
        
        
        
        
        //WriteFile.WriteContent("\\\\\n");
        
        
        return value;        
                       
    }        

    public static float getSPRMultiPassReliaiblity(ProbCircuit pCircuit, CellLibrary cellLib, String reliability) {
        
        /*
        pCircuit.setPatternSourceProbSignal();
        pCircuit.setPTMReliabilityMatrix();
        pCircuit.setProbSignalStates();
        */
        cellLib.setPTMCells2(Float.valueOf(reliability));
        
        float value;
        
        ArrayList<ProbSignal> fanouts = pCircuit.getFanouts();
        
        ProbSignal pSignal = fanouts.get(0);
        
        value = getMultiPass(pCircuit, fanouts, pSignal, 0);
        
        return value;
    }
    
    private static float getMultiPass(ProbCircuit pCircuit, ArrayList<ProbSignal> fanouts, ProbSignal pSignal, int currentIndex) {
        
        float value = 0f;
        int nextIndex = currentIndex+1;
        int[] states = pSignal.getStates();
                   
        
        if(nextIndex == fanouts.size()) {                                    
            for (int j = 0; j < states.length; j++) {
                pSignal.setCurrentState(states[j]);                
                value += getSPRReliability(pCircuit, fanouts);
            }
        } else {
            ProbSignal next = fanouts.get(nextIndex);
            for (int j = 0; j < states.length; j++) {
                pSignal.setCurrentState(states[j]);
                value += getMultiPass(pCircuit, fanouts, next, nextIndex);                
            }
        }
        
        
        return value;
    }
    
    private static float getAdaptedSPRPass(ProbCircuit pCircuit) {
        float foo = 0f;
        ArrayList<ProbSignal> fanout = pCircuit.getFanouts();
        String teste = "";
        
        for (int i = 0; i < fanout.size(); i++) {
            teste = teste + fanout.get(i).getCurrentState();
        }
        
        System.out.println(teste);
        
        return foo;
    }
    
    public static BigInteger[] getTotalPasses(ProbCircuit pCircuit, boolean reliableINs) {
        
        BigInteger[] result = new BigInteger[4];
        
        BigInteger totalPasses;
        BigInteger inPasses = BigInteger.ZERO;
        BigInteger midPasses = BigInteger.ZERO;

        int insFan = 0;
        int middleFan = 0;
        int totalFan = pCircuit.getFanouts().size();
        
        if(!reliableINs) {
            totalPasses = new BigInteger("4").pow(totalFan);
        } else {                        
            Iterator<ProbSignal> iterator = pCircuit.getFanouts().iterator();                       

            while (iterator.hasNext()) {
                ProbSignal next = iterator.next();
                if(next.getSignalOrigin() == null) {
                    insFan++;
                }
            }

            middleFan = totalFan - insFan;
            
            if(insFan != 0) {                
                inPasses = new BigInteger("2").pow(insFan);                
            }

            if (middleFan != 0) {
                midPasses = new BigInteger("4").pow(middleFan);
            }
            
            if(insFan == 0 || middleFan == 0) {
                if(insFan == 0) {
                    totalPasses = midPasses;
                } else {
                    totalPasses = inPasses;
                }
            } else {
                totalPasses = inPasses.multiply(midPasses);
            }

            
        }        
        result[0] = totalPasses;
        result[1] = new BigInteger(Integer.toString(insFan));
        result[2] = new BigInteger(Integer.toString(middleFan));
        result[3] = new BigInteger(Integer.toString(totalFan));
                
        return result;
    }
}
