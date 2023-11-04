package critical_vectors;
import io.jenetics.BitChromosome;
import io.jenetics.BitGene;
import io.jenetics.Genotype;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.engine.EvolutionStatistics;
import io.jenetics.internal.collection.BitArray;
import io.jenetics.util.Factory;

import java.math.BigDecimal;
import java.util.BitSet;


public class HelloWorld {
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

        return new BigDecimal("0.999").doubleValue();
    }

    public static void main(String[] args) {
        // 1.) Define the genotype (factory) suitable
        //     for the problem.
        Factory<Genotype<BitGene>> gtf =
                Genotype.of(BitChromosome.of(28, 0.5));



        Genotype<BitGene> gt = Genotype.of(BitChromosome.of(13), 149);
        Genotype<BitGene> gt2 = Genotype.of(BitChromosome.of(14));


        System.out.println("13 --> " + gt);
        System.out.println("length --> " + gt.length());
        System.out.println("chomo --> " + gt.get(0));
        System.out.println("chromo length --> " + gt.get(0).length());
        System.out.println("chromo list --> " + gt.get(0).stream().toList());
        System.out.println("+++++++++++++++");

        /*for(int i = 0; i < 150; i++) {
            System.out.println(Genotype.of(BitChromosome.of(15)));
        }*/


       //gtf.instances().forEach(System.out::println);

        // 3.) Create the execution environment.
        Engine<BitGene, BigDecimal> engine = Engine
                .builder(HelloWorld::eval2, gtf)
                .build();

        // 4.) Start the execution (evolution) and
        //     collect the result.
        Genotype<BitGene> result = engine.stream()
                .limit(100)
                .collect(EvolutionResult.toBestGenotype());

        System.out.println("Hello World:\n" + result);
    }
}
