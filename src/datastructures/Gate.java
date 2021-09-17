/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.ArrayList;

/**
 * Represent a DataStructure of a Logical Gate
 * @author Matheus Ferreira Pontes
 */
public class Gate {
    
    private String id;
    private ArrayList<Signal> inputs;
    private ArrayList<Signal> outputs;    
    private Cell type;    
    
    /**
     * Simple Gate constructor.
     *
     */
    public Gate() {
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
    }
    
    /**
     * Gate constructor with defined "id".
     *
     * @param id - Gate identification
     */
    public Gate(String id) {
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
        this.id = id;
    }
    
    /**
     * Gate constructor with defined "id" and "type".
     *
     * @param id - Gate identification
     * @param type - Gate Type
     */
    public Gate(String id, Cell type) {
        this.id = id; 
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
        this.type = type;
    }
    
    /**
     * Gate constructor with defined "id", "inputs", "outputs" and "type".
     *
     * @param id - Gate identification
     * @param inputs - Gate's signals inputs
     * @param outputs - Gate's signal outputs
     * @param type - Gate Cell Type
     */
    public Gate(String id, ArrayList<Signal> inputs, 
                       ArrayList<Signal> outputs, Cell type) {
        this.id = id;
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
        this.type = type;
    }
    
    /**
     * This method return the Gate identification.
     *
     * @return String - Gate ID
     */
    public String getId() {
        return id;
    }

    /**
     * This method set the Gate Identification.
     *
     * @param id String - Gate ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method return the Gate Input Signal list.
     *
     * @return Arraylist - Input Signal list
     */
    public ArrayList<Signal> getInputs() {
        return inputs;
    }

    /**
     * This method set the Gate Input Signal list.
     *
     * @param inputs ArrayList - Input Signal list
     */
    public void setInputs(ArrayList<Signal> inputs) {
        this.inputs = inputs;
    }
    
    /**
     * This method add a Signal Input to the Inputs Gate list.
     *
     * @param input Signal - Signal Input it'll be added
     */
    public void addInput(Signal input) {
        inputs.add(input);
    }

    /**
     * This method remove an Input Signal from the Input Signal List
 list.
     *
     * @param input Signal - Signal Input it'll be removed
     */
    public void removeInput(Signal input) {
        inputs.remove(input);
    }

    /**
     * This method return the Gate Output Signal list.
     *
     * @return Arraylist - Output Signal list
     */
    public ArrayList<Signal> getOutputs() {
        return outputs;
    }

    /**
     * This method set the Gate Output Signal list.
     *
     * @param outputs ArrayList - Output Signal list
     */
    public void setOutputs(ArrayList<Signal> outputs) {
        this.outputs = outputs;
    }
    
    /**
     * This method add a Signal Output to the Outputs Gate list.
     *
     * @param output Signal - Signal Output it'll be added
     */
    public void addOutput(Signal output) {
        outputs.add(output);
    }

    /**
     * This method remove an Output Signal from the Output Signal List
 list.
     *
     * @param output Signal - Signal Output it'll be removed
     */
    public void removeOutput(Signal output) {
        outputs.remove(output);
    }

    /**
     * This method return the Gate Type.
     *
     * @return Cell - Gate type
     */    
    public Cell getType() {
        return type;
    }

    /**
     * This method set the Gate type describe by Cell object.
     *
     * @param type Cell - Gate type
     */
    public void setType(Cell type) {
        this.type = type;
    }   

    @Override
    public String toString() {
        return this.id;
    }





    /*Clayton*/
    public String getInputsValuesToString() {
        String o = "";
        for (int i = 0; i < this.getInputs().size(); i++) {
                o = o + this.getInputs().get(i).getLogicValue() + " - ";
        }
        return o;
    }

    /*Clayton*/
    public String getOutputsValuesToString() {
        String o = "";
        for (int i = 0; i < this.getOutputs().size(); i++) {
            o = o + this.getOutputs().get(i).getLogicValue() + " - ";
        }
        return o;
    }
}
