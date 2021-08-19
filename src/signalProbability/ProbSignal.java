    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signalProbability;

import datastructures.Gate;
import datastructures.Signal;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author matheus
 */
public class ProbSignal extends Signal {
    
    private BigDecimal[][] probMatrix;
    private float[][] probMatrixFloat;
    private boolean visited = false;
    private ProbGate origin;
    private ArrayList<ProbGate> destiny = new ArrayList<>();
    private int currentState;
    private int[] states;
    private ArrayList<Boolean> signalValues;
    
    public ProbSignal(String id, Gate origin) {
        super(id);
        this.setOrigin(origin);
        this.setCurrentState(4);
    }
    
    public ProbSignal(String id, ArrayList<Gate> destiny) {
        super(id);
        this.setDestiny(destiny);
        this.setCurrentState(4);
    }
    
    public ProbSignal(String id, Gate origin, ArrayList<Gate> destiny) {
        
        super(id, origin, destiny);
        this.setCurrentState(4);

    }
    
    public ProbSignal(Signal signal) {
        super(signal.getId());
        if(signal.getOrigin() == null) {
            this.setDestiny(signal.getDestiny());                
        } else if(signal.getDestiny().isEmpty()) {
            this.setOrigin(signal.getOrigin());
        } else {
            this.setDestiny(signal.getDestiny());
            this.setOrigin(signal.getOrigin());            
        }
        this.setCurrentState(4);
    }

    public BigDecimal[][] getProbMatrix() {
        return probMatrix;
    }

    public void setProbMatrix(BigDecimal[][] probMatrix) {
        this.probMatrix = probMatrix;
    }

    public float[][] getProbMatrixFloat() {
        return probMatrixFloat;
    }

    public void setProbMatrixFloat(float[][] probMatrixFloat) {
        this.probMatrixFloat = probMatrixFloat;
    }
        
    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public ProbGate getPOrigin() {
        return origin;
    }

    public void setPOrigin(ProbGate origin) {
        this.origin = origin;
    }

    public ArrayList<ProbGate> getPDestiny() {
        return destiny;
    }

    public void setPDestiny(ArrayList<ProbGate> destiny) {
        this.destiny = destiny;
    }
    
    public void addDestiny(ProbGate pGate) {
        destiny.add(pGate);
    }

    public void removeDestiny(ProbGate pGate) {
        destiny.remove(pGate);
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public int[] getStates() {
        return states;
    }

    public void setStates(int[] states) {
        this.states = states;
    }

    public ArrayList<Boolean> getSignalValues() {
        return signalValues;
    }

    public void setSignalValues(ArrayList<Boolean> signalValues) {
        this.signalValues = signalValues;
    }        
    
    public void clearSignalValues() {
        this.signalValues.clear();
    }        
    
}
    
    

