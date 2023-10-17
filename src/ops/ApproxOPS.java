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
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public class ApproxOPS {
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
            //For while we use the SPR method to find critical vectors
            SPRController spr = new SPRController(exactVerilog, cellLib);

            Map<Integer, BigDecimal> mapp = ShellScriptOps.getOrderedCircuitReliabilities(exactVerilog, cellLib, false);

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
                                                 String criticalVectorMethod,
                                                 boolean multDontCare,
                                                 int vectorAmount) throws Exception {

        //Setting basics parameters to read a mapped verilog with CREST tool
        CellLibrary cellLib = new CellLibrary(cellLibraryPath);
        ProbCircuit exactVerilog = new CircuitFactory(cellLib, verilogSeed).getProbCircuit();

        int totalInputs = exactVerilog.getTotalInputVectors().intValue();

        if(vectorAmount > 0 && vectorAmount < totalInputs) {
            //For while we use the SPR method to find critical vectors
            SPRController spr = new SPRController(exactVerilog, cellLib);

            Map<Integer, BigDecimal> mapp = ShellScriptOps.getOrderedCircuitReliabilities(exactVerilog, cellLib, false);

            switch (criticalVectorMethod) {
                case "RANDOM":
                    mapp = ShellScriptOps.getRandomInputVectorsReliabilities(exactVerilog, cellLib, vectorAmount);
                    break;

                case "CRITICAL":
                    mapp = ShellScriptOps.getOrderedCircuitReliabilities(exactVerilog, cellLib, false);
                    break;

                case "MOST_RELIABLE":
                    mapp = ShellScriptOps.getOrderedCircuitReliabilities(exactVerilog, cellLib, true);

                default:
                    System.out.println("NÃO DEU");
            }



            ArrayList<Integer> approxVectors = new ArrayList<>(mapp.keySet());

            System.out.println("approxVectors size: " + approxVectors.size());


            //Create a PLA obj to add critical vectors
            //PLA pla = new PLAManipulator().readPLAFile(plaSeed);
            PLA pla = PLAOps.readPLAFile(plaSeed);

            //Add the N (vectorAmount) critical vectors as dont'care
            for (int w = 0; w < vectorAmount; w++) {
                System.out.println(approxVectors.get(w));
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
}
