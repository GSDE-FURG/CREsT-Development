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
public class InterLevel {
    
    private int level;
    private ArrayList<Signal> inSingnals;
    private ArrayList<Signal> outSingnals;
    
    public InterLevel(int level) {
        this.level = level;
        inSingnals = new ArrayList<>();
        outSingnals = new ArrayList<>();
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<Signal> getInSingnals() {
        return inSingnals;
    }

    public ArrayList<Signal> getOutSingnals() {
        return outSingnals;
    }
    
    public void addIn(Signal signal) {
        inSingnals.add(signal);
    }
    
    public void addOut(Signal signal) {
        outSingnals.add(signal);
    }
}
