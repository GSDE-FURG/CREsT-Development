package writers;

import datastructures.Circuit;
import datastructures.Signal;
import jxl.write.WriteException;
import logicSimulator.LogicSimulator;
import readers.MappedVerilogReader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteLog {
    private int sampleSize;
    private int threads;
    private int unmasked_faults;
    private float circuitReliaibility;
    private Circuit circuit;
    private MappedVerilogReader verilog_circuit;
    private ArrayList<Signal> signals_to_inject_faults;
    private ArrayList<Float> mtf_list = new ArrayList<>();


    public WriteLog(int sampleSize, int threads, int unmasked_faults,  float circuitReliaibility, Circuit circuit, MappedVerilogReader verilog_circuit ,ArrayList<Signal> signals_to_inject_faults){
            this.sampleSize = sampleSize;
            this.threads = threads;
            this.unmasked_faults = unmasked_faults;
            this.circuitReliaibility = circuitReliaibility;
            this.circuit = circuit;
            this.verilog_circuit = verilog_circuit;
            this.signals_to_inject_faults = signals_to_inject_faults;
    }
    public WriteLog(int sampleSize, int threads, int unmasked_faults,  float circuitReliaibility, Circuit circuit, MappedVerilogReader verilog_circuit ,ArrayList<Signal> signals_to_inject_faults, ArrayList <Float> mtf_list){
        this.sampleSize = sampleSize;
        this.threads = threads;
        this.unmasked_faults = unmasked_faults;
        this.circuitReliaibility = circuitReliaibility;
        this.circuit = circuit;
        this.verilog_circuit = verilog_circuit;
        this.signals_to_inject_faults = signals_to_inject_faults;
        this.mtf_list = mtf_list;
    }

    //fileName, date, dateend, propagateTimems, itemx_list
    public void WriteSimpleLog(String filename, String date, String dateend, long propagateTimems) throws IOException {

        ///System.out.println("Creating .txt -> file: " + filename);
        //float reliability_circuit =  (float) ( 1 - ((float) this.unmasked_faults / (float) this.sampleSize));
        //double lamb = - Math.log(reliability_circuit);
        //this.MTBF = (1 / lamb);
        //System.out.println("MTBF : " + this.MTBF);
        String content = "";
        try (FileWriter file = new FileWriter(filename+".txt")) {


            content = "Started at Date/hour: "  + date + " and finished at: " + dateend  + "\n\n";
            //content = content + "Date/hour (finished): "+ dateend + "\n\n";
            content = content + "Circuit: " + this.circuit.getName() + "\n";
            content = content+  "Number of Simulations (sample size = N): " + this.sampleSize + "\n";
            content = content+  "Number of Threads: " + this.threads + "\n";
            content = content + "Number of detected faults (Ne): " + this.unmasked_faults + "\n";
            content = content + "Fault Mask Rate (FMR): "+ " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + this.sampleSize + ")) = " + this.circuitReliaibility + "\n";
            //content = content +  "MTBF: " + this.MTBF + "\n\n";

            content = content +  "Performance time(s): " + (propagateTimems) + "\n";


            /*

              System.out.println("\n\n----------------- Results ------------------");
                System.out.println("Circuit: " + this.circuit.getName());
                System.out.println("- Simulation started at: " + formattedDate + " and finished at: "+ formattedDate2); //formattedDate

               // System.out.println("- PropagatedTime (s): " + propagateTime);
                System.out.println("- Sample Size (N): " + N);
                System.out.println("- Number of detected faults (Ne): " + this.unmasked_faults);
                System.out.println("- Fault Mask Rate (FMR): " + " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + N + ")) = " + this.circuitReliaibility);
                //System.out.println("- MTBF (Mean Time Between failure) : " + this.MTBF);
                System.out.println("- Simulation TimeElapsed: " + propagateTime
                        + "(s)");

                System.out.println("--------------------------------------------");

            */


            file.write(content);
        }
    }

    public void writeSimpleLogMTF(String filename, String date, String dateend, long propagateTimems, int order, int period, int frequency) throws IOException{

        ///System.out.println("Creating .txt -> file: " + filename);
        //float reliability_circuit =  (float) ( 1 - ((float) this.unmasked_faults / (float) this.sampleSize));
        //double lamb = - Math.log(reliability_circuit);
        //this.MTBF = (1 / lamb);
        //System.out.println("MTBF : " + this.MTBF);
        String content = "";
        try (FileWriter file = new FileWriter(filename+".txt")) {


            content = "Started at Date/hour: "  + date + " and finished at: " + dateend  + "\n\n";
            //content = content + "Date/hour (finished): "+ dateend + "\n\n";
            content = content + "Circuit: " + this.circuit.getName() + "\n";
            content = content+  "Number of Simulations (sample size = N): " + this.sampleSize + "\n";
            content = content+  "For each  (" + period + ") faults happens a Multiple Transient Fault (" + order + ") with frequency: (" + frequency + ") - Sample = " + this.sampleSize + "\n";
            content = content+  "Number of Threads: " + this.threads + "\n";
            content = content + "Number of detected faults (Ne): " + this.unmasked_faults + "\n";
            content = content + "Fault Mask Rate (FMR): "+ " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + this.sampleSize + ")) = " + this.circuitReliaibility + "\n";
            //content = content +  "MTBF: " + this.MTBF + "\n\n";

            content = content +  "Performance time(s): " + (propagateTimems) + "\n";


            /*

              System.out.println("\n\n----------------- Results ------------------");
                System.out.println("Circuit: " + this.circuit.getName());
                System.out.println("- Simulation started at: " + formattedDate + " and finished at: "+ formattedDate2); //formattedDate

               // System.out.println("- PropagatedTime (s): " + propagateTime);
                System.out.println("- Sample Size (N): " + N);
                System.out.println("- Number of detected faults (Ne): " + this.unmasked_faults);
                System.out.println("- Fault Mask Rate (FMR): " + " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + N + ")) = " + this.circuitReliaibility);
                //System.out.println("- MTBF (Mean Time Between failure) : " + this.MTBF);
                System.out.println("- Simulation TimeElapsed: " + propagateTime
                        + "(s)");

                System.out.println("--------------------------------------------");

            */


            file.write(content);
        }
    }

    public void writeSimpleLogMultipleTransientFault(String filename, String date, String dateend, long timeElapsedSimulation, ArrayList<Integer> mtf_list) throws IOException{

        ///System.out.println("Creating .txt -> file: " + filename);
        //float reliability_circuit =  (float) ( 1 - ((float) this.unmasked_faults / (float) this.sampleSize));
        //double lamb = - Math.log(reliability_circuit);
        //this.MTBF = (1 / lamb);
        //System.out.println("MTBF : " + this.MTBF);
        String content = "";
        try (FileWriter file = new FileWriter(filename+".txt")) {


            content = "Multiple Transient Fault Simulation Started at Date/hour: "  + date + " and finished at: " + dateend  + "\n\n";
            //content = content + "Date/hour (finished): "+ dateend + "\n\n";
            content = content + "Circuit: " + this.circuit.getName() + "\n";
            content = content+  "Number of Simulations (sample size = N): " + this.sampleSize + "\n";

            for (int i = 0; i < mtf_list.size(); i++) {
                content = content +  "      - For each  (" + mtf_list.get(i) + ") faults happens a MTF of order (" + (i+2) + ")\n" ;
            }
            content = content + "- Sample = " + this.sampleSize + "\n";
            //content = content+  "For each  (" + period + ") faults happens a Multiple Transient Fault (" + order + ") with frequency: (" + frequency + ") - Sample = " + this.sampleSize + "\n";
            content = content+  "Number of Threads: " + this.threads + "\n";
            content = content + "Number of detected faults (Ne): " + this.unmasked_faults + "\n";
            content = content + "Fault Mask Rate (FMR): "+ " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + this.sampleSize + ")) = " + this.circuitReliaibility + "\n";
            //content = content +  "MTBF: " + this.MTBF + "\n\n";

            content = content +  "Performance time m(s): " + (timeElapsedSimulation) + "\n";


            /*

              System.out.println("\n\n----------------- Results ------------------");
                System.out.println("Circuit: " + this.circuit.getName());
                System.out.println("- Simulation started at: " + formattedDate + " and finished at: "+ formattedDate2); //formattedDate

               // System.out.println("- PropagatedTime (s): " + propagateTime);
                System.out.println("- Sample Size (N): " + N);
                System.out.println("- Number of detected faults (Ne): " + this.unmasked_faults);
                System.out.println("- Fault Mask Rate (FMR): " + " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + N + ")) = " + this.circuitReliaibility);
                //System.out.println("- MTBF (Mean Time Between failure) : " + this.MTBF);
                System.out.println("- Simulation TimeElapsed: " + propagateTime
                        + "(s)");

                System.out.println("--------------------------------------------");

            */


            file.write(content);
        }
    }

    public void writeSimpleLogMultipleTransientFaultProportion(String filename, String date, String dateend, long timeElapsedSimulation, ArrayList <Float> mtf_list) throws IOException{

        ///System.out.println("Creating .txt -> file: " + filename);
        //float reliability_circuit =  (float) ( 1 - ((float) this.unmasked_faults / (float) this.sampleSize));
        //double lamb = - Math.log(reliability_circuit);
        //this.MTBF = (1 / lamb);
        //System.out.println("MTBF : " + this.MTBF);
        String content = "";
        try (FileWriter file = new FileWriter(filename+".txt")) {


            content = "Multiple Transient Fault Simulation Started at Date/hour: "  + date + " and finished at: " + dateend  + "\n\n";
            //content = content + "Date/hour (finished): "+ dateend + "\n\n";
            content = content + "Circuit: " + this.circuit.getName() + "\n";
            content = content+  "Number of Simulations (sample size = N): " + this.sampleSize + "\n";

            for (int i = 1; i < mtf_list.size(); i++) {
                content = content +  "      - (" + mtf_list.get(i) + "%) faults are MTF of order (" + (i) + ")\n" ;
            }
            content = content+  "Threads: " + this.threads + "\n";
            content = content + "Sample Size (Monte Carlo)= " + this.sampleSize + "\n";
            //content = content+  "For each  (" + period + ") faults happens a Multiple Transient Fault (" + order + ") with frequency: (" + frequency + ") - Sample = " + this.sampleSize + "\n";

            content = content + "Detected faults (Ne): " + this.unmasked_faults + "\n";
            content = content + "Fault Masking Rate (FMR): "+ " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + this.sampleSize + ")) = " + this.circuitReliaibility + "\n";
            content = content + "Performance time m(s): " + (timeElapsedSimulation) + "\n";


            /*

              System.out.println("\n\n----------------- Results ------------------");
                System.out.println("Circuit: " + this.circuit.getName());
                System.out.println("- Simulation started at: " + formattedDate + " and finished at: "+ formattedDate2); //formattedDate

               // System.out.println("- PropagatedTime (s): " + propagateTime);
                System.out.println("- Sample Size (N): " + N);
                System.out.println("- Number of detected faults (Ne): " + this.unmasked_faults);
                System.out.println("- Fault Mask Rate (FMR): " + " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + N + ")) = " + this.circuitReliaibility);
                //System.out.println("- MTBF (Mean Time Between failure) : " + this.MTBF);
                System.out.println("- Simulation TimeElapsed: " + propagateTime
                        + "(s)");

                System.out.println("--------------------------------------------");

            */


            file.write(content);
        }
    }

    public void writeSimpleLogMultipleTransientFaultExaustive(String filename, String date, String dateend, long timeElapsedSimulation) throws IOException{

        ///System.out.println("Creating .txt -> file: " + filename);
        //float reliability_circuit =  (float) ( 1 - ((float) this.unmasked_faults / (float) this.sampleSize));
        //double lamb = - Math.log(reliability_circuit);
        //this.MTBF = (1 / lamb);
        //System.out.println("MTBF : " + this.MTBF);
        String content = "";
        try (FileWriter file = new FileWriter(filename+".txt")) {


            content = "Multiple Transient Fault Simulation Started at Date/hour: "  + date + " and finished at: " + dateend  + "\n\n";
            //content = content + "Date/hour (finished): "+ dateend + "\n\n";
            content = content + "Circuit: " + this.circuit.getName() + "\n";
            content = content+  "Number of Simulations (sample size = N): " + this.sampleSize + "\n";

            // for (int i = 0; i < mtf_list.size(); i++) {
            //     content = content +  "      - For each  (" + mtf_list.get(i) + ") faults happens a MTF of order (" + (i+2) + ")\n" ;
            // }
            content = content + "- Sample = " + this.sampleSize + "\n";
            //content = content+  "For each  (" + period + ") faults happens a Multiple Transient Fault (" + order + ") with frequency: (" + frequency + ") - Sample = " + this.sampleSize + "\n";
            content = content+  "Number of Threads: " + this.threads + "\n";
            content = content + "Number of detected faults (Ne): " + this.unmasked_faults + "\n";
            content = content + "Fault Mask Rate (FMR): "+ " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + this.sampleSize + ")) = " + this.circuitReliaibility + "\n";
            //content = content +  "MTBF: " + this.MTBF + "\n\n";

            content = content +  "Performance time m(s): " + (timeElapsedSimulation) + "\n";


            /*

              System.out.println("\n\n----------------- Results ------------------");
                System.out.println("Circuit: " + this.circuit.getName());
                System.out.println("- Simulation started at: " + formattedDate + " and finished at: "+ formattedDate2); //formattedDate

               // System.out.println("- PropagatedTime (s): " + propagateTime);
                System.out.println("- Sample Size (N): " + N);
                System.out.println("- Number of detected faults (Ne): " + this.unmasked_faults);
                System.out.println("- Fault Mask Rate (FMR): " + " 1 - Ne/N = (1-(" + this.unmasked_faults + "/" + N + ")) = " + this.circuitReliaibility);
                //System.out.println("- MTBF (Mean Time Between failure) : " + this.MTBF);
                System.out.println("- Simulation TimeElapsed: " + propagateTime
                        + "(s)");

                System.out.println("--------------------------------------------");

            */


            file.write(content);
        }
    }

    public void writeCsvFileCompleteTh(String filename, ArrayList <LogicSimulator> itemx_list) throws IOException, WriteException {

        //System.out.println("Creating Complete log .....");
        ArrayList <Signal> outputSignals = this.circuit.getOutputs();
        ArrayList <Signal> inputSignals = this.circuit.getInputs();


        String Header = "Input Signals" + ";" + "Fault Signal" + ";" + "Fault Type" + ";" + "Fault-Free Circuit Output" + ";" + "Faulty Circuit Output";
        WriteCsvTh logfile = new WriteCsvTh();

        logfile.writeCSVCompleteThNew(this.circuit, filename, this.verilog_circuit.getCircuit().getName() + ".v", this.circuit.getInputs(), this.circuit.getOutputs(),
                this.signals_to_inject_faults,  Header , itemx_list , Integer.toString(this.unmasked_faults));

    }

    public void writeCsvFileCompleteThMTF(String filename, ArrayList <LogicSimulator> itemx_list) throws IOException, WriteException{ //here

        //System.out.println("Creating Complete log .....");
        ArrayList <Signal> outputSignals = this.circuit.getOutputs();
        ArrayList <Signal> inputSignals = this.circuit.getInputs();



        String Header = "Input Signals" + ";" + "Fault Signal" + ";" + "Fault Type" + ";" + "Fault-Free Circuit Output" + ";" + "Faulty Circuit Output";
        WriteCsvTh logfile = new WriteCsvTh();

        logfile.writeCSVCompleteThNewMTF(this.circuit, filename, this.verilog_circuit.getCircuit().getName() + ".v", this.circuit.getInputs(), this.circuit.getOutputs(),
                this.signals_to_inject_faults,  Header , itemx_list , Integer.toString(this.unmasked_faults));

    }
}
