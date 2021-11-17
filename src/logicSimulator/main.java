/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicSimulator;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
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
        
        
        
        public main(int threads, String reliabilityConst, String relativePath, String genlib) {
            this.threads = threads;
            this.reliabilityConst = reliabilityConst;
            this.relativePath = relativePath;
            this.genlib = genlib;
            this.OUTPUT_INFO = "";
            this.sample = 0;
        }

    public void setSample(int sample) {
        this.sample = sample;
    }

    public String getFMR(){
            return this.OUTPUT_INFO;
        }
        
        public static void main(String[] args) throws Exception {
            
             int threads = 4; //Número de threads
             int sampleSizeMonteCarlo = 20000;
                     
             String constReliability = "0.9999"; //Used for internal structures

            ArrayList <String> Psthzs = new ArrayList<>();
            ArrayList <String> Psthzs_lib = new ArrayList<>();
            //Psthzs.add("circuitos/fullv2/"); Psthzs_lib.add("lib_full_no_cost.genlib");
            //Psthzs.add("circuitos/full/"); Psthzs_lib.add("lib_full_no_cost_no_xor.genlib");
            //Psthzs.add("circuitos/complex/"); Psthzs_lib.add("lib_complex_no_cost_no_xor.genlib");
            //Psthzs.add("circuitos/basic/"); Psthzs_lib.add("lib_basic_no_cost.genlib");
            //Psthzs.add("circuitos/min/"); Psthzs_lib.add("lib_min_no_cost.genlib");

            Psthzs.add("teste/mapped/EPFL2015/1-minimal_no_cost/"); Psthzs_lib.add("1-minimal_no_cost.genlib");

            //Psthzs.add("teste/mapped/EPFL2015/5-full_no_cost/"); Psthzs_lib.add("5-full_no_cost.genlib");


            ArrayList <String> circsGates= new ArrayList<>();

            for (int i = 0; i < Psthzs.size() ; i++) {
               // try {

                String relativePath =  Psthzs.get(i); //"circuitos/fullv2/"; //"circuitos/fullv2/";//"circuitos/full/";//"circuitos/basic/"; //"circuitos/min/";
                //String genlib =  relativePath  + "lib_basic_no_cost.genlib";
                String genlib =  relativePath  + Psthzs_lib.get(i); // "lib_full_no_cost.genlib"; // "lib_complex_no_cost_no_xor.genlib"; //"lib_basic_no_cost.genlib";//"lib_min_no_cost.genlib"; //"cadence.genlib";

                main experimento = new main(threads, constReliability, relativePath, genlib);
                experimento.preparingEnviroment();

                //experimento.PrintCircuitsSpecs(threads, genlib);
                //experimento.multithreadingSimulationExaustic();
                //experimento.multithreadingSimulation("ALL_SIGNALS");
                //experimento.monteCarloSimulation(sampleSizeMonteCarlo, "ALL_SIGNALS");

                ArrayList<Float> mtf_sizes = new ArrayList<>();
                int sample = 20000;
                mtf_sizes.add((float) sample);
                mtf_sizes.add((float) 1);
                //mtf_sizes.add((float) 1);
                //mtf_sizes.add((float)0.00);
                System.out.println(mtf_sizes);
                experimento.setSample(sample);

                    //experimento.monteCarloSimulationMultipleTransientFaultsProportion(experimento.sample, mtf_sizes, "ALL_SIGNALS");
                    //experimento.readResultsInLot("Resultados Proporção 0.0 0.09 0.01/fullv2/", "ALL_SIGNALS");
                    // experimento.readResultsInLot("Resultados Proporção 0.0 0.09 0.01/full/", "ALL_SIGNALS");
                    //experimento.readResultsInLot("Resultados Proporção 0.0 0.09 0.01/complex/", "ALL_SIGNALS");
                    //experimento.readResultsInLot("Resultados Proporção 0.0 0.09 0.01/basic/", "ALL_SIGNALS");
                    //experimento.readResultsInLot("Resultados Proporção 0.0 0.09 0.01/min/", "ALL_SIGNALS");

                     // experimento.readResultsInLot("teste/mapped/EPFL2015/5-full_no_cost/Results/", "ALL_SIGNALS");
                //experimento.readResultsInLot("teste/mapped/EPFL2015/1-minimal_no_cost/Results/", "ALL_SIGNALS");


                    //circsGates = experimento.getCircuitsGates();

                  experimento.getCircuitsInputsAndGates();
                  exper





              //  }
                //catch (Exception e){
                //    System.out.println("ERRORRRRRR !!!!");
               // }

            }

            System.out.println("Lista Completa:  " + circsGates);


             
            // String op = "full+xor";
            // experimento.fooAlot("Resultados - Simulação - Diferentes Áreas/" + op);
             //experimento.fooExecutionTransistors();
             
            

             //experimento.fooExecution(); //Aqui
             
             /*
             experimento.readResultsInLot("Resultados - Todas as simulações - 5 bibliotecas - ISCAS85/min", "ALL_SIGNALS");
             experimento.readResultsInLot("Resultados - Todas as simulações - 5 bibliotecas - ISCAS85/min", "INTERMEDIATE_AND_OUTPUTS");
             experimento.readResultsInLot("Resultados - Todas as simulações - 5 bibliotecas - ISCAS85/min", "INTERMEDIATE_Multithreading_");
             */
             
             /*
             experimento.readResultsInLot("Resultados - Todas as simulações - 5 bibliotecas - ISCAS85/basic", "ALL_SIGNALS");
             
             
             experimento.readResultsInLot("Resultados - Todas as simulações - 5 bibliotecas - ISCAS85/basic", "INTERMEDIATE_AND_OUTPUTS");
             
             experimento.readResultsInLot("Resultados - Todas as simulações - 5 bibliotecas - ISCAS85/basic", "INTERMEDIATE_Multithreading_");
              
             */
 
             
             // experimento.readResultsInLot("Resultados - Todas as simulações - 5 bibliotecas - ISCAS85/complex", "ALL_SIGNALS");
              //experimento.readResultsInLot("Resultados - Todas as simulações - 5 bibliotecas - ISCAS85/full", "ALL_SIGNALS");
              
              //experimento.readResultsInLot("Resultados - Todas as simulações - 5 bibliotecas - ISCAS85/full+xor", "ALL_SIGNALS");
             //experimento.multithreadingSimulation("ALL_SIGNALS"); //TRue Table - //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
             
             //experimento.monteCarloSimulation(sampleSizeMonteCarlo, "ALL_SIGNALS"); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
        }

        public ArrayList<String> getCircuitList(){
            return this.circuitList;
        }

        public void multithreadingSimulation(String Signals) throws Exception{ //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
                //Loop na simulação de circuitos 
                for (int i = 0; i < this.circuitList.size(); i++) {
                     Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst,
                             this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
                            simulacaoMultithreading.runMultithreadingSimulation(Signals); 
                }
        }
        
        public void multithreadingSimulationExaustic() throws Exception{ //ou Signals =  "ALL_SIGNALS" for exaustive consider all_signals
                //Loop na simulação de circuitos 
                for (int i = 0; i < this.circuitList.size(); i++) {
                     Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst,
                             this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
                             simulacaoMultithreading.runMultithreadingExausticSimulation("ALL_SIGNALS"); 
                             this.OUTPUT_INFO = simulacaoMultithreading.getFRM("Sample (N = "
                                     + "2^Signals * Gates)");
                }
        }

        public void multithreadingSimulationExausticComplete() throws Exception{ //ou Signals =  "ALL_SIGNALS" for exaustive consider all_signals
            //Loop na simulação de circuitos
            for (int i = 0; i < this.circuitList.size(); i++) {
                Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst,
                        this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
                simulacaoMultithreading.runMultithreadingExausticSimulationComplete("ALL_SIGNALS");
                this.OUTPUT_INFO = simulacaoMultithreading.getFRM("Sample (N = "
                        + "2^Signals * Gates)");
            }
        }
        
        public void Area_monteCarloSimulation(int sampleSize, String Signals) throws Exception{
                //Loop na simulação de circuitos 
                String str = "";
                
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
        
        public void monteCarloSimulation(int sampleSize, String Signals) throws Exception{
                //Loop na simulação de circuitos 
                System.out.println("Path: " +this.relativePath);
                 System.out.println("Genlib: " +this.genlib);
                   System.out.println("Circuit: " +this.circuitList);

                   this.threads = 8;
                
                String str = "";

                for (int i = 0; i < this.circuitList.size(); i++) {
                     Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst,
                             this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
                             //simulacaoMultithreading.PrintCircuitSpecs();
                             //str = str + simulacaoMultithreading.PrintCircuitSpecs() + "\n";
                             simulacaoMultithreading.runMultithreadingMonteCarlo(sampleSize, Signals); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
                             this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" Sample(Monte Carlo = N)");
                }
                //System.out.println("STR: " + str);
        }

        public void monteCarloSimulationMultipleTransientFaults(int base, int order, int frequency, int sampleSize, String Signals) throws Exception{

                System.out.println("Multiple Fault Injection : " + sampleSize);
                System.out.println("Path: " +this.relativePath);
                System.out.println("Genlib: " +this.genlib);
                System.out.println("Circuit: " +this.circuitList);



                String str = "";
                for (int i = 0; i < this.circuitList.size(); i++) {

                    //Exaustic mode for debug
                    Orchestrator simulacaoMultithreading_debug = new Orchestrator(this.threads, this.reliabilityConst, this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
                    //
                    // simulacaoMultithreading_debug.runMultithreadingExausticSimulation("ALL_SIGNALS");




                    Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst, this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
                    //simulacaoMultithreading.PrintCircuitSpecs();
                    //str = str + simulacaoMultithreading.PrintCircuitSpecs() + "\n";
                    simulacaoMultithreading.runMultipleFaultInjectionMultithreadingMonteCarlo(base, order, frequency,sampleSize, Signals); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"


                    this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" MTFT Sample (Monte Carlo = N)");
                }
                //System.out.println("STR: " + str);
    }

        public void monteCarloSimulationMultipleTransientFaultsNew(ArrayList <Integer> mtf_sizes, String Signals) throws Exception{

            System.out.println("Multiple Fault Injection : " + mtf_sizes);
            System.out.println("Path: " +this.relativePath);
            System.out.println("Genlib: " +this.genlib);
            System.out.println("Circuit: " +this.circuitList);

            System.out.println("ARGS:"  +  mtf_sizes) ;

            String str = "";
            for (int i = 0; i < this.circuitList.size(); i++) {

                //Exaustic mode for debug
               //Orchestrator simulacaoMultithreading_debug = new Orchestrator(this.threads, this.reliabilityConst, this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));

                //simulacaoMultithreading_debug.runMultithreadingExausticSimulation("ALL_SIGNALS");

               // simulacaoMultithreading_debug.runMultithreadingExausticSimulationComplete("ALL_SIGNALS");

                    //
                // double x = 0.9999;
                /*
                    main experimento = new main(threads, "0.9999", relativePath, genlib);

                    experimento.preparingEnviroment();

                    experimento.monteCarloSimulation(20000, "ALL_SIGNALS");

                    System.out.println("\n\n\n" + "");
                */

                Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst, this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
                //simulacaoMultithreading.PrintCircuitSpecs();
                //str = str + simulacaoMultithreading.PrintCircuitSpecs() + "\n";
                //simulacaoMultithreading.runMultipleFaultInjectionMultithreadingMonteCarlo(base, order, frequency,sampleSize, Signals); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"

                simulacaoMultithreading.runMultipleFaultInjectionMultithreadingMonteCarloSimulation(mtf_sizes, Signals); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"


                this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" MTFT Sample (Monte Carlo = N)");
            }
            //System.out.println("STR: " + str);
        }

        public void monteCarloSimulationMultipleTransientFaultsProportion(int sample, ArrayList <Float> mtf_sizes, String Signals) throws Exception{

            System.out.println("Multiple Fault Injection : " + mtf_sizes);
            System.out.println("Path: " +this.relativePath);
            System.out.println("Genlib: " +this.genlib);
            System.out.println("Circuit: " +this.circuitList);

            System.out.println("ARGS:"  +  mtf_sizes) ;

            String str = "";
            for (int i = 0; i < this.circuitList.size(); i++) {
                System.out.println("_"+i);
                //Exaustic mode for debug
                //Orchestrator simulacaoMultithreading_debug = new Orchestrator(this.threads, this.reliabilityConst, this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));

                //simulacaoMultithreading_debug.runMultithreadingExausticSimulation("ALL_SIGNALS"); //STF

                //simulacaoMultithreading_debug.runMultithreadingExausticSimulationComplete("ALL_SIGNALS"); //MTF

                //
                // double x = 0.9999;
                    /*
                        main experimento = new main(threads, "0.9999", relativePath, genlib);

                        experimento.preparingEnviroment();

                        experimento.monteCarloSimulation(20000, "ALL_SIGNALS");

                        System.out.println("\n\n\n" + "");
                    */

                Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst, this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
                //simulacaoMultithreading.PrintCircuitSpecs();
                //str = str + simulacaoMultithreading.PrintCircuitSpecs() + "\n";

                //simulacaoMultithreading.runMultipleFaultInjectionMultithreadingMonteCarlo(base, order, frequency,sampleSize, Signals); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"

                /*
                ArrayList <Integer> teste = new ArrayList<>();
                teste.add(20000);
                teste.add(1000);
                teste.add(15000);
                teste.add(19999);
                simulacaoMultithreading.runMultipleFaultInjectionMultithreadingMonteCarloSimulation(teste, Signals); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
                */

                simulacaoMultithreading.runMultipleFaultInjectionMultithreadingMonteCarloSimulationProportion(sample, mtf_sizes, Signals); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"

                this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" MTFT Sample (Monte Carlo = N)");
            }
            //System.out.println("STR: " + str);
        }

        public ArrayList <String> getCircuitsGates() throws Exception{

            System.out.println("Gates Area of " + this.circuitList.size() + " circuits");
            /*
            System.out.println("Path: " +this.relativePath);
            System.out.println("Genlib: " +this.genlib);
            System.out.println("Circuit: " +this.circuitList);
             System.out.println("ARGS:"  +  mtf_sizes) ;
            */

            ArrayList <String> x = new ArrayList<>();


            String str = "";

            //System.out.println();

            for (int i = 0; i < this.circuitList.size(); i++) {

                System.out.println("_" + i);

                Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst, this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));

                x.add(simulacaoMultithreading.PrintCircuitSpecsFast()); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"

                //this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" MTFT Sample (Monte Carlo = N)");
            }






            return x;
        }

    public void getCircuitsInputsAndGates() throws Exception{

        System.out.println("Gates Area of " + this.circuitList.size() + " circuits");
            /*
            System.out.println("Path: " +this.relativePath);
            System.out.println("Genlib: " +this.genlib);
            System.out.println("Circuit: " +this.circuitList);
             System.out.println("ARGS:"  +  mtf_sizes) ;
            */

        ArrayList <String> x = new ArrayList<>();


        String str = "";

        //System.out.println();

        for (int i = 0; i < this.circuitList.size(); i++) {

            System.out.println("_" + i);

            Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst, this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));

            x.add(simulacaoMultithreading.estimateMultithreadingExausticSimulationSize_AND_PRINT_INPUTS_SIGNALS_GATES("ALL_SIGNALS")); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"

            //this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" MTFT Sample (Monte Carlo = N)");
        }


        try (FileWriter file = new FileWriter(relativePath + "/" + "---INPUTS_GATES_SIGNALS.txt")) {
            String content = "circuit; Inputs; Signals; Gates";
            for (int z = 0; z < x.size(); z++) {
                content =  content + x.get(z) + "\n";
            }
            file.write(content);
        }



       // return x;
    }



    public void monteCarloSimulationCalculateSensitiveArea(int sample, ArrayList <Float> mtf_sizes, String Signals) throws Exception{

        System.out.println("Multiple Fault Injection : " + mtf_sizes);
        System.out.println("Path: " +this.relativePath);
        System.out.println("Genlib: " +this.genlib);
        System.out.println("Circuit: " +this.circuitList);

        System.out.println("ARGS:"  +  mtf_sizes) ;

        String str = "";
        for (int i = 0; i < this.circuitList.size(); i++) {



            Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst, this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
            //simulacaoMultithreading.PrintCircuitSpecs();
            //str = str + simulacaoMultithreading.PrintCircuitSpecs() + "\n";

            //simulacaoMultithreading.runMultipleFaultInjectionMultithreadingMonteCarlo(base, order, frequency,sampleSize, Signals); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"

            /*
            ArrayList <Integer> teste = new ArrayList<>();
            teste.add(20000);
            teste.add(1000);
            teste.add(15000);
            teste.add(19999);
            simulacaoMultithreading.runMultipleFaultInjectionMultithreadingMonteCarloSimulation(teste, Signals); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
            */

            simulacaoMultithreading.runMultipleFaultInjectionMultithreadingMonteCarloSimulationProportion(sample, mtf_sizes, Signals); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"


            Orchestrator simulacaoMultithreadingCalculateSensitiveArea = new Orchestrator(this.threads, this.reliabilityConst, this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));

            simulacaoMultithreadingCalculateSensitiveArea.runCalculationSensitiveAreas(Signals, "teste/lookup_table.csv");

            this.OUTPUT_INFO = simulacaoMultithreading.getFRM(" MTFT Sample (Monte Carlo = N)");
        }
        //System.out.println("STR: " + str);
    }


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
        
        public void monteCarloSimulation_Per_Area(int sampleSize, String Signals, int smallestGatesIncicuitsSimulation) throws Exception{
                //Loop na simulação de circuitos 
                String str = "";
                for (int i = 0; i < this.circuitList.size(); i++) {
                     Orchestrator simulacaoMultithreading = new Orchestrator(this.threads, this.reliabilityConst,
                             this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
                             //simulacaoMultithreading.PrintCircuitSpecs();
                             //str = str + simulacaoMultithreading.PrintCircuitSpecs() + "\n";
                             simulacaoMultithreading.runMultithreading_MonteCarloSample_per_Area_Analisys(sampleSize, Signals, smallestGatesIncicuitsSimulation); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
                }
                //System.out.println("STR: " + str);
        }
        
        public void preparingEnviroment(){
                
                String[] circuitFiles;
                File f = new File(this.relativePath);
               
                circuitFiles = f.list();
                
                for (String pathname : circuitFiles) {
                    if(pathname.endsWith(".v")){ // test tipe .v
                        //System.out.println(pathname);
                        //circuitList.add(pathname); 
                        this.circuitList.add(pathname);
                    }  
                }
                System.out.println("Circuits in List: " +  this.circuitList);
                System.out.println("======================\n");
               
        }
        
        public void preparingEnviromentSingleFile(String circuit){
                
                this.circuitList.add(circuit);
                System.out.println("Circuits in analysis: " +  this.circuitList);
                System.out.println("======================\n");
               
        }
       
        public void fooAlot(String folderPath) throws IOException{

             this.readResultsInLot(folderPath, "ALL_SIGNALS");
             
        }

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
                System.out.println("ARquivo criado: " + path + "/" + filter + "_.txt" );
                /*
                System.out.println(path + "/" + " resultado_.txt");
                File file = new File(path + "/" + " resultado_.txt");
                if(file.createNewFile()){
                System.out.println(" File Created");
                }else System.out.println("File  already exists");
                 */
            
           
       
        }

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
                    String tttt [] =   x.split(":");
                    Ne = tttt[1]; //fileContentList.get(9).split(":");
                    //String[] time = fileContentList.get(10).split(":");

                    //FileContent.add(files.get(i) + ";" + sampleSize[1] + ";" + Ne[1] + ";" + softErrorRate[1] + ";" + time[1]);
                    //FileContent.add(files.get(i) + ";" + sampleSize[1] + ";" + Ne[1] + ";" + time[1]);
                    //System.out.println("File: " + files.get(i)  +  " sample: " + sampleSize[1] + " > " + x + " t(s):" + time[1] );
                }
                if(x.contains("- Sample") || (x.contains("Number of Simulations"))){
                    String tttt [] =   x.split("=");
                    sample = tttt[1]; //fileContentList.get(9).split(":");
                }
                if(x.contains("Performance time")){
                    String tttt [] =   x.split(":");
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
             experimento_genlib.Area_monteCarloSimulation(sampleSizeMonteCarlo, "ALL_SIGNALS"); 
             
             //experimento_genlib.PrintCircuitsSpecs(1, "ALL_SIGNALS");
             //experimento_genlib.monteCarloSimulation_Per_Area(sampleSizeMonteCarlo, "ALL_SIGNALS", 180);  //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"

        }

        
}

