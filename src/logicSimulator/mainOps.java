/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicSimulator;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Clayton Farias
 */
public class mainOps{
        
        private ArrayList <String> circuitList = new ArrayList<>(); //CircuitList for simulation
        int threads; 
        String reliabilityConst;
        String relativePath;
        String genlib;
        

        public mainOps(int threads, String reliabilityConst, String relativePath, String genlib) {
            this.threads = threads;
            this.reliabilityConst = reliabilityConst;
            this.relativePath = relativePath;
            this.genlib = genlib;
        }
        
        public ArrayList<String> getCircuitList(){
            return this.circuitList;
        }
        
        public void multithreadingSimulation() throws Exception{
                //Loop na simulação de circuitos 
                for (int i = 0; i < this.circuitList.size(); i++) {
                     Operations simulacaoMultithreading = new Operations(this.threads, this.reliabilityConst, 
                             this.relativePath, this.genlib, this.relativePath + this.circuitList.get(i));
                            simulacaoMultithreading.runMultithreadingSimulation();
                }
        }
        
        public void monteCarloSimulation(){
            
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
             String constReliability = "0.9999"; //Used for internal structures
             String relativePath = "teste/";
             String genlib =  relativePath  + "lib_full_no_cost.genlib";
             mainOps experimento = new mainOps(threads, constReliability, relativePath, genlib);
             experimento.preparingEnviroment();
             experimento.multithreadingSimulation();
        }
}

