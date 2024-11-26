package simulation;
/*
import java.io.IOException;
import java.util.ArrayList;

public class MAIN_OLD {  //Class to run debug tests
    int threads;
    int sample;
    String reliabilityConst;
    String relativePath;
    String genlib;
    String OUTPUT_INFO;
    ArrayList<String> gates =  new ArrayList<>();

    public MAIN_OLD(int threads, String reliabilityConst, String relativePath, String genlib){
        this.threads = threads;
        this.reliabilityConst = reliabilityConst;
        this.relativePath = relativePath;
        this.genlib = genlib;

    }

    public void runloop(Simulation simulationDebug) throws Exception {


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

/*
        for (int i = 0; i < simulationDebug.getCircuitListSpecs().size(); i++) {


            SimualtionType sim_mtf_debug = new SimualtionType(simulationDebug.getCircuitListSpecs().get(i)); // first Circuit c.v
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
        Simulation circuits_folder = new Simulation();



        circuits_folder.setup(path, "", 4);
        //circuits_folder.processParser(signalsToinjectFault, constReliability, mtf_sizes);
        //circuits_folder.print();
        String pathFolder = path + "";

         //"Resultados - ISVLSI/Minimal/";
        //circuits_folder.readResultsInLot(pathFolder,"_ASnotMaked");

    }

    public void setup_run( String path, String genlib, String signalsToinjectFault, String constReliability, ArrayList <Float> mtf_sizes, int threads) throws Exception {

        Simulation versao =  new Simulation();
        versao.setup(path, genlib, threads);
        versao.processParser(signalsToinjectFault, constReliability, mtf_sizes);
        versao.print();

        //Simulation versao2 =  new Simulation();
        //versao2.setup(path, genlib, 8);
        //versao2.processParser(signalsToinjectFault, constReliability, mtf_sizes);
        //versao2.print();

        ArrayList<SimulationCircuit> erros = new ArrayList<>();


        ArrayList <String> info = new ArrayList<>();

        //threads = 1;
        readResults(path);


        for (int i = 0; i < versao.getCircuitListSpecs().size(); i++) {
            //for (int i = 0; i < versao.getCircuitListSpecs().size(); i++) {
            System.out.println("circ: "+ versao.getCircuitListSpecs().get(i).getCircuit());


 /// gddggddgggdgdggdd

            for (int j = 0; j <1; j++) { //Run once -  Run 3 times

                //   try {
                System.out.println(i + " interaction:" +j + "  --> circ: "+ versao.getCircuitListSpecs().get(i).getCircuit());
                SimualtionType sim_mtf_mc = new SimualtionType(versao.getCircuitListSpecs().get(i));
               // SimualtionType sim_mtf_debug = new SimualtionType(versao.getCircuitListSpecs().get(i));
                //  if(i) {  ddddddd eowjejwejhiwjeijew
                // sim_mtf_mc.monteCarloReliabilityAPI("teste/lookup_table.csv");

                //sim_mtf_mc.faultToleranceExhaustiveCompleteMETAPIESTIMATION();

                // TODO: 26/09/2022  v1  TESTE

                //sim_mtf_debug.faultToleranceExhaustiveSET_SensitiveAreaAPI("teste/", "45nm_HP.pm", "Library.txt"); //TODO Working ....

                //sim_mtf_mc.faultToleranceExhaustiveSETAPI(); //TODO Working

                ArrayList <String> vectors = new ArrayList<>();

                vectors.add("000000000");
                vectors.add("000000001");
                vectors.add("000000010");
                vectors.add("000000011");
                vectors.add("000000100");
                vectors.add("000000101");
                vectors.add("000000110");
                vectors.add("000000111");
                vectors.add("000001000");
                vectors.add("000001001");
                vectors.add("000001010");
                vectors.add("000001011");
                vectors.add("000001100");

                //More vectors
                //


                sim_mtf_mc.logicalSimulator(vectors); //TODO Working

                //sim_mtf_debug.faultToleranceExhaustiveSETAPIFAULT(); //TODO Working
                //sim_mtf_debug.exhaustiveSensitiveAreaAnalysis_AVG_and_NotMasked(); //TODO Working NEW APROUC

                try {
                    // sim_mtf_debug.exhaustiveSensitiveAreaAnalysis_AVG_and_NotMasked();
                   // sim_mtf_mc.monteCarloReliabilityAPI("teste/lookup_table.csv");
                   //sim_mtf_mc.monteCarloSensitiveAreaAnalysis_AVG_and_NotMasked(); //TODO Teste
                }catch (Exception e){
                    erros.add(versao.getCircuitListSpecs().get(i));
                    System.err.println("XXXXXXXXX  Circuit show some problems " + versao.getCircuitListSpecs().get(i));
                }



                //sim_mtf_debug.faultToleranceExhaustiveSETAPIFAULTADAPTIVE();  //TODO Working
                //sim_mtf_debug.monteCarloReliability_SpiceGeneration_ElectricalSimulationAPI("teste/", "45nm_HP.pm", "Library.txt");

                //sim_mtf_debug.faultToleranceExhaustiveCompleteMETAPI();
                // sim_mtf_debug.monteCarloReliabilityAPI("teste/lookup_table.csv");
                // SimulationMode sim_mtf_debugz = new SimulationMode(versao.getCircuitListSpecs().get(i));

                //USAR ESSE ->
                //sim_mtf_debug.monteCarloReliability_SpiceGeneration_ElectricalSimulationAPI("teste/", "45nm_HP.pm", "Library.txt");

                // TODO: 27/10/2022  v2 AQUI
                //sim_mtf_debug.faultToleranceExhaustiveSET_SensitiveAreaAPI("teste/", "45nm_HP.pm", "Library.txt");

                //sim_mtf_debug.monteCarloReliabilitySensitiveAreasVectorsAPI("teste/");
                // }

                //saida

                ///
                //
                // int a = sim_mtf_debug.faultToleranceExhaustiveCompleteMETAPIESTIMATION();
                //sim_mtf_debug.vector_Analisys("circuitos/lookup_table.csv");
                // Number of gates

                //  if(j == 0) {
                // info.add(sim_mtf_debug.printGates("circuitos/lookup_table.csv"));  // Monte Carlo (n) - FMR e AS e MTBF
                // gates.add(genlib + ";" +sim_mtf_debug.printGates("circuitos/lookup_table.csv"));
                // }

                ///if(a <= 20000 && j ==0){
                //    System.out.println("ERR : " + versao.getCircuitListSpecs().get(i) + "" + a);
                //   gates.add(versao.getCircuitListSpecs().get(i).getCircuit() + ";" +a);
                //}
                /*
                }catch (Exception e){
                    System.err.print(" \n\n xxxxx  Error in circuit: " + versao.getCircuitListSpecs().get(i).getCircuit());
                }
                */
