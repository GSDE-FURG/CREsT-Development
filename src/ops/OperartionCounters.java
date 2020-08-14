/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ops;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import levelDatastructures.GateLevel;
import levelDatastructures.InterLevel;
import levelDatastructures.LevelCircuit;
import static ops.CommonOps.powLong;
import signalProbability.ProbCircuit;
import signalProbability.ProbGate;
import signalProbability.ProbGateLevel;
import signalProbability.ProbInterLevel;
import signalProbability.ProbSignal;

import ops.SPRMultiPassOps;

/**
 *
 * @author matheus
 */
public class OperartionCounters {
    
    public static BigInteger[] getTotalOperationsPTMwithMatrixRepresentation(ProbCircuit pCircuit) {        
        BigInteger[] result = new BigInteger[2];
        
        BigInteger multiplications;
        BigInteger sums = BigInteger.ZERO;
        
        BigInteger curMatrixX;
        BigInteger curMatrixY;
        
        BigInteger[] matrixParam;
        
        ProbInterLevel firstILevel = pCircuit.getProbInterLevels().get(0);
        ProbGateLevel firstGLevel = pCircuit.getProbGateLevels().get(0);
        
        // Here it will be pick the total multiplication in the first GateLevel
        multiplications = getPTMGateLevelMultiplicationsWithMatrix(firstGLevel);
        
        // Test if the first Interconnect Level has to be in account        
        if(!(firstILevel.getInSignals().equals(firstILevel.getOutSignals()))) {
            
            // if true the current matrix has the size of first interconnection level
            curMatrixX = getTotalMatrixLines(firstILevel);
            curMatrixY = getTotalMatrixColumns(firstILevel);
            
            matrixParam = getMatrixTotalOperations("MULTIPLICATION", 
                                                    curMatrixX, 
                                                    curMatrixY, 
                                                    getTotalMatrixLines(firstGLevel),
                                                    getTotalMatrixColumns(firstGLevel));
            
            curMatrixX = matrixParam[0];
            curMatrixY = matrixParam[1];
            
            sums = sums.add(matrixParam[3]);
            multiplications = multiplications.add(matrixParam[4]);
            
        } else {
            
            // if the first internection is linear, the current matrix is the first gate level
            curMatrixX = getTotalMatrixLines(firstGLevel);
            curMatrixY = getTotalMatrixColumns(firstGLevel);
        }
        
        for (int i = 1; i < pCircuit.getProbGateLevels().size(); i++) {
            
            ProbInterLevel iLevel = pCircuit.getProbInterLevels().get(i);
            ProbGateLevel gLevel = pCircuit.getProbGateLevels().get(i);
            
            
            if(!(iLevel.getInSignals().equals(iLevel.getOutSignals()))) {
                
                matrixParam = getMatrixTotalOperations("MULTIPLICATION", 
                                                    curMatrixX, 
                                                    curMatrixY, 
                                                    getTotalMatrixLines(iLevel),
                                                    getTotalMatrixColumns(iLevel));
            
                curMatrixX = matrixParam[0];
                curMatrixY = matrixParam[1];

                sums = sums.add(matrixParam[3]);
                multiplications = multiplications.add(matrixParam[4]);
                
                
            }
            
            //Account the GateLevel Multiplications
            multiplications = multiplications.add(getPTMGateLevelMultiplicationsWithMatrix(gLevel));
            
                                                
            matrixParam = getMatrixTotalOperations("MULTIPLICATION", 
                                                    curMatrixX, 
                                                    curMatrixY, 
                                                    getTotalMatrixLines(gLevel),
                                                    getTotalMatrixColumns(gLevel));
            
                curMatrixX = matrixParam[0];
                curMatrixY = matrixParam[1];

                sums = sums.add(matrixParam[3]);
                multiplications = multiplications.add(matrixParam[4]);
                
        }
        
        
        //Doubles operation because ITM Matrix!!!
        sums = sums.add(sums);
        multiplications = multiplications.add(multiplications);
        
        //PTM and ITM sum relation
        //OBS: the total "1"s in ITM matrix is equal to total lines in ITM/PTM matrix
        sums = sums.add(curMatrixX);
        
        //Here I considered the last step in equation: divide the sum PTM x ITM relation
        //by power of 2 and number of IN - 1 Miltiplications
        multiplications = multiplications.add(new BigInteger(Integer.toString(pCircuit.getProbInputs().size()-1)));
        
        result[0] = sums;
        result[1] = multiplications;
        
        return result;
    }
        
    
    public static BigInteger getPTMLevelTotalElements(Object level) {
        
        BigInteger total;
        
        BigInteger ins = new BigInteger("2");
        BigInteger outs = new BigInteger("2");
        
        if(level instanceof ProbGateLevel) {
            ProbGateLevel pGateLevel = (ProbGateLevel)level;
            ins = ins.pow(pGateLevel.getIn());
            outs = outs.pow(pGateLevel.getOut());
        } else if(level instanceof ProbInterLevel) {
            ProbInterLevel pInterLevel = (ProbInterLevel)level;
            ins = ins.pow(pInterLevel.getInSignals().size());
            outs = outs.pow(pInterLevel.getOutSignals().size());
        } else {
            return null;
        }
        
        total = ins.multiply(outs);
                         
        return total;
    }
    
