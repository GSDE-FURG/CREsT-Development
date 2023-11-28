package ops;

import datastructures.CellLibrary;
import datastructures.InputVector;
import manipulator.CircuitFactory;
import manipulator.SPRController;
import signalProbability.ProbCircuit;
import twoLevelDatastructures.PLA;
import twoLevelDatastructures.PLAManipulator;
import twoLevelDatastructures.PLAOps;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ApproxOPS {

    public static void justCriticalVectorsApprox(String plaSeed,
                                                 String verilogSeed,
                                                 String cellLibraryPath,
                                                 String aigOutputPath,
                                                 String plaOutputPath,
                                                 String minimizedPlaPath,
                                                 String verilogOutputPath,
                                                 boolean multDontCare,
                                                 ArrayList<InputVector> vectors) throws Exception {

        //Setting basics parameters to read a mapped verilog with CREST tool
        CellLibrary cellLib = new CellLibrary(cellLibraryPath);
        ProbCircuit exactVerilog = new CircuitFactory(cellLib, verilogSeed).getProbCircuit();

        int totalInputs = exactVerilog.getTotalInputVectors().intValue();

        if(vectors.size() > 0 && vectors.size() < totalInputs) {

            //Create a PLA obj to add critical vectors
            //PLA pla = new PLAManipulator().readPLAFile(plaSeed);
            PLA pla = PLAOps.readPLAFile(plaSeed);

            //Add the InputVectors as dont'care
            for (InputVector inputV : vectors) {
                pla.addDontCareTerm(inputV.getBinaryString());
            }

            //Is the per output method option?
            if(!multDontCare) {
                pla = PLAOps.getApproxPLAWithDontCarePerOutput(pla);
            }

            ShellScriptOps.makePLAMinimizationAndDeployToAigVerilog(pla,
                    plaOutputPath,
                    verilogOutputPath,
                    aigOutputPath,
                    minimizedPlaPath,
                    cellLibraryPath);


        } else {
            System.out.println("CHECK CRITICAL VECTORS AMOUNT " + vectors.size());
        }
    }
    public static void justCriticalVectorsApprox(String plaSeed,
                                                 String verilogSeed,
                                                 String cellLibraryPath,
                                                 String aigOutputPath,
                                                 String plaOutputPath,
                                                 String minimizedPlaPath,
                                                 String verilogOutputPath,
                                                 String criticalVectorMethod,
                                                 boolean multDontCare,
                                                 int vectorAmount) throws Exception {

        //Setting basics parameters to read a mapped verilog with CREST tool
        CellLibrary cellLib = new CellLibrary(cellLibraryPath);
        ProbCircuit exactVerilog = new CircuitFactory(cellLib, verilogSeed).getProbCircuit();

        int totalInputs = exactVerilog.getTotalInputVectors().intValue();

        if(vectorAmount > 0 && vectorAmount < totalInputs) {

            Map<Integer, BigDecimal> mapp = new HashMap<>();

            switch (criticalVectorMethod) {
                case "RANDOM":
                    mapp = ShellScriptOps.getRandomInputVectorsReliabilities(exactVerilog, cellLib, vectorAmount);
                    break;

                case "CRITICAL":
                    mapp = ShellScriptOps.getOrderedCircuitReliabilities(exactVerilog, cellLib, false);
                    break;

                case "RELIABLE":
                    mapp = ShellScriptOps.getOrderedCircuitReliabilities(exactVerilog, cellLib, true);
                    break;

                default:
                    System.out.println("NÃO DEU");
            }



            ArrayList<Integer> approxVectors = new ArrayList<>(mapp.keySet());



            //Create a PLA obj to add critical vectors
            //PLA pla = new PLAManipulator().readPLAFile(plaSeed);
            PLA pla = PLAOps.readPLAFile(plaSeed);

            //Add the N (vectorAmount) critical vectors as dont'care
            for (int w = 0; w < vectorAmount; w++) {
                InputVector vector = new InputVector(Integer.toString(approxVectors.get(w)), pla.getQtInputs());
                pla.addDontCareTerm(vector.getBinaryString());
            }

            //Is the per output method option?
            if(!multDontCare) {
                pla = PLAOps.getApproxPLAWithDontCarePerOutput(pla);
            }

            ShellScriptOps.makePLAMinimizationAndDeployToAigVerilog(pla,
                                                                    plaOutputPath,
                                                                    verilogOutputPath,
                                                                    aigOutputPath,
                                                                    minimizedPlaPath,
                                                                    cellLibraryPath);


        } else {
            System.out.println("CHECK CRITICAL VECTORS AMOUNT " + vectorAmount);
        }
    }

    public static void trackCriticalVectorsApprox(String plaSeed,
                                                 String verilogSeed,
                                                 String cellLibraryPath,
                                                 String aigOutputPath,
                                                 String plaOutputPath,
                                                 String minimizedPlaPath,
                                                 String verilogOutputPath,
                                                 boolean multDontCare,
                                                 int vectorAmount) throws Exception {

        //Setting basics parameters to read a mapped verilog with CREST tool
        CellLibrary cellLib = new CellLibrary(cellLibraryPath);
        ProbCircuit exactVerilog = new CircuitFactory(cellLib, verilogSeed).getProbCircuit();

        //Create temp and pla and verilog folder
        new File("tEmP").mkdir();
        new File("tEmP/verilog").mkdir();
        new File("tEmP/pla").mkdir();
        new File("tEmP/aig").mkdir();

        int totalInputs = exactVerilog.getTotalInputVectors().intValue();

        if(vectorAmount > 0 && vectorAmount < totalInputs) {

            ArrayList<Integer> moreCriticals = new ArrayList<>();
            ArrayList<Integer> approxVectors = new ArrayList<>();

            // Critical reliabilities
            Map<Integer, BigDecimal> mapp = ShellScriptOps.getOrderedCircuitReliabilities(exactVerilog, cellLib, false);
            moreCriticals = new ArrayList<>(mapp.keySet());
            //Add the critical vector
            approxVectors.add(moreCriticals.get(0));



            PLA pla = PLAOps.readPLAFile(plaSeed);

            String previousPLA = "";
            String previousVerilog = "";

            for (int w = 1; w < vectorAmount+1; w++) {

                if (w != 1) {
                    pla = PLAOps.readPLAFile(previousPLA);

                    ProbCircuit previousCircuit = new CircuitFactory(cellLib, previousVerilog).getProbCircuit();

                    mapp = ShellScriptOps.getOrderedCircuitReliabilities(previousCircuit, cellLib, false);
                    moreCriticals = new ArrayList<>(mapp.keySet());

                    int candidateVector = moreCriticals.get(0);

                    /**
                     * If the candidate has previously been added to the list,
                     * the next vector with a higher priority will be utilized.
                     */
                    if (approxVectors.contains(candidateVector)) {

                        int counter = 1;
                        boolean whileFlag = true;
                        while (whileFlag) {
                            candidateVector = moreCriticals.get(counter);
                            if (!approxVectors.contains(candidateVector)) {
                                approxVectors.add(candidateVector);
                                whileFlag = false;
                            }
                            counter = counter + 1;
                        }
                    } else {
                        approxVectors.add(candidateVector);
                    }

                }

                String pattern = String.format("%09d-TeMp-%s", w, exactVerilog.getName());

                for(int v : approxVectors) {
                    InputVector vector = new InputVector(Integer.toString(v), pla.getQtInputs());
                    pla.addDontCareTerm(vector.getBinaryString());
                }

                //Is the per output method option?
                if(!multDontCare) {
                    pla = PLAOps.getApproxPLAWithDontCarePerOutput(pla);
                }

                if(w != vectorAmount) {
                    //Deploy temp
                    ShellScriptOps.makePLAMinimizationAndDeployToAigVerilog(pla,
                            String.format("tEmP/pla/%s.pla", pattern),
                            String.format("tEmP/verilog/%s.v", pattern),
                            String.format("tEmP/aig/%s.aig", pattern),
                            String.format("tEmP/pla/%s_ESPRESSO.pla", pattern),
                            cellLibraryPath);

                    previousPLA = String.format("tEmP/pla/%s_ESPRESSO.pla", pattern);
                    previousVerilog = String.format("tEmP/verilog/%s.v", pattern);

                } else {
                    //Deploy true approx
                    ShellScriptOps.makePLAMinimizationAndDeployToAigVerilog(pla,
                            plaOutputPath,
                            verilogOutputPath,
                            aigOutputPath,
                            minimizedPlaPath,
                            cellLibraryPath);

                }
            }

        } else {
            System.out.println("CHECK CRITICAL VECTORS AMOUNT " + vectorAmount);
        }
    }

    public static void trackCriticalSameSeedVectorsApprox(String plaSeed,
                                                  String verilogSeed,
                                                  String cellLibraryPath,
                                                  String aigOutputPath,
                                                  String plaOutputPath,
                                                  String minimizedPlaPath,
                                                  String verilogOutputPath,
                                                  boolean multDontCare,
                                                  int vectorAmount) throws Exception {

        //Setting basics parameters to read a mapped verilog with CREST tool
        CellLibrary cellLib = new CellLibrary(cellLibraryPath);
        ProbCircuit exactVerilog = new CircuitFactory(cellLib, verilogSeed).getProbCircuit();

        //Create temp and pla and verilog folder
        new File("tEmP").mkdir();
        new File("tEmP/verilog").mkdir();
        new File("tEmP/pla").mkdir();
        new File("tEmP/aig").mkdir();

        int totalInputs = exactVerilog.getTotalInputVectors().intValue();

        if(vectorAmount > 0 && vectorAmount < totalInputs) {

            ArrayList<Integer> moreCriticals = new ArrayList<>();
            ArrayList<Integer> approxVectors = new ArrayList<>();

            // Critical reliabilities
            Map<Integer, BigDecimal> mapp = ShellScriptOps.getOrderedCircuitReliabilities(exactVerilog, cellLib, false);
            moreCriticals = new ArrayList<>(mapp.keySet());
            //Add the critical vector
            approxVectors.add(moreCriticals.get(0));



            PLA pla = PLAOps.readPLAFile(plaSeed);

            String previousPLA = "";
            String previousVerilog = "";

            for (int w = 1; w < vectorAmount+1; w++) {

                if (w != 1) {
                    pla = PLAOps.readPLAFile(previousPLA);

                    ProbCircuit previousCircuit = new CircuitFactory(cellLib, previousVerilog).getProbCircuit();

                    mapp = ShellScriptOps.getOrderedCircuitReliabilities(previousCircuit, cellLib, false);
                    moreCriticals = new ArrayList<>(mapp.keySet());

                    int candidateVector = moreCriticals.get(0);

                    /**
                     * If the candidate has previously been added to the list,
                     * the next vector with a higher priority will be utilized.
                     */
                    if (approxVectors.contains(candidateVector)) {

                        int counter = 1;
                        boolean whileFlag = true;
                        while (whileFlag) {
                            candidateVector = moreCriticals.get(counter);
                            if (!approxVectors.contains(candidateVector)) {
                                approxVectors.add(candidateVector);
                                whileFlag = false;
                            }
                            counter = counter + 1;
                        }
                    } else {
                        approxVectors.add(candidateVector);
                    }

                }

                String pattern = String.format("%09d-TeMp-%s", w, exactVerilog.getName());

                for(int v : approxVectors) {
                    InputVector vector = new InputVector(Integer.toString(v), pla.getQtInputs());
                    pla.addDontCareTerm(vector.getBinaryString());
                }

                //Is the per output method option?
                if(!multDontCare) {
                    pla = PLAOps.getApproxPLAWithDontCarePerOutput(pla);
                }

                if(w != vectorAmount) {
                    //Deploy temp
                    ShellScriptOps.makePLAMinimizationAndDeployToAigVerilog(pla,
                            String.format("tEmP/pla/%s.pla", pattern),
                            String.format("tEmP/verilog/%s.v", pattern),
                            String.format("tEmP/aig/%s.aig", pattern),
                            String.format("tEmP/pla/%s_ESPRESSO.pla", pattern),
                            cellLibraryPath);

                    previousPLA = String.format("tEmP/pla/%s_ESPRESSO.pla", pattern);
                    previousVerilog = String.format("tEmP/verilog/%s.v", pattern);

                } else {
                    //Same seed PLA
                    pla = PLAOps.readPLAFile(plaSeed);

                    for(int v : approxVectors) {
                        InputVector vector = new InputVector(Integer.toString(v), pla.getQtInputs());
                        pla.addDontCareTerm(vector.getBinaryString());
                    }

                    //Is the per output method option?
                    if(!multDontCare) {
                        pla = PLAOps.getApproxPLAWithDontCarePerOutput(pla);
                    }

                    //Deploy true approx
                    ShellScriptOps.makePLAMinimizationAndDeployToAigVerilog(pla,
                            plaOutputPath,
                            verilogOutputPath,
                            aigOutputPath,
                            minimizedPlaPath,
                            cellLibraryPath);
                }
            }

        } else {
            System.out.println("CHECK CRITICAL VECTORS AMOUNT " + vectorAmount);
        }
    }

    public static void approxMethodWrapper(String approxMethodType,
                                           String plaSeed,
                                           String verilogSeed,
                                           String cellLibraryPath,
                                           String aigOutputPath,
                                           String plaOutputPath,
                                           String minimizedPlaPath,
                                           String verilogOutputPath,
                                           String criticalVectorMethod,
                                           int vectorAmount) throws Exception {

        switch (approxMethodType) {
            case "JUST_CRIT_PER":
                justCriticalVectorsApprox(plaSeed,
                        verilogSeed,
                        cellLibraryPath,
                        aigOutputPath,
                        plaOutputPath,
                        minimizedPlaPath,
                        verilogOutputPath,
                        criticalVectorMethod,
                        false,
                        vectorAmount);
                break;

            case "JUST_CRIT_MULTI":
                justCriticalVectorsApprox(plaSeed,
                        verilogSeed,
                        cellLibraryPath,
                        aigOutputPath,
                        plaOutputPath,
                        minimizedPlaPath,
                        verilogOutputPath,
                        criticalVectorMethod,
                        true,
                        vectorAmount);
                break;

            case "TRACK_CRIT_PER":
                trackCriticalVectorsApprox(plaSeed,
                        verilogSeed,
                        cellLibraryPath,
                        aigOutputPath,
                        plaOutputPath,
                        minimizedPlaPath,
                        verilogOutputPath,
                        false,
                        vectorAmount);
                break;

            case "TRACK_CRIT_MULTI":
                trackCriticalVectorsApprox(plaSeed,
                        verilogSeed,
                        cellLibraryPath,
                        aigOutputPath,
                        plaOutputPath,
                        minimizedPlaPath,
                        verilogOutputPath,
                        true,
                        vectorAmount);
                break;

            case "TRACK_CRIT_SAME_SEED_PER":
                trackCriticalSameSeedVectorsApprox(plaSeed,
                        verilogSeed,
                        cellLibraryPath,
                        aigOutputPath,
                        plaOutputPath,
                        minimizedPlaPath,
                        verilogOutputPath,
                        false,
                        vectorAmount);
                break;

            case "TRACK_CRIT_SAME_SEED_MULTI":
                trackCriticalSameSeedVectorsApprox(plaSeed,
                        verilogSeed,
                        cellLibraryPath,
                        aigOutputPath,
                        plaOutputPath,
                        minimizedPlaPath,
                        verilogOutputPath,
                        true,
                        vectorAmount);
                break;
            default:
                System.out.println("NÃO DEU!! " + approxMethodType);
        }

        System.out.println("Done! " + plaOutputPath);

    }

    public static void approxMethodWrapper(String approxMethodType,
                                           String plaSeed,
                                           String verilogSeed,
                                           String cellLibraryPath,
                                           String aigOutputPath,
                                           String plaOutputPath,
                                           String minimizedPlaPath,
                                           String verilogOutputPath,
                                           ArrayList<InputVector> vectors) throws Exception {


        switch (approxMethodType) {
            case "JUST_CRIT_PER":
                justCriticalVectorsApprox(plaSeed,
                        verilogSeed,
                        cellLibraryPath,
                        aigOutputPath,
                        plaOutputPath,
                        minimizedPlaPath,
                        verilogOutputPath,
                        false,
                        vectors);
                break;

            case "JUST_CRIT_MULTI":
                justCriticalVectorsApprox(plaSeed,
                        verilogSeed,
                        cellLibraryPath,
                        aigOutputPath,
                        plaOutputPath,
                        minimizedPlaPath,
                        verilogOutputPath,
                        true,
                        vectors);
                break;

            /*case "TRACK_CRIT_PER":
                trackCriticalVectorsApprox(plaSeed,
                        verilogSeed,
                        cellLibraryPath,
                        aigOutputPath,
                        plaOutputPath,
                        minimizedPlaPath,
                        verilogOutputPath,
                        false,
                        vectorAmount);
                break;

            case "TRACK_CRIT_MULTI":
                trackCriticalVectorsApprox(plaSeed,
                        verilogSeed,
                        cellLibraryPath,
                        aigOutputPath,
                        plaOutputPath,
                        minimizedPlaPath,
                        verilogOutputPath,
                        true,
                        vectorAmount);
                break;

            case "TRACK_CRIT_SAME_SEED_PER":
                trackCriticalSameSeedVectorsApprox(plaSeed,
                        verilogSeed,
                        cellLibraryPath,
                        aigOutputPath,
                        plaOutputPath,
                        minimizedPlaPath,
                        verilogOutputPath,
                        false,
                        vectorAmount);
                break;

            case "TRACK_CRIT_SAME_SEED_MULTI":
                trackCriticalSameSeedVectorsApprox(plaSeed,
                        verilogSeed,
                        cellLibraryPath,
                        aigOutputPath,
                        plaOutputPath,
                        minimizedPlaPath,
                        verilogOutputPath,
                        true,
                        vectorAmount);
                break;*/
            default:
                System.out.println("NÃO DEU!! " + approxMethodType);
        }

        System.out.println("Done! " + plaOutputPath);

    }
    public static String getApproxMethod(String methodCandidate) {

        String result = null;

        if(methodCandidate.contains("just_crit")) {
            if(methodCandidate.contains("per")) {
                result = "JUST_CRIT_PER";
            } else {
                result = "JUST_CRIT_MULTI";
            }
        } else {
            if (methodCandidate.contains("track_crit")) {
                if(methodCandidate.contains("same_seed")) {
                    if(methodCandidate.contains("per")) {
                        result = "TRACK_CRIT_SAME_SEED_PER";
                    } else {
                        result = "TRACK_CRIT_SAME_SEED_MULTI";
                    }
                } else {
                    if(methodCandidate.contains("per")) {
                        result = "TRACK_CRIT_PER";
                    } else {
                        result = "TRACK_CRIT_MULTI";
                    }
                }
            }
        }

        return result;
    }
}
