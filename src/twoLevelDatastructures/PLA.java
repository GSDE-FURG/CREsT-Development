package twoLevelDatastructures;

import java.util.ArrayList;

public class PLA {
    private String name;
    private String type;
    private int qtInputs;
    private int qtOutputs;
    private String inputLabels;
    private String outputLabels;
    private ArrayList<Term> terms;

    public PLA (String name, String type, int qtInputs, int qtOutputs, ArrayList<Term> terms) {
        this.name = name;
        this.type = type;
        this.qtInputs = qtInputs;
        this.qtOutputs = qtOutputs;
        this.terms = terms;
    }

    public PLA (String name) {
        this.name = name;
        this.terms = new ArrayList<Term>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQtInputs() {
        return qtInputs;
    }

    public void setQtInputs(int qtInputs) {
        this.qtInputs = qtInputs;
    }

    public int getQtOutputs() {
        return qtOutputs;
    }

    public void setQtOutputs(int qtOutputs) {
        this.qtOutputs = qtOutputs;
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public void setTerms(ArrayList<Term> terms) {
        this.terms = terms;
    }

    public void addTerm(Term term) {
        this.terms.add(term);
    }

    public String getInputLabels() {
        return inputLabels;
    }

    public void setInputLabels(String inputLabels) {
        this.inputLabels = inputLabels;
    }

    public String getOutputLabels() {
        return outputLabels;
    }

    public void setOutputLabels(String outputLabels) {
        this.outputLabels = outputLabels;
    }

    public int getTotalTerms() {
        return terms.size();
    }

    public boolean checkPLATerms(String inputTerm) {
        char[] inputCandidate = inputTerm.toCharArray();

        for (Term term : this.terms) {
            if (term.checkTermContains(inputTerm)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkAllPLATerms() {
        boolean ret = false;
        for(Term term : this.getTerms()) {
            int nextIndex = this.getTerms().indexOf(term) + 1;
            for(int i = nextIndex; i < this.getTerms().size(); i++ ) {
                Term fooTerm = this.getTerms().get(i);
                if(fooTerm.checkTermContains(term.getInputs())) {
                    System.out.println(fooTerm + " --> " + term);
                    ret = true;
                }
            }
        }
        return ret;
    }

    public ArrayList<Term> getContainedTerm(String inputTerm) {
        char[] inputCandidate = inputTerm.toCharArray();
        ArrayList<Term> equalsTerms = new ArrayList<>();

        for (Term term : this.terms) {
            if (term.checkTermContains(inputTerm)) {
                equalsTerms.add(term);
            }
        }
        return equalsTerms;
    }

    public void addDontCareTerm(String newTermInput) {
        this.terms.add(0, new Term(newTermInput, "-".repeat(this.qtOutputs)));
    }

    public String toString() {
        return this.name;
    }
}
