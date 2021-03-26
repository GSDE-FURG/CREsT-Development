package writers;


import logicSimulator.Test_Item;
import logicSimulator.Logic_Simulator;
import datastructures.Signal;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import static java.util.Spliterators.iterator;


import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class WriteCsvTh {

    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;
    
    final String inputFile = "";
    private  String FileName = "";
    private  String FileNameCsv = "";
    private  String SheetName= "";

  
    public void write() throws IOException, WriteException {
        
        System.out.println("Creating Excell file : "+ this.FileName);
        
        File file = new File(inputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet(this.SheetName, 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        createLabel(excelSheet);
        createContent(excelSheet);

        workbook.write();
        workbook.close();
    }
    
    public void writeCSV(String filename, String circuitName, ArrayList <Signal> inputSignals,  ArrayList <Signal> outputSignals,  ArrayList <Signal> all_signals, String Head, ArrayList <ArrayList<String>> inputArrayList, ArrayList <ArrayList<String>> output) throws IOException {
       
        this.FileName = filename;
        this.FileNameCsv = this.FileName + ".csv";
        
        System.out.println("Write CSV");
            
        System.out.println("Creating .csv -> file: "+ this.FileNameCsv);
        
            List<List<String>> rows = Arrays.asList(
               
             );

             FileWriter csvWriter = new FileWriter(this.FileNameCsv);
             
             csvWriter.append(Head);
             csvWriter.append("\n");
             
             System.out.println("       Header:  " + Head);
             //System.out.println("content: " + inputFile);
            System.out.println("Contnet csv: " + inputArrayList);
            int i = 0;
            
            for (Iterator<ArrayList<String>> iterator = inputArrayList.iterator(); iterator.hasNext();) {
                    ArrayList<String> next = iterator.next();
                    //System.out.println("atual : " + next.toString()); 
                    StringBuffer inputVector = new StringBuffer();
                    StringBuffer outputVector = new StringBuffer();
                    
                    //inputVector.append("");
                    inputVector.append("'");
                    for (String s : next) {
                            inputVector.append(s);
                            //sb.append(" ");
                    }
                    //inputVector.append("'");
                    
                   
                    ArrayList <String> temp = output.get(i);
                    String outStr = "'";
                     for (int j = 0; j < temp.size(); j++) {
                         outStr = outStr + temp.get(j);
                     }
                     //outStr = outStr + "'";
                     //outStr = outStr + "";
                     i++;
                   
                    csvWriter.append(inputVector +";"+ outStr);
                    csvWriter.append("\n");
            }
            
            
         
             csvWriter.flush();
             csvWriter.close();
    }
   
    /**
     *
     * @param filename
     * @param circuitName
     * @param inputSignals
     * @param outputSignals
     * @param all_signals
     * @param Head
     * @param inputArrayList
     * @param output
     * @throws java.io.IOException
     */
    public void writeCSVComplete(String filename, String circuitName, ArrayList <Signal> inputSignals,  ArrayList <Signal> outputSignals,  ArrayList <Signal> all_signals, String Head, ArrayList <ArrayList<String>> inputArrayList, ArrayList <ArrayList<String>> output) throws IOException {
       
        this.FileName = filename;
        this.FileNameCsv = this.FileName + ".csv";
        
            
        System.out.println("Creating .csv -> file: "+ this.FileNameCsv);
        
            List<List<String>> rows = Arrays.asList(
                 //Arrays.asList("Jean", "author", "Java"),
                 //Arrays.asList("David", "editor", "Python"),
                 //Arrays.asList("Scott", "editor", "Node.js")
                 
                  // Arrays.asList();
             );
            
            //rows = Arrays.asList("aaaaa");

             FileWriter csvWriter = new FileWriter(this.FileNameCsv);
                /*
                addCaption(sheet, 0, 0, "Fanouts");
                addCaption(sheet, 1, 0, "Reliability");
                addCaption(sheet, 2, 0, "Failure Rate (1E-6)");
                addCaption(sheet, 3, 0, "MTBF");
                addCaption(sheet, 4, 0, "Time (ms)");
                */
             String newStr = "";
             csvWriter.append("Circuit Name: ;");
                csvWriter.append(circuitName);
             csvWriter.append("\n");
             
             csvWriter.append("Input ("+ inputSignals.size() +"): ;");
                String inputnewStr = inputSignals.toString().replace("[", "");
                csvWriter.append(inputnewStr.replace("]", ""));
             csvWriter.append("\n");
             
             csvWriter.append("Output ("+ outputSignals.size() +"): ;");
                 String outnewStr = outputSignals.toString().replace("[", "");
                 
                csvWriter.append(outnewStr.replace("]", ""));
             csvWriter.append("\n");
             
             csvWriter.append("internal Signals ("+ all_signals.size() +"): ;");
                 String internalnewStr = all_signals.toString().replace("[", "");
                 //String outnewStr2 = internalnewStr.replace(inputnewStr, "");
                 //String outnewStr3 = outnewStr2.replace(outnewStr, "");
                 csvWriter.append(internalnewStr.replace("]", ""));
             csvWriter.append("\n");
             
             
             csvWriter.append("\n");
             csvWriter.append("\n");
             csvWriter.append(Head);
             csvWriter.append("\n");
             
             //System.out.println("       Header:  " + Head);
            // System.out.println("content: " + inputFile);
             
            int i = 0;
            for (Iterator<ArrayList<String>> iterator = inputArrayList.iterator(); iterator.hasNext();) {
                    ArrayList<String> next = iterator.next();
                    //System.out.println("atual : " + next.toString()); 
                    StringBuffer inputVector = new StringBuffer();
                    StringBuffer outputVector = new StringBuffer();
                    
                    //inputVector.append("");
                    inputVector.append("'");
                    for (String s : next) {
                            inputVector.append(s);
                            //sb.append(" ");
                    }
                    //inputVector.append("'");
                   
                    ArrayList <String> temp = output.get(i);
                    String outStr = "'";
                     for (int j = 0; j < temp.size(); j++) {
                         outStr = outStr + temp.get(j);
                     }
                     //outStr = outStr + "'";
                    //System.out.println("outStr: "+ outStr);
                    i++;
                    /*
                    String outputStr = output.get(i).toString().replace("[", "");
                    String outputStr2 = outputStr.replace("]", "");
                    String outputStr3 = outputStr2.replace(",", "");
                    System.out.println("OUT: " + output.get(i).toString());
                    outputVector.append(outputStr3);
                    i++;
                    */
                    
                    csvWriter.append(inputVector +";" + ";" + ";"+ outStr + ";");
                    csvWriter.append("\n");
            }
            
             /*
             for (int i = 0; i < content.size(); i++) {
                     String aux = content.get(i);
                     String out = output.get(i);
                     //System.out.println("AUX: "+aux);
                     csvWriter.append(Arrays.toString(aux.toCharArray()) + "; ~faultSignal~" + ";FaultyType" + ";" +  Arrays.toString(out.toCharArray())+ "\n");
                     //csvWriter.append("\n");
             }
            */
           
             /*

             for (int i = 0; i < this.resultTable.size(); i++ ) {
                 
                 if(i == 0){
                      csvWriter.append(this.resultTable.get(i).getLineResultTable() + ";" + this.resultTable.get(i).getLoadTime() + ";" + this.resultTable.get(i).getPrecision() + ";" + this.resultTable.get(i).getNumberFanouts());
                 }
                 else{
                     csvWriter.append(this.resultTable.get(i).getLineResultTable() + ";");
                 }

                 csvWriter.append("\n");
             }
             */

             csvWriter.flush();
             csvWriter.close();
    }
    
    public void writeCSVCompleteTh(String filename, String circuitName, ArrayList <Signal> inputSignals,  ArrayList <Signal> outputSignals,  ArrayList <Signal> all_signals, String Head, ArrayList <Logic_Simulator> thread_list_test, String propagated_faults) throws IOException {
       
        this.FileName = filename;
        this.FileNameCsv = this.FileName + ".csv";
        
            
        System.out.println("Creating .csv -> file: "+ this.FileNameCsv);
             
            /*
            List<List<String>> rows = Arrays.asList(
                 //Arrays.asList("Jean", "author", "Java"),
                 //Arrays.asList("David", "editor", "Python"),
                 //Arrays.asList("Scott", "editor", "Node.js")
                 
                  // Arrays.asList();
             );
            */
            //rows = Arrays.asList("aaaaa");

             FileWriter csvWriter = new FileWriter(this.FileNameCsv);
                /*
                addCaption(sheet, 0, 0, "Fanouts");
                addCaption(sheet, 1, 0, "Reliability");
                addCaption(sheet, 2, 0, "Failure Rate (1E-6)");
                addCaption(sheet, 3, 0, "MTBF");
                addCaption(sheet, 4, 0, "Time (ms)");
                */
             String newStr = "";
             csvWriter.append("Circuit Name: ;");
                csvWriter.append(circuitName);
             csvWriter.append("\n");
             
             csvWriter.append("Input ("+ inputSignals.size() +"): ;");
                String inputnewStr = inputSignals.toString().replace("[", "");
                csvWriter.append(inputnewStr.replace("]", ""));
             csvWriter.append("\n");
             
             csvWriter.append("Output ("+ outputSignals.size() +"): ;");
                 String outnewStr = outputSignals.toString().replace("[", "");
                 
                csvWriter.append(outnewStr.replace("]", ""));
             csvWriter.append("\n");
             
             csvWriter.append("internal Signals ("+ all_signals.size() +"): ;");
                 String internalnewStr = all_signals.toString().replace("[", "");
                 //String outnewStr2 = internalnewStr.replace(inputnewStr, "");
                 //String outnewStr3 = outnewStr2.replace(outnewStr, "");
                 csvWriter.append(internalnewStr.replace("]", ""));
             csvWriter.append("\n");
             
             
             csvWriter.append("\n");
             csvWriter.append("\n");
             csvWriter.append(Head + ";" + "Unmasked Faults");
             csvWriter.append("\n");
             
             //System.out.println("Header:  " + Head);
             //System.out.println("content: " + inputFile);
             

            String t = "";
           
             for (int i = 0; i < thread_list_test.size(); i++) {
                
                boolean flag = true;
                
                ArrayList <String> input_i = thread_list_test.get(i).get_inputListValuesStr();
                
                ArrayList <Test_Item> item = thread_list_test.get(i).getThreadSimulatinArray();
                
                for (int x=0, y=0; x< input_i.size() && y< item.size(); x++ , y++){
                
                    //System.out.println("x: " + x + "  y: "+y);
                    
                    if((x < input_i.size()) && (y < item.size())){
                        String s = input_i.get(x);
                            s = s.replace("[", "");
                            s = s.replace("]", "");
                            s = s.replace(" ", "");
                            s = s.replace(",", "");
                            s = "'" + s; 
                            
                            String faultFree = "'" + item.get(y).getOrignalOutput(); //+ "'";
                            String fault = "'" + item.get(y).getFaultOutput();
                            String T = "";
                            
                            if(!faultFree.equals(fault))
                                T = "FAILURE";
                            
                            if(flag){
                               
                                flag = false;
                            }
                            
                           // System.out.println("T: " + i + " fault: " + item.get(y).getFaultSignal() + " - v: "+ s + " faultfree: " + faultFree + " fault: " + fault + " - " + T );
                            //csvWriter.append(s  + ";" + item.get(y).getFaultSignal() +";" + "(" + item.get(y).getFaultSignal().getOriginalLogicValue()+ ")to(" +  item.get(y).getFaultSignal().getLogicValue() + ")" + ";"+ faultFree + ";" + fault + ";" + propagated_faults);
                            csvWriter.append(s  + ";" + item.get(y).getFaultSignal() +";" + item.get(y).getBitFlip() + ";"+ faultFree + ";" + fault + ";" + propagated_faults);
                            csvWriter.append("\n");
                            propagated_faults = "";
                    }
                
                }
             }
            
            //String Header = "Input Signals" + ";" + "Fault Signal" + ";" + "Fault Type" + ";" + "Fault-Free Circuit Output" + ";" + "Faulty Circuit Output";
            /*
            Iterator<ArrayList<String>> iteratorx = outputFault.iterator();
            Iterator<ArrayList<String>> itaratorFree = outputFreeFault.iterator();
            
            for (Iterator<ArrayList<String>> iterator = inputArrayList.iterator(); iterator.hasNext() && iteratorx.hasNext() && itaratorFree.hasNext();) {
                    //ArrayList<String> next = iterator.next();
                    ArrayList<String> input_i = iterator.next();
                    StringBuffer inputVector = new StringBuffer();
                 
                    
                    
                    ArrayList<String> output_freeFault = itaratorFree.next();
                    
                    //Iterator<ArrayList<String>> iteratorfault = outputFault.iterator();
                    //ArrayList<String> output_fault = iteratorfault.next();
                      
                    Iterator<ArrayList<String>> iteratorfaultSIG = signalFault.iterator();
                    ArrayList<String> sig = iteratorfaultSIG.next();
                      
                    Iterator<ArrayList<String>> iteratorfaultbitFilp = signalFaultBitFlip.iterator();
                    ArrayList<String> bitflip = iteratorfaultbitFilp.next();
                    
                    ArrayList<String> output_thread_i = iteratorx.next();
                    j = 0;
                    for (String s : input_i) {
                            s = s.replace("[", "");
                            s = s.replace("]", "");
                            s = s.replace(" ", "");
                            s = s.replace(",", "");
                           // System.out.println(""+ s);
                            //inputVector.append(s);
                            //sb.append(" ");
                            s = "'" + s;
                            String output_free;
                            String fault;
                            t = "";
                            
                            if(j < output_freeFault.size()){
                               output_free = "'" + output_freeFault.get(j);
                               fault = "'" + output_thread_i.get(j);
                               
                               
                               //if(output_free.equals(fault)){
                               //    propagated_faults++;
                              // }
                               
                           */
            /*
                               if(i == 0 ){
                                   t = Integer.toString(propagated_faults) ;
                               }
                                csvWriter.append(s  +";" + sig.get(j) + ";" + bitflip.get(j) + ";"+ output_free + ";" + fault + ";" + t );
                                csvWriter.append("\n");
                            
                                 System.out.println((i+1) + "-input:" + s + "'" +"; " + sig.get(j) + "; " + bitflip.get(j) + "; "+ output_free + "; " + fault);
                            }
                               i++;
                               j++;
                    }
                     iteratorx.hasNext();
                     itaratorFree.hasNext();
                   
            }
             */
             System.out.println("Propagated faults (SET): " + propagated_faults);
             csvWriter.flush();
             csvWriter.close();
    }
    
    @SuppressWarnings("empty-statement")
     public void writeCSVTh(String filename, String circuitName, ArrayList <Signal> inputSignals,  ArrayList <Signal> outputSignals,  ArrayList <Signal> all_signals, String Head, ArrayList <Logic_Simulator> thread_list_tesst_items) throws IOException {
       
        this.FileName = filename;
        this.FileNameCsv = this.FileName + ".csv";
        
        System.out.println("Write CSV threading: ");
            
        System.out.println("Creating .csv -> file: "+ this.FileNameCsv);
        
            List<List<String>> rows = Arrays.asList(
               
             );

             FileWriter csvWriter = new FileWriter(this.FileNameCsv);
             
             csvWriter.append(Head);
             csvWriter.append("\n");
             
             System.out.println("       Header:  " + Head);
           /*
             //System.out.println("content: " + inputFile);
            System.out.println("Contend csv: " + inputArrayList);
            System.out.println("Contend csv SIZE: " + inputArrayList.size());
            System.out.println("OUT Contend csv SIZE: " + output.size());
            //System.out.println("OUT Contend csv SIZE: " + output.size());;;;
            */
            int count = 0;
            //int i = 0;
           
            
            for (int i = 0; i < thread_list_tesst_items.size(); i++) {
                
                ArrayList <String> input_i = thread_list_tesst_items.get(i).get_inputListValuesStr();
                
                ArrayList <Test_Item> item = thread_list_tesst_items.get(i).getThreadSimulatinArray();
                
                for (int x=0, y=0; x< input_i.size() && y< item.size(); x++ , y++){
                
                    //System.out.println("x: " + x + "  y: "+y);
                    
                    if((x < input_i.size()) && (y < item.size())){
                        String s = input_i.get(x);
                            s = s.replace("[", "");
                            s = s.replace("]", "");
                            s = s.replace(" ", "");
                            s = s.replace(",", "");
                            s = "'" + s; 
                            
                            String faultFree = "'" + item.get(y).getOrignalOutput();
                            
                            //System.out.println("T: " + i + " - v: "+ s + " faultfree: " + faultFree );
                            
                            csvWriter.append(s  + ";" + faultFree);
                            csvWriter.append("\n");
                    }
                
                }
                
               /*
                for ((String s : input_i) && (Test_Item t: x)) {
                            //System.out.println( );      
                            s = s.replace("[", "");
                            s = s.replace("]", "");
                            s = s.replace(" ", "");
                            s = s.replace(",", "");
                            s = "'" + s; 
                            
                            
                            
                            System.out.println("T: " + i + " - v: "+ s + " faultfree: "+ item);
                            //csvWriter.append(s  + ";" + out);
                }
                */
            }
            
            //Iterator<ArrayList<String>> iteratorx = output.iterator();
            /*
             for (;iteratorx.hasNext();) {

                         ArrayList<String> next = iteratorx.next();
                         System.out.println("Cx: "+ cx + "  - string: " + next);
                         cx++;
             }
             */
            /*
            for (Iterator<ArrayList<String>> iterator = inputArrayList.iterator(); iterator.hasNext() && iteratorx.hasNext();) {
               
                ArrayList<String> input_i = iterator.next();
                
                //ArrayList<String> output_thread_list_i = iteratorx.next();
                    //Iterator<ArrayList<String>> iteratorOut = output.iterator();
                    //ArrayList<String> itOut = iteratorOut.next();
                    //System.out.println("atual : " + next.toString()); 
                    //StringBuffer inputVector = new StringBuffer();
                   
                    //System.out.println("I: "+ count);
                    count++;
                    
                    ArrayList<String> output_thread_i = iteratorx.next();
                    //System.out.println("Count: "+ count + "input: " + input_i + "\n  - string: " + output_thread_i);
                    
                    int j = 0;
                    for (String s : input_i) {
                            //System.out.println( );      
                            s = s.replace("[", "");
                            s = s.replace("]", "");
                            s = s.replace(" ", "");
                            s = s.replace(",", "");
                            s = "'" + s; 
                            
                           // inputVector.append(s);
                            //sb.append(" ");
                               String out = "";
                               if(j<input_i.size()){
                                   out = "'" + output_thread_i.get(j);
                               }
                               //System.out.println((i+1) + " Input: " + s +  " out: " + out);
                               
                               csvWriter.append(s  + ";" + out);
                               csvWriter.append("\n");
                               i++;
                               j++;
                    }
                    
                  
                    
                    iteratorx.hasNext();
                   
                    
                   
                    
                    //csvWriter.append("\n");
                   
                   // csvWriter.append(inputVector +";"+ s);
                   // csvWriter.append("\n");
            }
            */
            
         
             csvWriter.flush();
             csvWriter.close();
    }
   

    private void createLabel(WritableSheet sheet)
            throws WriteException {
        // Lets create a times font
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        // Define the cell format
        times = new WritableCellFormat(times10pt);
        // Lets automatically wrap the cells
        times.setWrap(true);

        // create create a bold font with unterlines
        WritableFont times10ptBoldUnderline = new WritableFont(
                WritableFont.TIMES, 10, WritableFont.BOLD, false,
                UnderlineStyle.SINGLE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        // Lets automatically wrap the cells
        timesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);
        cv.setAutosize(true);

        // Write a few headers
        addCaption(sheet, 0, 0, "Fanouts");
        addCaption(sheet, 1, 0, "Reliability");
        addCaption(sheet, 2, 0, "Failure Rate");
        addCaption(sheet, 3, 0, "MTBF");
        addCaption(sheet, 4, 0, "Time m(s)");
        addCaption(sheet, 5, 0, "Time (min)");
        addCaption(sheet, 6, 0, "LoadTime m(s)");
        addCaption(sheet, 7, 0, "Precision");
        addCaption(sheet, 8, 0, "Number of Fanouts");
        

    }

    private void createContent(WritableSheet sheet) throws WriteException,
            RowsExceededException {
        // Write a few number
        /*
        for (int i = 1; i < 10; i++) {
            // First column
            addNumber(sheet, 0, i, i + 10);
            // Second column
            addNumber(sheet, 1, i, i * i);
        }
        */
        /*
        // Lets calculate the sum of it
        StringBuffer buf = new StringBuffer();
        buf.append("SUM(A2:A10)");
        Formula f = new Formula(0, 10, buf.toString());
        sheet.addCell(f);
        buf = new StringBuffer();
        buf.append("SUM(B2:B10)");
        f = new Formula(1, 10, buf.toString());
        sheet.addCell(f);

        // now a bit of text
        for (int i = 12; i < 20; i++) {
            // First column
            addLabel(sheet, 0, i, "Boring text " + i);
            // Second column
            addLabel(sheet, 1, i, "Another text");
        }
        */
        
        //HERE NOW
         //for (int i = 0; i <= this.idx; i++){
             //System.out.println(resultTable.get(i).PrintItemx());
             
             /*
             if(i == 0){
                addNumber(sheet, 0, i+1, (this.resultTable.get(i).getIdxFanout())); //Fanout
             
                addLabel(sheet, 1, i+1,  (this.resultTable.get(i).getReliability())); //reliability      
             
                String strFormulaFailureRate = "-LN(B"+(i+2)+")" + "/" + this.delimitator;//"  (-ln(0,99999)/1E-6) " ;//
             
                Formula formulaFailureRate = new Formula(2, i+1, strFormulaFailureRate);
             
                sheet.addCell(formulaFailureRate); //FailureRate

                String strFormulaMTBF = "1/(C"+(i+2)+")" ; //"-LN(B"+(i+2)+")"; //MTBF

                Formula formulaMTBF = new Formula(3, i+1, strFormulaMTBF);

                sheet.addCell(formulaMTBF); //MTBF

                addLabel(sheet, 4, i+1,  (this.resultTable.get(i).getTime())); //Time m(s)
                
                addLabel(sheet, 5, i+1,  (this.resultTable.get(i).getTimeMin())); //Time (min)
                
                addLabel(sheet, 6, i+1, this.resultTable.get(i).getLoadTime()); //Loatime
                
                addLabel(sheet, 7, i+1, this.resultTable.get(i).getPrecision()); //Precision
                
                addLabel(sheet, 8, i+1, this.resultTable.get(i).getNumberFanouts()); //NumberFanouts
                
             }
             else{
                
                if(i == this.idx){
                     addLabel(sheet, 0, i+1, (this.resultTable.get(i).getFanoutTimeoutOver())); //Fanout
                }else{
                     addNumber(sheet, 0, i+1, (this.resultTable.get(i).getIdxFanout())); //Fanout
                }
               
             
                addLabel(sheet, 1, i+1,  (this.resultTable.get(i).getReliability())); //reliability      
             
                String strFormulaFailureRate = "-LN(B"+(i+2)+")" + "/" + this.delimitator;//"  (-ln(0,99999)/1E-6) " ;//
             
                Formula formulaFailureRate = new Formula(2, i+1, strFormulaFailureRate);
             
                sheet.addCell(formulaFailureRate); //FailureRate

                String strFormulaMTBF = "1/(C"+(i+2)+")" ; //"-LN(B"+(i+2)+")"; //MTBF

                Formula formulaMTBF = new Formula(3, i+1, strFormulaMTBF);

                sheet.addCell(formulaMTBF); //MTBF

                addLabel(sheet, 4, i+1,  (this.resultTable.get(i).getTime()));
                
                 addLabel(sheet, 5, i+1,  (this.resultTable.get(i).getTimeMin())); //Time (min)

             }
             */
           
        // }
    }

    private void addCaption(WritableSheet sheet, int column, int row, String s)
            throws RowsExceededException, WriteException {
        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);
    }

    private void addNumber(WritableSheet sheet, int column, int row,
            Integer integer) throws WriteException, RowsExceededException {
        Number number;
        number = new Number(column, row, integer, times);
        sheet.addCell(number);
    }

    private void addLabel(WritableSheet sheet, int column, int row, String s)
            throws WriteException, RowsExceededException {
        Label label;
        label = new Label(column, row, s, times);
        sheet.addCell(label);
       
    }
    /*
    public static void main(String[] args) throws WriteException, IOException {
        WriteExcel test = new WriteExcel("Name.xls", "RESULTADO");
        //test.setOutputFile("lars.xls");
        test.write("Resultado");
        System.out
                .println("Please check the result file under : " + "Resultado");
    }
    */
}