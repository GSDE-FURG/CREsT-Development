/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;


import critical_vectors.CriticalVectorsUtils;
import datastructures.*;

import java.io.*;

import datastructures.InputVector;
import levelDatastructures.LevelCircuit;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.file.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


import manipulator.PTMMController;
import ops.*;


import javax.script.ScriptException;
import levelDatastructures.DepthGate;
import levelDatastructures.GateLevel;
import levelDatastructures.InterLevel;
import logicSimulator.main;
import manipulator.CircuitFactory;
import manipulator.SPRController;

import org.json.JSONTokener;
import readers.ReadTxt;
import signalProbability.ProbCircuit;
import signalProbability.ProbGate;
import signalProbability.ProbSignal;


import simulation.SimulationCircuit;
import simulation.SimualtionType;
import simulation.checkFiles;
import twoLevelDatastructures.PLA;
import twoLevelDatastructures.PLAManipulator;
import twoLevelDatastructures.PLAOps;
import writers.GenlibWriter;
import writers.VerilogWriter;
import writers.WriteFile;

import readers.CustomMatrixReader;
import wrv_algoritm.*;

import static ops.CommonOps.*;

import org.json.JSONObject;



/**
 *
 * @author matheus
 */
class ReportTimer extends TimerTask {
    public void run() {
        
        if(ops.SPRMultiPassV3Ops.FANOUTS != null) {
            String out = "";
            for(ProbSignal pSignal: ops.SPRMultiPassV3Ops.FANOUTS) {
                out = out + (pSignal + " ==> " + pSignal.getCurrentState() + ", ");                
            }
            System.out.println(out);
        }
        

    }
}



public class Commands {
    private Map<String, String> helpTree = new LinkedHashMap<>();
    int counter;
    String output;
    long timenow;
    
    public Commands() {
        
        helpTree.put("clear", "Clear terminal's window");
        
        /* clayton new command*/
        helpTree.put("mc_fault_injection", "Output the Fault Mask Rate (FMR - Logical Masking circuits topology) using the Statistical Monte Carlo Simulation");
        helpTree.put("mc_multiple_fault_injection",
                "Output the FMR using the Statistical Monte Carlo Simulation For Multiple Fault Injection");
        
        helpTree.put("help", "Show this message!!!");
        //helpTree.put("get_sonf_reliability", "Print the reliability value based on SONF PTM method");
        helpTree.put("print_gates", "Output circuit types based on readed verilog");
        helpTree.put("print_gates_level", "Output the gates in an specific depth level or all gates levels");
        helpTree.put("print_gatelevels", "Output the GateLevel of circuit with gate and type!!!");
        helpTree.put("print_genlib", "Output logical function, truth table (hex) of current genlib");
        helpTree.put("print_signals", "Output IOs and TOTAL Singals quantity");
        helpTree.put("print_types", "Output circuit types based on readed library");
        helpTree.put("ptm", "Output the reliability by Probabilistic Transfer Matrices using Java's BigDecimal\n\t\tdatatype (fixed gates' reliability in 0.99999802495");
        //helpTree.put("ptm_big_decimal", "Output the reliability by Original PTM Method using Java's BigDecimal datatype");
        //helpTree.put("ptm_double", "Output the reliability by Original PTM Method using double datatype");
        //helpTree.put("ptm_float", "Output the reliability by Original PTM Method using float datatype");
        helpTree.put("ptm_matrix_size", "Show the matrix size of each circuit level");
        helpTree.put("quit", "Exit tool!!!");
        helpTree.put("read_genlib", "Read a genlib file");
        helpTree.put("read_script", "Read a script file for multiple"
                + " circuits Fault Mask Rate (FMR) Analysis");
        helpTree.put("read_verilog", "Read circuit based in a verilog");
        helpTree.put("spr", "Output the reliability by Signal Probability Relaibility using Java's BigDecimal\n\t\tdatatype (fixed gates' reliability in 0.99999802495");
        //helpTree.put("spr_big_decimal", "Output the reliability by Signal Probability Relaibility using Java's BigDecimal datatype");
        //helpTree.put("spr_float", "Output the reliability by Signal Probability Relaibility using float datatype");
        helpTree.put("write_genlib", "Export de current genlib to a file");
        helpTree.put("write_verilog", "Export de current circuit to a file");
        
    }

    public String getHelpDesc(String command) {
        return helpTree.get(command);
    }
    
    public boolean verifyCircuitAndLib() {
        Circuit circuit = Terminal.getInstance().getCircuit();
        if(circuit == null) {
            Terminal.getInstance().terminalOutput("Circuit is Null!!!");
            return false;
        }
        
        CellLibrary lib = Terminal.getInstance().getCellLibrary();
        if(lib == null) {
            Terminal.getInstance().terminalOutput("Library is Null!!!");
            return false;
        }
        return true;
    }
    
//    public void InitLevel() {
//        
//        Terminal.getInstance().initLevelCircuit();
//        
//        LevelCircuit levelCircuit = Terminal.getInstance().getLevelCircuit();                
//        
//        if (levelCircuit != null) {
//            //levelCircuit.setTecReliability(new BigDecimal(reliability));
//            //Terminal.getInstance().getCellLibrary().setPTMCell(levelCircuit.getTecReliability());
//            Terminal.getInstance().terminalOutput("Level Circuit Init Success!!");
//            
//        } else {
//            Terminal.getInstance().terminalOutput("Circuit is Null!!");
//        }
//    }
    
    public void Help() {
        
        Terminal terminal = Terminal.getInstance();
        
        for (Map.Entry<String, String> entry : helpTree.entrySet()) {
            //terminal.terminalOutput(entry.getKey() + "   ####   " + entry.getValue());
            if(entry.getKey().length() > 13) {
                terminal.terminalOutput(entry.getKey() + "\t" + entry.getValue());
            } else {
                terminal.terminalOutput(entry.getKey() + "\t\t" + entry.getValue());
            }
            
        }
                
    }
    
    public void ReadCustomMatrix(String filename) throws IOException, ScriptException {
        String path = CommonOps.getWorkPath(this) + File.separator + filename;
        CustomMatrixLibrary cMatrixLib = new CustomMatrixReader(path).getcMatrixLib();        
        Terminal.getInstance().setCustomMatrixLib(cMatrixLib);
        
    }
    
    public void ReadGenlib(String filename) throws IOException, ScriptException {
        //String path = CommonOps.getWorkPath(this) + "abc" + File.separator + filename;                             
        String path = CommonOps.getWorkPath(this) + filename;                             
        Terminal.getInstance().initLibrary(path);        
    }
    
    private String circuit_analysis;
    private String genlib;
    private String relative_path;

    /**
     *
     * @param genlib
     * @param circuit
     * @param mc_sample
     * @throws IOException
     * @throws ScriptException
     * @throws Exception
     */
    public void Monte_Carlo_Fault_injection(String genlib, String circuit, String mc_sample) throws IOException, ScriptException, Exception {
        //String path = CommonOps.getWorkPath(this) + "abc" + File.separator + filename;
        //String path = CommonOps.getWorkPath(this) + "abc" + File.separator + filename;
        System.out.println("Monte Carlo SF Simulation ....");
        //System.out.println("--> Genlib: "+ genlib);
        //System.out.println("Circuit: "+ circuit);
        //System.out.println("MC_Sample: "+ flag);

        /* Chamar a muinha ferramenta */

        int threads = 8; //Numero de threads
        //int sampleSizeMonteCarlo = Integer.parseInt(mc_sample);
        String constReliability = "0.9999"; //Used for internal structures



        //String[] arrOfStr = circuit.split("/", 2);


        ///String relativePath = "/" + arrOfStr[0];
        // String relativePath = "abc/" ;

        // genlib = "abc/" + "cadence.genlib";


        constReliability = "0.9999"; //Used for internal structures
        // String relativePath = "abc/";
        String relativePath = "";

        checkFiles checkFiles = new checkFiles();
        relativePath = checkFiles.split_PathString(genlib);

        try {
            String[] textoSeparado = genlib.split("/");
            String[] circ = circuit.split("/");

            for (int i = 0; i < textoSeparado.length - 1; i++) {
                relativePath = relativePath + textoSeparado[i] + "/";
            }

            this.relative_path = relativePath;
            this.genlib = textoSeparado[textoSeparado.length-1];
            this.circuit_analysis = circ[circ.length-1];

        } catch (Exception e) {

            System.out.println("Error... ");
            this.relative_path = "";
            this.circuit_analysis = circuit;
            this.genlib = genlib;
        }



        //main experimento = new main(threads, constReliability, relativePath, this.relative_path  + this.genlib);
        //experimento.preparingEnviromentSingleFile(this.circuit_analysis);
        //experimento.multithreadingSimulationExaustic();

        SimulationCircuit simulationCircuit = new SimulationCircuit(circuit, relativePath, genlib , "ALL_SIGNALS", threads, constReliability,  Integer.parseInt(mc_sample));
        //simulationCircuit.print();
        SimualtionType experimento = new SimualtionType(simulationCircuit);
        experimento.printSpecSimulation();
        experimento.faultToleranceMonteCarloAPI();

        System.out.println("Simulation results:\n"

                + experimento.getFMR());


        Terminal.getInstance().terminalOutput("Simulation results with " + threads + " threads "
                + ": " + experimento.getFMR());
        
    }

    public void Monte_Carlo_Multiple_Transient_Fault_Injection(String genlib, String circuit, String base, String order, String frequency) throws IOException, ScriptException, Exception {
        //String path = CommonOps.getWorkPath(this) + "abc" + File.separator + filename;
        System.out.println("Multiple Transient Fault Injection random at Chip Die Areas");
        System.out.println("Genlib: "+ genlib);
        System.out.println("Circuit: "+ circuit);
        System.out.println("Order: "+ order);

        /* Chamar a minha ferramenta */

        int threads = 4; //Numero de threads
        int sampleSizeMonteCarlo = (int) (Math.pow(Integer.parseInt(base), Integer.parseInt(order)) * Integer.parseInt(frequency));// Integer.parseInt(order);
        String constReliability = "0.9999"; //Used for internal structures

        System.out.println("Sample Size: " + sampleSizeMonteCarlo);
        System.out.println("base: " + base + "  Order: " + order  + "  Frequency: " + frequency);

        //String[] arrOfStr = circuit.split("/", 2);


        ///String relativePath = "/" + arrOfStr[0];
        // String relativePath = "abc/" ;

        // genlib = "abc/" + "cadence.genlib";


        constReliability = "0.9999"; //Used for internal structures
        // String relativePath = "abc/";
        String relativePath = "";
        try {
            String[] textoSeparado = genlib.split("/");
            String[] circ = circuit.split("/");

            for (int i = 0; i < textoSeparado.length - 1; i++) {
                relativePath = relativePath + textoSeparado[i] + "/";
            }

            this.relative_path = relativePath;
            this.genlib = textoSeparado[textoSeparado.length-1];
            this.circuit_analysis = circ[circ.length-1];

        } catch (Exception e) {

            System.out.println("Error... ");
            this.relative_path = "";
            this.circuit_analysis = circuit;
            this.genlib = genlib;
        }





        //String genlib =  relativePath  + "lib_basic_no_cost.genlib";

        //genlib =  relativePath  + "cadence.genlib";
        File tmpDir = new File(circuit);
        boolean exists = tmpDir.exists();

        File genDir = new File(genlib);
        boolean exists2 = genDir.exists();

        if (exists && exists2){
            System.out.println(" ------ In"
                    + "side -------");
            System.out.println("Relative Path: " + relativePath);
            System.out.println("Genlib : " + genlib);
            System.out.println("Genlib Texto Separado: " + this.relative_path);

            main experimento = new main(threads, constReliability, relativePath, this.relative_path  + this.genlib);

            experimento.preparingEnviromentSingleFile(this.circuit_analysis);


            experimento.monteCarloSimulationMultipleTransientFaults(Integer.parseInt(base), Integer.parseInt(order), Integer.parseInt(frequency), sampleSizeMonteCarlo, "ALL_SIGNALS");

            System.out.println("Simulation results:\n" + experimento.getFMR());



            Terminal.getInstance().terminalOutput("Simulation results with " + threads + " threads "
                    + ": " + experimento.getFMR());
        }

        else{
            System.out.println("File or genlib not exist : " + circuit + "   -  " + genlib);
        }



    }


    /** Process the command line and extracts the fault array list [-mc_fault_injection teste/cadence.genlib teste/c.v -mc 20000 100 200 300]
     *  After process command line, this method links the method to Logic Simulator to procced MTF simulation
     *  This procedure in special overwrite the MTF fault list [20000 1 1 1] always choosing the highest fault order (Ex: 1 1 1 Triple injection) - Command Example ArrayList [-mc_fault_injection teste/cadence.genlib teste/c.v -mc 20000 1 1 1]
     *  <p>
     * @author Clayton Farias
     * @param genlib
     * @param circuit
     * @throws IOException
     * @throws ScriptException
     * @throws Exception
     */
    public void Monte_Carlo_Multiple_Transient_Fault_Injection_overWriteWorstOrder(String genlib, String circuit, ArrayList <String> splittedCommand) throws IOException, ScriptException, Exception {
        //String path = CommonOps.getWorkPath(this) + "abc" + File.separator + filename;
        System.out.println(" ---- METHOD MODE -----");
        System.out.println("Multiple Transient Fault Injection random at Chip Die Areas");
        System.out.println("Genlib: "+ genlib);
        System.out.println("Circuit: "+ circuit);
        // System.out.println("Order: "+ order);

        /* Chamar a minha ferramenta */
        System.out.println("SplitteCommand: " + splittedCommand);
        int threads = 4; //Numero de threads
        int sampleSizeMonteCarlo = Integer.parseInt(splittedCommand.get(4));//(int) (Math.pow(Integer.parseInt(base), Integer.parseInt(order)) * Integer.parseInt(frequency));// Integer.parseInt(order);
        String constReliability = "0.9999"; //Used for internal structures

        System.out.println("Sample Size: " + sampleSizeMonteCarlo);
        //System.out.println("base: " + base + "  Order: " + order  + "  Frequency: " + frequency);

        //String[] arrOfStr = circuit.split("/", 2);
        ArrayList <Integer> x = new ArrayList<>();

        for(int i = 4 ; i < splittedCommand.size(); i++) {
            x.add(Integer.parseInt(splittedCommand.get(i)));
        }

        System.out.println("X array :" + x);
        ///String relativePath = "/" + arrOfStr[0];
        // String relativePath = "abc/" ;

        // genlib = "abc/" + "cadence.genlib";


        constReliability = "0.9999"; //Used for internal structures
        // String relativePath = "abc/";
        String relativePath = "";
        try {
            String[] textoSeparado = genlib.split("/");
            String[] circ = circuit.split("/");

            for (int i = 0; i < textoSeparado.length - 1; i++) {
                relativePath = relativePath + textoSeparado[i] + "/";
            }

            this.relative_path = relativePath;
            this.genlib = textoSeparado[textoSeparado.length-1];
            this.circuit_analysis = circ[circ.length-1];

        } catch (Exception e) {

            System.out.println("Error... ");
            this.relative_path = "";
            this.circuit_analysis = circuit;
            this.genlib = genlib;
        }





        //String genlib =  relativePath  + "lib_basic_no_cost.genlib";

        //genlib =  relativePath  + "cadence.genlib";
        File tmpDir = new File(circuit);
        boolean exists = tmpDir.exists();

        File genDir = new File(genlib);
        boolean exists2 = genDir.exists();

        if (exists && exists2){
            System.out.println(" ------ In"
                    + "side -------");
            System.out.println("Relative Path: " + relativePath);
            System.out.println("Genlib : " + genlib);
            System.out.println("Genlib Texto Separado: " + this.relative_path);

            main experimento = new main(threads, constReliability, relativePath, this.relative_path  + this.genlib);

            experimento.preparingEnviromentSingleFile(this.circuit_analysis);


            experimento.monteCarloSimulationMultipleTransientFaultsNew(x, "ALL_SIGNALS");

            System.out.println("Simulation results:\n" + experimento.getFMR());



            Terminal.getInstance().terminalOutput("Simulation results with " + threads + " threads "
                    + ": " + experimento.getFMR());
        }

        else{
            System.out.println("File or genlib not exist : " + circuit + "   -  " + genlib);
        }



    }


    /** Process the command line and extracts the fault array list [-mc_fault_injection teste/cadence.genlib teste/c.v -mc 20000 100 200 300]
     *  After process command line, this method links the method to Logic Simulator to procced MTF simulation
     *  This procedure estimates proportion  *  <p>
     * @author Clayton Farias
     * @param genlib
     * @param circuit
     * @throws IOException
     * @throws ScriptException
     * @throws Exception
     */
    public void Monte_Carlo_Multiple_Transient_Fault_Injection_Proportion(String genlib, String circuit, ArrayList <String> splittedCommand) throws IOException, ScriptException, Exception {
        //String path = CommonOps.getWorkPath(this) + "abc" + File.separator + filename;
        /*
        System.out.println(" ---- Proportion METHOD MODE -----");
        System.out.println("Multiple Transient Fault Injection random at Chip Die Areas");
        System.out.println("Genlib: "+ genlib);
        System.out.println("Circuit: "+ circuit);
         */
        // System.out.println("Order: "+ order);

        /* Chamar a minha ferramenta */
        //System.out.println("SplitteCommand: " + splittedCommand);
        int threads = 8; //Numero de threads
        int sampleSizeMonteCarlo = Integer.parseInt(splittedCommand.get(4));//(int) (Math.pow(Integer.parseInt(base), Integer.parseInt(order)) * Integer.parseInt(frequency));// Integer.parseInt(order);
        String constReliability = "0.9999"; //Used for internal structures

        // System.out.println("Sample Size: " + sampleSizeMonteCarlo);
        //System.out.println("base: " + base + "  Order: " + order  + "  Frequency: " + frequency);

        //String[] arrOfStr = circuit.split("/", 2);
        ArrayList <Float> x = new ArrayList<>();

        for(int i = 4 ; i < splittedCommand.size(); i++) {
            x.add(Float.parseFloat(splittedCommand.get(i)));
        }
        //System.out.println("X array :" + x);
        constReliability = "0.9999"; //Used for internal structures
        String relativePath = "";

        try {
            String[] textoSeparado = genlib.split("/");
            String[] circ = circuit.split("/");

            for (int i = 0; i < textoSeparado.length - 1; i++) {
                relativePath = relativePath + textoSeparado[i] + "/";
            }
            //System.out.println("Relative Path: " + relativePath + "    mtf: " + x);
            this.relative_path = relativePath;
            this.genlib = textoSeparado[textoSeparado.length-1];
            this.circuit_analysis = circ[circ.length-1];

        } catch (Exception e) {

            System.out.println("Error... ");
            this.relative_path = "";
            this.circuit_analysis = circuit;
            this.genlib = genlib;
        }
        /*
        File tmpDir = new File(circuit);
        boolean exists = tmpDir.exists();

        File genDir = new File(genlib);
        boolean exists2 = genDir.exists();
        */

            /*
            System.out.println(" ------ Inside -------");
            System.out.println("Relative Path: " + relativePath);
            System.out.println("Genlib : " + genlib);
            System.out.println("Genlib Texto Separado: " + this.relative_path);
            */

        /*
            main experimento = new main(threads, constReliability, relativePath, this.relative_path  + this.genlib);
            experimento.preparingEnviromentSingleFile(this.circuit_analysis);
            experimento.monteCarloSimulationMultipleTransientFaultsProportion(sampleSizeMonteCarlo, x, "ALL_SIGNALS");
        */

        System.out.println("-CREST Linking Monte Carlo Fault Simulator API...");
            SimulationCircuit simulationCircuit = new SimulationCircuit(circuit, relativePath, genlib, "ALL_SIGNALS", threads, constReliability, x);
                //simulationCircuit.print();
                SimualtionType experimento = new SimualtionType(simulationCircuit);
                        experimento.printSpecSimulation();
                            experimento.faultToleranceMonteCarloMETAPI("CREsT");

                        System.out.println("---> Simulation results:\n" + experimento.getFMR());

            Terminal.getInstance().terminalOutput("Simulation results with " + threads + " threads "
                    + ": " + experimento.getFMR());

    }


    /**
     *
     * @param genlib
     * @param circuit
     * @param flag
     * @param path
     * @throws IOException
     * @throws ScriptException
     * @throws Exception
     */
    public void Exaustive_Fault_injection(String genlib, String circuit, String flag, String path) throws IOException, ScriptException, Exception {
        //String path = CommonOps.getWorkPath(this) + "abc" + File.separator + filename;
        System.out.println("Exaustive STF Simulation ....");
        //System.out.println("--> Genlib: "+ genlib);
        //System.out.println("Circuit: "+ circuit);
        //System.out.println("MC_Sample: "+ flag);
        
        /* Chamar a muinha ferramenta */
        
         int threads = 8; //Numero de threads
         //int sampleSizeMonteCarlo = Integer.parseInt(mc_sample);
         String constReliability = "0.9999"; //Used for internal structures
         
         
         
        //String[] arrOfStr = circuit.split("/", 2);
         
        
          ///String relativePath = "/" + arrOfStr[0];
         // String relativePath = "abc/" ;
                  
        // genlib = "abc/" + "cadence.genlib";
          
             
         constReliability = "0.9999"; //Used for internal structures
        // String relativePath = "abc/";
        String relativePath = path;

        checkFiles checkFiles = new checkFiles();
        relativePath = checkFiles.split_PathString(genlib);

        try {
              String[] textoSeparado = genlib.split("/");
              String[] circ = circuit.split("/");
             
              for (int i = 0; i < textoSeparado.length - 1; i++) {
                relativePath = relativePath + textoSeparado[i] + "/"; 
              }
              
              this.relative_path = relativePath;
              this.genlib = textoSeparado[textoSeparado.length-1];
              this.circuit_analysis = circ[circ.length-1];
              
        } catch (Exception e) {
            
             System.out.println("Error... ");
             this.relative_path = "";
             this.circuit_analysis = circuit;
             this.genlib = genlib;
        }



                        //main experimento = new main(threads, constReliability, relativePath, this.relative_path  + this.genlib);
                        //experimento.preparingEnviromentSingleFile(this.circuit_analysis);
                       //experimento.multithreadingSimulationExaustic();

                          SimulationCircuit simulationCircuit = new SimulationCircuit(circuit, relativePath, path + genlib, "ALL_SIGNALS", threads, constReliability, 1);
                          //simulationCircuit.print();
                          SimualtionType experimento = new SimualtionType(simulationCircuit);
                          experimento.printSpecSimulation();
                          experimento.faultToleranceExhaustiveSETAPI();

                       System.out.println("Simulation results:\n"
                               
                               + experimento.getFMR());


                       
                        Terminal.getInstance().terminalOutput("Simulation results with " + threads + " threads "
                                + ": " + experimento.getFMR());
    }


