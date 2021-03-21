package LogicSimulator;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Vector;



/** Main method to run this class
 * @author Clayton Farias
 * @version 2.0
 * @since Dev
*/


public class main {
    
    
   public static void main(final String[] args) throws Exception {
     
       
        Vector Threads = new Vector();
        Threads.add(4); //Number of threads
            String reliability = "0.9999"; // Used only to start necessary matrix dependences
        
     
        
        String relativePath = "abc/xor_experiment/"; //Folder with circuits and genlib
         //String library =  relativePath + "cadence.genlib"; // Standart genlib
        String library =  relativePath  + "lib_full_no_cost.genlib";  // Different genlib
        
        int sampleSize = 16577; // sample size (N) to 99% (IC - Interval of confidence) and E = 1% (Sample Error) 
       
        main Analysis = new main();

       // Analysis.singleCircuitMonteCarlo(library, relativePath, "c432_cadence.v", sampleSize, reliability, Threads); // Single circuit Monte Carlo evaluation

         Analysis.circuitsInFolderMonteCarlo(library, relativePath, sampleSize, reliability, Threads);// Vector List with the circuits to Monte Carlo evaluation
          
        
         
       
       
       /*   -- After simulation this command shutdown the computer !!! ---
            double timeInSeconds = 1;
            Runtime runtime = Runtime.getRuntime();
            String a;
            Process proc = runtime.exec("shutdown -s -t 1");
            System.exit(0);
       */
    
      
    }
    
   public class item{
            private int iteraction;
            private int propagatedFaults;
            private int testNumber;
            private long time;

        public item(int iteraction, int propagated, int testNumber, long time) {
            this.iteraction = iteraction;
            this.propagatedFaults =  propagated;
            this.testNumber = testNumber;
            this.time = time;
        }
        public int getInteractionIndex(){
            return this.iteraction;
        }
        public int getPropagatedFaults(){
            return this.propagatedFaults;
        }
        public int getVectorsNumbers(){
            return this.testNumber;
        }
        public long getTime(){
            return this.time;
        }
        
            
    }
    
     public void singleCircuitMonteCarlo(String library, String folderPath, String circuit, int sampleSize, String reliability, Vector Threads) throws Exception{
            
            Vector singleCircuit = new Vector();
            singleCircuit.add(circuit);
            int interactions = 1;
            folderPath = "./" + folderPath;
            System.out.println(""+folderPath);
            
            this.callMethodsAnalisysMonteCarlo(singleCircuit, folderPath ,library, reliability, Threads,  sampleSize, interactions); // sampleSize (N) 99% de confiança e = 1% -- Consider ALL Signals (input, intermediate, output)
            
            // this.callMethodsAnalisysMonteCarlo_Only_IntermediateSignals(singleCircuit, folderPath ,library, reliability, Threads,  sampleSize, interactions); // sampleSize (N) 99% de confiança e = 1% -- Consider only INTERMEDIATE Signals
            
            //this.callMethodsAnalisysMonteCarlo_Only_Intermediate_AND_OutputSignals(singleCircuit, folderPath ,library, reliability, Threads,  sampleSize, interactions); // sampleSize (N) 99% de confiança e = 1% -- Consider INTERMEDIATE and OUTPUT Signals
         
     }
     
