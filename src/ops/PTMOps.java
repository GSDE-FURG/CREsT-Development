/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ops;

import datastructures.CellLibrary;
import datastructures.Gate;
import datastructures.Signal;
import java.math.BigDecimal;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import levelDatastructures.DepthGate;
import levelDatastructures.GateLevel;
import levelDatastructures.InterLevel;
import levelDatastructures.LevelCircuit;

import org.ejml.simple.SimpleMatrix;
import sun.nio.cs.ext.Big5;

import writers.WriteFile;

/**
 *
 * @author matheus
 */
public class PTMOps {
    
    public static BigInteger MatrixInYobiByte(int expRows, int expColumns) {
        
        BigInteger big1 = new BigInteger("2");
        BigInteger big2 = new BigInteger("2");
        
        big1 = big1.pow(expRows);
        big2 = big2.pow(expColumns);
        
        
        System.out.println("row"+big1);
        System.out.println("col"+big2);
        
        BigInteger foo = new BigInteger("1024");
        
        big1 = big1.multiply(big2);
        big1 = big1.multiply(new BigInteger("4"));
        System.out.println("gghhhhhh"+big1);
        
        foo = foo.pow(3);
        System.out.println("foo_pow"+foo);
        
        big1 = big1.divide(foo);
        
        System.out.println("gghhhhhh"+big1);
        return big1;
    }
    
    public static int getFactorial2 (int columns, int rows) {
        
        int result = 0;
        BigInteger big = new BigInteger(Integer.toString(columns));
        big = big.multiply(new BigInteger(Integer.toString(rows)));
        
        while (!big.equals(BigInteger.ONE)) {            
            big = big.divide(new BigInteger("2"));
            result++;
        } 
        
        return result;
    }
    
    public static int PowInt(int number, int power) {
        
        double dNumber = (double)number;
        double dPower = (double)power;
        
        double result = Math.pow(dNumber, dPower);

        return (int)result;
    }
        
    
    public static String getMatrixLevelSize(Object level) {            

        int x;
        int y;
        
        if(level instanceof GateLevel) {
            GateLevel fooGateLevel = (GateLevel)level;

            x = 0;
            y = 0;
            
            for (int i = 0; i < fooGateLevel.getGates().size(); i++) {
                Object gate = fooGateLevel.getGates().get(i);
                if(gate instanceof DepthGate) {
                    DepthGate depthGate = (DepthGate)gate;
                    int inputs = depthGate.getGate().getInputs().size();
                    int outputs = depthGate.getGate().getOutputs().size();

                    x = x + inputs;
                    y = y + outputs;
                    

                } else if(gate instanceof Signal) {

                    x = x + 1;
                    y = y + 1;
                }
            }
            
            return "(2^" + x + ")" + " x (2^" + y + ")";
            
        } else if(level instanceof InterLevel) {
            InterLevel fooInterLevel = (InterLevel)level;

            x = fooInterLevel.getInSingnals().size();
            y = fooInterLevel.getOutSingnals().size();
            
            return "(2^" + x + ")" + " x (2^" + y + ")";
            
        } else {
            return null;
        }
    }    
    
    public static SimpleMatrix getInterconnectionMatrix(InterLevel interLevel) {
        
        int inSignals = interLevel.getInSingnals().size();
        int outSignals = interLevel.getOutSingnals().size();
        
        int x = PowInt(2, inSignals);
        int y = PowInt(2, outSignals);                
        
        
        SimpleMatrix interMatrix = new SimpleMatrix(x,y);        
        
        
        
        for (int i = 0; i < x; i++) {
            
            String binary = String.format("%0" + inSignals + "d", Integer.valueOf(Integer.toBinaryString(i)));
            
            int colunmIndex;
            
            Map<String, String> dict = new LinkedHashMap<>();
            
            for (int j = 0; j < inSignals; j++) {                
                dict.put(interLevel.getInSingnals().get(j).getId(), Character.toString(binary.charAt(j)));                
            }
            
            String binaryOut = "";
            
            for (int j = 0; j < outSignals; j++) {
                //System.out.println(interLevel.getOutSingnals().get(j).getId());
                binaryOut = binaryOut + dict.get(interLevel.getOutSingnals().get(j).getId());
            }
                        
            colunmIndex = Integer.parseInt(binaryOut,2);
            
            //System.out.println(binary);
            //System.out.println(colunmIndex + "\n\n");
            
            interMatrix.set(i, colunmIndex, 1.);            
        }
                        
        return interMatrix;
 
    }
    