    public void Exaustive_Fault_injectionComplete(String genlib, String circuit, String flag) throws IOException, ScriptException, Exception {
        //String path = CommonOps.getWorkPath(this) + "abc" + File.separator + filename;
        System.out.println("Exaustive Simulation ....");
        System.out.println("Genlib: "+ genlib);
        System.out.println("Circuit: "+ circuit);
        System.out.println("MC_Sample: "+ flag);

        /* Chamar a muinha ferramenta */

        int threads = 4; //Numero de threads
        //int sampleSizeMonteCarlo = Integer.parseInt(mc_sample);
        String constReliability = "0.9999"; //Used for internal structures



        //String[] arrOfStr = circuit.split("/", 2);


        ///String relativePath = "/" + arrOfStr[0];
        // String relativePath = "abc/" ;

        // genlib = "abc/" + "cadence.genlib";


        constReliability = "0.9999"; //Used for internal structures
        // String relativePath = "abc/";

        System.out.println("! GEN: " + genlib);

        checkFiles checkFiles = new checkFiles();
        String relativePath = checkFiles.split_PathString(genlib) ;

        System.out.println("PATH>>>>: " + relativePath);

        genlib = checkFiles.split_Genlib(genlib);
        System.out.println("GEN: " + genlib);


            this.relative_path = relativePath;
            this.genlib = genlib;
            this.circuit_analysis = circuit;


            /*
            main experimento = new main(threads, constReliability, relativePath, this.relative_path  + this.genlib);
            experimento.preparingEnviromentSingleFile(this.circuit_analysis);
            experimento.multithreadingSimulationExausticComplete();
             */

            SimulationCircuit simulationCircuit = new SimulationCircuit(circuit, relativePath, relativePath +  genlib, "ALL_SIGNALS", threads, constReliability, 1);
            //simulationCircuit.print();
            SimualtionType experimento = new SimualtionType(simulationCircuit);
            experimento.printSpecSimulation();
            experimento.faultToleranceExhaustiveCompleteMETAPI();

            System.out.println("Simulation results:\n"

                    + experimento.getFMR());



            Terminal.getInstance().terminalOutput("Simulation results with " + threads + " threads "
                    + ": " + experimento.getFMR());

    }


    /** Method for run FMR analysis in lot circuits with same genlib
    * @author Clayton Farias
    * @version 1.0
    * @since Realise in Integration with main framework
    * @param script_file
    */
    public void Read_Scrip_Mc_Fault_injection(String script_file) throws IOException, ScriptException, Exception {
        
        int threads = 4; //Numero de threads
        List<String> records = new ArrayList<>();
   
        try (BufferedReader reader = new BufferedReader(new FileReader(script_file))) {
            String line;
            while ((line = reader.readLine()) != null)
            {
                records.add(line);
                
            }
        }
                  
         System.out.println("File: " + script_file);
         System.out.println("Line 1: " + records.get(0));

         try {
             String[] arrOfStr = records.get(0).split(" ", 3);
             for (int i = 0; i < arrOfStr.length; i++) {
                 System.out.println("- :" + arrOfStr[i]);
             }
             
            String relativePath = arrOfStr[0] ;//"";//"abc/";
            String genlib = arrOfStr[1];
            String sampleSizeMonteCarlo = "";

             if(arrOfStr.length >= 3){
                System.out.println("MTF selected");
                sampleSizeMonteCarlo = arrOfStr[2];
            }else{
                System.out.println("STF selected");
                sampleSizeMonteCarlo = arrOfStr[2];
            }

            String constReliability = "0.9999"; //Used for internal structures
              
            //System.out.println("Path: " + relativePath);
            //System.out.println("Genlib: "+ genlib);
            //System.out.println("Circuit: "+ circuit);
            //System.out.println("MC_Sample: "+ sampleSizeMonteCarlo);

            for (int i = 1; i < records.size(); i++) {
             
                String circuit = records.get(i);
                String complete_file = relativePath + circuit;
                
                System.out.println("Testing file: " + complete_file  + " path: " + relativePath);
                
                File tmpDir = new File(relativePath + circuit);
                boolean exists = tmpDir.exists();
                if (exists){
                     System.out.println("        - File ok !");
                     //System.out.println("circuit: " + circuit + " genlib : " + genlib);
                     Terminal.getInstance().terminalOutput(" - Genlib File " + genlib + " read ..." );

                     Terminal.getInstance().terminalOutput(" - Verilog File " + circuit + " read ..." );

                     main experimento = new main(threads, constReliability, relativePath, relativePath+genlib);
                     experimento.preparingEnviromentSingleFile(circuit);
                     experimento.monteCarloSimulation(Integer.parseInt(sampleSizeMonteCarlo), "ALL_SIGNALS");

                     System.out.println("Simulation results:\n" + experimento.getFMR());
                     Terminal.getInstance().terminalOutput(experimento.getFMR());

                 }
                else{
                    System.out.println("x - Error file not exist : " + complete_file);
                }
            }
         
          } catch (Exception e) {
             System.out.println("OPS ERROR !!!!");
        }
    }
    
   
    
    public void ReadVerilog(String filename) throws IOException, Exception {
        //String path = CommonOps.getWorkPath(this) + "abc" + File.separator + filename;
        String path = CommonOps.getWorkPath(this) + filename;

        try {
            
            //Long timeNow = timenow();
            //System.out.println("Verificando init Circuit...");
            //System.out.println("");

            Terminal.getInstance().initCircuit(path);
            //timestamp(timeNow, "Init circuit");

            //timeNow = timenow();
            Terminal.getInstance().initLevelCircuit();
            //timestamp(timeNow, "Init Levelcircuit");

            //timeNow = timenow();
            Terminal.getInstance().initProbCircuit();
            //timestamp(timeNow, "Init Probcircuit");
        } catch (ScriptException ex) {
            System.out.println(ex);
        }
    }
    
    public void PrintCircuitGates() {
        Circuit circuit = Terminal.getInstance().getCircuit();
        if(circuit == null) {
            Terminal.getInstance().terminalOutput("Circuit is Null!!!");
        } else {
            
            Map<String, Integer> gateTypes = new HashMap<>();
            ArrayList<Gate> gates = circuit.getGates();

            for (int i = 0; i < gates.size(); i++) {
                if(gateTypes.get(gates.get(i).getType().getName()) == null) {
                    gateTypes.put(gates.get(i).getType().getName(), 1);
                }
                else {
                    gateTypes.put(gates.get(i).getType().getName(), gateTypes.get(gates.get(i).getType().getName()).intValue()+1);
                }
            }
            
            this.counter = 0;
            gateTypes.forEach((k,v)-> {                
                Terminal.getInstance().terminalOutput(k + " : " + v);
                this.counter = this.counter + v.intValue();                
            });
            Terminal.getInstance().terminalOutput("TOTAL: " + this.counter);
        }   
    }
    
    public void PrintCircuitGatesByLevel(boolean withWires, String filename) {
        int totalCounter = 0;
        output = "";
        
        LevelCircuit levelCircuit = Terminal.getInstance().getLevelCircuit();
        if(levelCircuit == null) {
            output = output + "Level Circuit is Null! Please use \"init_level\" command!";
        } else {
                        
            output += ("Circuit Name: " + levelCircuit.getName() + "\n");            

            for (int i = 0; i < levelCircuit.getGateLevels().size(); i++) {
                
                Map<String, Integer> gateTypes = new HashMap<String, Integer>();
                ArrayList<Object> gates = levelCircuit.getGateLevels().get(i).getGates();
                
                output += ("Gate Level " + levelCircuit.getGateLevels().get(i).getLevel() + "\n");
                
                for (int j = 0; j < gates.size(); j++) {
                    Object gate = gates.get(j);
                    
                    if (gate instanceof DepthGate) {
                        Gate fooGate = ((DepthGate)gate).getGate();
                        if(gateTypes.get(fooGate.getType().getName()) == null) {
                            gateTypes.put(fooGate.getType().getName(), 1);
                        } else {
                            gateTypes.put(fooGate.getType().getName(), gateTypes.get(fooGate.getType().getName()).intValue()+1);
                        }                        
                    } else if(withWires) {
                        if(gateTypes.get("WIRE") == null) {
                            gateTypes.put("WIRE", 1);
                        } else {
                            gateTypes.put("WIRE", gateTypes.get("WIRE").intValue()+1);
                        }
                    }                             
                    
                }
                this.counter = 0;
                gateTypes.forEach((String k,Integer v)-> {                        
                        output += (k + " : " + v + "\n");
                        this.counter = this.counter + v.intValue();
                });
                
                totalCounter = totalCounter + this.counter;
                output += ("Total on level: " + this.counter + "\n\n");
            }
            
            if(withWires) {
                output += ("TOTAL GATES/WIRES: " + totalCounter);
            } else {
                output += ("TOTAL GATES: " + totalCounter);
            }
        }
        
        if(!filename.isEmpty()) {
            WriteFile write = new WriteFile("files/" + filename, output);
        } else {
            Terminal.getInstance().terminalOutput(output);
        }
    }
    
    public void PrintCircuitGatesByLevel(int level, boolean withWires, String filename) {
        
        output = "";
        LevelCircuit levelCircuit = Terminal.getInstance().getLevelCircuit();
        int totalCounter = 0;
        if(levelCircuit == null) {
            output += ("Circuit is Null!!!");
        } else {
            
            output += ("Circuit Name: " + levelCircuit.getName() + "\n");
            GateLevel gLevel = levelCircuit.getGateLevels().get(level - 1);

                Map<String, Integer> gateTypes = new HashMap<String, Integer>();
                ArrayList<Object> gates = gLevel.getGates();
                
                output += ("Gate Level " + gLevel.getLevel() + "\n");
                
                for (int j = 0; j < gates.size(); j++) {
                    Object gate = gates.get(j);
                    
                    if (gate instanceof DepthGate) {
                        Gate fooGate = ((DepthGate)gate).getGate();
                        if(gateTypes.get(fooGate.getType().getName()) == null) {
                            gateTypes.put(fooGate.getType().getName(), 1);
                        } else {
                            gateTypes.put(fooGate.getType().getName(), gateTypes.get(fooGate.getType().getName()).intValue()+1);
                        }                        
                    } else if(withWires) {
                        if(gateTypes.get("WIRE") == null) {
                            gateTypes.put("WIRE", 1);
                        } else {
                            gateTypes.put("WIRE", gateTypes.get("WIRE").intValue()+1);
                        }
                    }                             
                    
                }
                this.counter = 0;
                gateTypes.forEach((String k,Integer v)-> {                        
                        output += (k + " : " + v + "\n");
                        this.counter = this.counter + v.intValue();
                });
                
                totalCounter = totalCounter + this.counter;

            if(withWires) {
                output += ("TOTAL GATES/WIRES: " + totalCounter);
            } else {
                output += ("TOTAL GATES: " + totalCounter);
            }
        }
        
        if(!filename.isEmpty()) {
            WriteFile write = new WriteFile("files/" + filename, output);
        } else {
            Terminal.getInstance().terminalOutput(output);
        }
    }
    
    public void PrintCircuitSignals() {
        Circuit circuit = Terminal.getInstance().getCircuit();
        
        Terminal.getInstance().terminalOutput("INPUTS: " + Integer.toString(circuit.getInputs().size()));
        Terminal.getInstance().terminalOutput("OUTPUTS: " + Integer.toString(circuit.getOutputs().size()));
        Terminal.getInstance().terminalOutput("ALL SIGNALS: " + Integer.toString(circuit.getSignals().size()));
        
    }
    
    public void PrintGenlib() throws ScriptException {
        
        ArrayList<Cell> library = Terminal.getInstance().getCellLibrary().getCells();
        
        if(library == null) {
            Terminal.getInstance().terminalOutput("Genlib is Null!!!");
        } else {
            for (int i = 0; i < library.size(); i++) {          

                Terminal.getInstance().terminalOutput(library.get(i).getName() + " ---- " +
                                                      library.get(i).getFunctions().toString() + " ---- " +
                                                      library.get(i).getTruthTable());
            }
        }       
    }
    
    public void PrintGateLevels() {
        LevelCircuit lcirc = Terminal.getInstance().getLevelCircuit();
        
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
        Terminal.getInstance().terminalOutput(output);
    }
    
    public void WriteGenlib(String filename, CellLibrary library) {
        GenlibWriter writer = new GenlibWriter(filename, library);
    }
    public void WriteVerilog(String filename, Circuit circuit) {
        VerilogWriter writer = new VerilogWriter(filename, circuit);
    }
    
    public void PtmMatrixSize(String filename) {
        
        final long startTime = System.currentTimeMillis();
        
        LevelCircuit lcirc = Terminal.getInstance().getLevelCircuit();
        
        this.output = "";
        
        output += "Circuit Name: " + lcirc.getName() + "\n";        
        
        for (int i = 0; i < lcirc.getGateLevels().size(); i++) {
            GateLevel gateLevel = lcirc.getGateLevels().get(i);
            InterLevel interLevel = lcirc.getInterLevels().get(i);                                                      
            
            output += ("INTERCONNECTION LEVEL " + interLevel.getLevel() + " -- " + "inSignals: " + interLevel.getInSingnals().size() + " -- outSignals: " + interLevel.getOutSingnals().size() + "\n");
            output += ("MatrixSize: " + PTMOps.getMatrixLevelSize(interLevel)+ "\n");
            output += ("GATE LEVEL " + gateLevel.getLevel() + " -- Wires: " + gateLevel.getWireGates().size() + " -- Gates: " + gateLevel.getDepthGates().size() + "\n");           
            output += ("MatrixSize: " + PTMOps.getMatrixLevelSize(gateLevel) + "\n\n");
        }
        final long endTime = System.currentTimeMillis();
        String timeConsup = "## TIME CONSUPTION ## ==> " + Long.toString((endTime - startTime)) + " ms";
        
        output += timeConsup;
        
        if(!filename.isEmpty()) {
            String pathname = "files/" + filename;           
            WriteFile write = new WriteFile(pathname , output);
        } else {
            Terminal.getInstance().terminalOutput(output);
        }
        
    }
    
    public void getReliabilityPTM_OLD(String reliability, String type) {
        
        if(verifyCircuitAndLib()) {
            final long startTime = System.currentTimeMillis();

            String result = "";
            LevelCircuit lcirc = Terminal.getInstance().getLevelCircuit();        
            CellLibrary cellLib = Terminal.getInstance().getCellLibrary();
            ProbCircuit pCircuit = Terminal.getInstance().getProbCircuit();

            // Tá dando erro aqui!!!
            cellLib.setPTMCells2(Float.valueOf(reliability));
            cellLib.setPTMCells(new BigDecimal(reliability));
            pCircuit.setPTMReliabilityMatrix();

            switch(type) {

                case "big_decimal":                
                    result = "Reliability PTM (in BigDecimal) of " + pCircuit.getName() + " CIRCUIT is " + PTMOps2.getCircuitReliabilityByPTM(pCircuit, null);
                    break;

                case "double":        
                    result = "Reliability PTM (in double (EJML Library) of " + lcirc.getName() + " CIRCUIT is " + PTMOps.getPTM(reliability, lcirc, cellLib, false);
                    break;

                case "float":                                          
                    result = "Reliability PTM (in float) of " + pCircuit.getName() + " CIRCUIT is " + PTMOps2Float.getCircuitReliabilityByPTM(pCircuit);
                    break;

                case "default":
                    BigDecimal val = PTMOps2.getCircuitReliabilityByPTM(pCircuit, null);
                    result = "MTBF using PTM of " + pCircuit.getName() + " CIRCUIT is " + CommonOps.getMTBFBigInt(val) + ")";                
                    break;

            }               

            Terminal.getInstance().terminalOutput(result);

            final long endTime = System.currentTimeMillis();
            String timeConsup = "## TIME CONSUPTION ## ==> " + Long.toString((endTime - startTime)) + " ms";
            //Terminal.getInstance().terminalOutput(timeConsup);
        }
    }

    public void getReliabilityPTM(String[] args, String type) {

        String reliability = args[0];
        String inputSignals = "";
        if (args.length > 1) {
            inputSignals = args[1];
        }

        if(verifyCircuitAndLib()) {
            final long startTime = System.currentTimeMillis();

            String result = "";
            LevelCircuit lcirc = Terminal.getInstance().getLevelCircuit();
            CellLibrary cellLib = Terminal.getInstance().getCellLibrary();
            ProbCircuit pCircuit = Terminal.getInstance().getProbCircuit();

            cellLib.setPTMCells2(Float.valueOf(reliability));
            cellLib.setPTMCells(new BigDecimal(reliability));
            pCircuit.setPTMReliabilityMatrix();

            switch(type) {

                case "big_decimal":
                    if (inputSignals == "") {
                        result = "Reliability PTM (in BigDecimal) of " + pCircuit.getName() + " CIRCUIT is " + PTMOps2.getCircuitReliabilityByPTM(pCircuit, null);
                    } else {
                        result = "Reliability PTM (in BigDecimal) of " + pCircuit.getName() + " CIRCUIT is " + PTMOps2.getCircuitReliabilityByPTM(pCircuit, inputSignals);
                    }
                    break;

                case "double":
                    result = "Reliability PTM (in double (EJML Library) of " + lcirc.getName() + " CIRCUIT is " + PTMOps.getPTM(reliability, lcirc, cellLib, false);
                    break;

                case "float":
                    result = "Reliability PTM (in float) of " + pCircuit.getName() + " CIRCUIT is " + PTMOps2Float.getCircuitReliabilityByPTM(pCircuit);
                    break;

                case "default":
                    BigDecimal val = PTMOps2.getCircuitReliabilityByPTM(pCircuit, null);
                    result = "MTBF using PTM of " + pCircuit.getName() + " CIRCUIT is " + CommonOps.getMTBFBigInt(val) + ")";
                    System.out.println("Raw reliability is " + val);
                    break;

            }

            Terminal.getInstance().terminalOutput(result);

            final long endTime = System.currentTimeMillis();
            String timeConsup = "## TIME CONSUPTION ## ==> " + Long.toString((endTime - startTime)) + " ms";
            //Terminal.getInstance().terminalOutput(timeConsup);
        }
    }
    
    public void getReliabilitySPR(String reliability, String type) {
        
        
        
        final long startTime = System.currentTimeMillis();
        
        if(verifyCircuitAndLib()) {
        
        
            String result = "";     
            CellLibrary cellLib = Terminal.getInstance().getCellLibrary();
            ProbCircuit pCircuit = Terminal.getInstance().getProbCircuit();
            SPRController controller;
            BigDecimal val;

            switch(type) {

                case "big_decimal":                                

                    controller = new SPRController(Terminal.getInstance());                        
                    val = controller.getReliability(new BigDecimal(reliability));

                    //result = "Reliability using SPR of " + pCircuit.getName() + " CIRCUIT is " + val + " (MTBF: " + CommonOps.getMTBFBigInt(val) + ")";
                    result = "MTBF using SPR of " + pCircuit.getName() + " CIRCUIT is " + CommonOps.getMTBFBigInt(val) + ")";
                    break;

                case "float":
                    cellLib.setPTMCells2(Float.valueOf(reliability));
                    pCircuit.setPTMReliabilityMatrix();
                    pCircuit.setDefaultProbSourceSignalMatrix();
                    result = "Reliability SPR (in float) of " + pCircuit.getName() + " CIRCUIT is " + SPROpsFloat.getSPRReliability(pCircuit);
                    break;

                case "custom_lib":                                

                    controller = new SPRController(Terminal.getInstance());                        
                    val = controller.getReliabilityCustomLib(new BigDecimal(reliability));

                    result = "Reliability using SPR (with CustomLib) of " + pCircuit.getName() + " CIRCUIT is " + val + " (MTBF: " + CommonOps.getMTBFBigInt(val) + ")";
                    break;

            }               

            Terminal.getInstance().terminalOutput(result);
            System.out.println(result);

            final long endTime = System.currentTimeMillis();
            String timeConsup = "## TIME CONSUPTION ## ==> " + Long.toString((endTime - startTime)) + " ms";
            //Terminal.getInstance().terminalOutput(timeConsup);
        }
                       
    }
    
    
    public void getReliabilitySPRMP(String reliability, String type) {
        
        
        
        final long startTime = System.currentTimeMillis();
        
        String result = "";     
        CellLibrary cellLib = Terminal.getInstance().getCellLibrary();
        ProbCircuit pCircuit = Terminal.getInstance().getProbCircuit();
        SPRController controller;
        BigDecimal val;
        
        switch(type) {
            
            case "big_decimal":
                cellLib.setPTMCells(new BigDecimal(reliability));                                         
                pCircuit.setDefaultProbSourceSignalMatrix();
                pCircuit.setPTMReliabilityMatrix();
                pCircuit.setProbSignalStates(false);

                val = SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit);
                
                result = "MTBF using SPR-MP of " + pCircuit.getName() + " CIRCUIT is " + CommonOps.getMTBFBigInt(val) + ")";
                break;            
                
            case "custom_lib":                                
                
                //cellLib.setPTMCells(new BigDecimal("0.99999802495"));                 
                pCircuit.syncCellPTMs();
                pCircuit.setDefaultProbSourceSignalMatrix();
                pCircuit.setProbSignalStates(false);
                pCircuit.setCustomMatrix(Terminal.getInstance().getCustomMatrixLib());  
                //controller = new SPRController(Terminal.getInstance());                        
                //val = controller.getReliabilityCustomLib(new BigDecimal(reliability));
                val = SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit);
                