    /**
     * Method to obtain the total multiplations in a PTM Gate Level construction
     * With matrix represetation
     * @param glevel TODO
     * @return TODO
     */
    public static BigInteger getPTMGateLevelMultiplicationsWithMatrix(ProbGateLevel glevel) {

        BigInteger totalMults = BigInteger.ZERO;
        BigInteger cMult = BigInteger.ZERO;        
        
        if(glevel.getGates().isEmpty()) {
            return null;
        } else {
            cMult = getPTMGateTotalElements(glevel.getGates().get(0));            
        }

        
        for (int i = 1; i < glevel.getGates().size(); i++) {
            Object gate = glevel.getGates().get(i);
            cMult = cMult.multiply(getPTMGateTotalElements(gate));
            totalMults = totalMults.add(cMult);
            
            
        }                        
                         
        return totalMults;
    }
    
    
    public static BigInteger getPTMGateTotalElements(Object gate) {

        BigInteger total;
        
        
        if(gate instanceof ProbGate) {
            ProbGate pGate = (ProbGate)gate;
            
            total = new BigInteger("2").pow(pGate.getpInputs().size());
            
            total = total.multiply(new BigInteger("2").pow(pGate.getpOutputs().size()));
            
        } else if(gate instanceof ProbSignal) {
            
            total = new BigInteger("4");
            
        } else {
            return null;
        }
                                 
        return total;
    }
    
