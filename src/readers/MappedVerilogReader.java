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
            auxSig.setId(preSignals[i].trim());            

            this.circ.addSignal(auxSig);
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
            auxSig.setId(preSignals[i].trim());
            auxSig.addDestiny(null);

            this.circ.addSignal(auxSig);
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
            
            gateIdIOs = preGate[1].replaceAll(" ", "");

            preGate = gateIdIOs.split("\\Q(\\E", 2);

            String gateId = preGate[0];

            String gateIOsString = preGate[1].replaceFirst("[\\Q.\\E]", "");


            gateIOsString = gateIOsString.substring(0, gateIOsString.length()-2);

            String[] gateIOs = gateIOsString.split("(\\Q),.\\E)");
            
            Gate fooGate = new Gate(gateId, cellLibrary.getCellByName(gateTypeName));
            
            ArrayList<Signal> inputs = new ArrayList<>(fooGate.getType().getInputs().size());
            ArrayList<Signal> outputs = new ArrayList<>(fooGate.getType().getOutputs().size());


            for (int i = 0; i < gateIOs.length; i++) {

                String[] signal = gateIOs[i].split("\\Q(\\E", 2);

                if(fooGate.getType().getInputs().contains(signal[0])) {
                    inputs.add(fooGate.getType().getInputs().indexOf(signal[0]), getSignalById(signal[1]));                
                    getSignalById(signal[1]).addDestiny(fooGate);
                }
                else {
                    if (fooGate.getType().getOutputs().contains(signal[0])) {
                        outputs.add(fooGate.getType().getOutputs().indexOf(signal[0]), getSignalById(signal[1]));
                        getSignalById(signal[1]).setOrigin(fooGate);
                    }
                    else {
                        // TODO: criar modelo para exceções
                        Terminal.getInstance().terminalOutput("KKKKKKKK");
                    }
                }
            }

            fooGate.setInputs(inputs);
            fooGate.setOutputs(outputs);

            this.circ.addGate(fooGate);
        
        } else {
            throw new java.lang.RuntimeException("this is not quite as badfasfasfasffas");
        }
    }
    
    public Signal getSignalById(String id) {
        
        for (int i = 0; i < this.circ.getSignals().size(); i++) {
            if(this.circ.getSignals().get(i).getId().equals(id)) {
                return this.circ.getSignals().get(i);
            }
        }
        
        System.err.println("Signal " + id + " doesnt exist!!");
        return null;
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
        
        File file = new File(path);
        BufferedReader br;
        this.circ = new Circuit();

        this.circ.setName(file.getName().substring(0, file.getName().indexOf(".")));        

        String reserved = "";        
            
        br = new BufferedReader(new FileReader(file));                        
        

        while ((line = br.readLine()) != null) {                        
            
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

            switch(reserved){
                case "input":
                case "wire":
                    signalFactory(line);
                    break;
                case "output":
                    signalFactory(line, true);
                    break;                        

                default:
                    if (isLineGate(line)) {
                        gateFactory(line);
                    }
            }

            if (line.contains(";")) {                   
               reserved = "null";
            }

        }        
    }
}
