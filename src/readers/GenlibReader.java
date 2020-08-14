/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readers;

import datastructures.Cell;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author matheus
 */
public class GenlibReader {
    
    private String line;
    private String path;
    private ArrayList<Cell> cells;

    public GenlibReader() {
        line = new String();
        path = new String();
        cells = new ArrayList<Cell>();
    }
    
    public ArrayList<Cell> getCells() {
        return cells;
    }
    
    public ArrayList<Cell> getCellLibrary(String path) throws IOException {
        readFile(path);        
        return cells;
    }
    
    public void addCellLibrary(Cell cell) {
        cells.add(cell);
    }
    
    /**
     * This method clean the genlib line file removing "whitespaces" in seguence
     * and all "tabs".
     * 
     * @param line
     * @return
     */
    public String getCleanedLine(String line) {
        String cleaned = line.replaceAll("\\s{2,}", " ").replaceAll("\t", " ");                                     
        return cleaned;
    }
    
    public String getCleanedFunction(String function) {
        return function.replaceAll("[\\Q!();\\E]", "");
    }
    
    public String getCellOutput(String function) {
        
        String output = function.split("=")[0];
        
        return output;
    }
    
    public String[] getCellInputs(String function) {
        
        String[] inputs;
        
        String delimiter = "##----##";
        
        String inputLine = function.split("=")[1];
        
        inputLine = getCleanedFunction(inputLine);
        
        inputLine = inputLine.replaceAll("[\\Q+*\\E]", delimiter);
        
        inputs = inputLine.split(delimiter);
                
        
        return inputs;
    }
    
    public ArrayList<String> removeDuplicates(ArrayList<String> list) {

        // Store unique items in result.
        ArrayList<String> result = new ArrayList<>();

        // Record encountered Strings in HashSet.
        HashSet<String> set = new HashSet<>();

        // Loop over argument list.
        for (String item : list) {

            // If String is not in set, add it to the list and the set.
            if (!set.contains(item)) {
                result.add(item);
                set.add(item);
            }
        }
        return result;
    }

    
    public Cell CellFactory(String line) {
        
        String[] cleanedLine = getCleanedLine(line).split(" ");
        
        String name = cleanedLine[1];
        
        float area = Float.valueOf(cleanedLine[2]);
        
        String function = cleanedLine[3].replaceAll(";", "");
        
        ArrayList<String> functions = new ArrayList<>();
        functions.add(function);
        
        ArrayList<String> outputs = new ArrayList<>();
        outputs.add(getCellOutput(function));
        
        ArrayList<String> inputs = new ArrayList<>();
        String[] teste = getCellInputs(function);
        
        if(teste[0].contains("CONST0") || teste[0].contains("CONST1")) {
            
            Cell noInputsCell = new Cell(name, inputs, outputs, area, functions);
            
            if(teste[0].contains("CONST0")) {
                noInputsCell.setTruthTable("0");
            } else {
                noInputsCell.setTruthTable("1");
            }
            
            return noInputsCell;
            
        } else {            
            for (int i = 0; i < teste.length; i++) {
                inputs.add(teste[i]);
            }

            inputs = removeDuplicates(inputs);
        }
        return new Cell(name, inputs, outputs, area, functions);    
    }        

    public void readFile(String file) throws IOException {

        BufferedReader br = null;
        this.path = file;

            
            br = new BufferedReader(new FileReader(this.path));

            while ((line = br.readLine()) != null) {
                if(line.matches("^GATE(.*)")) {                    
                    addCellLibrary(CellFactory(line));
                }
            }
                        
        
    }
    
}
