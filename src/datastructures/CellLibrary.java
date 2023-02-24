/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import ops.CommonOps;
import ops.PTMOps;
import static ops.PTMOps.PowInt;
import org.ejml.simple.SimpleMatrix;
import readers.GenlibReader;
import schivittz.Grafo;
import schivittz.Interface;
import schivittz.LeArquivo;

import ops.BooleanExpressionEvaluator;

/**
 *
 * @author matheus
 */
@SuppressWarnings("removal")
public class CellLibrary {
    
    private ArrayList<Cell> cells;

    public CellLibrary() {

    }
    
    public CellLibrary(String libraryPath) throws IOException, ScriptException {
        this.initLibrary(libraryPath);
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }
    
    
    public void initLibrary(String libraryPath) throws IOException {
        cells = new GenlibReader().getCellLibrary(libraryPath);
        setTruthTables();
        setITMCells();
    }
    
    public Cell getCellByName(String name) {
        for (int i = 0; i < cells.size(); i++) {
            if(cells.get(i).getName().equals(name)) {
                return cells.get(i);
            }
        }
        
        return null;
    }

    public void setTruthTables() {
        
        for (int i = 0; i < cells.size(); i++) {

            String truthTable = BooleanExpressionEvaluator.getTruthTable(cells.get(i));
            BigInteger bigIntTable = new BigInteger(truthTable,2);
            cells.get(i).setTruthTable(bigIntTable.toString(16));
            if(cells.get(i).getInputs().size() != 0) {
                cells.get(i).combFactory(truthTable);
            }
        }
    }

    public void setITMCells() {

        for (int i = 0; i < cells.size(); i++) {            
            Cell auxCell = cells.get(i);
            
            if(!auxCell.getInputs().isEmpty()) {
                String binTruthTable = CommonOps.getBinaryTruthTable(auxCell.getTruthTable(), auxCell.getInputs().size());
                String function = auxCell.getFunctions().get(0);
                int x = PowInt(2, auxCell.getInputs().size());
                int y = PowInt(2, auxCell.getOutputs().size());

                int[] itm = new int[x+1];

                // Set the array's column size
                itm[0] = y;

                int counter = 1;
                for(char c : binTruthTable.toCharArray()) {
                    itm[counter] = Character.getNumericValue(c);
                    counter++;
                }

                auxCell.setItm(itm);
            }
        }
    }

    public void setPTMCells(BigDecimal reliability) {

        BigDecimal[][] ptm;
        BigDecimal[][] itm2;
        
        for (int i = 0; i < cells.size(); i++) {
            Cell auxCell = cells.get(i);
            
            if(auxCell.getInputs().isEmpty()) {
                ptm = new BigDecimal[1][2];
                ptm[0][0] = reliability;
                ptm[0][1] = BigDecimal.ONE.subtract(reliability);
                itm2 = new BigDecimal[1][2];
                itm2[0][0] = BigDecimal.ONE;
                itm2[0][1] = BigDecimal.ZERO;                
            } else {
                int[] itm = auxCell.getItm();

                int x = PowInt(2, auxCell.getInputs().size());
                int y = PowInt(2, auxCell.getOutputs().size());

                ptm = new BigDecimal[x][y];
                itm2 = new BigDecimal[x][y];


                for (int j = 0; j < x; j++) {                                                 

                    if(itm[j+1] == 1) {
                        ptm[j][1] = reliability;
                        ptm[j][0] = BigDecimal.ONE.subtract(reliability);

                        itm2[j][1] = BigDecimal.ONE;
                        itm2[j][0] = BigDecimal.ZERO;
                    } else {
                        ptm[j][1] = BigDecimal.ONE.subtract(reliability);
                        ptm[j][0] = reliability;

                        itm2[j][1] = BigDecimal.ZERO;
                        itm2[j][0] = BigDecimal.ONE;
                    }
                }
            }
            
            auxCell.setPTM(ptm);
            auxCell.setItm2(itm2);
            
        }
    }

