package writers;

import datastructures.ItemX;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class WriteExcel {

    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;
    private String inputFile;
    private String FileName;
    private String SheetName;
    private int idx;
    private String TimeoutMiliSeconds;
    private long TimeoutSeconds;
    private String delimitator;
    private List<ItemX> resultTable;

    public WriteExcel(String inputFile, String SheetName, String TimeoutMiliSeconds ,List<ItemX> resultTable, int idx) {
            this.inputFile = inputFile + ".xls";
            this.FileName = this.inputFile;
            this.SheetName =  SheetName + "-" + TimeoutSeconds;
            this.resultTable = resultTable;
            this.TimeoutMiliSeconds = TimeoutMiliSeconds;
            this.TimeoutSeconds = Long.valueOf(TimeoutMiliSeconds) / 1000;
            this.idx = idx;
            this.delimitator = "0.000001"; //1E-6
            
            System.out.println("Failire Rate = Reliability / "+ this.delimitator + " = 1E-6");
            //System.out.println(this.TimeoutSeconds);
    }

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
        addCaption(sheet, 2, 0, "Failure Rate (1E-6)");
        addCaption(sheet, 3, 0, "MTBF");
        addCaption(sheet, 4, 0, "Time (ms)");
        
        


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
         for (int i = 0; i <= this.idx; i++){
             //System.out.println(resultTable.get(i).PrintItemx());
             addNumber(sheet, 0, i+1, (this.resultTable.get(i).getIdxFanout())); //Fanout
             
             addLabel(sheet, 1, i+1,  (this.resultTable.get(i).getMTBF())); //reliability      
             
                String strFormulaFailureRate = "-LN(B"+(i+2)+")" + "/" + this.delimitator;;//"(B"+(i+2)+")/1E-6" ;//
             
                Formula formulaFailureRate = new Formula(2, i+1, strFormulaFailureRate);
             
             sheet.addCell(formulaFailureRate); //FailureRate
             
             String strFormulaMTBF = "1/(C"+(i+2)+")" ; //"-LN(B"+(i+2)+")"; //MTBF
             
             Formula formulaMTBF = new Formula(3, i+1, strFormulaMTBF);
             
              sheet.addCell(formulaMTBF); //MTBF

             addLabel(sheet, 4, i+1,  (this.resultTable.get(i).getTime()));
             
              //this.
         }
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