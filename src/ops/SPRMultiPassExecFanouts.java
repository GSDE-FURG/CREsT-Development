package ops;


import tool.Terminal;



/****  new libraries*/

//package ops;

import datastructures.CellLibrary;
import datastructures.Circuit;
import datastructures.Signal;
import java.math.BigDecimal;
import levelDatastructures.DepthGate;
import levelDatastructures.GateLevel;
import levelDatastructures.LevelCircuit;

import readers.MappedVerilogReader;

import signalProbability.ProbCircuit;

/**   */

/**
 *
 * @author Clayton
 */
class main_SPRMP_Exec {

    public static void main(final String[] args) throws Exception {
        //System.out.println("\n\nHello world, working");
        String circuitPath = "c17_cadence.v";
        String reliability = "0.999";
        String mode = "big_decimal";
        CommonOps commonOps = new CommonOps();
        //String library = CommonOps.getWorkPath(this) + "abc" + File.separator + 
        //"cadence.genlib";
        String library = "cadence.genlib";

        SPRMultiPassExecFanouts objeto_teste = new SPRMultiPassExecFanouts(reliability, mode, circuitPath, library);
        //objeto_teste.PTM_CALL();
        //objeto_teste.SPRMP_CALL();
        //objeto_teste.PTM_CALL();
        //objeto.SPRMP_TESTE();
        
        
        

    }

}

/**
 *
 * @author Clayton
 */
public class SPRMultiPassExecFanouts {

    private String reliability;
    private String mode;
    private String circuitFilePath;
    private String genlib;
    private String genlibPATH;
    
    public CellLibrary cellLibrary;
    private Circuit circuit;
    private LevelCircuit levelCircuit;
    public ProbCircuit probCircuit;
    private LevelCircuit lCircuit;


    public SPRMultiPassExecFanouts(String reliabilty, String mode, String circuitFilePath, String genlib) {

        this.reliability = reliabilty;
        this.circuitFilePath = "abc\\" + circuitFilePath;
        this.mode = mode;
        this.genlib = genlib;
        this.genlibPATH = "abc\\" + this.genlib;
        this.cellLibrary = new CellLibrary();
        

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
        //Terminal.getInstance().terminalOutput(output);
         System.out.println("PrintGateLevels : "+output);
    }
     
     public void initProbCircuit() {
        if(this.circuit != null) {
            
            this.lCircuit = this.levelCircuit; //Terminal.getInstance().getLevelCircuit();
            this.probCircuit = new ProbCircuit(this.circuit);
            System.out.println("InitProbCircuit " + this.probCircuit.getName());
            
        } else {
            Terminal.getInstance().terminalOutput("Circuit is null!!");
        }
    }
    
     public void initLevelCircuit(){    
        if(this.circuit != null) {
            System.out.println("InitLevelCircuit");
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
     
     protected void SPRMP_CALL()throws Exception {
        System.out.println("Calcing SPRMP bro...");
    } 
   
     protected void PTM_CALL() throws Exception {

        System.out.println("SPRMP Development : " + this.circuitFilePath + "   -   "  + this.reliability);
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
         System.out.println("Circuits : " + this.circuit.getName());
         System.out.println("Gates : " + this.circuit.getGates());
         System.out.println("Inputs : " + this.circuit.getInputs());
         System.out.println("Outputs : " + this.circuit.getOutputs());
         System.out.println("Signals : " + this.circuit.getSignals());
         /*Circuit linked to verilog_circuit*/
       
        
         /*Circuit Probabilities */
         this.initLevelCircuit();
         this.initProbCircuit();
          /*Circuit Probabilities */
          
          
          /*Gate levels*/
          this.PrintGateLevels();
          /*Gate levels*/
        
          
          System.out.println("Level Circuit : "+ this.levelCircuit);

          
          /*PTM*/
          /*
           cellLib.setPTMCells2(Float.valueOf(reliability));
           cellLib.setPTMCells(new BigDecimal(reliability));
           cellLib.teste();
           pCircuit.setPTMsReliabilityMatrix(); //changed
          */
         
          cellLib.setPTMCells2(Float.valueOf(reliability));
          cellLib.setPTMCells(new BigDecimal(reliability));
          cellLib.teste();
          
         //CellLib
          this.cellLibrary = cellLib;
          
           //ProbCircuit
          probCircuit.setPTMsReliabilityMatrix(); //Sempre usar variaveis criadas 
          
          System.out.println("This cells : "+ this.cellLibrary);
          
          System.out.println("- PTM Reliability : " + PTMOps2.getCircuitReliabilityByPTM(probCircuit));
        

    }
}
