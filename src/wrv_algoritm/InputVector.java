/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrv_algoritm;

import datastructures.Circuit;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import tool.Terminal;

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

    public InputVector(BigInteger value) {
       this.value = value;
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

    //retorna uma representação binária do valor com tamanho igual ao número de sinais de entrada
    public String getBinaryString() {
        Circuit circuit = Terminal.getInstance().getCircuit();
        int numberInputs = circuit.getInputs().size(); 
        String bin = value.toString(2);         
        while (bin.length() < numberInputs) {
            bin = '0' + bin;
        }
        return bin;
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
}
