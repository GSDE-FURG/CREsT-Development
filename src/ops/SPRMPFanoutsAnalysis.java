package ops;

import writers.WriteExcel;
import datastructures.CellLibrary;
import datastructures.Circuit;
import datastructures.ItemX;
import datastructures.Signal;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.script.ScriptException;
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
       
        String circuitPath = "c432_cadence.v" ;//"c17v3_fritz.v";//"c17_cadence.v";
        String reliability = "0.9999";
        String library = "cadence.genlib";
        
        
        Vector <Integer> InterationTimeList = new Vector();
        InterationTimeList.add(10); //in seconds
        InterationTimeList.add(60); //Segundos - 1m
        
        SPRMPFanoutsAnalysis Analisys_SPR_SPRMP = new SPRMPFanoutsAnalysis(reliability, circuitPath, library);
        
         /*Read genlib*/
        Analisys_SPR_SPRMP.ReadGenlib();
    
        /*Read Circuit*/
        Analisys_SPR_SPRMP.ReadCircuit();
        
        /*Method SPR*/
        ItemX SPR = new ItemX(0, Analisys_SPR_SPRMP.getSPRBigDecimal(), Analisys_SPR_SPRMP.getSPRTimeConsumption());
            //System.out.println(SPR.PrintItemx());
            
        /*method SPR-MP*/
        Analisys_SPR_SPRMP.SPRMP_FANOUTS(10, library, circuitPath, SPR);
            
       
        
        /*
        SPRMPFanoutsAnalysis Analisys_SPR_SPRMP = new SPRMPFanoutsAnalysis(reliability, circuitPath, library);
        Analisys_SPR_SPRMP.FanoutsAnalysis(InterationTimeList);
        
         for (int i = 0; i < TimeoutList.size(); i++) {
            
            System.out.println(TimeoutList.get(i));
            long timeOutX = TimeoutList.get(i);
            System.out.println(i + "  -  Timeout : " +  timeOutX + "(s)");
            SPRMPFanoutsAnalysis spr_mp_analysis = new SPRMPFanoutsAnalysis(this.reliability,  this.circuitFilePathOriginal, this.genlib);
            spr_mp_analysis.SPR_MP_FANOUTS(timeOutX , this.genlib, this.circuitFilePathOriginal);
            System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
            
        }
        */
        
    }
    
    
}

/** This class implements SPRMP fanouts analysis and provides a set of reliability methods (that can be callable) such as PTM, SPR, and SPR-MP
 * @author Clayton Farias
 * @version 1.1
 * @since Dev
*/
public class SPRMPFanoutsAnalysis {
  

    private final String reliability;
    private final String mode;
    private final String circuitFilePath;
    private final String circuitFilePathOriginal;
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
    
    public SPRMPFanoutsAnalysis(String reliabilty, String circuitFilePath, String genlib) {
        

        this.reliability = reliabilty;
        this.circuitFilePath = "abc\\" + circuitFilePath;
        this.circuitFilePathOriginal =  circuitFilePath;
        this.genlib = genlib;
        this.genlibPATH = "abc\\" + this.genlib;
        this.mode = "";
        this.cellLibrary = new CellLibrary();
        
    }
    
    protected void ReadGenlib() throws IOException, ScriptException{
            /*CellLibrary*/
            CellLibrary cellLib = new CellLibrary();
            cellLib.initLibrary(this.genlibPATH);
            this.cellLibrary = cellLib;
    }
    