    /**
     * dsdsadsadsadsasdsda
     * @param op TODO
     * @param xFirstMatrix TODO
     * @param yFirstMatrix TODO
     * @param xSecondMatrix TODO
     * @param ySecondMatrix TODO
     * @return a BigInteger vector, where: <br>
     *   result[0] = newX <br>
     *   result[1] = newY <br>           
     *   result[2] = matrixResultElements <br>           
     *   result[3] = sums <br>           
     *   result[4] = mults <br>  
     */
    public static BigInteger[] getMatrixTotalOperations(String op, 
                                                BigInteger xFirstMatrix, 
                                                BigInteger yFirstMatrix, 
                                                BigInteger xSecondMatrix, 
                                                BigInteger ySecondMatrix) {

        BigInteger sums;
        BigInteger mults;
        BigInteger newX;
        BigInteger newY;        
        
        BigInteger[] result = new BigInteger[5];
        
        BigInteger matrixResultElements;
        
        if(op.equals("MULTIPLICATION")) {
            
            // The lines number in Multiplication is equals to line (X) of first matrix
            newX = xFirstMatrix;
            
            // The columns number in Multiplication is equals to column (Y) of second matrix
            newY = ySecondMatrix;
            
            // In a matrix multiplication the total elements is equal to:
            // first matrix line MULTIPLIED by second matrix column            
            matrixResultElements = xFirstMatrix.multiply(ySecondMatrix);
            
            // The total sums operations is equal to:
            // (First matrix column OR Second matrix line) - 1
            sums = matrixResultElements.multiply(yFirstMatrix.subtract(BigInteger.ONE));
            
            // The total multiplication operations is equal to:
            // (First matrix column OR Second matrix line) times the total elements
            mults = matrixResultElements.multiply(yFirstMatrix);
                                                         
            
        } else if(op.equals("KRONECKER")) {
            
            // The lines number in Kronecker is equals to line multiplication
            newX = xFirstMatrix.multiply(xSecondMatrix);
            
            // The columns number in Kronecker is equals to column multiplication
            newY = yFirstMatrix.multiply(ySecondMatrix);
            
            // In Kronecker's tensor the total elements is equal to:
            // first matrix elements MULTIPLIED by second matrix elements            
            matrixResultElements = newX.multiply(newY);
            
            // The total sums operations in Kronecker's Tensor is 0:            
            sums = BigInteger.ZERO;
            
            // The total multiplication operations is equal to:
            // Total Result Matrix Elements
            mults = matrixResultElements;
            
        } else {
            return null;
        }
        
        result[0] = newX;
        result[1] = newY;            
        result[2] = matrixResultElements;            
        result[3] = sums;            
        result[4] = mults;   
                                                 
        return result;
    }
    
    public static BigInteger getTotalMatrixLines(ProbGateLevel pGateLevel) {
        
        BigInteger total;
        
        total = new BigInteger("2").pow(pGateLevel.getIn());
        
        return total;
    }
    
    public static BigInteger getTotalMatrixColumns(ProbGateLevel pGateLevel) {
        
        BigInteger total;
        
        total = new BigInteger("2").pow(pGateLevel.getOut());
        
        return total;
    }
    
    public static BigInteger getTotalMatrixLines(ProbInterLevel pInterLevel) {
        
        BigInteger total;
        
        total = new BigInteger("2").pow(pInterLevel.getInSignals().size());
        
        return total;
    }
    
    public static BigInteger getTotalMatrixColumns(ProbInterLevel pInterLevel) {
        
        BigInteger total;
        
        total = new BigInteger("2").pow(pInterLevel.getOutSignals().size());
        
        return total;
    }
    
    
//    *
//    *
//    *
//    * PTM SERIAL
//    *
//    *
    
    
    public static BigInteger[] getTotalOperationsSerialPTM(ProbCircuit pCircuit) {
        
        BigInteger[] result = new BigInteger[]{BigInteger.ZERO, BigInteger.ZERO};
        BigInteger[] result2;                       
        
        ArrayList<Object> levels = new ArrayList<>();
       
        
        
        for (int i = 0; i < pCircuit.getProbGateLevels().size(); i++) {
            ProbInterLevel pInterLevel = pCircuit.getProbInterLevels().get(i);
            
            if(!(pInterLevel.getInSignals().equals(pInterLevel.getOutSignals()))) {
                pInterLevel.setSignalCombinations();

                levels.add(pInterLevel.getSignalCombinations());
            }

            levels.add((pCircuit.getProbGateLevels().get(i)));
        }
        
        result = getMultiplicationMatrixIndex(levels, 0, 0);
        

                
//        for (int i = 1; i < PTMOps.PowInt(2, pCircuit.getProbInputs().size()); i++) {
//            result2 = getMultiplicationMatrixIndex(levels, i, 0);
//            
//            System.out.println(" Elemento " + i + " x 0 == " + Arrays.toString(result2));
//
//            result[0] = result[0].add(result2[0]);
//            result[1] = result[1].add(result2[1]);            
//        }
        
        BigInteger totalElements = new BigInteger("2").pow(pCircuit.getProbInputs().size());
        /**
         * Total SUMS
         */
        // All sums to get the PTM Matrix
        result[0] = result[0].multiply(totalElements);
        
        // Final sum to obtain PTM reliability
        result[0] = result[0].add(totalElements.subtract(BigInteger.ONE));
        
        
        /**
         * Total multiplications
         */
        result[1] = result[1].multiply(totalElements);
        
        return result;
    }
    
//    public static BigInteger[] getTotalOperationsSerialPTMIndex (ProbCircuit pCircuit, long x, long y) {
//        
//        
//        ArrayList<Object> levels = new ArrayList<>();
//        
//        for (int i = 0; i < pCircuit.getProbGateLevels().size(); i++) {
//            ProbInterLevel pInterLevel = pCircuit.getProbInterLevels().get(i);
//            
//            if(!(pInterLevel.getInSignals().equals(pInterLevel.getOutSignals()))) {
//                pInterLevel.setSignalCombinations();
//                levels.add(pInterLevel.getSignalCombinations());
//            }
//            
//            levels.add((pCircuit.getProbGateLevels().get(i)));
//        }
//        
//        
//        BigInteger[] result = getMultiplicationMatrixIndex(levels, x, y);
//        
//        
//        
//        return result;
//    }

