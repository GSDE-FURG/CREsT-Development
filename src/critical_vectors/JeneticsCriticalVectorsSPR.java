package critical_vectors;
import datastructures.CellLibrary;
import datastructures.InputVector;
import io.jenetics.*;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.engine.EvolutionStatistics;
import io.jenetics.util.Factory;
import manipulator.SPRController;
import signalProbability.ProbCircuit;

import java.math.BigDecimal;
import java.util.ArrayList;

import static io.jenetics.engine.Limits.bySteadyFitness;


public class JeneticsCriticalVectorsSPR {

    static ProbCircuit pCircuit;
    static CellLibrary cellLib;
    static SPRController sprControl;


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

        BigDecimal valueBig = null;
        InputVector inputV = null;

        for(Chromosome b : gt) {
            StringBuilder builder = new StringBuilder(b.length());

            for (Object bitG : b.stream().toList()) {
                if(((BitGene)bitG).bit()) {
                    builder.append('1');
                } else {
                    builder.append('0');
                }
            }

            inputV = new InputVector(builder.toString(), pCircuit.getProbInputs().size(), true);
            valueBig = sprControl.getReliability(inputV, 14);

        }

        System.out.println(valueBig.doubleValue() + " --> " + inputV);
        return valueBig.doubleValue();
    }

    public JeneticsCriticalVectorsSPR(ProbCircuit pCircuit, CellLibrary cellLib) {
        this.cellLib = cellLib;
        this.pCircuit = pCircuit;

        sprControl = new SPRController(pCircuit, cellLib);
    }

    public void getCriticalVectors() {
        // 1.) Define the genotype (factory) suitable
        //     for the problem.
        Factory<Genotype<BitGene>> gtf =
                Genotype.of(BitChromosome.of(pCircuit.getProbInputs().size()));



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
                .builder(JeneticsCriticalVectorsSPR::eval3, gtf)
                .populationSize(30)
                .minimizing()
                .survivorsSelector(new RouletteWheelSelector<>())
                .offspringSelector(new TournamentSelector<>(3))
                //.alterers(new Mutator<>(0.1), new MultiPointCrossover<>(0.9, 2))
                //.alterers(new Mutator<>(), new MultiPointCrossover<>())
                .alterers(new Mutator<>(), new UniformCrossover<>())
                .build();

        // 4.) Start the execution (evolution) and
        //     collect the result.
        Genotype<BitGene> result = engine.stream()
                .limit(bySteadyFitness(6))
                .limit(15)
                .peek(statistics)
                .collect(EvolutionResult.toBestGenotype());

        System.out.println("result: " + result);
        System.out.println("result length " + result.length());
        System.out.println(statistics);
    }
}
