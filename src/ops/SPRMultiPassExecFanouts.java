package ops;

import java.io.IOException;
import java.io.StringBufferInputStream;
import java.math.BigDecimal;

import javax.script.ScriptException;

import datastructures.CellLibrary;

import tool.Terminal;

import signalProbability.ProbCircuit;
import tool.Commands;


/****  new libraries*/

//package ops;

import datastructures.CellLibrary;
import datastructures.Circuit;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import levelDatastructures.LevelCircuit;

import static ops.CommonOps.getKronecker;
import static ops.CommonOps.getMultipliedMatrix;
import readers.MappedVerilogReader;

import signalProbability.ProbCircuit;
import signalProbability.ProbGate;
import signalProbability.ProbGateLevel;
import signalProbability.ProbSignal;

/**   */

/**
 *
 * @author Clayton
 */
class main_SPRMP_Exec {

    public static void main(final String[] args) throws Exception {
        System.out.println("\n\nHello world, working");
        String circuitPath = "c17v3_fritz.v";
        String reliability = "0.999";
        String mode = "big_decimal";
        CommonOps commonOps = new CommonOps();
        //String library = CommonOps.getWorkPath(this) + "abc" + File.separator + 
        //"cadence.genlib";
        String library = "cadence.genlib";

        final SPRMultiPassExecFanouts objeto = new SPRMultiPassExecFanouts(reliability, mode, circuitPath, library);

        objeto.getReliabilitySPR_MP();

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
    
    private CellLibrary cellLibrary;
    private Circuit circuit;
    private LevelCircuit levelCircuit;
    private ProbCircuit probCircuit;


    public SPRMultiPassExecFanouts(String reliabilty, String mode, String circuitFilePath, String genlib) {

        this.reliability = reliabilty;
        this.circuitFilePath = "abc\\" + circuitFilePath;
        this.mode = mode;
        this.genlib = genlib;
        this.genlibPATH = "abc\\" + this.genlib;
        

    }
    
    public void initLevelCircuit(){    
        if(this.circuit != null) {
            this.levelCircuit = new LevelCircuit(circuit);      
        }
        else
        {
            System.out.println("Circuit is null!!");
        }
    }
    
    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public void getReliabilitySPR_MP()
            throws Exception {

        System.out.println("SPRMP Development : " + this.circuitFilePath + "   -   "  + this.reliability);
        final long startTime = System.currentTimeMillis();
        
 
        /*CellLibrary*/
        CellLibrary cellLib = new CellLibrary();
        cellLib.initLibrary(this.genlibPATH);
        /*new*/
        
         /*Read Verilog*/
        MappedVerilogReader verilog_circuit = new MappedVerilogReader(this.circuitFilePath, cellLib);
        System.out.println("Circuito : "+ verilog_circuit.getCircuit());
        
        
        /*Circuit linked to verilog_circuit*/
         this.circuit = verilog_circuit.getCircuit();
         System.out.println(this.circuit.getGates());
       
        
        //Circuit circuit = new Circuit();
        //ProbCircuit pCircuit = new ProbCircuit(circuit);
        /*Circuit*/
            
        String result = "";
        
        
        
        
        
        /*
        //CellLibrary cellLib = Terminal.getInstance().getCellLibrary();
        //ProbCircuit pCircuit = Terminal.getInstance().getProbCircuit();        
        
        
        pCircuit.clearProbSignalsMatrix();
        cellLib.setPTMCells2(Float.valueOf(reliability));
        cellLib.setPTMCells(new BigDecimal(reliability));
        pCircuit.setPTMReliabilityMatrix();
        pCircuit.setProbSignalStates(false);


        
        cmd.PrintCircuitGates();
    
        //cmd.getReliabilitySPR(reliability, "big_decimal");

            cmd.getReliability_SPRMP(this.reliability, this.mode);


           // cmd.getReliabilitySPR(reliability, this.mode);

           final SPRMP_CLASS_DEV_MODE teste = new SPRMP_CLASS_DEV_MODE();

           teste.getSPRMultiPassReliaiblity_mode(pCircuit);
            */
          
    }
}



class SPRMP_CLASS_DEV_MODE {
    
    static int[][] index = {{0,0},{0,1},{1,0},{1,1}};
    public static int COUNTER = 0;
    public static BigDecimal VALUECOUNTER = BigDecimal.ZERO;
    public static ArrayList<ProbSignal> FANOUTS;


