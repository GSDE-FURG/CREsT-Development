package twoLevelDatastructures;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class PLAManipulator {

    private String name;
    private int qtInputs;
    private int qtOutputs;
    private String inputsLabels;
    private String outputsLabels;
    private int qtTerms;
    private ArrayList<Term> terms = new ArrayList<>();
    public PLAManipulator() {

    }

    public PLA readPLAFile(String filepath) throws IOException {

        this.terms.clear();

        Pattern inPat = Pattern.compile("\\.i ([0-9]+)");
        Pattern outPat = Pattern.compile("\\.o ([0-9]+)");
        Pattern inLabelPat = Pattern.compile("\\.ilb ");
        Pattern outLabelPat = Pattern.compile("\\.ob ");
        Pattern qtTermsPat = Pattern.compile("\\.p ([0-9]+)");

        Matcher matcher;

        BufferedReader br = null;
        File file = new File(filepath);

        PLA pla = new PLA(file.getName().split(".pla")[0]);

        //System.out.println("Mamae ==> " + file.getName().split(".pla")[0]);

        br = new BufferedReader(new FileReader(file));

        String line;
        String[] prefixes = { "0", "1", "-" };

        while ((line = br.readLine()) != null) {

            /**
             * Match minterms
             */
            if(Stream.of(prefixes).anyMatch(line::startsWith)) {
                String[] term = line.split(" ");
                terms.add(new Term(term[0], term[1]));
                continue;
            }

            /**
             * Match to inputs quantity
             */
            matcher = inPat.matcher(line);
            if(matcher.find()) {
                this.qtInputs = Integer.valueOf(matcher.group(1));
                continue;
            }

            /**
             * Match to outputs quantity
             */
            matcher = outPat.matcher(line);
            if(matcher.find()) {
                this.qtOutputs = Integer.valueOf(matcher.group(1));
                continue;
            }

            /**
             * Match to input labels
             */
            matcher = inLabelPat.matcher(line);
            if(matcher.find()) {
                String inLabels = line.split(" ", 2)[1];
                this.inputsLabels = inLabels;
                continue;
            }

            /**
             * Match to output labels
             */
            matcher = outLabelPat.matcher(line);
            if(matcher.find()) {
                String outLabels = line.split(" ", 2)[1];
                this.outputsLabels = outLabels;
                continue;
            }

            /**
             * Match to terms (.p)
             */
            matcher = qtTermsPat.matcher(line);
            if(matcher.find()) {
                this.qtTerms = Integer.valueOf(matcher.group(1));
                continue;
            }

        }

        pla.setQtInputs(this.qtInputs);
        pla.setQtOutputs(this.qtOutputs);
        pla.setInputLabels(this.inputsLabels);
        pla.setOutputLabels(this.outputsLabels);

        terms.forEach(term -> pla.addTerm(term));

        return pla;
    }

    public void writePLA(String pathname, PLA pla) throws IOException {

        File file = new File(pathname);
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.printf(".i %s\n",pla.getQtInputs());
        printWriter.printf(".o %s\n", pla.getQtOutputs());
        printWriter.printf(".ilb %s\n", pla.getInputLabels());
        printWriter.printf(".ob %s\n", pla.getOutputLabels());
        printWriter.printf(".p %s\n", pla.getTotalTerms());

        pla.getTerms().forEach(term -> printWriter.printf("%s\n", term));

        printWriter.print(".e");
        printWriter.close();

    }

}
