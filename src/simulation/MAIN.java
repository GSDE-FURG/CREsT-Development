package simulation;

import com.sun.jdi.connect.Connector;
import com.sun.tools.javac.Main;

import java.io.IOException;
import java.util.ArrayList;

public class MAIN {  //Class to run debug tests
    int threads;
    int sample;
    String reliabilityConst;
    String relativePath;
    String genlib;
    String OUTPUT_INFO;
    ArrayList<String> gates =  new ArrayList<>();

    public MAIN(int threads, String reliabilityConst, String relativePath, String genlib){
            this.threads = threads;
            this.reliabilityConst = reliabilityConst;
            this.relativePath = relativePath;
            this.genlib = genlib;

    }

    public void runloop(SimulationInLot simulationInLotDebug) throws Exception {


        /*
        s953
                s5378
        s9234
                s15850
        s38417
                s35932
        s38584

             */
        //SimulationMode sim_mtf_debug = new SimulationMode(simulationInLotDebug.getCircuitListSpecs().get(4)); // first Circuit c.v
        //    sim_mtf_debug.PrintAVGSensitiveAreasCompiled("ok/", "45nm_HP.pm", "Library.txt");

             /*
        SimulationInLot simulationInLot = new SimulationInLot();
        simulationInLot.setup(relativePath, genlib, threads);
        simulationInLot.processParser(signalsToinjectFault, constReliability, sampleSize);  //STF's - String signalsToinjectFault, String reliabilityConst, int sampleSize
                //simulationInLot.processParser(signalsToinjectFault, constReliability, mtf_sizes); // MTF's
                simulationInLot.print();

        SimulationMode sim_stf = new SimulationMode(simulationInLot.getCircuitListSpecs().get(0)); // first Circuit c.v


        ///sim_stf.monteCarloSTFSimulation();
                //sim_stf.multithreadingSimulationExaustic();
                   //sim_stf.multithreadingSimulationExausticComplete();
                        //     sim_stf.monteCarloSimulationMultipleTransientFaultsProportion();



        SimulationInLot simulationInLotMTF = new SimulationInLot();
        simulationInLotMTF.setup(relativePath, genlib, threads);
        simulationInLotMTF.processParser(signalsToinjectFault, constReliability, mtf_sizes);

            SimulationMode sim_mtf = new SimulationMode(simulationInLotMTF.getCircuitListSpecs().get(0)); // first Circuit c.v
                //sim_mtf.monteCarloSimulationMultipleTransientFaults();
                    //sim_mtf.monteCarloSimulationMultipleTransientFaultsProportionAndCalculationOfSensitiveArea("netlist_files/", "45nm_HP.pm", "Library.txt");
                     //sim_mtf.monteCarloSimulationMultipleTransientFaultsProportionAndCalculationOfSensitiveArea();


        //SimulationSpiceNetlistCircuit simulationElectric= new SimulationSpiceNetlistCircuit();
            //simulationElectric.setup(relativePath + "netlist_files/", relativePath + "Library.txt");
                //simulationElectric.processParserSpiceNetlists();
                    //sim_mtf.runElectricalSimulation(relativePath , "netlist_files/" + simulationElectric.getCircuitsListName().get(4));  // fixed


        */
        //simulationInLotDebug.setup("circuitos/fullv2/", "lib_full_no_cost.genlib", threads);
        //simulationInLotDebug.setup("circuitos/ISCAS89/min/", "lib_min_no_cost.genlib", threads);
        /*
        simulationInLotDebug.setup("circuitos/ISCAS89/min/", "lib_min_no_cost.genlib", threads);
        sx.setup("circuitos/ISCAS89/fullv2/", "lib_full_no_cost.genlib", threads);
        epfl.setup("teste/mapped/EPFL2015/1-minimal_no_cost/", "1-minimal_no_cost.genlib", threads);

         */
        /*
        simulationInLotDebug.processParser(signalsToinjectFault, constReliability, mtf_sizes);
        sx.processParser(signalsToinjectFault, constReliability, mtf_sizes);
        epfl.processParser(signalsToinjectFault, constReliability, mtf_sizes);
        */


        for (int i = 0; i < simulationInLotDebug.getCircuitListSpecs().size(); i++) {


            SimulationMode sim_mtf_debug = new SimulationMode(simulationInLotDebug.getCircuitListSpecs().get(i)); // first Circuit c.v
            try {
                    sim_mtf_debug.monteCarloReliabilityAPI("");
                // sim_mtf_debug.GateCounter();

                //gates.add(sim_mtf_debug.OUTPUT_INFO);
                //sim_mtf_debug.faultToleranceExhaustiveCompleteMETAPI();
                ///sim_mtf_debug.faultToleranceExhaustiveSETAPI();
                //samples.add(sim_mtf_debug.getOutput_sample());
                //sim_mtf_debug.monteCarloReliabilityAPI("ok/", "45nm_HP.pm", "Library.txt");
                //sim_mtf_debug.monteCarloReliabilityAPI();
                //sim_mtf_debug.runElectricalSimulation();
                //-Xms2G -Xmx7G  para ISCAS89
           }catch (Exception e){
               System.out.println("- WARNING --- Some error happen in simulation test: " + e);
        }

        }
        System.out.println("---------------------- END ----------------------");
    }

