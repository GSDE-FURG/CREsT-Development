/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readers;

import datastructures.CellLibrary;
import datastructures.Circuit;
import datastructures.Gate;
import datastructures.Signal;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;

import static  ops.CommonOps.timenow;
import static  ops.CommonOps.timestamp;
import static  ops.CommonOps.timestampDiff;

import tool.Terminal;

/**
 * Read Verilog Files (input file) and Fill Circuit, Gate and Signal data
 structures, creating the structures to do the fault collapsing process.
 *
 * @author Gabriel Soares Porto
 */
public class MappedVerilogReader {

    private String line = new String();
    private String path = new String();
    private Circuit circ;
    private CellLibrary cellLibrary;

    public long gateAndSignalsCreation = 0;
    public long signalsSettings = 0;
    
    /**
     * Simple Verilog File Reader class constructor. To instance an object.
     *
     */
    /* public MappedVerilogReader() {
    }*/
    
    /*public MappedVerilogReader(String path) throws IOException {
    readFile(path);
    }*/
    
    public MappedVerilogReader(String path, CellLibrary library) throws IOException, Exception {        
        this.cellLibrary = library;
        readFile(path);
    }

    /**
     * This method return the Verilog File Path.
     *
     * @return String - Verilog File Path
     */
    public String getPath() {
        return path;
    }

    /**
     * This method set the Verilog File Path.
     *
     * @param path String - Verilog File Path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * This method return the Circuit Object.
     *
     * @return Circuit - Circuit Data Structure
     */
    public Circuit getCircuit() {
        return circ;
    }

    
    /**
     * This method return true if the verilog file
     * contains listed gate.
     * 
     * @param line String - Verilog File Line
     *
     * @return boolean
     */
    public boolean isLineGate(String line) {
     
        if (line.matches("(.*)("+ getGatePattern() +")(.*)")) {
            return true;
        }
        return false;
    }
    
    public String getGatePattern() {
        
        String inputString = "";
        String outputString = "";
        
        final String[] metaCharacters = {"\\","^","$","{","}","[","]","(",")",".","*","+","?","<",">","-","&"};         
        
        for (int i = 0; i < cellLibrary.getCells().size(); i++) {
            inputString = inputString + cellLibrary.getCells().get(i).getName() + "|";
        }
        
        for (int i = 0 ; i < metaCharacters.length ; i++){
            if(inputString.contains(metaCharacters[i])){
                outputString = inputString.replace(metaCharacters[i],"\\"+metaCharacters[i]);
                inputString = outputString;
            }
        }
        
        return inputString.substring(0, inputString.length()-1);
    }
    
    /**
     * This method cleanup the Mapped Verilog File Line removing the reserved
     * word, the ";" - if exists and the last ","
     * 
     * @param line String - Verilog File Line
     *
     * @return String list - a list with signals IDs
     */
    public String cleanLine(String line) {
        String cleaned; 
        String delimiter = "##--@@@---##";
        cleaned = line;
        cleaned = cleaned.trim();
        cleaned = cleaned.replaceAll("input|output|wire|;|,$", "");
        // Hack to eliminate more than one whitespaces
        cleaned = cleaned.replaceAll("( )+", delimiter);
        cleaned = cleaned.replaceAll(delimiter, " ");
        // ****************** //
        return cleaned;
    }
    
    
    /**
     * This method create and allocate signals in Class Signal Main list "sig"
     * 
     * @param line String - Verilog File Line
     * 
     */
    public void signalFactory(String line) {
        
        String cleanedLine = cleanLine(line);
        String[] preSignals = cleanedLine.split(",");
        
        for (int i = 0; i < preSignals.length; i++) {            
            Signal auxSig = new Signal();
            String signalID = preSignals[i].trim();
            auxSig.setId(signalID);

            this.circ.addSignal(auxSig);
            this.circ.addHashSignal(signalID, auxSig);
        }
    }
    
    /**
     * This method create and allocate signals in Class Signal Main list "sig"
     * 
     * @param line String - Verilog File Line
     * 
     */
    public void signalFactory(String line, boolean isOutput) {                
        
        String cleanedLine = cleanLine(line);
        String[] preSignals = cleanedLine.split(",");
        
        for (int i = 0; i < preSignals.length; i++) {            
            Signal auxSig = new Signal();
            String signalID = preSignals[i].trim();
            auxSig.setId(signalID);
            auxSig.addDestiny(null);

            this.circ.addSignal(auxSig);
            this.circ.addHashSignal(signalID, auxSig);
        }
    }
    
