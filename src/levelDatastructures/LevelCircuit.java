/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levelDatastructures;

import datastructures.Circuit;
import datastructures.Gate;
import datastructures.Signal;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author matheus
 */
public class LevelCircuit extends Circuit {
    
    private ArrayList<DepthGate> depthGates;
    private ArrayList<GateLevel> gateLevels;
    private ArrayList<InterLevel> interLevels;
    private BigDecimal tecReliability;
    
    public LevelCircuit(Circuit circuit) {                
        
        super(circuit.getName(), circuit.getSignals(), circuit.getGates());        
                
        depthGates = new ArrayList<>();
        gateLevels = new ArrayList<>();
        interLevels = new ArrayList<>();

        long gatesDEPTH;
        long gateLEVELS;
        long interLEVELS;
        
        for (int i = 0; i < circuit.getGates().size(); i++) {            
            depthGates.add(new DepthGate(circuit.getGates().get(i)));
        }        

        //gatesDEPTH = timenow();
        setGatesDepth();
        //timestamp(gatesDEPTH, "Gates Depth DFS");

        //gateLEVELS = timenow();
        setGateLevels();
        //timestamp(gateLEVELS, "Gate Levels setting");

        //interLEVELS = timenow();
        setInterLevels();
        //timestamp(interLEVELS, "Inter Levels setting");
        
    }
    
//    public LevelCircuit(String name, ArrayList<Signal> signals, ArrayList<Gate> gates) {
//        
//        super(name, signals, gates);
//        
//        depthGates = new ArrayList<>();
//        gateLevels = new ArrayList<>();
//        interLevels = new ArrayList<>();
//        
//        for (int i = 0; i < this.getGates().size(); i++) {
//            depthGates.add(new DepthGate(this.getGates().get(i)));
//        }
//        
//        
//        
//        setGatesDepth();
//        setGateLevels();
//        setInterLevels();
//        
//    }

    public ArrayList<DepthGate> getDepthGates() {
        return depthGates;
    }

    public ArrayList<GateLevel> getGateLevels() {
        return gateLevels;
    }

    public ArrayList<InterLevel> getInterLevels() {
        return interLevels;
    }

    public BigDecimal getTecReliability() {
        return tecReliability;
    }

    public void setTecReliability(BigDecimal tecReliability) {
        this.tecReliability = tecReliability;
    }
        
    

    public void setDepthGates(ArrayList<DepthGate> gates) {
        this.depthGates = gates;
    }
    
    
    
    /**
     * This method add a Gate to the Circuit Gates list.
     *
     * @param gate Gate - Gate it'll be added
     */
    public void addGate(DepthGate gate) {
        depthGates.add(gate);
    }

    /**
     * This method remove a Gate from the Circuit Gates List
     *
     * @param gate Gate - Gate it'll be removed
     */
    public void removeGate(DepthGate gate) {
        depthGates.remove(gate);
    }
    
    public void setGatesDepth() {
                
        for (int i = 0; i < this.getOutputs().size(); i++) {
            gateDfs(getDepthGate(this.getOutputs().get(i).getSignalOrigin()));
        }
                
    }
    
    public DepthGate gateDfs(DepthGate gate) {
        
        /**
         * ANTIGO (QUE DEU PROBLEMA NO CIRCUITO COM 68 NÍVEIS
         */
//        if (!gate.visited) {
//            
//            int fooDepth = 0;            
//            ArrayList<Signal> inputs = gate.getGate().getInputs();
//            gate.visited = true;
//
//            for (int i = 0; i < inputs.size(); i++) {
//                DepthGate fooGate = null;
//                
//                if(inputs.get(i).getOrigin() != null) {
//                    fooGate = gateDfs(getDepthGate(inputs.get(i).getOrigin()));
//                }
//                
//                if (fooGate != null) {
//                    if (fooGate.getDepth() > fooDepth) {
//                        fooDepth = fooGate.getDepth();
//                    }
//                } 
//            }
//
//            gate.setDepth(fooDepth + 1);        
//        }
        
        int fooDepth = 0;            
        ArrayList<Signal> inputs = gate.getGate().getInputs();
        
        if(gate.currentIn < inputs.size()) {
            
        }

        if (!gate.visited) {
            

            gate.visited = true;

            for (int i = 0; i < inputs.size(); i++) {
                DepthGate fooGate = null;
                
                if(inputs.get(i).getSignalOrigin() != null) {
                    fooGate = gateDfs(getDepthGate(inputs.get(i).getSignalOrigin()));
                }
                
                if (fooGate != null) {
                    if (fooGate.getDepth() > fooDepth) {
                        fooDepth = fooGate.getDepth();
                    }
                } 
            }

            gate.setDepth(fooDepth + 1);        
        }
        
        return gate;
    }
    
