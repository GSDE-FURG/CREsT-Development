package logicSimulator;

import datastructures.Signal;

import java.util.ArrayList;

public class itemnize {

    private final String id;
    private int originalValue;
    private int newValue;
    private final Signal sig;
    private ArrayList<Integer> inputVector =  new ArrayList<>();
    private boolean booleanFlag;


    public itemnize(Signal s, String id, ArrayList<Integer> input) {
        this.id = id;
        this.originalValue = -123; //= ;originalValue;
        this.sig = s;
        this.sig.setOriginalLogicValue(this.originalValue);
        this.sig.setLogicValue(123);
        this.inputVector = input;
        this.booleanFlag = false;
    }
    public void setOriginalValue(int value){
        this.originalValue = value;
    }

    public void setNewValue(int value){
        this.newValue = value;
        this.sig.setLogicValue(this.newValue);

        if(this.newValue == 0){
            this.sig.setLogicValueBoolean(Boolean.FALSE);
        }
        if(this.newValue == 1){
            this.sig.setLogicValueBoolean(Boolean.TRUE);
        }

    }

    public void setBooleanFlag(){
        this.booleanFlag = true;
    }

    public boolean getBooleaFlag(){
        return this.booleanFlag;
    }

    public int getNewValue(){
        return this.newValue;
    }
    public int getOriginalValue(){
        return this.originalValue;
    }
    public String getIdentidade(){
        return this.id;
    }
    private Signal getSignalItemnize(){
        return this.sig;
    }
    private void getInfoItemnize(){
        System.out.println(this.inputVector + " - ID: " + this.id + " Ori: " + this.originalValue + "  New: " + this.newValue + "  FaultSig: " + this.sig.getId());
    }
    public void CompInfoItemnize(String comp){
        System.out.println(this.inputVector + " >> " + comp + " || Itemnize ID: " + this.id + " Origem: " + this.originalValue + "  New: " + this.newValue + "  FaultSig: " + this.sig.getId());
    }
    public String getBitflip(){
        if(this.originalValue == 0){
            //return "(" + this.originalValue + ")to(1)";
            return "(" + this.originalValue + "/1)";
        }else{
            //return "(" + this.originalValue + ")to(0)";
            return "(" + this.originalValue + "/0)";
        }

    }


}
