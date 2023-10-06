package twoLevelDatastructures;

import ops.ShellScriptOps;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class PLAOps {
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

        System.out.println(pla.getInputLabels());
        System.out.println(pla.getOutputLabels());

        /**
         * Put product term on each output pla
         */
        for(Term t : pla.getTerms()) {

            char[] termOutput = t.getOutputs().toCharArray();

            for(int i = 0; i < termOutput.length; i++) {
                char output = termOutput[i];
                if(output == '1') {
                    result.get(i).addTerm(new Term(t.getInputs(), "1"));
                }
            }
        }

        return result;
    }


    public static PLA getMinimizedPLA(PLA pla) throws IOException {

        String tempFolder = "TEMP/";
        String baseName = System.currentTimeMillis() + "-" + pla.getName();
        String tempPLA = tempFolder + baseName + ".pla";
        String tempEspresso = tempFolder + baseName + "-ESPRESSO.pla";

        System.out.println("++++++++++++++++++++++");

        PLAOps.writePLA(tempPLA, pla);

        /**
         * Run ESPRESSO over tempPLA
         */
        try {
            String command = String.format("ESPRESSO %s %s", tempPLA, tempEspresso);
            ShellScriptOps.executeCommands("/media/sf_PastaUbuntuServer/ShellScripting/plaToESPRESSO.sh", command);
            System.out.println(tempPLA);
            System.out.println(tempEspresso);
            System.out.println(command);
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

        if(tempEspressoFile.delete()) {
            System.out.println("arquivo deletetado");
        } else {
            System.out.println("arquivo não deletetado!!");
            System.out.println(tempEspresso);
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

}
