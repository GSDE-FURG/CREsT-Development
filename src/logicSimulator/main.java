/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicSimulator;
import jxl.read.biff.SharedStringFormulaRecord;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Clayton Farias
 */
public class main{
        
        private final ArrayList <String> circuitList = new ArrayList<>(); //CircuitList for simulation
        int threads;
        int sample;
        String reliabilityConst;
        String relativePath;
        String genlib;
        String OUTPUT_INFO;

    /**
     *
     * @param threads Threads
     * @param reliabilityConst The reliability constant
     * @param relativePath Relative Path for simulation files (folder: /test_folder/ --- example circuit.v and cadence.genlib)
     * @param genlib Genlib name (example: cadence.genlib)
     */
        public main(int threads, String reliabilityConst, String relativePath, String genlib) {
            this.threads = threads;
            this.reliabilityConst = reliabilityConst;
            this.relativePath = relativePath;
            this.genlib = genlib;
            this.OUTPUT_INFO = "";
            this.sample = 0;
        }

    /**
     *
      * @param sample set Monte Carlo Sampling
     */
    public void setSample(int sample) {
        this.sample = sample;
    }

    /**
     *
     * @return this.OUTPUT_INFO
     */
    public String getFMR(){
            return this.OUTPUT_INFO;
    }

    /**
     * Main method for call the logic simulator
     */
        public static void main(String[] args) throws Exception {
            
            int threads = 4; //Número de threads
            String constReliability = "0.9999"; //Used for internal structures
            ArrayList <String> Psthzs = new ArrayList<>();
            ArrayList <String> Psthzs_lib = new ArrayList<>();
            //ArrayList <String> result_path = new ArrayList<>();
            List<String> result_path = new ArrayList<>();
            List<String> result_path_filter = new ArrayList<>();

            //Psthzs.add("circuitos/min/"); Psthzs_lib.add("lib_min_no_cost.genlib");
            //Psthzs.add("teste/mapped/EPFL2015/1-minimal_no_cost/"); Psthzs_lib.add("1-minimal_no_cost.genlib");
            //Psthzs.add("teste/mapped/LGSynth91/Comb/COMB - Minimal/"); Psthzs_lib.add("lib_min_no_cost.genlib");

            Psthzs.add("teste/cccc/"); Psthzs_lib.add("cadence.genlib");
            /*
            Psthzs.add("teste/mapped/LGSynth91/Comb/COMB - Minimal/"); Psthzs_lib.add("lib_min_no_cost.genlib");
            Psthzs.add("teste/mapped/LGSynth91/Comb/COMB - Complex/"); Psthzs_lib.add("lib_complex_no_cost_no_xor.genlib");
            Psthzs.add("teste/mapped/LGSynth91/Comb/COMB - Full/"); Psthzs_lib.add("lib_full_no_cost.genlib");

            result_path.add("teste/mapped/LGSynth91/result_comb_minimal/"); result_path_filter.add("ALL_SIGNALS_MULTIPLE_MonteCarlo_Simple_Log_");
            result_path.add("teste/mapped/LGSynth91/result_comb_complex/"); result_path_filter.add("ALL_SIGNALS_MULTIPLE_MonteCarlo_Simple_Log_");
            result_path.add("teste/mapped/LGSynth91/result_comb_full/"); result_path_filter.add("ALL_SIGNALS_MULTIPLE_MonteCarlo_Simple_Log_");


                ArrayList <String> circsGates= new ArrayList<>();
                ArrayList <String> filesx = new ArrayList<>();
                filesx.add("alu4");
                filesx.add("vda");
                filesx.add("frg2");
                filesx.add("t481");
                filesx.add("dalu");
                filesx.add("k2");
                filesx.add("pair");
                filesx.add("i8");
                filesx.add("i10");
                filesx.add("des");

                 */


            for (int i = 0; i < Psthzs.size() ; i++) {
                 try {
                     String relativePath = Psthzs.get(i);
                     String genlib = relativePath + Psthzs_lib.get(i);
                     main experimento = new main(threads, constReliability, relativePath, genlib);
                     experimento.preparingEnviroment();
                     int sampleSizeMonteCarlo = 20000;
                     //experimento.PrintCircuitsSpecs(threads, genlib);
                     //experimento.multithreadingSimulationExaustic();
                     //experimento.multithreadingSimulation("ALL_SIGNALS");

                     // STF - SET
                     //experimento.monteCarloSimulation(sampleSizeMonteCarlo, "ALL_SIGNALS"); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"


                    // experimento.parseVerilogToSpiceNetlist(20000, "ALL_SIGNALS");


                        //-- MTF - PROP SET --
                         ArrayList<Float> mtf_sizes = new ArrayList<>();
                         int sample = 20000;
                         mtf_sizes.add((float) sample);
                         mtf_sizes.add((float) 0.9);
                         mtf_sizes.add((float) 0.09);
                         mtf_sizes.add((float) 0.01);
                         System.out.println(mtf_sizes);
                         experimento.setSample(sample);
                         experimento.monteCarloSimulationMultipleTransientFaultsProportion(sample, mtf_sizes, "ALL_SIGNALS");


                     //experimento.readResultsInLot("Resultados Proporção 0.0 0.09 0.01/min/", "ALL_SIGNALS");
                     //experimento.readResultsInLot("teste/mapped/EPFL2015/1-minimal_no_cost/Results/", "ALL_SIGNALS");

                     //experimento.readResultsInLotWithFilter("teste/mapped/LGSynth91/result_comb_minimal/", "ALL_SIGNALS");
                     //experimento.readResultsInLotWithFilter(result_path.get(i), result_path_filter.get(i), filesx);

                                //circsGates = experimento.getCircuitsGates();
                                //experimento.getCircuitsInputsGatesSignals(); // Extract The files results for detailed types of gates
                                //experimento.getCircuitsInputsGatesSignalsFiltered("_lib_min_no_cost.v"); // Extract The files results for detailed types of gates
                     //experimento.getCircuitsInputsGatesSignalsFiltered(Psthzs_lib.get(i).replace(".genlib", ""), filesx); // Extract The files results for detailed types of gates

                 }catch (Exception e){
                     System.err.println("ERRROR  in process ----------- " + e);
                 }

                }

             //String op = "full+xor";
             //experimento.fooAlot("Resultados - Simulação - Diferentes Areas/" + op);
             //experimento.fooExecutionTransistors();
             //experimento.fooExecution(); //Aqui
             
        }

