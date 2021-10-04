/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicSimulator;

import datastructures.CellLibrary;
import datastructures.Circuit;
import datastructures.Gate;
import datastructures.Signal;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.script.ScriptException;
import javax.swing.border.Border;

import jxl.write.WriteException;
import levelDatastructures.LevelCircuit;
import readers.MappedVerilogReader;
import signalProbability.ProbCircuit;
import writers.WriteCsvTh;

/**
 *
 * @author Clayton Farias
 */



 public class Orchestrator extends main{
    
    private ArrayList <LogicSimulator> itemx_list = new ArrayList<>();
    private int sampleSize;
    private CellLibrary cellLibrary;
    private Circuit circuit;
    private final String circuitNameStr;
    private LevelCircuit levelCircuit;
    private ProbCircuit probCircuit;
    private LevelCircuit lCircuit;
    private int unmasked_faults;
    private double MTBF;
    private long time_seconds;
    private float circuitReliaibility;
    private MappedVerilogReader verilog_circuit;
    private String Performance_Time;

    private ArrayList <Signal> signals_to_inject_faults = new ArrayList<>();
    private final ArrayList <String> inputListValues = new ArrayList<>();
    private final ArrayList <ArrayList<String>> inputListValuesStr = new ArrayList<>();
    private final ArrayList <ArrayList<String>> outputFreeFaultListValueStr = new ArrayList<>();
    private final ArrayList <ArrayList<String>> outputListValueStr = new ArrayList<>();
    private final ArrayList <ArrayList<String>> SignalFault = new ArrayList<>();
    private final ArrayList <ArrayList<String>> SignalFaultBitFlip = new ArrayList<>();

    public Orchestrator(int threads, String reliabilityConst, String relativePath, String genlib, String circuitName) {
            super(threads, reliabilityConst, relativePath, genlib);

             this.cellLibrary = new CellLibrary();
             this.circuitNameStr = circuitName;
    }

    public ArrayList <Signal> signalsToInjectFault(String option){

        ArrayList <Signal> signalList = new ArrayList<>();
        signalList = this.circuit.getSignals();
        ArrayList <Signal> r = new ArrayList<>();

        switch (option){

            case "INPUTS":

                    for (int i = 0; i < signalList.size(); i++) {
                                if(((this.circuit.getInputs().contains(signalList.get(i))))){

                                   r.add(signalList.get(i));
                                }

                        }
                     System.out.println("  --- Input Signals (total): " + r.size() );
                     return r;
                     //break;


            case "INTERMEDIATE_AND_OUTPUTS":

                for (int i = 0; i < signalList.size(); i++) {
                    if(!((this.circuit.getInputs().contains(signalList.get(i))))){
                            r.add(signalList.get(i));
                    }

                 }
                System.out.println("  --- Intermediate and output Signals (total): " + r.size() );
                return r;



            case "INTERMEDIATE":

                for (int i = 0; i < signalList.size(); i++) {
                            if(!((this.circuit.getInputs().contains(signalList.get(i))) || (this.circuit.getOutputs().contains(signalList.get(i))))){

                               r.add(signalList.get(i));
                            }

                    }
                 System.out.println("  --- Intermediate Signals (total): " + r.size() );
                 return r;

            case "INPUTS_OUTPUTS":

                for (int i = 0; i < signalList.size(); i++) {
                            if(((this.circuit.getInputs().contains(signalList.get(i))) || (this.circuit.getOutputs().contains(signalList.get(i))))){

                               r.add(signalList.get(i));
                            }

                    }
                 System.out.println("  --- Intermediate Signals (total): " + r.size() );
                 return r;


            case "ALL_SIGNALS":

              for (int i = 0; i < signalList.size(); i++) {
                    r.add(signalList.get(i));
              }
               System.out.println("--> Considering all signals (input, intermediate, output): "+r.size());
              return r;


         case "":
             System.out.println("Error !!! Please insert the correct Fault Signals String");
             break;
        }
        return null;

    }

    public class gate_counter{
            String type;
            int counter;

        public gate_counter(String type, int counter) {
            this.type = type;
            this.counter = counter;
        }

        public int get_gate_counter(){
            return this.counter;
        }

        public String get_gate_type(){
            return this.type;
        }

        public void update_count(){
            this.counter++;
        }



    }

    public String PrintSpecs(){

         System.out.println("           Circuit Name : " + this.circuit.getName());
         //System.out.println("- Logic Gates : " + this.circuit.getGates());
        //System.out.println("               - Logic Gates (size): " + this.levelCircuit.getGates().size());
         //System.out.println("               - Levels (size): " + this.levelCircuit.getGateLevels().size());
         //System.out.println("- Inputs : " + this.circuit.getInputs());
         //System.out.println("               - Inputs : " + this.circuit.getInputs().size()  + " - " +this.circuit.getInputs());
         //System.out.println("               - Outputs : " + this.circuit.getOutputs().size() + " - " + " - " +this.circuit.getOutputs());
         //System.out.println("               - Signals : " + this.circuit.getSignals().size());
         //String str = this.circuit.getName()+ ";" + this.circuit.getGates().size() + ";"+ this.levelCircuit.getGateLevels().size();

         return "";
     }

     public String PrintSpecsThesis(){
         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
         System.out.println("           Circuit Name : " + this.circuit.getName());
         System.out.println("               - Logic Gates : " + this.circuit.getGates());
         System.out.println("               - Logic Gates (size): " + this.circuit.getGates().size());
         System.out.println("               - Levels (size): " + this.levelCircuit.getGateLevels().size());
         //System.out.println("- Inputs : " + this.circuit.getInputs());
         System.out.println("               - Inputs (" + this.circuit.getInputs().size()  + ") : " +this.circuit.getInputs());
         System.out.println("               - Outputs (" + this.circuit.getOutputs().size() + ") :" +this.circuit.getOutputs());
         System.out.println("               - Signals (" + this.circuit.getSignals().size() + ") : " + this.circuit.getSignals());
         //String str = this.circuit.getName()+ ";" + this.circuit.getGates().size() + ";"+ this.levelCircuit.getGateLevels().size();
         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

         return "";
     }

    public String PrintTransistorsNumber(){

         System.out.println("           Circuit Name : " + this.circuit.getName());
         //System.out.println("- Logic Gates : " + this.circuit.getGates());
         System.out.println("               - Logic Gates (size): " + this.circuit.getGates().size() );
         System.out.println("               - Levels (size): " + this.levelCircuit.getGateLevels().size());

          System.out.println("Gates: ");
          int transitorCount = 0;
          for (int i = 0; i < this.circuit.getGates().size(); i++) {
               //System.out.println("Gates: " + this.circuit.getGates().get(i).getType() +  " ("+  this.circuit.getGates().get(i)+")" + "  - input(size): " + this.circuit.getGates().get(i).getInputs().size());
               transitorCount = transitorCount + (2 * this.circuit.getGates().get(i).getInputs().size());

          }
          System.out.println("Transistor Count: " + transitorCount);

           System.out.println(" -> " + this.circuit.getGates().get(0).getType());
         //System.out.println("- Inputs : " + this.circuit.getInputs());
         //System.out.println("               - Inputs : " + this.circuit.getInputs().size()  + " - " +this.circuit.getInputs());
         //System.out.println("               - Outputs : " + this.circuit.getOutputs().size() + " - " + " - " +this.circuit.getOutputs());
         //System.out.println("               - Signals : " + this.circuit.getSignals().size());
        // String str = this.circuit.getName()+ ";" + this.circuit.getGates().size() + ";"+ this.levelCircuit.getGateLevels().size();

         return Integer.toString(transitorCount);
     }

    public boolean searchGateInList(String x, ArrayList <gate_counter> arraylist){

        for(int i = 0; i < arraylist.size(); i++){
            //temp = arraylist.get(i);
            if(arraylist.get(i).get_gate_type().equals(x)){
                //System.out.println("Achou -> " + x);
                arraylist.get(i).update_count();
                //System.out.println("Atualizar -> " + x +  "   c: " + arraylist.get(i).get_gate_counter());
                return true;
            }

        }


        return false;

    }

    public String PrintGatesCounter(){
         System.out.println("           Circuit Name : " + this.circuit.getName());
         //System.out.println("- Logic Gates : " + this.circuit.getGates());
         System.out.println("               - Logic Gates (size): " + this.circuit.getGates().size() );
         ///System.out.println("               - Levels (size): " + this.levelCircuit.getGateLevels().size());

         ArrayList <gate_counter> temp = new ArrayList<>();

         temp.add(new gate_counter("ZERO", 0));
         temp.add(new gate_counter("ONE", 0));
         temp.add(new gate_counter("BUF", 0));
         temp.add(new gate_counter("INV", 0));

         temp.add(new gate_counter("NOR2", 0));
         temp.add(new gate_counter("NOR3", 0));
         temp.add(new gate_counter("NOR4", 0));
         temp.add(new gate_counter("NAND2", 0));

         temp.add(new gate_counter("NAND3", 0));
         temp.add(new gate_counter("NAND4", 0));
         temp.add(new gate_counter("OAI21", 0));
         temp.add(new gate_counter("OAI211", 0));

         temp.add(new gate_counter("OAI22", 0));
         temp.add(new gate_counter("OAI221", 0));
         temp.add(new gate_counter("OAI222", 0));
         temp.add(new gate_counter("AOI21", 0));

         temp.add(new gate_counter("AOI211", 0));
         temp.add(new gate_counter("AOI22", 0));
         temp.add(new gate_counter("AOI221", 0));
         temp.add(new gate_counter("AOI222", 0));

          temp.add(new gate_counter("XOR2", 0));



         for(Gate i: this.circuit.getGates()) {
             //System.out.println("-" + i.getType());

            /* if(searchGateInList(i.getType().toString(), temp) == false){ // Adicionar a lista
                 gate_counter novo_gate = new gate_counter(i.getType().toString(), 0);
                 temp.add(novo_gate);
             }
             */
             if(searchGateInList(i.getType().toString(), temp) == false)
             {
                 System.out.println("ERROR !!!!");
             }
         }

         System.out.println("- Lista completa: ");
         int c = 0;
         String result = "\n" + this.circuit.getName() + ";" + this.circuit.getGates().size() +  "\n";


         for(gate_counter item: temp){
             //System.out.println(item.get_gate_type() + " " + item.get_gate_counter());
             //result = result + item.get_gate_type() + ";" + item.get_gate_counter() + "\n";

             result = result +item.get_gate_counter() + "\n";
             c = c + item.get_gate_counter();
         }

         if(c == this.circuit.getGates().size()){
             System.out.println("OK NUMERO DE GATES CORRETO: " + c);
         }else{
             System.out.println("!!!! Erro número de gates divergente: c:" + c + "  circ: " + this.circuit.getGates().size());
         }

         return result;

     }



    public void initProbCircuit() {
        if(this.circuit != null) {

            this.lCircuit = this.levelCircuit; //Terminal.getInstance().getLevelCircuit();
            this.probCircuit = new ProbCircuit(this.circuit);
            //System.out.println("InitProbCircuit " + this.probCircuit.getName());

        } else {
            System.out.println("Circuit is null !!!");
        }
    }

    public void initLevelCircuit(){
        if(this.circuit != null) {
            //System.out.println("InitLevelCircuit");
            this.levelCircuit = new LevelCircuit(this.circuit);
        }
        else
        {
            System.out.println("Circuit is null!!");
        }
    }

    public ArrayList<String> generateInputVector(String input_option){


        if(input_option.equals("TRUE_TABLE")){
            //this.probCircuit.getInputs().size(), this.sampleSize
             ArrayList <String> vector = new ArrayList<>();
             int numLines = (int) Math.pow(2 ,this.probCircuit.getInputs().size());
             int n_inputs  = this.probCircuit.getInputs().size();

              for (int i = 0; i < numLines; i++){
                if(i < (numLines/2)){
                   //System.out.println(i + " - " + Integer.toBinaryString(i) +" len " +Integer.toBinaryString(i).length());

                   int len = Integer.toBinaryString(i).length();
                   String str = Integer.toBinaryString(i); //Transform in binary
                   if(len < n_inputs){
                       while(len < n_inputs){
                           str = "0" + str;
                           len++;
                       }
                   }

                   //System.out.println("" + (i+1) + " - " + str);
                   vector.add(str);
                }
                else{
                    //System.out.println("" + (i+1) + " - " + Integer.toBinaryString(i) );
                    vector.add(Integer.toBinaryString(i));
                   }
               }


             //System.out.println("TRUE TABLE: " + vector);
             return vector;

        }
        if(input_option.equals("RANDOM")){

            int inputSignaisSize = this.circuit.getInputs().size();

                        ArrayList <String> vector = new ArrayList<>();
                        Random gerador = new Random();

                        for (int i = 0; i < this.sampleSize; i++) {
                             String str = "";
                             for (int j = 0; j < inputSignaisSize; j++) {
                                  //int randomLogicValue = gerador.nextInt(2);
                                  //str = str + "0";
                                  str = str + gerador.nextInt(2);
                             }
                             vector.add(str);
                             //str = "";
                          }
                    return vector;
        }

        return null;
    }

    public ArrayList<String> calcInputTableVector(int n_inputs, int numLines){

         //System.out.println("       - Truthtable: ");
         ArrayList <String> vector = new ArrayList<>();

         for (int i = 0; i < numLines; i++) {

             if(i < (numLines/2)){
                //System.out.println(i + " - " + Integer.toBinaryString(i) +" len " +Integer.toBinaryString(i).length());

                int len = Integer.toBinaryString(i).length();
                String str = Integer.toBinaryString(i);
                if(len < n_inputs){
                    while(len < n_inputs){
                        str = "0" + str;
                        len++;
                    }
                }

                //System.out.println("" + (i+1) + " - " + str);
                vector.add(str);
             }
             else{
                 //System.out.println("" + (i+1) + " - " + Integer.toBinaryString(i) );
                 vector.add(Integer.toBinaryString(i));
             }
         }

         //System.out.println(""+vector);
         return vector;
     }

    public ArrayList <ArrayList<Integer>> splitInputPatternsInInt(ArrayList <String> vector, int n_input){

        ArrayList <ArrayList<Integer>> v = new ArrayList<>();

         for (int i = 0; i < vector.size(); i++) {
             ArrayList <Integer>  temp =  new ArrayList<>();

             String str = vector.get(i);
             for (int j = 0; j < n_input ; j++) {
                 temp.add(Integer.parseInt(str.substring(j, j+1)));
             }

             //System.out.println("TEMP: "+ temp);

             v.add(temp);
         }

         //System.out.println(""+v);

         return v;

     }

    public ArrayList <Integer> get_Input_Vectors(ArrayList <ArrayList<Integer>> ListInputVectors, int i){


             ArrayList <Integer> inputVectorTemp = ListInputVectors.get(i);
             ArrayList <Integer> inputVector = new ArrayList<>();

             //  System.out.println(">>: "+ ListInputVectors.get(i) + " SIZE: "+ ListInputVectors.size());

             for (int j = 0; j < inputVectorTemp.size(); j++) {  // 0 - 0000 ; 1 - 0001; 2 - 0010; .... (n) inputs
                              inputVector.add(ListInputVectors.get(i).get(j));
             }

         return inputVector;

     }

    public int sortRandomFaultInjection(){
                        //vetor de entrada aleatório

                        Random gerador = new Random();
                        int randomSignalIndex = gerador.nextInt(this.signals_to_inject_faults.size());

                        //System.out.println("Signal sorted: "+ this.internSignals.get(randomSignalIndex));

                        return randomSignalIndex;
     }

    public void writeCsvFileCompleteTh(String filename, ArrayList <LogicSimulator> itemx_list) throws IOException, WriteException{

             //System.out.println("Creating Complete log .....");
             ArrayList <Signal> outputSignals = this.circuit.getOutputs();
             ArrayList <Signal> inputSignals = this.circuit.getInputs();


            String Header = "Input Signals" + ";" + "Fault Signal" + ";" + "Fault Type" + ";" + "Fault-Free Circuit Output" + ";" + "Faulty Circuit Output";
            WriteCsvTh logfile = new WriteCsvTh();

            logfile.writeCSVCompleteThNew(this.circuit, filename, this.verilog_circuit.getCircuit().getName() + ".v", this.circuit.getInputs(), this.circuit.getOutputs(),
                    this.signals_to_inject_faults,  Header , itemx_list , Integer.toString(this.unmasked_faults));

     }

    public void writeCsvFileCompleteThMTF(String filename, ArrayList <LogicSimulator> itemx_list) throws IOException, WriteException{

        //System.out.println("Creating Complete log .....");
        ArrayList <Signal> outputSignals = this.circuit.getOutputs();
        ArrayList <Signal> inputSignals = this.circuit.getInputs();


        String Header = "Input Signals" + ";" + "Fault Signal" + ";" + "Fault Type" + ";" + "Fault-Free Circuit Output" + ";" + "Faulty Circuit Output";
        WriteCsvTh logfile = new WriteCsvTh();

        logfile.writeCSVCompleteThNewMTF(this.circuit, filename, this.verilog_circuit.getCircuit().getName() + ".v", this.circuit.getInputs(), this.circuit.getOutputs(),
                this.signals_to_inject_faults,  Header , itemx_list , Integer.toString(this.unmasked_faults));

    }


    public void writeSimpleLog(String filename, String date, String dateend, long propagateTimems) throws IOException{

        ///System.out.println("Creating .txt -> file: " + filename);
        //float reliability_circuit =  (float) ( 1 - ((float) this.unmasked_faults / (float) this.sampleSize));
        //double lamb = - Math.log(reliability_circuit);
        //this.MTBF = (1 / lamb);
        //System.out.println("MTBF : " + this.MTBF);
        String content = "";
        try (FileWriter file = new FileWriter(filename+".txt")) {


            content = "Started at Date/hour: "  + date + " and finished at: " + dateend  + "\n\n";
            //content = content + "Date/hour (finished): "+ dateend + "\n\n";
            content = content + "Circuit: " + this.circuit.getName() + "\n";
            content = content+  "Number of Simulations (sample size = N): " + this.sampleSize + "\n";
            content = content+  "Number of Threads: " + this.threads + "\n";
            content = content + "Number of detected faults (Ne): " + this.unmasked_faults + "\n";
            content = content + "Fault Mask Rate (FMR): "+ " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + this.sampleSize + ")) = " + this.circuitReliaibility + "\n";
            //content = content +  "MTBF: " + this.MTBF + "\n\n";

            content = content +  "Performance time(s): " + (propagateTimems) + "\n";


            /*

              System.out.println("\n\n----------------- Results ------------------");
                System.out.println("Circuit: " + this.circuit.getName());
                System.out.println("- Simulation started at: " + formattedDate + " and finished at: "+ formattedDate2); //formattedDate

               // System.out.println("- PropagatedTime (s): " + propagateTime);
                System.out.println("- Sample Size (N): " + N);
                System.out.println("- Number of detected faults (Ne): " + this.unmasked_faults);
                System.out.println("- Fault Mask Rate (FMR): " + " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + N + ")) = " + this.circuitReliaibility);
                //System.out.println("- MTBF (Mean Time Between failure) : " + this.MTBF);
                System.out.println("- Simulation TimeElapsed: " + propagateTime
                        + "(s)");

                System.out.println("--------------------------------------------");

            */


            file.write(content);
        }
     }

    public void writeSimpleLogMTF(String filename, String date, String dateend, long propagateTimems, int order, int period, int frequency) throws IOException{

        ///System.out.println("Creating .txt -> file: " + filename);
        //float reliability_circuit =  (float) ( 1 - ((float) this.unmasked_faults / (float) this.sampleSize));
        //double lamb = - Math.log(reliability_circuit);
        //this.MTBF = (1 / lamb);
        //System.out.println("MTBF : " + this.MTBF);
        String content = "";
        try (FileWriter file = new FileWriter(filename+".txt")) {


            content = "Started at Date/hour: "  + date + " and finished at: " + dateend  + "\n\n";
            //content = content + "Date/hour (finished): "+ dateend + "\n\n";
            content = content + "Circuit: " + this.circuit.getName() + "\n";
            content = content+  "Number of Simulations (sample size = N): " + this.sampleSize + "\n";
            content = content+  "For each  (" + period + ") faults happens a Multiple Transient Fault (" + order + ") with frequency: (" + frequency + ") - Sample = " + this.sampleSize + "\n";
            content = content+  "Number of Threads: " + this.threads + "\n";
            content = content + "Number of detected faults (Ne): " + this.unmasked_faults + "\n";
            content = content + "Fault Mask Rate (FMR): "+ " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + this.sampleSize + ")) = " + this.circuitReliaibility + "\n";
            //content = content +  "MTBF: " + this.MTBF + "\n\n";

            content = content +  "Performance time(s): " + (propagateTimems) + "\n";


            /*

              System.out.println("\n\n----------------- Results ------------------");
                System.out.println("Circuit: " + this.circuit.getName());
                System.out.println("- Simulation started at: " + formattedDate + " and finished at: "+ formattedDate2); //formattedDate

               // System.out.println("- PropagatedTime (s): " + propagateTime);
                System.out.println("- Sample Size (N): " + N);
                System.out.println("- Number of detected faults (Ne): " + this.unmasked_faults);
                System.out.println("- Fault Mask Rate (FMR): " + " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + N + ")) = " + this.circuitReliaibility);
                //System.out.println("- MTBF (Mean Time Between failure) : " + this.MTBF);
                System.out.println("- Simulation TimeElapsed: " + propagateTime
                        + "(s)");

                System.out.println("--------------------------------------------");

            */


            file.write(content);
        }
    }
    public void writeSimpleLogMTFMODE(String filename, String date, String dateend, long propagateTimems, ArrayList <Integer> mtf_list) throws IOException{

        ///System.out.println("Creating .txt -> file: " + filename);
        //float reliability_circuit =  (float) ( 1 - ((float) this.unmasked_faults / (float) this.sampleSize));
        //double lamb = - Math.log(reliability_circuit);
        //this.MTBF = (1 / lamb);
        //System.out.println("MTBF : " + this.MTBF);
        String content = "";
        try (FileWriter file = new FileWriter(filename+".txt")) {


            content = "Multiple Transient Fault Simulation Started at Date/hour: "  + date + " and finished at: " + dateend  + "\n\n";
            //content = content + "Date/hour (finished): "+ dateend + "\n\n";
            content = content + "Circuit: " + this.circuit.getName() + "\n";
            content = content+  "Number of Simulations (sample size = N): " + this.sampleSize + "\n";

            for (int i = 0; i < mtf_list.size(); i++) {
                content = content +  "      - For each  (" + mtf_list.get(i) + ") faults happens a MTF of order (" + (i+2) + ")\n" ;
            }
            content = content + "- Sample = " + this.sampleSize + "\n";
            //content = content+  "For each  (" + period + ") faults happens a Multiple Transient Fault (" + order + ") with frequency: (" + frequency + ") - Sample = " + this.sampleSize + "\n";
            content = content+  "Number of Threads: " + this.threads + "\n";
            content = content + "Number of detected faults (Ne): " + this.unmasked_faults + "\n";
            content = content + "Fault Mask Rate (FMR): "+ " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + this.sampleSize + ")) = " + this.circuitReliaibility + "\n";
            //content = content +  "MTBF: " + this.MTBF + "\n\n";

            content = content +  "Performance time(s): " + (propagateTimems) + "\n";


            /*

              System.out.println("\n\n----------------- Results ------------------");
                System.out.println("Circuit: " + this.circuit.getName());
                System.out.println("- Simulation started at: " + formattedDate + " and finished at: "+ formattedDate2); //formattedDate

               // System.out.println("- PropagatedTime (s): " + propagateTime);
                System.out.println("- Sample Size (N): " + N);
                System.out.println("- Number of detected faults (Ne): " + this.unmasked_faults);
                System.out.println("- Fault Mask Rate (FMR): " + " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + N + ")) = " + this.circuitReliaibility);
                //System.out.println("- MTBF (Mean Time Between failure) : " + this.MTBF);
                System.out.println("- Simulation TimeElapsed: " + propagateTime
                        + "(s)");

                System.out.println("--------------------------------------------");

            */


            file.write(content);
        }
    }


    public List particionateVectorPerThread(ArrayList <ArrayList<Integer>> ListInputVectors) throws ScriptException, Exception{

        List thread_list = new ArrayList();

                int N = this.sampleSize;

                int partition;
                if(this.threads == 1){
                     partition = N; //final_pos/NThreads ;
                }
                else{
                    double temp;
                    temp = Math.floor(N/this.threads);
                    partition =  (int) temp ;//(ints) Math.round(collapsed_faults/NThreads);
                }

                int start = 0;
                int end = partition;

                /* In case logic gates One and Zero
                    //ArrayList <Signal> Signals_CTE_ONE_ZERO = identificate_ONE_ZERO_CTE();  //ONLY USE WHEN ITS NOT CADENCE.GENLIB or GenIB with ZERO ONE GATES
                    //System.out.println("LOGIC GATES consider WIRES (CTE) Can't inject fault: " + Signals_CTE_ONE_ZERO);
               */


               for (int i = 0; i < this.threads; i++) { //Loop of simulations

                        ArrayList <TestVectorInformation> ItemxSimulationList = new ArrayList<>();
                        ArrayList <Integer> inputVector = new ArrayList<>();
                        //System.out.println("Start: " + start + " End: " + end);
                        if((this.threads-1) == (i)){

                            start = end;
                            end = N;
                        }
                        else{
                            if(i == 0){
                                start = 0;
                                end = partition;
                            }else{
                                 start = start + partition;
                                 end = start + partition;
                            }

                        }


                        System.out.println(" - starting thread: "+i  + " - simulate fault injection (number): " + partition);
                        for (int j = start; j < end ; j++) {

                                inputVector = this.get_Input_Vectors(ListInputVectors, j); //input Test n
                                int SigIndex = this.sortRandomFaultInjection(); //int SigIndex = decide_Random_Signals_Contrains(Signals_CTE_ONE_ZERO);

                                TestVectorInformation temp = new TestVectorInformation(inputVector, this.signals_to_inject_faults.get(SigIndex), j+1);
                                ItemxSimulationList.add(temp);
                        }

                        LogicSimulator threadItem = new LogicSimulator(ItemxSimulationList, this.circuit, this.cellLibrary, this.levelCircuit, start, end, this.genlib , this.circuitNameStr); // Thread contex info
                        threadItem.setMode("Single");
                        itemx_list.add(threadItem);

                        Runnable runnable = threadItem;
                        Thread thread = new Thread(runnable);
                        thread.setName(Integer.toString(threadItem.hashCode()));
                        thread_list.add(thread);

                     }

               return thread_list;

    }

    public int sortExclusiveFaultIndex(ArrayList <Integer> SigIndexList, TestVectorInformation temp ){

        boolean flag = true;

        while(flag){

            int new_pos = this.sortRandomFaultInjection();

            if(!SigIndexList.contains(new_pos)){
                //
                // System.out.println(" New position Founded : " +  this.signals_to_inject_faults.get(new_pos) + "   faultList:" + temp.get_MTF_FaultSignal_List() + "     complete signalslist: " + this.signals_to_inject_faults );
                flag = false;
                return new_pos;
            }
            else{
                //System.out.println(" Alread exist in faultlist : " + this.signals_to_inject_faults.get(new_pos) +  "     list: " + temp.get_MTF_FaultSignal_List());
            }

            /*
            if(new_pos != used){
                flag = false;
                return new_pos;
            }
            */



        }

        return  -1;
    }

    public HashMap <Integer, Integer> getKeys(HashMap<Integer, Integer> map, Integer value) {
        HashMap <Integer, Integer> keys = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                keys.put(entry.getKey(), entry.getValue());
            }
        }
        return keys;
    }

    public int getPosMap(HashMap <Integer, multiple_faults_object> map,  int value){
        HashMap <Integer, multiple_faults_object> keys = new HashMap<>();
        for (Map.Entry<Integer, multiple_faults_object> entry : map.entrySet()) {
            if (entry.getValue().getCounter()
                    == value) {
                keys.put(entry.getKey(), entry.getValue());
                System.out.println("FOUNDED: " + entry.getKey());
                return value;
            }
        }

        return -1;

    }

    public int getPosArrayListNew(ArrayList <Integer> map, ArrayList <Integer> map_original,  int value){
        int pos = -1;
        //int times = 0;
        ArrayList <Integer> times_ = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
                    if(map.get(i) == value){
                        pos = i;
                        int new_value =  map_original.get(i)+ map.get(i);
                        map.set(i,new_value);
                    }
        }


        /*
        if(times_.size() > 0){ // equal occorrence happens [15000 6000 15000] for example
            for (int i = 0; i < map.size(); i++) {
                if((map.get(i) == value) && (times_.get(i) == i) && (times_.get(i) != pos)){

                }
            }
        }
        */


        return pos;

    }

    public List particionateMultipletransientFaultInjectionVectorPerThreadMODE(ArrayList <ArrayList<Integer>> ListInputVectors, ArrayList <Integer> mtf_list) throws ScriptException, Exception{

        System.out.println("\n\n         +++++++    Dev mode  ++++++");
        System.out.println("MTF LIST = " + mtf_list);

        List thread_list = new ArrayList();
        int count_frequency = 0;

        int N = this.sampleSize;

        int partition;
        if(this.threads == 1){
            partition = N; //final_pos/NThreads ;
        }
        else{
            double temp;
            temp = Math.floor(N/this.threads);
            partition =  (int) temp ;//(ints) Math.round(collapsed_faults/NThreads);
        }

        int start = 0;
        int end = partition;

                /* In case logic gates One and Zero
                    //ArrayList <Signal> Signals_CTE_ONE_ZERO = identificate_ONE_ZERO_CTE();  //ONLY USE WHEN ITS NOT CADENCE.GENLIB or GenIB with ZERO ONE GATES
                    //System.out.println("LOGIC GATES consider WIRES (CTE) Can't inject fault: " + Signals_CTE_ONE_ZERO);
               */

        HashMap  <Integer, multiple_faults_object> arraylist_mtf = new HashMap<>();
        //HashMap  <Integer, Integer> map = new HashMap<>();
        //HashMap  <Integer, Integer> mapOrder = new HashMap<>();

        mtf_list.remove(0);

        final ArrayList <Integer> arrayList_mtf_original = new ArrayList<>(mtf_list); // Original ArrayList

        for (int i = 1; i < mtf_list.size(); i++) {
            multiple_faults_object object_temp =  new multiple_faults_object(i+1, mtf_list.get(i), 0);
            arraylist_mtf.put(mtf_list.get(i), object_temp);
            //map.put(mtf_list.get(i), i+1);
            //mapOrder.put(i+1 , mtf_list.get(i));
        }
        System.out.println(arraylist_mtf + " and original " +  arrayList_mtf_original);

        //int times = 1;
        for (int i = 0; i < this.threads; i++) { //Loop of simulations

            ArrayList<TestVectorInformation> ItemxSimulationList = new ArrayList<>();
            ArrayList<Integer> inputVector = new ArrayList<>();
            //System.out.println("Start: " + start + " End: " + end);
            if ((this.threads - 1) == (i)) {
                start = end;
                end = N;
            } else {
                if (i == 0) {
                    //start = 1;
                    start = 0;
                    end = partition;
                } else {
                    start = start + partition;
                    end = start + partition;
                }

            }



            System.out.println(" - starting thread: "+i  + " - simulate fault injection (number): " + partition);
            int counter = 0;

            for (int j = start; j < end ; j++) {
                //System.out.println(i);
                //if((arraylist_mtf.containsKey(j)) && (arraylist_mtf.get(j).getOrder() > 1)){
                //int index = getPosMap(arraylist_mtf, j);

                int index = getPosArrayListNew(mtf_list, arrayList_mtf_original, j);

                //if(j> 149 && j < 1000)
                   /// System.out.println(j + "  index: " + index + " Key: " + mtf_list + "    " + arrayList_mtf_original
                 //
                //   );

               /// if((index > -1) && (arraylist_mtf.containsKey(j)) && (arraylist_mtf.get(j).getOrder() >1)){

                if((index > -1)){

                    //int order = arraylist_mtf.get(j).getOrder();

                    int order = (index)+ 2;


                   // multiple_faults_object temp_x = arraylist_mtf.get(j);




                    inputVector = this.get_Input_Vectors(ListInputVectors, j); //input Test n

                    int SigIndex = this.sortRandomFaultInjection(); //int SigIndex = decide_Random_Signals_Contrains(Signals_CTE_ONE_ZERO);

                    //TestVectorInformation temp = new TestVectorInformation(inputVector, this.signals_to_inject_faults.get(SigIndex), j + 1);
                    TestVectorInformation temp = new TestVectorInformation(inputVector, this.signals_to_inject_faults.get(SigIndex), j );

                    ArrayList <Integer> SigIndexList = new ArrayList<Integer>();

                    SigIndexList.add(SigIndex);

                    for (int k = 1; k < order; k ++){
                        //System.out.println("  ~~ ~~~~ ~~  Injection MTF number : " + k);
                        //temp.setMultipleTransientFaultInjection( this.signals_to_inject_faults.get( this.sortRandomFaultInjection()));
                        int new_pos = sortExclusiveFaultIndex(SigIndexList, temp);
                        temp.setMultipleTransientFaultInjection( this.signals_to_inject_faults.get(new_pos));
                        SigIndexList.add(new_pos); // Do no reapet signals

                    }

                    System.out.println("  Founded key : " + j + "  list: " + mtf_list +  "   " + arrayList_mtf_original + " fault list " + temp.get_MTF_FaultSignal_List() + "  order: "  + order  );


                    ItemxSimulationList.add(temp);

                   ///

                    //mtf_list.set(index, arrayList_mtf_original.get(index) + j);

                    /*

                    multiple_faults_object x_temp = arraylist_mtf.get(j);






                    int a =  x_temp.getCounter();    //150
                    x_temp.setList(j+ arraylist_mtf.get(j).getOriginal_counter()); //300
                    x_temp.updateCounter(j+ arraylist_mtf.get(j).getOriginal_counter()); //counter 300


                    //multiple_faults_object yy_temp = arraylist_mtf.get(j);

                    arraylist_mtf.remove(j);// remove 150
                    arraylist_mtf.put(j , x_temp); // Add 300 key


                    x_temp.printList();


                     */

                  //  System.out.println(inputVector+ "--> Order: " + order + "   J index: " + j + "  mapOld" + map + "  mapNew: " + arraylist_mtf

                   //         + " - faultSig list random choose : " + temp.get_MTF_FaultSignal_List() );


                }else{
                    inputVector = this.get_Input_Vectors(ListInputVectors, j); //input Test n
                    int SigIndex = this.sortRandomFaultInjection(); //int SigIndex = decide_Random_Signals_Contrains(Signals_CTE_ONE_ZERO);

                    //TestVectorInformation temp = new TestVectorInformation(inputVector, this.signals_to_inject_faults.get(SigIndex), j + 1);
                    TestVectorInformation temp = new TestVectorInformation(inputVector, this.signals_to_inject_faults.get(SigIndex), j
                    );
                    ItemxSimulationList.add(temp);
                }
                counter++;

            }
            System.out.println("Start : " + start + " END: " + end);
            System.out.println("  Founded key : " + this.sampleSize + "  list: " + mtf_list +  "   " + arrayList_mtf_original
            );


            LogicSimulator threadItem = new LogicSimulator(ItemxSimulationList, this.circuit, this.cellLibrary, this.levelCircuit, start, end, this.genlib , this.circuitNameStr); // Thread contex info
            threadItem.setMode("Multiple");
            itemx_list.add(threadItem);

            Runnable runnable = threadItem;
            Thread thread = new Thread(runnable);
            thread.setName(Integer.toString(threadItem.hashCode()));
            thread_list.add(thread);

            System.out.println("            \n                  - Thread id: " + threadItem.getThreadId() + "  Simulation Size: " + threadItem.getThreadSimulatinArray().size() + "  MTF: " + ItemxSimulationList.size());


        }
        System.out.println("SIZE : " +  arraylist_mtf.size());

        return thread_list;
    }

    public List particionateMultipletransientFaultInjectionVectorPerThread(ArrayList <ArrayList<Integer>> ListInputVectors,int period, int order, int frequency) throws ScriptException, Exception{

        System.out.println("\n\n         +++++++    Dev mode  ++++++");
        System.out.println("Period = " +period);

        List thread_list = new ArrayList();
        int count_frequency = 0;

        int N = this.sampleSize;

        int partition;
        if(this.threads == 1){
            partition = N; //final_pos/NThreads ;
        }
        else{
            double temp;
            temp = Math.floor(N/this.threads);
            partition =  (int) temp ;//(ints) Math.round(collapsed_faults/NThreads);
        }

        int start = 0;
        int end = partition;

                /* In case logic gates One and Zero
                    //ArrayList <Signal> Signals_CTE_ONE_ZERO = identificate_ONE_ZERO_CTE();  //ONLY USE WHEN ITS NOT CADENCE.GENLIB or GenIB with ZERO ONE GATES
                    //System.out.println("LOGIC GATES consider WIRES (CTE) Can't inject fault: " + Signals_CTE_ONE_ZERO);
               */


        for (int i = 0; i < this.threads; i++) { //Loop of simulations

            ArrayList <TestVectorInformation> ItemxSimulationList = new ArrayList<>();
            ArrayList <Integer> inputVector = new ArrayList<>();
            //System.out.println("Start: " + start + " End: " + end);
            if((this.threads-1) == (i)){
                start = end;
                end = N;
            }
            else{
                if(i == 0){
                    start = 0;
                    end = partition;
                }else{
                    start = start + partition;
                    end = start + partition;
                }

            }


            System.out.println(" - starting thread: "+i  + " - simulate fault injection (number): " + partition);
            for (int j = start; j < end ; j++) {

                if(count_frequency == (period-1)){

                    System.out.println("  \n   --------> MTF Injection : " + period + "  j: " + j );
                    // Inject multiple fault order
                    count_frequency = 0;

                    inputVector = this.get_Input_Vectors(ListInputVectors, j); //input Test n

                    int SigIndex = this.sortRandomFaultInjection(); //int SigIndex = decide_Random_Signals_Contrains(Signals_CTE_ONE_ZERO);

                    TestVectorInformation temp = new TestVectorInformation(inputVector, this.signals_to_inject_faults.get(SigIndex), j + 1);

                    ArrayList <Integer> SigIndexList = new ArrayList<Integer>();

                    SigIndexList.add(SigIndex);

                    // temp.set_MTBF_flag();

                    for (int k = 1 ; k < order; k ++){
                        System.out.println(" xxxxxx Injection MTF number : " + k);
                        //temp.setMultipleTransientFaultInjection( this.signals_to_inject_faults.get( this.sortRandomFaultInjection()));
                        int new_pos = sortExclusiveFaultIndex(SigIndexList, temp);
                        temp.setMultipleTransientFaultInjection( this.signals_to_inject_faults.get(new_pos));

                        System.out.println(" - faultSig list random choose : " + temp.get_MTF_FaultSignal_List() );
                    }

                    ItemxSimulationList.add(temp);

                }
                else {
                    count_frequency++;

                    inputVector = this.get_Input_Vectors(ListInputVectors, j); //input Test n
                    int SigIndex = this.sortRandomFaultInjection(); //int SigIndex = decide_Random_Signals_Contrains(Signals_CTE_ONE_ZERO);

                    TestVectorInformation temp = new TestVectorInformation(inputVector, this.signals_to_inject_faults.get(SigIndex), j + 1);
                    ItemxSimulationList.add(temp);
                }
            }

            LogicSimulator threadItem = new LogicSimulator(ItemxSimulationList, this.circuit, this.cellLibrary, this.levelCircuit, start, end, this.genlib , this.circuitNameStr); // Thread contex info
            threadItem.setMode("Multiple");
            itemx_list.add(threadItem);

            Runnable runnable = threadItem;
            Thread thread = new Thread(runnable);
            thread.setName(Integer.toString(threadItem.hashCode()));
            thread_list.add(thread);

            System.out.println("            \n                  - Thread id: " + threadItem.getThreadId() + "  Simulation Size: " + threadItem.getThreadSimulatinArray().size() + "  MTF: " + ItemxSimulationList.size());


        }

        return thread_list;
    }


    public List particionateExausticVector(ArrayList <ArrayList<Integer>> ListInputVectors) throws ScriptException, Exception{

        List thread_list = new ArrayList();

                int N = this.sampleSize;

                int partition;
                if(this.threads == 1){
                     partition = N; //final_pos/NThreads ;
                }
                else{
                    double temp;
                    temp = Math.floor(N/this.threads);
                    partition =  (int) temp ;//(int) Math.round(collapsed_faults/NThreads);
                }

                int start = 0;
                int end = partition;

                /* In case logic gates One and Zero
                    //ArrayList <Signal> Signals_CTE_ONE_ZERO = identificate_ONE_ZERO_CTE();  //ONLY USE WHEN ITS NOT CADENCE.GENLIB
                    //System.out.println("LOGIC GATES consider WIRES (CTE) Can't inject fault: " + Signals_CTE_ONE_ZERO);
               */

               for (int i = 0; i < this.threads; i++) { //Loop of simulations

                        ArrayList <TestVectorInformation> ItemxSimulationList = new ArrayList<>();
                        ArrayList <Integer> inputVector = new ArrayList<>();

                        if((this.threads-1) == (i)){

                            start = end;
                            end = N;
                        }
                        else{
                            if(i == 0){
                                start = 0;
                                end = partition;
                            }else{
                                 start = start + partition;
                                 end = start + partition;
                            }

                        }


                        System.out.println(" - starting thread: "+i  + " - simulate fault injection (number): " + partition);
                        for (int j = start; j < end ; j++) {

                            for (int aux = 0; aux < this.signals_to_inject_faults.size(); aux++) {
                                inputVector = this.get_Input_Vectors(ListInputVectors, j); //input Test n

                                int SigIndex = aux;//this.sortRandomFaultInjection(); //int SigIndex = decide_Random_Signals_Contrains(Signals_CTE_ONE_ZERO);

                                TestVectorInformation temp = new TestVectorInformation(inputVector, this.signals_to_inject_faults.get(SigIndex), j+1);
                                ItemxSimulationList.add(temp);

                                System.out.println("Vec: " + inputVector + " Fault Signal: " +  this.signals_to_inject_faults.get(SigIndex));
                            }

                        }

                        LogicSimulator threadItem = new LogicSimulator(ItemxSimulationList, this.circuit, this.cellLibrary, this.levelCircuit, start, end, this.genlib , this.circuitNameStr); // Thread contex info
                        threadItem.setMode("Single");
                        itemx_list.add(threadItem);

                        Runnable runnable = threadItem;
                        Thread thread = new Thread(runnable);
                        thread.setName(Integer.toString(threadItem.hashCode()));
                        thread_list.add(thread);

                     }

               return thread_list;

    }

    public void runMultithreadingExausticSimulation(String option) throws IOException, Exception{ //Test All possibilities


                System.out.println(" ----- Exaustive Simulation Version -------");
                long loadTimeStart = System.nanoTime();//System.currentTimeMillis();


                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);
                System.out.println("    - Simulation start in : " + formattedDate);
                System.out.println("    - Threads in execution: " + this.threads);

                /*Reading CellLibrary*/
                CellLibrary cellLib = new CellLibrary();

               // System.out.println("1");
                this.cellLibrary = cellLib;
               // System.out.println("2");
                this.cellLibrary.initLibrary(this.genlib);
                System.out.println("3"
                        + "");
                System.out.println("    ... Reading Genlib " + " at -> " + this.genlib  + " ... ok");
                //System.out.println("  - Avaliable logic gatesin this library: "+cellLib.getCells());


                /*Reading verilog*/
                MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitNameStr, this.cellLibrary);
                this.verilog_circuit = verilog_circuit;
                /*Circuit linked to verilog_circuit - init circuit*/
                this.circuit = verilog_circuit.getCircuit();
                System.out.println("    ... Reading verilog "+ " at -> " + this.circuitNameStr  + " ... ok");
                //System.out.println("Patterns : " + this.verilog_circuit.getGatePattern());



                /* Print circuit Specs*/
                System.out.println("\n        ------ Printing Circuit Specs: --------");
                     //this.PrintSpecsThesis();
                System.out.println("          ---------------------------------------\n");
                /*----------------------*/


                /*Circuit Probabilities */
                this.initLevelCircuit();

                /*Init ProbCircuits*/
                this.initProbCircuit();

                /*Init PTMs Const*/
                cellLib.setPTMCells2(Float.valueOf(this.reliabilityConst));
                cellLib.setPTMCells(new BigDecimal(this.reliabilityConst));

                long loadTimeEnd = System.nanoTime();//System.currentTimeMillis();
                long loadTime =   TimeUnit.NANOSECONDS.toMillis(loadTimeEnd - loadTimeStart);
                //System.out.println("- Load Time m(s): " + loadTime);

                this.sampleSize = (int) Math.pow(2, this.probCircuit.getInputs().size());  //(int) Math.pow(2, this.probCircuit.getInputs().size());
                int N = this.sampleSize; // random_input_vectors.size();//testNumber;

                int sizeExasuticTest;

                System.out.println("-   Sample size (N = 2^ENTRADAS): " + "2^"+ this.circuit.getInputs().size() + " = " + this.sampleSize);

                this.signals_to_inject_faults = this.signalsToInjectFault(option); // Consider all signals to fault inject

                sizeExasuticTest = (this.sampleSize * this.signals_to_inject_faults.size());

                ArrayList <String> random_input_vectors =  this.generateInputVector("TRUE_TABLE"); //this.calcInputTableVector(this.probCircuit.getInputs().size(), this.sampleSize);
                ArrayList <ArrayList<Integer>> ListInputVectors =  this.splitInputPatternsInInt(random_input_vectors, this.probCircuit.getInputs().size());

                //System.out.println("LIST:::::: "+ ListInputVectors);

                List thread_list = particionateExausticVector(ListInputVectors);  // TESTE ALL GATES ///particionateVectorPerThread(ListInputVectors); // x - vectors per thread

                long propagateTimeStart = System.nanoTime();

                /*Execução das threads*/
                Thread thread_temp = null;
                for (int i=0; i < thread_list.size() ; i++) {
                    thread_temp = (Thread) thread_list.get(i);
                    thread_temp.start();

                }
                /*Esperando termino das threads*/
                for (int i=0; i < thread_list.size() ; i++) {
                    thread_temp = (Thread) thread_list.get(i);
                    thread_temp.join();
                }

                /* Compilando os resultados - Falhas detectadas Ne*/
                for (int i=0; i < this.itemx_list.size() ; i++) {
                    this.unmasked_faults = this.unmasked_faults +  itemx_list.get(i).getPropagatedFaults();
                }

                /*circuit reliability SER (Soft Error Rate)*/
                this.circuitReliaibility = (float) (1.0 - ((float) this.unmasked_faults / (float) sizeExasuticTest));

                System.out.println("-> Umasked Faults: " + this.unmasked_faults);
                 System.out.println("-> Sample: " + sizeExasuticTest);
                  System.out.println("-> SER : " + this.circuitReliaibility);


                long propagateTimeEnd = System.nanoTime();
                //long propagateTime =    TimeUnit.NANOSECONDS.toSeconds(propagateTimeEnd - propagateTimeStart);
                long propagateTime =  TimeUnit.NANOSECONDS.toMillis(propagateTimeEnd - propagateTimeStart);


                LocalDateTime myDateObj2 = LocalDateTime.now();
                DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate2 = myDateObj2.format(myFormatObj2);

                this.sampleSize = sizeExasuticTest;

                this.writeSimpleLog("ExausticSimulation_" +this.circuit.getName()+"_Threads-"+ this.threads + "_sampleSize-" + this.sampleSize, formattedDate,  formattedDate2, propagateTime);

                this.writeCsvFileCompleteTh("ExausticSimulation_"+this.circuit.getName()+"_Theads-"+ this.threads + "_sampleSize-" + this.sampleSize, itemx_list);


                System.out.println("\n\n----------------- Results ------------------");
                System.out.println("Circuit: " + this.circuit.getName());
                System.out.println("- Simulation finished at: " + formattedDate2);
                //System.out.println("- PropagatedTime (s): " + propagateTime);
                System.out.println("- Sample (N): " + this.sampleSize);
                System.out.println("- Detected faults (Ne): " + this.unmasked_faults);
                System.out.println("- Fault Masking Rate (FMR): " + "(1-(" + this.unmasked_faults + "/" + this.sampleSize + ")) = " + this.circuitReliaibility);
                // System.out.println("- MTBF (Mean Time Between failure) : " + this.MTBF);
                System.out.println("- Simulation TimeElapsed: " + propagateTime + "(s)");
                System.out.println("--------------------------------------------");

                this.Performance_Time = "Simulation started at: " + formattedDate + " and finished at: " + formattedDate2;

                System.out.println(" ----------------------------------------------------------------------------------------------------------------------------\n\n");
             /*
             */
     }

    public void runMultithreadingSimulation(String option) throws IOException, Exception{


                 System.out.println(" ----- Multithreading Version -------");
                 long loadTimeStart = System.nanoTime();//System.currentTimeMillis();

                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);
                System.out.println("    - Simulation start in : " + formattedDate);
                System.out.println("    - Threads in execution: " + this.threads);

                /*Reading CellLibrary*/
                CellLibrary cellLib = new CellLibrary();
                this.cellLibrary = cellLib;
                this.cellLibrary.initLibrary(this.genlib);
                System.out.println("    ... Reading Genlib " + " at -> " + this.genlib  + " ... ok");
                //System.out.println("  - Avaliable logic gatesin this library: "+cellLib.getCells());


                /*Reading verilog*/
                MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitNameStr, this.cellLibrary);
                this.verilog_circuit = verilog_circuit;
                /*Circuit linked to verilog_circuit - init circuit*/
                this.circuit = verilog_circuit.getCircuit();
                System.out.println("    ... Reading verilog "+ " at -> " + this.circuitNameStr  + " ... ok");
                //System.out.println("Patterns : " + this.verilog_circuit.getGatePattern());





                /*Circuit Probabilities */
                this.initLevelCircuit();

                /*Init ProbCircuits*/
                this.initProbCircuit();

                /*Init PTMs Const*/
                cellLib.setPTMCells2(Float.valueOf(this.reliabilityConst));
                cellLib.setPTMCells(new BigDecimal(this.reliabilityConst));

                 long loadTimeEnd = System.nanoTime();//System.currentTimeMillis();
                long loadTime =   TimeUnit.NANOSECONDS.toMillis(loadTimeEnd - loadTimeStart);
                //System.out.println("- Load Time m(s): " + loadTime);

                this.sampleSize = (int) Math.pow(2, this.probCircuit.getInputs().size());  //(int) Math.pow(2, this.probCircuit.getInputs().size());
                int N = this.sampleSize; // random_input_vectors.size();//testNumber;

                System.out.println("-   Sample size (N = 2^ENTRADAS): " + "2^"+ this.circuit.getInputs().size() + " = " + this.sampleSize);

                this.signals_to_inject_faults = this.signalsToInjectFault(option); // Consider all signals to fault inject

                ArrayList <String> random_input_vectors =  this.generateInputVector("TRUE_TABLE"); //this.calcInputTableVector(this.probCircuit.getInputs().size(), this.sampleSize);
                ArrayList <ArrayList<Integer>> ListInputVectors =  this.splitInputPatternsInInt(random_input_vectors, this.probCircuit.getInputs().size());

                //System.out.println("LIST:::::: "+ ListInputVectors);

                List thread_list = particionateVectorPerThread(ListInputVectors); // x - vectors per thread

                long propagateTimeStart = System.nanoTime();

                /*Execução das threads*/
                Thread thread_temp = null;
                for (int i=0; i < thread_list.size() ; i++) {
                    thread_temp = (Thread) thread_list.get(i);
                    thread_temp.start();

                }
                /*Esperando termino das threads*/
                for (int i=0; i < thread_list.size() ; i++) {
                    thread_temp = (Thread) thread_list.get(i);
                    thread_temp.join();
                }

                /* Compilando os resultados - Falhas detectadas Ne*/
                for (int i=0; i < this.itemx_list.size() ; i++) {
                    this.unmasked_faults = this.unmasked_faults +  itemx_list.get(i).getPropagatedFaults();
                }

                /*circuit reliability SER (Soft Error Rate)*/
                //this.circuitReliaibility = (1 - (this.unmasked_faults/this.sampleSize));

                  /*circuit reliability SER (Soft Error Rate)*/
                this.circuitReliaibility = (float) (1.0 - ((float) this.unmasked_faults / (float) this.sampleSize));

                long propagateTimeEnd = System.nanoTime();
                //long propagateTime =    TimeUnit.NANOSECONDS.toSeconds(propagateTimeEnd - propagateTimeStart);
                long propagateTime =  TimeUnit.NANOSECONDS.toMillis(propagateTimeEnd - propagateTimeStart);


                LocalDateTime myDateObj2 = LocalDateTime.now();
                DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate2 = myDateObj2.format(myFormatObj2);

                this.Performance_Time = "Simulation started at: " + formattedDate + " and finished at: " + formattedDate2;

                this.writeSimpleLog("MonteCarlo_Simple_Log_" +this.circuit.getName()+"_Threads-"+ this.threads +  "_sampleSize-" + N, formattedDate,  formattedDate2, propagateTime);

                this.writeCsvFileCompleteTh("MonteCarlo_Complete_Log_"+this.circuit.getName()+"_Theads-"+ this.threads + "_sampleSize", itemx_list);

                   /* Print circuit Specs*/
                System.out.println("\n        ------ Printing Circuit Specs: --------");
                this.PrintSpecsThesis();
                System.out.println("          ---------------------------------------\n");
                /*----------------------*/

                System.out.println("\n\n----------------- Results ------------------");
                System.out.println("Circuit: " + this.circuit.getName());
                System.out.println("- Simulation finished at: " + formattedDate2);
                //System.out.println("- PropagatedTime (s): " + propagateTime);
                System.out.println("- Sample (N): " + N);
                System.out.println("- Detected faults (Ne): " + this.unmasked_faults);
                System.out.println("- Fault Masking Rate (FMR): " + "(1-(" + this.unmasked_faults + "/" + N + ")) = " + this.circuitReliaibility);
                // System.out.println("- MTBF (Mean Time Between failure) : " + this.MTBF);
                System.out.println("- Simulation TimeElapsed: " + propagateTime + "(s)");
                System.out.println("--------------------------------------------");

                 System.out.println(" ----------------------------------------------------------------------------------------------------------------------------\n\n");
             /*
             */
     }

    public void runMultithreading_MonteCarloSample_per_Area_Analisys(int sampleSize, String option, int smallestGatesIncicuitsSimulation) throws IOException, Exception{

                System.out.println(" ----- Monte Carlo per area analysis version -------");
                long loadTimeStart = System.nanoTime();//System.currentTimeMillis();

                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);
                System.out.println("    - Simulation start in : " + formattedDate);
                System.out.println("    - Threads in execution: " + this.threads);

                /*Reading CellLibrary*/
                CellLibrary cellLib = new CellLibrary();
                this.cellLibrary = cellLib;
                this.cellLibrary.initLibrary(this.genlib);
                System.out.println("    ... Reading Genlib " + " at -> " + this.genlib  + " ... ok");
                //System.out.println("  - Avaliable logic gatesin this library: "+cellLib.getCells());


                /*Reading verilog*/
                MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitNameStr, this.cellLibrary);
                this.verilog_circuit = verilog_circuit;
                /*Circuit linked to verilog_circuit - init circuit*/
                this.circuit = verilog_circuit.getCircuit();
                System.out.println("    ... Reading verilog "+ " at -> " + this.circuitNameStr  + " ... ok");
                //System.out.println("Patterns : " + this.verilog_circuit.getGatePattern());



                /* Print circuit Specs*/


                /*Circuit Probabilities */
                this.initLevelCircuit();

                /*Init ProbCircuits*/
                this.initProbCircuit();

                /*Init PTMs Const*/
                cellLib.setPTMCells2(Float.valueOf(this.reliabilityConst));
                cellLib.setPTMCells(new BigDecimal(this.reliabilityConst));

                long loadTimeEnd = System.nanoTime();//System.currentTimeMillis();
                long loadTime =   TimeUnit.NANOSECONDS.toMillis(loadTimeEnd - loadTimeStart);
                //System.out.println("- Load Time m(s): " + loadTime);

                System.out.println("\n        ------ Printing Circuit Specs: --------");
                this.PrintSpecs();
                System.out.println("          ---------------------------------------\n");
                /*----------------------*/
                float divisao = (float) ((float) this.circuit.getGates().size() / (float) smallestGatesIncicuitsSimulation);
                float temp_value = ((divisao ) * sampleSize);

                this.sampleSize = (int) temp_value; //sampleSize; //(int) Math.pow(2, this.probCircuit.getInputs().size());  //(int) Math.pow(2, this.probCircuit.getInputs().size());
                int N = this.sampleSize; // random_input_vectors.size();//testNumber;

                System.out.println("Circuit: " + this.circuit.getName() + "  lib: " + this.genlib);
                     System.out.println("        -Gates: " + this.circuit.getGates().size() + " c1355 (Ngates): " + smallestGatesIncicuitsSimulation + " sampleInicial: " + sampleSize );
                     System.out.println("       (" + this.circuit.getGates().size() + "/" + smallestGatesIncicuitsSimulation + ")*" + sampleSize + " =  " + temp_value);
                     System.out.println("       (" + divisao + ")*" + sampleSize + " =  " + temp_value);



                System.out.println("-  (input) Sample size = " + this.sampleSize);

                this.signals_to_inject_faults = this.signalsToInjectFault(option); // Consider all signals to fault inject

                ArrayList <String> random_input_vectors = this.generateInputVector("RANDOM"); // Generate Random Input Vectors or InputTrueTable

                ArrayList <ArrayList<Integer>> ListInputVectors =  this.splitInputPatternsInInt(random_input_vectors, this.probCircuit.getInputs().size());

                List thread_list = this.particionateVectorPerThread(ListInputVectors); // x - vectors per thread

                long propagateTimeStart = System.nanoTime();

                /*Execução das threads*/
                Thread thread_temp = null;
                for (int i=0; i < thread_list.size() ; i++) {
                    thread_temp = (Thread) thread_list.get(i);
                    thread_temp.start();

                }
                /*Esperando termino das threads*/
                for (int i=0; i < thread_list.size() ; i++) {
                    thread_temp = (Thread) thread_list.get(i);
                    thread_temp.join();
                }

                /* Compilando os resultados - Falhas detectadas Ne*/
                for (int i=0; i < this.itemx_list.size() ; i++) {
                    this.unmasked_faults = this.unmasked_faults +  itemx_list.get(i).getPropagatedFaults();
                }

                 this.circuitReliaibility = (float) (1.0 - ((float) this.unmasked_faults / (float) this.sampleSize));

                /*circuit reliability SER (Soft Error Rate)*/
                //this.circuitReliaibility = (1 - (this.unmasked_faults/this.sampleSize));

                long propagateTimeEnd = System.nanoTime();
                long propagateTime =    TimeUnit.NANOSECONDS.toSeconds(propagateTimeEnd - propagateTimeStart);
                long propagateTimems =  TimeUnit.NANOSECONDS.toMillis(propagateTimeEnd - propagateTimeStart);


                LocalDateTime myDateObj2 = LocalDateTime.now();
                DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate2 = myDateObj2.format(myFormatObj2);

                this.writeSimpleLog(option + "_MonteCarloXArea_Simple_Log_" +this.circuit.getName()+"_Threads-"+ this.threads +  "_sampleSize-" + this.sampleSize, formattedDate,  formattedDate2, propagateTime);

                this.writeCsvFileCompleteTh(option+"_MonteCarloXArea_Complete_Log_"+this.circuit.getName()+"_Theads-"+ this.threads + "_sampleSize"+ this.sampleSize, itemx_list);

                System.out.println("\n\n----------------- Results ------------------");
                System.out.println("Circuit: " + this.circuit.getName());
                System.out.println("- Simulation finished at: " + formattedDate2);
                System.out.println("- PropagatedTime (s): " + propagateTime);
                System.out.println("- Total Vectors (N): " + N);
                System.out.println("- Propagated fault(s) (Ne): " + this.unmasked_faults);
                System.out.println("- FMR: " + "(1-(" + this.unmasked_faults + "/" + N + ")) = " + this.circuitReliaibility);
                System.out.println("- MTBF (Mean Time Between failure) : " + this.MTBF);
                System.out.println("- Simulation TimeElapsed: " + propagateTime + " m(s)");
                System.out.println("--------------------------------------------");


                System.out.println(" ----------------------------------------------------------------------------------------------------------------------");
             /*
             */
     }

    public void runMultithreadingMonteCarlo(int sampleSize, String option) throws IOException, Exception{

                System.out.println(" ----- Monte Carlo version -------");
                long loadTimeStart = System.nanoTime();//System.currentTimeMillis();

                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);
                System.out.println("    - Simulation start in : " + formattedDate);
                System.out.println("    - Threads in execution: " + this.threads);

                /*Reading CellLibrary*/
                System.out.println("1");
                CellLibrary cellLib = new CellLibrary();
                System.out.println("2");
                this.cellLibrary = cellLib;
                System.out.println("3");
                this.cellLibrary.initLibrary(this.genlib);
                System.out.println("4");
                System.out.println("    ... Reading Genlib " + " at -> " + this.genlib  + " ... ok");
                System.out.println("  - Avaliable logic gatesin this library: "+cellLib.getCells());

                 System.out.println("    ... Reading verilog "+ " at -> " + this.circuitNameStr  + " ... ok");
                /*Reading verilog*/
                MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitNameStr, this.cellLibrary);
                this.verilog_circuit = verilog_circuit;
                /*Circuit linked to verilog_circuit - init circuit*/
                this.circuit = verilog_circuit.getCircuit();
                System.out.println("    ... Reading verilog "+ " at -> " + this.circuitNameStr  + " ... ok");
                //System.out.println("Patterns : " + this.verilog_circuit.getGatePattern());



                /* Print circuit Specs*/


                /*Circuit Probabilities */
                this.initLevelCircuit();

                /*Init ProbCircuits*/
                this.initProbCircuit();

                /*Init PTMs Const*/
                cellLib.setPTMCells2(Float.valueOf(this.reliabilityConst));
                cellLib.setPTMCells(new BigDecimal(this.reliabilityConst));

                long loadTimeEnd = System.nanoTime();//System.currentTimeMillis();
                long loadTime =   TimeUnit.NANOSECONDS.toMillis(loadTimeEnd - loadTimeStart);
                //System.out.println("- Load Time m(s): " + loadTime);

                 System.out.println("\n        ------ Printing Circuit Specs: --------");
                this.PrintSpecs();
                System.out.println("          ---------------------------------------\n");
                /*----------------------*/

                this.sampleSize = sampleSize; //(int) Math.pow(2, this.probCircuit.getInputs().size());  //(int) Math.pow(2, this.probCircuit.getInputs().size());
                int N = this.sampleSize; // random_input_vectors.size();//testNumber;

                System.out.println("-  (input) Sample size = " + this.sampleSize);

                this.signals_to_inject_faults = this.signalsToInjectFault(option); // Consider all signals to fault inject

                ArrayList <String> random_input_vectors = this.generateInputVector("RANDOM"); // Generate Random Input Vectors or InputTrueTable

                ArrayList <ArrayList<Integer>> ListInputVectors =  this.splitInputPatternsInInt(random_input_vectors, this.probCircuit.getInputs().size());

                List thread_list = this.particionateVectorPerThread(ListInputVectors); // x - vectors per thread

                long propagateTimeStart = System.nanoTime();

                /*Execução das threads*/
                Thread thread_temp = null;
                for (int i=0; i < thread_list.size() ; i++) {
                    thread_temp = (Thread) thread_list.get(i);
                    thread_temp.start();

                }
                /*Esperando termino das threads*/
                for (int i=0; i < thread_list.size() ; i++) {
                    thread_temp = (Thread) thread_list.get(i);
                    thread_temp.join();
                }

                /* Compilando os resultados - Falhas detectadas Ne*/
                for (int i=0; i < this.itemx_list.size() ; i++) {
                    this.unmasked_faults = this.unmasked_faults +  itemx_list.get(i).getPropagatedFaults();
                }

                 this.circuitReliaibility = (float) (1.0 - ((float) this.unmasked_faults / (float) this.sampleSize));

                /*circuit reliability SER (Soft Error Rate)*/
                //this.circuitReliaibility = (1 - (this.unmasked_faults/this.sampleSize));

                long propagateTimeEnd = System.nanoTime();
                long propagateTime =    TimeUnit.NANOSECONDS.toSeconds(propagateTimeEnd - propagateTimeStart);
                long propagateTimems =  TimeUnit.NANOSECONDS.toMillis(propagateTimeEnd - propagateTimeStart);


                LocalDateTime myDateObj2 = LocalDateTime.now();
                DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate2 = myDateObj2.format(myFormatObj2);


                this.writeSimpleLog(option + "_MonteCarlo_Simple_Log_" +this.circuit.getName()+"_Threads-"+ this.threads +  "_sampleSize-" + this.sampleSize, formattedDate,  formattedDate2, propagateTime);

                this.writeCsvFileCompleteTh(option+"_MonteCarlo_Complete_Log_"+this.circuit.getName()+"_Theads-"+ this.threads + "_sampleSize"+ this.sampleSize, itemx_list);


                System.out.println("\n\n----------------- Results ------------------");
                System.out.println("Circuit: " + this.circuit.getName());
                System.out.println("- Simulation started at: " + formattedDate + " and finished at: "+ formattedDate2); //formattedDate

               // System.out.println("- PropagatedTime (s): " + propagateTime);
                System.out.println("- Sample Size (N): " + N);
                System.out.println("- Propagated fault(s) (Ne): " + this.unmasked_faults);
                System.out.println("- Fault Mask Rate (FMR): " + " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + N + ")) = " + this.circuitReliaibility);
                //System.out.println("- MTBF (Mean Time Between failure) : " + this.MTBF);
                System.out.println("- Simulation TimeElapsed: " + propagateTime
                        + "(s)");

                System.out.println("--------------------------------------------");
                this.Performance_Time = "Simulation started at: " + formattedDate + " and finished at: " + formattedDate2;
                this.sampleSize = N;
                System.out.println(" ----------------------------------------------------------------------------------------------------------------------");
             /*

             */


     }

    public void runMultipleFaultInjectionMultithreadingMonteCarlo(int base, int order, int frequency, int sampleSize, String option) throws IOException, Exception{

        System.out.println(" ----- Monte Carlo version  for Multiple Transient Fault Injection -------");
        long loadTimeStart = System.nanoTime();//System.currentTimeMillis();

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("    - Simulation start in : " + formattedDate);
        System.out.println("    - Threads in execution: " + this.threads);

        /*Reading CellLibrary*/
        //System.out.println("1");
        CellLibrary cellLib = new CellLibrary();
        //System.out.println("2");
        this.cellLibrary = cellLib;
        //System.out.println("3");
        this.cellLibrary.initLibrary(this.genlib);
        //System.out.println("4");

        System.out.println("    ... Reading Genlib " + " at -> " + this.genlib  + " ... ok");
        //System.out.println("  - Avaliable logic gatesin this library: "+cellLib.getCells());

        System.out.println("    ... Reading verilog "+ " at -> " + this.circuitNameStr  + " ... ok");
        /*Reading verilog*/
        MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitNameStr, this.cellLibrary);
        this.verilog_circuit = verilog_circuit;
        /*Circuit linked to verilog_circuit - init circuit*/
        this.circuit = verilog_circuit.getCircuit();
        System.out.println("    ... Reading verilog "+ " at -> " + this.circuitNameStr  + " ... ok");
        //System.out.println("Patterns : " + this.verilog_circuit.getGatePattern());


        /*Circuit Probabilities */
        this.initLevelCircuit();

        /*Init ProbCircuits*/
        this.initProbCircuit();

        /*Init PTMs Const*/
        cellLib.setPTMCells2(Float.valueOf(this.reliabilityConst));
        cellLib.setPTMCells(new BigDecimal(this.reliabilityConst));

        long loadTimeEnd = System.nanoTime();//System.currentTimeMillis();
        long loadTime =   TimeUnit.NANOSECONDS.toMillis(loadTimeEnd - loadTimeStart);
        //System.out.println("- Load Time m(s): " + loadTime);

        System.out.println("\n        ------ Printing Circuit Specs: --------");
        this.PrintSpecs();
        System.out.println("          ---------------------------------------\n");
        /*----------------------*/

        this.sampleSize = sampleSize; //(int) Math.pow(2, this.probCircuit.getInputs().size());  //(int) Math.pow(2, this.probCircuit.getInputs().size());
        int N = this.sampleSize; // random_input_vectors.size();//testNumber;
        int period =  (int) Math.pow(base, order);
        System.out.println("-  (input) Sample size = " + this.sampleSize);

        this.signals_to_inject_faults = this.signalsToInjectFault(option); // Consider all signals to fault inject

        ArrayList <String> random_input_vectors = this.generateInputVector("RANDOM"); // Generate Random Input Vectors or InputTrueTable

        ArrayList <ArrayList<Integer>> ListInputVectors =  this.splitInputPatternsInInt(random_input_vectors, this.probCircuit.getInputs().size());

        List thread_list = this.particionateMultipletransientFaultInjectionVectorPerThread(ListInputVectors, period, order, frequency); // x - vectors per thread

        long propagateTimeStart = System.nanoTime();

        /*Execução das threads*/
        Thread thread_temp = null;
        for (int i=0; i < thread_list.size() ; i++) {
            thread_temp = (Thread) thread_list.get(i);
            thread_temp.start();

        }
        /*Esperando termino das threads*/
        for (int i=0; i < thread_list.size() ; i++) {
            thread_temp = (Thread) thread_list.get(i);
            thread_temp.join();
        }

        /* Compilando os resultados - Falhas detectadas Ne*/
        for (int i=0; i < this.itemx_list.size() ; i++) {
            this.unmasked_faults = this.unmasked_faults +  itemx_list.get(i).getPropagatedFaults();
        }

        this.circuitReliaibility = (float) (1.0 - ((float) this.unmasked_faults / (float) this.sampleSize));

        /*circuit reliability SER (Soft Error Rate)*/
        //this.circuitReliaibility = (1 - (this.unmasked_faults/this.sampleSize));

        long propagateTimeEnd = System.nanoTime();
        long propagateTime =    TimeUnit.NANOSECONDS.toSeconds(propagateTimeEnd - propagateTimeStart);
        long propagateTimems =  TimeUnit.NANOSECONDS.toMillis(propagateTimeEnd - propagateTimeStart);


        LocalDateTime myDateObj2 = LocalDateTime.now();
        DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate2 = myDateObj2.format(myFormatObj2);


        this.writeSimpleLogMTF(option + "_MULTIPLE_MonteCarlo_Simple_Log_" +this.circuit.getName()+"_Threads-"+ this.threads +  "_sampleSize-" + this.sampleSize, formattedDate,  formattedDate2, propagateTime, order, period, frequency);

        this.writeCsvFileCompleteThMTF(option+"_MULTIPLE_MonteCarlo_Complete_Log_"+this.circuit.getName()+"_Theads-"+ this.threads + "_sampleSize"+ this.sampleSize, itemx_list);


        System.out.println("\n\n----------------- Results ------------------");
        System.out.println("Circuit: " + this.circuit.getName());
        System.out.println("- Simulation started at: " + formattedDate + " and finished at: "+ formattedDate2); //formattedDate

        // System.out.println("- PropagatedTime (s): " + propagateTime);
        System.out.println("- Sample Size (N): " + N);
        System.out.println("- Propagated fault(s) (Ne): " + this.unmasked_faults);
        System.out.println("- Fault Mask Rate (FMR): " + " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + N + ")) = " + this.circuitReliaibility);
        //System.out.println("- MTBF (Mean Time Between failure) : " + this.MTBF);
        System.out.println("- Simulation TimeElapsed: " + propagateTime
                + "(s)");

        System.out.println("--------------------------------------------");
        this.Performance_Time = "Simulation started at: " + formattedDate + " and finished at: " + formattedDate2;
        this.sampleSize = N;
        System.out.println(" ----------------------------------------------------------------------------------------------------------------------");
        /*

         */


    }

    public void runMultipleFaultInjectionMultithreadingMonteCarloMODE(ArrayList <Integer> mtf_list, String option) throws IOException, Exception{

        System.out.println(" ----- Monte Carlo version  for Multiple Transient Fault Injection -------");
        long loadTimeStart = System.nanoTime();//System.currentTimeMillis();

        int sampleSize = mtf_list.get(0);

        System.out.println("        - SampleSize: " + sampleSize);

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("    - Simulation start in : " + formattedDate);
        System.out.println("    - Threads in execution: " + this.threads);

        /*Reading CellLibrary*/
        //System.out.println("1");
        CellLibrary cellLib = new CellLibrary();
        //System.out.println("2");
        this.cellLibrary = cellLib;
        //System.out.println("3");
        this.cellLibrary.initLibrary(this.genlib);
        //System.out.println("4");

        System.out.println("    ... Reading Genlib " + " at -> " + this.genlib  + " ... ok");
        //System.out.println("  - Avaliable logic gatesin this library: "+cellLib.getCells());

        System.out.println("    ... Reading verilog "+ " at -> " + this.circuitNameStr  + " ... ok");
        /*Reading verilog*/
        MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitNameStr, this.cellLibrary);
        this.verilog_circuit = verilog_circuit;
        /*Circuit linked to verilog_circuit - init circuit*/
        this.circuit = verilog_circuit.getCircuit();
        System.out.println("    ... Reading verilog "+ " at -> " + this.circuitNameStr  + " ... ok");
        //System.out.println("Patterns : " + this.verilog_circuit.getGatePattern());


        /*Circuit Probabilities */
        this.initLevelCircuit();

        /*Init ProbCircuits*/
        this.initProbCircuit();

        /*Init PTMs Const*/
        cellLib.setPTMCells2(Float.valueOf(this.reliabilityConst));
        cellLib.setPTMCells(new BigDecimal(this.reliabilityConst));

        long loadTimeEnd = System.nanoTime();//System.currentTimeMillis();
        long loadTime =   TimeUnit.NANOSECONDS.toMillis(loadTimeEnd - loadTimeStart);
        //System.out.println("- Load Time m(s): " + loadTime);

        System.out.println("\n        ------ Printing Circuit Specs: --------");
        this.PrintSpecs();
        System.out.println("          ---------------------------------------\n");
        /*----------------------*/

        this.sampleSize = sampleSize; //(int) Math.pow(2, this.probCircuit.getInputs().size());  //(int) Math.pow(2, this.probCircuit.getInputs().size());
        int N = this.sampleSize; // random_input_vectors.size();//testNumber;
        //int period =  (int) Math.pow(base, order);
        System.out.println("-  (input) Sample size = " + this.sampleSize);

        this.signals_to_inject_faults = this.signalsToInjectFault(option); // Consider all signals to fault inject

        ArrayList <String> random_input_vectors = this.generateInputVector("RANDOM"); // Generate Random Input Vectors or InputTrueTable

        ArrayList <ArrayList<Integer>> ListInputVectors =  this.splitInputPatternsInInt(random_input_vectors, this.probCircuit.getInputs().size());

        ArrayList <Integer> tt = new ArrayList<>(mtf_list);
        tt.remove(0);


        final ArrayList <Integer> arrayList_mtf_original = new ArrayList<>(tt); // Original ArrayList

        List thread_list = this.particionateMultipletransientFaultInjectionVectorPerThreadMODE(ListInputVectors, mtf_list); // x - vectors per thread

        long propagateTimeStart = System.nanoTime();

        /*Execução das threads*/
        Thread thread_temp = null;
        for (int i=0; i < thread_list.size() ; i++) {
            thread_temp = (Thread) thread_list.get(i);
            thread_temp.start();

        }
        /*Esperando termino das threads*/
        for (int i=0; i < thread_list.size() ; i++) {
            thread_temp = (Thread) thread_list.get(i);
            thread_temp.join();
        }

        /* Compilando os resultados - Falhas detectadas Ne*/
        for (int i=0; i < this.itemx_list.size() ; i++) {
            this.unmasked_faults = this.unmasked_faults +  itemx_list.get(i).getPropagatedFaults();
        }

        this.circuitReliaibility = (float) (1.0 - ((float) this.unmasked_faults / (float) this.sampleSize));

        /*circuit reliability SER (Soft Error Rate)*/
        //this.circuitReliaibility = (1 - (this.unmasked_faults/this.sampleSize));

        long propagateTimeEnd = System.nanoTime();
        long propagateTime =    TimeUnit.NANOSECONDS.toSeconds(propagateTimeEnd - propagateTimeStart);
        long propagateTimems =  TimeUnit.NANOSECONDS.toMillis(propagateTimeEnd - propagateTimeStart);


        LocalDateTime myDateObj2 = LocalDateTime.now();
        DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate2 = myDateObj2.format(myFormatObj2);


        this.writeSimpleLogMTFMODE(option + "_MULTIPLE_MonteCarlo_Simple_Log_" +this.circuit.getName()+"_Threads-"+ this.threads +  "_sampleSize-" + this.sampleSize, formattedDate,  formattedDate2, propagateTime, arrayList_mtf_original
        );

        //
         this.writeCsvFileCompleteThMTF(option+"_MULTIPLE_MonteCarlo_Complete_Log_"+this.circuit.getName()+"_Theads-"+ this.threads + "_sampleSize"+ this.sampleSize, itemx_list);


        System.out.println("\n\n----------------- Results ------------------");
        System.out.println("Circuit: " + this.circuit.getName());
        System.out.println("- Simulation started at: " + formattedDate + " and finished at: "+ formattedDate2); //formattedDate

        // System.out.println("- PropagatedTime (s): " + propagateTime);
        System.out.println("- Sample Size (N): " + N);
        System.out.println("- Propagated fault(s) (Ne): " + this.unmasked_faults);
        System.out.println("- Fault Mask Rate (FMR): " + " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + N + ")) = " + this.circuitReliaibility);
        //System.out.println("- MTBF (Mean Time Between failure) : " + this.MTBF);
        System.out.println("- Simulation TimeElapsed: " + propagateTime
                + "(s)");

        System.out.println("--------------------------------------------");
        this.Performance_Time = "Simulation started at: " + formattedDate + " and finished at: " + formattedDate2;
        this.sampleSize = N;
        System.out.println(" ----------------------------------------------------------------------------------------------------------------------");
        /*

         */


    }


    public String getFRM(String identification){

        //float FMR, int sample, int unmasked_faults, long propagatedTime
        String result;

        result = "\n";
        result = result + "         " + this.Performance_Time + "\n";
        result = result + "         Circuit: " + this.circuit.getName() + " \n";  //+ " P(I): " + this.circuit.getInputs().size() + " Gates: " + this.circuit.getGates().size() +
        result = result + "         Signals: " + this.circuit.getSignals().size() + " - Gates: " + this.circuit.getGates().size() + " \n";
        result = result + "         Simulation " + identification + " : " + this.sampleSize + "\n";
        result = result + "         Detected Faults (Ne): " + this.unmasked_faults + "\n";
        result = result + "         Fault Mask Rate (FMR): " + this.circuitReliaibility + "\n";
        return result;
    }

    public String PrintCircuitSpecs() throws IOException, Exception{


                //System.out.println(" ----- Monte Carlo version -------");
                long loadTimeStart = System.nanoTime();//System.currentTimeMillis();

                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);
                //System.out.println("    - Simulation start in : " + formattedDate);
                //System.out.println("    - Threads in execution: " + this.threads);

                /*Reading CellLibrary*/
                CellLibrary cellLib = new CellLibrary();
                this.cellLibrary = cellLib;
                this.cellLibrary.initLibrary(this.genlib);
                //System.out.println("    ... Reading Genlib " + " at -> " + this.genlib  + " ... ok");
                //System.out.println("  - Avaliable logic gatesin this library: "+cellLib.getCells());


                /*Reading verilog*/
                MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitNameStr, this.cellLibrary);
                this.verilog_circuit = verilog_circuit;
                /*Circuit linked to verilog_circuit - init circuit*/
                this.circuit = verilog_circuit.getCircuit();
                //System.out.println("    ... Reading verilog "+ " at -> " + this.circuitNameStr  + " ... ok");
                //System.out.println("Patterns : " + this.verilog_circuit.getGatePattern());

                 /*Circuit Probabilities */
                this.initLevelCircuit();

                /*Init ProbCircuits*/
                this.initProbCircuit();

                /*Init PTMs Const*/
                cellLib.setPTMCells2(Float.valueOf(this.reliabilityConst));
                cellLib.setPTMCells(new BigDecimal(this.reliabilityConst));


                /* Print circuit Specs*/
                System.out.println("\n        ------ Printing Circuit Specs: --------");
                //String str = this.PrintSpecs();

                String str =  this.PrintGatesCounter(); //this.PrintTransistorsNumber();

                System.out.println("          ---------------------------------------\n");
                /*----------------------*/

                return str;

                //System.out.println(" ----------------------------------------------------------------------------------------------------------------------");
             /*
             */
     }

}
