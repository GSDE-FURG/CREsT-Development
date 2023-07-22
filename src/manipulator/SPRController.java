/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulator;

import datastructures.Cell;
import datastructures.CellLibrary;
import datastructures.CustomMatrixLibrary;
import datastructures.InputVector;
import java.math.BigDecimal;

import ops.CommonOps;
import ops.SPROpsChuloMedio;
import ops.SPROpsV2;
import signalProbability.ProbCircuit;
import tool.Terminal;

/**
 *
 * @author Matheus Pontes
 */
public class SPRController {
    
    private Terminal terminal;
    private ProbCircuit pCircuit;
    private CellLibrary cellLib;
    private CustomMatrixLibrary cMatrixLib;
    private boolean sprPrepared = false;
    
    
    
    public SPRController(Terminal terminal) {
        this.terminal = terminal;
        this.initController();        
    }
    
    public SPRController(ProbCircuit pCircuit, CellLibrary cLibrary) {
        this.pCircuit = pCircuit;
        this.cellLib = cLibrary;
        this.verifyInputs();
    }
    
    public BigDecimal getReliability() {
        prepareForSPR(new BigDecimal("0.99999802495"));
        BigDecimal confValue = SPROpsChuloMedio.getSPRReliability(pCircuit);
        return confValue;
    }
    
    public BigDecimal getReliability(int scale) {
        prepareForSPR(new BigDecimal("0.99999802495"));
        BigDecimal confValue = SPROpsChuloMedio.getSPRReliability(pCircuit, scale);
        return confValue;
    }
    
    public BigDecimal getReliability(InputVector inVector, int scale) {
        if(!this.sprPrepared) {
            prepareForSPR(new BigDecimal("0.99999802495"));
        }         
        BigDecimal confValue = SPROpsChuloMedio.getSPRReliability(pCircuit, inVector, scale);
        return confValue;
    }
    
    public BigDecimal getReliability(String decimalVector, String gateReliability, int scale) {
        if(!this.sprPrepared) {
            prepareForSPR(new BigDecimal(gateReliability));
        }         
        BigDecimal confValue = SPROpsChuloMedio.getSPRReliability(pCircuit, new InputVector(decimalVector), scale);
        return confValue;
    }
    
    public BigDecimal getReliability(String decimalVector, int scale, CustomMatrixLibrary customLib) {
        if(!this.sprPrepared) {
            prepareForSPR(new BigDecimal("0.99999802495"));
        }
        pCircuit.setCustomMatrix(customLib);
        BigDecimal confValue = SPROpsChuloMedio.getSPRReliability(pCircuit, new InputVector(decimalVector), scale);
        return confValue;
    }
    
    public BigDecimal getReliability(String reliValue) {
        prepareForSPR(new BigDecimal(reliValue));
        BigDecimal confValue = SPROpsChuloMedio.getSPRReliability(pCircuit);
        return confValue;
    }

    public BigDecimal getReliability(String reliValue, int scale) {
        prepareForSPR(new BigDecimal(reliValue));
        BigDecimal confValue = SPROpsChuloMedio.getSPRReliability(pCircuit, scale);
        return confValue;
    }
    
    public BigDecimal getReliability(BigDecimal reliValue) {
        prepareForSPR(reliValue);
        BigDecimal confValue = SPROpsChuloMedio.getSPRReliability(pCircuit);
        return confValue;
    }
    
    public BigDecimal getReliabilityCustomLib() {
        prepareForSPR(new BigDecimal("0.99999802495"));
        
        pCircuit.setCustomMatrix(cMatrixLib);
        BigDecimal confValue = SPROpsChuloMedio.getSPRReliability(pCircuit);
        return confValue;
    }
    
    public BigDecimal getReliabilityCustomLib(String reliValue) {
        prepareForSPR(new BigDecimal(reliValue));
        
        pCircuit.setCustomMatrix(cMatrixLib);
        BigDecimal confValue = SPROpsChuloMedio.getSPRReliability(pCircuit);
        return confValue;
    }
    
    public BigDecimal getReliabilityCustomLib(BigDecimal reliValue) {
        prepareForSPR(reliValue);
        pCircuit.setCustomMatrix(cMatrixLib);
        BigDecimal confValue = SPROpsChuloMedio.getSPRReliability(pCircuit);
        return confValue;
    }
    
    public BigDecimal getReliabilityCustomLib(CustomMatrixLibrary customLib) {
        prepareForSPR(new BigDecimal("0.99999802495"));         
        pCircuit.setCustomMatrix(customLib);
        BigDecimal confValue = SPROpsChuloMedio.getSPRReliability(pCircuit);
        return confValue;
    }
    
    public BigDecimal getReliabilityMTBF() {
        return null;
    }
    
    public final void initController() {
        this.pCircuit = terminal.getProbCircuit();
        this.cellLib = terminal.getCellLibrary();
        this.cMatrixLib = terminal.getCustomMatrixLib();
        this.verifyInputs(terminal);
    }
    
    
    private void verifyInputs(Terminal t) {
        /**
         * Verify ProbCircuit
         */
        if(t.getProbCircuit() == null) {
            System.out.println("ProbCircuit not initialized");
        }
        
        /**
         * Verify CellLibrary
         */
        if(t.getCellLibrary() == null) {
            System.out.println("CellLibrary not initialized");
        }        
    }
    
    private void verifyInputs() {
        /**
         * Verify ProbCircuit
         */
        if(this.pCircuit == null) {
            System.out.println("ProbCircuit not initialized");
        }
        
        /**
         * Verify CellLibrary
         */
        if(this.cellLib == null) {
            System.out.println("CellLibrary not initialized");
        }        
    }
        
    
    private void prepareForSPR(BigDecimal reliability) {
        this.cellLib.setPTMCells(reliability);                                         
        this.pCircuit.setDefaultProbSourceSignalMatrix();
        this.pCircuit.setPTMReliabilityMatrix();
        this.sprPrepared = true;

        /**
         * Torna a confiabilidade VOTADOR ideal
         */
        Cell cell = this.cellLib.getCellByName("VOTADOR");
        if(cell != null) {
            this.cellLib.setPTMCellByName(cell.getName(), BigDecimal.ONE);
        }
    }
}
