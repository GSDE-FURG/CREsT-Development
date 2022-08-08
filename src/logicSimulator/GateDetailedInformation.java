package logicSimulator;

import levelDatastructures.DepthGate;

import java.util.ArrayList;

public class GateDetailedInformation {

    private ArrayList <String> inputs;
    private ArrayList <String> outputs;

    private DepthGate gate;

    private Float gateSensitiveArea;

    GateDetailedInformation(){
        //this.gate = gate;
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
    }

    public void setGate(DepthGate gate) {
        this.gate = gate;
    }


    public void setOutputs(Boolean outputs) {

        if(outputs == true){
            this.outputs.add("1");
        }
        if(outputs == false){
            this.outputs.add("0");
        }
    }

    public ArrayList<String> getOutputs() {
        return outputs;
    }

    public void setgateSensitiveArea(Float gateSensitiveArea) {
        this.gateSensitiveArea = gateSensitiveArea;
    }

    public Float getgateSensitiveArea() {
        return gateSensitiveArea;
    }

    public void setInputs(ArrayList <Boolean> vector_value){
        ArrayList<String> concat = new ArrayList<>();
        for (int i = 0; i < vector_value.size() ; i++){
            if(vector_value.get(i) == Boolean.TRUE){
                concat.add("1");
            }
            if(vector_value.get(i) == Boolean.FALSE){
                concat.add("0");
            }
        }
        this.inputs = concat;
    }

    public DepthGate getGate() {
        return this.gate;
    }

    public ArrayList<String> getInputs() {
        return inputs;
    }


    public void print(){
        System.out.println("Gate: " + this.gate.getGate() + "  Input: " + this.inputs);
    }
}
