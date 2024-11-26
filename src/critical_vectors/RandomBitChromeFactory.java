package critical_vectors;

import io.jenetics.BitChromosome;
import io.jenetics.BitGene;
import io.jenetics.Genotype;
import io.jenetics.util.Factory;

import java.math.BigInteger;
import java.util.Random;

public class RandomBitChromeFactory implements Factory<Genotype<BitGene>> {

    static int bitChromeLength;
    private static int requestedBigInt = 0;

    private static Random rnd;
    public RandomBitChromeFactory(int bitLength) {
        bitChromeLength = bitLength;
        rnd = new Random();
    }
    @Override
    public Genotype newInstance() {
        incrementRequestedBigInt();
        BigInteger randomBigInt = new BigInteger(bitChromeLength, rnd);
        System.out.println(randomBigInt);
        //return Genotype.of(BitChromosome.of(new BigInteger("511"), bitChromeLength));
        return Genotype.of(BitChromosome.of(randomBigInt, bitChromeLength));
    }

    private void incrementRequestedBigInt() {
        requestedBigInt = requestedBigInt + 1;
    }

    public int getRequestedBigInt() {
        return requestedBigInt;
    }
}
