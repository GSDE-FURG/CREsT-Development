/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrv_algorithm_OLD;

import datastructures.Circuit;
import datastructures.Gate;
import datastructures.InputVector;
import datastructures.Signal;
import ops.CommonOps;
import ops.SPROps;
import signalProbability.ProbCircuit;
import signalProbability.ProbGate;
import signalProbability.ProbSignal;
import tool.Commands;
import tool.Terminal;
import writers.VerilogWriter;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Marcio
 */
public abstract class Utils {
    
    public static void createSubCircuits() {
        Circuit circuit = Terminal.getInstance().getCircuit();
        ArrayList<Signal> outputs = circuit.getOutputs();
        //cria sub-circuito para cada saída PO
        //quando existir apenas uma saída, o sub-circuito não é criado, pois já é o próprio circuito
        if (outputs.size() > 1) {
            for (Signal output : outputs) {
                Set<Signal> setSignalsSubCircuit = new HashSet<>();
                setSignalsSubCircuit.add(output);
                Set<Gate> setGatesSubCircuit = new HashSet<>();
                //cria um sub-circuito
                Circuit subCircuit = new Circuit();
                //define o nome do sub-circuito igual ao circuito + id da saída
                subCircuit.setName(circuit.getName() + "_" + output.getId().replace("\\", ""));
                Gate gate = output.getSignalOrigin();
                if (gate == null) {
                    continue;
                } else {
                    setGatesSubCircuit.add(gate);
                    //lista próximos gates a ser incluídos no sub-circuito
                    Set<Gate> listNextGates = new HashSet<>();
                    listNextGates.add(gate);
                    //System.out.println("Saída: " + output);
                    while (!listNextGates.isEmpty()) {
                        //System.out.println("ListNextGates: " + listNextGates.size());
                        Set<Gate> listGates = new HashSet<>();
                        Set<Signal> listSignals = new HashSet<>();
                        //lista os sinais a partir das portas lógicas
                        for (Gate nextGate : listNextGates) {
                            listSignals.addAll(nextGate.getInputs());
                            //System.out.println("ListSignals: " + listSignals.size());
                            setSignalsSubCircuit.addAll(nextGate.getInputs());
                        }
                        //lista as portas lógicas a partir dos sinais
                        for (Signal signal : listSignals) {
                            if (signal.getSignalOrigin() != null) {
                                listGates.add(signal.getSignalOrigin());
                                //System.out.println("ListGates: " + listGates.size());
                                setGatesSubCircuit.add(signal.getSignalOrigin());
                            }
                        }
                        listNextGates = listGates;
                    }
                    ArrayList<Signal> signalsSubCircuit = new ArrayList<>(setSignalsSubCircuit);
                    ArrayList<Gate> gatesSubCircuit = new ArrayList<>(setGatesSubCircuit);
                    subCircuit.setSignals(signalsSubCircuit);
                    subCircuit.setGates(gatesSubCircuit);
                }
                List<Signal> outsCorretion = new ArrayList<>();
                for (Signal out : subCircuit.getOutputs()) {
                    if (out != output) {
                        outsCorretion.add(out);
                        out.getSignalDestiny().remove(null);
                    }
                }
                //FIXME deixar o local pra onde vão os arquivos mais dinâmico
                String filename = "abc/" + circuit.getName() + "/" + subCircuit.getName() + ".v";
                VerilogWriter writer = new VerilogWriter(filename, subCircuit);                
                for (Signal o : outsCorretion) {
                    o.getSignalDestiny().add(0, null);
                }
            }
        }
    }


