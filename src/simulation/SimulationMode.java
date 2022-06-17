package simulation;

import java.util.ArrayList;

public class SimulationMode {

    private SimulationCircuit simulationCircuit;

    public String OUTPUT_INFO = "";

    public String output_sample = "";

    public ArrayList <String> info = new ArrayList<>();

    public SimulationMode(SimulationCircuit simulationCircuit){
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
        simulacaoMultithreading.monteCarloReliability(Math.round(simulationCircuit.getMtf_sizes().get(0)), simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault(), simulationCircuit.getRelativePath() + "lookup_table.csv");
                simulacaoMultithreading.generateSensitiveNodesForSETSpiceFile(spiceScriptsFolder, PTMLibrary, SpiceNetListLibrary);
                    simulacaoMultithreading.printSensitiveAreasAnalysis();
                         //simulacaoMultithreading.PrintGatesCounterDetailsSortedCompliled(0, simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());
                this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" MTFT Sample (Monte Carlo = N)");

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
        simulacaoMultithreading.classifySensitiveAreas();
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

                //this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" MTFT Sample (Monte Carlo = N)");

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
            this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" MTFT Sample (Monte Carlo = N)");
        }
        else{
            Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                    simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());
            simulacaoMultithreading.runMultipleFaultInjectionMultithreadingMonteCarloSimulationProportion(Math.round(simulationCircuit.getMtf_sizes().get(0)),
                    simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault());
            this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" MTFT Sample (Monte Carlo = N)");
        }




    }

    public void faultToleranceMonteCarloAPI() throws Exception{
            this.printSpecSimulation();
           //System.out.println(" >>>> PATH AND FILE: " + simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath()+ simulationCircuit.getGenlib(), simulationCircuit.getRelativePath()+  simulationCircuit.getCircuit());

                simulacaoMultithreading.runMultithreadingMonteCarlo(simulationCircuit.getSample(), simulationCircuit.getSignalsToinjectFault()); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"

                this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" Sample(Monte Carlo = N)");
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
        this.OUTPUT_INFO = simulacaoMultithreading.getFRM("Sample (N = "
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
            this.OUTPUT_INFO = simulacaoMultithreading.getFRM("Sample (N = "
                    + "2^Signals * Gates)");
    }

    public int faultToleranceExhaustiveCompleteMETAPIESTIMATION() throws Exception{ //ou Signals =  "ALL_SIGNALS" for exhaustive consider all_signals
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() +   simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

        // this.output_sample = simulacaoMultithreading.SampleSizeExhaustiveCompleteMET(simulationCircuit.getSignalsToinjectFault());

        //simulacaoMultithreading.SampleSizeExhaustiveCompleteMET();
        int sample = simulacaoMultithreading.samplefaultToleranceExhaustiveCompleteMET(simulationCircuit.getSignalsToinjectFault());

        return sample;
        //this.OUTPUT_INFO = simulacaoMultithreading.getFRM("Sample (N = " + "2^Signals * Gates)");

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