    public void circuitsInFolderMonteCarlo(String library, String relativePath, int sampleSize, String reliability, Vector Threads) throws Exception{
         
        String[] circuitFiles;
        String relative_circuit_PATH =  relativePath;
        File f = new File(relative_circuit_PATH);
        int interactions = 1;
        
        Vector circuitList = new Vector();

        // Populates the array with names of files and directories
         System.out.println("CircuitList: ");
        circuitFiles = f.list();
        // For each pathname in the circuitFiles array
        for (String pathname : circuitFiles) {
            // Print the names of files and directories
            if(pathname.endsWith(".v")){
                System.out.println(pathname);
                circuitList.add(pathname); 
            }
             //circuitListName.add(pathname);
        }
         System.out.println("======================\n");
        //System.out.println(""+circuitList);
        
            this.callMethodsAnalisysMonteCarlo(circuitList,relativePath, library, reliability, Threads,  sampleSize, interactions); // sampleSize (N) 99% de confiança e = 1% -- Consider all Signals
       
            //this.callMethodsAnalisysMonteCarlo_Only_IntermediateSignals(circuitList, relativePath, library, reliability, Threads,  sampleSize, interactions); // sampleSize (N) 99% de confiança e = 1% -- Consider only INTERMEDIATE Signals
       
            //this.callMethodsAnalisysMonteCarlo_Only_Intermediate_AND_OutputSignals(circuitList, relativePath, library, reliability, Threads,  sampleSize, interactions); // sampleSize (N) 99% de confiança e = 1% -- Consider only INTERMEDIATE Signals
       
        
         
     }
    
    
    public void callMethodsAnalisys(Vector circuitList, String library, String Reliability, Vector threads, int testNumber) throws Exception{
        
         Vector <Integer> TimeoutList = new Vector();
         ArrayList <item> list_itemx = new ArrayList<>();
         
         for (int i = 0; i < circuitList.size(); i++) {
             
             for (int k = 0; k < threads.size(); k++) {
                   Fault_Injection_Campaign logicSimulatorAnalisys = new Fault_Injection_Campaign(Reliability, i , circuitList.get(i).toString(), library, (int) threads.get(k));
                    //logicSimulatorAnalisys.Sequential_Logic_Simulator(library, Reliability); //Analise Sequencial
                    //logicSimulatorAnalisys.MulltiThreading_Logic_Simulator(library, Reliability);;
                   
                    //logicSimulatorAnalisys.MulltiThreading_Logic_Simulator_ramdomInputs(library, Reliability, testNumber); 
                    
                    //logicSimulatorAnalisys.print_Circuit_Specs(library, Reliability);
                        
               
        }
         
        

        
         
    }
      }

    /**
     *
     * @param circuitList
     * @param library
     * @param Reliability
     * @param threads
     * @param testNumber
     * @param interactions
     * @throws Exception
     */
    public void callMethodsAnalisysMonteCarlo(Vector circuitList, String folderPath, String library, String Reliability, Vector threads, int testNumber, int interactions) throws Exception{
        
         Vector <Integer> TimeoutList = new Vector();
        
         
         for (int i = 0; i < circuitList.size(); i++) {
             
             ArrayList <item> list_itemx = new ArrayList<>();
             System.out.println("=======  Simulation: " +  i + " -  Circuit: " + circuitList.get(i) + "    =======");
             
             for (int k = 0; k < threads.size(); k++) {
                  
                    //logicSimulatorAnalisys.Sequential_Logic_Simulator(library, Reliability); //Analise Sequencial
                    //logicSimulatorAnalisys.MulltiThreading_Logic_Simulator(library, Reliability);;
                    //logicSimulatorAnalisys.MulltiThreading_Logic_Simulator_ramdomInputs(library, Reliability, testNumber); 
                        
                    for (int j = 0; j < interactions; j++) {
                         
                         Fault_Injection_Campaign logicSimulatorAnalisys = new Fault_Injection_Campaign(Reliability, i , folderPath + circuitList.get(i).toString(), library, (int) threads.get(k));
                         logicSimulatorAnalisys.MulltiThreading__Logic_Simulator_ramdomInputs_MonteCarlo(library, Reliability, testNumber, interactions, j); 
                         //logicSimulatorAnalisys.TESTEMODE(library, Reliability, testNumber, interactions, j);
                         item x = new item(j, logicSimulatorAnalisys.getPropagatedFaults(), testNumber, logicSimulatorAnalisys.getTimeExecutionRound());
                         list_itemx.add(x);
                    }
                              
                   //System.out.println("Lista: " + list_itemx);           ;
                   
                   
                    
                   
                   //logicSimulatorAnalisys.circuits_specifications(library, Reliability);
                    
                    //logicSimulatorAnalisys.PTM();
                    //logicSimulatorAnalisys.getSPRBigDecimal(threads);
             }
            //System.out.println("i ; testNumber;  unmaskedFaults; time");
            String str = "Rodada;FalhasPropagadas;Tempo(s);Confiabilidade;NrVec" + "\n";
            for (int dd = 0; dd < list_itemx.size(); dd++) {
                  String confiabilidade = Float.toString(1-((float)list_itemx.get(dd).getPropagatedFaults()/testNumber));
                  confiabilidade = confiabilidade.replace(".",",");
                 //System.out.println(dd + " - TestNumber:" + list_itemx.get(dd).getVectorsNumbers() + " ; unmaskedfaults: " + list_itemx.get(dd).getPropagatedFaults() + " ; time: "+list_itemx.get(dd).getTime() + "(s)");
                  if(dd == 0){
                       str = str +  list_itemx.get(dd).getInteractionIndex()  + ";" + list_itemx.get(dd).getPropagatedFaults() + ";" + list_itemx.get(dd).getTime() + ";" + confiabilidade  +";" + list_itemx.get(dd).getVectorsNumbers() + ";\n";
             
                  }else{
                      str = str +  list_itemx.get(dd).getInteractionIndex()  + ";" + list_itemx.get(dd).getPropagatedFaults() + ";" + list_itemx.get(dd).getTime() + ";" + confiabilidade + ";"  +";\n";
                  }
            
            }

            try (FileWriter file = new FileWriter("Monte_Carlo_"+circuitList.get(i).toString()+ "_TestNumber-" + testNumber +"_interactions-"+ interactions + ".csv")) {
                   file.write(str);
           }
        }
         
        

        
         
    }