    /*public static SimpleMatrix getGateLevelMatrix(double prob, GateLevel gateLevel) {
    
    SimpleMatrix gateLevelMatrix;
    
    if(gateLevel.getGates().get(0) instanceof DepthGate) {
    gateLevelMatrix = getGateMatrix(prob, ((DepthGate)gateLevel.getGates().get(0)).getGate());
    } else {
    gateLevelMatrix = getWireMatrix();
    }
    
    for (int i = 1; i < gateLevel.getGates().size(); i++) {
    Object gate = gateLevel.getGates().get(i);
    if (gate instanceof DepthGate) {
    gateLevelMatrix = gateLevelMatrix.kron(getGateMatrix(prob, ((DepthGate)gate).getGate()));
    } else {
    gateLevelMatrix = gateLevelMatrix.kron(getWireMatrix());
    }
    }
    return gateLevelMatrix;
    }*/
    
    public static SimpleMatrix getGateLevelMatrix(GateLevel gateLevel, boolean sonf) {
        
        SimpleMatrix gateLevelMatrix;        
        
        if(gateLevel.getGates().get(0) instanceof DepthGate) {
            if(sonf) {
                gateLevelMatrix = getSOnFGateMatrix(((DepthGate)gateLevel.getGates().get(0)).getGate());
                if(gateLevelMatrix == null) {
                    System.err.println("DEURUIM!!!");                   
                }
            } else {
                gateLevelMatrix = getPTMGateMatrix(((DepthGate)gateLevel.getGates().get(0)).getGate());
            }
        } else {
            gateLevelMatrix = getWireMatrix();
        }        
        
        for (int i = 1; i < gateLevel.getGates().size(); i++) {
            Object gate = gateLevel.getGates().get(i);
            if (gate instanceof DepthGate) {
                if(sonf) {
                    gateLevelMatrix = gateLevelMatrix.kron(getSOnFGateMatrix(((DepthGate)gate).getGate()));
                } else {
                    gateLevelMatrix = gateLevelMatrix.kron(getPTMGateMatrix(((DepthGate)gate).getGate()));
                }
            } else {
                System.out.println("mamae");
                System.out.println(gateLevelMatrix);
                gateLevelMatrix = gateLevelMatrix.kron(getWireMatrix());
            }
        }
        return gateLevelMatrix;
    }
    
    
    /*public static SimpleMatrix getGateMatrix(Gate gate) {
    
    //System.out.println("GATE: " + gate.getType());
    
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine engine = mgr.getEngineByName("JavaScript");
    
    String function = gate.getType().getFunctions().get(0);
    int x = PowInt(2, gate.getInputs().size());
    int y = PowInt(2, gate.getOutputs().size());
    SimpleMatrix gateMatrix = new SimpleMatrix(x,y);
    
    function = function.split("=")[1];
    function = function.replaceAll("\\Q*\\E", "&&");
    function = function.replaceAll("\\Q+\\E", "||");
    
    for (int i = 0; i < x; i++) {
    
    String binary = String.format("%0" + gate.getInputs().size() + "d", Integer.valueOf(Integer.toBinaryString(i)));
    //System.out.println(binary);
    
    try {
    for (int j = 0; j < gate.getType().getInputs().size(); j++) {
    int bit = Character.getNumericValue(binary.charAt(j));
    engine.eval("var " + gate.getType().getInputs().get(j) + "=" + Boolean.toString(bit != 0) + ";");
    }
    //System.out.println("ROW: " + i);
    boolean result = (boolean)engine.eval(function + ";");
    
    if(result) {
    gateMatrix.set(i, 1, 1.);
    } else {
    gateMatrix.set(i, 0, 1.);
    }
    
    } catch (ScriptException ex) {
    Logger.getLogger(PTMOps.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    return gateMatrix;
    }*/
    
    /*public static SimpleMatrix getGateMatrix(double prob, Gate gate) {
    SimpleMatrix probGateMatrix = getGateMatrix(gate);
    
    for (int i = 0; i < probGateMatrix.getNumElements(); i++) {
    if(probGateMatrix.get(i) == 1.) {
    probGateMatrix.set(i, prob);
    } else {
    probGateMatrix.set(i, 1. - prob);
    }
    }
    return probGateMatrix;
    }*/
    
    public static SimpleMatrix getPTMGateMatrix(Gate gate) {
        
        BigDecimal[][] ptmMatrix = gate.getType().getPTM();
                
        if(ptmMatrix != null) {
            SimpleMatrix simpleMatrix = new SimpleMatrix(ptmMatrix.length, ptmMatrix[0].length);
            
            for (int i = 0; i < ptmMatrix.length; i++) {
                for (int j = 0; j < ptmMatrix[0].length; j++) {
                    simpleMatrix.set(i, j, ptmMatrix[i][j].doubleValue());
                }
            }
            
            return simpleMatrix;
        }
        
        return null;
    }
    