    /**
     * This method create and allocate gates in Class Gate Main list "gat"
     * 
     * @param line String - Verilog File Line
     * 
     */
    public void gateFactory(String line) throws Exception {
        
        String gateTypeName;
        String gateIdIOs;
        String[] preGate;
        String cleanedLine = cleanLine(line);
        
        preGate = cleanedLine.split(" ", 2);        
        gateTypeName = preGate[0];


        if (cellLibrary.getCellByName(gateTypeName) != null) {

            //long timeFlag1 = timenow();

            gateIdIOs = preGate[1].replaceAll(" ", "");

            preGate = gateIdIOs.split("\\Q(\\E", 2);

            String gateId = preGate[0];

            String gateIOsString = preGate[1].replaceFirst("[\\Q.\\E]", "");

            gateIOsString = gateIOsString.substring(0, gateIOsString.length()-2);

            String[] gateIOs = gateIOsString.split("(\\Q),.\\E)");
            
            Gate fooGate = new Gate(gateId, cellLibrary.getCellByName(gateTypeName));
            
            ArrayList<Signal> inputs = new ArrayList<>(fooGate.getType().getInputs().size());
            ArrayList<Signal> outputs = new ArrayList<>(fooGate.getType().getOutputs().size());

            //gateAndSignalsCreation = gateAndSignalsCreation + timestampDiff(timeFlag1);

            //long timeFlag2 = timenow();



            for (int i = 0; i < gateIOs.length; i++) {

                String[] signal = gateIOs[i].split("\\Q(\\E", 2);


                // Se tem, quer dizer que o sinal é uma entrada!
                if(fooGate.getType().getInputs().contains(signal[0])) {
                    inputs.add(fooGate.getType().getInputs().indexOf(signal[0]), getSignalById2(signal[1]));
                    getSignalById2(signal[1]).addDestiny(fooGate);
                }
                else {
                    if (fooGate.getType().getOutputs().contains(signal[0])) {
                        outputs.add(fooGate.getType().getOutputs().indexOf(signal[0]), getSignalById2(signal[1]));
                        getSignalById2(signal[1]).setOrigin(fooGate);
                    }
                    else {
                        // TODO: criar modelo para exceções
                        Terminal.getInstance().terminalOutput("KKKKKKKK");
                    }
                }
            }



            //signalsSettings = signalsSettings + timestampDiff(timeFlag2);

            fooGate.setInputs(inputs);
            fooGate.setOutputs(outputs);

            this.circ.addGate(fooGate);
        
        } else {
            throw new java.lang.RuntimeException("Cell " + gateTypeName + " not founded!!");
        }
    }
    
    public Signal getSignalById(String id) {
        
        for (int i = 0; i < this.circ.getSignals().size(); i++) {
            if(this.circ.getSignals().get(i).getId().equals(id)) {
                return this.circ.getSignals().get(i);
            }
        }
        
        System.out.println(this.circ.getName() + "<----");
        System.err.println("Signal " + id + " doesnt exist!!");
        return null;
    }

    public Signal getSignalById2(String id) {
        return this.circ.getHashSignalById(id);
    }
    
    /**
     * This method read the verilog file (input file) according to the file path
     * parameter, creating a graph for the fundamentals structures circuit, gate
     * and signal representing the circuit from verilog file.
     *
     * @param path String - Verilog File Path
     * @throws java.io.IOException
     */
    public void readFile(String path) throws IOException, Exception {

        long lineIFstime = 0;
        long signalFactoryTime = 0;
        long gateFactoryTime = 0;
        long whileHead;
        long whileTail;
        long signalFlag;
        long gateFlag;
        
        File file = new File(path);
        BufferedReader br;
        //Long timeNow = timenow();
        this.circ = new Circuit();
        //Debug_matheus
        //timestamp(timeNow, "New Circuit...");

        //timeNow = timenow();
        this.circ.setName(file.getName().substring(0, file.getName().indexOf(".")));
        //Debug_matheus
        //timestamp(timeNow, "Circuit naming...");
        String reserved = "";        

        //timeNow = timenow();
        br = new BufferedReader(new FileReader(file));
        //Debug_matheus
        //timestamp(timeNow, "Buffreader setting...");

        //Long timeWhile = timenow();

        while ((line = br.readLine()) != null) {
            //Debug_matheus
            //whileHead = timenow();

            if(line.contains("//")) {
                line = line.split("//")[0];
            }
            
            if (line.contains("input")) {                   
               reserved = "input";
            }

            if (line.contains("output")) {                   
               reserved = "output";
            }

            if (line.contains("wire")) {                   
               reserved = "wire";
            }

            //Debug_matheus
            //lineIFstime = lineIFstime + timestampDiff(whileHead);

            switch(reserved){
                case "input":
                case "wire":
                    //signalFlag = timenow();
                    signalFactory(line);
                    //Debug_matheus
                    //signalFactoryTime = signalFactoryTime + timestampDiff(signalFlag);
                    break;
                case "output":
                    //signalFlag = timenow();
                    signalFactory(line, true);
                    //Debug_matheus
                    //signalFactoryTime = signalFactoryTime + timestampDiff(signalFlag);
                    break;                        

                default:
                    if (isLineGate(line)) {
                        //gateFlag = timenow();
                        gateFactory(line);
                        //Debug_matheus
                        //gateFactoryTime = gateFactoryTime + timestampDiff(gateFlag);
                    }
            }

            //whileTail = timenow();
            if (line.contains(";")) {                   
               reserved = "null";
            }
            //Debug_matheus
            //lineIFstime = lineIFstime + timestampDiff(whileTail);

        }

        //Debug_matheus
        /*
        timestamp(timeWhile, "After file lines while...");
        System.out.println("WhileIFs time = " + lineIFstime + " ms");
        System.out.println("SignalsFactory time = " + signalFactoryTime + " ms");
        System.out.println("GatesFactory time = " + gateFactoryTime + " ms");
        System.out.println();
        System.out.println("Gates and Signals Creation = " + gateAndSignalsCreation + " ms");
        System.out.println("Signals Settings = " + signalsSettings + " ms");
        */
    }
}