    public static BigInteger[] getMultiplicationMatrixIndex(ArrayList<Object> levels, long x, long y) {
        
        BigInteger[] result = new BigInteger[2];
        BigInteger sums = BigInteger.ZERO;
        BigInteger multiplications = BigInteger.ZERO;
        
        if(levels.size() == 2) {
            return getMultiplicationMatrixIndex(levels.get(0), levels.get(1), x, y);
        } else {
            result[0] = BigInteger.ZERO;
            result[1] = BigInteger.ZERO;
            long limit;
            
            Object lastLevel = levels.get(levels.size()-1);
            ArrayList<Object> auxList = new ArrayList<>(levels.subList(0, levels.size()-1));
            
            
            if( lastLevel instanceof ProbGateLevel) {
                limit = powLong(2, ((ProbGateLevel)lastLevel).getIn());
            } else {
                int[] fooList = (int[])lastLevel;
                for (int i = 0; i < fooList.length; i++) {
                    if(fooList[i] == y) {
                        return getMultiplicationMatrixIndex(auxList, x, fooList[i]);                        
                    }
                }
                
                //return new BigInteger[]{BigInteger.ZERO, BigInteger.ZERO};
                return null;
            }

            long bigCounter = 0;
            long match = 0;
            
            while(bigCounter < limit) {

                BigInteger[] aux;

                aux = getMultiplicationMatrixIndex(auxList, x, bigCounter);
                
                if(aux != null) {
                    aux[1] = aux[1].add(new BigInteger(Integer.toString(((ProbGateLevel)lastLevel).getGates().size()-1)));
                    
                    result[0] = result[0].add(aux[0]);
                    result[1] = result[1].add(aux[1]);
                    
                    match++;
                }                                    

                    
//                }                                                                
                
                bigCounter += 1;
            }
            
            result[0] = result[0].add(new BigInteger(Long.toString(match-1)));
            result[1] = result[1].add(new BigInteger(Long.toString(match)));
            

            return result;
        }
    }
    
    public static BigInteger[] getMultiplicationMatrixIndex(Object levelA, Object levelB, long x, long y) {
        
        if (levelA instanceof int[]) {
            return getMultiplicationMatrixIndex((int[])levelA, (ProbGateLevel)levelB, x, y);
        } else {
            if (levelB instanceof int[]) {
                return getMultiplicationMatrixIndex((ProbGateLevel)levelA, (int[])levelB, x, y);
            } else {
                return getMultiplicationMatrixIndex((ProbGateLevel)levelA, (ProbGateLevel)levelB, x, y);
            }
        }
    }
    
    public static BigInteger[] getMultiplicationMatrixIndex(int[] levelA,  ProbGateLevel levelB, long x, long y) {
        
        BigInteger multiplications = BigInteger.ZERO;
        
        multiplications = new BigInteger(Integer.toString(levelB.getGates().size()-1));
                                            
        return new BigInteger[]{BigInteger.ZERO, multiplications};
    }
    
