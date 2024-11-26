package logicSimulator;

import datastructures.Signal;

public class SignalExtendedProperties extends Signal
{
    private Signal signal;
    private boolean passed;
    private int orignalValueExtended;
    private int faultValueExtended;
    private boolean faultValueExtendedBoolean;

    public SignalExtendedProperties(){
        super();
        this.signal = null;
        this.passed = false;
        this.orignalValueExtended = -1;
        this.faultValueExtended = -1;

    }

    public int getOrignalValueExtended(){
        return this.orignalValueExtended;
    }

    public int getFaultValueExtended(){
        return this.faultValueExtended;
    }

    public void setOrignalValueExtended(int original){
            this.orignalValueExtended = original;
    }

    public void setFaultValueExtended(int faultValue){
        this.faultValueExtended = faultValue;
    }

    public void setSignal(Signal sig){
        this.signal = sig;
    }

    public void setFaultValueBoolean(boolean value){
        this.faultValueExtendedBoolean = value;
    }

    public boolean getFaultValueBoolean(){
        return this.faultValueExtendedBoolean;
    }


    public Signal getSignal() {
        return this.signal;
    }

    public boolean getPassed(){
        return this.passed;
    }

    public void setPassed(){
        this.passed = true;
    }
}
