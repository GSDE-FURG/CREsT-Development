package logicSimulator;

import datastructures.CellLibrary;
import datastructures.Circuit;
import levelDatastructures.GateLevel;
import levelDatastructures.LevelCircuit;

import java.util.ArrayList;

public class createCircuitVectorIndependent {

    final private Circuit circuit;
    //private ArrayList<Circuit> circuitAccordingVector = new ArrayList<>();
    final private CellLibrary cellLibrary;
    final private LevelCircuit levelCircuit;
    final private int threadSimulationIndex;
    final private ArrayList <GateLevel> gatesLevels;
    String vector;
    String faultSig;
    public createCircuitVectorIndependent(Circuit circuit, CellLibrary cellLibrary, LevelCircuit levelCircuit, int threadSimulationIndex, ArrayList <GateLevel> gatesLevels){
        this.circuit = circuit;
        this.levelCircuit = levelCircuit;
        this.cellLibrary = cellLibrary;
        this.vector = "";
        this.faultSig = "";
        this.threadSimulationIndex = threadSimulationIndex;
        this.gatesLevels = gatesLevels;
    }

    public Circuit getCircuit() {
        return this.circuit;
    }

    public CellLibrary getCellLibrary() {
        return this.cellLibrary;
    }

    public LevelCircuit getLevelCircuit() {
        return this.levelCircuit;
    }

    public int getThreadSimulationIndex() {
        return this.threadSimulationIndex;
    }

    @Override
    public int hashCode() {
        return this.levelCircuit.hashCode();
    }
}
