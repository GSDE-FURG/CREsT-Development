/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator;

import datastructures.Cell;
import datastructures.CellLibrary;
import datastructures.Circuit;
import datastructures.Gate;
import datastructures.ItemX;
import datastructures.Signal;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.N;
import javax.script.ScriptException;
import jxl.write.WriteException;
import levelDatastructures.DepthGate;
import levelDatastructures.GateLevel;
import levelDatastructures.LevelCircuit;
import ops.PTMOps2;
import ops.SPRMultiPassV3Ops;
import ops.SPROps;
import readers.MappedVerilogReader;
import signalProbability.ProbCircuit;
import signalProbability.ProbSignal;
import writers.WriteCsv;
import writers.WriteCsvTh;
import writers.WriteExcel;

/**
 *
 * @author Clayton Farias
 */
 public class Fault_Injection_Campaign {
  

    private final String reliability;
    private final String circuitFilePath;
    private final String library;
    private final String libraryPath;
    private CellLibrary cellLibrary;
    private Circuit circuit;
    private LevelCircuit levelCircuit;
    private ProbCircuit probCircuit;
    private LevelCircuit lCircuit;
    private final long timeout;
    private final long timeoutMiliSeconds;
    private long spr_timeConsumption;
    private int thread;
    private int unmasked_faults ;
    private float reliability_circuit;
    private double MTBF;
    private long time_seconds;
    private MappedVerilogReader verilog_circuit;
    private ArrayList <Signal> internSignals = new ArrayList<>();
    private final ArrayList <String> inputListValues = new ArrayList<>();
    private final ArrayList <ArrayList<String>> inputListValuesStr = new ArrayList<>();
    private final ArrayList <ArrayList<String>> outputFreeFaultListValueStr = new ArrayList<>();
    private final ArrayList <ArrayList<String>> outputListValueStr = new ArrayList<>();
    private final ArrayList <ArrayList<String>> SignalFault = new ArrayList<>();
    private final ArrayList <ArrayList<String>> SignalFaultBitFlip = new ArrayList<>();
    
    
     public Fault_Injection_Campaign(String reliabilty,long Timeout, String circuitFilePath, String library, int threads) {
        

        this.reliability = reliabilty;
        this.circuitFilePath = "abc\\" + circuitFilePath;
        
        this.library = library;
        this.libraryPath = "abc\\" + this.library;
        this.cellLibrary = new CellLibrary();
        
        this.timeout = Timeout;
        this.timeoutMiliSeconds = this.timeout * 1000;
        //this.resultTable = new Hashtable<Integer, BigDecimal>();
        
        this.thread = threads;
        
        this.unmasked_faults = 0;
    }

     public long getSPRTimeConsumption(){
        //System.out.println("TESTE : "+ this.spr_timeConsumption);
        return this.spr_timeConsumption;
    }

     public static void matrixPrint(BigDecimal[][] matrix) {
        
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
     
     public long getTimeExecutionRound(){
         return this.time_seconds;
     }
     
     public int getPropagatedFaults(){
         return this.unmasked_faults;
     }
     
     public void PrintSpecs(){
         
         System.out.println("Circuit : " + this.circuit.getName());
        // System.out.println("- Logic Gates : " + this.circuit.getGates());
         System.out.println("- Logic Gates (size): " + this.circuit.getGates().size() );
         //System.out.println("- Inputs : " + this.circuit.getInputs());
         System.out.println("- inputs (size): " + this.circuit.getInputs().size() );
         System.out.println("- Outputs : " + this.circuit.getOutputs().size());
         //System.out.println("- Signals : " + this.circuit.getSignals());
        
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
    
     public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
     }
     
     public BigDecimal getSPRBigDecimal(int indxFanouts)throws Exception {
        
        SPROps teste2 = new SPROps();
        System.out.println("\n ===== SPR Big Decimal File: " + this.circuitFilePath + "   - Precision : "  + this.reliability + " ===== ");
         //System.out.println("Fanouts (" + indxFanouts  + ")");
         
        /*CellLibrary*/
        CellLibrary cellLib = new CellLibrary();
        cellLib.initLibrary(this.libraryPath);
        /*CellLibrary*/
        
         /*Read Verilog*/
        MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitFilePath, cellLib);
        //System.out.println("Circuito : "+ verilog_circuit.getCircuit());
        /*Read Verilog*/
        
        
        /*Circuit linked to verilog_circuit - init circuit*/
         this.circuit = verilog_circuit.getCircuit();
            //this.PrintSpecs();
         /*Circuit linked to verilog_circuit*/
       
        
         /*Circuit Probabilities */
         this.initLevelCircuit();
         this.initProbCircuit();
          /*Circuit Probabilities */
          
          
          /*Gate levels*/
            //this.PrintGateLevels();
          /*Gate levels*/
        
          
          //System.out.println("Level Circuit : "+ this.levelCircuit);
  
          cellLib.setPTMCells2(Float.valueOf(reliability));
          cellLib.setPTMCells(new BigDecimal(reliability));
          //cellLib.teste();
          
          //CellLib
          this.cellLibrary = cellLib;
          
          //ProbCircuit
          probCircuit.setPTMReliabilityMatrix(); //Sempre usar variaveis criadas 
          // taking off probCircuit.setProbSignalStates(false); //novo 
          probCircuit.setDefaultProbSourceSignalMatrix();
          
          //final long startTime = System.currentTimeMillis();
          
          
          /* New try*/
          /*
          SPROps teste2 = new SPROps();

          final long sprStartTIme = System.currentTimeMillis();
          
            BigDecimal spr_resultx = teste2.getSPRReliability(this.probCircuit); //SPROps.getSPRReliability(this.probCircuit);
          
          final long sprEndTime = System.currentTimeMillis();
          
          System.out.println("SPR - Millis : " + (sprEndTime - sprStartTIme));
          
          */
          /*
          SPROps teste = new SPROps();
          
          Instant start = Instant.now();
                   BigDecimal spr_result = teste.getSPRReliability(this.probCircuit); //SPROps.getSPRReliability(this.probCircuit);
          Instant finish = Instant.now();
           
         long timeElapsed = Duration.between(start, finish).toMillis(); 
         
          this.spr_timeConsumption = (timeElapsed);
          
          System.out.println("SPR - Instant Now : " + timeElapsed);
          
          */
         
          
          long startTime = System.nanoTime();    
                     BigDecimal spr_result = teste2.getSPRReliability(this.probCircuit); //SPROps.getSPRReliability(this.probCircuit);
          long endTime= System.nanoTime();
          long durationInMs = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
          this.spr_timeConsumption = (durationInMs);
          
          //System.out.println("SPR - Nano: " + durationInMs);
            
          /*
          final long sprStartTIme = System.currentTimeMillis();
          
            BigDecimal spr_result = SPROps.getSPRReliability(this.probCircuit);
          
          final long sprEndTime = System.currentTimeMillis();
          
          //this.spr_timeConsumption = (sprEndTime - sprStartTIme);
          
          */
          
        
          System.out.println("- SPR Bigdecimal Reliability : " + spr_result + " calc Time: " +(this.getSPRTimeConsumption()) + " (ms)");
          
          //System.out.println("- SPR Bigdecimal Reliability : " + spr_result + " PauloTime: " +(endTime - ) + " (ms)");
          
          System.out.println(" =================================== END SPR ================= \n");         
          
          return spr_result;
        
    } 
     
     public void getInputFanouts(String library, String CircuitFile)throws Exception {
         
        /*CellLibrary*/
        CellLibrary cellLib = new CellLibrary();
        cellLib.initLibrary(this.libraryPath);
        /*CellLibrary*/
        
         /*Read Verilog*/
        MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitFilePath, cellLib);
        
        
        /*Circuit linked to verilog_circuit - init circuit*/
         this.circuit = verilog_circuit.getCircuit();
         
            //this.PrintSpecs();
         /*Circuit linked to verilog_circuit*/
       
        
         /*Circuit Probabilities */
         this.initLevelCircuit();
         this.initProbCircuit();
          /*Circuit Probabilities */
          
          
          /*Gate levels*/
            //this.PrintGateLevels();
          /*Gate levels*/

  
          cellLib.setPTMCells2(Float.valueOf(reliability));
          cellLib.setPTMCells(new BigDecimal(reliability));
          //cellLib.teste();
          
          //CellLib
          this.cellLibrary = cellLib;
          
          //ProbCircuit
          this.probCircuit.setPTMReliabilityMatrix(); //Sempre usar variaveis criadas 
          // taking off probCircuit.setProbSignalStates(false); //novo 
          this.probCircuit.setDefaultProbSourceSignalMatrix();
          this.probCircuit.setProbSignalStates(false); //HERE
          
         
      
          //Find entry fanouts
         ArrayList<ProbSignal> inputfanouts = new ArrayList<>();
         
         inputfanouts.addAll(this.probCircuit.getInputFanoutsX());
         
         System.out.println("- Circuit fanouts Counter : " + this.probCircuit.getFanouts().size());
                  
         System.out.println("- All input Fanouts (first gateLevel) : \n    Size: "+ inputfanouts.size());
            
         for (int i = 0; i < inputfanouts.size(); i++) {
             //System.out.println("     " + i + " - " + inputfanouts.get(i));
         }
         
     
         
     } 
   
     public void SPR_MP_FANOUTS(String library, String CircuitFile)throws Exception {
         
         SPRMultiPassV3Ops teste = new SPRMultiPassV3Ops();
         int idx = 0;
        
       
        ItemX t;
        List<ItemX> resultTable = new ArrayList<>();   
        
        
        /*SPR Call*/
        Fault_Injection_Campaign spr = new Fault_Injection_Campaign(this.reliability, this.timeout,  CircuitFile, library, 0);
        
        /*Time SPR*/
        //long sprStartMethodTime = System.currentTimeMillis();
         
            BigDecimal spr_result = spr.getSPRBigDecimal(idx);
        
       // long sprEndMethodTime = System.currentTimeMillis();
          
        //-------- ----------- --------- ----------- -------------
        // System.out.println("       ~   SPR Method Time: " + (sprEndMethodTime - sprStartMethodTime) + "m(s)");
        
            
        
         //long PauloTime2 = System.currentTimeMillis();
          
         
        long loadTimeStart = System.nanoTime();//System.currentTimeMillis();
         
        /*CellLibrary*/
        CellLibrary cellLib = new CellLibrary();
        cellLib.initLibrary(this.libraryPath);
        /*CellLibrary*/
        
         /*Read Verilog*/
        MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitFilePath, cellLib);
        
        
        /*Circuit linked to verilog_circuit - init circuit*/
         this.circuit = verilog_circuit.getCircuit();
         
            //this.PrintSpecs();
         /*Circuit linked to verilog_circuit*/
       
        
         /*Circuit Probabilities */
         this.initLevelCircuit();
         this.initProbCircuit();
          /*Circuit Probabilities */
          
          
          /*Gate levels*/
            //this.PrintGateLevels();
          /*Gate levels*/

  
          cellLib.setPTMCells2(Float.valueOf(reliability));
          cellLib.setPTMCells(new BigDecimal(reliability));
          //cellLib.teste();
          
          //CellLib
          this.cellLibrary = cellLib;
          
          //ProbCircuit
          this.probCircuit.setPTMReliabilityMatrix(); //Sempre usar variaveis criadas 
          // taking off probCircuit.setProbSignalStates(false); //novo 
          this.probCircuit.setDefaultProbSourceSignalMatrix();
          this.probCircuit.setProbSignalStates(false); //HERE
          
          long load_TimeEnd = System.nanoTime();//System.currentTimeMillis();
          
          long estimated_load_Time = TimeUnit.NANOSECONDS.toMillis(load_TimeEnd - loadTimeStart);
          
          t = new ItemX (0 ,spr_result, spr.getSPRTimeConsumption()); 
            resultTable.add(t);
               t.setLoadTime(estimated_load_Time);
                t.setPrecision(this.reliability);
                 t.setNumberFanouts(this.probCircuit.getFanouts().size());
          
          System.out.println("- Time to loag Genlib, Circuit (verilog), and settings probabilities: " + (estimated_load_Time) + " m(s)");
          
         /*News TimeStamp with Instant Now*/
         //long startTime = System.nanoTime();//Instant.now();//System.currentTimeMillis();
         
         //long executionTimeGlobal = System.nanoTime(); //Instant.now();//System.currentTimeMillis();
         
        //    long timeoutsideIF =  TimeUnit.NANOSECONDS.toMillis(executionTimeGlobal - startTime);//Duration.between(startTime, executionTimeGlobal).toMillis(); //(executionTimeGlobal - startTime);
         
       long globalTime = 0;
         
       String output = "";
         //long endTime= System.nanoTime();
         //long durationInMs = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
         
         //System.out.println("- Running SPR-MP with the timeout: " +(this.timeoutMiliSeconds) + " (ms)");
         System.out.println("- Total of Fanouts (n) : " + this.probCircuit.getFanouts().size() + "    Timeout: " + this.timeout + "(s)");
         System.out.println("\n- Fanout(n)        Reliability        Time(ms)           Time(s)"); 
         
         SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            
          
            for (int i = 1; i <= this.probCircuit.getFanouts().size(); i++){ //Fluxogram TimeExecution (ms)
                
                 Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
                    String timeStampBegin = sdf.format(hora);
                    
                 long sprmpStartTime = System.nanoTime(); //System.currentTimeMillis();
                      BigDecimal sprmp_result = teste.getPRMultiPassReliaiblityByLimitedFanouts(this.probCircuit, i);
                 long sprmpEndTime = System.nanoTime();//System.currentTimeMillis();
                 
                    hora = Calendar.getInstance().getTime();
                     String timeStampEnd = sdf.format(hora);
                 
                 long sprmpRoundTime =   TimeUnit.NANOSECONDS.toMillis(sprmpEndTime - sprmpStartTime);//Duration.between(start, finish).toMillis();
               
                 if((sprmpRoundTime) <=  (this.timeoutMiliSeconds)){ //(m(s)
                     
                      globalTime = globalTime + sprmpRoundTime;
                         
                         String line = "";
                         
                         line = i  + "        " + sprmp_result + "       (Round): " + sprmpRoundTime + " m(s)     ~   "  + sprmpRoundTime/1000 + " (s)  -  " + sprmpRoundTime/(1000*60) + " min  -  " + sprmpRoundTime/(1000*60*60) + " hora" + "    (Global): " + (globalTime) + " m(s) - " + timeStampBegin + " - " + timeStampEnd ;
                       
                         System.out.println(line);
                         
                         output = output + line + "\n";
                                
                        
                        ItemX item = new ItemX(i , sprmp_result, sprmpRoundTime);
                        resultTable.add(item);
                        idx = i;
                        
                       
                        
                        if(( sprmpRoundTime * 1.9) > this.timeoutMiliSeconds){ //preempção
                             System.out.println("Stoping the execution, expected time ~ (" + sprmpRoundTime * 1.5 + ") m(s) above the Timeout (~2x), Timeout : " + this.timeoutMiliSeconds + " ~ " + this.timeout  + "(s)");
                             i = (this.probCircuit.getFanouts().size()) + 11;
                             break;
                        }
                        
                       
                        
                       
                  }
                 else{
                     
                     System.out.println("Stoping process indexlastLevel : " + i);
                     
                     globalTime = globalTime + sprmpRoundTime;
                     
                     String overTimeout = "";
                     
                     overTimeout = "           ~ "+i  + "        " + sprmp_result + "       (Round): " + sprmpRoundTime + " m(s)     ~   " + " (Global): " + (globalTime) + " m(s) " + sprmpRoundTime/1000 + " (s)  -  " + sprmpRoundTime/(1000*60) + " min  -  " + sprmpRoundTime/(1000*60*60) + " hora  - " + timeStampBegin + " - " + timeStampEnd ;
                     
                     System.out.println(overTimeout);
                     
                     output = output + "\n" + overTimeout;
                     
                        ItemX item = new ItemX(i, sprmp_result, sprmpRoundTime);
                        item.setFanoutTimeoutOver();
                        resultTable.add(item);
                        idx = i;
                        /*
                        ItemX item2 = new ItemX(0, sprmp_result, globalTime);
                        resultTable.add(item2);
                        idx = i+1;
                        */
                     
                     break;
                        
              }
         }
            
         System.out.println("\n");
         
         for (int i = 0; i <= idx; i++){
             System.out.println(resultTable.get(i).PrintItemx());
         }
            
         
          System.out.println(" =================================== END SPR-MP ================= \n");
          
          //System.out.println("TimeOut-" + this.timeout +"(s)");
          
           FileWriter logFile = new FileWriter("Log_"+this.circuit.getName()+"_"+"TimeOut-" + this.timeout +"(s).txt");
           
           logFile.write(output);
           
           logFile.close();
           
           System.out.println("LogFile saved as :" + "Log_"+this.circuit.getName()+"_"+"TimeOut-" + this.timeout +"(s).txt");
          
           WriteExcel resultFile = new WriteExcel(this.circuit.getName() + "-" + this.timeout + "(s)"  , "TimeOut-" + this.timeout +"(s)"    , Long.toString(this.timeoutMiliSeconds) , resultTable, idx);
          
           resultFile.write();
           
           resultFile.writeCSV();
            
           /*
            //Runtime runtime = Runtime.getRuntime();
            //Process proc = runtime.exec("shutdown -s -t " + "10");
           */
     } 
   
     public void PTM() throws Exception {

         System.out.println(" ===== PTM File: " + this.circuitFilePath + "   - Precision : "  + this.reliability + " ===== ");
        final long startTime = System.currentTimeMillis();
        
 
        /*CellLibrary*/
        CellLibrary cellLib = new CellLibrary();
        cellLib.initLibrary(this.libraryPath);
        /*CellLibrary*/
        
         /*Read Verilog*/
        MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitFilePath, cellLib);
        System.out.println("Circuito : "+ verilog_circuit.getCircuit());
        /*Read Verilog*/
        
        
        /*Circuit linked to verilog_circuit - init circuit*/
         this.circuit = verilog_circuit.getCircuit();
             //this.PrintSpecs();
         /*Circuit linked to verilog_circuit*/
       
        
         /*Circuit Probabilities */
         this.initLevelCircuit();
         this.initProbCircuit();
          /*Circuit Probabilities */
          
          
          /*Gate levels*/
            //this.PrintGateLevels();
          /*Gate levels*/
        
          
          //System.out.println("Level Circuit : "+ this.levelCircuit);

          cellLib.setPTMCells2(Float.valueOf(reliability));
          cellLib.setPTMCells(new BigDecimal(reliability));
          //cellLib.teste();
          
     
           //ProbCircuit
          probCircuit.setPTMsReliabilityMatrix(); //Sempre usar variaveis criadas 
                   
          System.out.println("- PTM Reliability : " + PTMOps2.getCircuitReliabilityByPTM(probCircuit));
          
           System.out.println(" =================================== END PTM ================= ");
        

    }
     
     public Signal getSignalType( GateLevel gateLevel, int j,CellLibrary cellLib){
        
         Signal cell = (Signal) gateLevel.getGates().get(j);
         return cell; 
     }
     
     public Gate getGateType(GateLevel gateLevel, int j,CellLibrary cellLib){
        
         DepthGate cell = (DepthGate) gateLevel.getGates().get(j);
         return cell.getGate(); 
     }
     
     public String typeStructureDefinition(String anwser, int j,CellLibrary cellLib){
         String input = "";
         String output = "";
         String result = "" ;         
         //ArrayList<Cell> cells = cellLib.getCells();
        
        
         if(anwser.equals("class levelDatastructures.DepthGate")){
                                 anwser = "Gate";
                                /*
                                anwser = "Gate";
                                DepthGate cell = (DepthGate) gateLevel.getGates().get(j);
                                
                                Cell celula = cell.getGate().getType();
                                int pos = cells.lastIndexOf(cell.getGate().getType());
                                //System.out.println("Celula : " + celula.getComb());
                                input = cell.getGate().getInputs().toString();
                                output = cell.getGate().getOutputs().toString();
                                result = "                    (" + anwser + ")- "+ gateLevel.getGates().get(j) + " type (" + cell.getGate().getType()  + ")  - Input(s): " + input + "  - Output: " + output;
                                */
                            }
                            
                            else{//(anwser.equals("class datastructures.Signal")){
                                anwser = "Signal";
                                /*
                                anwser = "Wire";
                                Signal cell = (Signal) gateLevel.getGates().get(j);
                                result = "                    (" + anwser + ")- "+ gateLevel.getGates().get(j) ;//+ "    Sigvalue: " + signal_temp.getSignalValue();
                                */
                            }
                            
         return anwser;
                            
     }
     
     public void printGateLevel(CellLibrary cellLib){
          
         /* -----------------------------------------------*/
                  
                  System.out.println("\n------ Printing GateLevels: --------");
                  ArrayList<GateLevel> gatesLevel = this.levelCircuit.getGateLevels();
                  //loop gateLevels
                  for (int i = 0; i < gatesLevel.size(); i++) {
                       
                        GateLevel gateLevel =  gatesLevel.get(i);
                        System.out.println(" Gate Level: " + i + "\n    + gateLevel (wires considered): ");
                        
                        //Loop for gates in the level
                        for (int j = 0; j <  gateLevel.getGates().size() ; j++) {
                            
                            String anwser = gateLevel.getGates().get(j).getClass().toString();
                            
                            //Print the output
                            //System.out.println(this.typeStructureDefinition(anwser, gateLevel, j, cellLib));
                            
                        }
                  }
                  
                    System.out.println("");
                
                
                //Information about the type of gates
                /*
                System.out.println("- Information about the type of gates");
                
                  ArrayList  <Gate> gates = this.circuit.getGates();
                  for (int i = 0; i < gates.size(); i++) {
                       
                        Gate gate =  gates.get(i);
                        Cell celula = gate.getType();
                        Signal sig = gate.getInputs().get(0);
                        System.out.println( i + " Gates: " + gate.getId() + "  -  " + gate.getType() + " Input: " + gate.getInputs() + " SIGnal : " + sig.getSignalValue());
                        String type_gate =  gate.getType().toString();
                        
                        if(type_gate.equals("NAND2X1")){
                            System.out.println("In");
                            
                            logic.gates.NAND porta = new NAND();
                            logic.gates.LogicGate t;
                            
                           porta.setInputLimit(2);
                           
                            
                        }
                        //System.out.println("                - Cell: " +celula.getCombination());
                        
                 }
                    */
     
     }
     
     public String SignalProcessing(Gate nextGate, int gatelevel, String path, GatePath outGate, int i, ArrayList<Object> levelGates){
        
            Signal sig = (Signal) levelGates.get(i);
            
            //System.out.println("                - Sig : "+sig);

            for (int k = 0; k < nextGate.getInputs().size(); k++) {
                
                Signal sigInput = nextGate.getInputs().get(k);
                System.out.println("    - SiginputS : "+sigInput + "  -  Sig " + sig + "   flag: " + sigInput.getVisited());
                
                
                if(gatelevel < 0){
                                //System.out.println("           *** Result path: "+ path + "   GATES: " + outGate.getGate() + " list: " + outGate.getgateinthePatch());
                                //listPath.add();
                                 //sigInput.setVisited();
                                 Gate A = new Gate("stop");
                                 //outGate.setgateinthePatch(A);
                                 String awn = "\n Ini: " + sig.toString() + " -> "+ path;

                                 return  this.DFS(nextGate, gatelevel, "\nIni: " + nextGate.toString() + " -> "+ path, outGate);

                }
                 if (sig == sigInput){
                                                            
                            System.out.println("                       Input Signal ( "  +  sig   + " ) -> signal destiny ( " + sig.getDestiny() +  " )   flaged as: "  + sig.getVisited());
                            //sig.setOutputPath(sigInput.getDestiny());
                            
                           // outGate.setgateinthePatch(sigInput);
                            
                            //sigInput.setVisited();
                            
                            
                            
                           //this.GateProcessing(nextGate, gatelevel, path, outGate, i, levelGates);
                           
                            // path = this.DFS(nextGate, gatelevel, sigInput.toString() + " -> "+ path, outGate);
                           this.DFS(nextGate, gatelevel, sigInput.toString() + " -> "+ path, outGate);

                }
                
            }
         
         return path;
         
     }
     
     public String GateProcessing(Gate nextGate, int gatelevel, String path, GatePath outGate, int i, ArrayList<Object> levelGates){
        
                  DepthGate outputGate = (DepthGate) levelGates.get(i);
                  Gate previousGate = outputGate.getGate();


                 //Função de checagem se saida do gate anterior é igual as entradas do próximo gate no outro nivel 
                 for (int j = 0; j < nextGate.getInputs().size(); j++) {

                     for (int k = 0; k < previousGate.getOutputs().size(); k++) {

                         Signal outSignal = previousGate.getOutputs().get(k);

                         if (outSignal == nextGate.getInputs().get(j) && (outputGate.get_visited())){
                               System.out.println("                       match:  Input Signal ( "  +  outSignal   + " -> gate ( " + previousGate +  " )   flaged as: "  + outputGate.visited);

                               //System.out.println("ANT: "+ previousGate.getOutputPath());

                               previousGate.setOutputPath(nextGate);

                               //outSignal.setVisited();
                               
                               //outputGate.set_visited();
                               //gateAnt.nextgate.add(gate);

                               //System.out.println("                                                         NEXT GATE  : "+ gateAnt.genextGate());

                               outGate.setgateinthePatch(previousGate);

                                if(gatelevel == 0){
                                    //System.out.println("           *** Result path: "+ path + "   GATES: " + outGate.getGate() + " list: " + outGate.getgateinthePatch());
                                    //listPath.add();
                                     Gate A = new Gate("stop");
                                     //A.setId("stop");
                                     outGate.setgateinthePatch(A);
                                     int t = outGate.getgateinthePatch().size();
                                     outGate.setindexStop(t);

                                     String awn = "\n Ini: " + previousGate.toString() + " -> "+ path;


                                     return  this.DFS(previousGate, gatelevel, awn , outGate);

                               }


                              path = this.DFS(previousGate, gatelevel, previousGate.toString() + " -> "+ path, outGate);

                               //outputGate.set_visited(false);
                         }
                      }
                }
                                              // outputGate.set_visited(true);

                                        //}
                                        /*
                                        else{ // É sinal
                                            
                                            Signal sig = (Signal) levelGates.get(i);
                                            System.out.println("                - Sig : "+sig);
                                            /*
                                            for (int k = 0; k < nextGate.getInputs().size(); k++) {
                                                Signal sigInput = nextGate.getInputs().get(k);
                                                System.out.println("                - SiginputS : "+sigInput + "  -  Sig " + sig + "   flag: " + sigInput.getVisited());
                                                
                                                if(gatelevel < 0){
                                                                //System.out.println("           *** Result path: "+ path + "   GATES: " + outGate.getGate() + " list: " + outGate.getgateinthePatch());
                                                                //listPath.add();
                                                                 //sigInput.setVisited();
                                                                 Gate A = new Gate("stop");
                                                                 outGate.setgateinthePatch(A);
                                                                 String awn = "\n Ini: " + sig.toString() + " -> "+ path;
                                                                 
                                                                 return  this.DFS(nextGate, gatelevel, "\nIni: " + nextGate.toString() + " -> "+ path, outGate);
                                                           
                                                }
                                                      
                                                if (sig == sigInput){
                                                            
                                                            System.out.println("                       Input Signal ( "  +  sig   + " ) -> signal destiny ( " + sig.getDestiny() +  " )   flaged as: "  + sig.getVisited());
                                                            //sig.setOutputPath(sigInput.getDestiny());
                                                            outGate.setgateinthePatch(sig);
                                                            //sigInput.setVisited();
                                                           // path = this.DFS(nextGate, gatelevel, sigInput.toString() + " -> "+ path, outGate);
                                                           // this.DFS(nextGate, gatelevel, sigInput.toString() + " -> "+ path, outGate);

                                                }
                                            }
                                            */
                                           
                                              
                                     //   };*/
        return path;
         
     }
     
     public String DFS(Gate nextGate ,int gatelevel, String path, GatePath outGate) {
        
          gatelevel = gatelevel-1;
          
          
          if(gatelevel >= 0){
                 
               
                //Compara gate do nivel anterior
                 ArrayList<GateLevel> lastGateLevel = this.levelCircuit.getGateLevels();
                 ArrayList<Object> levelGates = lastGateLevel.get(gatelevel).getGates();
                 //System.out.println("                       level : " + gatelevel + "   GATES : " + levelGates + " size: " + levelGates.size());
                 System.out.println("                   - GateLevel: " + gatelevel + " - ID ( " + nextGate +") - gates in level: " + levelGates);
                 
                 for (int i = 0; i < levelGates.size(); i++) {  //Gates nesse nível
                                        String AwnsString = levelGates.get(i).getClass().toString();
                                        //System.out.println("awns : " + AwnsString + "  index: "+i);
                                        
                                        if(AwnsString.equals("class levelDatastructures.DepthGate")){  //Verifica se é Gate
                                              this.GateProcessing(nextGate, gatelevel, path, outGate, i, levelGates);
                                        }
                                        else{
                                            Signal sig = (Signal) levelGates.get(i);
                                            //System.out.println("    SINAL ATUAl : " + sig);
                                            this.SignalProcessing(nextGate, gatelevel, path, outGate, i, levelGates);
                                        }
                 }
               
               gatelevel--;
          }
         
          //System.out.println("Inicio : " + nextGate  + ": CAMINHO: "+ path);
         
          /*
          for (int i = 0; i < gate.getInputs().size(); i++) {
             this.DFS_searching_Signals(gate.getInputs().get(i), gatelevel);
          }
         */
          return (path);
    }
     
     public ArrayList <Gate> DFS_searching(Gate origin, Gate gate, ArrayList <Gate> gateList){
        
        System.out.println("GATE => " + gate + " - " + gate.getOutputPath());
        
        ArrayList <Gate> gate_aux = (ArrayList <Gate>) gate.getOutputPath();  //Arraylist
        
        if(gate_aux.size() >= 1){
         
          Gate x = gate_aux.get(0);
          
          System.out.println("      Ant " + gate + " Next gate: " + x);
                gateList.add(x);
                origin.setnextGate(x);
                DFS_searching(origin, x, gateList);
        
         }
        return gateList;
         
     }
     
     public int generateRandomInput(){
          
            Random gerador = new Random();
          int randomLogicValue = gerador.nextInt(2);
            
          return randomLogicValue;
     }
     
     public void insertInputVector(CellLibrary cellLib, String Option, ArrayList <Integer> vector, int index){
              //cellLib.teste();
              
              ArrayList <Cell>  cells;
               
               cells = cellLib.getCells();
                
                //System.out.println("VECTOR : "+ vector);
                // - ---------------------------------------- Inserting input vector ------------------------------------------------------
                System.out.println("");
               
                switch (Option){
                       
                    case "random":
                        ArrayList <Signal> inputSignais = this.circuit.getInputs();
                        System.out.println("Input Signals: " + inputSignais + " Size: " + inputSignais.size());

                        Random gerador = new Random();
                        //vetor de entrada aleatório
                        for (int i = 0; i < inputSignais.size(); i++) {
                                int randomLogicValue = gerador.nextInt(2);
                                inputSignais.get(i).setLogicValue(randomLogicValue);
                               // System.out.println(inputSignais.get(i)+" : '"+inputSignais.get(i).getLogicValue()+"'");
                        }
                       
                    break;
                    
                    case "selected":
                        ArrayList <Signal> inputSignals = this.circuit.getInputs();
                        System.out.println("TestNumber (" + (index+1) + ") - Selected vector" + vector + " - Input Signals: " + inputSignals + " Size: " + inputSignals.size());

                        //Random gerador = new Random();
                        //vetor de entrada aleatório
                        
                        for (int i = 0; i < inputSignals.size(); i++) {  // insere valores nos sinais de entrada
                                //int randomLogicValue = gerador.nextInt(2);
                                inputSignals.get(i).setLogicValue(vector.get(i));
                               // System.out.println(inputSignals.get(i)+" : '"+inputSignals.get(i).getLogicValue()+"'");
                        }
                   break;
                       
                }
      
                
               
              
     }
     
     public ArrayList<GatePath> getOutputToInputPath(){
         
                 ArrayList <GatePath> possiblePaths = new ArrayList<>();  
               
                 ArrayList <Signal> outputSignals = this.circuit.getOutputs();
                
                 System.out.println("\n------ Getting the output(s) paths: --------");
                 
                 System.out.println("Output(s): " + outputSignals + " - size: " + outputSignals.size());
                
                 System.out.println("\n - Starting search in depth...\n");

                        ArrayList<GateLevel> GatesLevels = this.levelCircuit.getGateLevels();

                        int indexlastLevel = GatesLevels.size()-1;
                        //int size = indexlastLevel -1 ;

                        ArrayList<Object> gatesInThisLevel = GatesLevels.get(indexlastLevel).getGates();

                        System.out.println("Total of GateLevels: " + GatesLevels.size());  //Procurar os gates lógicos do ultimo nível
                        
                        System.out.println("Gates on last level: "+ gatesInThisLevel);

                        for (int i = 0; i < gatesInThisLevel.size() ; i++) {
                                   
                                   DepthGate outputGate = (DepthGate) gatesInThisLevel.get(i) ;
                                   Gate gate = outputGate.getGate();
                                   System.out.println("               - Output Gate ( " + gate + " ) of " + gatesInThisLevel  + " - Inputs: "+ gate.getInputs());
                                   
                                   GatePath outGate = new GatePath(gate);
                                   
                                   String awns = this.DFS(gate,indexlastLevel, gate.toString(), outGate);
                                   System.out.println("[ - Output gate ( " + gate + " ) path => "+ outGate.REV_getgateinthePatch()+ " awns: "+ awns);
                                   
                                   possiblePaths.add(outGate);
                                   //Thread.sleep(500);
                       }
                //System.out.println("---------------------------------------------\n");
                
                
                return possiblePaths;
                        
     }
     
     public GatePath calcsubleafONE(ArrayList <GatePath> possiblePaths, GatePath A,int i, int ini, int end){
        
         
                                List subList = possiblePaths.get(i).getgateinthePatch().subList(ini,end);
 
                                System.out.println("Sub list elements : InI: " + ini + "   -  End: "+ end);
                                    
                              
                                        Gate z = (Gate) possiblePaths.get(i).getgateinthePatch().get(ini);
                                        if(!z.getId().equals("stop")){
                                            A.setgateinthePatch(possiblePaths.get(i).getgateinthePatch().get(ini));
                                        }
                                    
         
         
         return A;
         
     }
        
     public GatePath calcsubleaf(ArrayList <GatePath> possiblePaths, GatePath A,int i, int ini, int end){
        
         
                                //List subList = possiblePaths.get(i).getgateinthePatch().subList(ini,end);
 
                                System.out.println("Sub list elements : InI: " + ini + "   -  End: "+ end);
                                    
                                    for(int k =ini; k < end ; k++){
                                       //System.out.println(subList.get(k));
                                        Gate z = (Gate) possiblePaths.get(i).getgateinthePatch().get(k);
                                        if(!z.getId().equals("stop")){
                                            A.setgateinthePatch(possiblePaths.get(i).getgateinthePatch().get(k));
                                        }
                                    }
            System.out.println("K>:" + end);
         
         return A;
         
     }
        
     public GatePath calcsubleafMODE(ArrayList <GatePath> possiblePaths, GatePath A,int i, int index, int ini, int end, ArrayList<Object> vector){
        
          //A.setgateinthePatch(possiblePaths.get(i).getgateinthePatch().get(k));
                             
                                List <Object> sub_list = new ArrayList<>();

                                sub_list = vector.subList(ini, end);

                                ///System.out.println("  ------SUB: "+ sub_list + " ini: "+ ini + "  end:" + end);
                                
                                //A.setgateinthePatch(sub_list);//this.calcsubleaf(possiblePaths, A, index, ini , end);

                                //System.out.println("    A inside: "+A.getGate());
                                //A.setgateinthePatch(possiblePaths.get(i).getGate());
                                 for(int k = 0; k < sub_list.size() ; k++){
                                       //System.out.println("item (" + k + "): " + sub_list.get(k));
                                       //if(sub_list.size()>= 0 && k < sub_list.size()){
                                            Gate z = (Gate) sub_list.get(k);
                                            if(!z.getId().equals("stop")){
                                                A.setgateinthePatch((Gate) sub_list.get(k));
                                            }
                                       //}
                                       
                                    }
                                  
         
         return A;
         
     }
     
     public ArrayList <GatePath> calcLeaf(ArrayList <GatePath> possiblePaths){
         
         System.out.println("- Separating the paths...");
        
         ArrayList <GatePath> leafs =  new ArrayList<>();
         
         for (int i = 0; i < possiblePaths.size(); i++) { //Percorrer vetor de caminhos (i)
             
             System.out.println(  "     i : "+  i + " - Possible Path (" + possiblePaths.get(i).getGate() + ") : " + possiblePaths.get(i).getgateinthePatch());
             
             //System.out.println("Calcing leaf:");
             
             String r = "";
             String result_type = this.typeStructureDefinition(r, i, cellLibrary);
             
             if(result_type.equals("Gate")){
                   GatePath Temp = possiblePaths.get(i);
                   Gate x = (Gate) Temp.getgateinthePatch().get(0);
             }
             else{
                 GatePath Temp = possiblePaths.get(i);
                 //Gate x = (Gate) Temp.getgateinthePatch().get(0);
             }
             
            
                    
             int vector_size = possiblePaths.get(i).getgateinthePatch().size();
                           
             int ini = 0; //Inicio = 1 por causa do stop inicial [stop, x, y, z]
             
             //System.out.println("Vector index pos: " + possiblePaths.get(i).getindexStop());
             
             ArrayList <Object> vector = possiblePaths.get(i).getgateinthePatch();
            
             ArrayList<Integer> stopCount = new ArrayList<>();
             //int ini = 0;
             //int k;
             //int end = 0;
             stopCount.add(0);
             for (int j = 0; j < vector.size(); j++) {
                 
                 String awn = "";
                 awn =  vector.get(j).getClass().toString();
                 
                ///System.out.println("Vector pos : "+ j);
               
                 if(awn.equals("class datastructures.Gate")){
                       Gate x = (Gate) vector.get(j);
                       //System.out.println("Gate: " + x.getId());
                        if("stop".equals(x.getId()) && (j> 0) && j<=vector.size()){
                                stopCount.add(j);
                        }
      
                 }
             
              
             }
             
             ini = 0;//stopCount.get(0);
        
             int vecSize = stopCount.size();
             
             System.out.println("stop count : " + stopCount  + " size :  " + vecSize);
   
              for (int index = 0; index <= vecSize; index++){
                    
                     if(index < vecSize){
                        int end = stopCount.get(index);

                        GatePath A = new GatePath(possiblePaths.get(i).getGate()); 
                        A = this.calcsubleafMODE(possiblePaths, A, i,index, ini, end, vector);
                        
                        if(A.REV_getgateinthePatch().size()>0){
                           leafs.add(A);
                        }
                        ini = end+1;
                     }
                     else{
                          // System.out.println("End tape: " + index);
                           ini = stopCount.get(index-1)+1;
                           int end = vector.size();
                          
                        
                             GatePath X = new GatePath(possiblePaths.get(i).getGate());
                             for(int k = vector.size()-1; k >= ini; k--){
                                      /// System.out.println(" dev item (" + k + "): " + vector.get(k));
                                       //if(sub_list.size()>= 0 && k < sub_list.size()){
                                            Gate z = (Gate) vector.get(k);
                                            if(!z.getId().equals("stop")){
                                                X.setgateinthePatch(z);
                                            }
                                       //}
                                       
                               }
                             //X.setgateinthePatch(possiblePaths.get(i).getGate());
                             leafs.add(X);
                             
                             //System.out.println("OUT: "+ X.outGate.getId());
                             //X.setgateinthePatch(possiblePaths.get(i).getGate());
                             
                           /*tESTE*/
                         
                     }

                }    
              
             }      
       
         return leafs;
         
     }
     
     public void printPossiblePaths(ArrayList <GatePath> possiblePaths){
         
         for (int i = 0; i < possiblePaths.size(); i++) {
             System.out.println("           Possible path: "+  i + " - (" + possiblePaths.get(i).getGate() + ") : " + possiblePaths.get(i).getgateinthePatch());
         }
         
     }
     
     public void printPossibleLeafs(ArrayList <GatePath> possiblePaths){
         
         for (int i = 0; i < possiblePaths.size(); i++) {
             System.out.println(" -- Begin from input to output: "+ possiblePaths.get(i).getGate() + " Avaliable path (" + i + ") -> " + possiblePaths.get(i).getgateinthePatch());
         }
         
     }
     
     public void printPossiblePathsDetails(ArrayList <GatePath> possiblePaths){
         
         for (int i = 0; i < possiblePaths.size(); i++) {
             System.out.println("           Possible path: "+  i + " - (" + possiblePaths.get(i).getGate() + ") : " + possiblePaths.get(i).getgateinthePatch());
             
             for (int j = 0; j < possiblePaths.get(i).getgateinthePatch().size(); j++) {
                 System.out.println("Cell: "+ possiblePaths.get(i).getgateinthePatch().get(j));
                 
                 Object T = (Object) possiblePaths.get(i).getgateinthePatch().get(j);
                 
                 
                // Gate teste = (Gate) T;
                 
             }
                        
         }
         
     }
     
     public boolean calculateOutputGateValue(Cell cells, DepthGate gate,ArrayList <Signal> inputsSignals){
                      
                Map<ArrayList<Boolean>, Boolean> comb = cells.getComb();
                ArrayList <Boolean> input = new ArrayList<>();
                ArrayList <Integer> signals = new ArrayList<>();
                for (int index = 0; index < inputsSignals.size(); index++) {
                        //System.out.println("                                Input Signal: " + inputsSignals.get(index).getLogicValue());
                        
                        signals.add(inputsSignals.get(index).getLogicValue());
                        
                        if(inputsSignals.get(index).getLogicValue() == 0){
                            input.add(Boolean.FALSE);
                        }
                        if(inputsSignals.get(index).getLogicValue() == 1){
                            input.add(Boolean.TRUE);
                        }
                }
                              
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
                                 
                             }
                             if(x == false){
                                 r = "0";
                             }
                           
                         }
                }
                if(!output.equals("stuck")){
                    System.out.println("               Gate: " + gate.getGate() + "(" +cells.getName() + ") inputSignals: " + gate.getGate().getInputs() + " -> values: "  + signals + " ~ " + input  + " -> Output " + gate.getGate().getOutputs() + " is: " + r + " ["+ output +"] ------ \n");
                }else{
                    System.out.println("ERROR stuck !!!!!");
                }
               
         
         return (boolean) output;
     }
     
     public void getPropagateResults(CellLibrary cellLib, ArrayList <Integer> vector){
         
            System.out.println(" Propagation results: ");
                        
            ArrayList <Signal> signalsOutput = this.circuit.getOutputs();
            ArrayList <Signal> signalsInput = this.circuit.getInputs();
            //System.out.println("           - input vector: " + vector);
            
            ArrayList <String> input_value_str = new ArrayList<>();
            ArrayList <String> output_value_str = new ArrayList<>();
            
            String input = "Inputs";
            String input_value = "";
            String output_value = "";
           
            
            for (int i = 0; i < signalsInput.size(); i++) {
                      if(i > 0){
                        //input = "" + ";"; // + signalsInput.get(i).getId();
                        input_value = input_value + signalsInput.get(i).getLogicValue();
                        input_value_str.add(String.valueOf(signalsInput.get(i).getLogicValue()));
                      }
                      else{
                        //input = "Signals" + ";";//signalsInput.get(i).getId();
                        input_value =  "" +  signalsInput.get(i).getLogicValue();
                        input_value_str.add(String.valueOf(signalsInput.get(i).getLogicValue()));
                      }
                      //values = values + signalsInput.get(i).getLogicValue() + "     ";
                      System.out.println(" - Signal (" + signalsInput.get(i) + ") Logic Value: " + signalsInput.get(i).getLogicValue());
            }
            
            for (int s = 0; s < signalsOutput.size(); s++) {
                       if(s > 0){
                        //input = "" + ";"; // + signalsInput.get(i).getId();
                         output_value = output_value + signalsOutput.get(s).getLogicValue();
                         output_value_str.add(String.valueOf(signalsOutput.get(s).getLogicValue()));
                      }
                      else{
                        //input = "Signals" + ";";//signalsInput.get(i).getId();
                        output_value = "" + signalsOutput.get(s).getLogicValue();
                           output_value_str.add(String.valueOf(signalsOutput.get(s).getLogicValue()));
                        //input_value =  "" +  signalsInput.get(i).getLogicValue();
                      }
                
                      System.out.println("           - Gate: " + signalsOutput.get(s).getOrigin() + " (" + signalsOutput.get(s).getOrigin().getType() + ") - Output: "+ signalsOutput.get(s) + " - Logic value: "+ signalsOutput.get(s).getLogicValue() + " [" + signalsOutput.get(s).getLogicValueBoolean() + "]" );             
            }
            
            //this.inputListValues.add(input_value);

            this.inputListValuesStr.add(input_value_str);
            
            this.outputListValueStr.add(output_value_str);
            
           //System.out.println("Inputs : "+ input );
            
           //System.out.println("SigValues : " + this.inputListValues);
            
          //System.out.println("Str : " + input_value_str);
           
            /*
            for (Iterator<ArrayList<String>> iterator = this.inputListValuesStr.iterator(); iterator.hasNext();) {
                    ArrayList<String> next = iterator.next();
                    //System.out.println("NEsxt: " + next.toString());
                    
                    StringBuffer sb = new StringBuffer();
                    for (String s : next) {
                            sb.append(s);
                            //sb.append(" ");
                    }
                    System.out.println( "           SB: "+sb);
                   
            }
            */
            
        
           
     }
     
     public void propagate(CellLibrary cellLib, ArrayList <Integer> vector) throws IOException, WriteException{
           
           System.out.println("Propagating...");
           
           ArrayList <GateLevel> gatesLevels = this.levelCircuit.getGateLevels();  //gates on this levels
           
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
                        boolean test = this.calculateOutputGateValue(gate.getGate().getType(), gate, gate.getGate().getInputs());  //Method calc the output from the gate
                        
                        if(test == true){
                            for (int s = 0; s < gate.getGate().getOutputs().size(); s++) {
                                Signal sig = gate.getGate().getOutputs().get(s);
                                sig.setLogicValue(1);
                                sig.setLogicValueBoolean(test);
                            }
                        }else{
                             for (int s = 0; s < gate.getGate().getOutputs().size(); s++) {
                                Signal sig = gate.getGate().getOutputs().get(s);
                                sig.setLogicValue(0);
                                sig.setLogicValueBoolean(test);
                            }
                            
                        }
                    }
               }
                
           }
           
         
     }
     
     public ArrayList<String> calcInputTableVector(int n_inputs, int numLines){
         
         System.out.println("       - Truthtable: ");
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
                
                System.out.println("" + (i+1) + " - " + str);
                vector.add(str);
             }
             else{
                 System.out.println("" + (i+1) + " - " + Integer.toBinaryString(i) );
                 vector.add(Integer.toBinaryString(i));
             }
         }
         
         //System.out.println(""+vector);
         return vector;
     }
     
     public  ArrayList<String> calcInputRandom(CellLibrary cellLib, String Option, int vecInput_size, int numTest){
              
                // - ---------------------------------------- creating input vector ------------------------------------------------------
                System.out.println("");
               
              
                        int inputSignaisSize = this.circuit.getInputs().size();
                        //System.out.println("Input Signals: " + inputSignais + " Size: " + inputSignais.size());
                        ArrayList <String> vector = new ArrayList<>();
                        
                        Random gerador = new Random();
                        String str = "";
                        if(numTest <= vecInput_size){
                            for (int i = 0; i < numTest; i++) {
                                 for (int j = 0; j < inputSignaisSize; j++) {
                                      int randomLogicValue = gerador.nextInt(2);
                                      str = str + randomLogicValue;
                                 }
                                // System.out.println("STR: "+str);
                               
                                 vector.add(str);
                                     
                                 str = "";
                                //inputSignais.get(i).setLogicValue(randomLogicValue);
                               // System.out.println(inputSignais.get(i)+" : '"+inputSignais.get(i).getLogicValue()+"'");
                          }
                       
                       }
                        
                        //vetor de entrada aleatório
                        
                      
                 
                
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
     
     public void writeCsvFileCompleteTh(String filename, ArrayList <Logic_Simulator> itemx_list) throws IOException, WriteException{
         
             //System.out.println("Creating Complete log .....");
             ArrayList <Signal> outputSignals = this.circuit.getOutputs();
             ArrayList <Signal> inputSignals = this.circuit.getInputs();
              
            
            String Header = "Input Signals" + ";" + "Fault Signal" + ";" + "Fault Type" + ";" + "Fault-Free Circuit Output" + ";" + "Faulty Circuit Output";
            //String outputElementsHeader = "";
            
            /*
            for (int i = 0; i < inputSignals.size(); i++) {
                      if(i > 0){
                        //inputSignalStr = inputSignalStr  + inputSignals.get(i).getId();
                        inputValueSrt = inputValueSrt  +  inputSignals.get(i).getLogicValue();
                      }
                      else{
                        //inputSignalStr = inputSignals.get(i).getId();
                        inputValueSrt =  "" +  inputSignals.get(i).getLogicValue();
                      }
                      //values = values + signalsInput.get(i).getLogicValue() + "     ";
                     // System.out.println(" - Signal (" + inputSignals.get(i) + ") Logic Value: " + inputSignals.get(i).getLogicValue());
            }
            for (int s = 0; s < outputSignals.size(); s++) {
                          outputElementsHeader  = outputElementsHeader  + outputSignals.get(s).toString() + "(output)";
            }
              System.out.println("input signals: "+inputSignalStr);
              System.out.println("input signals vaalues: "+inputValueSrt);
           */
            
            
            
           // System.out.println("---->>>"+ this.outputListValueStr);
            WriteCsvTh logfile = new WriteCsvTh();
            
            logfile.writeCSVCompleteTh(filename, this.verilog_circuit.getCircuit().getName() + ".v", this.circuit.getInputs(), this.circuit.getOutputs(), 
                    this.internSignals,  Header , itemx_list , Integer.toString(this.unmasked_faults));
                
     }
     
     public void writeCsvFileComplete(String filename) throws IOException, WriteException{
         
             //System.out.println("Creatinf COmplete log .....");
             ArrayList <Signal> outputSignals = this.circuit.getOutputs();
             ArrayList <Signal> inputSignals = this.circuit.getInputs();
              
            
            String Header = "Input Signals" + ";" + "Fault Signal" + ";" + "Fault Type" + ";" + "Fault-Free Circuit Output" + ";" + "Faulty Circuit Output";
            //String outputElementsHeader = "";
            
            /*
            for (int i = 0; i < inputSignals.size(); i++) {
                      if(i > 0){
                        //inputSignalStr = inputSignalStr  + inputSignals.get(i).getId();
                        inputValueSrt = inputValueSrt  +  inputSignals.get(i).getLogicValue();
                      }
                      else{
                        //inputSignalStr = inputSignals.get(i).getId();
                        inputValueSrt =  "" +  inputSignals.get(i).getLogicValue();
                      }
                      //values = values + signalsInput.get(i).getLogicValue() + "     ";
                     // System.out.println(" - Signal (" + inputSignals.get(i) + ") Logic Value: " + inputSignals.get(i).getLogicValue());
            }
            for (int s = 0; s < outputSignals.size(); s++) {
                          outputElementsHeader  = outputElementsHeader  + outputSignals.get(s).toString() + "(output)";
            }
              System.out.println("input signals: "+inputSignalStr);
              System.out.println("input signals vaalues: "+inputValueSrt);
           */
            
           // System.out.println("---->>>"+ this.outputListValueStr);
            WriteCsv logfile = new WriteCsv();
            logfile.writeCSVComplete(filename, this.verilog_circuit.getCircuit().getName() + ".v", this.circuit.getInputs(), this.circuit.getOutputs(), 
                    this.internSignals,  Header , this.inputListValuesStr, this.outputListValueStr);
                
     }
    
     public void writeCsvFile(String filename) throws IOException, WriteException{
         
            String Header = "Input Signals" + ";" + "Fault-Free Circuit Output";
            
            WriteCsv logfile = new WriteCsv();
            logfile.writeCSV(filename, this.verilog_circuit.getCircuit().getName() + ".v", this.circuit.getInputs(), this.circuit.getOutputs(), 
                    this.circuit.getSignals(),  Header , this.inputListValuesStr, this.outputListValueStr);
                
     }
     
     public void writeCsvFileTh(String filename, ArrayList <Logic_Simulator> itemx_list) throws IOException, WriteException{
            System.out.println("---------- New Csv_Txt ----------\n");
            String Header = "Input Signals" + ";" + "Fault-Free Circuit Output";
            
            //System.out.println("  SIZE INPUT: "+this.inputListValuesStr.size());
            //System.out.println("  SIZE OUT: "+this.outputListValueStr.size());
            
            ArrayList <ArrayList<String>> temp = new ArrayList<>();
            ArrayList <String> temp2 = new ArrayList<>();
            for (int i = 0; i < this.outputListValueStr.size(); i++) {
               temp.add(this.outputListValueStr.get(i));
     
           }
            
            WriteCsvTh logfile = new WriteCsvTh();
            
           //System.out.println("-> SIZE OUT: "+ this.circuit.getOutputs().size());
           
            logfile.writeCSVTh(filename, this.verilog_circuit.getCircuit().getName() + ".v", this.circuit.getInputs(), this.circuit.getOutputs(), 
                    this.circuit.getSignals(),  Header , itemx_list);
           
           System.out.println("--------------------\n");
     }
     
     public void writeTxtLog(String filename, String date, String dateend, int threads, long propagateTime, int nrVec,long propagateTimems) throws IOException{
        
        System.out.println("Creating .txt -> file: " + filename);
        
        //this.unmasked_faults = propagate_faults;
        this.reliability_circuit =  (float) ( 1 - ((float) this.unmasked_faults / (float) nrVec));
         
       
        double lamb = - Math.log(this.reliability_circuit);
        this.MTBF = (1 / lamb);
        
        System.out.println("MTBF : " + this.MTBF);
        String content = "";
        try (FileWriter file = new FileWriter(filename+".txt")) {
            
            
            content = "Date/hour: "  + date + "\n";
                    
            content = content + "Date/hour (end):d "+ dateend + "\n\n";
            
            content = content + "Circuit: " + this.circuit.getName() + "\n";
            content = content+  "Number of Simulations (N): " + nrVec + "\n";
            
            content = content+  "Number of Threads: " + threads + "\n";
            content = content + "Number of observed faults (Ne): " + this.unmasked_faults + "\n";
            // content = content + "Number of observed faults (Ne): " + "\n";
            
            content = content +  "Reliability: " + this.reliability_circuit + "\n";
            // content = content +  "Reliability: " + reliability + "\n";
            
            content = content +  "MTBF: " + this.MTBF + "\n\n";
                
            content = content +  "Execution Time m(s): " + propagateTimems + "\n";
            content = content +  "Execution Time (s): " + propagateTime + "\n";
            
            file.write(content);
        }
     }
     
     public ArrayList <Signal> get_all_signals(){
         //System.out.println("Sort Fault in the input injection");
         ArrayList <Signal> signalList = new ArrayList<>();
         signalList = this.circuit.getSignals();
         
         ArrayList <Signal> r = new ArrayList<>();
                for (int i = 0; i < signalList.size(); i++) {
                        //if(!((this.circuit.getInputs().contains(signalList.get(i))) || (this.circuit.getOutputs().contains(signalList.get(i))))){
                           //this.internSignals.add(signalList.get(i));
                           r.add(signalList.get(i));
                        //}
                    
                }
                System.out.println(" Considering all signals --> "+r);  
                System.out.println("Signals list: "+ signalList.toString());
         return r;
     }
     
     public ArrayList <Signal> get_all_intermediate_signals(){
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
     
     public int randomInjectionFault(){
                        //vetor de entrada aleatório
                        
                        Random gerador = new Random();
                        int randomSignalIndex = gerador.nextInt(this.internSignals.size());
                        
                        //System.out.println("Signal sorted: "+ this.internSignals.get(randomSignalIndex));
                        
                        return randomSignalIndex;
     }
     
     public int calculate_Number_Of_Input_Vector(){
        int n = (int) Math.pow(2, this.probCircuit.getInputs().size());
        return n;
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
    
     public void setInputVectorPartitionYhread(int partition, int start, int end){
         
     }
     
     public void processLogicSimMultiThreadingRsults(List thread_list, ArrayList <Logic_Simulator> itemx_list, long propagateTimeStart, int N, String dateini) throws IOException, WriteException{
         
                 
         
                 for (int i=0; i < itemx_list.size() ; i++) {

                            // System.out.println("SIgnal fault list : "+ this.SignalFault);
                           
                            this.unmasked_faults = this.unmasked_faults +  itemx_list.get(i).getPropagatedFaults();
                            
                            System.out.println("Thread: " + itemx_list.get(i).getThreadId() + " - Total Faults : "+  itemx_list.get(i).getPropagatedFaults());
                    }
               
                
                
                long propagateTimeEnd = System.nanoTime();
                long propagateTime =   TimeUnit.NANOSECONDS.toSeconds(propagateTimeEnd - propagateTimeStart);
                long propagateTimems =   TimeUnit.NANOSECONDS.toMillis(propagateTimeEnd - propagateTimeStart);
                
             
                LocalDateTime myDateObj2 = LocalDateTime.now();
                DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate2 = myDateObj2.format(myFormatObj2);
                
                System.out.println("- Simulation finished at: " + formattedDate2);
                System.out.println("- PropagatedTime (s): " + propagateTime);
                System.out.println("");
                
                this.writeCsvFileTh("Multithreading_Log_fault_free_"+this.circuit.getName()+"_Theads-"+ this.thread, itemx_list);
                
                this.writeTxtLog("Multithreading_Simple_Log_" +this.circuit.getName()+"_Theads-"+ this.thread , dateini,  formattedDate2, thread_list.size(), propagateTime, N, propagateTimems);
                
                this.writeCsvFileCompleteTh("Multithreading_Complete_Log_"+this.circuit.getName()+"_Theads-"+ this.thread, itemx_list);
               
                
                System.out.println("----------------- Results ------------------");
                System.out.println("Circuit: " + this.circuit.getName());
                System.out.println("Total Vectors (N): " + N);
                System.out.println("Propagated fault(s) (Ne): " + this.unmasked_faults);
                System.out.println("Reliability: " + "(1-(" + this.unmasked_faults + "/" + N + ")) = " + this.reliability_circuit);
                System.out.println("MTBF (Mean Time Between failure) : " + this.MTBF);
                System.out.println("Simulation TimeElapsed: " + propagateTime + " m(s)");
                System.out.println("--------------------------------------------");

                
         
     }
      
     public void processLogicSimMultiThreadingResultsRandom(List thread_list, ArrayList <Logic_Simulator> itemx_list, long propagateTimeStart, int N, String dateini, int testNumber) throws IOException, WriteException{
         
                 
         
                 for (int i=0; i < itemx_list.size() ; i++) {

                            // System.out.println("SIgnal fault list : "+ this.SignalFault);
                           
                            this.unmasked_faults = this.unmasked_faults +  itemx_list.get(i).getPropagatedFaults();
                            
                            System.out.println("Thread: " + itemx_list.get(i).getThreadId() + " - Total Faults : "+  itemx_list.get(i).getPropagatedFaults());
                    }
               
                
                
                long propagateTimeEnd = System.nanoTime();
                long propagateTime =   TimeUnit.NANOSECONDS.toSeconds(propagateTimeEnd - propagateTimeStart);
                long propagateTimems =   TimeUnit.NANOSECONDS.toMillis(propagateTimeEnd - propagateTimeStart);
                
             
                LocalDateTime myDateObj2 = LocalDateTime.now();
                DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate2 = myDateObj2.format(myFormatObj2);
                
                System.out.println("- Simulation finished at: " + formattedDate2);
                System.out.println("- PropagatedTime (s): " + propagateTime);
                System.out.println("");
                
                //this.writeCsvFileTh("Multithreading_Log_fault_free_"+this.circuit.getName()+"_Theads-"+ this.thread +  "_TestNumber-"+ N , itemx_list);
                
                this.writeTxtLog("Multithreading_Simple_Log_" +this.circuit.getName()+"_Theads-"+ this.thread +  "_TestNumber-"+ N , dateini,  formattedDate2, thread_list.size(), propagateTime, N, propagateTimems);
                
                this.writeCsvFileCompleteTh("Multithreading_Complete_Log_"+this.circuit.getName()+"_Theads-"+ this.thread + "_TestNumber-"+ N , itemx_list);
               
                
                System.out.println("----------------- Results ------------------");
                System.out.println("Circuit: " + this.circuit.getName());
                System.out.println("Total Vectors (N): " + N);
                System.out.println("Propagated fault(s) (Ne): " + this.unmasked_faults);
                System.out.println("Reliability: " + "(1-(" + this.unmasked_faults + "/" + N + ")) = " + this.reliability_circuit);
                System.out.println("MTBF (Mean Time Between failure) : " + this.MTBF);
                System.out.println("Simulation TimeElapsed: " + propagateTime + " m(s)");
                System.out.println("--------------------------------------------");

                
         
     }
    
     public void MulltiThreading_Logic_Simulator_ramdomInputs(String library, String CircuitFile, int testNumber) throws IOException, Exception{
                
                long loadTimeStart = System.nanoTime();//System.currentTimeMillis();
                
                LocalDateTime myDateObj = LocalDateTime.now();
                //System.out.println("Before formatting: " + myDateObj);
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);
                System.out.println("- Simulation start in : " + formattedDate);
         
                /*Reading CellLibrary*/
                CellLibrary cellLib = new CellLibrary();
                this.cellLibrary = cellLib;
                this.cellLibrary.initLibrary(this.libraryPath);
                
                System.out.println("--- Reading celllib ...");
                //System.out.println("  - Avaliable logic gatesin this library: "+cellLib.getCells());
                /*----------------------*/
                
                /*Reading verilog*/
                MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitFilePath, this.cellLibrary);
                this.verilog_circuit = verilog_circuit;
                /*Circuit linked to verilog_circuit - init circuit*/
                this.circuit = verilog_circuit.getCircuit();
                System.out.println("--- Reading verilog: " + this.verilog_circuit.getCircuit().getName() + ".v");
                //System.out.println("Patterns : " + this.verilog_circuit.getGatePattern());
                /*----------------------*/
                
                
                /* Print circuit Specs*/
                System.out.println("\n------ Printing Circuit Specs: --------");
                this.PrintSpecs();
                System.out.println("---------------------------------------\n");
                /*----------------------*/
                
                /*Circuit Probabilities */
                this.initLevelCircuit();
               
                /*Init ProbCircuits*/
                 this.initProbCircuit();
                
                /*Init PTMs Const*/
                cellLib.setPTMCells2(Float.valueOf(reliability));
                cellLib.setPTMCells(new BigDecimal(reliability));
               
                long loadTimeEnd = System.nanoTime();//System.currentTimeMillis();
              
                long loadTime =   TimeUnit.NANOSECONDS.toMillis(loadTimeEnd - loadTimeStart);
              
                System.out.println("- Load Time m(s): " + loadTime);
               
                System.out.println("\n------ Inserting the input vector --------");
                System.out.println("     - Circuit Input(s): "+this.probCircuit.getInputs());
                System.out.println("     - Circuit Output(s): "+this.probCircuit.getOutputs());
                
               
                int N = this.calculate_Number_Of_Input_Vector();  //(int) Math.pow(2, this.probCircuit.getInputs().size());
               
                // System.out.println("Number total of input vectors : "+ N);
                
                System.out.println("Threads: " + this.thread); 
               
                this.internSignals = this.get_all_intermediate_signals(); // calc intern Signals
                
                ArrayList <String> random_input_vectors =  this.calcInputRandom(cellLib, library, N, testNumber); //this.calcInputTableVector(this.probCircuit.getInputs().size(), N);
 
                ArrayList <ArrayList<Integer>> ListInputVectors =  this.splitInputPatternsInInt(random_input_vectors, this.probCircuit.getInputs().size());
                
                N = testNumber;
                
                List thread_list = new ArrayList();
                
                long propagateTimeStart = System.nanoTime();
                
                int partition;
                if(this.thread == 1){
                     partition = N; //final_pos/NThreads ;
                }
                else{
                    double temp;
                    temp = Math.floor(N/this.thread); 
                    //System.out.println("Division : "+ a);
                    partition =  (int) temp ;//(int) Math.round(collapsed_faults/NThreads); 
                }
                
                //System.out.println("Partion : "+ partition);
                int start = 0;
                int end = partition;
                
                 ArrayList <Logic_Simulator> itemx_list = new ArrayList<>();
                 
                 //ArrayList <Integer, String> example = new ArrayList<>();
                
                for (int i = 0; i < this.thread; i++) { //Loop of simulations
                                //System.out.println(" - Thread: "+i);
                               
                                ArrayList <Test_Item> ItemxSimulationList = new ArrayList<>();
                    
                                ArrayList <Integer> inputVector = new ArrayList<>();
                                
                                if((this.thread-1) == (i)){

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
                               // System.out.println(" start: "+ start + "  - end: " + end);                         
                                for (int j = start; j < end ; j++) {
                                        
                                        inputVector = this.get_Input_Vectors(ListInputVectors, j); //input Test n
                                        //this.insertInputVector(cellLib, "selected", inputVector); //Depois na hora de inseriri vetor
                                        int SigIndex = this.randomInjectionFault();

                                       // System.out.println("index: "+(j+1) + "     -     vec: " + inputVector);

                                        Test_Item temp = new Test_Item(inputVector, this.internSignals.get(SigIndex), j+1);

                                        ItemxSimulationList.add(temp);
                                }
                                
                                
                                Logic_Simulator threadItem = new Logic_Simulator(ItemxSimulationList, this.circuit, this.cellLibrary, this.levelCircuit, start, end, this.library, this.circuitFilePath); // Thread contex info
                                itemx_list.add(threadItem); // Itemx sorted's
                              
                             
                                
                           
                             
                             Runnable runnable = threadItem; // Logic_Simulator(listThreadItemx, this.circuit, cellLib, levelCircuit);
                             Thread thread = new Thread(runnable);
                             thread_list.add(thread);
                                
                           //  System.out.println("\n---------------------------------------");  
                             
                     }
                  
                    Thread thread_temp = null;// new Thread();
                    for (int i=0; i < thread_list.size() ; i++) {
                        thread_temp = (Thread) thread_list.get(i);
                        thread_temp.start();
                        
                    }
                    
                     for (int i=0; i < thread_list.size() ; i++) {
                        thread_temp = (Thread) thread_list.get(i);
                        thread_temp.join();
                    }
                     
                     
                    this.processLogicSimMultiThreadingResultsRandom(thread_list, itemx_list, propagateTimeStart, N, formattedDate, testNumber);
                    
                    //int propagate_faults = 0;
                  
                   /* last version
                    for (int i=0; i < itemx_list.size() ; i++) {
                        
                            //System.out.println(thread_item_list.get(i).getResults());
                            this.inputListValuesStr.add(itemx_list.get(i).get_inputListValuesStr());
                           
                            
                            //System.out.println((i+1) + " - SAIDA FREE FAULT: " + thread_item_list.get(i).get_outputListValueStr());
                           /// System.out.println("         MAP thread:"+(i+1) + " - SAIDA FREE FAULT: " + thread_item_list.get(i).get_Map_fault_free());
                            
                            //Map<String, String> freeMap = new TreeMap<>(itemx_list.get(i).get_Map_fault_free());
                            Map<String, String> faultMap = new TreeMap<>(itemx_list.get(i).get_Map_fault_fault());
                            //thread_item_list.get(i).printMap(treeMap); 
                           // System.out.println("Tree: "+ treeMap);
                            //thread_item_list.get(i).get_Map_fault_free_str();
                           // ArrayList<String> fault_free = new ArrayList<>();
                            ArrayList<String> fault_inject = new ArrayList<>();
                            
                           //System.out.println("~~~~~~ Unsorted Free: "+freeMap);
                            
                           
                            freeMap.entrySet().forEach((entry) -> { 
                                fault_free.add(entry.getValue());
                            });
                            
                             faultMap.entrySet().forEach((entryx) -> { 
                                fault_inject.add(entryx.getValue());
                            });
                            
                             //System.out.println("~~~~~~ Free: "+freeMap);
                            
                            //System.out.println(" Thread("+ i +"): fault free list-> " + fault_free + "  fault injected -> " + fault_inject);
                            //this.outputFreeFaultListValueStr.add(fault_free); //mandei o fault free
                            this.outputFreeFaultListValueStr.add(itemx_list.get(i).getLinkedFaultFreeResults()); //faultfree
                            

                            //this.outputListValueStr.add(thread_item_list.get(i).get_outputListValueStr());
                            //this.outputListValueStr.add(fault_inject);
                            this.outputListValueStr.add(itemx_list.get(i).getLinkedFaultResults());
                            
                            ///this.SignalFault.add(itemx_list.get(i).get_signalFaultList());
                            this.SignalFault.add(itemx_list.get(i).get_signalFaultListCuncorrentLinked());
                            
                            this.SignalFaultBitFlip.add(itemx_list.get(i).get_signalFaultListBitflip());
                            
                            System.out.println("SIgnal fault list : "+ this.SignalFault);
                            //propagate_faults = propagate_faults + itemx_list.get(i).getPropagatedFaults();
                            this.unmasked_faults = this.unmasked_faults +  itemx_list.get(i).getPropagatedFaults();
                            
                            System.out.println(i + "Faults in : "+  itemx_list.get(i).getPropagatedFaults());
                    }
                    */
                    /*
                   System.out.println(""+this.inputListValuesStr);
                   System.out.println(""+this.outputFreeFaultListValueStr);
                   System.out.println(""+this.outputListValueStr);
                    */
                
                /*
                long propagateTimeEnd = System.nanoTime();
                long propagateTime =   TimeUnit.NANOSECONDS.toMillis(propagateTimeEnd - propagateTimeStart);
                
             
                LocalDateTime myDateObj2 = LocalDateTime.now();
                DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate2 = myDateObj2.format(myFormatObj2);
                
                System.out.println("- Simulation finished at: " + formattedDate2);
                System.out.println("- PropagatedTime m(s): " + propagateTime);
                System.out.println("");
                
                this.writeCsvFileTh("Multithreading_Log_fault_free_"+this.circuit.getName()+"_Theads-"+ this.thread);
                this.writeTxtLog("Multithreading_Simple_Log_" +this.circuit.getName()+"_Theads-"+ this.thread , formattedDate2, thread_list.size(), propagateTime, N);
                this.writeCsvFileCompleteTh("Multithreading_Complete_Log_"+this.circuit.getName()+"_Theads-"+ this.thread);
               
                System.out.println("Main: Porpagated fault -: "+this.unmasked_faults);

                */
          
     }
    
    
     public void Sequential_Logic_Simulator(String library, String CircuitFile) throws IOException, ScriptException, Exception{  
         
                long loadTimeStart = System.nanoTime();//System.currentTimeMillis();
                
                LocalDateTime myDateObj = LocalDateTime.now();
                //System.out.println("Before formatting: " + myDateObj);
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);
                System.out.println("- Simulation start in : " + formattedDate);
         
                /*Reading CellLibrary*/
                CellLibrary cellLib = new CellLibrary();
                cellLib.initLibrary(this.libraryPath);
                
                System.out.println("--- Reading celllib ...");
                //System.out.println("  - Avaliable logic gatesin this library: "+cellLib.getCells());
                /*----------------------*/
                
                /*Reading verilog*/
                MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitFilePath, cellLib);
                this.verilog_circuit = verilog_circuit;
                /*Circuit linked to verilog_circuit - init circuit*/
                this.circuit = verilog_circuit.getCircuit();
                System.out.println("--- Reading verilog: " + this.verilog_circuit.getCircuit().getName() + ".v");
                //System.out.println("Patterns : " + this.verilog_circuit.getGatePattern());
                /*----------------------*/
                
                
                /* Print circuit Specs*/
                System.out.println("\n------ Printing Circuit Specs: --------");
                this.PrintSpecs();
                System.out.println("---------------------------------------\n");
                /*----------------------*/
                
                /*Circuit Probabilities */
                this.initLevelCircuit();
               
                /*Init ProbCircuits*/
                this.initProbCircuit();
                
                /*Init PTMs Const*/
                cellLib.setPTMCells2(Float.valueOf(reliability));
                cellLib.setPTMCells(new BigDecimal(reliability));
               
                long loadTimeEnd = System.nanoTime();//System.currentTimeMillis();
              
                long loadTime =   TimeUnit.NANOSECONDS.toMillis(loadTimeEnd - loadTimeStart);
              
                //System.out.println("- Load Time m(s): " + loadTime);
               
                System.out.println("\n------ Inserting the input vector --------");
                System.out.println("     - Circuit Input(s): "+this.probCircuit.getInputs());
                System.out.println("     - Circuit Output(s): "+this.probCircuit.getOutputs());
                
               
                int N = this.calculate_Number_Of_Input_Vector();  //(int) Math.pow(2, this.probCircuit.getInputs().size());
               
                System.out.println("Total input vectors: 2^(" + this.probCircuit.getInputs().size() + ") = " + N);
                
                this.internSignals = this.get_all_intermediate_signals(); // calc intern Signals
                
                ArrayList <String> raw_inputVectors =  this.calcInputTableVector(this.probCircuit.getInputs().size(), N);
 
                ArrayList <ArrayList<Integer>> ListInputVectors =  this.splitInputPatternsInInt(raw_inputVectors, this.probCircuit.getInputs().size());
    
                long propagateTimeStart = System.nanoTime();
                
                for (int i = 0; i < N; i++) { //Loop of simulations
                    
                             ArrayList <Integer> inputVector = new ArrayList<>();
                             
                             inputVector = this.get_Input_Vectors(ListInputVectors, i);

                             this.insertInputVector(cellLib, "selected", inputVector, i);
                             
                            // System.out.println("Index: "+(i+1));// + "     -     vec: " + inputVector);
                                                          
                             this.propagate(cellLib, inputVector);
                             this.getPropagateResults(cellLib, inputVector);
                                                          
                             //System.out.println("\n---------------------------------------");  
                             
                }
             
                
                long propagateTimeEnd = System.nanoTime();
                long propagateTime =   TimeUnit.NANOSECONDS.toMillis(propagateTimeEnd - propagateTimeStart);
                
             
                LocalDateTime myDateObj2 = LocalDateTime.now();
                DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate2 = myDateObj2.format(myFormatObj2);
                
                System.out.println("- Simulation finished at: " + formattedDate2);
                System.out.println("- PropagatedTime m(s): " + propagateTime);
                System.out.println("");
                
                this.writeCsvFile("Log_Fault_Free_"+this.circuit.getName());
                //writeTxtLog("Simple_Log_" +this.circuit.getName() , formattedDate2, 0, propagateTime, N);
                this.writeCsvFileComplete("Complete_Log_"+this.circuit.getName());
               
                
                
                        /*----------------------*/
              
                           /* A partir da saida percorrer o circuito Recursivo */
                     /*
                           
                    System.out.println("\n------ Compting possible Paths --------");
                    ArrayList <GatePath> possiblePaths = new ArrayList<>();
                    possiblePaths = this.getOutputToInputPath();
                    System.out.println("\n---------------------------------------");
                    
                    
                   
                    System.out.println("\n------ Possible Paths vector input to output --------");
                    this.printPossiblePaths(possiblePaths);
                    System.out.println("\n---------------------------------------");
 
                    System.out.println("------- Leafs Avaliables :  --------"); 
                         ArrayList <GatePath> calcedLeafs = this.calcLeaf(possiblePaths);
                        //this.printPossibleLeafs(calcedLeafs);
                    System.out.println("------------------------------- \n");
                    */
                    
                   
     }
      
     public void MulltiThreading__Logic_Simulator_ramdomInputs_MonteCarlo(String library, String CircuitFile, int testNumber, int interaction, int id) throws IOException, Exception{
                
                long loadTimeStart = System.nanoTime();//System.currentTimeMillis();
                
                LocalDateTime myDateObj = LocalDateTime.now();
                //System.out.println("Before formatting: " + myDateObj);
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);
                System.out.println("- Simulation start in : " + formattedDate);
                System.out.println("- Vec size: " + testNumber);
                 System.out.println("- Round : " + interaction);
         
                /*Reading CellLibrary*/
                CellLibrary cellLib = new CellLibrary();
                this.cellLibrary = cellLib;
                this.cellLibrary.initLibrary(this.libraryPath);
                
                System.out.println("--- Reading celllib ...");
                //System.out.println("  - Avaliable logic gatesin this library: "+cellLib.getCells());
                /*----------------------*/
                
                /*Reading verilog*/
                MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitFilePath, this.cellLibrary);
                this.verilog_circuit = verilog_circuit;
                /*Circuit linked to verilog_circuit - init circuit*/
                this.circuit = verilog_circuit.getCircuit();
                System.out.println("--- Reading verilog: " + this.verilog_circuit.getCircuit().getName() + ".v");
                //System.out.println("Patterns : " + this.verilog_circuit.getGatePattern());
                /*----------------------*/
                
                
                /* Print circuit Specs*/
                System.out.println("\n------ Printing Circuit Specs: --------");
                this.PrintSpecs();
                System.out.println("---------------------------------------\n");
                /*----------------------*/
                
                /*Circuit Probabilities */
                this.initLevelCircuit();
               
                /*Init ProbCircuits*/
                 this.initProbCircuit();
                
                /*Init PTMs Const*/
                cellLib.setPTMCells2(Float.valueOf(reliability));
                cellLib.setPTMCells(new BigDecimal(reliability));
               
                long loadTimeEnd = System.nanoTime();//System.currentTimeMillis();
              
                long loadTime =   TimeUnit.NANOSECONDS.toMillis(loadTimeEnd - loadTimeStart);
              
                System.out.println("- Load Time m(s): " + loadTime);
               
                System.out.println("\n------ Inserting the input vector --------");
                System.out.println("     - Circuit Input(s): "+this.probCircuit.getInputs());
                System.out.println("     - Circuit Output(s): "+this.probCircuit.getOutputs());
                
               
                int N = this.calculate_Number_Of_Input_Vector();  //(int) Math.pow(2, this.probCircuit.getInputs().size());
               
                // System.out.println("Number total of input vectors : "+ N);
                
                System.out.println("Threads: " + this.thread); 
               
                //this.internSignals = this.get_all_intermediate_signals(); // calc intern Signals
                    
                this.internSignals = this.get_all_signals(); //Consider all signals in circuit, input, intermediate and output ones
                
                ArrayList <String> random_input_vectors =  this.calcInputRandom(cellLib, library, N, testNumber); //this.calcInputTableVector(this.probCircuit.getInputs().size(), N);
 
                ArrayList <ArrayList<Integer>> ListInputVectors =  this.splitInputPatternsInInt(random_input_vectors, this.probCircuit.getInputs().size());
                
                N = testNumber;
                
                List thread_list = new ArrayList();
                
                long propagateTimeStart = System.nanoTime();
                
                int partition;
                if(this.thread == 1){
                     partition = N; //final_pos/NThreads ;
                }
                else{
                    double temp;
                    temp = Math.floor(N/this.thread); 
                    //System.out.println("Division : "+ a);
                    partition =  (int) temp ;//(int) Math.round(collapsed_faults/NThreads); 
                }
                
                //System.out.println("Partion : "+ partition);
                int start = 0;
                int end = partition;
                
                 ArrayList <Logic_Simulator> itemx_list = new ArrayList<>();
                 
                 //ArrayList <Integer, String> example = new ArrayList<>();
                
                for (int i = 0; i < this.thread; i++) { //Loop of simulations
                                //System.out.println(" - Thread: "+i);
                               
                                ArrayList <Test_Item> ItemxSimulationList = new ArrayList<>();
                    
                                ArrayList <Integer> inputVector = new ArrayList<>();
                                
                                if((this.thread-1) == (i)){

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
                               // System.out.println(" start: "+ start + "  - end: " + end);                         
                                for (int j = start; j < end ; j++) {
                                        
                                        inputVector = this.get_Input_Vectors(ListInputVectors, j); //input Test n
                                        //this.insertInputVector(cellLib, "selected", inputVector); //Depois na hora de inseriri vetor
                                        int SigIndex = this.randomInjectionFault();

                                       // System.out.println("index: "+(j+1) + "     -     vec: " + inputVector);

                                        Test_Item temp = new Test_Item(inputVector, this.internSignals.get(SigIndex), j+1);

                                        ItemxSimulationList.add(temp);
                                }
                                
                                
                                Logic_Simulator threadItem = new Logic_Simulator(ItemxSimulationList, this.circuit, this.cellLibrary, this.levelCircuit, start, end, this.library, this.circuitFilePath); // Thread contex info
                                itemx_list.add(threadItem); // Itemx sorted's
                              
                             
                                
                           
                             
                             Runnable runnable = threadItem; // Logic_Simulator(listThreadItemx, this.circuit, cellLib, levelCircuit);
                             Thread thread = new Thread(runnable);
                             thread_list.add(thread);
                                
                           //  System.out.println("\n---------------------------------------");  
                             
                     }
                  
                    Thread thread_temp = null;// new Thread();
                    for (int i=0; i < thread_list.size() ; i++) {
                        thread_temp = (Thread) thread_list.get(i);
                        thread_temp.start();
                        
                    }
                    
                     for (int i=0; i < thread_list.size() ; i++) {
                        thread_temp = (Thread) thread_list.get(i);
                        thread_temp.join();
                    }
                     
                     
                    //this.processLogicSimMultiThreadingResultsRandom(thread_list, itemx_list, propagateTimeStart, N, formattedDate, testNumber);
                    
                    for (int i=0; i < itemx_list.size() ; i++) {

                            // System.out.println("SIgnal fault list : "+ this.SignalFault);
                           
                            this.unmasked_faults = this.unmasked_faults +  itemx_list.get(i).getPropagatedFaults();
                            
                           /// System.out.println("Thread: " + itemx_list.get(i).getThreadId() + " - Sample size: " + partition + " - Total Faults : "+  itemx_list.get(i).getPropagatedFaults());
                    }
               
                
                
                long propagateTimeEnd = System.nanoTime();
                long propagateTime =   TimeUnit.NANOSECONDS.toSeconds(propagateTimeEnd - propagateTimeStart);
                long propagateTimems =   TimeUnit.NANOSECONDS.toMillis(propagateTimeEnd - propagateTimeStart);
                
             
                LocalDateTime myDateObj2 = LocalDateTime.now();
                DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate2 = myDateObj2.format(myFormatObj2);
                
                System.out.println("- Simulation finished at: " + formattedDate2);
                System.out.println("- PropagatedTime (s): " + propagateTime);
                System.out.println("");
                
                //this.writeCsvFileTh("Multithreading_Log_fault_free_"+this.circuit.getName()+"_Theads-"+ this.thread +  "_TestNumber-"+ N , itemx_list);
                this.time_seconds = propagateTime;
                
                this.writeTxtLog("Multithreading_Simple_Log_" +this.circuit.getName()+"_Theads-"+ this.thread +  "_TestNumber-"+ N + "_round-"+id+" of " + interaction , formattedDate,  formattedDate2, thread_list.size(), propagateTime, N, propagateTimems);
               
                this.writeCsvFileCompleteTh("Multithreading_Complete_Log_"+this.circuit.getName()+"_Theads-"+ this.thread + "_TestNumber-"+ N + "_round-"+id+" of " + interaction , itemx_list);
               
                
                System.out.println("----------------- Results ------------------");
                System.out.println("Circuit: " + this.circuit.getName());
                System.out.println("Total Vectors (N): " + N);
                System.out.println("Propagated fault(s) (Ne): " + this.unmasked_faults);
                System.out.println("Reliability: " + "(1-(" + this.unmasked_faults + "/" + N + ")) = " + this.reliability_circuit);
                System.out.println("MTBF (Mean Time Between failure) : " + this.MTBF);
                System.out.println("Simulation TimeElapsed: " + propagateTime + " m(s)");
                System.out.println("--------------------------------------------");
     }
     
     public void MulltiThreading_Logic_Simulator(String library, String CircuitFile) throws IOException, Exception{
                
                long loadTimeStart = System.nanoTime();//System.currentTimeMillis();
                
                LocalDateTime myDateObj = LocalDateTime.now();
                //System.out.println("Before formatting: " + myDateObj);
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);
                System.out.println("- Simulation start in : " + formattedDate);
         
                /*Reading CellLibrary*/
                CellLibrary cellLib = new CellLibrary();
                this.cellLibrary = cellLib;
                this.cellLibrary.initLibrary(this.libraryPath);
                
                //System.out.println("--- Reading celllib ...");
                //System.out.println("  - Avaliable logic gatesin this library: "+cellLib.getCells());
                /*----------------------*/
                
                /*Reading verilog*/
                MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitFilePath, this.cellLibrary);
                this.verilog_circuit = verilog_circuit;
                /*Circuit linked to verilog_circuit - init circuit*/
                this.circuit = verilog_circuit.getCircuit();
                System.out.println("--- Reading verilog: " + this.verilog_circuit.getCircuit().getName() + ".v");
                //System.out.println("Patterns : " + this.verilog_circuit.getGatePattern());
                /*----------------------*/
                
                
                /* Print circuit Specs*/
                System.out.println("\n------ Printing Circuit Specs: --------");
                this.PrintSpecs();
                System.out.println("---------------------------------------\n");
                /*----------------------*/
                
                /*Circuit Probabilities */
                this.initLevelCircuit();
               
                /*Init ProbCircuits*/
                 this.initProbCircuit();
                
                /*Init PTMs Const*/
                cellLib.setPTMCells2(Float.valueOf(reliability));
                cellLib.setPTMCells(new BigDecimal(reliability));
               
                long loadTimeEnd = System.nanoTime();//System.currentTimeMillis();
              
                long loadTime =   TimeUnit.NANOSECONDS.toMillis(loadTimeEnd - loadTimeStart);
              
                System.out.println("- Load Time m(s): " + loadTime);
               
                System.out.println("\n------ Inserting the input vector --------");
                System.out.println("     - Circuit Input(s): "+this.probCircuit.getInputs());
                System.out.println("     - Circuit Output(s): "+this.probCircuit.getOutputs());
                
               
                int N = this.calculate_Number_Of_Input_Vector();  //(int) Math.pow(2, this.probCircuit.getInputs().size());
               
                System.out.println("Number total of input vectors : "+ N);
                
                this.internSignals = this.get_all_intermediate_signals(); // calc intern Signals
                
                ArrayList <String> raw_inputVectors =  this.calcInputTableVector(this.probCircuit.getInputs().size(), N);
 
                ArrayList <ArrayList<Integer>> ListInputVectors =  this.splitInputPatternsInInt(raw_inputVectors, this.probCircuit.getInputs().size());
                
                //this.simulator(library, CircuitFile); //Without threads
                
                
                List thread_list = new ArrayList();
                
                long propagateTimeStart = System.nanoTime();
                
                int partition;
                if(this.thread == 1){
                     partition = N; //final_pos/NThreads ;
                }
                else{
                    double temp;
                    temp = Math.floor(N/this.thread); 
                    //System.out.println("Division : "+ a);
                    partition =  (int) temp ;//(int) Math.round(collapsed_faults/NThreads); 
                }
                
                System.out.println("Partion : "+ partition);
                int start = 0;
                int end = partition;
                
                 ArrayList <Logic_Simulator> itemx_list = new ArrayList<>();
                 
                 //ArrayList <Integer, String> example = new ArrayList<>();
                
                for (int i = 0; i < this.thread; i++) { //Loop of simulations
                                //System.out.println("~~~~~~~~Thread: "+i);
                               
                                ArrayList <Test_Item> ItemxSimulationList = new ArrayList<>();
                    
                                ArrayList <Integer> inputVector = new ArrayList<>();
                                
                                for (int j = start; j < end ; j++) {
                                    
                                        inputVector = this.get_Input_Vectors(ListInputVectors, j); //input Test n
                                        //this.insertInputVector(cellLib, "selected", inputVector); //Depois na hora de inseriri vetor
                                        int SigIndex = this.randomInjectionFault();

                                        //System.out.println("index: "+(j+1) + "     -     vec: " + inputVector);

                                        Test_Item temp = new Test_Item(inputVector, this.internSignals.get(SigIndex), j+1);

                                        ItemxSimulationList.add(temp);
                                }
                             
                             Logic_Simulator threadItem = new Logic_Simulator(ItemxSimulationList, this.circuit, this.cellLibrary, this.levelCircuit, start, end, this.library, this.circuitFilePath); // Thread contex info
                             itemx_list.add(threadItem); // Itemx sorted's
                                
                                start = start +  partition;
                                if(start + partition <= N){
                                    end = start + partition;
                                }
                                
                            
                             
                             Runnable runnable = threadItem; // Logic_Simulator(listThreadItemx, this.circuit, cellLib, levelCircuit);
                             Thread thread = new Thread(runnable);
                             thread_list.add(thread);
                                
                             //System.out.println("\n---------------------------------------");  
                             
                     }
                  
                    Thread thread_temp = null;// new Thread();
                    for (int i=0; i < thread_list.size() ; i++) {
                        thread_temp = (Thread) thread_list.get(i);
                        thread_temp.start();
                        
                    }
                    
                     for (int i=0; i < thread_list.size() ; i++) {
                        thread_temp = (Thread) thread_list.get(i);
                        thread_temp.join();
                    }
                     
                     
                    this.processLogicSimMultiThreadingRsults(thread_list, itemx_list, propagateTimeStart, N, formattedDate);
                     
                    //int propagate_faults = 0;
                  
                   /* last version
                    for (int i=0; i < itemx_list.size() ; i++) {
                        
                            //System.out.println(thread_item_list.get(i).getResults());
                            this.inputListValuesStr.add(itemx_list.get(i).get_inputListValuesStr());
                           
                            
                            //System.out.println((i+1) + " - SAIDA FREE FAULT: " + thread_item_list.get(i).get_outputListValueStr());
                           /// System.out.println("         MAP thread:"+(i+1) + " - SAIDA FREE FAULT: " + thread_item_list.get(i).get_Map_fault_free());
                            
                            //Map<String, String> freeMap = new TreeMap<>(itemx_list.get(i).get_Map_fault_free());
                            Map<String, String> faultMap = new TreeMap<>(itemx_list.get(i).get_Map_fault_fault());
                            //thread_item_list.get(i).printMap(treeMap); 
                           // System.out.println("Tree: "+ treeMap);
                            //thread_item_list.get(i).get_Map_fault_free_str();
                           // ArrayList<String> fault_free = new ArrayList<>();
                            ArrayList<String> fault_inject = new ArrayList<>();
                            
                           //System.out.println("~~~~~~ Unsorted Free: "+freeMap);
                            
                           
                            freeMap.entrySet().forEach((entry) -> { 
                                fault_free.add(entry.getValue());
                            });
                            
                             faultMap.entrySet().forEach((entryx) -> { 
                                fault_inject.add(entryx.getValue());
                            });
                            
                             //System.out.println("~~~~~~ Free: "+freeMap);
                            
                            //System.out.println(" Thread("+ i +"): fault free list-> " + fault_free + "  fault injected -> " + fault_inject);
                            //this.outputFreeFaultListValueStr.add(fault_free); //mandei o fault free
                            this.outputFreeFaultListValueStr.add(itemx_list.get(i).getLinkedFaultFreeResults()); //faultfree
                            

                            //this.outputListValueStr.add(thread_item_list.get(i).get_outputListValueStr());
                            //this.outputListValueStr.add(fault_inject);
                            this.outputListValueStr.add(itemx_list.get(i).getLinkedFaultResults());
                            
                            ///this.SignalFault.add(itemx_list.get(i).get_signalFaultList());
                            this.SignalFault.add(itemx_list.get(i).get_signalFaultListCuncorrentLinked());
                            
                            this.SignalFaultBitFlip.add(itemx_list.get(i).get_signalFaultListBitflip());
                            
                            System.out.println("SIgnal fault list : "+ this.SignalFault);
                            //propagate_faults = propagate_faults + itemx_list.get(i).getPropagatedFaults();
                            this.unmasked_faults = this.unmasked_faults +  itemx_list.get(i).getPropagatedFaults();
                            
                            System.out.println(i + "Faults in : "+  itemx_list.get(i).getPropagatedFaults());
                    }
                    */
                    /*
                   System.out.println(""+this.inputListValuesStr);
                   System.out.println(""+this.outputFreeFaultListValueStr);
                   System.out.println(""+this.outputListValueStr);
                    */
                
                /*
                long propagateTimeEnd = System.nanoTime();
                long propagateTime =   TimeUnit.NANOSECONDS.toMillis(propagateTimeEnd - propagateTimeStart);
                
             
                LocalDateTime myDateObj2 = LocalDateTime.now();
                DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate2 = myDateObj2.format(myFormatObj2);
                
                System.out.println("- Simulation finished at: " + formattedDate2);
                System.out.println("- PropagatedTime m(s): " + propagateTime);
                System.out.println("");
                
                this.writeCsvFileTh("Multithreading_Log_fault_free_"+this.circuit.getName()+"_Theads-"+ this.thread);
                this.writeTxtLog("Multithreading_Simple_Log_" +this.circuit.getName()+"_Theads-"+ this.thread , formattedDate2, thread_list.size(), propagateTime, N);
                this.writeCsvFileCompleteTh("Multithreading_Complete_Log_"+this.circuit.getName()+"_Theads-"+ this.thread);
               
                System.out.println("Main: Porpagated fault -: "+this.unmasked_faults);

                */
          
     }
        
     public void Print_Circuit_Specs (String library, String CircuitFile) throws IOException, Exception{
                
                long loadTimeStart = System.nanoTime();//System.currentTimeMillis();
                
                LocalDateTime myDateObj = LocalDateTime.now();
                //System.out.println("Before formatting: " + myDateObj);
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);
                System.out.println("- Simulation start in : " + formattedDate);
         
                /*Reading CellLibrary*/
                CellLibrary cellLib = new CellLibrary();
                this.cellLibrary = cellLib;
                this.cellLibrary.initLibrary(this.libraryPath);
                
                System.out.println("--- Reading celllib ...");
                //System.out.println("  - Avaliable logic gatesin this library: "+cellLib.getCells());
                /*----------------------*/
                
                /*Reading verilog*/
                MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitFilePath, this.cellLibrary);
                this.verilog_circuit = verilog_circuit;
                /*Circuit linked to verilog_circuit - init circuit*/
                this.circuit = verilog_circuit.getCircuit();
                System.out.println("--- Reading verilog: " + this.verilog_circuit.getCircuit().getName() + ".v");
                //System.out.println("Patterns : " + this.verilog_circuit.getGatePattern());
                /*----------------------*/
                
                
                /* Print circuit Specs*/
                System.out.println("\n------ Printing Circuit Specs: --------");
                this.PrintSpecs();
                //int n = (int) Math.pow(2, this.circuit.getInputs().size());
                
               
               
                
                 
                /*Circuit Probabilities */
                this.initLevelCircuit();
               
                /*Init ProbCircuits*/
                 this.initProbCircuit();
                
                /*Init PTMs Const*/
                cellLib.setPTMCells2(Float.valueOf(reliability));
                cellLib.setPTMCells(new BigDecimal(reliability));
               
                long loadTimeEnd = System.nanoTime();//System.currentTimeMillis();
              
                long loadTime =   TimeUnit.NANOSECONDS.toMillis(loadTimeEnd - loadTimeStart);
              
                
                
               
                 int N = this.calculate_Number_Of_Input_Vector();  //(int) Math.pow(2, this.probCircuit.getInputs().size());
                 this.internSignals = this.get_all_intermediate_signals();
                 
                 System.out.println("Total Signals: " + this.circuit.getSignals().size()); 
                 System.out.println("Total input vectors: 2^" + this.circuit.getInputs().size());
                 System.out.println("Intern Signals: " + this.internSignals.size()); 
                 System.out.println("Total outputs: " + this.circuit.getOutputs().size());
                 System.out.println("Total Logic Levels: " + this.probCircuit.getProbGateLevels().size());
                 System.out.println("---------------------------------------\n");
                 
                /*----------------------*/
                
             
                    //int propagate_faults = 0;
                  
                   /* last version
                    for (int i=0; i < itemx_list.size() ; i++) {
                        
                            //System.out.println(thread_item_list.get(i).getResults());
                            this.inputListValuesStr.add(itemx_list.get(i).get_inputListValuesStr());
                           
                            
                            //System.out.println((i+1) + " - SAIDA FREE FAULT: " + thread_item_list.get(i).get_outputListValueStr());
                           /// System.out.println("         MAP thread:"+(i+1) + " - SAIDA FREE FAULT: " + thread_item_list.get(i).get_Map_fault_free());
                            
                            //Map<String, String> freeMap = new TreeMap<>(itemx_list.get(i).get_Map_fault_free());
                            Map<String, String> faultMap = new TreeMap<>(itemx_list.get(i).get_Map_fault_fault());
                            //thread_item_list.get(i).printMap(treeMap); 
                           // System.out.println("Tree: "+ treeMap);
                            //thread_item_list.get(i).get_Map_fault_free_str();
                           // ArrayList<String> fault_free = new ArrayList<>();
                            ArrayList<String> fault_inject = new ArrayList<>();
                            
                           //System.out.println("~~~~~~ Unsorted Free: "+freeMap);
                            
                           
                            freeMap.entrySet().forEach((entry) -> { 
                                fault_free.add(entry.getValue());
                            });
                            
                             faultMap.entrySet().forEach((entryx) -> { 
                                fault_inject.add(entryx.getValue());
                            });
                            
                             //System.out.println("~~~~~~ Free: "+freeMap);
                            
                            //System.out.println(" Thread("+ i +"): fault free list-> " + fault_free + "  fault injected -> " + fault_inject);
                            //this.outputFreeFaultListValueStr.add(fault_free); //mandei o fault free
                            this.outputFreeFaultListValueStr.add(itemx_list.get(i).getLinkedFaultFreeResults()); //faultfree
                            

                            //this.outputListValueStr.add(thread_item_list.get(i).get_outputListValueStr());
                            //this.outputListValueStr.add(fault_inject);
                            this.outputListValueStr.add(itemx_list.get(i).getLinkedFaultResults());
                            
                            ///this.SignalFault.add(itemx_list.get(i).get_signalFaultList());
                            this.SignalFault.add(itemx_list.get(i).get_signalFaultListCuncorrentLinked());
                            
                            this.SignalFaultBitFlip.add(itemx_list.get(i).get_signalFaultListBitflip());
                            
                            System.out.println("SIgnal fault list : "+ this.SignalFault);
                            //propagate_faults = propagate_faults + itemx_list.get(i).getPropagatedFaults();
                            this.unmasked_faults = this.unmasked_faults +  itemx_list.get(i).getPropagatedFaults();
                            
                            System.out.println(i + "Faults in : "+  itemx_list.get(i).getPropagatedFaults());
                    }
                    */
                    /*
                   System.out.println(""+this.inputListValuesStr);
                   System.out.println(""+this.outputFreeFaultListValueStr);
                   System.out.println(""+this.outputListValueStr);
                    */
                
                /*
                long propagateTimeEnd = System.nanoTime();
                long propagateTime =   TimeUnit.NANOSECONDS.toMillis(propagateTimeEnd - propagateTimeStart);
                
             
                LocalDateTime myDateObj2 = LocalDateTime.now();
                DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate2 = myDateObj2.format(myFormatObj2);
                
                System.out.println("- Simulation finished at: " + formattedDate2);
                System.out.println("- PropagatedTime m(s): " + propagateTime);
                System.out.println("");
                
                this.writeCsvFileTh("Multithreading_Log_fault_free_"+this.circuit.getName()+"_Theads-"+ this.thread);
                this.writeTxtLog("Multithreading_Simple_Log_" +this.circuit.getName()+"_Theads-"+ this.thread , formattedDate2, thread_list.size(), propagateTime, N);
                this.writeCsvFileCompleteTh("Multithreading_Complete_Log_"+this.circuit.getName()+"_Theads-"+ this.thread);
               
                System.out.println("Main: Porpagated fault -: "+this.unmasked_faults);

                */
          
     }
        

}
