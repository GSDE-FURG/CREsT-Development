/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.ArrayList;

/**
 *
 * @author Matheus Ferreira Pontes
 */
public class Signal {
    
    private String id;
    private Gate origin;
    private ArrayList<Gate> destiny = new ArrayList<>();
    //valor lógico esperado
    private boolean logicValue;
    //valor lógico incorreto
    private boolean fol;
    
    /**
     * Simple Signal constructor.
     *
     */
    public Signal() {

    }
    
    /**
     * Signal constructor with defined "id".
     *
     * @param id - Signal identification
     */
    public Signal(String id) {
        this.id = id;
    }
    
    /**
     * Signal constructor with defined "id", "origin" and "destiny".
     *
     * @param id - Signal identification
     * @param origin - Signal origin
     * @param destiny - Signal destinies
     */
    public Signal(String id, Gate origin, ArrayList<Gate> destiny) {
        this.id = id;
        this.origin = origin;
        this.destiny = destiny;
    }
    
    public boolean getLogicValue() {
        return logicValue;
    }

    public void setLogicValue(boolean logicValue) {
        this.logicValue = logicValue;
    }

    public boolean getFol() {
        return fol;
    }

    public void setFol(boolean fol) {
        this.fol = fol;
    }
    
    /**
     * This method return the Signal identification.
     *
     * @return String - Signal ID
     */
    public String getId() {
        return id;
    }
    
    /**
     * This method set the Signal identification.
     *
     * @param id String - Signal ID
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * This method return the Signal Origin (Gate's Output).
     *
     * @return Gate - Origin Gate
     */
    public Gate getOrigin() {
        return origin;
    }

    /**
     *
     * This method set the Signal origin
     * @param origin - Gate (output)
     */
    public void setOrigin(Gate origin) {
        this.origin = origin;
    }
    
    /**
     * This method return the Signal Destination (One or more Gates).
     *
     * @return ArrayList - Gates ArrayList
     */
    public ArrayList<Gate> getDestiny() {
        return destiny;
    }
    
    /**
     * This method set the Signal identification.
     *
     * @param destiny - Gates ArrayList
     */
    public void setDestiny(ArrayList<Gate> destiny) {
        this.destiny = destiny;
    }
    
    /**
     * This method add a Destination Gate to the Signal Destination Gate list.
     *
     * @param gate Gate - Destination Gate
     */
    public void addDestiny(Gate gate) {
        destiny.add(gate);
    }

    /**
     * This method remove a Destination Gate from the Signal Destination Gate
 list.
     *
     * @param gate Gate - Destination signal
     */
    public void removeDestiny(Gate gate) {
        destiny.remove(gate);
    }
    
    public String toString() {
        return this.id;
    }
}
