/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levelDatastructures;

import datastructures.Gate;

/**
 *
 * @author matheus
 */
public class DepthGate {
    public boolean visited = false;
    public int currentIn = 0;
    private Gate gate;
    private int depth = 0;
    
    public DepthGate(Gate gate) {
        this.gate = gate;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setVisited(){
        this.visited = true;
    }

    public boolean isVisited() {
        return visited;
    }

    @Override
    public String toString() {
        return gate.toString();
    }        
    
}
