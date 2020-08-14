/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import datastructures.Cell;
import datastructures.CellLibrary;
import datastructures.Circuit;
import datastructures.Gate;
import datastructures.Signal;
import java.io.BufferedReader;

import levelDatastructures.LevelCircuit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import ops.CommonOps;

import static ops.CommonOps.matrixPrint;
import static ops.CommonOps.getITM;
import static ops.CommonOps.sortByValue;



import ops.PTMOps;

import ops.SerialPTMOps;

import javax.script.ScriptException;
import levelDatastructures.DepthGate;
import levelDatastructures.GateLevel;
import levelDatastructures.InterLevel;
import ops.PTMOps2;
import ops.PTMOps2Float;
import ops.SPROps;
import ops.SPROpsFloat;
import ops.SerialPTMOpsFloat;
import ops.SPRMultiPassV2BigDecimalOps;
import ops.SPRMultiPassV2Ops;
import ops.SerialPTMOpsFloatOptized;

import readers.ReadTxt;
import signalProbability.ProbCircuit;
import signalProbability.ProbGate;
import signalProbability.ProbSignal;


import writers.GenlibWriter;
import writers.VerilogWriter;
import writers.WriteFile;
import static ops.CommonOps.getMTBF;
import ops.FanoutOps;
import ops.SPRMultiPassV3Ops;
import wrv_algoritm.InputVector;
import wrv_algoritm.RunScore;
import wrv_algoritm.ScoreBySPR;
import wrv_algoritm.ScoreCount;
import wrv_algoritm.Utils;
import wrv_algoritm.WRVAlgoritm;

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
    
    public Commands() {
        
        helpTree.put("clear", "Clear terminal's window");
        helpTree.put("help", "Show this message!!!");
        helpTree.put("get_sonf_reliability", "Print the reliability value based on SONF PTM method");
        helpTree.put("init_level", "Initialize the Level Circuit");
        helpTree.put("print_gates", "Output circuit types based on readed verilog");
        helpTree.put("print_gates_level", "Output the gates in an specific depth level or all gates levels");
        helpTree.put("print_gatelevels", "Output the GateLevel of circuit with gate and type!!!");
        helpTree.put("print_genlib", "Output logical function, truth table (hex) of current genlib");
        helpTree.put("print_signals", "Output IOs and TOTAL Singals quantity");
        helpTree.put("print_types", "Output circuit types based on readed library");
        helpTree.put("ptm_big_decimal", "Output the reliability by Original PTM Method using Java's BigDecimal datatype");
        helpTree.put("ptm_double", "Output the reliability by Original PTM Method using double datatype");
        helpTree.put("ptm_float", "Output the reliability by Original PTM Method using float datatype");
        helpTree.put("ptm_matrix_size", "Show the matrix size of each circuit level");
        helpTree.put("quit", "Exit tool!!!");
        helpTree.put("read_genlib", "Read a genlib file");
        helpTree.put("read_verilog", "Read circuit based in a verilog");
        helpTree.put("write_genlib", "Export de current genlib to a file");
        helpTree.put("write_verilog", "Export de current circuit to a file");
        
    }
    
    public String getHelpDesc(String command) {
        return helpTree.get(command);
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
            terminal.terminalOutput(entry.getKey() + "   ####   " + entry.getValue());
        }
                
    }
    
    public void ReadGenlib(String filename) throws IOException, ScriptException {
        String path = CommonOps.getWorkPath(this) + "abc" + File.separator + filename;                             
        //String path = CommonOps.getWorkPath(this) + filename;                             

        Terminal.getInstance().initLibrary(path);        
    }
    
    public void ReadVerilog(String filename) throws IOException, Exception {
        String path = CommonOps.getWorkPath(this) + "abc" + File.separator + filename;
        //String path = CommonOps.getWorkPath(this) + filename;
        
        
        try {
            Terminal.getInstance().initCircuit(path);
            Terminal.getInstance().initLevelCircuit();
            Terminal.getInstance().initProbCircuit();
        } catch (ScriptException ex) {
            System.out.println(ex);
        }
    }
    
    public void PrintCircuitGates() {
        Circuit circuit = Terminal.getInstance().getCircuit();
        if(circuit == null) {
            Terminal.getInstance().terminalOutput("Circuit is Null!!!");
        } else {
            
            Map<String, Integer> gateTypes = new HashMap<String, Integer>();
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

        for (int i = 0; i < library.size(); i++) {          

            Terminal.getInstance().terminalOutput(library.get(i).getName() + " ---- " +
                                                  library.get(i).getFunctions().toString() + " ---- " +
                                                  library.get(i).getTruthTable());
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
    
    public void getReliabilityPTM(String reliability, String type) {
        
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
                result = "Reliability PTM (in BigDecimal) of " + pCircuit.getName() + " CIRCUIT is " + PTMOps2.getCircuitReliabilityByPTM(pCircuit);
                break;
            
            case "double":        
                result = "Reliability PTM (in double (EJML Library) of " + lcirc.getName() + " CIRCUIT is " + PTMOps.getPTM(reliability, lcirc, cellLib, false);
                break;
            
            case "float":                                          
                result = "Reliability PTM (in float) of " + pCircuit.getName() + " CIRCUIT is " + PTMOps2Float.getCircuitReliabilityByPTM(pCircuit);
                break;
                        
        }               
        
        Terminal.getInstance().terminalOutput(result);
        
        final long endTime = System.currentTimeMillis();
        String timeConsup = "## TIME CONSUPTION ## ==> " + Long.toString((endTime - startTime)) + " ms";
        Terminal.getInstance().terminalOutput(timeConsup);               
    }
    
    public void getReliabilitySPR(String reliability, String type) {
        
        final long startTime = System.currentTimeMillis();
        
        String result = "";     
        CellLibrary cellLib = Terminal.getInstance().getCellLibrary();
        ProbCircuit pCircuit = Terminal.getInstance().getProbCircuit();         
        cellLib.setPTMCells2(Float.valueOf(reliability));
        cellLib.setPTMCells(new BigDecimal(reliability));
        pCircuit.setPTMReliabilityMatrix();
        pCircuit.setDefaultProbSourceSignalMatrix();
        
        switch(type) {
            
            case "big_decimal":        
                result = "Reliability SPR (in BigDecimal) of " + pCircuit.getName() + " CIRCUIT is " + SPROps.getSPRReliability(pCircuit);
                break;
            
            case "float":                                          
                result = "Reliability SPR (in float) of " + pCircuit.getName() + " CIRCUIT is " + SPROpsFloat.getSPRReliability(pCircuit);
                break;
                        
        }               
        
        Terminal.getInstance().terminalOutput(result);
        System.out.println(result);
        
        final long endTime = System.currentTimeMillis();
        String timeConsup = "## TIME CONSUPTION ## ==> " + Long.toString((endTime - startTime)) + " ms";
        Terminal.getInstance().terminalOutput(timeConsup);               
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
    
    public void Report() {
        
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
    
    public void Foo() {
        
        final long startTime = System.currentTimeMillis();
        
        BigDecimal reliability = new BigDecimal("0.99");
        BigDecimal ptm = BigDecimal.ZERO;
        
        LevelCircuit lCircuit = Terminal.getInstance().getLevelCircuit();
        CellLibrary cellLib = Terminal.getInstance().getCellLibrary();
        int circuitIns = PTMOps.PowInt(2, lCircuit.getInputs().size());
        int circuitOuts = PTMOps.PowInt(2, lCircuit.getOutputs().size());
        int[] itm = new int[circuitIns];
        
        lCircuit.setTecReliability(reliability);
        
        BigDecimal auxReliability = lCircuit.getTecReliability();                
        
        cellLib.setPTMCells(BigDecimal.ONE);
        
        long timeITM = 0;
        long timePTM = 0;
        
        //ArrayList<Integer> teste = PTMOps.getITM(lCircuit);
        
        //System.out.println(teste);
        
        
        for (int i = 0; i < circuitIns; i++) {
                        
            
            for (int j = 0; j < circuitOuts; j++) {
                BigDecimal big = SerialPTMOps.getPTMCircuitIndex(Terminal.getInstance().getLevelCircuit(), Integer.toString(i), Integer.toString(j), reliability);
                if(big.compareTo(new BigDecimal("1")) == 0) {
                    itm[i] = j;
                    break;
                }
                
                System.out.println(i + "x" + j + "=");
            }
            
            System.out.println("Linha " + i);
            final long endTime = System.currentTimeMillis();
            String timeConsup = "## TIME CONSUPTION ## ==> " + Long.toString((endTime - startTime)) + " ms";            
            System.out.println(timeConsup);
        }
        
        timeITM = startTime - System.currentTimeMillis();
        
        System.out.println("Cheguei aqui...");
        
        cellLib.setPTMCells(auxReliability);
        
        
        
        for (int i = 0; i < itm.length; i++) {
            BigDecimal big = SerialPTMOps.getPTMCircuitIndex(Terminal.getInstance().getLevelCircuit(), Integer.toString(i), Integer.toString(itm[i]), reliability);
            System.out.println("("+i+"x"+itm[i]+") = " + big);
            ptm = ptm.add(big);
            
            final long endTime = System.currentTimeMillis();
            String timeConsup = "## TIME CONSUPTION ## ==> " + Long.toString((endTime - startTime)) + " ms";            
            System.out.println(timeConsup);
        }
        
        
        
        
        /* for (int i = 0; i < teste.size(); i++) {
            BigDecimal big = SerialPTMOps.getPTMCircuitIndex(Terminal.getInstance().getLevelCircuit(), Integer.toString(i), Integer.toString(itm[i]), reliability);
            System.out.println("("+i+"x"+itm[i]+") = " + big);
            
            final long endTime = System.currentTimeMillis();
            String timeConsup = "## TIME CONSUPTION ## ==> " + Long.toString((endTime - startTime)) + " ms";            
            System.out.println(timeConsup);
            ptm = ptm.add(big);
        } */
        
        
        ptm = ptm.divide(new BigDecimal(circuitIns));
        
        final long endTime = System.currentTimeMillis();
        String timeConsup = "## TIME CONSUPTION ## ==> " + Long.toString((endTime - startTime)) + " ms";
        
        System.out.println("Tempo ITM = " + timeITM);
        
        Terminal.getInstance().terminalOutput(timeConsup);
        Terminal.getInstance().terminalOutput("Circuit PTM is "+ptm);
    }
    
    public void Foo2() {
        
        final long startTime = System.currentTimeMillis();
        
        BigDecimal reliability = new BigDecimal("0.99");
        BigDecimal ptm = BigDecimal.ZERO;
        
        LevelCircuit lCircuit = Terminal.getInstance().getLevelCircuit();
        CellLibrary cellLib = Terminal.getInstance().getCellLibrary();
        int circuitIns = PTMOps.PowInt(2, lCircuit.getInputs().size());
        int circuitOuts = PTMOps.PowInt(2, lCircuit.getOutputs().size());
        
        int[] itm;
        
        ReadTxt read = new ReadTxt();
        
        try {
            itm = read.readFile("ITM-claGGv3.txt", circuitIns);
            
            for (int i = 0; i < itm.length; i++) {
                BigDecimal big = SerialPTMOps.getPTMCircuitIndex(Terminal.getInstance().getLevelCircuit(), Integer.toString(i), Integer.toString(itm[i]), reliability);
                System.out.println("("+i+"x0) = " + big);
        } 
            
        } catch (IOException ex) {
            Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               
        
        final long endTime = System.currentTimeMillis();
        String timeConsup = "## TIME CONSUPTION ## ==> " + Long.toString((endTime - startTime)) + " ms";
        
        Terminal.getInstance().terminalOutput(timeConsup);
        Terminal.getInstance().terminalOutput("Circuit PTM is "+ptm);
    }
    
    public void Foo3(String argument) {
        
        final long startTime = System.currentTimeMillis();
        
        String[] circuits = new String[]{
            //"NAND4v1.v",
            //"NAND4v2.v",
            //"NAND4v3.v",
            //"AOIv1.v",
            //"AOIv2.v",
            //"AOIv3.v",
            "claGGv2.v",
            "claGGv3.v",
        };
        
        for (int i = 0; i < circuits.length; i++) {
            try {
                Terminal.getInstance().executeCommand("read_verilog "+circuits[i]);
                Terminal.getInstance().executeCommand("init_level");
                Terminal.getInstance().executeCommand("get_ptm_reliability " + argument);
                Terminal.getInstance().executeCommand("get_sonf_reliability " + argument);
            } catch (ScriptException ex) {
                Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
               
        
        final long endTime = System.currentTimeMillis();
        String timeConsup = "## TIME CONSUPTION ## ==> " + Long.toString((endTime - startTime)) + " ms";
        
        Terminal.getInstance().terminalOutput(timeConsup);
    }
    
    public void Foo4() {
        
        double[] reliabilities = new double[]{
            0.7,
            0.75,
            0.8,
            0.85,
            0.9,
            0.95,
            0.99
        };
        
        for (int i = 0; i < reliabilities.length; i++) {
            
            String output = "########## Reliability " + reliabilities[i] + " #############";
            
            Terminal.getInstance().terminalOutput(output + "\n\n\n");
            
            try {
                Terminal.getInstance().executeCommand("foo3 "+reliabilities[i]);
            } catch (ScriptException ex) {
                Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
            }
        }            
    }
    
    //CALCULA AS MULTIPLICAÇÕES E SOMAS NECESSÁRIAS PARA SE OBTER AS MATRIZES PTM
    public void Foo5() {                                               
        
        final long startTime = System.currentTimeMillis();
        
        String[] circuits = new String[]{
            
            "c8_fritz.v",
            "c9_fritz.v",
            "c10_fritz.v",
            "c11_fritz.v",
            "c20_cadence.v",
            "c17v1_fritz.v",
            "c17v2_fritz.v",
            "c17v3_fritz.v",
            "c17v4_fritz.v",
            "c432_cadence.v",
            "c499_cadence.v",
            "c880_cadence.v",
            "c1355_cadence.v",
            "c1908_cadence.v",
            "c2670_cadence.v",
            "c3540_cadence.v",
            "c5315_cadence.v",
            "c6288_cadence.v",
            "c7552_cadence.v",
            
        };
        
        for (int i = 0; i < circuits.length; i++) {
            try {
                Terminal.getInstance().executeCommand("read_verilog "+circuits[i]);
                Terminal.getInstance().executeCommand("init_level");                
                
                LevelCircuit lCircuit = Terminal.getInstance().getLevelCircuit();
                
                PTMOps.getTotalMultiplicationsWithMatrixRepresentation(lCircuit);
                System.out.println("####WITHOUT####");
                PTMOps.getTotalMultiplicationsWithoutMatrixRepresentation(lCircuit);
                
            } catch (ScriptException ex) {
                Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
               
        
        final long endTime = System.currentTimeMillis();
        String timeConsup = "## TIME CONSUPTION ## ==> " + Long.toString((endTime - startTime)) + " ms";
        
        Terminal.getInstance().terminalOutput(timeConsup);
    }
    
    public void Foo6() {
        
//        CellLibrary cellLib = Terminal.getInstance().getCellLibrary();
//        
//        for (int i = 0; i < cellLib.getCells().size(); i++) {
//            System.out.println(cellLib.getCells().get(i));
//            System.out.println(Arrays.asList(cellLib.getCells().get(i).getComb()));
//        }
        
        
        
        //Timer timer = new Timer();
        
        //timer.schedule(new ReportTimer(), 0, 5000);                
        
        final long startTime = System.currentTimeMillis();
        
        long startMethod;
        
        ArrayList<Long> timeArray = new ArrayList<>();
        
        String[] circuits = new String[]{            
                   
//            "circuitoMinas.v",
//            "nandWithTMR.v",
            /*
             *  ISCAS89 VERSAO COMBINACIONAL COMPLETOS
             */
//              "s27_comb_Marcelinho.v",
//              "s208_comb_Marcelinho.v",         
//              "s298_comb_Marcelinho.v",         
//              "s344_comb_Marcelinho.v",         
//              "s349_comb.v",         
//              "s382_comb.v",         
//              "s386_comb.v",         
//              "s400_comb.v",         
//              "s420_comb.v",         
//              "s444_comb.v",         
//              "s510_comb.v",         
//              "s641_comb.v",         
//              "s713_comb.v",         
//              "s820_comb.v",         
//              "s832_comb.v",         
//              "s838_comb.v",         
//              "s953_comb.v",         
//              "s1196_comb.v",         
//              "s1238_comb.v",         
//              "s1423_comb.v",         
//              "s1488_comb.v",         
//              "s1494_comb.v",         
//              "s5378_comb.v",         
//              "s9234_comb.v",         
//              "s13207_comb.v",         
//              "s15850_comb.v",         
//              "s38417_comb.v",         
//              "s38584_comb.v",
              
            /*
             * CIRCUITOS UTILIZADOS NA DISSERTACAO
             * NA COMPARACAO SPR E SPR-MP
             */
//              "c17v1_fritz.v",            
//              "c17v2_fritz.v",            
//              "c17v3_fritz.v",
//              "c17v4_fritz.v",
//              "multiplex-4bits.v", 
//              "FAv1.v",
//              "FAv2.v",
//              "FAv3.v",  
//              "s27_comb_Marcelinho.v",
//              "s208_comb_Marcelinho.v",
//              "s298_n64.v",
//              "s344_n61.v",
//              "s349_n66.v",
//              "s382_n69.v",
//              "s386_n49.v",
//              "s400_n64.v",
//              "s420_Z.v",
              "s444_n109.v",
//              "s510_n78.v",
//              "s641_n178.v",
//              "s713_n177.v",
//              "s820_n95.v",              
//              "s832_n90.v",                         
//              "s838_n215.v",                           
//              "s953_n104.v",              
//              "s1196_G542.v",                          
//              "s1238_n117.v",                           
//              "s1423_n90.v",                                                     
//              "s1488_n75.v",                                                     
//              "s1494_n70.v",                                                   
//              "s5378_n240.v",                                                                                                                                                                                                     
//              "s9234_n676.v",                                                                                                                                                                                                                                                                                                                                                                                                         
//              "s13207_n594.v",                                                                                                                                                                                                               
//              "s15850_n460.v",                                                                                                                                                                                              
//              "s38417_n7962.v",
//              "s38584_n7656.v",
            /*
             * ########################### 
             */
            
            /*
             * CIRCUITOS UTILIZADOS NA DISSERTACAO
             * NA ANÁLISE DE PORTAS SPR
             */
//              "s9234_n676.v",
//              "s386_n49.v",
//              "s838_n215.v", 
//              "s382_n69.v",
//              "s400_n64.v",                            
//              "s27_comb_Marcelinho.v",
//              "s298_n64.v",
//              "s444_n109.v",
//              "s344_n61.v",
//              "s349_n66.v",
//              "s832_n90.v",
//              "s13207_n594.v",
//              "s38584_n7656.v",
//              "s510_n78.v",
//              "s1488_n75.v",
//              "s820_n95.v",
//              "s1494_n70.v",
              
              
//              "s208_comb_Marcelinho.v",                                                                                    
//              "s420_Z.v",              
//              
//              "s641_n178.v",
//              "s713_n177.v",
//                                                                                                           
//              "s953_n104.v",              
//              "s1196_G542.v",                          
//              "s1238_n117.v",                           
//              "s1423_n90.v",                                                     
//                                                                   
//                                                                 
//              "s5378_n240.v",                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
//                                                                                                                                                                                                                             
//              "s15850_n460.v",                                                                                                                                                                                              
//              "s38417_n7962.v",
//              
            /*
             * ########################### 
             */
              
              
//              "b01_comb.v",
//            "b02_comb.v",
//            "b03_comb.v",
//            "b06_comb.v",
//            "b06_comb_bench.v",
//              "s298_comb_Marcelinho.v",
//              "s344_comb_Marcelinho.v",
//            "c17Classic.v",
//            "c17v1_fritz.v",            
//            "c17v2_fritz.v",            
//            "c17v3_fritz.v",
//            "c17v4_fritz.v",
//            "multiplex-4bits.v", 
//            "FAv1.v",
//            "FAv2.v",
//            "FAv3.v",                                                                                                
//            "c8_fritz.v",
//            "c9_fritz.v",                        
//            "c10_fritz.v",
//            "c11_fritz.v",
//            "c20.v",
//            "fooCircuit2.v",
//            "c20_cadence_fanout_one.v",
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
            
            
            
            
            //"c432_cadence.v",
            /*
            "c499_cadence.v",                                    
            "c880_cadence.v",
            "c1355_cadence.v",
            "c1908_cadence.v",
            "c2670_cadence.v",
            "c3540_cadence.v",
            "c5315_cadence.v",
            "c6288_cadence.v",
            */            
            //"c7552_cadence.v",
            
            /*
            "invAndCircuit.v",
            
            "randomCircuit.v",
            
            "randomCircuitV2.v",
            */
            
        };
        
        String[] reliabilities = new String[]{                                  

//            "0.8",  
//            "0.85",
//            "0.9",
//            "0.95",
//            "0.96",
//            "0.97",
//            "0.98",
//            "0.99",            
//            "0.999",
            "0.9999",
//            "0.99999",
//            "0.999999",
//            "0.9999999",
//            "0.99999999",
//            "0.999999999",
//              "1",
//                      
//            "0.75",
//            "0.7",
//            "0.65",
//            "0.60",
//            "0.55",
//            "0.50",
//            "0.45",
//            "0.40",
//            "0.35",
//            "0.30",
//            "0.25",
//            "0.20",
//            "0.15",
//            "0.10",
//            "0.05",
//            "0.01",
            
            
        };
        
        float[] floatReliabilities = new float[]{
            0.99f,            
            //0.95f,            
            //0.9f,            
            //0.85f,            
            //0.8f,            
            //0.75f,            
            //0.7f,            
            //0.65f,                       
        };
        
        
        try {
            for (int j = 0; j < circuits.length; j++) {
                Terminal.getInstance().executeCommand("read_verilog "+circuits[j]);
                LevelCircuit lCircuit = Terminal.getInstance().getLevelCircuit();
                ProbCircuit pCircuit = ProbCircuit.create(lCircuit.getName(), lCircuit.getSignals(), lCircuit.getGates(), lCircuit.getGateLevels());

                Map newMap = null;
                Map newMap2 = null;
                
                Map<String, BigDecimal> mtbfExato0 = null;
                Map<String, BigDecimal> fitExato0 = null;
                Map<String, BigDecimal> mtbfExato099999 = null;
                Map<String, BigDecimal> fitExato099999 = null;
                Map<String, BigDecimal> mtbfSpr0 = null;
                Map<String, BigDecimal> fitSpr0 = null;
                Map<String, BigDecimal> mtbfSpr1 = null;
                Map<String, BigDecimal> fitSpr099999 = null;
                
                ArrayList<String> goldArray = new ArrayList<>();
                ArrayList<String> fooSet;
                ArrayList<Object> fooBigDecimal;
                HashMap<String,BigDecimal> sprMap = new HashMap<String, BigDecimal>();
                HashMap<String, BigDecimal> sprmpMap = new HashMap<String, BigDecimal>();
                
                HashMap<String, BigDecimal> hashMtbfExato0 = new HashMap<String, BigDecimal>();
                HashMap<String, BigDecimal> hashFitExato0 = new HashMap<String, BigDecimal>();
                HashMap<String, BigDecimal> hashMtbfExato099999 = new HashMap<String, BigDecimal>();
                HashMap<String, BigDecimal> hashFitExato099999 = new HashMap<String, BigDecimal>();
                HashMap<String, BigDecimal> hashMtbfSpr0 = new HashMap<String, BigDecimal>();
                HashMap<String, BigDecimal> hashFitSpr0 = new HashMap<String, BigDecimal>();
                HashMap<String, BigDecimal> hashMtbfSpr1 = new HashMap<String, BigDecimal>();
                HashMap<String, BigDecimal> hashFitSpr099999 = new HashMap<String, BigDecimal>();
                
                ArrayList<String> classficaMTBFExato0;
                ArrayList<String> classficaFITExato0;
                ArrayList<String> classficaMTBFExato099999;
                ArrayList<String> classficaFITExato099999;
                ArrayList<String> classficaMTBFSPR0;
                ArrayList<String> classficaFITSPR0;
                ArrayList<String> classficaMTBFSPR1;
                ArrayList<String> classficaFITSPR099999;
                
//                ArrayList<ArrayList<Boolean>> combinacoes = getCombVectors(0, PTMOps.PowInt(2, pCircuit.getProbInputs().size()) - 1, pCircuit.getProbInputs().size());
//                pCircuit.propagate(combinacoes);
//                ArrayList<Boolean> saidas = pCircuit.getProbOutputs().get(0).getSignalValues();
//                int falseCounter = 0;
//                
//                for (int i = 0; i < saidas.size(); i++) {
//                    if(!saidas.get(i)) {
//                        System.out.println("TEM false!!");
//                        falseCounter++;
//                    }
//                }
//                
//                System.out.println("No total foram " + falseCounter + " Falses");
                
                
                
                
                
                //System.out.println(Arrays.toString(getTotalOperationsSerialPTM(pCircuit)));
                
                //System.out.println(PTMOps.PowInt(2, pCircuit.getProbInputs().size()) - 1);
                //ArrayList<boolean[]> inCombination = getCombVectors(0, PTMOps.PowInt(2, pCircuit.getProbInputs().size()) - 1, pCircuit.getProbInputs().size());
                
                
                
//                System.out.println(pCircuit.getName());
//                
//                int[] itm = getITM(pCircuit);
//                int[] itm2 = PTMOps2.getCircuitITM(pCircuit);
//                
//                for (int i = 0; i < itm.length; i++) {
//                    if(itm[i] != itm2[i]) {
//                        System.out.println("DEU MERDA");
//                    }
//                    //System.out.println("" + i + " --> " + itm[i] + " ==> " + itm2[i+1]);
//                }
                

                
                /*
                
                System.out.println("pass " + pass);
                for (int i = 0; i < pass; i++) {
                    startMethod = System.currentTimeMillis();
                    
                    ArrayList<ArrayList<Boolean>> inCombination = getCombVectors(begin, end-1, pCircuit.getProbInputs().size());
                    pCircuit.propagate(inCombination);
                    begin = begin + foo;
                    end = end + foo;
                    System.out.println("Pass: " + i + " --> " + (System.currentTimeMillis() - startMethod));
                }
                
                */
                
//                System.out.println("PTM-BIGDECIMAL");
//                
//                for (int i = 0; i < reliabilities.length; i++) {
//                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
//                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));
//
//                    pCircuit.clearProbSignalsMatrix();
//                    pCircuit.setPTMReliabilityMatrix();
//                    pCircuit.setPatternSourceProbSignal();
//                    pCircuit.setProbSignalStates(false);
//                    
//                    System.out.println(PTMOps2.getCircuitReliabilityByPTM(pCircuit));
//                }
////                
//                System.out.println("PTM-FLOAT");
//                
//                for (int i = 0; i < reliabilities.length; i++) {
//                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
//                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));
//
//                    pCircuit.clearProbSignalsMatrix();
//                    pCircuit.setPTMReliabilityMatrix();
//                    pCircuit.setPatternSourceProbSignal();
//                    pCircuit.setProbSignalStates(false);
//                    
//                    System.out.println(PTMOps2Float.getCircuitReliabilityByPTM(pCircuit));
//                }
//                
//            
                
              
//                System.out.println("PTM-BIG");
//                
//                for (int i = 0; i < reliabilities.length; i++) {
//                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
//                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));
//
//                    pCircuit.clearProbSignalsMatrix();
//                    pCircuit.setPTMReliabilityMatrix();
//                    pCircuit.setPatternSourceProbSignal();
//                    pCircuit.setProbSignalStates(false);
//                    
//                    BigDecimal ptmValue = PTMOps2.getCircuitReliabilityByPTM(pCircuit);
//                    
//                    System.out.println(ptmValue);
//                }
//                
//                System.out.println("PTM-FLOAT");
//                
//                for (int i = 0; i < reliabilities.length; i++) {
//                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
//                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));
//
//                    pCircuit.clearProbSignalsMatrix();
//                    pCircuit.setPTMReliabilityMatrix();
//                    pCircuit.setPatternSourceProbSignal();
//                    pCircuit.setProbSignalStates(false);
//                    
//                    float ptmValue = PTMOps2Float.getCircuitReliabilityByPTM(pCircuit);
//                    
//                    System.out.println(ptmValue);
//                }
                
//                System.out.println("SPR-BIG");
//                
//                for (int i = 0; i < reliabilities.length; i++) {
//                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
//                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));
//
//                    pCircuit.clearProbSignalsMatrix();
//                    pCircuit.setPTMReliabilityMatrix();
//                    pCircuit.setPatternSourceProbSignal();
//                    pCircuit.setProbSignalStates(false);
//                    
//                    System.out.println(SPROps.getSPRReliability(pCircuit));
//                }
                        
                
//                System.out.println("SPR-FLOAT");
//                
//                for (int i = 0; i < reliabilities.length; i++) {
//                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
//                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));
//
//                    pCircuit.clearProbSignalsMatrix();
//                    pCircuit.setPTMReliabilityMatrix();
//                    pCircuit.setPatternSourceProbSignal();
//                    pCircuit.setProbSignalStates(false);
//                    
//                    System.out.println(SPROpsFloat.getSPRReliability(pCircuit));
//                }
                
//                System.out.println("SPR-MP_BIGDECIMAL");
//                
//                for (int i = 0; i < reliabilities.length; i++) {
//                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
//                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));
//
//                    pCircuit.clearProbSignalsMatrix();
//                    pCircuit.setPTMReliabilityMatrix();
//                    pCircuit.setPatternSourceProbSignal();
//                    pCircuit.setProbSignalStates(false);
//                    
//                    System.out.println("--> " + SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit));
//                    System.out.println("--> " + SPRMultiPassV2BigDecimalOps.getSPRMultiPassReliaiblity(pCircuit));
//                }
                
                
                
                /*
                System.out.println("SPR-MP_FLOAT");
                
                for (int i = 0; i < reliabilities.length; i++) {
                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));

                    pCircuit.clearProbSignalsMatrix();
                    pCircuit.setPTMReliabilityMatrix();
                    pCircuit.setPatternSourceProbSignal();
                    pCircuit.setProbSignalStates(false);
                    
                    System.out.println(SPRMultiPassV2Ops.getSPRMultiPassReliaiblity(pCircuit));
                }
                
                System.out.println("Inherent Big");
                
                for (int i = 0; i < reliabilities.length; i++) {
                    
                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));

                    pCircuit.clearProbSignalsMatrix();
                    pCircuit.setPTMReliabilityMatrix();
                    pCircuit.setPatternSourceProbSignal();
                    pCircuit.setProbSignalStates(false);
                    
                    BigDecimal inherent = inherentReliability(pCircuit, reliabilities[i]);

                    System.out.println(inherent.toPlainString());
                }
//                
                System.out.println("Masking Cap PTM Big");
                
                for (int i = 0; i < reliabilities.length; i++) {
                    
                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));

                    pCircuit.clearProbSignalsMatrix();
                    pCircuit.setPTMReliabilityMatrix();
                    pCircuit.setPatternSourceProbSignal();
                    pCircuit.setProbSignalStates(false);
                    
                    BigDecimal inherent = inherentReliability(pCircuit, reliabilities[i]);
                    BigDecimal ptmValue = PTMOps2.getCircuitReliabilityByPTM(pCircuit);

                    System.out.println(ptmValue.subtract(inherent).toPlainString());
                }
//                
                System.out.println("Masking Cap SPR Big");
                
                for (int i = 0; i < reliabilities.length; i++) {
                    
                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));

                    pCircuit.clearProbSignalsMatrix();
                    pCircuit.setPTMReliabilityMatrix();
                    pCircuit.setPatternSourceProbSignal();
                    pCircuit.setProbSignalStates(false);
                    
                    BigDecimal inherent = inherentReliability(pCircuit, reliabilities[i]);
                    BigDecimal sprValue = SPROps.getSPRReliability(pCircuit);

                    System.out.println(sprValue.subtract(inherent).toPlainString());
                }
                
                System.out.println("Inherent Float");
                
                for (int i = 0; i < reliabilities.length; i++) {
                    
                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));

                    pCircuit.clearProbSignalsMatrix();
                    pCircuit.setPTMReliabilityMatrix();
                    pCircuit.setPatternSourceProbSignal();
                    pCircuit.setProbSignalStates(false);
                    
                    float inherent = CommonOps.inherentReliabilityFloat(pCircuit, reliabilities[i]);

                    System.out.println(inherent);
                }
                
                System.out.println("Masking Cap PTM Float");
                
                for (int i = 0; i < reliabilities.length; i++) {
                    
                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));

                    pCircuit.clearProbSignalsMatrix();
                    pCircuit.setPTMReliabilityMatrix();
                    pCircuit.setPatternSourceProbSignal();
                    pCircuit.setProbSignalStates(false);
                    
                    float inherent = CommonOps.inherentReliabilityFloat(pCircuit, reliabilities[i]);
                    float ptmValue = PTMOps2Float.getCircuitReliabilityByPTM(pCircuit);
                    
                    double dFoo = ptmValue-inherent;
                    BigDecimal foo = new BigDecimal(dFoo);

                    System.out.println(foo.toPlainString());
                }
//                
                System.out.println("Masking Cap SPR Float");
                
                for (int i = 0; i < reliabilities.length; i++) {
                    
                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));

                    pCircuit.clearProbSignalsMatrix();
                    pCircuit.setPTMReliabilityMatrix();
                    pCircuit.setPatternSourceProbSignal();
                    pCircuit.setProbSignalStates(false);
                    
                    float inherent = CommonOps.inherentReliabilityFloat(pCircuit, reliabilities[i]);
                    float sprValue = SPROpsFloat.getSPRReliability(pCircuit);
                    
                    double dFoo = sprValue-inherent;
                    BigDecimal foo = new BigDecimal(dFoo);

                    System.out.println(foo.toPlainString());
                }
                
                ArrayList<ProbSignal> primeiros50 = new ArrayList<>();
                ArrayList<ProbSignal> ultimos50 = new ArrayList<>();
                ArrayList<ProbSignal> circuitsFanouts = pCircuit.getFanouts();                                        

                int flagNumber = ( (circuitsFanouts.size() / 2) + (circuitsFanouts.size() % 2) );                                        

                for (int k = 0; k < flagNumber; k++) {
                    primeiros50.add(circuitsFanouts.get(k));
                }


                if(circuitsFanouts.size() % 2 == 0) {
                    for (int k = flagNumber; k < circuitsFanouts.size(); k++) {
                        ultimos50.add(circuitsFanouts.get(k));
                    }
                } else {
                    for (int k = flagNumber -1; k < circuitsFanouts.size(); k++) {
                        ultimos50.add(circuitsFanouts.get(k));
                    }
                }
////                    
//                    
                if (circuitsFanouts.size() != 0 && circuitsFanouts.size() != 1) {

                    System.out.println("50%IN");
                    for (int i = 0; i < reliabilities.length; i++) {
                        Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                        Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));
                        pCircuit.clearProbSignalsMatrix();
                        pCircuit.setPTMReliabilityMatrix();
                        pCircuit.setPatternSourceProbSignal();
                        pCircuit.setProbSignalStates(false);

                        System.out.println(ops.SPRMultiPassV2BigDecimalOps.getSPRMultiPassReliaiblity(pCircuit, primeiros50));
                    }

                    System.out.println("50%OUT");
                    for (int i = 0; i < reliabilities.length; i++) {
                        Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                        Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));
                        pCircuit.clearProbSignalsMatrix();
                        pCircuit.setPTMReliabilityMatrix();
                        pCircuit.setPatternSourceProbSignal();
                        pCircuit.setProbSignalStates(false);

                        System.out.println(ops.SPRMultiPassV2BigDecimalOps.getSPRMultiPassReliaiblity(pCircuit, ultimos50));
                    }


                } else if(circuitsFanouts.size() == 1) {                                                

                    startMethod = System.nanoTime();
                    //System.out.println(ops.SPRMultiPassV2BigDecimalOps.getSPRMultiPassReliaiblity(pCircuit, circuitsFanouts) + " <== MultiPass 100% BigDecimal(" + reliabilities[i] + "): ## TIME CONSUPTION ## ==> " + Long.toString((System.nanoTime() - startMethod) / 1000) + " microseconds");                                                

                }
//                    
//                    
                for (int k = 0; k < circuitsFanouts.size(); k++) {

                    System.out.println("FANOUT == " + (k+1));

                    for (int i = 0; i < reliabilities.length; i++) {
                        Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                        Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));
                        pCircuit.clearProbSignalsMatrix();
                        pCircuit.setPTMReliabilityMatrix();
                        pCircuit.setPatternSourceProbSignal();
                        pCircuit.setProbSignalStates(false);

                        System.out.println(ops.SPRMultiPassV2BigDecimalOps.getSPRMultiPassReliaiblity(pCircuit, circuitsFanouts.get(k)));  
                    }


                    //System.out.println("## " + circuitsFanouts.get(k) + " ##");
                    //System.out.println(ops.SPRMultiPassV2Ops.getSPRMultiPassReliaiblity(pCircuit, circuitsFanouts.get(k)));


                }
                
                
                //
                //
                //
                //   VALUES IN FIT
                //
                //
                //
                
                System.out.println("PTM-BIG ## FIT ##");
                
                for (int i = 0; i < reliabilities.length; i++) {
                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));

                    pCircuit.clearProbSignalsMatrix();
                    pCircuit.setPTMReliabilityMatrix();
                    pCircuit.setPatternSourceProbSignal();
                    pCircuit.setProbSignalStates(false);
                    
                    BigDecimal ptmValue = PTMOps2.getCircuitReliabilityByPTM(pCircuit);
                    
                    System.out.println(getFIT(ptmValue));
                }
                
                System.out.println("PTM-FLOAT ## FIT ##");
                
                for (int i = 0; i < reliabilities.length; i++) {
                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));

                    pCircuit.clearProbSignalsMatrix();
                    pCircuit.setPTMReliabilityMatrix();
                    pCircuit.setPatternSourceProbSignal();
                    pCircuit.setProbSignalStates(false);
                    
                    float ptmValue = PTMOps2Float.getCircuitReliabilityByPTM(pCircuit);
                    
                    double dFoo = CommonOps.getFITFloat(ptmValue);
                    
                    BigDecimal foo = new BigDecimal(dFoo);

                    System.out.println(foo.toPlainString());
                }
                
                System.out.println("SPR-BIG ## FIT ##");
                
                for (int i = 0; i < reliabilities.length; i++) {
                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));

                    pCircuit.clearProbSignalsMatrix();
                    pCircuit.setPTMReliabilityMatrix();
                    pCircuit.setPatternSourceProbSignal();
                    pCircuit.setProbSignalStates(false);
                    
                    System.out.println(getFIT(SPROps.getSPRReliability(pCircuit)));
                }
                
                System.out.println("SPR-FLOAT ## FIT ##");
                
                for (int i = 0; i < reliabilities.length; i++) {
                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));

                    pCircuit.clearProbSignalsMatrix();
                    pCircuit.setPTMReliabilityMatrix();
                    pCircuit.setPatternSourceProbSignal();
                    pCircuit.setProbSignalStates(false);
                    
                    double dFoo = CommonOps.getFITFloat(SPROpsFloat.getSPRReliability(pCircuit));
                    
                    BigDecimal foo = new BigDecimal(dFoo);

                    System.out.println(foo.toPlainString());
                    
                }
                
                System.out.println("SPR-MP_BIGDECIMAL ## FIT ##");
                
                for (int i = 0; i < reliabilities.length; i++) {
                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));

                    pCircuit.clearProbSignalsMatrix();
                    pCircuit.setPTMReliabilityMatrix();
                    pCircuit.setPatternSourceProbSignal();
                    pCircuit.setProbSignalStates(false);
                    
                    System.out.println(getFIT(SPRMultiPassV2BigDecimalOps.getSPRMultiPassReliaiblity(pCircuit)));
                }
                
                System.out.println("SPR-MP_FLOAT  ## FIT ##");
                
                for (int i = 0; i < reliabilities.length; i++) {
                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));

                    pCircuit.clearProbSignalsMatrix();
                    pCircuit.setPTMReliabilityMatrix();
                    pCircuit.setPatternSourceProbSignal();
                    pCircuit.setProbSignalStates(false);
                    
                    double dFoo = CommonOps.getFITFloat(SPRMultiPassV2Ops.getSPRMultiPassReliaiblity(pCircuit));
                    
                    BigDecimal foo = new BigDecimal(dFoo);

                    System.out.println(foo.toPlainString());

                }
                
                System.out.println("Inherent Big  ## FIT ##");
                
                for (int i = 0; i < reliabilities.length; i++) {
                    
                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));

                    pCircuit.clearProbSignalsMatrix();
                    pCircuit.setPTMReliabilityMatrix();
                    pCircuit.setPatternSourceProbSignal();
                    pCircuit.setProbSignalStates(false);
                    
                    BigDecimal inherent = inherentReliability(pCircuit, reliabilities[i]);

                    System.out.println(getFIT(inherent).toPlainString());
                }
                
                System.out.println("Inherent Float  ## FIT ##");
                
                for (int i = 0; i < reliabilities.length; i++) {
                    
                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));

                    pCircuit.clearProbSignalsMatrix();
                    pCircuit.setPTMReliabilityMatrix();
                    pCircuit.setPatternSourceProbSignal();
                    pCircuit.setProbSignalStates(false);
                    
                    float inherent = inherentReliabilityFloat(pCircuit, reliabilities[i]);
                    
                    double dFoo = CommonOps.getFITFloat(inherent);
                    
                    BigDecimal foo = new BigDecimal(dFoo);

                    System.out.println(foo.toPlainString());
                }
                

                

                
                
////                    
//                    
                if (circuitsFanouts.size() != 0 && circuitsFanouts.size() != 1) {

                    System.out.println("50%IN  ## FIT ##");
                    for (int i = 0; i < reliabilities.length; i++) {
                        Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                        Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));
                        pCircuit.clearProbSignalsMatrix();
                        pCircuit.setPTMReliabilityMatrix();
                        pCircuit.setPatternSourceProbSignal();
                        pCircuit.setProbSignalStates(false);

                        System.out.println(getFIT(ops.SPRMultiPassV2BigDecimalOps.getSPRMultiPassReliaiblity(pCircuit, primeiros50)));
                    }

                    System.out.println("50%OUT  ## FIT ##");
                    for (int i = 0; i < reliabilities.length; i++) {
                        Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                        Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));
                        pCircuit.clearProbSignalsMatrix();
                        pCircuit.setPTMReliabilityMatrix();
                        pCircuit.setPatternSourceProbSignal();
                        pCircuit.setProbSignalStates(false);

                        System.out.println(getFIT(ops.SPRMultiPassV2BigDecimalOps.getSPRMultiPassReliaiblity(pCircuit, ultimos50)));
                    }


                } else if(circuitsFanouts.size() == 1) {                                                

                    startMethod = System.nanoTime();
                    //System.out.println(ops.SPRMultiPassV2BigDecimalOps.getSPRMultiPassReliaiblity(pCircuit, circuitsFanouts) + " <== MultiPass 100% BigDecimal(" + reliabilities[i] + "): ## TIME CONSUPTION ## ==> " + Long.toString((System.nanoTime() - startMethod) / 1000) + " microseconds");                                                

                }
//                    
//                    
                for (int k = 0; k < circuitsFanouts.size(); k++) {

                    System.out.println("FANOUT == " + (k+1) + " ## FIT ##");

                    for (int i = 0; i < reliabilities.length; i++) {
                        Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
                        Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));
                        pCircuit.clearProbSignalsMatrix();
                        pCircuit.setPTMReliabilityMatrix();
                        pCircuit.setPatternSourceProbSignal();
                        pCircuit.setProbSignalStates(false);

                        System.out.println(getFIT(ops.SPRMultiPassV2BigDecimalOps.getSPRMultiPassReliaiblity(pCircuit, circuitsFanouts.get(k))));  
                    }


                    //System.out.println("## " + circuitsFanouts.get(k) + " ##");
                    //System.out.println(ops.SPRMultiPassV2Ops.getSPRMultiPassReliaiblity(pCircuit, circuitsFanouts.get(k)));


                }
//                

//              */
                
//                System.out.println("SPR-BIG ## FIT ##");
//                
//                for (int i = 0; i < reliabilities.length; i++) {
//                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
//                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));
//
//                    pCircuit.clearProbSignalsMatrix();                    
//                    pCircuit.setPatternSourceProbSignal();
//                    pCircuit.setProbSignalStates(false);
//                    
//                    for (int k = 0; k < pCircuit.getProbGates().size(); k++) {
//                        pCircuit.getProbGates().get(k).setGateReliability(new BigDecimal("0"));
//                        pCircuit.setPTMReliabilityMatrix();
//                        
//                        //sprMap.put(getFIT(SPROps.getSPRReliability(pCircuit)), pCircuit.getProbGates().get(k).getId());
//                        sprMap.put(pCircuit.getProbGates().get(k).getId(), SPROps.getSPRReliability(pCircuit));
//                        pCircuit.getProbGates().get(k).setGateReliability(null);
//                    }
//                    
//                }

                boolean valoresTabela = false;
                
                
                boolean analiseClassifica = false;
                boolean characteristicas = false;
                boolean fooAnalisys = true;
                boolean analiseFanouts = false;
                boolean analiseTemposSPR = false;
                boolean analisePortasSPR = false;
                boolean tiposPortas = false;
                
                
                
                
//                boolean analiseClassifica = true;
//                boolean characteristicas = false;
//                
                
                if(characteristicas) {
                    
                    
                    System.out.println("### " + pCircuit.getName() + " ###");
                    System.out.println("Portas = " + pCircuit.getProbGates().size());
                    System.out.println("Entradas = " + pCircuit.getProbInputs().size());
                    System.out.println("Saídas = " + pCircuit.getProbOutputs().size());
                    System.out.println("Fanouts = " + pCircuit.getFanouts().size());
                    System.out.println("Niveis = " + pCircuit.getProbGateLevels().size());
                    System.out.println("");
                    
                    
                }
                
                if(fooAnalisys) {
                                        
                    
                    for (int i = 0; i < reliabilities.length; i++) {
                        Terminal.getInstance().getCellLibrary().setPTMCells2(Float.valueOf(reliabilities[i]));
                        Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal(reliabilities[i]));

                        pCircuit.clearProbSignalsMatrix();                    
                        pCircuit.setDefaultProbSourceSignalMatrix();
                        pCircuit.setProbSignalStates(false);
                        pCircuit.setPTMReliabilityMatrix();
                        
                        Timer timer = new Timer();
        
                        timer.schedule(new ReportTimer(), 0, 15000);
                        
                        System.out.println("### " + pCircuit.getName() + " ###");
                        
                        long timeStamp = System.currentTimeMillis();
                        
                        BigDecimal sprMPreliability = SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit);
                        BigDecimal mtbfSPR_MP = getMTBF(sprMPreliability);
                        
                        timeStamp = System.currentTimeMillis() - timeStamp;
                        
                        System.out.println("SPR-MP Reliability ==> " + sprMPreliability);
                        System.out.println("SPR-MP Reliability in MTBF ==> " + mtbfSPR_MP);
                        System.out.println("TimeStamp SPR-MP ==> " + timeStamp);
                        
                        System.out.println("");

                        System.out.println("Circuit Name = " + pCircuit.getName());
                        System.out.println("Quantidade de Sinais = " + pCircuit.getProbSignals().size());                                                
                        
                        
                        
                        timeStamp = System.currentTimeMillis();
                        BigDecimal sprReliability = SPROps.getSPRReliability(pCircuit);
                        BigDecimal mtbfSPR = getMTBF(sprReliability);
                        timeStamp = System.currentTimeMillis() - timeStamp;
                        
                        System.out.println("SPR Reliability ==> " + sprReliability);
                        System.out.println("SPR Reliability in MTBF ==> " + mtbfSPR);
                        System.out.println("TimeStamp SPR-MP ==> " + timeStamp);
                        
                        timer.cancel();
                        System.out.println("");
                        System.out.println("");
                        System.out.println("");
                        System.out.println("##################################################################################################");
                        System.out.println("##################################################################################################");
                        System.out.println("##################################################################################################");
                        System.out.println("##################################################################################################");
                        System.out.println("##################################################################################################");
                        System.out.println("##################################################################################################");
                        System.out.println("##################################################################################################");
                        System.out.println("##################################################################################################");
                        System.out.println("");
                        System.out.println("");
                        System.out.println("");
                    }
                }
                
                
                for (int i = 0; i < reliabilities.length; i++) {
                    
                    String[] gateReliabilities = new String[]{                                  
//                        "0.0",                        
//                        "0.01",                        
//                        "0.02",                        
//                        "0.03",                        
//                        "0.04",                        
//                        "0.05",                        
//                        "0.06",                        
//                        "0.07",                        
//                        "0.08",                        
//                        "0.09",                        
//                        "0.1",                        
//                        "0.11",                        
//                        "0.12",                        
//                        "0.13",                        
//                        "0.14",                        
//                        "0.15",                        
//                        "0.16",                        
//                        "0.17",                        
//                        "0.18",                        
//                        "0.19",                        
//                        "0.2",                        
//                        "0.21",                        
//                        "0.22",                        
//                        "0.23",                        
//                        "0.24",                        
//                        "0.25",                        
//                        "0.26",                        
//                        "0.27",                        
//                        "0.28",                        
//                        "0.29",                        
//                        "0.3",                        
//                        "0.31",                        
//                        "0.32",                        
//                        "0.33",                        
//                        "0.34",                        
//                        "0.35",                        
//                        "0.36",                        
//                        "0.37",                        
//                        "0.38",                        
//                        "0.39",                        
//                        "0.4",                                                
//                        "0.41",                                                
//                        "0.42",                                                
//                        "0.43",                                                
//                        "0.44",                                                
//                        "0.45",                                                
//                        "0.46",                                                
//                        "0.47",                                                
//                        "0.48",                                                
//                        "0.49",                                                                        
//                        "0.5",                                                
//                        "0.51",                                                
//                        "0.52",                                                
//                        "0.53",                                                
//                        "0.54",                                                
//                        "0.55",                                                
//                        "0.56",                                                
//                        "0.57",                                                
//                        "0.58",                                                
//                        "0.59",                                                
//                        "0.6",                                                
//                        "0.7",                                                
//                        "0.8",                                                
//                        "0.9",                                                
//                        "0.99",                                                
//                        "0.999",                                                
//                        "0.9999",                                                
                        "0.99999",                                                                                              
                    };
                    //System.out.println("###--- " + reliabilities[i] + " ---###");
                    
                    if (valoresTabela) {
                        for (int k = 0; k < pCircuit.getProbGates().size(); k++) {
                            System.out.println("###--- " + pCircuit.getProbGates().get(k) + " ---###");
                            for (int l = 0; l < gateReliabilities.length; l++) {
                                Terminal.getInstance().getCellLibrary().setPTMCells2(Float.valueOf(reliabilities[i]));
                                Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal(reliabilities[i]));

                                pCircuit.clearProbSignalsMatrix();                    
                                pCircuit.setDefaultProbSourceSignalMatrix();
                                pCircuit.setProbSignalStates(false);
                                pCircuit.getProbGates().get(k).setGateReliability(new BigDecimal(gateReliabilities[l]));
                                pCircuit.setPTMReliabilityMatrix();

                                //System.out.println(getFIT(SPROps.getSPRReliability(pCircuit)));
                                System.out.println(getMTBF(SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit)));
                                //System.out.println(gateReliabilities[l] + " ==> " + SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit));                            
                                pCircuit.getProbGates().get(k).setGateReliability(null);                                                        
                            }                                                                                                                    
                        }
                    }                    
                    
                    
                    if(analiseClassifica) {
//                        for (ProbGate pGate: pCircuit.getProbGates()) {
//                            if(pGate.getType().getName().equals("VOTADOR")) {
//                                Terminal.getInstance().getCellLibrary().setPTMCells2(Float.valueOf(reliabilities[i]));
//                                Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal(reliabilities[i]));
//
//                                pCircuit.clearProbSignalsMatrix();                    
//                                pCircuit.setDefaultProbSourceSignalMatrix();
//                                pCircuit.setProbSignalStates(false);
//                                pGate.setGateReliability(BigDecimal.ONE);
//                                pCircuit.setPTMReliabilityMatrix();
//                                
//                                System.out.println("==> " + SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit));
//                                
//                            }
//                        }
                                            

                            //Pegar o Comparador GOLD!!
//                        if(sprmpMap.isEmpty()) {
//                            for (int k = 0; k < pCircuit.getProbGates().size(); k++) {
//                                Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
//                                Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));
//
//                                pCircuit.clearProbSignalsMatrix();                    
//                                pCircuit.setPatternSourceProbSignal();
//                                pCircuit.setProbSignalStates(false);
//                                pCircuit.getProbGates().get(k).setGateReliability(new BigDecimal(gateReliabilities[l]));
//                                pCircuit.setPTMReliabilityMatrix();
//
//                                sprmpMap.put(pCircuit.getProbGates().get(k).getId(), getFIT(SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit)).setScale(9, RoundingMode.HALF_UP));
//                                pCircuit.getProbGates().get(k).setGateReliability(null);                                                        
//                            }
//
//                            newMap = sortByValue(sprmpMap);
//                            goldArray = new ArrayList<>(newMap.keySet());
//                            System.out.println("O gold eh:  " + newMap.keySet());
//                            System.out.println("gold Array:  " + newMap.keySet());
//                            System.out.println("[" + reliabilities[i] + "] ==> " + newMap);
//                        }

                        System.out.println("CIRCUITO: " + pCircuit.getName());

                        /***
                        *         _______..______   .______              .___  ___. .______   
                        *        /       ||   _  \  |   _  \             |   \/   | |   _  \  
                        *       |   (----`|  |_)  | |  |_)  |     ______ |  \  /  | |  |_)  | 
                        *        \   \    |   ___/  |      /     |______||  |\/|  | |   ___/  
                        *    .----)   |   |  |      |  |\  \----.        |  |  |  | |  |      
                        *    |_______/    | _|      | _| `._____|        |__|  |__| | _|      
                        *                                                                     
                        */

                        System.out.println("## SPR-MP ##");
                        
/***
 *         _______..______   .______              .___  ___. .______        ___                 .___  ___. .___________..______    _______ 
 *        /       ||   _  \  |   _  \             |   \/   | |   _  \      / _ \                |   \/   | |           ||   _  \  |   ____|
 *       |   (----`|  |_)  | |  |_)  |     ______ |  \  /  | |  |_)  |    | | | |     ______    |  \  /  | `---|  |----`|  |_)  | |  |__   
 *        \   \    |   ___/  |      /     |______||  |\/|  | |   ___/     | | | |    |______|   |  |\/|  |     |  |     |   _  <  |   __|  
 *    .----)   |   |  |      |  |\  \----.        |  |  |  | |  |         | |_| |               |  |  |  |     |  |     |  |_)  | |  |     
 *    |_______/    | _|      | _| `._____|        |__|  |__| | _|          \___/                |__|  |__|     |__|     |______/  |__|     
 *                                                                                                                                         
 */
                        for (int k = 0; k < pCircuit.getProbGates().size(); k++) {
                                Terminal.getInstance().getCellLibrary().setPTMCells2(Float.valueOf(reliabilities[i]));
                                Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal(reliabilities[i]));

                                pCircuit.clearProbSignalsMatrix();                    
                                pCircuit.setDefaultProbSourceSignalMatrix();
                                pCircuit.setProbSignalStates(false);
                                pCircuit.getProbGates().get(k).setGateReliability(BigDecimal.ZERO);
                                pCircuit.setPTMReliabilityMatrix();

                                hashMtbfExato0.put(pCircuit.getProbGates().get(k).getId(), getMTBF(SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit)).setScale(9, RoundingMode.HALF_UP));
                                pCircuit.getProbGates().get(k).setGateReliability(null);
                                
                                System.out.println(k + " / " + pCircuit.getProbGates().size());
                        }
                        
                        mtbfExato0 = sortByValue(hashMtbfExato0);
                        
                        classficaMTBFExato0 = new ArrayList<>(mtbfExato0.keySet());
                        
                        System.out.println("Classificação Exata confiabilidade 0");
                        for (int k = 0; k < classficaMTBFExato0.size(); k++) {
                            System.out.println(classficaMTBFExato0.get(k));
                        }
                        System.out.println("");
                        System.out.println("--------------");
                        System.out.println("");
                        
                        System.out.println("SPR-MP conf 0 - MTBF");
                        for (Map.Entry entry : mtbfExato0.entrySet()) {
                            System.out.println(entry.getValue());                            
                        }
                        
/***
 *         _______..______   .______              .___  ___. .______        ___                  _______  __  .___________.
 *        /       ||   _  \  |   _  \             |   \/   | |   _  \      / _ \                |   ____||  | |           |
 *       |   (----`|  |_)  | |  |_)  |     ______ |  \  /  | |  |_)  |    | | | |     ______    |  |__   |  | `---|  |----`
 *        \   \    |   ___/  |      /     |______||  |\/|  | |   ___/     | | | |    |______|   |   __|  |  |     |  |     
 *    .----)   |   |  |      |  |\  \----.        |  |  |  | |  |         | |_| |               |  |     |  |     |  |     
 *    |_______/    | _|      | _| `._____|        |__|  |__| | _|          \___/                |__|     |__|     |__|     
 *                                                                                                                         
 */
//                        for (int k = 0; k < pCircuit.getProbGates().size(); k++) {
//                                Terminal.getInstance().getCellLibrary().setPTMCells2(Float.valueOf(reliabilities[i]));
//                                Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal(reliabilities[i]));
//
//                                pCircuit.clearProbSignalsMatrix();                    
//                                pCircuit.setDefaultProbSourceSignalMatrix();
//                                pCircuit.setProbSignalStates(false);
//                                pCircuit.getProbGates().get(k).setGateReliability(BigDecimal.ZERO);
//                                pCircuit.setPTMReliabilityMatrix();
//
//                                hashFitExato0.put(pCircuit.getProbGates().get(k).getId(), getFIT(SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit)).setScale(9, RoundingMode.HALF_UP));
//                                pCircuit.getProbGates().get(k).setGateReliability(null);                                                        
//                        }
//                        
//                        fitExato0 = sortByValue(hashFitExato0);
//                        
//                        System.out.println("");
//                        System.out.println("--------------");
//                        System.out.println("");
//                        
//                        classficaFITExato0 = new ArrayList<>(fitExato0.keySet());
//                        
//                        Collections.reverse(classficaFITExato0);
//                        
//                        System.out.println("Classificação Exato 0 => MTBF e FIT são iguais??");
//                        if(classficaMTBFExato0.equals(classficaFITExato0)) {
//                            System.out.println("CLASSIFICAÇÃO Exato 0 => SPR-MP MTBF e FIT Iguais!!!");
//                        } else {
//                            System.out.println("CLASSIFICAÇÃO Exato 0 => SPR-MP MTBF e FIT DIFERENTES!!!");
//                            System.out.println(mtbfExato0);
//                            System.out.println(fitExato0);
//                            System.out.println("*************");
//                            System.out.println(classficaMTBFExato0);
//                            System.out.println(classficaFITExato0);
//                        }
//                        
//                        System.out.println("");
//                        System.out.println("--------------");
//                        System.out.println("");
//                        
//                        System.out.println("SPR-MP conf 0 - FIT");
//                        
//                        fooBigDecimal = new ArrayList<>();
//                        for (Map.Entry entry : fitExato0.entrySet()) {
//                            fooBigDecimal.add(entry.getValue());
//                        }
//                        
//                        Collections.reverse(fooBigDecimal);
//                        
//                        for (int k = 0; k < fooBigDecimal.size(); k++) {
//                            System.out.println(fooBigDecimal.get(k));
//                        }

                        
/***
            _______..______   .______            .___  ___. .______        _______      ___   .___________. _______     __    ___     ___     _     ___                .___  ___. .___________..______    _______ 
           /       ||   _  \  |   _  \           |   \/   | |   _  \      /  _____|    /   \  |           ||   ____|   /_ |  / _ \   / _ \   / \   /  /                |   \/   | |           ||   _  \  |   ____|
          |   (----`|  |_)  | |  |_)  |    ______|  \  /  | |  |_)  |    |  |  __     /  ^  \ `---|  |----`|  |__       | | | | | | | | | | ( o ) /  /       ______    |  \  /  | `---|  |----`|  |_)  | |  |__   
           \   \    |   ___/  |      /    |______|  |\/|  | |   ___/     |  | |_ |   /  /_\  \    |  |     |   __|      | | | | | | | | | |  \_/ /  / _     |______|   |  |\/|  |     |  |     |   _  <  |   __|  
       .----)   |   |  |      |  |\  \----.      |  |  |  | |  |         |  |__| |  /  _____  \   |  |     |  |____     | | | |_| | | |_| |     /  / / \               |  |  |  |     |  |     |  |_)  | |  |     
       |_______/    | _|      | _| `._____|      |__|  |__| | _|          \______| /__/     \__\  |__|     |_______|    |_|  \___/   \___/     /  / ( o )              |__|  |__|     |__|     |______/  |__|     
                                                                                                                                              /__/   \_/                                                          

 */



//                        for (int k = 0; k < pCircuit.getProbGates().size(); k++) {
//                                Terminal.getInstance().getCellLibrary().setPTMCells2(Float.valueOf(reliabilities[i]));
//                                Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal(reliabilities[i]));
//
//                                pCircuit.clearProbSignalsMatrix();                    
//                                pCircuit.setDefaultProbSourceSignalMatrix();
//                                pCircuit.setProbSignalStates(false);
//                                pCircuit.getProbGates().get(k).setGateReliability(BigDecimal.ONE);
//                                pCircuit.setPTMReliabilityMatrix();
//
//                                hashMtbfExato099999.put(pCircuit.getProbGates().get(k).getId(), getMTBF(SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit)).setScale(9, RoundingMode.HALF_UP));
//                                pCircuit.getProbGates().get(k).setGateReliability(null);                                                        
//                        }
//                        
//                        mtbfExato099999 = sortByValue(hashMtbfExato099999);
//                        
//                        classficaMTBFExato099999 = new ArrayList<>(mtbfExato099999.keySet());
//                        
//                        System.out.println("Classificação Exata confiabilidade gate 100%");
//                        for (int k = 0; k < classficaMTBFExato099999.size(); k++) {
//                            System.out.println(classficaMTBFExato099999.get(k));
//                        }
//                        System.out.println("");
//                        System.out.println("--------------");
//                        System.out.println("");
//                        
//                        System.out.println("SPR-MP conf gate 100% - MTBF");
//                        for (Map.Entry entry : mtbfExato099999.entrySet()) {
//                            System.out.println(entry.getValue());                            
//                        }
                        
/***
 *         _______..______   .______              .___  ___. .______        ___     ___     ___     ___     ___     ___                  _______  __  .___________.
 *        /       ||   _  \  |   _  \             |   \/   | |   _  \      / _ \   / _ \   / _ \   / _ \   / _ \   / _ \                |   ____||  | |           |
 *       |   (----`|  |_)  | |  |_)  |     ______ |  \  /  | |  |_)  |    | | | | | (_) | | (_) | | (_) | | (_) | | (_) |     ______    |  |__   |  | `---|  |----`
 *        \   \    |   ___/  |      /     |______||  |\/|  | |   ___/     | | | |  \__, |  \__, |  \__, |  \__, |  \__, |    |______|   |   __|  |  |     |  |     
 *    .----)   |   |  |      |  |\  \----.        |  |  |  | |  |         | |_| |    / /     / /     / /     / /     / /                |  |     |  |     |  |     
 *    |_______/    | _|      | _| `._____|        |__|  |__| | _|          \___/    /_/     /_/     /_/     /_/     /_/                 |__|     |__|     |__|     
 *                                                                                                                                                                 
 */
//                        for (int k = 0; k < pCircuit.getProbGates().size(); k++) {
//                                Terminal.getInstance().getCellLibrary().setPTMCells2(Float.valueOf(reliabilities[i]));
//                                Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal(reliabilities[i]));
//
//                                pCircuit.clearProbSignalsMatrix();                    
//                                pCircuit.setDefaultProbSourceSignalMatrix();
//                                pCircuit.setProbSignalStates(false);
//                                pCircuit.getProbGates().get(k).setGateReliability(new BigDecimal("0.99999"));
//                                pCircuit.setPTMReliabilityMatrix();
//
//                                hashFitExato099999.put(pCircuit.getProbGates().get(k).getId(), getFIT(SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit)).setScale(9, RoundingMode.HALF_UP));
//                                pCircuit.getProbGates().get(k).setGateReliability(null);                                                        
//                        }
//                        
//                        fitExato099999 = sortByValue(hashFitExato099999);
//                        
//                        System.out.println("");
//                        System.out.println("--------------");
//                        System.out.println("");
//                        
//                        classficaFITExato099999 = new ArrayList<>(fitExato099999.keySet());
//                        
//                        Collections.reverse(classficaFITExato099999);
//                        
//                        System.out.println("Classificação Exato 099999 => MTBF e FIT são iguais??");
//                        if(classficaMTBFExato099999.equals(classficaFITExato099999)) {
//                            System.out.println("CLASSIFICAÇÃO Exato 099999 => SPR-MP MTBF e FIT Iguais!!!");
//                        } else {
//                            System.out.println("CLASSIFICAÇÃO Exato 099999 => SPR-MP MTBF e FIT DIFERENTES!!!");
//                            System.out.println(mtbfExato099999);
//                            System.out.println(fitExato099999);
//                            System.out.println("*************");
//                            System.out.println(classficaMTBFExato099999);
//                            System.out.println(classficaFITExato099999);
//                        }
//                        
//                        System.out.println("");
//                        System.out.println("--------------");
//                        System.out.println("");
//                        
//                        System.out.println("SPR-MP conf 099999 - FIT");
//                        
//                        fooBigDecimal = new ArrayList<>();
//                        for (Map.Entry entry : fitExato099999.entrySet()) {
//                            fooBigDecimal.add(entry.getValue());
//                        }
//                        
//                        Collections.reverse(fooBigDecimal);
//                        
//                        for (int k = 0; k < fooBigDecimal.size(); k++) {
//                            System.out.println(fooBigDecimal.get(k));
//                        }


                        
                        /***
                         *         _______..______   .______      
                         *        /       ||   _  \  |   _  \     
                         *       |   (----`|  |_)  | |  |_)  |    
                         *        \   \    |   ___/  |      /     
                         *    .----)   |   |  |      |  |\  \----.
                         *    |_______/    | _|      | _| `._____|
                         *                                        
                         */

                        System.out.println("## SPR ##");
                        
/***
 *         _______..______   .______           ___                 .___  ___. .___________..______    _______ 
 *        /       ||   _  \  |   _  \         / _ \                |   \/   | |           ||   _  \  |   ____|
 *       |   (----`|  |_)  | |  |_)  |       | | | |     ______    |  \  /  | `---|  |----`|  |_)  | |  |__   
 *        \   \    |   ___/  |      /        | | | |    |______|   |  |\/|  |     |  |     |   _  <  |   __|  
 *    .----)   |   |  |      |  |\  \----.   | |_| |               |  |  |  |     |  |     |  |_)  | |  |     
 *    |_______/    | _|      | _| `._____|    \___/                |__|  |__|     |__|     |______/  |__|     
 *                                                                                                            
 */
                        for (int k = 0; k < pCircuit.getProbGates().size(); k++) {
                                Terminal.getInstance().getCellLibrary().setPTMCells2(Float.valueOf(reliabilities[i]));
                                Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal(reliabilities[i]));

                                pCircuit.clearProbSignalsMatrix();                    
                                pCircuit.setDefaultProbSourceSignalMatrix();
                                pCircuit.setProbSignalStates(false);
                                pCircuit.getProbGates().get(k).setGateReliability(BigDecimal.ZERO);
                                pCircuit.setPTMReliabilityMatrix();

                                hashMtbfSpr0.put(pCircuit.getProbGates().get(k).getId(), getMTBF(SPROps.getSPRReliability(pCircuit)).setScale(9, RoundingMode.HALF_UP));
                                pCircuit.getProbGates().get(k).setGateReliability(null);                                                        
                        }
                        
                        mtbfSpr0 = sortByValue(hashMtbfSpr0);
                        
                        classficaMTBFSPR0 = new ArrayList<>(mtbfSpr0.keySet());
                        
                        System.out.println("Classificação SPR confiabilidade 0");
                        for (int k = 0; k < classficaMTBFSPR0.size(); k++) {
                            System.out.println(classficaMTBFSPR0.get(k));
                        }
                        System.out.println("");
                        System.out.println("--------------");
                        System.out.println("");
                        
                        System.out.println("SPR conf 0 - MTBF");
                        for (Map.Entry entry : mtbfSpr0.entrySet()) {
                            System.out.println(entry.getValue());                            
                        }
                        
/***
 *         _______..______   .______           ___                  _______  __  .___________.
 *        /       ||   _  \  |   _  \         / _ \                |   ____||  | |           |
 *       |   (----`|  |_)  | |  |_)  |       | | | |     ______    |  |__   |  | `---|  |----`
 *        \   \    |   ___/  |      /        | | | |    |______|   |   __|  |  |     |  |     
 *    .----)   |   |  |      |  |\  \----.   | |_| |               |  |     |  |     |  |     
 *    |_______/    | _|      | _| `._____|    \___/                |__|     |__|     |__|     
 *                                                                                            
 */
//                        for (int k = 0; k < pCircuit.getProbGates().size(); k++) {
//                                Terminal.getInstance().getCellLibrary().setPTMCells2(Float.valueOf(reliabilities[i]));
//                                Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal(reliabilities[i]));
//
//                                pCircuit.clearProbSignalsMatrix();                    
//                                pCircuit.setDefaultProbSourceSignalMatrix();
//                                pCircuit.setProbSignalStates(false);
//                                pCircuit.getProbGates().get(k).setGateReliability(BigDecimal.ZERO);
//                                pCircuit.setPTMReliabilityMatrix();
//
//                                hashFitSpr0.put(pCircuit.getProbGates().get(k).getId(), getFIT(SPROps.getSPRReliability(pCircuit)).setScale(9, RoundingMode.HALF_UP));
//                                pCircuit.getProbGates().get(k).setGateReliability(null);                                                        
//                        }
//                        
//                        fitSpr0 = sortByValue(hashFitSpr0);
//                        
//                        System.out.println("");
//                        System.out.println("--------------");
//                        System.out.println("");
//                        
//                        classficaFITSPR0 = new ArrayList<>(fitSpr0.keySet());
//                        
//                        Collections.reverse(classficaFITSPR0);
//                        
//                        System.out.println("Classificação SPR 0 => MTBF e FIT são iguais??");
//                        if(classficaMTBFSPR0.equals(classficaFITSPR0)) {
//                            System.out.println("CLASSIFICAÇÃO SPR 0 => SPR MTBF e FIT Iguais!!!");
//                        } else {
//                            System.out.println("CLASSIFICAÇÃO SPR 0 => SPR MTBF e FIT DIFERENTES!!!");
//                            System.out.println(mtbfSpr0);
//                            System.out.println(fitSpr0);
//                            System.out.println("*************");
//                            System.out.println(classficaMTBFSPR0);
//                            System.out.println(classficaFITSPR0);
//                        }
//                        
//                        System.out.println("");
//                        System.out.println("--------------");
//                        System.out.println("");
//                        
//                        System.out.println("SPR conf 0 - FIT");
//                        
//                        fooBigDecimal = new ArrayList<>();
//                        for (Map.Entry entry : fitSpr0.entrySet()) {
//                            fooBigDecimal.add(entry.getValue());
//                        }
//                        
//                        Collections.reverse(fooBigDecimal);
//                        
//                        for (int k = 0; k < fooBigDecimal.size(); k++) {
//                            System.out.println(fooBigDecimal.get(k));
//                        }

                        
/***

        _______..______   .______           _______      ___   .___________. _______     __    ___     ___     _     ___                .___  ___. .___________..______    _______ 
       /       ||   _  \  |   _  \         /  _____|    /   \  |           ||   ____|   /_ |  / _ \   / _ \   / \   /  /                |   \/   | |           ||   _  \  |   ____|
      |   (----`|  |_)  | |  |_)  |       |  |  __     /  ^  \ `---|  |----`|  |__       | | | | | | | | | | ( o ) /  /       ______    |  \  /  | `---|  |----`|  |_)  | |  |__   
       \   \    |   ___/  |      /        |  | |_ |   /  /_\  \    |  |     |   __|      | | | | | | | | | |  \_/ /  / _     |______|   |  |\/|  |     |  |     |   _  <  |   __|  
   .----)   |   |  |      |  |\  \----.   |  |__| |  /  _____  \   |  |     |  |____     | | | |_| | | |_| |     /  / / \               |  |  |  |     |  |     |  |_)  | |  |     
   |_______/    | _|      | _| `._____|    \______| /__/     \__\  |__|     |_______|    |_|  \___/   \___/     /  / ( o )              |__|  |__|     |__|     |______/  |__|     
                                                                                                               /__/   \_/                                                          
*/

                        for (int k = 0; k < pCircuit.getProbGates().size(); k++) {
                                Terminal.getInstance().getCellLibrary().setPTMCells2(Float.valueOf(reliabilities[i]));
                                Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal(reliabilities[i]));

                                pCircuit.clearProbSignalsMatrix();                    
                                pCircuit.setDefaultProbSourceSignalMatrix();
                                pCircuit.setProbSignalStates(false);
                                pCircuit.getProbGates().get(k).setGateReliability(BigDecimal.ONE);
                                pCircuit.setPTMReliabilityMatrix();

                                hashMtbfSpr1.put(pCircuit.getProbGates().get(k).getId(), getMTBF(SPROps.getSPRReliability(pCircuit)).setScale(9, RoundingMode.HALF_UP));
                                pCircuit.getProbGates().get(k).setGateReliability(null);                                                        
                        }
                        
                        mtbfSpr1 = sortByValue(hashMtbfSpr1);
                        
                        
                        classficaMTBFSPR1 = new ArrayList<>(mtbfSpr1.keySet());
                        ArrayList<Object> bigFoo = new ArrayList<>();
                        
                        Collections.reverse(classficaMTBFSPR1);
                        
                        System.out.println("Classificação SPR confiabilidade 1");
                        for (int k = 0; k < classficaMTBFSPR1.size(); k++) {
                            System.out.println(classficaMTBFSPR1.get(k));
                        }
                        System.out.println("");
                        System.out.println("--------------");
                        System.out.println("");
                        
                        System.out.println("SPR conf 1 - MTBF");
                        for (Map.Entry entry : mtbfSpr1.entrySet()) {
                            bigFoo.add(entry.getValue());                            
                        }
                        
                        Collections.reverse(bigFoo);
                        
                        for(Object obj : bigFoo) {
                            System.out.println(obj);
                        }
                        
/***
 *         _______..______   .______           ___     ___     ___     ___     ___     ___                  _______  __  .___________.
 *        /       ||   _  \  |   _  \         / _ \   / _ \   / _ \   / _ \   / _ \   / _ \                |   ____||  | |           |
 *       |   (----`|  |_)  | |  |_)  |       | | | | | (_) | | (_) | | (_) | | (_) | | (_) |     ______    |  |__   |  | `---|  |----`
 *        \   \    |   ___/  |      /        | | | |  \__, |  \__, |  \__, |  \__, |  \__, |    |______|   |   __|  |  |     |  |     
 *    .----)   |   |  |      |  |\  \----.   | |_| |    / /     / /     / /     / /     / /                |  |     |  |     |  |     
 *    |_______/    | _|      | _| `._____|    \___/    /_/     /_/     /_/     /_/     /_/                 |__|     |__|     |__|     
 *                                                                                                                                    
 */
//                        for (int k = 0; k < pCircuit.getProbGates().size(); k++) {
//                                Terminal.getInstance().getCellLibrary().setPTMCells2(Float.valueOf(reliabilities[i]));
//                                Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal(reliabilities[i]));
//
//                                pCircuit.clearProbSignalsMatrix();                    
//                                pCircuit.setDefaultProbSourceSignalMatrix();
//                                pCircuit.setProbSignalStates(false);
//                                pCircuit.getProbGates().get(k).setGateReliability(new BigDecimal("0.99999"));
//                                pCircuit.setPTMReliabilityMatrix();
//                                
//                                hashFitSpr099999.put(pCircuit.getProbGates().get(k).getId(), getFIT(SPROps.getSPRReliability(pCircuit)).setScale(9, RoundingMode.HALF_UP));
//                                pCircuit.getProbGates().get(k).setGateReliability(null);                                                        
//                        }
//                        
//                        fitSpr099999 = sortByValue(hashFitSpr099999);
//                        
//                        System.out.println("");
//                        System.out.println("--------------");
//                        System.out.println("");
//                        
//                        classficaFITSPR099999 = new ArrayList<>(fitSpr099999.keySet());
//                        
//                        Collections.reverse(classficaFITSPR099999);
//                        
//                        System.out.println("Classificação SPR 099999 => MTBF e FIT são iguais??");
//                        if(classficaMTBFSPR099999.equals(classficaFITSPR099999)) {
//                            System.out.println("CLASSIFICAÇÃO SPR 099999 => SPR MTBF e FIT Iguais!!!");
//                        } else {
//                            System.out.println("CLASSIFICAÇÃO SPR 099999 => SPR MTBF e FIT DIFERENTES!!!");
//                            System.out.println(mtbfSpr099999);
//                            System.out.println(fitSpr099999);
//                            System.out.println("*************");
//                            System.out.println(classficaMTBFSPR099999);
//                            System.out.println(classficaFITSPR099999);
//                        }
//                        
//                        System.out.println("");
//                        System.out.println("--------------");
//                        System.out.println("");
//                        
//                        System.out.println("SPR conf 099999 - FIT");
//                        
//                        fooBigDecimal = new ArrayList<>();
//                        for (Map.Entry entry : fitSpr099999.entrySet()) {
//                            fooBigDecimal.add(entry.getValue());
//                        }
//                        
//                        Collections.reverse(fooBigDecimal);
//                        
//                        for (int k = 0; k < fooBigDecimal.size(); k++) {
//                            System.out.println(fooBigDecimal.get(k));
//                        }
                        
//                        // Classificação Exata em 0.99999
//                        for (int k = 0; k < pCircuit.getProbGates().size(); k++) {
//                                Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
//                                Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));
//
//                                pCircuit.clearProbSignalsMatrix();                    
//                                pCircuit.setPatternSourceProbSignal();
//                                pCircuit.setProbSignalStates(false);
//                                pCircuit.getProbGates().get(k).setGateReliability(new BigDecimal("0.99999"));
//                                pCircuit.setPTMReliabilityMatrix();
//
//                                hashExato099999.put(pCircuit.getProbGates().get(k).getId(), getMTBF(SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit)).setScale(9, RoundingMode.HALF_UP));
//                                pCircuit.getProbGates().get(k).setGateReliability(null);                                                        
//                        }
//                        
//                        exato099999 = sortByValue(hashExato099999);
//                        System.out.println(exato099999);
                        


//                            for (int k = 0; k < pCircuit.getProbGates().size(); k++) {
//                                Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
//                                Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));
//
//                                pCircuit.clearProbSignalsMatrix();                    
//                                pCircuit.setPatternSourceProbSignal();
//                                pCircuit.setProbSignalStates(false);
//                                pCircuit.getProbGates().get(k).setGateReliability(new BigDecimal(gateReliabilities[l]));
//                                pCircuit.setPTMReliabilityMatrix();
//
//                                sprMap.put(pCircuit.getProbGates().get(k).getId(), getFIT(SPROps.getSPRReliability(pCircuit)).setScale(9, RoundingMode.HALF_UP));
//                                //sprMap.put(pCircuit.getProbGates().get(k).getId(), getFIT(SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit)).setScale(9, RoundingMode.HALF_UP));
//                                pCircuit.getProbGates().get(k).setGateReliability(null);                                                        
//                            }
//
//                            newMap2 = sortByValue(sprMap);                     
//                            ArrayList<String> fooSet = new ArrayList<>(newMap2.keySet());
//
//                                                      
//
//                            if(goldArray.equals(fooSet)) {
//                                System.out.println(gateReliabilities[l] + " gerou igual!!!");
//                            } else {
//                                System.out.println(gateReliabilities[l] + " GEROU DIFERENTE!!!!!!!!!!!!!!!!!!!!");
//                                System.out.println(goldArray);
//                                System.out.println(fooSet);
//                                System.out.println(newMap2);
//                            }
//                            sprMap.clear();
                                                                                                                                         
                    
                                    

                    
//                    for (int k = 0; k < pCircuit.getProbGates().size(); k++) {
//                        pCircuit.getProbGates().get(k).setGateReliability(new BigDecimal("0.7"));
//                        pCircuit.setPTMReliabilityMatrix();
//
//                        
//                        //sprmpMap.put(pCircuit.getProbGates().get(k).getId(), getFIT(SPRMultiPassV2BigDecimalOps.getSPRMultiPassReliaiblity(pCircuit)));
//                        //sprmpMap.put(pCircuit.getProbGates().get(k).getId(), SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit));
//                        sprmpMap.put(pCircuit.getProbGates().get(k).getId(), SPROps.getSPRReliability(pCircuit));
//                        pCircuit.getProbGates().get(k).setGateReliability(null);
//                    }
                    
//                    Map<String, BigDecimal> treeMap2 = new TreeMap<String, BigDecimal>(sprmpMap);                    
//                    System.out.println(treeMap2);
                    
                        sprmpMap = null;
                    }
                    
                    if(analiseFanouts) {
                        
                        Terminal.getInstance().getCellLibrary().setPTMCells2(Float.valueOf(reliabilities[i]));
                        Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal(reliabilities[i]));

                        pCircuit.clearProbSignalsMatrix();                    
                        pCircuit.setDefaultProbSourceSignalMatrix();
                        pCircuit.setProbSignalStates(false);
                        pCircuit.setPTMReliabilityMatrix();
                        
                        ArrayList<ProbSignal> fanouts = FanoutOps.getPartialFanoutsInOut(pCircuit.getFanouts(), 50, false);
                        
                        //ArrayList<ProbSignal> fanouts = FanoutOps.getJustINorNotIN(pCircuit.getFanouts(), false);                        

                        System.out.println(fanouts.size());
                        
//                        long timeElapsed = System.currentTimeMillis();
//                        
//                        if(fanouts.isEmpty()) {
//                            System.out.println("TAVAZIO");
//                        } else {
//                            System.out.println(getMTBF(SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit, fanouts)));
//                        }
//                        
//                        
//                        
//                        timeArray.add((System.currentTimeMillis() - timeElapsed));
                        
                        
//                        for (int k = 0; k < 10; k++) {
//                            long start = System.currentTimeMillis();
//                            getMTBF(SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit, fanouts));
//                            timeElapsed = timeElapsed + (System.currentTimeMillis() - start);
//                        }
                        
                    }
                    
                    
                    
                    if(analiseTemposSPR) {
                        
                        Terminal.getInstance().getCellLibrary().setPTMCells2(Float.valueOf(reliabilities[i]));
                        Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal(reliabilities[i]));

                        pCircuit.clearProbSignalsMatrix();                    
                        pCircuit.setDefaultProbSourceSignalMatrix();
                        pCircuit.setProbSignalStates(false);
                        pCircuit.setPTMReliabilityMatrix();
                        
                        BigDecimal tempo = BigDecimal.ZERO;
                        BigDecimal aux;
                        BigDecimal result;
                                                
                        for (int k = 0; k < 15; k++) {
                            
                            long tempoNano = System.nanoTime();
                            result = SPROps.getSPRReliability(pCircuit);
                            tempoNano = System.nanoTime() - tempoNano;
                            
                            aux = new BigDecimal(tempoNano).divide(new BigDecimal("1000000"));
                            
                            tempo = tempo.add(aux);
                        }
                        
                        System.out.println(tempo.divide(new BigDecimal("15"), RoundingMode.HALF_DOWN));
                        
                        
                    }
                    
                    if(analisePortasSPR) {
                        
                        String[] PORTAS = null;

                        
                        System.out.println(pCircuit.getName());                        
                        
                        switch(pCircuit.getName()) {
                            case "s386_n49":
                                PORTAS = Portas.s386;
                                break;
                                
                            case "s9234_n676":
                                PORTAS = Portas.s9234;
                                break;
                            case "s838_n215":
                                PORTAS = Portas.s838;
                                break;
                            case "s382_n69":
                                PORTAS = Portas.s382;
                                break;
                            case "s400_n64":
                                PORTAS = Portas.s400;
                                break;
                            case "s27_comb_Marcelinho":
                                PORTAS = Portas.s27;
                                break;
                            case "s298_n64":
                                PORTAS = Portas.s298;
                                break;
                            case "s444_n109":
                                PORTAS = Portas.s444;
                                break;
                            case "s344_n61":
                                PORTAS = Portas.s344;
                                break;
                            case "s349_n66":
                                PORTAS = Portas.s349;
                                break;
                            case "s832_n90":
                                PORTAS = Portas.s832;
                                break;
                            case "s13207_n594":
                                PORTAS = Portas.s13207;
                                break;
                            case "s38584_n7656":
                                PORTAS = Portas.s38584;
                                break;
                            case "s510_n78":
                                PORTAS = Portas.s510;
                                break;
                            case "s1488_n75":
                                PORTAS = Portas.s1488;
                                break;
                            case "s820_n95":
                                PORTAS = Portas.s820;
                                break;
                        }
                        
                        System.out.println("##################");
                        System.out.println(pCircuit.getName() + " ==> " + Arrays.toString(PORTAS));
                        System.out.println("##################");
                        
                        String[] TMRs = {
                            "0.9",
                            "0.99",
                            "0.999",
                            "0.9999",
                            "0.99999",
                            "0.999999",                            
                            "1",
                        };
                        
                        for (String melhoria : TMRs) {
                            
                            System.out.println("#######");
                            System.out.println(melhoria);
                            System.out.println("#######");
                            
                            for (String gate : PORTAS) {
                                
                                ProbGate pGate = pCircuit.getProbGateByName(gate);
                            
                                if(pGate == null) {
                                    System.err.println("PORTA " + gate + " NÃO EXISTE NO CIRCUITO " +pCircuit.getName());
                                } else {
                                    
                                    /**
                                     * SPR-MP
                                     */
                                    Terminal.getInstance().getCellLibrary().setPTMCells2(Float.valueOf(reliabilities[i]));
                                    Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal(reliabilities[i]));

                                    pCircuit.clearProbSignalsMatrix();                    
                                    pCircuit.setDefaultProbSourceSignalMatrix();
                                    pCircuit.setProbSignalStates(false);                                
                                    pGate.setGateReliability(new BigDecimal(melhoria));                                                                
                                    pCircuit.setPTMReliabilityMatrix();
                                    
                                    System.out.println(getMTBF(SPRMultiPassV3Ops.getSPRMultiPassReliaiblity(pCircuit)));
                                    
                                    pGate.setGateReliability(null);
                                    
                                    /**
                                     * SPR
                                     */
                                    Terminal.getInstance().getCellLibrary().setPTMCells2(Float.valueOf(reliabilities[i]));
                                    Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal(reliabilities[i]));

                                    pCircuit.clearProbSignalsMatrix();                    
                                    pCircuit.setDefaultProbSourceSignalMatrix();
                                    pCircuit.setProbSignalStates(false);                                
                                    pGate.setGateReliability(new BigDecimal(melhoria));                                                                
                                    pCircuit.setPTMReliabilityMatrix();
                                    
                                    System.out.println(getMTBF(SPROps.getSPRReliability(pCircuit)));                                   
                                    pGate.setGateReliability(null);
                                    
                                }
                            }
                        }                        
                    }
                    
                    if(tiposPortas) {
                        
                        ArrayList<ProbGate> probGates = new ArrayList<>();
                        
                        for(ProbGate pGate : pCircuit.getProbGates()) {
                            probGates.add(pGate);
                        }
                        
                        
                        Collections.sort(probGates, new Comparator<ProbGate>() {
                            @Override
                            public int compare(ProbGate pGate1, ProbGate pGate2) {
                                return pGate1.getId().compareTo(pGate2.getId());
                            }
                        });
                        
                        System.out.println("######################");
                        System.out.println(pCircuit.getName());
                        for(ProbGate fooGate : probGates) {
                            System.out.println(fooGate);
                        }
                        System.out.println("######################");
                        System.out.println("");
                        System.out.println("");
                        
                        System.out.println("######################");
                        System.out.println(pCircuit.getName());
                        for(ProbGate fooGate : probGates) {
                            System.out.println(fooGate.getType().getName());
                        }
                        System.out.println("######################");
                        System.out.println("");
                        System.out.println("");
                        
                        System.out.println("######################");
                        System.out.println(pCircuit.getName());
                        for(ProbGate fooGate : probGates) {
                            System.out.println(fooGate.getDepth());
                        }
                        System.out.println("######################");
                        System.out.println("");
                        System.out.println("");
                    }
                
                    
//                
            }
              
                


                
