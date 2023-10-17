package twoLevelDatastructures;

import ops.ShellScriptOps;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class PLAOps {

    public static PLA getApproxPLAWithDontCarePerOutput(PLA originalPLA) {

        /**
         * Split the original PLA in per output PLAs
         */
        ArrayList<PLA> oneOutPLAs = PLAOps.getOneOutputPLAs(originalPLA);

        /**
         * Auxiliar ESPRESSO PLA list
         */
        ArrayList<PLA> espressoPLAs = new ArrayList<>();

        /**
         * iterate over one output PLAs
         */
        for(PLA p : oneOutPLAs) {
            //System.out.println(p);
            //for (Term t : p.getTerms()) {
            //    System.out.println(t);
            //}
            //p.checkAllPLATerms(false);
            //System.out.println("After CubeBreak");
            //for (Term t : p.getTerms()) {
            //    System.out.println(t);
            //}

            try {
                //System.out.println("ESPRESSO");
                PLA espressoPLA = PLAOps.getMinimizedPLA(p);
                //for(Term t : espressoPLA.getTerms()) {
                //  System.out.println(t);
                //}
                espressoPLAs.add(espressoPLA);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //String newName = originalPLA.getName() + "_approx_by_per_output_method";
        PLA resultPLA = PLAOps.joinPLAs(espressoPLAs, originalPLA.getName());

        return resultPLA;
    }

    public static PLA getApproxPLAWithMultipleDontCare(PLA originalPLA, ArrayList<String> inputVectors) {

        originalPLA.setName(originalPLA.getName() + "_mult_dontcare_approx");

        for (String v : inputVectors) {
            originalPLA.addDontCareTerm(v);
        }

        PLA result = null;

        try {
            result = PLAOps.getMinimizedPLA(originalPLA);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static ArrayList<PLA> getOneOutputPLAs(PLA pla) {
        ArrayList<PLA> result = new ArrayList<>();
        String[] outLabels = null;

        /**
         * handle in/out labels
         */
        if(pla.getOutputLabels() != null) {
            outLabels = pla.getOutputLabels().split(" ");
        }

        /**
         * Create a PLA per output
         */
        for(int i = 0; i < pla.getQtOutputs(); i++) {
            PLA fooPla = new PLA(String.format("%s-output-%03d", pla.getName(), i));

            if(outLabels != null) {
                fooPla.setInputLabels(pla.getInputLabels());
                fooPla.setOutputLabels(outLabels[i]);
            }

            fooPla.setQtInputs(pla.getQtInputs());
            fooPla.setQtOutputs(1);

            result.add(fooPla);
        }
        /**
         * Put product term on each output pla
         */
        for(Term t : pla.getTerms()) {

            char[] termOutput = t.getOutputs().toCharArray();

            for(int i = 0; i < termOutput.length; i++) {
                char output = termOutput[i];
                if(output != '0') {
                    result.get(i).addTerm(new Term(t.getInputs(), Character.toString(output)));
                }
                //if(output == '1') {
                //    result.get(i).addTerm(new Term(t.getInputs(), "1"));
                //}
            }
        }

        return result;
    }


    public static PLA getMinimizedPLA(PLA pla) throws IOException {

        String tempFolder = "TEMP/";
        String baseName = System.currentTimeMillis() + "-" + pla.getName();
        String tempPLA = tempFolder + baseName + ".pla";
        String tempEspresso = tempFolder + baseName + "-ESPRESSO.pla";

        //System.out.println("++++++++++++++++++++++");

        PLAOps.writePLA(tempPLA, pla);

        /**
         * Run ESPRESSO over tempPLA
         */
        try {
            String command = String.format("ESPRESSO %s %s", tempPLA, tempEspresso);
            ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh", command);
            //System.out.println(tempPLA);
            //System.out.println(tempEspresso);
            //System.out.println(command);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        /**
         * Read the minized PLA from ESPRESSO
         */
        PLA result = new PLAManipulator().readPLAFile(tempEspresso);

        /**
         * delete temp files
         */
        File tempPlaFile = new File(tempPLA);
        File tempEspressoFile = new File(tempEspresso);

        tempPlaFile.delete();

        if(!tempEspressoFile.delete()) {
            System.err.println("file not deleted!!");
        }

        return result;
    }

    public static PLA joinPLAs(ArrayList<PLA> plas,String joinedPlaName) {

        PLA joinedPLA = new PLA(joinedPlaName);

        if(!plas.isEmpty()) {
            /**
             * set input/output quantity
             */
            joinedPLA.setQtInputs(plas.get(0).getQtInputs());
            joinedPLA.setQtOutputs(plas.size());

            /**
             * add terms
             */
            int i = 0;
            for (PLA pla : plas) {
                String output = "0".repeat(plas.size());
                StringBuilder newOut = new StringBuilder(output);
                newOut.setCharAt(i, '1');
                for (Term t : pla.getTerms()) {
                    joinedPLA.addTerm(new Term(t.getInputs(), newOut.toString()));
                }
                i++;
            }

            /**
             * if exists, set in/out labels
             */
            if(plas.get(0).getOutputLabels() != null) {
                String outLabel = "";
                joinedPLA.setInputLabels(plas.get(0).getInputLabels());

                for (PLA pla : plas) {
                    outLabel = outLabel + pla.getOutputLabels() + " ";
                }
                joinedPLA.setOutputLabels(outLabel.replaceAll(".$", ""));
            }

            return joinedPLA;
        } else {
            System.out.println("PLA list is empty");
            return null;
        }

    }

    public static void writePLA(String pathname, PLA pla) throws IOException {

        File file = new File(pathname);
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        //Write comments
        for(String line : pla.getCommentLines()) {
            printWriter.printf("# %s", line);
        }
        printWriter.printf(".i %s\n",pla.getQtInputs());
        printWriter.printf(".o %s\n", pla.getQtOutputs());

        if(pla.getInputLabels() != null) {
            printWriter.printf(".ilb %s\n", pla.getInputLabels());
            printWriter.printf(".ob %s\n", pla.getOutputLabels());
        }
        printWriter.printf(".p %s\n", pla.getTotalTerms());

        pla.getTerms().forEach(term -> printWriter.printf("%s\n", term));

        printWriter.print(".e");
        printWriter.close();

    }

    public static PLA readPLAFile(String filepath) throws IOException {

        //this.terms.clear();

        Pattern inPat = Pattern.compile("\\.i ([0-9]+)");
        Pattern outPat = Pattern.compile("\\.o ([0-9]+)");
        Pattern inLabelPat = Pattern.compile("\\.ilb ");
        Pattern outLabelPat = Pattern.compile("\\.ob ");
        Pattern qtTermsPat = Pattern.compile("\\.p ([0-9]+)");

        Matcher matcher;

        BufferedReader br = null;
        File file = new File(filepath);

        PLA pla = new PLA(file.getName().split(".pla")[0]);


        br = new BufferedReader(new FileReader(file));

        String line;
        String[] prefixes = { "0", "1", "-" };

        while ((line = br.readLine()) != null) {

            /**
             * Match minterms
             */
            if(Stream.of(prefixes).anyMatch(line::startsWith)) {
                String[] term = line.split(" ");
                //terms.add(new Term(term[0], term[1]));
                pla.addTerm(new Term(term[0], term[1]));
                continue;
            }

            /**
             * Match to inputs quantity
             */
            matcher = inPat.matcher(line);
            if(matcher.find()) {
                //this.qtInputs = Integer.valueOf(matcher.group(1));
                pla.setQtInputs(Integer.valueOf(matcher.group(1)));
                continue;
            }

            /**
             * Match to outputs quantity
             */
            matcher = outPat.matcher(line);
            if(matcher.find()) {
                //this.qtOutputs = Integer.valueOf(matcher.group(1));
                pla.setQtOutputs(Integer.valueOf(matcher.group(1)));
                continue;
            }

            /**
             * Match to input labels
             */
            matcher = inLabelPat.matcher(line);
            if(matcher.find()) {
                String inLabels = line.split(" ", 2)[1];
                //this.inputsLabels = inLabels;
                pla.setInputLabels(inLabels);
                continue;
            }

            /**
             * Match to output labels
             */
            matcher = outLabelPat.matcher(line);
            if(matcher.find()) {
                String outLabels = line.split(" ", 2)[1];
                //this.outputsLabels = outLabels;
                pla.setOutputLabels(outLabels);
                continue;
            }


            //Match to terms (.p)
            /*matcher = qtTermsPat.matcher(line);
            if(matcher.find()) {
                this.qtTerms = Integer.valueOf(matcher.group(1));
                continue;
            }*/

        }

        /**
         * close buffreader
         */
        br.close();

        /*pla.setQtInputs(this.qtInputs);
        pla.setQtOutputs(this.qtOutputs);
        pla.setInputLabels(this.inputsLabels);
        pla.setOutputLabels(this.outputsLabels);*/

        //terms.forEach(term -> pla.addTerm(term));

        return pla;
    }

}
