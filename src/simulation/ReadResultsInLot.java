package simulation;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to parse result files and create the single compiled (.txt or .csv) versions
 */
public class ReadResultsInLot {
    private String path;
    private String filter;

    public static void main(String[] args) throws IOException {
                /*
                ArrayList <String> filesToSearch = new ArrayList<>();
                    filesToSearch.add("alu4");
                    filesToSearch.add("vda");
                    filesToSearch.add("frg2");
                    filesToSearch.add("t481");
                    filesToSearch.add("dalu");
                    filesToSearch.add("k2");
                    filesToSearch.add("pair");
                    filesToSearch.add("i8");
                    filesToSearch.add("i10");
                    filesToSearch.add("des");
                */

        ArrayList <String> filesToSearch = new ArrayList<>();
            filesToSearch.add("c432");

        ReadResultsInLot resultsInLot = new ReadResultsInLot();

        //resultsInLot.readResultsInLot("Resultados Proporção/min/", "ALL_SIGNALS");

        resultsInLot.readResultsInLotWithFilter("Resultados Proporção/min/", "ALL_SIGNALS", filesToSearch);

    }

    public ReadResultsInLot(){

    }

    public void readEachFileProportioncsv(ArrayList<String> files, String path, String filter) throws IOException{

        ArrayList <String> FileContent = new ArrayList<>();
        String sample = "";
        String Ne  = "";
        String FMR = "";
        String  time = "";

        FileContent.add("File; sample; NE; Time(s);");
        for (int i = 0; i < files.size(); i++) {
            List<String> fileContentList  = this.readFile(path + "/" +files.get(i));
            //System.out.println("Records: " + fileContentList);

            for (String x : fileContentList){

                if((x.contains("Reliability (soft error):"))|| (x.contains("Number of detected faults (Ne)"))){
                    //System.out.println("INSIDE");
                    ///String[] softErrorRate = x.split(":");
                    //out = files.get(i) + ";" + t[1];
                    //System.out.println("----- +" + t[1]);
                    //String[] sampleSize = fileContentList.get(3).split(":");
                    String[] tttt =   x.split(":");
                    Ne = tttt[1]; //fileContentList.get(9).split(":");
                    //String[] time = fileContentList.get(10).split(":");

                    //FileContent.add(files.get(i) + ";" + sampleSize[1] + ";" + Ne[1] + ";" + softErrorRate[1] + ";" + time[1]);
                    //FileContent.add(files.get(i) + ";" + sampleSize[1] + ";" + Ne[1] + ";" + time[1]);
                    //System.out.println("File: " + files.get(i)  +  " sample: " + sampleSize[1] + " > " + x + " t(s):" + time[1] );
                }
                if(x.contains("- Sample") || (x.contains("Number of Simulations"))){
                    String[] tttt =   x.split("=");
                    sample = tttt[1]; //fileContentList.get(9).split(":");
                }
                if(x.contains("Performance time")){
                    String[] tttt =   x.split(":");
                    time = tttt[1]; //fileContentList.get(9).split(":");
                }


            }
            FileContent.add(files.get(i) + ";" + sample + ";" + Ne + ";" + time);
            //System.out.println("File: " + files.get(i)  +  " sample: " + sample + " > " + Ne + " t(s):" + time );
        }


        PrintWriter pw = new PrintWriter(new FileOutputStream(path + "/" + "Results_compiled" + "_.csv"));
        for (String club : FileContent) {
            pw.println(club);
        }
        pw.close();
        System.out.println("");
        System.out.println("- Created file: " + path + "Results_compiled" + ".csv" );
                /*
                System.out.println(path + "/" + " resultado_.txt");
                File file = new File(path + "/" + " resultado_.txt");
                if(file.createNewFile()){
                System.out.println(" File Created");
                }else System.out.println("File  already exists");
                 */



    }

    public void readResultsInLotWithFilter(String path, String filter, ArrayList <String> filesToSearch) throws IOException{

        System.out.println("- Reading Result files... ");
        String[] circuitFiles;
        File f = new File(path);
        ArrayList <String> files = new ArrayList<>();
        ArrayList <String> filtered_files = new ArrayList<>();
        circuitFiles = f.list();

        if(Files.exists(f.toPath())){
            for (String pathname : circuitFiles) {
                if(pathname.endsWith(".txt")){
                    files.add(pathname);
                }
            }

            System.out.println("- Path folder: " + path);
            System.out.println("- Size List: " +  files.size());

            System.out.println("- Filtered files: ");
            for (int i = 0; i < files.size(); i++) {
                for (String file_temp: filesToSearch){
                    //String tmp = "ALL_SIGNALS_MULTIPLE_MonteCarlo_Simple_Log_" + file_temp + "_lib_full_no_cost_Threads-4_sampleSize-20000.txt";//"_lib_complex_no_cost_no_xor_Threads-4_sampleSize-20000.txt";
                    String tmp = file_temp;
                    if(files.get(i).contains(tmp)){
                        System.out.println("    -" + files.get(i));
                        filtered_files.add(files.get(i));
                    }
                }
            }
            System.out.println("- Total filtered files: " + filtered_files.size());
            this.readEachFileProportioncsv(filtered_files, path, filter);

            System.out.println("\n ------ End Process -------\n");
        }
        else{
            System.err.println("x Path to result files do not founded: " + path );
        }
    }

