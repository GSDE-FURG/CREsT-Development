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
public class ProbGateLevel extends GateLevel {
    
    
    
    public ProbGateLevel(int level, GateLevel gLevel) {
        
        super(level);
        
        
    }
    
    public ProbGateLevel(GateLevel gLevel) {
        
        super(gLevel.getLevel(), gLevel.getGates());
        this.setIn(gLevel.getIn());
        this.setOut(gLevel.getOut());
        
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
}