 public static BigDecimal[][] getSignalMatrix_mode(BigDecimal[][] matrix, int[] itm) {
        
        BigDecimal[][] signalMatrix = new BigDecimal[2][2];
        
        BigDecimal correct0 = BigDecimal.ZERO;
        BigDecimal incorrect0 = BigDecimal.ZERO;
        BigDecimal correct1 = BigDecimal.ZERO;
        BigDecimal incorrect1 = BigDecimal.ZERO;
        
        for (int i = 1; i < itm.length; i++) {
            if(itm[i] == 0) {                
                correct0 = correct0.add(matrix[i-1][0]);                
                incorrect1 = incorrect1.add(matrix[i-1][1]);
            } else {                
                incorrect0 = incorrect0.add(matrix[i-1][0]);                
                correct1 = correct1.add(matrix[i-1][1]);
            }
        }
        
        signalMatrix[0][0] = correct0;
        signalMatrix[0][1] = incorrect1;
        signalMatrix[1][0] = incorrect0;
        signalMatrix[1][1] = correct1;
        
        return signalMatrix;
    }
    
    
 public static BigDecimal getSPRReliability(ProbCircuit pCircuit, ArrayList<ProbSignal> fanouts) {
                
        BigDecimal value = BigDecimal.ONE;
        BigDecimal probState;
        BigDecimal flag;
        int[] indexes;
        int a;
        int b;
        
        
        /**
         * Iteração entre todos os níveis de Gate do Circuito
         */
        for (int i = 0; i < pCircuit.getProbGateLevels().size(); i++) {                        
            
            System.out.println("------ Nivel do Gate : " + i);
            ProbGateLevel pGateLevel = pCircuit.getProbGateLevels().get(i);
            
            /**
             * Iteração sobre todos as Gates do nível de Gate
             */
            for (int j = 0; j < pGateLevel.getProbGates().size(); j++) {
                

                ProbGate pGate = pGateLevel.getProbGates().get(j);

                System.out.println("    ------ Gate Atual : " + pGate );
                
                
                /**
                 * Pega o primeiro sinal de entrada da Gate
                 */
                ProbSignal aSignal = pGate.getpInputs().get(0); 

                System.out.println("        ------ Sinal A : " +  pGate.getpInputs().get(0) + "    Sinal B : "+ pGate.getpInputs().get(1));

                BigDecimal[][] matrix = aSignal.getProbMatrix();  
                    
               
                BigDecimal[][] fooMatrix;                    

                int aState = aSignal.getCurrentState();
                
                System.out.println("                         - Estado Matriz[a][b] : "+ aSignal.getCurrentState() + "          -          " +aSignal.getSignalValues());


                /**
                 * State = 4 quer dizer que o sinal NÃO é fanout
                 */
                if(aState == 4) {
                    System.out.println("     ++Sinal normal : "+ aState);
                    /**
                     * Se não é um inversor ou buffer ou...
                     */
                    if(pGate.getpInputs().size() > 1) {

                        for (int k = 1; k < pGate.getpInputs().size(); k++) {

                            
                            ProbSignal bSignal = pGate.getpInputs().get(k);
                            int bState = bSignal.getCurrentState();
                            System.out.println("Sinal k: " + k);
                            System.out.println("bSignal : " + pGate.getpInputs().get(k));

                            if(bState == 4) {                                
                                matrix = getKronecker(matrix, bSignal.getProbMatrix());
                            } else {
                                indexes = index[bState];
                                a = indexes[0];
                                b = indexes[1];

                                System.out.println("HERE");
                                flag = bSignal.getProbMatrix()[a][b];   
                                
                                if(flag.compareTo(BigDecimal.ZERO) == 0) {                                        
                                    return new BigDecimal(fanouts.indexOf(bSignal)).negate();
                                }

                                fooMatrix = new BigDecimal[][]{{BigDecimal.ZERO,BigDecimal.ZERO},{BigDecimal.ZERO,BigDecimal.ZERO}};
                                fooMatrix[a][b] = BigDecimal.ONE;
                                
                                matrix = getKronecker(matrix, fooMatrix);
                            }                                                                                           
                        }
                    }
                    /**
                     * aSignal É fanout...
                     */
                } else {

                    System.out.println("     ++Sinal fanout : "+ index);

                    indexes = index[aState];                        
                    a = indexes[0];
                    b = indexes[1];
                    
                    System.out.println("Erro bem aqui matriz de sinal : " + aSignal.getId());

                    System.out.println("Checkando :" + aSignal.getProbMatrix()[0][0]);
      
                    probState = aSignal.getProbMatrix()[a][b]; //AQUI

                    if(probState.compareTo(BigDecimal.ZERO) == 0) {
                        return new BigDecimal(fanouts.indexOf(aSignal)).negate();
                    }

                    fooMatrix = new BigDecimal[][]{{BigDecimal.ZERO,BigDecimal.ZERO},{BigDecimal.ZERO,BigDecimal.ZERO}};
                    fooMatrix[a][b] = BigDecimal.ONE;
                    matrix = fooMatrix;
                    

                    if(pGate.getpInputs().size() > 1) { 

                        for (int k = 1; k < pGate.getpInputs().size(); k++) {
                            ProbSignal bSignal = pGate.getpInputs().get(k);
                            int bState = bSignal.getCurrentState();

                            if(bState == 4) {
                                matrix = getKronecker(matrix, bSignal.getProbMatrix());
                            } else {
                                indexes = index[bState];
                                a = indexes[0];
                                b = indexes[1];
                                flag = bSignal.getProbMatrix()[a][b];                                                                                                            

                                if(flag.compareTo(BigDecimal.ZERO) == 0) {
                                    //return -1f;
                                    //return new BigDecimal(fanouts.indexOf(bSignal)).multiply(new BigDecimal("-1"));
                                    return new BigDecimal(fanouts.indexOf(bSignal)).negate();
                                }

                                fooMatrix = new BigDecimal[][]{{BigDecimal.ZERO,BigDecimal.ZERO},{BigDecimal.ZERO,BigDecimal.ZERO}};
                                fooMatrix[a][b] = BigDecimal.ONE;
                                matrix = getKronecker(matrix, fooMatrix);
                            }                                                                                           
                        }
                    }                        
                }                   
                matrix = getMultipliedMatrix(matrix, pGate.getReliabilityMatrix());

                matrix = getSignalMatrix_mode(matrix, pGate.getType().getItm());

                pGate.getpOutputs().get(0).setProbMatrix(matrix);
                                                
            }           
        }
                
        
        for (ProbSignal pSignal : pCircuit.getProbOutputs()) {                                    
            
            if(pSignal.getCurrentState() == 4) {
                BigDecimal correct0 = pSignal.getProbMatrix()[0][0];
                BigDecimal correct1 = pSignal.getProbMatrix()[1][1];               
                value = value.multiply(correct0.add(correct1)).setScale(13, RoundingMode.HALF_UP);;
            } else {
                /**
                 * Se caiu aqui é pq a saída é um fanout
                 * assim o sendo, se os estados de sinal correntes forem ou 1 ou 0
                 * INCORRETOS, o valor da passada deverá ser 0 (Matheus - 28/11/2018)
                 */
                if(pSignal.getCurrentState() == 1 || pSignal.getCurrentState() == 2) {
                    return BigDecimal.ZERO;
                }
            }           
            
        }
        
        for (ProbSignal fanout : fanouts) {
            indexes = index[fanout.getCurrentState()];
            a = indexes[0];
            b = indexes[1];                       
            value = value.multiply(fanout.getProbMatrix()[a][b]).setScale(13, RoundingMode.HALF_UP);
        }       
        
        FANOUTS = fanouts;
        
//        COUNTER++;        
//        VALUECOUNTER = VALUECOUNTER.add(value);
        

        
        return value;        
                       
    }        
    

public static BigDecimal getSPRMultiPassReliaiblity_mode(ProbCircuit pCircuit) {
                        
        BigDecimal value;

        ArrayList<ProbSignal> fanouts = pCircuit.getFanouts();
        ArrayList<ProbSignal> newFanouts = new ArrayList<>();
        
        for (int i = 0; i < (fanouts.size()); i++) {
            System.out.println("Fanout : "+ i + "   -   " + fanouts.get(i));
            
            newFanouts.add(fanouts.get(i));            
        }
                
        ProbSignal pSignal = newFanouts.get(0);
        
        value = getMultiPass(pCircuit, newFanouts, pSignal, 0);
        
        
        return value;
    }
    
    
private static BigDecimal getMultiPass(ProbCircuit pCircuit, ArrayList<ProbSignal> fanouts, ProbSignal pSignal, int currentIndex) {
                
        BigDecimal value = BigDecimal.ZERO;
        BigDecimal flag;
        int nextIndex = currentIndex+1;
        int[] states = pSignal.getStates();
        
        if(nextIndex == fanouts.size()) {

            for (int j = 0; j < states.length; j++) {
                
                pSignal.setCurrentState(states[j]);
                flag = getSPRReliability(pCircuit, fanouts);

                if(flag.compareTo(BigDecimal.ZERO) > 0) {
                    value = value.add(flag);
                } else {
                    if(flag.compareTo(BigDecimal.ZERO) < 0) {
                        int foo = flag.intValue();
                        foo = foo * -1;
                        if(fanouts.indexOf(pSignal) != foo) {
                            return flag;
                        }
                    }                                        
                }
            }
        } else {            
            ProbSignal next = fanouts.get(nextIndex);
            for (int j = 0; j < states.length; j++) {
                pSignal.setCurrentState(states[j]);
                flag = getMultiPass(pCircuit, fanouts, next, nextIndex);
                
                if(flag.compareTo(BigDecimal.ZERO) > 0) {
                    value = value.add(flag);
                } else {
                    if(flag.compareTo(BigDecimal.ZERO) < 0) {
                        int foo = flag.intValue();
                        foo = foo * -1;
                        if(fanouts.indexOf(pSignal) != foo) {                        
                            return flag;
                        }  
                    }                    
                }                               
            }
        }
        
        return value;
    }
    
    
 }