package logicSimulator;

import datastructures.Circuit;

import java.util.ArrayList;

public class CircuitPath {

    private ArrayList <GateSensitivivity> gatesInCircuitpath;
    private int threadId;
    private String inputVector;

    private void setGateInCircuitPath(GateSensitivivity gate){
        this.gatesInCircuitpath.add(gate);
    }

    public ArrayList<GateSensitivivity> getGatesInCircuitpath() {
        return gatesInCircuitpath;
    }

    public void setInputVector(String inputVector) {
        this.inputVector = inputVector;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }

    public String getInputVector() {
        return inputVector;
    }

    public int getThreadId() {
        return threadId;
    }
}