    public static SimpleMatrix getSOnFGateMatrix(Gate gate) {
        
        BigDecimal[][] sonfMatrix = gate.getType().getPtmSOnF();
                
        if(sonfMatrix != null) {
            SimpleMatrix simpleMatrix = new SimpleMatrix(sonfMatrix.length, sonfMatrix[0].length);
            
            for (int i = 0; i < sonfMatrix.length; i++) {
                for (int j = 0; j < sonfMatrix[0].length; j++) {
                    simpleMatrix.set(i, j, sonfMatrix[i][j].doubleValue());
                }
            }
            
            return simpleMatrix;
        }
        
        return null;
    }
    
    
    
    public static SimpleMatrix getPTMMatrix(LevelCircuit lcirc, boolean sonf) {
        
        int fileCounter = 1;
        int counter = 1;

        SimpleMatrix resultMatrix;
        SimpleMatrix interMatrix;
        SimpleMatrix gatMatrix;
        String inter = "";
        String gat = "";
        InterLevel auxInter = lcirc.getInterLevels().get(0);
        gatMatrix = PTMOps.getGateLevelMatrix(lcirc.getGateLevels().get(0), sonf);
        
        if(!(auxInter.getInSingnals().equals(auxInter.getOutSingnals()))) {
            interMatrix = getInterconnectionMatrix(lcirc.getInterLevels().get(0));
            //interMatrix.saveToFileCSV(fileCounter + "-inter1.csv");
            resultMatrix = interMatrix.mult(gatMatrix);
        } else {
            resultMatrix = gatMatrix;
        }
        fileCounter++;
        //gatMatrix.saveToFileCSV(fileCounter + "-gate1.csv");
        fileCounter++;
        //resultMatrix.saveToFileCSV(fileCounter + "-ptm" + counter + ".csv");
        fileCounter++;
        counter++;

        for (int i = 1; i < lcirc.getGateLevels().size(); i++) {
            
            auxInter = lcirc.getInterLevels().get(i);
            
            if(!(auxInter.getInSingnals().equals(auxInter.getOutSingnals()))) {
                inter += ("" + i);
                interMatrix = getInterconnectionMatrix(auxInter);
                //interMatrix.saveToFileCSV(fileCounter + "-inter" + (i+1) +".csv");
                fileCounter++;
                
                resultMatrix = resultMatrix.mult(interMatrix);
                //resultMatrix.saveToFileCSV(fileCounter + "-ptm" + counter +".csv");
                fileCounter++;
                counter++;
            }
            
            gat += ("" + i);
            gatMatrix = PTMOps.getGateLevelMatrix(lcirc.getGateLevels().get(i), sonf);
            //gatMatrix.saveToFileCSV(fileCounter + "-gate" + (i+1)+ ".csv");
            fileCounter++;
            
            resultMatrix = resultMatrix.mult(gatMatrix);
            //resultMatrix.saveToFileCSV(fileCounter + "-ptm"+ counter +".csv");
            fileCounter++;
            counter++;
            
            
        }
        return resultMatrix;

    }
    
    public static ArrayList<Integer> getITM(LevelCircuit lcirc) {
        
        
        
        ArrayList<Integer> itm = new ArrayList<>();
        
        InterLevel auxInter = lcirc.getInterLevels().get(0);
        SimpleMatrix itmMatrix;
        
        if(!(auxInter.getInSingnals().equals(auxInter.getOutSingnals()))) {
            itmMatrix = getInterconnectionMatrix(auxInter);
            itmMatrix = itmMatrix.mult(getGateLevelMatrix(lcirc.getGateLevels().get(0), false));
        } else {            
            itmMatrix = getGateLevelMatrix(lcirc.getGateLevels().get(0), false);
        }
                
        
        for (int i = 1; i < lcirc.getGateLevels().size(); i++) {
            auxInter = lcirc.getInterLevels().get(i);
            
            if(!(auxInter.getInSingnals().equals(auxInter.getOutSingnals()))) {
                itmMatrix = itmMatrix.mult(getInterconnectionMatrix(lcirc.getInterLevels().get(i)));
            }
            
            itmMatrix = itmMatrix.mult(getGateLevelMatrix(lcirc.getGateLevels().get(i), false));
        }
        
        
        System.out.println(itmMatrix);
        for (int i = 0; i < itmMatrix.getNumElements(); i++) {
            if(itmMatrix.get(i) == 1.) {
                itm.add(i);
            }
        }
        
        System.out.println("A ITM já está no vetor!!!");
        return itm;
    }
    