    public void setPTMCellByName(String cellName, BigDecimal reliability) {
        Cell cell = this.getCellByName(cellName);
        if(cell == null) {
            System.err.print("Cell: " + cellName + " Not Found!!!");
        } else {
            int[] itm = cell.getItm();

            BigDecimal[][] ptm;
            BigDecimal[][] itm2;
            
            if(cell.getInputs().isEmpty()) {            
                ptm = new BigDecimal[1][2];
                ptm[0][0] = reliability;
                ptm[0][1] = BigDecimal.ONE.subtract(reliability);
                itm2 = new BigDecimal[1][2];
                itm2[0][0] = BigDecimal.ONE;
                itm2[0][1] = BigDecimal.ZERO;                
            } else {            

                int x = PowInt(2, cell.getInputs().size());
                int y = PowInt(2, cell.getOutputs().size());

                ptm = new BigDecimal[x][y];
                itm2 = new BigDecimal[x][y];

                                
                for (int j = 0; j < x; j++) {

                    if(itm[j+1] == 1) {
                        ptm[j][1] = reliability;
                        ptm[j][0] = BigDecimal.ONE.subtract(reliability);

                        itm2[j][1] = BigDecimal.ONE;
                        itm2[j][0] = BigDecimal.ZERO;
                    } else {
                        ptm[j][1] = BigDecimal.ONE.subtract(reliability);
                        ptm[j][0] = reliability;

                        itm2[j][1] = BigDecimal.ZERO;
                        itm2[j][0] = BigDecimal.ONE;
                    }
                }
            }

            cell.setPTM(ptm);
            cell.setItm2(itm2);
        }
    }

    public void setPTMCells2(float reliability) {
        float[][] ptm;
        
        for (int i = 0; i < cells.size(); i++) {            
            Cell auxCell = cells.get(i);
            int[] itm = auxCell.getItm();
            


            if(auxCell.getInputs().isEmpty()) {
                ptm = new float[1][2];
                ptm[0][0] = reliability;
                ptm[0][1] = 1 - reliability;

            } else {

                int x = PowInt(2, auxCell.getInputs().size());
                int y = PowInt(2, auxCell.getOutputs().size());

                ptm = new float[x][y];

                for (int j = 0; j < x; j++) {

                    if(itm[j+1] == 1) {
                        ptm[j][1] = reliability;
                        ptm[j][0] = 1 - reliability;

                    } else {
                        ptm[j][1] = 1 - reliability;
                        ptm[j][0] = reliability;

                    }
                }
            }
            auxCell.setPtm2(ptm);                        
        }        
    }
    
    public void setPTMSonfCell(BigDecimal reliability) {
        
        String[] gates = new String[]{
            "NAND2X1",
            "NAND3X1",
            "NAND4X1",
            "INVX1",
            "NOR2X1",
            "NOR4X1",
            "AOI21X1"
        };
        
        for (int i = 0; i < gates.length; i++) {
            LeArquivo l = new LeArquivo();
            String arquivo = "SOnF-gates/" + gates[i] +".txt";
            
            System.out.println(arquivo);
            
            try {
                l.setConfiabilidade(reliability);
                l.Ler(arquivo, 1);
                l.InsereProbEntradas("50");
            } catch (IOException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }

            Grafo g = new Grafo(l.getListaTransistores(), l.getListaEntradas(), l.getListaNodos(), l.getConfiabilidade());

            getCellByName(gates[i]).setPtmSOnF(g.analiseSOnF());                        
            
        }        
        
    }

