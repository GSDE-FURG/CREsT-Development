/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signalProbability;

import datastructures.Cell;
import datastructures.CellLibrary;
import datastructures.Circuit;
import datastructures.CustomMatrix;
import datastructures.CustomMatrixLibrary;
import java.util.ArrayList;
import datastructures.Signal;
import datastructures.Gate;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import levelDatastructures.DepthGate;
import levelDatastructures.GateLevel;
import ops.CommonOps;
import tool.Terminal;

import static ops.CommonOps.timenow;
import static ops.CommonOps.timestamp;

/**
 *
 * @author matheus
 */
public class ProbCircuit extends Circuit {
    
    private ArrayList<ProbSignal> probSignals = new ArrayList<>();
    private ArrayList<ProbGate> probGates = new ArrayList<>();
    private ArrayList<ProbSignal> pInputs;
    private ArrayList<ProbSignal> pOutputs;

    private ArrayList<ProbGateLevel> probGateLevels = new ArrayList<>();
    private ArrayList<ProbInterLevel> probInterLevels = new ArrayList<>();
    private Map<String, ArrayList<ProbGate>> probGatesOrganized;

    private boolean forInputs = false;
    private boolean forOutputs = false;
    
    private boolean probGatesOrgFlag = false;

    
    public ProbCircuit (Circuit circuit) {
        this(circuit.getName(), circuit.getSignals(), circuit.getGates());
    }
    
    public ProbCircuit(String name, ArrayList<Signal> signals, ArrayList<Gate> gates) {
        super(name, signals, gates);

        this.makeProbSignalsAndProbGates();
        //System.out.println("Foi o makeProbSignalsAndProbGates");
        this.setGatesDepth();

        // Matheus 2023-02-17 - O SPR UTILIZA OS OBJS ProbGateLevels - verificar se vale a pena manter isso
        this.makeProbGateLevels();

        this.makeProbInterLevels();
        
    }

    public ArrayList<ProbSignal> getProbSignals() {
        return probSignals;
    }

    public void setProbSignals(ArrayList<ProbSignal> probSignals) {
        this.probSignals = probSignals;
    }
    
    public void addProbSignal(ProbSignal probSignal) {
        probSignals.add(probSignal);
    }


    public void removeProbSignal(ProbSignal probSignal) {
        probSignals.remove(probSignal);
    }
    
    public ProbSignal getProbSignal(Signal signal) {
        for(ProbSignal pSignal : this.probSignals) {
            if(pSignal.getId().equals(signal.getId())) {
                return pSignal;
            }
        }
        
        return null;
    }
    
    public ProbSignal getProbSignal(String id) {
        for(ProbSignal pSignal : this.probSignals) {
            if(pSignal.getId().equals(id)) {
                return pSignal;
            }
        }
        
        return null;
    }
        
    
    
    public void setProbSignalMatrix(ProbSignal pSignal, BigDecimal[][] matrix) {
        for(ProbSignal probSignal : this.probSignals) {
            if(probSignal.equals(pSignal)) {
                probSignal.setProbMatrix(matrix);
            }
        }
    }
    
    public void setDefaultProbSourceSignalMatrix() {
        
        BigDecimal correctSignal = new BigDecimal("0.5");
        
        for(ProbSignal probSignal : this.probSignals) {
            if(probSignal.getOrigin() == null) {
                probSignal.setProbMatrix(new BigDecimal[][]{
                    {correctSignal, BigDecimal.ZERO},
                    {BigDecimal.ZERO, correctSignal}
                });
                
                probSignal.setProbMatrixFloat(new float[][]{
                    {0.5f,0f},
                    {0f,0.5f}
                });
            }
        }
    }
    
    public void setSourcesProbSignalMatrix(BigDecimal[][] matrix) {
        for(ProbSignal probSignal : this.probSignals) {
            if(probSignal.getOrigin() == null) {
                probSignal.setProbMatrix(matrix);
            }
        }
    }
    
