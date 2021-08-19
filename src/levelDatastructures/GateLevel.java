/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levelDatastructures;

import datastructures.Signal;
import java.util.ArrayList;

/**
 *
 * @author matheus
 */
public class GateLevel {
    private ArrayList<Object> gates;
    private int level;
    private int in;
    private int out;
    
    public GateLevel(int level) {
        this.gates = new ArrayList<>();
        this.level = level;
    }
    
    public GateLevel(int level, ArrayList<Object> gates) {
        this.level = level;
        this.gates = gates;
    }
    
    public ArrayList<Object> getGates() {
        return gates;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void addGate(Object gate) {
        gates.add(gate);
    }
    
    public boolean containsGate(Object gate) {
        if(this.gates.contains(gate)) {
            return true;
        } else {
            return false;
        }        
    }
    
    public ArrayList<DepthGate> getDepthGates() {
        ArrayList<DepthGate> depthGates = new ArrayList<>();
        
        for (int i = 0; i < gates.size(); i++) {
            if(gates.get(i) instanceof DepthGate) {
                depthGates.add((DepthGate)gates.get(i));
            }
        }        
        return depthGates;
    }
    
    public ArrayList<Signal> getWireGates() {
        ArrayList<Signal> wireGates = new ArrayList<>();
        
        for (int i = 0; i < gates.size(); i++) {
            if(gates.get(i) instanceof Signal) {
                wireGates.add((Signal)gates.get(i));
            }
        }        
        return wireGates;
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

    public void setOut(int out) {
        this.out = out;
    }
    
    public void setGates(ArrayList<Object> gates) {
        this.gates = gates;
    }
    
}
