package simulation;

import java.util.ArrayList;

public class SimulationMode {

    private SimulationCircuit simulationCircuit;

    public String OUTPUT_INFO = "";

    public SimulationMode(SimulationCircuit simulationCircuit){
        this.simulationCircuit = simulationCircuit;
    }

    public String getFMR(){
        return this.OUTPUT_INFO;
    }

    public void monteCarloSimulationMultipleTransientFaultsProportionAndCalculationOfSensitiveArea(String spiceScriptsFolder, String PTMLibrary, String SpiceNetListLibrary) throws Exception{

        this.printSpecSimulation();

        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

        //simulacaoMultithreading.runMultithreadingMonteCarlo(Math.round(simulationCircuit.getMtf_sizes().get(0)), "ALL_SIGNALS");
        System.out.println("> : " + simulationCircuit.getMtf_sizes().get(0));
        simulacaoMultithreading.runMTFMonteCarlo_calulateSensitiveArea(Math.round(simulationCircuit.getMtf_sizes().get(0)), simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault(), simulationCircuit.getRelativePath() + "lookup_table.csv");
                simulacaoMultithreading.generateSensitiveNodesForSETSpiceFile(spiceScriptsFolder, PTMLibrary, SpiceNetListLibrary);
                    simulacaoMultithreading.printSensitiveAreas();
                         simulacaoMultithreading.PrintGatesCounterDetailsSortedCompliled(0, simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());
                this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" MTFT Sample (Monte Carlo = N)");

    }

    public void PrintAVGSensitiveAreasCompiled(String spiceScriptsFolder, String PTMLibrary, String SpiceNetListLibrary) throws Exception{

        this.printSpecSimulation();

        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

        //simulacaoMultithreading.runMultithreadingMonteCarlo(Math.round(simulationCircuit.getMtf_sizes().get(0)), "ALL_SIGNALS");
        System.out.println("> : " + simulationCircuit.getMtf_sizes().get(0));
             simulacaoMultithreading.runMTFMonteCarlo_calulateSensitiveArea(Math.round(simulationCircuit.getMtf_sizes().get(0)), simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault(), simulationCircuit.getRelativePath() + "lookup_table.csv");
                  simulacaoMultithreading.PrintGatesCounterDetailsSortedCompliled(0, simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());
        this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" MTFT Sample (Monte Carlo = N)");

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

    public void monteCarloSimulationMultipleTransientFaults() throws Exception{


        System.out.println("\n\n ------");

        /*
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() +  simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());
        */

        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

        //this.printSpecSimulation();

                simulacaoMultithreading.runMultipleFaultInjectionMultithreadingMonteCarloSimulationProportion(Math.round(simulationCircuit.getMtf_sizes().get(0)),
                simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault());

        this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" MTFT Sample (Monte Carlo = N)");

    }

    public void monteCarloSTFSimulation() throws Exception{
            this.printSpecSimulation();
           //System.out.println(" >>>> PATH AND FILE: " + simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getGenlib(), simulationCircuit.getCircuit());

                    simulacaoMultithreading.runMultithreadingMonteCarlo(simulationCircuit.getSample(), simulationCircuit.getSignalsToinjectFault()); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"

                this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" Sample(Monte Carlo = N)");
    }

    /**
     * Method to link the Logic Simulator and run method Exhaustive Simulation for SET evaluation (Extracts the Fault Masking Rate - FMR)
     * @throws Exception e e
     */
    public void multithreadingSimulationExaustic() throws Exception { //ou Signals =  "ALL_SIGNALS" for exaustive consider all_signals

        /*
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() + simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

         */
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getGenlib(), simulationCircuit.getCircuit());

            simulacaoMultithreading.runMultithreadingExausticSimulation(simulationCircuit.getSignalsToinjectFault());
        this.OUTPUT_INFO = simulacaoMultithreading.getFRM("Sample (N = "
                + "2^Signals * Gates)");
    }

    /**
     * Method to link the Logic Simulator and run method Exhaustive Complete Simulation for MET evaluation (All combinations) (Extracts the Fault Masking Rate - FMR)
     * @throws Exception e e
     */
    public void multithreadingSimulationExausticComplete() throws Exception{ //ou Signals =  "ALL_SIGNALS" for exhaustive consider all_signals
        Management simulacaoMultithreading = new Management(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getGenlib(), simulationCircuit.getCircuit());


        simulacaoMultithreading.runMultithreadingExausticSimulationComplete(simulationCircuit.getSignalsToinjectFault());
            this.OUTPUT_INFO = simulacaoMultithreading.getFRM("Sample (N = "
                    + "2^Signals * Gates)");
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