    public static BigInteger[] getMultiplicationMatrixIndex(ProbGateLevel levelA,  int[] levelB, long x, long y) {
        
        BigInteger multiplications = BigInteger.ZERO;
        
        for (int i = 0; i < levelB.length; i++) {
            if(levelB[i] == (int) y) {
                multiplications = new BigInteger(Integer.toString(levelA.getGates().size()-1));
            }
        }               
        
        return new BigInteger[]{BigInteger.ZERO, multiplications};
    }
    
    public static BigInteger[] getMultiplicationMatrixIndex(ProbGateLevel levelA, ProbGateLevel levelB, long x, long y) {
        
        BigInteger multiplicationsA = new BigInteger(Integer.toString(levelA.getGates().size()-1));
        BigInteger multiplicationsB = new BigInteger(Integer.toString(levelB.getGates().size()-1));        
        BigInteger multiplications = multiplicationsA.add(multiplicationsB);
        
        BigInteger sums = BigInteger.ZERO;
        
        multiplications = multiplications.add(BigInteger.ONE);
        multiplications = multiplications.multiply(new BigInteger("2").pow(levelB.getIn()));
        
        sums = new BigInteger(Integer.toString(levelB.getIn()-1));
        
        return new BigInteger[]{sums, multiplications};
    }
    
    
    /*
     .----------------.  .----------------.  .----------------. 
| .--------------. || .--------------. || .--------------. |
| |    _______   | || |   ______     | || |  _______     | |
| |   /  ___  |  | || |  |_   __ \   | || | |_   __ \    | |
| |  |  (__ \_|  | || |    | |__) |  | || |   | |__) |   | |
| |   '.___`-.   | || |    |  ___/   | || |   |  __ /    | |
| |  |`\____) |  | || |   _| |_      | || |  _| |  \ \_  | |
| |  |_______.'  | || |  |_____|     | || | |____| |___| | |
| |              | || |              | || |              | |
| '--------------' || '--------------' || '--------------' |
 '----------------'  '----------------'  '----------------' 
    
    
    
    */
    
