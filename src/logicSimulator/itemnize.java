package logicSimulator;

import datastructures.Signal;

import java.util.ArrayList;

public class itemnize {

    private final String id;
    private final int originalValue;
    private int newValue;
    private final Signal sig;
    private ArrayList<Integer> inputVector =  new ArrayList<>();


    public itemnize(Signal s, String id, int originalValue, ArrayList<Integer> input) {
        this.id = id;
        this.originalValue = originalValue;
        this.sig = s;
        this.sig.setOriginalLogicValue(this.originalValue);
        this.sig.setLogicValue(123);
        this.inputVector = input;
    }

    private void setNewValue(int value){
        this.newValue = value;
        this.sig.setLogicValue(this.newValue);

        if(this.newValue == 0){
            this.sig.setLogicValueBoolean(Boolean.FALSE);
        }
        if(this.newValue == 1){
            this.sig.setLogicValueBoolean(Boolean.TRUE);
        }

    }
    private int getNewValue(){
        return this.newValue;
    }
    private int getOriginalValue(){
        return this.originalValue;
    }
    private String getIdentidade(){
        return this.id;
    }
    private Signal getSignalItemnize(){
        return this.sig;
    }
    private void getInfoItemnize(){
        System.out.println(this.inputVector + " - ID: " + this.id + " Ori: " + this.originalValue + "  New: " + this.newValue + "  FaultSig: " + this.sig.getId());
    }
    public void CompInfoItemnize(String comp){
        System.out.println(this.inputVector + " >> " + comp + " || Itemnize ID: " + this.id + " Ori: " + this.originalValue + "  New: " + this.newValue + "  FaultSig: " + this.sig.getId());
    }


}
