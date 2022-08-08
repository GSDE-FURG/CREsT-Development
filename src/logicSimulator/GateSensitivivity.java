package logicSimulator;

import levelDatastructures.DepthGate;

import java.util.ArrayList;

public class GateSensitivivity {

    private ArrayList <Boolean> inputs;

    private DepthGate gate;

    private Float sensitiveArea;

    GateSensitivivity(){
        //this.gate = gate;
        this.inputs = new ArrayList<>();
    }



    public void setGate(DepthGate gate) {
        this.gate = gate;
    }

    public void setSensitiveArea(Float sensitiveArea) {
        this.sensitiveArea = sensitiveArea;
    }

    public Float getSensitiveArea() {
        return sensitiveArea;
    }

    public void setInputs(ArrayList <Boolean> vector_value){
        this.inputs = vector_value;
    }

    public DepthGate getGate() {
        return this.gate;
    }

    public ArrayList<Boolean> getInputs() {
        return inputs;
    }
    public ArrayList<String> getInputsStr() {
        ArrayList<String> concat = new ArrayList<>();
        for (int i = 0; i < this.inputs.size() ; i++) {
            if(this.inputs.get(i) == Boolean.TRUE){
                   concat.add("1");
            }
            if(this.inputs.get(i) == Boolean.FALSE){
                concat.add("0");
            }
        }
        return concat;
    }

    public void print(){
        System.out.println("Gate: " + this.gate.getGate() + "  Input: " + this.inputs);
    }
}