    public static BigInteger[] getTotalOperationsSPR(ProbCircuit pCircuit) {
        
        BigInteger[] result = new BigInteger[2];
        BigInteger sums = BigInteger.ZERO;
        BigInteger multiplications = BigInteger.ZERO;
        
        for (ProbGate gate : pCircuit.getProbGates()) {
            
            /**
             * For each gate it has to add multiplications
             * Because of Kronecker tensor in IN gate signal
             */
            BigInteger currentMult = BigInteger.ZERO;            
            for (int i = gate.getpInputs().size(); i > 1; i--) {
                currentMult = currentMult.add(new BigInteger("4").pow(i));                
            }
            multiplications = multiplications.add(currentMult);
            
            
            /**
             * So next SPR step is a multiplication of the IN Signal Matrix with
             * PTM matrix of current gate
             */
            BigInteger[] currentOperations = getMatrixTotalOperations("MULTIPLICATION", 
                                                                      new BigInteger("2").pow(gate.getpInputs().size()), 
                                                                      new BigInteger("2").pow(gate.getpInputs().size()), 
                                                                      new BigInteger("2").pow(gate.getpInputs().size()), 
                                                                      new BigInteger("2").pow(gate.getpOutputs().size())
            );
            
            sums = sums.add(currentOperations[3]);
            multiplications = multiplications.add(currentOperations[4]);
            
            
            /**
             * The decode in gate's output signal
             * is made by relation between matrix multiplication of last step
             * and gate's ITM matrix. That relation result in:
             * sums = (matrixLines - 2) * matrixColumn
             */
            sums = sums.add(
                    new BigInteger("2").pow(gate.getpInputs().size()).subtract(
                    new BigInteger("2")).multiply(new BigInteger("2").pow(gate.getpOutputs().size()))
            );                        
            
        }
        
        /**
         * The last step is counting the mult and sums of reliability combination
         * of all outputs. So, the number of sums and mult are equal to number of outputs.
         * Because for each reliability output has to account one sum of correct zero and one.
         * And one multiplication for each output too because of acumulative reliability.        
         */
        BigInteger fooBig = new BigInteger(Integer.toString(pCircuit.getProbOutputs().size()));
        sums = sums.add(fooBig);
        multiplications = multiplications.add(fooBig);
        
        result[0] = sums;
        result[1] = multiplications;
        
        return result;
    }
    
    
    /*        
     .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------. 
| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |
| |    _______   | || |   ______     | || |  _______     | || |              | || | ____    ____ | || |   ______     | |
| |   /  ___  |  | || |  |_   __ \   | || | |_   __ \    | || |              | || ||_   \  /   _|| || |  |_   __ \   | |
| |  |  (__ \_|  | || |    | |__) |  | || |   | |__) |   | || |    ______    | || |  |   \/   |  | || |    | |__) |  | |
| |   '.___`-.   | || |    |  ___/   | || |   |  __ /    | || |   |______|   | || |  | |\  /| |  | || |    |  ___/   | |
| |  |`\____) |  | || |   _| |_      | || |  _| |  \ \_  | || |              | || | _| |_\/_| |_ | || |   _| |_      | |
| |  |_______.'  | || |  |_____|     | || | |____| |___| | || |              | || ||_____||_____|| || |  |_____|     | |
| |              | || |              | || |              | || |              | || |              | || |              | |
| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |
 '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'       
    */
    
    
    public static BigInteger[] getTotalOperationsSPRMP(ProbCircuit pCircuit, boolean reliableIN) {
        BigInteger[] result = new BigInteger[2];
        BigInteger[] fooBig;
        
        BigInteger sums;
        BigInteger multiplications;
        
        /**
         * First pick the operation in a basic SPR
         */
        fooBig = getTotalOperationsSPR(pCircuit);
        
        /**
         * Set the inicial operation based on results of SPR
         */
        sums = fooBig[0];
        multiplications = fooBig[1];
        
        /**
         * For each SPR-MP has to add one multiplication per fanout       
         */
        multiplications = multiplications.add(new BigInteger(Integer.toString(pCircuit.getFanouts().size())));
        
        /**
         * Multiply the number of passes for current sums and multiplications
         */        
        BigInteger totalPasses = SPRMultiPassOps.getTotalPasses(pCircuit, reliableIN)[0];
        System.out.println("TOTAL PASSES " + totalPasses);
        sums = sums.multiply(totalPasses);
        multiplications = multiplications.multiply(totalPasses);
        
        /**
         * Finally add totalPasses - 1 sums. Because the reliability acumulate 
         * each pass reliability
         */
        sums = sums.add(totalPasses.subtract(BigInteger.ONE));
        
        result[0] = sums;
        result[1] = multiplications;
        
                
        return result;
    }
    
    public static BigInteger[] getTotalOperationsSPRMP(ProbCircuit pCircuit, BigInteger passes) {
        BigInteger[] result = new BigInteger[2];
        BigInteger[] fooBig;
        
        BigInteger sums;
        BigInteger multiplications;
        
        /**
         * First pick the operation in a basic SPR
         */
        fooBig = getTotalOperationsSPR(pCircuit);
        
        /**
         * Set the inicial operation based on results of SPR
         */
        sums = fooBig[0];
        multiplications = fooBig[1];
        
        /**
         * For each SPR-MP has to add one multiplication per fanout       
         */
        multiplications = multiplications.add(new BigInteger(Integer.toString(pCircuit.getFanouts().size())));
        
        /**
         * Multiply the number of passes for current sums and multiplications
         */                
        sums = sums.multiply(passes);
        multiplications = multiplications.multiply(passes);
        
        /**
         * Finally add totalPasses - 1 sums. Because the reliability acumulate 
         * each pass reliability
         */
        sums = sums.add(passes.subtract(BigInteger.ONE));
        
        result[0] = sums;
        result[1] = multiplications;
        
                
        return result;
    }
}