//                for (int i = 0; i < reliabilities.length; i++) {
//                    
//                    System.out.println("RELIABILITY = " + reliabilities[i] + " ==> " + (i+1));
//                    
//                    Terminal.getInstance().getCellLibrary().setPTMCell2(Float.valueOf(reliabilities[i]));
//                    Terminal.getInstance().getCellLibrary().setPTMCell(new BigDecimal(reliabilities[i]));
//                    startMethod = System.currentTimeMillis();
//                    BigInteger[] ops = OperartionCounters.getTotalOperationsPTMwithMatrixRepresentation(pCircuit);
//                    System.out.println("circuit " + pCircuit.getName() + " total operations in Normal PTM is: sums -> " + ops[0] + "  multiplications -> " + ops[1]);
//                    System.out.println("TIME: " + (System.currentTimeMillis()-startMethod));
                    
//                    startMethod = System.currentTimeMillis();
//                    BigInteger[] ops = OperartionCounters.getTotalOperationsSerialPTM(pCircuit);
//                    System.out.println("circuit " + pCircuit.getName() + " total operations in SERIAL PTM is: sums -> " + ops[0] + "  multiplications -> " + ops[1]);
//                    System.out.println("TIME: " + (System.currentTimeMillis()-startMethod));
                    
                    
//                    startMethod = System.currentTimeMillis();
//                    callReliabilityMethod("PTM_FLOAT", pCircuit, reliabilities[i], "ms", 1);
//                    System.out.println("TIME: " + (System.currentTimeMillis()-startMethod));

