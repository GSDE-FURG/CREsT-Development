/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator;

import datastructures.Signal;
import java.util.ArrayList;
import java.util.List;
import sun.applet.Main;

/**
 *
 * @author clayt
 */
public class Teste {
    public static void main(String[] args) {
        System.out.println("Teste");
        
        ArrayList <Signal> circuitSignals = new ArrayList<Signal>();
        
        Signal x = new Signal("Teste");
       
        circuitSignals.add(new Signal("Oi"));
        circuitSignals.add(new Signal("ola"));
        circuitSignals.add(x);
        circuitSignals.add(new Signal("Saida"));
        circuitSignals.add(new Signal("Venus"));
        
        ArrayList <Signal> faultSignals = new ArrayList<>();
        faultSignals.add(x);
        
        
        System.out.println("Array: "+ circuitSignals);
        
        
         for (int index = 0; index < circuitSignals.size(); index++) {
                        
                        if(faultSignals.contains(circuitSignals.get(index))){ //bit-flip 
                            System.out.println("Founded.... apply biftlip in pos: "+ index);
                        }
         }
        
        
        
    }
}