    public static double getPTM(String reliability, LevelCircuit lcirc, CellLibrary cellLib, boolean sonf) {
        
        // Setting q = 1. for taking the ITM 
        cellLib.setPTMCells(BigDecimal.ONE);
        
        ArrayList<Integer> itmCircuit = getITM(lcirc);
        SimpleMatrix ptmMatrix;        
                
        if(sonf) {
            cellLib.setPTMSonfCell(new BigDecimal(reliability));
            ptmMatrix = getPTMMatrix(lcirc, true);
        } else {
            //Setting q to passed reliability
            cellLib.setPTMCells(new BigDecimal(reliability));
            ptmMatrix = getPTMMatrix(lcirc, false);
        }
        
        double sum = 0.;
        double n = (double)PowInt(2, lcirc.getInputs().size());
        
        for (int i = 0; i < itmCircuit.size(); i++) {
            sum = sum + ptmMatrix.get(itmCircuit.get(i));
        }
        
        //System.out.println(sum);
        //System.out.println(n);
        return ((1./n) * sum);
    }
    
    public static double getPTM(LevelCircuit lcirc, boolean sonf, int[] itm) {
        
        SimpleMatrix ptmMatrix;        
                
        if(sonf) {
            ptmMatrix = getPTMMatrix(lcirc, true);
        } else {
            ptmMatrix = getPTMMatrix(lcirc, false);
        }
        
        double sum = 0.;
        double n = (double)PowInt(2, lcirc.getInputs().size());
        
        for (int i = 1; i < itm.length; i++) {
            sum = sum + ptmMatrix.get(i-1, itm[i]);
        }

        return ((1./n) * sum);
    }
    
    public static SimpleMatrix getWireMatrix() {
        
        SimpleMatrix wireMatrix = new SimpleMatrix(2, 2, true, new double[]{1.,0.,0.,1.});
        
        return wireMatrix;
    }
    
    // GET THE TOTAL ELEMENTS OF PTM MATRIX GATE
    public static int getTotalElements(Gate gate) {
        
        int total;
        int ins = gate.getInputs().size();
        int outs = gate.getOutputs().size();
        
        total = PTMOps.PowInt(2, ins) * PTMOps.PowInt(2, outs);
                        
        return total;
    }
    
    public static BigDecimal getTotalElements(GateLevel gLevel) {
        
        BigDecimal total;
        
        BigDecimal ins = new BigDecimal(2);
        BigDecimal outs = new BigDecimal(2);
        
        ins = ins.pow(gLevel.getIn());
        outs = outs.pow(gLevel.getOut());
             
        total = ins.multiply(outs);
                         
        return total;
    }
    
    public static BigDecimal getTotalElements(InterLevel iLevel) {
        
        BigDecimal total;
        
        BigDecimal ins = new BigDecimal(2);
        BigDecimal outs = new BigDecimal(2);
        
        ins = ins.pow(iLevel.getInSingnals().size());
        outs = outs.pow(iLevel.getOutSingnals().size());
             
        total = ins.multiply(outs);
                         
        return total;
    }
    
    public static BigDecimal getTotalElements(LevelCircuit lCircuit) {
        
        BigDecimal total;
        
        BigDecimal ins = new BigDecimal(2);
        BigDecimal outs = new BigDecimal(2);
        
        
        ins = ins.pow(lCircuit.getInputs().size());
        outs = outs.pow(lCircuit.getOutputs().size());
             
        total = ins.multiply(outs);
                         
        return total;
    }
    