//                   callReliabilityMethod("PTM_BIGDECIMAL", pCircuit, reliabilities[i], "ms", 1); 
//                   callReliabilityMethod("PTM_FLOAT", pCircuit, reliabilities[i], "ms", 1);
//                   callReliabilityMethod("SPR_BIGDECIMAL", pCircuit, reliabilities[i], "ms", 1);
//                   callReliabilityMethod("SPR_FLOAT", pCircuit, reliabilities[i], "ms", 1);                   
//                   callReliabilityMethod("SPR-MP_BIGDECIMAL", pCircuit, reliabilities[i], "ms", 1);
//                   callReliabilityMethod("SPR-MP_FLOAT", pCircuit, reliabilities[i], "ms", 1);
                    


                     
                     
                     
        
                     
                     
                     
                   
                   
                   
                    
//                    for (int k = 0; k < pCircuit.getProbOutputs().size(); k++) {
//                        BigDecimal[][] fooBig = pCircuit.getProbOutputs().get(k).getProbMatrix();
//                        float[][] fooFloat = pCircuit2.getProbOutputs().get(k).getProbMatrixFloat();
//                        
//                        BigDecimal diff0c = fooBig[0][0].subtract(new BigDecimal(fooFloat[0][0])).divide(new BigDecimal(fooFloat[0][0]), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal(100));
//                        BigDecimal diff1i = fooBig[0][1].subtract(new BigDecimal(fooFloat[0][1])).divide(new BigDecimal(fooFloat[0][1]), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal(100));
//                        BigDecimal diff0i = fooBig[1][0].subtract(new BigDecimal(fooFloat[1][0])).divide(new BigDecimal(fooFloat[1][0]), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal(100));
//                        BigDecimal diff1c = fooBig[1][1].subtract(new BigDecimal(fooFloat[1][1])).divide(new BigDecimal(fooFloat[1][1]), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal(100));
//                        
//                        
//                        System.out.println("#------#");
//                        System.out.println("BIGDECIMAL --> " + pCircuit.getOutputs().get(k) + " --> [" + fooBig[0][0] + " ," + fooBig[0][1] + " ," + fooBig[1][0] + " ," + fooBig[1][1] + "]");
//                        System.out.println("FLOAT --> " + pCircuit2.getOutputs().get(k) + " --> [" + fooFloat[0][0] + " ," + fooFloat[0][1] + " ," + fooFloat[1][0] + " ," + fooFloat[1][1] + "]");
//                        System.out.println("Differences: 0c = " + diff0c + " 0i = " + diff0i + " 1c = " + diff1c + " 1i = " + diff1i);
//                        System.out.println("#------#");
//                    }

                    /*
                    
                    for (int l = 0; l < pCircuit.getProbGateLevels().size(); l++) {
                        System.out.println("$$$$$$$$$$$$$$$");
                        System.out.println("$$$$$$$$$$$$$$$");
                        System.out.println("$$$$$$$$$$$$$$$");
                        
                        System.out.println("GATE LEVEL " + l);
                        
                        for (int k = 0; k < pCircuit.getProbGateLevels().get(l).getProbGates().size(); k++) {
                            BigDecimal[][] fooBig = pCircuit.getProbGateLevels().get(l).getProbGates().get(k).getpOutputs().get(0).getProbMatrix();
                            float[][] fooFloat = pCircuit2.getProbGateLevels().get(l).getProbGates().get(k).getpOutputs().get(0).getProbMatrixFloat();

                            BigDecimal diff0c = fooBig[0][0].subtract(new BigDecimal(fooFloat[0][0])).divide(new BigDecimal(fooFloat[0][0]), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal(100));
                            BigDecimal diff1i = fooBig[0][1].subtract(new BigDecimal(fooFloat[0][1])).divide(new BigDecimal(fooFloat[0][1]), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal(100));
                            BigDecimal diff0i = fooBig[1][0].subtract(new BigDecimal(fooFloat[1][0])).divide(new BigDecimal(fooFloat[1][0]), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal(100));
                            BigDecimal diff1c = fooBig[1][1].subtract(new BigDecimal(fooFloat[1][1])).divide(new BigDecimal(fooFloat[1][1]), 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal(100));


                            System.out.println("#------#");
                            System.out.println("BIGDECIMAL --> " + pCircuit.getProbGateLevels().get(l).getProbGates().get(k) + " --> [" + fooBig[0][0] + " ," + fooBig[0][1] + " ," + fooBig[1][0] + " ," + fooBig[1][1] + "]");
                            System.out.println("FLOAT --> " + pCircuit2.getProbGateLevels().get(l).getProbGates().get(k) + " --> [" + fooFloat[0][0] + " ," + fooFloat[0][1] + " ," + fooFloat[1][0] + " ," + fooFloat[1][1] + "]");
                            System.out.println("Differences: 0c = " + diff0c + " 0i = " + diff0i + " 1c = " + diff1c + " 1i = " + diff1i);
                            System.out.println("Gatelevel " + l);
                            System.out.println("#------#");
                        }

                        System.out.println("$$$$$$$$$$$$$$$");
                        System.out.println("$$$$$$$$$$$$$$$");
                        System.out.println("$$$$$$$$$$$$$$$");
                    }
                    
                    */

                    
                    