    protected void ReadCircuit() throws Exception{
            /*Read Verilog*/
            MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitFilePath, this.cellLibrary);
            this.circuit = verilog_circuit.getCircuit();
    }
    
    protected void InitCircuitProbabilities() throws Exception{
            /*Circuit Probabilities */
            this.initLevelCircuit();
            this.initProbCircuit();
    }
     
    protected ItemX spr() throws Exception{
        ItemX sprObject;
        
        /*chamada do método*/
        System.out.println("\n SPR : DEBUGGING CODE ANALYSIS OF FANOUTS IN SPR-MP  File: " + this.circuitFilePath + "   - Precision : "  + this.reliability + " ===== ");
         
        SPRMPFanoutsAnalysis spr = new SPRMPFanoutsAnalysis(this.reliability, this.circuitFilePathOriginal, this.genlib);
        
        sprObject = new ItemX (0 , spr.getSPRBigDecimal(), spr.getSPRTimeConsumption()); 
        
        return sprObject;
        
       
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
     
     protected BigDecimal getSPRBigDecimal()throws Exception {
         
        //final long startTimeMethod = System.currentTimeMillis();
        
        System.out.println("\n          ===== SPR File: " + this.circuitFilePath + "   - Precision : "  + this.reliability + " ===== ");
       
         
        /*CellLibrary*/
        CellLibrary cellLib = new CellLibrary();
        cellLib.initLibrary(this.genlibPATH);
        
        
         /*Read Verilog*/
        MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitFilePath, cellLib);
        
        
        
         /*Circuit linked to verilog_circuit - init circuit*/
         this.circuit = verilog_circuit.getCircuit();
            //this.PrintSpecs();
     
       
        
         /*Circuit Probabilities */
         this.initLevelCircuit();
         this.initProbCircuit();
          /*Circuit Probabilities */
          
          
          /*Gate levels*/
            //this.PrintGateLevels();
         
        
          
          //System.out.println("Level Circuit : "+ this.levelCircuit);
  
          cellLib.setPTMCells2(Float.valueOf(reliability));
          cellLib.setPTMCells(new BigDecimal(reliability));
          //cellLib.teste();
          
          //CellLib
          this.cellLibrary = cellLib;
          
          //ProbCircuit
          probCircuit.setPTMReliabilityMatrix(); //Sempre usar variaveis criadas 
         
          probCircuit.setDefaultProbSourceSignalMatrix();
          
          final long sprStartTime = System.currentTimeMillis();
          
            BigDecimal spr_result = SPROps.getSPRReliability(probCircuit);
          
          final long sprEndTime = System.currentTimeMillis();
          
          this.setSPRTimeConsumption(sprEndTime - sprStartTime);
          
          System.out.println("                  - SPR Bigdecimal Reliability : " + spr_result + " TimeMethod: " +(sprEndTime - sprStartTime) + " (ms)");
          
          //System.out.println("                  - Complete time to run spr : " +(sprEndTime - startTimeMethod) + " (ms)");
          
          System.out.println("          ===== SPR END ================= ");         
          
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
    
      public void SPRMP_FANOUTS(long timeout, String library, String CircuitFile, ItemX sprItem)throws Exception {
        
        System.out.println("\n          ===== SPRMP File: " + this.circuitFilePath + "   - Precision : "  + this.reliability + " ===== ");
         
        List<ItemX> resultTable = new ArrayList<>();    
        this.timeout = timeout;
        this.timeoutMiliSeconds = timeout * 1000;
        int idx=0;
        
        
        resultTable.add(sprItem);       
        
        long startTimeMethod = System.currentTimeMillis();
          
        /*CellLibrary*/
        CellLibrary cellLib = new CellLibrary();
        cellLib.initLibrary(this.genlibPATH);
        
        
         /*Read Verilog*/
        MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitFilePath, cellLib);
        
        
        /*Circuit linked to verilog_circuit - init circuit*/
         this.circuit = verilog_circuit.getCircuit();
         
         //this.PrintSpecs();
         /*Circuit linked to verilog_circuit*/
       
        
         /*Circuit Probabilities */
         this.initLevelCircuit();
         this.initProbCircuit();
         

          /*Gate levels*/
            //this.PrintGateLevels();
         

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
          
         System.out.println("       Running SPR-MP with the timeout: " +(this.timeoutMiliSeconds) + " (ms)");
         System.out.println("       Total of Fanouts (n) : " + this.probCircuit.getFanouts().size() + "    Timeout: " + this.timeout + "(s)");
         
         /*limitar número de fanouts*/
         System.out.println("\nFanout(n)        Reliability        Time(ms)           Time(s)"); 
        
        
         long startTime = System.currentTimeMillis();
         
         long endTime = System.currentTimeMillis();
         
        // long PauloTime3 = System.currentTimeMillis();
         
         System.out.println(" Load genlib and Circuits : " + (endTime - startTimeMethod) + "m(s)  ~   " + (endTime-startTimeMethod)/1000  + " (s)");
          
            for (int i = 1; i <= this.probCircuit.getFanouts().size(); i++){ //Fluxogram TimeExecution (ms)
               
                final long timeConsup = (endTime - startTime);
                //final long timeConsup = (endTime - startTime)/1000;
                //String timeConsup =  Long.toString((endTime - startTime)) + " ms"; 
              
              if((timeConsup) <=  (this.timeoutMiliSeconds)){ //(m(s)
                      
                      BigDecimal sprmp_result = SPRMultiPassV3Ops.getPRMultiPassReliaiblityByLimitedFanouts(probCircuit, i);
                      long endTimeInside = System.currentTimeMillis();
                      
                      
                      final long insideTime = (endTimeInside - startTime); //seconds
                      
                      System.out.println(i  + "        " + sprmp_result + "              " + insideTime + "           ~         " + insideTime/1000 + " (s)");
                      
                      endTime = endTimeInside;
                      
                      if(insideTime <= (this.timeoutMiliSeconds)){
                        ItemX item = new ItemX(i, sprmp_result, insideTime);
                        resultTable.add(item);
                        idx = i;
                        
                        if(insideTime * 2.1 > this.timeoutMiliSeconds){
                            System.out.println("\nX - Stoping the execution, expected time " + this.timeoutMiliSeconds*2 + " above the timelimit (~2x) : " + this.timeoutMiliSeconds + " ~ " + this.timeout  + "(s)");
                            i = (this.probCircuit.getFanouts().size()) + 11;
                            break;
                        }
                      }
              }else{
                  break;
              }
         }
         for (int i = 0; i <= idx; i++){
             System.out.println(resultTable.get(i).PrintItemx());
         }
            
          System.out.println(" ==== SPR-MP ================= \n");
          
           WriteExcel resultFile = new WriteExcel(this.circuit.getName() + "-" + this.timeout + "(s)"  , "TimeOut-" + this.timeout +"(s)"    , Long.toString(this.timeoutMiliSeconds) , resultTable, idx);
          
           resultFile.write();
           
            
     } 
     
     public void SPR_MP_FANOUTS(long timeout, String library, String CircuitFile)throws Exception {
        
        System.out.println("\n DEBUGGING CODE ANALYSIS OF FANOUTS IN SPR-MP  File: " + this.circuitFilePath + "   - Precision : "  + this.reliability + " ===== ");
        this.timeout = timeout;
        this.timeoutMiliSeconds = timeout * 1000;
        int idx=0;
       
        
        List<ItemX> resultTable = new ArrayList<>();   
        
        
        SPRMPFanoutsAnalysis spr = new SPRMPFanoutsAnalysis(this.reliability, CircuitFile, library);
        ItemX t;
        t = new ItemX (0 , spr.getSPRBigDecimal(), spr.getSPRTimeConsumption()); 
        //AQUI
        
        
        resultTable.add(t);
        
        
        long PauloTime2 = System.currentTimeMillis();
          
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
         System.out.println("Total of Fanouts (n) : " + this.probCircuit.getFanouts().size() + "    Timeout: " + this.timeout + "(s)");
         /*limitar número de fanouts*/
         System.out.println("\nFanout(n)        Reliability        Time(ms)           Time(s)"); 
        
         //System.out.println("Number of fanouts : " + this.probCircuit.getFanouts().size());
         long startTime = System.currentTimeMillis();
         long endTime = System.currentTimeMillis();
         
         long PauloTime3 = System.currentTimeMillis();
         
         
         System.out.println("PauloTime : " + (PauloTime3 - PauloTime2));
          
            for (int i = 1; i <= this.probCircuit.getFanouts().size(); i++){ //Fluxogram TimeExecution (ms)
               
                final long timeConsup = (endTime - startTime);
                //final long timeConsup = (endTime - startTime)/1000;
                //String timeConsup =  Long.toString((endTime - startTime)) + " ms"; 
              
              if((timeConsup) <=  (this.timeoutMiliSeconds)){ //(m(s)
                      
                      BigDecimal sprmp_result = SPRMultiPassV3Ops.getPRMultiPassReliaiblityByLimitedFanouts(probCircuit, i);
                      long endTimeInside = System.currentTimeMillis();
                      
                      //final long insideTime = (endTimeInside - startTime)/1000; //seconds
                      final long insideTime = (endTimeInside - startTime); //seconds
                      
                      System.out.println(i  + "        " + sprmp_result + "        " + insideTime + "  ~  " + insideTime/1000 + " (s)");
                      endTime = endTimeInside;
                      
                      if(insideTime <= (this.timeoutMiliSeconds)){
                        ItemX item = new ItemX(i, sprmp_result, insideTime);
                        resultTable.add(item);
                        idx = i;
                        if(insideTime * 2 > this.timeoutMiliSeconds){
                            System.out.println("Stoping the execution, expected time " + this.timeoutMiliSeconds*2 + " above the timelimit (~2x) : " + this.timeoutMiliSeconds + " ~ " + this.timeout  + "(s)");
                            i = (this.probCircuit.getFanouts().size()) + 11;
                            break;
                        }
                      }
              }else{
                  break;
              }
         }
         for (int i = 0; i <= idx; i++){
             System.out.println(resultTable.get(i).PrintItemx());
         }
            
         
          System.out.println(" =================================== END SPR-MP ================= \n");
          
          //System.out.println("TimeOut-" + this.timeout +"(s)");
          
           WriteExcel resultFile = new WriteExcel(this.circuit.getName() + "-" + this.timeout + "(s)"  , "TimeOut-" + this.timeout +"(s)"    , Long.toString(this.timeoutMiliSeconds) , resultTable, idx);
          
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