    public DepthGate getDepthGate(Gate gate) {
        for (int i = 0; i < this.depthGates.size(); i++) {
            if(this.depthGates.get(i).getGate().equals(gate)) {
                return this.depthGates.get(i);
            } 
        }
        
        return null;
    }
    
    public ArrayList<DepthGate> getDepthGatesByLevel(int level) {
        ArrayList<DepthGate> fooDepthGates = new ArrayList<>();
        
        for (int n = 0; n < this.depthGates.size(); n++) {
            if(this.depthGates.get(n).getDepth() == level) {
                fooDepthGates.add(this.depthGates.get(n));
            }
        }
        
        return fooDepthGates;
    }
    
    public void setGateLevels() {
        int lastLevel = 0;
        int inCounter = 0;
        int outCounter = 0;
        
        ArrayList<Object> foo = new ArrayList<>();
        
        for (int i = 0; i < getOutputs().size(); i++) {
            foo.add(getDepthGate(getOutputs().get(i).getSignalOrigin()));
        }
        
        for (int i = 0; i < foo.size(); i++) {    
            
            if (foo.get(i) instanceof DepthGate) {
                
                DepthGate fooGate = (DepthGate)foo.get(i);
                /*
                ** Verify the last depth of circuit
                */
                if (lastLevel < fooGate.getDepth()) {
                    lastLevel = fooGate.getDepth();
                }
            }                            
        }
        
        GateLevel gateLevel = new GateLevel(lastLevel);
        
        for (int i = 0; i < foo.size(); i++) {
            if (foo.get(i) instanceof DepthGate) {
                
                DepthGate fooGate = (DepthGate)foo.get(i);
                
                if (fooGate.getDepth() == gateLevel.getLevel()) {
                    if(!gateLevel.containsGate(foo.get(i))) {
                        gateLevel.addGate(fooGate);
                        inCounter += fooGate.getGate().getInputs().size();
                        outCounter += fooGate.getGate().getOutputs().size();
                    }
                } else {
                    /*
                    ** Take the previous depthGate outputs
                    */
                    for (int j = 0; j < fooGate.getGate().getOutputs().size(); j++) {
                        Signal fooSignal = fooGate.getGate().getOutputs().get(j);
                        gateLevel.addGate(fooSignal);
                        inCounter++;
                        outCounter++;
                    }
                    
                }                
            } 
        }
        
        gateLevel.setIn(inCounter);
        gateLevel.setOut(outCounter);
        this.gateLevels.add(gateLevel);
        makeLeftGateLevels(gateLevel);
        
        
        
        
    }
    