    private List<String> readFile(String filename){
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

    public void readResultsInLot(String path, String filter) throws IOException {

        System.out.println("-----------------------------------------------");

        String[] circuitFiles;
        File f = new File(path);
        ArrayList<String> files = new ArrayList<>();
        ArrayList <String> filtered_files = new ArrayList<>();
        circuitFiles = f.list();

        for (String pathname : circuitFiles) {
            if(pathname.endsWith(".txt")){ // test tipe .v
                //System.out.println(pathname);
                //circuitList.add(pathname);
                //this.circuitList.add(pathname);
                files.add(pathname);
            }
        }

        //System.out.println("- List: " +  files);
        System.out.println("- Results founded in path: " + path + "    Size: "+  files.size());

        for (int i = 0; i < files.size(); i++) {
            if(files.get(i).contains(filter)){
                System.out.println("    Extracting -> " + files.get(i));
                filtered_files.add(files.get(i));
            }
        }

        System.out.println("\n");
                /*
                List<String> records  = this.readFile(path + "/" +files.get(0));
                System.out.println("Records: " + records);

                for (String x : records){
                    System.out.println(">"+x);
                }
                 */


        //this.readEachFile(filtered_files, path, filter);
        this.readEachFileProportion(filtered_files, path, filter);
        System.out.println("------------------------------------------");

    }

    public void readEachFileProportion(ArrayList<String> files, String path, String filter) throws IOException{

        ArrayList <String> FileContent = new ArrayList<>();
        String sample = "";
        String Ne  = "";
        String FMR = "";
        String  time = "";

        FileContent.add("File; sample; NE; Time(s);");
        for (int i = 0; i < files.size(); i++) {
            List<String> fileContentList  = this.readFile(path + "/" +files.get(i));
            //System.out.println("Records: " + fileContentList);

            for (String x : fileContentList){

                if((x.contains("Reliability (soft error):"))|| (x.contains("Number of detected faults (Ne)"))){
                    //System.out.println("INSIDE");
                    ///String[] softErrorRate = x.split(":");
                    //out = files.get(i) + ";" + t[1];
                    //System.out.println("----- +" + t[1]);
                    //String[] sampleSize = fileContentList.get(3).split(":");
                    String[] tttt =   x.split(":");
                    Ne = tttt[1]; //fileContentList.get(9).split(":");
                    //String[] time = fileContentList.get(10).split(":");

                    //FileContent.add(files.get(i) + ";" + sampleSize[1] + ";" + Ne[1] + ";" + softErrorRate[1] + ";" + time[1]);
                    //FileContent.add(files.get(i) + ";" + sampleSize[1] + ";" + Ne[1] + ";" + time[1]);
                    //System.out.println("File: " + files.get(i)  +  " sample: " + sampleSize[1] + " > " + x + " t(s):" + time[1] );
                }
                if(x.contains("- Sample") || (x.contains("Number of Simulations"))){
                    String[] tttt =   x.split("=");
                    sample = tttt[1]; //fileContentList.get(9).split(":");
                }
                if(x.contains("Performance time")){
                    String[] tttt =   x.split(":");
                    time = tttt[1]; //fileContentList.get(9).split(":");
                }


            }
            FileContent.add(files.get(i) + ";" + sample + ";" + Ne + ";" + time);
            System.out.println("- File: " + files.get(i)  +  ";  sample: " + sample + ";" + Ne + "; t(s)" + time );
        }


        PrintWriter pw = new PrintWriter(new FileOutputStream(path + "/ResultsCompiled" + filter + "_.txt"));
        for (String club : FileContent) {
            pw.println(club);
        }
        pw.close();

        System.out.println();
        System.out.println("    >>> Compiled file created: " + path + "/ResultsCompiled" + filter + "_.txt" );
                /*
                System.out.println(path + "/" + " resultado_.txt");
                File file = new File(path + "/" + " resultado_.txt");
                if(file.createNewFile()){
                System.out.println(" File Created");
                }else System.out.println("File  already exists");
                 */



    }

}
