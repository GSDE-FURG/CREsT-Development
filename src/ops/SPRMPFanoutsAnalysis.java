package ops;
import tool.Terminal;

import writers.WriteExcel;
import datastructures.CellLibrary;
import datastructures.Circuit;
import datastructures.ItemX;
import datastructures.Signal;
import java.io.FileWriter;
import java.math.BigDecimal;
import static java.nio.file.Files.list;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.list;
import java.util.Hashtable;
import java.util.List;
import javax.swing.SpringLayout;
import levelDatastructures.DepthGate;
import levelDatastructures.GateLevel;
import levelDatastructures.LevelCircuit;
import readers.MappedVerilogReader;
import signalProbability.ProbCircuit;
import signalProbability.ProbSignal;


/** Main method to run this class
 * @author Clayton Farias
 * @version 1.0
 * @since Dev
*/
class main_SPRMP_Exec {

    public static void main(final String[] args) throws Exception {
       
        String circuitPath ="c432_cadence.v" ;//"c17v3_fritz.v";//"c17_cadence.v";
        String reliability = "0.9999";
        String mode = "big_decimal";
        long timeout = 3; //seconds
        //CommonOps commonOps = new CommonOps();
        String library = "cadence.genlib";
        
        /*
        SPRMPFanoutsAnalysis spr_mp = new SPRMPFanoutsAnalysis(reliability, mode, circuitPath, library);
        spr_mp.SPR_MP();
        
        SPRMPFanoutsAnalysis spr = new SPRMPFanoutsAnalysis(reliability, mode, circuitPath, library);
        spr.SPR_BigDecimal();
        
        SPRMPFanoutsAnalysis ptm = new SPRMPFanoutsAnalysis(reliability, mode, circuitPath, library);
        ptm.PTM();
        
        */
       
        System.out.println("Timeout : " + timeout);
        SPRMPFanoutsAnalysis spr_mp_analysis = new SPRMPFanoutsAnalysis(reliability, mode, circuitPath, library);
        spr_mp_analysis.SPR_MP_FANOUTS(timeout, library, circuitPath);
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
    private long timeout;
    private long timeoutMiliSeconds;
    private long spr_timeConsumption;
    //private final Hashtable<Integer, BigDecimal , BigDecimal> resultTable; // idx fanout, MTBF, Time
    
    
    private void setSPRTimeConsumption(long time){
        this.spr_timeConsumption = time;
    }
    public long getSPRTimeConsumption(){
        //System.out.println("TESTE : "+ this.spr_timeConsumption);
        return (this.spr_timeConsumption);
    }
    
    public SPRMPFanoutsAnalysis(String reliabilty, String mode, String circuitFilePath, String genlib) {
        

        this.reliability = reliabilty;
        this.circuitFilePath = "abc\\" + circuitFilePath;
        this.mode = mode;
        this.genlib = genlib;
        this.genlibPATH = "abc\\" + this.genlib;
        this.cellLibrary = new CellLibrary();
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
         System.out.println("Fanouts (" + indxFanouts  + ")");
        final long startTime = System.currentTimeMillis();
        
 
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
          
  
          BigDecimal spr_result = SPROps.getSPRReliability(probCircuit);
          
          final long endTime = System.currentTimeMillis();
          
          //String timeConsup =  Long.toString((endTime - startTime));
          
          this.setSPRTimeConsumption(endTime - startTime);
          
          System.out.println("- SPR Bigdecimal Reliability : " + spr_result + " Time: " +(endTime - startTime) + " (ms)");
         
          System.out.println(" =================================== END SPR ================= ");         
          
          return spr_result;
        
    } 
    
     public void SPR()throws Exception {

        System.out.println("\n ===== SPR Big Decimal File: " + this.circuitFilePath + "   - Precision : "  + this.reliability + " ===== ");
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
          
          
          //System.out.println("This cells : "+ this.cellLibrary);
                  
  
          BigDecimal spr_result = SPROps.getSPRReliability(probCircuit);
          
          final long endTime = System.currentTimeMillis();
          
          String timeConsup =  Long.toString((endTime - startTime)) + " ms";
          
          System.out.println("- SPR Bigdecimal Reliability : " + spr_result + " Time(s) : " +timeConsup);
         
          System.out.println(" =================================== END SPR ================= ");
          
         
          //System.out.println("- SPRMP Reliability : " + SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(this.probCircuit));
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
        
     public void SPR_MP_FANOUTS(long timeout, String library, String CircuitFile)throws Exception {

        
        this.timeout = timeout;
        this.timeoutMiliSeconds = timeout * 1000;
        int idx=0;
        final long startTime;
        ItemX t;
        List<ItemX> resultTable = new ArrayList<>();   
        SPRMPFanoutsAnalysis spr = new SPRMPFanoutsAnalysis(this.reliability, this.mode, CircuitFile, library);
          
        System.out.println("\n DEBUGGING CODE ANALYSIS OF FANOUTS IN SPR-MP  File: " + this.circuitFilePath + "   - Precision : "  + this.reliability + " ===== ");
        
        startTime = System.currentTimeMillis();
        
        t = new ItemX (0 , spr.getSPRBigDecimal(idx), spr.getSPRTimeConsumption()); 
        resultTable.add(t);
          
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
          probCircuit.setPTMReliabilityMatrix(); //Sempre usar variaveis criadas 
          // taking off probCircuit.setProbSignalStates(false); //novo 
          probCircuit.setDefaultProbSourceSignalMatrix();
          probCircuit.setProbSignalStates(false); //HERE
          
         System.out.println("Running SPR-MP with the timeout: " +(this.timeoutMiliSeconds) + " (ms)");
         System.out.println("Total of Fanouts (n) : " + this.probCircuit.getFanouts().size());
         /*limitar número de fanouts*/
         System.out.println("\nFanout(n)        Reliability        Time(ms)        "); 
        
        long endTime = System.currentTimeMillis();
        for (int i = 1; i <= this.probCircuit.getFanouts().size(); i++) {
               
                final long timeConsup = (endTime - startTime);
              //final long timeConsup = (endTime - startTime)/1000;
              //String timeConsup =  Long.toString((endTime - startTime)) + " ms"; 
              
              if((timeConsup) <=  (this.timeoutMiliSeconds)){
                  
                      BigDecimal sprmp_result = SPRMultiPassV3Ops.getPRMultiPassReliaiblityByLimitedFanouts(probCircuit, i);
                      long endTimeInside = System.currentTimeMillis();
                      
                      //final long insideTime = (endTimeInside - startTime)/1000; //seconds
                      final long insideTime = (endTimeInside - startTime); //seconds
                      
                      System.out.println(i  + "        " + sprmp_result + "        " + insideTime);
                      endTime = endTimeInside;
                      
                      if(insideTime <= (this.timeoutMiliSeconds)){
                        ItemX item = new ItemX(i, sprmp_result, insideTime);
                        resultTable.add(item);
                        idx = i;
                      }
              }else{
                  break;
              }
         }
         for (int i = 0; i <= idx; i++){
             System.out.println(resultTable.get(i).PrintItemx());
         }
            
         
          System.out.println(" =================================== END SPR-MP ================= \n");
          
           WriteExcel resultFile = new WriteExcel(this.circuit.getName() + "-" + this.timeout + "(s)", "Resultado", Long.toString(this.timeoutMiliSeconds) , resultTable, idx);
          
           resultFile.write();
            
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
