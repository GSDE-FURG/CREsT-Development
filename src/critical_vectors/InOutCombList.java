package critical_vectors;

import java.util.HashMap;

public class InOutCombList {
    private HashMap<String, String> inOutCombination;

    public InOutCombList(HashMap<String, String> combinations) {
        inOutCombination = combinations;
    }

    public HashMap<String, String> getInOutCombination() {
        return inOutCombination;
    }

    public void setInOutCombination(HashMap<String, String> inOutCombination) {
        this.inOutCombination = inOutCombination;
    }
}
