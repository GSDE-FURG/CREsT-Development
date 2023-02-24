package simulation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private ArrayList <SimulationCircuit> circuitListSpecs;
    private ArrayList <String> circuitsListName;
    private SimulationInLotParser simulationCircuits;
    private String relativePath;
    private String genlib;
    int threads;

    /**
     * Constructor
     */
    public Simulation(){

    }

    public void setup(String relativePath, String genlib, int threads){

        this.threads = threads;
        this.genlib = genlib;
        this.relativePath = relativePath;


    }

    public void print(){
        System.out.println("Path: " + this.relativePath + " ~ Genlib: " + this.genlib + " ~ Threads: " + this.threads);
    }

    public ArrayList<SimulationCircuit> getCircuitListSpecs() {
        return circuitListSpecs;
    }

    public SimulationInLotParser getSimulationCircuits() {
        return simulationCircuits;
    }

    /**
     * Method for STF construction
     * @param signalsToinjectFault
     * @param reliabilityConst
     * @param sampleSize
     */
    public void processParser(String signalsToinjectFault, String reliabilityConst, int sampleSize){ // FOR STF's
            SimulationInLotParser parserFiles = new SimulationInLotParser(this.relativePath);

            parserFiles.getVerilogCircuitFilesPath();
            parserFiles.circuitListParser(this.genlib, signalsToinjectFault, threads, reliabilityConst, sampleSize); //String genlib, String signalsToinjectFault, int threads, String reliabilityConst, int sampleSize

            this.circuitsListName = parserFiles.getCircuitList();  /// List of circuits
            this.circuitListSpecs = parserFiles.getSimulationCircuitsList();

            System.out.println("- STF: CircuitList: " + this.circuitsListName );
            System.out.println("- STF: Circuits object parsed: " + this.circuitListSpecs );


            parserFiles.printCircuitsSpecs();


    }

    /**
     * Method for MTF's construction
     * @param signalsToinjectFault
     * @param reliabilityConst
     * @param mtf_sizes
     */
    public void processParser(String signalsToinjectFault, String reliabilityConst,  ArrayList <Float> mtf_sizes){
            SimulationInLotParser parserFiles = new SimulationInLotParser(this.relativePath);

            parserFiles.getVerilogCircuitFilesPath();
            parserFiles.circuitListParser(this.genlib, signalsToinjectFault, threads, reliabilityConst, mtf_sizes); //String genlib, String signalsToinjectFault, int threads, String reliabilityConst, int sampleSize

            this.circuitsListName = parserFiles.getCircuitList();  /// List of circuits

            this.circuitListSpecs = parserFiles.getSimulationCircuitsList();

            System.out.println("- MTF: CircuitList: " + this.circuitsListName );
            System.out.println("- MTF: Circuits object parsed: " + this.circuitListSpecs );
            parserFiles.printCircuitsSpecs();
    }

    //TODO: Make method for read results in lot
    /**
     * This method parses multiple circuits results logs in a third one log (combining all circuits) MAJOR METHOD
     * @param path circuits path
     * @param filter filter (.v)
     * @throws IOException e
     */
    public void readResultsInLot(String path, String filter) throws IOException{
        System.out.println("INSIDE");
        String[] circuitFiles;
        File f = new File(path);
        ArrayList <String> files = new ArrayList<>();
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
        System.out.println("results in List: " +  files);
        System.out.println("Size List: " +  files.size());

        for (int i = 0; i < files.size(); i++) {
            if(files.get(i).contains(filter)){
                System.out.println("->" + files.get(i));
                filtered_files.add(files.get(i));
            }
        }
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

    /**
     * This method parses circuits results logs MET (FMR log) files, to combine the simulation informations (circuit, sample, Ne, FMR, time)  in a third one log (combining all circuits)
     * @param files Arraylist with circuits names
     * @param path circuits path
     * @param filter filter (.v)
     * @throws IOException e
     */
    public void readEachFileProportion(ArrayList<String> files, String path, String filter) throws IOException{

        ArrayList <String> FileContent = new ArrayList<>();
        String avgSA = "";
        String Ne  = "";
        String MTBF  = "";
        String FMR = "";
        String  time = "";

        FileContent.add("Circuit"+ ";" + "Ne" + ";" + "FMR" + ";" + "ASavg" + ";" + "MTBF" + ";" + "Time(s)");
        for (int i = 0; i < files.size(); i++) {
            List<String> fileContentList  = this.readFile(path + "/" +files.get(i));
            //System.out.println("Records: " + fileContentList);

            for (String x : fileContentList){


                if((x.contains("MTBF:"))){
                   // System.out.println("INSIDE");
                    String[] tttt =   x.split(":");
                    MTBF = tttt[1]; //fileContentList.get(9).split(":");
                }
                if((x.contains("Detected faults (Ne):"))){

                    String[] tttt =   x.split(":");

                    Ne = tttt[1]; //fileContentList.get(9).split(":");
                   // System.out.println(" --------- " + tttt + "     Ne: " + Ne);
                }
                if(x.contains("avgSA:")){
                    String[] tttt =   x.split(":");

                    avgSA = tttt[1]; //fileContentList.get(9).split(":");
                }
                if(x.contains("Fault Masking Rate (FMR):")){
                    String[] tttt =   x.split(":");
                    String[] ttttcc =   tttt[1].split("=");



                    FMR = ttttcc[2]; //fileContentList.get(9).split(":");
                    System.out.println(" \n ----->>>>> FMR = " + FMR);
                }
                if(x.contains("Performance time m(s):")){
                    String[] tttt =   x.split(":");
                    time = tttt[1]; //fileContentList.get(9).split(":");
                }


            }
            FileContent.add(files.get(i) + ";" + Ne + ";" + FMR + ";" + avgSA + ";" + MTBF + ";" + time);
            System.out.println("File: " + files.get(i) + " Ne: " + Ne +  " FMR" + FMR + " avgSA: " + avgSA + " MTBF: " + MTBF + " Time: " + time);
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




}