    public static void readResults( String path) throws IOException {
        SimulationInLot circuits_folder = new SimulationInLot();



        circuits_folder.setup(path, "", 4);
        //circuits_folder.processParser(signalsToinjectFault, constReliability, mtf_sizes);
        circuits_folder.print();
        circuits_folder.readResultsInLot(path,"ALL_SIGNALS");

    }

    public void setup_run( String path, String genlib, String signalsToinjectFault, String constReliability, ArrayList <Float> mtf_sizes, int threads) throws Exception {

        SimulationInLot versao =  new SimulationInLot();

        versao.setup(path, genlib, threads);
        versao.processParser(signalsToinjectFault, constReliability, mtf_sizes);
        versao.print();
        ArrayList <String> info = new ArrayList<>();


        for (int i = 0; i < versao.getCircuitListSpecs().size(); i++) {
            //for (int i = 0; i < versao.getCircuitListSpecs().size(); i++) {
            System.out.println("circ: "+ versao.getCircuitListSpecs().get(i).getCircuit());
            readResults(path);

            for (int j = 0; j < 1; j++) { //Run once -  Run 3 times

             //   try {
                    System.out.println(j + "  --> circ: "+ versao.getCircuitListSpecs().get(i).getCircuit());
                    SimulationMode sim_mtf_debug = new SimulationMode(versao.getCircuitListSpecs().get(i));
                  //  if(i) {
                       sim_mtf_debug.monteCarloReliabilityAPI("teste/lookup_table.csv");

                    //sim_mtf_debug.faultToleranceExhaustiveCompleteMETAPIESTIMATION();
                    //sim_mtf_debug.faultToleranceExhaustiveSETAPI();
                   // sim_mtf_debug.faultToleranceExhaustiveCompleteMETAPI();
                   // sim_mtf_debug.monteCarloReliabilityAPI("teste/lookup_table.csv");
               // SimulationMode sim_mtf_debugz = new SimulationMode(versao.getCircuitListSpecs().get(i));
                //    sim_mtf_debugz.monteCarloReliability_SpiceGeneration_ElectricalSimulationAPI("teste/", "45nm_HP.pm", "Library.txt");
                   // }

                    //saida

                    ///
                    //
                   // int a = sim_mtf_debug.faultToleranceExhaustiveCompleteMETAPIESTIMATION();
                    //sim_mtf_debug.vector_Analisys("circuitos/lookup_table.csv");
                    // Number of gates

                    if(j == 0) {
                       // info.add(sim_mtf_debug.printGates("circuitos/lookup_table.csv"));  // Monte Carlo (n) - FMR e AS e MTBF
                       // gates.add(genlib + ";" +sim_mtf_debug.printGates("circuitos/lookup_table.csv"));
                    }

                    ///if(a <= 20000 && j ==0){
                    //    System.out.println("ERR : " + versao.getCircuitListSpecs().get(i) + "" + a);
                     //   gates.add(versao.getCircuitListSpecs().get(i).getCircuit() + ";" +a);
                    //}
                /*
                }catch (Exception e){
                    System.err.print(" \n\n xxxxx  Error in circuit: " + versao.getCircuitListSpecs().get(i).getCircuit());
                }
                */

        }




            //info.add( sim_mtf_debug.printGates("circuitos/ISCAS89/min/lookup_table.csv"));  // Monte Carlo (n) - FMR e AS e MTBF
            //readResults("circuitos/ISCAS89/fullv2/");
        }


        for (int i = 0; i < info.size() ; i++) {
            System.out.println(info.get(i));
        }
    }