    public void makeProbSignalsAndProbGates() {
        
        Map<String, ProbSignal> fooSignals = new LinkedHashMap<>();
        
        Map<String, ProbGate> fooGates = new LinkedHashMap<>();
        
        for (Signal signal : this.getSignals()) {
            
            ProbSignal pSignal = new ProbSignal(signal);
            
            fooSignals.put(pSignal.getId(), pSignal);
            
            probSignals.add(pSignal);
        }
        
        for (Gate gate : this.getGates()) {
            
            ProbGate pGate = new ProbGate(gate);
            
            for (int i = 0; i < pGate.getInputs().size(); i++) {
                Signal signal = pGate.getInputs().get(i);
                
                pGate.addPInput(fooSignals.get(signal.getId()));
                
            }
            
            for (int i = 0; i < pGate.getOutputs().size(); i++) {
                Signal signal = pGate.getOutputs().get(i);
                
                pGate.addPOutput(fooSignals.get(signal.getId()));
            }
            
            fooGates.put(pGate.getId(), pGate);
            
            probGates.add(pGate);
        }
        
        for (ProbSignal probSignal : probSignals) {
            
            if(!probSignal.getDestiny().isEmpty()) {
                
                  /**
                   * Antes de a ferramenta lidar com fanouts nas saídas
                   */
//                for (int i = 0; i < probSignal.getDestiny().size(); i++) {
//                    probSignal.addDestiny(fooGates.get(probSignal.getDestiny().get(i).getId()));
//                }
                
                for(Gate flagGate: probSignal.getDestiny()) {
                    if(flagGate != null) {
                        probSignal.addDestiny(fooGates.get(flagGate.getId()));
                    } else {
                        probSignal.addDestiny(null);
                    }
                }
            }
            
            if(probSignal.getOrigin() != null) {
                probSignal.setPOrigin(fooGates.get(probSignal.getOrigin().getId()));
            }
            
            
        }
    }

    public ArrayList<ProbSignal> getProbInputs_OLD() {

        ArrayList<ProbSignal> pInputs = new ArrayList<>();

        for(ProbSignal pSignal : this.probSignals) {
            if(pSignal.getOrigin() == null && !pSignal.getDestiny().isEmpty()) {
                pInputs.add(pSignal);
            }
        }
        return pInputs;
    }

    public ArrayList<ProbSignal> getProbOutputs_OLD() {

        ArrayList<ProbSignal> pOutputs = new ArrayList<>();

        for(ProbSignal pSignal : this.probSignals) {

//            if(pSignal.getDestiny().isEmpty()) {
//                pOutputs.add(pSignal);
//            }

            for(Gate fooGate: pSignal.getDestiny()) {
                if(fooGate == null) {
                    pOutputs.add(pSignal);
                    break;
                }
            }
        }
        return pOutputs;
    }
    
    public ArrayList<ProbSignal> getProbInputs() {

        if (forInputs) {
            return this.pInputs;
        } else {
            this.pInputs = new ArrayList<>();

            /**
             * 2023-03-03 - OLD - Antes eu desconsiderava sinais de entrada que não iam para nenhuma gate ou saída
             */
            //for (ProbSignal pSignal : this.probSignals) {
            //if (pSignal.getOrigin() == null && !pSignal.getDestiny().isEmpty()) {
            //        this.pInputs.add(pSignal);
            //    }
            //}

            for (ProbSignal pSignal : this.probSignals) {
                if (pSignal.getOrigin() == null) {
                    this.pInputs.add(pSignal);
                }
            }
            return pInputs;
        }
    }

    public ArrayList<ProbSignal> getProbOutputs() {

        if(forOutputs) {
            return this.pOutputs;
        } else {
            this.pOutputs = new ArrayList<>();

            for(ProbSignal pSignal : this.probSignals) {

                for(Gate fooGate: pSignal.getDestiny()) {
                    if(fooGate == null) {
                        this.pOutputs.add(pSignal);
                        break;
                    }
                }
            }
            this.forOutputs = true;
            return this.pOutputs;
        }
    }

    public ArrayList<ProbGate> getProbGates() {
        return probGates;
    }
    
    public ProbGate getProbGateByName(String name) {
        
        //ProbGate pGate = null;
        
        for(ProbGate fooGate : this.probGates) {
            if(fooGate.getId().equals(name)) {
                //pGate = fooGate;
                return fooGate;
            }
        }
        
        return null;
    }

    public void setProbGates(ArrayList<ProbGate> probGates) {
        this.probGates = probGates;
    }

