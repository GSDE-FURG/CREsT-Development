/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Matheus Ferreira Pontes
 */
public class Cell {
    
    private String name;
    private ArrayList<String> inputs;
    private ArrayList<String> outputs;
    private float area;
    private ArrayList<String> functions;
    private String truthTable;
    private BigDecimal[][] ptm;
    private float[][] ptm2;
    private BigDecimal[][] itm2;
    private BigDecimal[][] ptmSOnF;
    private int[] itm;
    private Map<ArrayList<Boolean>, Boolean> comb;
    
    
    public Cell() {
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
        functions = new ArrayList<>();
        comb = new LinkedHashMap<>();
    }
    
    public Cell(String name) {
        
        this.name = name;
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
        functions = new ArrayList<>();
        comb = new LinkedHashMap<>();
    }
    
    public Cell(String name, ArrayList<String> inputs,
                ArrayList<String> outputs, float area,
                ArrayList<String> functions) {
        this.name = name;
        this.inputs = inputs;
        this.outputs = outputs;
        this.area = area;
        this.functions = functions;
        comb = new LinkedHashMap<>();
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getInputs() {
        return inputs;
    }

    public void setInputs(ArrayList<String> inputs) {
        this.inputs = inputs;
    }
    
    public void addInput(String input) {
        inputs.add(input);
    }

    public void removeInput(String input) {
        inputs.remove(input);
    }

    public ArrayList<String> getOutputs() {
        return outputs;
    }

    public void setOutputs(ArrayList<String> outputs) {
        this.outputs = outputs;
    }
    
    public void addOutput(String output) {
        outputs.add(output);
    }

    public void removeOutput(String output) {
        outputs.remove(output);
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public ArrayList<String> getFunctions() {
        return functions;
    }

    public void setFunctions(ArrayList<String> functions) {
        this.functions = functions;
    }
    
    public void addFunction(String function) {
        functions.add(function);
    }

    public void removeFunction(String function) {
        functions.remove(function);
    }

    public String getTruthTable() {
        return truthTable;
    }

    public void setTruthTable(String truthTable) {
        this.truthTable = truthTable;
    }
    
    public BigDecimal[][] getPTM() {
        return this.ptm;
    }
    
    public BigDecimal getPTMIndex(int x, int y) {
        return ptm[x][y];
    }
    
    public void setPTM(BigDecimal[][] ptm) {
        this.ptm = ptm;
    }
    
    ////////////////////////////////////////////////////

    public float[][] getPtm2() {
        return ptm2;
    }

    public void setPtm2(float[][] ptm2) {
        this.ptm2 = ptm2;
    }
    
    public float getPTM2Index(int x, int y) {
        return ptm2[x][y];
    }
    
    /////////////////////////////////////////////////

    public BigDecimal[][] getItm2() {
        return itm2;
    }

    public void setItm2(BigDecimal[][] itm) {
        this.itm2 = itm;
    }

    public int[] getItm() {
        return itm;
    }

    public void setItm(int[] itm) {
        this.itm = itm;
    }
        

    public BigDecimal[][] getPtmSOnF() {
        return ptmSOnF;
    }
    
    public BigDecimal getPtmSOnfIndex(int x, int y) {
        return ptmSOnF[x][y];
    }

    public void setPtmSOnF(BigDecimal[][] ptmSOnF) {
        this.ptmSOnF = ptmSOnF;
    }

    public Map<ArrayList<Boolean>, Boolean> getComb() {
        return comb;
    }

    public void setComb(Map<ArrayList<Boolean>, Boolean> comb) {
        this.comb = comb;
    }
            
    @Override
    public String toString() {
        return name;
    }
    
    public void combFactory(String combResult) {

        String numBits = "%"+this.inputs.size()+"s";
        Map<ArrayList<Boolean>, Boolean> combination = new LinkedHashMap<>();
        
        char[] charArray = combResult.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            String inComb = String.format(numBits, Integer.toBinaryString(i)).replace(' ', '0');            
            char[] charInComb = inComb.toCharArray();
            ArrayList<Boolean> vectorInComb = new ArrayList<>();
            //boolean[] vectorInComb = new boolean[charInComb.length];
            
            for (int j = 0; j < charInComb.length; j++) {
                vectorInComb.add(charInComb[j] == '1');
                //vectorInComb[j] = charInComb[j] == '1';
            }
            
            combination.put(vectorInComb, (charArray[i] == '1'));
        }
        
        this.setComb(combination);

    }
    
    public boolean getCombination(ArrayList<Boolean> comb) {
        return this.comb.get(comb);
    }
        
}
