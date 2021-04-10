/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicSimulator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
        
        public void monteCarloSimulation_Per_Area(int sampleSize, String Signals, int smallestGatesIncicuitsSimulation) throws Exception{
                //Loop na simulação de circuitos 
                String str = "";
                for (int i = 0; i < this.circuitList.size(); i++) {
                     Operations simulacaoMultithreading = new Operations(this.threads, this.reliabilityConst, 
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
       
        public void fooAlot(String folderPath) throws IOException{

             this.readResultsInLot(folderPath, "ALL_SIGNALS");
             
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
             
             String op = "min";
             
             experimento.fooAlot("Resultados - Simulação - Diferentes Áreas/" + op);
             
             //experimento.fooExecution();

             //experimento.fooExecution();
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
 
             //experimento.multithreadingSimulation("ALL_SIGNALS"); //TRue Table - //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
             
             //experimento.monteCarloSimulation(sampleSizeMonteCarlo, "ALL_SIGNALS"); //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"
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
                   List<String> records  = this.readFile(path + "/" +files.get(i));
                   //System.out.println("Records: " + records);
                        
                        for (String x : records){
                           
                            if(x.contains("Number of detected faults (Ne):")){
                               
                                String[] t = x.split(":");
                                //out = files.get(i) + ";" + t[1];
                                //System.out.println("----- +" + t[1]);
                                String[] z = records.get(8).split(":");
                                FileContent.add(files.get(i) + ";" + t[1] + ";" + z[1]);
                                
                                 System.out.println("File: " + files.get(i) + " >"+x + " t(s):" + z[1] );
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
        
        public void readResultsInLot(String path, String filter) throws IOException{
                
                String[] circuitFiles;
                File f = new File(path);
                ArrayList <String> files = new ArrayList<>();
                ArrayList <String> temp = new ArrayList<>();
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
                            temp.add(files.get(i));
                        }
                }
                
                //List<String> records  = this.readFile(path + "/" +files.get(0));
                //System.out.println("Records: " + records);
                /*
                for (String x : records){
                    System.out.println(">"+x);
                }
                */
                
                this.readEachFile(temp, path, filter);
                System.out.println("------------------------------------------");
                    
        }
        
        public void fooExecution() throws Exception{
            
             //this.foo("Simulação Circuitos - ABC/min/", "lib_min_no_cost.genlib"); // 1° genlib
             
             //this.foo("Simulação Circuitos - ABC/basic/", "lib_basic_no_cost.genlib"); // 2° genlib
             
             this.foo("Simulação Circuitos - ABC/complex/", "lib_complex_no_cost_no_xor.genlib"); // 3° genlib

            // this.foo("Simulação Circuitos - ABC/full/", "lib_full_no_cost_no_xor.genlib"); // 4° genlib
             
             
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
             
             experimento_genlib.monteCarloSimulation_Per_Area(sampleSizeMonteCarlo, "ALL_SIGNALS", 180);  //ou Signals =  "ALL_SIGNALS" ou "INTERMEDIATE" ou "INTERMEDIATE_AND_OUTPUTS" ou "INPUTS" ou "INPUTS_OUTPUTS"

        }

        
}

