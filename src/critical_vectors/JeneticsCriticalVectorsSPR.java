package critical_vectors;
import datastructures.CellLibrary;
import datastructures.InputVector;
import io.jenetics.*;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.engine.EvolutionStatistics;
import io.jenetics.engine.Limits;
import io.jenetics.stat.MinMax;
import io.jenetics.util.Factory;
import io.jenetics.util.ISeq;
import manipulator.SPRController;
import signalProbability.ProbCircuit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class JeneticsCriticalVectorsSPR {

    static ProbCircuit pCircuit;
    static CellLibrary cellLib;
    static SPRController sprControl;
    int inputVectorLength;
    static HashMap<Chromosome, InputVector> hashCromosome;
    private static int evalRequests = 0;
    private static int sprRequests = 0;


    // 2.) Definition of the fitness function.
    private static Integer eval(Genotype<BitGene> gt) {
        return gt.chromosome()
                .as(BitChromosome.class)
                .bitCount();
    }

    private static BigDecimal eval2(Genotype<BitGene> gt) {

        return new BigDecimal("0.999");
    }

    private static Double eval3(Genotype<BitGene> gt) {

        incrementEvalRequests();

        InputVector inputVector = hashCromosome.get(gt.get(0));

        if(inputVector == null) {
            double reliabilityValue = -1.0;

            for(Chromosome b : gt) {

                StringBuilder builder = new StringBuilder(b.length());

                for (Object bitG : b.stream().toList()) {
                    if(((BitGene)bitG).bit()) {
                        builder.append('1');
                    } else {
                        builder.append('0');
                    }
                }

                inputVector = new InputVector(builder.toString(), pCircuit.getProbInputs().size(), true);
                reliabilityValue = sprControl.getReliability(inputVector, 14).doubleValue();
                incrementSprRequests();
                inputVector.setDoubleReliability(reliabilityValue);

            }

            System.out.println(reliabilityValue + " --> " + inputVector);

            hashCromosome.put(gt.get(0), inputVector);

            return reliabilityValue;

        } else {
            System.out.println(inputVector.getDoubleReliability() + " --> " + inputVector);
            return inputVector.getDoubleReliability();
        }
    }

    public JeneticsCriticalVectorsSPR(ProbCircuit pCircuit, CellLibrary cellLib) {
        this.cellLib = cellLib;
        this.pCircuit = pCircuit;
        this.inputVectorLength = this.pCircuit.getProbInputs().size();

        sprControl = new SPRController(pCircuit, cellLib);
        hashCromosome = new HashMap<>();
    }

    public void getCriticalVectors() {
        // 1.) Define the genotype (factory) suitable
        //     for the problem.
        Factory<Genotype<BitGene>> gtf =
                Genotype.of(BitChromosome.of(pCircuit.getProbInputs().size()));

        Factory<Genotype<BitGene>> randomBitChromosome = new RandomBitChromeFactory(inputVectorLength);



        /*Genotype<BitGene> gt = Genotype.of(BitChromosome.of(pCircuit.getProbInputs().size()), 49);

        for(Chromosome b : gt) {
            StringBuilder builder = new StringBuilder(b.length());

            for (Object bitG : b.stream().toList()) {
                if(((BitGene)bitG).bit()) {
                    builder.append('1');
                } else {
                    builder.append('0');
                }
            }

            InputVector inputV = new InputVector(builder.toString(), pCircuit.getProbInputs().size(), true);
            BigDecimal valueBig = sprControl.getReliability(inputV, 14);

            System.out.println(inputV);
            System.out.println("BigDecimal " + valueBig);
            System.out.println("    Double " + valueBig.doubleValue());
            System.out.println("------------");
        }*/

        /*for(int i = 0; i < 150; i++) {
            System.out.println(Genotype.of(BitChromosome.of(15)));
        }*/


        //gtf.instances().forEach(System.out::println);

        EvolutionStatistics<Double, ?> statistics = EvolutionStatistics.ofNumber();

        // 3.) Create the execution environment.
        Engine<BitGene, Double> engine = Engine
                .builder(JeneticsCriticalVectorsSPR::eval3, randomBitChromosome)
                .populationSize(50)
                .optimize(Optimize.MINIMUM)
                .survivorsSelector(new TournamentSelector<>(3))
                .offspringSelector(new TournamentSelector<>(3))
                //.alterers(new Mutator<>(0.1), new MultiPointCrossover<>(0.9, 2))
                //.alterers(new Mutator<>(1/inputVectorLength), new MultiPointCrossover<>(0.9, 2))
                //.alterers(new Mutator<>(1/inputVectorLength))
                .alterers(new Mutator<>((double)1/inputVectorLength), new MultiPointCrossover<>(2))
                //.alterers(new Mutator<>((double)1/inputVectorLength), new UniformCrossover<>())
                .build();



        Genotype<BitGene> result = engine.stream()
                .limit(Limits.bySteadyFitness(15))
                .limit(50)
                .peek(statistics)
                .collect(EvolutionResult.toBestGenotype());


        List<InputVector> criticalInputVectors = new ArrayList<>(hashCromosome.values());
        CriticalInputVectorComparator criticalComparator = new CriticalInputVectorComparator();

        Collections.sort(criticalInputVectors, criticalComparator);

        double simpleMean = 0;

        for (InputVector in : criticalInputVectors) {
            System.out.println(in.getBinaryString() + " --> " + in.getDoubleReliability());
            simpleMean = simpleMean + in.getDoubleReliability();
        }

        System.out.println("Jenetics MEAN == " + (simpleMean/(double)criticalInputVectors.size()));

        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        System.out.println("CriticalVectorList " + criticalInputVectors.size());
        System.out.println("result: " + result);
        System.out.println("result length " + result.length());
        System.out.println(statistics);
        System.out.println("HashChromosome size " + hashCromosome.size());
        System.out.println("Requested BigInt: " + ((RandomBitChromeFactory)randomBitChromosome).getRequestedBigInt());

        int teste = ((RandomBitChromeFactory)randomBitChromosome).getRequestedBigInt() + 1000;
        System.out.println("Requested BigInt After: " + ((RandomBitChromeFactory)randomBitChromosome).getRequestedBigInt());

        System.out.println("Eval requests: " + this.evalRequests);
        System.out.println("SPR requests: " + this.sprRequests);

        System.out.println("Engine Generator: " + ((RandomBitChromeFactory)engine.genotypeFactory()).getRequestedBigInt());


    }

    private static void incrementEvalRequests() {
        evalRequests = evalRequests + 1;
    }

    private static void incrementSprRequests() {
        sprRequests = sprRequests + 1;
    }
}
