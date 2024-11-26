/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulator;

import datastructures.CellLibrary;
import datastructures.CustomMatrixLibrary;
import datastructures.InputVector;
import ops.PTMOps2;
import signalProbability.ProbCircuit;
import tool.Terminal;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author Matheus Pontes
 */
public class PTMMController {

    private Terminal terminal;
    private ProbCircuit pCircuit;
    private CellLibrary cellLib;
    private CustomMatrixLibrary cMatrixLib;
    private boolean ptmmPrepared = false;



    public PTMMController(Terminal terminal) {
        this.terminal = terminal;
        this.initController();
    }

    public PTMMController(ProbCircuit pCircuit, CellLibrary cLibrary) {
        this.pCircuit = pCircuit;
        this.cellLib = cLibrary;
        this.verifyInputs();
    }
    
    public BigDecimal getReliability() {
        prepareForPTMM(new BigDecimal("0.99999802495"));
        //prepareForPTMM(new BigDecimal("0.9"));

        int[] itm = PTMOps2.getCircuitITM(this.pCircuit);
        BigDecimal[][] ptm = PTMOps2.getCircuitPTMMatrix(this.pCircuit);

        for(BigInteger i : PTMOps2.getMTBFInputVectors(itm, ptm)) {
            System.out.println(i);
        }

        BigDecimal confValue = PTMOps2.getCircuitReliabilityByPTM(pCircuit, "-1");
        return confValue;
    }

    public BigDecimal getReliability(String fixedGateReliability) {
        prepareForPTMM(new BigDecimal(fixedGateReliability));

        BigDecimal confValue = PTMOps2.getCircuitReliabilityByPTM(pCircuit, "-1");
        return confValue;
    }

    //public BigDecimal getReliability(int scale) {
    //    prepareForSPR(new BigDecimal("0.99999802495"));
    //    BigDecimal confValue = SPROpsChuloMedio.getSPRReliability(pCircuit, scale);
    //    return confValue;
    //}



    //public BigDecimal getReliability(InputVector inVector, int scale) {
    public BigDecimal getReliability(InputVector inVector) {
        if(!this.ptmmPrepared) {
            prepareForPTMM(new BigDecimal("0.99999802495"));
        }
        BigDecimal confValue = PTMOps2.getCircuitReliabilityByPTM(pCircuit, inVector.getDecimalString());

        return confValue;
    }

    public BigDecimal getReliability(InputVector inVector, String fixedGateReliability) {
        prepareForPTMM(new BigDecimal(fixedGateReliability));
        BigDecimal confValue = PTMOps2.getCircuitReliabilityByPTM(pCircuit, inVector.getDecimalString());

        return confValue;
    }
    
    public BigDecimal getReliability(String decimalVector, int scale) {
        if(!this.ptmmPrepared) {
            prepareForPTMM(new BigDecimal("0.99999802495"));
        }
        BigDecimal confValue = PTMOps2.getCircuitReliabilityByPTM(pCircuit, decimalVector);
        return confValue;
    }
    /*
    public BigDecimal getReliability(String decimalVector, int scale, CustomMatrixLibrary customLib) {
        if(!this.sprPrepared) {
            prepareForSPR(new BigDecimal("0.99999802495"));
        }
        pCircuit.setCustomMatrix(customLib);
        BigDecimal confValue = SPROpsChuloMedio.getSPRReliability(pCircuit, new InputVector(decimalVector), scale);
        return confValue;
    } */

    /*
    public BigDecimal getReliability(String reliValue) {
        prepareForSPR(new BigDecimal(reliValue));
        BigDecimal confValue = SPROpsChuloMedio.getSPRReliability(pCircuit);
        return confValue;
    } */

    /*
    public BigDecimal getReliability(BigDecimal reliValue) {
        prepareForSPR(reliValue);
        BigDecimal confValue = SPROpsChuloMedio.getSPRReliability(pCircuit);
        return confValue;
    } */
    
    public BigDecimal getReliabilityCustomLib() {
        prepareForPTMM(new BigDecimal("0.99999802495"));
        
        pCircuit.setCustomMatrix(cMatrixLib);

        int[] itm = PTMOps2.getCircuitITM(this.pCircuit);
        BigDecimal[][] ptm = PTMOps2.getCircuitPTMMatrix(this.pCircuit);

        for(BigInteger i : PTMOps2.getMTBFInputVectors(itm, ptm)) {
            System.out.println(i);
        }

        BigDecimal confValue = PTMOps2.getCircuitReliabilityByPTM(pCircuit, "-1");
        return confValue;
    }

    /*
    public BigDecimal getReliabilityCustomLib(String reliValue) {
        prepareForSPR(new BigDecimal(reliValue));
        
        pCircuit.setCustomMatrix(cMatrixLib);
        BigDecimal confValue = SPROpsChuloMedio.getSPRReliability(pCircuit);
        return confValue;
    } */

    /*
    public BigDecimal getReliabilityCustomLib(BigDecimal reliValue) {
        prepareForSPR(reliValue);
        pCircuit.setCustomMatrix(cMatrixLib);
        BigDecimal confValue = SPROpsChuloMedio.getSPRReliability(pCircuit);
        return confValue;
    } */

    /*
    public BigDecimal getReliabilityCustomLib(CustomMatrixLibrary customLib) {
        prepareForSPR(new BigDecimal("0.99999802495"));         
        pCircuit.setCustomMatrix(customLib);
        BigDecimal confValue = SPROpsChuloMedio.getSPRReliability(pCircuit);
        return confValue;
    } */
    
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
        
    
    private void prepareForPTMM(BigDecimal reliability) {
        this.cellLib.setPTMCells(reliability);
        this.pCircuit.setPTMReliabilityMatrix();
        this.ptmmPrepared = true;
    }

    public int[] getCircuitITM() {
        if(!this.ptmmPrepared) {
            prepareForPTMM(new BigDecimal("0.99999802495"));
        }
        return PTMOps2.getCircuitITM(this.pCircuit);
    }

    public BigDecimal[][] getCircuitPTMMatrix() {
        if(!this.ptmmPrepared) {
            prepareForPTMM(new BigDecimal("0.99999802495"));
        }
        return PTMOps2.getCircuitPTMMatrix(this.pCircuit);
    }
}
