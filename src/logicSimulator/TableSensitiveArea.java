package logicSimulator;

import java.util.ArrayList;
import java.util.List;

public class TableSensitiveArea {

    private ArrayList<LogicSimulator> thread_items_list;

    public TableSensitiveArea(ArrayList<LogicSimulator> thread_items_list) {
        this.thread_items_list = thread_items_list;
    }

    public ArrayList<String> createTable(String path, String circuitName) {

        ArrayList<String> f = new ArrayList<>();

        String contentHeader = "Vectors;";
        Boolean passed = false;

        for (int i = 0; i < this.thread_items_list.size(); i++) {
            //0 to 8 threads
            List<TestVectorInformation> x = this.thread_items_list.get(i).get_threadSimulationList();

            //Vector axis Y
            for (int j = 0; j < x.size(); j++) {
                //Vectors axis X 000; 0000; 0000; 0000; 0000;
                //System.out.print(x.get(j).getinputVector().toString()); // Head


                String content = x.get(j).getinputVector().toString();
                String contentv2 = "";

                for (int k = 0; k < x.get(j).getGatesLogicalPath().size(); k++) {
                    //Values for each gate

                    if (!passed && i == 0) {
                        contentHeader = contentHeader + x.get(j).getGatesLogicalPath().get(k).getGate() + ";";
                    }


                    // System.out.print(" " + x.get(j).getGatesLogicalPath().get(k).getGate()
                    // + " In: " + x.get(j).getGatesLogicalPath().get(k).getInputs()
                    // + " Out: " + x.get(j).getGatesLogicalPath().get(k).getOutputs()
                    //    + " " + x.get(j).getGatesLogicalPath().get(k).getgateSensitiveArea());

                    contentv2 = contentv2 + x.get(j).getGatesLogicalPath().get(k).getGate() + "-" + x.get(j).getGatesLogicalPath().get(k).getgateSensitiveArea() + ";";
                    content = content + "; " + x.get(j).getGatesLogicalPath().get(k).getgateSensitiveArea();


                }
                content = content + ";" + x.get(j).getSum_sensitive_cells_area_str();

                if (i == 0 && (!passed)) {
                    f.add(contentHeader + " AStotal" + ";");
                }
                passed = true;

                f.add(content);

            }


        }

        //System.out.println(header);
        //System.out.println(line);

        return f;

        /*
        try {
            FileWriter myWriter = new FileWriter(path + "TableAS_ " + circuitName +".csv");

            for (int i = 0; i < f.size(); i++) {
                myWriter.write(f.get(i) + "\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

         */
    }



