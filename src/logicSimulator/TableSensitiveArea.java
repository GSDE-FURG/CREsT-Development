package logicSimulator;

import java.util.ArrayList;
import java.util.List;

public class TableSensitiveArea {

    private ArrayList<LogicSimulator> thread_items_list;

    public TableSensitiveArea(ArrayList<LogicSimulator> thread_items_list){
         this.thread_items_list = thread_items_list;
    }

    public void createTable(){
        // Create head
        String header = "";
        String content = "";

        for (int i = 0; i < this.thread_items_list.size() ; i++) {
            //0 to 8 threads
            List<TestVectorInformation> x =  this.thread_items_list.get(i).get_threadSimulationList();
            
            //Vector axis Y
            for (int j = 0; j < x.size(); j++) {
                //Vectors axis X 000; 0000; 0000; 0000; 0000;
                System.out.print(x.get(j).getinputVector()); // Head
                for (int k = 0; k < x.get(j).getGatesLogicalPath().size(); k++) {
                    //Values for each gate

                    System.out.print(" " + x.get(j).getGatesLogicalPath().get(k).getGate()
                       // + " In: " + x.get(j).getGatesLogicalPath().get(k).getInputs()
                       // + " Out: " + x.get(j).getGatesLogicalPath().get(k).getOutputs()
                        + " SA: " + x.get(j).getGatesLogicalPath().get(k).getgateSensitiveArea());

                }
                System.out.println("");
            }
        }

    }


}
