/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signalProbability;

import datastructures.Signal;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import levelDatastructures.DepthGate;
import levelDatastructures.GateLevel;

/**
 *
 * @author matheus
 */
public class ProbGateLevel {
    
    private ArrayList<Object> gates;
    private int level;
    private int in;
    private int out;
    
    public ProbGateLevel(int level) {
        this.gates = new ArrayList<>();
        this.level = level;
    }
    
    public ArrayList<Object> getGates() {
        
        return gates;
    }

    public void setGates(ArrayList<Object> gates) {
        this.gates = gates;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public int getOut() {
        return out;
    }

//    public ProbGateLevel(int level, GateLevel gLevel) {
//        
//        super(level);
//
//        
//    }
//    public ProbGateLevel(GateLevel gLevel) {
//        
//        super(gLevel.getLevel(), gLevel.getGates());
//        this.setIn(gLevel.getIn());
//        this.setOut(gLevel.getOut());
//        
//    }
    public void setOut(int out) {    
        this.out = out;
    }

    public ArrayList<ProbGate> getProbGates() {
        ArrayList<ProbGate> pGates = new ArrayList<>();
        
        for (int i = 0; i < this.getGates().size(); i++) {
            if(this.getGates().get(i) instanceof ProbGate) {
                pGates.add((ProbGate)this.getGates().get(i));
            }
        }
        
        return pGates;
    }
    
    public ArrayList<ProbSignal> getProbWireGates() {
        ArrayList<ProbSignal> pWireGates = new ArrayList<>();
        
        for (int i = 0; i < this.getGates().size(); i++) {
            if(this.getGates().get(i) instanceof ProbSignal) {
                pWireGates.add((ProbSignal)this.getGates().get(i));
            }
        }        
        return pWireGates;
    }
    
    public boolean containsGate(Object gate) {
        if(this.gates.contains(gate)) {
            return true;
        } else {
            return false;
        }        
    }
    
    public void addGate(Object gate) {
        gates.add(gate);
    }
}