    public ArrayList<ProbGateLevel> getProbGateLevels() {
        return probGateLevels;
    }

    public void setProbGateLevels(ArrayList<ProbGateLevel> probGateLevels) {
        this.probGateLevels = probGateLevels;
    }

    public void makeProbGateLevels() {
        int lastLevel = 0;
        int inCounter = 0;
        int outCounter = 0;

        ArrayList<Object> foo = new ArrayList<>();

        for (int i = 0; i < this.getProbOutputs().size(); i++) {
            //System.out.println(this.getProbOutputs().get(i).getPOrigin().getDepth());
            foo.add(this.getProbOutputs().get(i).getPOrigin());
        }

        for (int i = 0; i < foo.size(); i++) {

            if (foo.get(i) instanceof ProbGate) {

                ProbGate fooGate = (ProbGate)foo.get(i);
                /*
                 ** Verify the last depth of circuit
                 */
                if (lastLevel < fooGate.getDepth()) {
                    lastLevel = fooGate.getDepth();
                }
            }
        }

        ProbGateLevel gateLevel = new ProbGateLevel(lastLevel);

        for (int i = 0; i < foo.size(); i++) {
            if (foo.get(i) instanceof ProbGate) {

                ProbGate fooGate = (ProbGate)foo.get(i);

                if (fooGate.getDepth() == gateLevel.getLevel()) {
                    if(!gateLevel.containsGate(foo.get(i))) {
                        gateLevel.addGate(fooGate);
                        inCounter += fooGate.getpInputs().size();
                        outCounter += fooGate.getpOutputs().size();
                    }
                } else {
                    /*
                     ** Take the previous depthGate outputs
                     */
                    for (int j = 0; j < fooGate.getpOutputs().size(); j++) {
                        ProbSignal fooSignal = fooGate.getpOutputs().get(j);
                        gateLevel.addGate(fooSignal);
                        inCounter++;
                        outCounter++;
                    }

                }
            }
        }

        gateLevel.setIn(inCounter);
        gateLevel.setOut(outCounter);
        this.probGateLevels.add(gateLevel);
        makeLeftGateLevels(gateLevel);
    }

    /**
     * 2023-02-18 -- Tinha feito esse método pensando em rodar benchmarks maiores
     * O SPR utiliza o ProbGateLevel
     * Talvez seja interessante fazer o ProbGateLevel e ProbInterLevel apenas para a PTMM
     */
    public void makeProbGateLevels_TESTE() {
        int greaterDepth = 0;
        //long timeNow = timenow();

        /**
         * Verificar a maior profundidade lógica
         */
        for (int i = 0; i < this.getProbOutputs().size(); i++) {
            ProbGate pGate = this.getProbOutputs().get(i).getPOrigin();
            if(pGate != null) {
                int flag = pGate.getDepth();
                if(flag > greaterDepth) {
                    greaterDepth = flag;
                }
            }
        }

        ArrayList<ProbGateLevel> pGatesLevels = new ArrayList<>(greaterDepth);

        this.probGateLevels = new ArrayList<>(greaterDepth);
        for(int i = 0; i < greaterDepth; i++) {
            this.probGateLevels.add(new ProbGateLevel(i+1));
        }

        for (int i = 0; i < this.getProbGates().size(); i++) {
            ProbGate pGate = this.getProbGates().get(i);
            int depth = pGate.getDepth();
            this.getProbGateLevels().get(depth-1).addGate(pGate);
        }

        //timestamp(timeNow, "Make ProbGateLevels Done!!");

    }
    
