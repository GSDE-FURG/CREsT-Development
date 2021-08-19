/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import datastructures.Cell;
import datastructures.CellLibrary;
import datastructures.Gate;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptException;
import ops.CommonOps;
import ops.PTMOps;
import ops.PTMOps2;
import ops.SPRMultiPassV3Ops;
import ops.SPROps;
import readers.MappedVerilogReader;
import signalProbability.ProbCircuit;
import signalProbability.ProbGate;
import signalProbability.ProbGateLevel;
import signalProbability.ProbInterLevel;
import signalProbability.ProbSignal;

/**
 *
 * @author Matheus Pontes
 */
public class CommandLineCustom {
    
    public CommandLineCustom() {
        System.out.println("papai");
    }
    
    public CommandLineCustom(String[] args) {
        
        CellLibrary library = new CellLibrary();
        ProbCircuit pCircuit;
        
        String libraryPath = CommonOps.getWorkPath(this) + "abc" + File.separator + args[0];
        String circuitPath = CommonOps.getWorkPath(this) + "abc" + File.separator + args[1];
        
        try {
            library.initLibrary(libraryPath);
            pCircuit = new ProbCircuit(new MappedVerilogReader(circuitPath, library).getCircuit());
            
            
            for (Cell cell : library.getCells()) {
                System.out.println("Cell ==> " + cell);
            }

            for (ProbGate pGate : pCircuit.getProbGates()) {
                System.out.println("pGate DEPTH ==> " + pGate.getDepth());
            }
            
            for (ProbGateLevel pGLevel : pCircuit.getProbGateLevels()) {
                System.out.println("ProbGateLevel ==> " + pGLevel.getLevel());
                
                for(ProbGate pGate : pGLevel.getProbGates()) {
                    System.out.println("Gate ==> " + pGate);
                }
            }
            
            for (ProbInterLevel pILevel : pCircuit.getProbInterLevels()) {
                System.out.println("ProbInterLevel ==> " + pILevel.getLevel());
                
                System.out.println("ProbSingals IN");
                for (ProbSignal pSignal : pILevel.getInSignals()) {
                    System.out.println("ProbSignal " + pSignal);
                }
                
                System.out.println("ProbSingals OUT");
                for (ProbSignal pSignal : pILevel.getOutSignals()) {
                    System.out.println("ProbSignal " + pSignal);
                }
            }
            
            library.setPTMCells2((float).999999);
            library.setPTMCells(new BigDecimal("0.999999"));

            
//            pCircuit.clearProbSignalsMatrix();                    
//            pCircuit.setDefaultProbSourceSignalMatrix();
//            pCircuit.setProbSignalStates(false);
//            pCircuit.setPTMReliabilityMatrix();
//            
//            System.out.println("PTM em 0.999999 ==> " + PTMOps2.getCircuitReliabilityByPTM(pCircuit));
//            
//            pCircuit.clearProbSignalsMatrix();                    
//            pCircuit.setDefaultProbSourceSignalMatrix();
//            pCircuit.setProbSignalStates(false);
//            pCircuit.setPTMReliabilityMatrix();
//            
//            System.out.println("SPR em 0.999999 ==> " + SPROps.getSPRReliability(pCircuit));
            
            pCircuit.clearProbSignalsMatrix();                    
            pCircuit.setDefaultProbSourceSignalMatrix();
            pCircuit.setProbSignalStates(false);
            pCircuit.setPTMReliabilityMatrix();
            
            long timeNow = System.currentTimeMillis();
            
            System.out.println("SPR-MP em 0.999999 ==> " + SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit));
            
            System.out.println("LEVOU ==> " + (System.currentTimeMillis() - timeNow));
                        

        } catch (IOException | ScriptException ex) {
            System.out.println("Deu ruim!! ==> " + ex);
            Logger.getLogger(CommandLineCustom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println("Deu ruim!! ==> " + ex);
            Logger.getLogger(CommandLineCustom.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        


    }
    
}
