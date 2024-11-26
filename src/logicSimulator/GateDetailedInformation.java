package logicSimulator;

import datastructures.Cell;
import levelDatastructures.DepthGate;

import java.util.ArrayList;
import java.util.Map;

public class GateDetailedInformation {

    private ArrayList <String> inputs;
    private ArrayList <String> inputs_original;
    private ArrayList <String> outputs;

    private ArrayList <String> outputs_original;

    private DepthGate gate;

    private Float gateSensitiveArea;

    private Float gateSensitiveAreaOriginal;

    private Cell cells;
    GateDetailedInformation(){
        //this.gate = gate;
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
        this.outputs_original = new ArrayList<>();
        this.gateSensitiveArea = 0F;
        this.gateSensitiveAreaOriginal = 0F;
    }

    public void setGate(DepthGate gate) {
        this.gate = gate;
    }


    public void setOutputs(Boolean outputs) {

        if(outputs == true){
            this.outputs.add("1");
        }
        if(outputs == false){
            this.outputs.add("0");
        }
    }

    public void setOutputsOriginal( Boolean outputsOriginal) {

        if(outputsOriginal == true){
            this.outputs_original.add("1");
        }
        if(outputsOriginal == false){
            this.outputs_original.add("0");
        }

           // this.outputs = outputsOriginal;

    }

    public ArrayList<String> getOutputs() {
        return this.outputs;
    }

    public ArrayList<String> getOutputsOriginal() {
        return this.outputs_original;
    }

    public void setgateSensitiveArea(Float gSA) {
        this.gateSensitiveArea = gSA;
    }

    public Float getgateSensitiveArea() {
        return this.gateSensitiveArea;
    }


    public void setgateSensitiveAreaOriginal(Float gSA) {
        this.gateSensitiveAreaOriginal = gSA;
    }


    public Float getgateSensitiveAreaOriginal() {
        return this.gateSensitiveAreaOriginal;
    }

    public void setInputs(ArrayList <Boolean> vector_value){
        ArrayList<String> concat = new ArrayList<>();
        for (int i = 0; i < vector_value.size() ; i++){
            if(vector_value.get(i) == Boolean.TRUE){
                concat.add("1");
            }
            if(vector_value.get(i) == Boolean.FALSE){
                concat.add("0");
            }
        }
        this.inputs = concat;
    }

    public void setInputsOriginal(ArrayList <Boolean> vector_value){
        ArrayList<String> concat = new ArrayList<>();
        for (int i = 0; i < vector_value.size() ; i++){
            if(vector_value.get(i) == Boolean.TRUE){
                concat.add("1");
            }
            if(vector_value.get(i) == Boolean.FALSE){
                concat.add("0");
            }
        }
        this.inputs_original = concat;
    }

    public DepthGate getGate() {
        return this.gate;
    }

    public ArrayList<String> getInputs() {
        return this.inputs;
    }

    public ArrayList<String> getInputsOriginal() {
        return this.inputs_original;
    }


    public void print(){
        System.out.println("Gate: " + this.gate.getGate() + "  Input: " + this.inputs);
    }

    private boolean calculateTheOutputGatesInBoolean(Map<ArrayList<Boolean>, Boolean> comb, ArrayList <Boolean> input, DepthGate gate){

        Object output = "stuck";

        for (Map.Entry<ArrayList<Boolean>, Boolean> entry : comb.entrySet()){ // From the input gate values define the output value
            if(entry.getKey().equals(input)){

                //System.out.println("Input Finded: " + entry.getKey() + " output " + entry.getValue());
                //Gate k = null;

                boolean x = entry.getValue();
                output = entry.getValue();
                //r = "";//output.toString();
                if(x){ // x == true (gates output equal to true(logic value 1))
                    //r = "1";
                    return x;
                }
                if(!x){ // x == false (gates output equal to false(logic value 0))
                    //r = "0";
                    return x;
                }

            }
        }


        //System.out.println("               Gate: " + gate.getGate() + "(" +cells.getName() + ") inputSignals: " + gate.getGate().getInputs() + " -> values: "  + signals + " ~ " + input  + " -> Output " + gate.getGate().getOutputs() + " is: " + r + " ["+ output +"] ------ \n");
        if(output.equals("stuck")){
            System.err.println("ERROR stuck !!!!! o: "+output + "  GATE: " + gate.getGate() + "  type: " + gate.getGate().getType());
        }


        return (boolean) output;
    }