    public void makeLeftGateLevels(ProbGateLevel lastGateLevel) {
        
        int currentLevelNumber = lastGateLevel.getLevel() - 1;
        
        int inCounter = 0;
        int outCounter = 0;

        
        if(currentLevelNumber != 0) {        
            ProbGateLevel currentLevel = new ProbGateLevel(currentLevelNumber);

            for (int j = 0; j < lastGateLevel.getGates().size(); j++) {
                Object gate = lastGateLevel.getGates().get(j);

                if (gate instanceof ProbGate) {
                    ProbGate fooGate = (ProbGate)gate;
                    for (int i = 0; i < fooGate.getpInputs().size(); i++) {
                        ProbSignal fooSignal = fooGate.getpInputs().get(i);                    
                        ProbGate originGate = fooSignal.getPOrigin();
                        if (originGate != null) {
                            if (originGate.getDepth() != currentLevelNumber) {                            
                                currentLevel.addGate(fooSignal);
                                inCounter++;
                                outCounter++;
                            } else {
                                if(!currentLevel.containsGate((Object)originGate)) {
                                    currentLevel.addGate((Object)originGate);
                                    inCounter += originGate.getpInputs().size();
                                    outCounter += originGate.getpOutputs().size();
                                }
                            }
                        } else {
                            currentLevel.addGate((Object)fooSignal);
                            inCounter++;
                            outCounter++;
                        } 
                    }
                } else if(gate instanceof ProbSignal) {
                    ProbSignal fooSignal = (ProbSignal)gate;
                    ProbGate originGate = fooSignal.getPOrigin();

                    if(originGate != null) {
                        if (originGate.getDepth() != currentLevelNumber) {
                                currentLevel.addGate(fooSignal);
                                inCounter++;
                                outCounter++;
                        } else {
                            if(!currentLevel.containsGate((Object)originGate)) {
                                currentLevel.addGate(originGate);
                                inCounter += originGate.getpInputs().size();
                                outCounter += originGate.getpOutputs().size();
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
            
            this.probGateLevels.add(0, currentLevel);

            if(currentLevel.getLevel() != 1) {
                makeLeftGateLevels(currentLevel);

            }
        }
    }
    
    public void addProbGateLevel(ProbGateLevel pGateLevel) {
        this.probGateLevels.add(pGateLevel);
    }

    public ArrayList<ProbInterLevel> getProbInterLevels() {
        return probInterLevels;
    }

    public void setProbInterLevels(ArrayList<ProbInterLevel> probInterLevels) {
        this.probInterLevels = probInterLevels;
    }
    
    
    
    public void makeProbGateLevelsOLD() {
        
        Map<String, ProbSignal> fooSignals = new LinkedHashMap<>();
        
        Map<String, ProbGate> fooGates = new LinkedHashMap<>();
        
        for (ProbSignal pSignal : this.getProbSignals()) {
            fooSignals.put(pSignal.getId(), pSignal);
        }
        
        for (ProbGate pGate : this.getProbGates()) {
            fooGates.put(pGate.getId(), pGate);
        }

        for (ProbGateLevel pGateLevel : this.probGateLevels) {
            
            ArrayList<Object> newGates = new ArrayList<>();
            
            for (Object gate : pGateLevel.getGates()) {
                
                if(gate instanceof DepthGate) {
                                        
                    ProbGate pGate = fooGates.get(((DepthGate)gate).getGate().getId());
                    
                    pGate.setDepth(pGateLevel.getLevel());
                    
                    newGates.add(pGate);
                } else {
                    
                    ProbSignal pSignal = fooSignals.get(((Signal)gate).getId());
                    newGates.add(pSignal);
                }
            }
            
            pGateLevel.setGates(newGates);
        }
    }
    
    public void setPTMReliabilityMatrix() {
        
        int counter = 0;
        
        for(ProbGate pGate : this.probGates) {
            if(pGate.getGateReliability() != null) {
                pGate.setReliabilityMatrix(pGate.getGateReliability());
            } else {
                pGate.setReliabilityMatrix(pGate.getType().getPTM());
            }            
            pGate.setReliabilityMatrix2(pGate.getType().getPtm2());
        }
    }
    
    /*
    ** Desenvolvi para gerar os resultados para o Schivittz 03/11/2019
    */
    public void setPTMsReliabilityMatrix() {
        
        for(ProbGate pGate : this.probGates) {
            if(pGate.getReliabilityMatrix() == null) {
                pGate.setReliabilityMatrix(pGate.getType().getPTM());
            }
        }
    }
    
    public void syncCellPTMs() {
        
        for(ProbGate pGate : this.probGates) {                       
            pGate.setReliabilityMatrix(pGate.getType().getPTM());            
        }
    }
    
    public void makeProbInterLevels() {
        
        for (int i = 0; i < this.probGateLevels.size(); i++) {
            if(i == 0) {
                ProbInterLevel pInterLevel = new ProbInterLevel(1);

                for (int j = 0; j < getProbInputs().size(); j++) {
                    pInterLevel.addIn(getProbInputs().get(j));
                }
                
                ArrayList<Object> pGates = this.probGateLevels.get(i).getGates();
                for (int j = 0; j < pGates.size(); j++) {
                    if(pGates.get(j) instanceof ProbGate) {
                        ProbGate pGate = (ProbGate)pGates.get(j);
                        ArrayList<ProbSignal> pSignals = pGate.getpInputs();
                        for (int k = 0; k < pSignals.size(); k++) {
                            pInterLevel.addOut(pSignals.get(k));
                        }
                    } else {
                        pInterLevel.addOut((ProbSignal)pGates.get(j));
                    }  
                }
                
                this.probInterLevels.add(pInterLevel);
            } else {
                
                ProbInterLevel pInterLevel = new ProbInterLevel(i+1);
                ArrayList<Object> originGates = this.probGateLevels.get(i-1).getGates();
                ArrayList<Object> destGates = this.probGateLevels.get(i).getGates();
                
                for (int j = 0; j < originGates.size(); j++) {
                    if(originGates.get(j) instanceof ProbGate) {
                        ProbGate pGate = (ProbGate)originGates.get(j);
                        ArrayList<ProbSignal> pSignals = pGate.getpOutputs();
                        for (int k = 0; k < pSignals.size(); k++) {
                            pInterLevel.addIn(pSignals.get(k));
                        }
                    } else {
                        pInterLevel.addIn((ProbSignal)originGates.get(j));
                    }  
                }
                
                for (int j = 0; j < destGates.size(); j++) {
                    if(destGates.get(j) instanceof ProbGate) {
                        ProbGate pGate = (ProbGate)destGates.get(j);
                        ArrayList<ProbSignal> pSignals = pGate.getpInputs();
                        for (int k = 0; k < pSignals.size(); k++) {
                            pInterLevel.addOut(pSignals.get(k));
                        }
                    } else {
                        pInterLevel.addOut((ProbSignal)destGates.get(j));
                    }  
                }
                this.probInterLevels.add(pInterLevel);
            }
        }
    }

    public ArrayList<ProbSignal> getFanouts_OLD() {

        ArrayList<ProbSignal> fanouts = new ArrayList<>();
        LinkedHashSet<ProbSignal> linked = new LinkedHashSet();

        for (int i = 0; i < this.getProbInterLevels().size(); i++) {

            ProbInterLevel fooInter = this.getProbInterLevels().get(i);

            ArrayList<ProbSignal> ins = fooInter.getInSignals();
            ArrayList<ProbSignal> outs = new ArrayList<>(fooInter.getOutSignals());


            if(!ins.equals(outs) || ins.size() != outs.size()) {
                for (int k = 0; k < ins.size(); k++) {
                    for (int l = 0; l < outs.size(); l++) {
                        if(ins.get(k).equals(outs.get(l))) {
                            outs.remove(l);
                            break;
                        }
                    }
                }

                linked.addAll(outs);
            }
        }

        fanouts.addAll(linked);
        return fanouts;
    }
    
    public ArrayList<ProbSignal> getFanouts() {

        ArrayList<ProbSignal> fanouts = new ArrayList<>();
        for(ProbSignal pSignal : this.getProbSignals()) {
            if(pSignal.getPDestiny().size() > 1) {
                fanouts.add(pSignal);
            }
        }
        return fanouts;
    }
    
    
    public void setProbSignalStates(boolean debug) {
        ArrayList<ProbSignal> fanouts = getFanouts();
        
        int[] normalFanout = {0,1,2,3};
        int[] sourceFanout = {0,3};
        
        if(debug) {
            for (int i = 0; i < fanouts.size(); i++) {
                ProbSignal next = fanouts.get(i);
                next.setStates(normalFanout);
                next.setCurrentState(4);
            }
            
        } else {
            for (int i = 0; i < fanouts.size(); i++) {
                ProbSignal next = fanouts.get(i);
                if(next.getOrigin() == null) {
                    next.setStates(sourceFanout);
                } else {
                    next.setStates(normalFanout);
                }
                next.setCurrentState(4);
            } 
        }
    }
    
    public void clearProbSignalsMatrix() {
        for (int i = 0; i < this.probSignals.size(); i++) {
            this.probSignals.get(i).setProbMatrixFloat(null);
        }
    }
    
    public void clearProbGatesReliabilitiesMatrix() {
        for (int i = 0; i < this.probGates.size(); i++) {
            this.probGates.get(i).clearGateReliabilityMatrix();
        }
    }
    
    public void propagate(ArrayList<ArrayList<Boolean>> inCombination) {
        
        for (int i = 0; i < this.getProbInputs().size(); i++) {
            this.getProbInputs().get(i).setSignalValues(inCombination.get(i));
        }
        
        //ProbGateLevel firstGateLevel = this.getProbGateLevels().get(0);
        
        //System.out.println(inCombination.size());
        
        for (int i = 0; i < inCombination.get(0).size(); i++) {
            String comb = "";
            for (int j = 0; j < inCombination.size(); j++) {
                comb = comb + inCombination.get(j).get(i);
            }
            
            System.out.println("Comb " + i + " ==> " + comb);
        }
        
        
        /**
         * Iterar sobre os GateLevels
         */
        //for (int i = 0; i < 1; i++) {
        for (int i = 0; i < this.getProbGateLevels().size(); i++) {
            ProbGateLevel pGateLevel = this.getProbGateLevels().get(i);
            
            /**
             * Iterar sobre os Gates de cada Gatelevel
             */
            for (int j = 0; j < pGateLevel.getProbGates().size(); j++) {
                ProbGate pGate = pGateLevel.getProbGates().get(j);
                ArrayList<Boolean> outComb = new ArrayList<>();
                ArrayList<ArrayList<Boolean>> pGateComb = new ArrayList<>();
                
                /**
                 * Iterar sobre os sinais de cada Gate
                 * Colocar os vetores em uma nova lista
                 */
                for (int k = 0; k < pGate.getpInputs().size(); k++) {
                    ProbSignal pSignal = pGate.getpInputs().get(k);
                    pGateComb.add(pSignal.getSignalValues());
                }
                
                //System.out.println(pGate + " sobre os sinais " + pGate.getpInputs() + " ==> " + pGateComb);
                
                //System.out.println("pComb.get(0) size = " + pGateComb.get(0).size());
                //System.out.println("pComb size = " + pGateComb.size());
                
                for (int l = 0; l < pGateComb.get(0).size(); l++) {
                    ArrayList<Boolean> fooComb = new ArrayList<>();
                    for (int p = 0; p < pGateComb.size(); p++) {
                        fooComb.add(pGateComb.get(p).get(l));                        
                    }
                    
                    outComb.add(pGate.getType().getCombination(fooComb));
                    //System.out.println("Gate " + pGate + " -- " + pGate.getType() + " ==> Comb " + l + " ==> " + fooComb + " = " + pGate.getType().getCombination(fooComb));                    
                }
                
                pGate.getpOutputs().get(0).setSignalValues(outComb);
                
            }
        }
        
        for (int i = 0; i < this.getProbInputs().get(0).getSignalValues().size(); i++) {
            String input = "";
            String output = "";
            
            for (int j = 0; j < this.getProbInputs().size(); j++) {
                input = input + this.getProbInputs().get(j).getSignalValues().get(i);
            }
            
            for (int j = 0; j < this.getProbOutputs().size(); j++) {
                output = output + this.getProbOutputs().get(j).getSignalValues().get(i);
            }
            
//            System.out.println(input + " ---> " + output);
        }
        
//        for (int i = 0; i < firstGateLevel.getProbGates().size(); i++) {            
//            Object gate = firstGateLevel.getGates().get(i);
//            
//            if(gate instanceof ProbSignal) {
//                ProbSignal pSignal = (ProbSignal)gate;
//                System.out.println(pSignal);
//            } else {
//                ProbGate pGate = (ProbGate) gate;
//                for (int j = 0; j < pGate.getpInputs().size(); j++) {
//                    System.out.println(pGate.getpInputs().get(j));
//                }
//            }
//            
////            for (int j0 = 0; j0 < pGate.getpInputs(); j0++) {
////                
////            }
//                   
//        }
                
        
        
                        
        Map<boolean[], Boolean> helpTree = new LinkedHashMap<>();
        
        helpTree.put(new boolean[]{false, false}, false);
    }
    
    public void setGatesDepth() {
                
        for (int i = 0; i < this.getOutputs().size(); i++) {
            ProbGate pGate = this.getProbOutputs().get(i).getPOrigin();
            if(pGate != null) {
                gateDfs(this.getProbOutputs().get(i).getPOrigin());
            }
        }
                
    }
    
    public ProbGate gateDfs(ProbGate gate) {
        int fooDepth = 0;            
        ArrayList<ProbSignal> inputs = gate.getpInputs();
        
//        if(gate.currentIn < inputs.size()) {
//            
//        }

        if (!gate.visited) {            
            gate.visited = true;

            for (int i = 0; i < inputs.size(); i++) {
                ProbGate fooGate = null;
                
                if(inputs.get(i).getOrigin() != null) {
                    fooGate = gateDfs(inputs.get(i).getPOrigin());
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

    public ProbGate gateDfsProbGateLevel(ProbGate gate, int previousDepth) {

        ArrayList<ProbSignal> inputs = gate.getpInputs();
        int currentDepth = gate.getDepth();
        int depthDiff = previousDepth-currentDepth;

        if (!gate.visited2) {
            gate.visited2 = true;

            this.probGateLevels.get(currentDepth-1).addGate(gate);

            for (int i = 0; i < inputs.size(); i++) {
                if(inputs.get(i).getOrigin() != null) {
                    gateDfsProbGateLevel(inputs.get(i).getPOrigin(), gate.getDepth());
                } else {
                    if(currentDepth > 1) {
                        for (int j = 0; j < currentDepth-1; j++) {
                            this.probGateLevels.get(j).addGate(inputs.get(i));
                        }
                    }
                }
            }
        }
        //ADD intermediate signals as gates
        if(depthDiff > 1) {
            for(int i = 0; i < depthDiff-1; i++) {
                this.probGateLevels.get(currentDepth+i).addGate(gate.getpOutputs().get(0));
            }
        }
        return gate;
    }
    
    public void setCustomMatrix(CustomMatrixLibrary cLibrary) {
        
        this.initOrgProbGatesList();
        this.probGatesOrganized.forEach((k,pGates)-> {
            CustomMatrix cMatrix = cLibrary.getCMatrix(k);
            
            if(cMatrix != null) {
                for (ProbGate pGate : pGates) {
                    pGate.setReliabilityMatrix(cMatrix.getcMatrix());
                }
            } else {
                System.out.println("Cell " + k + " not found at CustomMatrix Library!!!");
            }
        });
    }
    
    public void setCustomMatrix(Cell cellType) {
        
    }
    
    public void setCustomMatrix(ProbGate pGate) {
        
    }
    
    
    public String toString() {
        return this.getName();
    }
    
    public void initOrgProbGatesList() {
        
        Map<String, ArrayList<ProbGate>> pGateListOrg = new HashMap<>();
        
        for (ProbGate probGate : this.probGates) {
            if(pGateListOrg.get(probGate.getType().getName()) == null) {
                pGateListOrg.put(probGate.getType().getName(), new ArrayList<ProbGate>());
                pGateListOrg.get(probGate.getType().getName()).add(probGate);
            } else {
                pGateListOrg.get(probGate.getType().getName()).add(probGate);
                //pGateListOrg.put(probGate.getType().getName(), gateTypes.get(gates.get(i).getType().getName()).intValue()+1);
            }
        }
        
        this.probGatesOrganized = pGateListOrg;
        this.probGatesOrgFlag = true;

    }

    public Map<String, ArrayList<ProbGate>> getProbGatesOrganized() {
        return probGatesOrganized;
    }
    
    public int getCellQuantity(String cellName) {
        
        int cellQuantity;
        
        if(this.probGatesOrgFlag) {
            ArrayList<ProbGate> list = probGatesOrganized.get(cellName);
            if(list == null) {
                cellQuantity = 0;
            } else {
                cellQuantity = list.size();
            }
        } else {
            initOrgProbGatesList();
            cellQuantity = this.getCellQuantity(cellName);
        }
                    
        return cellQuantity;
    }
    
    
}
