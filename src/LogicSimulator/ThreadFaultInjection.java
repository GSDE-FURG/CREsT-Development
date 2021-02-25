/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator;

import datastructures.Cell;
import datastructures.CellLibrary;
import datastructures.Circuit;
import datastructures.Signal;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static javafx.scene.input.KeyCode.K;
import static javafx.scene.input.KeyCode.V;
import javax.script.ScriptException;
import static jdk.nashorn.internal.objects.NativeArray.map;
import jxl.write.WriteException;
import levelDatastructures.DepthGate;
import levelDatastructures.GateLevel;
import levelDatastructures.LevelCircuit;
import readers.MappedVerilogReader;
import signalProbability.ProbCircuit;

/**
 *
 * @author clayt
 */
 public class ThreadFaultInjection implements Runnable{

        private long threadID;
        private String inputStr;
        private String outputStr;
        private int startPos;
        private int endPosition;
        
        private int propagated_faults;
        
        private String genlibPATH;
        private MappedVerilogReader verilog_circuit;
        private ProbCircuit probCircuit;
        private LevelCircuit lCircuit;
        
        private ArrayList <Test_Item> threadSimulationList =  new ArrayList<>();
        private Circuit circuit;
        private CellLibrary cellLibrary;
        private LevelCircuit levelCircuit;
        private  ArrayList <Cell>  cells;
        
        
        private ConcurrentLinkedQueue<String> linkedQueueFaultFree; //= new ConcurrentLinkedQueue<Integer>(); 
        private ConcurrentLinkedQueue<String> linkedQueueFault; //= new ConcurrentLinkedQueue<Integer>(); 
        
        //private final Map<String, String> concurrentMap_output_free = new ConcurrentHashMap<>();;
        //private final Map<String, String> concurrentMap_output_fault = new ConcurrentHashMap<>();;
        
        private final ArrayList <String> inputVectors = new ArrayList<>();
        private final ArrayList <String> faultInjectionOutputArray = new ArrayList<>();
        private final ArrayList <String> faultFreeOutputArray = new ArrayList<>();
        private final ArrayList <String> faultSignalArray = new ArrayList<>();
        
        private ConcurrentLinkedQueue<String> faultSignalCuncorrentLinked = new ConcurrentLinkedQueue<>();;
       
        private final ArrayList <String> faultSignalBitFlipArray = new ArrayList<>();
        
        public ThreadFaultInjection(ArrayList <Test_Item> threadSimulationList, Circuit circuit, CellLibrary cellLibrary, LevelCircuit levelCircuit, int start, int end, String genlib, String circuitFilePath) throws IOException, ScriptException, Exception{
       
          
           this.threadSimulationList = threadSimulationList;
           this.circuit = circuit;
           this.cellLibrary = cellLibrary;
           this.levelCircuit = levelCircuit;
           this.startPos = start;
           this.endPosition = end;
           //this.genlibPATH = genlib;
           this.genlibPATH = "abc\\" + genlib;
           
           this.propagated_faults = 0;
           
           this.linkedQueueFaultFree = new ConcurrentLinkedQueue<>(); 
           this.linkedQueueFault = new ConcurrentLinkedQueue<>(); 
           this.faultSignalCuncorrentLinked = new ConcurrentLinkedQueue<>(); 
           
                /*Reading CellLibrary*/
                CellLibrary cellLib = new CellLibrary();
                this.cellLibrary = cellLib;
                this.cellLibrary.initLibrary(this.genlibPATH);
                
                //System.out.println("--- Reading celllib ...");
                //System.out.println("  - Avaliable logic gatesin this library: "+cellLib.getCells());
                /*----------------------*/
                
                /*Reading verilog*/
                MappedVerilogReader verilog_circuit_temp = new MappedVerilogReader(circuitFilePath, this.cellLibrary);
                this.verilog_circuit = verilog_circuit_temp;
                /*Circuit linked to verilog_circuit - init circuit*/
                this.circuit = this.verilog_circuit.getCircuit();
                //System.out.println("--- Reading verilog: " + this.verilog_circuit.getCirc().getName() + ".v");
                //System.out.println("Patterns : " + this.verilog_circuit.getGatePattern());
                /*----------------------*/
                
                
                /* Print circuit Specs
                System.out.println("\n------ Printing Circuit Specs: --------");
                this.PrintSpecs();
                System.out.println("---------------------------------------\n");
                ----------------------*/
                
                /*Circuit Probabilities */
                this.initLevelCircuit();
               
                /*Init ProbCircuits*/
                this.initProbCircuit();
                
                /*Init PTMs Const*/
                //cellLib.setPTMCells2(Float.valueOf("0.9999"));
                //cellLib.setPTMCells(new BigDecimal("0.9999"));
                this.cellLibrary.setPTMCells2(Float.valueOf("0.9999"));
                this.cellLibrary.setPTMCells(new BigDecimal("0.9999"));
        
        }
       
        private void startSimulationFaultFree() throws IOException, WriteException{   
            
            
            
            for (int i = 0; i < this.threadSimulationList.size(); i++) {
                    //System.out.println("Index : "+ this.threadSimulationList.get(i).getSimulationIndex() + "  - vec:" + this.threadSimulationList.get(i).getinputVector());
                    //this.insertInputVectors(cellLib, "selected", this.threadSimulationList.get(i).getinputVector());
                    this.insertInputVectors("selected", this.threadSimulationList.get(i).getinputVector());
                    this.propagateInputVectors(this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i).getinputVector(), this.threadSimulationList.get(i));
                    this.getPropagateFaultFreeResults( this.threadSimulationList.get(i).getinputVector(), this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i), i+1);
            }
                
            
        }

        public  ArrayList <Test_Item> getThreadSimulatinArray(){
            return this.threadSimulationList;
        }
        
        public String getThreadId(){
            return Integer.toString((int) this.threadID);
        }
        
        private void PrintSpecs(){
         System.out.println("Circuit : " + this.circuit.getName());
         System.out.println("- Logic Gates : " + this.circuit.getGates());
         System.out.println("- Inputs : " + this.circuit.getInputs());
         System.out.println("- Outputs : " + this.circuit.getOutputs());
         System.out.println("- Signals : " + this.circuit.getSignals());
        
     }
     
        private void initProbCircuit() {
        if(this.circuit != null) {
            
            this.lCircuit = this.levelCircuit; //Terminal.getInstance().getLevelCircuit();
            this.probCircuit = new ProbCircuit(this.circuit);
            //System.out.println("InitProbCircuit " + this.probCircuit.getName());
            
        } else {
            System.out.println("Circuit is null !!!");
        }
    }
    
        private  void initLevelCircuit(){    
        if(this.circuit != null) {
            //System.out.println("InitLevelCircuit");
            this.levelCircuit = new LevelCircuit(this.circuit);      
        }
        else
        {
            System.out.println("Circuit is null!!");
        }
    }
        
        private  void propagateInputVectors(int testNumber, ArrayList <Integer> vector, Test_Item thread_item) throws IOException, WriteException{
           
            this.threadID = (long) Thread.currentThread().getId();
            thread_item.setThreadID(this.threadID);
           
           //System.out.println("-> Propagating testNumber(" + testNumber + ")" + " - at Thread_ID - " + this.threadID );
           //System.out.println("  Vector: " + vector);
           ArrayList <GateLevel> gatesLevels = this.levelCircuit.getGateLevels();
           
           for (int j = 0; j < gatesLevels.size(); j++) {
                            
                ArrayList <Object> gatesInThisLevel = gatesLevels.get(j).getGates();
                
                for (int k = 0; k < gatesInThisLevel.size(); k++) {
                    String AwnsString = gatesInThisLevel.get(k).getClass().toString();
                    //System.out.println("Aws: "+ AwnsString);
                    if(AwnsString.equals("class levelDatastructures.DepthGate")){
                        Object object = gatesInThisLevel.get(k);
                        DepthGate gate = (DepthGate) object;
                        //gate.getGate().getType()
                        //System.out.println("              - Gate: "+ gatesInThisLevel.get(k)  + "  type: "+ gate.getGate().getType());
                        boolean outputGate = this.calculateFaultFreeOutputGateValue(gate.getGate().getType(), gate, gate.getGate().getInputs());  //Method calc the output from the gate
                      
                        
                        for (int s = 0; s < gate.getGate().getOutputs().size(); s++) {
                            
                            Signal sig = gate.getGate().getOutputs().get(s);
                            
                            //System.out.println(faultSig+" Sig EQUAL "+sig);

                            if(outputGate == true){    //Saida do GATE  = 1
                                thread_item.setSignalOriginalValue(1);
                                
                                
                                //sig.setLogicValue(1);
                                gate.getGate().getOutputs().get(s).setOriginalLogicValue(1);
                                gate.getGate().getOutputs().get(s).setLogicValue(1);
                                gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.TRUE);  
                                
                                /*
                                if(sig.getId().equals(faultSig.getId())){
                                   // System.out.println("@ "+faultSig+" Sig EQUAL "+sig);
                                    faultSig.setOriginalLogicValue(1);
                                }
                                */
                            }
                            else{           
                                thread_item.setSignalOriginalValue(0);
                                //sig.setLogicValue(0);
                                gate.getGate().getOutputs().get(s).setOriginalLogicValue(0);
                                gate.getGate().getOutputs().get(s).setLogicValue(0);
                                gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.FALSE);
                                
                                /*
                                if(sig.getId().equals(faultSig.getId())){
                                    //System.out.println("@ "+faultSig+" Sig EQUAL "+sig);
                                    faultSig.setOriginalLogicValue(0);
                                }
                                */
                            }
                            
                            //  System.out.println("              - Gate: "+ gatesInThisLevel.get(k)  + "  type: "+ gate.getGate().getType() + "Sig: " + sig +  " output: " + sig.getOriginalLogicValue());
                        }
                    }
               }
                
           }
           
         
     }
   
        public String getVectorString(ArrayList <Integer> vector){
            String O = vector.toString();
            String rO = O.replace("[", "");
            rO = rO.replace(",", "");
            rO = rO.replace("]", "");
            rO = rO.replace(" ", "");
            
            return rO;
        } 
        
        public ArrayList<String> getLinkedFaultFreeResults(){
            ArrayList<String> list = new ArrayList<>(this.linkedQueueFaultFree);
            return list;
        }
        
        public ArrayList<String> getLinkedFaultResults(){
            ArrayList<String> list = new ArrayList<>(this.linkedQueueFault);
            return list;
        }
         
        private  void getPropagateFaultFreeResults(ArrayList <Integer> vector, int testNumber, Test_Item thread_item, int id_num){
         
            ArrayList <Signal> signalsOutput = this.circuit.getOutputs();
            
            String outputFaultFree = "";
            
            for (int s = 0; s < signalsOutput.size(); s++) {   //Concatena a saida             
                 
                  outputFaultFree = outputFaultFree + signalsOutput.get(s).getOriginalLogicValue(); 
                
                  //System.out.println(testNumber + "           - Gate: " + signalsOutput.get(s).getOrigin() + " (" + signalsOutput.get(s).getOrigin().getType() + ") - Output: "+ signalsOutput.get(s) + " - Logic value: "+ signalsOutput.get(s).getLogicValue() + " [" + signalsOutput.get(s).getLogicValueBoolean() + "]" );             
                  
                 
                 //System.out.println(testNumber + " free - Gate: " + signalsOutput.get(s).getOrigin() + " (" + signalsOutput.get(s).getOrigin().getType() + ") - Output: "+ signalsOutput.get(s) + " - Logic value: "+ signalsOutput.get(s).getOriginalLogicValue() + " [" + signalsOutput.get(s).getLogicValueBoolean() + "]" );             
            }
            
            /*
            String O = vector.toString();
            String rO = O.replace("[", "");
            rO = rO.replace(",", "");
            rO = rO.replace("]", "");
            rO = rO.replace(" ", "");
            //String S = Integer.toString(id_num);
            //int o = Integer.parseInt(rO);
            */
            
            
            //this.concurrentMap_output_free.put(this.getVectorString(vector), outputFaultFree);
            
            //this.linkedQueueFaultFree.add(outputFaultFree);
            
            thread_item.setFaultFreeOutput(outputFaultFree);

         //   System.out.println("free (" + testNumber  +") Input Vector: "+ vector + "  Output: " + outputFaultFree ); //+  "           " + this.concurrentMap_output_free + " - " + this.linkedQueue  +" -> ID:" + this.threadID);
            
           
           
     }
       
        private void getFaultInjectionResults( ArrayList <Integer> vector, int testNumber, Test_Item thread_item){
         
            ArrayList <Signal> signalsOutput = this.circuit.getOutputs();
            
            String outputInjection = "";
            
            for (int s = 0; s < signalsOutput.size(); s++) { //All outputs to str   
                
                if(this.circuit.getOutputs().get(s).getId().equals(thread_item.getFaultSignal().getId())){
                     
                     if(this.circuit.getOutputs().get(s).getLogicValueBoolean()){ //Bitflip output signal 
                          int flag = 0;
                          //this.circuit.getOutputs().get(s).setLogicValue(0);
                          outputInjection = outputInjection + 0;
                           System.out.println("0: Vec: " + thread_item.getinputVector() + "     "+ this.circuit.getOutputs().get(s) + " Output: Expect(" + this.circuit.getOutputs().get(s).getLogicValue()+ ")" 
                                   + " Boolean :(" + this.circuit.getOutputs().get(s).getLogicValueBoolean()+ ")" );
                          
                     }else{
                          int flag = 1;
                          //this.circuit.getOutputs().get(s).setLogicValue(0);
                          outputInjection = outputInjection +  1;
                          
                          System.out.println("1: Vec: " + thread_item.getinputVector() + "     "+ this.circuit.getOutputs().get(s) + " Output: Expect(" + this.circuit.getOutputs().get(s).getLogicValue()+ ")" 
                                   + " Boolean :(" + this.circuit.getOutputs().get(s).getLogicValueBoolean()+ ")" );
                        
                     }
                    
                     
                 }else{
                     outputInjection = outputInjection +  this.circuit.getOutputs().get(s).getLogicValue();
                 }
                
                // outputInjection = outputInjection + signalsOutput.get(s).getOriginalLogicValue(); 
               //  System.out.println("           - Gate: " + signalsOutput.get(s).getOrigin() + " (" + signalsOutput.get(s).getOrigin().getType() + ") - Output: "+ signalsOutput.get(s) + " - Logic value: "+ signalsOutput.get(s).getLogicValue() + " [" + signalsOutput.get(s).getLogicValueBoolean() + "]" );             
                //System.out.println("free - Gate: " + signalsOutput.get(s).getOrigin() + " (" + signalsOutput.get(s).getOrigin().getType() + ") - Output: "+ signalsOutput.get(s) + " - Logic value: "+ signalsOutput.get(s).getOriginalLogicValue() + " [" + signalsOutput.get(s).getLogicValueBoolean() + "]" );             
            }
            /*
            String O = vector.toString();
            String fO = O.replace("[", "");
            fO = fO.replace(",", "");
            fO = fO.replace("]", "");
            fO = fO.replace(" ", "");
            */
            //String S = Integer.toString(id_num);
            //int o = Integer.parseInt(rO);
            
            //this.concurrentMap_output_fault.put(this.getVectorString(vector), outputInjection );
            
            //this.linkedQueueFault.add(outputInjection);
            //System.out.println(vector + " OUT INJECTON: " + outputInjection);
           
            thread_item.setFaultOutput(outputInjection);

            //System.out.println(" --->> Fault (" + testNumber  +") Input Vector: "+ vector + "  Output: " + outputInjection + " -> ID:" + this.threadID);
            
           
     }
        
        public  ArrayList <String> get_inputListValuesStr(){
            return this.inputVectors;
        }
       
        public  ArrayList <String> get_outputListValueStr(){
            return this.faultInjectionOutputArray;
        }
        
        public  ArrayList <String> get_outputOriginalListValueStr(){
            return this.faultFreeOutputArray;
        }
        
        public  ArrayList <String> get_signalFaultList(){
            return this.faultSignalArray;
        }
        
        public  ArrayList <String> get_signalFaultListCuncorrentLinked(){
             ArrayList<String> list = new ArrayList<>(this.faultSignalCuncorrentLinked);
            return list;
        }
        
        public  ArrayList <String> get_signalFaultListBitflip(){
            return this.faultSignalBitFlipArray;
        }
       
        private ArrayList <Test_Item> getSimulationList(){
            return this.threadSimulationList;
        }
        
        private String settingFaultInjectionResults(){
            
            int ini = this.startPos;
          
            
            String r = "";
            for (int i = 0; i < this.threadSimulationList.size(); i++) {
            
                /*
                System.out.println("fault ( " + (ini+1) + ") - Input vector: " + this.threadSimulationList.get(i).getinputVector().toString() + " - F. free(out): "+ this.threadSimulationList.get(i).getOrignalOutput() + " Fault (out): "+  this.threadSimulationList.get(i).getFaultOutput() +"   -  faultSig (" + this.threadSimulationList.get(i).getFaultSignal()
                            + ") - ("+ this.threadSimulationList.get(i).getSignalOriginalValue() + ")to("+  this.threadSimulationList.get(i).getFaultSignalValue()
                            + ")" + " ->Th:" +this.threadID);
                */
                
                /*
                r = r + ini + " - Input: " + this.threadSimulationList.get(i).getinputVector().toString() + " - F. free(out): "+ this.threadSimulationList.get(i).getOrignalOutput() + " Fault (out): "+  this.threadSimulationList.get(i).getFaultOutput() +"   -  fault (" + this.threadSimulationList.get(i).getFaultSignal()
                            + ") - ("+ this.threadSimulationList.get(i).getFaultSignal().getOriginalLogicValue() + ")to("+  this.threadSimulationList.get(i).getFaultSignalValue()
                            + ")\n";
                */
                ini++;
                
              // in.add(this.threadSimulationList.get(i).getinputVector().toString());
              // out.add(this.threadSimulationList.get(i).getFaultOutput());
               this.inputVectors.add(this.threadSimulationList.get(i).getinputVector().toString()); //Input Vector
               
                String freeFaultOutput_i, faultOutput_i;
                
                freeFaultOutput_i = this.threadSimulationList.get(i).getOrignalOutput();
                faultOutput_i = this.threadSimulationList.get(i).getFaultOutput();
                
                
                if(!freeFaultOutput_i.equals(faultOutput_i)){
                       //System.out.println(i + "  freeFaultOutput_i: " + freeFaultOutput_i + "   faultOutput_i: " + faultOutput_i);
                       this.propagated_faults++;
                }
                
                /*
                this.faultFreeOutputArray.add(freeFaultOutput_i); // Fault Free output
                this.faultInjectionOutputArray.add(faultOutput_i); //  //Fault injection output
                
                this.faultSignalArray.add(this.threadSimulationList.get(i).getFaultSignal().toString());
                
                this.faultSignalCuncorrentLinked.add(this.threadSimulationList.get(i).getFaultSignal().getId());
                
               // System.out.println("FaultSIgnals old - new: " + this.faultSignalArray + " - " + this.faultSignalCuncorrentLinked);
                
                //System.out.println("TH: " + this.threadID + " vec: " + this.threadSimulationList.get(i).getinputVector()  + "-> sig " + this.threadSimulationList.get(i).getFaultSignal().toString() + " " + this.threadSimulationList.get(i).getSignalOriginalValue() + "  to " +  this.threadSimulationList.get(i).getFaultSignalValue());
                //this.faultSignalBitFlipArray.add("("+ this.threadSimulationList.get(i).getSignalOriginalValue() + ")to("+  this.threadSimulationList.get(i).getFaultSignalValue()+ ")" );
                 this.faultSignalBitFlipArray.add(this.setBitFlip(i));
                */
            }
          
            return r;
        }        
       
        public int getPropagatedFaults(){
            return this.propagated_faults;
        }
        
        private String setBitFlip(int i){
            String bitflip;
            String r = "";
           // "("+ this.threadSimulationList.get(i).getSignalOriginalValue() + ")to("+  this.threadSimulationList.get(i).getFaultSignalValue()+ ")" 
            if(this.threadSimulationList.get(i).getSignalOriginalValue() == 0){
                bitflip = "1";
            }
            else{
                bitflip = "0";
            }
            
            r = "("+ this.threadSimulationList.get(i).getSignalOriginalValue() + ")to("+ bitflip + ")";
            
            return r;
            
            
        }
        
        private  void insertInputVectors(String Option, ArrayList <Integer> vector){
              //cellLib.teste();
              
              // ArrayList <Cell>  cells;
               
               this.cells = this.cellLibrary.getCells();//cellLib.getCells();
                
                //System.out.println("VECTOR : "+ vector);
                // - ---------------------------------------- Inserting input vector ------------------------------------------------------
                //System.out.println("");
               
                switch (Option){
                       
                    case "random":
                        ArrayList <Signal> inputSignais = this.circuit.getInputs();
                        //System.out.println("Input Signals: " + inputSignais + " Size: " + inputSignais.size());

                        Random gerador = new Random();
                        //vetor de entrada aleatório
                        for (int i = 0; i < inputSignais.size(); i++) {
                                int randomLogicValue = gerador.nextInt(2);
                                //inputSignais.get(i).setLogicValue(randomLogicValue); //old
                                this.circuit.getInputs().get(i).setLogicValue(randomLogicValue);
                                this.circuit.getInputs().get(i).setOriginalLogicValue(vector.get(i));
                               // System.out.println(inputSignais.get(i)+" : '"+inputSignais.get(i).getLogicValue()+"'");
                        }
                       
                    break;
                    
                    case "selected":
                        ArrayList <Signal> inputSignals = this.circuit.getInputs();
                       // System.out.println("Selected vector" + vector + " - Input Signals: " + inputSignals + " Size: " + inputSignals.size());

                        //Random gerador = new Random();
                        //vetor de entrada aleatório
                        
                        for (int i = 0; i < inputSignals.size(); i++) {
                                //int randomLogicValue = gerador.nextInt(2);
                                 //inputSignals.get(i).setLogicValue(vector.get(i));
                                 this.circuit.getInputs().get(i).setLogicValue(vector.get(i));
                                 this.circuit.getInputs().get(i).setOriginalLogicValue(vector.get(i));
                                //System.out.println(inputSignals.get(i)+" : '"+inputSignals.get(i).getLogicValue()+"'");
                        }
                   break;
                       
                }
      
                
               
              
     }

        private  void propagateFaultInjections(int testNumber, ArrayList <Integer> vector, Signal faultSig, int index, Test_Item thread_item) throws IOException, WriteException{
           
             this.threadID = (long) Thread.currentThread().getId();
           
           //System.out.println("-> Propagating testNumber(" + testNumber + ")" + " - at Thread_ID - " + this.threadID );
           //System.out.println("  Vector: " + vector);
           ArrayList <GateLevel> gatesLevels = this.levelCircuit.getGateLevels();
           
           for (int j = 0; j < gatesLevels.size(); j++) {
                            
                ArrayList <Object> gatesInThisLevel = gatesLevels.get(j).getGates();
                
                for (int k = 0; k < gatesInThisLevel.size(); k++) {
                    String AwnsString = gatesInThisLevel.get(k).getClass().toString();
                    //System.out.println("Aws: "+ AwnsString);
                    if(AwnsString.equals("class levelDatastructures.DepthGate")){
                        Object object = gatesInThisLevel.get(k);
                        final DepthGate gate = (DepthGate) object;
                        //gate.getGate().getType()
                        //System.out.println("              - Gate: "+ gatesInThisLevel.get(k)  + "  type: "+ gate.getGate().getType());
                        boolean gateResult = this.calculateOutputFacultInjectionGateValue(gate.getGate().getType(), gate, gate.getGate().getInputs(), faultSig, thread_item);  //Method calc the output from the gate cal bitflip
                        
                        for (int s = 0; s < gate.getGate().getOutputs().size(); s++) {
                            
                            Signal sig = gate.getGate().getOutputs().get(s);
                            
                            if(gateResult == true){  //Gate Output
                                gate.getGate().getOutputs().get(s).setOriginalLogicValue(1);
                                gate.getGate().getOutputs().get(s).setLogicValue(1);
                                gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.TRUE);  
                                
                              
                                
                                if(sig.getId().equals(faultSig.getId())){
                                   // System.out.println("@ "+faultSig+" Sig EQUAL "+sig);
                                       faultSig.setOriginalLogicValue(1);
                                     faultSig.setLogicValue(0); // bitfip
                                    faultSig.setLogicValueBoolean(Boolean.FALSE);
                                    
                                    thread_item.setSignalOriginalValue(1);
                                     thread_item.setFaultSignalValue(0);
                                   
                               
                                }
                                
                            }
                            else{           
                                gate.getGate().getOutputs().get(s).setOriginalLogicValue(0);
                                gate.getGate().getOutputs().get(s).setLogicValue(0);
                                gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.FALSE);
                                
                                
                                
                                if(sig.getId().equals(faultSig.getId())){
                                   // System.out.println("@ "+faultSig+" Sig EQUAL "+sig);
                                    faultSig.setOriginalLogicValue(0);
                                   faultSig.setLogicValue(1); // bitfip
                                   faultSig.setLogicValueBoolean(Boolean.TRUE);
                                    
                                   thread_item.setSignalOriginalValue(0);
                                    thread_item.setFaultSignalValue(1);
                                }
                                
                                
                            }
                        }
  
                      }
                  }
               }
                
           }

        public  void startSimulationFaultInjection() throws IOException, WriteException{
             
            //System.out.println(" ---- \nStart Printing Fault injection - ThreadID: " + this.threadID);
            
            
            
            for (int i = 0; i < this.threadSimulationList.size(); i++) {
                this.insertInputVectors("selected", this.threadSimulationList.get(i).getinputVector());
                this.propagateFaultInjections(this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i).getinputVector(), this.threadSimulationList.get(i).getFaultSignal(), i,  this.threadSimulationList.get(i));
                this.getFaultInjectionResults(this.threadSimulationList.get(i).getinputVector(), this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i));
                
                /*
                if(i == (this.threadSimulationList.size()/2)){
                     LocalDateTime myDateObj = LocalDateTime.now();
                        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        String formattedDate = myDateObj.format(myFormatObj);
                     System.out.println("->" + formattedDate + " HALF Simulation test ended: " + this.threadID + "   size: "+this.threadSimulationList.size());
                }
                */
               
            }
             this.settingFaultInjectionResults();
            
            // System.out.println(" ---- ENDED Simulation Printing Fault injection - ThreadID: " + this.threadID);
        }
        
        public ArrayList <Signal> calcInternSignals(){
         //System.out.println("Sort Fault in the input injection");
         ArrayList <Signal> signalList = new ArrayList<>();
         signalList = this.circuit.getSignals();
         
         ArrayList <Signal> r = new ArrayList<>();
                for (int i = 0; i < signalList.size(); i++) {
                        if(!((this.circuit.getInputs().contains(signalList.get(i))) || (this.circuit.getOutputs().contains(signalList.get(i))))){
                           //this.internSignals.add(signalList.get(i));
                           r.add(signalList.get(i));
                        }
                    
                }
                //System.out.println(" --> "+ this.internSignals);  
                //System.out.println("Signals list: "+ signalList.toString());
         return r;
     }
        
        public  void startSimulationFaultInjection_ALL_POSSIBILITIES() throws IOException, WriteException{
             
            System.out.println(" ---- \nALL POSSIBILITIES Start Printing Fault injection - ThreadID: " + this.threadID);
            ArrayList <Signal> signalList = new ArrayList<>();
            signalList =  this.calcInternSignals();
            
            for (int i = 0; i < this.threadSimulationList.size(); i++) {
                this.insertInputVectors("selected", this.threadSimulationList.get(i).getinputVector());
                for (int j = 0; j < signalList.size(); j++) {
                        this.propagateFaultInjections(this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i).getinputVector(), signalList.get(i), i,  this.threadSimulationList.get(i));
                }
                
                this.getFaultInjectionResults(this.threadSimulationList.get(i).getinputVector(), this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i));
            }
             this.settingFaultInjectionResults();
        }
        
        private  boolean calculateOutputFacultInjectionGateValue(Cell cells, DepthGate gate, ArrayList <Signal> inputsSignals, Signal faultSig,  Test_Item thread_item){
                //System.out.println("inn... + " + thread_item.getItem().toString());      
                final Map<ArrayList<Boolean>, Boolean> comb = cells.getComb();
                final ArrayList <Boolean> input = new ArrayList<>();
                final ArrayList <Integer> signals = new ArrayList<>();
                
                for (int index = 0; index < inputsSignals.size(); index++) {
                        
                        if(inputsSignals.get(index).getId().equals(faultSig.getId())){ //bit-flip 
                           // System.out.println("Falha In");
                            //System.out.println("entrou");
                            if(inputsSignals.get(index).getOriginalLogicValue() == 0){ //Efetua o bitflip
                                thread_item.getFaultSignal().setOriginalLogicValue(0);
                                thread_item.getFaultSignal().setLogicValue(1);
                                thread_item.getFaultSignal().setLogicValueBoolean(Boolean.TRUE);
                                
                                inputsSignals.get(index).setOriginalLogicValue(thread_item.getFaultSignal().getOriginalLogicValue());
                                inputsSignals.get(index).setLogicValue(thread_item.getFaultSignal().getLogicValue());
                                inputsSignals.get(index).setLogicValueBoolean(thread_item.getFaultSignal().getLogicValueBoolean());
                                
                                faultSig.setOriginalLogicValue(thread_item.getFaultSignal().getOriginalLogicValue());
                                faultSig.setLogicValue(thread_item.getFaultSignal().getLogicValue());
                                faultSig.setLogicValueBoolean(thread_item.getFaultSignal().getLogicValueBoolean());
                                
                                        thread_item.setSignalOriginalValue(thread_item.getFaultSignal().getOriginalLogicValue());
                                        thread_item.setFaultSignalValue(thread_item.getFaultSignal().getLogicValue());
                                
                               
                                /*
                                thread_item.setSignalOriginalValue(0);
                                thread_item.setFaultSignalValue(1);
                              
                                inputsSignals.get(index).setLogicValue(1);
                                inputsSignals.get(index).setLogicValueBoolean(Boolean.TRUE);
                                */
                                
                                /* thread item */
                                /*
                                 thread_item.getFaultSignal().setOriginalLogicValue(0);
                                 thread_item.getFaultSignal().setLogicValue(1);
                                 thread_item.getFaultSignal().setLogicValueBoolean(Boolean.TRUE);
                                */
                                 
                                 /*Fault Sig*/
                                /* 
                                faultSig.setOriginalLogicValue(0);
                                 faultSig.setLogicValue(1);
                                 faultSig.setLogicValueBoolean(Boolean.TRUE);
                                 */
                            }
                            else{
                                 thread_item.getFaultSignal().setOriginalLogicValue(1);
                                 thread_item.getFaultSignal().setLogicValue(0);
                                 thread_item.getFaultSignal().setLogicValueBoolean(Boolean.FALSE);
                                
                                 inputsSignals.get(index).setOriginalLogicValue(thread_item.getFaultSignal().getOriginalLogicValue());
                                 inputsSignals.get(index).setLogicValue(thread_item.getFaultSignal().getLogicValue());
                                 inputsSignals.get(index).setLogicValueBoolean(thread_item.getFaultSignal().getLogicValueBoolean());
                                 
                                    faultSig.setOriginalLogicValue(thread_item.getFaultSignal().getOriginalLogicValue());
                                    faultSig.setLogicValue(thread_item.getFaultSignal().getLogicValue());
                                    faultSig.setLogicValueBoolean(thread_item.getFaultSignal().getLogicValueBoolean());
                                    
                                     thread_item.setSignalOriginalValue(thread_item.getFaultSignal().getOriginalLogicValue());
                                        thread_item.setFaultSignalValue(thread_item.getFaultSignal().getLogicValue());
                                 
                            }
                          // System.out.println(" -> fault injected (" + faultSig + ")" +  " - O(v):"+inputsSignals.get(index).getOriginalLogicValue() + "  N(v):"+inputsSignals.get(index).getLogicValue());                   
                        }
                        
                        signals.add(inputsSignals.get(index).getLogicValue());
                        
                        if(inputsSignals.get(index).getLogicValue() == 0){
                            input.add(Boolean.FALSE);
                        }
                        if(inputsSignals.get(index).getLogicValue() == 1){
                            input.add(Boolean.TRUE);
                        }
                }
                
                //System.out.println("                                Input Signal: " + inputsSignals + " v: "+input);
                Object output = "stuck";
                
                String r = "";
                for (Map.Entry<ArrayList<Boolean>, Boolean> entry : comb.entrySet()){
                         if(entry.getKey().equals(input)){
                             //System.out.println("Input Finded: " + entry.getKey() + " output " + entry.getValue());
                             //Gate k = null;
                             boolean x = entry.getValue();
                             output = entry.getValue();
                             r = "";//output.toString();
                             if(x == true){
                                 r = "1";
                                 
                             }
                             if(x == false){
                                 r = "0";
                             }
                           
                         }
                }
                if(!output.equals("stuck")){
                   //System.out.println("               Gate: " + gate.getGate() + "(" +cells.getName() + ") inputSignals: " + gate.getGate().getInputs() + " -> values: "  + signals + " ~ " + input  + " -> Output " + gate.getGate().getOutputs() + " is: " + r + " ["+ output +"] ------ \n");
                }else{
                    System.out.println("ERROR stuck !!!!! o: "+output);
                    
                }
               
         
         return (boolean) output;
     }    
          
        private  boolean calculateFaultFreeOutputGateValue(Cell cells, DepthGate gate,ArrayList <Signal> inputsSignals){
                      
                Map<ArrayList<Boolean>, Boolean> comb = cells.getComb();
                ArrayList <Boolean> input = new ArrayList<>();
                ArrayList <Integer> signals = new ArrayList<>();
                for (int index = 0; index < inputsSignals.size(); index++) {
                       
                        
                        signals.add(inputsSignals.get(index).getLogicValue());
                        
                        if(inputsSignals.get(index).getLogicValue() == 0){
                            input.add(Boolean.FALSE);
                        }
                        if(inputsSignals.get(index).getLogicValue() == 1){
                            input.add(Boolean.TRUE);
                        }
                }
                
                //System.out.println("                                Input Signal: " + inputsSignals + " v: "+input);
                Object output = "stuck";
                
                String r = "";
                for (Map.Entry<ArrayList<Boolean>, Boolean> entry : comb.entrySet()){
                         if(entry.getKey().equals(input)){
                             //System.out.println("Input Finded: " + entry.getKey() + " output " + entry.getValue());
                             //Gate k = null;
                             boolean x = entry.getValue();
                             output = x;
                             r = output.toString();
                             if(x == true){
                                 r = "1";
                                 
                                 
                                 //¹¹output = x;
                             }
                             if(x == false){
                                 r = "0";
                                 //output = x;
                             }
                           //output = x;
                         }
                }
                if(!output.equals("stuck")){
                   //System.out.println("   xxaxaxaxa            Gate: " + gate.getGate() + "(" +cells.getName() + ") inputSignals: " + gate.getGate().getInputs() + " -> values: "  + signals + " ~ " + input  + " -> Output " + gate.getGate().getOutputs() + " is: " + r + " ["+ output +"] ------ \n");
                }else{
                    System.out.println("ERROR stuck !!!!! out : " + output);
                }
               
         
         return (boolean) output;
     }    
        
     
        @Override
        public  void run() {
            try {
                //propagateTh();
                //getPropagateFaultFreeResults(cellLibrary, vector);
                startSimulationFaultFree();
                startSimulationFaultInjection();
                //printResults();
            } catch (IOException ex) {
                Logger.getLogger(ThreadFaultInjection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WriteException ex) {
                Logger.getLogger(ThreadFaultInjection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
     }
     