    public static Map<ProbGate, BigDecimal> orderedGatesByWRV(InputVector inputVector, String q, String newQ) {
        long init = System.currentTimeMillis();
        ProbCircuit pCircuit = Terminal.getInstance().getProbCircuit();
        System.out.println(pCircuit.getName());
        Map<ProbGate, BigDecimal> gatesReliability = new HashMap<>();
        RunScore runScore = new ScoreBySPR(new BigDecimal(q));
        //System.out.println("Portas;R;MTBF");
        for (int k = 0; k < pCircuit.getProbGates().size(); k++) {
            //pCircuit.getProbGates().get(k).setGateReliability(getReliabilityTMR(q));
            pCircuit.getProbGates().get(k).setGateReliability(new BigDecimal(newQ));
            pCircuit.setPTMReliabilityMatrix();
            ScoreVector sc = runScore.execute(inputVector);
            BigDecimal mtbf = CommonOps.getMTBF(sc.getScore()).setScale(2, RoundingMode.HALF_UP);            
            gatesReliability.put(pCircuit.getProbGates().get(k), mtbf);
            pCircuit.getProbGates().get(k).setGateReliability(null);
        }
        //mapa é ordenado pelo valor de confiabilidade
        Map<ProbGate, BigDecimal> orderedGates = gatesReliability.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        //List<ProbGate> listOrderedGates = new ArrayList<>(orderedGates.keySet());
        long end = System.currentTimeMillis();
        long time = end - init;
        System.out.println("Tempo de ordenação: " + time);
        System.out.println(orderedGates);
        return orderedGates;
    }

    public static BigDecimal getReliabilityTMR(String r) {
        BigDecimal rModule = new BigDecimal(r);
        //cálculo tmr considerando votador ideal
        //r = 3 * modulo^2 - 2 * modulo^3
        BigDecimal rTMR = rModule.pow(2).multiply(new BigDecimal(3)).subtract(rModule.pow(3).multiply(new BigDecimal(2)));
        return rTMR;
    }

