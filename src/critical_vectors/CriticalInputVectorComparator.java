package critical_vectors;

import datastructures.InputVector;

import java.util.Comparator;

public class CriticalInputVectorComparator implements Comparator<InputVector> {

    @Override
    public int compare(InputVector inputV1, InputVector inputV2) {
        return Double.compare(inputV1.getDoubleReliability(), inputV2.getDoubleReliability());
    }

    @Override
    public Comparator<InputVector> reversed() {
        return Comparator.super.reversed();
    }
}
