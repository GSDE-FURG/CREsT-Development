package twoLevelDatastructures;

public class Term {
    private String inputs;
    private String outputs;
    private String termo;

    public Term(String inputs, String outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.termo = String.format("%s %s", inputs, outputs);
    }

    public Term(String inputs, String outputs, String termo) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.termo = termo;
    }

    public String getInputs() {
        return inputs;
    }

    public void setInputs(String inputs) {
        this.inputs = inputs;
    }

    public String getOutputs() {
        return outputs;
    }

    public void setOutputs(String outputs) {
        this.outputs = outputs;
    }

    public String getTermo() {
        return termo;
    }

    public void setTermo(String termo) {
        this.termo = termo;
    }

    public String toString() {
        return termo;
    }

    public boolean checkTermContains(String term) {
        char[] a = this.inputs.toCharArray();
        char[] b = term.toCharArray();

        for(int i = 0; i < a.length; i++) {
            char value = a[i];
            char value2 = b[i];
            if(value == '-') {
                continue;
            } else if(value != value2) {
                return false;
            }
        }
        return true;
    }
}
