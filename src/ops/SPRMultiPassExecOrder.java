package ops;

import java.io.IOException;
import java.io.StringBufferInputStream;
import java.math.BigDecimal;

import javax.script.ScriptException;

import datastructures.CellLibrary;
//import jdk.internal.jline.Terminal;
import signalProbability.ProbCircuit;
import tool.Commands;

/**
 *
 * @author Clayton
 */
class main_SPRMP_Exec {

    public static void main(final String[] args) throws IOException, ScriptException {
        System.out.println("\n\nHello world, working");
        String circuitPath = "c17_cadence.v";
        String reliability = "0.999";
        String mode = "SPRMP";

        final SPRMultiPassExecOrder objeto = new SPRMultiPassExecOrder(reliability, mode, circuitPath, "cadence.genlib");

        objeto.getReliabilitySPR_MP();

    }

}

/**
 *
 * @author Clayton
 */
public class SPRMultiPassExecOrder {

    private String reliability;
    private String mode;
    private String circuitFilePath;
    private String genlib;


    public SPRMultiPassExecOrder(String reliabilty, String mode, String circuitFilePath, String genlib) {

        this.reliability = reliabilty;
        this.circuitFilePath = circuitFilePath;
        this.mode = mode;
        this.genlib = genlib;

    }

    public void getReliabilitySPR_MP()
            throws Exception {

        System.out.println("SPRMP Development : " + this.circuitFilePath + "   -   "  + this.reliability);
        
        final long startTime = System.currentTimeMillis();
        
        final Commands cmd = new Commands();

        //Reading library and verilog file (extension .v)
        cmd.ReadGenlib(this.genlib);

        cmd.ReadVerilog(this.circuitFilePath);

    }
}

       /*
        
        Terminal Terminal = Terminal.getInstance(); 


        String result = "";
        CellLibrary cellLib = Terminal.getInstance().getCellLibrary();
        ProbCircuit pCircuit = Terminal.getInstance().getProbCircuit();         
        pCircuit.clearProbSignalsMatrix();
        cellLib.setPTMCells2(Float.valueOf(reliability));
        cellLib.setPTMCells(new BigDecimal(reliability));
        pCircuit.setPTMReliabilityMatrix();
        pCircuit.setProbSignalStates(false);
        
        switch(type) {
            
            case "big_decimal":        
                result = "Reliability SPR_MP (in BigDecimal) of " + pCircuit.getName() + " CIRCUIT is " + SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit);
                break;
            
            case "float":                                          
                result = "Reliability SPR (in float) of "; //+ pCircuit.getName() + " CIRCUIT is " + SPROpsFloat.getSPRReliability(pCircuit);
                break;
                        
        }               
        
        //Terminal.getInstance().terminalOutput(result);
        System.out.println(result);
        
        final long endTime = System.currentTimeMillis();
        String timeConsup = "## TIME CONSUPTION ## ==> " + Long.toString((endTime - startTime)) + " ms";
        //Terminal.getInstance().terminalOutput(timeConsup);               
    }

    */
    