    public void setInterLevels() {

        
        for (int i = 0; i < gateLevels.size(); i++) {
            if(i == 0) {
                InterLevel interLevel = new InterLevel(1);
                for (int j = 0; j < getInputs().size(); j++) {
                    interLevel.addIn(getInputs().get(j));
                }
                
                ArrayList<Object> gateLevel = gateLevels.get(i).getGates();
                for (int j = 0; j < gateLevel.size(); j++) {
                    if(gateLevel.get(j) instanceof DepthGate) {
                        DepthGate fooGate = (DepthGate)gateLevel.get(j);
                        ArrayList<Signal> fooSignals = fooGate.getGate().getInputs();
                        for (int k = 0; k < fooSignals.size(); k++) {
                            interLevel.addOut(fooSignals.get(k));
                        }
                    } else {
                        interLevel.addOut((Signal)gateLevel.get(j));
                    }  
                }
                
                interLevels.add(interLevel);
            } else {
                InterLevel interLevel = new InterLevel(i+1);
                ArrayList<Object> originGates = gateLevels.get(i-1).getGates();
                ArrayList<Object> destGates = gateLevels.get(i).getGates();
                
                for (int j = 0; j < originGates.size(); j++) {
                    if(originGates.get(j) instanceof DepthGate) {
                        DepthGate fooGate = (DepthGate)originGates.get(j);
                        ArrayList<Signal> fooSignals = fooGate.getGate().getOutputs();
                        for (int k = 0; k < fooSignals.size(); k++) {
                            interLevel.addIn(fooSignals.get(k));
                        }
                    } else {
                        interLevel.addIn((Signal)originGates.get(j));
                    }  
                }
                
                for (int j = 0; j < destGates.size(); j++) {
                    if(destGates.get(j) instanceof DepthGate) {
                        DepthGate fooGate = (DepthGate)destGates.get(j);
                        ArrayList<Signal> fooSignals = fooGate.getGate().getInputs();
                        for (int k = 0; k < fooSignals.size(); k++) {
                            interLevel.addOut(fooSignals.get(k));
                        }
                    } else {
                        interLevel.addOut((Signal)destGates.get(j));
                    }  
                }
                interLevels.add(interLevel);
            }
            
        }
    }
    
    public void makeLeftGateLevels(GateLevel lastGateLevel) {
        
        int currentLevelNumber = lastGateLevel.getLevel() - 1;
        
        int inCounter = 0;
        int outCounter = 0;

        
        if(currentLevelNumber != 0) {        
            GateLevel currentLevel = new GateLevel(currentLevelNumber);

            for (int j = 0; j < lastGateLevel.getGates().size(); j++) {
                Object gate = lastGateLevel.getGates().get(j);

                if (gate instanceof DepthGate) {
                    DepthGate fooGate = (DepthGate)gate;
                    for (int i = 0; i < fooGate.getGate().getInputs().size(); i++) {
                        Signal fooSignal = fooGate.getGate().getInputs().get(i);                    
                        DepthGate originGate = getDepthGate(fooSignal.getSignalOrigin());
                        if (originGate != null) {
                            if (originGate.getDepth() != currentLevelNumber) {                            
                                currentLevel.addGate(fooSignal);
                                inCounter++;
                                outCounter++;
                            } else {
                                if(!currentLevel.containsGate((Object)originGate)) {
                                    currentLevel.addGate((Object)originGate);
                                    inCounter += originGate.getGate().getInputs().size();
                                    outCounter += originGate.getGate().getOutputs().size();
                                }
                            }
                        } else {
                            currentLevel.addGate((Object)fooSignal);
                            inCounter++;
                            outCounter++;
                        } 
                    }
                } else if(gate instanceof Signal) {
                    Signal fooSignal = (Signal)gate;
                    DepthGate originGate = getDepthGate(fooSignal.getSignalOrigin());

                    if(originGate != null) {
                        if (originGate.getDepth() != currentLevelNumber) {
                                currentLevel.addGate(fooSignal);
                                inCounter++;
                                outCounter++;
                        } else {
                            if(!currentLevel.containsGate((Object)originGate)) {
                                currentLevel.addGate(originGate);
                                inCounter += originGate.getGate().getInputs().size();
                                outCounter += originGate.getGate().getOutputs().size();
                            }                        
                        }
                    } else {
                        if(!currentLevel.containsGate((Object)fooSignal)) {
                            currentLevel.addGate(fooSignal);
                            inCounter++;
                            outCounter++;
                        }  
                    }
                }            
            }

            currentLevel.setIn(inCounter);
            currentLevel.setOut(outCounter);

            this.gateLevels.add(0, currentLevel);
            //System.out.println("teste");
            if(currentLevel.getLevel() != 1) {
                makeLeftGateLevels(currentLevel);

            }
        }
    }
}
