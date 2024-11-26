/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrv_algorithm_OLD;

import datastructures.InputVector;
import signalProbability.ProbCircuit;
import signalProbability.ProbSignal;
import tool.Terminal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 *
 * @author Marcio
 */
public class WRVAlgoritm {

    private List<ScoreVector> candidateVectors;
    private List<ScoreVector> nextCandidateVectors;
    private Set<List<ScoreVector>> sampleListCandidateVectors;
    private int lengthCandidateVectors;
    private int numberRandomVectors;
    private final int bitsThreshold;
    private int unDeterminedBits;
    private ScoreVector wrv;
    private BigDecimal consensusThreshold;
    private static final int DEFAULT_LENGTH_CANDIDATE_VECTORS = 10;
    private static final int DEFAULT_NUMBER_RANDOM_VECTORS = 1000;
    private static final int DEFAULT_BITS_THRESHOLD = 17;
    private static final String DEFAULT_CONSENSUS_TH = "0.9";
    private static final String DEFAULT_MIN_CONSENSUS_TH = "0.7";
    private static final double DEFAULT_SEARCH_MAX_PERCENT = 0.20;
    private final BigDecimal minConsensusThreshold;
    private final RunScore runScore;
    private Map<Integer, Character> determinedBits;
    private final Set<BigInteger> executedVectors;
    private boolean isPermittedMoreList = false;
    private final ConsensusByPercentualScore consensus;
    private BigDecimal nextMaxConsensusThreshold;
    private final boolean isPermittedRepeatedVectors = true;
    private int countVectors;

    public WRVAlgoritm(RunScore runScore) {
        this.runScore = runScore;
        this.lengthCandidateVectors = DEFAULT_LENGTH_CANDIDATE_VECTORS;
        this.numberRandomVectors = DEFAULT_NUMBER_RANDOM_VECTORS;
        this.consensusThreshold = new BigDecimal(DEFAULT_CONSENSUS_TH);
        this.minConsensusThreshold = new BigDecimal(DEFAULT_MIN_CONSENSUS_TH);
        this.bitsThreshold = DEFAULT_BITS_THRESHOLD;
        this.determinedBits = new HashMap<>();
        this.consensus = new ConsensusByPercentualScore(runScore, consensusThreshold);
        this.nextMaxConsensusThreshold = BigDecimal.ZERO;
        this.executedVectors = new HashSet<>();
    }