/*
            }




            //info.add( sim_mtf_debug.printGates("circuitos/ISCAS89/min/lookup_table.csv"));  // Monte Carlo (n) - FMR e AS e MTBF
            //readResults("circuitos/ISCAS89/fullv2/");
        }


        for (int i = 0; i < info.size() ; i++) {
            System.out.println(info.get(i));
        }
        System.out.println("Circuits that present error: " + erros);
    }


    public static void main(String[] args) throws Exception{

        System.out.println("---> " + args[0]);
        System.out.println("- New methodology....");
        MAIN_OLD experiment = new MAIN_OLD(0 ,"", "", "");
        String relativePath = "teste/mapped/EPFL2015/5-full_no_cost/"; //"teste/demo/";
        String genlib = "5-full_no_cost.genlib"; // "cadence.genlib"; //"lib_min_no_cost.genlib"; //"cadence.genlib";
        String signalsToinjectFault = "ALL_SIGNALS";
        String constReliability = "0.9999";




        int threads = 2;
        int sample = 10;

        ArrayList<Float> mtf_sizes = new ArrayList<>();  //MTF's
        mtf_sizes.add((float) sample); // Sample
        mtf_sizes.add((float) 1); // STF
        mtf_sizes.add((float) 0); // MTF
        mtf_sizes.add((float) 0); // MTF

        String cx = ";";

        /*
        for (int i = 0; i < 0 ; i++) {

            Simulation circuits_folder = new Simulation();

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

         */
/*
        Simulation minimal = new Simulation();
        Simulation basic = new Simulation();
        Simulation complex = new Simulation();
        Simulation full = new Simulation();
        Simulation fullv2 = new Simulation();



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
*/        // sssss


        //      experiment.setup_run("circuitos/ISCAS89/minimal/", "minimal.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

        //   experiment.setup_run("circuitos/ISCAS89/basic/", "basic.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

        //  experiment.setup_run("circuitos/ISCAS89/complex/", "complex.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

        //experiment.setup_run(  "circuitos/ISCAS89/full/", "full.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

        // experiment.setup_run("circuitos/ISCAS89/fullv2/", "fullv2.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);
        /**/


        //experiment.setup_run("circuitos/EPFL15/minimal/", "minimal.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

        //experiment.setup_run("circuitos/EPFL15/basic/", "basic.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

        //experiment.setup_run("circuitos/EPFL15/complex/", "complex.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);
/*
        experiment.setup_run(  "teste/", "mylib.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);







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
/*
    }
}
*/