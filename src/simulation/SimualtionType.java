package simulation;

import writers.WriteFile;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SimualtionType {

    private SimulationCircuit simulationCircuit;

    public String OUTPUT_INFO = "";

    public String output_sample = "";

    public ArrayList <String> info = new ArrayList<>();

    public SimualtionType(SimulationCircuit simulationCircuit){
        this.simulationCircuit = simulationCircuit;
    }

    public String getFMR(){
        return this.OUTPUT_INFO;
    }


    public void monteCarloReliability_SpiceGeneration_ElectricalSimulationAPI(String spiceScriptsFolder, String PTMLibrary, String SpiceNetListLibrary) throws Exception{

        this.printSpecSimulation();

        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

        //simulacaoMultithreading.runMultithreadingMonteCarlo(Math.round(simulationCircuit.getMtf_sizes().get(0)), "ALL_SIGNALS");
        System.out.println("> : " + simulationCircuit.getMtf_sizes().get(0));
        simulacaoMultithreading.monteCarloReliabilitySpiceGeneration(Math.round(simulationCircuit.getMtf_sizes().get(0)), simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault(), simulationCircuit.getRelativePath() + "lookup_table.csv");
        simulacaoMultithreading.printSensitiveAreasAnalysis();
        simulacaoMultithreading.generateSensitiveNodesForSETSpiceFile(spiceScriptsFolder, PTMLibrary, SpiceNetListLibrary);


        //Comentado para simulação // simulacaoMultithreading.generateSensitiveNodesForSETSpiceFile(spiceScriptsFolder, PTMLibrary, SpiceNetListLibrary);

                         //simulacaoMultithreading.PrintGatesCounterDetailsSortedCompliled(0, simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());
                this.OUTPUT_INFO = simulacaoMultithreading.getER(" MTFT Sample (Monte Carlo = N)");

    }

    public void monteCarloReliability_Electrical_SensitiveAreaAPI(String spiceScriptsFolder, String PTMLibrary, String SpiceNetListLibrary) throws Exception{

        this.printSpecSimulation();

        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

        //simulacaoMultithreading.runMultithreadingMonteCarlo(Math.round(simulationCircuit.getMtf_sizes().get(0)), "ALL_SIGNALS");
        System.out.println("> : " + simulationCircuit.getMtf_sizes().get(0));
        simulacaoMultithreading.monteCarloReliabilitySpiceGeneration(Math.round(simulationCircuit.getMtf_sizes().get(0)), simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault(), simulationCircuit.getRelativePath() + "lookup_table.csv");

        //Comentado para simulação // simulacaoMultithreading.generateSensitiveNodesForSETSpiceFile(spiceScriptsFolder, PTMLibrary, SpiceNetListLibrary);

        simulacaoMultithreading.printSensitiveAreasAnalysis();

        //simulacaoMultithreading.PrintGatesCounterDetailsSortedCompliled(0, simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());
        this.OUTPUT_INFO = simulacaoMultithreading.getER(" MTFT Sample (Monte Carlo = N)");

    }

    public void monteCarloReliabilitySensitiveAreasVectorsAPI(String Sensitive_Library) throws Exception{

        this.printSpecSimulation();

        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

        //simulacaoMultithreading.runMultithreadingMonteCarlo(Math.round(simulationCircuit.getMtf_sizes().get(0)), "ALL_SIGNALS");
        //System.out.println("> : " + simulationCircuit.getMtf_sizes().get(0));
        //simulacaoMultithreading.PrintGatesCounterDetailsSortedCompliled();
        simulacaoMultithreading.monteCarloReliability(Math.round(simulationCircuit.getMtf_sizes().get(0)), simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault(),
                Sensitive_Library);
        simulacaoMultithreading.printSensitiveAreasAnalysis();
        //String output = simulacaoMultithreading.PrintGatesCounterDetailsSortedCompliled();
        this.OUTPUT_INFO = simulacaoMultithreading.getMTBF(" MTFT Sample (Monte Carlo = N): ");
        System.out.println(this.OUTPUT_INFO);
    }


    public void monteCarloReliabilityAPI(String Sensitive_Library) throws Exception{

        this.printSpecSimulation();

        /* Constructor */
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

        /* Monte Carlo Simulation */
        simulacaoMultithreading.monteCarloReliability(Math.round(simulationCircuit.getMtf_sizes().get(0)), simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault(), Sensitive_Library);
        simulacaoMultithreading.classifyTotalSensitiveAreas();
        /* Sensitive Areas Analysis based in all vectors (20k)*/
        //info.add(simulacaoMultithreading.printGates(Math.round(simulationCircuit.getMtf_sizes().get(0)), simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault(), Sensitive_Library));

        //simulacaoMultithreading.printSensitiveAreasAnalysis();

        this.OUTPUT_INFO = simulacaoMultithreading.getMTBF(" MTFT Sample (Monte Carlo = N): ");
        System.out.println(this.OUTPUT_INFO);
    }

    public void vector_Analisys(String Sensitive_Library) throws Exception{

        this.printSpecSimulation();

        /* Constructor */
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

        /* Monte Carlo Simulation */
        //simulacaoMultithreading.calculateTotalSensitiveAreaRegular();
        simulacaoMultithreading.vectorAnalysis(Math.round(simulationCircuit.getMtf_sizes().get(0)), simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault(), Sensitive_Library);
        /* Sensitive Areas Analysis based in all vectors (20k)*/
        //info.add(simulacaoMultithreading.printGates(Math.round(simulationCircuit.getMtf_sizes().get(0)), simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault(), Sensitive_Library));

        //simulacaoMultithreading.printSensitiveAreasAnalysis();

        this.OUTPUT_INFO = simulacaoMultithreading.getMTBF(" MTFT Sample (Monte Carlo = N): ");
        System.out.println(this.OUTPUT_INFO);
    }

    public String printGates(String Sensitive_Library) throws Exception{

        this.printSpecSimulation();

        /* Constructor */
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

                        /* Monte Carlo Simulation */
                        //simulacaoMultithreading.monteCarloReliability(Math.round(simulationCircuit.getMtf_sizes().get(0)), simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault(), Sensitive_Library);

                        /* Sensitive Areas Analysis based in all vectors (20k)*/
                        return  ((simulacaoMultithreading.printGates(Math.round(simulationCircuit.getMtf_sizes().get(0)),
                                simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault(), Sensitive_Library)));

        //simulacaoMultithreading.printSensitiveAreasAnalysis();

         //           this.OUTPUT_INFO = simulacaoMultithreading.getMTBF(" MTFT Sample (Monte Carlo = N): ");
       // System.out.println(this.OUTPUT_INFO);


    }

    public void GateCounter() throws Exception{

        this.printSpecSimulation();

        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

        //simulacaoMultithreading.runMultithreadingMonteCarlo(Math.round(simulationCircuit.getMtf_sizes().get(0)), "ALL_SIGNALS");
        System.out.println("> Gate Counter ");

        //simulacaoMultithreading.monteCarloReliability(Math.round(simulationCircuit.getMtf_sizes().get(0)), simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault(), simulationCircuit.getRelativePath() + "lookup_table.csv");
        //String output = simulacaoMultithreading.PrintGatesCounterDetailsSortedCompliled();
        this.OUTPUT_INFO =  simulacaoMultithreading.PrintGatesTotal();
        System.out.println(this.OUTPUT_INFO);
    }

    public void runElectricalSimulation(String pathElectricSimulator, String pathSpiceCircuit) throws Exception{

        this.printSpecSimulation();

        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

            //checkFiles a = new checkFiles();
           // a.fileExists(pathElectricSimulator + pathSpiceCircuit);

                simulacaoMultithreading.runElectricalSimulator(pathElectricSimulator, pathSpiceCircuit);

                //this.OUTPUT_INFO = simulacaoMultithreading.getER(" MTFT Sample (Monte Carlo = N)");

    }

    public void faultToleranceMonteCarloMETAPI(String External_Link) throws Exception{


       // System.out.println("\n\n ------");

        /*
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() +  simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());
        */

        if(External_Link.equals("CREsT")) {
            Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                    simulationCircuit.getGenlib(), simulationCircuit.getCircuit());
            simulacaoMultithreading.runMultipleFaultInjectionMultithreadingMonteCarloSimulationProportion(Math.round(simulationCircuit.getMtf_sizes().get(0)),
                    simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault());
            this.OUTPUT_INFO = simulacaoMultithreading.getER(" MTFT Sample (Monte Carlo = N)");
        }
        else{
            Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                    simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());
            simulacaoMultithreading.runMultipleFaultInjectionMultithreadingMonteCarloSimulationProportion(Math.round(simulationCircuit.getMtf_sizes().get(0)),
                    simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault());
            this.OUTPUT_INFO = simulacaoMultithreading.getER(" MTFT Sample (Monte Carlo = N)");
        }




    }


    public void faultToleranceMonteCarloMETAPIExceptoinVoterFree(String External_Link) throws Exception{


        // System.out.println("\n\n ------");

        /*
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() +  simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());
        */

        if(External_Link.equals("CREsT")) {
            Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                    simulationCircuit.getGenlib(), simulationCircuit.getCircuit());
            simulacaoMultithreading.runMultipleFaultInjectionMultithreadingMonteCarloSimulationProportion(Math.round(simulationCircuit.getMtf_sizes().get(0)),
                    simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault());
            this.OUTPUT_INFO = simulacaoMultithreading.getER(" MTFT Sample (Monte Carlo = N)");
        }
        else{
            Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                    simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());
            simulacaoMultithreading.runMultipleFaultInjectionMultithreadingMonteCarloSimulationProportion(Math.round(simulationCircuit.getMtf_sizes().get(0)),
                    simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault());
            this.OUTPUT_INFO = simulacaoMultithreading.getER(" MTFT Sample (Monte Carlo = N)");
        }




    }

    public void faultToleranceMonteCarloAPI() throws Exception{
            this.printSpecSimulation();
           //System.out.println(" >>>> PATH AND FILE: " + simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath()+ simulationCircuit.getGenlib(), simulationCircuit.getRelativePath()+  simulationCircuit.getCircuit());

                simulacaoMultithreading.runMultithreadingMonteCarlo(simulationCircuit.getSample(), simulationCircuit.getSignalsToinjectFault()); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"

                this.OUTPUT_INFO = simulacaoMultithreading.getER(" Sample(Monte Carlo = N)");
    }

    /**
     * Method to link the Logic Simulator and run method Exhaustive Simulation for SET evaluation (Extracts the Fault Masking Rate - FMR)
     * @throws Exception e e
     */
    public void faultToleranceExhaustiveSETAPI() throws Exception { //ou Signals =  "ALL_SIGNALS" for exaustive consider all_signals

        /*
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

         */
        System.out.println("Exaustive");
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath()+ simulationCircuit.getGenlib(), simulationCircuit.getRelativePath()+ simulationCircuit.getCircuit());

           //this.output_sample = simulacaoMultithreading.SampleSizeExausticSimulation(simulationCircuit.getSignalsToinjectFault());
            simulacaoMultithreading.runMultithreadingExausticSimulation(simulationCircuit.getSignalsToinjectFault());

        //simulacaoMultithreading.printSensitiveAreasAnalysis();
        this.OUTPUT_INFO = simulacaoMultithreading.getER("Sample (N = "
                + "2^Signals * Gates)");
    }
    public void logicalSimulator(ArrayList <String> vectors) throws Exception { //ou Signals =  "ALL_SIGNALS" for exaustive consider all_signals

        /*
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

         */
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath()+ simulationCircuit.getGenlib(), simulationCircuit.getRelativePath()+ simulationCircuit.getCircuit());


        //this.output_sample = simulacaoMultithreading.SampleSizeExausticSimulation(simulationCircuit.getSignalsToinjectFault());
        simulacaoMultithreading.LogicalSimulationFaultFree(simulationCircuit.getSignalsToinjectFault(), vectors);

        //simulacaoMultithreading.printSensitiveAreasAnalysis();
        this.OUTPUT_INFO = simulacaoMultithreading.getER("Sample (N = "
                + "2^Signals * Gates)");
    }

    /**
     *
     * @throws Exception
     */
    public void faultToleranceExhaustiveSETAPIFAULT() throws Exception { //ou Signals =  "ALL_SIGNALS" for exaustive consider all_signals

        /*
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

         */
        System.out.println("Exaustive");
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath()+ simulationCircuit.getGenlib(), simulationCircuit.getRelativePath()+ simulationCircuit.getCircuit());

        //this.output_sample = simulacaoMultithreading.SampleSizeExausticSimulation(simulationCircuit.getSignalsToinjectFault());
        simulacaoMultithreading.runMultithreadingExhausticSimulationAndNotMaskedSensitiveArea(simulationCircuit.getSignalsToinjectFault());
            simulacaoMultithreading.printSensitiveAreasAnalysis();

        //simulacaoMultithreading.printSensitiveAreasAnalysis();
        this.OUTPUT_INFO = simulacaoMultithreading.getER("Sample (N = "
                + "2^Signals * Gates)");
    }


    /**
     *
     * @throws Exception
     */
    public void exhaustiveSensitiveAreaAnalysis_AVG_and_NotMasked() throws Exception { //ou Signals =  "ALL_SIGNALS" for exaustive consider all_signals

        /*
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

         */
        System.out.println("Exaustive");
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath()+ simulationCircuit.getGenlib(), simulationCircuit.getRelativePath()+ simulationCircuit.getCircuit());

        //this.output_sample = simulacaoMultithreading.SampleSizeExausticSimulation(simulationCircuit.getSignalsToinjectFault());
        //simulacaoMultithreading.classifyGatesSensitiveAreas();


        try {
            /* Monte Carlo Simulation */
            //simulacaoMultithreading.monteCarloReliability(Math.round(simulationCircuit.getMtf_sizes().get(0)), simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault(), Sensitive_Library);
            simulacaoMultithreading.runMultithreadingSimulationAndNotMaskedSensitiveArea(simulationCircuit.getSignalsToinjectFault());

            // simulacaoMultithreading.classifyTotalSensitiveAreas();


            //simulacaoMultithreading.printSensitiveAreasAnalysis();
            simulacaoMultithreading.printPropagationGates("Exhaustive");

        }catch (Exception e){
            System.out.println("Error !!!");

        }




        //simulacaoMultithreading.printSensitiveAreasAnalysis();
        this.OUTPUT_INFO = simulacaoMultithreading.getER("Sample (N = "
                + "2^Signals * Gates)");
    }
    public void monteCarloSensitiveAreaAnalysis_AVG_and_NotMasked() throws Exception { //ou Signals =  "ALL_SIGNALS" for exaustive consider all_signals

        /*
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

         */
        String Sensitive_Library  = "teste/lookup_table.csv";
        //System.out.println("Exaustive");
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath()+ simulationCircuit.getGenlib(), simulationCircuit.getRelativePath()+ simulationCircuit.getCircuit());

        //this.output_sample = simulacaoMultithreading.SampleSizeExausticSimulation(simulationCircuit.getSignalsToinjectFault());
        //simulacaoMultithreading.classifyGatesSensitiveAreas();

        /* Monte Carlo Simulation */
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        System.out.println("               \n------> Start simulation " + formattedDate);

        simulacaoMultithreading.monteCarloSensitiveAreaAnalysis_AVG_and_NotMasked(Math.round(simulationCircuit.getMtf_sizes().get(0)), simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault(), Sensitive_Library);


        LocalDateTime myDateObj1 = LocalDateTime.now();
        String formattedDate1 = myDateObj1.format(myFormatObj);

        System.out.println("               \n------> End Simulation " + formattedDate1);

        //simulacaoMultithreading.classifyTotalSensitiveAreas();

        System.out.println("               \n------> Start SA print " + formattedDate1);
        simulacaoMultithreading.printPropagationGates("Monte_Carlo");

        LocalDateTime myDateObj2 = LocalDateTime.now();
        String formattedDate2 = myDateObj2.format(myFormatObj);
        System.out.println("               \n------> END SA print " + formattedDate2);


        long timeElapsed_sim = Duration.between(myDateObj, myDateObj1).toSeconds();
        long timeElapsed_print = Duration.between(myDateObj1, myDateObj2).toSeconds();
        long timeElapsed_total = Duration.between(myDateObj, myDateObj2).toSeconds();


        System.out.println("-Simulation time elapsed: " + (timeElapsed_sim)+ " s");
        System.out.println("-Print and files time elapsed: " + (timeElapsed_print)+ " s");
        System.out.println("-Total time elapsed: " + (timeElapsed_total) + " s");

        this.OUTPUT_INFO = simulacaoMultithreading.getER("Sample (N = "
                + "2^Signals * Gates)");
        System.out.println("------------");


        ArrayList<String> content =new ArrayList<>();

        content.add("Simulation Time (s): " +";"+ String.valueOf(timeElapsed_sim));
        content.add("Generating Files (s): " +";"+ String.valueOf(timeElapsed_print));
        content.add("Total time (s): " +";"+ String.valueOf(timeElapsed_total));
        WriteFile file_AS = new WriteFile(this.simulationCircuit.relativePath+this.simulationCircuit.getCircuit() + "_Measure_time" , content , ".txt");
    }

    /**
     *
     * @throws Exception
     */
    public void faultToleranceExhaustiveSETAPIFAULTADAPTIVE() throws Exception { //ou Signals =  "ALL_SIGNALS" for exaustive consider all_signals

        /*
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

         */
        System.out.println("Exaustive");
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath()+ simulationCircuit.getGenlib(), simulationCircuit.getRelativePath()+ simulationCircuit.getCircuit());

        //this.output_sample = simulacaoMultithreading.SampleSizeExausticSimulation(simulationCircuit.getSignalsToinjectFault());
        simulacaoMultithreading.runMultithreadingExhausticSimulationAdaptiveSensitiveArea(simulationCircuit.getSignalsToinjectFault());
        simulacaoMultithreading.printSensitiveAreasAnalysis();

        //simulacaoMultithreading.printSensitiveAreasAnalysis();
        this.OUTPUT_INFO = simulacaoMultithreading.getER("Sample (N = "
                + "2^Signals * Gates)");
    }


    /**
     *
     * @apiNote  STABLE - Use this method to extract the circuit Sensitive Area x Vectors (True Table)
     * @param spiceScriptsFolder
     * @param PTMLibrary
     * @param SpiceNetListLibrary
     * */
    public void faultToleranceExhaustiveSET_SensitiveAreaAPI(String spiceScriptsFolder, String PTMLibrary, String SpiceNetListLibrary) throws Exception { //ou Signals =  "ALL_SIGNALS" for exaustive consider all_signals

        /*
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());
         */
        System.out.println("Exaustive");
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath()+ simulationCircuit.getGenlib(), simulationCircuit.getRelativePath()+ simulationCircuit.getCircuit());

        //this.output_sample = simulacaoMultithreading.SampleSizeExausticSimulation(simulationCircuit.getSignalsToinjectFault());
        simulacaoMultithreading.ExhaustiveSensitiveAreaAnalysis(Math.round(simulationCircuit.getMtf_sizes().get(0)), simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault(), simulationCircuit.getRelativePath() + "lookup_table.csv");
         simulacaoMultithreading.printSensitiveAreasAnalysis();
        this.OUTPUT_INFO = simulacaoMultithreading.getER("Sample (N = "
                + "2^Signals * Gates)");
    }

    /**
     * Method to link the Logic Simulator and run method Exhaustive Complete Simulation for MET evaluation (All combinations) (Extracts the Fault Masking Rate - FMR)
     * @throws Exception e e
     */
    public void faultToleranceExhaustiveCompleteMETAPI() throws Exception{ //ou Signals =  "ALL_SIGNALS" for exhaustive consider all_signals
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() +   simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

       // this.output_sample = simulacaoMultithreading.SampleSizeExhaustiveCompleteMET(simulationCircuit.getSignalsToinjectFault());
            simulacaoMultithreading.faultToleranceExhaustiveCompleteMET(simulationCircuit.getSignalsToinjectFault());
            this.OUTPUT_INFO = simulacaoMultithreading.getER("Sample (N = "
                    + "2^Signals * Gates)");
    }

    public int faultToleranceExhaustiveCompleteMETAPIESTIMATION() throws Exception{ //ou Signals =  "ALL_SIGNALS" for exhaustive consider all_signals
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() +   simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

        // this.output_sample = simulacaoMultithreading.SampleSizeExhaustiveCompleteMET(simulationCircuit.getSignalsToinjectFault());

        //simulacaoMultithreading.SampleSizeExhaustiveCompleteMET();
        int sample = simulacaoMultithreading.samplefaultToleranceExhaustiveCompleteMET(simulationCircuit.getSignalsToinjectFault());

        return sample;
        //this.OUTPUT_INFO = simulacaoMultithreading.getER("Sample (N = " + "2^Signals * Gates)");

    }

    public String getOutput_sample() {
        return output_sample;
    }

    public void printSpecSimulation(){

        if(this.simulationCircuit.getMode().equals("STF")){
            System.out.println();
            System.out.println("------------SimulationMode------------------");
            System.out.println("- STF's Simulation -");
            System.out.println("- Circuit: " + this.simulationCircuit.getCircuit());
            System.out.println("- MC Single Fault Injection: " + this.simulationCircuit.getMode());
            System.out.println("- Path: " + this.simulationCircuit.getRelativePath());
            System.out.println("- Genlib: " + this.simulationCircuit.getGenlib());
            System.out.println("----------------");
        }

        if(this.simulationCircuit.getMode().equals("MTF")){
            System.out.println();
            System.out.println("------------SimulationMode------------------");
            System.out.println("- MTF's Simulation Proportion ");
            System.out.println("- Circuit: " + this.simulationCircuit.getCircuit());
            System.out.println("- MC Multiple Fault Injection : " + this.simulationCircuit.getMtf_sizes());
            System.out.println("- Path: " + this.simulationCircuit.getRelativePath());
            System.out.println("- Genlib: " + this.simulationCircuit.getGenlib());
            System.out.println("----------------");

        }
    }

}