    public InputVector execute() {
        long initSearch = System.currentTimeMillis();
        ProbCircuit circuit = Terminal.getInstance().getProbCircuit();
        ArrayList<ProbSignal> inputs = circuit.getProbInputs();
        //define o número máximo de execuções de escores permitidas
        int maxExecutedVector = (int) (Math.pow(2, inputs.size()) * DEFAULT_SEARCH_MAX_PERCENT);
        //cria a lista de vetores candidatos
        candidateVectors = new ArrayList<>();
        //cria uma segunda lista de vetores candidatos
        nextCandidateVectors = new ArrayList<>();
        //cria uma coleção para receber outras listas de vetores candidatos
        sampleListCandidateVectors = new HashSet<>();
        //cria um gerador de 0 e 1 aleatórios
        Random random = new Random();
        //variável para determinar o número de iterações sem descobrir nenhum bit
        int iterWithoutAlteration = 0;
        //número de vezes que a lista de candidatos não é alterada
        int listCandidateVectorNoAlter = 0;
        int prevDeterminedBits;
        //número de bits não determinados
        unDeterminedBits = inputs.size();
        //se o circuito possuir menos de 23 entradas, configura o número de vetores aleatórios 
        //para 10 vezes número de bits não determinados
        if (inputs.size() < 23) {
            numberRandomVectors = 10 * unDeterminedBits;
            
            if (unDeterminedBits <= bitsThreshold) {
                wrv = runScore.execute(new InputVector(new BigInteger("0")));
            }
        }
        //executa enquanto o limite de bits para busca sequencial não for alcançado ou
        //não ocorrer 5 iterações sem descobrir bits com th igual a 0.7
        //ou número de vetores executados for menor que o máximo permitido        
        while (unDeterminedBits > bitsThreshold && iterWithoutAlteration < 5
                && countVectors < maxExecutedVector) {

            //configura o número de bits determinados para comparar se houve descoberta de novos bits
            prevDeterminedBits = determinedBits.size();
            ScoreVector scoreVector;
            //configura lista de vetores candidatos para comparação se houve alteração depois da iteração
            List<ScoreVector> prevCandidateVectors = new ArrayList<>();
            prevCandidateVectors.addAll(candidateVectors);
            String bin;
            //itera até o número de vetores aleatórios
            for (int i = 0; i < numberRandomVectors; i++) {
                char[] inputVectorChars = new char[inputs.size()];
                if (isPermittedRepeatedVectors) {
                    for (int b = 0; b < inputs.size(); b++) {
                        if (determinedBits.containsKey(b)) {
                            inputVectorChars[b] = determinedBits.get(b);
                        } else {
                            inputVectorChars[b] = (char) (random.nextInt(2) + '0');
                        }
                    }
                } else {                    
                    do {
                        for (int b = 0; b < inputs.size(); b++) {
                            if (determinedBits.containsKey(b)) {
                                inputVectorChars[b] = determinedBits.get(b);
                            } else {
                                inputVectorChars[b] = (char) (random.nextInt(2) + '0');
                            }
                        }
                        bin = String.valueOf(inputVectorChars);       
                    } while (!executedVectors.add(new BigInteger(bin, 2)));
                }

                InputVector randomInputVector = new InputVector(inputVectorChars);
                scoreVector = runScore.execute(randomInputVector);
                //adiciona vetor candidato
                addCandidateVector(scoreVector);
                System.out.println(i + " - " + scoreVector.getInputVector().getHexaString() + " - " + scoreVector.getScore());
                boolean flag;
                do {
                    flag = false;
                    //obtem lista de vizinhos do vetor
                    List<InputVector> neighborsVectors = randomInputVector.getNeighbors(determinedBits.keySet());
                    for (InputVector neighbor : neighborsVectors) {
                        if (!isPermittedRepeatedVectors) {
                            bin = neighbor.getBinaryString();
                            if (!executedVectors.add(new BigInteger(bin))) {
                                continue;
                            }
                        }
                        //obtem o score do vetor vizinho
                        ScoreVector neighborScoreVector = runScore.execute(neighbor);
                        //se o escore do vizinho for melhor, substitui vetor selecionado pelo vetor vizinho
                        if (neighborScoreVector.getScore().compareTo(scoreVector.getScore()) < 0) {
                            randomInputVector = neighborScoreVector.getInputVector();
                            scoreVector = neighborScoreVector;
                            //adiciona vetor candidato
                            addCandidateVector(neighborScoreVector);
                            flag = true;
                        }
                    }
                } while (flag);
            }
            //imprime a lista de vetores candidatos
            for (ScoreVector sc : candidateVectors) {
                System.out.println("Candidatos: "
                        + sc.getInputVector().getHexaString()
                        + " - " + sc.getScore());
            }
            System.out.println("Consenso: " + consensusThreshold);
            //se não houve alteração dos vetores candidatos, não executa o consenso
            if (prevCandidateVectors.equals(candidateVectors)
                    && nextMaxConsensusThreshold.compareTo(consensusThreshold) != 0
                    && !isPermittedMoreList) {
                //listCandidateVectorNoAlter++;            
                //e o consenso é 0.85 ou mais
                //e a diferença do próximo consenso para o atual for de 0,05 ou mais
                //permite alterar os vetores com escores iguais
                BigDecimal diferConsensus = consensusThreshold.subtract(nextMaxConsensusThreshold);
                if (diferConsensus.compareTo(new BigDecimal("0.05")) >= 0) {
                    isPermittedMoreList = true;
                    //numberRandomVectors = 10 * (inputs.size() - determinedBits.size());
                    //listCandidateVectorNoAlter = 0;
                }
                System.out.println("Lista não alterada " + listCandidateVectorNoAlter);
            } else {
                //listCandidateVectorNoAlter = 0;
                runConsensus();
                System.out.println("Próximo Consenso: " + nextMaxConsensusThreshold);
                unDeterminedBits = inputs.size() - determinedBits.size();
                isPermittedMoreList = false;
            }
            if (prevDeterminedBits == determinedBits.size()) {
                //se limite de consenso é maior que o mínimo, subtrai 0,01 do consenso
                if (consensus.getConsensusThreshold().compareTo(minConsensusThreshold) > 0) {
                    consensusThreshold = consensusThreshold.subtract(new BigDecimal("0.01"));
                    consensus.setConsensusThreshold(consensusThreshold);
                    //se o limite de consenso estiver entre 0,82 e 0,75 configura o tamanho da lista
                    //de vetores candidatos para 20
                    if (consensusThreshold.compareTo(new BigDecimal("0.82")) <= 0
                            && consensusThreshold.compareTo(new BigDecimal("0.75")) > 0) {
                        lengthCandidateVectors = 20;
                    }
                    //se o limite de consenso estiver entre 0,75 e 0,70 configura o tamanho da lista
                    //de vetores candidatos para 30
                    if (consensusThreshold.compareTo(new BigDecimal("0.75")) <= 0
                            && consensusThreshold.compareTo(new BigDecimal("0.70")) > 0) {
                        lengthCandidateVectors = 30;
                    }
                } else {
                    //se não houve descoberta de bits na iteração incrementa o número de iterações sem alteração
                    iterWithoutAlteration++;
                }
            } else {
                iterWithoutAlteration = 0;
            }
            if (determinedBits.size() >= 8 || consensusThreshold.compareTo(new BigDecimal("0.88")) <= 0) {
                if (inputs.size() > 50) {
                    numberRandomVectors = 80;
                } else {
                    numberRandomVectors = 10 * unDeterminedBits;
                }
            }
            countVectors = ScoreBySPR.getNumberExecution();
            
            System.out.println("Consultas: " + countVectors);
            System.out.println("Bits determinados: " + determinedBits);
        }
        long endSearch = System.currentTimeMillis();
        long timeSearch = endSearch - initSearch;
        System.out.println("Tempo em milisegundos: " + timeSearch);
        System.out.println("WRV antes da pesquisa sequencial:");
        System.out.println(wrv.getInputVector().getHexaString()
                + " - "
                + wrv.getInputVector().getBinaryString()
                + " - " + wrv.getScore());
        executeSearchSequential();
        System.out.println("WRV depois da pesquisa sequencial:");
        System.out.println(wrv.getInputVector().getHexaString()
                + " - "
                + wrv.getInputVector().getBinaryString()
                + " - " + wrv.getScore());
        return wrv.getInputVector();
    }

