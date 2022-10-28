/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicSimulator;

import datastructures.Cell;
import datastructures.CellLibrary;
import datastructures.Circuit;
import datastructures.Signal;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptException;

import jxl.write.WriteException;
import levelDatastructures.DepthGate;
import levelDatastructures.GateLevel;
import levelDatastructures.LevelCircuit;
import readers.MappedVerilogReader;
import signalProbability.ProbCircuit;

/**
 * teste Clayton Dale Grêmio
 * teste2
 * @author clayt
 */

 public class LogicSimulator implements Runnable{

        private long threadID;
        //private String inputStr;
        //private String outputStr;
        private final int startPos;
        private final int endPosition;
        private int propagated_faults;
        private final String genlibPATH;
        private final MappedVerilogReader verilog_circuit;
        private ProbCircuit probCircuit;
        private LevelCircuit lCircuit; 
        private List<TestVectorInformation> threadSimulationList =  Collections.synchronizedList(new ArrayList<TestVectorInformation>()); //new ArrayList<>();
        private Circuit circuit;
        private CellLibrary cellLibrary;
        private LevelCircuit levelCircuit;
        private  ArrayList <Cell>  cells; 
        private final ConcurrentLinkedQueue<String> linkedQueueFaultFree; //= new ConcurrentLinkedQueue<Integer>(); 
        private final ConcurrentLinkedQueue<String> linkedQueueFault; //= new ConcurrentLinkedQueue<Integer>();

        public ArrayList <String> parsedNetlistContent = new ArrayList<>();

        private String inGate;
        private int lastposPlot;
        //private final Map<String, String> concurrentMap_output_free = new ConcurrentHashMap<>();;
        //private final Map<String, String> concurrentMap_output_fault = new ConcurrentHashMap<>();;
        
        private final ArrayList <String> inputVectors = new ArrayList<>();

        private final ArrayList <DepthGate> gates_passed = new ArrayList<>();
        private final ArrayList <String> faultInjectionOutputArray = new ArrayList<>();
        private final ArrayList <String> faultFreeOutputArray = new ArrayList<>();
        private final ArrayList <String> faultSignalArray = new ArrayList<>();
        private ConcurrentLinkedQueue<String> faultSignalCuncorrentLinked = new ConcurrentLinkedQueue<>();;
        private final ArrayList <String> faultSignalBitFlipArray = new ArrayList<>();

        /*new*/
        private Map <String, SensitiveCell> sensitive_cells = new HashMap<>();
        private float sum_sensitive_cells_area;
        private int tempIndex;
        private String mode;

        public int bitflipcounter;


        public LogicSimulator(ArrayList <TestVectorInformation> threadSimulationList, Circuit circuit, CellLibrary cellLibrary, LevelCircuit levelCircuit, int start, int end, String genlib, String circuitFilePath) throws IOException, ScriptException, Exception{
       
          
           this.threadSimulationList = threadSimulationList;
           this.circuit = circuit;
           this.cellLibrary = cellLibrary;
           this.levelCircuit = levelCircuit;
           this.startPos = start;
           this.endPosition = end;
           this.genlibPATH = genlib;
           //this.genlibPATH = "abc\\" + genlib;
           
           this.propagated_faults = 0;

           this.lastposPlot = 0;

           this.bitflipcounter = 0;

           this.tempIndex = -1;
           
           this.linkedQueueFaultFree = new ConcurrentLinkedQueue<>(); 
           this.linkedQueueFault = new ConcurrentLinkedQueue<>(); 
           this.faultSignalCuncorrentLinked = new ConcurrentLinkedQueue<>();

           this.sum_sensitive_cells_area = 0;
           
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
                
                
                /* Print circuit Specs */
                //System.out.println("\n------ Printing Circuit Specs: --------");
                this.PrintSpecs();
                //System.out.println("---------------------------------------\n");
                
                
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

        public void setSensitiveCellsMap(Map <String, SensitiveCell> sensitive_cells){
            this.sensitive_cells = sensitive_cells;
        }

        public List<TestVectorInformation> getthreadSimulationList(){
            return this.threadSimulationList;
        }

        public ArrayList<DepthGate> getGatesPassed (){
            return  this.gates_passed;
        }

    public ArrayList <String> getParsedNetlistContent() {
            return this.parsedNetlistContent;
    }

    public String getStartendPos(){
          return ("Pos: " + this.startPos + " - " + this.endPosition);
        }
       
        private void startSimulationFaultFree() throws IOException, WriteException{   
 
            for (int i = 0; i < this.threadSimulationList.size(); i++) {
                    this.insertInputVectors("selected", this.threadSimulationList.get(i).getinputVector());
                    this.propagateInputVectors(this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i).getinputVector(), this.threadSimulationList.get(i));
                    this.getPropagateFaultFreeResults( this.threadSimulationList.get(i).getinputVector(), this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i), i+1);
            }  
        }

        private void startCalculationSensitiveAreas() throws IOException, WriteException{

            for (int i = 0; i < this.threadSimulationList.size(); i++){
                this.insertInputVectors("selected", this.threadSimulationList.get(i).getinputVector());
                this.propagateInputVectorsForSensitiveAreaCalculation(this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i).getinputVector(), this.threadSimulationList.get(i));
                //this.getPropagateFaultFreeResults( this.threadSimulationList.get(i).getinputVector(), this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i), i+1);
                //System.out.println("------------------------- vec: " +  this.threadSimulationList.get(i).getSimulationIndex() +  " sum: " + this.threadSimulationList.get(i).getSum_sensitive_cells_area() + "--------------------------------\n");
            }

        }

    private void parseVerilogToGenerateSpiceNetList() throws IOException, WriteException{

       // for (int i = 0; i < this.threadSimulationList.size(); i++){
            //this.insertInputVectors("selected", this.threadSimulationList.get(i).getinputVector());
            int i = 0;
            String parsedNetlist = "";
            if(this.circuit.getInputs().size() <= 10){ //small benchmark circuits
                parsedNetlist = this.createSpiceNetlist("A", "1");
            }
            else{
                parsedNetlist = this.createSpiceNetlistLargerCircuits(this.threadSimulationList.get(0));
            }

            //this.parsedNetlistContent = parsedNetlist;
            //this.getPropagateFaultFreeResults( this.threadSimulationList.get(i).getinputVector(), this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i), i+1);
            //System.out.println("------------------------- vec: " +  this.threadSimulationList.get(i).getSimulationIndex() +  " sum: " + this.threadSimulationList.get(i).getSum_sensitive_cells_area() + "--------------------------------\n");
       // }

    }

        public List <TestVectorInformation> get_threadSimulationList(){
            return this.threadSimulationList;
        }

        private void startSimulationFaultFreeMTF() throws IOException, WriteException{

            for (int i = 0; i < this.threadSimulationList.size(); i++) {
                this.insertInputVectorsMTF("selected", this.threadSimulationList.get(i).getinputVector(), threadSimulationList.get(i));
                this.propagateInputVectorsMTF(this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i).getinputVector(), this.threadSimulationList.get(i));
                this.getPropagateFaultFreeResults( this.threadSimulationList.get(i).getinputVector(), this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i), i+1);
            }
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = myDateObj.format(myFormatObj);

            System.out.println("Golden --- Finished thd : " + threadID + "  at: " + formattedDate + " NUMBER OF BITFLIPS: " + this.bitflipcounter);
        }

        public  List <TestVectorInformation> getThreadSimulatinArray(){
            return this.threadSimulationList;
        }
        
        public String getThreadId(){
            
            return Integer.toString((int) this.threadID);
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
        
    private  void propagateInputVectors(int testNumber, ArrayList <Integer> vector, TestVectorInformation thread_item) throws IOException, WriteException{
           
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
                            /*
                             System.out.println("              - Gate: "+ gatesInThisLevel.get(k)  
                                     +  "  type: "+ gate.getGate().getType() 
                                     +  " - Inputs: " + gate.getGate().getInputs().get(0) + " value: " + gate.getGate().getInputs().get(0).getLogicValue()
                                     +  " - Inputs: " + gate.getGate().getInputs().get(1)+ " value: " + gate.getGate().getInputs().get(1).getLogicValue()
                                     +  "              - output: " + sig.getOriginalLogicValue());
                            */
                            }
                    }
               }
                
           }
           
         
     }

    private  void propagateInputVectorsForSensitiveAreaCalculation(int testNumber, ArrayList <Integer> vector, TestVectorInformation thread_item) throws IOException, WriteException{

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
                    boolean outputGate = this.calculateFaultFreeOutputGateValueForSensitiveAreaCalculation(gate.getGate().getType(), gate, gate.getGate().getInputs(), thread_item);  //Method calc the output from the gate


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
                            /*
                             System.out.println("              - Gate: "+ gatesInThisLevel.get(k)
                                     +  "  type: "+ gate.getGate().getType()
                                     +  " - Inputs: " + gate.getGate().getInputs().get(0) + " value: " + gate.getGate().getInputs().get(0).getLogicValue()
                                     +  " - Inputs: " + gate.getGate().getInputs().get(1)+ " value: " + gate.getGate().getInputs().get(1).getLogicValue()
                                     +  "              - output: " + sig.getOriginalLogicValue());
                            */
                    }
                }
            }

        }


    }

    private String createSpiceNetlist(String SensitiveNode, String bitflipValue) throws IOException, WriteException{

            String template = "" +
                    "* Função Transiente\n" +
                    ".include 45nm_HP.pm\n" +
                    ".include Library.txt\n" +
                    "\n" +
                    "* Definindo a temperatura de operação\n" +
                    "   .TEMP 25\n" +
                    "\n" +
                    "* Declarando parâmetros que serão utilizados nas simulações\n" +
                    "   .param supply = 1.0\n" +
                    "\n" +
                    "* Declaração das fontes\n" +
                    "\tVvdd vdd 0 1.0\n" +
                    "\tVvss vss 0 0" +
                    "\n " +
                    "" +
                    "* Fontes de Tensão\n";


        this.threadID = (long) Thread.currentThread().getId();
        //thread_item.setThreadID(this.threadID);

        ArrayList <GateLevel> gatesLevels = this.levelCircuit.getGateLevels();

        ArrayList <String> gatesNetlist = new ArrayList<>();

        String outputsCapacitance = " ";
        String plotOutput = " ";
        int sizeInputs =  (int) Math.pow(2, this.circuit.getInputs().size() + this.circuit.getOutputs().size())/2;
        int size = sizeInputs;



        String plot= "plot ";

        int id_node = 1;

        /*vA A 0 PULSE (0 1.0 8n 1p 1p 8n 16n)
         vB B 0 PULSE (0 1.0 4n 1p 1p 4n 8n)
         vC C 0 PULSE (0 1.0 2n 1p 1p 2n 4n)
         vd D 0 PULSE (0 1.0 1n 1p 1p 1n 2n)
         */

        ArrayList <String> concat_inputs = new ArrayList<>();
        int size_temp = sizeInputs;
        int size_final = size_temp;
        int sum = 5 ;
        String input_values = "";
        for (Signal x: this.circuit.getInputs()){
            template = template + "v"+x.getId().toString() + " " + x.getId().toString() + " 0 PULSE (0 1.0 "+ size_temp/2 + "n 1p 1p " + size_temp/2 + "n " + size_temp + "n)"  + "\n";
            //plot = plot + "v(" + x.getId().toString() + ")+" + sizeInputs*2 + " ";//;"plot v(G1gat)+8 V(G2gat)+6 V(G6gat)"
            plot = plot + "v(" + x.getId().toString() + ")+" + sum + " ";;//plot + "v(" + x.getId().toString() + ")+" + sizeInputs + " ";//;"plot v(G1gat)+8 V(G2gat)+6 V(G6gat)"
            //sizeInputs = sizeInputs/2;
            //sizeInputs = sizeInputs - 2;
            size_temp = size_temp / 2;
            sizeInputs = sizeInputs/2;
            sum = sum + 5;

            //input_values = input_values +
        }
        int temp = sizeInputs;
        String ouputs = "";
        for (Signal z: this.circuit.getOutputs()){
            //Cload G6gat 0 1f
            //template = template + "v"+x.getId().toString() + " " + x.getId().toString() + " 0 PULSE (0 1.0 "+ sizeInputs + "n 1p 1p " + sizeInputs + "n " + sizeInputs*2 + "n)"  + "\n";
            //plot = plot + "v(" + x.getId().toString() + ")+" + sizeInputs + " ";//;"plot v(G1gat)+8 V(G2gat)+6 V(G6gat)"
            //sizeInputs = sizeInputs/2;
            outputsCapacitance = outputsCapacitance + "* Cload " + z.getId().toString() + " 0 1f\n";
            ouputs = ouputs + " " + z.getId().toString();
            plotOutput = plotOutput + "v(" + z.getId().toString() + ")+" + sum + " ";
            temp = temp/2;//sizeInputs/2;
            sum = sum + 20;

        }

        template = template + "\n * Portas Logicas";

        System.out.println("INPUTS TENSION (v): " + concat_inputs);

        for (int j = 0; j < gatesLevels.size(); j++) {

            ArrayList <Object> gatesInThisLevel = gatesLevels.get(j).getGates();

            for (int k = 0; k < gatesInThisLevel.size(); k++) {
                String AwnsString = gatesInThisLevel.get(k).getClass().toString();
                //System.out.println("Aws: "+ AwnsString);
                if(AwnsString.equals("class levelDatastructures.DepthGate")) {
                    Object object = gatesInThisLevel.get(k);
                    DepthGate gate = (DepthGate) object;
                    template = template + "\n" + this.generateGateNetlist(gate.getGate().getType(), gate, gate.getGate().getInputs(), id_node) + "\n";  //Method calc the output from the gate
                    id_node++;
                }
            }
        }

        template = template + "\n* SET no nodo 'Inv1'\n" +
                //"\t\t*Iexp 0 out exp(0 190u 1n 40p 1.00001n 320p) \n" +
                "\t\tIexp 0 " + SensitiveNode + " exp(" + bitflipValue + " 190u 1n 10p 1.00001n 320p) \n" +
                "\t*transicao 0-1-0\n" +
                "\n" +
                "* Declarando uma capacitância de saída que pode ser usada para emular uma carga\n" +
                "*Cload out 0 1f\n" +
                 outputsCapacitance +
                "\n" +
                "\n" +
                ".control\n" +
                "run\t\t\t\t\n" +
                "\t\t\t\t\t\tset color0=white\n" +
                "\t\t\t            set xbrushwidth = 3\n" +
                "\t\t *plot i(Vfonte)\n" +
                "\t     *plot v(A)+8 V(B)+6 V(C)+4 V(out)+2\n" +
                 "*" + plot + plotOutput +
                "\n"
                + "*plot " + plotOutput + "\n"+
                 "wrdata  outputFile_" + SensitiveNode + "_" + ".txt" + ouputs + " \n"+
                ".endc\t     \n" +
                "\n" +
                "* Declarando o tipo de simulação *Precisa mudar para 15 (0 - 15 = 16 unidades de tempo) pois senão nao exitira descida para entrada A\n" +
                ".tran 0.1n " + size_final + "n \n" +
                "\n" +
                "* Definindo comandos measure para fazer medidas\n" +
                "\n" +
                ".end";

        //System.out.println(template);

        return template;
    }

    private String setBitFlipInNetListFile(TestVectorInformation threadSimulationList){
        //this.threadSimulationList.get(i).get_MTF_FaultSignal_List_thd();
        //String SensitiveNode = threadSimulationList.getFaultSignal().toString();
        //item.get(y).getMTFPERSONAL_LIST_Identities()
        //item.get(y).getBitFlipMTFPERSONAL_LIST()

        String output = "";// + threadSimulationList.get_MTF_FaultSignal_List()  + " " + threadSimulationList.getinputVector()+ "\n";

        //String output = "" + threadSimulationList.get_MTF_FaultSignal_List()  + " " + threadSimulationList.getinputVector()+ "\n";
        for (Signal x: threadSimulationList.get_MTF_FaultSignal_List()){

            String SensitiveNode = x.getId();//threadSimulationList.getFaultSignal().toString();
            String bitflipValue =  Integer.toString(x.getLogicValue());//Integer.toString(threadSimulationList.getFaultSignal().getLogicValue());
            //"\t\t*Iexp 0 " + SensitiveNode + " exp(" + bitflipValue + " 190u 1n 10p 1.00001n 320p) \n" +
            String bit = "";
            int critCharge = 200;
            String critChargeStr = "";
            if(bitflipValue == "0"){
                bit = "0";
                critChargeStr = Integer.toString(critCharge);
            }
            else{
                bit = "1";
                critChargeStr = "-" + Integer.toString(critCharge);
            }

            output = output +  "\t\tIexp " + 0 + " " + SensitiveNode + " exp(" + bitflipValue + " " + critChargeStr + "u 1n 10p 1.00001n 320p)\n" ;
           // output = output +  "\t\tIexp 0 " + SensitiveNode + " exp(" + bitflipValue + " 190u 1n 10p 1.00001n 320p)\n" ;
        }
        //"\t\t*Iexp 0 " + SensitiveNode + " exp(" + bitflipValue + " 190u 1n 10p 1.00001n 320p) \n" +

        //System.out.println("  --- >>> " + output + "  <<< ------- \n");
        return  output;
    }

    private String setPlotSensitiveNodesNetlistFile(String x, int ini){

            String out = "";
            int base = ini;
            //int b = ini;
            String [] exploit = x.split(" ");

                //System.out.println("Explot: " + Arrays.toString(exploit));

                for (int i = 1; i < exploit.length; i++) {
                    if(ini != 0) {
                        out += " v(" + exploit[i] + ")+" + base + " ";
                        base = base + ini;
                    }else{
                        if(i==1){
                            out = exploit[i];
                        }else {
                            out += ", " + exploit[i];
                        }
                    }
                }

                this.lastposPlot = base;

            return out;
    }

    /**
     * @deprecated OLD VERSION BUT WORKS
     * @param SensitiveNode
     * @param bitflipValue
     * @return
     * @throws IOException
     * @throws WriteException
     */
    private String createSpiceNetlistLargerCircuitsOLD(String SensitiveNode, String bitflipValue) throws IOException, WriteException{

        String template = "" +
                "* Função Transiente\n" +
                ".include 45nm_HP.pm\n" +
                ".include Library.txt\n" +
                "\n" +
                "* Definindo a temperatura de operação\n" +
                "   .TEMP 25\n" +
                "\n" +
                "* Declarando parâmetros que serão utilizados nas simulações\n" +
                "   .param supply = 1.0\n" +
                "\n" +
                "* Declaração das fontes\n" +
                "\tVvdd vdd 0 1.0\n" +
                "\tVvss vss 0 0" +
                "\n " +
                "" +
                "* Fontes de Tensão\n";


        this.threadID = (long) Thread.currentThread().getId();
        //thread_item.setThreadID(this.threadID);

        ArrayList <GateLevel> gatesLevels = this.levelCircuit.getGateLevels();

        ArrayList <String> gatesNetlist = new ArrayList<>();

        String outputsCapacitance = " ";
        String plotOutput = " ";
        int sizeInputs =  this.circuit.getInputs().size();
        int size = sizeInputs;



        String plot= "plot ";

        int id_node = 1;

        /*vA A 0 PULSE (0 1.0 8n 1p 1p 8n 16n)
         vB B 0 PULSE (0 1.0 4n 1p 1p 4n 8n)
         vC C 0 PULSE (0 1.0 2n 1p 1p 2n 4n)
         vd D 0 PULSE (0 1.0 1n 1p 1p 1n 2n)
         */

        ArrayList <String> concat_inputs = new ArrayList<>();
        int size_temp = sizeInputs;
        int size_final = size_temp;
        int sum = 5 ;
        for (Signal x: this.circuit.getInputs()){
            template = template + "v"+x.getId().toString() + " " + x.getId().toString() + " 0 PULSE (0 1.0 "+ size_temp + "n 1p 1p " + size_temp + "n " + size_temp + "n)"  + "\n";
            //plot = plot + "v(" + x.getId().toString() + ")+" + sizeInputs*2 + " ";//;"plot v(G1gat)+8 V(G2gat)+6 V(G6gat)"
            plot = plot + "v(" + x.getId().toString() + ")+" + sum + " ";;//plot + "v(" + x.getId().toString() + ")+" + sizeInputs + " ";//;"plot v(G1gat)+8 V(G2gat)+6 V(G6gat)"
            //sizeInputs = sizeInputs/2;
            //sizeInputs = sizeInputs - 2;
            size_temp = size_temp - 1;
            sizeInputs = sizeInputs - 1;
            sum = sum + 5;
        }
        int temp = sizeInputs;
        for (Signal z: this.circuit.getOutputs()){
            //Cload G6gat 0 1f
            //template = template + "v"+x.getId().toString() + " " + x.getId().toString() + " 0 PULSE (0 1.0 "+ sizeInputs + "n 1p 1p " + sizeInputs + "n " + sizeInputs*2 + "n)"  + "\n";
            //plot = plot + "v(" + x.getId().toString() + ")+" + sizeInputs + " ";//;"plot v(G1gat)+8 V(G2gat)+6 V(G6gat)"
            //sizeInputs = sizeInputs/2;
            outputsCapacitance = outputsCapacitance + "* Cload " + z.getId().toString() + " 0 1f\n";

            plotOutput = plotOutput + "v(" + z.getId().toString() + ")+" + sum + " ";
            temp = temp/2;//sizeInputs/2;
            sum = sum + 20;

        }

        template = template + "\n * Portas Logicas";

        System.out.println("INPUTS TENSION (v): " + concat_inputs);

        for (int j = 0; j < gatesLevels.size(); j++) {

            ArrayList <Object> gatesInThisLevel = gatesLevels.get(j).getGates();

            for (int k = 0; k < gatesInThisLevel.size(); k++) {
                String AwnsString = gatesInThisLevel.get(k).getClass().toString();
                //System.out.println("Aws: "+ AwnsString);
                if(AwnsString.equals("class levelDatastructures.DepthGate")) {
                    Object object = gatesInThisLevel.get(k);
                    DepthGate gate = (DepthGate) object;
                    template = template + "\n" + this.generateGateNetlist(gate.getGate().getType(), gate, gate.getGate().getInputs(), id_node);  //Method calc the output from the gate
                    id_node++;
                }
            }
        }

        template = template + "\n\n\n ****** SET Injection in ramdom node Inv1\n" +
                //"\t\t*Iexp 0 out exp(0 190u 1n 40p 1.00001n 320p) \n" +
                "\t\tIexp 0 " + SensitiveNode + " exp(" + bitflipValue + " 190u 1n 10p 1.00001n 320p) \n" +
                "\t*transicao 0-1-0\n" +
                "\n" +
                "* Declarando uma capacitância de saída que pode ser usada para emular uma carga\n" +
                "*Cload out 0 1f\n" +
                outputsCapacitance +
                "\n" +
                "\n" +
                ".control\n" +
                "run\t\t\t\t\n" +
                "\t\t\t\t\t\tset color0=white\n" +
                "\t\t\t            set xbrushwidth = 3\n" +
                "\t\t *plot i(Vfonte)\n" +
                "\t     *plot v(A)+8 V(B)+6 V(C)+4 V(out)+2\n" +
                "*" + plot + plotOutput +
                "\n"
                + "plot " + plotOutput + "\n"+
                ".endc\t     \n" +
                "\n" +
                "* Declarando o tipo de simulação *Precisa mudar para 15 (0 - 15 = 16 unidades de tempo) pois senão nao exitira descida para entrada A\n" +
                ".tran 0.1n " + size_final + "n \n" +
                "\n" +
                "* Definindo comandos measure para fazer medidas\n" +
                "\n" +
                ".end";

        //System.out.println(template);

        return template;
    }

    private String createSpiceNetlistLargerCircuits(TestVectorInformation threadSimulationList) throws IOException, WriteException{

        //String SensitiveNode = threadSimulationList.getFaultSignal().toString();
        //String bitflipValue =  Integer.toString(threadSimulationList.getFaultSignal().getLogicValue());

        //System.out.println("Sensitive Node: " + SensitiveNode + "   i: " + threadSimulationList.getSimulationIndex());
        String template = "" +
                "* Função Transiente\n" +
                ".include 45nm_HP.pm\n" +
                ".include Library.txt\n" +
                "\n" +
                "* Definindo a temperatura de operação\n" +
                "   .TEMP 25\n" +
                "\n" +
                "* Declarando parâmetros que serão utilizados nas simulações\n" +
                "   .param supply = 1.0\n" +
                "\n" +
                "* Declaração das fontes\n" +
                "\tVvdd vdd 0 1.0\n" +
                "\tVvss vss 0 0" +
                "\n " +
                "" +
                "* Fontes de Tensão\n";


        this.threadID = (long) Thread.currentThread().getId();
        //thread_item.setThreadID(this.threadID);

        ArrayList <GateLevel> gatesLevels = this.levelCircuit.getGateLevels();

        ArrayList <String> gatesNetlist = new ArrayList<>();

        String outputsCapacitance = " ";
        String plotOutput = " ";
        int sizeInputs =  this.circuit.getInputs().size();
        int size = sizeInputs;

        String plot= "plot ";

        int id_node = 1;

        /*vA A 0 PULSE (0 1.0 8n 1p 1p 8n 16n)
         vB B 0 PULSE (0 1.0 4n 1p 1p 4n 8n)
         vC C 0 PULSE (0 1.0 2n 1p 1p 2n 4n)
         vd D 0 PULSE (0 1.0 1n 1p 1p 1n 2n)
         */

        ArrayList <String> concat_inputs = new ArrayList<>();
        int size_temp = sizeInputs;
        int size_final = size_temp;
        int sum = 5 ;

        // Va  a  gnd  PWL (0n 0 4.0n 0 4.01n supply 10.0n supply 10.01n 0 14n 0 14.01n supply 16.0n supply 16.01n 0)
        String input_values = "";
        for (Signal x: this.circuit.getInputs()){
            //template = template + "v"+x.getId().toString() + " " + x.getId().toString() + " 0 PULSE (0 1.0 "+ size_temp + "n 1p 1p " + size_temp + "n " + size_temp + "n)"  + "\n";
            template = template + "v"+x.getId().toString() + " " + x.getId().toString() + " 0 PWL (0n " + Integer.toString(x.getOriginalLogicValue()) + "  " + "10n " + Integer.toString(x.getOriginalLogicValue()) + " )"  + "\n";
            input_values = input_values + Integer.toString(x.getOriginalLogicValue());
            //plot = plot + "v(" + x.getId().toString() + ")+" + sizeInputs*2 + " ";//;"plot v(G1gat)+8 V(G2gat)+6 V(G6gat)"
            plot = plot + "v(" + x.getId().toString() + ")+" + sum + " ";;//plot + "v(" + x.getId().toString() + ")+" + sizeInputs + " ";//;"plot v(G1gat)+8 V(G2gat)+6 V(G6gat)"
            //sizeInputs = sizeInputs/2;
            //sizeInputs = sizeInputs - 2;
            size_temp = size_temp - 1;
            sizeInputs = sizeInputs - 1;
            sum = sum + 5;
        }
        int temp = sizeInputs;
        String ouputs = "";
        for (Signal z: this.circuit.getOutputs()){
            //Cload G6gat 0 1f
            //template = template + "v"+x.getId().toString() + " " + x.getId().toString() + " 0 PULSE (0 1.0 "+ sizeInputs + "n 1p 1p " + sizeInputs + "n " + sizeInputs*2 + "n)"  + "\n";
            //plot = plot + "v(" + x.getId().toString() + ")+" + sizeInputs + " ";//;"plot v(G1gat)+8 V(G2gat)+6 V(G6gat)"
            //sizeInputs = sizeInputs/2;
            outputsCapacitance = outputsCapacitance + "* Cload " + z.getId().toString() + " 0 1f\n";
            ouputs = ouputs + " " + z.getId().toString();
            plotOutput = plotOutput + "v(" + z.getId().toString() + ")+" + sum + " ";
            temp = temp/2;//sizeInputs/2;
            sum = sum + 20;

        }

        template = template + "\n * Portas Logicas";

                //System.out.println("INPUTS TENSION (v): " + concat_inputs);

        for (int j = 0; j < gatesLevels.size(); j++) {

            ArrayList <Object> gatesInThisLevel = gatesLevels.get(j).getGates();

            for (int k = 0; k < gatesInThisLevel.size(); k++) {
                String AwnsString = gatesInThisLevel.get(k).getClass().toString();
                //System.out.println("Aws: "+ AwnsString);
                if(AwnsString.equals("class levelDatastructures.DepthGate")) {
                    Object object = gatesInThisLevel.get(k);
                    DepthGate gate = (DepthGate) object;
                    template = template + "\n" + this.generateGateNetlist(gate.getGate().getType(), gate, gate.getGate().getInputs(), id_node);  //Method calc the output from the gate
                    id_node++;
                }
            }
        }

        //this.setBitFlipInNetListFile(threadSimulationList);
        String[] relativePath =  this.genlibPATH.split("/");
        String c = "";
        String d = ouputs.replace(" ", "_");
        for (int i = 0; i < relativePath.length-1; i++) {
            c = c + relativePath[i] + "/";
        }
        String file_output = c +  this.circuit.getName() + threadSimulationList.concatInputVector() + "_" + threadSimulationList.concatMTFFaultSignals() + "_Outputs" + d  + ".txt";

        //String plotVSensitiveNodes = "";

        template = template + "\n\n\n ****** SET Injection in ramdom node \n" +
                //"\t\t*Iexp 0 out exp(0 190u 1n 40p 1.00001n 320p) \n" +
                this.setBitFlipInNetListFile(threadSimulationList) +
                //"\t\t*Iexp 0 " + SensitiveNode + " exp(" + bitflipValue + " 190u 1n 10p 1.00001n 320p) \n" +
                "\t*transicao 0-1-0\n" +
                "\n" +
                "* Declarando uma capacitância de saída que pode ser usada para emular uma carga\n" +
                //"*Cload out 0 1f\n" +
                outputsCapacitance +
                "\n" +
                "\n" +
                ".control\n" +
                "run\t\t\t\t\n" +
                "\t\t\t\t\t\tset color0=white\n" +
                "\t\t\t            set xbrushwidth = 3\n" +
                "\t\t *plot i(Vfonte)\n" +
                "\t     *plot v(A)+8 V(B)+6 V(C)+4 V(out)+2\n" +
                "*" + plot + plotOutput +
                "\n"
                + "*plot " + plotOutput + "\n"+
                "plot " + this.setPlotSensitiveNodesNetlistFile(threadSimulationList.concatMTFFaultSignals(" "), 0) + " , "+ this.setPlotSensitiveNodesNetlistFile(ouputs, 0) + "\n"
                +"plot " + this.setPlotSensitiveNodesNetlistFile(threadSimulationList.concatMTFFaultSignals(" "), 5) + this.setPlotSensitiveNodesNetlistFile(ouputs, this.lastposPlot) + "\n"

                //"wrdata  " + this.genlibPATH +"-"+ this.circuit.getName() +  "_" + SensitiveNode + "_" + input_values + ".txt  " + SensitiveNode + " " + ouputs + " \n"+
             +   "wrdata  "  + file_output + " " + threadSimulationList.concatMTFFaultSignals(" ") + ouputs + " \n" +
                ".endc\t     \n" +
                "\n" +
                "* Declarando o tipo de simulação *Precisa mudar para 15 (0 - 15 = 16 unidades de tempo) pois senão nao exitira descida para entrada A\n" +
                ".tran 1p " + size_final + "n \n" +
                "\n" +
                "* Definindo comandos measure para fazer medidas\n" +
                "\n" +
                ".end";

        //System.out.println(template);

        return template;
    }

    private  void propagateInputVectorsMTF(int testNumber, ArrayList <Integer> vector, TestVectorInformation thread_item) throws IOException, WriteException{

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
                    boolean outputGate = this.calculateFaultFreeOutputGateValueMTF(gate.getGate().getType(), gate, gate.getGate().getInputs(), thread_item);  //Method calc the output from the gate


                    for (int s = 0; s < gate.getGate().getOutputs().size(); s++) {

                        Signal sig = gate.getGate().getOutputs().get(s);

                        //System.out.println(faultSig+" Sig EQUAL "+sig);
                        int pos = thread_item.getPositionFaultSignalInMTFListThd(gate.getGate().getOutputs().get(s)); //identifica se o sinal de entrada (index) está na lista de sinais a serem injetadas as falhas


                        if(outputGate == true){    //Saida do GATE  = 1
                            //thread_item.setSignalOriginalValue(1);


                            if(pos > -1){
                                thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(1);
                                thread_item.getMTF_PERSONAL_LIST().get(pos).setOriginalValue(1);
                            }

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
                            //thread_item.setSignalOriginalValue(0);
                            //sig.setLogicValue(0);
                            gate.getGate().getOutputs().get(s).setOriginalLogicValue(0);
                            gate.getGate().getOutputs().get(s).setLogicValue(0);
                            gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.FALSE);

                            if(pos > -1){
                                thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(0);
                                thread_item.getMTF_PERSONAL_LIST().get(pos).setOriginalValue(0);
                            }
                                /*
                                if(sig.getId().equals(faultSig.getId())){
                                    //System.out.println("@ "+faultSig+" Sig EQUAL "+sig);
                                    faultSig.setOriginalLogicValue(0);
                                }
                                */
                        }
                            /*
                             System.out.println("              - Gate: "+ gatesInThisLevel.get(k)
                                     +  "  type: "+ gate.getGate().getType()
                                     +  " - Inputs: " + gate.getGate().getInputs().get(0) + " value: " + gate.getGate().getInputs().get(0).getLogicValue()
                                     +  " - Inputs: " + gate.getGate().getInputs().get(1)+ " value: " + gate.getGate().getInputs().get(1).getLogicValue()
                                     +  "              - output: " + sig.getOriginalLogicValue());
                            */
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
         
        private  void getPropagateFaultFreeResults(ArrayList <Integer> vector, int testNumber, TestVectorInformation thread_item, int id_num){
         
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
       
        private void getFaultInjectionResults( ArrayList <Integer> vector, int testNumber, TestVectorInformation thread_item){
         
            ArrayList <Signal> signalsOutput = this.circuit.getOutputs();
            
            String outputInjection = "";
            String outFree = "";
            for (int s = 0; s < signalsOutput.size(); s++) { //All outputs to str   
                
                if(this.circuit.getOutputs().get(s).getId().equals(thread_item.getFaultSignal().getId())){  //Inject in output sognal BItflip
                     
                     if(this.circuit.getOutputs().get(s).getLogicValueBoolean()){ //Bitflip output signal 
                          int flag = 0;

                          //this.circuit.getOutputs().get(s).setLogicValue(0);
                          outputInjection = outputInjection + 0;
                          /*
                          System.out.println("0: Vec: " + thread_item.getinputVector() + "     "+ this.circuit.getOutputs().get(s) + " Output: Expect(" + this.circuit.getOutputs().get(s).getLogicValue()+ ")"
                                   + " Boolean :(" + this.circuit.getOutputs().get(s).getLogicValueBoolean()+ ")" );
                                   */

                          
                     }else{
                          int flag = 1;
                          //this.circuit.getOutputs().get(s).setLogicValue(0);
                          outputInjection = outputInjection +  1;
                          /*
                          System.out.println("1: Vec: " + thread_item.getinputVector() + "     "+ this.circuit.getOutputs().get(s) + " Output: Expect(" + this.circuit.getOutputs().get(s).getLogicValue()+ ")"
                                   + " Boolean :(" + this.circuit.getOutputs().get(s).getLogicValueBoolean()+ ")" );
                            */
                     }
                    
                     
                 }else{
                     outputInjection = outputInjection +  this.circuit.getOutputs().get(s).getLogicValue();
                 }
                
                // outputInjection = outputInjection + signalsOutput.get(s).getOriginalLogicValue(); 
               //  System.out.println("           - Gate: " + signalsOutput.get(s).getOrigin() + " (" + signalsOutput.get(s).getOrigin().getType() + ") - Output: "+ signalsOutput.get(s) + " - Logic value: "+ signalsOutput.get(s).getLogicValue() + " [" + signalsOutput.get(s).getLogicValueBoolean() + "]" );             
                //System.out.println("free - Gate: " + signalsOutput.get(s).getOrigin() + " (" + signalsOutput.get(s).getOrigin().getType() + ") - Output: "+ signalsOutput.get(s) + " - Logic value: "+ signalsOutput.get(s).getOriginalLogicValue() + " [" + signalsOutput.get(s).getLogicValueBoolean() + "]" );             
                //outFree = outFree + this.circuit.getOutputs().get(s).getOriginalLogicValue()

                //;
            }


            String O = vector.toString();
            String fO = O.replace("[", "");
            fO = fO.replace(",", "");
            fO = fO.replace("]", "");
            fO = fO.replace(" ", "");

            //String S = Integer.toString(id_num);
            //int o = Integer.parseInt(rO);
            
            //this.concurrentMap_output_fault.put(this.getVectorString(vector), outputInjection );
            
            //this.linkedQueueFault.add(outputInjection);
            //System.out.println(vector + " OUT INJECTON: " + outputInjection);
            //System.out.println( testNumber  +" Input Vector: "+ vector + "    faultSig: " + thread_item.get_MTF_FaultSignal_List_thd() + " expected(" + thread_item.getOrignalOutput() +")  RealOutput: " + outputInjection + " -> ID:" + this.threadID);
            //System.out.println("'"+fO + "';'" + thread_item.getOrignalOutput() +";'" + outputInjection + ";" + thread_item.get_MTF_FaultSignal_List_thd() + ";" + thread_item.getSimulationIndex());


            thread_item.setFaultOutput(outputInjection);

           // System.out.println( testNumber  +") Input Vector: "+ vector + "  Output: " + outputInjection + " -> ID:" + this.threadID);
            
           
     }

    private void getFaultInjectionResultsMTF( ArrayList <Integer> vector, int testNumber, TestVectorInformation thread_item){

        ArrayList <Signal> signalsOutput = this.circuit.getOutputs();

        String outputInjection = "";
        String outFree = "";

        for (int s = 0; s < signalsOutput.size(); s++) { //All outputs to str

            int pos = thread_item.getPositionFaultSignalInMTFListThd(this.circuit.getOutputs().get(s));

            if(pos > -1){

                //Inject in output signal BItflip

              //  if(this.circuit.getOutputs().get(s).getLogicValueBoolean()){ //Bitflip output signal
               if(thread_item.getMTF_PERSONAL_LIST().get(pos).getIdentidade().equals(thread_item.get_MTF_FaultSignal_List_thd().get(pos).getId()) && (thread_item.getMTF_PERSONAL_LIST().get(pos).getNewValue() == 0)){

                    //if(thread_item.getMTF_PERSONAL_LIST().get(pos).getIdentidade().equals(thread_item.get_MTF_FaultSignal_List_thd().get(pos).getId()) && (thread_item.getMTF_PERSONAL_LIST().get(pos).getNewValue() !=  thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue())){

                        //int flag = 0;

                    //this.circuit.getOutputs().get(s).setLogicValue(0);

                   if(thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue() != thread_item.getMTF_PERSONAL_LIST().get(pos).getNewValue()){
                       outputInjection = outputInjection + 0;

                   }
                   else {
                       outputInjection = outputInjection + 1;
                   }
                          /*
                          System.out.println("0: Vec: " + thread_item.getinputVector() + "     "+ this.circuit.getOutputs().get(s) + " Output: Expect(" + this.circuit.getOutputs().get(s).getLogicValue()+ ")"
                                   + " Boolean :(" + this.circuit.getOutputs().get(s).getLogicValueBoolean()+ ")" );
                                   */

                }

               if(thread_item.getMTF_PERSONAL_LIST().get(pos).getIdentidade().equals(thread_item.get_MTF_FaultSignal_List_thd().get(pos).getId()) && (thread_item.getMTF_PERSONAL_LIST().get(pos).getNewValue() == 1)){ //Bitflip output signal
                     int flag = 1;
                    //this.circuit.getOutputs().get(s).setLogicValue(0);
                   if(thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue() != thread_item.getMTF_PERSONAL_LIST().get(pos).getNewValue()){
                       outputInjection = outputInjection + 1;

                   }
                   else {
                       outputInjection = outputInjection + 0;
                   }
                    /*

                          System.out.println("1: Vec: " + thread_item.getinputVector() + "     "+ this.circuit.getOutputs().get(s) + " Output: Expect(" + this.circuit.getOutputs().get(s).getLogicValue()+ ")"
                                   + " Boolean :(" + this.circuit.getOutputs().get(s).getLogicValueBoolean()+ ")" );
                            */
                }


            }else{
                outputInjection = outputInjection +  this.circuit.getOutputs().get(s).getLogicValue();
            }

            // outputInjection = outputInjection + signalsOutput.get(s).getOriginalLogicValue();
            //  System.out.println("           - Gate: " + signalsOutput.get(s).getOrigin() + " (" + signalsOutput.get(s).getOrigin().getType() + ") - Output: "+ signalsOutput.get(s) + " - Logic value: "+ signalsOutput.get(s).getLogicValue() + " [" + signalsOutput.get(s).getLogicValueBoolean() + "]" );
            //System.out.println("free - Gate: " + signalsOutput.get(s).getOrigin() + " (" + signalsOutput.get(s).getOrigin().getType() + ") - Output: "+ signalsOutput.get(s) + " - Logic value: "+ signalsOutput.get(s).getOriginalLogicValue() + " [" + signalsOutput.get(s).getLogicValueBoolean() + "]" );
            //outFree = outFree + this.circuit.getOutputs().get(s).getOriginalLogicValue()

            //;
        }


        String O = vector.toString();
        String fO = O.replace("[", "");
        fO = fO.replace(",", "");
        fO = fO.replace("]", "");
        fO = fO.replace(" ", "");

        //String S = Integer.toString(id_num);
        //int o = Integer.parseInt(rO);

        //this.concurrentMap_output_fault.put(this.getVectorString(vector), outputInjection );

        //this.linkedQueueFault.add(outputInjection);
        //System.out.println(vector + " OUT INJECTON: " + outputInjection);
        //System.out.println( testNumber  +" Input Vector: "+ vector + "    faultSig: " + thread_item.get_MTF_FaultSignal_List_thd() + " expected(" + thread_item.getOrignalOutput() +")  RealOutput: " + outputInjection + " -> ID:" + this.threadID);
        //System.out.println("'"+fO + "';'" + thread_item.getOrignalOutput() +";'" + outputInjection + ";" + thread_item.get_MTF_FaultSignal_List_thd() + ";" + thread_item.getSimulationIndex());


        thread_item.setFaultOutput(outputInjection);

        // System.out.println( testNumber  +") Input Vector: "+ vector + "  Output: " + outputInjection + " -> ID:" + this.threadID);


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
       
        private List <TestVectorInformation> getSimulationList(){
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

    private String settingFaultInjectionResultsMTF(){

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
        
        private  void insertInputVectors(String Option, ArrayList <Integer> vector){
                   
               this.cells = this.cellLibrary.getCells();//cellLib.getCells();
              
                switch (Option){
                    /*
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
                    */
                    
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

        private  void insertInputVectorsMTF(String Option, ArrayList <Integer> vector, TestVectorInformation thread_item){

        this.cells = this.cellLibrary.getCells();//cellLib.getCells();

        switch (Option){
                    /*
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
                    */

            case "selected":
                ArrayList <Signal> inputSignals = this.circuit.getInputs();
                // System.out.println("Selected vector" + vector + " - Input Signals: " + inputSignals + " Size: " + inputSignals.size());

                //Random gerador = new Random();
                //vetor de entrada aleatório
                //System.out.println("INPUTS: " + inputSignals);
                for (int i = 0; i < inputSignals.size(); i++) {
                    //int randomLogicValue = gerador.nextInt(2);
                    //inputSignals.get(i).setLogicValue(vector.get(i));
                    this.circuit.getInputs().get(i).setLogicValue(vector.get(i));
                    this.circuit.getInputs().get(i).setOriginalLogicValue(vector.get(i));
                    /*
                    for (int j = 0; j < thd.getMTF_FaultSignal_List_thd().size(); j++) {
                            if(this.circuit.getInputs().get(i).getId().equals(thd.get_MTF_FaultSignal_List_thd().get(j).getId())){ // Search for input signal in MultipleFaultList
                                //System.out.println(" InputSetted Founded: " +  thd.get_MTF_FaultSignal_List_thd().get(j).getId() +" thd: "+ thd.getSimulationIndex());
                                thd.get_MTF_FaultSignal_List_thd().get(j).setOriginalLogicValue(vector.get(i));
                                thd.get_MTF_FaultSignal_List_thd().get(j).setLogicValue(vector.get(i));
                            }
                    }
                    */
                    int pos =  thread_item.getPositionFaultSignalInMTFListThd(inputSignals.get(i));
                    if(pos > -1){
                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(vector.get(i));
                        //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValue(vector.get(i));
                    }

                    //System.out.println(inputSignals.get(i)+" : '"+inputSignals.get(i).getLogicValue()+"'");
                }
                break;

        }





    }

        private  void propagateFaultInjections(int testNumber, ArrayList <Integer> vector, Signal faultSig, int index, TestVectorInformation thread_item) throws IOException, WriteException{
           
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

        private  void propagateMultipleFaultInjections(int testNumber, ArrayList <Integer> vector, Signal faultSig, int index, TestVectorInformation thread_item) throws IOException, WriteException{

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

                    Signal fault = null;
                    boolean gateResult = this.calculateOutputFacultInjectionGateValueV2(gate.getGate().getType(), gate, gate.getGate().getInputs(), thread_item.get_MTF_FaultSignal_List(), fault, thread_item);  //Method calc the output from the gate cal bitflip

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

        private  void propagateMultipleFaultInjectionsV2(Signal faultSig, TestVectorInformation thread_item) throws IOException, WriteException{

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

                    Signal fault = null;
                    boolean gateResult = this.calculateOutputFacultInjectionGateValueV2(gate.getGate().getType(), gate, gate.getGate().getInputs(), thread_item.get_MTF_FaultSignal_List(), fault, thread_item);  //Method calc the output from the gate cal bitflip

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

    private  void propagateMultipleFaultInjectionsV2NEWMODMODULAR(TestVectorInformation thread_item) throws IOException, WriteException{

        this.threadID = (long) Thread.currentThread().getId();

        ArrayList <GateLevel> gatesLevels = this.levelCircuit.getGateLevels();

        //String track_operations = thread_item.getSimulationIndex() + " -  Fault List: " + thread_item.get_MTF_FaultSignal_List_thd() + "    " + thread_item.getinputVector() +  "\n";

        String outGates = "";

        this.inGate = "";

        for (int j = 0; j < gatesLevels.size(); j++) {

            ArrayList <Object> gatesInThisLevel = gatesLevels.get(j).getGates();

            for (int k = 0; k < gatesInThisLevel.size(); k++) {
                String AwnsString = gatesInThisLevel.get(k).getClass().toString();
                //System.out.println("Aws: "+ AwnsString);
                if(AwnsString.equals("class levelDatastructures.DepthGate")){
                    Object object = gatesInThisLevel.get(k);
                    final DepthGate gate = (DepthGate) object;
                    //pos, pos_faultList
                    //ConcurrentHashMap<Integer, Integer> hashMap_temp = new ConcurrentHashMap<>();

                    //Aplica o bitflip nas entradas se estiver na lista de falhas e Calcula a saida da porta lógica  -- Testa os sinais de entrada para ver se estão na lista de falhas
                    //boolean gateResult = this.calculateOutputFacultInjectionGateValueMultipleFaultInjectionV2NEWMODEMODULAR(gate.getGate().getType(), gate, gate.getGate().getInputs(), thread_item);  //Method calc the output from the gate cal bitflip
                    boolean gateResult = this.calculateOutputFacultInjectionGateValueMultipleFaultInjectionV2NEWMODE(gate.getGate().getType(), gate, gate.getGate().getInputs(), thread_item);  //Method calc the output from the gate cal bitflip


                    Signal faultSig;

                    if(this.tempIndex == -1){  // Defines a position in
                        this.tempIndex = 0;
                        //faultSig = thread_item.get_MTF_FaultSignal_List_thd().get(0);

                    }


                    for (int s = 0; s < gate.getGate().getOutputs().size(); s++) {

                        Signal sig = gate.getGate().getOutputs().get(s);

                       // track_operations = track_operations + "inputSigs: " + gate.getGate().getInputs() + " Gate: [" + gate.getGate().getId() + "] OutputSigs: " + gate.getGate().getOutputs();

                        if(gateResult){  // Set the gate output's from gateResult after search to apply fault injection if signal its in the fault list

                            //Define as saidas da porta lógica
                            gate.getGate().getOutputs().get(s).setOriginalLogicValue(1);
                            gate.getGate().getOutputs().get(s).setLogicValue(1);
                            gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.TRUE);


                            outGates =  "[" +gate.getGate().getId() + "]  Ori: " + gate.getGate().getOutputsOriginalValuesToString()
                                    + " New: " + gate.getGate().getOutputsValuesToString() + "  ";

                            //for (int inside = 0; inside < hashMap_temp.size(); inside++) { // Test Case for faulsignals in outputs gate

                            //boolean check = hashMap_temp.containsKey(inside); //key founded in hashmap

                            int pos = thread_item.getPositionFaultSignalInMTFListThd( gate.getGate().getOutputs().get(s));

                            if((pos > -1)) { //
                                //int pos = hashMap_temp.get(inside);
                                this.tempIndex = pos;
                                Signal fault_signal_MTF = thread_item.get_MTF_FaultSignal_List_thd().get(pos);

                                if (sig.getId().equals(fault_signal_MTF.getId())){

                                    if (thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue() == gate.getGate().getOutputs().get(s).getLogicValue()) {


                                        //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setVisited();

                                        //System.out.println("INSIDEEEEEEEEE ...... " + sig + " test"+ thread_item.getSimulationIndex());
                                        //if(sig.getId().equals(faultSig.getId())){ // COmparar com lista novamente
                                        // Compara a saida da porta logica com faultsignalList e Define o bitflip 1 to 0


                                        outGates = outGates + " OUTGATE ****** 1 to 0 FAULTSIGNAL IDENTIFICADO " + fault_signal_MTF.getId() + "  posFaultList: " + pos;
                                        //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(1);

                                        thread_item.getMTF_PERSONAL_LIST().get(pos).setNewValue(0);

                                        gate.getGate().getOutputs().get(s).setLogicValue(0);
                                        gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.FALSE);

                                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValue(0);
                                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValueBoolean(Boolean.FALSE);

                                        outGates = outGates + " xxxx " +
                                                "" +
                                                "O(v): " + thread_item.get_MTF_FaultSignal_List_thd().get(pos).getOriginalLogicValue() + " N(v): " + thread_item.get_MTF_FaultSignal_List_thd().get(pos).getLogicValue();
                                    /*
                                    System.out.println(thread_item.getinputVector() + " ** 1 to 0 OUTPUSIGNAL FROM GATE: " + gate.getGate().getId()
                                            + "  faultsig: "       +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getId() +
                                            "    MTF FAUlt LIST: " + thread_item.get_MTF_FaultSignal_List_thd() + "   POS: " + pos + "   VALUES: " +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getSignalOriginalAndNewValue()

                                    );
                                     */
                                        String debug = " 1 to 0 OutputtSig: " + gate.getGate().getOutputs().get(s) + " Ori: " + gate.getGate().getOutputs().get(s).getOriginalLogicValue()
                                                + "  N: " + gate.getGate().getOutputs().get(s).getLogicValue() +
                                                " || Thd Sig " + thread_item.get_MTF_FaultSignal_List_thd().get(pos).getId() + " Ori: " + thread_item.get_MTF_FaultSignal_List_thd().get(pos).getOriginalLogicValue()
                                                + "  N: " + thread_item.get_MTF_FaultSignal_List_thd().get(pos).getLogicValue() + "|| " + thread_item.get_MTF_FaultSignal_List_thd()
                                                + "  || itemnizelist: " + thread_item.getMTF_PERSONAL_LIST_NODESINFO() + " ||";
                                        /*DEbug*/
                    /*
                    track_operations = track_operations + "  input ****** 0 to 1 FAULT INPUT SIGNAL IDENTIFICADO ["
                            +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getId()
                            + "]  Ori: " +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getOriginalLogicValue()
                            + " New: " +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getLogicValue() +"  posFaultList: " + pos + "  ~ ";
                    */
                                       // thread_item.getMTF_PERSONAL_LIST().get(pos).CompInfoItemnize(debug); //print
                                    }
                                }

                            }
                            // }



                        }
                        else{
                            gate.getGate().getOutputs().get(s).setOriginalLogicValue(0);
                            gate.getGate().getOutputs().get(s).setLogicValue(0);
                            gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.FALSE);

                            outGates = gate.getGate().getId() + "  Ori: " + gate.getGate().getOutputsOriginalValuesToString()
                                    + " New: " + gate.getGate().getOutputsValuesToString() + "  ";

                            // for (int inside = 0; inside < hashMap_temp.size(); inside++) { // Test Case for faulsignals in outputs gate

                            int pos = thread_item.getPositionFaultSignalInMTFListThd( gate.getGate().getOutputs().get(s));


                            if((pos > -1) ){ //
                                //int pos = hashMap_temp.get(inside);

                                this.tempIndex = pos;
                                Signal fault_signal_MTF = thread_item.get_MTF_FaultSignal_List_thd().get(pos);

                                if(sig.getId().equals(fault_signal_MTF.getId())) {
                                    //if(sig.getId().equals(faultSig.getId())){ // Define bitflip 0 to 1
                                    //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setVisited();
                                    if (thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue() == gate.getGate().getOutputs().get(s).getLogicValue()) {
                                        // System.out.println("Aplicavel..." +
                                        //         "");


                                        // System.out.println("@ "+faultSig+" Sig EQUAL "+sig);
                                        thread_item.getMTF_PERSONAL_LIST().get(pos).setNewValue(1);
                                        outGates = outGates + "  OUTGATE ****** 0 to 1 FAULTSIGNAL IDENTIFICADO " + fault_signal_MTF.getId() + "  posFaultList: " + pos;

                                        //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(0);
                                        gate.getGate().getOutputs().get(s).setLogicValue(1);
                                        gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.TRUE);

                                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValue(1);
                                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValueBoolean(Boolean.TRUE);

                                        outGates = outGates + " 0 to 1 OUT XXXXX O(v): " + thread_item.get_MTF_FaultSignal_List_thd().get(pos).getOriginalLogicValue() + " N(v): " + thread_item.get_MTF_FaultSignal_List_thd().get(pos).getLogicValue();

                                    /*
                                    System.out.println(thread_item.getinputVector() + "  ** 0 to 1 OUTPUSIGNAL FROM GATE: " + gate.getGate().getId()
                                            + "  faultsig: "       +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getId() +
                                            "    MTF FAUlt LIST: " + thread_item.get_MTF_FaultSignal_List_thd() + "   POS: " + pos + "   VALUES: " +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getSignalOriginalAndNewValue()
                                    );

                                     */
                                        String debug = " 0 to 1 OutputSig: " + gate.getGate().getOutputs().get(s) + " Ori: " + gate.getGate().getOutputs().get(s).getOriginalLogicValue()
                                                + "  N: " + gate.getGate().getOutputs().get(s).getLogicValue() +
                                                " || Thd Sig " + thread_item.get_MTF_FaultSignal_List_thd().get(pos).getId() + " Ori: " + thread_item.get_MTF_FaultSignal_List_thd().get(pos).getOriginalLogicValue()
                                                + "  N: " + thread_item.get_MTF_FaultSignal_List_thd().get(pos).getLogicValue() + "|| " + thread_item.get_MTF_FaultSignal_List_thd()
                                                + "  || itemnizelist: " + thread_item.getMTF_PERSONAL_LIST_NODESINFO() + " ||";
                                        /*DEbug*/
                    /*
                    track_operations = track_operations + "  input ****** 0 to 1 FAULT INPUT SIGNAL IDENTIFICADO ["
                            +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getId()
                            + "]  Ori: " +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getOriginalLogicValue()
                            + " New: " +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getLogicValue() +"  posFaultList: " + pos + "  ~ ";
                    */
                                       // thread_item.getMTF_PERSONAL_LIST().get(pos).CompInfoItemnize(debug);
                                    }
                                    //  }
                                }
                            }
                        }
                    }
                    /* Debug */
                    /*
                    track_operations = track_operations +  " in: [" + gate.getGate().getInputsValuesToString() + "]" +
                            //"  faultSig: " + faultSig.getId() + " value Ori - New: " + faultSig.getOriginalLogicValue() + " " + faultSig.getLogicValue()
                            " outPropageted: (" + gate.getGate().getOutputsValuesToString()
                            + ") free: " + gate.getGate().getOutputsOriginalValuesToString() + inGate + outGates +

                            "  ||  threadItem faultSig: " +  thread_item.get_MTF_FaultSignal_List_thd()
                            // + "  value Ori - New: " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getOriginalLogicValue()
                            // + " " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getLogicValue()
                            + " || "

                     */

                            /*
                            " inputs_value: " + gate.getGate().getInputsValuesToString() +
                            //"  faultSig: " + faultSig.getId() + " value Ori - New: " + faultSig.getOriginalLogicValue() + " " + faultSig.getLogicValue()
                            "-> GATE: " + gate.getGate().getId() + "  OutSignalGate: " + gate.getGate().getOutputs() + " value propag: concat(" + gate.getGate().getOutputsValuesToString()
                            + ") free: " + gate.getGate().getOutputsOriginalValuesToString() + inGate + outGates +

                            "  ||  threadItem faultSig: " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getId()
                            + "  value Ori - New: " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getOriginalLogicValue()
                            + " " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getLogicValue()
                            + " || " +

                            */

                           // "\n";

                    /* Debug Mode */


                    /*
                    if(thread_item.getSimulationIndex() == 100 || thread_item.getSimulationIndex() == 200)
                        System.out.println(thread_item.getSimulationIndex() + " - Inputs: " + gate.getGate().getInputs() + "  values: " +  gate.getGate().getInputsValuesToString() +  " Gate: " +  gate.getGate().getId()
                                + " Output: " + gate.getGate().getOutputsValuesToString() + " faultList: " + thread_item.get_MTF_FaultSignal_List_thd() + " faultListExtended: " + thread_item.getMTF_FaultSignal_List_Extended() );

                    */
                    /* Debug Mode */

                }
            }
        }


    }

    private  void propagateMultipleFaultInjectionsV2NEWMODMODULARFASTER(TestVectorInformation thread_item) throws IOException, WriteException{

        this.threadID = (long) Thread.currentThread().getId();

        int inner_bitflip_counter = 0;

        ArrayList <GateLevel> gatesLevels = this.levelCircuit.getGateLevels();

        //String track_operations = thread_item.getSimulationIndex() + " -  Fault List: " + thread_item.get_MTF_FaultSignal_List_thd() + "    " + thread_item.getinputVector() +  "\n";

        String outGates = "";

        this.inGate = "";

        for (int j = 0; j < gatesLevels.size(); j++) {

            ArrayList <Object> gatesInThisLevel = gatesLevels.get(j).getGates();

            for (int k = 0; k < gatesInThisLevel.size(); k++) {
                String AwnsString = gatesInThisLevel.get(k).getClass().toString();
                //System.out.println("Aws: "+ AwnsString);
                if(AwnsString.equals("class levelDatastructures.DepthGate")){
                    Object object = gatesInThisLevel.get(k);
                    final DepthGate gate = (DepthGate) object;
                    //   boolean gateResult = this.calculateOutputFacultInjectionGateValueMultipleFaultInjectionV2NEWMODE(gate.getGate().getType(), gate, gate.getGate().getInputs(), thread_item);  //Method calc the output from the gate cal bitflip
                    boolean gateResult = this.calculateOutputFacultInjectionGateValueMultipleFaultInjectionV2NEWMODEFASTER(gate.getGate().getType(), gate, gate.getGate().getInputs(), thread_item);  //Method calc the output from the gate cal bitflip
                    ///Signal faultSig;

                    if(this.tempIndex == -1){  // Defines a position in
                        this.tempIndex = 0;
                        //faultSig = thread_item.get_MTF_FaultSignal_List_thd().get(0);
                    }

                    for (int s = 0; s < gate.getGate().getOutputs().size(); s++) {

                        Signal sig = gate.getGate().getOutputs().get(s);

                        // track_operations = track_operations + "inputSigs: " + gate.getGate().getInputs() + " Gate: [" + gate.getGate().getId() + "] OutputSigs: " + gate.getGate().getOutputs();

                        if(gateResult){  // Set the gate output's from gateResult after search to apply fault injection if signal its in the fault list

                            //Define as saidas da porta lógica
                            //gate.getGate().getOutputs().get(s).setOriginalLogicValue(1);
                            gate.getGate().getOutputs().get(s).setLogicValue(1);
                            gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.TRUE);

                            if(gate.getGate().getOutputs().get(s).getOriginalLogicValue() != gate.getGate().getOutputs().get(s).getLogicValue()){
                                this.bitflipcounter++;
                                inner_bitflip_counter++;
                            }

                            /*
                            outGates =  "[" +gate.getGate().getId() + "]  Ori: " + gate.getGate().getOutputsOriginalValuesToString()

                                    + " New: " + gate.getGate().getOutputsValuesToString() + "  ";
                            */
                            //for (int inside = 0; inside < hashMap_temp.size(); inside++) { // Test Case for faulsignals in outputs gate

                            //boolean check = hashMap_temp.containsKey(inside); //key founded in hashmap

                            //

                            int pos = thread_item.getPositionFaultSignalInMTFListThd( gate.getGate().getOutputs().get(s));

                            if((pos > -1)) { //
                                //int pos = hashMap_temp.get(inside);
                                this.tempIndex = pos;
                                Signal fault_signal_MTF = thread_item.get_MTF_FaultSignal_List_thd().get(pos);

                                if (sig.getId().equals(fault_signal_MTF.getId())){

                                    //if ((gate.getGate().getOutputs().get(s).getLogicValue() == thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue()) && (!thread_item.getMTF_PERSONAL_LIST().get(pos).getBooleaFlag())) {
                                        // G7gat =        ORI 0  Aplica w4  G7(0/1) G7( 0/0)

                                        if ((gate.getGate().getOutputs().get(s).getLogicValue() == thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue())) {

                                             // outGates = outGates + " OUTGATE ****** 1 to 0 FAULTSIGNAL IDENTIFICADO " + fault_signal_MTF.getId() + "  posFaultList: " + pos;
                                            //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(1);

                                            thread_item.getMTF_PERSONAL_LIST().get(pos).setNewValue(0);
                                            thread_item.getMTF_PERSONAL_LIST().get(pos).setBooleanFlag();

                                            gate.getGate().getOutputs().get(s).setLogicValue(0);
                                            gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.FALSE);

                                            thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValue(0);
                                            thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValueBoolean(Boolean.FALSE);


                                    }
                                }

                            }
                            // }



                        }

                        else{
                            //gate.getGate().getOutputs().get(s).setOriginalLogicValue(0);
                            gate.getGate().getOutputs().get(s).setLogicValue(0);
                            gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.FALSE);

                            outGates = gate.getGate().getId() + "  Ori: " + gate.getGate().getOutputsOriginalValuesToString()
                                    + " New: " + gate.getGate().getOutputsValuesToString() + "  ";



                            if(gate.getGate().getOutputs().get(s).getOriginalLogicValue() != gate.getGate().getOutputs().get(s).getLogicValue()){
                                this.bitflipcounter++;
                                inner_bitflip_counter++;
                            }

                            // for (int inside = 0; inside < hashMap_temp.size(); inside++) { // Test Case for faulsignals in outputs gate

                            int pos = thread_item.getPositionFaultSignalInMTFListThd( gate.getGate().getOutputs().get(s));


                            if((pos > -1) ){ //

                                //int pos = hashMap_temp.get(inside);

                                this.tempIndex = pos;
                                Signal fault_signal_MTF = thread_item.get_MTF_FaultSignal_List_thd().get(pos);

                                if(sig.getId().equals(fault_signal_MTF.getId())) {
                                    //if(sig.getId().equals(faultSig.getId())){ // Define bitflip 0 to 1
                                    //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setVisited();
                                    //if ((gate.getGate().getOutputs().get(s).getLogicValue() == thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue()) && (!thread_item.getMTF_PERSONAL_LIST().get(pos).getBooleaFlag())) {
                                    if ((gate.getGate().getOutputs().get(s).getLogicValue() == thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue())) {


                                        // System.out.println("Aplicavel..." +
                                        //         "");

                                        // System.out.println("@ "+faultSig+" Sig EQUAL "+sig);
                                        thread_item.getMTF_PERSONAL_LIST().get(pos).setNewValue(1);
                                        thread_item.getMTF_PERSONAL_LIST().get(pos).setBooleanFlag();


                                        //outGates = outGates + "  OUTGATE ****** 0 to 1 FAULTSIGNAL IDENTIFICADO " + fault_signal_MTF.getId() + "  posFaultList: " + pos;

                                        //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(0);
                                        gate.getGate().getOutputs().get(s).setLogicValue(1);
                                        gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.TRUE);

                                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValue(1);
                                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValueBoolean(Boolean.TRUE);


                                        // thread_item.getMTF_PERSONAL_LIST().get(pos).CompInfoItemnize(debug);
                                    }
                                    //  }
                                }
                            }
                        }
                    }
                    /* Debug */
                    /*
                    track_operations = track_operations +  " in: [" + gate.getGate().getInputsValuesToString() + "]" +
                            //"  faultSig: " + faultSig.getId() + " value Ori - New: " + faultSig.getOriginalLogicValue() + " " + faultSig.getLogicValue()
                            " outPropageted: (" + gate.getGate().getOutputsValuesToString()
                            + ") free: " + gate.getGate().getOutputsOriginalValuesToString() + inGate + outGates +

                            "  ||  threadItem faultSig: " +  thread_item.get_MTF_FaultSignal_List_thd()
                            // + "  value Ori - New: " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getOriginalLogicValue()
                            // + " " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getLogicValue()
                            + " || "

                     */

                            /*
                            " inputs_value: " + gate.getGate().getInputsValuesToString() +
                            //"  faultSig: " + faultSig.getId() + " value Ori - New: " + faultSig.getOriginalLogicValue() + " " + faultSig.getLogicValue()
                            "-> GATE: " + gate.getGate().getId() + "  OutSignalGate: " + gate.getGate().getOutputs() + " value propag: concat(" + gate.getGate().getOutputsValuesToString()
                            + ") free: " + gate.getGate().getOutputsOriginalValuesToString() + inGate + outGates +

                            "  ||  threadItem faultSig: " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getId()
                            + "  value Ori - New: " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getOriginalLogicValue()
                            + " " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getLogicValue()
                            + " || " +

                            */

                    // "\n";

                    /* Debug Mode */


                    /*
                    if(thread_item.getSimulationIndex() == 100 || thread_item.getSimulationIndex() == 200)
                        System.out.println(thread_item.getSimulationIndex() + " - Inputs: " + gate.getGate().getInputs() + "  values: " +  gate.getGate().getInputsValuesToString() +  " Gate: " +  gate.getGate().getId()
                                + " Output: " + gate.getGate().getOutputsValuesToString() + " faultList: " + thread_item.get_MTF_FaultSignal_List_thd() + " faultListExtended: " + thread_item.getMTF_FaultSignal_List_Extended() );

                    */
                    /* Debug Mode */

                }
            }
        }

        //System.out.println(thread_item.getinputVector() + "  faullist: " + thread_item.getMTFPERSONAL_LIST_Identities() + " " + "Inner Bitflip: " + inner_bitflip_counter);


    }

    private  void propagateAndCalculateSensitiveAreas(TestVectorInformation thread_item) throws IOException, WriteException{

        this.threadID = (long) Thread.currentThread().getId();

        int inner_bitflip_counter = 0;

        ArrayList <GateLevel> gatesLevels = this.levelCircuit.getGateLevels();

        //String track_operations = thread_item.getSimulationIndex() + " -  Fault List: " + thread_item.get_MTF_FaultSignal_List_thd() + "    " + thread_item.getinputVector() +  "\n";

        String outGates = "";

        this.inGate = "";

        for (int j = 0; j < gatesLevels.size(); j++) {

            ArrayList <Object> gatesInThisLevel = gatesLevels.get(j).getGates();

            for (int k = 0; k < gatesInThisLevel.size(); k++) {
                String AwnsString = gatesInThisLevel.get(k).getClass().toString();
                //System.out.println("Aws: "+ AwnsString);
                if(AwnsString.equals("class levelDatastructures.DepthGate")){
                    Object object = gatesInThisLevel.get(k);
                    final DepthGate gate = (DepthGate) object;
                    //   boolean gateResult = this.calculateOutputFacultInjectionGateValueMultipleFaultInjectionV2NEWMODE(gate.getGate().getType(), gate, gate.getGate().getInputs(), thread_item);  //Method calc the output from the gate cal bitflip
                    //original boolean gateResult = this.calculateOutputFacultInjectionGateValueMultipleFaultInjectionAndCalculateSensitiveArea(gate.getGate().getType(), gate, gate.getGate().getInputs(), thread_item);  //Method calc the output from the gate cal bitflip

                    boolean gateResult = this.calculateSensitiveArea(gate.getGate().getType(), gate, gate.getGate().getInputs(), thread_item);  //Method calc the output from the gate cal bitflip

                    ///Signal faultSig;

                    if(this.tempIndex == -1){  // Defines a position in
                        this.tempIndex = 0;
                        //faultSig = thread_item.get_MTF_FaultSignal_List_thd().get(0);
                    }

                    for (int s = 0; s < gate.getGate().getOutputs().size(); s++) {

                        Signal sig = gate.getGate().getOutputs().get(s);

                        // track_operations = track_operations + "inputSigs: " + gate.getGate().getInputs() + " Gate: [" + gate.getGate().getId() + "] OutputSigs: " + gate.getGate().getOutputs();

                        if(gateResult){  // Set the gate output's from gateResult after search to apply fault injection if signal its in the fault list

                            //Define as saidas da porta lógica
                            //gate.getGate().getOutputs().get(s).setOriginalLogicValue(1);
                            gate.getGate().getOutputs().get(s).setLogicValue(1);
                            gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.TRUE);

                            if(gate.getGate().getOutputs().get(s).getOriginalLogicValue() != gate.getGate().getOutputs().get(s).getLogicValue()){
                                this.bitflipcounter++;
                                inner_bitflip_counter++;
                            }

                            /*
                            outGates =  "[" +gate.getGate().getId() + "]  Ori: " + gate.getGate().getOutputsOriginalValuesToString()

                                    + " New: " + gate.getGate().getOutputsValuesToString() + "  ";
                            */
                            //for (int inside = 0; inside < hashMap_temp.size(); inside++) { // Test Case for faulsignals in outputs gate

                            //boolean check = hashMap_temp.containsKey(inside); //key founded in hashmap

                            //

                            int pos = thread_item.getPositionFaultSignalInMTFListThd( gate.getGate().getOutputs().get(s));

                            if((pos > -1)) { //
                                //int pos = hashMap_temp.get(inside);
                                this.tempIndex = pos;
                                Signal fault_signal_MTF = thread_item.get_MTF_FaultSignal_List_thd().get(pos);

                                if (sig.getId().equals(fault_signal_MTF.getId())){

                                    //if ((gate.getGate().getOutputs().get(s).getLogicValue() == thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue()) && (!thread_item.getMTF_PERSONAL_LIST().get(pos).getBooleaFlag())) {
                                    // G7gat =        ORI 0  Aplica w4  G7(0/1) G7( 0/0)

                                    if ((gate.getGate().getOutputs().get(s).getLogicValue() == thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue())) {

                                        // outGates = outGates + " OUTGATE ****** 1 to 0 FAULTSIGNAL IDENTIFICADO " + fault_signal_MTF.getId() + "  posFaultList: " + pos;
                                        //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(1);

                                        thread_item.getMTF_PERSONAL_LIST().get(pos).setNewValue(0);
                                        thread_item.getMTF_PERSONAL_LIST().get(pos).setBooleanFlag();

                                        gate.getGate().getOutputs().get(s).setLogicValue(0);
                                        gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.FALSE);

                                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValue(0);
                                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValueBoolean(Boolean.FALSE);


                                    }
                                }

                            }
                            // }



                        }

                        else{
                            //gate.getGate().getOutputs().get(s).setOriginalLogicValue(0);
                            gate.getGate().getOutputs().get(s).setLogicValue(0);
                            gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.FALSE);

                            outGates = gate.getGate().getId() + "  Ori: " + gate.getGate().getOutputsOriginalValuesToString()
                                    + " New: " + gate.getGate().getOutputsValuesToString() + "  ";



                            if(gate.getGate().getOutputs().get(s).getOriginalLogicValue() != gate.getGate().getOutputs().get(s).getLogicValue()){
                                this.bitflipcounter++;
                                inner_bitflip_counter++;
                            }

                            // for (int inside = 0; inside < hashMap_temp.size(); inside++) { // Test Case for faulsignals in outputs gate

                            int pos = thread_item.getPositionFaultSignalInMTFListThd( gate.getGate().getOutputs().get(s));


                            if((pos > -1) ){ //

                                //int pos = hashMap_temp.get(inside);

                                this.tempIndex = pos;
                                Signal fault_signal_MTF = thread_item.get_MTF_FaultSignal_List_thd().get(pos);

                                if(sig.getId().equals(fault_signal_MTF.getId())) {
                                    //if(sig.getId().equals(faultSig.getId())){ // Define bitflip 0 to 1
                                    //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setVisited();
                                    //if ((gate.getGate().getOutputs().get(s).getLogicValue() == thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue()) && (!thread_item.getMTF_PERSONAL_LIST().get(pos).getBooleaFlag())) {
                                    if ((gate.getGate().getOutputs().get(s).getLogicValue() == thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue())) {


                                        // System.out.println("Aplicavel..." +
                                        //         "");

                                        // System.out.println("@ "+faultSig+" Sig EQUAL "+sig);
                                        thread_item.getMTF_PERSONAL_LIST().get(pos).setNewValue(1);
                                        thread_item.getMTF_PERSONAL_LIST().get(pos).setBooleanFlag();


                                        //outGates = outGates + "  OUTGATE ****** 0 to 1 FAULTSIGNAL IDENTIFICADO " + fault_signal_MTF.getId() + "  posFaultList: " + pos;

                                        //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(0);
                                        gate.getGate().getOutputs().get(s).setLogicValue(1);
                                        gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.TRUE);

                                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValue(1);
                                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValueBoolean(Boolean.TRUE);


                                        // thread_item.getMTF_PERSONAL_LIST().get(pos).CompInfoItemnize(debug);
                                    }
                                    //  }
                                }
                            }
                        }
                    }
                    /* Debug */
                    /*
                    track_operations = track_operations +  " in: [" + gate.getGate().getInputsValuesToString() + "]" +
                            //"  faultSig: " + faultSig.getId() + " value Ori - New: " + faultSig.getOriginalLogicValue() + " " + faultSig.getLogicValue()
                            " outPropageted: (" + gate.getGate().getOutputsValuesToString()
                            + ") free: " + gate.getGate().getOutputsOriginalValuesToString() + inGate + outGates +

                            "  ||  threadItem faultSig: " +  thread_item.get_MTF_FaultSignal_List_thd()
                            // + "  value Ori - New: " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getOriginalLogicValue()
                            // + " " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getLogicValue()
                            + " || "

                     */

                            /*
                            " inputs_value: " + gate.getGate().getInputsValuesToString() +
                            //"  faultSig: " + faultSig.getId() + " value Ori - New: " + faultSig.getOriginalLogicValue() + " " + faultSig.getLogicValue()
                            "-> GATE: " + gate.getGate().getId() + "  OutSignalGate: " + gate.getGate().getOutputs() + " value propag: concat(" + gate.getGate().getOutputsValuesToString()
                            + ") free: " + gate.getGate().getOutputsOriginalValuesToString() + inGate + outGates +

                            "  ||  threadItem faultSig: " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getId()
                            + "  value Ori - New: " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getOriginalLogicValue()
                            + " " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getLogicValue()
                            + " || " +

                            */

                    // "\n";

                    /* Debug Mode */


                    /*
                    if(thread_item.getSimulationIndex() == 100 || thread_item.getSimulationIndex() == 200)
                        System.out.println(thread_item.getSimulationIndex() + " - Inputs: " + gate.getGate().getInputs() + "  values: " +  gate.getGate().getInputsValuesToString() +  " Gate: " +  gate.getGate().getId()
                                + " Output: " + gate.getGate().getOutputsValuesToString() + " faultList: " + thread_item.get_MTF_FaultSignal_List_thd() + " faultListExtended: " + thread_item.getMTF_FaultSignal_List_Extended() );

                    */
                    /* Debug Mode */

                }
            }
        }

        //System.out.println(thread_item.getinputVector() + "  faullist: " + thread_item.getMTFPERSONAL_LIST_Identities() + " " + "Inner Bitflip: " + inner_bitflip_counter);


    }


    private  void propagateMultipleFaultInjectionsAndCalculateSensitiveAreas(TestVectorInformation thread_item) throws IOException, WriteException{

        this.threadID = (long) Thread.currentThread().getId();

        int inner_bitflip_counter = 0;

        ArrayList <GateLevel> gatesLevels = this.levelCircuit.getGateLevels();

        //String track_operations = thread_item.getSimulationIndex() + " -  Fault List: " + thread_item.get_MTF_FaultSignal_List_thd() + "    " + thread_item.getinputVector() +  "\n";

        String outGates = "";

        this.inGate = "";

        for (int j = 0; j < gatesLevels.size(); j++) {

            ArrayList <Object> gatesInThisLevel = gatesLevels.get(j).getGates();

            for (int k = 0; k < gatesInThisLevel.size(); k++) {
                String AwnsString = gatesInThisLevel.get(k).getClass().toString();
                //System.out.println("Aws: "+ AwnsString);
                if(AwnsString.equals("class levelDatastructures.DepthGate")){
                    Object object = gatesInThisLevel.get(k);
                    final DepthGate gate = (DepthGate) object;
                    //   boolean gateResult = this.calculateOutputFacultInjectionGateValueMultipleFaultInjectionV2NEWMODE(gate.getGate().getType(), gate, gate.getGate().getInputs(), thread_item);  //Method calc the output from the gate cal bitflip
                    boolean gateResult = this.calculateOutputFacultInjectionGateValueMultipleFaultInjectionAndCalculateSensitiveArea(gate.getGate().getType(), gate, gate.getGate().getInputs(), thread_item);  //Method calc the output from the gate cal bitflip
                    ///Signal faultSig;

                    if(this.tempIndex == -1){  // Defines a position in
                        this.tempIndex = 0;
                        //faultSig = thread_item.get_MTF_FaultSignal_List_thd().get(0);
                    }

                    for (int s = 0; s < gate.getGate().getOutputs().size(); s++) {

                        Signal sig = gate.getGate().getOutputs().get(s);

                        // track_operations = track_operations + "inputSigs: " + gate.getGate().getInputs() + " Gate: [" + gate.getGate().getId() + "] OutputSigs: " + gate.getGate().getOutputs();

                        if(gateResult){  // Set the gate output's from gateResult after search to apply fault injection if signal its in the fault list

                            //Define as saidas da porta lógica
                            //gate.getGate().getOutputs().get(s).setOriginalLogicValue(1);
                            gate.getGate().getOutputs().get(s).setLogicValue(1);
                            gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.TRUE);

                            if(gate.getGate().getOutputs().get(s).getOriginalLogicValue() != gate.getGate().getOutputs().get(s).getLogicValue()){
                                this.bitflipcounter++;
                                inner_bitflip_counter++;
                            }

                            /*
                            outGates =  "[" +gate.getGate().getId() + "]  Ori: " + gate.getGate().getOutputsOriginalValuesToString()

                                    + " New: " + gate.getGate().getOutputsValuesToString() + "  ";
                            */
                            //for (int inside = 0; inside < hashMap_temp.size(); inside++) { // Test Case for faulsignals in outputs gate

                            //boolean check = hashMap_temp.containsKey(inside); //key founded in hashmap

                            //

                            int pos = thread_item.getPositionFaultSignalInMTFListThd( gate.getGate().getOutputs().get(s));

                            if((pos > -1)) { //
                                //int pos = hashMap_temp.get(inside);
                                this.tempIndex = pos;
                                Signal fault_signal_MTF = thread_item.get_MTF_FaultSignal_List_thd().get(pos);

                                if (sig.getId().equals(fault_signal_MTF.getId())){

                                    //if ((gate.getGate().getOutputs().get(s).getLogicValue() == thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue()) && (!thread_item.getMTF_PERSONAL_LIST().get(pos).getBooleaFlag())) {
                                    // G7gat =        ORI 0  Aplica w4  G7(0/1) G7( 0/0)

                                    if ((gate.getGate().getOutputs().get(s).getLogicValue() == thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue())) {

                                        // outGates = outGates + " OUTGATE ****** 1 to 0 FAULTSIGNAL IDENTIFICADO " + fault_signal_MTF.getId() + "  posFaultList: " + pos;
                                        //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(1);

                                        thread_item.getMTF_PERSONAL_LIST().get(pos).setNewValue(0);
                                        thread_item.getMTF_PERSONAL_LIST().get(pos).setBooleanFlag();

                                        gate.getGate().getOutputs().get(s).setLogicValue(0);
                                        gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.FALSE);

                                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValue(0);
                                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValueBoolean(Boolean.FALSE);


                                    }
                                }

                            }
                            // }



                        }

                        else{
                            //gate.getGate().getOutputs().get(s).setOriginalLogicValue(0);
                            gate.getGate().getOutputs().get(s).setLogicValue(0);
                            gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.FALSE);

                            outGates = gate.getGate().getId() + "  Ori: " + gate.getGate().getOutputsOriginalValuesToString()
                                    + " New: " + gate.getGate().getOutputsValuesToString() + "  ";



                            if(gate.getGate().getOutputs().get(s).getOriginalLogicValue() != gate.getGate().getOutputs().get(s).getLogicValue()){
                                this.bitflipcounter++;
                                inner_bitflip_counter++;
                            }

                            // for (int inside = 0; inside < hashMap_temp.size(); inside++) { // Test Case for faulsignals in outputs gate

                            int pos = thread_item.getPositionFaultSignalInMTFListThd( gate.getGate().getOutputs().get(s));


                            if((pos > -1) ){ //

                                //int pos = hashMap_temp.get(inside);

                                this.tempIndex = pos;
                                Signal fault_signal_MTF = thread_item.get_MTF_FaultSignal_List_thd().get(pos);

                                if(sig.getId().equals(fault_signal_MTF.getId())) {
                                    //if(sig.getId().equals(faultSig.getId())){ // Define bitflip 0 to 1
                                    //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setVisited();
                                    //if ((gate.getGate().getOutputs().get(s).getLogicValue() == thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue()) && (!thread_item.getMTF_PERSONAL_LIST().get(pos).getBooleaFlag())) {
                                    if ((gate.getGate().getOutputs().get(s).getLogicValue() == thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue())) {


                                        // System.out.println("Aplicavel..." +
                                        //         "");

                                        // System.out.println("@ "+faultSig+" Sig EQUAL "+sig);
                                        thread_item.getMTF_PERSONAL_LIST().get(pos).setNewValue(1);
                                        thread_item.getMTF_PERSONAL_LIST().get(pos).setBooleanFlag();


                                        //outGates = outGates + "  OUTGATE ****** 0 to 1 FAULTSIGNAL IDENTIFICADO " + fault_signal_MTF.getId() + "  posFaultList: " + pos;

                                        //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(0);
                                        gate.getGate().getOutputs().get(s).setLogicValue(1);
                                        gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.TRUE);

                                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValue(1);
                                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValueBoolean(Boolean.TRUE);


                                        // thread_item.getMTF_PERSONAL_LIST().get(pos).CompInfoItemnize(debug);
                                    }
                                    //  }
                                }
                            }
                        }
                    }
                    /* Debug */
                    /*
                    track_operations = track_operations +  " in: [" + gate.getGate().getInputsValuesToString() + "]" +
                            //"  faultSig: " + faultSig.getId() + " value Ori - New: " + faultSig.getOriginalLogicValue() + " " + faultSig.getLogicValue()
                            " outPropageted: (" + gate.getGate().getOutputsValuesToString()
                            + ") free: " + gate.getGate().getOutputsOriginalValuesToString() + inGate + outGates +

                            "  ||  threadItem faultSig: " +  thread_item.get_MTF_FaultSignal_List_thd()
                            // + "  value Ori - New: " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getOriginalLogicValue()
                            // + " " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getLogicValue()
                            + " || "

                     */

                            /*
                            " inputs_value: " + gate.getGate().getInputsValuesToString() +
                            //"  faultSig: " + faultSig.getId() + " value Ori - New: " + faultSig.getOriginalLogicValue() + " " + faultSig.getLogicValue()
                            "-> GATE: " + gate.getGate().getId() + "  OutSignalGate: " + gate.getGate().getOutputs() + " value propag: concat(" + gate.getGate().getOutputsValuesToString()
                            + ") free: " + gate.getGate().getOutputsOriginalValuesToString() + inGate + outGates +

                            "  ||  threadItem faultSig: " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getId()
                            + "  value Ori - New: " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getOriginalLogicValue()
                            + " " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getLogicValue()
                            + " || " +

                            */

                    // "\n";

                    /* Debug Mode */


                    /*
                    if(thread_item.getSimulationIndex() == 100 || thread_item.getSimulationIndex() == 200)
                        System.out.println(thread_item.getSimulationIndex() + " - Inputs: " + gate.getGate().getInputs() + "  values: " +  gate.getGate().getInputsValuesToString() +  " Gate: " +  gate.getGate().getId()
                                + " Output: " + gate.getGate().getOutputsValuesToString() + " faultList: " + thread_item.get_MTF_FaultSignal_List_thd() + " faultListExtended: " + thread_item.getMTF_FaultSignal_List_Extended() );

                    */
                    /* Debug Mode */

                }
            }
        }

        //System.out.println(thread_item.getinputVector() + "  faullist: " + thread_item.getMTFPERSONAL_LIST_Identities() + " " + "Inner Bitflip: " + inner_bitflip_counter);


    }

    private  void propagateMultipleFaultInjectionsV2NEWMOD(TestVectorInformation thread_item) throws IOException, WriteException{

        this.threadID = (long) Thread.currentThread().getId();

        //System.out.println("-> Propagating testNumber(" + testNumber + ")" + " - at Thread_ID - " + this.threadID );
        //System.out.println("  Vector: " + vector)

        ArrayList <GateLevel> gatesLevels = this.levelCircuit.getGateLevels();

        String track_operations = thread_item.getSimulationIndex() + " -  Fault List: " + thread_item.get_MTF_FaultSignal_List_thd() + "    " + thread_item.getinputVector() +  "\n";
        String outGates = "";
        this.inGate = "";

        for (int j = 0; j < gatesLevels.size(); j++) {

            ArrayList <Object> gatesInThisLevel = gatesLevels.get(j).getGates();

            for (int k = 0; k < gatesInThisLevel.size(); k++) {
                String AwnsString = gatesInThisLevel.get(k).getClass().toString();
                //System.out.println("Aws: "+ AwnsString);
                if(AwnsString.equals("class levelDatastructures.DepthGate")){
                    Object object = gatesInThisLevel.get(k);
                    final DepthGate gate = (DepthGate) object;
                                        //pos, pos_faultList
                    ConcurrentHashMap<Integer, Integer> hashMap_temp = new ConcurrentHashMap<>();

                    //Aplica o bitflip nas entradas se estiver na lista de falhas e Calcula a saida da porta lógica  -- Testa os sinais de entrada para ver se estão na lista de falhas
                    boolean gateResult = this.calculateOutputFacultInjectionGateValueMultipleFaultInjectionV2NEWMODE(gate.getGate().getType(), gate, gate.getGate().getInputs(), thread_item);  //Method calc the output from the gate cal bitflip


                    Signal faultSig;

                    if(this.tempIndex == -1){  // Defines a position in
                        this.tempIndex = 0;
                        //faultSig = thread_item.get_MTF_FaultSignal_List_thd().get(0);

                    }


                    for (int s = 0; s < gate.getGate().getOutputs().size(); s++) {

                        Signal sig = gate.getGate().getOutputs().get(s);

                        track_operations = track_operations + "inputSigs: " + gate.getGate().getInputs() + " Gate: [" + gate.getGate().getId() + "] OutputSigs: " + gate.getGate().getOutputs();

                        if(gateResult){  // Set the gate output's from gateResult after search to apply fault injection if signal its in the fault list

                            //Define as saidas da porta lógica
                            gate.getGate().getOutputs().get(s).setOriginalLogicValue(1);
                            gate.getGate().getOutputs().get(s).setLogicValue(1);
                            gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.TRUE);


                            outGates =  "[" +gate.getGate().getId() + "]  Ori: " + gate.getGate().getOutputsOriginalValuesToString()
                                    + " New: " + gate.getGate().getOutputsValuesToString() + "  ";

                            //for (int inside = 0; inside < hashMap_temp.size(); inside++) { // Test Case for faulsignals in outputs gate

                                //boolean check = hashMap_temp.containsKey(inside); //key founded in hashmap

                                int pos = thread_item.getPositionFaultSignalInMTFListThd( gate.getGate().getOutputs().get(s));

                            if((pos > -1)){ //
                                    //int pos = hashMap_temp.get(inside);
                                    this.tempIndex = pos;
                                    Signal fault_signal_MTF = thread_item.get_MTF_FaultSignal_List_thd().get(pos);

                                    if(sig.getId().equals(fault_signal_MTF.getId())){
                                        //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setVisited();

                                        //System.out.println("INSIDEEEEEEEEE ...... " + sig + " test"+ thread_item.getSimulationIndex());
                                        //if(sig.getId().equals(faultSig.getId())){ // COmparar com lista novamente
                                        // Compara a saida da porta logica com faultsignalList e Define o bitflip 1 to 0


                                        outGates = outGates + " OUTGATE ****** 1 to 0 FAULTSIGNAL IDENTIFICADO " + fault_signal_MTF.getId() + "  posFaultList: " + pos;
                                        //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(1);


                                        gate.getGate().getOutputs().get(s).setLogicValue(1);
                                        gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.TRUE);

                                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValue(gate.getGate().getOutputs().get(s).getLogicValue());
                                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValueBoolean(gate.getGate().getOutputs().get(s).getLogicValueBoolean());

                                        outGates = outGates + " xxxx " +
                                                "" +
                                                "O(v): " + thread_item.get_MTF_FaultSignal_List_thd().get(pos).getOriginalLogicValue() + " N(v): " + thread_item.get_MTF_FaultSignal_List_thd().get(pos).getLogicValue();

                                        System.out.println(thread_item.getinputVector() + " ** 1 to 0 OUTPUSIGNAL FROM GATE: " + gate.getGate().getId()
                                                 + "  faultsig: "       +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getId() +
                                                "    MTF FAUlt LIST: " + thread_item.get_MTF_FaultSignal_List_thd() + "   POS: " + pos + "   VALUES: " +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getSignalOriginalAndNewValue()
                                        );
                                    }
                                }


                           // }



                        }
                        else{
                            gate.getGate().getOutputs().get(s).setOriginalLogicValue(0);
                            gate.getGate().getOutputs().get(s).setLogicValue(0);
                            gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.FALSE);

                            outGates = gate.getGate().getId() + "  Ori: " + gate.getGate().getOutputsOriginalValuesToString()
                                    + " New: " + gate.getGate().getOutputsValuesToString() + "  ";

                           // for (int inside = 0; inside < hashMap_temp.size(); inside++) { // Test Case for faulsignals in outputs gate

                                int pos = thread_item.getPositionFaultSignalInMTFListThd( gate.getGate().getOutputs().get(s));

                                if((pos > -1) ){ //
                                    //int pos = hashMap_temp.get(inside);

                                    this.tempIndex = pos;
                                    Signal fault_signal_MTF = thread_item.get_MTF_FaultSignal_List_thd().get(pos);

                                    if(sig.getId().equals(fault_signal_MTF.getId())) {
                                        //if(sig.getId().equals(faultSig.getId())){ // Define bitflip 0 to 1
                                        //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setVisited();

                                        // System.out.println("@ "+faultSig+" Sig EQUAL "+sig);
                                        outGates = outGates + "  OUTGATE ****** 0 to 1 FAULTSIGNAL IDENTIFICADO " + fault_signal_MTF.getId() + "  posFaultList: " + pos;

                                        //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(0);
                                        gate.getGate().getOutputs().get(s).setLogicValue(0);
                                        gate.getGate().getOutputs().get(s).setLogicValueBoolean(Boolean.FALSE);

                                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValue(gate.getGate().getOutputs().get(s).getLogicValue());
                                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValueBoolean(gate.getGate().getOutputs().get(s).getLogicValueBoolean());

                                        outGates = outGates + " 0 to 1 OUT XXXXX O(v): " + thread_item.get_MTF_FaultSignal_List_thd().get(pos).getOriginalLogicValue() + " N(v): " + thread_item.get_MTF_FaultSignal_List_thd().get(pos).getLogicValue();

                                        System.out.println(thread_item.getinputVector() + "  ** 0 to 1 OUTPUSIGNAL FROM GATE: " + gate.getGate().getId()
                                                + "  faultsig: "       +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getId() +
                                                "    MTF FAUlt LIST: " + thread_item.get_MTF_FaultSignal_List_thd() + "   POS: " + pos + "   VALUES: " +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getSignalOriginalAndNewValue()
                                        );
                                    }
                              //  }

                            }
                        }
                    }
                    /* Debug */
                    track_operations = track_operations +  " in: [" + gate.getGate().getInputsValuesToString() + "]" +
                            //"  faultSig: " + faultSig.getId() + " value Ori - New: " + faultSig.getOriginalLogicValue() + " " + faultSig.getLogicValue()
                            " outPropageted: (" + gate.getGate().getOutputsValuesToString()
                            + ") free: " + gate.getGate().getOutputsOriginalValuesToString() + inGate + outGates +

                            "  ||  threadItem faultSig: " +  thread_item.get_MTF_FaultSignal_List_thd()
                           // + "  value Ori - New: " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getOriginalLogicValue()
                           // + " " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getLogicValue()
                            + " || "
                            +
                            /*
                            " inputs_value: " + gate.getGate().getInputsValuesToString() +
                            //"  faultSig: " + faultSig.getId() + " value Ori - New: " + faultSig.getOriginalLogicValue() + " " + faultSig.getLogicValue()
                            "-> GATE: " + gate.getGate().getId() + "  OutSignalGate: " + gate.getGate().getOutputs() + " value propag: concat(" + gate.getGate().getOutputsValuesToString()
                            + ") free: " + gate.getGate().getOutputsOriginalValuesToString() + inGate + outGates +

                            "  ||  threadItem faultSig: " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getId()
                            + "  value Ori - New: " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getOriginalLogicValue()
                            + " " +  thread_item.get_MTF_FaultSignal_List_thd().get(this.tempIndex).getLogicValue()
                            + " || " +

                            */

                             "\n";

                    /* Debug Mode */


                    /*
                    if(thread_item.getSimulationIndex() == 100 || thread_item.getSimulationIndex() == 200)
                        System.out.println(thread_item.getSimulationIndex() + " - Inputs: " + gate.getGate().getInputs() + "  values: " +  gate.getGate().getInputsValuesToString() +  " Gate: " +  gate.getGate().getId()
                                + " Output: " + gate.getGate().getOutputsValuesToString() + " faultList: " + thread_item.get_MTF_FaultSignal_List_thd() + " faultListExtended: " + thread_item.getMTF_FaultSignal_List_Extended() );

                    */
                    /* Debug Mode */

                }
            }
        }
            if((thread_item.getSimulationIndex() == 100 || thread_item.getSimulationIndex() == 200)){
                //System.out.println(track_operations);
            }

    }

    public  void startSimulationFaultInjection() throws IOException, WriteException{

            for (int i = 0; i < this.threadSimulationList.size(); i++) {
                /*
                this.insertInputVectors("selected", this.threadSimulationList.get(i).getinputVector());

                if(this.threadSimulationList.get(i).get_MTF_flag() && this.threadSimulationList.get(i).get_MTF_FaultSignal_List().size()>1){
                    System.out.println("Detected MTF inside Logic Simulator...");
                    this.propagateMultipleFaultInjections(this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i).getinputVector(), this.threadSimulationList.get(i).getFaultSignal(), i,  this.threadSimulationList.get(i));
                    //PropagateFaultV2
                }
                else{
                    this.propagateFaultInjections(this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i).getinputVector(), this.threadSimulationList.get(i).getFaultSignal(), i,  this.threadSimulationList.get(i));
                }
                this.getFaultInjectionResults(this.threadSimulationList.get(i).getinputVector(), this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i));
                */


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
        }

        public void startSimulationMultipleFaultInjection() throws IOException, WriteException{

            for (int i = 0; i < this.threadSimulationList.size(); i++) {

                this.insertInputVectors("selected", this.threadSimulationList.get(i).getinputVector());

                //                this.propagateMultipleFaultInjectionsV2NEWMOD(this.threadSimulationList.get(i));
                //this.propagateMultipleFaultInjectionsV2NEWMODMODULAR(this.threadSimulationList.get(i)); //WORKS PERFECT
                this.propagateMultipleFaultInjectionsV2NEWMODMODULARFASTER(this.threadSimulationList.get(i)); //SOLVE MTF PROBLEMS


                //this.getFaultInjectionResults(this.threadSimulationList.get(i).getinputVector(), this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i));

                this.getFaultInjectionResultsMTF(this.threadSimulationList.get(i).getinputVector(), this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i));

        }
            System.out.println("End thd....");

            System.out.println("-" + threadID + " NUMBER OF BITFLIPS: " + this.bitflipcounter);

        //this.settingFaultInjectionResults();
            this.settingFaultInjectionResultsMTF();
    }

    public ArrayList<String> getparsedNetlistContent(){
      return this.parsedNetlistContent;
    }

    public void calculateSensitiveAreaGenerateSpiceNetLists() throws IOException, WriteException{

        // System.out.println("- threadSimulationList: " + this.threadSimulationList.size());
        for (int i = 0; i < this.threadSimulationList.size(); i++) {

            this.insertInputVectors("selected", this.threadSimulationList.get(i).getinputVector());
            this.propagateAndCalculateSensitiveAreas(this.threadSimulationList.get(i)); //SOLVE MTF PROBLEMS
            this.getFaultInjectionResultsMTF(this.threadSimulationList.get(i).getinputVector(), this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i));

                String freeFaultOutput_i, faultOutput_i;
                freeFaultOutput_i = this.threadSimulationList.get(i).getOrignalOutput();
                faultOutput_i = this.threadSimulationList.get(i).getFaultOutput();



        }
         //System.out.println("End Thd");

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        System.out.println("FI Finished thd : " + threadID + "  at: " + formattedDate + " NUMBER OF BITFLIPS: " + this.bitflipcounter);

        //this.settingFaultInjectionResults();
        this.settingFaultInjectionResultsMTF();
    }




    public void startSimulationMultipleFaultInjectionCalculateSensitiveAreaGenerateSpiceNetLists() throws IOException, WriteException{

        // System.out.println("- threadSimulationList: " + this.threadSimulationList.size());
        for (int i = 0; i < this.threadSimulationList.size(); i++) {

            this.insertInputVectors("selected", this.threadSimulationList.get(i).getinputVector());
            this.propagateMultipleFaultInjectionsAndCalculateSensitiveAreas(this.threadSimulationList.get(i)); //SOLVE MTF PROBLEMS
            this.getFaultInjectionResultsMTF(this.threadSimulationList.get(i).getinputVector(), this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i));

            String freeFaultOutput_i, faultOutput_i;
            freeFaultOutput_i = this.threadSimulationList.get(i).getOrignalOutput();
            faultOutput_i = this.threadSimulationList.get(i).getFaultOutput();

            if(!freeFaultOutput_i.equals(faultOutput_i)){ // Set identified
                ///String set = i + "  freeFaultOutput_i: " + freeFaultOutput_i + "   faultOutput_i: " + faultOutput_i;

                    /*
                    System.out.println("- SET propaget faultSig: " + this.threadSimulationList.get(i).getFaultSignals() +
                            "   FList: " + this.threadSimulationList.get(i).get_MTF_FaultSignal_List_thd() + " vec: " + this.threadSimulationList.get(i).getinputVector() + " - threadSimulationList: " + i + "  thd: " + this.getThreadId() + "  info: " + set);
                    */
                //this.propagated_faults++;

                //this.parsedNetlistContent = this.createSpiceNetlistLargerCircuits(this.threadSimulationList.get(i));

                /* UNCOMMEND*/
                this.parsedNetlistContent.add(this.createSpiceNetlistLargerCircuits(this.threadSimulationList.get(i)));
                //this.parsedNetlistContent.add("");
            }else{
                this.parsedNetlistContent.add("");
            }

        }
        //System.out.println("End Thd");

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        System.out.println("FI Finished thd : " + threadID + "  at: " + formattedDate + " NUMBER OF BITFLIPS: " + this.bitflipcounter);

        //this.settingFaultInjectionResults();
        this.settingFaultInjectionResultsMTF();
    }


    public void startSimulationMultipleFaultInjectionMonteCarlo() throws IOException, WriteException{

        // System.out.println("- threadSimulationList: " + this.threadSimulationList.size());
        for (int i = 0; i < this.threadSimulationList.size(); i++) {

            this.insertInputVectors("selected", this.threadSimulationList.get(i).getinputVector());
            this.propagateMultipleFaultInjectionsAndCalculateSensitiveAreas(this.threadSimulationList.get(i)); //SOLVE MTF PROBLEMS
            this.getFaultInjectionResultsMTF(this.threadSimulationList.get(i).getinputVector(), this.threadSimulationList.get(i).getSimulationIndex(), this.threadSimulationList.get(i));



        }
        //System.out.println("End Thd");

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        System.out.println("FI Finished thd : " + threadID + "  at: " + formattedDate + " NUMBER OF BITFLIPS: " + this.bitflipcounter);

        //this.settingFaultInjectionResults();
        this.settingFaultInjectionResultsMTF();
    }


    private int getFaultSignalPosition(ArrayList <Signal> List, Signal inputSignal, int index, TestVectorInformation thread_item){

            for(int i=0; i < List.size(); i++)
            {
                //System.out.println(List.get(i).getId() + "   -  " + inputSignal.getId());
                if(List.get(i).getId().equals(inputSignal.getId())){
                    //System.out.println("Sig  + " + List.get(i) + "  - " +  thread_item.get_MTF_FaultSignal_List() + "   - info: " + thread_item.getSimulationIndex());
                    //List.remove(i);
                    return i;
                }
            }

            return -1;
           // return false;
    }

        private boolean decision(int pos){
        return pos > -1;
    }

        private  boolean calculateOutputFacultInjectionGateValueV2(Cell cells, DepthGate gate, ArrayList <Signal> inputsSignals, ArrayList <Signal> MTFfaultSignalList, Signal fault, TestVectorInformation thread_item){
        //System.out.println("inn... + " + thread_item.getItem().toString());
        final Map<ArrayList<Boolean>, Boolean> comb = cells.getComb();
        final ArrayList <Boolean> input = new ArrayList<>();
        final ArrayList <Integer> signals = new ArrayList<>();
        Signal faultSig;

        //ArrayList <Signal> faultSig2temp = new ArrayList<>(MTFfaultSignalList);

        for (int index = 0; index < inputsSignals.size(); index++) { // Input Signals index

                int pos = getFaultSignalPosition(MTFfaultSignalList, inputsSignals.get(index), index, thread_item);

                //faultSig = MTFfaultSignalList.get(pos);  //Pos in the fault list

                //System.out.println("Fault Signal MTF : " + faultSig + "   - " + thread_item.get_MTF_FaultSignal_List());

               if (decision(pos) && thread_item.get_MTF_flag() && MTFfaultSignalList.size()>1) { //bit-flip

                  // faultSig = faultSig2temp.get(pos);  //Pos in the fault list
                   faultSig = MTFfaultSignalList.get(pos);

                    // if (inputsSignals.get(index).getId().equals(faultSig.getId()) && decision(pos) && thread_item.get_MTF_flag()) { //bit-flip

                   System.out.println("        - FaultSignal + " + faultSig + " new list: " + MTFfaultSignalList + "    removed from  " + thread_item.get_MTF_FaultSignal_List() + "  "+ thread_item.getSimulationIndex());

                    //faultSig2temp.remove(faultSig2temp.get(pos));
                    //MTFfaultSignalList.remove(faultSig2temp.get(pos));
                    //System.out.println("entrou");
                    if (inputsSignals.get(index).getOriginalLogicValue() == 0) { //Efetua o bitflip

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


                        /* SET Bitflip for multiple fault*/
                        thread_item.get_MTF_FaultSignal_List().get(pos).setOriginalLogicValue(0);
                        thread_item.get_MTF_FaultSignal_List().get(pos).setLogicValue(1);
                        thread_item.get_MTF_FaultSignal_List().get(pos).setLogicValueBoolean(Boolean.TRUE);


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
                    } else {
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

                        /* New block*/
                        /* SET Bitflip for multiple fault*/
                        thread_item.get_MTF_FaultSignal_List().get(pos).setOriginalLogicValue(1);
                        thread_item.get_MTF_FaultSignal_List().get(pos).setLogicValue(0);
                        thread_item.get_MTF_FaultSignal_List().get(pos).setLogicValueBoolean(Boolean.FALSE);

                    }
                    System.out.println("\n -> fault injected (" + faultSig + ")" +  " - Original(v):"+inputsSignals.get(index).getOriginalLogicValue() + "  New(v):"+inputsSignals.get(index).getLogicValue() + "  - FaultList" + thread_item.get_MTF_FaultSignal_List() + " - ID: " + thread_item.getSimulationIndex()
                            + "  \n         threaditemFaultList: " + thread_item.get_MTF_FaultSignal_List().get(pos)
                            + " O(v):" + thread_item.get_MTF_FaultSignal_List().get(pos).getOriginalLogicValue()
                            + " N(v):" + thread_item.get_MTF_FaultSignal_List().get(pos).getLogicValue());
                }

                signals.add(inputsSignals.get(index).getLogicValue());

                if (inputsSignals.get(index).getLogicValue() == 0) {
                    input.add(Boolean.FALSE);
                }
                if (inputsSignals.get(index).getLogicValue() == 1) {
                    input.add(Boolean.TRUE);
                }

            }




        //System.out.println("                                Input Signal: " + inputsSignals + " v: "+input);
        Object output = "stuck";

        String r = "";

        String gate_temp = gate.getGate().getType().toString();
        //System.out.println("Gate: " + gate_temp);

        if(gate_temp.equals("ZERO")){
            //System.out.println("  OPS ------------------------------ ZERO    " + gate_temp );
            r = "0";
            //boolean saida =
            return Boolean.FALSE;
        }
        if(gate_temp.equals("ONE")){
            //System.out.println("  OPS ------------------------------ OONE    " + gate_temp );
            r = "1";
            //boolean saida =
            return Boolean.TRUE;
        }
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
            System.out.println("ERROR stuck !!!!! o: "+output + "  GATE: " + gate.getGate() + "  type: " + gate.getGate().getType());


        }

        //System.out.println("        END ROUND- FaultSignal + " + " new list: " + faultSig2temp + "    removed from  " + thread_item.get_MTF_FaultSignal_List() + "  "+ thread_item.getSimulationIndex());

        return (boolean) output;
    }

        // return signals logic value
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

        //Compara os sinais da lista de falhas com sinais de entrada da porta lógica
    private  boolean calculateOutputFacultInjectionGateValueMultipleFaultInjectionV2NEWMODE(Cell cells, DepthGate gate, ArrayList <Signal> inputsSignals, TestVectorInformation thread_item){

            //System.out.println("inn... + " + thread_item.getItem().toString());
        final Map<ArrayList<Boolean>, Boolean> comb = cells.getComb(); // Calculate the gate output
        final ArrayList <Boolean> input = new ArrayList<>();
        //final ArrayList <Integer> signals = new ArrayList<>();

        for (int index = 0; index < inputsSignals.size(); index++) {

            // Bloco para decisão se é ou não sinal da lista de falhas
            //Signal faultSig = null ;//thread_item.getPositionFaultSignalInMTFList(inputsSignals.get(index));

            int pos = thread_item.getPositionFaultSignalInMTFListThd(inputsSignals.get(index)); //identifica se o sinal de entrada (index) está na lista de sinais a serem injetadas as falhas

            if(pos > -1){ // Identifica se a posição na lista de falhas esta vazia (pos>-1)

                this.tempIndex = pos;

                //hashmap.put(index , pos);

                if((inputsSignals.get(index).getOriginalLogicValue() == 0) && (inputsSignals.get(index).getId().equals(thread_item.getMTF_FaultSignal_List_thd().get(pos).getId()))){ //Efetua o bitflip para sinal com valor 0 original

                    // if((inputsSignals.get(index).getOriginalLogicValue() == 0) && (inputsSignals.get(index).getId().equals(thread_item.getMTF_FaultSignal_List_thd().get(pos).getId()))){ //Efetua o bitflip para sinal com valor 0 original
                    /* Here is applied the bitflip on gate input signals 0 to 1 *Signals present in MultipleFaultList*/
                    //thread_item.getMTF_FaultSignal_List_thd().get(pos).setVisited();
                    //thread_item.setFAULT_MTF_PERSONAL_LIST(pos, inputsSignals.get(index).getOriginalLogicValue(), thread_item.getinputVector());
                    //thread_item.getMTF_PERSONAL_LIST().get(pos).setOriginalValue(0);
                    thread_item.getMTF_PERSONAL_LIST().get(pos).setNewValue(1);
                    //thread_item.getMTF_FaultSignal_List_thd().get(pos).setVisited();

                    inputsSignals.get(index).setOriginalLogicValue(0);
                    inputsSignals.get(index).setLogicValue(1);
                    inputsSignals.get(index).setLogicValueBoolean(Boolean.TRUE);

                    //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(0);
                    thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValue(1);
                    thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValueBoolean(Boolean.TRUE);

                    String debug = " inputSig: " + inputsSignals.get(index) + " Ori: " + inputsSignals.get(index).getOriginalLogicValue()
                                + "  N: " + inputsSignals.get(index).getLogicValue() +
                            " || Thd Sig " + thread_item.get_MTF_FaultSignal_List_thd().get(pos).getId() +  " Ori: " +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getOriginalLogicValue()
                            + "  N: " + thread_item.get_MTF_FaultSignal_List_thd().get(pos).getLogicValue() + "|| " + thread_item.get_MTF_FaultSignal_List_thd()
                            +"  || itemnizelist: " + thread_item.getMTF_PERSONAL_LIST_NODESINFO()  + " ||";
                    /*DEbug*/
                    /*
                    track_operations = track_operations + "  input ****** 0 to 1 FAULT INPUT SIGNAL IDENTIFICADO ["
                            +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getId()
                            + "]  Ori: " +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getOriginalLogicValue()
                            + " New: " +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getLogicValue() +"  posFaultList: " + pos + "  ~ ";
                    */


                    //thread_item.getMTF_PERSONAL_LIST().get(pos).CompInfoItemnize(debug);



                    // IF case to development and see possible errors

                    if((0 != inputsSignals.get(index).getOriginalLogicValue())){

                        String oriInput = "Sig: " + inputsSignals.get(index) + " Ori(v): " + inputsSignals.get(index).getOriginalLogicValue();
                        String oriInputNew = "  New(v): " + inputsSignals.get(index).getLogicValue() + "  end inputs";

                        String newInputThdOri = "  --- Ori_Thd(v): " + thread_item.getMTF_FaultSignal_List_thd().get(pos).getOriginalLogicValue();
                        String newInputThd = "  New_Thd(v): " + thread_item.getMTF_FaultSignal_List_thd().get(pos).getLogicValue();
                        System.err.println(thread_item.getinputVector() + "  Tipo 0 Original bit 0 to 1>  id: " + thread_item.getSimulationIndex() +" v2 ERROR threadItem  2 :" + oriInput + oriInputNew + newInputThdOri  + " =  " + newInputThd );
                    }

                    if((1 != inputsSignals.get(index).getLogicValue())){

                        String oriInput = "Sig: " + inputsSignals.get(index) + " Ori(v): " + inputsSignals.get(index).getOriginalLogicValue();
                        String oriInputNew = "  New(v): " + inputsSignals.get(index).getLogicValue() + "  end inputs";

                        String newInputThdOri = "   ~~~~ Ori_Thd(v): " + thread_item.getMTF_FaultSignal_List_thd().get(pos).getOriginalLogicValue();
                        String newInputThd = "  New_Thd(v): " + thread_item.getMTF_FaultSignal_List_thd().get(pos).getLogicValue();
                        System.err.println(thread_item.getinputVector() +" Tipo 0 Logical bit 0 to 1> id: " + thread_item.getSimulationIndex()+" v2 ERROR threadItem  2 :" + oriInput + oriInputNew + newInputThdOri  + " =  " + newInputThd );
                    }




                }
                if((inputsSignals.get(index).getOriginalLogicValue() ==  1) && (thread_item.getMTF_FaultSignal_List_thd().get(pos).getId().equals(inputsSignals.get(index).getId()))){
                    /* Here is applied the bitflip on gate input signals 1 to 0 *Signals present in MultipleFaultList*/
                     //
                    // thread_item.getMTF_FaultSignal_List_thd().get(pos).setVisited();
                    //thread_item.setFAULT_MTF_PERSONAL_LIST(pos, inputsSignals.get(index).getOriginalLogicValue(), thread_item.getinputVector());
                    //thread_item.getMTF_PERSONAL_LIST().get(pos).setOriginalValue(1);
                    thread_item.getMTF_PERSONAL_LIST().get(pos).setNewValue(0);

                    //thread_item.getMTF_FaultSignal_List_thd().get(pos).setVisited();
                    inputsSignals.get(index).setOriginalLogicValue(1);
                    inputsSignals.get(index).setLogicValue(0);
                    inputsSignals.get(index).setLogicValueBoolean(Boolean.FALSE);

                    //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(1);
                    thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValue(0);
                    thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValueBoolean(Boolean.FALSE);

                    String debug = " inputSig: " + inputsSignals.get(index) + " Ori: " + inputsSignals.get(index).getOriginalLogicValue()
                            + "  N: " + inputsSignals.get(index).getLogicValue() +
                            " || Thd Sig " + thread_item.get_MTF_FaultSignal_List_thd().get(pos).getId() +  " Ori: " +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getOriginalLogicValue()
                            + "  N: " + thread_item.get_MTF_FaultSignal_List_thd().get(pos).getLogicValue() + "|| " + thread_item.get_MTF_FaultSignal_List_thd()
                            +"  || itemnizelist: " + thread_item.getMTF_PERSONAL_LIST_NODESINFO()  + " ||";

                    //thread_item.getMTF_PERSONAL_LIST().get(pos).CompInfoItemnize(debug);

                    /* Debug */
                    /*
                    track_operations = track_operations + "  input ****** 1 to 0 FAULT INPUT SIGNAL IDENTIFICADO ["
                            +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getId()
                            + "]  Ori: "
                            +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getOriginalLogicValue()
                            + " New: " +  thread_item.get_MTF_FaultSignal_List_thd().get(pos).getLogicValue() +"  posFaultList: " + pos + " ~ ";
                    */
                    // IF case to development and see possible errors

                    if((1 != inputsSignals.get(index).getOriginalLogicValue())){

                        String oriInput = "Sig: " + inputsSignals.get(index) + " Ori(v): " + inputsSignals.get(index).getOriginalLogicValue();
                        String oriInputNew = "  New(v): " + inputsSignals.get(index).getLogicValue() + "  end inputs";


                        String newInputThdOri = "  --- Ori_Thd(v): " + thread_item.getMTF_FaultSignal_List_thd().get(pos).getOriginalLogicValue();
                        String newInputThd = "  New_Thd(v): " + thread_item.getMTF_FaultSignal_List_thd().get(pos).getLogicValue();
                        System.err.println(thread_item.getinputVector() + " Tipo 1 Original bit 1 to 0>  id: " + thread_item.getSimulationIndex()+" v2 ERROR threadItem  2 :" + oriInput + oriInputNew + newInputThdOri  + " =  " + newInputThd );
                    }

                    if((0 != inputsSignals.get(index).getLogicValue())){

                        String oriInput = "Sig: " + inputsSignals.get(index) + " Ori(v): " + inputsSignals.get(index).getOriginalLogicValue();
                        String oriInputNew = "  New(v): " + inputsSignals.get(index).getLogicValue() + "  end inputs";


                        String newInputThdOri = "   ~~~~ Ori_Thd(v): " + thread_item.getMTF_FaultSignal_List_thd().get(pos).getOriginalLogicValue();
                        String newInputThd = "  New_Thd(v): " + thread_item.getMTF_FaultSignal_List_thd().get(pos).getLogicValue();
                        System.err.println(thread_item.getinputVector() + " Tipo 1 Logical bit 1 to 0> id: " + thread_item.getSimulationIndex()+" v2 ERROR threadItem  2 :" + oriInput + oriInputNew + newInputThdOri  + " =  " + newInputThd );
                    }



                }

              //  this.inGate = track_operations;

            }
                if(inputsSignals.get(index).getLogicValue() == 0){
                    input.add(Boolean.FALSE);  //Store input to another arrayList
                }
                if(inputsSignals.get(index).getLogicValue() == 1){
                    input.add(Boolean.TRUE); //Store input to another arrayList
                }
        }

          //Convert the input signal values to boolean
          boolean output_converted = this.calculateTheOutputGatesInBoolean(comb, input, gate);

            /* ---------- Special case for logical gates as wires GATE: ZERO or ONE ------- */
            String r = "";

            String gate_temp = gate.getGate().getType().toString();
            //System.out.println("Gate: " + gate_temp);

            if(gate_temp.equals("ZERO")){
                //System.out.println("  OPS ------------------------------ ZERO    " + gate_temp );
                return Boolean.FALSE;
            }
            if(gate_temp.equals("ONE")){
                //System.out.println("  OPS ------------------------------ OONE    " + gate_temp );
                return Boolean.TRUE;
            }
            /* ---------- Special case for logical gates as wires GATE: ZERO or ONE ------- */

        return output_converted;
    }

    private  boolean calculateOutputFacultInjectionGateValueMultipleFaultInjectionV2NEWMODEFASTER(Cell cells, DepthGate gate, ArrayList <Signal> inputsSignals, TestVectorInformation thread_item){

        //System.out.println("inn... + " + thread_item.getItem().toString());
        final Map<ArrayList<Boolean>, Boolean> comb = cells.getComb(); // Calculate the gate output
        final ArrayList <Boolean> input = new ArrayList<>();
        //final ArrayList <Integer> signals = new ArrayList<>();

        for (int index = 0; index < inputsSignals.size(); index++) {

            // Bloco para decisão se é ou não sinal da lista de falhas
            //Signal faultSig = null ;//thread_item.getPositionFaultSignalInMTFList(inputsSignals.get(index));

            int pos = thread_item.getPositionFaultSignalInMTFListThd(inputsSignals.get(index)); //identifica se o sinal de entrada (index) está na lista de sinais a serem injetadas as falhas

            if(pos > -1){ // Identifica se a posição na lista de falhas esta vazia (pos>-1)

                this.tempIndex = pos;

                //hashmap.put(index , pos);
                //

                if((inputsSignals.get(index).getOriginalLogicValue() == 0
                ) && (inputsSignals.get(index).getId().equals(thread_item.getMTF_FaultSignal_List_thd().get(pos).getId()))){ //Efetua o bitflip para sinal com valor 0 original

                    if ((inputsSignals.get(index).getLogicValue() == thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue()) && (!thread_item.getMTF_PERSONAL_LIST().get(pos).getBooleaFlag())) {

                        // if((inputsSignals.get(index).getOriginalLogicValue() == 0) && (inputsSignals.get(index).getId().equals(thread_item.getMTF_FaultSignal_List_thd().get(pos).getId()))){ //Efetua o bitflip para sinal com valor 0 original
                        /* Here is applied the bitflip on gate input signals 0 to 1 *Signals present in MultipleFaultList*/
                        //thread_item.getMTF_FaultSignal_List_thd().get(pos).setVisited();
                        //thread_item.setFAULT_MTF_PERSONAL_LIST(pos, inputsSignals.get(index).getOriginalLogicValue(), thread_item.getinputVector());
                        //thread_item.getMTF_PERSONAL_LIST().get(pos).setOriginalValue(0);
                        thread_item.getMTF_PERSONAL_LIST().get(pos).setNewValue(1);

                        thread_item.getMTF_PERSONAL_LIST().get(pos).setBooleanFlag();
                        //thread_item.getMTF_FaultSignal_List_thd().get(pos).setVisited();

                        inputsSignals.get(index).setOriginalLogicValue(0);
                        inputsSignals.get(index).setLogicValue(1);
                        inputsSignals.get(index).setLogicValueBoolean(Boolean.TRUE);

                        //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(0);
                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValue(1);
                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValueBoolean(Boolean.TRUE);

                    }
                }
                if((inputsSignals.get(index).getOriginalLogicValue() ==  1) && (thread_item.getMTF_FaultSignal_List_thd().get(pos).getId().equals(inputsSignals.get(index).getId()))){
                    /* Here is applied the bitflip on gate input signals 1 to 0 *Signals present in MultipleFaultList*/
                    //
                    // thread_item.getMTF_FaultSignal_List_thd().get(pos).setVisited();
                    //thread_item.setFAULT_MTF_PERSONAL_LIST(pos, inputsSignals.get(index).getOriginalLogicValue(), thread_item.getinputVector());
                    //thread_item.getMTF_PERSONAL_LIST().get(pos).setOriginalValue(1);

                    if ((inputsSignals.get(index).getLogicValue() == thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue()) && (!thread_item.getMTF_PERSONAL_LIST().get(pos).getBooleaFlag())) {

                        thread_item.getMTF_PERSONAL_LIST().get(pos).setNewValue(0);

                        thread_item.getMTF_PERSONAL_LIST().get(pos).setBooleanFlag();


                        //thread_item.getMTF_FaultSignal_List_thd().get(pos).setVisited();
                        inputsSignals.get(index).setOriginalLogicValue(1);
                        inputsSignals.get(index).setLogicValue(0);
                        inputsSignals.get(index).setLogicValueBoolean(Boolean.FALSE);

                        //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(1);
                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValue(0);
                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValueBoolean(Boolean.FALSE);
                    }

                }

                //  this.inGate = track_operations;

            }
            if(inputsSignals.get(index).getLogicValue() == 0){
                input.add(Boolean.FALSE);  //Store input to another arrayList
            }
            if(inputsSignals.get(index).getLogicValue() == 1){
                input.add(Boolean.TRUE); //Store input to another arrayList
            }
        }

        /*ORIGNAL POS*/
        //Convert the input signal values to boolean
        //boolean output_converted = this.calculateTheOutputGatesInBoolean(comb, input, gate);

        /* ---------- Special case for logical gates as wires GATE: ZERO or ONE ------- */
        String r = "";

        String gate_temp = gate.getGate().getType().toString();
        //System.out.println("Gate: " + gate_temp);

        if(gate_temp.equals("ZERO")){
            //System.out.println("  OPS ------------------------------ ZERO    " + gate_temp );
            return Boolean.FALSE;
        }
        if(gate_temp.equals("ONE")){
            //System.out.println("  OPS ------------------------------ OONE    " + gate_temp );
            return Boolean.TRUE;
        }
        /* ---------- Special case for logical gates as wires GATE: ZERO or ONE ------- */

        //Convert the input signal values to boolean
        boolean output_converted = this.calculateTheOutputGatesInBoolean(comb, input, gate);


        return output_converted;
    }

    private  boolean calculateSensitiveArea(Cell cells, DepthGate gate, ArrayList <Signal> inputsSignals, TestVectorInformation thread_item){

        final Map<ArrayList<Boolean>, Boolean> comb = cells.getComb(); // Calculate the gate output
        final ArrayList <Boolean> input = new ArrayList<>();
        //final ArrayList <Integer> signals = new ArrayList<>();
        String concat_inputs = "";
        String concat = "";


        for (int index = 0; index < inputsSignals.size(); index++) {

            // Bloco para decisão se é ou não sinal da lista de falhas
            //Signal faultSig = null ;//thread_item.getPositionFaultSignalInMTFList(inputsSignals.get(index));
            //Test2 t2 = (Test2)t1.clone();
            //this.gates_passed.add();

            if(inputsSignals.get(index).getLogicValue() == 0){
                input.add(Boolean.FALSE);  //Store input to another arrayList
                concat_inputs = concat_inputs + "0";


            }
            if(inputsSignals.get(index).getLogicValue() == 1){
                input.add(Boolean.TRUE); //Store input to another arrayList
                concat_inputs = concat_inputs + "1";
            }
        }
        concat = concat_inputs;



        /*ORIGNAL POS*/
        //Convert the input signal values to boolean
        //boolean output_converted = this.calculateTheOutputGatesInBoolean(comb, input, gate);

        /* ---------- Special case for logical gates as wires GATE: ZERO or ONE ------- */
        String r = "";

        String gate_temp = gate.getGate().getType().toString();
        //System.out.println("Gate: " + gate_temp);

        if(gate_temp.equals("ZERO")){
            //System.out.println("  OPS ------------------------------ ZERO    " + gate_temp );
            return Boolean.FALSE;
        }
        if(gate_temp.equals("ONE")){
            //System.out.println("  OPS ------------------------------ OONE    " + gate_temp );
            return Boolean.TRUE;
        }
        /* ---------- Special case for logical gates as wires GATE: ZERO or ONE ------- */

        //Convert the input signal values to boolean
        boolean output_converted = this.calculateTheOutputGatesInBoolean(comb, input, gate); // hero to convert

        /* Calculate Sensitive Area of This Gate */
        ///SensitiveCell cell = this.sensitive_cells.get(gate.getGate().getType()  + "_" + concat_inputs);
        String key = "";
        if(gate.getGate().getType().toString().contains("X1")){ // "X1" version
            key = gate.getGate().getType()  + "_" + concat_inputs; // Calculate the exact input vector
        }
        else{
            key = gate.getGate().getType()  + "X1_" + concat_inputs; // Calculate the exact input vector

        }

        SensitiveCell cell = this.sensitive_cells.get(key);

        //TODO: Add the X1 in contain keys

       // System.out.println(input + "->  " + gate.getGate().getType() + "  cell: " + cell.getCell_id() + "  -- key " + key + " cellList: " + this.sensitive_cells.size());

        //CircuitPath circuitPath = new CircuitPath();
        //circuitPath.setCircuitInputCircuitVector(thread_item.getinputVector());

            GateDetailedInformation gateSensitivivity = new GateDetailedInformation();
            gateSensitivivity.setGate(gate);
            gateSensitivivity.setCell(cells);
            gateSensitivivity.setInputs(input);
            gateSensitivivity.setOutputs(output_converted);


            //gateSensitivivity.print();
            //thread_item.circuitPath.setGateInCircuitPath(gateSensitivivity);

        // TODO: 26/09/2022 v2 
        if((cell != null)){

                //thread_item.circuitPathv2.setGateInCircuitPath(gateSensitivivity);

            //this.sum_sensitive_cells_area = this.sum_sensitive_cells_area + Float.parseFloat(cell.getSensitive_are());
            thread_item.sum_sensitive_cells_area(Float.parseFloat(cell.getSensitive_are()));
            thread_item.sum_sensitive_cells_area_gate(Float.parseFloat(cell.getSensitive_are()), gate);
            gateSensitivivity.setgateSensitiveArea(Float.parseFloat(cell.getSensitive_are()));
            thread_item.setGatesLogicalPath(gateSensitivivity);
            System.out.println("idx: " + thread_item.getSimulationIndex() + "  invec: " + thread_item.getinputVector() + " gateid: " + gate.getGate().getId() + " gate: " + gate.getGate().getOutputs() + " sigs: " + gate.getGate().getInputs() +  " CEll founded: " + cell.getCell_id() + " input: " +cell.getInput_vec()  +
            " out: " + output_converted +
               " sensitive area: "+ cell.getSensitive_are() + " sum: " + thread_item.getSum_sensitive_cells_area());
            /*
            gateSensitivivity.setSensitiveArea(Float.parseFloat(cell.getSensitive_are()));
            circuitPath.setGateInCircuitPath(gateSensitivivity);
            thread_item.circuitPath.add(gateSensitivivity);
            */
            gateSensitivivity.calculatGateSusceptibility(input);

        }


        return output_converted;
    }

    private  boolean calculateOutputFacultInjectionGateValueMultipleFaultInjectionAndCalculateSensitiveArea(Cell cells, DepthGate gate, ArrayList <Signal> inputsSignals, TestVectorInformation thread_item){

        //System.out.println("inn... + " + thread_item.getItem().toString());
        final Map<ArrayList<Boolean>, Boolean> comb = cells.getComb(); // Calculate the gate output
        final ArrayList <Boolean> input = new ArrayList<>();
        //final ArrayList <Integer> signals = new ArrayList<>();
        String concat_inputs = "";
        for (int index = 0; index < inputsSignals.size(); index++) {

            // Bloco para decisão se é ou não sinal da lista de falhas
            //Signal faultSig = null ;//thread_item.getPositionFaultSignalInMTFList(inputsSignals.get(index));

            int pos = thread_item.getPositionFaultSignalInMTFListThd(inputsSignals.get(index)); //identifica se o sinal de entrada (index) está na lista de sinais a serem injetadas as falhas

            if(pos > -1){ // Identifica se a posição na lista de falhas esta vazia (pos>-1)

                this.tempIndex = pos;

                //hashmap.put(index , pos);
                //

                if((inputsSignals.get(index).getOriginalLogicValue() == 0
                ) && (inputsSignals.get(index).getId().equals(thread_item.getMTF_FaultSignal_List_thd().get(pos).getId()))){ //Efetua o bitflip para sinal com valor 0 original

                    if ((inputsSignals.get(index).getLogicValue() == thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue()) && (!thread_item.getMTF_PERSONAL_LIST().get(pos).getBooleaFlag())) {

                        // if((inputsSignals.get(index).getOriginalLogicValue() == 0) && (inputsSignals.get(index).getId().equals(thread_item.getMTF_FaultSignal_List_thd().get(pos).getId()))){ //Efetua o bitflip para sinal com valor 0 original
                        /* Here is applied the bitflip on gate input signals 0 to 1 *Signals present in MultipleFaultList*/
                        //thread_item.getMTF_FaultSignal_List_thd().get(pos).setVisited();
                        //thread_item.setFAULT_MTF_PERSONAL_LIST(pos, inputsSignals.get(index).getOriginalLogicValue(), thread_item.getinputVector());
                        //thread_item.getMTF_PERSONAL_LIST().get(pos).setOriginalValue(0);
                        thread_item.getMTF_PERSONAL_LIST().get(pos).setNewValue(1);

                        thread_item.getMTF_PERSONAL_LIST().get(pos).setBooleanFlag();
                        //thread_item.getMTF_FaultSignal_List_thd().get(pos).setVisited();

                        inputsSignals.get(index).setOriginalLogicValue(0);
                        inputsSignals.get(index).setLogicValue(1);
                        inputsSignals.get(index).setLogicValueBoolean(Boolean.TRUE);

                        //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(0);
                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValue(1);
                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValueBoolean(Boolean.TRUE);

                    }
                }
                if((inputsSignals.get(index).getOriginalLogicValue() ==  1) && (thread_item.getMTF_FaultSignal_List_thd().get(pos).getId().equals(inputsSignals.get(index).getId()))){
                    /* Here is applied the bitflip on gate input signals 1 to 0 *Signals present in MultipleFaultList*/
                    //
                    // thread_item.getMTF_FaultSignal_List_thd().get(pos).setVisited();
                    //thread_item.setFAULT_MTF_PERSONAL_LIST(pos, inputsSignals.get(index).getOriginalLogicValue(), thread_item.getinputVector());
                    //thread_item.getMTF_PERSONAL_LIST().get(pos).setOriginalValue(1);

                    if ((inputsSignals.get(index).getLogicValue() == thread_item.getMTF_PERSONAL_LIST().get(pos).getOriginalValue()) && (!thread_item.getMTF_PERSONAL_LIST().get(pos).getBooleaFlag())) {

                        thread_item.getMTF_PERSONAL_LIST().get(pos).setNewValue(0);

                        thread_item.getMTF_PERSONAL_LIST().get(pos).setBooleanFlag();


                        //thread_item.getMTF_FaultSignal_List_thd().get(pos).setVisited();
                        inputsSignals.get(index).setOriginalLogicValue(1);
                        inputsSignals.get(index).setLogicValue(0);
                        inputsSignals.get(index).setLogicValueBoolean(Boolean.FALSE);

                        //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(1);
                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValue(0);
                        thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValueBoolean(Boolean.FALSE);
                    }

                }

                //  this.inGate = track_operations;

            }
            if(inputsSignals.get(index).getLogicValue() == 0){
                input.add(Boolean.FALSE);  //Store input to another arrayList
                concat_inputs = concat_inputs + "0";
            }
            if(inputsSignals.get(index).getLogicValue() == 1){
                input.add(Boolean.TRUE); //Store input to another arrayList
                concat_inputs = concat_inputs + "1";
            }
        }

        /*ORIGNAL POS*/
        //Convert the input signal values to boolean
        //boolean output_converted = this.calculateTheOutputGatesInBoolean(comb, input, gate);

        /* ---------- Special case for logical gates as wires GATE: ZERO or ONE ------- */
        String r = "";

        String gate_temp = gate.getGate().getType().toString();
        //System.out.println("Gate: " + gate_temp);

        if(gate_temp.equals("ZERO")){
            //System.out.println("  OPS ------------------------------ ZERO    " + gate_temp );
            return Boolean.FALSE;
        }
        if(gate_temp.equals("ONE")){
            //System.out.println("  OPS ------------------------------ OONE    " + gate_temp );
            return Boolean.TRUE;
        }
        /* ---------- Special case for logical gates as wires GATE: ZERO or ONE ------- */

        //Convert the input signal values to boolean
        boolean output_converted = this.calculateTheOutputGatesInBoolean(comb, input, gate);

        /* Calculate Sensitive Area of This Gate */
        ///SensitiveCell cell = this.sensitive_cells.get(gate.getGate().getType()  + "_" + concat_inputs);
        String key = "";
        if(gate.getGate().getType().toString().contains("X1")){ // "X1" version
            key = gate.getGate().getType()  + "_" + concat_inputs; // Calculate the exact input vector
        }
        else{
            key = gate.getGate().getType()  + "X1_" + concat_inputs; // Calculate the exact input vector

        }

        SensitiveCell cell = this.sensitive_cells.get(key);

        //TODO: Add the X1 in contain keys

        //  System.out.println("-> " + gate.getGate().getType() + "  cell: " + cell + "  -- key " + key + " cellList: " + this.sensitive_cells.size());
        if((cell != null)){

            //this.sum_sensitive_cells_area = this.sum_sensitive_cells_area + Float.parseFloat(cell.getSensitive_are());
            thread_item.sum_sensitive_cells_area(Float.parseFloat(cell.getSensitive_are()));
            //System.out.println("idx: " + thread_item.getSimulationIndex() + "  invec: " + thread_item.getinputVector() + " gateid: " + gate.getGate().getId() + " gate: " + gate.getGate().getOutputs() + " sigs: " + gate.getGate().getInputs() +  " CEll founded: " + cell.getCell_id() + " input: " +cell.getInput_vec()  +
            //" out: " + output
            //   " sensitive area: "+ cell.getSensitive_are() + " sum: " + thread_item.getSum_sensitive_cells_area());
        }


        return output_converted;
    }

    private boolean checkIfSignalIsInFaultList(Signal signal, TestVectorInformation thread_item){


            // FaultList Position
            int position = thread_item.getPositionFaultSignalInMTFListThd(signal);

            int r = -1;

            if(position >= 0){ // Entrou
                //flag = true; // Founnded in FaultList
                r = applyBitFlip(signal, thread_item, position);

            }

            if(r == 0 || r == 1){
                return true;
            }
            else {
                return false;
            }
    }

    private int applyBitFlip(Signal f, TestVectorInformation thread_item, int pos){

            int value = f.getOriginalLogicValue();
            String type = "";

            if(value == 0){
                type = "0 to 1";
            }
            if(value == 1){
                type = "1 to 0";
            }

            if(type.equals("1 to 0")){
                thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValue(0);
                thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValueBoolean(Boolean.FALSE);
                return 0;
            }
            if(type.equals("0 to 1")){
                thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValue(1);
                thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValueBoolean(Boolean.TRUE);
                return 1;
            }
            return -1;
    }

    private  boolean calculateOutputFacultInjectionGateValueMultipleFaultInjectionV2NEWMODEMODULAR(Cell cells, DepthGate gate, ArrayList <Signal> inputsSignals, TestVectorInformation thread_item){

        //System.out.println("inn... + " + thread_item.getItem().toString());
        final Map<ArrayList<Boolean>, Boolean> comb = cells.getComb(); // Calculate the gate output
        final ArrayList <Boolean> input = new ArrayList<>();
        //final ArrayList <Integer> signals = new ArrayList<>();

        for (int index = 0; index < inputsSignals.size(); index++) {

            boolean bitflipFlag = checkIfSignalIsInFaultList(inputsSignals.get(index), thread_item);

            if(bitflipFlag){
                System.out.println("Bitflip: ");
            }
            else
            {

                if (inputsSignals.get(index).getLogicValue() == 0) {
                    input.add(Boolean.FALSE);  //Store input to another arrayList
                }
                if (inputsSignals.get(index).getLogicValue() == 1) {
                    input.add(Boolean.TRUE); //Store input to another arrayList
                }
            }
        }

        //Convert the input signal values to boolean
        boolean output_converted = this.calculateTheOutputGatesInBoolean(comb, input, gate);

        /* ---------- Special case for logical gates as wires GATE: ZERO or ONE ------- */
        String r = "";

        String gate_temp = gate.getGate().getType().toString();
        //System.out.println("Gate: " + gate_temp);

        if(gate_temp.equals("ZERO")){
            //System.out.println("  OPS ------------------------------ ZERO    " + gate_temp );
            return Boolean.FALSE;
        }
        if(gate_temp.equals("ONE")){
            //System.out.println("  OPS ------------------------------ OONE    " + gate_temp );
            return Boolean.TRUE;
        }
        /* ---------- Special case for logical gates as wires GATE: ZERO or ONE ------- */

        return output_converted;
    }

    private  boolean calculateOutputFacultInjectionGateValue(Cell cells, DepthGate gate, ArrayList <Signal> inputsSignals, Signal faultSig,  TestVectorInformation thread_item){
                //System.out.println("inn... + " + thread_item.getItem().toString());      
                final Map<ArrayList<Boolean>, Boolean> comb = cells.getComb();
                final ArrayList <Boolean> input = new ArrayList<>();
                final ArrayList <Integer> signals = new ArrayList<>();

                String concat_inputs = "";
                String concat = "";

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

                        this.calculateLogicalMasking(faultSig);
                        }
                        
                        signals.add(inputsSignals.get(index).getLogicValue());
                        
                        if(inputsSignals.get(index).getLogicValue() == 0){
                            input.add(Boolean.FALSE);

                            concat_inputs = concat_inputs + "0";
                        }
                        if(inputsSignals.get(index).getLogicValue() == 1){
                            input.add(Boolean.TRUE);

                            concat_inputs = concat_inputs + "1";
                        }
                }
                concat = concat_inputs;
                //System.out.println("                                Input Signal: " + inputsSignals + " v: "+input);
                Object output = "stuck";
                
                String r = "";
                
                 String gate_temp = gate.getGate().getType().toString();
                //System.out.println("Gate: " + gate_temp);
                
                if(gate_temp.equals("ZERO")){
                    //System.out.println("  OPS ------------------------------ ZERO    " + gate_temp );
                    r = "0";
                    //boolean saida = 
                    return Boolean.FALSE;
                }
                if(gate_temp.equals("ONE")){
                    //System.out.println("  OPS ------------------------------ OONE    " + gate_temp );
                    r = "1";
                    //boolean saida = 
                    return Boolean.TRUE;
                }
                //Compute Gates output
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
                    System.out.println("ERROR stuck !!!!! o: "+output + "  GATE: " + gate.getGate() + "  type: " + gate.getGate().getType());
                    
                    
                }


                this.calculateGateAS(comb, input, gate, concat_inputs, thread_item, cells, faultSig);
         
         return (boolean) output;
     }    

     public void calculateLogicalMasking(Signal faultSig){
         System.out.println("FaultSignal:  " + faultSig);
     }

     public Boolean compareGateToFaultSignal(DepthGate gate, Signal faultSig){

         for (int i = 0; i < gate.getGate().getInputs().size() ; i++) {
                String str = gate.getGate().getInputs().get(i).getId().toString();
                if(str.equals(faultSig.getId().toString())){
                    System.out.println("}}} Finded INPUTS: " + faultSig);
                    return true;
                }
         }

         for (int j = 0; j < gate.getGate().getOutputs().size(); j++) {
             String str2 = gate.getGate().getInputs().get(j).getId().toString();
             if(str2.equals(faultSig.getId().toString())){
                 System.out.println("}}} Finded OUTPUTS: " + faultSig);
                 return true;
             }
         }
        return false;
     }

     public void calculateGateAS(final Map<ArrayList<Boolean>, Boolean> comb, final ArrayList<Boolean> input, DepthGate gate, String concat_inputs, TestVectorInformation thread_item, Cell cells, Signal faultSig){

         //Convert the input signal values to boolean
         boolean output_converted = this.calculateTheOutputGatesInBoolean(comb, input, gate); // hero to convert

         /* Calculate Sensitive Area of This Gate */
         ///SensitiveCell cell = this.sensitive_cells.get(gate.getGate().getType()  + "_" + concat_inputs);
         String key = "";
         if(gate.getGate().getType().toString().contains("X1")){ // "X1" version
             key = gate.getGate().getType()  + "_" + concat_inputs; // Calculate the exact input vector
         }
         else{
             key = gate.getGate().getType()  + "X1_" + concat_inputs; // Calculate the exact input vector
         }


         SensitiveCell cell = this.sensitive_cells.get(key);

         //TODO: Add the X1 in contain keys
         GateDetailedInformation gateSensitivivity = new GateDetailedInformation();
         gateSensitivivity.setGate(gate);
         gateSensitivivity.setCell(cells);
         gateSensitivivity.setInputs(input);
         gateSensitivivity.setOutputs(output_converted);


         //if(gate.getGate().toString().equals("U0")){
             ///System.out.println("--sensitiveList: " + this.sensitive_cells.size() + " Key: " + key + " - gate: " + cell + " = " + gateSensitivivity.getgateSensitiveArea() + "  GATE: " + gate.getGate() + " Inputs: " + input + " Output: " + output_converted);
         //}

         if((cell != null)){
             // System.out.println("Cell: " + cell);

             System.out.println("--sensitiveList: " + this.sensitive_cells.size() + " Key: " + key + " - gate: " + cell.getCell_id() + " = " + cell.sensitive_are + "  GATE: " + gate.getGate() + " Inputs: " + input + " Output: " + output_converted);

             //thread_item.circuitPathv2.setGateInCircuitPath(gateSensitivivity);

             //this.sum_sensitive_cells_area = this.sum_sensitive_cells_area + Float.parseFloat(cell.getSensitive_are());
             thread_item.sum_sensitive_cells_area(Float.parseFloat(cell.getSensitive_are()));
             thread_item.sum_sensitive_cells_area_gate(Float.parseFloat(cell.getSensitive_are()), gate);
             gateSensitivivity.setgateSensitiveArea(Float.parseFloat(cell.getSensitive_are()));
             thread_item.setGatesLogicalPath(gateSensitivivity);

             if(faultSig.getId().equals(gate.getGate().toString())){
                 System.out.println("-----> " + faultSig);
             }
             System.out.println("idx: " + thread_item.getSimulationIndex() + "  invec: " + thread_item.getinputVector() + " gateid: " + gate.getGate().getId() + " gate: " + gate.getGate().getOutputs() + " sigs: " + gate.getGate().getInputs() +  " CEll founded: " + cell.getCell_id()
                     + " input: " +cell.getInput_vec()  + " | " + " faultSigIn: " + gate.getGate().getInputs() + " faultSigOut: " + gate.getGate().getOutputs() + " faultSig: " + faultSig.getId() + " inOrigial: "+ faultSig.getOriginalLogicValue() + " inLogical: " + faultSig.getLogicValue() + "|" +
                     " out: " + output_converted +
                     " sensitive area: "+ cell.getSensitive_are() + " sum: " + thread_item.getSum_sensitive_cells_area()) ;


           Boolean flag = this.compareGateToFaultSignal(gate, faultSig);

           if(flag){
               //Do something about masking
           }

             /*
            gateSensitivivity.setSensitiveArea(Float.parseFloat(cell.getSensitive_are()));
            circuitPath.setGateInCircuitPath(gateSensitivivity);
            thread_item.circuitPath.add(gateSensitivivity);
            */
             gateSensitivivity.calculatGateSusceptibility(input);

         }

     }
     public void setMode(String mode_var){
            this.mode = mode_var;
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
                
                String gate_temp = gate.getGate().getType().toString();
                //System.out.println("Gate: " + gate_temp);
                
                if(gate_temp.equals("ZERO")){
                    //System.out.println("  OPS ------------------------------ ZERO    " + gate_temp );
                    r = "0";
                    //boolean saida = 
                    return Boolean.FALSE;
                }
                if(gate_temp.equals("ONE")){
                    //System.out.println("  OPS ------------------------------ OONE    " + gate_temp );
                    r = "1";
                    //boolean saida = 
                    return Boolean.TRUE;
                }
                
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
                    System.out.println("ERROR stuck !!!!! out : " + output + "  GATE: " + gate.getGate() + "  -- INFO type : " + gate.getGate().getType() );
                }
               
         
         return (boolean) output;
     }

     private  boolean calculateFaultFreeOutputGateValueForSensitiveAreaCalculation(Cell cells, DepthGate gate,ArrayList <Signal> inputsSignals, TestVectorInformation thread_item){

        Map<ArrayList<Boolean>, Boolean> comb = cells.getComb();
        ArrayList <Boolean> input = new ArrayList<>();
        ArrayList <Integer> signals = new ArrayList<>();
        String concat_inputs = "";

        for (int index = 0; index < inputsSignals.size(); index++) {

            signals.add(inputsSignals.get(index).getLogicValue());

            if(inputsSignals.get(index).getLogicValue() == 0){
                input.add(Boolean.FALSE);
                concat_inputs = concat_inputs + "0";
            }
            if(inputsSignals.get(index).getLogicValue() == 1){
                input.add(Boolean.TRUE);
                concat_inputs = concat_inputs + "1";
            }
        }



         //System.out.println("Concat signals: " + concat_inputs);

        //System.out.println("                                Input Signal: " + inputsSignals + " v: "+input);
        Object output = "stuck";

        String r = "";

        String gate_temp = gate.getGate().getType().toString();
        //System.out.println("Gate: " + gate_temp);

        if(gate_temp.equals("ZERO")){
            //System.out.println("  OPS ------------------------------ ZERO    " + gate_temp );
            r = "0";
            //boolean saida =
            return Boolean.FALSE;
        }
        if(gate_temp.equals("ONE")){
            //System.out.println("  OPS ------------------------------ OONE    " + gate_temp );
            r = "1";
            //boolean saida =
            return Boolean.TRUE;
        }

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
            System.out.println("ERROR stuck !!!!! out : " + output + "  GATE: " + gate.getGate() + "  -- INFO type : " + gate.getGate().getType() );
        }

           /* Calculate Sensitive Area of This Gate */
            SensitiveCell cell = this.sensitive_cells.get(gate.getGate().getType()+"_"+concat_inputs);

            if((cell != null)){

                //this.sum_sensitive_cells_area = this.sum_sensitive_cells_area + Float.parseFloat(cell.getSensitive_are());
                thread_item.sum_sensitive_cells_area(Float.parseFloat(cell.getSensitive_are()));
                //System.out.println("idx: " + thread_item.getSimulationIndex() + "  invec: " + thread_item.getinputVector() + " gateid: " + gate.getGate().getId() + " gate: " + gate.getGate().getOutputs() + " sigs: " + gate.getGate().getInputs() +  " CEll founded: " + cell.getCell_id() + " input: " +cell.getInput_vec()  + " out: " + output + " sensitive area: "+ cell.getSensitive_are() + " sum: " + thread_item.getSum_sensitive_cells_area());
            }

            //System.out.println("Gate: " + gate.getGate().getType() + " inputSigs: " + inputsSignals + " inputs: " + concat_inputs + "  Output: " + output);

         /* Calculate Sensitive Area of This Gate */

        return (boolean) output;
    }

    private String generateGateNetlist(Cell cells, DepthGate gate,ArrayList <Signal> inputsSignals, int id_node){

        Map<ArrayList<Boolean>, Boolean> comb = cells.getComb();
       // ArrayList <Boolean> input = new ArrayList<>();
       // ArrayList <Integer> signals = new ArrayList<>();
       // String concat_inputs = "";
        //System.out.println("Circuit level: " + gate.getGate());

        String gate_netlist = inputsSignals.toString();
        String id_node_A = "X" + id_node;
        String inputs = "";
        String sources = " vdd vss";
        String gateOutputName = "";// +gate.getGate().getOutputs().toString();
        String gateType = " " + gate.getGate().getType().toString();
        String concat = "";

                //System.out.println("Inputs: " + inputsSignals + " GATE: " + gate.getGate().getType() + " inputs: " + gate.getGate().getInputs());
        for (Signal x: inputsSignals){
            inputs = inputs + " " + x.getId().toString();
        }

        for (Signal x: gate.getGate().getOutputs()){
            gateOutputName = gateOutputName+ " " + x.getId().toString();
        }

            //System.out.println(" --> : " + id_node_A + inputs + sources + gateOutputName + gateType);
        if(!gateType.contains("X1")) {
            concat = id_node_A + inputs + sources + gateOutputName + gateType + "X1";
        }else{
            concat = id_node_A + inputs + sources + gateOutputName + gateType ;
        }
        /*
        for (int index = 0; index < inputsSignals.size(); index++) {

            signals.add(inputsSignals.get(index).getLogicValue());

            if(inputsSignals.get(index).getLogicValue() == 0){
                input.add(Boolean.FALSE);
                concat_inputs = concat_inputs + "0";
            }
            if(inputsSignals.get(index).getLogicValue() == 1){
                input.add(Boolean.TRUE);
                concat_inputs = concat_inputs + "1";
            }
        }
        */

        return concat;
    }

    private String generateGateNetlistMTF(Cell cells, DepthGate gate,ArrayList <Signal> inputsSignals, int id_node){

        Map<ArrayList<Boolean>, Boolean> comb = cells.getComb();
        // ArrayList <Boolean> input = new ArrayList<>();
        // ArrayList <Integer> signals = new ArrayList<>();
        // String concat_inputs = "";
        //System.out.println("Circuit level: " + gate.getGate());

        String gate_netlist = inputsSignals.toString();
        String id_node_A = "X" + id_node;
        String inputs = "";
        String sources = " vdd vss";
        String gateOutputName = "";// +gate.getGate().getOutputs().toString();
        String gateType = " " + gate.getGate().getType().toString();
        String concat = "";

        //System.out.println("Inputs: " + inputsSignals + " GATE: " + gate.getGate().getType() + " inputs: " + gate.getGate().getInputs());
        for (Signal x: inputsSignals){
            inputs = inputs + " " + x.getId().toString();
        }

        for (Signal x: gate.getGate().getOutputs()){
            gateOutputName = gateOutputName+ " " + x.getId().toString();
        }

        //System.out.println(" --> : " + id_node_A + inputs + sources + gateOutputName + gateType);
        if(!gateType.contains("X1")) {
            concat = id_node_A + inputs + sources + gateOutputName + gateType + "X1";
        }else{
            concat = id_node_A + inputs + sources + gateOutputName + gateType ;
        }
        /*
        for (int index = 0; index < inputsSignals.size(); index++) {

            signals.add(inputsSignals.get(index).getLogicValue());

            if(inputsSignals.get(index).getLogicValue() == 0){
                input.add(Boolean.FALSE);
                concat_inputs = concat_inputs + "0";
            }
            if(inputsSignals.get(index).getLogicValue() == 1){
                input.add(Boolean.TRUE);
                concat_inputs = concat_inputs + "1";
            }
        }
        */

        return concat;
    }

    /**
     * @deprecated
     * @param cells
     * @param gate
     * @param inputsSignals
     * @param thread_item
     * @return
     */
    private  boolean calculateFaultFreeOutputGateValueMTF(Cell cells, DepthGate gate,ArrayList <Signal> inputsSignals, TestVectorInformation thread_item){

        Map<ArrayList<Boolean>, Boolean> comb = cells.getComb();
        ArrayList <Boolean> input = new ArrayList<>();
        ArrayList <Integer> signals = new ArrayList<>();
        for (int index = 0; index < inputsSignals.size(); index++) {


            signals.add(inputsSignals.get(index).getLogicValue());

            if(inputsSignals.get(index).getLogicValue() == 0){
                input.add(Boolean.FALSE);

                int pos = thread_item.getPositionFaultSignalInMTFListThd(inputsSignals.get(index)); //identifica se o sinal de entrada (index) está na lista de sinais a serem injetadas as falhas

                if(pos > -1){
                    thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(0);
                    thread_item.getMTF_PERSONAL_LIST().get(pos).setOriginalValue(0);
                }
            }
            if(inputsSignals.get(index).getLogicValue() == 1){
                input.add(Boolean.TRUE);

                int pos = thread_item.getPositionFaultSignalInMTFListThd(inputsSignals.get(index)); //identifica se o sinal de entrada (index) está na lista de sinais a serem injetadas as falhas

                if(pos > -1){
                    thread_item.get_MTF_FaultSignal_List_thd().get(pos).setOriginalLogicValue(1);
                    thread_item.getMTF_PERSONAL_LIST().get(pos).setOriginalValue(1);
                    //thread_item.get_MTF_FaultSignal_List_thd().get(pos).setLogicValue(1);
                }
                }
            }




        //System.out.println("                                Input Signal: " + inputsSignals + " v: "+input);
        Object output = "stuck";

        String r = "";


        String gate_temp = gate.getGate().getType().toString();
        //System.out.println("Gate: " + gate_temp);

        if(gate_temp.equals("ZERO")){
            //System.out.println("  OPS ------------------------------ ZERO    " + gate_temp );
            r = "0";
            //boolean saida =
            return Boolean.FALSE;
        }
        if(gate_temp.equals("ONE")){
            //System.out.println("  OPS ------------------------------ OONE    " + gate_temp );
            r = "1";
            //boolean saida =
            return Boolean.TRUE;
        }

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
            System.out.println("ERROR stuck !!!!! out : " + output + "  GATE: " + gate.getGate() + "  -- INFO type : " + gate.getGate().getType() );
        }


        return (boolean) output;
    }

    @Override
        public  void run() {

            switch (this.mode){

                case ("MTF-Generate_Netlist"):
                    System.out.println(" ~~~~~~ Run Proportion Monte Carlo, Generate Spice Netlist ~~~~~~");
                    try {
                        startSimulationFaultFreeMTF();
                        startSimulationMultipleFaultInjection();
                        //startSimulationMultipleFaultInjectionMonteCarlo(); //Original
                        //parseVerilogToGenerateSpiceNetList();
                        //startCalculationSensitiveAreas();
                    } catch (IOException | WriteException ex) {
                        Logger.getLogger(LogicSimulator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case ("MTF-Sensitive_Area-Generate_Netlist"):
                    System.out.println(" ~~~~~~ MTF-Sensitive_Area-Generate_Netlist ~~~~~~");
                    try {
                        startSimulationFaultFreeMTF();
                        //startSimulationMultipleFaultInjection();
                        startSimulationMultipleFaultInjectionCalculateSensitiveAreaGenerateSpiceNetLists(); //Original
                        //parseVerilogToGenerateSpiceNetList();
                        //startCalculationSensitiveAreas();
                    } catch (IOException | WriteException ex) {
                        Logger.getLogger(LogicSimulator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case ("Generate_Netlist"):
                    System.out.println(" ~~~~~~ Generate Spice Netlist ~~~~~~");
                    try {
                        parseVerilogToGenerateSpiceNetList();
                    }
                    catch (IOException | WriteException ex) {
                        Logger.getLogger(LogicSimulator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case ("Sensitive_Area"):
                    System.out.println(" ~~~~~~ Calculate Sensitive Area ~~~~~~");
                    try {
                        startCalculationSensitiveAreas();
                    }
                    catch (IOException | WriteException ex) {
                        Logger.getLogger(LogicSimulator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                
                    /// TODO: 26/09/2022  
                case ("Single_SA"): // FOR SENSITIVE AREA ANALYSIS //Here
                    System.out.println("SIMULATION ID (100): ~~~~~~ SENSITIVE AREA ANALYSIS for Single Transient Event - SET ~~~~~~ thd: " + this.threadID);
                    try {
                        startSimulationFaultFree();
                        //startSimulationFaultInjection();
                        calculateSensitiveAreaGenerateSpiceNetLists(); //Original
                        //startSimulationMultipleFaultInjectionCalculateSensitiveAreaGenerateSpiceNetLists(); //Original
                        //startSimulationMultipleFaultInjection();

                    } catch (IOException | WriteException ex) {
                        Logger.getLogger(LogicSimulator.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;

                case ("Single"):
                    System.out.println("SIMULATION ID (000): ~~~~~~ Single Transient Event - SET ~~~~~~ thd: " + this.threadID);
                    try {
                        startSimulationFaultFree();
                        startSimulationFaultInjection();
                        //startSimulationMultipleFaultInjection();

                    } catch (IOException | WriteException ex) {
                        Logger.getLogger(LogicSimulator.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;

                case ("Multiple"):
                    //System.out.println("Multiple Transient Event - SET");
                    try {
                        //caulculate Sensitive Areas from csv file

                        startSimulationFaultFreeMTF();
                        startSimulationMultipleFaultInjection();
                    } catch (IOException | WriteException ex) {
                        Logger.getLogger(LogicSimulator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
            }



        }

        private void PrintSpecs() {
         Thread t = Thread.currentThread();
         System.out.println("\n"
                 + " - Thread: " +(long) Thread.currentThread().getId()//
                 + " - Circuit: " + this.circuit.getName() 
                 + " - Circuit Hashcode: " + this.circuit.hashCode()  
                 + " - Signals Hashcode: " + this.circuit.getSignals().hashCode()
                 + " - Verilog Hashcode: "  + this.verilog_circuit.hashCode()
         );
       
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



     }
     