//                    startMethod = System.currentTimeMillis();
//                    callReliabilityMethod("PTM_SERIAL_FLOAT_OPT", pCircuit, reliabilities[i], "ms", 5);
//                    System.out.println("TIME: " + (System.currentTimeMillis()-startMethod));
                    
//                    startMethod = System.currentTimeMillis();
//                    callReliabilityMethod("PTM_SERIAL_FLOAT_OPT", pCircuit, reliabilities[i], "ms", 1);
//                    System.out.println("TIME: " + (System.currentTimeMillis()-startMethod));
                    
                    
                    //System.out.println(PTMOps2.getCircuitReliabilityByPTM(pCircuit) + "<== PTM WITH BIGDECIMAL (" + reliabilities[i] + "): " + " ## TIME CONSUPTION ## ==> " + Long.toString((System.nanoTime() - startMethod) / 1000) + " microseconds");
                    //System.out.println(PTMOps2Float.getCircuitReliabilityByPTM(pCircuit));
                    
                
                    //System.out.println(SPROpsFloat.getSPRReliability(pCircuit) + "<== SPR WITH FLOAT (" + reliabilities[i] + "):" + " ## TIME CONSUPTION ## ==> " + Long.toString((System.nanoTime() - startMethod) / 1000) + " microseconds");
                    //System.out.println(SPROpsFloat.getSPRReliability(pCircuit));

                    
                    
                    /*
                    startMethod = System.nanoTime();
                    System.out.println(SPROps.getSPRReliability(pCircuit) + "<== SPR WITH BIGDECIMAL (" + reliabilities[i] + "):" + " ## TIME CONSUPTION ## ==> " + Long.toString((System.nanoTime() - startMethod) / 1000) + " microseconds");
                    //System.out.println(SPROpsFloat.getSPRReliability(pCircuit));
                    */
                    
                    