    // Old JavaScript Engine methods
    public void setTruthTables_OLD() throws ScriptException {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        for (int i = 0; i < cells.size(); i++) {

            switch(cells.get(i).getName()) {
                case "ZERO":
                case "_const0_":
                    cells.get(i).setTruthTable("0");
                    break;
                case "ONE":
                case "_const1_":
                    cells.get(i).setTruthTable("1");
                    break;
                default:
                    String function = cells.get(i).getFunctions().get(0);
                    function = function.split("=")[1];
                    function = function.replaceAll("\\Q*\\E", "&&");
                    function = function.replaceAll("\\Q+\\E", "||");

                    int size = cells.get(i).getInputs().size();

                    if(size > 15) {
                        cells.get(i).setTruthTable("-1");
                    } else {
                        int rows = (int) Math.pow(2,size);
                        ArrayList<Integer> temp = new ArrayList<>();
                        String s = "";
                        boolean result;


                        for (int k=0; k<rows; k++) {
                            temp.clear();
                            for (int j=size-1; j>=0; j--) {
                                temp.add((k/(int) Math.pow(2, j))%2);
                            }

                            for (int j = 0; j < cells.get(i).getInputs().size(); j++) {
                                engine.eval("var " + cells.get(i).getInputs().get(j) + "=" + Boolean.toString(temp.get(j) != 0) + ";");
                            }
                            result = (boolean)engine.eval(function + ";");
                            s = s + (result ? "1" : "0");
                        }
                        BigInteger truthTable = new BigInteger(s,2);

                        /*
                        Esse método é pra setar a HashMap de todos as céluals
                        */
                        cells.get(i).combFactory(s);
                        cells.get(i).setTruthTable(truthTable.toString(16));
                    }
                    break;
            }


        }
    }
    public void setITMCells_OLD() {

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        for (int i = 0; i < cells.size(); i++) {
            Cell auxCell = cells.get(i);

            if(!auxCell.getInputs().isEmpty()) {
                String function = auxCell.getFunctions().get(0);
                int x = PowInt(2, auxCell.getInputs().size());
                int y = PowInt(2, auxCell.getOutputs().size());

                int[] itm = new int[x+1];

                // Set the array's column size
                itm[0] = y;


                function = function.split("=")[1];
                function = function.replaceAll("\\Q*\\E", "&&");
                function = function.replaceAll("\\Q+\\E", "||");

                for (int j = 0; j < x; j++) {
                    BigInteger teste = new BigInteger("" + j);
                    //System.out.println(teste);
                    String binary = String.format("%0" + auxCell.getInputs().size() + "d", Integer.valueOf(Integer.toBinaryString(j)));




                    try {
                        for (int p = 0; p < auxCell.getInputs().size(); p++) {
                            int bit = Character.getNumericValue(binary.charAt(p));
                            String formula = "var " + auxCell.getInputs().get(p) + "=" + Boolean.toString(bit != 0) + ";";

                            engine.eval(formula);
                        }

                        boolean result = (boolean)engine.eval(function + ";");

                        if(result) {

                            itm[j+1] = 1;

                        } else {
                            itm[j+1] = 0;
                        }

                    } catch (ScriptException ex) {
                        Logger.getLogger(PTMOps.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                auxCell.setItm(itm);
            }
        }
    }
    public void setPTMCells_OLD(BigDecimal reliability) {

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        BigDecimal[][] ptm;
        BigDecimal[][] itm2;

        for (int i = 0; i < cells.size(); i++) {
            Cell auxCell = cells.get(i);

            if(auxCell.getInputs().isEmpty()) {
                System.out.println("Mirna!!");
                System.out.println(auxCell);
                ptm = new BigDecimal[1][2];
                ptm[0][0] = reliability;
                ptm[0][1] = BigDecimal.ONE.subtract(reliability);
                itm2 = new BigDecimal[1][2];
                itm2[0][0] = BigDecimal.ONE;
                itm2[0][1] = BigDecimal.ZERO;
            } else {
                String function = auxCell.getFunctions().get(0);
                int x = PowInt(2, auxCell.getInputs().size());
                int y = PowInt(2, auxCell.getOutputs().size());

                ptm = new BigDecimal[x][y];
                itm2 = new BigDecimal[x][y];


                function = function.split("=")[1];
                function = function.replaceAll("\\Q*\\E", "&&");
                function = function.replaceAll("\\Q+\\E", "||");

                for (int j = 0; j < x; j++) {

                    String binary = String.format("%0" + auxCell.getInputs().size() + "d", Integer.valueOf(Integer.toBinaryString(j)));


                    try {
                        for (int p = 0; p < auxCell.getInputs().size(); p++) {
                            int bit = Character.getNumericValue(binary.charAt(p));
                            String formula = "var " + auxCell.getInputs().get(p) + "=" + Boolean.toString(bit != 0) + ";";

                            engine.eval(formula);
                        }

                        boolean result = (boolean)engine.eval(function + ";");

                        if(result) {

                            ptm[j][1] = reliability;
                            ptm[j][0] = BigDecimal.ONE.subtract(reliability);

                            itm2[j][1] = BigDecimal.ONE;
                            itm2[j][0] = BigDecimal.ZERO;

                        } else {
                            ptm[j][1] = BigDecimal.ONE.subtract(reliability);
                            ptm[j][0] = reliability;

                            itm2[j][1] = BigDecimal.ZERO;
                            itm2[j][0] = BigDecimal.ONE;
                        }

                    } catch (ScriptException ex) {
                        Logger.getLogger(PTMOps.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            auxCell.setPTM(ptm);
            auxCell.setItm2(itm2);

        }
    }
    public void setPTMCellByName_OLD(String cellName, BigDecimal reliability) {
        Cell cell = this.getCellByName(cellName);
        if(cell == null) {
            System.err.print("Cell: " + cellName + " Not Found!!!");
        } else {

            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            BigDecimal[][] ptm;
            BigDecimal[][] itm2;

            if(cell.getInputs().isEmpty()) {
                ptm = new BigDecimal[1][2];
                ptm[0][0] = reliability;
                ptm[0][1] = BigDecimal.ONE.subtract(reliability);
                itm2 = new BigDecimal[1][2];
                itm2[0][0] = BigDecimal.ONE;
                itm2[0][1] = BigDecimal.ZERO;
            } else {
                String function = cell.getFunctions().get(0);
                int x = PowInt(2, cell.getInputs().size());
                int y = PowInt(2, cell.getOutputs().size());

                ptm = new BigDecimal[x][y];
                itm2 = new BigDecimal[x][y];


                function = function.split("=")[1];
                function = function.replaceAll("\\Q*\\E", "&&");
                function = function.replaceAll("\\Q+\\E", "||");

                for (int j = 0; j < x; j++) {

                    String binary = String.format("%0" + cell.getInputs().size() + "d", Integer.valueOf(Integer.toBinaryString(j)));


                    try {
                        for (int p = 0; p < cell.getInputs().size(); p++) {
                            int bit = Character.getNumericValue(binary.charAt(p));
                            String formula = "var " + cell.getInputs().get(p) + "=" + Boolean.toString(bit != 0) + ";";

                            engine.eval(formula);
                        }

                        boolean result = (boolean)engine.eval(function + ";");

                        if(result) {

                            ptm[j][1] = reliability;
                            ptm[j][0] = BigDecimal.ONE.subtract(reliability);

                            itm2[j][1] = BigDecimal.ONE;
                            itm2[j][0] = BigDecimal.ZERO;

                        } else {
                            ptm[j][1] = BigDecimal.ONE.subtract(reliability);
                            ptm[j][0] = reliability;

                            itm2[j][1] = BigDecimal.ZERO;
                            itm2[j][0] = BigDecimal.ONE;
                        }

                    } catch (ScriptException ex) {
                        Logger.getLogger(PTMOps.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            cell.setPTM(ptm);
            cell.setItm2(itm2);
        }
    }
    public void setPTMCells2_OLD(float reliability) {

        System.out.println("PAPAI ===> " + reliability);
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        for (int i = 0; i < cells.size(); i++) {
            Cell auxCell = cells.get(i);
            String function = auxCell.getFunctions().get(0);
            int x = PowInt(2, auxCell.getInputs().size());
            int y = PowInt(2, auxCell.getOutputs().size());

            float[][] ptm = new float[x][y];

            function = function.split("=")[1];
            function = function.replaceAll("\\Q*\\E", "&&");
            function = function.replaceAll("\\Q+\\E", "||");

            for (int j = 0; j < x; j++) {

                if(!("ZERO".equals(auxCell.getName()) || "ONE".equals(auxCell.getName()))) {

                    String binary = String.format("%0" + auxCell.getInputs().size() + "d", Integer.valueOf(Integer.toBinaryString(j)));


                    try {
                        for (int p = 0; p < auxCell.getInputs().size(); p++) {
                            int bit = Character.getNumericValue(binary.charAt(p));
                            String formula = "var " + auxCell.getInputs().get(p) + "=" + Boolean.toString(bit != 0) + ";";

                            engine.eval(formula);
                        }


                        boolean result = (boolean)engine.eval(function + ";");

                        if(result) {

                            ptm[j][1] = reliability;
                            ptm[j][0] = 1 - reliability;

                        } else {
                            ptm[j][1] = 1 - reliability;
                            ptm[j][0] = reliability;

                        }

                    } catch (ScriptException ex) {
                        Logger.getLogger(PTMOps.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            auxCell.setPtm2(ptm);
        }
    }
    
    public void teste(){
        
        for (int i = 0; i < cells.size(); i++) {
            System.out.println(cells.get(i).getName());
            System.out.println("### float ####");
            CommonOps.matrixPrint(cells.get(i).getPtm2());
            System.out.println("### big_decimal ####");
            CommonOps.matrixPrint(cells.get(i).getPTM());
        }
    } 
}
