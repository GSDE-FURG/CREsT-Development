package ops;

import writers.WriteExcel;
import datastructures.CellLibrary;
import datastructures.Circuit;
import datastructures.ItemX;
import datastructures.Signal;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;
import levelDatastructures.DepthGate;
import levelDatastructures.GateLevel;
import levelDatastructures.LevelCircuit;
import readers.MappedVerilogReader;
import signalProbability.ProbCircuit;


/** Main method to run this class
 * @author Clayton Farias
 * @version 1.0
 * @since Dev
*/
class main_SPRMP_Exec {

    public static void main(final String[] args) throws Exception {
        
         Vector <Integer> TimeoutList = new Vector();
       
        //Read Inputs Circuit, Reliability, Genlib//
        String circuitPath = "c432_cadence.v" ;//"c17v3_fritz.v";//"c17_cadence.v";
        String reliability = "0.9999";
        String mode = "big_decimal";
        String library = "cadence.genlib";
         
     
        /*Timeout List*/
        TimeoutList.add(10); //Segundos - 1m
        //TimeoutList.add(600); // - 10m
        //TimeoutList.add(3600);//1h
        
       
        for (int i = 0; i < TimeoutList.size(); i++) {
            
            System.out.println(TimeoutList.get(i));
            long timeout = TimeoutList.get(i);
            System.out.println(i + "  -  Timeout : " + timeout + "(s)");
                SPRMPFanoutsAnalysis spr_mp_analysis = new SPRMPFanoutsAnalysis(reliability, timeout ,mode, circuitPath, library);
                spr_mp_analysis.SPR_MP_FANOUTS(library, circuitPath);
            System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
            
        }
        
    }
 
}

/** This class implements SPRMP fanouts analysis and provides a set of reliability methods (that can be callable) such as PTM, SPR, and SPR-MP
 * @author Clayton Farias
 * @version 1.0
 * @since Dev
*/
public class SPRMPFanoutsAnalysis {
  

    private final String reliability;
    private final String mode;
    private final String circuitFilePath;
    private final String genlib;
    private final String genlibPATH;
    
    private CellLibrary cellLibrary;
    private Circuit circuit;
    private LevelCircuit levelCircuit;
    private ProbCircuit probCircuit;
    private LevelCircuit lCircuit;
    private final long timeout;
    private final long timeoutMiliSeconds;
    private long spr_timeConsumption;
  
    
    public long getSPRTimeConsumption(){
        //System.out.println("TESTE : "+ this.spr_timeConsumption);
        return this.spr_timeConsumption;
    }
    
    publi
    
    public SPRMPFanoutsAnalysis(String reliabilty,long Timeout, String mode, String circuitFilePath, String genlib) {
        

        this.reliability = reliabilty;
        this.circuitFilePath = "abc\\" + circuitFilePath;
        this.mode = mode;
        this.genlib = genlib;
        this.genlibPATH = "abc\\" + this.genlib;
        this.cellLibrary = new CellLibrary();
        
        this.timeout = Timeout;
        this.timeoutMiliSeconds = this.timeout * 1000;
        //this.resultTable = new Hashtable<Integer, BigDecimal>();
        

    }
    
