package LogicSimulator;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Vector;



/** Main method to run the logic simulator
 * @author Clayton Farias
 * @version 2.0
 * @since Dev
*/


public class LogicSimTool {
    
    public static void main(final String[] args) throws Exception {
     
            Vector circuitList = new Vector();
            Vector Threads = new Vector();
            String reliability = "0.9999"; //4 digitos
            String library = "cadence.genlib";

            circuitList.add("c17v3_fritz.v");

            Threads.add(8);


             /*
            circuitList.add("c432_cadence.v");
            circuitList.add("C499_cadence.v");
            circuitList.add("C880_cadence.v");
            circuitList.add("C1355_cadence.v");
            circuitList.add("C1908_cadence.v");
            circuitList.add("C3540_cadence.v");
            circuitList.add("C2670_cadence.v");
            circuitList.add("C5315_cadence.v");
            */


            // Vector circuit750k = new Vector();
            //circuit750k.add("C2670_cadence.v");

            /*
            circuit750k.add("c432_cadence.v");
            circuit750k.add("C499_cadence.v");
            circuit750k.add("C1908_cadence.v");
            circuit750k.add("C3540_cadence.v");
            */
        
            /* 
            circuitPath.add("c17v2_fritz.v"); //ok 
            circuitPath.add("c17v3_fritz.v"); //ok
            circuitPath.add("C17_cadence.v");   //Ok testado !
            circuitPath.add("FAv1.v");  //Ok testado !
            circuitPath.add("c17_mapped.v"); //Ok testado!!
            */
        
             LogicSimTool Analysis = new LogicSimTool();
             
             Analysis.callMethodsAnalisysMonteCarlo(circuitList, library, reliability, Threads, 32, 10);
             /*
             Analysis.callMethodsAnalisysMonteCarlo(circuitList, library, reliability, Threads, 400, 10);
             Analysis.callMethodsAnalisysMonteCarlo(circuitList, library, reliability, Threads, 800, 10); 
             Analysis.callMethodsAnalisysMonteCarlo(circuitList, library, reliability, Threads, 1600, 10); 
             Analysis.callMethodsAnalisysMonteCarlo(circuitList, library, reliability, Threads, 3200, 10);
             Analysis.callMethodsAnalisysMonteCarlo(circuitList, library, reliability, Threads, 6400, 10);
             Analysis.callMethodsAnalisysMonteCarlo(circuitList, library, reliability, Threads, 12800, 10);;
            */             
             
            /*
             Analysis.callMethodsAnalisys(circuitList, library, reliability, Threads, 10);
             Analysis.callMethodsAnalisys(circuitList, library, reliability, Threads, 250000);
             Analysis.callMethodsAnalisys(circuitList, library, reliability, Threads, 2000000);
            */
        
            /* Shutdown computer after simulation 
            double timeInSeconds = 1;
            Runtime runtime = Runtime.getRuntime();
            String a;
            Process proc = runtime.exec("shutdown -s -t 1");

            System.exit(0);
           */
    
      
    }
    
    public class Simulation{
            private final int iteraction;
            private final int propagatedFaults;
            private final int testNumber;
            private final long time;

      public Simulation(int iteraction, int propagated, int testNumber, long time) {
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
    
    public void callMethodsAnalisys(Vector circuitList, String library, String Reliability, Vector threads, int testNumber) throws Exception{
        
         Vector <Integer> TimeoutList = new Vector();
         ArrayList <Simulation> list_itemx = new ArrayList<>();
         
         for (int i = 0; i < circuitList.size(); i++) {
                for (int k = 0; k < threads.size(); k++) {
                       Operations_LogicSim logicSimulatorAnalisys = new Operations_LogicSim(Reliability, i , circuitList.get(i).toString(), library, (int) threads.get(k));
                       //logicSimulatorAnalisys.SequentialSimulator(library, Reliability); //Analise Sequencial
                       //logicSimulatorAnalisys.MulltiThreading_Simulator(library, Reliability);
                       //logicSimulatorAnalisys.MulltiThreading_Simulator_ramdomInputs(library, Reliability, testNumber); 
                       //logicSimulatorAnalisys.Print_Circuit_Specs(library, Reliability);
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
    public void callMethodsAnalisysMonteCarlo(Vector circuitList, String library, String Reliability, Vector threads, int testNumber, int interactions) throws Exception{
        
         Vector <Integer> TimeoutList = new Vector();
         
         for (int i = 0; i < circuitList.size(); i++) {
             ArrayList <Simulation> list_itemx = new ArrayList<>();
             
             for (int k = 0; k < threads.size(); k++) {         
                    for (int j = 0; j < interactions; j++) {
                         
                         Operations_LogicSim logicSimulatorAnalisys = new Operations_LogicSim(Reliability, i , circuitList.get(i).toString(), library, (int) threads.get(k));
                         logicSimulatorAnalisys.MulltiThreading_Simulator_ramdomInputs_MonteCarlo(library, Reliability, testNumber, interactions, j); 
                         Simulation x = new Simulation(j, logicSimulatorAnalisys.getPropagatedFaults(), testNumber, logicSimulatorAnalisys.getTimeExecutionRound());
                         list_itemx.add(x);
                    }
                              
                    //System.out.println("Lista: " + list_itemx);           ;
                    //logicSimulatorAnalisys.circuits_specifications(library, Reliability);
                    //logicSimulatorAnalisys.PTM();
                    //logicSimulatorAnalisys.getSPRBigDecimal(threads);
             }
            String str = "Round;Propagated Faults(n);Propagate Faults(%);Time(s);Reliability;Sample size" + "\n";
            //testNumber = 31 // 1 até X : 32 ( 1 - 32)
            for (int dd = 0; dd < list_itemx.size(); dd++) {
                  String confiabilidade = Float.toString(1-((float)list_itemx.get(dd).getPropagatedFaults()/testNumber));
                  String propagacao_falhas = Float.toString(((float)list_itemx.get(dd).getPropagatedFaults()*100)/testNumber);
                  confiabilidade = confiabilidade.replace(".",",");
                  propagacao_falhas = propagacao_falhas.replace(".",",");
               
                  if(dd == 0){
                       str = str +  list_itemx.get(dd).getInteractionIndex()  + ";" + propagacao_falhas + ";" + list_itemx.get(dd).getPropagatedFaults() + ";" + list_itemx.get(dd).getTime() + ";" + confiabilidade  +";" + (list_itemx.get(dd).getVectorsNumbers()) + ";\n";
             
                  }else{
                      str = str +  list_itemx.get(dd).getInteractionIndex() + ";" + propagacao_falhas + ";" +list_itemx.get(dd).getPropagatedFaults() + ";" + list_itemx.get(dd).getTime() + ";" + confiabilidade + ";"  +";\n";
                  }
            }

            try (FileWriter file = new FileWriter("Monte_Carlo_"+circuitList.get(i).toString()+ "_TestNumber-" + testNumber +"_interactions-"+ interactions + ".csv")) {
                   file.write(str);
           }
        }
   
    }

}
