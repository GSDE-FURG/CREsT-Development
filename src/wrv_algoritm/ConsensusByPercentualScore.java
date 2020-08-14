/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrv_algoritm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe que representa a determinação de consenso
 * @author Marcio
 */
public class ConsensusByPercentualScore implements Consensus {

    private static final char CHAR_ONE = '1';
    private static final char CHAR_ZERO = '0';
    private static final int INT_ZERO = 0;

    private final RunScore runScore;
    private Map<Integer, Character> determinedBits;
    private BigDecimal consensusThreshold;
    //representa o maior valor limite de consenso excluindo o consensusThreshold
    //caso não haja aleração da lista de vetores candidatos
    private BigDecimal nextMaxConsensusThreshold;

    public ConsensusByPercentualScore(RunScore runScore, BigDecimal consensusThreshold) {
        this.determinedBits = new HashMap<>();
        this.runScore = runScore;
        this.consensusThreshold = consensusThreshold;
    }

    public BigDecimal getNextMaxConsensusThreshold() {
        return nextMaxConsensusThreshold;
    }

    public void setDeterminedBits(Map<Integer, Character> determinedBits) {
        this.determinedBits = determinedBits;
    }

    public BigDecimal getConsensusThreshold() {
        return consensusThreshold;
    }

    public void setConsensusThreshold(BigDecimal consensusThreshold) {
        this.consensusThreshold = consensusThreshold;
    }

    @Override
    public Map<Integer, Character> execute(List<ScoreVector> scoreVectors) {
        Map<Integer, Character> discoveredBits = new HashMap<>();
        nextMaxConsensusThreshold = BigDecimal.ZERO;
        if (!scoreVectors.isEmpty()) {
            int numberBits = scoreVectors.get(0).getInputVector().getBinaryString().length();
            for (int i = 0; i < numberBits; i++) {
                //se o bit na posição i já foi determinado pula a iteração
                if (determinedBits.containsKey(i)) {
                    continue;
                }
                //total dos escores quando o bit em análise é configurado para 1
                BigDecimal sumScoresOnes = BigDecimal.ZERO;
                //total dos escores quando o bit em análise é configurado para 0
                BigDecimal sumScoresZeros = BigDecimal.ZERO;
                System.out.println("Valor de i: " + i);
                //iterar pela lista de vetores candidatos
                for (ScoreVector scoreVector : scoreVectors) {
                    InputVector inputVector = scoreVector.getInputVector();
                    //obter um vetor de chars que representa o vetor de entrada
                    char[] inputVectorChars = inputVector.getValueToChar();
                    ScoreVector sc;
                    for (int v = 0; v < 2; v++) {
                        inputVectorChars[i] = (char) (v + '0');
                        InputVector iv = new InputVector(inputVectorChars);
                        if (!inputVector.getBinaryString().equals(iv.getBinaryString())) {
                            sc = runScore.execute(iv);
                        } else {
                            sc = scoreVector;
                        }
                        //System.out.println("Consenso1: " + inputVector.getValue() + ";" + sc.getScore());
                        //System.out.println("Consenso2: " + iv.getValue() + ";" + sc.getScore());
                        if (v == 0) {
                            sumScoresZeros = sumScoresZeros.add(BigDecimal.ONE.subtract(sc.getScore()));
                        } else {
                            sumScoresOnes = sumScoresOnes.add(BigDecimal.ONE.subtract(sc.getScore()));
                        }
//                        if (v == 0) {
//                            sumScoresZeros = sumScoresZeros.add(sc.getScore());
//                        } else {
//                            sumScoresOnes = sumScoresOnes.add(sc.getScore());
//                        }
                    }
                }
                //System.out.println("Escore ONE: " + sumScoresOnes);
                //System.out.println("Escore Zero: " + sumScoresZeros);
                BigDecimal consensusByOne = sumScoresOnes.divide(sumScoresOnes.add(sumScoresZeros), 10, RoundingMode.HALF_UP);
                BigDecimal consensusByZero = sumScoresZeros.divide(sumScoresZeros.add(sumScoresOnes), 10, RoundingMode.HALF_UP);
                if (consensusByOne.compareTo(consensusThreshold) >= INT_ZERO) {
                    discoveredBits.put(i, CHAR_ONE);
                } else {
                    if (consensusByOne.setScale(2, RoundingMode.FLOOR).compareTo(nextMaxConsensusThreshold) > 0) {
                        nextMaxConsensusThreshold = consensusByOne.setScale(2, RoundingMode.FLOOR);
                    }
                }
                if (consensusByZero.compareTo(consensusThreshold) >= INT_ZERO) {
                    discoveredBits.put(i, CHAR_ZERO);
                } else {
                    if (consensusByZero.setScale(2, RoundingMode.FLOOR).compareTo(nextMaxConsensusThreshold) > 0) {
                        nextMaxConsensusThreshold = consensusByZero.setScale(2, RoundingMode.FLOOR);
                    }
                }
                System.out.println("Consensus ONE " + consensusByOne);
                System.out.println("Consensus ZERO " + consensusByZero);
            }
        }

        return discoveredBits;
    }
}