     public static void matrixPrint(BigDecimal[][] matrix) {
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
     
     public void PrintSpecs(){
         System.out.println("Circuits : " + this.circuit.getName());
         System.out.println("Gates : " + this.circuit.getGates());
         System.out.println("Inputs : " + this.circuit.getInputs());
         System.out.println("Outputs : " + this.circuit.getOutputs());
         System.out.println("Signals : " + this.circuit.getSignals());
        
     }
     
     public void PrintGateLevels() {
        LevelCircuit lcirc = this.lCircuit;
        
        String output = "";
        
        for (int i = 0; i < lcirc.getGateLevels().size(); i++) {                        
            GateLevel gLevel = lcirc.getGateLevels().get(i);
   
            output = output + "###########################\n";        
            output = output + " ## GATE LEVEL " + gLevel.getLevel() + " ##\n"; 
            output = output + "###########################\n";        
                        
            for (int j = 0; j < gLevel.getGates().size(); j++) {
                Object gate = gLevel.getGates().get(j);
                if(gate instanceof DepthGate) {
                    DepthGate depthGate = (DepthGate)gate;
                    output = output + (depthGate.toString() + " --> " + depthGate.getGate().getType() + "\n");
                    output = output + "## ## ##\n";
                } else {
                    Signal signal = (Signal)gate;
                    output = output + (signal.toString() + " --> WIRE!!!\n");                    
                    output = output + "## ## ##\n";
                }
            }                               
        } 
        
         System.out.println("PrintGateLevels : "+output);
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

        System.out.println("\n ===== SPR Big Decimal File: " + this.circuitFilePath + "   - Precision : "  + this.reliability + " ===== ");
         //System.out.println("Fanouts (" + indxFanouts  + ")");
        
        
 
        /*CellLibrary*/
        CellLibrary cellLib = new CellLibrary();
        cellLib.initLibrary(this.genlibPATH);
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
          
          
          final long sprStartTIme = System.currentTimeMillis();
          
            BigDecimal spr_result = SPROps.getSPRReliability(this.probCircuit);
          
          final long sprEndTime = System.currentTimeMillis();
          
            this.spr_timeConsumption = (sprEndTime - sprStartTIme);
          
        
          System.out.println("- SPR Bigdecimal Reliability : " + spr_result + " calc Time: " +(this.getSPRTimeConsumption()) + " (ms)");
          
          //System.out.println("- SPR Bigdecimal Reliability : " + spr_result + " PauloTime: " +(endTime - ) + " (ms)");
          
          System.out.println(" =================================== END SPR ================= \n");         
          
          return spr_result;
        
    } 
     
     public void SPR_MP()throws Exception {

        System.out.println("\n ===== SPR-MP File: " + this.circuitFilePath + "   - Precision : "  + this.reliability + " ===== ");
        final long startTime = System.currentTimeMillis();
        
 
        /*CellLibrary*/
        CellLibrary cellLib = new CellLibrary();
        cellLib.initLibrary(this.genlibPATH);
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
          
          //CellLib
          this.cellLibrary = cellLib;
          
          //ProbCircuit
          probCircuit.setPTMReliabilityMatrix(); //Sempre usar variaveis criadas 
          // taking off probCircuit.setProbSignalStates(false); //novo 
          probCircuit.setDefaultProbSourceSignalMatrix();
          probCircuit.setProbSignalStates(false); //HEREn
          
          
          //System.out.println("This cells : "+ this.cellLibrary.getClass());
                  
          //SPR-MP info estipulation"
          //SPRMultiPassV3Ops.getTotalPasses(probCircuit);
          //SPRMultiPassV3Ops.getSPRMPPerState(probCircuit);
          
          
          
         
          
           BigDecimal sprmp_result = SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(probCircuit);
           
            final long endTime = System.currentTimeMillis();
                   
           String timeConsup =  Long.toString((endTime - startTime)) + " ms";
           
           System.out.println("SPR-MP Reliability : " + sprmp_result + " - Time (s) : " + timeConsup);
          //String timeConsup =  Long.toString((endTime - startTime)) + " ms";
        
          System.out.println(" =================================== END SPR-MP ================= ");
    } 
        
     public void SPR_MP_FANOUTS(String library, String CircuitFile)throws Exception {
        
         int idx = 0;
        
       
        ItemX t;
        List<ItemX> resultTable = new ArrayList<>();   
        
        
        /*SPR Call*/
        SPRMPFanoutsAnalysis spr = new SPRMPFanoutsAnalysis(this.reliability, this.timeout, this.mode, CircuitFile, library);
        
        /*Time SPR*/
        //long sprStartMethodTime = System.currentTimeMillis();
         
            BigDecimal spr_result = spr.getSPRBigDecimal(idx);
        
       // long sprEndMethodTime = System.currentTimeMillis();
          
        //-------- ----------- --------- ----------- -------------
        // System.out.println("       ~   SPR Method Time: " + (sprEndMethodTime - sprStartMethodTime) + "m(s)");
        
            t = new ItemX (0 ,spr_result, spr.getSPRTimeConsumption()); 
            resultTable.add(t);
        
         //long PauloTime2 = System.currentTimeMillis();
          
         
        long loadTimeStart = System.currentTimeMillis();
         
        /*CellLibrary*/
        CellLibrary cellLib = new CellLibrary();
        cellLib.initLibrary(this.genlibPATH);
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
          
          long load_TimeEnd = System.currentTimeMillis();
          
          System.out.println("- Time to loag Genlib, Circuit (verilog), and settings probabilities: " + (load_TimeEnd - loadTimeStart) + " m(s)");
          
         
        
         long startTime = System.currentTimeMillis();
         
         long executionTimeGlobal = System.currentTimeMillis();
         
         long timeoutsideIF = (executionTimeGlobal - startTime);
         
         long nFanoutsTime = 0;
         
         //System.out.println("- Running SPR-MP with the timeout: " +(this.timeoutMiliSeconds) + " (ms)");
         System.out.println("- Total of Fanouts (n) : " + this.probCircuit.getFanouts().size() + "    Timeout: " + this.timeout + "(s)");
         System.out.println("\n- Fanout(n)        Reliability        Time(ms)           Time(s)"); 
          
            for (int i = 1; i <= this.probCircuit.getFanouts().size(); i++){ //Fluxogram TimeExecution (ms)
               
   
              if((timeoutsideIF) <=  (this.timeoutMiliSeconds) && (nFanoutsTime <= this.timeoutMiliSeconds)){ //(m(s)
                      
                      long sprmpStartTime = System.currentTimeMillis();
                        BigDecimal sprmp_result = SPRMultiPassV3Ops.getPRMultiPassReliaiblityByLimitedFanouts(this.probCircuit, i);
                      long endTimeInside = System.currentTimeMillis();
                      
                      long sprmpTime = (endTimeInside - sprmpStartTime); //Rodada SPR

                      nFanoutsTime = nFanoutsTime + sprmpTime; // Final - inicio
                      
                      System.out.println(i  + "        " + sprmp_result + "       (Round): " + sprmpTime + " m(s)     ~   " + " (Global): " + nFanoutsTime + " m(s) " + nFanoutsTime/1000 + " (s)");
                     
      
                      if(nFanoutsTime <= (this.timeoutMiliSeconds)){
                        
                        ItemX item = new ItemX(i, sprmp_result, nFanoutsTime);
                        resultTable.add(item);
                        idx = i;
                        
                        if(nFanoutsTime * 1.7 > this.timeoutMiliSeconds){ //preempção
                            System.out.println("Stoping the execution, expected time ~ (" + nFanoutsTime * 2 + ") m(s) above the Timeout (~2x), Timeout : " + this.timeoutMiliSeconds + " ~ " + this.timeout  + "(s)");
                            i = (this.probCircuit.getFanouts().size()) + 11;
                            break;
                        }
                        
                       
                      }
                      
                      
                       
                      
              }else{
                  break;
              }
         }
         System.out.println("\n");
         for (int i = 0; i <= idx; i++){
             System.out.println(resultTable.get(i).PrintItemx());
         }
            
         
          System.out.println(" =================================== END SPR-MP ================= \n");
          
          //System.out.println("TimeOut-" + this.timeout +"(s)");
          
           WriteExcel resultFile = new WriteExcel(this.circuit.getName() + "-" + this.timeout + "(s)"  , "TimeOut-" + this.timeout +"(s)"    , Long.toString(this.timeoutMiliSeconds) , resultTable, idx);
          
           resultFile.write();
           
           resultFile.writeCSV();
            
     } 
   
     public void PTM() throws Exception {

         System.out.println(" ===== PTM File: " + this.circuitFilePath + "   - Precision : "  + this.reliability + " ===== ");
        final long startTime = System.currentTimeMillis();
        
 
        /*CellLibrary*/
        CellLibrary cellLib = new CellLibrary();
        cellLib.initLibrary(this.genlibPATH);
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
}