     /**
     * This method return teh circuit list
     */
    public ArrayList<String> getCircuitList(){
            return this.circuitList;
    }

        /**
         * Method to link  the Logic Simulator and run method (runMultithreadingSimulation)
         * @param positionToFaultInjection The signals can be definied as "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
         */
        public void multithreadingSimulation(String positionToFaultInjection) throws Exception{  //Loop na simulação de circuitos
            for (String s : this.circuitList) {
                Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst,
                        this.relativePath, this.genlib, this.relativePath + s);
                simulacaoMultithreading.runMultithreadingSimulation(positionToFaultInjection);
            }
        }

    /**
     * Method to link the Logic Simulator and run method Exhaustive Simulation for SET evaluation (Extracts the Fault Masking Rate - FMR)
     * @throws Exception e e
     */
    public void multithreadingSimulationExaustic() throws Exception{ //ou Signals =  "ALL_SIGNALS" for exaustive consider all_signals
            for (String s : this.circuitList) {
                Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst,
                        this.relativePath, this.genlib, this.relativePath + s);
                simulacaoMultithreading.runMultithreadingExausticSimulation("ALL_SIGNALS");
                this.OUTPUT_INFO = simulacaoMultithreading.getFRM("Sample (N = "
                        + "2^Signals * Gates)");
            }
        }

    /**
     * Method to link the Logic Simulator and run method Exhaustive Complete Simulation for MET evaluation (All combinations) (Extracts the Fault Masking Rate - FMR)
     * @throws Exception e e
     */
    public void multithreadingSimulationExausticComplete() throws Exception{ //ou Signals =  "ALL_SIGNALS" for exaustive consider all_signals
            //Loop na simulação de circuitos
            for (String s : this.circuitList) {
                Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst,
                        this.relativePath, this.genlib, this.relativePath + s);
                simulacaoMultithreading.runMultithreadingExausticSimulationComplete("ALL_SIGNALS");
                this.OUTPUT_INFO = simulacaoMultithreading.getFRM("Sample (N = "
                        + "2^Signals * Gates)");
            }
        }


    /**
     * Method to link  the Logic Simulator and run method (runMultithreadingSimulation)
     * @param monteCarloSample Monte Carlo sample size
     * @param positionToFaultInjection The signals can be definied as "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
     * @throws Exception e
     */
    public void monteCarloSimulation(int monteCarloSample , String positionToFaultInjection) throws Exception{
                this.threads = 8;
            for (String s : this.circuitList) {
                Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst,
                        this.relativePath, this.genlib, this.relativePath + s);
                        simulacaoMultithreading.runMultithreadingMonteCarlo(monteCarloSample, positionToFaultInjection); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
                        this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" Sample(Monte Carlo = N)");
            }
        }


    /**
     * Method to link  the Logic Simulator and run method (runMultithreadingSimulation)
     * @param monteCarloSample Monte Carlo sample size
     * @param positionToFaultInjection The signals can be definied as "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
     * @throws Exception e
     */
    public void parseVerilogToSpiceNetlist(int monteCarloSample , String positionToFaultInjection) throws Exception{
        this.threads = 0;
        for (String s : this.circuitList) {
            System.out.println("---> " + s);
            Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst,
                    this.relativePath, this.genlib, this.relativePath + s);
            simulacaoMultithreading.generateSpiceNetlist(this.relativePath); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
            this.OUTPUT_INFO = simulacaoMultithreading.getFRM("Netlist circuit generated: " + s);
        }
    }


    /**
         * This method is deprecated, was used for link the Logic Simulator and run method runMultipleFaultInjectionMultithreadingMonteCarlo
         * @deprecated
         * @param base base deprecated
         * @param order order deprecated
         * @param frequency frequency deprecated
         * @param sampleSize Monte Carlo sampling deprecated
         * @param Signals Singnals to inject fault deprecated
         * @throws Exception e
         */
        public void monteCarloSimulationMultipleTransientFaults(int base, int order, int frequency, int sampleSize, String Signals) throws Exception{

                System.out.println("Multiple Fault Injection : " + sampleSize);
                System.out.println("Path: " +this.relativePath);
                System.out.println("Genlib: " +this.genlib);
                System.out.println("Circuit: " +this.circuitList);


            for (String s : this.circuitList) {

                //Exaustic mode for debug
                //Orchestrator simulacaoMultithreading_debug = new Orchestrator(this.threads, this.reliabilityConst, this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
                //
                // simulacaoMultithreading_debug.runMultithreadingExausticSimulation("ALL_SIGNALS");


                Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst, this.relativePath, this.genlib, this.relativePath + s);
                //simulacaoMultithreading.PrintCircuitSpecs();
                //str = str + simulacaoMultithreading.PrintCircuitSpecs() + "\n";
                simulacaoMultithreading.runMultipleFaultInjectionMultithreadingMonteCarlo(base, order, frequency, sampleSize, Signals); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"


                this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" MTFT Sample (Monte Carlo = N)");
            }
                //System.out.println("STR: " + str);
    }

    /**
     * This method is deprecated, was used for link the Logic Simulator and run method runMultipleFaultInjectionMultithreadingMonteCarlo
     * @deprecated
     * @param mtf_sizes deprecated
     * @param Signals deprecated
     * @throws Exception e
     */
    public void monteCarloSimulationMultipleTransientFaultsNew(ArrayList <Integer> mtf_sizes, String Signals) throws Exception{
            /*
            System.out.println("Multiple Fault Injection : " + mtf_sizes);
            System.out.println("Path: " +this.relativePath);
            System.out.println("Genlib: " +this.genlib);
            System.out.println("Circuit: " +this.circuitList);
            System.out.println("ARGS:"  +  mtf_sizes) ;
            */

            String str = "";
        for (String s : this.circuitList) {
            Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst, this.relativePath, this.genlib, this.relativePath + s);
            simulacaoMultithreading.runMultipleFaultInjectionMultithreadingMonteCarloSimulation(mtf_sizes, Signals); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
            this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" MTFT Sample (Monte Carlo = N)");
        }

        }

    /**
     * This method links the Logic Simulator and simulate MET evaluation based in proportion of single, doubles, triples, ... faults order (Extracts the Fault Masking Rate - FMR)
     * @param monteCarloSample Monte Carlo sample size
     * @param mtf_sizes Proportion of (single, doubles, triples, ..., n) faults order. Example: 0.9 0.09 0.01 (90% single, 9% double, 1% triple
     * @param positionToFaultInjection The signals can be definied as "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
     * @throws Exception e
     */
    public void monteCarloSimulationMultipleTransientFaultsProportion(int monteCarloSample, ArrayList <Float> mtf_sizes, String positionToFaultInjection) throws Exception{

            System.out.println("Multiple Fault Injection : " + mtf_sizes);
            System.out.println("Path: " +this.relativePath);
            System.out.println("Genlib: " +this.genlib);
            System.out.println("Circuit: " +this.circuitList);
            System.out.println("ARGS:"  +  mtf_sizes) ;


                for (int i = 0; i < this.circuitList.size(); i++) {
                    System.out.println("_"+this.circuitList.get(i));

                   // try {
                        Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst, this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
                        simulacaoMultithreading.runMultipleFaultInjectionMultithreadingMonteCarloSimulationProportion(monteCarloSample, mtf_sizes, positionToFaultInjection);
                        this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" MTFT Sample (Monte Carlo = N)");
                     //   }
                    //catch (Exception e){
                    //    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
                    //}

                }


    }

    /**
     * This method returns an Arraylist of number of gates circuit(s)
     * * @return ArrayList <String> gatesCirctuits
     * @throws Exception e
     */
    public ArrayList <String> getCircuitsGates() throws Exception{

            System.out.println("Gates Area of " + this.circuitList.size() + " circuits");
            ArrayList <String> gatesCirctuits = new ArrayList<>();
            String str = "";
            for (int i = 0; i < this.circuitList.size(); i++) {
                try {
                    System.out.println("_" + i);
                    Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst, this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
                    gatesCirctuits.add(simulacaoMultithreading.PrintCircuitSpecsFast());
                    this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" MTFT Sample (Monte Carlo = N)");
                }
                catch (Exception e){
                    System.err.println("ERORR GETTING GATES _______________");
                }

            }

            return gatesCirctuits;
    }

    /**
     *
     * @param relativePath
     * @param header
     * @param content
     * @param outputFilename
     */
    public void writeInformationInFileLog(String relativePath,  String header, String content, String outputFilename) throws IOException {
        try {
            String content_file = header + "\n" + content;

            relativePath = relativePath + "/netlist_files";
            Path path = Paths.get(relativePath);

            //java.nio.file.Files;
            Files.createDirectories(path);

            System.out.println("Directory is created!");
            try (FileWriter file = new FileWriter(relativePath + "/" + outputFilename + ".txt")) {
                file.write(content_file);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param relativePath
     * @param header
     * @param content
     * @param outputFilename
     */
    public void writeInformationInFileLogcsv(String relativePath,  String header, String content, String outputFilename){
        String content_file = header + "\n" + content;
        try (FileWriter file = new FileWriter(relativePath + "/" + outputFilename + ".csv")) {
            file.write(content_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns an Arraylist compiled of the number Inputs, Signals, and Gates in circuit(s)
     * @throws Exception e
     */
    public void getCircuitsInputsGatesSignals(String filter, ArrayList<String> files_to_filter) throws Exception{

        System.out.println("Gates Area of " + this.circuitList.size() + " circuits");
        ArrayList <String> gatesCirctuits = new ArrayList<>();
        String str = "";
        String compiled = "";
        for (int i = 0; i < this.circuitList.size(); i++) {
            try {
                //System.out.println("_" + i);
                Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst, this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
                gatesCirctuits.add(simulacaoMultithreading.estimateMultithreadingExausticSimulationSize_AND_PRINT_INPUTS_SIGNALS_GATES("ALL_SIGNALS")); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
                //String s = simulacaoMultithreading.PrintGatesCounterDetailsSorted();
                compiled = compiled + simulacaoMultithreading.PrintGatesCounterDetailsSortedCompliled(i, files_to_filter, filter);

               // this.writeInformationInFileLog(relativePath, "circuit; Gates", s, "GATES-" + this.circuitList.get(i));



                this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" MTFT Sample (Monte Carlo = N)");
            }
            catch (Exception e){
                System.err.println("ERROR GETTING GATES_________________");
            }

        }

        String content = "";

        for (int z = 0; z < gatesCirctuits.size(); z++) {
            content =  content + gatesCirctuits.get(z) + "\n";
        }
        this.writeInformationInFileLog(relativePath, "circuit; Inputs; Signals; Gates", content , "COMPILATION-NUMBER_OF_INPUTS_GATES_SIGNALS_CIRCUITLIST");

        this.writeInformationInFileLog(relativePath, "Gates; Circuit", compiled, "-----COMPILEDGATESIDENTIFICATION");
    }

    /**
     * This method returns an Arraylist compiled of the number Inputs, Signals, and Gates in circuit(s)
     * @throws Exception e
     */
    public void getCircuitsInputsGatesSignalsFiltered(String filter, ArrayList<String> files_to_filter) throws Exception{

        System.out.println("- Extracting Gates Area from " + this.circuitList.size() + " circuits (.v)");
        ArrayList <String> gatesCirctuits = new ArrayList<>();

        String compiled = "";
            /*
            ArrayList <String> filesx = new ArrayList<>();
            filesx.add("alu4");
            filesx.add("vda");
            filesx.add("frg2");
            filesx.add("t481");
            filesx.add("dalu");
            filesx.add("k2");
            filesx.add("pair");
            filesx.add("i8");
            filesx.add("i10");
            filesx.add("des");
            */
        /*
        for (String file_temp: filesx){
            String tmp = "ALL_SIGNALS_MULTIPLE_MonteCarlo_Simple_Log_" + file_temp + "_lib_min_no_cost_Threads-4_sampleSize-20000.txt";
            if(files.get(i).contains(tmp)){
                System.out.println("->" + files.get(i));
                filtered_files.add(files.get(i));
            }
        }*/
        System.out.println("- Filter_library: " + filter);
        for (int i = 0; i < this.circuitList.size(); i++) {
            //System.out.println("------> " + this.circuitList.get(i));
            try {
                for (String file_temp: files_to_filter){
                    String tmp =  file_temp + "_" + filter; ///"_lib_min_no_cost.v";
                    if(this.circuitList.get(i).contains(tmp)){
                        //System.out.println("_" + i);
                        //System.out.println("    -" + this.circuitList.get(i));
                        Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst, this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
                        gatesCirctuits.add(simulacaoMultithreading.estimateMultithreadingExausticSimulationSize_AND_PRINT_INPUTS_SIGNALS_GATES("ALL_SIGNALS")); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
                        //String s = simulacaoMultithreading.PrintGatesCounterDetailsSorted();
                        compiled = compiled + simulacaoMultithreading.PrintGatesCounterDetailsSortedCompliled(i, files_to_filter, filter);
                        // this.writeInformationInFileLog(relativePath, "circuit; Gates", s, "GATES-" + this.circuitList.get(i));
                        this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" MTFT Sample (Monte Carlo = N)");
                        System.out.println("\n");
                    }
                }
            }
            catch (Exception e){
                System.err.println("ERROR GETTING GATES_________________");
            }
        }

        String content = "";
        System.out.println("COMPILED GATES TYPES: \n" + compiled);

        for (int z = 0; z < gatesCirctuits.size(); z++) {
            content =  content + gatesCirctuits.get(z) + "\n";
        }
        this.writeInformationInFileLogcsv(relativePath, "circuit; Inputs; Signals; Gates", content , "COMPILATION-NUMBER_OF_INPUTS_GATES_SIGNALS_CIRCUITLISTS_");

        this.writeInformationInFileLogcsv(relativePath, "Gates; Circuit", compiled, "Compilation_GatesTypes_");
    }

    /**
     * This method estimates the sensitive areas compiled (Exhaustive method), based on the extraction of the sensitive cells lookup table (csv file). For each vector is complied an individual total susceptible SET/MET area
     * @param monteCarloSample Monte Carlo sample size
     * @param mtf_sizes
     * @param Signals
     * @throws Exception e
     */
    public void CalculateSensitiveAreaExhative(int monteCarloSample, ArrayList <Float> mtf_sizes, String Signals) throws Exception{
        /*
        System.out.println("Multiple Fault Injection : " + mtf_sizes);
        System.out.println("Path: " +this.relativePath);
        System.out.println("Genlib: " +this.genlib);
        System.out.println("Circuit: " +this.circuitList);
        System.out.println("ARGS:"  +  mtf_sizes) ;
        */

        for (int i = 0; i < this.circuitList.size(); i++) {
            Orchestrator simulacaoMultithreadingCalculateSensitiveArea = new Orchestrator(this.threads, this.reliabilityConst, this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
            simulacaoMultithreadingCalculateSensitiveArea.runCalculationSensitiveAreas(Signals, "teste/lookup_table.csv");
            this.OUTPUT_INFO = simulacaoMultithreadingCalculateSensitiveArea.getFRM(" the computation of sensitive areas is complete");
        }
        //System.out.println("STR: " + str);
    }


    /**
     * Debug Method
     * @param sampleSize
     * @param Signals
     * @throws Exception e
     */
    public void PrintCircuitsSpecs(int sampleSize, String Signals) throws Exception{
                //Loop na simulação de circuitos 
                String str = "";
                for (int i = 0; i < this.circuitList.size(); i++) {
                     Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst,
                             this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
                             //simulacaoMultithreading.PrintCircuitSpecs();
                             str = str + this.circuitList.get(i)+ ";" + simulacaoMultithreading.PrintCircuitSpecs() + "\n";
                            // simulacaoMultithreading.runMultithreading_MonteCarloSample_per_Area_Analisys(sampleSize, Signals, smallestGatesIncicuitsSimulation); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
                }
                System.out.println("------");
                System.out.println(str);
                System.out.println("------");
        }


    /**
     * Method for prepare the simulation enviroment for batch simulation, it's filters the verilogs files (.v) present in the inputed folder
     */
    public void preparingEnviroment(){

                String[] circuitFiles;
                File f = new File(this.relativePath);
               
                circuitFiles = f.list();
                
                for (String pathname : circuitFiles) {
                    if(pathname.endsWith(".v")){ // test type .v
                        this.circuitList.add(pathname);
                    }  
                }
                //System.out.println("Circuits in List: " +  this.circuitList);
        System.out.println("- Were founded " + this.circuitList.size() + " circuit (.v) in path folder: " + this.relativePath );
               
        }

    /**
     * Method for prepare the simulation enviroment for single circuit simulation
     */
    public void preparingEnviromentSingleFile(String circuit){
                this.circuitList.add(circuit);
                System.out.println("Circuits in analysis: " +  this.circuitList);

    }
       
        public void fooAlot(String folderPath) throws IOException{

             this.readResultsInLot(folderPath, "ALL_SIGNALS");
             
        }

    /**
     * This method reads the result files (is used embeeded another method)
     * @param filename Log filename
     * @return List<String>
     */
    private List<String> readFile(String filename)
        {
                            List<String> records = new ArrayList<String>();
                            try
                            {
                              BufferedReader reader = new BufferedReader(new FileReader(filename));
                              String line;
                              while ((line = reader.readLine()) != null)
                              {
                                records.add(line);
                              }
                              reader.close();
                              return records;
                            }
                            catch (Exception e)
                            {
                              System.err.format("Exception occurred trying to read '%s'.", filename);
                              e.printStackTrace();
                              return null;
                             }
        }
        /**
     * This method parses circuits results logs SET (FMR log) files, to combine the simulation informations (circuit, sample, Ne, FMR, time)  in a third one log (combining all circuits)
     * @param files Arraylist with circuits names
     * @param path circuits path
     * @param filter filter (.v)
     * @throws IOException e
     */
        public void readEachFile(ArrayList<String> files, String path, String filter) throws IOException{
         
            ArrayList <String> FileContent = new ArrayList<>();
            for (int i = 0; i < files.size(); i++) {
                   List<String> fileContentList  = this.readFile(path + "/" +files.get(i));
                   System.out.println("Records: " + fileContentList);
                        
                        for (String x : fileContentList){
                           
                            if((x.contains("Reliability (soft error):"))|| (x.contains("Number of detected faults (Ne)"))){
                               
                                String[] softErrorRate = x.split(":");
                                //out = files.get(i) + ";" + t[1];
                                //System.out.println("----- +" + t[1]);
                                String[] sampleSize = fileContentList.get(4).split(":");
                                String[] Ne = fileContentList.get(6).split(":");
                                String[] time = fileContentList.get(8).split(":");
                                
                                FileContent.add(files.get(i) + ";" + sampleSize[1] + ";" + Ne[1] + ";" + softErrorRate[1] + ";" + time[1]);
                                System.out.println("File: " + files.get(i)  +  " sample: " + sampleSize[1] + " > " + x + " t(s):" + time[1] );
                            }
                           
                        }
                   
            }
            
            
                PrintWriter pw = new PrintWriter(new FileOutputStream(path + "/" + filter + "_.txt"));
                for (String club : FileContent) {
                    pw.println(club);
                }
                pw.close();
                System.out.println("Created file : " + path + "/" + filter + "_.txt" );
                /*
                System.out.println(path + "/" + " resultado_.txt");
                File file = new File(path + "/" + " resultado_.txt");
                if(file.createNewFile()){
                System.out.println(" File Created");
                }else System.out.println("File  already exists");
                 */
            
           
       
        }

    /**
     * This method parses circuits results logs MET (FMR log) files, to combine the simulation informations (circuit, sample, Ne, FMR, time)  in a third one log (combining all circuits)
     * @param files Arraylist with circuits names
     * @param path circuits path
     * @param filter filter (.v)
     * @throws IOException e
     */
    public void readEachFileProportion(ArrayList<String> files, String path, String filter) throws IOException{

        ArrayList <String> FileContent = new ArrayList<>();
        String sample = "";
        String Ne  = "";
        String FMR = "";
        String  time = "";

        FileContent.add("File; sample; NE; Time(s);");
        for (int i = 0; i < files.size(); i++) {
            List<String> fileContentList  = this.readFile(path + "/" +files.get(i));
            System.out.println("Records: " + fileContentList);

            for (String x : fileContentList){

                if((x.contains("Reliability (soft error):"))|| (x.contains("Number of detected faults (Ne)"))){
                    System.out.println("INSIDE");
                    ///String[] softErrorRate = x.split(":");
                    //out = files.get(i) + ";" + t[1];
                    //System.out.println("----- +" + t[1]);
                    //String[] sampleSize = fileContentList.get(3).split(":");
                    String[] tttt =   x.split(":");
                    Ne = tttt[1]; //fileContentList.get(9).split(":");
                    //String[] time = fileContentList.get(10).split(":");

                    //FileContent.add(files.get(i) + ";" + sampleSize[1] + ";" + Ne[1] + ";" + softErrorRate[1] + ";" + time[1]);
                    //FileContent.add(files.get(i) + ";" + sampleSize[1] + ";" + Ne[1] + ";" + time[1]);
                    //System.out.println("File: " + files.get(i)  +  " sample: " + sampleSize[1] + " > " + x + " t(s):" + time[1] );
                }
                if(x.contains("- Sample") || (x.contains("Number of Simulations"))){
                    String[] tttt =   x.split("=");
                    sample = tttt[1]; //fileContentList.get(9).split(":");
                }
                if(x.contains("Performance time")){
                    String[] tttt =   x.split(":");
                    time = tttt[1]; //fileContentList.get(9).split(":");
                }


            }
            FileContent.add(files.get(i) + ";" + sample + ";" + Ne + ";" + time);
            System.out.println("File: " + files.get(i)  +  " sample: " + sample + " > " + Ne + " t(s):" + time );
        }


        PrintWriter pw = new PrintWriter(new FileOutputStream(path + "/" + filter + "_.txt"));
        for (String club : FileContent) {
            pw.println(club);
        }
        pw.close();
        System.out.println("ARquivo criado: " + path + "/" + filter + "_.txt" );
                /*
                System.out.println(path + "/" + " resultado_.txt");
                File file = new File(path + "/" + " resultado_.txt");
                if(file.createNewFile()){
                System.out.println(" File Created");
                }else System.out.println("File  already exists");
                 */



    }

    /**
     * This method parses circuits results logs MET (FMR log) files to .csv, to combine the simulation informations (circuit, sample, Ne, FMR, time)  in a third one log (combining all circuits)
     * @param files Arraylist with circuits names
     * @param path circuits path
     * @param filter filter (.v)
     * @throws IOException e
     */
    public void readEachFileProportioncsv(ArrayList<String> files, String path, String filter) throws IOException{

        ArrayList <String> FileContent = new ArrayList<>();
        String sample = "";
        String Ne  = "";
        String FMR = "";
        String  time = "";

        FileContent.add("File; sample; NE; Time(s);");
        for (int i = 0; i < files.size(); i++) {
            List<String> fileContentList  = this.readFile(path + "/" +files.get(i));
            //System.out.println("Records: " + fileContentList);

            for (String x : fileContentList){

                if((x.contains("Reliability (soft error):"))|| (x.contains("Number of detected faults (Ne)"))){
                    //System.out.println("INSIDE");
                    ///String[] softErrorRate = x.split(":");
                    //out = files.get(i) + ";" + t[1];
                    //System.out.println("----- +" + t[1]);
                    //String[] sampleSize = fileContentList.get(3).split(":");
                    String[] tttt =   x.split(":");
                    Ne = tttt[1]; //fileContentList.get(9).split(":");
                    //String[] time = fileContentList.get(10).split(":");

                    //FileContent.add(files.get(i) + ";" + sampleSize[1] + ";" + Ne[1] + ";" + softErrorRate[1] + ";" + time[1]);
                    //FileContent.add(files.get(i) + ";" + sampleSize[1] + ";" + Ne[1] + ";" + time[1]);
                    //System.out.println("File: " + files.get(i)  +  " sample: " + sampleSize[1] + " > " + x + " t(s):" + time[1] );
                }
                if(x.contains("- Sample") || (x.contains("Number of Simulations"))){
                    String[] tttt =   x.split("=");
                    sample = tttt[1]; //fileContentList.get(9).split(":");
                }
                if(x.contains("Performance time")){
                    String[] tttt =   x.split(":");
                    time = tttt[1]; //fileContentList.get(9).split(":");
                }


            }
            FileContent.add(files.get(i) + ";" + sample + ";" + Ne + ";" + time);
            //System.out.println("File: " + files.get(i)  +  " sample: " + sample + " > " + Ne + " t(s):" + time );
        }


        PrintWriter pw = new PrintWriter(new FileOutputStream(path + "/" + "Results_compiled" + "_.csv"));
        for (String club : FileContent) {
            pw.println(club);
        }
        pw.close();
        System.out.println("");
        System.out.println("- Created file: " + path + "Results_compiled" + ".csv" );
                /*
                System.out.println(path + "/" + " resultado_.txt");
                File file = new File(path + "/" + " resultado_.txt");
                if(file.createNewFile()){
                System.out.println(" File Created");
                }else System.out.println("File  already exists");
                 */



    }



    /**
     * This method parses multiple circuits results logs in a third one log (combining all circuits) MAJOR METHOD
     * @param path circuits path
     * @param filter filter (.v)
     * @throws IOException e
     */
    public void readResultsInLot(String path, String filter) throws IOException{
                
                String[] circuitFiles;
                File f = new File(path);
                ArrayList <String> files = new ArrayList<>();
                ArrayList <String> filtered_files = new ArrayList<>();
                circuitFiles = f.list();
                
                for (String pathname : circuitFiles) {
                    if(pathname.endsWith(".txt")){ // test tipe .v
                        //System.out.println(pathname);
                        //circuitList.add(pathname); 
                        //this.circuitList.add(pathname);
                        files.add(pathname);
                    }  
                }
                System.out.println("results in List: " +  files);
                System.out.println("Size List: " +  files.size());
                
                for (int i = 0; i < files.size(); i++) {
                        if(files.get(i).contains(filter)){
                            System.out.println("->" + files.get(i));
                            filtered_files.add(files.get(i));
                        }
                }
                /*
                List<String> records  = this.readFile(path + "/" +files.get(0));
                System.out.println("Records: " + records);

                for (String x : records){
                    System.out.println(">"+x);
                }
                 */

                
                //this.readEachFile(filtered_files, path, filter);
                 this.readEachFileProportion(filtered_files, path, filter);
                System.out.println("------------------------------------------");
                    
        }

    /**
     * This method compile a lot of results (*.txt) in a single .csv file
     * @param path Results file path
     * @param filter String to filter results
     * @throws IOException
     */
    public void readResultsInLotWithFilter(String path, String filter, ArrayList <String> filesx) throws IOException{

        System.out.println("- Reading Result files... ");
        String[] circuitFiles;
        File f = new File(path);
        ArrayList <String> files = new ArrayList<>();
        ArrayList <String> filtered_files = new ArrayList<>();
        circuitFiles = f.list();

        if(Files.exists(f.toPath())){
                for (String pathname : circuitFiles) {
                    if(pathname.endsWith(".txt")){
                        files.add(pathname);
                    }
                }

                System.out.println("- Path folder: " + path);
                System.out.println("- Size List: " +  files.size());
                System.out.println("- Filtered files: ");
                for (int i = 0; i < files.size(); i++) {
                    for (String file_temp: filesx){
                        //String tmp = "ALL_SIGNALS_MULTIPLE_MonteCarlo_Simple_Log_" + file_temp + "_lib_full_no_cost_Threads-4_sampleSize-20000.txt";//"_lib_complex_no_cost_no_xor_Threads-4_sampleSize-20000.txt";
                        String tmp = filter + file_temp;
                        if(files.get(i).contains(tmp)){
                                System.out.println("    -" + files.get(i));
                            filtered_files.add(files.get(i));
                        }
                    }
                }
                System.out.println("- Total filtered files: " + filtered_files.size());
                this.readEachFileProportioncsv(filtered_files, path, filter);

                System.out.println("\n ------ End Process -------\n");
        }
        else{
            System.err.println("x Path to result files do not founded: " + path );
            }
    }


    /**
     * dev methods
      * @throws Exception e
     */
    public void fooExecutionTransistors() throws Exception{
            
             //this.fooTransistors("Simulação Circuitos - ABC/min/", "lib_min_no_cost.genlib"); // 1° genlib
             
            //this.fooTransistors("Simulação Circuitos - ABC/basic/", "lib_basic_no_cost.genlib"); // 2° genlib
             
            //this.fooTransistors("Simulação Circuitos - ABC/complex/", "lib_complex_no_cost_no_xor.genlib"); // 3° genlib

             //this.fooTransistors("Simulação Circuitos - ABC/full/", "lib_full_no_cost_no_xor.genlib"); // 4° genlib
             
            // this.fooTransistors("Simulação Circuitos - ABC/full + xor/", "lib_full_no_cost.genlib"); // 5° genlib
             
             
             /*
            double timeInSeconds = 1;
            Runtime runtime = Runtime.getRuntime();
            String a;
            Process proc = runtime.exec("shutdown -s -t 1");
            System.exit(0);
            */
             
             this.foo("Simulação Circuitos - ABC/full + xor/", "lib_full_no_cost.genlib"); // 5° genlib
             
               //this.foo("teste/", "cadence.genlib");
               //this.foo("Simulação Circuitos - ABC/basic/", "lib_basic_no_cost.genlib");
               //this.foo(relativePath, genlib);
           }
    /**
     * dev methods
     * @throws Exception e
     */
        public void fooTransistors(String relativePath , String genlibTemp) throws Exception{
            
             int threads = 8; //Numero de threads
             int sampleSizeMonteCarlo = 20000;
             String constReliability = "0.9999"; //Used for internal structures
             //String relativePath = "teste/";
             String genlib =  relativePath  + genlibTemp;
            
             main experimento_genlib = new main(threads, constReliability, relativePath, genlib);
             experimento_genlib.preparingEnviroment(); 
             //experimento_genlib.multithreadingSimulation("ALL_SIGNALS");
             //experimento_genlib.multithreadingSimulationExaustic();
             //experimento_genlib
             //experimento_genlib.monteCarloSimulation(sampleSizeMonteCarlo, "INTERMEDIATE");  //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
             //experimento_genlib.monteCarloSimulation(sampleSizeMonteCarlo, "INTERMEDIATE_AND_OUTPUTS"); 
             experimento_genlib.PrintCircuitsSpecs(threads, genlib);
             //experimento_genlib.monteCarloSimulation_Per_Area(sampleSizeMonteCarlo, "ALL_SIGNALS", 180);  //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"

        }
    /**
     * dev methods
     * @throws Exception e
     */
        public void fooExecution() throws Exception{
            
            //this.foo("testar/", "cadence.genlib");
            
            
             //this.foo("Simulação Circuitos - ABC/min/", "lib_min_no_cost.genlib"); // 1° genlib
          
             this.foo("Simulação Circuitos - ABC/basic/", "lib_basic_no_cost.genlib"); // 2° genlib
             
             //this.foo("Simulação Circuitos - ABC/complex/", "lib_complex_no_cost_no_xor.genlib"); // 3° genlib

             //this.foo("Simulação Circuitos - ABC/full/", "lib_full_no_cost_no_xor.genlib"); // 4° genlib
            
             
             /*
            double timeInSeconds = 1;
            Runtime runtime = Runtime.getRuntime();
            String a;
            Process proc = runtime.exec("shutdown -s -t 1");
            System.exit(0);
            */
             
             //this.foo("Simulação Circuitos - ABC/full + xor/", "lib_full_no_cost.genlib"); // 5° genlib
             
               //this.foo("teste/", "cadence.genlib");
               //this.foo("Simulação Circuitos - ABC/basic/", "lib_basic_no_cost.genlib");
               //this.foo(relativePath, genlib);
           }
    /**
     * dev methods
     * @throws Exception e
     */
        public void foo(String relativePath , String genlibTemp) throws Exception{
            
             int threads = 8; //Numero de threads
             int sampleSizeMonteCarlo = 20000;
             String constReliability = "0.9999"; //Used for internal structures
             //String relativePath = "teste/";
             String genlib =  relativePath  + genlibTemp;
            
             main experimento_genlib = new main(threads, constReliability, relativePath, genlib);
             experimento_genlib.preparingEnviroment(); 
             //experimento_genlib.multithreadingSimulation("ALL_SIGNALS");
             //experimento_genlib.multithreadingSimulationExaustic();
             //experimento_genlib
             //experimento_genlib.monteCarloSimulation(sampleSizeMonteCarlo, "INTERMEDIATE");  //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
             //experimento_genlib.monteCarloSimulation(sampleSizeMonteCarlo, "INTERMEDIATE_AND_OUTPUTS"); 
             experimento_genlib.foo3(sampleSizeMonteCarlo, "ALL_SIGNALS"); 
             
             //experimento_genlib.PrintCircuitsSpecs(1, "ALL_SIGNALS");
             //experimento_genlib.monteCarloSimulation_Per_Area(sampleSizeMonteCarlo, "ALL_SIGNALS", 180);  //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"

        }

        /**
     * dev methods
     * @throws Exception e
     */
        public void foo3(int sampleSize, String Signals) throws Exception{
            //Loop na simulação de circuitos
            //String str = "";

            ArrayList <Integer> N = new ArrayList<>();
            //c1355         c1908           c2670           c3540           c432            c499            c5315           c6288           c7552           c880
            // Min N.add(20353);	N.add(19557);	N.add(33793);	N.add(43664);	N.add(10973);	N.add(20353);	N.add(66834);	N.add(123588);	N.add(88712);	N.add(13341);

            /*Basic N.add(18988);   N.add(16765);   N.add(25985);   N.add(39434);	 N.add(8739);	 N.add(18988);	 N.add(51993);	 N.add(117352);	 N.add(727540);	  N.add(10673);*/

            /// COmplex N.add(19009);	N.add(19226);	N.add(26357);	N.add(35277);	N.add(8863);	N.add(17664);	N.add(54051);	//N.add(111984);	//N.add(77284);	 N.add(10435);

            /* Full */ /* N.add(20250);	N.add(19319);	N.add(25395);	N.add(35349);	N.add(8470);	N.add(19091);*/	 /*N.add(51931);*/ //N.add(131768); N.add(79269); // N.add(9908);


            /* Full + XOR  N.add(9887);	 N.add(11159);	 N.add(22044);	 N.add(30168);	 N.add(8129);	 N.add(9887);	 N.add(39810);*/	//N.add(64545);  N.add(52938); // N.add(8739);

            if(N.size() == this.circuitList.size()){
                for (int i = 0; i < this.circuitList.size(); i++) {

                    Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst,
                            this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
                    //simulacaoMultithreading.PrintCircuitSpecs();
                    //str = str + simulacaoMultithreading.PrintCircuitSpecs() + "\n";
                    simulacaoMultithreading.runMultithreadingMonteCarlo(N.get(i), Signals); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
                }
            }
            //System.out.println("STR: " + str);
        }

        
}