    public ArrayList<String> createTableFaultFree(String path, String circuitName) {

        ArrayList<String> f = new ArrayList<>();

        String contentHeader = "Vectors;";
        Boolean passed = false;
        int cont = 0;
        List<TestVectorInformation> vectorPassed = new ArrayList<>();

        for (int i = 0; i < this.thread_items_list.size(); i++) {

            //0 to 8 threads
            List<TestVectorInformation> x = this.thread_items_list.get(i).get_threadSimulationList();
            //passed_vector = false;
            //Vector axis Y
            for (int j = 0; j < x.size(); j++) {
                //Vectors axis X 000; 0000; 0000; 0000; 0000;
                //System.out.print(x.get(j).getinputVector().toString()); // Head

                Float sum = 0F;
                vectorPassed.add(x.get(j));



                if (x.get(j).getFaultSignal().toString().equals(x.get(0).getFaultSignal().toString())) {
                    String content = x.get(j).getinputVector().toString();
                    //String contentv2 = "";
                    cont++;
                    for (int k = 0; k < x.get(j).getGatesLogicalPath().size(); k++) {
                        //Values for each gate

                        if (!passed && i == 0) {
                            contentHeader = contentHeader + x.get(j).getGatesLogicalPath().get(k).getGate() + ";";
                        }
                        //x.get(j).getGatesLogicalPath().get(k).getgateSensitiveArea()
                        sum = sum + x.get(j).getGatesLogicalPath().get(k).getgateSensitiveAreaOriginal();

                         System.out.print(" " + i + "_"+ cont + " -" + x.get(j).getGatesLogicalPath().get(k).getGate()
                         + " In: " + x.get(j).getGatesLogicalPath().get(k).getInputsOriginal()
                         + " Out: " + x.get(j).getGatesLogicalPath().get(k).getOutputsOriginal()
                            + " " + x.get(j).getGatesLogicalPath().get(k).getgateSensitiveAreaOriginal() +"|"+ x.get(j).getGatesLogicalPath().get(k).getgateSensitiveArea()+ " sum: " + sum + "  AST: " + x.get(j).getSum_sensitive_cells_area_original_str());

                        //contentv2 = contentv2 + x.get(j).getGatesLogicalPath().get(k).getGate() + "-" + x.get(j).getGatesLogicalPath().get(k).getgateSensitiveArea() + ";";

                        content = content + "; " + x.get(j).getGatesLogicalPath().get(k).getgateSensitiveAreaOriginal();
                        //passed_vector = true;


                    }

                    System.out.println("");
                    content = content + ";" + x.get(j).getSum_sensitive_cells_area_original_str();

                    if (i == 0 && (!passed)) {
                        f.add(contentHeader + " AStotal" + ";");
                    }
                    passed = true;

                    f.add(content);
                }
            }

            System.out.println("Content: " + f);
        }

        //System.out.println(header);
        //System.out.println(line);

        return f;

        /*
        try {
            FileWriter myWriter = new FileWriter(path + "TableAS_ " + circuitName +".csv");

            for (int i = 0; i < f.size(); i++) {
                myWriter.write(f.get(i) + "\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

         */
    }

    public ArrayList<String> createTableFaultNotMasked(String path, String circuitName) {

        ArrayList<String> f = new ArrayList<>();

        String contentHeader = "Vectors;FaultSignal;";
        Boolean passed = false;

        for (int i = 0; i < this.thread_items_list.size(); i++) {
            //0 to 8 threads
            List<TestVectorInformation> x = this.thread_items_list.get(i).get_threadSimulationList();

            //Vector axis Y
            for (int j = 0; j < x.size(); j++) {
                //Vectors axis X 000; 0000; 0000; 0000; 0000;
                //System.out.print(x.get(j).getinputVector().toString()); // Head


                String content = x.get(j).getinputVector().toString() + ";" + x.get(j).getFaultSignal().toString();
                String contentv2 = "";

                for (int k = 0; k < x.get(j).getGatesLogicalPath().size(); k++) {
                    //Values for each gate

                    if (!passed && i == 0) {
                        contentHeader = contentHeader + x.get(j).getGatesLogicalPath().get(k).getGate() + ";";
                    }


                    // System.out.print(" " + x.get(j).getGatesLogicalPath().get(k).getGate()
                    // + " In: " + x.get(j).getGatesLogicalPath().get(k).getInputs()
                    // + " Out: " + x.get(j).getGatesLogicalPath().get(k).getOutputs()
                    //    + " " + x.get(j).getGatesLogicalPath().get(k).getgateSensitiveArea());

                    contentv2 = contentv2 + x.get(j).getGatesLogicalPath().get(k).getGate() + "-" + x.get(j).getGatesLogicalPath().get(k).getgateSensitiveArea() + ";";
                    content = content + "; " + x.get(j).getGatesLogicalPath().get(k).getgateSensitiveArea();


                }
                content = content + ";" + x.get(j).getSum_sensitive_cells_area_str();

                if (i == 0 && (!passed)) {
                    f.add(contentHeader + " AStotal" + ";");
                }
                passed = true;

                f.add(content);

            }


        }

        //System.out.println(header);
        //System.out.println(line);

        return f;

        /*
        try {
            FileWriter myWriter = new FileWriter(path + "TableAS_ " + circuitName +".csv");

            for (int i = 0; i < f.size(); i++) {
                myWriter.write(f.get(i) + "\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

         */

    }
}