    /**
     *
     * @param circuitList
     * @param library
     * @param Reliability
     * @param threads
     * @param testNumber
     * @param interactions
     * @throws Exception
     */
    public void callMethodsAnalisysMonteCarlo_Only_IntermediateSignals(Vector circuitList, String folderPath, String library, String Reliability, Vector threads, int testNumber, int interactions) throws Exception{
        
         Vector <Integer> TimeoutList = new Vector();
        
         
         for (int i = 0; i < circuitList.size(); i++) {
             
             ArrayList <item> list_itemx = new ArrayList<>();
             System.out.println("=======  Simulation: " +  i + " -  Circuit: " + circuitList.get(i) + "    =======");
             
             for (int k = 0; k < threads.size(); k++) {
                  
                    //logicSimulatorAnalisys.Sequential_Logic_Simulator(library, Reliability); //Analise Sequencial
                    //logicSimulatorAnalisys.MulltiThreading_Logic_Simulator(library, Reliability);;
                    //logicSimulatorAnalisys.MulltiThreading_Logic_Simulator_ramdomInputs(library, Reliability, testNumber); 
                        
                    for (int j = 0; j < interactions; j++) {
                         
                         Fault_Injection_Campaign logicSimulatorAnalisys = new Fault_Injection_Campaign(Reliability, i , folderPath + circuitList.get(i).toString(), library, (int) threads.get(k));
                         //logicSimulatorAnalisys.MulltiThreading__Logic_Simulator_ramdomInputs_MonteCarlo_Only_IntermediateSignals(library, Reliability, testNumber, interactions, j); 
                         logicSimulatorAnalisys.MulltiThreading__Logic_Simulator_ramdomInputs_MonteCarlo_Only_InputsSignals(library, Reliability, testNumber, interactions, j); 
                         
                         
                         //logicSimulatorAnalisys.TESTEMODE(library, Reliability, testNumber, interactions, j);
                         item x = new item(j, logicSimulatorAnalisys.getPropagatedFaults(), testNumber, logicSimulatorAnalisys.getTimeExecutionRound());
                         list_itemx.add(x);
                    }
                              
                   //System.out.println("Lista: " + list_itemx);           ;
                   
                   
                    
                   
                   //logicSimulatorAnalisys.circuits_specifications(library, Reliability);
                    
                    //logicSimulatorAnalisys.PTM();
                    //logicSimulatorAnalisys.getSPRBigDecimal(threads);
             }
            //System.out.println("i ; testNumber;  unmaskedFaults; time");
            String str = "Rodada;FalhasPropagadas;Tempo(s);Confiabilidade;NrVec" + "\n";
            for (int dd = 0; dd < list_itemx.size(); dd++) {
                  String confiabilidade = Float.toString(1-((float)list_itemx.get(dd).getPropagatedFaults()/testNumber));
                  confiabilidade = confiabilidade.replace(".",",");
                 //System.out.println(dd + " - TestNumber:" + list_itemx.get(dd).getVectorsNumbers() + " ; unmaskedfaults: " + list_itemx.get(dd).getPropagatedFaults() + " ; time: "+list_itemx.get(dd).getTime() + "(s)");
                  if(dd == 0){
                       str = str +  list_itemx.get(dd).getInteractionIndex()  + ";" + list_itemx.get(dd).getPropagatedFaults() + ";" + list_itemx.get(dd).getTime() + ";" + confiabilidade  +";" + list_itemx.get(dd).getVectorsNumbers() + ";\n";
             
                  }else{
                      str = str +  list_itemx.get(dd).getInteractionIndex()  + ";" + list_itemx.get(dd).getPropagatedFaults() + ";" + list_itemx.get(dd).getTime() + ";" + confiabilidade + ";"  +";\n";
                  }
            
            }

            try (FileWriter file = new FileWriter("Monte_Carlo_"+circuitList.get(i).toString()+ "_TestNumber-" + testNumber +"_interactions-"+ interactions + ".csv")) {
                   file.write(str);
           }
        }
         
        

        
         
    }
    
