/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicSimulator;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Clayton Farias
 */
public class main{
        
        private final ArrayList <String> circuitList = new ArrayList<>(); //CircuitList for simulation
        int threads; 
        String reliabilityConst;
        String relativePath;
        String genlib;
        

        public main(int threads, String reliabilityConst, String relativePath, String genlib) {
            this.threads = threads;
            this.reliabilityConst = reliabilityConst;
            this.relativePath = relativePath;
            this.genlib = genlib;
        }
        
        public ArrayList<String> getCircuitList(){
            return this.circuitList;
        }

        public void multithreadingSimulation(String Signals) throws Exception{ //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
                //Loop na simulação de circuitos 
                for (int i = 0; i < this.circuitList.size(); i++) {
                     Operations simulacaoMultithreading = new Operations(this.threads, this.reliabilityConst, 
                             this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
                            // simulacaoMultithreading.runMultithreadingSimulation(Signals); 
                }
        }
        
        public void multithreadingSimulationExaustic() throws Exception{ //ou Signals =  "ALL_SIGNALS" for exaustive consider all_signals
                //Loop na simulação de circuitos 
                for (int i = 0; i < this.circuitList.size(); i++) {
                     Operations simulacaoMultithreading = new Operations(this.threads, this.reliabilityConst, 
                             this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
                             simulacaoMultithreading.runMultithreadingExausticSimulation("ALL_SIGNALS"); 
                }
        }
        
        public void monteCarloSimulation(int sampleSize, String Signals) throws Exception{
                //Loop na simulação de circuitos 
                String str = "";
                for (int i = 0; i < this.circuitList.size(); i++) {
                     Operations simulacaoMultithreading = new Operations(this.threads, this.reliabilityConst, 
                             this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
                             //simulacaoMultithreading.PrintCircuitSpecs();
                             //str = str + simulacaoMultithreading.PrintCircuitSpecs() + "\n";
                             simulacaoMultithreading.runMultithreadingMonteCarlo(sampleSize, Signals); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
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
        
        public static void main(String[] args) throws Exception {
            
             int threads = 2; //Numero de threads
             int sampleSizeMonteCarlo = 8;
             String constReliability = "0.9999"; //Used for internal structures
             String relativePath = "teste/";
             
             String genlib =  relativePath  + "lib_basic_no_cost.genlib";
            
             //String genlib =  relativePath  + "cadence.genlib";
             
             main experimento = new main(threads, constReliability, relativePath, genlib);
             
             experimento.preparingEnviroment();
             
             experimento.fooExecution();
 
             //experimento.multithreadingSimulation("ALL_SIGNALS"); //TRue Table - //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
             
             //experimento.monteCarloSimulation(sampleSizeMonteCarlo, "ALL_SIGNALS"); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
        }
            
        public void fooExecution() throws Exception{
            
             this.foo("Simulação Circuitos - ABC/min/", "lib_min_no_cost.genlib"); // 1° genlib
             
             //this.foo("Simulação Circuitos - ABC/basic/", "lib_basic_no_cost.genlib"); // 2° genlib
             
             //this.foo("Simulação Circuitos - ABC/complex/", "lib_complex_no_cost_no_xor.genlib"); // 3° genlib
             
             //this.foo("Simulação Circuitos - ABC/full/", "lib_complex_no_cost_no_xor.genlib"); // 4° genlib
             
            // this.foo("Simulação Circuitos - ABC/full + xor/", "lib_full_no_cost.genlib"); // 5° genlib
             
               //this.foo("teste/", "cadence.genlib");
               //this.foo("Simulação Circuitos - ABC/basic/", "lib_basic_no_cost.genlib");
               //this.foo(relativePath, genlib);
           } 
        
        public void foo(String relativePath , String genlibTemp) throws Exception{
            
             int threads = 8; //Numero de threads
             int sampleSizeMonteCarlo = 10;
             String constReliability = "0.9999"; //Used for internal structures
             //String relativePath = "teste/";
             String genlib =  relativePath  + genlibTemp;
            
             main experimento_genlib = new main(threads, constReliability, relativePath, genlib);
             experimento_genlib.preparingEnviroment(); 
             //experimento_genlib.multithreadingSimulation("ALL_SIGNALS");
             //experimento_genlib.multithreadingSimulationExaustic();
             //experimento_genlib
             experimento_genlib.monteCarloSimulation(sampleSizeMonteCarlo, "INTERMEDIATE");  //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
              experimento_genlib.monteCarloSimulation(sampleSizeMonteCarlo, "INTERMEDIATE_AND_OUTPUTS\""); 
                experimento_genlib.monteCarloSimulation(sampleSizeMonteCarlo, "ALL_SIGNALS");  //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"

        }

        
}