    public static void main(String[] args) throws Exception{

            System.out.println("- New methodology....");
            MAIN experiment = new MAIN(0 ,"", "", "");
            String relativePath = "teste/mapped/EPFL2015/5-full_no_cost/"; //"teste/demo/";
            String genlib = "5-full_no_cost.genlib"; // "cadence.genlib"; //"lib_min_no_cost.genlib"; //"cadence.genlib";
            String signalsToinjectFault = "ALL_SIGNALS";
            String constReliability = "0.9999";




        int threads = 8;

        ArrayList<Float> mtf_sizes = new ArrayList<>();  //MTF's
        mtf_sizes.add((float) 200); // Sample
        mtf_sizes.add((float) 1); // STF
        mtf_sizes.add((float) 0); // MTF
        mtf_sizes.add((float) 0); // MTF

        String cx = ";";

        for (int i = 0; i < 0 ; i++) {

            SimulationInLot circuits_folder = new SimulationInLot();

            circuits_folder.setup(relativePath, genlib, threads);
            circuits_folder.processParser(signalsToinjectFault, constReliability, mtf_sizes);
            circuits_folder.print();

           // SimulationMode sim_mtf_debug = new SimulationMode(circuits_folder.getCircuitListSpecs().get(0));

            //sim_mtf_debug.faultToleranceMonteCarloMETAPI(""); // Simulação de Monte Carlo (N)  - FMR
            //sim_mtf_debug.faultToleranceExhaustiveSETAPI();    // Exaustiva Simples - FMR
            //sim_mtf_debug.faultToleranceExhaustiveCompleteMETAPI();  // Exaustiva Completa - FMR

           // sim_mtf_debug.monteCarloReliabilityAPI("teste/mapped/EPFL2015/5-full_no_cost/lookup_table.csv");  // Monte Carlo (n) - FMR e AS e MTBF
            //readResults("circuitos/ISCAS89/min");
        }

        SimulationInLot minimal = new SimulationInLot();
        SimulationInLot basic = new SimulationInLot();
        SimulationInLot complex = new SimulationInLot();
        SimulationInLot full = new SimulationInLot();
        SimulationInLot fullv2 = new SimulationInLot();



        //String minimal_path = "circuitos/ISCAS85/minimal/"; // ISCAS85
        //String basic_path = "circuitos/ISCAS85/basic/"; // ISCAS85


       // experiment.setup_run("circuitos/ISCAS85/minimal/", "minimal.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

        //experiment.setup_run("circuitos/ISCAS85/basic/", "basic.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

        //experiment.setup_run("circuitos/ISCAS85/complex/", "complex.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

        //experiment.setup_run(  "circuitos/ISCAS85/full/", "full.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

        //experiment.setup_run("circuitos/ISCAS85/fullv2/", "fullv2.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);
        /*
*/

        //  experiment.setup_run("circuitos/LGSynth91/minimal/", "minimal.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);
/*
        experiment.setup_run("circuitos/LGSynth91/basic/", "basic.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

        experiment.setup_run("circuitos/LGSynth91/complex/", "complex.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

        experiment.setup_run(  "circuitos/LGSynth91/full/", "full.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

        experiment.setup_run("circuitos/LGSynth91/fullv2/", "fullv2.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);
*/


  //      experiment.setup_run("circuitos/ISCAS89/minimal/", "minimal.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

     //   experiment.setup_run("circuitos/ISCAS89/basic/", "basic.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

      //  experiment.setup_run("circuitos/ISCAS89/complex/", "complex.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

        //experiment.setup_run(  "circuitos/ISCAS89/full/", "full.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

       // experiment.setup_run("circuitos/ISCAS89/fullv2/", "fullv2.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);
        /**/


        //experiment.setup_run("circuitos/EPFL15/minimal/", "minimal.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

        //experiment.setup_run("circuitos/EPFL15/basic/", "basic.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

        //experiment.setup_run("circuitos/EPFL15/complex/", "complex.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

        experiment.setup_run(  "teste/", "cadence.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);




        //experiment.setup_run("circuitos/EPFL15/fullv2/", "fullv2.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);
        /*
    */

       // experiment.gates.add(";;;");

       // System.out.println(experiment.gates);

       // for (int i = 0; i < experiment.gates.size(); i++) {
         //   System.out.println(experiment.gates.get(i));
        //}

        // minimal.setup(minimal_path, "lib_min_no_cost.genlib", threads);
           // minimal.processParser(signalsToinjectFault, constReliability, mtf_sizes);
           // minimal.print();



/*




        try {
            System.out.println("1t");

            String x  = "c";
            cx = x;
            System.out.println("SSSSS");
            if(x.equals("cccccc")){
                System.out.println("SSSSS");
            }else{
                System.out.println("SSSSS");
            }
          ///  Runtime.getRuntime().exec("c:\\Windows\\System32\\shutdown -s -t 60");
        } catch (IOException e) {
            String  a = "ok";
            e.printStackTrace();
        }
*/

    }
}
