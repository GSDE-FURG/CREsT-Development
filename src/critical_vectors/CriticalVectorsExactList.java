package critical_vectors;

import datastructures.InputVector;

import java.util.ArrayList;

public class CriticalVectorsExactList {
    long secondsTimeStamp;
    ArrayList<InputVector> criticalVectors;


    public CriticalVectorsExactList(ArrayList<InputVector> list, long seconds) {
        this.criticalVectors = list;
        this.secondsTimeStamp = seconds;
    }

    public ArrayList<InputVector> getCriticalVectors() {
        return criticalVectors;
    }

    public long getSecondsTimeStamp() {
        return secondsTimeStamp;
    }
}