//                   
//                    
//                    
//                                        
//                
//                    
//                pCircuit.clearProbSignalsMatrix();
//                pCircuit.setPatternSourceProbSignal();
//                pCircuit.setProbSignalStates(false);
//                
//                startMethod = System.nanoTime();                
//                System.out.println(ops.SPRMultiPassV2BigDecimalOps.getSPRMultiPassReliaiblity(pCircuit, circuitsFanouts) + " <== MultiPass 100% BigDecimal(" + reliabilities[i] + "): ## TIME CONSUPTION ## ==> " + Long.toString((System.nanoTime() - startMethod) / 1000) + " microseconds");                                                        
//                System.out.println("TOTAL PASSES mpv2Big: " + ops.SPRMultiPassV2BigDecimalOps.COUNTER);
//                
//                pCircuit.clearProbSignalsMatrix();
//                pCircuit.setPatternSourceProbSignal();
//                pCircuit.setProbSignalStates(false);
//                
//                startMethod = System.nanoTime();                
//                System.out.println(ops.SPRMultiPassV2Ops.getSPRMultiPassReliaiblity(pCircuit, circuitsFanouts) + " <== MultiPass 100% BigDecimal(" + reliabilities[i] + "): ## TIME CONSUPTION ## ==> " + Long.toString((System.nanoTime() - startMethod) / 1000) + " microseconds");                                                        
//                System.out.println("TOTAL PASSES mpv2Normal: " + ops.SPRMultiPassV2Ops.COUNTER);
//                
//                pCircuit.clearProbSignalsMatrix();
//                pCircuit.setPatternSourceProbSignal();
//                pCircuit.setProbSignalStates(false);
//                
                
            
                }

