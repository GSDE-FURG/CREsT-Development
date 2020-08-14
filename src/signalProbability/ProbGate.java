/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signalProbability;

import datastructures.Cell;
import datastructures.Gate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import ops.CommonOps;

/**
 *
 * @author matheus
 */
public class ProbGate extends Gate {
    
    private ArrayList<ProbSignal> pInputs;
    private ArrayList<ProbSignal> pOutputs;
    private BigDecimal[][] reliabilityMatrix;
    private float[][] reliabilityMatrix2;
    private BigDecimal gateReliability;
    private int depth;
    
    public ProbGate(String id, Cell type, ArrayList<ProbSignal> inputs, ArrayList<ProbSignal> outputs) {
        super(id, type);
        this.pInputs = inputs;
        this.pOutputs = outputs;
    }
    
    public ProbGate(Gate gate) {
        super(gate.getId(), gate.getType());
        this.setInputs(gate.getInputs());
        this.setOutputs(gate.getOutputs());
        
        this.pInputs = new ArrayList<>();
        this.pOutputs = new ArrayList<>();
    }
    
    /**
     * This method return the Gate Input Signal list.
     *
     * @return Arraylist - Input Signal list
     */
    public ArrayList<ProbSignal> getpInputs() {
        return pInputs;
    }

    /**
     * This method set the Gate Input Signal list.
     *
     * @param inputs ArrayList - Input Signal list
     */
    public void setpInputs(ArrayList<ProbSignal> inputs) {
        this.pInputs = inputs;
    }
    
    /**
     * This method add a Signal Input to the Inputs Gate list.
     *
     * @param input Signal - Signal Input it'll be added
     */
    public void addPInput(ProbSignal input) {
        pInputs.add(input);
    }

    /**
     * This method remove an Input Signal from the Input Signal List
 list.
     *
     * @param input Signal - Signal Input it'll be removed
     */
    public void removePInput(ProbSignal input) {
        pInputs.remove(input);
    }

    /**
     * This method return the Gate Output Signal list.
     *
     * @return Arraylist - Output Signal list
     */
    public ArrayList<ProbSignal> getpOutputs() {
        return pOutputs;
    }

    /**
     * This method set the Gate Output Signal list.
     *
     * @param outputs ArrayList - Output Signal list
     */
    public void setpOutputs(ArrayList<ProbSignal> outputs) {
        this.pOutputs = outputs;
    }
    
    /**
     * This method add a Signal Output to the Outputs Gate list.
     *
     * @param output Signal - Signal Output it'll be added
     */
    public void addPOutput(ProbSignal output) {
        pOutputs.add(output);
    }

    /**
     * This method remove an Output Signal from the Output Signal List
 list.
     *
     * @param output Signal - Signal Output it'll be removed
     */
    public void removePOutput(ProbSignal output) {
        pOutputs.remove(output);
    }

    public BigDecimal[][] getReliabilityMatrix() {
        return reliabilityMatrix;
    }

    public void setReliabilityMatrix(BigDecimal gateReliability) {
        BigDecimal[][] itm = this.getType().getItm2();
        BigDecimal[][] ptm = new BigDecimal[itm.length][itm[0].length];
        
        
        for (int i = 0; i < itm.length; i++) {
            for (int j = 0; j < itm[0].length; j++) {
                if(itm[i][j].compareTo(BigDecimal.ONE) == 0) {
                    ptm[i][j] = gateReliability;
                } else {
                    ptm[i][j] = BigDecimal.ONE.subtract(gateReliability);
                }
            }
            
        }
        this.reliabilityMatrix = ptm;
    }
    
    public void setReliabilityMatrix(BigDecimal[][] reliabilityMatrix) {
        this.reliabilityMatrix = reliabilityMatrix;
    }

    public float[][] getReliabilityMatrix2() {
        return reliabilityMatrix2;
    }

    public void setReliabilityMatrix2(float[][] reliabilityMatrix2) {
        this.reliabilityMatrix2 = reliabilityMatrix2;
    }
    
    

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public BigDecimal getGateReliability() {
        return gateReliability;
    }

    public void setGateReliability(BigDecimal gateReliability) {
        this.gateReliability = gateReliability;
    }        
        
}
