package logicSimulator;

import datastructures.Signal;

public class SignalExtendedProperties extends Signal
{
    private Signal signal;
    private boolean passed;

    public SignalExtendedProperties(Signal signal){
        super();
        this.signal = signal;
        this.passed = false;

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