    public void runConsensus() {
        //configura melhor opção de vetores candidatos
        Map<Integer, Character> tempDeterminedBits = new HashMap<>();
        tempDeterminedBits.putAll(determinedBits);
        List<ScoreVector> tempCandidateVectors = new ArrayList<>();
        //configura os bits que já foram descobertos
        consensus.setDeterminedBits(determinedBits);
        //se for permitido procurar uma melhor opção de listas de vetores candidatos
        if (isPermittedMoreList || consensusThreshold.compareTo(new BigDecimal(DEFAULT_CONSENSUS_TH)) == 0) {
            for (List<ScoreVector> othersCandidateVector : sampleListCandidateVectors) {
                if (othersCandidateVector.size() < lengthCandidateVectors) {
                    continue;
                }
                for (ScoreVector sc : othersCandidateVector) {
                    System.out.println("Lista de candidatos temporários: "
                            + sc.getInputVector().getHexaString()
                            + ": " + sc.getInputVector().getBinaryString() + " - "
                            + sc.getScore());
                }
                //calcula o consenso para os vetores candidatos
                Map<Integer, Character> othersDeterminedBits = new HashMap<>();
                othersDeterminedBits.putAll(determinedBits);
                othersDeterminedBits.putAll(consensus.execute(othersCandidateVector));
                System.out.println("Bits Determinados temporários: " + othersDeterminedBits);
                if (othersDeterminedBits.size() > tempDeterminedBits.size()) {
                    tempDeterminedBits = othersDeterminedBits;
                    tempCandidateVectors = othersCandidateVector;
                    nextMaxConsensusThreshold = consensus.getNextMaxConsensusThreshold();
                }
            }
            sampleListCandidateVectors.clear();
        }
        determinedBits.putAll(consensus.execute(candidateVectors));
        if (tempDeterminedBits.size() > determinedBits.size()) {
            determinedBits = tempDeterminedBits;
            candidateVectors = tempCandidateVectors;
        } else {
            nextMaxConsensusThreshold = consensus.getNextMaxConsensusThreshold();
        }
        wrv = candidateVectors.get(0);
    }

