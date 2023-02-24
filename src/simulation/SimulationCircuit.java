package simulation;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Circuits Specs for SET/MET simulation .....
 */
public class SimulationCircuit {
    //private final ArrayList<String> circuitList = new ArrayList<>(); //CircuitList for simulation
    private String circuit; //CircuitList for simulation
    int threads;
    int sample;
    String reliabilityConst;
    String relativePath;
    String genlib;
    String OUTPUT_INFO;
    String signalsToinjectFault = "ALL_SIGNALS";
    ArrayList <Float> mtf_sizes = new ArrayList<>();
    String mode = "";


    /**
     * Constructor for STF specs simulation
     * @param circuit
     * @param relativePath
     * @param genlib
     * @param signalsToinjectFault
     * @param threads
     * @param reliabilityConst
     * @param sampleSize
     */
    public SimulationCircuit(String circuit, String relativePath, String genlib, String signalsToinjectFault, int threads, String reliabilityConst, int sampleSize) {
        this.circuit = circuit;
        this.reliabilityConst = reliabilityConst;
        this.relativePath = relativePath;
        this.genlib = genlib;
        this.threads = threads;
        this.OUTPUT_INFO = "";
        this.sample = sampleSize;
        this.signalsToinjectFault = signalsToinjectFault;
        this.mode = "STF";
    }

    /**
     * Constructor for MTF specs simulation
     * @param circuit
     * @param relativePath
     * @param genlib
     * @param signalsToinjectFault
     * @param threads
     * @param reliabilityConst
     * @param mtf_sizes
     */
    public SimulationCircuit(String circuit, String relativePath, String genlib, String signalsToinjectFault, int threads, String reliabilityConst, ArrayList <Float> mtf_sizes) {
        this.circuit = circuit;
        this.reliabilityConst = reliabilityConst;
        this.relativePath = relativePath;
        this.genlib = genlib;
        this.threads = threads;
        this.OUTPUT_INFO = "";
        this.mtf_sizes = mtf_sizes;
        this.signalsToinjectFault = signalsToinjectFault;
        this.mode = "MTF";
    }

    public void setSample(int sample) {
        this.sample = sample;
    }

    public String getMode() {
        return mode;
    }

    public int getSample() {
        return this.sample;
    }

    public int getThreads() {
        return threads;
    }

    public String getGenlib() {
        return genlib;
    }

    public String getOUTPUT_INFO() {
        return OUTPUT_INFO;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public String getReliabilityConst() {
        return reliabilityConst;
    }

    public void setReliabilityConst(String reliabilityConst) {
        this.reliabilityConst = reliabilityConst;
    }

    public String getSignalsToinjectFault() {
        return signalsToinjectFault;
    }

    public void setGenlib(String genlib) {
        this.genlib = genlib;
    }

    public void setOUTPUT_INFO(String OUTPUT_INFO) {
        this.OUTPUT_INFO = OUTPUT_INFO;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public void setSignalsToinjectFault(String signalsToinjectFault) {
        this.signalsToinjectFault = signalsToinjectFault;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public String getCircuit() {
        return circuit;
    }

    public ArrayList<Float> getMtf_sizes() {
        return mtf_sizes;
    }

    public void setMtf_sizes(ArrayList<Float> mtf_sizes) {
        this.mtf_sizes = mtf_sizes;
    }

    public void print(){
        System.out.println();
        System.out.println("------------simulationCircuit------------------");
        System.out.println("    -Name: " + this.getCircuit());
        System.out.println("    -Genlib: " + this.getGenlib());
        System.out.println("    -Path: " + this.getRelativePath());
        System.out.println("    -SampleSize: " + this.getSample());
        System.out.println("    -ReliabilityConst: " + this.getReliabilityConst());
        System.out.println("    -OUTPUTINFO: " + this.getOUTPUT_INFO());
        System.out.println("------------------------------");


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimulationCircuit that = (SimulationCircuit) o;
        return threads == that.threads && sample == that.sample && Objects.equals(circuit, that.circuit) && Objects.equals(reliabilityConst, that.reliabilityConst) && Objects.equals(relativePath, that.relativePath) && Objects.equals(genlib, that.genlib) && Objects.equals(OUTPUT_INFO, that.OUTPUT_INFO) && Objects.equals(signalsToinjectFault, that.signalsToinjectFault) && Objects.equals(mtf_sizes, that.mtf_sizes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(circuit, threads, sample, reliabilityConst, relativePath, genlib, OUTPUT_INFO, signalsToinjectFault, mtf_sizes);
    }
}
