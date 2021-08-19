/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ops;

import java.util.ArrayList;
import signalProbability.ProbSignal;

/**
 *
 * @author matheus
 */
public class FanoutOps {
    
    public static ArrayList<ProbSignal> getPartialFanoutsInOut(ArrayList<ProbSignal> fanouts, float percentage, boolean isIN) {
        
        ArrayList<ProbSignal> newFanouts = new ArrayList<>();
        float foo = fanouts.size() * (percentage / 100);
        int index = (int)Math.ceil(foo);
        String label;                
        
        if(isIN) {
            label = "in";
            for (int i = 0; i < index; i++) {
                newFanouts.add(fanouts.get(i));
            }
        } else {
            label = "out";
                        
            for (int i = (fanouts.size() - index); i < fanouts.size(); i++) {
                newFanouts.add(fanouts.get(i));
            }
        }
        
//        System.out.println("");
//        System.out.println("Total: " + fanouts.size());
//        System.out.println(percentage + "% " + label + ": " + index);
//        System.out.println("NewFanouts: " + newFanouts);
//        System.out.println("AllFanouts: " + fanouts);
//        System.out.println("");
        
        return newFanouts;
    }
    
    public static ArrayList<ProbSignal> getJustINorNotIN(ArrayList<ProbSignal> fanouts, boolean isIN) {
        
        ArrayList<ProbSignal> inFanouts = new ArrayList<>();
        ArrayList<ProbSignal> notFanouts = new ArrayList<>();
        

        for (ProbSignal pSignal : fanouts) {
            if(pSignal.getOrigin() == null && !pSignal.getDestiny().isEmpty()) {
                inFanouts.add(pSignal);
            } else {
                notFanouts.add(pSignal);
            }
        }
        
//        System.out.println("");
//        System.out.println("Total: " + fanouts.size());
//        System.out.println("IN: " + inFanouts.size());
//        System.out.println("Not IN: " + notFanouts.size());
//        System.out.println("");
        
        if(isIN) {
            return inFanouts;
        } else {
            return notFanouts;
        }
        
        
    }
}
