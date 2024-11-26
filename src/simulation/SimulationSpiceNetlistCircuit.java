package simulation;

import java.util.ArrayList;

public class SimulationSpiceNetlistCircuit{


        private ArrayList<SimulationCircuit> circuitListSpecs;
        private ArrayList <String> circuitsListName;
        private SimulationInLotParser simulationCircuits;
        private String relativePath;
        private String library;
        int threads;

        /**
         * Constructor
         */
        public SimulationSpiceNetlistCircuit(){

        }

        public void setup(String relativePath, String genlib){

            //this.threads = threads;
            this.library = genlib;
            this.relativePath = relativePath;


        }

        public void print(){
            System.out.println("Path: " + this.relativePath + " ~ Genlib: " + this.library);
        }

    public ArrayList<String> getCircuitsListName() {
        return circuitsListName;
    }

    /**
         * Method for Spice construction
         */
        public void processParserSpiceNetlists(){
            SimulationInLotParser parserFiles = new SimulationInLotParser(this.relativePath);

            parserFiles.getSpiceCircuitFilesPath();
            //parserFiles.circuitListParser(this.library, signalsToinjectFault, threads, reliabilityConst, mtf_sizes); //String genlib, String signalsToinjectFault, int threads, String reliabilityConst, int sampleSize

            this.circuitsListName = parserFiles.getCircuitList();  /// List of circuits
            //this.circuitListSpecs = parserFiles.getSimulationCircuitsList();

            System.out.println("- MTF: Spice CircuitList: " + this.circuitsListName.size() );
            //System.out.println("- MTF: Circuits object parsed: " + this.circuitListSpecs.size() );
            printCircuitsSpecs();
        }


    public void printCircuitsSpecs(){
        System.out.println("\n");
        for(int i = 0; i < this.circuitsListName.size(); i++){
            System.out.println("Circ: " + this.circuitsListName.get(i) + "  (" + i + ")");
        }
    }



}
