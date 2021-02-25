package LogicSimulator;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Vector;



/** Main method to run this class
 * @author Clayton Farias
 * @version 1.0
 * @since Dev
*/


public class LogicSimTool {
    
    
   

   public static void main(final String[] args) throws Exception {
     
        Vector circuitList = new Vector();
        Vector Threads = new Vector();
        String reliability = "0.9999"; //4 digitos
        String library = "cadence.genlib";
        
        //Threads.add(64);
        //Threads.add(1);
        Threads.add(8);
        
        
       // Vector circuit750k = new Vector();
       circuitList.add("c17v3_fritz.v");
        
       // circuitList.add("c6288_cadence.v");
        //circuitList.add("c7552_cadence.v");
       
        // circuitList.add("C499_cadence.v");
        /*
        circuitList.add("C880_cadence.v");
        
        circuitList.add("C1355_cadence.v");
        circuitList.add("C1908_cadence.v");
        circuitList.add("C3540_cadence.v");
        circuitList.add("C2670_cadence.v");
        */
        ////circuitList.add("C5315_cadence.v");
        
         //circuit750k.add("C2670_cadence.v");
        //circuit750k.add("C5315_cadence.v");
        
        /*
        circuit750k.add("c432_cadence.v");
        circuit750k.add("C499_cadence.v");
        circuit750k.add("C1908_cadence.v");
        circuit750k.add("C3540_cadence.v");
        
        */
        
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
        /*
        Vector circuitListXXXXXXXX = new Vector();
        circuitListXXXXXXXX.add("C2670_cadence.v");
        /*
        //circuitList.add("C2670_cadence.v");
        /*
         circuitList.add("c17v3_fritz.v"); //TI
         circuitList.add("c17_Cadence.v"); //TI
         circuitList.add("FAV1.v"); //TI
         circuitList.add("multiplex-4bits.v"); //TI
        */
        
        
        //circuitList.add("c17v3_fritz.v");
       
       // circuitList.add("c432_cadence.v");
       /*
        circuitList.add("C499_cadence.v");
        circuitList.add("C880_cadence.v");
        circuitList.add("C1355_cadence.v");
        circuitList.add("C1908_cadence.v");
        
         circuitList.add("C2670_cadence.v"); Prblematico
       
       */

        //circuitList.add("C3540_cadence.v");
       // circuitList.add("C5315_cadence.v");
        //circuitList.add("C6288_cadence.v");
        
     
        
        /*
        Threads.add(2);
        Threads.add(4);
        Threads.add(8);
        Threads.add(16);
        Threads.add(32);
        Threads.add(64);
        
        
        
        
        circuitList.add("c17v3_fritz.v");
        circuitList.add("C17_cadence.v");
        circuitList.add("FAv1.v");
        circuitList.add("c20.v");
        */

        //circuitList.add("C17_cadence.v");
        //circuitList.add("FAv1.v"); 
         //circuitPath.add("c17Classic.v");
       // circuitList.add("c17v3_fritz.v");
        //Lista de teste
        /* //Funciona Sem erro e Bate Resultados Planilha 
            circuitPath.add("c17v2_fritz.v"); //ok 
            circuitPath.add("c17v3_fritz.v"); //ok
            circuitPath.add("C17_cadence.v");   //Ok testado !
            circuitPath.add("FAv1.v");  //Ok testado !
            circuitPath.add("c17_mapped.v"); //Ok testado!!
        */
       
         //circuitPath.add("cla_unit.v");
       // circuitPath.add("c17v3_fritz.v"); 
      // circuitPath.add("FAv1.v"); 
         //circuitPath.add("c17v3_fritz.v"); //ok
      //circuitPath.add("FAv1.v");  //Ok testado !
       //circuitPath.add("C17_cadence.v"); 
       //  circuitPath.add("c17v3_fritz.v");
         //circuitPath.add("s38584_n2564.v");
         // circuitPath.add("C17_cadence.v");   
       //  circuitPath.add("c20.v");
       /* 
       circuitPath.add("c17Classic.v");
       
       circuitPath.add("C17_cadence.v");   
      
       circuitPath.add("c17v3_fritz.v");
      
       //circuitPath.add("c17v2_fritz.v");         //Circuito Diferente do C17 Saidas são incorretas
       
       circuitPath.add("FAV1.v");
       
       circuitPath.add("c20.v");
       
       circuitPath.add("c432_cadence.v");
       
      */
       //try {
            //LogicSimTool Analysis2 = new LogicSimTool();
            
            //Analysis2.callMethodsAnalisys(circuit750k, library, reliability, Threads, 1000000);
           
             LogicSimTool Analysis = new LogicSimTool();
             
             
             
            // Analysis.callMethodsAnalisys(circuitList, library, reliability, Threads, 0);
             
             
            
             Analysis.callMethodsAnalisysMonteCarlo(circuitList, library, reliability, Threads, 32, 1);
             /*
             Analysis.callMethodsAnalisysMonteCarlo(circuitList, library, reliability, Threads, 400, 10);
             Analysis.callMethodsAnalisysMonteCarlo(circuitList, library, reliability, Threads, 800, 10); 
             Analysis.callMethodsAnalisysMonteCarlo(circuitList, library, reliability, Threads, 1600, 10); 
             Analysis.callMethodsAnalisysMonteCarlo(circuitList, library, reliability, Threads, 3200, 10);
             Analysis.callMethodsAnalisysMonteCarlo(circuitList, library, reliability, Threads, 6400, 10);
             Analysis.callMethodsAnalisysMonteCarlo(circuitList, library, reliability, Threads, 12800, 10);;
            */             
             
             //Analysis.callMethodsAnalisys(circuitList, library, reliability, Threads, 10);
           // Analysis.callMethodsAnalisys(circuitList, library, reliability, Threads, 250000);
           // Analysis.callMethodsAnalisys(circuitList, library, reliability, Threads, 2000000);
            
            /*
           
            */
      // } catch (Exception e) {
       //    System.out.println("Error!!!!");
      // }
      
       
       
       
       
       /*
      
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
    
      public void callMethodsAnalisys(Vector circuitList, String library, String Reliability, Vector threads, int testNumber) throws Exception{
        
         Vector <Integer> TimeoutList = new Vector();
         ArrayList <item> list_itemx = new ArrayList<>();
         
         for (int i = 0; i < circuitList.size(); i++) {
             
             for (int k = 0; k < threads.size(); k++) {
                   Operations_LogicSim logicSimulatorAnalisys = new Operations_LogicSim(Reliability, i , circuitList.get(i).toString(), library, (int) threads.get(k));
                    //logicSimulatorAnalisys.SequentialSimulator(library, Reliability); //Analise Sequencial
                    //logicSimulatorAnalisys.MulltiThreading_Simulator(library, Reliability);;
                   
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
             ArrayList <item> list_itemx = new ArrayList<>();
             
             for (int k = 0; k < threads.size(); k++) {
                  
                    //logicSimulatorAnalisys.SequentialSimulator(library, Reliability); //Analise Sequencial
                    //logicSimulatorAnalisys.MulltiThreading_Simulator(library, Reliability);;
                    //logicSimulatorAnalisys.MulltiThreading_Simulator_ramdomInputs(library, Reliability, testNumber); 
                        
                    for (int j = 0; j < interactions; j++) {
                         
                         Operations_LogicSim logicSimulatorAnalisys = new Operations_LogicSim(Reliability, i , circuitList.get(i).toString(), library, (int) threads.get(k));
                         logicSimulatorAnalisys.MulltiThreading_Simulator_ramdomInputs_MonteCarlo(library, Reliability, testNumber, interactions, j); 
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

}