    public void setCell(Cell cells){
            this.cells = cells;
    }

    public boolean checkGateCoverSusceptibility(Boolean outputStandart, Boolean fault){

       if(outputStandart == fault){ // Bitflip Masked
           return true;
       }
       else { //NOT Masked
           return false;
       }
    }

    public ArrayList <Boolean> flipInput(ArrayList <Boolean> input, int vecPos){
        ArrayList <Boolean> inputNew;
        inputNew = (ArrayList<Boolean>) input.clone();
        if(!input.get(vecPos)){
            inputNew.set(vecPos, true);
        }else{
            inputNew.set(vecPos, false);
        }
        return inputNew;
    }
    public void calculatGateSusceptibility(ArrayList <Boolean> input){ //Trazer o original também

        final Map<ArrayList<Boolean>, Boolean> combx = this.cells.getComb(); // Calculate the gate output
        final Map<ArrayList<Boolean>, Boolean> comb = this.cells.getComb();
        //final ArrayList <Boolean> input = new ArrayList<>();

        boolean output = this.calculateTheOutputGatesInBoolean(comb, input, this.gate); // Output

        // How get original inputs



        int masked = 0;
        int faill = 0;
        int tested = 0;

        ArrayList <Integer> sensitiveList = new ArrayList<>();

        for (int i = 0; i < input.size() ; i++) {
            int flag = 0;
            ArrayList <Boolean> inputBit = (ArrayList<Boolean>) input.clone();


            ArrayList <Boolean> inputFliped = this.flipInput(inputBit, i);
            boolean outputFault = this.calculateTheOutputGatesInBoolean(comb, inputFliped, this.gate);

            if(output == outputFault){ // The outputed was caculate
                masked++;
                flag = 5555;

            }else{
                faill++;
                sensitiveList.add(i);
            }

            //System.out.println("\n)))) GateType: " + this.cells.getName() + "  Inputs: " + inputBit + " Fliped: " + inputFliped + " -> output: " + output +  " ->New_out: " + outputFault +  "  Equal masked: " + flag + " -- tested: " + tested + " Masked: " + masked);

            tested++;
        }
        float coverage;


        float m = masked;
        float t = tested;

        if(masked != 0){
            coverage = 100 * (m / t);
        }
        else{
            coverage = 0;
        }
        System.out.println("\n Gate " + gate.getGate().getId() + " susceptibility tests: " + tested  +" -> masked: " + masked + " - propagated: " + faill +   " | input: " + input + " Output: " + output + " Coverage: " + coverage +"%" + " SensitiveList: " + sensitiveList);
       // System.out.println(")))) GateType: " + this.cells.getName() + "  Inputs: " + input + " -> output: " + output + " -- tested: " + tested + " Masked: " + masked);
    }

    /**
     * Compare if inputs gates original and propagated are equal
     * @return YES they fault are masked, NO if not masked logically
     * @param input
     * @param inputOriginal
     * @return
     */
    public Boolean calculatGateSusceptibilityLogicalMasking(ArrayList <Boolean> input, ArrayList <Boolean> inputOriginal){ //Trazer o original também

        final Map<ArrayList<Boolean>, Boolean> comb = this.cells.getComb(); // Calculate the gate output
        final Map<ArrayList<Boolean>, Boolean> comb_Original = this.cells.getComb();
        //final ArrayList <Boolean> input = new ArrayList<>();

        boolean output = this.calculateTheOutputGatesInBoolean(comb, input, this.gate); // Output

        boolean output_original = this.calculateTheOutputGatesInBoolean(comb_Original, inputOriginal, this.gate); // Output

        return output == output_original;
    }
}
