package ops;

import datastructures.CellLibrary;
import datastructures.InputVector;
import manipulator.SPRController;
import signalProbability.ProbCircuit;
import twoLevelDatastructures.PLA;
import twoLevelDatastructures.PLAManipulator;
import twoLevelDatastructures.PLAOps;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;

import static ops.CommonOps.getValueLinkHashMapByIndex;

public class ShellScriptOps {
    private static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines()
                    .forEach(consumer);
        }
    }

    public static ArrayList<String> executeCommands(String path, String commands) throws IOException, InterruptedException {

        ArrayList<String> result = new ArrayList<>();

        String scriptPath = path;

        Process process = Runtime.getRuntime().exec(scriptPath + " " + commands);
        process.waitFor();

        int exitValue = process.exitValue();
        if (exitValue == 0) {
            try {
                String line;

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(process.getInputStream()) );
                while ((line = in.readLine()) != null) {
                    result.add(line);
                }
                in.close();
            }
            catch (Exception e) {
                // ...
            }
        } else {
            result.add("Script failed with exit value: " + exitValue);
        }

        return result;
    }

    public static void randomInputApprox() throws IOException, InterruptedException {

        LinkedHashMap<Integer, BigDecimal> mapper = new LinkedHashMap<Integer, BigDecimal>();

        ArrayList<Integer> randomElements = new ArrayList<>();
        Random rand = new Random(24587);


        int counter = 0;
        int counter2 = 1;


        ArrayList<Integer> givenList = new ArrayList<>(mapper.keySet());

        int numberOfElements = 90;

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(givenList.size());
            int randomElement = givenList.get(randomIndex);
            randomElements.add(randomElement);
            givenList.remove(randomIndex);
        }

        PLAManipulator plaManipulator = new PLAManipulator();
        PLA pla = plaManipulator.readPLAFile("9sym_AMMES.pla");
        pla = plaManipulator.readPLAFile("9sym_AMMES.pla");

        for(int randEle : randomElements) {
            Map.Entry<Integer, BigDecimal> randomValue = getValueLinkHashMapByIndex(mapper, randEle);
            InputVector inputRandom = new InputVector(Integer.toString(randomValue.getKey()), pla.getQtInputs());
            pla.addDontCareTerm(inputRandom.getBinaryString());

            String outputPlaname = String.format("%02d-9sym_random_%02d", counter2, counter2);
            PLAOps.writePLA("pla/9sym_random_originals/" + outputPlaname + ".pla", pla);

            ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                    String.format("ESPRESSO pla/9sym_random_originals/%s.pla pla/%s_ESPRESSO.pla", outputPlaname, outputPlaname));

            ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                    String.format("ABC pla/%s_ESPRESSO.pla mylib.genlib verilogs/%s.v", outputPlaname, outputPlaname));

            System.out.println("PLA: " + outputPlaname);
            counter2++;
        }
    }

    public static void deployPLA(PLA pla, String outName) throws IOException, InterruptedException {

        //String outputPlaname = String.format("%02d-9sym_crit_%02d", i, i);
        String outputPlaname = outName;

        PLAManipulator pManipulator = new PLAManipulator();

        //pManipulator.writePLA("pla/pla_c/9sym_track_crit_originals/" + outputPlaname + ".pla", pla);
        PLAOps.writePLA("pla/pla_c/9sym_track_crit_originals/" + outputPlaname + ".pla", pla);

        ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                String.format("ESPRESSO pla/pla_c/9sym_track_crit_originals/%s.pla pla/pla_c/%s_ESPRESSO.pla", outputPlaname, outputPlaname));

        //ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
        //        String.format("ABC pla/%s_ESPRESSO.pla mylib.genlib verilogs/%s.v", outputPlaname, outputPlaname));

        ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                String.format("ABC_C pla/pla_c/%s_ESPRESSO.pla mylib.genlib verilogsC/%s.v", outputPlaname, outputPlaname));

        System.out.println("PLA: " + outputPlaname);
    }

    public static void deployPLAEspressoVerilog(PLA pla, String outName, String outPLADir, String verilogDir, String espressoDir) throws IOException, InterruptedException {

        //String outputPlaname = String.format("%02d-9sym_crit_%02d", i, i);
        String outputPlaname = outName;

        PLAManipulator pManipulator = new PLAManipulator();

        //pManipulator.writePLA("pla/pla_c/9sym_track_crit_originals/" + outputPlaname + ".pla", pla);
        //pManipulator.writePLA(outPLADir + "/" + outputPlaname + ".pla", pla);
        PLAOps.writePLA(outPLADir + "/" + outputPlaname + ".pla", pla);

        ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                String.format("ESPRESSO %s/%s.pla %s/%s_ESPRESSO.pla", outPLADir, outputPlaname, espressoDir, outputPlaname));
                //String.format("ESPRESSO pla/pla_c/9sym_track_crit_originals/%s.pla pla/pla_c/%s_ESPRESSO.pla", outputPlaname, outputPlaname));

        //ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
        //        String.format("ABC pla/%s_ESPRESSO.pla mylib.genlib verilogs/%s.v", outputPlaname, outputPlaname));

        ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                String.format("ABC_C2 %s/%s_ESPRESSO.pla mylib.genlib %s/%s.v", espressoDir, outputPlaname, verilogDir, outputPlaname));
                //String.format("ABC_C pla/pla_c/%s_ESPRESSO.pla mylib.genlib verilogsC/%s.v", outputPlaname, outputPlaname));

        System.out.println("PLA: " + outputPlaname);
    }

    public static void deployPLAEspressoAigVerilog(PLA pla, String outName, String outPLADir, String verilogDir, String aigDir, String espressoDir, String libraryDir) throws IOException, InterruptedException {

        //String outputPlaname = String.format("%02d-9sym_crit_%02d", i, i);
        String outputPlaname = outName;

        PLAManipulator pManipulator = new PLAManipulator();

        //pManipulator.writePLA("pla/pla_c/9sym_track_crit_originals/" + outputPlaname + ".pla", pla);
        //pManipulator.writePLA(outPLADir + "/" + outputPlaname + ".pla", pla);
        PLAOps.writePLA(outPLADir + "/" + outputPlaname + ".pla", pla);

        ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                String.format("ESPRESSO %s/%s.pla %s/%s_ESPRESSO.pla", outPLADir, outputPlaname, espressoDir, outputPlaname));
        //String.format("ESPRESSO pla/pla_c/9sym_track_crit_originals/%s.pla pla/pla_c/%s_ESPRESSO.pla", outputPlaname, outputPlaname));

        //ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
        //        String.format("ABC pla/%s_ESPRESSO.pla mylib.genlib verilogs/%s.v", outputPlaname, outputPlaname));

        ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                String.format("ABC_C3 %s/%s_ESPRESSO.pla %s/%s.aig %s %s/%s.v", espressoDir,
                                                                                outputPlaname,
                                                                                aigDir,
                                                                                outputPlaname,
                                                                                libraryDir,
                                                                                verilogDir,
                                                                                outputPlaname));
        //String.format("ABC_C pla/pla_c/%s_ESPRESSO.pla mylib.genlib verilogsC/%s.v", outputPlaname, outputPlaname));

        System.out.println("PLA: " + outputPlaname);
    }

    public static void makePLAMinimizationAndDeployToAigVerilog(PLA pla, String outPLADir, String verilogDir, String aigDir, String espressoDir, String libraryDir) throws IOException, InterruptedException {

        PLAManipulator pManipulator = new PLAManipulator();

        PLAOps.writePLA(outPLADir, pla);

        //Make PLA minimization
        ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                String.format("ESPRESSO %s %s", outPLADir, espressoDir));

        //Based on ESPRESSO minimization, write aig and mapped verilog
        ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                String.format("ABC_PLA_AIG_VERILOG %s %s %s %s", espressoDir,
                                                                 aigDir,
                                                                 libraryDir,
                                                                 verilogDir));

        System.out.println("PLA: " + outPLADir);
    }

    public static void deployPLAAigVerilog(String outName, String inputPLADir, String verilogDir, String aigDir, String libraryDir) throws IOException, InterruptedException {

        String outputPlaname = outName;

        ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                String.format("ABC_PLA_AIG_VERILOG %s %s/%s.aig %s %s/%s.v",
                        inputPLADir,
                        aigDir,
                        outputPlaname,
                        libraryDir,
                        verilogDir,
                        outputPlaname));
        //String.format("ABC_C pla/pla_c/%s_ESPRESSO.pla mylib.genlib verilogsC/%s.v", outputPlaname, outputPlaname));

        System.out.println("Verilog: " + outputPlaname);
    }

    public static void deployBLIFAigVerilog(String outName, String inputBlifDir, String verilogDir, String aigDir, String libraryDir) throws IOException, InterruptedException {

        String outputPlaname = outName;

        ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                String.format("MAPPED_BLIF_AIG_VERILOG %s %s %s/%s.aig %s/%s.v",
                        libraryDir,
                        inputBlifDir,
                        aigDir,
                        outputPlaname,
                        verilogDir,
                        outputPlaname));
        //String.format("ABC_C pla/pla_c/%s_ESPRESSO.pla mylib.genlib verilogsC/%s.v", outputPlaname, outputPlaname));

        System.out.println("Verilog: " + outputPlaname);
    }

    /*"MAPPED_BLIF_AIG_VERILOG")
            ./abc -c "read_library $2; read $3; strash; resyn2; strash; resyn2; resyn2; resyn2; resyn2; resyn2; resyn2; resyn2; resyn2; resyn2; resyn2; resyn2; resyn2; write_aiger $4; map -a; w $5"
    ;;*/

    public static PLA inputListApprox(PLA pla, ArrayList<Integer> inputList) {

        for(int input : inputList) {
            InputVector inputCritical = new InputVector(Integer.toString(input), pla.getQtInputs());
            pla.addDontCareTerm(inputCritical.getBinaryString());
        }

        /*
        for (int i = 1; i < 91; i++) {
            Map.Entry<Integer, BigDecimal> moreCritical = getValueLinkHashMapByIndex(nMapper, i);
            InputVector inputCritical = new InputVector(Integer.toString(moreCritical.getKey()), pla.getQtInputs());
            pla.addDontCareTerm(inputCritical.getBinaryString());

            String outputPlaname = String.format("%02d-9sym_crit_%02d", i, i);
            plaManipulator.writePLA("pla/9sym_crit_originals/" + outputPlaname + ".pla", pla);

            ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                    String.format("ESPRESSO pla/9sym_crit_originals/%s.pla pla/%s_ESPRESSO.pla", outputPlaname, outputPlaname));

            ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh",
                    String.format("ABC pla/%s_ESPRESSO.pla mylib.genlib verilogs/%s.v", outputPlaname, outputPlaname));

            System.out.println("PLA: " + outputPlaname);
        } */

        return pla;
    }

    public static Map.Entry<Integer, BigDecimal> getAboluteCriticalVector(ProbCircuit pCircuit, CellLibrary cellLib) {

        Map<Integer, BigDecimal> orderedMapper = getOrderedCircuitReliabilities(pCircuit, cellLib, false);

        Map.Entry<Integer, BigDecimal> criticalVector = getValueLinkHashMapByIndex(orderedMapper, 0);

        return criticalVector;
    }

    public static Map<Integer, BigDecimal> getOrderedCircuitReliabilities(ProbCircuit pCircuit,
                                                                          CellLibrary cellLib,
                                                                          boolean reversed) {

        LinkedHashMap<Integer, BigDecimal> mapper = new LinkedHashMap<Integer, BigDecimal>();

        SPRController spr = new SPRController(pCircuit, cellLib);

        int powInt = PTMOps.PowInt(2, pCircuit.getProbInputs().size());

        for(int i = 0; i < powInt; i++) {
            BigDecimal vectorReliability = spr.getReliability(Integer.toString(i), "0.99999802495", 15);
            mapper.put(i, vectorReliability);
        }

        return CommonOps.sortReliabilitiesMap(mapper, reversed);
    }


    public static Map<Integer, BigDecimal> getRandomInputVectorsReliabilities(ProbCircuit pCircuit,
                                                                              CellLibrary cellLib,
                                                                              int vectorsAmount) {

        LinkedHashMap<Integer, BigDecimal> mapper = new LinkedHashMap<Integer, BigDecimal>();

        long rgenseed = System.currentTimeMillis();

        Random rand = new Random(rgenseed);

        SPRController spr = new SPRController(pCircuit, cellLib);

        //Make a integer list with the "vectorsAmount" of random vectors
        ArrayList<Integer> randomVectors = new ArrayList<>();
        while(randomVectors.size() != vectorsAmount) {
            int vectorCandidate = rand.nextInt(pCircuit.getTotalInputVectors().intValue());
            //verify if exists in list
            if(!randomVectors.contains(vectorCandidate)) {
                randomVectors.add(vectorCandidate);
            }
        }

        for(int rVector : randomVectors) {
            BigDecimal candidateReliability = spr.getReliability(Integer.toString(rVector), "0.99999802495", 15);
            mapper.put(rVector, candidateReliability);
        }
        return mapper;
    }


}
