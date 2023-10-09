package twoLevelDatastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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

    public void removeTermByInput(String input) {
        ArrayList<Term> toRemove = new ArrayList<>();
        for (Term term : this.terms) {
            if(term.getInputs().equals(input)) {
                toRemove.add(term);
            }
        }

        for (Term r : toRemove) {
            this.terms.remove(r);
        }
    }

    public void removeTerm(Term term) {
        this.terms.remove(term);
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

    public boolean checkAllPLATerms(boolean doAdjusts) {

        boolean mintermsNotUnique = true;
        /**
         * fazer o check até todos os mintermos forem únicos
         */
        HashMap<Term, ArrayList<Term>> repeatedTerms = getRepeatedTerms();
        ArrayList<Term> removeList = new ArrayList<>();

        if(repeatedTerms.isEmpty()) {
            System.out.println("All PLA minterms are unique!");
            mintermsNotUnique = false;
        } else {
            if (doAdjusts) {

                for (Term t : repeatedTerms.keySet()) {
                    System.out.println(t + " --> " + repeatedTerms.get(t));
                }
                for (Term firstTerm : repeatedTerms.keySet()) {
                    for (Term cube : repeatedTerms.get(firstTerm)) {
                        /**
                         * Equal terms and Contained terms
                         */
                        if(firstTerm.getOutputs().equals(cube.getOutputs())) {
                            if(firstTerm.getInputs().equals(cube.getInputs())) {
                                if(!removeList.contains(cube)) {
                                    removeList.add(cube);
                                }
                            } else {
                                if(!removeList.contains(firstTerm)) {
                                    removeList.add(firstTerm);
                                }
                            }
                        } else {

                            if(!firstTerm.getInputs().equals(cube.getInputs())) {
                                if(!removeList.contains(cube)) {
                                    this.breakCubeTerm(firstTerm, cube);
                                    removeList.add(cube);
                                }
                            } else {
                                if(firstTerm.getOutputs().contains("-")) {
                                    if(!removeList.contains(cube)) {
                                        removeList.add(cube);
                                    }
                                } else {
                                    if(!removeList.contains(firstTerm)) {
                                        removeList.add(firstTerm);
                                    }
                                }
                            }
                        }
                    }
                }

                /**
                 * Efetivar as exclusões
                 */
                for(Term t : removeList) {
                    this.removeTerm(t);
                    //System.out.println(t);
                }

            } else {
                for (Term t : repeatedTerms.keySet()) {
                    System.out.println(t + " --> " + repeatedTerms.get(t));
                }
            }
        }

        if(doAdjusts && mintermsNotUnique) {
            this.checkAllPLATerms(doAdjusts);
        }

        return mintermsNotUnique;
    }
    public HashMap<Term, ArrayList<Term>> getRepeatedTerms() {
        HashMap<Term, ArrayList<Term>> result = new HashMap<>();
        this.sortTermsByDontCare();

        for(Term term : this.getTerms()) {
            int nextIndex = this.getTerms().indexOf(term) + 1;
            for(int i = nextIndex; i < this.getTerms().size(); i++ ) {
                Term fooTerm = this.getTerms().get(i);
                if(fooTerm.checkTermContains(term.getInputs())) {
                    if(result.get(term) != null) {
                        result.get(term).add(fooTerm);
                    } else {
                        result.put(term, new ArrayList<Term>(Arrays.asList(fooTerm)));
                    }
                }
            }
        }

        return result;
    }

    public void breakCubeTerm(Term approxTerm, Term cube) {
        char[] approx = approxTerm.getInputs().toCharArray();
        char[] inCube = cube.getInputs().toCharArray();

        ArrayList<char[]> result = new ArrayList<>();
        ArrayList<Integer> dontcarePosition = new ArrayList<>();
        int counter = 0;

        for(char c : inCube) {
            if(c == '-') {
                result.add(inCube.clone());
                dontcarePosition.add(counter);
            }
            counter++;
        }

        for (int p : dontcarePosition) {
            char currentValue;
            char oppositeValue;
            if(approx[p] == '0') {
                currentValue = '0';
                oppositeValue = '1';
            } else {
                currentValue = '1';
                oppositeValue = '0';
            }
        }

        /**
         * each dont care position will generate a new cube
         */
        for (int i = 0; i < dontcarePosition.size(); i++) {
            char currentValue;
            char oppositeValue;
            if(approx[dontcarePosition.get(i)] == '0') {
                oppositeValue = '1';
            } else {
                oppositeValue = '0';
            }

            /**
             * each current position will be the oppositive value o approx term
             */
            result.get(i)[dontcarePosition.get(i)] = oppositeValue;

            /**
             * previous dont care values will be same approx values
             */
            for (int w = 0; w < i; w++) {
                result.get(i)[dontcarePosition.get(w)] = approx[dontcarePosition.get(w)];
            }
        }

        for(char[] array : result) {
            this.addTerm(new Term(new String(array), cube.getOutputs()));
        }
        //this.removeTermByInput(cube.getInputs());
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

    public void sortTermsByDontCare() {

        HashMap<Integer, ArrayList<Term>> map = new HashMap<>();
        for(Term t : this.getTerms()) {
            int dontCounter = 0;
            char[] inputTerm = t.getInputs().toCharArray();
            for (char c : inputTerm) {
                if(c == '-') {
                    dontCounter++;
                }
            }
            if(map.get(dontCounter) != null) {
                map.get(dontCounter).add(t);
            } else {
                map.put(dontCounter, new ArrayList<>(Arrays.asList(t)));
            }
        }

        ArrayList<Term> newTerms = new ArrayList<>();
        for (int k : map.keySet()) {
            newTerms.addAll(map.get(k));
        }

        this.setTerms(newTerms);
    }

    public void addDontCareTerm(String newTermInput) {
        //this.terms.add(0, new Term(newTermInput, "~".repeat(this.qtOutputs)));
        this.terms.add(0, new Term(newTermInput, "-".repeat(this.qtOutputs)));
    }

    public String toString() {
        return this.name;
    }
}
