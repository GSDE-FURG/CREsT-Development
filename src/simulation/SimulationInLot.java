package simulation;

import java.util.ArrayList;

public class SimulationInLot {

    private ArrayList <SimulationCircuit> circuitListSpecs;
    private ArrayList <String> circuitsListName;
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

    public ArrayList<SimulationCircuit> getCircuitListSpecs() {
        return circuitListSpecs;
    }

    public SimulationInLotParser getSimulationCircuits() {
        return simulationCircuits;
    }

    /**
     * Method for STF construction
     * @param signalsToinjectFault
     * @param reliabilityConst
     * @param sampleSize
     */
    public void processParser(String signalsToinjectFault, String reliabilityConst, int sampleSize){ // FOR STF's
            SimulationInLotParser parserFiles = new SimulationInLotParser(this.relativePath);

            parserFiles.getVerilogCircuitFilesPath();
            parserFiles.circuitListParser(this.genlib, signalsToinjectFault, threads, reliabilityConst, sampleSize); //String genlib, String signalsToinjectFault, int threads, String reliabilityConst, int sampleSize

            this.circuitsListName = parserFiles.getCircuitList();  /// List of circuits
            this.circuitListSpecs = parserFiles.getSimulationCircuitsList();

            System.out.println("- STF: CircuitList: " + this.circuitsListName );
            System.out.println("- STF: Circuits object parsed: " + this.circuitListSpecs );


            parserFiles.printCircuitsSpecs();


    }

    /**
     * Method for MTF's construction
     * @param signalsToinjectFault
     * @param reliabilityConst
     * @param mtf_sizes
     */
    public void processParser(String signalsToinjectFault, String reliabilityConst,  ArrayList <Float> mtf_sizes){
            SimulationInLotParser parserFiles = new SimulationInLotParser(this.relativePath);

            parserFiles.getVerilogCircuitFilesPath();
            parserFiles.circuitListParser(this.genlib, signalsToinjectFault, threads, reliabilityConst, mtf_sizes); //String genlib, String signalsToinjectFault, int threads, String reliabilityConst, int sampleSize

            this.circuitsListName = parserFiles.getCircuitList();  /// List of circuits
            this.circuitListSpecs = parserFiles.getSimulationCircuitsList();

            System.out.println("- MTF: CircuitList: " + this.circuitsListName );
            System.out.println("- MTF: Circuits object parsed: " + this.circuitListSpecs );
            parserFiles.printCircuitsSpecs();
    }

}
