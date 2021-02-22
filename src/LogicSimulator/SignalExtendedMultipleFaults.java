/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator;

import datastructures.Signal;

/**
 *
 * @author clayt
 */
public class SignalExtendedMultipleFaults {
    
    private Signal Signal_X;

        public SignalExtendedMultipleFaults(Signal sig) {
            this.Signal_X = sig;
        }
        
        public String getBitFlipExtended(){
            
            String bitflip;
            String r = "";
            System.out.println("Bitflip Sig: "+ this.Signal_X + " - O: " + this.Signal_X.getLogicValue());
            if(this.Signal_X.getLogicValue() == 0){
                bitflip = "1";
            }
            else{
                bitflip = "0";
            }
            
            r = "("+ this.Signal_X.getLogicValue() + ")to("+ bitflip + ")";
            
            return r;
            
            
        }
        
    
}
