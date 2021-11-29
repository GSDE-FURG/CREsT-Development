/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicSimulator;

import com.opencsv.CSVReader;
import datastructures.CellLibrary;
import datastructures.Circuit;
import datastructures.Gate;
import datastructures.Signal;

import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.script.ScriptException;

import jxl.write.WriteException;
import levelDatastructures.LevelCircuit;
import readers.MappedVerilogReader;
import signalProbability.ProbCircuit;
import writers.WriteCsvTh;

/**
 *  @author Clayton Farias
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
    private int sizeExaustiveCompleteSimulation;

    private ArrayList <Signal> signals_to_inject_faults = new ArrayList<>();
    private final ArrayList <String> inputListValues = new ArrayList<>();
    private final ArrayList <ArrayList<String>> inputListValuesStr = new ArrayList<>();
    private final ArrayList <ArrayList<String>> outputFreeFaultListValueStr = new ArrayList<>();
    private final ArrayList <ArrayList<String>> outputListValueStr = new ArrayList<>();
    private final ArrayList <ArrayList<String>> SignalFault = new ArrayList<>();
    private final ArrayList <ArrayList<String>> SignalFaultBitFlip = new ArrayList<>();

    /**
     *
     * @param threads
     * @param reliabilityConst
     * @param relativePath
     * @param genlib
     * @param circuitName
     * @throws IOException
     */
    public Orchestrator(int threads, String reliabilityConst, String relativePath, String genlib, String circuitName) throws IOException {
            super(threads, reliabilityConst, relativePath, genlib);

             this.cellLibrary = new CellLibrary();
             this.circuitNameStr = circuitName;
    }

    /**
     *
     * @param option
     * @return
     */
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
               System.out.println("     --> Considering all signals (input, intermediate, output): "+r.size());
              return r;


         case "":
             System.out.println("Error !!! Please insert the correct Fault Signals String");
             break;
        }
        return null;

    }

    /**
     *
     */
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
         System.out.println("               - Inputs : " + this.circuit.getInputs().size()) ;// + " - " +this.circuit.getInputs());
         //System.out.println("               - Outputs : " + this.circuit.getOutputs().size() + " - " + " - " +this.circuit.getOutputs());
         System.out.println("               - Signals : " + this.circuit.getSignals().size());
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

        // System.out.println("- Lista completa: ");
         int c = 0;
         String result = "\n" + this.circuit.getName() + ";" + this.circuit.getGates().size() +  "\n";


         for(gate_counter item: temp){
             //System.out.println(item.get_gate_type() + " " + item.get_gate_counter());
             //result = result + item.get_gate_type() + ";" + item.get_gate_counter() + "\n";

             result = result +item.get_gate_counter() + "\n";
             c = c + item.get_gate_counter();
         }

         if(c == this.circuit.getGates().size()){
           //  System.out.println("OK NUMERO DE GATES CORRETO: " + c);
         }else{
             System.out.println("!!!! Erro número de gates divergente: c:" + c + "  circ: " + this.circuit.getGates().size());
         }

         return Integer.toString(c);

     }

    public String PrintGatesCounterDetails(){
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

        //System.out.println("- Lista completa: ");
        int c = 0;
        String result = "\n" + this.circuit.getName() + ";" + this.circuit.getGates().size() +  "\n";


        for(gate_counter item: temp){
            //System.out.println(item.get_gate_type() + " " + item.get_gate_counter());
            //result = result + item.get_gate_type() + ";" + item.get_gate_counter() + "\n";

            result = result +item.get_gate_counter() + "\n";
            c = c + item.get_gate_counter();
        }

        if(c == this.circuit.getGates().size()){
           // System.out.println("OK NUMERO DE GATES CORRETO: " + c);
        }else{
            System.out.println("!!!! Erro número de gates divergente: c:" + c + "  circ: " + this.circuit.getGates().size());
        }

        return result;

    }

    public String PrintGatesCounterDetailsSorted(){
        System.out.println("          ~~~~~~ Circuit Name : " + this.circuit.getName());
        //System.out.println("- Logic Gates : " + this.circuit.getGates());
        System.out.println("               - Logic Gates (size): " + this.circuit.getGates().size() );
        ///System.out.println("               - Levels (size): " + this.levelCircuit.getGateLevels().size());

        ArrayList <gate_counter> temp = new ArrayList<>();

        temp.add(new gate_counter("AOI21", 0));
        temp.add(new gate_counter("AOI211", 0));
        temp.add(new gate_counter("AOI22", 0));
        temp.add(new gate_counter("AOI221", 0));
        temp.add(new gate_counter("AOI222", 0));

        temp.add(new gate_counter("BUF", 0));
        temp.add(new gate_counter("INV", 0));

        temp.add(new gate_counter("NAND2", 0));
        temp.add(new gate_counter("NAND3", 0));
        temp.add(new gate_counter("NAND4", 0));

        temp.add(new gate_counter("NOR2", 0));
        temp.add(new gate_counter("NOR3", 0));
        temp.add(new gate_counter("NOR4", 0));

        temp.add(new gate_counter("OAI21", 0));
        temp.add(new gate_counter("OAI211", 0));
        temp.add(new gate_counter("OAI22", 0));
        temp.add(new gate_counter("OAI221", 0));
        temp.add(new gate_counter("OAI222", 0));

        temp.add(new gate_counter("ONE", 0));

        temp.add(new gate_counter("XOR2", 0));
        temp.add(new gate_counter("ZERO", 0));



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

        //System.out.println("- Lista completa: ");
        int c = 0;
        String result = "\n" + this.circuit.getName() + ";" + this.circuit.getGates().size() +  "\n";


        for(gate_counter item: temp){
            System.out.println(item.get_gate_type() + " " + item.get_gate_counter());

            result = result + item.get_gate_type() + ";" + item.get_gate_counter() + "\n";

            //result = result +item.get_gate_counter() + "\n";
            c = c + item.get_gate_counter();
        }

        if(c == this.circuit.getGates().size()){
            //System.out.println("OK NUMERO DE GATES CORRETO: " + c);
        }else{
            System.out.println("!!!! Erro número de gates divergente: c:" + c + "  circ: " + this.circuit.getGates().size());
        }

        return result;

    }

    public String PrintGatesCounterDetailsSortedCompliled(int id, ArrayList <String> files, String filter){
        //System.out.println("    - Circuit Name : " + this.circuit.getName());
        //System.out.println("- Logic Gates : " + this.circuit.getGates());
        //System.out.println("    - Logic Gates (size): " + this.circuit.getGates().size() );
        ///System.out.println("               - Levels (size): " + this.levelCircuit.getGateLevels().size());

        ArrayList <gate_counter> temp = new ArrayList<>();

        temp.add(new gate_counter("AOI21", 0));
        temp.add(new gate_counter("AOI211", 0));
        temp.add(new gate_counter("AOI22", 0));
        temp.add(new gate_counter("AOI221", 0));
        temp.add(new gate_counter("AOI222", 0));

        temp.add(new gate_counter("BUF", 0));
        temp.add(new gate_counter("INV", 0));

        temp.add(new gate_counter("NAND2", 0));
        temp.add(new gate_counter("NAND3", 0));
        temp.add(new gate_counter("NAND4", 0));

        temp.add(new gate_counter("NOR2", 0));
        temp.add(new gate_counter("NOR3", 0));
        temp.add(new gate_counter("NOR4", 0));

        temp.add(new gate_counter("OAI21", 0));
        temp.add(new gate_counter("OAI211", 0));
        temp.add(new gate_counter("OAI22", 0));
        temp.add(new gate_counter("OAI221", 0));
        temp.add(new gate_counter("OAI222", 0));

        temp.add(new gate_counter("ONE", 0));

        temp.add(new gate_counter("XOR2", 0));
        temp.add(new gate_counter("ZERO", 0));

        /*
        ArrayList <String> files = new ArrayList<>();
        files.add("alu4");
                files.add("vda");
                        files.add(    "frg2");
                                files.add(      "t481");
                                        files.add(      "dalu");
                                                files.add(     "k2");
                                                        files.add(      "pair");
                                                                files.add(      "i8");
                                                                        files.add(     "i10");
                                                                                files.add(     "des");
        */

        String f = this.circuit.getName().replace("_"+filter, "");
       // f = f.replace("")
       System.out.println("             CIRC: "+this.circuitNameStr + "  " + this.circuit.getName() + " string: " + f);
       if(files.contains(f) || (id == 0)) {
           //System.out.println("FINDED: " + this.circuit.getName());
           for (Gate i : this.circuit.getGates()) {
               //System.out.println("-" + i.getType());
            /* if(searchGateInList(i.getType().toString(), temp) == false){ // Adicionar a lista
                 gate_counter novo_gate = new gate_counter(i.getType().toString(), 0);
                 temp.add(novo_gate);
             }
             */
               if (searchGateInList(i.getType().toString(), temp) == false) {
                   System.err.println("ERROR comparing number of gates !!!!");
               }
           }

           //System.out.println("- Lista completa: ");
           int c = 0;
           String result = this.circuit.getName(); //+ this.circuit.getName() + ";" + this.circuit.getGates().size() +  "\n";
           String gates = "";
           if (id == 0) {
               for (gate_counter item : temp) {
                   gates = gates + "; " + item.get_gate_type();
               }
               gates = gates + "\n";
           }

           for (gate_counter item : temp) {
               //System.out.println(item.get_gate_type() + " " + item.get_gate_counter());
               result = result + ";" + item.get_gate_counter();
               //result = result +item.get_gate_counter() + "\n";
               c = c + item.get_gate_counter();
           }

           result = gates + result + "\n";

           if (c == this.circuit.getGates().size()) {
               //System.out.println("OK NUMERO DE GATES CORRETO: " + c);

           } else {
               System.err.println("!!!! Erro número de gates divergente: c:" + c + "  circ: " + this.circuit.getGates().size());
               return "";
           }
           return result;
       }
        return "";

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

                        //Collections Sorted

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

    public void writeSimpleLogMultipleTransientFault(String filename, String date, String dateend, long timeElapsedSimulation, ArrayList <Integer> mtf_list) throws IOException{

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

            content = content +  "Performance time m(s): " + (timeElapsedSimulation) + "\n";


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

    public void writeSimpleLogMultipleTransientFaultProportion(String filename, String date, String dateend, long timeElapsedSimulation, ArrayList <Float> mtf_list) throws IOException{

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

            content = content +  "Performance time m(s): " + (timeElapsedSimulation) + "\n";


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

    public void writeSimpleLogMultipleTransientFaultExaustive(String filename, String date, String dateend, long timeElapsedSimulation) throws IOException{

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

            // for (int i = 0; i < mtf_list.size(); i++) {
            //     content = content +  "      - For each  (" + mtf_list.get(i) + ") faults happens a MTF of order (" + (i+2) + ")\n" ;
            // }
            content = content + "- Sample = " + this.sampleSize + "\n";
            //content = content+  "For each  (" + period + ") faults happens a Multiple Transient Fault (" + order + ") with frequency: (" + frequency + ") - Sample = " + this.sampleSize + "\n";
            content = content+  "Number of Threads: " + this.threads + "\n";
            content = content + "Number of detected faults (Ne): " + this.unmasked_faults + "\n";
            content = content + "Fault Mask Rate (FMR): "+ " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + this.sampleSize + ")) = " + this.circuitReliaibility + "\n";
            //content = content +  "MTBF: " + this.MTBF + "\n\n";

            content = content +  "Performance time m(s): " + (timeElapsedSimulation) + "\n";


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
                    if((map.get(i) == value) && (map.get(i) != 0)){
                        //if(map.get(i) == value){
                        /*


                         */
                        pos = i;
                        int new_value =  map_original.get(i) + map.get(i) + 1

                                ;
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

    public float sumProportionPercentage(ArrayList<Float> mtf_list){

        float sum_proportion = 0;


        for (int percentage = 1; percentage < mtf_list.size(); percentage++) { // Check if it higher then 100% (1.0)
            sum_proportion = sum_proportion + mtf_list.get(percentage);
        }
        return sum_proportion;
    }

    public ArrayList <Integer> passProportionPercentage(ArrayList<Float> mtf_list, int sample){

        float sum_proportion = sumProportionPercentage(mtf_list);
        ArrayList <Integer> m = new ArrayList<>();
        float sum_up = 0;

        if(sum_proportion == 1.0){
            for (int pos = 1; pos < mtf_list.size(); pos++) {
                    m.add((int) Math.round(sample * mtf_list.get(pos)));
            }
        }
        if(sum_proportion < 1){
            //need complete in this case
            float dif = 1 - sum_proportion;
            //int dif = 1 - Math.round(sum_proportion);

            for (int pos = 1; pos < mtf_list.size(); pos++) {
                m.add(Math.round(sample * mtf_list.get(pos)));
                sum_up = sum_up + (sample * mtf_list.get(pos));
            }

            //while (sum_up < mtf_list.get(0)){  // In case disparity in sum up rounding
            //    sum_up++;
            //}

            int sum = Math.round(sum_up);
            int least_to_complete = sample - sum;
            float temp_sample = Math.round(mtf_list.get(0));
            mtf_list.remove(0);

            System.out.println("sum up: " + sum_up);

            if(sum_up <= temp_sample) { // 20k == 20k

                System.err.println("- Warning:" + " based on your inputs: " + mtf_list + " the sum should be 1 (" + sum_proportion + "), although " +
                        " in this case the procedure will complete this number of tests (" + least_to_complete + ") because the diference was (1 - " + sum_proportion + " ) = " + dif + " * " + temp_sample + " = " + least_to_complete);

                System.out.println("- temp arraylist mtf orriginal " + m);
                //Insert in simple faults
                m.set(0, m.get(0) + least_to_complete);
                System.out.println("- temp arraylist mtf " + m);

                //Insert in medium faults
                //m.set(m.size()/2, m.get(0) + least_to_complete);
                //System.out.println("m " + m);

                //Insert in Higher order faults
                //m.set(m.size(), m.get(0) + least_to_complete);
                //System.out.println("m " + m);
            }
        }else{
            System.out.println("Proportion sum up is : " + sum_proportion);
        }

        System.out.println("- MTF LIST: " + m);

        return m;
    }

    /**
     *
     *
     * @param ListInputVectors
     * @param mtf_list
     * @return
     * @throws ScriptException
     * @throws Exception
     */
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


        if(mtf_list.size() > 0){
            mtf_list.remove(0);
        }else{

            System.out.println("pass");
        }






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
                //if((arraylist_mtf.containsKey(j)) && (arraylist_mtf.get(j).getOrder(
                // ) > 1)){
                //int index = getPosMap(arraylist_mtf, j);
                //System.out.println("J: " + j);


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

                   //

                    //System.out.println(inputVector + "  Founded key : " + j + "  list: " + mtf_list +  "   " + arrayList_mtf_original + " fault list " + temp.get_MTF_FaultSignal_List() + "  order: "  + order  );


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
               System.out.println("  Founded key : " + this.sampleSize + "  list: " + mtf_list +  "   " + arrayList_mtf_original);


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

    public List particionateMultipletransientFaultInjectionVectorPerThreadProportion(ArrayList <ArrayList<Integer>> ListInputVectors, ArrayList <Float> mtf_list) throws ScriptException, Exception{

        System.out.println("\n\n         +++++++    Dev mode Percentage (Proportion mode)  ++++++");
        System.out.println("                     -   MTF LIST = " + mtf_list);

        List thread_list = new ArrayList();
        int count = 0;
        float sum_proportion;
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



        //HashMap  <Integer, multiple_faults_object> arraylist_mtf = new HashMap<>();
        sum_proportion = sumProportionPercentage(mtf_list);
        int sample_base = Math.round(mtf_list.get(0));
        ArrayList <Integer> new_MTF = passProportionPercentage(mtf_list, sample_base);
        System.out.println("- new MTF LIST " + new_MTF + "  size: " + new_MTF.size());

        final ArrayList<Float> arrayList_mtf_original = new ArrayList<>(mtf_list); // Original ArrayList

        ArrayList <TestVectorInformation> listOflist = new ArrayList<>();

        ArrayList <Integer> combined_MTF = new ArrayList<>();
        combined_MTF.add(new_MTF.get(0));
        int base_sum = new_MTF.get(0);

        //Loop for Single, Double or tripple
        int start = 0;
        int end = partition;

        //System.out.println("");
        ArrayList<TestVectorInformation> ItemxSimulationList = new ArrayList<>();

        for (int prop_index = 0; prop_index < new_MTF.size(); prop_index++) {
            System.out.println(new_MTF.get(prop_index));

            if(prop_index >0){
                base_sum = base_sum + new_MTF.get(prop_index);
                combined_MTF.add(base_sum);
            }

            for (int index = 0; index < new_MTF.get(prop_index); index++){
                // For prop_index

                ArrayList<Integer> inputVector = new ArrayList<>();

                inputVector = this.get_Input_Vectors(ListInputVectors, count); //input Test n

                if (prop_index == 0){ // Single Transient Fault
                    int SigIndex = this.sortRandomFaultInjection(); //int SigIndex = decide_Random_Signals_Contrains(Signals_CTE_ONE_ZERO);
                    TestVectorInformation temp = new TestVectorInformation(inputVector, this.signals_to_inject_faults.get(SigIndex), count );
                    ArrayList <Integer> SigIndexList = new ArrayList<Integer>();
                    SigIndexList.add(SigIndex);
                    ItemxSimulationList.add(temp);
                   /// System.out.println("~ Injection Single TF number : " + count + " - Sig Index" + SigIndexList + "  temp: "+ temp.getMTFPERSONAL_LIST_Identities() + "  " +  temp.getSimulationIndex());
                }
                else{ // Double , Tripple
                    int SigIndex = this.sortRandomFaultInjection(); //int SigIndex = decide_Random_Signals_Contrains(Signals_CTE_ONE_ZERO);
                    TestVectorInformation temp = new TestVectorInformation(inputVector, this.signals_to_inject_faults.get(SigIndex), count );
                    ArrayList <Integer> SigIndexList = new ArrayList<Integer>();
                    SigIndexList.add(SigIndex);
                    for (int k = 1; k <= prop_index; k ++){
                        int new_pos = sortExclusiveFaultIndex(SigIndexList, temp);
                        temp.setMultipleTransientFaultInjection( this.signals_to_inject_faults.get(new_pos));
                        SigIndexList.add(new_pos); // Do no reapet signals
                    }
                    //System.out.println("~ Injection MTF number : " + count + " - Sig Index" + SigIndexList + "  temp: "+ temp.getMTFPERSONAL_LIST_Identities()  + "  " +  temp.getSimulationIndex());
                    ItemxSimulationList.add(temp);
                }
                count++;
            }
            System.out.println(prop_index + " Count: " + count + " ItemxSimulation: " + ItemxSimulationList.size() + "  order: ");
        }

        System.out.println("- End Count: " + count);
        System.out.println("- Combined: " + combined_MTF);
        System.out.println("- Itemx Size: " + ItemxSimulationList.size());
        System.out.println("- ThreadList: " + thread_list.size());

        start = 0;
        end = partition;
        for (int i = 0; i < this.threads; i++) { //Loop of simulations
            
            System.out.println("Start: " + start + " End: " + end);
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

            ArrayList<TestVectorInformation> temp = new ArrayList<TestVectorInformation>(ItemxSimulationList.subList(start, end));
            LogicSimulator threadItem = new LogicSimulator(temp, this.circuit, this.cellLibrary, this.levelCircuit, start, end, this.genlib , this.circuitNameStr); // Thread contex info

            threadItem.setMode("Multiple");
            itemx_list.add(threadItem);

            Runnable runnable = threadItem;
            Thread thread = new Thread(runnable);
            thread.setName(Integer.toString(threadItem.hashCode()));
            thread_list.add(thread);
            System.out.println("\n x-x-x ->>>>>> " + i + " Start: " + start + " End: " + end + "  ThreadItem: " + threadItem.getStartendPos() + " size inputss: " + threadItem.getThreadSimulatinArray().size() + " real: " + temp.size());

        }
        //System.out.println("Signal to inject fault: " + this.signals_to_inject_faults);
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

                        for (int j = start; j < end ; j++){

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

    public List particionateExausticVectorForSensitiveAreas(ArrayList <ArrayList<Integer>> ListInputVectors,  Map <String, SensitiveCell> sensitive_cells) throws ScriptException, Exception{

        System.out.println("- Sensitive Area inputs ...");

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

            for (int j = start; j < end ; j++){

                for (int aux = 0; aux < 1; aux++) {

                    inputVector = this.get_Input_Vectors(ListInputVectors, j); //input Test n

                    int SigIndex = aux;//this.sortRandomFaultInjection(); //int SigIndex = decide_Random_Signals_Contrains(Signals_CTE_ONE_ZERO);

                    TestVectorInformation temp = new TestVectorInformation(inputVector, this.signals_to_inject_faults.get(SigIndex), j+1);
                    ItemxSimulationList.add(temp);

                    System.out.println(j + " Vec: " + inputVector + " Fault Signal: " +  this.signals_to_inject_faults.get(SigIndex));
                }

            }

            LogicSimulator threadItem = new LogicSimulator(ItemxSimulationList, this.circuit, this.cellLibrary, this.levelCircuit, start, end, this.genlib , this.circuitNameStr); // Thread contex info
            threadItem.setMode("Sensitive_Area");
            threadItem.setSensitiveCellsMap(sensitive_cells);
            itemx_list.add(threadItem);

            Runnable runnable = threadItem;
            Thread thread = new Thread(runnable);
            thread.setName(Integer.toString(threadItem.hashCode()));
            thread_list.add(thread);

        }

        return thread_list;

    }

    public List parseVerilogToSpiceNetList() throws ScriptException, Exception{

        System.out.println("- Parsing Verilog mapped to Spice Netlist ");

        ArrayList <Integer> inputVector = new ArrayList<>();
        inputVector.add(0);

        ArrayList<TestVectorInformation> ItemxSimulationList = new ArrayList<>();
        List thread_list = new ArrayList();

            TestVectorInformation temp = new TestVectorInformation(inputVector, this.signals_to_inject_faults.get(0), 0);
            ItemxSimulationList.add(temp);

            LogicSimulator threadItem = new LogicSimulator(ItemxSimulationList, this.circuit, this.cellLibrary, this.levelCircuit, 0, 0, this.genlib , this.circuitNameStr); // Thread contex info
            threadItem.setMode("Generate_Netlist");
            //threadItem.setSensitiveCellsMap(sensitive_cells);
            itemx_list.add(threadItem);

            Runnable runnable = threadItem;
            Thread thread = new Thread(runnable);
            thread.setName(Integer.toString(threadItem.hashCode()));
            thread_list.add(thread);



        return thread_list;

    }




    public long factorialUsingRecursion(int n) {
        if (n <= 2) {
            return n;
        }
        return n * factorialUsingRecursion(n - 1);
    }

    public long combination(int n, int p){
        int combination = (int) (factorialUsingRecursion(n)/ ((factorialUsingRecursion(p) * (factorialUsingRecursion(n-p)))));
        return combination;
    }

    private static void helper(List<int[]> combinations, int data[], int start, int end, int index) {
        if (index == data.length) {
            int[] combination = data.clone();
            combinations.add(combination);
        } else if (start <= end) {
            data[index] = start;
            helper(combinations, data, start + 1, end, index + 1);
            helper(combinations, data, start + 1, end, index);
        }
    }

    public static List<int[]> generate(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
        helper(combinations, new int[r], 0, n-1, 0);
        return combinations;
    }

    public List particionateExausticVectorComplete(ArrayList <ArrayList<Integer>> ListInputVectors) throws ScriptException, Exception{

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



        int vec = (int) Math.pow(2, this.circuit.getInputs().size());
        long result_computation = 0;
        for (int i = 1; i < this.signals_to_inject_faults.size(); i++) {
            ///int combination = (int) (factorialUsingRecursion(this.circuit.getSignals().size())/ ((factorialUsingRecursion(this.signals_to_inject_faults.size() - i)) * (factorialUsingRecursion(i))));
            long comb = combination(this.circuit.getSignals().size(), i);

            result_computation = result_computation + (comb);
            System.out.println("conjunto  " + this.circuit.getSignals().size() + "," + i  + " : " + comb + "  testComplexity = " + comb + " = " + (result_computation)) ;
        }
            this.sizeExaustiveCompleteSimulation = (int) result_computation * vec;
        /*
        for (int i = 0; i < this.signals_to_inject_faults.size(); i++) {
            List<int[]> combinations = generate(this.signals_to_inject_faults.size(), i);
            for (int[] combination : combinations) {
               // System.out.println(Arrays.toString(combination));
            }
            //System.out.printf("generated %d combinations of %d items from %d ", combinations.size(), this.signals_to_inject_faults.size(), i);
        }
        */

        //this.sizeExaustiveCompleteSimulation = 0;
        int counter = 0;
        int counterv2 = 0;

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


            System.out.println("SIGNALS LIST: " + this.signals_to_inject_faults);



            for (int j = start; j < end ; j++){

                inputVector = this.get_Input_Vectors(ListInputVectors, j);  // Vetores [0000] [00001]
                    for (int p = 1; p < this.signals_to_inject_faults.size(); p++) { // 1 , 2, 3, 4

                        String nxp = "  p: " + p + "  vec: "+ inputVector + "  vv2: " + counterv2 + "  j: "+ j;


    //[20000 100 200 400]

                        //System.out.println(nxp);
                        //int SigIndex = p-1; // G1, G2 , G3

                        List<int[]> combinations = generate(this.signals_to_inject_faults.size(), p); // Combination of 11 and 1 = 11 ~ 11 and 2 = 55 ~ 11and 3

                        for (int[] combination : combinations){ //
                            TestVectorInformation temp = new TestVectorInformation(inputVector, this.signals_to_inject_faults.get(combination[0
                                    ]), counterv2);  //Inject in G1 first

                            // this.sizeExaustiveCompleteSimulation++;
                            //String pivot = this.signals_to_inject_faults.get(combination[0]).toString();

                            for (int element = 1; element < combination.length; element++) {
                                ArrayList<Integer> SigIndexList = new ArrayList<Integer>();
                                //SigIndexList.add(combination[element]);
                                temp.setMultipleTransientFaultInjection(this.signals_to_inject_faults.get(combination[element]));
                                SigIndexList.add(combination[element]);
                                //String pivoCerto = this.signals_to_inject_faults.get(combination[0]).toString();

                                /*
                                if(j ==0) {
                                    System.out.println(inputVector +
                                            "  Sig Index List: " + SigIndexList +
                                            "  Comb: " + Arrays.toString(combination) +
                                            "  Pivot Atual:  " + pivot +
                                            "  Pivot Certo: " + pivoCerto
                                            + " combEl: " + combination[element]);
                                }
                                */

                            }
                            counterv2++;

                            counter++;
 //                           if(j == 0)
//                               System.out.println(ill + " ill " + "Vec: " + inputVector + " comb: " + Arrays.toString(combination) + " Fault Signal: " +  this.signals_to_inject_faults.get(SigIndex) + " list: "+ "" + temp.get_MTF_FaultSignal_List() + " counter: " + counter);

                            ItemxSimulationList.add(temp);
                        }
                       // counterv2++;
                   // }
                }
                /*
                for (int aux = 0; aux < this.signals_to_inject_faults.size(); aux++) {

                    inputVector = this.get_Input_Vectors(ListInputVectors, j); //input Test n

                    int SigIndex = aux;//this.sortRandomFaultInjection(); //int SigIndex = decide_Random_Signals_Contrains(Signals_CTE_ONE_ZERO);

                    TestVectorInformation temp = new TestVectorInformation(inputVector, this.signals_to_inject_faults.get(SigIndex), j+1);
                    ItemxSimulationList.add(temp);

                    System.out.println("Vec: " + inputVector + " Fault Signal: " +  this.signals_to_inject_faults.get(SigIndex));
                }
                */


            }


            LogicSimulator threadItem = new LogicSimulator(ItemxSimulationList, this.circuit, this.cellLibrary, this.levelCircuit, start, end, this.genlib , this.circuitNameStr); // Thread contex info
            threadItem.setMode("Multiple");
            itemx_list.add(threadItem);

            Runnable runnable = threadItem;
            Thread thread = new Thread(runnable);
            thread.setName(Integer.toString(threadItem.hashCode()));
            thread_list.add(thread);


            System.out.println(i + " Thread - Size simulation" + " " + this.sizeExaustiveCompleteSimulation + "    computation: " + result_computation + "   - SimulatlistionSize: "+itemx_list.get(i).getThreadSimulatinArray().size() + "   counter: " +counter);
        }
        this.sizeExaustiveCompleteSimulation = (int) counter;

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

    public String estimateMultithreadingExausticSimulationSize_AND_PRINT_INPUTS_SIGNALS_GATES(String option) throws IOException, Exception{ //Test All possibilities


        //System.out.println(" ----- Estimate Exaustive Simulation Size -------");
        long loadTimeStart = System.nanoTime();//System.currentTimeMillis();


        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);


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



        /* Print circuit Specs*/
       // System.out.println("\n        ------ Printing Circuit Specs: --------");
         // this.PrintSpecsThesis();
       // this.PrintSpecs();
       // System.out.println("          ---------------------------------------\n");

        int sizeExasuticTest;

        //System.out.println("-   Sample size (N = 2^ENTRADAS): " + "2^"+ this.circuit.getInputs().size() + " = " + this.sampleSize);

        this.signals_to_inject_faults = this.signalsToInjectFault(option); // Consider all signals to fault inject

        //sizeExasuticTest = (this.sampleSize * this.signals_to_inject_faults.size());

        //ArrayList <String> random_input_vectors =  this.generateInputVector("TRUE_TABLE"); //this.calcInputTableVector(this.probCircuit.getInputs().size(), this.sampleSize);

        //System.out.println("SIZE: " + sizeExasuticTest)

        //ArrayList <ArrayList<Integer>> ListInputVectors =  this.splitInputPatternsInInt(random_input_vectors, this.probCircuit.getInputs().size());

        //System.out.println("LIST:::::: "+ ListInputVectors);

        //List thread_list = particionateExausticVector(ListInputVectors);  // TESTE ALL GATES ///particionateVectorPerThread(ListInputVectors); // x - vectors per thread

        //long propagateTimeStart = System.nanoTime();

        String str = this.circuit.getName() + ";" + this.circuit.getInputs().size() + ";" + this.circuit.getSignals().size() + ";" + this.PrintGatesCounter(); //this.PrintTransistorsNumber();
        System.out.println("           - Extracted string (circuit; inputs; signals; gates): " + str);
        return str;

        /*Execução das threads*/
        /*
        Thread thread_temp = null;
        for (int i=0; i < thread_list.size() ; i++) {
            thread_temp = (Thread) thread_list.get(i);
            thread_temp.start();

        }

         */
        /*Esperando termino das threads*/
        /*
        for (int i=0; i < thread_list.size() ; i++) {
            thread_temp = (Thread) thread_list.get(i);
            thread_temp.join();
        }
        */
        /* Compilando os resultados - Falhas detectadas Ne*/
         /*
        for (int i=0; i < this.itemx_list.size() ; i++) {
            this.unmasked_faults = this.unmasked_faults +  itemx_list.get(i).getPropagatedFaults();
        }
         */
        /*circuit reliability SER (Soft Error Rate)*/
        /*
        this.circuitReliaibility = (float) (1.0 - ((float) this.unmasked_faults / (float) sizeExasuticTest));

        System.out.println("-> Umasked Faults: " + this.unmasked_faults);
        System.out.println("-> Sample: " + sizeExasuticTest);
        System.out.println("-> SER : " + this.circuitReliaibility);
        */
        /*
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

        */

        /*
         */
    }

    public void runMultithreadingExausticSimulationComplete(String option) throws IOException, Exception{ //Test All possibilities


        System.out.println(" ----- Exaustive Complete Simulation Version -------");
        long loadTimeStart = System.nanoTime();//System.currentTimeMillis();
        Instant start = Instant.now();

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


        Instant loadTimeElapsed = Instant.now();
        /* Print circuit Specs*/
        System.out.println("\n        ------ Printing Circuit Specs: --------");
        //this.PrintSpecsThesis();
        System.out.println("          ---------------------------------------\n");
        /*----------------------*/
        Instant startPreparingSimulationTimeElapsed = Instant.now();

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

        ArrayList <String> random_input_vectors =  this.generateInputVector("TRUE_TABLE"); //this.calcInputTableVector(this.probCircuit.getInputs().size(), this.sampleSize);

        ArrayList <ArrayList<Integer>> ListInputVectors =  this.splitInputPatternsInInt(random_input_vectors, this.probCircuit.getInputs().size());

        Instant endPreparingSimulationTimeElapsed = Instant.now();

        Instant startThreadingTimeElapsed = Instant.now();

        List thread_list = particionateExausticVectorComplete(ListInputVectors);  // TESTE ALL GATES ///particionateVectorPerThread(ListInputVectors); // x - vectors per thread

        N = sizeExasuticTest = this.sizeExaustiveCompleteSimulation;//(this.sampleSize * this.signals_to_inject_faults.size());

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
        int bitfipCcounter = 0;
        for (int i=0; i < this.itemx_list.size() ; i++) {
            this.unmasked_faults = this.unmasked_faults +  itemx_list.get(i).getPropagatedFaults();
            bitfipCcounter = bitfipCcounter + itemx_list.get(i).bitflipcounter;
        }

        Instant endThreadingTimeElapsed = Instant.now();

        /*circuit reliability SER (Soft Error Rate)*/
        this.circuitReliaibility = (float) (1.0 - ((float) this.unmasked_faults / (float) sizeExasuticTest));

        System.out.println("-> Umasked Faults: " + this.unmasked_faults);
        System.out.println("-> Sample: " + sizeExasuticTest);
        System.out.println("-> SER : " + this.circuitReliaibility);

        Instant finish = Instant.now();
        long timeElapsed_Instant = Duration.between(start, finish).toSeconds();

        LocalDateTime myDateObj2 = LocalDateTime.now();
        DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate2 = myDateObj2.format(myFormatObj2);


        Instant startTimelogGeneration = Instant.now();

        long timeElapsed_loadTime = Duration.between(start, loadTimeElapsed).toSeconds();
        long timeElapsed_PrepareTime = Duration.between(startPreparingSimulationTimeElapsed, endPreparingSimulationTimeElapsed).toSeconds();
        long timeElapsed_ThreadingTime = Duration.between(startThreadingTimeElapsed, endThreadingTimeElapsed).toSeconds();


        this.sampleSize = sizeExasuticTest;


        this.writeSimpleLogMultipleTransientFaultExaustive(option + "ExaustiveCompleteSimulation_Simple_Log_" +this.circuit.getName()+"_Threads-"+ this.threads +  "_sampleSize-" + this.sampleSize, formattedDate,  formattedDate2, timeElapsed_Instant);

        this.writeCsvFileCompleteThMTF("ExausticCompleteSimulation_"+this.circuit.getName()+"_Theads-"+ this.threads + "_sampleSize-" + this.sampleSize, itemx_list);

        Instant endTimelogGeneration = Instant.now();

        long timeElapsed_logGeneration = Duration.between(startTimelogGeneration, endTimelogGeneration).toSeconds();

        System.out.println("\n\n----------------- Results ------------------");
        System.out.println("Circuit: " + this.circuit.getName());
        System.out.println("- Simulation started at: " + formattedDate + " and finished at: "+ formattedDate2); //formattedDate

        // System.out.println("- PropagatedTime (s): " + propagateTime);
        System.out.println("- Sample Size (N): " + N);
        System.out.println("- Propagated fault(s) (Ne): " + this.unmasked_faults);
        System.out.println("- Fault Mask Rate (FMR): " + " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + N + ")) = " + this.circuitReliaibility);
        //System.out.println("- MTBF (Mean Time Between failure) : " + this.MTBF);
        // System.out.println("- Simulation TimeElapsed: " + propagateTimems + "(ms)");

        String time = "- Load Time : " + timeElapsed_loadTime + "(s) - Setup Time: " + timeElapsed_PrepareTime  + "(s) - Threading Execution Time: " + timeElapsed_ThreadingTime
                + "(s) - Log Generation: " + timeElapsed_logGeneration
                + "(s) - Simulation Instant TimeElapsed: " + timeElapsed_Instant +" (s)";
        //System.out.println("- MTF order list: " + mtf_list);
        System.out.println("- Bitflip Counter: " + bitfipCcounter);
        System.out.println(time);

        System.out.println("--------------------------------------------");
        this.Performance_Time = "Simulation started at: " + formattedDate
                + " and finished at: " + formattedDate2;
        this.sampleSize = N;
        System.out.println(" ----------------------------------------------------------------------------------------------------------------------");

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

    /**
     * @deprecated DO NOT USE IT
     * @param sampleSize
     * @param option
     * @param smallestGatesIncicuitsSimulation
     * @throws IOException
     * @throws Exception
     */
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

     public int parseResultsAndCalculateFMR(){

         int bitfipCcounter = 0;

         for (int i=0; i < this.itemx_list.size() ; i++) {
             this.unmasked_faults = this.unmasked_faults +  itemx_list.get(i).getPropagatedFaults();
             bitfipCcounter = bitfipCcounter + itemx_list.get(i).bitflipcounter;
         }

         this.circuitReliaibility = (float) (1.0 - ((float) this.unmasked_faults / (float) this.sampleSize));

         return bitfipCcounter;
     }

     public void printResultLog(){
         System.out.println("\n\n----------------- Printing Result ------------------");


         System.out.println("--------------------------------------------");
     }

    /**
     * @deprecated
     * This method orchestrates the settup enviroment for run Multithreading SET evalaution (LOGICAL SIMULATOR)
     * @param sampleSize
     * @param option
     * @throws IOException
     * @throws Exception
     */
    public void runMultithreadingMonteCarloDEPRECIATED(int sampleSize, String option) throws IOException, Exception{

                System.out.println(" ----- Monte Carlo version Single Transient Fault (STF) -------");
                long loadTimeStart = System.nanoTime();//System.currentTimeMillis();
                Instant start = Instant.now();
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

                Instant loadTimeElapsed = Instant.now();

                long loadTimeEnd = System.nanoTime();//System.currentTimeMillis();
                long loadTime =   TimeUnit.NANOSECONDS.toMillis(loadTimeEnd - loadTimeStart);
                //System.out.println("- Load Time m(s): " + loadTime);

                 System.out.println("\n        ------ Printing Circuit Specs: --------");
                this.PrintSpecs();
                System.out.println("          ---------------------------------------\n");
                /*----------------------*/

        Instant startPreparingSimulationTimeElapsed = Instant.now();

                this.sampleSize = sampleSize; //(int) Math.pow(2, this.probCircuit.getInputs().size());  //(int) Math.pow(2, this.probCircuit.getInputs().size());
                int N = this.sampleSize; // random_input_vectors.size();//testNumber;

                System.out.println("-  (input) Sample size = " + this.sampleSize);

                this.signals_to_inject_faults = this.signalsToInjectFault(option); // Consider all signals to fault inject

                ArrayList <String> random_input_vectors = this.generateInputVector("RANDOM"); // Generate Random Input Vectors or InputTrueTable

                ArrayList <ArrayList<Integer>> ListInputVectors =  this.splitInputPatternsInInt(random_input_vectors, this.probCircuit.getInputs().size());

                List thread_list = this.particionateVectorPerThread(ListInputVectors); // x - vectors per thread

        Instant endPreparingSimulationTimeElapsed = Instant.now();

        Instant startThreadingTimeElapsed = Instant.now();

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
        Instant endThreadingTimeElapsed = Instant.now();

                /* Compilando os resultados - Falhas detectadas Ne*/
                int bitfipCcounter = 0;
                for (int i=0; i < this.itemx_list.size() ; i++) {
                    this.unmasked_faults = this.unmasked_faults +  itemx_list.get(i).getPropagatedFaults();
                    bitfipCcounter = bitfipCcounter + itemx_list.get(i).bitflipcounter;
                }

                 this.circuitReliaibility = (float) (1.0 - ((float) this.unmasked_faults / (float) this.sampleSize));

                /*circuit reliability SER (Soft Error Rate)*/
                //this.circuitReliaibility = (1 - (this.unmasked_faults/this.sampleSize));


        Instant finish = Instant.now();
        long timeElapsed_Instant = Duration.between(start, finish).toSeconds();

                LocalDateTime myDateObj2 = LocalDateTime.now();
                DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate2 = myDateObj2.format(myFormatObj2);

                Instant startTimelogGeneration = Instant.now();

                long timeElapsed_loadTime = Duration.between(start, loadTimeElapsed).toSeconds();
                long timeElapsed_PrepareTime = Duration.between(startPreparingSimulationTimeElapsed, endPreparingSimulationTimeElapsed).toSeconds();
                long timeElapsed_ThreadingTime = Duration.between(startThreadingTimeElapsed, endThreadingTimeElapsed).toSeconds();


                this.writeSimpleLog(option + "_MonteCarlo_Simple_Log_" +this.circuit.getName()+"_Threads-"+ this.threads +  "_sampleSize-" + this.sampleSize, formattedDate,  formattedDate2, timeElapsed_Instant);
                this.writeCsvFileCompleteTh(option+"_MonteCarlo_Complete_Log_"+this.circuit.getName()+"_Theads-"+ this.threads + "_sampleSize"+ this.sampleSize, itemx_list);

        Instant endTimelogGeneration = Instant.now();
        long timeElapsed_logGeneration = Duration.between(startTimelogGeneration, endTimelogGeneration).toSeconds();


                /*
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
                System.out.println("\n\n----------------- Results ------------------");
                System.out.println("Circuit: " + this.circuit.getName());
                System.out.println("- Simulation started at: " + formattedDate + " and finished at: "+ formattedDate2); //formattedDate

                // System.out.println("- PropagatedTime (s): " + propagateTime);
                System.out.println("- Sample Size (N): " + N);
                System.out.println("- Propagated fault(s) (Ne): " + this.unmasked_faults);
                System.out.println("- Fault Mask Rate (FMR): " + " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + N + ")) = " + this.circuitReliaibility);
                //System.out.println("- MTBF (Mean Time Between failure) : " + this.MTBF);
                // System.out.println("- Simulation TimeElapsed: " + propagateTimems + "(ms)");

                String time = "- Load Time : " + timeElapsed_loadTime + "(s) - Setup Time: " + timeElapsed_PrepareTime  + "(s) - Threading Execution Time: " + timeElapsed_ThreadingTime
                        + "(s) - Log Generation: " + timeElapsed_logGeneration
                        + "(s) - Simulation Instant TimeElapsed: " + timeElapsed_Instant +" (s)";
                System.out.println("- Bitflip Counter: " + bitfipCcounter);
                System.out.println(time);

                System.out.println("--------------------------------------------");
                this.Performance_Time = "Simulation started at: " + formattedDate
                        + " and finished at: " + formattedDate2;
                this.sampleSize = N;
                System.out.println(" ----------------------------------------------------------------------------------------------------------------------");



    }

    /**
     * Write Simple and Complete logs, showing the simulation results: FMR, Failure Rate, Sensitive Nodes, ...., another informations
     * @param fileName
     * @param date
     * @param dateend
     * @param propagateTimems
     * @param itemx_list
     * @throws IOException
     * @throws WriteException
     */
    public void writeLogs(String fileName, String date, String dateend, long propagateTimems,  ArrayList<LogicSimulator> itemx_list ) throws IOException, WriteException {

        System.out.println("- Writing logs ...");

        this.writeSimpleLog(fileName, date,  dateend, propagateTimems);

        System.out.println("- Simple Log " + fileName + "  (.txt)");

        this.writeCsvFileCompleteTh(fileName, itemx_list);

        System.out.println("- Complete Log " + fileName + "  (.csv)");
    }

    /**
     * Run the thread simulation in parallel
     * @param thread_list
     * @throws Exception
     */
    public void executeThreadsSimulation(List thread_list) throws Exception {
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

    }

    /**
     * Generate input vectors according with sampleSize and signalsOption defition
     * @param sampleSize eg: 20000 SET faults (Particle generating SET)
     * @param signalsOption
     * @return
             * @throws Exception
     */
    public List createVectorsAndParticionate(int sampleSize, String signalsOption, String option) throws Exception {

        List thread_list = new ArrayList();

        this.sampleSize = sampleSize; //(int) Math.pow(2, this.probCircuit.getInputs().size());  //(int) Math.pow(2, this.probCircuit.getInputs().size());

        this.signals_to_inject_faults = this.signalsToInjectFault(signalsOption); // Consider all signals to fault inject

        ArrayList <String> random_input_vectors = this.generateInputVector(option); // Generate Random Input Vectors or InputTrueTable

        switch (option) {
            case "RANDOM":
                    ArrayList <ArrayList<Integer>> ListInputVectors =  this.splitInputPatternsInInt(random_input_vectors, this.probCircuit.getInputs().size());
                    thread_list = this.particionateVectorPerThread(ListInputVectors); // x - vectors per thread
                break;

        }

        return (thread_list);
    }

    /**
     * This methods setup genlib and verilog to proceed any simulation
     * @param simulation_type
     * @throws IOException
     * @throws Exception
     */
    public void setupEnviroment(String simulation_type) throws IOException, Exception{

        System.out.println(simulation_type);

        /*Reading CellLibrary*/
        CellLibrary cellLib = new CellLibrary();
        this.cellLibrary = cellLib;
        this.cellLibrary.initLibrary(this.genlib);
        System.out.println("    ... Reading Genlib " + " at -> " + this.genlib  + " ... ok");
        System.out.println("    ... Avaliable logic gates in this library: "+ cellLib.getCells().size());
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

        //System.out.println("\n        ------ Printing Circuit Specs: --------");
            //this.PrintSpecs();
        //System.out.println("          ---------------------------------------\n");
        /*----------------------*/
    }

    /**
     * This method orchestrates the settup enviroment for run Multithreading SET evalaution (LOGICAL SIMULATOR)
     * @param sampleSize
     * @param option
     * @throws IOException
     * @throws Exception
     */
    public void runMultithreadingMonteCarlo(int sampleSize, String option) throws IOException, Exception{

        Instant start = Instant.now();

            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = myDateObj.format(myFormatObj);

            this.setupEnviroment("\n ----- Monte Carlo version Single Transient Fault (STF) -------");

        System.out.println("    - Simulation start in : " + formattedDate);
        System.out.println("    - Threads in execution: " + this.threads);

        Instant loadTimeElapsed = Instant.now();

        Instant startPreparingSimulationTimeElapsed = Instant.now();

            int N = this.sampleSize; // random_input_vectors.size();//testNumber;

            System.out.println("-  (input) Sample size = " + this.sampleSize);

            List thread_list =  this.createVectorsAndParticionate(sampleSize, option, "RANDOM");

        Instant endPreparingSimulationTimeElapsed = Instant.now();

        Instant startThreadingTimeElapsed = Instant.now();

            this.executeThreadsSimulation(thread_list);  // Prepare and run the thread simulation

        Instant endThreadingTimeElapsed = Instant.now();

            int bitfipCcounter = this.parseResultsAndCalculateFMR();  // FMR

            Instant finish = Instant.now();

        long timeElapsed_Instant = Duration.between(start, finish).toSeconds();

            LocalDateTime myDateObj2 = LocalDateTime.now();
            DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate2 = myDateObj2.format(myFormatObj2);

        Instant startTimelogGeneration = Instant.now();

            long timeElapsed_loadTime = Duration.between(start, loadTimeElapsed).toSeconds();
            long timeElapsed_PrepareTime = Duration.between(startPreparingSimulationTimeElapsed, endPreparingSimulationTimeElapsed).toSeconds();
            long timeElapsed_ThreadingTime = Duration.between(startThreadingTimeElapsed, endThreadingTimeElapsed).toSeconds();

        Instant endTimelogGeneration = Instant.now();

        long timeElapsed_logGeneration = Duration.between(startTimelogGeneration, endTimelogGeneration).toSeconds();


        this.writeLogs(option + "_MonteCarlo_Simple_Log_" +this.circuit.getName()+"_Threads-"+ this.threads +  "_sampleSize-" + this.sampleSize, formattedDate,  formattedDate2, timeElapsed_Instant, itemx_list);

        System.out.println("----------------------------------------------------------------------");

        System.out.println("- Simulation started at: " + formattedDate + " and finished at: "+ formattedDate2);
        System.out.println("- Circuit: " + this.circuit.getName());
        System.out.println("- Sample Size (N): " + this.sampleSize );
        System.out.println("- Fault Mask Rate (FMR): " + " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + this.sampleSize + ")) = " + this.circuitReliaibility);
        System.out.println("- Bitflip Counter: " + bitfipCcounter );
        System.out.println("- Load Time : " + timeElapsed_loadTime + "(s) - Setup Time: " + timeElapsed_PrepareTime  + "(s) - Threading Execution Time: " + timeElapsed_ThreadingTime
                + "(s) - Log Generation: " + timeElapsed_logGeneration
                + "(s) - Simulation Instant TimeElapsed: " + timeElapsed_Instant +" (s)" );

        System.out.println("-----------------------END SIMULATION---------------------------------");

            this.Performance_Time = "Simulation started at: " + formattedDate + " and finished at: " + formattedDate2;
            //this.sampleSize = N;

    }

    public void runMultipleFaultInjectionMultithreadingMonteCarlo(int base, int order, int frequency, int sampleSize, String option) throws IOException, Exception{

        System.out.println(" ----- Monte Carlo version  for Multiple Transient Fault Injection -------");
        long loadTimeStart = System.nanoTime();//System.currentTimeMillis();
        Instant start = Instant.now();

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

        Instant finish = Instant.now();
        long timeElapsed_Instant = Duration.between(start, finish).toMillis();
        //long propagateTimems =  TimeUnit.NANOSECONDS.toMillis(propagateTimeEnd - propagateTimeStart);


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

    public void runMultipleFaultInjectionMultithreadingMonteCarloSimulation(ArrayList <Integer> mtf_list, String option) throws IOException, Exception{


        System.out.println(" ----- Monte Carlo version  for Multiple Transient Fault Injection -------");

        Instant start = Instant.now();

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

        Instant loadTimeElapsed = Instant.now();

        System.out.println("\n        ------ Printing Circuit Specs: --------");
        this.PrintSpecs();
        System.out.println("          ---------------------------------------\n");
        /*----------------------*/

        Instant startPreparingSimulationTimeElapsed = Instant.now();

            this.sampleSize = sampleSize; //(int) Math.pow(2, this.probCircuit.getInputs().size());  //(int) Math.pow(2, this.probCircuit.getInputs().size());
            int N = this.sampleSize; // random_input_vectors.size();//testNumber;
            //int period =  (int) Math.pow(base, order);
            System.out.println("-  (input) Sample size = " + this.sampleSize);

            this.signals_to_inject_faults = this.signalsToInjectFault(option); // Consider all signals to fault inject

            ArrayList <String> random_input_vectors = this.generateInputVector("RANDOM"); // Generate Random Input Vectors or InputTrueTable

           // Collections.sort(random_input_vectors);

            ///
            // System.out.println(random_input_vectors);

            ArrayList <ArrayList<Integer>> ListInputVectors =  this.splitInputPatternsInInt(random_input_vectors, this.probCircuit.getInputs().size());

            ArrayList <Integer> tt = new ArrayList<>(mtf_list);
            tt.remove(0); // 20k

            final ArrayList <Integer> arrayList_mtf_original = new ArrayList<>(tt); // Original ArrayList

            List thread_list = this.particionateMultipletransientFaultInjectionVectorPerThreadMODE(ListInputVectors, mtf_list); // x - vectors per thread

        Instant endPreparingSimulationTimeElapsed = Instant.now();

        Instant startThreadingTimeElapsed = Instant.now();

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

        Instant endThreadingTimeElapsed = Instant.now();

        /* Compilando os resultados - Falhas detectadas Ne*/
        int bitfipCcounter = 0;
        for (int i=0; i < this.itemx_list.size() ; i++) {
            this.unmasked_faults = this.unmasked_faults +  itemx_list.get(i).getPropagatedFaults();
            bitfipCcounter = bitfipCcounter + itemx_list.get(i).bitflipcounter;
        }


        this.circuitReliaibility = (float) (1.0 - ((float) this.unmasked_faults / (float) this.sampleSize));

        /*circuit reliability SER (Soft Error Rate)*/
        //this.circuitReliaibility = (1 - (this.unmasked_faults/this.sampleSize));


        Instant finish = Instant.now();
        long timeElapsed_Instant = Duration.between(start, finish).toSeconds();

        LocalDateTime myDateObj2 = LocalDateTime.now();
        DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate2 = myDateObj2.format(myFormatObj2);

        Instant startTimelogGeneration = Instant.now();

        long timeElapsed_loadTime = Duration.between(start, loadTimeElapsed).toSeconds();
        long timeElapsed_PrepareTime = Duration.between(startPreparingSimulationTimeElapsed, endPreparingSimulationTimeElapsed).toSeconds();
        long timeElapsed_ThreadingTime = Duration.between(startThreadingTimeElapsed, endThreadingTimeElapsed).toSeconds();

        this.writeSimpleLogMultipleTransientFault(option + "_MULTIPLE_MonteCarlo_Simple_Log_" +this.circuit.getName()+"_Threads-"+ this.threads +  "_sampleSize-" + this.sampleSize, formattedDate,  formattedDate2, timeElapsed_Instant, arrayList_mtf_original);
        this.writeCsvFileCompleteThMTF(option+"_MULTIPLE_MonteCarlo_Complete_Log_"+this.circuit.getName()+"_Theads-"+ this.threads + "_sampleSize"+ this.sampleSize, itemx_list);

        Instant endTimelogGeneration = Instant.now();


        long timeElapsed_logGeneration = Duration.between(startTimelogGeneration, endTimelogGeneration).toSeconds();

        System.out.println("\n\n----------------- Results ------------------");
        System.out.println("Circuit: " + this.circuit.getName());
        System.out.println("- Simulation started at: " + formattedDate + " and finished at: "+ formattedDate2); //formattedDate

        // System.out.println("- PropagatedTime (s): " + propagateTime);
        System.out.println("- Sample Size (N): " + N);
        System.out.println("- Propagated fault(s) (Ne): " + this.unmasked_faults);
        System.out.println("- Fault Mask Rate (FMR): " + " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + N + ")) = " + this.circuitReliaibility);
        //System.out.println("- MTBF (Mean Time Between failure) : " + this.MTBF);
        // System.out.println("- Simulation TimeElapsed: " + propagateTimems + "(ms)");

        String time = "- Load Time : " + timeElapsed_loadTime + "(s) - Setup Time: " + timeElapsed_PrepareTime  + "(s) - Threading Execution Time: " + timeElapsed_ThreadingTime
                + "(s) - Log Generation: " + timeElapsed_logGeneration
                + "(s) - Simulation Instant TimeElapsed: " + timeElapsed_Instant +" (s)";
        System.out.println("- MTF order list: " + mtf_list
        );
        System.out.println("- Bitflip Counter: " + bitfipCcounter);
        System.out.println(time);

        System.out.println("--------------------------------------------");
        this.Performance_Time = "Simulation started at: " + formattedDate
                + " and finished at: " + formattedDate2;
        this.sampleSize = N;
        System.out.println(" ----------------------------------------------------------------------------------------------------------------------");

    }

    public void runMultipleFaultInjectionMultithreadingMonteCarloSimulationProportion(int sample, ArrayList <Float> mtf_list, String option) throws IOException, Exception{

        System.out.println("SUM PROPORTION: " + sumProportionPercentage(mtf_list));
        if (sumProportionPercentage(mtf_list) == 1.0) {  // 100%

            System.out.println(" ----- Monte Carlo version  for Multiple Transient Fault Injection -------");

            Instant start = Instant.now();

            int sampleSize = sample;//mtf_list.get(0);

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

            System.out.println("    ... Reading Genlib " + " at -> " + this.genlib + " ... ok");
            //System.out.println("  - Avaliable logic gatesin this library: "+cellLib.getCells());

            System.out.println("    ... Reading verilog " + " at -> " + this.circuitNameStr + " ... ok");
            /*Reading verilog*/
            MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitNameStr, this.cellLibrary);
            this.verilog_circuit = verilog_circuit;
            /*Circuit linked to verilog_circuit - init circuit*/
            this.circuit = verilog_circuit.getCircuit();

            //System.out.println("Patterns : " + this.verilog_circuit.getGatePattern());


            /*Circuit Probabilities */
            this.initLevelCircuit();

            /*Init ProbCircuits*/
            this.initProbCircuit();

            /*Init PTMs Const*/
            cellLib.setPTMCells2(Float.valueOf(this.reliabilityConst));
            cellLib.setPTMCells(new BigDecimal(this.reliabilityConst));

            Instant loadTimeElapsed = Instant.now();

            System.out.println("\n        ------ Printing Circuit Specs: --------");
            this.PrintSpecs();
            System.out.println("          ---------------------------------------\n");
            /*----------------------*/

            Instant startPreparingSimulationTimeElapsed = Instant.now();

            this.sampleSize = sampleSize; //(int) Math.pow(2, this.probCircuit.getInputs().size());  //(int) Math.pow(2, this.probCircuit.getInputs().size());
            int N = this.sampleSize; // random_input_vectors.size();//testNumber;
            //int period =  (int) Math.pow(base, order);
            System.out.println("-  (input) Sample size = " + this.sampleSize);

            this.signals_to_inject_faults = this.signalsToInjectFault(option); // Consider all signals to fault inject

            ArrayList<String> random_input_vectors = this.generateInputVector("RANDOM"); // Generate Random Input Vectors or InputTrueTable

            //Collections.sort(random_input_vectors);

            ///
            //System.out.println(" ....... dev input vectors size: " + random_input_vectors.size());

            ArrayList<ArrayList<Integer>> ListInputVectors = this.splitInputPatternsInInt(random_input_vectors, this.probCircuit.getInputs().size());

            ArrayList<Float> tt = new ArrayList<>(mtf_list);
            tt.remove(0); // 20k

            final ArrayList<Float> arrayList_mtf_original = new ArrayList<>(tt); // Original ArrayList

            //List thread_list = this.particionateMultipletransientFaultInjectionVectorPerThreadMODE(ListInputVectors, mtf_list); // x - vectors per thread
            List thread_list = this.particionateMultipletransientFaultInjectionVectorPerThreadProportion(ListInputVectors, mtf_list); // x - vectors per thread

            Instant endPreparingSimulationTimeElapsed = Instant.now();

            Instant startThreadingTimeElapsed = Instant.now();

            /*Execução das threads*/
            Thread thread_temp = null;
            for (int i = 0; i < thread_list.size(); i++) {
                thread_temp = (Thread) thread_list.get(i);
                thread_temp.start();
            }
            /*Esperando termino das threads*/
            for (int i = 0; i < thread_list.size(); i++) {
                thread_temp = (Thread) thread_list.get(i);
                thread_temp.join();
            }

            Instant endThreadingTimeElapsed = Instant.now();

            /* Compilando os resultados - Falhas detectadas Ne*/
            int bitfipCcounter = 0;

            for (int i = 0; i < this.itemx_list.size(); i++) {
                System.out.println(" itemx_list: " + itemx_list.get(i) + " prop: " + itemx_list.get(i).getPropagatedFaults());
                this.unmasked_faults = this.unmasked_faults + itemx_list.get(i).getPropagatedFaults();
                bitfipCcounter = bitfipCcounter + itemx_list.get(i).bitflipcounter;
            }


            this.circuitReliaibility = (float) (1.0 - ((float) this.unmasked_faults / (float) this.sampleSize));

            /*circuit reliability SER (Soft Error Rate)*/
            //this.circuitReliaibility = (1 - (this.unmasked_faults/this.sampleSize));


            Instant finish = Instant.now();
            long timeElapsed_Instant = Duration.between(start, finish).toSeconds();

            LocalDateTime myDateObj2 = LocalDateTime.now();
            DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate2 = myDateObj2.format(myFormatObj2);

            Instant startTimelogGeneration = Instant.now();

            long timeElapsed_loadTime = Duration.between(start, loadTimeElapsed).toSeconds();
            long timeElapsed_PrepareTime = Duration.between(startPreparingSimulationTimeElapsed, endPreparingSimulationTimeElapsed).toSeconds();
            long timeElapsed_ThreadingTime = Duration.between(startThreadingTimeElapsed, endThreadingTimeElapsed).toSeconds();

            //this.writeSimpleLogMultipleTransientFault(option + "_MULTIPLE_MonteCarlo_Simple_Log_" +this.circuit.getName()+"_Threads-"+ this.threads +  "_sampleSize-" + this.sampleSize, formattedDate,  formattedDate2, timeElapsed_Instant, arrayList_mtf_original);
            this.writeSimpleLogMultipleTransientFaultProportion(option + "_MULTIPLE_MonteCarlo_Simple_Log_" + this.circuit.getName() + "_Threads-" + this.threads + "_sampleSize-" + this.sampleSize, formattedDate, formattedDate2, timeElapsed_Instant, arrayList_mtf_original);

            this.writeCsvFileCompleteThMTF(option + "_MULTIPLE_MonteCarlo_Complete_Log_" + this.circuit.getName() + "_Theads-" + this.threads + "_sampleSize" + this.sampleSize, itemx_list);

            Instant endTimelogGeneration = Instant.now();


            long timeElapsed_logGeneration = Duration.between(startTimelogGeneration, endTimelogGeneration).toSeconds();

            System.out.println("\n\n----------------- Results ------------------");
            System.out.println("Circuit: " + this.circuit.getName());
            System.out.println("- Simulation started at: " + formattedDate + " and finished at: " + formattedDate2); //formattedDate

            // System.out.println("- PropagatedTime (s): " + propagateTime);
            System.out.println("- Sample Size (N): " + N);
            System.out.println("- Propagated fault(s) (Ne): " + this.unmasked_faults);
            System.out.println("- Fault Mask Rate (FMR): " + " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + N + ")) = " + this.circuitReliaibility);
            //System.out.println("- MTBF (Mean Time Between failure) : " + this.MTBF);
            // System.out.println("- Simulation TimeElapsed: " + propagateTimems + "(ms)");

            String time = "- Load Time : " + timeElapsed_loadTime + "(s) - Setup Time: " + timeElapsed_PrepareTime + "(s) - Threading Execution Time: " + timeElapsed_ThreadingTime
                    + "(s) - Log Generation: " + timeElapsed_logGeneration
                    + "(s) - Simulation Instant TimeElapsed: " + timeElapsed_Instant + " (s)";

           // ArrayList <Integer> new_MTF = passProportionPercentage(arrayList_mtf_original, sample);
            System.out.println("- MTF order list: " + mtf_list);

            System.out.println("- Bitflip Counter: " + bitfipCcounter);
            System.out.println(time);

            System.out.println("--------------------------------------------");
            this.Performance_Time = "Simulation started at: " + formattedDate
                    + " and finished at: " + formattedDate2;
            this.sampleSize = N;
            System.out.println(" ----------------------------------------------------------------------------------------------------------------------\n\n...");
        }
        else{
            System.err.println("- Inputs inserted sum up ("+sumProportionPercentage(mtf_list)+") above 1 (100%), these were the inserted commands: " + mtf_list);
        }

    }

    public Map<String, SensitiveCell> readCsvFileAndMapSensitiveCellsArea(String input, String comma){


            String line = "";

            String outputString;

            //String ArrayCsv[] = new String[4];

            Map<String, SensitiveCell> sensitive_cells = new HashMap<>();

            try (BufferedReader br = new BufferedReader(new FileReader(input))) {

                //Para cada linea no vacia
                for (int i = 0; (line = br.readLine()) != null ; i++) {

                    String[] table = line.split(comma);

                    String concat = table[0]+"_"+table[1];

                    outputString = "Cell " + table[0] + " Vec = " + table[1]+ " , Sensitive_Area " + table[2];

                    SensitiveCell cell = new SensitiveCell(concat, table[1], table[2]);

                    sensitive_cells.put(concat, cell);

                    System.out.println(line + " splitted " + outputString);

                }
            } catch (IOException e) {
                System.out.println("Error al leer .csv");
                e.printStackTrace();
            }
            return sensitive_cells;
    }

    public void runCalculationSensitiveAreas(String option, String file) throws IOException, Exception{ //Test All possibilities
        System.out.println(" ----- Calculate Sensitive Areas -------");
            System.out.println("File: " + file);
            //file = "teste\\lookup_table.csv";
            //System.out.println("File: " + file);

        Map <String, SensitiveCell> sensitive_cells = readCsvFileAndMapSensitiveCellsArea(file, ",");

        System.out.println("Sensitive Cells: " + sensitive_cells);

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

        sizeExasuticTest = this.sampleSize;//(this.sampleSize * this.signals_to_inject_faults.size());

        ArrayList <String> random_input_vectors =  this.generateInputVector("TRUE_TABLE"); //this.calcInputTableVector(this.probCircuit.getInputs().size(), this.sampleSize);

        ArrayList <ArrayList<Integer>> ListInputVectors =  this.splitInputPatternsInInt(random_input_vectors, this.probCircuit.getInputs().size());

        System.out.println("LIST:::::: "+ ListInputVectors.size());

        List thread_list = particionateExausticVectorForSensitiveAreas(ListInputVectors, sensitive_cells);  // TESTE ALL GATES ///particionateVectorPerThread(ListInputVectors); // x - vectors per thread

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
        //for (int i=0; i < this.itemx_list.size() ; i++) {
        //    this.unmasked_faults = this.unmasked_faults +  itemx_list.get(i).getPropagatedFaults();
        //}
        for (int i = 0; i < this.itemx_list.size(); i++) {
            List <TestVectorInformation> x =  this.itemx_list.get(i).get_threadSimulationList();
            for (int j = 0; j < x.size(); j++) {
                System.out.println(" index: " + x.get(j).getSimulationIndex() + " vec: " + x.get(j).getinputVector() + " sensitive area sum: " + x.get(j).getSum_sensitive_cells_area() );
            }
        }

        //System.out.println("SIZE: " + this.itemx_list.size());

        /*circuit reliability SER (Soft Error Rate)*/
       // this.circuitReliaibility = (float) (1.0 - ((float) this.unmasked_faults / (float) sizeExasuticTest));

        //System.out.println("-> Umasked Faults: " + this.unmasked_faults);
        //System.out.println("-> Sample: " + sizeExasuticTest);
        //System.out.println("-> SER : " + this.circuitReliaibility);


        long propagateTimeEnd = System.nanoTime();
        //long propagateTime =    TimeUnit.NANOSECONDS.toSeconds(propagateTimeEnd - propagateTimeStart);
        long propagateTime =  TimeUnit.NANOSECONDS.toMillis(propagateTimeEnd - propagateTimeStart);


        LocalDateTime myDateObj2 = LocalDateTime.now();
        DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate2 = myDateObj2.format(myFormatObj2);

        this.sampleSize = sizeExasuticTest;

        //this.writeSimpleLog("ExausticSimulation_" +this.circuit.getName()+"_Threads-"+ this.threads + "_sampleSize-" + this.sampleSize, formattedDate,  formattedDate2, propagateTime);

        //this.writeCsvFileCompleteTh("ExausticSimulation_"+this.circuit.getName()+"_Theads-"+ this.threads + "_sampleSize-" + this.sampleSize, itemx_list);


        System.out.println("\n\n----------------- Sensitive Area ------------------");
        System.out.println("Circuit: " + this.circuit.getName());
        System.out.println("- Simulation finished at: " + formattedDate2);
        //System.out.println("- PropagatedTime (s): " + propagateTime);
        System.out.println("- Sample (N): " + this.sampleSize);
        //System.out.println("- Detected faults (Ne): " + this.unmasked_faults);
        //System.out.println("- Fault Masking Rate (FMR): " + "(1-(" + this.unmasked_faults + "/" + this.sampleSize + ")) = " + this.circuitReliaibility);
        // System.out.println("- MTBF (Mean Time Between failure) : " + this.MTBF);
        System.out.println("- Simulation TimeElapsed: " + propagateTime + "(s)");
        System.out.println("--------------------------------------------");

        this.Performance_Time = "Simulation started at: " + formattedDate + " and finished at: " + formattedDate2;

        System.out.println(" ----------------------------------------------------------------------------------------------------------------------------\n\n");
        /*
         */
    }

    public void generateSpiceNetlist(String path) throws IOException, Exception{ //Test All possibilities
        System.out.println(" ----- Start to generate the Spice Netlist -------");
        //System.out.println("File: " + file);
        //file = "teste\\lookup_table.csv";
        //System.out.println("File: " + file);

        //Map <String, SensitiveCell> sensitive_cells = readCsvFileAndMapSensitiveCellsArea(file, ",");

        //System.out.println("Sensitive Cells: " + sensitive_cells);

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
        //System.out.println("\n        ------ Printing Circuit Specs: --------");
        //this.PrintSpecsThesis();
        //System.out.println("          ---------------------------------------\n");
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

        this.signals_to_inject_faults = this.signalsToInjectFault("ALL_SIGNALS"); // Consider all signals to fault inject

        sizeExasuticTest = this.sampleSize;//(this.sampleSize * this.signals_to_inject_faults.size());

        //ArrayList <String> random_input_vectors =  this.generateInputVector("TRUE_TABLE"); //this.calcInputTableVector(this.probCircuit.getInputs().size(), this.sampleSize);

        //ArrayList <ArrayList<Integer>> ListInputVectors =  this.splitInputPatternsInInt(random_input_vectors, this.probCircuit.getInputs().size());

        //System.out.println("LIST:::::: "+ ListInputVectors.size());

        List thread_list = parseVerilogToSpiceNetList();  // TESTE ALL GATES ///particionateVectorPerThread(ListInputVectors); // x - vectors per thread

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
        //for (int i=0; i < this.itemx_list.size() ; i++) {
        //    this.unmasked_faults = this.unmasked_faults +  itemx_list.get(i).getPropagatedFaults();
        //}
        for (int i = 0; i < this.itemx_list.size(); i++) {
            List <TestVectorInformation> x =  this.itemx_list.get(i).get_threadSimulationList();
            for (int j = 0; j < x.size(); j++) {
                System.out.println(" index: " + x.get(j).getSimulationIndex() + " content parsed file" + this.itemx_list.get(i).getParsedNetlistContent());
            }
        }
        System.out.println("PATH: " + path);
        this.writeInformationInFileLog(path, "", this.itemx_list.get(0).getParsedNetlistContent(), "netlist_"+this.circuit.getName());
        System.out.println("Created parsed netlist file: " + path + "netlist_"+this.circuit.getName() + ".txt");
        System.out.println("- Calling eletric simulator....");
        //path = path + "netlist_files/";
        List<File> files = new ArrayList<>();


        files.add(new File(path+"/45nm_HP.pm"));
        files.add(new File(path+"/Library.txt"));
        //String path = "C:/destination/";
        for(File file : files) {
            Files.copy(file.toPath(),
                    (new File(path + "netlist_files/" + file.getName())).toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        }
        this.runElectricalSimulator(path, path + "netlist_files/" +"netlist_"+this.circuit.getName() + ".txt");
        // this.writeSimpleLogMultipleTransientFaultProportion(option + "_MULTIPLE_MonteCarlo_Simple_Log_" + this.circuit.getName() + "_Threads-" + this.threads + "_sampleSize-" + this.sampleSize, formattedDate, formattedDate2, timeElapsed_Instant, arrayList_mtf_original);
        //System.out.println("SIZE: " + this.itemx_list.size());

        /*circuit reliability SER (Soft Error Rate)*/
        // this.circuitReliaibility = (float) (1.0 - ((float) this.unmasked_faults / (float) sizeExasuticTest));

        //System.out.println("-> Umasked Faults: " + this.unmasked_faults);
        //System.out.println("-> Sample: " + sizeExasuticTest);
        //System.out.println("-> SER : " + this.circuitReliaibility);


        long propagateTimeEnd = System.nanoTime();
        //long propagateTime =    TimeUnit.NANOSECONDS.toSeconds(propagateTimeEnd - propagateTimeStart);
        long propagateTime =  TimeUnit.NANOSECONDS.toMillis(propagateTimeEnd - propagateTimeStart);


        LocalDateTime myDateObj2 = LocalDateTime.now();
        DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate2 = myDateObj2.format(myFormatObj2);

        //this.sampleSize = sizeExasuticTest;

        //this.writeSimpleLog("ExausticSimulation_" +this.circuit.getName()+"_Threads-"+ this.threads + "_sampleSize-" + this.sampleSize, formattedDate,  formattedDate2, propagateTime);

        //this.writeCsvFileCompleteTh("ExausticSimulation_"+this.circuit.getName()+"_Theads-"+ this.threads + "_sampleSize-" + this.sampleSize, itemx_list);

            /*
        //System.out.println("\n\n-----------------------------------");
        System.out.println("Circuit: " + this.circuit.getName());
        System.out.println("- Simulation finished at: " + formattedDate2);
        //System.out.println("- PropagatedTime (s): " + propagateTime);
        System.out.println("- Sample (N): " + this.sampleSize);
        //System.out.println("- Detected faults (Ne): " + this.unmasked_faults);
        //System.out.println("- Fault Masking Rate (FMR): " + "(1-(" + this.unmasked_faults + "/" + this.sampleSize + ")) = " + this.circuitReliaibility);
        // System.out.println("- MTBF (Mean Time Between failure) : " + this.MTBF);
        System.out.println("- Simulation TimeElapsed: " + propagateTime + "(s)");
        System.out.println("--------------------------------------------");
        */
        System.out.println("- Simulation TimeElapsed: " + propagateTime + "(s)");
        this.Performance_Time = "Simulation started at: " + formattedDate + " and finished at: " + formattedDate2;

        System.out.println(" ----------------------------------------------------------------------------------------------------------------------------\n\n");
        /*
         */
    }

    public void runElectricalSimulator(String path, String spiceCircuito){
        try {
            Runtime runTime = Runtime.getRuntime();

            String executablePath = path + "ngspice.exe " + spiceCircuito;//"C:\\Users\\sdkca\\AppData\\Local\\Programs\\Microsoft VS Code\\Code.exe";

            Process process = runTime.exec(executablePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

                String str = this.circuit.getName() + ";" + this.PrintGatesCounter(); //this.PrintTransistorsNumber();

                System.out.println("          ---------------------------------------\n");
                /*----------------------*/

                return str;

                //System.out.println(" ----------------------------------------------------------------------------------------------------------------------");
             /*
             */
     }

    public String PrintCircuitSpecsFast() throws IOException, Exception{

        CellLibrary cellLib = new CellLibrary();
        //System.out.println("2");
        this.cellLibrary = cellLib;
        //System.out.println("3");
        this.cellLibrary.initLibrary(this.genlib);
        //System.out.println("4");

        System.out.println("    ... Reading Genlib " + " at -> " + this.genlib + " ... ok");
        //System.out.println("  - Avaliable logic gatesin this library: "+cellLib.getCells());

        System.out.println("    ... Reading verilog " + " at -> " + this.circuitNameStr + " ... ok");
        /*Reading verilog*/
        MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitNameStr, this.cellLibrary);
        this.verilog_circuit = verilog_circuit;
        /*Circuit linked to verilog_circuit - init circuit*/
        this.circuit = verilog_circuit.getCircuit();

        //System.out.println("Patterns : " + this.verilog_circuit.getGatePattern());


        /*Circuit Probabilities */
        this.initLevelCircuit();

        /*Init ProbCircuits*/
        this.initProbCircuit();

        /*Init PTMs Const*/
        cellLib.setPTMCells2(Float.valueOf(this.reliabilityConst));
        cellLib.setPTMCells(new BigDecimal(this.reliabilityConst));


        String str = this.circuit.getName() + ";" + this.circuit.getInputs().size() + ";" + this.circuit.getSignals().size() + ";" + this.PrintGatesCounter(); //this.PrintTransistorsNumber();
        System.out.println("STR: " + str);
        return str;

    }

}