    public void executeSearchSequential() {
        int length;
        if (unDeterminedBits <= bitsThreshold) {
            length = unDeterminedBits;
        } else {
            length = bitsThreshold;
        }
        int searchSpace = (int) Math.pow(2, length);
        int lengthInput = unDeterminedBits + determinedBits.size();
        for (int i = 0; i < searchSpace; i++) {
            InputVector inputVector = new InputVector
                    (new BigInteger(String.valueOf(i)));
            int count = determinedBits.size();
            char[] inputVectorChar = inputVector.getValueToChar();
            char[] searchInputVector = new char[lengthInput];
            for (int b = 0; b < lengthInput; b++) {
                if (determinedBits.containsKey(b)) {
                    searchInputVector[b] = determinedBits.get(b);
                } else {
                    searchInputVector[b] = inputVectorChar[count];
                    count++;
                }
            }
            inputVector = new InputVector(searchInputVector);
            ScoreVector scoreVector;
            scoreVector = runScore.execute(inputVector);
            if (scoreVector.getScore().compareTo(wrv.getScore()) < 0) {
                wrv = scoreVector;
            }
            System.out.println(i + ";" + inputVector.getHexaString() + ";" + inputVector.getBinaryString() + ";" + scoreVector.getScore());
        }
    }

    public boolean searchCandidateVector(ScoreVector scoreVector) {
        boolean flag = false;
        for (ScoreVector sc : candidateVectors) {
            if (sc.getInputVector().getHexaString().equals(scoreVector.getInputVector().getHexaString())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void addCandidateVector(ScoreVector scoreVector) {
        if (searchCandidateVector(scoreVector)) {
            return;
        }
        if (candidateVectors.size() < lengthCandidateVectors) {
            candidateVectors.add(scoreVector);
        } else {
//            ScoreVector minScoreVector = Collections.min(candidateVectors, Comparator.comparing(ScoreVector::getScore));
//            if (scoreVector.getScore().compareTo(minScoreVector.getScore()) > 0) {
            ScoreVector maxScoreVector = Collections.max(candidateVectors, Comparator.comparing(ScoreVector::getScore));
            if (scoreVector.getScore().compareTo(maxScoreVector.getScore()) < 0) {
                candidateVectors.remove(maxScoreVector);
                //candidateVectors.remove(minScoreVector);
                candidateVectors.add(scoreVector);
                sampleListCandidateVectors.clear();
                nextCandidateVectors.clear();
            } else {
                if ((isPermittedMoreList || consensusThreshold.compareTo(new BigDecimal(DEFAULT_CONSENSUS_TH)) == 0)
                        && sampleListCandidateVectors.size() < 5) {
                    ScoreVector minScoreVector = Collections.min(candidateVectors, Comparator.comparing(ScoreVector::getScore));
                    if (maxScoreVector.getScore().compareTo(minScoreVector.getScore()) == 0
                            && scoreVector.getScore().compareTo(maxScoreVector.getScore()) == 0) {
                        if (nextCandidateVectors.size() < lengthCandidateVectors) {
                            nextCandidateVectors.add(scoreVector);
                        } else {
                            sampleListCandidateVectors.add(nextCandidateVectors);
                            nextCandidateVectors = new ArrayList<>();
                        }
                    }
                }
            }
        }
//        candidateVectors.sort(Comparator.comparing(ScoreVector::getScore).reversed());
        candidateVectors.sort(Comparator.comparing(ScoreVector::getScore));
    }
}
