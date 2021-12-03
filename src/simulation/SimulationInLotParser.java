package simulation;

import java.io.File;
import java.util.ArrayList;

public class SimulationInLotParser {

        private String  circuitfilesPath;
        private ArrayList<String> circuitList = new ArrayList<>();
        private ArrayList<SimulationCircuit> simulationCircuitsList = new ArrayList<>();

        public SimulationInLotParser(String circuitfilesPath){
                this.circuitfilesPath = circuitfilesPath;
        }

        public ArrayList<String> getCircuitList() {
                return circuitList;
        }

        public ArrayList<SimulationCircuit> getSimulationCircuitsList() {
                return simulationCircuitsList;
        }

        public void getVerilogCircuitFilesPath(){
                String[] circuitFiles;
                File f = new File(this.circuitfilesPath);

                circuitFiles = f.list();

                for (String pathname : circuitFiles) {
                        if(pathname.endsWith(".v")){ // test type for verilogs .v
                                this.circuitList.add(pathname);
                        }
                }
                //System.out.println("Circuits in List: " +  this.circuitList);
                System.out.println("- Were founded " + this.circuitList.size() + " circuit (.v) in path folder: " + this.circuitfilesPath);
                //return this.circuitList;
        }

        public void circuitListParser(String genlib, String signalsToinjectFault, int threads, String reliabilityConst, int sampleSize){

                for (String file: this.circuitList){
                        SimulationCircuit circuit = new SimulationCircuit(file, this.circuitfilesPath, genlib, signalsToinjectFault, threads, reliabilityConst, sampleSize); //String circuit, String relativePath, String genlib, String signalsToinjectFault, int threads, String reliabilityConst, int sampleSize
                                //System.out.println("   ~~~~~ sample: " + circuit.getSample());
                        this.simulationCircuitsList.add(circuit);
                }
        }

        public void circuitListParser(String genlib, String signalsToinjectFault, int threads, String reliabilityConst,  ArrayList <Float> mtf_sizes){

                for (String file: this.circuitList){
                        SimulationCircuit circuit = new SimulationCircuit(file, this.circuitfilesPath, genlib, signalsToinjectFault, threads, reliabilityConst, mtf_sizes); //String circuit, String relativePath, String genlib, String signalsToinjectFault, int threads, String reliabilityConst, int sampleSize
                        this.simulationCircuitsList.add(circuit);
                }
        }

        public void printCircuitsSpecs(){
                System.out.println("\n");
                for (SimulationCircuit circ: this.simulationCircuitsList){
                        System.out.println("Circ: " + circ.getCircuit() + " genlib: " + circ.getGenlib() + "  threads: " + circ.getThreads() + " STF or MTF: " + circ.getSample()+ " " + circ.getMtf_sizes());
                }
        }
}