//                System.out.println("############");
                                        
                //System.out.println("");
                                               
                
                //System.out.println(OperartionCounters.getPTMGateLevelMultiplicationsWithMatrix(pGateLevel));
                
                /*
                System.out.println("### PTM ####");
                BigInteger[] ptm = OperartionCounters.getTotalOperationsPTMwithMatrixRepresentation(pCircuit);
                System.out.println("SUMS             = " + ptm[0]);
                System.out.println("MULTIPLICATIONS  = " + ptm[1]);
                
                System.out.println("### SPR ####");
                BigInteger[] spr = OperartionCounters.getTotalOperationsSPR(pCircuit);
                System.out.println("SUMS             = " + spr[0]);
                System.out.println("MULTIPLICATIONS  = " + spr[1]);
                 
                */
                
//                pCircuit.clearProbSignalsMatrix();
//                pCircuit.setPatternSourceProbSignal();
//                pCircuit.setProbSignalStates(false);
                
                
                
//                System.out.println("### SPR-MP Unreliable IN Fanouts##");
//                BigInteger[] sprmp = OperartionCounters.getTotalOperationsSPRMP(pCircuit, false);
//                System.out.println("SUMS             = " + sprmp[0]);
//                System.out.println("MULTIPLICATIONS  = " + sprmp[1]);
//                System.out.println();
                
