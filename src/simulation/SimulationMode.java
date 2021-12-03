package simulation;

import logicSimulator.Orchestrator;

import java.util.ArrayList;

public class SimulationMode {

    private SimulationCircuit simulationCircuit;

    public String OUTPUT_INFO = "";

    public SimulationMode(SimulationCircuit simulationCircuit){
        this.simulationCircuit = simulationCircuit;
    }

    public void monteCarloSimulationMultipleTransientFaultsProportion() throws Exception{

        this.printSpecSimulation();

        Orchestrator simulacaoMultithreading = new Orchestrator(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                simulationCircuit.getRelativePath() +  simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

                simulacaoMultithreading.runMTFMonteCarlo_calulateSensitiveArea_runElectricalSimulation(simulationCircuit.getSample(),
                        simulationCircuit.getMtf_sizes(), simulationCircuit.getSignalsToinjectFault(), simulationCircuit.getRelativePath() + "lookup_table.csv");
                        //simulacaoMultithreading.runMTFMonteCarlo_calulateSensitiveArea_runElectricalSimulation(monteCarloSample, mtf_sizes, positionToFaultInjection, "teste/lookup_table.csv");

        this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" MTFT Sample (Monte Carlo = N)");

    }

    public void monteCarloSTFSimulation() throws Exception{
            this.printSpecSimulation();
        System.out.println(" >>>> PATH AND FILE: " + simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());
            Orchestrator simulacaoMultithreading = new Orchestrator(simulationCircuit.getThreads(), simulationCircuit.getReliabilityConst(), simulationCircuit.getRelativePath(),
                    simulationCircuit.getRelativePath() +  simulationCircuit.getGenlib(), simulationCircuit.getRelativePath() + simulationCircuit.getCircuit());

                    simulacaoMultithreading.runMultithreadingMonteCarlo(simulationCircuit.getSample(), simulationCircuit.getSignalsToinjectFault()); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"

                this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" Sample(Monte Carlo = N)");
    }

    public void printSpecSimulation(){

        if(this.simulationCircuit.getMode().equals("STF")){
            System.out.println("- STF's Simulation -");
            System.out.println("- Circuit: " + this.simulationCircuit.getCircuit());
            System.out.println("- MC Single Fault Injection: " + this.simulationCircuit.getMode());
            System.out.println("- Path: " + this.simulationCircuit.getRelativePath());
            System.out.println("- Genlib: " + this.simulationCircuit.getGenlib());
        }
        if(this.simulationCircuit.getMode().equals("MTF")){
            System.out.println("- MTF's Simulation Proportion ");
            System.out.println("- Circuit: " + this.simulationCircuit.getCircuit());
            System.out.println("- MC Multiple Fault Injection : " + this.simulationCircuit.getMtf_sizes());
            System.out.println("- Path: " + this.simulationCircuit.getRelativePath());
            System.out.println("- Genlib: " + this.simulationCircuit.getGenlib());

        }
    }

}