    public void callMethodsAnalisysMonteCarlo_Only_Intermediate_AND_OutputSignals(Vector circuitList, String folderPath, String library, String Reliability, Vector threads, int testNumber, int interactions) throws Exception{
        
         Vector <Integer> TimeoutList = new Vector();
                  
        
         for (int i = 0; i < circuitList.size(); i++) {
             
             
             ArrayList <item> list_itemx = new ArrayList<>();
             System.out.println("=======  Simulation: " +  i + " -  Circuit: " + circuitList.get(i) + "    =======");
             
             for (int k = 0; k < threads.size(); k++) {
                  
                    //logicSimulatorAnalisys.Sequential_Logic_Simulator(library, Reliability); //Analise Sequencial
                    //logicSimulatorAnalisys.MulltiThreading_Logic_Simulator(library, Reliability);;
                    //logicSimulatorAnalisys.MulltiThreading_Logic_Simulator_ramdomInputs(library, Reliability, testNumber); 
                        
                    for (int j = 0; j < interactions; j++) {
                         
                         Fault_Injection_Campaign logicSimulatorAnalisys = new Fault_Injection_Campaign(Reliability, i , folderPath + circuitList.get(i).toString(), library, (int) threads.get(k));
                         
                         //Monte Carlo 16577 sample size
                         logicSimulatorAnalisys.MulltiThreading__Logic_Simulator_ramdomInputs_MonteCarlo_Only_Intermediate_AND_outputSignals(library, Reliability, testNumber, interactions, j); 
                         
                        
                         //True Table - all input vectors 2^n - Option NÂO PRECISA DO testNumber 
                         //logicSimulatorAnalisys.MulltiThreading__Logic_Simulator_ramdomInputs_MonteCarlo_Only_Intermediate_AND_outputSignals_TRUETABLE(library, Reliability, testNumber, interactions, j); 
                      
                         
                         item x = new item(j, logicSimulatorAnalisys.getPropagatedFaults(), testNumber, logicSimulatorAnalisys.getTimeExecutionRound());
                         list_itemx.add(x);
                    }
                  
             }
            //System.out.println("i ; testNumber;  unmaskedFaults; time");
            String str = "Rodada;FalhasPropagadas;Tempo(s);Confiabilidade;NrVec" + "\n";
            for (int dd = 0; dd < list_itemx.size(); dd++) {
                  String confiabilidade = Float.toString(1-((float)list_itemx.get(dd).getPropagatedFaults()/testNumber));
                  confiabilidade = confiabilidade.replace(".",",");
                 //System.out.println(dd + " - TestNumber:" + list_itemx.get(dd).getVectorsNumbers() + " ; unmaskedfaults: " + list_itemx.get(dd).getPropagatedFaults() + " ; time: "+list_itemx.get(dd).getTime() + "(s)");
                  if(dd == 0){
                       str = str +  list_itemx.get(dd).getInteractionIndex()  + ";" + list_itemx.get(dd).getPropagatedFaults() + ";" + list_itemx.get(dd).getTime() + ";" + confiabilidade  +";" + list_itemx.get(dd).getVectorsNumbers() + ";\n";
             
                  }else{
                      str = str +  list_itemx.get(dd).getInteractionIndex()  + ";" + list_itemx.get(dd).getPropagatedFaults() + ";" + list_itemx.get(dd).getTime() + ";" + confiabilidade + ";"  +";\n";
                  }
            
            }

            try (FileWriter file = new FileWriter("Monte_Carlo_"+circuitList.get(i).toString()+ "_TestNumber-" + testNumber +"_interactions-"+ interactions + ".csv")) {
                   file.write(str);
           }
        }
         
        

        
         
    }

}
