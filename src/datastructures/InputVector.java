package datastructures;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * a classe InputVector representa uma combinação de sinais de entrada de um
 * circuito 
 *
 * @author Marcio
 */
public class InputVector {

    private static final char CHAR_ONE = '1';
    private static final char CHAR_ZERO = '0';
    private final BigInteger value;
    private int bits = 0;

    private final BigDecimal[][] M_ZERO = new BigDecimal[][]{{BigDecimal.ONE, BigDecimal.ZERO},{BigDecimal.ZERO,BigDecimal.ZERO}};
    private final BigDecimal[][] M_ONE = new BigDecimal[][]{{BigDecimal.ZERO, BigDecimal.ZERO},{BigDecimal.ZERO,BigDecimal.ONE}};

    public InputVector(BigInteger value) {
       this.value = value;
    }
    
    public InputVector(String value) {
       this.value = new BigInteger(value);
    }
    
    public InputVector(BigInteger value, int bits) {
       this.value = value;
       this.bits = bits;
    }
        
    public InputVector(char[] value) {
        String bin = String.valueOf(value);        
        this.value = new BigInteger(bin, 2);
    }

    public String getDecimalString() {
        return value.toString(10);
    }

    //retorna um array de char dos bits de entrada
    public char[] getValueToChar() {
        return this.getBinaryString().toCharArray();
    }
    
    public String getHexaString() {
        return value.toString(16);
    }

    /**
     * Retorna um Arraylist de matrizes de BigDecimal, representando o InputVector
     * @return
     */
    public ArrayList<BigDecimal[][]> getSignalMatricesRepresentation() {
        ArrayList<BigDecimal[][]> matrices = new ArrayList<BigDecimal[][]>();

        for(char c : this.getBinaryString().toCharArray()) {
            if(c == '0') {
                matrices.add(M_ZERO);
            } else {
                matrices.add(M_ONE);
            }
        }

        return matrices;
    }
    /*
    //retorna uma representação binária do valor com tamanho igual ao número de sinais de entrada
    */
    public String getBinaryString() {
        if (bits != 0) {
            String bin = value.toString(2);         
            while (bin.length() < this.bits) {
                bin = '0' + bin;
            }
            return bin;
        }
        else {
            return this.value.toString(2);
        }
    }
    
    
    //o método retorna uma lista de vetores de entrada vizinhos do vetor analisado
    //ou seja, a diferença entre eles é de um bit.
    //recebe como parâmetro de entrada as posições dos bits que já estão determinados 
    //portanto, não vai alterar
    public List<InputVector> getNeighbors(Set<Integer> determinedBits) {
        List<InputVector> listNeighbors = new ArrayList<>();
        char[] currentValue = this.getValueToChar();
        for (int i = 0; i < currentValue.length; i++) {
            if (!determinedBits.contains(i)) {
                char[] tempValue = this.getValueToChar();
                if (currentValue[i] == CHAR_ONE) {
                    tempValue[i] = CHAR_ZERO;
                } else {
                    tempValue[i] = CHAR_ONE;
                }               
                InputVector neighbor = new InputVector(tempValue);
                listNeighbors.add(neighbor);
            }
        }

        return listNeighbors;

    }
    
    public void setBits(int bits) {
        this.bits = bits;
    }
    
    @Override
    public String toString() {        
        return this.getBinaryString();
    }    
}