                result = "Reliability using SPRMP (with CustomLib) of " + pCircuit.getName() + " CIRCUIT is " + val + " (MTBF: " + CommonOps.getMTBFBigInt(val) + ")";
                break;
                        
        }               
        
        Terminal.getInstance().terminalOutput(result);
        System.out.println(result);
        
        final long endTime = System.currentTimeMillis();
        String timeConsup = "## TIME CONSUPTION ## ==> " + Long.toString((endTime - startTime)) + " ms";
        //Terminal.getInstance().terminalOutput(timeConsup);               
    }
    
    public void getReliabilitySONF(String reliability) {
        
        final long startTime = System.currentTimeMillis();
        LevelCircuit lcirc = Terminal.getInstance().getLevelCircuit();
        CellLibrary cellLib = Terminal.getInstance().getCellLibrary();
        
        Terminal.getInstance().terminalOutput("SONF Reliability PTM of " + lcirc.getName() + " CIRCUIT is " + (float)PTMOps.getPTM(reliability, lcirc, cellLib, true));
        
        final long endTime = System.currentTimeMillis();
        String timeConsup = "## TIME CONSUPTION ## ==> " + Long.toString((endTime - startTime)) + " ms";
        Terminal.getInstance().terminalOutput(timeConsup);               
    }
    
    public void Report() throws IOException, Exception {
        
        ArrayList<String> circuitNames = new ArrayList<>();
        ArrayList<String> totalGates = new ArrayList<>();
        ArrayList<String> totalLevels = new ArrayList<>();
        ArrayList<String> gatesBiggestLevel = new ArrayList<>();
        ArrayList<String> gatesAndWiresBiggestLevel = new ArrayList<>();
        ArrayList<String> biggestMatrix = new ArrayList<>();
        ArrayList<String> totalElements = new ArrayList<>();
        ArrayList<String> totalYiB = new ArrayList<>();
        
        String[] circuits = {"c17_cadence.v", 
                             "c432_cadence.v", 
                             "c499_cadence.v",
                             "c880_cadence.v",
                             "c1355_cadence.v",
                             "c1908_cadence.v",
                             "c2670_cadence.v",
                             "c3540_cadence.v",
                             "c5315_cadence.v",
                             "c6288_cadence.v",
                             "c7552_cadence.v",};
        
        String pathname = "files/print-level-gates.txt";
        String pathname2 = "files/print-level-gates-with-wires.txt";
        String pathname3 = "files/ptm_matrix_size.txt";
        
        String line;                    

        BufferedReader br;               
        
        BigInteger currentBigInt;
            
        NumberFormat formatter = new DecimalFormat("0.###E0", DecimalFormatSymbols.getInstance(Locale.ROOT));
        
        try {
            Terminal.getInstance().executeCommand("read_genlib cadence.genlib");
            
            
            for (int i = 0; i < circuits.length; i++) {
                Terminal.getInstance().executeCommand("read_verilog " + circuits[i]);
                Terminal.getInstance().executeCommand("init_level");
                Terminal.getInstance().executeCommand("print_gates_level --export=print-level-gates.txt");
                Terminal.getInstance().executeCommand("print_gates_level --with_wires --export=print-level-gates-with-wires.txt");
                Terminal.getInstance().executeCommand("ptm_matrix_size --export=ptm_matrix_size.txt");
                
                int levelCounter = 0;
                int justGates = 0;
                int wiresAndGates = 0;
                String biggestLevelGates = "";
                String biggestLevelGatesAndWires = "";
                String currentLevel = "";
                String bigMatrix = "";
                String previousLine = "";
                String matrixGateLevel = "";
                
                File file = new File(pathname);
                File file2 = new File(pathname2);
                File file3 = new File(pathname3);
                
                boolean flag = false;
                
                // Read file
                try {
                    br = new BufferedReader(new FileReader(file));

                    while ((line = br.readLine()) != null) {
                        // Found Circuit Names!!
                        if(line.matches("Circuit Name(.*)")) {
                            String cName = line.replaceAll(" ", "");
                            cName = cName.split(":")[1];
                            circuitNames.add(cName);
                        }

                        //Found Total Gates
                        if(line.matches("TOTAL GATES(.*)")) {
                            String tGates = line.replaceAll(" ", "");
                            tGates = tGates.split(":")[1];
                            totalGates.add(tGates);
                        }

                        //Count Levels
                        if(line.matches("Gate Level(.*)")) {
                            currentLevel = line;
                            levelCounter++;
                        }

                        //Bigger Level Just Gates
                        if(line.matches("Total on level(.*)")) {
                            String str = line.replaceAll(" ", "");
                            str = str.split(":")[1];

                            if(justGates < Integer.valueOf(str)) {
                                justGates = Integer.valueOf(str);
                                biggestLevelGates = currentLevel;
                            }
                        }
                    }

                    br = new BufferedReader(new FileReader(file2));                                

                    while ((line = br.readLine()) != null) {


                        //Verify Bigger Level
                        if(line.matches("Gate Level(.*)")) {
                            currentLevel = line;
                        }

                        //Biggest Level Gates + Wires
                        if(line.matches("Total on level(.*)")) {
                            String str = line.replaceAll(" ", "");
                            str = str.split(":")[1];

                            if(wiresAndGates < Integer.valueOf(str)) {
                                wiresAndGates = Integer.valueOf(str);
                                biggestLevelGatesAndWires = currentLevel;
                            }
                        }
                    }

                    br = new BufferedReader(new FileReader(file3));
                    currentBigInt = new BigInteger("0");

                    while ((line = br.readLine()) != null) {
                        
                        if(line.matches("GATE LEVEL(.*)")) {
                            previousLine = line;
                            flag = true;
                        }
                        
                        if(line.matches("MatrixSize(.*)")) {
                            
                            if (flag) {
                                BigInteger bigIntX = new BigInteger("2");
                                BigInteger bigIntY = new BigInteger("2");
                                BigInteger bigIntResult;

                                String matrix = "";
                                String str = line.replaceAll(" ", "");                        
                                matrix = (str.split(":"))[1];

                                str = matrix.replaceAll("\\Q(2^\\E|\\Q)\\E", "");                                                

                                String x = (str.split("x"))[0];
                                String y = (str.split("x"))[1];

                                bigIntX = bigIntX.pow(Integer.valueOf(x));
                                bigIntY = bigIntY.pow(Integer.valueOf(y));

                                bigIntResult = bigIntX.multiply(bigIntY);

                                if (bigIntResult.compareTo(currentBigInt) == 1) {
                                    currentBigInt = bigIntResult;
                                    bigMatrix = matrix;
                                    matrixGateLevel = previousLine;
                                }
                                
                                flag = false;
                            } 
                        }                    
                    }                                

                    // ADD current circuit's total levels
                    totalLevels.add(Integer.toString(levelCounter));

                    // ADD current circuit's gates on the biggest level
                    gatesBiggestLevel.add("Gates: " + justGates + " on " + biggestLevelGates);

                    // ADD current circuit's gates and wires on the biggest level
                    gatesAndWiresBiggestLevel.add("Gates And Wires: " + wiresAndGates + " on " + biggestLevelGatesAndWires);

                    // ADD current circuit's biggest matrix order
                    biggestMatrix.add(bigMatrix + " on " + matrixGateLevel);

                    // ADD current circuit's total elements of previous matrix
                    totalElements.add(formatter.format(currentBigInt));
                    
                    
                    // ADD Estimative matrix needed storage size in GB
                    String matrixOrder = bigMatrix.replaceAll("\\Q(2^\\E|\\Q)\\E", "");
                    int expRows = Integer.valueOf(matrixOrder.split("x")[0]);
                    int expColumns = Integer.valueOf(matrixOrder.split("x")[1]);
                    BigInteger storage = PTMOps.MatrixInYobiByte(expRows, expColumns);
                    
                    System.out.println(formatter.format(storage) + "YiB");
                    totalYiB.add(formatter.format(storage));
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // Delete file
                try {                
                    Files.delete(file.toPath());
                    Files.delete(file2.toPath());
                    Files.delete(file3.toPath());
                } catch (NoSuchFileException x) {
                    System.err.format("%s: no such" + " file or directory%n", pathname);
                } catch (DirectoryNotEmptyException x) {
                    System.err.format("%s not empty%n", pathname);
                } catch (IOException x) {
                    // File permission problems are caught here.
                    System.err.println(x);
                }
                
            }
            
            output = "Circuit Name\t|\t" +
                    "Total Gates\t|\t"+
                    "Total Levels\t|\t" +
                    "Gates on Biggest Level\t|\t" + 
                    "Elements on Biggest Level\t\t|\t" +
                    "Biggest Matrix Order\t\t\t|\t" +
                    "Total Element on Biggest Gate Matrix\n";
            
            for (int i = 0; i < circuitNames.size(); i++) {
                output += (circuitNames.get(i) + "\t|\t" + 
                        totalGates.get(i) + "\t|\t" +
                        totalLevels.get(i) + "\t|\t" +
                        gatesBiggestLevel.get(i) + "\t|\t" +
                        gatesAndWiresBiggestLevel.get(i) + "\t|\t" +
                        biggestMatrix.get(i) + "\t|\t" +
                        totalElements.get(i) + "\t|\t" +
                        totalYiB.get(i) + "\n");
            }
            
            System.out.println(output);
            Terminal.getInstance().terminalOutput(output);


            
        } catch (ScriptException ex) {
            Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Foo() throws IOException, ScriptException, Exception {
        
        
        
        Terminal.getInstance().executeCommand("read_genlib ../files/genlibs/lib_full_no_cost.genlib");
        Terminal.getInstance().executeCommand("read_custom_matrix 45nm.txt");
        //Terminal.getInstance().executeCommand("read_verilog ../files/mappeds/c432_full_no_costV2.v");
        
        
        //Terminal.getInstance().executeCommand("spr_big_decimal 0.999999");
        //Terminal.getInstance().executeCommand("spr_big_decimal 0.999999001");
        //Terminal.getInstance().executeCommand("spr");
        
       
        String[] cellList = {"ZERO",
                             "ONE",
                             "BUF",
                             "INV",
                             "NOR2",
                             "NOR3",
                             "NOR4",
                             "NAND2",
                             "NAND3",
                             "NAND4",
                             "OAI21",
                             "OAI211",
                             "OAI22",
                             "OAI221",
                             "OAI222",
                             "AOI21",
                             "AOI211",
                             "AOI22",
                             "AOI221",
                             "AOI222",
                             "XOR2"};
        
        String[] benchs = {"c432",
                           "c499",
                           "c880",
                           "c1355",
                           "c1908",
                           "c2670",
                           "c3540",
                           "c5315",
                           "c6288",
                           "c7552"};
        
        
        String[] libs = {"lib_min_no_cost",
                         "lib_basic_no_cost",
                         "lib_complex_no_cost",
                         "lib_full_no_cost",
                         "lib_full_area_cost",
                         "lib_full_susceptability_cost",
                         "lib_full_worst_susceptability_cost",
                         "lib_complex_no_cost_no_xor",
                         "lib_full_no_cost_no_xor",
                         "lib_full_area_cost_no_xor",
                         "lib_full_susceptability_cost_no_xor",
                         "lib_full_worst_susceptability_cost_no_xor"};
        
        String[] reliabilities = {"total_gates",
                                  "fanouts",
                                  "levels",
                                  "in",
                                  "out",
                                  "fixed_reliability",
                                  "mtbf_fixed_reliability",
                                  "custom_45nm",
                                  "mtbf_custom_45nm",
                                  "circuit_name_file"};
        
        
        File dir = new File("files/mappeds");
        
        for (String bench : benchs) {
            
            ArrayList<ArrayList<String>> csv = new ArrayList<>();
            
            // Add header
            csv.add(new ArrayList<>());
            csv.get(0).add(bench);
            
            //Add indexes
            int counter = 1;
            for (String cell : cellList) {                
                csv.add(new ArrayList<>());
                csv.get(counter).add(cell);
                counter++;
            }
            
            for (String reliability : reliabilities) {
                csv.add(new ArrayList<>());
                csv.get(counter).add(reliability);
                counter++;
            }
            
            //Complete header
            for (String lib : libs) {
                csv.get(0).add(lib);
            }
            
            
            File[] matches = dir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.startsWith(bench);
                }
            });
            
            ArrayList<ProbCircuit> circuits = new ArrayList<>();
        
                                    
            for (File matche : matches) {
                circuits.add(new CircuitFactory(Terminal.getInstance().getCellLibrary(), "files/mappeds/" + matche.getName()).getProbCircuit());
                System.out.println(matche);
            }
                        
            
            for (String lib : libs) {
                int counter2 = 1;
                String name = bench + "_" + lib;
                for (ProbCircuit circuit : circuits) {
                    if(circuit.getName().equals(name)) {
                        for (String cell : cellList) {
                            csv.get(counter2).add(Integer.toString(circuit.getCellQuantity(cell)));
                            counter2++;
                        }
                        
                        csv.get(counter2).add(Integer.toString(circuit.getProbGates().size()));
                        counter2++;
                        
                        csv.get(counter2).add(Integer.toString(circuit.getFanouts().size()));
                        counter2++;
                        
                        csv.get(counter2).add(Integer.toString(circuit.getProbGateLevels().size()));
                        counter2++;
                        
                        csv.get(counter2).add(Integer.toString(circuit.getProbInputs().size()));
                        counter2++;
                        
                        csv.get(counter2).add(Integer.toString(circuit.getProbOutputs().size()));
                        counter2++;
                        
                        SPRController spr = new SPRController(circuit, Terminal.getInstance().getCellLibrary());
                        BigDecimal val = spr.getReliability();
                        csv.get(counter2).add(val.toString());
                        counter2++;
                        
                        csv.get(counter2).add(CommonOps.getMTBFBigInt(val).toString());
                        counter2++;
                        
                        val = spr.getReliabilityCustomLib(Terminal.getInstance().getCustomMatrixLib());
                        csv.get(counter2).add(val.toString());
                        counter2++;
                        
                        csv.get(counter2).add(CommonOps.getMTBFBigInt(val).toString());
                        counter2++;
                        
                        csv.get(counter2).add("files/mappeds/" + circuit.getName());                        
                    }
                }
            }
            
            
            for (ArrayList<String> arrayList : csv) {
                String collect = arrayList.stream().collect(Collectors.joining(","));

                System.out.println(collect);
            }
                        
        }        
                
        
        
        
                
        //CustomMatrixLibrary cMatrixLib = Terminal.getInstance().getCustomMatrixLib();        
        //System.out.println(pCircuit);
        
        //pCircuit.setCustomMatrix(cMatrixLib);
        
        
        //Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal("0.99999802495"));

        //pCircuit.setPTMReliabilityMatrix();

        
        //matrixPrint(pCircuit.getProbGateByName("g059").getReliabilityMatrix());
        //System.out.println("# # # #");
        
        //pCircuit.setCustomMatrix(cMatrixLib);        
        //matrixPrint(pCircuit.getProbGateByName("g059").getReliabilityMatrix());
        //System.out.println("# # # #");
        //System.out.println(cMatrixLib.getName());
        
        //SPRController controller = new SPRController(Terminal.getInstance());
        
        //System.out.println(controller.getReliability(new BigDecimal("0.99999802495")));
        //System.out.println(controller.getReliability(new BigDecimal("0.999999")));
        
    }
        
    
    public void Foo2() throws IOException, Exception {
        
        Terminal.getInstance().executeCommand("read_genlib ../files/genlibs/lib_full_no_cost.genlib");
        Terminal.getInstance().executeCommand("read_custom_matrix 45nm.txt");
        Terminal.getInstance().executeCommand("read_verilog ../files/mappeds/c880_lib_basic_no_cost.v");
        

        CellLibrary cellLib = Terminal.getInstance().getCellLibrary();
        
        String[] benchs = {"c432",
                           "c499",
                           "c880",
                           "c1355",
                           "c1908",
                           "c2670",
                           "c3540",
                           "c5315",
                           "c6288",
                           "c7552"};
        
        
        String[] libs = {"lib_min_no_cost",
                         "lib_basic_no_cost",
                         "lib_complex_no_cost",
                         "lib_full_no_cost",
                         "lib_full_area_cost",
                         "lib_full_susceptability_cost",
                         "lib_full_worst_susceptability_cost",
                         "lib_complex_no_cost_no_xor",
                         "lib_full_no_cost_no_xor",
                         "lib_full_area_cost_no_xor",
                         "lib_full_susceptability_cost_no_xor",
                         "lib_full_worst_susceptability_cost_no_xor"};
        
        String[] reliabilities = {"custom_45nm_avg",
                                  "mtbf_custom_45nm_avg"};
        
        SPRController spr = new SPRController(Terminal.getInstance());
        

        BigDecimal reliability = new BigDecimal("0.99999802495");
        BigDecimal complement = BigDecimal.ONE.subtract(reliability);
        
        BigDecimal[][] signalMatrix = new BigDecimal[][]{{new BigDecimal("0.5"),new BigDecimal("0")},
                                                         {new BigDecimal("0"),new BigDecimal("0.5")}};
        
        BigDecimal[][] invPTM = new BigDecimal[][]{{complement,reliability},
                                                {reliability,complement}};
        
        BigDecimal[][] multpliedResult = getMultipliedMatrix(signalMatrix, invPTM);
                
        
        BigDecimal[][] resultTensor = getKronecker(signalMatrix, signalMatrix);
        resultTensor = getKronecker(resultTensor, signalMatrix);
        
        System.out.println("SINAL DE ENTRADA");
        matrixPrint(signalMatrix);
        System.out.println("#####");
        System.out.println("");

        BigDecimal[][] saidaINV = new BigDecimal[][]{{multpliedResult[1][0], multpliedResult[1][1]},
                                                     {multpliedResult[0][0], multpliedResult[0][1]}};
       
        System.out.println("SAIDA INV SEM TMR");
        matrixPrint(saidaINV);
        System.out.println("MTBF: " + CommonOps.getMTBF(saidaINV[0][0].add(saidaINV[1][1])));
        System.out.println("*************");
        System.out.println("");
        
        BigDecimal[][] votadorITM = new BigDecimal[][]{{BigDecimal.ONE,BigDecimal.ZERO},
                                                       {BigDecimal.ONE,BigDecimal.ZERO},
                                                       {BigDecimal.ONE,BigDecimal.ZERO},
                                                       {BigDecimal.ZERO,BigDecimal.ONE},
                                                       {BigDecimal.ONE,BigDecimal.ZERO},
                                                       {BigDecimal.ZERO,BigDecimal.ONE},
                                                       {BigDecimal.ZERO,BigDecimal.ONE},
                                                       {BigDecimal.ZERO,BigDecimal.ONE}};
        
        BigDecimal[][] fanout1to3 = new BigDecimal[][]{{BigDecimal.ONE,
                                                        BigDecimal.ZERO,
                                                        BigDecimal.ZERO,
                                                        BigDecimal.ZERO,
                                                        BigDecimal.ZERO,
                                                        BigDecimal.ZERO,
                                                        BigDecimal.ZERO,
                                                        BigDecimal.ZERO},
                                                       {BigDecimal.ZERO,
                                                        BigDecimal.ZERO,
                                                        BigDecimal.ZERO,
                                                        BigDecimal.ZERO,
                                                        BigDecimal.ZERO,
                                                        BigDecimal.ZERO,
                                                        BigDecimal.ZERO,
                                                        BigDecimal.ONE}};
        
               
        
        BigDecimal[][] currentPTM = getMultipliedMatrix(signalMatrix, fanout1to3);
        
        currentPTM = getMultipliedMatrix(currentPTM, getKronecker(getKronecker(invPTM, invPTM), invPTM));        
        
        currentPTM = getMultipliedMatrix(currentPTM, votadorITM);
        
        saidaINV = new BigDecimal[][]{{currentPTM[1][0], currentPTM[1][1]},
                                      {currentPTM[0][0], currentPTM[0][1]}};
       
        System.out.println("SAIDA INV com TMR");
        matrixPrint(saidaINV);
        System.out.println("MTBF: " + CommonOps.getMTBF(saidaINV[0][0].add(saidaINV[1][1])));
        System.out.println("*************");
        
    }
    
    public void Foo3(String argument) throws IOException, Exception {
        int flag2c = 0;
        int flag3c = 0;
        int flag2e = 0;
        int flag3e = 0;

        char[] exato = "00000001000101110001011101111111000101110111111101111111111111110001011101111111011111111111111101111111111111111111111111111110000101110111111101111111111111110111111111111111111111111111111001111111111111111111111111111110111111111111111011111110111010000001011101111111011111111111111101111111111111111111111111111110011111111111111111111111111111101111111111111110111111101110100001111111111111111111111111111110111111111111111011111110111010001111111111111110111111101110100011111110111010001110100010000000".toCharArray();
        char[] mod1  = "00000001000101110001111101111111000101110111111101111111111111110001011101111111011111111111111101111111111111111111111111111110000101110111111101111111111111110111111111111111111111111111111001111111111111111111111111111110111111111111111011111110111010000001011101111111011111111111111101111111111111111111111111111110011111111111111111111111111111101111111111111110111111101110100001111111111111111111111111111110111111111111111011111110111010001111111111111110111111101110100011111110111010001110100010000000".toCharArray();
        char[] mod2  = "00000001000101110001011101111111000101110111111101111111111111110001011101111111011111111111111101111111111111111111111111111110000101110111111101111111111111110111111111111111111111111111111001111111111111111111111111111110111111111111111011111110111010000001011101111111011111111111111101111111111111111111111111111110011111111111111111111111111111101111111111111110111111101110100001111111111111111111111111111111111111111111111011111110111010001111111111111110111111101110100011111110111010001110100010000000".toCharArray();
        char[] mod3  = "00000001000101110001011101111111000101110111111101111111111111110001011101111111011111111111111101111111111111111111111111111110001101110111111101111111111111110111111111111111111111111111111001111111111111111111111111111110111111111111111011111110111010000001011101111111011111111111111101111111111111111111111111111110011111111111111111111111111111101111111111111110111111101110100001111111111111111111111111111110111111111111111011111110111010001111111111111110111111101110100011111110111010001110100010000000".toCharArray();


        /**
         * Testei todos contra o módulo exato
         */
        /*
        for(int n=0; n<exato.length; n++) {
            if(exato[n]==mod1[n]) {
                if (exato[n] == mod2[n]) {
                    flag3c = flag3c + 1;
                } else {
                    flag2c = flag2c + 1;
                }
            } else {
                if(mod1[n]==mod2[n]) {
                    flag2e = flag2e + 1;
                } else {
                    flag2c = flag2c + 1;
                }
            }
        }
        */

        for(int n=0; n<exato.length; n++) {
            if(exato[n]==mod1[n]) {
                if (exato[n] == mod2[n]) {
                    if(exato[n]== mod3[n]) {
                        flag3c = flag3c + 1;
                    } else {
                        flag2c = flag2c + 1;
                    }
                } else {
                    if(exato[n]== mod3[n]) {
                        flag2c = flag2c + 1;
                    } else {
                        flag2e = flag2e + 1;
                    }
                }
            } else {
                if(exato[n]==mod2[n]) {
                    if(exato[n]== mod3[n]) {
                        flag2c = flag2c + 1;
                    } else {
                        flag2e = flag2e + 1;
                    }
                } else {
                    if (exato[n]== mod3[n]) {
                        flag2e = flag2e + 1;
                    } else {
                        flag3e = flag3e + 1;
                    }
                }
            }
        }

        System.out.println("3C x 0E = " + flag3c);
        System.out.println("2C x 1E = " + flag2c);
        System.out.println("2E x 1C = " + flag2e);
        System.out.println("3E x 0C = " + flag3e);
    }
    
    public void Foo4() throws IOException, Exception {

        Terminal.getInstance().executeCommand("read_genlib abc/mylib.genlib");
        Terminal.getInstance().executeCommand("read_custom_matrix abc/45nm.txt");

        CellLibrary cellLib = Terminal.getInstance().getCellLibrary();
        CustomMatrixLibrary customLib = Terminal.getInstance().getCustomMatrixLib();
        //ProbCircuit pCircuit1 = new CircuitFactory(cellLib, "abc/c17-abc.v").getProbCircuit();
        //ProbCircuit pCircuit1 = new CircuitFactory(cellLib, "abc/c17_tmr.v").getProbCircuit();
        //ProbCircuit pCircuit1 = new CircuitFactory(cellLib, "abc/9sym_exact.v").getProbCircuit();
        ProbCircuit pCircuit1 = new CircuitFactory(cellLib, "abc/9sym_exact_tmr.v").getProbCircuit();
        //ProbCircuit pCircuit1 = new CircuitFactory(cellLib, "abc/9sym_1_1_0.v").getProbCircuit();
        //ProbCircuit pCircuit1 = new CircuitFactory(cellLib, "abc/9sym_1_1_1.v").getProbCircuit();
        //ProbCircuit pCircuit1 = new CircuitFactory(cellLib, "abc/9sym_1_1_2.v").getProbCircuit();
        //ProbCircuit pCircuit1 = new CircuitFactory(cellLib, "abc/9sym_64_64_0.v").getProbCircuit();
        //ProbCircuit pCircuit1 = new CircuitFactory(cellLib, "abc/9sym_64_64_1.v").getProbCircuit();
        //ProbCircuit pCircuit1 = new CircuitFactory(cellLib, "abc/9sym_64_64_2.v").getProbCircuit();
        //ProbCircuit pCircuit1 = new CircuitFactory(cellLib, "abc/9sym_256_512_0.v").getProbCircuit();
        //ProbCircuit pCircuit1 = new CircuitFactory(cellLib, "abc/9sym_256_512_1.v").getProbCircuit();
        //ProbCircuit pCircuit1 = new CircuitFactory(cellLib, "abc/9sym_256_512_2.v").getProbCircuit();

        //ProbCircuit pCircuit1 = new CircuitFactory(cellLib, "abc/atmr_9sym_1_1.v").getProbCircuit();
        //ProbCircuit pCircuit1 = new CircuitFactory(cellLib, "abc/atmr_9sym_64_64.v").getProbCircuit();
        //ProbCircuit pCircuit1 = new CircuitFactory(cellLib, "abc/atmr_9sym_256_512.v").getProbCircuit();

        System.out.println("GATES: " + pCircuit1.getProbGates().size());
        System.out.println("Levels: " + pCircuit1.getProbGateLevels().size());
        System.out.println("Fanouts: " + pCircuit1.getFanouts().size());
        System.out.println("I/O: " + pCircuit1.getProbInputs().size() + "/" + pCircuit1.getProbOutputs().size());

        //InputVector input = new InputVector("11", pCircuit1.getInputs().size());

        //System.out.println("INPUT:" + input.getBinaryString());

        // SPR !!!
        SPRController spr = new SPRController(pCircuit1, cellLib);
        BigDecimal result = spr.getReliability("0.99999802495", 15);

        BigDecimal averageReli = BigDecimal.ZERO;

        for(int i = 0; i<512; i++) {
            averageReli = averageReli.add(spr.getReliability(Integer.toString(i), "0.99999802495", 15));
        }
        averageReli = averageReli.divide(new BigDecimal("512"), RoundingMode.HALF_UP);
        System.out.println(averageReli);
        System.out.println(CommonOps.getMTBFBigInt(averageReli));




        //cellLib.setPTMCells(new BigDecimal("0.99999802495"));



        //System.out.println("Matriz PTM Votador:");
        //CommonOps.matrixPrint(pCircuit1.getProbGateByName("g0").getReliabilityMatrix());

        // SPR-MP
        /*

        pCircuit1.setDefaultProbSourceSignalMatrix();
        pCircuit1.setPTMReliabilityMatrix();
        pCircuit1.setProbSignalStates(false);

        ArrayList<ProbSignal> fanouts = new ArrayList<>();

        System.out.println(pCircuit1.getFanouts());
        fanouts.add(pCircuit1.getFanouts().get(0));
        fanouts.add(pCircuit1.getFanouts().get(1));
        fanouts.add(pCircuit1.getFanouts().get(2));
        fanouts.add(pCircuit1.getFanouts().get(3));
        fanouts.add(pCircuit1.getFanouts().get(4));
        fanouts.add(pCircuit1.getFanouts().get(5));
        fanouts.add(pCircuit1.getFanouts().get(6));
        fanouts.add(pCircuit1.getFanouts().get(7));
        fanouts.add(pCircuit1.getFanouts().get(8));

        System.out.println("Fanouts considerados:");
        System.out.println(fanouts);

        BigDecimal result = SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit1, fanouts);
        //BigDecimal result = SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit1);
        BigDecimal tmr_result = Utils.getReliabilityTMR(result.toString());

         */

        System.out.println(result);
        //System.out.println(tmr_result);
        System.out.println("MTBFs");
        System.out.println(CommonOps.getMTBFBigInt(result));
        //System.out.println(CommonOps.getMTBFBigInt(new BigDecimal("0.999930957")));
        //System.out.println("Input Realiability");
       // System.out.println(spr.getReliability(input, 13));

        //CommonOps.getLogicSignalFromSignalMatrix(pCircuit1.getProbOutputs().get(0).getProbMatrix());
        CommonOps.matrixPrint(pCircuit1.getProbOutputs().get(0).getProbMatrix(), 4);







        //System.out.println("Here");
        //CommonOps.matrixPrint(matrixABC);
        //System.out.println(pCircuit1.getFanouts());

    }

    public void Foo5() throws IOException, Exception {

        /**
         * Set CellLibrary
         */
        CellLibrary cellLib = new CellLibrary("genlibs/mylib.genlib");
        //CellLibrary cellLib = new CellLibrary("genlibs/asap.genlib");

        String seedName = "000-5xp1_fromBlif";
        String circuitAndApproxMethod = "";


        /**
         * Exact reference Circuit
         */
        ProbCircuit exactVerilog = new CircuitFactory(cellLib, String.format("5xp1/seeds/verilog/%s.v", seedName)).getProbCircuit();
        ArrayList<BigDecimal> exactReliability = new ArrayList<>();

        int counter = 0;
        int counter2 = 1;
        String directoryVersion = "E1";


        SPRController spr = new SPRController(exactVerilog, cellLib);
        /*
        for (int i = 0; i < PTMOps.PowInt(2, exactVerilog.getProbInputs().size()); i++) {
            String input = new InputVector(Integer.toString(i), exactVerilog.getProbInputs().size()).getBinaryString();
            BigDecimal vectorReliability = spr.getReliability(Integer.toString(i), "0.99999802495", 15);
            String output = CommonOps.getOutputVector(exactVerilog.getProbOutputs());
            System.out.println(String.format("%s %s", input, output));
        } */


        ArrayList<Integer> flexVectors = new ArrayList<>();


        Map<Integer, BigDecimal> mapp = ShellScriptOps.getOrderedCircuitReliabilities(exactVerilog, cellLib, false);
        ArrayList<Integer> moreCriticals = new ArrayList<>(mapp.keySet());
        int seedMoreCritical = moreCriticals.get(0);

        //ArrayList<Integer> moreCriticals2 = new ArrayList<>(mapp.keySet());
        //Map.Entry<Integer, BigDecimal> seedMoreCritical = ShellScriptOps.getAboluteCriticalVector(exactVerilog, cellLib);

        /*
        Map<Integer, BigDecimal> finalMapp = mapp;
        moreCriticals.forEach(crit -> {
            InputVector inputV = new InputVector(Integer.toString(crit), exactVerilog.getProbInputs().size());
            System.out.println(inputV + " --> " + finalMapp.get(crit));
        }); */

        //TimeUnit.MINUTES.sleep(130);
        //PLA pla = new PLAManipulator().readPLAFile("5xp1_exact_ESPRESSO.pla");
        PLA pla;
        InputVector vector = new InputVector(Integer.toString(moreCriticals.get(0)), exactVerilog.getProbInputs().size());
        flexVectors.add(seedMoreCritical);

        //InputVector vector = new InputVector(Integer.toString(seedMoreCritical.getKey()), exactVerilog.getProbInputs().size());
        //flexVectors.add(seedMoreCritical.getKey());



        /**
         * // JUST CRITICAL APPROX - MULT DONTCARE
         */

        moreCriticals = new ArrayList<>(mapp.keySet());
        counter = 1;
        circuitAndApproxMethod = "5xp1_just_crit_mult_dontcare";



        for (int i = 1; i < 128; i++) {

            int termIndex = counter;
            pla = new PLAManipulator().readPLAFile(String.format("5xp1/seeds/pla/%s.pla", seedName));
            String pattern = String.format("%03d-%s", counter, circuitAndApproxMethod);
            for (int w = 0; w < termIndex; w++) {
                vector = new InputVector(Integer.toString(moreCriticals.get(w)), pla.getQtInputs());
                pla.addDontCareTerm(vector.getBinaryString());
            }


            ShellScriptOps.deployPLAEspressoAigVerilog(  pla,
                                                         pattern,
                                                         String.format("5xp1/%s/pla/%s/",directoryVersion, circuitAndApproxMethod),
                                                         String.format("5xp1/%s/verilog/%s/", directoryVersion, circuitAndApproxMethod),
                                                         String.format("5xp1/%s/aig/%s/", directoryVersion, circuitAndApproxMethod),
                                                         String.format("5xp1/%s/pla/%s/", directoryVersion, circuitAndApproxMethod),
                                                         "genlibs/mylib.genlib");

            counter = counter + 1;
        }


        /**
         * // JUST CRITICAL APPROX - PER OUTPUT DONTCARE
         */

        moreCriticals = new ArrayList<>(mapp.keySet());
        counter = 1;
        circuitAndApproxMethod = "5xp1_just_crit_per_output_dontcare";


        for (int i = 1; i < 128; i++) {

            int termIndex = counter;
            pla = new PLAManipulator().readPLAFile(String.format("5xp1/seeds/pla/%s.pla", seedName));
            String pattern = String.format("%03d-%s", counter, circuitAndApproxMethod);
            for (int w = 0; w < termIndex; w++) {
                vector = new InputVector(Integer.toString(moreCriticals.get(w)), pla.getQtInputs());
                pla.addDontCareTerm(vector.getBinaryString());
            }

            pla = PLAOps.getApproxPLAWithDontCarePerOutput(pla);

            ShellScriptOps.deployPLAEspressoAigVerilog(  pla,
                                                         pattern,
                                                         String.format("5xp1/%s/pla/%s/",directoryVersion, circuitAndApproxMethod),
                                                         String.format("5xp1/%s/verilog/%s/", directoryVersion, circuitAndApproxMethod),
                                                         String.format("5xp1/%s/aig/%s/", directoryVersion, circuitAndApproxMethod),
                                                         String.format("5xp1/%s/pla/%s/", directoryVersion, circuitAndApproxMethod),
                                                        "genlibs/mylib.genlib");

            counter = counter + 1;
        }


        /**
         * TRACK-CRITICAL mult dontcares
         */
        circuitAndApproxMethod = "5xp1_track_crit_mult_dontcare";
        flexVectors.clear();
        flexVectors.add(seedMoreCritical);

        pla = new PLAManipulator().readPLAFile(String.format("5xp1/seeds/pla/%s.pla", seedName));
        for (int i = 1; i < 128; i++) {

            int previous;

            if (i != 1) {
                previous = i - 1;
                String previousPattern = String.format("%03d-%s", previous, circuitAndApproxMethod);

                pla = new PLAManipulator().readPLAFile(String.format("5xp1/%s/pla/%s/%s_ESPRESSO.pla",
                                                                        directoryVersion,
                                                                        circuitAndApproxMethod,
                                                                        previousPattern));
                String previousVerilog = String.format("5xp1/%s/verilog/%s/%s.v",
                                                        directoryVersion,
                                                        circuitAndApproxMethod,
                                                        previousPattern);
                ProbCircuit previousCircuit = new CircuitFactory(cellLib, previousVerilog).getProbCircuit();

                mapp = ShellScriptOps.getOrderedCircuitReliabilities(previousCircuit, cellLib, false);
                moreCriticals = new ArrayList<>(mapp.keySet());

                int candidateVector = moreCriticals.get(0);

                /**
                 * If the candidate has previously been added to the list,
                 * the next vector with a higher priority will be utilized.
                 */
                if (flexVectors.contains(candidateVector)) {

                    int counter3 = 1;
                    boolean whileFlag = true;
                    while (whileFlag) {
                        candidateVector = moreCriticals.get(counter3);
                        if (!flexVectors.contains(candidateVector)) {
                            flexVectors.add(candidateVector);
                            whileFlag = false;
                        }
                        counter3 = counter3 + 1;
                    }
                } else {
                    flexVectors.add(candidateVector);
                }

                //vector = new InputVector(Integer.toString(candidateVector), pla.getQtInputs());
            }

            String pattern = String.format("%03d-%s", i, circuitAndApproxMethod);

            for(int v : flexVectors) {
                vector = new InputVector(Integer.toString(v), pla.getQtInputs());
                pla.addDontCareTerm(vector.getBinaryString());
            }



            ShellScriptOps.deployPLAEspressoAigVerilog(  pla,
                                                         pattern,
                                                         String.format("5xp1/%s/pla/%s/",directoryVersion, circuitAndApproxMethod),
                                                         String.format("5xp1/%s/verilog/%s/", directoryVersion, circuitAndApproxMethod),
                                                         String.format("5xp1/%s/aig/%s/", directoryVersion, circuitAndApproxMethod),
                                                         String.format("5xp1/%s/pla/%s/", directoryVersion, circuitAndApproxMethod),
                                                        "genlibs/mylib.genlib");
        }

        /**
         * TRACK-CRITICAL per output dontcares
         */
        circuitAndApproxMethod = "5xp1_track_crit_per_output_dontcare";
        flexVectors.clear();
        flexVectors.add(seedMoreCritical);

        pla = new PLAManipulator().readPLAFile(String.format("5xp1/seeds/pla/%s.pla", seedName));
        for (int i = 1; i < 128; i++) {

            int previous;

            if (i != 1) {
                previous = i - 1;
                String previousPattern = String.format("%03d-%s", previous, circuitAndApproxMethod);

                pla = new PLAManipulator().readPLAFile(String.format("5xp1/%s/pla/%s/%s_ESPRESSO.pla",
                                                                        directoryVersion,
                                                                        circuitAndApproxMethod,
                                                                        previousPattern));
                String previousVerilog = String.format("5xp1/%s/verilog/%s/%s.v",
                                                        directoryVersion,
                                                        circuitAndApproxMethod,
                                                        previousPattern);
                ProbCircuit previousCircuit = new CircuitFactory(cellLib, previousVerilog).getProbCircuit();

                mapp = ShellScriptOps.getOrderedCircuitReliabilities(previousCircuit, cellLib, false);
                moreCriticals = new ArrayList<>(mapp.keySet());

                int candidateVector = moreCriticals.get(0);

        /**
         * If the candidate has previously been added to the list,
         * the next vector with a higher priority will be utilized.
         */
                if (flexVectors.contains(candidateVector)) {

                    int counter3 = 1;
                    boolean whileFlag = true;
                    while (whileFlag) {
                        candidateVector = moreCriticals.get(counter3);
                        if (!flexVectors.contains(candidateVector)) {
                            flexVectors.add(candidateVector);
                            whileFlag = false;
                        }
                        counter3 = counter3 + 1;
                    }
                } else {
                    flexVectors.add(candidateVector);
                }
            }

            String pattern = String.format("%03d-%s", i, circuitAndApproxMethod);

            for(int v : flexVectors) {
                vector = new InputVector(Integer.toString(v), pla.getQtInputs());
                pla.addDontCareTerm(vector.getBinaryString());
            }

            pla = PLAOps.getApproxPLAWithDontCarePerOutput(pla);


            ShellScriptOps.deployPLAEspressoAigVerilog(  pla,
                                                         pattern,
                                                         String.format("5xp1/%s/pla/%s/",directoryVersion, circuitAndApproxMethod),
                                                         String.format("5xp1/%s/verilog/%s/", directoryVersion, circuitAndApproxMethod),
                                                         String.format("5xp1/%s/aig/%s/", directoryVersion, circuitAndApproxMethod),
                                                         String.format("5xp1/%s/pla/%s/", directoryVersion, circuitAndApproxMethod),
                                                         "genlibs/mylib.genlib");
        }


        /**
         * TRACK-CRITICAL SAME PLA multi-dontcares
         */
        circuitAndApproxMethod = "5xp1_track_crit_same_seed_mult_dontcare";

        flexVectors.clear();
        flexVectors.add(seedMoreCritical);

        for (int i = 1; i < 128; i++) {

            int previous;
            pla = new PLAManipulator().readPLAFile(String.format("5xp1/seeds/pla/%s.pla", seedName));

            if (i != 1) {
                previous = i - 1;
                String previousPattern = String.format("%03d-%s", previous, circuitAndApproxMethod);

                //pla = new PLAManipulator().readPLAFile(String.format("5xp1/%s/pla/%s/%s_ESPRESSO.pla",
                //        directoryVersion,
                //        circuitAndApproxMethod,
                //        previousPattern));
                String previousVerilog = String.format("5xp1/%s/verilog/%s/%s.v",
                        directoryVersion,
                        circuitAndApproxMethod,
                        previousPattern);
                ProbCircuit previousCircuit = new CircuitFactory(cellLib, previousVerilog).getProbCircuit();

                mapp = ShellScriptOps.getOrderedCircuitReliabilities(previousCircuit, cellLib, false);
                moreCriticals = new ArrayList<>(mapp.keySet());

                int candidateVector = moreCriticals.get(0);

                /**
                 * If the candidate has previously been added to the list,
                 * the next vector with a higher priority will be utilized.
                 */
                if (flexVectors.contains(candidateVector)) {

                    int counter3 = 1;
                    boolean whileFlag = true;
                    while (whileFlag) {
                        candidateVector = moreCriticals.get(counter3);
                        if (!flexVectors.contains(candidateVector)) {
                            flexVectors.add(candidateVector);
                            whileFlag = false;
                        }
                        counter3 = counter3 + 1;
                    }
                } else {
                    flexVectors.add(candidateVector);
                }

            }

            String pattern = String.format("%03d-%s", i, circuitAndApproxMethod);

            for(int v : flexVectors) {
                vector = new InputVector(Integer.toString(v), pla.getQtInputs());
                pla.addDontCareTerm(vector.getBinaryString());
            }



            ShellScriptOps.deployPLAEspressoAigVerilog(  pla,
                                                         pattern,
                                                         String.format("5xp1/%s/pla/%s/",directoryVersion, circuitAndApproxMethod),
                                                         String.format("5xp1/%s/verilog/%s/", directoryVersion, circuitAndApproxMethod),
                                                         String.format("5xp1/%s/aig/%s/", directoryVersion, circuitAndApproxMethod),
                                                         String.format("5xp1/%s/pla/%s/", directoryVersion, circuitAndApproxMethod),
                                                        "genlibs/mylib.genlib");
        }


        /**
         * TRACK-CRITICAL SAME PLA per output dontcares
         */
        circuitAndApproxMethod = "5xp1_track_crit_same_seed_per_output_dontcare";

        flexVectors.clear();
        flexVectors.add(seedMoreCritical);

        /**
         * Fui até o 120 pq o AIG do 121 causou "core_dump" no ABC
         */
        for (int i = 1; i < 121; i++) {

            int previous;
            pla = new PLAManipulator().readPLAFile(String.format("5xp1/seeds/pla/%s.pla", seedName));

            if (i != 1) {
                previous = i - 1;
                String previousPattern = String.format("%03d-%s", previous, circuitAndApproxMethod);

                String previousVerilog = String.format("5xp1/%s/verilog/%s/%s.v",
                        directoryVersion,
                        circuitAndApproxMethod,
                        previousPattern);
                ProbCircuit previousCircuit = new CircuitFactory(cellLib, previousVerilog).getProbCircuit();

                mapp = ShellScriptOps.getOrderedCircuitReliabilities(previousCircuit, cellLib, false);
                moreCriticals = new ArrayList<>(mapp.keySet());

                int candidateVector = moreCriticals.get(0);

                /**
                 * If the candidate has previously been added to the list,
                 * the next vector with a higher priority will be utilized.
                 */
                if (flexVectors.contains(candidateVector)) {

                    int counter3 = 1;
                    boolean whileFlag = true;
                    while (whileFlag) {
                        candidateVector = moreCriticals.get(counter3);
                        if (!flexVectors.contains(candidateVector)) {
                            flexVectors.add(candidateVector);
                            whileFlag = false;
                        }
                        counter3 = counter3 + 1;
                    }
                } else {
                    flexVectors.add(candidateVector);
                }

            }

            String pattern = String.format("%03d-%s", i, circuitAndApproxMethod);

            for(int v : flexVectors) {
                vector = new InputVector(Integer.toString(v), pla.getQtInputs());
                pla.addDontCareTerm(vector.getBinaryString());
            }

            pla = PLAOps.getApproxPLAWithDontCarePerOutput(pla);

            ShellScriptOps.deployPLAEspressoAigVerilog(  pla,
                                                         pattern,
                                                         String.format("5xp1/%s/pla/%s/",directoryVersion, circuitAndApproxMethod),
                                                         String.format("5xp1/%s/verilog/%s/", directoryVersion, circuitAndApproxMethod),
                                                         String.format("5xp1/%s/aig/%s/", directoryVersion, circuitAndApproxMethod),
                                                         String.format("5xp1/%s/pla/%s/", directoryVersion, circuitAndApproxMethod),
                                                        "genlibs/mylib.genlib");
        }




        System.out.println("Finished!");
        TimeUnit.MINUTES.sleep(30);

    }
    
    public void Foo6() throws IOException, Exception {

        /**
         * Deploy PLAs do AMMES
         */

        /*ArrayList<java.nio.file.Path> paths = new ArrayList<>();

        Files.list(Paths.get("5xp1_AMMES")).sorted().forEach(path -> paths.add(path));

        for (Path path : paths) {
            String fileName = path.getFileName().toString().split(".pla")[0];

            ShellScriptOps.deployPLAAigVerilog( fileName,
                                                path.toString(),
                                       "5xp1/ammes/verilog/",
                                                "5xp1/ammes/aig/",
                                        "genlibs/mylib.genlib");
        }*/

        /**
         * Deploy Alsrac
         */

        /*ArrayList<java.nio.file.Path> paths = new ArrayList<>();

        Files.list(Paths.get("CIRCUITOS-AMMES-MANSKE/results/5xp1_mapA_mylib/alsrac/blif/")).sorted().forEach(path -> paths.add(path));

        for (Path path : paths) {
            String fileName = path.getFileName().toString().split(".blif")[0];


            ShellScriptOps.deployBLIFAigVerilog( fileName,
                    path.toString(),
                    "CIRCUITOS-AMMES-MANSKE/results/5xp1_mapA_mylib/alsrac/verilog/",
                    "CIRCUITOS-AMMES-MANSKE/results/5xp1_mapA_mylib/alsrac/aig/",
                    "genlibs/mylib.genlib");
        }

        System.out.println("Finished!");
        TimeUnit.MINUTES.sleep(130);*/

        CellLibrary cellLib = new CellLibrary("genlibs/mylib.genlib");
        ArrayList<BigDecimal> exactReliability = new ArrayList<>();

        ProbCircuit goldenCircuit = new CircuitFactory(cellLib, "CIRCUITOS-AMMES-MANSKE/results/5xp1_mapA_mylib/5xp1_mapA_mylib.v").getProbCircuit();


        //ArrayList<Path> circuits = ops.CommonOps.getAllVerilogCircuitsFromPath("approx-9sym");
        //ArrayList<Path> circuits = ops.CommonOps.getAllVerilogCircuitsFromPath("5xp1/E10/verilog/5xp1_just_crit_mult_dontcare");
        //ArrayList<Path> circuits = ops.CommonOps.getAllVerilogCircuitsFromPath("CIRCUITOS-AMMES-MANSKE/results/verilogs");
        ArrayList<Path> circuits = ops.CommonOps.getAllVerilogCircuitsFromPath("CIRCUITOS-AMMES-MANSKE/results/5xp1_mapA_mylib/alsrac/verilog");



        /**
         * Matheus 2023-08-14 Usei para tratar os arquivos inexistes do Ammes/Manske
         */
        /*
        for (int i = 1; i < 91; i++) {
            boolean notContains = true;
            String pattern = String.format("%02d-9sym_1_%02d", i, i);
            for(Path path: circuits) {
                if(path.getFileName().toString().contains(pattern)) {
                    notContains = false;
                }
            }
            if(notContains) {
                File fooFile = new File("verilogs/" +pattern+"TEMP.v");
                fooFile.createNewFile();
            }
        }
        ArrayList<Path> newList = ops.CommonOps.getAllVerilogCircuitsFromPath("verilogs");

        for(Path path : newList) {
            System.out.println(path);
        } */
        //ArrayList<String> truthTable = new ArrayList<>();
        ArrayList<String> truthTable = getProbCircuitTruthTableBySPR(goldenCircuit, cellLib);
        SPRController sprGold = new SPRController(goldenCircuit, cellLib);
        for(int i = 0; i<truthTable.size(); i++) {
            BigDecimal goldVectorReliability = sprGold.getReliability(Integer.toString(i), "0.99999802495", 15);
            exactReliability.add(i, goldVectorReliability);
        }


        for(Path path: circuits) {
            if(path.getFileName().toString().contains("TEMP")) {
                String fooName = path.getFileName().toString().split("TEMP")[0];
                String d = "***";
                System.out.println(String.format("%s %s %s %s %s %s %s %s %s %s", fooName,d,d,d,d,d,d,d,d,d));
            } else {
                ProbCircuit pCircuit = new CircuitFactory(cellLib, path.toString()).getProbCircuit();
                pCircuit.setName(path.getFileName().toString().split(".v")[0]);

                //if(path.getFileName().toString().contains("00-5xp1_exact_blif")) {
                 //   truthTable = getProbCircuitTruthTableBySPR(pCircuit, cellLib);
                    //for(String truth : truthTable) {
                    //    System.out.println(truth);
                    //}
                //}

                // Pega gates
                int gates = pCircuit.getGates().size();

                // Pega area
                float area = pCircuit.getTotalArea();

                // fanouts
                int fanouts = pCircuit.getFanouts().size();

                // levels
                int levels = pCircuit.getProbGateLevels().size();

                // itera SPR por vetor
                SPRController spr = new SPRController(pCircuit, cellLib);

                // vetores cobertos
                int coveredVectors = 0;

                // conf media
                BigDecimal averageReli = BigDecimal.ZERO;

                // conf itm exato
                BigDecimal averageItmExact = BigDecimal.ZERO;

                // conf exact + approx
                BigDecimal averageExactAndApprox = BigDecimal.ZERO;

                //reli covered vectors
                BigDecimal averageCoveredVectors = BigDecimal.ZERO;

                //average reliability, in exact 9sym, just for covered vectors
                BigDecimal coveredExact = BigDecimal.ZERO;

                //System.out.println(pCircuit);

                //System.out.println("-----------------------------------------------");

                for(int i = 0; i<truthTable.size(); i++) {

                    BigDecimal vectorReliability = spr.getReliability(Integer.toString(i), "0.99999802495", 15);
                    averageReli = averageReli.add(vectorReliability);


                    //BigDecimal[][] probMatrix = pCircuit.getProbOutputs().get(0).getProbMatrix();

                    boolean isSameLogicValue = CommonOps.sameLogicValue(pCircuit.getProbOutputs(), truthTable.get(i));

                    BigDecimal value = CommonOps.getExactLogicSignalProbability(pCircuit.getProbOutputs(), truthTable.get(i), isSameLogicValue);

                    averageItmExact = averageItmExact.add(value);

                    if(isSameLogicValue) {
                        coveredVectors = coveredVectors + 1;
                        averageExactAndApprox = averageExactAndApprox.add(vectorReliability);
                        averageCoveredVectors = averageCoveredVectors.add(vectorReliability);
                        coveredExact = coveredExact.add(exactReliability.get(i));

                    } else {
                        //System.out.println("Mamae --> " + path.toString() + " --> " + new InputVector(Integer.toString(i), pCircuit.getProbInputs().size()) + " ==> " + CommonOps.getOutputVector(pCircuit.getProbOutputs()) + " --> " + truthTable.get(i));
                        averageExactAndApprox = averageExactAndApprox.add(BigDecimal.ONE);
                        //System.out.println("Circuito: " + pCircuit + " -- vetor: " + i);
                    }

                }

                averageReli = averageReli.divide(new BigDecimal(Integer.toString(truthTable.size())), RoundingMode.HALF_UP);
                averageItmExact = averageItmExact.divide(new BigDecimal(Integer.toString(truthTable.size())), RoundingMode.HALF_UP);
                averageExactAndApprox = averageExactAndApprox.divide(new BigDecimal(Integer.toString(truthTable.size())), RoundingMode.HALF_UP);

                String saidaAverageExactAndApprox;
                String saidaAverageCoveredVectors;
                String saidaCoveredExact;

                if(averageExactAndApprox.compareTo(BigDecimal.ONE) == 0) {
                    saidaAverageExactAndApprox = "null";
                } else {
                    saidaAverageExactAndApprox = CommonOps.getMTBFBigInt(averageExactAndApprox).toString();
                }

                if(averageCoveredVectors.compareTo(BigDecimal.ZERO) != 0) {
                    if(coveredVectors > 0) {
                        averageCoveredVectors = averageCoveredVectors.divide(new BigDecimal(Integer.toString(coveredVectors)), RoundingMode.HALF_UP);
                        coveredExact = coveredExact.divide(new BigDecimal(Integer.toString(coveredVectors)), RoundingMode.HALF_UP);
                        saidaAverageCoveredVectors = CommonOps.getMTBFBigInt(averageCoveredVectors).toString();
                        saidaCoveredExact = CommonOps.getMTBFBigInt(coveredExact).toString();
                    } else {
                        saidaAverageCoveredVectors = "null";
                        saidaCoveredExact = "0";
                    }
                } else {
                    saidaAverageCoveredVectors = "null";
                    saidaCoveredExact = "null";
                }



                String outSTR = String.format("%s %d %d %d %d %d %s %s %s %s %s",
                        pCircuit.getName(),
                        gates,
                        (int)area,
                        fanouts,
                        levels,
                        coveredVectors,
                        CommonOps.getMTBFBigInt(averageReli).toString(),
                        CommonOps.getMTBFBigInt(averageItmExact).toString(),
                        saidaAverageExactAndApprox,
                        saidaAverageCoveredVectors,
                        saidaCoveredExact);

                System.out.println(outSTR);
            }
        }

        /*
        for (BigDecimal big : exact9sym) {
            mapper.put(counter, big);
            counter++;
        } */

        //plaManipulator.writePLA("9sym_crit_test.pla", pla);

        //ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh", "9sym_AMMES.pla 9sym_AMMES_ESPRESSO.pla");

        //System.out.println("-----------------------------------------------------------------------");

        //System.out.println("Aqui!");
        //exact9sym.remove(51);

        //System.out.println(CommonOps.getMTBFBigInt(CommonOps.getAverageValue(exact9sym)));
        //System.out.println(CommonOps.getAverageValue(exact9sym));
    }
    
    public void Foo7() throws IOException, Exception {

        //PLA pla = new PLAManipulator().readPLAFile("c17/exact/pla/c17_mapa_karnaugh_ESPRESSO.pla");
        PLA pla = new PLAManipulator().readPLAFile("5xp1/seeds/00-5xp1_fromBlif.pla");

        pla.addDontCareTerm("0010100");
        pla.addDontCareTerm("0110100");
        pla.addDontCareTerm("1111110");
        pla.addDontCareTerm("0001110");
        pla.addDontCareTerm("1110000");
        pla.addDontCareTerm("0011011");
        pla.addDontCareTerm("1000110");
        pla.addDontCareTerm("0011001");
        pla.addDontCareTerm("1110011");
        pla.addDontCareTerm("1010011");
        pla.addDontCareTerm("0001011");
        pla.addDontCareTerm("1000011");
        pla.addDontCareTerm("0010101");
        pla.addDontCareTerm("1010101");
        pla.addDontCareTerm("0011110");
        pla.addDontCareTerm("0001101");
        pla.addDontCareTerm("1101000");
        pla.addDontCareTerm("1100111");
        pla.addDontCareTerm("1111011");
        pla.addDontCareTerm("1101111");
        pla.addDontCareTerm("1101011");
        pla.addDontCareTerm("1101101");
        pla.addDontCareTerm("0111000");
        pla.addDontCareTerm("1110010");
        pla.addDontCareTerm("1100011");
        pla.addDontCareTerm("1011001");
        pla.addDontCareTerm("1111010");
        pla.addDontCareTerm("1011010");
        pla.addDontCareTerm("1101100");
        pla.addDontCareTerm("0101100");
        pla.addDontCareTerm("1100101");
        pla.addDontCareTerm("1100100");
        pla.addDontCareTerm("0011100");
        pla.addDontCareTerm("1001101");
        pla.addDontCareTerm("1010100");
        pla.addDontCareTerm("0101010");
        pla.addDontCareTerm("0111010");
        pla.addDontCareTerm("1111101");
        pla.addDontCareTerm("1010110");
        pla.addDontCareTerm("1001100");
        pla.addDontCareTerm("0111110");
        pla.addDontCareTerm("1111001");
        pla.addDontCareTerm("1110001");
        pla.addDontCareTerm("1100110");
        pla.addDontCareTerm("1110101");
        pla.addDontCareTerm("1101110");
        pla.addDontCareTerm("0111001");
        pla.addDontCareTerm("0011101");
        pla.addDontCareTerm("1011011");
        pla.addDontCareTerm("1001011");
        pla.addDontCareTerm("1111000");
        pla.addDontCareTerm("0011111");
        pla.addDontCareTerm("1011111");
        pla.addDontCareTerm("1011101");
        pla.addDontCareTerm("1111100");
        pla.addDontCareTerm("0111100");
        pla.addDontCareTerm("0101110");
        pla.addDontCareTerm("1110100");
        pla.addDontCareTerm("0001111");
        pla.addDontCareTerm("1101010");
        pla.addDontCareTerm("1001110");
        pla.addDontCareTerm("1001111");
        pla.addDontCareTerm("1011100");
        pla.addDontCareTerm("1011110");



        PLA newPLA = PLAOps.getApproxPLAWithDontCarePerOutput(pla);

        newPLA = PLAOps.getMinimizedPLA(newPLA);

        PLAOps.writePLA("TEMP/5xp1_crit_approx_method_64_break_cubes.pla", newPLA);
    }
    
    public void Foo8() throws IOException, Exception {


        /**
         * 2023-10-11 Experimento para tentar entender o método PGM
         */


        CellLibrary cellLib = new CellLibrary("genlibs/basic.genlib");

        ProbCircuit goldenCircuit = new CircuitFactory(cellLib, "c17_six_nand.v").getProbCircuit();

        SPRController sprGold = new SPRController(goldenCircuit, cellLib);

        System.out.println("Confiabilidade média pelo SPR (one pass)");
        System.out.println(sprGold.getReliability("0.95", 8));
        System.out.println(String.format("Matriz de sinal output %s: ", goldenCircuit.getProbOutputs().get(0)));
        matrixPrint(goldenCircuit.getProbOutputs().get(0).getProbMatrix(), 4);
        System.out.println(String.format("Matriz de sinal output %s: ", goldenCircuit.getProbOutputs().get(1)));
        matrixPrint(goldenCircuit.getProbOutputs().get(1).getProbMatrix(), 4);
        System.out.println("-------------------------");

        System.out.println("ANÁLISE POR VETOR:");
        BigDecimal bigCounter = BigDecimal.ZERO;

        for(int i = 0; i<32; i++) {
            BigDecimal goldVectorReliability = sprGold.getReliability(Integer.toString(i), "0.95", 4);
            System.out.println(new InputVector(Integer.toString(i), goldenCircuit.getProbInputs().size()));
            System.out.println(String.format("Matriz de sinal output %s: ", goldenCircuit.getProbOutputs().get(0)));
            matrixPrint(goldenCircuit.getProbOutputs().get(0).getProbMatrix(), 4);
            System.out.println(String.format("Matriz de sinal output %s: ", goldenCircuit.getProbOutputs().get(1)));
            matrixPrint(goldenCircuit.getProbOutputs().get(1).getProbMatrix(), 4);
            bigCounter = bigCounter.add(goldVectorReliability);
            System.out.println("-----------------------------------------");
        }

        System.out.println("Conf media dos vetores: " + bigCounter.divide(new BigDecimal("32")));


        /**
         * C17 six-nanded PTMM per input vector analysis
         */
        goldenCircuit = new CircuitFactory(cellLib, "c17_six_nand.v").getProbCircuit();

        PTMMController ptmmC = new PTMMController(goldenCircuit, cellLib);

        System.out.println("Confiabilidade média pelo PTMM ");
        System.out.println(ptmmC.getReliability("0.95"));

        System.out.println("-------------------------");

        System.out.println("ANÁLISE POR VETOR:");
        bigCounter = BigDecimal.ZERO;

        for(int i = 0; i<32; i++) {

            //BigDecimal goldVectorReliability = sprGold.getReliability(Integer.toString(i), "0.95", 4);
            InputVector vector = new InputVector(Integer.toString(i), goldenCircuit.getProbInputs().size());

            BigDecimal goldVectorReliability = ptmmC.getReliability(vector, "0.95");
            System.out.println(vector + " --> " + goldVectorReliability);


            bigCounter = bigCounter.add(goldVectorReliability);
            //System.out.println("-----------------------------------------");
        }

        System.out.println("Conf media dos vetores: " + bigCounter.divide(new BigDecimal("32")));


        TimeUnit.MINUTES.sleep(130);

        Map<String, BigDecimal[][]> schivittzCells = new HashMap<>();
        
        String[] circuits = new String[]{            
                   
//            "c17Classic.v",
//            "c432_cadence.v",
//            "c499_cadence.v",
//            "c880_cadence.v",
//            "c1355_cadence.v",
//            "c1908_cadence.v",
//            "c2670_cadence.v",
//            "c3540_cadence.v",
//            "c5315_cadence.v",
//            "c6288_cadence.v",
//            "c7552_cadence.v",
            "c2670_schiv.v",
        };
        
        BigDecimal classicReliability = new BigDecimal("0.999999");
        String schivittzCellsFile = "15nm.txt";
        
        
        schivittzCells = ReadTxt.readSchivittzCells(schivittzCellsFile);
        Terminal.getInstance().getCellLibrary().setPTMCells(classicReliability);
        
//        for(Cell cell: Terminal.getInstance().getCellLibrary().getCells()) {
//            System.out.println("Cell: " + cell.getName());
//            for (int i = 0; i < cell.getPTM().length; i++) {
//                for (int j = 0; j < cell.getPTM()[0].length; j++) {
//                    System.out.println("[" + i + "]["+j+"] == " + cell.getPTM()[i][j]);
//                }
//            }
//        }
//        
//        System.out.println("############################");
//        for (String porta: schivittzCells.keySet()) {
//            BigDecimal[][] fooMatrix = schivittzCells.get(porta);
//            System.out.println("Cell: " + porta);
//            for (int i = 0; i < fooMatrix.length; i++) {
//                for (int j = 0; j < fooMatrix[0].length; j++) {
//                    System.out.println("[" + i + "]["+j+"] == " + fooMatrix[i][j]);
//                }
//            }
//        }
        
        for (int j = 0; j < circuits.length; j++) {
            try {
                Terminal.getInstance().executeCommand("read_verilog "+circuits[j]);
            } catch (ScriptException ex) {
                Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
            }            
            ProbCircuit pCircuit = new ProbCircuit(Terminal.getInstance().getCircuit());
            
            System.out.println("Circuit: " + pCircuit.getName());
            
            for (int i = 0; i < pCircuit.getProbGates().size(); i++) {
                String cellName = pCircuit.getProbGates().get(i).getType().getName();
                
                if(schivittzCells.get(cellName) != null) {
                    pCircuit.getProbGates().get(i).setReliabilityMatrix(schivittzCells.get(cellName));                
//                    System.out.println("CellName: " + cellName);                
//                    for (int k = 0; k < pCircuit.getProbGates().get(i).getReliabilityMatrix().length; k++) {
//                        for (int l = 0; l < pCircuit.getProbGates().get(i).getReliabilityMatrix()[0].length; l++) {
//                            System.out.println(k + "--" + l + " ==> " + pCircuit.getProbGates().get(i).getReliabilityMatrix()[k][l]);
//                        }
//                    }
                }               
            }
            
            /*
            ** Coloca 0.5 nas probabilidades de 0 e 1 corretos nos sinais de entrada
            */
            pCircuit.setDefaultProbSourceSignalMatrix();            
            
            /*
            ** Coloca as matrizes PTM nas portas, somente se essa ainda não tiver setada
            */
            pCircuit.setPTMsReliabilityMatrix();
            
            System.out.println("SPR Schivittz: " + SPROps.getSPRReliability(pCircuit));
            
            /*
            ** Seta "null" nas matrizes dos sinais
            */
            pCircuit.clearProbSignalsMatrix();
            
            pCircuit.setDefaultProbSourceSignalMatrix();            
            
            /*
            ** Seta "null" nas matrizes de confiabilidade das portas
            */
            pCircuit.clearProbGatesReliabilitiesMatrix();
            pCircuit.setPTMsReliabilityMatrix();
            
            System.out.println("SPR Normal:    " + SPROps.getSPRReliability(pCircuit));
            System.out.println("");
        }
    }
    
    
    public void Foo9() throws ScriptException, IOException, Exception {

        //Parameters
        String mainlyPath = "CIRCUITOS-AMMES-MANSKE";
        String resultPath = String.format("%s/results", mainlyPath);
        String genlibPath = "genlibs/mylib.genlib";

        ArrayList<java.nio.file.Path> paths = new ArrayList<>();

        Files.list(Paths.get(String.format("%s/seeds/verilog", mainlyPath))).sorted().forEach(path -> paths.add(path));

        //Iterate over exact circuits
        for (Path p : paths) {

            //Local parameters
            String circuitName = p.getFileName().toString().split(".v")[0];

            //Creates circuits main folder
            File mainCircuitFolder = new File(String.format("%s/%s", resultPath, circuitName));
            String mainCircuitFolderPath = mainCircuitFolder.getAbsolutePath();
            mainCircuitFolder.mkdir();

            //Create a circuit copy inside main folder AND
            //Create the exact PLA seed
            Path exactCopy = Paths.get(String.format("%s/%s", mainCircuitFolderPath, p.getFileName().toString()));
            Files.copy(p, exactCopy, StandardCopyOption.REPLACE_EXISTING);
            String plaSeedPath = String.format("%s/%s.pla", mainCircuitFolderPath, circuitName);
            String plaSeedEspressoPath = String.format("%s/%s_ESPRESSO.pla", mainCircuitFolderPath, circuitName);
            ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                    String.format("MAPPED_VERILOG_TO_PLA %s %s %s %s",
                            genlibPath,
                            exactCopy.toString(),
                            plaSeedPath,
                            plaSeedEspressoPath));


            //Make approx methods folders
            ArrayList<String> approxMethodsFoldersNames = new ArrayList<>();
            approxMethodsFoldersNames.add(String.format("%s/%s_just_crit_per_output",
                    mainCircuitFolderPath, circuitName));
            approxMethodsFoldersNames.add(String.format("%s/%s_just_crit_multi_output",
                    mainCircuitFolderPath, circuitName));
            approxMethodsFoldersNames.add(String.format("%s/%s_track_crit_per_output",
                    mainCircuitFolderPath, circuitName));
            approxMethodsFoldersNames.add(String.format("%s/%s_track_crit_multi_output",
                    mainCircuitFolderPath, circuitName));
            approxMethodsFoldersNames.add(String.format("%s/%s_track_crit_same_seed_per_output",
                    mainCircuitFolderPath, circuitName));
            approxMethodsFoldersNames.add(String.format("%s/%s_track_crit_same_seed_multi_output",
                    mainCircuitFolderPath, circuitName));

            for(String folder : approxMethodsFoldersNames) {
                new File(folder).mkdir();
                // make AIG, PLA and VERILOG folder
                new File(folder + "/aig").mkdir();
                new File(folder + "/pla").mkdir();
                new File(folder + "/verilog").mkdir();
            }

            //Prepare to run approx methods

            ProbCircuit exactCircuit = new CircuitFactory(new CellLibrary(genlibPath), p.toString()).getProbCircuit();
            //int totalAproxVector = (int)Math.round(exactCircuit.getTotalInputVectors().intValue() * 0.82);
            int totalAproxVector = (int)Math.round(exactCircuit.getTotalInputVectors().intValue() * 0.2);

            /*String methodJustCritPerOutBasename = Paths.get(approxMethodsFoldersNames.get(0)).getFileName().toString();
            String methodJustCritMultOutBasename = Paths.get(approxMethodsFoldersNames.get(1)).getFileName().toString();
            String methodTrackCritPerOutBasename = Paths.get(approxMethodsFoldersNames.get(2)).getFileName().toString();
            String methodTrackCritMultOutBasename = Paths.get(approxMethodsFoldersNames.get(3)).getFileName().toString();
            String methodTrackCritSeedPerOutBasename = Paths.get(approxMethodsFoldersNames.get(4)).getFileName().toString();
            String methodTrackCritSeedMultOutBasename = Paths.get(approxMethodsFoldersNames.get(5)).getFileName().toString();*/


            for (String folder : approxMethodsFoldersNames) {

                String approxCircuitSuffix = Paths.get(folder).getFileName().toString();

                int length = String.valueOf(exactCircuit.getTotalInputVectors().intValue()).length();



                for (int i = 1; i < totalAproxVector; i++) {

                    String patternName = String.format("%0" + length + "d-%s", i, approxCircuitSuffix);
                    String aigOutput = String.format("%s/aig/%s.aig", folder, patternName);
                    String verilogOutput = String.format("%s/verilog/%s.v", folder, patternName);
                    String plaOutput = String.format("%s/pla/%s.pla", folder, patternName);
                    String plaESPRESSOOutput = String.format("%s/pla/%s_ESPRESSO.pla", folder, patternName);

                    ApproxOPS.approxMethodWrapper(ApproxOPS.getApproxMethod(approxCircuitSuffix),
                            plaSeedEspressoPath,
                            exactCopy.toString(),
                            genlibPath,
                            aigOutput,
                            plaOutput,
                            plaESPRESSOOutput,
                            verilogOutput,
                            "CRITICAL",
                            i);

                    //JUST CRIT PER OUTPUT
                    /*ApproxOPS.justCriticalVectorsApprox(plaSeedEspressoPath,
                            exactCopy.toString(),
                            genlibPath,
                            aigOutput,
                            plaOutput,
                            plaESPRESSOOutput,
                            verilogOutput,
                            "CRITICAL",
                            false,
                            i);*/


                }

            }
        }

        System.out.println("Finished! Here!");
        TimeUnit.MINUTES.sleep(130);

        String seedName = "000-5xp1_fromBlif";

        String exactVerilog = String.format("5xp1/seeds/verilog/%s.v", seedName);
        String plaSeed = String.format("5xp1/seeds/pla/%s.pla", seedName);



        String criticalVectorsMethod = "RELIABLE";
        String circuitAndApproxMethod = "5xp1_just_crit_RELIABLE_mult_dontcare";
        //String circuitAndApproxMethod = "5xp1_just_crit_RELIABLE_per_output_dontcare";
        boolean multDontCare = true;
        String rootPath = "5xp1/E10";
        PLA pla;

        // aqui aproximar até 80% dos vetores
        for (int i = 1; i < 127; i++) {

            String pattern = String.format("%03d-%s", i, circuitAndApproxMethod);

            String aigOutput = String.format("%s/aig/%s/%s.aig", rootPath, circuitAndApproxMethod, pattern);
            String verilogOutput = String.format("%s/verilog/%s/%s.v", rootPath, circuitAndApproxMethod, pattern);
            String plaOutput = String.format("%s/pla/%s/%s.pla", rootPath, circuitAndApproxMethod, pattern);
            String plaESPRESSOOutput = String.format("%s/pla/%s/%s_ESPRESSO.pla", rootPath, circuitAndApproxMethod, pattern);

            ApproxOPS.justCriticalVectorsApprox(plaSeed,
                    exactVerilog,
                    "genlibs/mylib.genlib",
                    aigOutput,
                    plaOutput,
                    plaESPRESSOOutput,
                    verilogOutput,
                    criticalVectorsMethod,
                    multDontCare,
                    i);
        }

    }

    public void Foo10() throws Exception {

        final long startTime = System.currentTimeMillis();

        //Parameters
        String mainlyPath = "CIRCUITOS-AMMES-MANSKE";
        String resultPath = String.format("%s/results", mainlyPath);
        String genlibPath = "genlibs/asap7_RVT_TT_ccs_ABC.genlib";

        CellLibrary cellLib = new CellLibrary(genlibPath);
        ArrayList<java.nio.file.Path> circuits = new ArrayList<>();

        List<String> exceptions = Arrays.asList(
                //"5xp1",
                //"9sym",
                "alu4",
                //"apex",
                //"b12",
                "clip",
                //"ex1010",
                //"intb",
                //"max1024",
                "misex"
                //"sao2",
                //"t481"
        );


        Files.list(Paths.get(String.format("%s/seeds/verilog", mainlyPath))).sorted().forEach(path -> circuits.add(path));

        for(Path circuit : circuits) {

            //Verify if current circuit is in exception list
            boolean flagException = false;
            for (String ex : exceptions) {
                if(circuit.toString().toLowerCase().contains(ex)) {
                    flagException = true;
                }
            }
            if(!flagException) {
                String circuitName = circuit.getFileName().toString().split(".v")[0];

                ProbCircuit exactSeedCircuit = new CircuitFactory(cellLib, circuit.toString()).getProbCircuit();

                HashMap<String, String> truthTablesList = new HashMap<>();
                String jsonPath = String.format("%s/seeds/truthTable/%s.json", mainlyPath, circuitName);
                truthTablesList = CriticalVectorsUtils.inputOutputcombinationFromJSON(jsonPath);


                String alsracMainFolder = String.format("%s/%s/alsrac", resultPath, circuitName);

                ArrayList<java.nio.file.Path> verilogPaths = new ArrayList<>();
                ArrayList<java.nio.file.Path> alsracCandidates = new ArrayList<>();

                ArrayList<String> alsracSeedCircuitsNames = new ArrayList<>(Arrays.asList(
                        "05P---t481_0.00541016_1.4_158.23",
                        "10P---t481_0.0144336_1.47_144.67",
                        "20P---t481_0.187754_0.8_94.09"));



                Files.list(Paths.get(String.format("%s/verilog", alsracMainFolder))).sorted().forEach(path -> verilogPaths.add(path));

                for (Path verilog : verilogPaths) {
                    for(String s : alsracSeedCircuitsNames) {
                        if(verilog.toString().contains(s)) {
                            alsracCandidates.add(verilog);
                        }
                    }
                }

                for(Path candidate : alsracCandidates) {

                    String approxName = candidate.getFileName().toString().replace(".v", "");

                    ProbCircuit approxCircuit = new CircuitFactory(cellLib, candidate.toString()).getProbCircuit();

                    //HashMap<String, String> truthTablesListSubCircuit = new HashMap<>();
                    ArrayList<InputVector> truthTablesListSubCircuit = new ArrayList<>();
                    ArrayList<InputVector> uncoveredVectors = new ArrayList<>();

                    for (int i = 0; i < approxCircuit.getTotalInputVectors().intValue(); i++) {
                        InputVector inputV = new InputVector(i, approxCircuit.getProbInputs().size());
                        ArrayList<Boolean> outputVector = approxCircuit.propagateInputVector(inputV);
                        String bString = CriticalVectorsUtils.boolArrayToBinaryString(outputVector);

                        inputV.setOutputBinaryString(bString);

                        //truthTablesListSubCircuit.put(inputV.getBinaryString(), bString);
                        truthTablesListSubCircuit.add(inputV);


                    }



                    for (InputVector inputV : truthTablesListSubCircuit) {
                        if(!truthTablesList.get(inputV.getBinaryString()).equals(inputV.getOutputBinaryString())) {

                            uncoveredVectors.add(inputV);
                        }
                    }

                    System.out.println(approxName + " ==> " + uncoveredVectors.size() + " unconvered vectors");

                    boolean plaOriginalFlag;
                    boolean withDontCare;

                    String alsracXplaMainFolderPath = "CIRCUITOS-AMMES-MANSKE/results/t481_asap7_RVT_TT_ccs_ABC/alsracXpla/";

                    //make folder

                    File mainlyAlsracXplaFolder = new File(alsracXplaMainFolderPath + approxName);
                    File alsracXplaAigFolder = new File(alsracXplaMainFolderPath + approxName + "/aig");
                    File alsracXplaPLAfolder = new File(alsracXplaMainFolderPath + approxName + "/pla");
                    File alsracXplaVerilogFolder = new File(alsracXplaMainFolderPath + approxName + "/verilog");

                    mainlyAlsracXplaFolder.mkdir();
                    alsracXplaAigFolder.mkdir();
                    alsracXplaPLAfolder.mkdir();
                    alsracXplaVerilogFolder.mkdir();


                    Path plaOriginal = Paths.get("CIRCUITOS-AMMES-MANSKE/seeds/pla/t481_ESPRESSO.pla");
                    Path plaSeedAlsrac = Paths.get(candidate.getParent().getParent().toString() + "/pla/" + approxName + "_ESPRESSO.pla");

                    String plaSeedName;
                    String approxTermsOutput;

                    String newPlaName;

                    Path currentPlaSeed;

                    for(int o = 0; o < 2; o++) {
                        for(int d = 0; d < 2; d++) {
                            if(o == 0) {
                                plaOriginalFlag = false;
                                plaSeedName = "alsrac";
                                currentPlaSeed = plaSeedAlsrac;
                            } else {
                                plaOriginalFlag = true;
                                plaSeedName = "original";
                                currentPlaSeed = plaOriginal;
                            }

                            if(d == 0) {
                                withDontCare = false;
                                approxTermsOutput = "with_approx_fixed_output";
                            } else {
                                withDontCare = true;
                                approxTermsOutput = "with_dont_cares";
                            }

                            newPlaName = String.format("t481_%s_SEED_from_%s_UNCOVERED_VECTORS_%s", plaSeedName, approxName, approxTermsOutput);

                            String plaOutputPath = String.format("%s/%s.pla", alsracXplaPLAfolder.getAbsolutePath(), newPlaName);
                            String EspressoOutputPath = String.format("%s/%s_ESPRESSO.pla", alsracXplaPLAfolder.getAbsolutePath(), newPlaName);
                            String aigOutputPath = String.format("%s/%s.aig", alsracXplaAigFolder.getAbsolutePath(), newPlaName);
                            String verilogOutputPath = String.format("%s/%s.v", alsracXplaVerilogFolder.getAbsolutePath(), newPlaName);


                            ApproxOPS.insertInputVectorListandOutoutsInPLADeployAigVerilog(
                                    currentPlaSeed.toString(),
                                    plaOutputPath,
                                    EspressoOutputPath,
                                    genlibPath,
                                    aigOutputPath,
                                    verilogOutputPath,
                                    uncoveredVectors,
                                    withDontCare);
                        }
                    }





                }


                System.out.println("Finished!");
            }


        }


        final long endTime = System.currentTimeMillis();

        long secondstimestamp = (endTime - startTime)/1000;

        String timeConsup = "## TIME CONSUPTION ## ==> " + secondstimestamp + " secs";
        System.out.println(timeConsup);

    }


    public void MakeALSRACBlifResults() throws Exception {

        final long startTime = System.currentTimeMillis();

        //Parameters
        String mainlyPath = "CIRCUITOS-AMMES-MANSKE";
        String resultPath = String.format("%s/results", mainlyPath);
        String genlibPath = "genlibs/asap7_RVT_TT_ccs_ABC.genlib";

        CellLibrary cellLib = new CellLibrary(genlibPath);
        ArrayList<java.nio.file.Path> circuits = new ArrayList<>();

        List<String> exceptions = Arrays.asList(
                //"5xp1",
                //"9sym",
                "alu4",
                //"apex",
                //"b12",
                "clip",
                //"ex1010",
                //"intb",
                //"max1024",
                "misex"
                //"sao2",
                //"t481"
        );


        Files.list(Paths.get(String.format("%s/seeds/verilog", mainlyPath))).sorted().forEach(path -> circuits.add(path));

        for(Path circuit : circuits) {

            //Verify if current circuit is in exception list
            boolean flagException = false;
            for (String ex : exceptions) {
                if(circuit.toString().toLowerCase().contains(ex)) {
                    flagException = true;
                }
            }
            if(!flagException) {
                String circuitName = circuit.getFileName().toString().split(".v")[0];



                String alsracMainFolder = String.format("%s/%s/alsrac", resultPath, circuitName);

                ArrayList<java.nio.file.Path> paths = new ArrayList<>();

                // make AIG, PLA and VERILOG folder
                new File(String.format("%s/aig", alsracMainFolder)).mkdir();
                new File(String.format("%s/pla", alsracMainFolder)).mkdir();
                new File(String.format("%s/verilog", alsracMainFolder)).mkdir();

                Files.list(Paths.get(String.format("%s/blif", alsracMainFolder))).sorted().forEach(path -> paths.add(path));

                for (Path blif : paths) {
                    String fileName = blif.getFileName().toString().split(".blif")[0];
                    String aigOutputPath = String.format("%s/aig/%s.aig", blif.getParent().getParent().toString(), fileName);
                    String verilogOutputPath = String.format("%s/verilog/%s.v", blif.getParent().getParent().toString(), fileName);
                    String plaPath = String.format("%s/pla/%s.pla", blif.getParent().getParent().toString(), fileName);
                    String plaESPRESSOPath = String.format("%s/pla/%s_ESPRESSO.pla", blif.getParent().getParent().toString(), fileName);

                    String command = String.format("ALSRAC_BLIF_AIG_PLA_ESPRESSO %s %s %s %s %s %s",
                            genlibPath,
                            blif.toString(),
                            aigOutputPath,
                            verilogOutputPath,
                            plaPath,
                            plaESPRESSOPath);

                    Object[] result = ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                            command);

                }

                System.out.println("Finished!");
            }


        }

        /*ArrayList<java.nio.file.Path> paths = new ArrayList<>();

        Files.list(Paths.get("CIRCUITOS-AMMES-MANSKE/results/5xp1_mapA_mylib/alsrac/blif/")).sorted().forEach(path -> paths.add(path));

        for (Path path : paths) {
            String fileName = path.getFileName().toString().split(".blif")[0];


            ShellScriptOps.deployBLIFAigVerilog( fileName,
                    path.toString(),
                    "CIRCUITOS-AMMES-MANSKE/results/5xp1_mapA_mylib/alsrac/verilog/",
                    "CIRCUITOS-AMMES-MANSKE/results/5xp1_mapA_mylib/alsrac/aig/",
                    "genlibs/mylib.genlib");
        }

        System.out.println("Finished!");*/


        final long endTime = System.currentTimeMillis();

        long secondstimestamp = (endTime - startTime)/1000;

        String timeConsup = "## TIME CONSUPTION ## ==> " + secondstimestamp + " secs";
        System.out.println(timeConsup);

    }
    public void AnalyseApproxVersions() throws Exception {

        final long startTime = System.currentTimeMillis();

        List<String> exceptions = Arrays.asList(
                //"5xp1",
                //"9sym",
                "alu4",
                //"apex",
                //"b12",
                "clip",
                //"ex1010",
                //"intb",
                //"max1024",
                "misex"
                //"sao2",
                //"t481"
        );

        //Parameters
        String mainlyPath = "CIRCUITOS-AMMES-MANSKE";
        String resultPath = String.format("%s/results", mainlyPath);
        String genlibPath = "genlibs/asap7_RVT_TT_ccs_ABC.genlib";

        CellLibrary cellLib = new CellLibrary(genlibPath);
        ArrayList<java.nio.file.Path> circuits = new ArrayList<>();

        Files.list(Paths.get(String.format("%s/seeds/verilog", mainlyPath))).sorted().forEach(path -> circuits.add(path));

        for(Path p : circuits) {

            //Verify if current circuit is in exception list
            boolean flagException = false;
            for (String ex : exceptions) {
                if(p.toString().toLowerCase().contains(ex)) {
                    flagException = true;
                }
            }
            if(!flagException) {
                String circuitName = p.getFileName().toString().split(".v")[0];

                ProbCircuit exactSeedCircuit = new CircuitFactory(cellLib, p.toString()).getProbCircuit();
                int[] gatesAndConstAmount = exactSeedCircuit.getProbGatesAndConstAmount();

                //System.out.println(circuitName + " ==> " + truthTablesList.keySet().size());


                SPRController sprController = new SPRController(exactSeedCircuit, cellLib);
                BigInteger mtbf = CommonOps.getMTBFBigInt(sprController.getReliability("0.99999802495", 25));


                // PRINT MAPPED VERILOG REFERENCE
                System.out.println(String.format("%s %d/%d %d %d %s **** **** %s %d %d",
                        exactSeedCircuit.getName(),
                        exactSeedCircuit.getProbInputs().size(),
                        exactSeedCircuit.getProbOutputs().size(),
                        gatesAndConstAmount[0],
                        gatesAndConstAmount[1],
                        String.format("%.2f", exactSeedCircuit.getTotalArea()).replace(',', '.'),
                        mtbf.toString(),
                        exactSeedCircuit.getProbGateLevels().size(),
                        exactSeedCircuit.getFanouts().size()
                ));

            /*String approxRefMid = String.format("%s/%s/%s", resultPath, circuitName, circuitName);
            String aproxRefPath = String.format("%s_fromPLA.v", approxRefMid);

            ProbCircuit approxRefCircuit = new CircuitFactory(cellLib, aproxRefPath).getProbCircuit();*/

                HashMap<String, String> truthTablesList = new HashMap<>();

                String jsonPath = String.format("%s/seeds/truthTable/%s.json", mainlyPath, circuitName);
                truthTablesList = CriticalVectorsUtils.inputOutputcombinationFromJSON(jsonPath);

            /*int[] gatesAndConstAmountApproxRef = approxRefCircuit.getProbGatesAndConstAmount();

            SPRController sprControllerApproxRef = new SPRController(approxRefCircuit, cellLib);
            BigInteger mtbfApproxRef = CommonOps.getMTBFBigInt(sprControllerApproxRef.getReliability("0.99999802495", 25));*/



                // PRINT MAPPED FROM ABC PLA VERILOG REFERENCE
            /*System.out.println(String.format("%s %d/%d %d %d %s **** **** %s %d %d",
                    approxRefCircuit.getName(),
                    approxRefCircuit.getProbInputs().size(),
                    approxRefCircuit.getProbOutputs().size(),
                    gatesAndConstAmountApproxRef[0],
                    gatesAndConstAmountApproxRef[1],
                    String.format("%.2f", approxRefCircuit.getTotalArea()).replace(',', '.'),
                    mtbfApproxRef.toString(),
                    approxRefCircuit.getProbGateLevels().size(),
                    approxRefCircuit.getFanouts().size()
            ));*/


                ArrayList<java.nio.file.Path> subCircuits = new ArrayList<>();

                /*//Multi-output
                Files.list(Paths.get(String.format("%s/%s/%s_just_crit_multi_output/verilog", resultPath, circuitName, circuitName))).sorted().forEach(path -> subCircuits.add(path));

                //Per-output
                Files.list(Paths.get(String.format("%s/%s/%s_just_crit_per_output/verilog", resultPath, circuitName, circuitName))).sorted().forEach(path -> subCircuits.add(path));

                //ALSRAC
                Files.list(Paths.get(String.format("%s/%s/alsrac/verilog", resultPath, circuitName))).sorted().forEach(path -> subCircuits.add(path));

                //ALSRAC-x-PLA--05P
                Files.list(Paths.get(String.format("%s/%s/alsracXpla/05P---t481_0.00541016_1.4_158.23/verilog", resultPath, circuitName))).sorted().forEach(path -> subCircuits.add(path));

                //ALSRAC-x-PLA--10P
                Files.list(Paths.get(String.format("%s/%s/alsracXpla/10P---t481_0.0144336_1.47_144.67/verilog", resultPath, circuitName))).sorted().forEach(path -> subCircuits.add(path));*/

                //ALSRAC-x-PLA--20P
                Files.list(Paths.get(String.format("%s/%s/alsracXpla/20P---t481_0.187754_0.8_94.09/verilog", resultPath, circuitName))).sorted().forEach(path -> subCircuits.add(path));

                for (Path pt : subCircuits) {

                    String approxName = pt.getFileName().toString().replace(".v", "");

                    ProbCircuit approxCircuit = new CircuitFactory(cellLib, pt.toString()).getProbCircuit();

                    HashMap<String, String> truthTablesListSubCircuit = new HashMap<>();

                    for (int i = 0; i < approxCircuit.getTotalInputVectors().intValue(); i++) {
                        InputVector inputV = new InputVector(i, approxCircuit.getProbInputs().size());
                        ArrayList<Boolean> outputVector = approxCircuit.propagateInputVector(inputV);
                        String bString = CriticalVectorsUtils.boolArrayToBinaryString(outputVector);

                        truthTablesListSubCircuit.put(inputV.getBinaryString(), bString);
                    }

                    int counter = 0;

                    for (String keyApprox : truthTablesListSubCircuit.keySet()) {
                        if(!truthTablesList.get(keyApprox).equals(truthTablesListSubCircuit.get(keyApprox))) {

                            counter = counter + 1;

                            //if(pt.getFileName().toString().toLowerCase().contains("00263672_1.84")) {
                            if(pt.getFileName().toString().toLowerCase().contains("0.210811_0.68")) {
                                System.out.println(String.format("%s %s", keyApprox, truthTablesList.get(keyApprox)));
                            }
                        }
                    }
                    //System.out.println("SubCircuit " + approxCircuit.getName() + " has " + counter + " uncovered Vectors of " + exactSeedCircuit.getTotalInputVectors());
                    int[] gatesAmount2 = approxCircuit.getProbGatesAndConstAmount();

                    String mtbf2;

                    if(gatesAmount2[0] == 0) {
                        mtbf2 = "infinity";
                    } else {
                        SPRController sprController2 = new SPRController(approxCircuit, cellLib);
                        mtbf2 = CommonOps.getMTBFBigInt(sprController2.getReliability("0.99999802495", 25)).toString();
                    }

                    BigDecimal test = new BigDecimal(Integer.toString(counter));
                    test = test.divide(new BigDecimal(approxCircuit.getTotalInputVectors()));
                    test = test.multiply(new BigDecimal("100")).setScale(2, RoundingMode.CEILING);


                    System.out.println(String.format("%s %d/%d %d %d %s %s %d %s %d %d",
                            approxName,
                            approxCircuit.getProbInputs().size(),
                            approxCircuit.getProbOutputs().size(),
                            gatesAmount2[0],
                            gatesAmount2[1],
                            String.format("%.2f", approxCircuit.getTotalArea()).replace(',', '.'),
                            test.toString(),
                            counter,
                            mtbf2.toString(),
                            approxCircuit.getProbGateLevels().size(),
                            approxCircuit.getFanouts().size()
                    ));

                }

                System.out.println("####################################################################");



                ArrayList<java.nio.file.Path> subCircuitsPLA = new ArrayList<>();

                //Seed
                //subCircuitsPLA.add(Paths.get(String.format("%s/%s/%s_ESPRESSO.pla", resultPath, circuitName, circuitName)));
                subCircuitsPLA.add(Paths.get(String.format("%s/seeds/pla/%s_ESPRESSO.pla", mainlyPath, circuitName.split("_")[0])));

                //Multi-output
                /*Files.list(Paths.get(String.format("%s/%s/%s_just_crit_multi_output/pla", resultPath, circuitName, circuitName))).sorted().forEach(path -> {
                    if(path.toString().toLowerCase().contains("espresso")) {
                        subCircuitsPLA.add(path);
                    }
                });

                //Per-output
                Files.list(Paths.get(String.format("%s/%s/%s_just_crit_per_output/pla", resultPath, circuitName, circuitName))).sorted().forEach(path -> {
                    if(path.toString().toLowerCase().contains("espresso")) {
                        subCircuitsPLA.add(path);
                    }
                });

                //ALSRAC
                Files.list(Paths.get(String.format("%s/%s/alsrac/pla", resultPath, circuitName))).sorted().forEach(path -> {
                    if(path.toString().toLowerCase().contains("espresso")) {
                        subCircuitsPLA.add(path);
                    }
                });

                //ALSRAC-x-PLA---05P
                Files.list(Paths.get(String.format("%s/%s/alsracXpla/05P---t481_0.00541016_1.4_158.23/pla", resultPath, circuitName))).sorted().forEach(path -> {
                    if(path.toString().toLowerCase().contains("espresso")) {
                        subCircuitsPLA.add(path);
                    }
                });

                //ALSRAC-x-PLA---10P
                Files.list(Paths.get(String.format("%s/%s/alsracXpla/10P---t481_0.0144336_1.47_144.67/pla", resultPath, circuitName))).sorted().forEach(path -> {
                    if(path.toString().toLowerCase().contains("espresso")) {
                        subCircuitsPLA.add(path);
                    }
                }); */

                //ALSRAC-x-PLA---20P
                Files.list(Paths.get(String.format("%s/%s/alsracXpla/20P---t481_0.187754_0.8_94.09/pla", resultPath, circuitName))).sorted().forEach(path -> {
                    if(path.toString().toLowerCase().contains("espresso")) {
                        subCircuitsPLA.add(path);
                    }
                });

                for(Path plaPath : subCircuitsPLA) {
                    PLA currentPLA = PLAOps.readPLAFile(plaPath.toString());
                    int[] literals = currentPLA.countingAllInputLiterals();

                    String plaInOUt = String.format("%d/%d", currentPLA.getQtInputs(), currentPLA.getQtOutputs());

                    String plaTableLine = String.format("%s %s %d %d %d %d",
                            plaPath.getFileName().toString(),
                            plaInOUt,
                            literals[0],
                            literals[1],
                            literals[2],
                            currentPLA.getTerms().size());

                    System.out.println(plaTableLine);
                }

                System.out.println("####################################################################");

                ArrayList<java.nio.file.Path> subCircuitsAIG = new ArrayList<>();

                //Seed
                subCircuitsAIG.add(Paths.get(String.format("%s/seeds/aig/%s.aig", mainlyPath, circuitName.split("_")[0])));

                //Multi-output
                /*Files.list(Paths.get(String.format("%s/%s/%s_just_crit_multi_output/aig", resultPath, circuitName, circuitName))).sorted().forEach(path -> {
                    if(!path.toString().toLowerCase().contains("stats")) {
                        subCircuitsAIG.add(path);
                    }
                });

                //Per-output
                Files.list(Paths.get(String.format("%s/%s/%s_just_crit_per_output/aig", resultPath, circuitName, circuitName))).sorted().forEach(path -> {
                    if(!path.toString().toLowerCase().contains("stats")) {
                        subCircuitsAIG.add(path);
                    }
                });

                //ALSRAC
                Files.list(Paths.get(String.format("%s/%s/alsrac/aig", resultPath, circuitName))).sorted().forEach(path -> {
                    if(!path.toString().toLowerCase().contains("stats")) {
                        subCircuitsAIG.add(path);
                    }
                });

                //ALSRAC-x-PLA---05P
                Files.list(Paths.get(String.format("%s/%s/alsracXpla/05P---t481_0.00541016_1.4_158.23/aig", resultPath, circuitName))).sorted().forEach(path -> {
                    if(!path.toString().toLowerCase().contains("stats")) {
                        subCircuitsAIG.add(path);
                    }
                });

                //ALSRAC-x-PLA---10P
                Files.list(Paths.get(String.format("%s/%s/alsracXpla/10P---t481_0.0144336_1.47_144.67/aig", resultPath, circuitName))).sorted().forEach(path -> {
                    if(!path.toString().toLowerCase().contains("stats")) {
                        subCircuitsAIG.add(path);
                    }
                }); */

                //ALSRAC-x-PLA---20P
                Files.list(Paths.get(String.format("%s/%s/alsracXpla/20P---t481_0.187754_0.8_94.09/aig", resultPath, circuitName))).sorted().forEach(path -> {
                    if(!path.toString().toLowerCase().contains("stats")) {
                        subCircuitsAIG.add(path);
                    }
                });

                for (Path aig : subCircuitsAIG) {


                    //Writting AIG Stats
                    String parentFolder = aig.getParent().toString();
                    String aigName = aig.getFileName().toString().replace(".aig", "");
                    String aigJsonPath = String.format("%s/%s_STATS.json", parentFolder, aigName);
                    ShellScriptOps.aigABCStatsToJSON(aig.toString(), aigJsonPath);


                    //Reading JSON AIG Stats
                    FileReader reader = new FileReader(aigJsonPath);
                    JSONTokener tokener = new JSONTokener(reader);
                    JSONObject jsonData = new JSONObject(tokener);

                    String aigStatsLine = String.format("%s %d/%d %d %d",
                            aig.getFileName().toString(),
                            jsonData.getInt("input"),
                            jsonData.getInt("output"),
                            jsonData.getInt("and"),
                            jsonData.getInt("level"));

                    System.out.println(aigStatsLine);

                }
                System.out.println("############################################################");


                //TimeUnit.MINUTES.sleep(660);
            }


        }

        final long endTime = System.currentTimeMillis();

        long secondstimestamp = (endTime - startTime)/1000;

        String timeConsup = "## TIME CONSUPTION ## ==> " + secondstimestamp + " secs";
        System.out.println(timeConsup);

    }

    public void MakeExaustiveTruthTableToJSON() throws Exception {

        final long startTime = System.currentTimeMillis();

        //Parameters
        String mainlyPath = "CIRCUITOS-AMMES-MANSKE";
        String resultPath = String.format("%s/results", mainlyPath);
        String genlibPath = "genlibs/asap7_RVT_TT_ccs_ABC.genlib";

        CellLibrary cellLib = new CellLibrary(genlibPath);
        ArrayList<java.nio.file.Path> circuits = new ArrayList<>();

        Files.list(Paths.get(String.format("%s/seeds/verilog", mainlyPath))).sorted().forEach(path -> circuits.add(path));

        for(Path p : circuits) {

            String circuitName = p.getFileName().toString().split(".v")[0];

            ProbCircuit exactSeedCircuit = new CircuitFactory(cellLib, p.toString()).getProbCircuit();

            HashMap<String, String> truthTablesList = new HashMap<>();

            for (int i = 0; i < exactSeedCircuit.getTotalInputVectors().intValue(); i++) {
                InputVector inputV = new InputVector(i, exactSeedCircuit.getProbInputs().size());
                ArrayList<Boolean> outputVector = exactSeedCircuit.propagateInputVector(inputV);
                String bString = CriticalVectorsUtils.boolArrayToBinaryString(outputVector);

                truthTablesList.put(inputV.getBinaryString(), bString);

            }
            String jsonPath = String.format("CIRCUITOS-AMMES-MANSKE/seeds/truthTable/%s.json", circuitName);
            CriticalVectorsUtils.inputOutputcombinationtoJSON(truthTablesList, jsonPath);


        }

        final long endTime = System.currentTimeMillis();

        long secondstimestamp = (endTime - startTime)/1000;

        String timeConsup = "## TIME CONSUPTION ## ==> " + secondstimestamp + " secs";
        System.out.println(timeConsup);

    }

    public void MakeExaustiveCriticalVectorsToJSON() throws Exception {

        final long startTime = System.currentTimeMillis();

        //Parameters
        String mainlyPath = "CIRCUITOS-AMMES-MANSKE";
        String resultPath = String.format("%s/results", mainlyPath);
        String genlibPath = "genlibs/asap7_RVT_TT_ccs_ABC.genlib";

        CellLibrary cellLib = new CellLibrary(genlibPath);
        ArrayList<java.nio.file.Path> circuits = new ArrayList<>();

        Files.list(Paths.get(String.format("%s/seeds/verilog", mainlyPath))).sorted().forEach(path -> circuits.add(path));

        for(Path p : circuits) {

            String circuitName = p.getFileName().toString().split(".v")[0];
            ProbCircuit exactSeedCircuit = new CircuitFactory(cellLib, p.toString()).getProbCircuit();


            final long startTime2 = System.currentTimeMillis();
            ArrayList<InputVector> list = ShellScriptOps.getOrderedInputVectorsReliability(exactSeedCircuit, cellLib, false);

            final long endTime = System.currentTimeMillis();
            long secondstimestamp = (endTime - startTime2)/1000;
            // Writer
            //CriticalVectorsUtils.criticalVectorsExactListToJSON(list, "CIRCUITOS-AMMES-MANSKE/seeds/criticalVectors/apex4_mapA_asap7-RVT-TT-CCS_criticalVectorsEXACTlist.json", secondstimestamp);
            CriticalVectorsUtils.criticalVectorsExactListToJSON(list, String.format("%s/seeds/criticalVectors/%s_criticalVectors.json", mainlyPath, circuitName), (System.currentTimeMillis() - startTime2)/1000);
            String timeConsup = "## TIME CONSUPTION ## ==> " + secondstimestamp + " secs";
            System.out.println(timeConsup);
        }

        final long wholeProcessEndTime = System.currentTimeMillis();
        long secondstimestamp = (wholeProcessEndTime - startTime)/1000;
        String timeConsup = "## TIME CONSUPTION ## ==> " + secondstimestamp + " secs";
        System.out.println(timeConsup);
    }

    public void prepareForResultsMappedVerilogAsSeed() throws Exception {

        final long startTime = System.currentTimeMillis();


        //ArrayList<Path> circuits = ops.CommonOps.getAllVerilogCircuitsFromPath("CIRCUITOS-AMMES-MANSKE/seeds/verilog");

        //Parameters
        String mainlyPath = "CIRCUITOS-AMMES-MANSKE";
        String resultPath = String.format("%s/results", mainlyPath);
        String genlibPath = "genlibs/asap7_RVT_TT_ccs_ABC.genlib";

        CellLibrary cellLib = new CellLibrary(genlibPath);
        ArrayList<java.nio.file.Path> circuits = new ArrayList<>();

        Files.list(Paths.get(String.format("%s/seeds/verilog", mainlyPath))).sorted().forEach(path -> circuits.add(path));
        for(Path p : circuits) {

            long startTime2 = System.currentTimeMillis();

            //Local parameters
            String circuitName = p.getFileName().toString().split(".v")[0];

            //Creates circuits main folder
            File mainCircuitFolder = new File(String.format("%s/%s", resultPath, circuitName));
            String mainCircuitFolderPath = mainCircuitFolder.getAbsolutePath();
            mainCircuitFolder.mkdir();


            String verilogFromPLACircuit = String.format("%s/%s", mainCircuitFolderPath, circuitName+"_fromPLA.v");
            String aigFromPLA = String.format("%s/%s", mainCircuitFolderPath, circuitName+"_fromPLA.aig");

            String plaSeedPath = String.format("%s/%s.pla", mainCircuitFolderPath, circuitName);
            String plaSeedEspressoPath = String.format("%s/%s_ESPRESSO.pla", mainCircuitFolderPath, circuitName);

            String scriptCommand = String.format("MAPPED_VERILOG_TO_PLA_TO_VERILOG %s %s %s %s %s %s",
                    genlibPath,
                    p.toString(),
                    plaSeedPath,
                    plaSeedEspressoPath,
                    aigFromPLA,
                    verilogFromPLACircuit);

            ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                    scriptCommand);

            ProbCircuit exactFromPlaCircuit = new CircuitFactory(cellLib, verilogFromPLACircuit).getProbCircuit();

            //Start exaustive critical vectors
            ArrayList<InputVector> list = ShellScriptOps.getOrderedInputVectorsReliability(exactFromPlaCircuit, cellLib, false);
            CriticalVectorsUtils.criticalVectorsExactListToJSON(list, String.format("%s/%s_fromPLA_criticalVectors.json", mainCircuitFolderPath, circuitName), (System.currentTimeMillis() - startTime2)/1000);

            exactFromPlaCircuit = new CircuitFactory(cellLib, verilogFromPLACircuit).getProbCircuit();

            HashMap<String, String> truthTablesList = new HashMap<>();

            for (int i = 0; i < exactFromPlaCircuit.getTotalInputVectors().intValue(); i++) {
                InputVector inputV = new InputVector(i, exactFromPlaCircuit.getProbInputs().size());
                ArrayList<Boolean> outputVector = exactFromPlaCircuit.propagateInputVector(inputV);
                String bString = CriticalVectorsUtils.boolArrayToBinaryString(outputVector);
                truthTablesList.put(inputV.getBinaryString(), bString);
            }
            String jsonPath = String.format("%s/%s_fromPLA_truthTable.json", mainCircuitFolderPath, circuitName);
            CriticalVectorsUtils.inputOutputcombinationtoJSON(truthTablesList, jsonPath);

            System.out.println(circuitName + " done!!");

        }

        final long endTime = System.currentTimeMillis();

        long secondstimestamp = (endTime - startTime)/1000;

        String timeConsup = "## TIME CONSUPTION ## ==> " + secondstimestamp + " secs";
        System.out.println(timeConsup);

    }

    public void prepareForResultsPLAAsSeed() throws Exception {

        final long startTime = System.currentTimeMillis();

        //Parameters
        String mainlyPath = "CIRCUITOS-AMMES-MANSKE";
        String resultPath = String.format("%s/results", mainlyPath);
        Path gentlibPath = Paths.get("genlibs/asap7_RVT_TT_ccs_ABC.genlib");


        //flags
        boolean makeEspressoAigBlifVerilog = false;

        CellLibrary cellLib = new CellLibrary(gentlibPath.toString());
        ArrayList<java.nio.file.Path> plas = new ArrayList<>();

        Files.list(Paths.get(String.format("%s/seeds/pla", mainlyPath))).sorted().forEach(path -> {
            if(!path.toString().toLowerCase().contains("espresso")) {
                plas.add(path);
            }
        });

        for(Path p : plas) {

            long startTime2 = System.currentTimeMillis();

            //Local parameters
            String plaName = p.getFileName().toString().split(".pla")[0];
            String espressoPLAName = String.format("%s_ESPRESSO.pla", plaName);

            String verilogName = String.format("%s_%s.v", plaName, gentlibPath.getFileName().toString().replace(".genlib", ""));


            if(makeEspressoAigBlifVerilog) {
                //Original PLA to ESPRESSO
                ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                        String.format("ESPRESSO %s %s", p.toString(), p.getParent().toString()+"/"+espressoPLAName));

                //Generate AIG
                ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                        String.format("ABC_PLA_TO_AIG %s %s", p.getParent().toString()+"/"+espressoPLAName,
                                String.format("%s/aig/%s.aig", p.getParent().getParent().toString(), plaName)));

                //Generate BLIF
                ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                        String.format("ABC_AIG_TO_BLIF %s %s", String.format("%s/aig/%s.aig", p.getParent().getParent().toString(), plaName),
                                String.format("%s/blif/%s.blif", p.getParent().getParent().toString(), plaName)));

                //Generatate VERILOG
                ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                        String.format("ABC_AIG_TO_VERILOG %s %s %s", String.format("%s/aig/%s.aig", p.getParent().getParent().toString(), plaName),
                                gentlibPath.toString(),
                                String.format("%s/verilog/%s", p.getParent().getParent().toString(), verilogName)));

                //Creates circuits main folder
                File mainCircuitFolder = new File(String.format("%s/%s", resultPath, verilogName.replace(".v", "")));
                String mainCircuitFolderPath = mainCircuitFolder.getAbsolutePath();
                mainCircuitFolder.mkdir();
            }

            System.out.println(plaName + " done!!");

        }

        final long endTime = System.currentTimeMillis();

        long secondstimestamp = (endTime - startTime)/1000;

        String timeConsup = "## TIME CONSUPTION ## ==> " + secondstimestamp + " secs";
        System.out.println(timeConsup);

        if(makeEspressoAigBlifVerilog) {
            this.MakeExaustiveTruthTableToJSON();
            this.MakeExaustiveCriticalVectorsToJSON();
        }

        this.MakeExactJustCritApprox();

    }

    public void MakeExactJustCritApprox() throws Exception {

        final long startTime = System.currentTimeMillis();

        List<String> exceptions = Arrays.asList(
                                                //"5xp1",
                                                //"9sym",
                                                //"alu4",
                                                //"apex",
                                                //"b12",
                                                //"clip",
                                                //"ex1010",
                                                //"intb",
                                                //"max1024",
                                                //"misex",
                                                //"sao2",
                                                //"t481"
                                                );


        //ArrayList<Path> circuits = ops.CommonOps.getAllVerilogCircuitsFromPath("CIRCUITOS-AMMES-MANSKE/seeds/verilog");

        //Parameters
        String mainlyPath = "CIRCUITOS-AMMES-MANSKE";
        String resultPath = String.format("%s/results", mainlyPath);
        String genlibPath = "genlibs/asap7_RVT_TT_ccs_ABC.genlib";

        CellLibrary cellLib = new CellLibrary(genlibPath);
        ArrayList<java.nio.file.Path> circuits = new ArrayList<>();

        Files.list(Paths.get(String.format("%s/seeds/verilog", mainlyPath))).sorted().forEach(path -> circuits.add(path));

        for(Path p : circuits) {

            //Verify if current circuit is in exception list
            boolean flagException = false;
            for (String ex : exceptions) {
                if(p.toString().toLowerCase().contains(ex)) {
                    flagException = true;
                    System.out.println(ex);
                }
            }
            if(!flagException) {

                //Local parameters
                String circuitName = p.getFileName().toString().split(".v")[0];
                String benchName = circuitName.split("_")[0];

                String mainCircuitFolderPath = String.format("%s/%s", resultPath, circuitName);
                //String exactSeedCircuitPath = String.format("%s/%s_fromPLA.v", mainCircuitFolderPath, circuitName);
                String critVectorsPath = String.format("%s/seeds/criticalVectors/%s_criticalVectors.json",
                        mainlyPath, circuitName);


                ArrayList<InputVector> criticals = CriticalVectorsUtils.criticalVectorsExactListFromJSON(critVectorsPath).getCriticalVectors();
                ProbCircuit exactSeedCircuit = new CircuitFactory(cellLib, p.toString()).getProbCircuit();

                ArrayList<Double> percentuals = new ArrayList<>(Arrays.asList(0.05, 0.1, 0.2, 0.4, 0.8, 0.95));





                ArrayList<Integer> percentualsVectors = new ArrayList<>();

                for(double percentual : percentuals) {
                    percentualsVectors.add((int)(exactSeedCircuit.getTotalInputVectors().intValue() * percentual));
                }

                /*percentualsVectors.add((int)(exactSeedCircuit.getTotalInputVectors().intValue() * 0.05));
                percentualsVectors.add((int)(exactSeedCircuit.getTotalInputVectors().intValue() * 0.1));
                percentualsVectors.add((int)(exactSeedCircuit.getTotalInputVectors().intValue() * 0.2));
                percentualsVectors.add((int)(exactSeedCircuit.getTotalInputVectors().intValue() * 0.4));
                percentualsVectors.add((int)(exactSeedCircuit.getTotalInputVectors().intValue() * 0.8));
                percentualsVectors.add((int)(exactSeedCircuit.getTotalInputVectors().intValue() * 0.95));*/

                //Make approx methods folders
                ArrayList<String> approxMethodsFoldersNames = new ArrayList<>();
                approxMethodsFoldersNames.add(String.format("%s/%s_just_crit_per_output",
                        mainCircuitFolderPath, circuitName));
                approxMethodsFoldersNames.add(String.format("%s/%s_just_crit_multi_output",
                        mainCircuitFolderPath, circuitName));

                for(String folder : approxMethodsFoldersNames) {
                    new File(folder).mkdir();
                    // make AIG, PLA and VERILOG folder
                    new File(folder + "/aig").mkdir();
                    new File(folder + "/pla").mkdir();
                    new File(folder + "/verilog").mkdir();
                }


                for (String folder : approxMethodsFoldersNames) {

                    String approxCircuitSuffix = Paths.get(folder).getFileName().toString();

                    boolean multiOutput = false;
                    if(approxCircuitSuffix.contains("per_output")) {
                        multiOutput = true;
                    }

                    for(int j = 0; j < percentuals.size(); j++) {
                        ArrayList<InputVector> vectorsToApprox = new ArrayList<>();

                        for (int i = 0; i < percentualsVectors.get(j); i++) {
                            vectorsToApprox.add(criticals.get(i));
                        }

                        int length = String.valueOf(exactSeedCircuit.getTotalInputVectors().intValue()).length();
                        //String percentualStr = Double.toString(percentuals.get(j)).split(",")[1];
                        String percentualStr = String.format("%.2f", percentuals.get(j)).split(",")[1];



                        String plaSeedEspressoPath = String.format("%s/seeds/pla/%s_ESPRESSO.pla", mainlyPath, benchName);
                        String patternName = String.format("%sP---%0" + length + "d-%s", percentualStr, vectorsToApprox.size(), approxCircuitSuffix);
                        String aigOutput = String.format("%s/aig/%s.aig", folder, patternName);
                        String verilogOutput = String.format("%s/verilog/%s.v", folder, patternName);
                        String plaOutput = String.format("%s/pla/%s.pla", folder, patternName);
                        String plaESPRESSOOutput = String.format("%s/pla/%s_ESPRESSO.pla", folder, patternName);
                        //String verilogFromPLACircuit = String.format("%s/%s", mainCircuitFolderPath, circuitName+"_fromPLA.v");

                        ApproxOPS.approxMethodWrapper(ApproxOPS.getApproxMethod(approxCircuitSuffix),
                                plaSeedEspressoPath,
                                p.toString(),
                                genlibPath,
                                aigOutput,
                                plaOutput,
                                plaESPRESSOOutput,
                                verilogOutput,
                                vectorsToApprox);
                    }
                }
                System.out.println("-------------");
                //System.out.println("MAMMAE");
                //TimeUnit.MINUTES.sleep(660);
            }
        }

        final long endTime = System.currentTimeMillis();

        long secondstimestamp = (endTime - startTime)/1000;

        String timeConsup = "## TIME CONSUPTION ## ==> " + secondstimestamp + " secs";
        System.out.println(timeConsup);

        System.out.println("DONE!");
        //TimeUnit.MINUTES.sleep(660);





        //ProbCircuit exactVerilog = new CircuitFactory(cellLib, "CIRCUITOS-AMMES-MANSKE/seeds/verilog/table5_mapA_asap7-RVT-TT-CCS.v").getProbCircuit();





        // Reader
        //ArrayList<InputVector> vectors = CriticalVectorsUtils.criticalVectorsListFromJSON("testandoUtils.json");
        //CriticalVectorsExactList criticalObj = CriticalVectorsUtils.criticalVectorsExactListFromJSON("testandoObjCriticalExactlist.json");

        /*Gson gson = new Gson();*/
        /*JsonWriter jsonWriter = new JsonWriter(new FileWriter("5xp1_inputVectors.json"));*/
        /*JsonReader jsonReader = new JsonReader(new FileReader("5xp1_inputVectors.json"));*/
        /*Type listType = new TypeToken<ArrayList<InputVector>>(){}.getType();*/

        /*gson.toJson(vectors, listType, jsonWriter);
        jsonWriter.close();*/

        /*ArrayList<InputVector> fromJsonList = gson.fromJson(jsonReader, listType);*/




        /*System.out.println("*******************************************");

        System.out.println("OK");

        for(InputVector in : criticalObj.getCriticalVectors()) {
            System.out.println(in.getBinaryString() + " -*==*-> " + in.getDoubleReliability());
        }

        System.out.println("Timestamp from criticalObj == " + criticalObj.getSecondsTimeStamp() + "secs");*/
        /*System.out.println("JSON LIST");
        System.out.println("----------------------------------------");

        for(InputVector in : fromJsonList) {
            System.out.println(in.getBinaryString() + " -x-> " + in.getDoubleReliability());
        }
*/

        /*exactVerilog = new CircuitFactory(cellLib, "CIRCUITOS-AMMES-MANSKE/seeds/verilog/prom1_mapA_mylib.v").getProbCircuit();

        JeneticsCriticalVectorsSPR criticals = new JeneticsCriticalVectorsSPR(exactVerilog, cellLib);
        criticals.getCriticalVectors();*/






    }
    public void Foo_ISCAS2021() throws Exception {


        Terminal.getInstance().executeCommand("read_genlib abc/Matheus/5-full_no_cost.genlib");
        Terminal.getInstance().executeCommand("read_custom_matrix 45nm.txt");
    //Terminal.getInstance().executeCommand("read_verilog ../files/mappeds/c432_full_no_costV2.v");


    //Terminal.getInstance().executeCommand("spr_big_decimal 0.999999");
    //Terminal.getInstance().executeCommand("spr_big_decimal 0.999999001");
    //Terminal.getInstance().executeCommand("spr");


    String[] cellList = {"ZERO",
            "ONE",
            "BUF",
            "INV",
            "NOR2",
            "NOR3",
            "NOR4",
            "NAND2",
            "NAND3",
            "NAND4",
            "OAI21",
            "OAI211",
            "OAI22",
            "OAI221",
            "OAI222",
            "AOI21",
            "AOI211",
            "AOI22",
            "AOI221",
            "AOI222",
            "XOR2"};

        String[] benchs = {"c432",
                "c499",
                "c880",
                "c1355",
                "c1908",
                "c2670",
                "c3540",
                "c5315",
                "c6288",
                "c7552"};

    /*String[] benchs = {"b01_C",
                       "b02_C",
            "b03_C",
            "b04_C",
            "b05_C",
            "b06_C",
            "b07_C",
            "b08_C",
            "b09_C",
            "b10_C",
            "b11_C",
            "b12_C",
            "b13_C",
            "b14_C",
            "b15_C",
            "b17_C",
            "b18_C",
            "b19_C",
            "b20_C",
            "b21_C",
            "b22_C"};*/

    /*String[] benchs = {"25_1",
            "25_2",
            "25_3",
            "25_4",
            "25_5",
            "25_6",
            "25_7",
            "25_8",
            "25_9",
            "25_10",
            "25_11",
            "25_12",
            "25_13",
            "25_14",
            "25_15",
            "25_16",
            "25_17",
            "25_18",
            "25_19",
            "25_20",
            "25_21",
            "25_22",
            "25_23",
            "25_24",
            "25_25",
            "g25",
            "g36",
            "g125",
            "g216",
            "g625",
            "g1296",
            "LEKU-CB",
            "LEKU-CD"};*/

    /*String[] benchs = {"arbiter",
                       "cavlc",
                       "ctrl",
                       "dec",
                       "i2c",
                       "int2float",
                       "mem_ctrl",
                       "priority",
                       "router",
                       "voter"}; */

    /*String[] benchs = {"adder",
            "bar",
            "div",
            "hyp",
            "log2",
            "max",
            "multiplier",
            "sin",
            "sqrt",
            "square"};*/




    String[] libs = {"1-minimal_no_cost",
                     "2-basic_no_cost",
                     "3-complex_V1_no_cost",
                     "4-complex_V2_no_cost",
                     "5-full_no_cost"};

    String[] reliabilities = {"total_gates",
            "fanouts",
            "levels",
            "in",
            "out",
            "fixed_reliability",
            "mtbf_fixed_reliability",
            "custom_45nm",
            "mtbf_custom_45nm",
            "circuit_name_file"};


    File dir = new File("abc/Matheus");

        for (String bench : benchs) {

        ArrayList<ArrayList<String>> csv = new ArrayList<>();

        // Add header
        csv.add(new ArrayList<>());
        csv.get(0).add(bench);

        //Add indexes
        int counter = 1;
        for (String cell : cellList) {
            csv.add(new ArrayList<>());
            csv.get(counter).add(cell);
            counter++;
        }

        for (String reliability : reliabilities) {
            csv.add(new ArrayList<>());
            csv.get(counter).add(reliability);
            counter++;
        }

        //Complete header
        for (String lib : libs) {
            csv.get(0).add(lib);
        }


        File[] matches = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith(bench);
            }
        });

        ArrayList<ProbCircuit> circuits = new ArrayList<>();


        for (File matche : matches) {
            circuits.add(new CircuitFactory(Terminal.getInstance().getCellLibrary(), "abc/Matheus/" + matche.getName()).getProbCircuit());
            System.out.println(matche);
        }


        for (String lib : libs) {
            int counter2 = 1;
            String name = bench + "_" + lib;
            for (ProbCircuit circuit : circuits) {
                if(circuit.getName().equals(name)) {
                    for (String cell : cellList) {
                        csv.get(counter2).add(Integer.toString(circuit.getCellQuantity(cell)));
                        counter2++;
                    }

                    csv.get(counter2).add(Integer.toString(circuit.getProbGates().size()));
                    counter2++;

                    csv.get(counter2).add(Integer.toString(circuit.getFanouts().size()));
                    counter2++;

                    csv.get(counter2).add(Integer.toString(circuit.getProbGateLevels().size()));
                    counter2++;

                    csv.get(counter2).add(Integer.toString(circuit.getProbInputs().size()));
                    counter2++;

                    csv.get(counter2).add(Integer.toString(circuit.getProbOutputs().size()));
                    counter2++;

                    SPRController spr = new SPRController(circuit, Terminal.getInstance().getCellLibrary());
                    BigDecimal val = spr.getReliability();
                    csv.get(counter2).add(val.toString());
                    counter2++;

                    csv.get(counter2).add(CommonOps.getMTBFBigInt(val).toString());
                    counter2++;

                    val = spr.getReliabilityCustomLib(Terminal.getInstance().getCustomMatrixLib());
                    csv.get(counter2).add(val.toString());
                    counter2++;

                    csv.get(counter2).add(CommonOps.getMTBFBigInt(val).toString());
                    counter2++;

                    csv.get(counter2).add("Circuits/mapped/EPFL2015/" + lib + "/" + circuit.getName());
                }
            }
        }


        for (ArrayList<String> arrayList : csv) {
            String collect = arrayList.stream().collect(Collectors.joining(","));

            System.out.println(collect);
        }

    }





    //CustomMatrixLibrary cMatrixLib = Terminal.getInstance().getCustomMatrixLib();
    //System.out.println(pCircuit);

    //pCircuit.setCustomMatrix(cMatrixLib);


    //Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal("0.99999802495"));

    //pCircuit.setPTMReliabilityMatrix();


    //matrixPrint(pCircuit.getProbGateByName("g059").getReliabilityMatrix());
    //System.out.println("# # # #");

    //pCircuit.setCustomMatrix(cMatrixLib);
    //matrixPrint(pCircuit.getProbGateByName("g059").getReliabilityMatrix());
    //System.out.println("# # # #");
    //System.out.println(cMatrixLib.getName());

    //SPRController controller = new SPRController(Terminal.getInstance());

    //System.out.println(controller.getReliability(new BigDecimal("0.99999802495")));
    //System.out.println(controller.getReliability(new BigDecimal("0.999999")));

}
    
    public Object[] callReliabilityMethod(String method, ProbCircuit pCircuit, String reliability) {
        
        Object[] value = new Object[1];
        
        Terminal.getInstance().getCellLibrary().setPTMCells2(Float.valueOf(reliability));
        Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal(reliability));
        
        pCircuit.clearProbSignalsMatrix();
        pCircuit.setPTMReliabilityMatrix();
        pCircuit.setDefaultProbSourceSignalMatrix();
        pCircuit.setProbSignalStates(false);
        
        switch(method) {
            case "PTM_BIGDECIMAL":
                long time = System.nanoTime();
                long finalTime;
                value[0] = PTMOps2.getCircuitReliabilityByPTM(pCircuit, null);
                finalTime = System.nanoTime() - time;
                BigDecimal big = new BigDecimal(finalTime).multiply(new BigDecimal("0.000001"));
                System.out.println("Tempo Verdadeiro = " + big);
                return value;
            
            case "PTM_FLOAT":
                time = System.nanoTime();
                value[0] = PTMOps2Float.getCircuitReliabilityByPTM(pCircuit);
                finalTime = System.nanoTime() - time;
                big = new BigDecimal(finalTime).multiply(new BigDecimal("0.000001"));
                System.out.println("Tempo Verdadeiro = " + big);
                return value;
                        
            
            case "PTM_SERIAL_FLOAT":
                time = System.nanoTime();
                value[0] = SerialPTMOpsFloat.getCircuitReliability(pCircuit, getITM(pCircuit), Float.parseFloat(reliability));
                finalTime = System.nanoTime() - time;
                big = new BigDecimal(finalTime).multiply(new BigDecimal("0.000001"));
                System.out.println("Tempo Verdadeiro = " + big);
                return value;
            
            case "PTM_SERIAL_FLOAT_OPT":
                time = System.nanoTime();
                value[0] = SerialPTMOpsFloatOptized.getCircuitReliability(pCircuit, getITM(pCircuit));
                finalTime = System.nanoTime() - time;
                big = new BigDecimal(finalTime).multiply(new BigDecimal("0.000001"));
                System.out.println("Tempo Verdadeiro = " + big);
                return value;
            
            case "SPR_FLOAT":
                time = System.nanoTime();
                value[0] = SPROpsFloat.getSPRReliability(pCircuit);
                finalTime = System.nanoTime() - time;
                big = new BigDecimal(finalTime).multiply(new BigDecimal("0.000001"));
                System.out.println("Tempo Verdadeiro = " + big);
                return value;
            
            case "SPR_BIGDECIMAL":
                time = System.nanoTime();
                value[0] = SPROps.getSPRReliability(pCircuit);
                finalTime = System.nanoTime() - time;
                big = new BigDecimal(finalTime).multiply(new BigDecimal("0.000001"));
                System.out.println("Tempo Verdadeiro = " + big);
                return value;
            
            case "SPR-MP_BIGDECIMAL":
                time = System.nanoTime();
                value[0] = SPRMultiPassV2BigDecimalOps.getSPRMultiPassReliaiblity(pCircuit);
                finalTime = System.nanoTime() - time;
                big = new BigDecimal(finalTime).multiply(new BigDecimal("0.000001"));
                System.out.println("Tempo Verdadeiro = " + big);
                return value;
            
            case "SPR-MP_FLOAT":
                time = System.nanoTime();                
                value[0] = SPRMultiPassV2Ops.getSPRMultiPassReliaiblity(pCircuit);
                finalTime = System.nanoTime() - time;
                big = new BigDecimal(finalTime).multiply(new BigDecimal("0.000001"));
                System.out.println("Tempo Verdadeiro = " + big);
                return value;
            
        }
        
        return null;
    }
    
    public Object[] callReliabilityMethod(String method, ProbCircuit pCircuit, String reliability, String timeType) {
        long startMethod;
        Object[] value = new Object[3];
        
        switch(timeType) {
            case "nano":
                value[1] = "Nanoseconds";
                startMethod = System.nanoTime();                
                value[0] = callReliabilityMethod(method, pCircuit, reliability)[0];
                value[2] = (System.nanoTime() - startMethod);                
                //System.out.println("Reliability = " + value[0] + " AND TIME CONSUPTION (" + value[1] + "): " + value[2]);                
            
            case "ms":
                value[1] = "Milliseconds";
                startMethod = System.currentTimeMillis();
                value[0] = callReliabilityMethod(method, pCircuit, reliability)[0];
                value[2] = (System.currentTimeMillis() - startMethod);
                //System.out.println("Reliability = " + value[0] + " AND TIME CONSUPTION (" + value[1] + "): " + value[2]);                
                
        }
        
        return value;
    }
    
    public void callReliabilityMethod(String method, ProbCircuit pCircuit, String reliability, String timeType, int cycles) {        
        Object[] properties = callReliabilityMethod(method, pCircuit, reliability, timeType);
        
        long firstTime = (long)properties[2];
        
        BigInteger timeAccumulator = new BigInteger(Long.toString(firstTime));
        
        for (int i = 1; i < cycles; i++) {
            long time = (long) callReliabilityMethod(method, pCircuit, reliability, timeType)[2];
            timeAccumulator = timeAccumulator.add(new BigInteger(Long.toString(time)));
        }
        
        BigInteger result = timeAccumulator.divide(new BigInteger(Integer.toString(cycles)));
        
        System.out.println("Reliability (" + reliability + ") of circuit " + pCircuit.getName() + "by " + method +  " method is " + properties[0] + ",  TIME CONSUPTION (" + properties[1] + ") was " + result + " in a " + cycles + " cycles average");
    }
    
    /**
     * Method created to ESREF 2021
     * Generates SPR results for fiexed and customized matrices
     * @throws IOException
     * @throws ScriptException
     * @throws Exception 
     */
    public void CSVSprGenerator() throws IOException, ScriptException, Exception {
        
        
        
        Terminal.getInstance().executeCommand("read_genlib ../files/genlibs/lib_full_no_cost.genlib");
        Terminal.getInstance().executeCommand("read_custom_matrix 45nm.txt");
        
        CellLibrary cellLib = Terminal.getInstance().getCellLibrary();
       
        String[] cellList = {"ZERO",
                             "ONE",
                             "BUF",
                             "INV",
                             "NOR2",
                             "NOR3",
                             "NOR4",
                             "NAND2",
                             "NAND3",
                             "NAND4",
                             "OAI21",
                             "OAI211",
                             "OAI22",
                             "OAI221",
                             "OAI222",
                             "AOI21",
                             "AOI211",
                             "AOI22",
                             "AOI221",
                             "AOI222",
                             "XOR2"};
        
        String[] benchs = {"c432",
                           "c499",
                           "c880",
                           "c1355",
                           "c1908",
                           "c2670",
                           "c3540",
                           "c5315",
                           "c6288",
                           "c7552"};
        
        
        String[] libs = {"lib_min_no_cost",
                         "lib_basic_no_cost",
                         "lib_complex_no_cost",
                         "lib_full_no_cost",
                         "lib_full_area_cost",
                         "lib_full_susceptability_cost",
                         "lib_full_worst_susceptability_cost",
                         "lib_complex_no_cost_no_xor",
                         "lib_full_no_cost_no_xor",
                         "lib_full_area_cost_no_xor",
                         "lib_full_susceptability_cost_no_xor",
                         "lib_full_worst_susceptability_cost_no_xor"};
        
        String[] reliabilities = {"total_gates",
                                  "fanouts",
                                  "levels",
                                  "in",
                                  "out",
                                  "fixed_reliability",
                                  "mtbf_fixed_reliability",                                  
                                  "custom_45nm",                                  
                                  "mtbf_custom_45nm",
                                  "custom_45nm_avg",
                                  "mtbf_custom_45nm_avg",
                                  "circuit_name_file"};
        
        
        Map<String, BigDecimal> reliMap = new HashMap<String, BigDecimal>();
        
        reliMap.put("BUF", new BigDecimal("0.9999960499"));
        reliMap.put("INV", new BigDecimal("0.99999802495"));
        reliMap.put("NAND2", new BigDecimal("0.9999975068"));
        reliMap.put("NOR2", new BigDecimal("0.9999968973"));
        reliMap.put("NAND3", new BigDecimal("0.999996887"));
        reliMap.put("NOR3", new BigDecimal("0.9999958711"));
        reliMap.put("NAND4", new BigDecimal("0.9999966945"));
        reliMap.put("NOR4", new BigDecimal("0.999995323"));
        reliMap.put("AOI21", new BigDecimal("0.9999949513"));
        reliMap.put("AOI22", new BigDecimal("0.9999951616"));
        reliMap.put("AOI211", new BigDecimal("0.9999928822"));
        reliMap.put("AOI221", new BigDecimal("0.9999931706"));
        reliMap.put("AOI222", new BigDecimal("0.9999911458"));
        reliMap.put("OAI21", new BigDecimal("0.9999958318"));
        reliMap.put("OAI22", new BigDecimal("0.9999951277"));
        reliMap.put("OAI211", new BigDecimal("0.9999946092"));
        reliMap.put("OAI221", new BigDecimal("0.9999942796"));
        reliMap.put("OAI222", new BigDecimal("0.9999912698"));
        reliMap.put("XOR2", new BigDecimal("0.9999941709"));
        reliMap.put("ZERO", new BigDecimal("0"));
        reliMap.put("ONE", new BigDecimal("1"));
        
        
        File dir = new File("files/mappeds");
        
        for (String bench : benchs) {
            
            ArrayList<ArrayList<String>> csv = new ArrayList<>();
            
            // Add header
            csv.add(new ArrayList<>());
            csv.get(0).add(bench);
            
            //Add indexes
            int counter = 1;
            for (String cell : cellList) {                
                csv.add(new ArrayList<>());
                csv.get(counter).add(cell);
                counter++;
            }
            
            for (String reliability : reliabilities) {
                csv.add(new ArrayList<>());
                csv.get(counter).add(reliability);
                counter++;
            }
            
            //Complete header
            for (String lib : libs) {
                csv.get(0).add(lib);
            }
            
            
            File[] matches = dir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.startsWith(bench);
                }
            });
            
            ArrayList<ProbCircuit> circuits = new ArrayList<>();
        
                                    
            for (File matche : matches) {
                circuits.add(new CircuitFactory(Terminal.getInstance().getCellLibrary(), "files/mappeds/" + matche.getName()).getProbCircuit());
            }
                        
            
            for (String lib : libs) {
                int counter2 = 1;
                String name = bench + "_" + lib;
                for (ProbCircuit circuit : circuits) {
                    if(circuit.getName().equals(name)) {
                        for (String cell : cellList) {
                            csv.get(counter2).add(Integer.toString(circuit.getCellQuantity(cell)));
                            counter2++;
                        }
                        
                        csv.get(counter2).add(Integer.toString(circuit.getProbGates().size()));
                        counter2++;
                        
                        csv.get(counter2).add(Integer.toString(circuit.getFanouts().size()));
                        counter2++;
                        
                        csv.get(counter2).add(Integer.toString(circuit.getProbGateLevels().size()));
                        counter2++;
                        
                        csv.get(counter2).add(Integer.toString(circuit.getProbInputs().size()));
                        counter2++;
                        
                        csv.get(counter2).add(Integer.toString(circuit.getProbOutputs().size()));
                        counter2++;
                        
                        SPRController spr = new SPRController(circuit, Terminal.getInstance().getCellLibrary());
                        BigDecimal val = spr.getReliability();
                        csv.get(counter2).add(val.toString());
                        counter2++;
                        
                        //csv.get(counter2).add(CommonOps.getMTBFBigInt(val).toString());
                        csv.get(counter2).add(CommonOps.getMTBF(val).toString());
                        counter2++;
                        
                        val = spr.getReliabilityCustomLib(Terminal.getInstance().getCustomMatrixLib());
                        csv.get(counter2).add(val.toString());
                        counter2++;
                        
                        //csv.get(counter2).add(CommonOps.getMTBFBigInt(val).toString());
                        csv.get(counter2).add(CommonOps.getMTBF(val).toString());
                        counter2++;
                        
                        for(Map.Entry<String, BigDecimal> entry : reliMap.entrySet()) {
                            String key = entry.getKey();
                            BigDecimal value = entry.getValue();

                            cellLib.setPTMCellByName(key, value);

                        }
                        
                        circuit.syncCellPTMs();
                        circuit.setDefaultProbSourceSignalMatrix();
                        BigDecimal result = SPROpsChuloMedio.getSPRReliability(circuit);                                                
                        csv.get(counter2).add(result.toString());
                        counter2++;                        
                        //csv.get(counter2).add(CommonOps.getMTBFBigInt(result).toString());
                        csv.get(counter2).add(CommonOps.getMTBF(result).toString());
                        counter2++;   

                        csv.get(counter2).add("files/mappeds/" + circuit.getName());                        
                    }
                }
            }
            
            
            for (ArrayList<String> arrayList : csv) {
                String collect = arrayList.stream().collect(Collectors.joining(","));

                System.out.println(collect);
            }
                        
        }                
        
    }
    
    
    
    public void CSVSprGeneratorOLD() throws IOException, ScriptException, Exception {
        
        
        
        Terminal.getInstance().executeCommand("read_genlib ../files/genlibs/lib_full_no_cost.genlib");
        Terminal.getInstance().executeCommand("read_custom_matrix 45nm.txt");       
       
        String[] cellList = {"ZERO",
                             "ONE",
                             "BUF",
                             "INV",
                             "NOR2",
                             "NOR3",
                             "NOR4",
                             "NAND2",
                             "NAND3",
                             "NAND4",
                             "OAI21",
                             "OAI211",
                             "OAI22",
                             "OAI221",
                             "OAI222",
                             "AOI21",
                             "AOI211",
                             "AOI22",
                             "AOI221",
                             "AOI222",
                             "XOR2"};
        
        String[] benchs = {"c432",
                           "c499",
                           "c880",
                           "c1355",
                           "c1908",
                           "c2670",
                           "c3540",
                           "c5315",
                           "c6288",
                           "c7552"};
        
        
        String[] libs = {"lib_min_no_cost",
                         "lib_basic_no_cost",
                         "lib_complex_no_cost",
                         "lib_full_no_cost",
                         "lib_full_area_cost",
                         "lib_full_susceptability_cost",
                         "lib_full_worst_susceptability_cost",
                         "lib_complex_no_cost_no_xor",
                         "lib_full_no_cost_no_xor",
                         "lib_full_area_cost_no_xor",
                         "lib_full_susceptability_cost_no_xor",
                         "lib_full_worst_susceptability_cost_no_xor"};
        
        String[] reliabilities = {"total_gates",
                                  "fanouts",
                                  "levels",
                                  "in",
                                  "out",
                                  "fixed_reliability",
                                  "mtbf_fixed_reliability",
                                  "custom_45nm",
                                  "mtbf_custom_45nm",
                                  "circuit_name_file"};
        
        
        File dir = new File("files/mappeds");
        
        for (String bench : benchs) {
            
            ArrayList<ArrayList<String>> csv = new ArrayList<>();
            
            // Add header
            csv.add(new ArrayList<>());
            csv.get(0).add(bench);
            
            //Add indexes
            int counter = 1;
            for (String cell : cellList) {                
                csv.add(new ArrayList<>());
                csv.get(counter).add(cell);
                counter++;
            }
            
            for (String reliability : reliabilities) {
                csv.add(new ArrayList<>());
                csv.get(counter).add(reliability);
                counter++;
            }
            
            //Complete header
            for (String lib : libs) {
                csv.get(0).add(lib);
            }
            
            
            File[] matches = dir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.startsWith(bench);
                }
            });
            
            ArrayList<ProbCircuit> circuits = new ArrayList<>();
        
                                    
            for (File matche : matches) {
                circuits.add(new CircuitFactory(Terminal.getInstance().getCellLibrary(), "files/mappeds/" + matche.getName()).getProbCircuit());
                System.out.println(matche);
            }
                        
            
            for (String lib : libs) {
                int counter2 = 1;
                String name = bench + "_" + lib;
                for (ProbCircuit circuit : circuits) {
                    if(circuit.getName().equals(name)) {
                        for (String cell : cellList) {
                            csv.get(counter2).add(Integer.toString(circuit.getCellQuantity(cell)));
                            counter2++;
                        }
                        
                        csv.get(counter2).add(Integer.toString(circuit.getProbGates().size()));
                        counter2++;
                        
                        csv.get(counter2).add(Integer.toString(circuit.getFanouts().size()));
                        counter2++;
                        
                        csv.get(counter2).add(Integer.toString(circuit.getProbGateLevels().size()));
                        counter2++;
                        
                        csv.get(counter2).add(Integer.toString(circuit.getProbInputs().size()));
                        counter2++;
                        
                        csv.get(counter2).add(Integer.toString(circuit.getProbOutputs().size()));
                        counter2++;
                        
                        SPRController spr = new SPRController(circuit, Terminal.getInstance().getCellLibrary());
                        BigDecimal val = spr.getReliability();
                        csv.get(counter2).add(val.toString());
                        counter2++;
                        
                        csv.get(counter2).add(CommonOps.getMTBFBigInt(val).toString());
                        counter2++;
                        
                        val = spr.getReliabilityCustomLib(Terminal.getInstance().getCustomMatrixLib());
                        csv.get(counter2).add(val.toString());
                        counter2++;
                        
                        csv.get(counter2).add(CommonOps.getMTBFBigInt(val).toString());
                        counter2++;
                        
                        csv.get(counter2).add("files/mappeds/" + circuit.getName());                        
                    }
                }
            }
            
            
            for (ArrayList<String> arrayList : csv) {
                String collect = arrayList.stream().collect(Collectors.joining(","));

                System.out.println(collect);
            }
                        
        }                
        
    }
    
    public void PrintPath(){
        Terminal.getInstance().terminalOutput(System.getProperty("user.dir"));
    }

    /* Marcio */
    public void createSubCircuits() {
        Utils.createSubCircuits();
    }

    public void getOrderedGates(String q, String newQ){
        //obter uma lista de portas em ordem crítica
        Utils.getOrderedGates(q, newQ);
    }

    public void getOrderedGatesByWRV(InputVector iv, String q, String newQ) {
        //obter uma lista de portas em ordem crítica do vetor crítico
        Utils.orderedGatesByWRV(iv, q, newQ);
    }

    public void getReliabilityByImprovementGate(String q, String newQ) {
        //melhoria da confiabilidade do circuito ao proteger as portas críticas
        Map<ProbGate, BigDecimal> orderedGates = Utils.getOrderedGates(q, newQ);
        List<ProbGate> listGates = new ArrayList<>(orderedGates.keySet());
        Utils.getReliabilityByImprovementGate(listGates, q, newQ);
    }

    public void getWorstReliabilityVector(String q) {

        final long startTime = System.currentTimeMillis();
        //configura o método SPR para executar os cálculos de confiabilidade
        RunScore runScore = new ScoreBySPR(new BigDecimal(q));
        //cria o algoritmo para identificação do vetor crítico
        //passando o método de cálculo
        WRVAlgoritm wrvalg = new WRVAlgoritm(runScore);
        //executa o algoritmo
        //retorna um InputVector
        wrvalg.execute();

        final long endTime = System.currentTimeMillis();

        String timeConsup = "## TIME CONSUPTION ## ==> " + Long.toString((endTime - startTime)/1000) + " secs";
        System.out.println(timeConsup);



    }

    public void getAreaCostWithTMR(String q, String newQ) {
        //obtem a quantidade de portas ao aplicar um TMR no circuito
        Map<ProbGate, BigDecimal> orderedGates = Utils.getOrderedGates(q, newQ);
        Utils.getAreaCostWithTMR(orderedGates);
    }

    public void executeScoreCount() {
        RunScore runScore = new ScoreCount();
        ProbCircuit circuit = Terminal.getInstance().getProbCircuit();
        ArrayList<ProbSignal> inputs = circuit.getProbInputs();
        int inputLength;
        if (inputs.size() > 24) {
            inputLength = (int) Math.pow(2, 24);
        } else {
            inputLength = (int) Math.pow(2, inputs.size());
        }
        for (int i = 0; i < inputLength; i++) {
            InputVector iv = new InputVector(new BigInteger(String.valueOf(i), 10));
            System.out.println(i + ";" + iv.getBinaryString() + ";" + runScore.execute(iv).getScore());
        }
    }
    
}