    public static BigDecimal getTotalMultiplicationsWithMatrixRepresentation(LevelCircuit lCircuit) {        
        
        BigDecimal multiplications;
        BigDecimal sums = BigDecimal.ZERO;
        
        InterLevel firstILevel = lCircuit.getInterLevels().get(0);
        GateLevel firstGLevel = lCircuit.getGateLevels().get(0);
        
        multiplications = PTMOps.getTotalElements(firstGLevel); 
        
        if(!(firstILevel.getInSingnals().equals(firstILevel.getOutSingnals()))) {                        
            
            BigDecimal colsSecondMarix = new BigDecimal(2).pow(firstILevel.getOutSingnals().size());
            
            multiplications = multiplications.add(colsSecondMarix);
            sums = sums.add(colsSecondMarix.subtract(BigDecimal.ONE));
            
        }
        
        for (int i = 1; i < lCircuit.getGateLevels().size(); i++) {
            InterLevel iLevel = lCircuit.getInterLevels().get(i);
            GateLevel gLevel = lCircuit.getGateLevels().get(i);
            
            if(!(iLevel.getInSingnals().equals(iLevel.getOutSingnals()))) {
                
                BigDecimal linesSecondMatrix = new BigDecimal(2).pow(iLevel.getInSingnals().size());
                
                multiplications = multiplications.add(linesSecondMatrix);
                sums = sums.add(linesSecondMatrix.subtract(BigDecimal.ONE));
            }
            
            multiplications = multiplications.add(PTMOps.getTotalElements(gLevel));
            
            BigDecimal linesSecondMatrix = new BigDecimal(2).pow(gLevel.getIn());
            
            multiplications = multiplications.add(linesSecondMatrix);
            sums = sums.add(linesSecondMatrix.subtract(BigDecimal.ONE));
        }
        
        BigDecimal d = multiplications.stripTrailingZeros();
        BigDecimal e = sums.stripTrailingZeros();
        
        System.out.println("MULTIPLICATONS: " + multiplications);
        System.out.println("MULTIPLICATONS: " + (d.precision() - d.scale()));
        System.out.println("SUMS: "           + sums);
        System.out.println("SUMS: "           + (e.precision() - e.scale()));
        return multiplications;
    }
    
    public static BigDecimal getTotalMultiplicationsWithoutMatrixRepresentation(LevelCircuit lCircuit) {        
        
        BigDecimal multiplications = BigDecimal.ZERO;
        BigDecimal sums = BigDecimal.ZERO;
        
        
        InterLevel firstILevel = lCircuit.getInterLevels().get(0);
        GateLevel firstGLevel = lCircuit.getGateLevels().get(0);
        
        BigDecimal gLevelMultiplications = new BigDecimal(firstGLevel.getGates().size() - 1);
        
        
        if(firstILevel.getInSingnals().equals(firstILevel.getOutSingnals())) {
            multiplications = multiplications.add(gLevelMultiplications);            
        } else {
            BigDecimal cols = new BigDecimal(2).pow(firstILevel.getOutSingnals().size());
            
            multiplications = cols.multiply(gLevelMultiplications);
            multiplications = multiplications.add(cols);
            
            sums = cols.subtract(BigDecimal.ONE);
            
        }
        
        for (int i = 1; i < lCircuit.getGateLevels().size(); i++) {
            
            InterLevel iLevel = lCircuit.getInterLevels().get(i);
            GateLevel gLevel = lCircuit.getGateLevels().get(i);
            
            if(!(iLevel.getInSingnals().equals(iLevel.getOutSingnals()))) {
                
                BigDecimal cols = new BigDecimal(2).pow(iLevel.getInSingnals().size());
                
                multiplications = multiplications.multiply(cols);
                multiplications = multiplications.add(cols);
                
                sums = sums.multiply(cols);
                sums = sums.add(cols.subtract(BigDecimal.ONE));
            }
            
            BigDecimal cols = new BigDecimal(2).pow(gLevel.getIn());
            gLevelMultiplications = new BigDecimal(gLevel.getGates().size() - 1);
                
            multiplications = cols.multiply(multiplications.add(gLevelMultiplications));
            multiplications = multiplications.add(cols);

            sums = sums.multiply(cols);
            sums = sums.add(cols.subtract(BigDecimal.ONE));
                        
        }
        
        BigDecimal d = multiplications.stripTrailingZeros();
        BigDecimal e = sums.stripTrailingZeros();
        
        System.out.println("MULTIPLICATONS: " + multiplications);
        System.out.println("MULTIPLICATONS: " + (d.precision() - d.scale()));
        System.out.println("SUMS: "           + sums);
        System.out.println("SUMS: "           + (e.precision() - e.scale()));
        
        return multiplications;
    }
    
    public static ArrayList<BigDecimal> getOneElementSumsAndMultiplications(Object levelA, Object levelB) {
        
        ArrayList<BigDecimal> information = new ArrayList<>();
        
        BigDecimal multiplications = BigDecimal.ZERO;
        BigDecimal sums = BigDecimal.ZERO;
        
        if(!(levelA instanceof InterLevel)) {
            GateLevel gLevel = (GateLevel)levelA;
        }
        
        return information;
    }
    
    public static BigDecimal getLevelOneElementsMultiplications(Object level) {
        BigDecimal multiplications = BigDecimal.ZERO;
        
        if(level instanceof GateLevel) {
            GateLevel gLevel = (GateLevel)level;
            multiplications = new BigDecimal(gLevel.getGates().size() - 1);
        }
        
        return multiplications;
    }
    
    
    
}
