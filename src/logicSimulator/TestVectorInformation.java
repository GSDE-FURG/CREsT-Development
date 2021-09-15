/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicSimulator;

import datastructures.Signal;
import java.util.ArrayList;

/**
 *
 * @author clayton
 */
public class TestVectorInformation {
    
    private ArrayList <Integer> inputVector;
    private String outputFreeFault;
    private String faultOutput;
    private Signal faultSignals;
    private ArrayList <Signal> allfaultSignals;
    private int originalSignalValue;
    private int faultSignalValue;
    private int index;
    private long threadID;

    private boolean MTF;
    private ArrayList<Signal> MTF_FaultSignal_List;
    private ArrayList<Signal> MTF_FaultSignal_List_Base;
    
    public TestVectorInformation(ArrayList <Integer> inputVector, Signal faultSignal, int input_pos) {
            this.faultSignals = faultSignal;
            this.inputVector = inputVector;
            //this.faultSignalValue = sigValue;
            this.index = input_pos;
            this.threadID = 0;
            this.allfaultSignals = new ArrayList<>();

            /* new */


            this.MTF = false;
            this.MTF_FaultSignal_List = new ArrayList<>();
            this.MTF_FaultSignal_List.add(faultSignal);
            this.MTF_FaultSignal_List_Base = new ArrayList<>(this.MTF_FaultSignal_List);
            
    }
    
     public String getBitFlip(){
            
            String bitflip;
            String r = "";
           
            if(this.originalSignalValue == 0){
                bitflip = "1";
            }
            else{
                bitflip = "0";
            }
            
            r = "("+ this.originalSignalValue + ")to("+ bitflip + ")";
            
            return r;
            
            
        }

     public ArrayList <Signal> get_MTF_FaultSignal_List (){
        return this.MTF_FaultSignal_List;
     }

    public ArrayList <Signal> get_MTF_FaultSignal_List_Base (){
        return this.MTF_FaultSignal_List_Base;
    }


    public boolean get_MTF_flag(){
        return this.MTF;
     }



     public void setMultipleTransientFaultInjection(Signal faultSignal){

        if(this.MTF == false)
        {
                this.MTF = true;
                //this.MTF_FaultSignal_List.add(this.faultSignals);
                this.MTF_FaultSignal_List.add(faultSignal);

        }
        else
        {
                this.MTF_FaultSignal_List.add(faultSignal);
        }

     }

     public void setThreadID(long id){
         this.threadID = id;
     }
    
     public void setFaultSignalValue(int FaultSigValue){
         this.faultSignalValue = FaultSigValue;
     }
     
     public void setSignalOriginalValue(int SigValue){
         this.originalSignalValue = SigValue;
         
         if(this.originalSignalValue == 0){
             this.faultSignalValue = 1;
         }
         else{
             this.faultSignalValue = 0;
         }
             
         
     }
     
     public int getSignalOriginalValue(){
        return this.originalSignalValue;
     }
      
     public int getFaultSignalValue(){
        return this.faultSignalValue;
     }
    
     public void setFaultFreeOutput(String output){
        this.outputFreeFault = output;
    }
     
     public void setFaultOutput(String output){
        this.faultOutput = output;
    }
     
     public String getFaultOutput(){
        return this.faultOutput;
    }
     
     public String getOrignalOutput(){
        return this.outputFreeFault;
    }
    
     public Object getItem(){
        System.out.println("This: "+ this.faultSignals);
        return this;
    }
     
     public Signal getFaultSignal(){
        return this.faultSignals;
    }
    
     public ArrayList <Integer> getinputVector(){
        return this.inputVector;
    }
     
     public int getSimulationIndex(){
        return this.index;
    }
    
}