    public static Map<ProbGate, BigDecimal> getOrderedGates(String q, String newQ) {
        long init = System.currentTimeMillis();
        ProbCircuit pCircuit = Terminal.getInstance().getProbCircuit();
        Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal(q));
        Map<ProbGate, BigDecimal> gatesReliability = new HashMap<>();
        //System.out.println("Portas;MTBF");
        for (int k = 0; k < pCircuit.getProbGates().size(); k++) {
            pCircuit.setDefaultProbSourceSignalMatrix();
            //pCircuit.getProbGates().get(k).setGateReliability(getReliabilityTMR(q));
            pCircuit.getProbGates().get(k).setGateReliability(new BigDecimal(newQ));
            pCircuit.setPTMReliabilityMatrix();
            BigDecimal reliability = SPROps.getSPRReliability(pCircuit);
            BigDecimal mtbf = CommonOps.getMTBF(reliability).setScale(2, RoundingMode.HALF_UP);
            //System.out.println(pCircuit.getProbGates().get(k).getId() + ";" + reliability + ";" + mtbf);            
            gatesReliability.put(pCircuit.getProbGates().get(k), mtbf);
            pCircuit.getProbGates().get(k).setGateReliability(null);            
        }
        Map<ProbGate, BigDecimal> orderedGates = gatesReliability.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                //.sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        //List<ProbGate> listOrderedGates = new ArrayList<>(orderedGates.keySet());
        long end = System.currentTimeMillis();
        long time = end - init;
        System.out.println("Time;" + time);
        System.out.println(orderedGates);
        return orderedGates;
    }

    public static void getReliabilityByImprovementGate(List<ProbGate> orderedGates, String q, String newQ) {
        long init = System.currentTimeMillis();
        ProbCircuit pCircuit = Terminal.getInstance().getProbCircuit();
        Terminal.getInstance().getCellLibrary().setPTMCells(new BigDecimal(q));
        pCircuit.clearProbSignalsMatrix();
        pCircuit.setDefaultProbSourceSignalMatrix();
        pCircuit.setPTMReliabilityMatrix();
        //List<ProbGate> listGates = new ArrayList<>(orderedGates);
        System.out.println("Improved Gates;R (More critical order);MTBF (More critical order)");
        System.out.println("0;" + SPROps.getSPRReliability(pCircuit) + ";" + CommonOps.getMTBF(SPROps.getSPRReliability(pCircuit)).intValue());
        //Collections.shuffle(listGates);
        List<ProbGate> listProtectedGates = new ArrayList<>();
        for (ProbGate gate : orderedGates) {
            listProtectedGates.add(gate);
            gate.setGateReliability(new BigDecimal(newQ));
            pCircuit.setPTMReliabilityMatrix();
            BigDecimal r = SPROps.getSPRReliability(pCircuit);
            System.out.println(listProtectedGates + ";"
                    + r + ";"
                    + CommonOps.getMTBF(r).intValue());
        }
        long end = System.currentTimeMillis();
        long time = end - init;
        System.out.println("Time: " + time);
    }

    public static void getAreaCostWithTMR(Map<ProbGate, BigDecimal> orderedGates) {
        int numberVoters;
        System.out.println("Gates;Votadores;Total de Gates;Custo de Área (%)");
        List<ProbGate> listGates = new ArrayList<>(orderedGates.keySet()); 
        for (int i = 0; i < listGates.size(); i++) {
            numberVoters = 0;
            for (ProbGate probGate : listGates.subList(0, i + 1)) {
                //System.out.println(probGate.getId());
                List<ProbSignal> probSignals = probGate.getpOutputs();
                for (ProbSignal probSignal : probSignals) {
                    if (probSignal == null || !listGates.subList(0, i + 1).containsAll(probSignal.getPDestiny())) {
                        numberVoters++;
                        break;
                    }
                }
            }
            int twiceGates = (i + 1) * 2;
            int totalGates = listGates.size() + twiceGates + numberVoters;
            double cost = ((double) (totalGates - listGates.size()) / listGates.size()) * 100.0;
            System.out.println(String.format("%d;%d;%d;%.2f", i + 1, numberVoters, totalGates, cost));
        }

        //tenta diminuir o número de votadores
        //proteção de 20%
//        int twenty = (int) (orderedGates.size() * 0.2);
//        List<ProbGate> subList = listGates.subList(0, twenty);
//        System.out.println(orderedGates.get(subList.get(twenty - 1)));
    }
    
    public static List<String> getCriticalGates(String s, String q, String newQ, Map<String, String> vectors, int n) {
        File dir = new File(System.getProperty("user.dir") + "/abc/" + s);
        File[] files = dir.listFiles();
        Commands cmd = new Commands();
        Map<String, BigDecimal> totalByOut = new HashMap<>();
        Map<String, Integer> countGates = new HashMap<>();
        for (File file : files) {
            try {                
                if (!vectors.containsKey(file.getName().substring(0, file.getName().length() - 2))) {
                    continue;
                }
                cmd.ReadVerilog(s + "/" + file.getName());                
                String vector = vectors.get(file.getName().substring(0, file.getName().length() - 2));
                InputVector iv = new InputVector(new BigInteger(vector, 16));
                RunScore runScore = new ScoreBySPR(new BigDecimal(q));
                ScoreVector sc = runScore.execute(iv);
                BigDecimal mtbf = CommonOps.getMTBF(sc.getScore()).setScale(2, RoundingMode.HALF_UP);
                Map<ProbGate, BigDecimal> orderedGates = Utils.orderedGatesByWRV(iv, q, newQ);
                System.out.println("WRV: " + sc.getScore());
                List<ProbGate> listGates = new ArrayList<>(orderedGates.keySet());
                if (n < listGates.size()) {
                    listGates = listGates.subList(0, n);
                }
                
                for (ProbGate probGate: listGates) {
                    BigDecimal value = orderedGates.get(probGate).divide(mtbf, 5, RoundingMode.HALF_UP);
                    System.out.println("Gate: " + probGate.getId() + " R: " + value);
                    //BigDecimal value = orderedGates.get(probGate);
                    if (totalByOut.containsKey(probGate.getId())) {
                        totalByOut.put(probGate.getId(), totalByOut.get(probGate.getId()).add(value));
                        countGates.put(probGate.getId(), countGates.get(probGate.getId()) + 1);
                    } else {
                        totalByOut.put(probGate.getId(), value);
                        countGates.put(probGate.getId(), 1);
                    }
                }                                    
            } catch (Exception ex) {
                Logger.getLogger(Commands.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        totalByOut = totalByOut.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new)); 
        
        List<String> criticalGates = new ArrayList(totalByOut.keySet()); 
        System.out.println(totalByOut);
        criticalGates = criticalGates.subList(0, n);
        System.out.println("Gate;Total de saídas;Peso");
        for (String key: criticalGates) {
            System.out.println(key + ";" + countGates.get(key) + ";" + totalByOut.get(key));
        }
       return criticalGates;
    }
}
