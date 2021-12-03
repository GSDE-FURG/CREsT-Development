package simulation;

import java.util.ArrayList;

public class MAIN {

    public MAIN(){

    }

    public static void main(String[] args) throws Exception{
        System.out.println("New methodology....");

        String relativePath = "teste/cccc/circuitos_pequenos/";
        String genlib = "cadence.genlib"; //"cadence.genlib";
        String signalsToinjectFault = "ALL_SIGNALS";
        String constReliability = "0.9999";

        ArrayList<Float> mtf_sizes = new ArrayList<>();  //MTF's

        int sampleSize = 20000;
        int threads = 8;

        SimulationInLot simulationInLot = new SimulationInLot();
        simulationInLot.setup(relativePath, genlib, threads);

        simulationInLot.processParser(signalsToinjectFault, constReliability, sampleSize);  //STF's - String signalsToinjectFault, String reliabilityConst, int sampleSize
                //simulationInLot.processParser(signalsToinjectFault, constReliability, mtf_sizes); // MTF's
        simulationInLot.print();

        SimulationMode proportion_mtf = new SimulationMode(simulationInLot.getCircuitListSpecs().get(0)); // first Circuit c.v

        proportion_mtf.monteCarloSTFSimulation();
            //proportion_mtf.monteCarloSimulationMultipleTransientFaultsProportion();

    }
}
