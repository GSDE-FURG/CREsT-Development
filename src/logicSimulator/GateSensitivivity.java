package logicSimulator;

import levelDatastructures.DepthGate;

import java.util.ArrayList;

public class GateSensitivivity {

    private ArrayList <String> inputs;

    private DepthGate gate;

    private GateSensitivivity(DepthGate gate){
        this.gate = gate;
    }

    private void setGate(DepthGate gate){
        gate = gate;
    }

    private void setInputs(String vector_value){
        this.inputs.add(vector_value);
    }

    public DepthGate getGate() {
        return gate;
    }

    public ArrayList<String> getInputs() {
        return inputs;
    }
}