//                System.out.println("### SPR-MP Reliable IN Fanouts##");
//                BigInteger[] sprmp2 = OperartionCounters.getTotalOperationsSPRMP(pCircuit, true);
//                System.out.println("SUMS             = " + sprmp2[0]);
//                System.out.println("MULTIPLICATIONS  = " + sprmp2[1]);
//                System.out.println();                                
                
                
                /*
                System.out.println("### SPR-MP Considered Fanouts States ##");
                BigInteger[] sprmp3 = OperartionCounters.getTotalOperationsSPRMP(pCircuit, new BigInteger(Integer.toString(ops.SPRMultiPassV2BigDecimalOps.COUNTER)));
                System.out.println("TOTAL PASSES: " + ops.SPRMultiPassV2BigDecimalOps.COUNTER);
                System.out.println("SUMS             = " + sprmp3[0]);
                System.out.println("MULTIPLICATIONS  = " + sprmp3[1]);
                System.out.println();
                
                
                ops.SPRMultiPassV2Ops.COUNTER = 0;
                ops.SPRMultiPassV2Ops.VALUECOUNTER = 0;
                
                ops.SPRMultiPassV2BigDecimalOps.COUNTER = 0;
                ops.SPRMultiPassV2BigDecimalOps.VALUECOUNTER = BigDecimal.ZERO;
                
                */
