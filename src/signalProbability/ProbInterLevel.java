/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signalProbability;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import static ops.CommonOps.getCombVectors;
import ops.PTMOps;

/**
 *
 * @author matheus
 */
public class ProbInterLevel {
    
    private int level;
    private ArrayList<ProbSignal> inSignals;
    private ArrayList<ProbSignal> outSignals;
    private int[] signalCombinations;
    
    /**
     * Parameter that limits the total of signal combinations;
     */
    int LIMIT = 23;
    
    public ProbInterLevel (int level) {
        this.level = level;
        inSignals = new ArrayList<>();
        outSignals = new ArrayList<>();
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<ProbSignal> getInSignals() {
        return inSignals;
    }

    public ArrayList<ProbSignal> getOutSignals() {
        return outSignals;
    }
    
    public void addIn(ProbSignal signal) {
        inSignals.add(signal);
    }
    
    public void addOut(ProbSignal signal) {
        outSignals.add(signal);
    }

    public int[] getSignalCombinations() {
        return signalCombinations;
    }

    public void setSignalCombinations(int[] signalCombinations) {
        this.signalCombinations = signalCombinations;
    }
    
    public void setSignalCombinations() {        
        
        if(this.inSignals.size() < LIMIT) {
            if (!this.inSignals.equals(outSignals)) {                                
                ArrayList<ArrayList<Boolean>> combinations = getCombVectors(0, PTMOps.PowInt(2, inSignals.size())-1, inSignals.size());
                int[] signalComb = new int[combinations.get(0).size()];
                
                for (int i = 0; i < combinations.get(0).size(); i++) {
                    
                    int counter = outSignals.size() - 1;
                    
                    Map<String, Boolean> dict = new LinkedHashMap<>();
                    int outColumn = 0;
                
                    for (int j = 0; j < inSignals.size(); j++) {
                        dict.put(inSignals.get(j).getId(), combinations.get(j).get(i));
                    }
                    
                    for (int j = 0; j < outSignals.size(); j++) {
                        if(dict.get(outSignals.get(j).getId())) {
                            outColumn = outColumn + PTMOps.PowInt(2, counter);
                        }
                        counter--;
                    }
                    
                    
                    signalComb[i] = outColumn;
                }
                
                    setSignalCombinations(signalComb);                
            }            
        }
    }
    
    public boolean getInterLevelMatrixIndex(int x, int y) {        
        return signalCombinations[x] == y;
    }
     
}
