package simulation;

import java.util.ArrayList;

public class SimulationInLot {

    private ArrayList <SimulationCircuit> circuitSpecs;
    private SimulationInLotParser simulationCircuits;
    private String relativePath;
    private String genlib;
    int threads;
    /**
     * Constructor
     */
    public SimulationInLot(){

    }

    public void setup(String relativePath, String genlib, int threads){

        this.threads = threads;
        this.genlib = genlib;
        this.relativePath = relativePath;


    }

    public void print(){
        System.out.println("Path: " + this.relativePath + " ~ Genlib: " + this.genlib + " ~ Threads: " + this.threads);
    }

    public void processParser(String signalsToinjectFault, String reliabilityConst, int sampleSize){ // FOR STF's
            SimulationInLotParser parserFiles = new SimulationInLotParser(this.relativePath);

            parserFiles.getVerilogCircuitFilesPath();
            parserFiles.circuitListParser(this.genlib, signalsToinjectFault, threads, reliabilityConst, sampleSize); //String genlib, String signalsToinjectFault, int threads, String reliabilityConst, int sampleSize
                //System.out.println("Sample: " + sampleSize);
            ArrayList <String> circuitListNames = parserFiles.getCircuitList();  /// List of circuits
            ArrayList <SimulationCircuit> circuitList = parserFiles.getSimulationCircuitsList();

            System.out.println("- STF: CircuitList: " + circuitListNames );
            System.out.println("- STF: Circuits object parsed: " + circuitList );


            parserFiles.printCircuitsSpecs();


    }

    public void processParser(String signalsToinjectFault, String reliabilityConst,  ArrayList <Float> mtf_sizes){
            SimulationInLotParser parserFiles = new SimulationInLotParser(this.relativePath);

            parserFiles.getVerilogCircuitFilesPath();
            parserFiles.circuitListParser(this.genlib, signalsToinjectFault, threads, reliabilityConst, mtf_sizes); //String genlib, String signalsToinjectFault, int threads, String reliabilityConst, int sampleSize

            ArrayList <String> circuitListNames = parserFiles.getCircuitList();  /// List of circuits
            ArrayList <SimulationCircuit> circuitList = parserFiles.getSimulationCircuitsList();


            System.out.println("- MTF: CircuitList: " + circuitListNames );
            System.out.println("- MTF: Circuits object parsed: " + circuitList );
            parserFiles.printCircuitsSpecs();
    }

    pub


}