//            }
                        
            
            
        }
        catch (ScriptException ex) {
            Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
        }                                        
                
                
        final long endTime = System.currentTimeMillis();
        String timeConsup = "## TIME CONSUPTION ## ==> " + Long.toString((endTime - startTime)) + " ms";
        
        //timer.cancel();
        
        Terminal.getInstance().terminalOutput(timeConsup);
        
        System.out.println("TEMPOS");
        for(long time: timeArray) {
            System.out.println(time);
        }
    }
    
    public void Foo7() {
                
        /*
        
        \begin{table}
            \begin{center}
                \caption{Memória necessária para o ISCAS85}\label{memoriaISCAS}
                \bigskip
                \begin{tabular}{p{2.5cm}p{2.5cm}p{2.5cm}p{3.5cm}p{2.5cm}p{2.5cm}p{2.5cm}}
                    \hline

                    \textbf{Circuito} & \textbf{Portas} & \textbf{Níveis} & \textbf{Maior Matriz} & \textbf{Memória(GB)} 
                    & \textbf{Memória(GB)} & \textbf{Memória(GB)}
                    \Tstrut\Bstrut\\
                    \hline
                    \Tstrut\Tstrut\Tstrut\Tstrut
                    {\small C7552} &  1151 & 15 & $2^{734} x 2^{531}$ & $2.3\mathrm{e}{+372}$ & $2.3\mathrm{e}{+372}$\Bstrut\\
                    \hline
                \end{tabular}
            \end{center}
        \end{table}
        
        */
        
        String[] circuits = new String[]{
            "c17v3_fritz.v",
        };
        
        String[] reliabilities = new String[]{
            "0.99",            
            //"0.95",
            //"0.9",
            //"0.85",
            //"0.8",            
            //"0.75",
            //"0.7",
            //"0.65",
            
            
        };
        
        String tableTitle = "Tabela de Resultados Teste";
        String lable = "TabelaTeste";
        
        WriteFile wFile = new WriteFile("resultsNandsC17.txt");
        
        
        
        
        
        
        try {
            for (int j = 0; j < circuits.length; j++) {
                Terminal.getInstance().executeCommand("read_verilog "+circuits[j]);
                LevelCircuit lCircuit = Terminal.getInstance().getLevelCircuit();
                ProbCircuit pCircuit = ProbCircuit.create(lCircuit.getName(), lCircuit.getSignals(), lCircuit.getGates(), lCircuit.getGateLevels());
                
                System.out.println(pCircuit.getName());
                
                for (int i = 0; i < reliabilities.length; i++) {
                    Terminal.getInstance().getCellLibrary().setPTMCells2(Float.valueOf(reliabilities[i]));
                    
                    pCircuit.clearProbSignalsMatrix();
                    pCircuit.setPTMReliabilityMatrix();
                    pCircuit.setDefaultProbSourceSignalMatrix();
                    pCircuit.setProbSignalStates(false);
                    
                    ArrayList<ProbSignal> fooProbSignals = new ArrayList<>();
                    
                    for (int k = 0; k < pCircuit.getProbSignals().size(); k++) {
                        ProbSignal fooSignal = pCircuit.getProbSignals().get(k);
                        if(fooSignal.getPOrigin() == null) {
                            fooProbSignals.add(fooSignal);
                        }
                    }
                    
                    for (int k = 0; k < pCircuit.getProbSignals().size(); k++) {
                        ProbSignal fooSignal = pCircuit.getProbSignals().get(k);
                        if(fooSignal.getPOrigin() != null && !fooSignal.getDestiny().isEmpty()) {
                            fooProbSignals.add(fooSignal);
                        }
                    }
                    
                    
                    for (int k = 0; k < pCircuit.getProbSignals().size(); k++) {                   
                        ProbSignal fooSignal = pCircuit.getProbSignals().get(k);
                        if(fooSignal.getDestiny().isEmpty()) {
                            fooProbSignals.add(fooSignal);
                        }
                    }
                    
                    pCircuit.setProbSignals(fooProbSignals);
                    
                    System.out.println(pCircuit.getProbSignals());
                    
                    Float result = SPROpsFloat.getSPRReliability(pCircuit);
                    
                    System.out.println(result + "<== SPR WITH FLOAT" + " ## TIME CONSUPTION ## ==> ");
                    
                    WriteFile.WriteContent("\\begin{table}\n" + "   \\begin{center}\n" + "\\caption{"+ tableTitle + "}\n" + 
                "\\label{ " + lable + "}\n");
                    
                    WriteFile.WriteContent("\\begin{tabular}{");
                    
                    WriteFile.WriteContent("|p{1cm}");
                    
                    for (int k = 0; k < pCircuit.getProbSignals().size(); k++) {
                        if(k>4) {
                            WriteFile.WriteContent("|p{2.7cm}");
                        } else {
                            WriteFile.WriteContent("|p{1cm}");
                        }
                    }
                    
                    WriteFile.WriteContent("|}\n" + "\\hline\n");
                    
                    WriteFile.WriteContent(" \\centering \\textbf{PASS} & ");
                    
                    for (int k = 0; k < pCircuit.getProbSignals().size(); k++) {
                        ProbSignal fooSignal = pCircuit.getProbSignals().get(k);
                        
                        WriteFile.WriteContent("\\centering  \\textbf{" + fooSignal.getId() + "} ");
                        
                        if(k < pCircuit.getProbSignals().size() - 1) {
                            WriteFile.WriteContent(" & ");
                        }
                        
                        //System.out.println(fooSignal.getId() + ": ");
                        //matrixPrint(fooSignal.getProbMatrixFloat());
                        //System.out.println("#####");
                        
                    }
                    
                    WriteFile.WriteContent("\\tabularnewline\n\\hline\n");
                    
                    WriteFile.WriteContent("\\centering\\\n" +
"\\newline \n**** &");
                    
                    for (int k = 0; k < pCircuit.getProbSignals().size(); k++) {
                        ProbSignal fooSignal = pCircuit.getProbSignals().get(k);
                        
                        
                        WriteFile.WriteContent("\\tiny{\n" +
                                                "\\[\n" +
                                                "\\setlength\\arraycolsep{2pt}\n" +
                                                "\\begin{bmatrix}\n" +
                                                "    \\scalebox{.9}{" + fooSignal.getProbMatrixFloat()[0][0] + "} &  \\scalebox{.9}{ " + fooSignal.getProbMatrixFloat()[0][1] + "}\\\\\n" +
                                                "    \\scalebox{.9}{" + fooSignal.getProbMatrixFloat()[1][0] + "} &  \\scalebox{.9}{ " + fooSignal.getProbMatrixFloat()[1][1] + "}\\\\\n" +                                                
                                                "\\end{bmatrix}\n" +
                                                "\\]\n" +
                                                "}");
                        
                        if(k < pCircuit.getProbSignals().size() - 1) {
                            WriteFile.WriteContent(" & ");
                        }
                        
                        System.out.println(fooSignal.getId() + ": ");
                        matrixPrint(fooSignal.getProbMatrixFloat());
                        System.out.println("#####");
                        
                    }
                    
                    WriteFile.WriteContent("\\\\\n\\hline\n");
                    
                    WriteFile.WriteContent("\\fbox{\\parbox{\\textwidth}{\\textbf{\\quad \\qquad RELIABILITY BY SPR = " + result + "}}}\n");
                    
                    WriteFile.WriteContent("\\end{tabular}\n");
        
                    wFile.WriteContent("    \\end{center}\n" +"\\end{table}");
                    
                    System.out.println(pCircuit.getProbSignals());

                    
                    pCircuit.clearProbSignalsMatrix();
                    pCircuit.setPTMReliabilityMatrix();
                    pCircuit.setDefaultProbSourceSignalMatrix();
                    pCircuit.setProbSignalStates(true);

                    System.out.println("MultiPassV1 (" + reliabilities[i] + "): " + ops.SPRMultiPassOps.getSPRMultiPassReliaiblity(pCircuit, Terminal.getInstance().getCellLibrary(), reliabilities[i]));
                    
                    pCircuit.clearProbSignalsMatrix();
                    pCircuit.setPTMReliabilityMatrix();
                    pCircuit.setDefaultProbSourceSignalMatrix();
                    pCircuit.setProbSignalStates(true);
                    
                    
                }

                
                

                System.out.println("COUNTER MultiPassV1: " + ops.SPRMultiPassOps.COUNTER);                      
                System.out.println("");
                
                ops.SPRMultiPassOps.COUNTER = 0;
                ops.SPRMultiPassOps.VALUECOUNTER = 0;

                                                
            }
        }
        catch (ScriptException ex) {
            Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
        }
                                
        
        
        
        
        
        wFile.CloseFile();
    }
    
    public void Foo8() {
        
        //TESTE!!!
        
        String[] circuits = new String[]{
            "c17_cadence.v", 
            "c432_cadence.v", 
            "c499_cadence.v",
            "c880_cadence.v",
            "c1355_cadence.v",
            "c1908_cadence.v",
            "c2670_cadence.v",
            "c3540_cadence.v",
            "c5315_cadence.v",
            "c6288_cadence.v",
            "c7552_cadence.v",
        };
        
        String[] reliabilities = new String[]{

            "0.99", 
            "0.995", 
            "0.999", 
            "0.9999", 
            "0.99999", 
            "0.999999",             
        };
        
        
        
        for (int i = 0; i < circuits.length; i++) {
            try {
                Terminal.getInstance().executeCommand("read_verilog "+circuits[i]);          
                
                LevelCircuit lCircuit = Terminal.getInstance().getLevelCircuit();
                ProbCircuit pCircuit = ProbCircuit.create(lCircuit.getName(), lCircuit.getSignals(), lCircuit.getGates(), lCircuit.getGateLevels());
                
                System.out.println("CIRCUIT ==> " + pCircuit.getName());
                System.out.println("níveis " + pCircuit.getProbGateLevels().size());
                System.out.println("fanouts " + pCircuit.getFanouts().size());
                System.out.println("portas " + pCircuit.getProbGates().size());
                System.out.println("");
                
                for (int j = 0; j < reliabilities.length; j++) {
                    Terminal.getInstance().getCellLibrary().setPTMCells2(Float.valueOf(reliabilities[j]));
                    Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal(reliabilities[j]));

                    pCircuit.clearProbSignalsMatrix();                    
                    pCircuit.setDefaultProbSourceSignalMatrix();
                    pCircuit.setProbSignalStates(false);
                    pCircuit.setPTMReliabilityMatrix();
                    
                    //System.out.println(SPROpsFloat.getSPRReliability(pCircuit) + " <=== sprFloat");
                    //System.out.println(inherentReliability(pCircuit, reliabilities[j]).toPlainString() + " <=== INERENTE");
                    System.out.println("");
                }
                
                
                
                
            } catch (ScriptException ex) {
                Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("");
            System.out.println("");
            System.out.println("");
        }
        
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
                value[0] = PTMOps2.getCircuitReliabilityByPTM(pCircuit);
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
        
        //configura o método SPR para executar os cálculos de confiabilidade
        RunScore runScore = new ScoreBySPR(new BigDecimal(q));
        //cria o algoritmo para identificação do vetor crítico
        //passando o método de cálculo
        WRVAlgoritm wrvalg = new WRVAlgoritm(runScore); 
        //executa o algoritmo
        //retorna um InputVector
        wrvalg.execute();          
        
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
