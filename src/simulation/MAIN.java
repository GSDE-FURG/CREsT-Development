package simulation;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParseResult;
import picocli.CommandLine.Spec;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Callable;

@Command(name = "propagator", mixinStandardHelpOptions = true, version = "propagator 1.0",
        description = "Propagate logic signal in a mapped verilog circuit in a fault free scenario")
class Propagator implements Callable<Integer> {
    @Spec CommandSpec spec;
    @Option(names = {"-i"}, paramLabel = "MAPPED-VERILOG", description = "input mapped verilog circuit")
    File inVerilog;
    @Option(names = {"-g"}, paramLabel = "GENLIB", description = "input genlib")
    File inGenlib;

    @Override
    public Integer call() throws Exception {
        /**
          * Usei para verificar a quantidade de argumentos passados
          * É praciso testar se ao usar opções "required" como que esse comando se comportará
          */
        ParseResult parseResult = spec.commandLine().getParseResult();
        //System.out.println(parseResult.expandedArgs()); // all args after expanding any @file arguments
        //System.out.println(parseResult.originalArgs()); // original args prior to expanding @file arguments

        if (parseResult.originalArgs().size() > 0) {
            MAIN experiment = new MAIN(0 ,"", "", "");
            experiment.propagateSetup(inVerilog, inGenlib);
            return 0;
        } else {
            System.out.println("Nao foi passado nenhum parâmentro, abrir o modo interativo??");
            return 1;
        }
    }
}

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

    public void propagateSetup() throws Exception {

        MAIN experiment = new MAIN(0 ,"", "", "");
        String signalsToinjectFault = "ALL_SIGNALS";
        String constReliability = "0.9999";

        int threads = 2;
        int sample = 10;

        ArrayList<Float> mtf_sizes = new ArrayList<>();  //MTF's
        mtf_sizes.add((float) sample); // Sample
        mtf_sizes.add((float) 1); // STF
        mtf_sizes.add((float) 0); // MTF
        mtf_sizes.add((float) 0); // MTF

        experiment.setup_run(  "teste/", "mylib.genlib", signalsToinjectFault, constReliability, mtf_sizes, threads);

    }

    public void propagateSetup(File verilog, File genlib) throws Exception {

        MAIN experiment = new MAIN(0 ,"", "", "");
        String signalsToinjectFault = "ALL_SIGNALS";
        String constReliability = "0.9999";

        int threads = 2;
        int sample = 10;

        ArrayList<Float> mtf_sizes = new ArrayList<>();  //MTF's
        mtf_sizes.add((float) sample); // Sample
        mtf_sizes.add((float) 1); // STF
        mtf_sizes.add((float) 0); // MTF
        mtf_sizes.add((float) 0); // MTF

        experiment.setup_run_one(  verilog, genlib, signalsToinjectFault, constReliability, mtf_sizes, threads);

    }

    public void setup_run( String path, String genlib, String signalsToinjectFault, String constReliability, ArrayList <Float> mtf_sizes, int threads) throws Exception {

        Simulation versao =  new Simulation();
        versao.setup(path, genlib, threads);
        versao.processParser(signalsToinjectFault, constReliability, mtf_sizes);

        ArrayList<SimulationCircuit> erros = new ArrayList<>();


        ArrayList <String> info = new ArrayList<>();

        //threads = 1;
        //readResults(path);


        for (int i = 0; i < versao.getCircuitListSpecs().size(); i++) {
            //for (int i = 0; i < versao.getCircuitListSpecs().size(); i++) {
            System.out.println("circ: "+ versao.getCircuitListSpecs().get(i).getCircuit());


            for (int j = 0; j <1; j++) { //Run once -  Run 3 times

                //   try {
                System.out.println(i + " interaction:" +j + "  --> circ: "+ versao.getCircuitListSpecs().get(i).getCircuit());
                SimualtionType sim_mtf_mc = new SimualtionType(versao.getCircuitListSpecs().get(i));


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

                /*
                try {
                    // sim_mtf_debug.exhaustiveSensitiveAreaAnalysis_AVG_and_NotMasked();
                   // sim_mtf_mc.monteCarloReliabilityAPI("teste/lookup_table.csv");
                   //sim_mtf_mc.monteCarloSensitiveAreaAnalysis_AVG_and_NotMasked(); //TODO Teste
                }catch (Exception e){
                    erros.add(versao.getCircuitListSpecs().get(i));
                    System.err.println("XXXXXXXXX  Circuit show some problems " + versao.getCircuitListSpecs().get(i));
                } */

            }
        }


        for (int i = 0; i < info.size() ; i++) {
            System.out.println(info.get(i));
        }
        System.out.println("Circuits that present error: " + erros);
    }


    public void setup_run_one( File verilog, File genlib, String signalsToinjectFault, String constReliability, ArrayList <Float> mtf_sizes, int threads) throws Exception {

        String full_path = verilog.getAbsolutePath();
        String full_parent_path = full_path.replace("" + File.separator + verilog.getName(), "");

        Simulation versao =  new Simulation();
        versao.setup(full_parent_path, genlib.getName(), threads);


        versao.processParserMatheus(verilog, genlib, signalsToinjectFault, constReliability, mtf_sizes);

        ArrayList<SimulationCircuit> erros = new ArrayList<>();
        ArrayList <String> info = new ArrayList<>();



        for (int i = 0; i < versao.getCircuitListSpecs().size(); i++) {

            for (int j = 0; j <1; j++) { //Run once -  Run 3 times

                SimualtionType sim_mtf_mc = new SimualtionType(versao.getCircuitListSpecs().get(i));

                ArrayList <String> vectors = new ArrayList<>();

                /*
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
                */
                //More vectors
                //


                sim_mtf_mc.logicalSimulator(vectors); //TODO Working

                /*
                try {
                    // sim_mtf_debug.exhaustiveSensitiveAreaAnalysis_AVG_and_NotMasked();
                   // sim_mtf_mc.monteCarloReliabilityAPI("teste/lookup_table.csv");
                   //sim_mtf_mc.monteCarloSensitiveAreaAnalysis_AVG_and_NotMasked(); //TODO Teste
                }catch (Exception e){
                    erros.add(versao.getCircuitListSpecs().get(i));
                    System.err.println("XXXXXXXXX  Circuit show some problems " + versao.getCircuitListSpecs().get(i));
                } */

            }
        }


        for (int i = 0; i < info.size() ; i++) {
            System.out.println(info.get(i));
        }
        //System.out.println("Circuits that present error: " + erros);
    }



    public static void main(String... args) throws Exception{
        int exitCode = new CommandLine(new Propagator()).execute(args);
        System.exit(exitCode);
    }
}
