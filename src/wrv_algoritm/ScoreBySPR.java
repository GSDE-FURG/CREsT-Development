/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrv_algoritm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import ops.SPROps;
import signalProbability.ProbCircuit;
import signalProbability.ProbGate;
import signalProbability.ProbSignal;
import tool.Terminal;

/**
 *
 * @author Marcio
 */
public class ScoreBySPR implements RunScore {

    private static final char CHAR_ONE = '1';
    private final BigDecimal q;
    private final ProbCircuit circuit;
    private final ArrayList<ProbSignal> inputs;
    private static int numberExecution;

    public ScoreBySPR(BigDecimal q) {
        this.q = q;
        Terminal.getInstance().getCellLibrary().setPTMCells(q);
        circuit = Terminal.getInstance().getProbCircuit();
        circuit.setPTMReliabilityMatrix();
        inputs = circuit.getProbInputs(); 
    }

    public void setImprovementGates(List<ProbGate> improvementGates, BigDecimal q) {
        if (!improvementGates.isEmpty()) {
            for (ProbGate probGate: improvementGates) {
                probGate.setGateReliability(q); 
                circuit.setPTMReliabilityMatrix();
            }                        
        }        
    }

    public static int getNumberExecution() {
        return numberExecution;
    }    
    
    @Override
    public ScoreVector execute(InputVector inputVector) {
        String vector = inputVector.getBinaryString();
        //System.out.println("Sinais de entrada: " + inputs);
        //percorre os bits do vetor de entrada para configurar a entrada
        for (int i = 0; i < vector.length(); i++) {
            ProbSignal probSignal = inputs.get(i);
            if (vector.charAt(i) == CHAR_ONE) {
                probSignal.setProbMatrix(new BigDecimal[][]{
                    {BigDecimal.ZERO, BigDecimal.ZERO},
                    {BigDecimal.ZERO, BigDecimal.ONE}
                });
            } else {
                probSignal.setProbMatrix(new BigDecimal[][]{
                    {BigDecimal.ONE, BigDecimal.ZERO},
                    {BigDecimal.ZERO, BigDecimal.ZERO}
                });
            }
        }
        BigDecimal score = SPROps.getSPRReliability(circuit);
        ScoreVector scoreVector = new ScoreVector(inputVector, score);
        numberExecution++;
        return scoreVector;
    }
}
