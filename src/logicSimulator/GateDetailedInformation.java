package logicSimulator;

import datastructures.Cell;
import levelDatastructures.DepthGate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public class GateDetailedInformation {

    private ArrayList <String> inputs;
    private ArrayList <String> outputs;

    private DepthGate gate;

    private Float gateSensitiveArea;

    private Cell cells;
    GateDetailedInformation(){
        //this.gate = gate;
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
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

    public ArrayList<String> getOutputs() {
        return outputs;
    }

    public void setgateSensitiveArea(Float gateSensitiveArea) {
        this.gateSensitiveArea = gateSensitiveArea;
    }

    public Float getgateSensitiveArea() {
        return gateSensitiveArea;
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

    public DepthGate getGate() {
        return this.gate;
    }

    public ArrayList<String> getInputs() {
        return inputs;
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
    public void calculatGateSusceptibility(ArrayList <Boolean> input){

        final Map<ArrayList<Boolean>, Boolean> combx = this.cells.getComb(); // Calculate the gate output
        final Map<ArrayList<Boolean>, Boolean> comb = this.cells.getComb();
        //final ArrayList <Boolean> input = new ArrayList<>();
        boolean output = this.calculateTheOutputGatesInBoolean(comb, input, this.gate);

        int masked = 0;
        int faill = 0;
        int tested = 0;

        ArrayList <Integer> sensitiveList = new ArrayList<>();

        for (int i = 0; i < input.size() ; i++) {
            int flag = 0;
            ArrayList <Boolean> inputBit = (ArrayList<Boolean>) input.clone();


            ArrayList <Boolean> inputFliped = this.flipInput(inputBit, i);
            boolean outputFault = this.calculateTheOutputGatesInBoolean(comb, inputFliped, this.gate);

            if(output == outputFault){
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
        System.out.println("\n Masked: " + masked + " - Failed: " + faill +  " tested: " +tested + "| input: " + input + " Output: " + output + " Coverage: " + coverage +"%" + " SensitiveList: " + sensitiveList);
       // System.out.println(")))) GateType: " + this.cells.getName() + "  Inputs: " + input + " -> output: " + output + " -- tested: " + tested + " Masked: " + masked);
    }
}
