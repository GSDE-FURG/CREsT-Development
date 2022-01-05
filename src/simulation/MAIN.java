package simulation;

import java.util.ArrayList;

public class MAIN {  //Class to run debug tests
    int threads;
    int sample;
    String reliabilityConst;
    String relativePath;
    String genlib;
    String OUTPUT_INFO;

    public MAIN(int threads, String reliabilityConst, String relativePath, String genlib){
            this.threads = threads;
            this.reliabilityConst = reliabilityConst;
            this.relativePath = relativePath;
            this.genlib = genlib;
    }

    public static void main(String[] args) throws Exception{
        System.out.println("- New methodology....");
            String relativePath = "teste/cccc/circuitos_pequenos/";
            String genlib = "cadence.genlib"; //"cadence.genlib";
            String signalsToinjectFault = "ALL_SIGNALS";
            String constReliability = "0.9999";


        int sampleSize = 20000;
        int threads = 8;

        SimulationInLot simulationInLot = new SimulationInLot();
        simulationInLot.setup(relativePath, genlib, threads);
        simulationInLot.processParser(signalsToinjectFault, constReliability, sampleSize);  //STF's - String signalsToinjectFault, String reliabilityConst, int sampleSize
                //simulationInLot.processParser(signalsToinjectFault, constReliability, mtf_sizes); // MTF's
                simulationInLot.print();

        SimulationMode sim_stf = new SimulationMode(simulationInLot.getCircuitListSpecs().get(0)); // first Circuit c.v


        ///sim_stf.monteCarloSTFSimulation();
                //sim_stf.multithreadingSimulationExaustic();
                   //sim_stf.multithreadingSimulationExausticComplete();
                        //     sim_stf.monteCarloSimulationMultipleTransientFaultsProportion();

        ArrayList<Float> mtf_sizes = new ArrayList<>();  //MTF's
        mtf_sizes.add((float) 20000);
        mtf_sizes.add((float) 1);
        mtf_sizes.add((float) 0);
        mtf_sizes.add((float) 0);

        SimulationInLot simulationInLotMTF = new SimulationInLot();
        simulationInLotMTF.setup(relativePath, genlib, threads);
        simulationInLotMTF.processParser(signalsToinjectFault, constReliability, mtf_sizes);

            SimulationMode sim_mtf = new SimulationMode(simulationInLotMTF.getCircuitListSpecs().get(0)); // first Circuit c.v
                //sim_mtf.monteCarloSimulationMultipleTransientFaults();
                    //sim_mtf.monteCarloSimulationMultipleTransientFaultsProportionAndCalculationOfSensitiveArea("netlist_files/", "45nm_HP.pm", "Library.txt");
                     //sim_mtf.monteCarloSimulationMultipleTransientFaultsProportionAndCalculationOfSensitiveArea();


        //SimulationSpiceNetlistCircuit simulationElectric= new SimulationSpiceNetlistCircuit();
            //simulationElectric.setup(relativePath + "netlist_files/", relativePath + "Library.txt");
                //simulationElectric.processParserSpiceNetlists();
                    //sim_mtf.runElectricalSimulation(relativePath , "netlist_files/" + simulationElectric.getCircuitsListName().get(4));  // fixed



        SimulationInLot simulationInLotDebug = new SimulationInLot();
        //simulationInLotDebug.setup("circuitos/fullv2/", "lib_full_no_cost.genlib", threads);
        //simulationInLotDebug.setup("circuitos/ISCAS89/min/", "lib_min_no_cost.genlib", threads);
        simulationInLotDebug.setup("teste/ISCAS85/", "cadence.genlib", threads);
        simulationInLotDebug.processParser(signalsToinjectFault, constReliability, mtf_sizes);
        simulationInLotDebug.print();

        ArrayList <String> samples = new ArrayList<>();

        for (int i = 0; i < simulationInLotDebug.getCircuitListSpecs().size(); i++) {


            SimulationMode sim_mtf_debug = new SimulationMode(simulationInLotDebug.getCircuitListSpecs().get(i)); // first Circuit c.v

               // sim_mtf_debug.monteCarloReliabilityAPI("ok/", "45nm_HP.pm", "Library.txt");
                //sim_mtf_debug.faultToleranceExhaustiveCompleteMETAPI();
                sim_mtf_debug.faultToleranceExhaustiveSETAPI();
                samples.add(sim_mtf_debug.getOutput_sample());
            //sim_mtf_debug.monteCarloReliabilityAPI("ok/", "45nm_HP.pm", "Library.txt");


            //-Xms2G -Xmx7G  para ISCAS89
       }

        System.out.println("\n\n");

        for (int i = 0; i < samples.size(); i++) {

            System.out.println(samples.get(i) + "   -" + simulationInLotDebug.getCircuitListSpecs().get(i));
        }
        //SimulationMode sim_mtf_debug = new SimulationMode(simulationInLotDebug.getCircuitListSpecs().get(4)); // first Circuit c.v
        //    sim_mtf_debug.PrintAVGSensitiveAreasCompiled("ok/", "45nm_HP.pm", "Library.txt");

    }
}
