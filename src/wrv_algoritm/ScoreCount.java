/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrv_algoritm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import signalProbability.ProbCircuit;
import signalProbability.ProbGate;
import signalProbability.ProbGateLevel;
import signalProbability.ProbSignal;
import tool.Terminal;

/**
 *
 * @author mdorr
 */
public class ScoreCount implements RunScore {

    private static final char CHAR_ONE = '1';
    private final ProbCircuit circuit;
    private final ArrayList<ProbSignal> inputs;
    Set<ProbGate> visitedGates;
    ArrayList<ProbGateLevel> probGateLevels;
    ArrayList<ProbSignal> listFanouts;
    Map<ProbGate, Integer> countGate;
    private int cnc;
    private static int numberExecution;

    public ScoreCount() {
        circuit = Terminal.getInstance().getProbCircuit();
        inputs = circuit.getProbInputs();
        probGateLevels = circuit.getProbGateLevels();
        listFanouts = circuit.getFanouts();
        countGate = new HashMap<>();
    }

    public static int getNumberExecution() {
        return numberExecution;
    }

    @Override
    public ScoreVector execute(InputVector inputVector) {
        visitedGates = new HashSet<>();
        cnc = 0;
        String vector = inputVector.getBinaryString();
        //configura o vetor de entrada
        for (int i = 0; i < vector.length(); i++) {
            ProbSignal probSignal = inputs.get(i);
            if (vector.charAt(i) == CHAR_ONE) {
                probSignal.setLogicValue(true);
                probSignal.setFol(true);
            } else {
                probSignal.setLogicValue(false);
                probSignal.setFol(false);
            }
        }

        //atualiza a saída dos gates a partir do vetor de entrada
        for (int i = 0; i < probGateLevels.size(); i++) {
            ProbGateLevel pGateLevel = probGateLevels.get(i);
            for (int j = 0; j < pGateLevel.getProbGates().size(); j++) {
                ProbGate pGate = pGateLevel.getProbGates().get(j);
                ArrayList<Boolean> comb = new ArrayList<>();
                for (ProbSignal probSignal : pGate.getpInputs()) {
                    comb.add(probSignal.getLogicValue());
                }
                for (ProbSignal probSignal : pGate.getpOutputs()) {                    
                    probSignal.setLogicValue(pGate.getType().getCombination(comb));
                    probSignal.setFol(pGate.getType().getCombination(comb));
                }
            }
        }
        //percorre o circuito a partir das saídas e calcula o score de criticalidade para o vetor de entrada
        for (ProbSignal po : circuit.getProbOutputs()) {
            ProbGate gatePO = po.getPOrigin();
            countCriticalGate(gatePO);
        }
        ScoreVector sc = new ScoreVector(inputVector, new BigDecimal(cnc));
        countGate =  countGate.entrySet().
                stream().
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        //System.out.println(countGate);
        numberExecution++;
        return sc;
    }

    public void countCriticalGate(ProbGate gate) {
        if (!visitedGates.add(gate)) {
            return;
        }
        boolean criticalSignal = true;
        
        //este trecho comentado considera os fanouts do circuito
        //precisa de mais análise
        
//        for (ProbSignal outSignal : gate.getpOutputs()) {            
//            if (listFanouts.contains(outSignal)) {                
//                ProbGate gateOrigin = outSignal.getPOrigin();
//                int i;
//                for (i = 0; i < probGateLevels.size(); i++) {
//                    ArrayList<ProbGate> pbGates = probGateLevels.get(i).getProbGates();
//                    if (pbGates.contains(gateOrigin)) {
//                        break;
//                    }
//                }
//                outSignal.setFol(!outSignal.getFol());
//                for (int c = i + 1; c < probGateLevels.size(); c++) {
//                    ArrayList<ProbGate> pbGates = probGateLevels.get(c).getProbGates();
//                    for (ProbGate pbGate : pbGates) {
//                        ArrayList<Boolean> comb = new ArrayList<>();
//                        for (ProbSignal inSignal : pbGate.getpInputs()) {                            
//                            comb.add(inSignal.getFol());
//                        }
//                        for (ProbSignal probSignal : pbGate.getpOutputs()) {
//                            probSignal.setFol(pbGate.getType().getCombination(comb));
//                        }
//                    }
//                }
//                for (ProbSignal po : circuit.getProbOutputs()) {
//                    if (po.getFol() == po.getLogicValue()) {
//                        criticalSignal = false;
//                    } else {
//                        criticalSignal = true;
//                        break;
//                    }
//                }
//            }
//        }
//        if (!criticalSignal) {
//            return;
//        }
        cnc++;
        if (countGate.containsKey(gate)) {
            countGate.put(gate, countGate.get(gate) + 1);
        } else {
            countGate.put(gate, 1);
        }
        ArrayList<ProbSignal> signals = gate.getpInputs();  
        String type = gate.getType().getName();
        for (ProbSignal signal : signals) {
            if (signal.getPOrigin() == null) {
                continue;
            }            
            ArrayList<ProbSignal> copySignals = new ArrayList<>(signals);
            copySignals.remove(signal);
            //verifica se signal é crítico
            //analisando os outros sinais de entrada da porta lógica
            for (ProbSignal s : copySignals) {                
                if (type.toUpperCase().contains("AND") && !s.getLogicValue() || type.toUpperCase().contains("OR") && s.getLogicValue()) {
                    criticalSignal = false;
                    break;
                }
            }
            if (criticalSignal) {
                countCriticalGate(signal.getPOrigin());
            } else {
                criticalSignal = true;
            }
        }
    }

    @Override
    public void setImprovementGates(List<ProbGate> improvementGates, BigDecimal q) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
