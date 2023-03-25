
package tool;

import datastructures.CellLibrary;
import datastructures.Circuit;
import datastructures.CustomMatrixLibrary;
import datastructures.InputVector;
import java.awt.AWTException;
import java.awt.Font;
import java.awt.Robot;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptException;

import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import levelDatastructures.LevelCircuit;
import ops.SPROpsChuloMedio;
import readers.MappedVerilogReader;
import signalProbability.ProbCircuit;

import org.apache.commons.cli.*;

//TODO: Keep a global StringBuilder to decrease memory footprint

class
TerminalWrapper {
    public static void main(String[] args) throws
            ScriptException, AWTException, IOException, Exception {   
        if(args.length > 0) {
          /**
            * Terminal usado no trabalho de sistemas evolutivos
            */
             /* Clayton */
            //System.out.println("____>  " + args.toString());
                Options options = new Options();

                Option indataset = new Option("id", "indataset", true, "input dataset path");
                indataset.setRequired(false);
                options.addOption(indataset);


                Option output = new Option("o", "output", true, "output file name");
                output.setRequired(false);
                options.addOption(output);

                Option inputvector = new Option("iv", "invector", true, "input vector (big)int format");
                inputvector.setRequired(false);
                options.addOption(inputvector);

                Option range = new Option("rg", "range", true, "range for input vector (big)int format");
                range.setRequired(false);
                options.addOption(range);

                Option help = new Option("h", "help", false, "show this message");
                help.setRequired(false);
                options.addOption(help);

                Option mc = new Option("mc", "mtf", true, "Monte carlo fault injection");
                mc.setDescription("This method simulates the fault injection campaign (MC) " +
                        "\n - Monte Carlo (MC) provides reduced test simulation and statistical confidence interval for resonable sample size\n" +
                        " - Command: -mc_fault_injection <stdcell.genlig> <circuit.v> -mc <sample> <Proportion Single> <Proportion Double> ... <Proportion Triple> " +
                        "\n - Usage example (90% Single faults, 10% Double faults) the pergentage must be lower or equal to 1 (100%), above values will not be performed: -mc 20000 0.9 0.1");
                mc.setRequired(false);

                Option exhaustive = new Option("exhaustive", "exh", false, "Monte carlo fault injection");
                exhaustive.setDescription("This method simulates the fault injection campaign (Exhaustive method) " +
                        "- For Single Event Transient (SET) - Command: -mc_fault_injection <stdcell.genlig> <circuit.v> -exhaustive\n" +
                        "- For Multiple Event Transient (MET) * Note: This method consider (from 1 to signals size combination, in order to proceed the complete fault injection simulation " +
                                "- Command: -mc_fault_injection <stdcell.genlig> <circuit.v> -exhaustive -complete");

                exhaustive.setRequired(false);

                Option read_scripts = new Option("read_script", "rs", true, "Read script to perform Monte Carlo Fault injection in batch mode");
                read_scripts.setDescription("This method simulates the fault injection campaign (MC) " + "in reading a script. The script example is present in 'scripts/read_script_example.txt'");
                read_scripts.setRequired(false);


                Option xmx= new Option("Xmx4G", "", false, "Read script to perform Monte Carlo Fault injection in batch mode");
                xmx.setDescription("Use -Xmx to specify the max Java heap size");
                xmx.setRequired(false);

                Option xms= new Option("Xms2G", "", false, "Read script to perform Monte Carlo Fault injection in batch mode");
                xms.setDescription("Use -Xmx to specify the initial Java heap size");
                xms.setRequired(false);


               Option exhaustiveOp = new Option("complete", "complete", false, "Monte carlo fault injection");
                exhaustiveOp.setDescription("This method simulates the fault injection campaign (Exhaustive complete method) \n" + "- For Multiple Event Transient (MET) * Note: This method consider (from 1 to signals size combination, in order to proceed the complete fault injection simulation " +
                "- Command: -mc_fault_injection <stdcell.genlig> <circuit.v> -exhaustive -complete");
               exhaustiveOp.setRequired(false);


                options.addOption(mc);
                options.addOption(exhaustive);
                options.addOption(exhaustiveOp);
                options.addOption(read_scripts);
                options.addOption(xms);
                options.addOption(xmx);

                CommandLineParser parser = new DefaultParser();
                HelpFormatter formatter = new HelpFormatter();
                CommandLine cmd;

                Terminal terminal = Terminal.getInstance();

            System.out.println("PROCESS...");
                try {
                        cmd = parser.parse(options, args);
                        System.out.println(parser);
                        //System.out.println(mc.getValue());
                        if (cmd.hasOption("help")) {
                            formatter.printHelp("VetoresCriticos", options);
                            System.exit(1);
                        }
                        if(cmd.hasOption("indataset") || cmd.hasOption("output")  || cmd.hasOption("invector")){
                            String inputDatasetPath = cmd.getOptionValue("indataset");
                            String outputFilePath = cmd.getOptionValue("output");
                            String inVector = cmd.getOptionValue("invector");

                            ProbCircuit pCircuit = initLibraryAndCircuit();

                            ArrayList<String> vectorsAndReliabilities = new ArrayList<>();

                            if (cmd.hasOption("indataset")) {
                                vectorsAndReliabilities = getReliabilitiesFromDataset(pCircuit, inputDatasetPath);
                            } else {
                                if (cmd.hasOption("range") && (cmd.hasOption("invector"))) {
                                    int rangeValue = Integer.parseInt(cmd.getOptionValue("range"));
                                    BigInteger v = new BigInteger(inVector);
                                    for (int i = 0; i < rangeValue; i++) {
                                        vectorsAndReliabilities.add(getSPRReliabilityVector(pCircuit, v));
                                        v = v.add(BigInteger.ONE);
                                    }
                                } else {
                                    if (cmd.hasOption("invector")) {
                                        vectorsAndReliabilities.add(getSPRReliabilityVector(pCircuit, new BigInteger(inVector)));
                                    }
                                }
                            }


                            if (!vectorsAndReliabilities.isEmpty()) {
                                if (cmd.hasOption("output")) {
                                    writeResult(outputFilePath, vectorsAndReliabilities);
                                } else {
                                    for (String vectorsAndReliability : vectorsAndReliabilities) {
                                        System.out.println(vectorsAndReliability);
                                    }
                                }
                            }
                        }

                    } catch (ParseException e) {
                        System.out.println(e.getMessage());
                        formatter.printHelp("VetoresCriticos", options);

                        System.exit(1);
                    }

                try {                                       // Procedure for Single and Multiple Fault Injection (Monte Carlo procedure)
                    cmd = parser.parse(options, args);
                    if (cmd.hasOption("exhaustive") || cmd.hasOption("complete")) {
                        int args_size = args.length;

                        String genlib = args[1];
                        String circuit = args[2];
                        String command = args[3];

                        if (args_size == 4) { // Define Single Faults
                            System.out.println("- Exhaustive Simulation Single Event Transient (SET) -exaustive: ");
                            terminal.executeCommand("mc_fault_injection" + " " + genlib + " " + circuit + " " + command);
                        } else {
                            String command_op;
                            command_op = args[4];
                            System.out.println("- Exahustive Simulation Multiple Event Transient (SET) -exaustive -complete: " + Arrays.toString(args));
                            terminal.executeCommand("mc_fault_injection" + " " + genlib + " " + circuit + " " + command + " " + command_op);
                        }

                    }

                    if (cmd.hasOption("mc")) {
                        int args_size = args.length;

                        String option = "";
                        String genlib = args[1];
                        String circuit = args[2];
                        String command = args[3];
                        String sampleSTF = args[4];

                        if (args_size == 5) { // Define Single Faults  [-mc_fault_injection teste/cadence.genlib teste/c.v -mc 20000]
                            option = "Single";
                        } else {
                            option = "Multiple";  // Define Multiple Faults [-mc_fault_injection teste/cadence.genlib teste/c.v -mc 20000 1 1 1]
                        }

                        switch (option) {

                            case ("Single"):
                                System.out.println("Single Event Transient - MC");
                                terminal.executeCommand("mc_fault_injection" + " " + genlib + " " + circuit + " " + command + " " + sampleSTF);
                                break;

                            case ("Multiple"):
                                ArrayList<String> x = new ArrayList<>();
                                String arguments_compiled = "";
                                for (int i = 4; i < args.length; i++) {
                                    x.add(args[i]);
                                    if (i <= 4) {
                                        arguments_compiled = args[i];
                                    } else {

                                        arguments_compiled = arguments_compiled + " " + args[i];
                                    }
                                }
                                System.out.println("- Multiple Event Transient (SET) -mcmtf: " + arguments_compiled);
                                terminal.executeCommand("mc_fault_injection" + " " + genlib + " " + circuit + " " + command + " " + arguments_compiled);
                                break;
                        }

                    }

                    if (cmd.hasOption("read_script")){

                        System.out.println("SCRIPT READING ... " + cmd.getArgs().toString());

                        String script_file = "";

                        for (int i = 0; i < args.length; i++) {
                            if(!args[i].contains("-Xmx")){
                                script_file = args[i];
                            }
                        }

                        terminal.executeCommand("read_script" + " " + script_file);


                    }

                }catch (Exception e){
                    System.out.println("Error : " + e);
                }


                }

         else {
            Terminal term = Terminal.getInstance();        
            term.open(0, 0, 820, 700);
            
            // FOR DEV
            
            Robot r = new Robot();


            //term.executeCommand("read_genlib minimal.genlib");
            //term.executeCommand("read_verilog c17_hard_approx.v"); c17_approx_A.v
            //term.executeCommand("read_verilog c17_a.v");
            //term.executeCommand("read_verilog c17_approx_A.v");
            //term.executeCommand("read_verilog c17_approx_B.v");
            //
            // term.executeCommand("read_custom_matrix 45nm.txt");
            
           
            //term.executeCommand("read_genlib lib_full_no_cost.genlib");
            //term.executeCommand("read_custom_matrix 45nm.txt");
            /*Commite antes da reunião */
            //term.executeCommand("read_genlib cadence_schiv.genlib");
            //term.executeCommand("read_genlib abc/22-1.genli");
            //term.executeCommand("read_verilog c17_cadence.v");
            //term.executeCommand("read_verilog /abc/c17v3_fritz.v");
            //term.executeCommand("read_verilog c432_cadence.v");
            //term.executeCommand("read_verilog c20_cadence.v");
            //term.executeCommand("read_verilog c6288_cadence.v");
            //term.executeCommand("read_verilog twoInvOneAnd.v");
            //term.executeCommand("init_level");
             //term.executeCommand("clear");
           // mc_fault_injection abc/c17v3_fritz.v abc/cadence.genlib
           // term.executeCommand("help");
            //term.executeCommand("spr");
            term.executeCommand("clear");
            
            r.keyPress(KeyEvent.VK_ENTER);
            r.keyRelease(KeyEvent.VK_ENTER);
            term.executeCommand("clear");
            
            //System.out.println(term.getCircuit());
            // ##########
            
            // term.executeCommand("clear");
            //r.keyPress(KeyEvent.VK_ENTER);
            //r.keyRelease(KeyEvent.VK_ENTER);
            
        }        
    }
    /**
     * MÉTODOS USADOS NO TRABALHO DE SISTEMAS EVOLUTIVOS 2020
     */
    private static String getSPRReliabilityVector(ProbCircuit pCircuit, BigInteger vector) {        
        InputVector inputV = new InputVector(vector);
        String result = "" + vector + ";" + SPROpsChuloMedio.getSPRReliability(pCircuit, inputV, 30);
        
        return result;
    }
    
    private static ArrayList<String> getReliabilitiesFromDataset(ProbCircuit pCircuit, String path) throws IOException {
        
        ArrayList<String> vectorsAndReliabilities = new ArrayList<>();
        String resultReliability;
        
        for (String str : readVectorsList(path)) {
                    resultReliability = SPROpsChuloMedio.getSPRReliability(pCircuit, new InputVector(new BigInteger(str)), 30).toString();

                    vectorsAndReliabilities.add("" + str + ";" + resultReliability);
                }
        
        return vectorsAndReliabilities;
    }
    
    private static ProbCircuit initLibraryAndCircuit() throws IOException, ScriptException, Exception {
        
        CellLibrary library = new CellLibrary("./cadence.genlib");
        
        // Set reliability value
        library.setPTMCells(new BigDecimal("0.999"));
        
        Circuit circuit = new MappedVerilogReader("./b10_C_cadence.v", library).getCircuit();
        
        ProbCircuit pCircuit = new ProbCircuit(circuit);

        pCircuit.clearProbSignalsMatrix();                    
        pCircuit.setDefaultProbSourceSignalMatrix();
        pCircuit.setProbSignalStates(false);
        pCircuit.setPTMReliabilityMatrix();
        
        return pCircuit;
    }
    
    private static ArrayList<String> readVectorsList(String path) throws FileNotFoundException, IOException {
        
        ArrayList<String> result = new ArrayList<>();

        BufferedReader br;
        String linha;
        br = new BufferedReader(new FileReader(path));
        while ((linha = br.readLine()) != null) {
            result.add(linha);
        }
        br.close();
        return result;
    }
    
    
    private static void writeResult(String outputPath, ArrayList<String> dataset) {
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                  new FileOutputStream(outputPath), "utf-8"));
            
            writer.write("Vetor;Confiabilidade" + "\n");
            for (String string : dataset) {
                writer.write(string + "\n");
            }                        
            writer.close();
        } catch (IOException ex) {
        } finally {
           try {writer.close();} catch (Exception ex) {/*ignore*/}
        }
    }

}





public class
Terminal {
    private final JFrame frm = new JFrame("CREsT - Circuit Reliability Estimation Tool");
    private final JTextArea txtArea = new JTextArea();
    //private final JEditorPane txtArea = new JEditorPane("text/html", "");
    private final JScrollPane scrollPane = new JScrollPane();
    private final CommandProcessor processor = CommandProcessor.getInstance();
    private final String LINE_SEPARATOR = System.lineSeparator();
    private final Font font = new Font("SansSerif", Font.BOLD, 15);
    
    private CellLibrary cellLibrary;
    private CustomMatrixLibrary CustomMatrixLib;
    private Circuit circuit;
    private LevelCircuit levelCircuit;
    private ProbCircuit probCircuit;

    private Terminal() {
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.getContentPane().add(scrollPane);
        scrollPane.setViewportView(txtArea);
        txtArea.addKeyListener(new KeyListener());
        txtArea.setFont(font);
        disableArrowKeys(txtArea.getInputMap());        
        cellLibrary = new CellLibrary();
        
    }

    private void disableArrowKeys(InputMap inputMap) {
        String[] keystrokeNames = { "UP", "DOWN", "LEFT", "RIGHT", "HOME" };
        for (int i = 0; i < keystrokeNames.length; ++i)
            inputMap.put(KeyStroke.getKeyStroke(keystrokeNames[i]), "none");
    }

    public void open(final int xLocation, final int yLocation, final int width,
            final int height) {
        SwingUtilities.invokeLater(() -> {
            try {
                frm.setBounds(xLocation, yLocation, width, height);
                frm.setVisible(true);
                showPrompt();
                Robot r = new Robot();
                // Hack para iniciar o prompt
                clear();
                r.keyPress(KeyEvent.VK_ENTER);
                r.keyRelease(KeyEvent.VK_ENTER);
                // ****************
                //
                txtArea.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent event) {



                        txtArea.setCaretPosition(txtArea.getDocument().getLength()-1);
                    }
                });
            } catch (AWTException ex) {
                Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public CellLibrary getCellLibrary() {
        return cellLibrary;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public void initLibrary(String path) throws ScriptException, IOException {
        this.cellLibrary.initLibrary(path);
    }
    
    public void initCircuit (String path) throws ScriptException, IOException, Exception {
        
        if(Terminal.getInstance().getCellLibrary().getCells() == null) {
            Terminal.getInstance().terminalOutput("Please read a library first!!!");
        }
        else {
            Terminal.getInstance().setCircuit(new MappedVerilogReader(path, Terminal.getInstance().getCellLibrary()).getCircuit());
        }
    }

    public LevelCircuit getLevelCircuit() {
        return levelCircuit;
    }

    public CustomMatrixLibrary getCustomMatrixLib() {
        return CustomMatrixLib;
    }

    public void setCustomMatrixLib(CustomMatrixLibrary CustomMatrixLib) {
        this.CustomMatrixLib = CustomMatrixLib;
    }
    
    
    
    public void initLevelCircuit() {
        if(this.circuit != null) {
            this.levelCircuit = new LevelCircuit(circuit);      
        } else {
            Terminal.getInstance().terminalOutput("Circuit is null!!");
        }
    }
    
    public ProbCircuit getProbCircuit() {
        return probCircuit;
    }
        
    public void initProbCircuit() {
        if(this.circuit != null) {
            LevelCircuit lCircuit = Terminal.getInstance().getLevelCircuit();
            this.probCircuit = new ProbCircuit(Terminal.getInstance().getCircuit());
            
        } else {
            Terminal.getInstance().terminalOutput("Circuit is null!!");
        }
    }
    

    public void close() {
        frm.dispose();
    }

    public void clear() {        
        txtArea.setText(null);
    }
    
    public void terminalOutput(String out) {
        showNewLine();
        txtArea.setText(txtArea.getText() + out);         
    }
    
    public void doNothing() {        
        txtArea.setText(txtArea.getText());         
    }

    private void showPrompt() {
        txtArea.setText(txtArea.getText() + "CREsT> ");
    }

    private void showNewLine() {
        if(!txtArea.getText().equals("")) {
            txtArea.setText(txtArea.getText() + LINE_SEPARATOR);
        }
        
    }

    private class KeyListener extends KeyAdapter {
        private final int ENTER_KEY = KeyEvent.VK_ENTER;
        private final int BACK_SPACE_KEY = KeyEvent.VK_BACK_SPACE;
        private final String BACK_SPACE_KEY_BINDING = getKeyBinding(
                txtArea.getInputMap(), "BACK_SPACE");
        private final int INITIAL_CURSOR_POSITION = 2;

        private boolean isKeysDisabled;
        private int minCursorPosition = INITIAL_CURSOR_POSITION;

        private String getKeyBinding(InputMap inputMap, String name) {
            return (String) inputMap.get(KeyStroke.getKeyStroke(name));
        }

        @Override
        public void keyPressed(KeyEvent evt) {
            int keyCode = evt.getKeyCode();
            if (keyCode == BACK_SPACE_KEY) {
                int cursorPosition = txtArea.getCaretPosition();
                if (cursorPosition == minCursorPosition && !isKeysDisabled) {
                    disableBackspaceKey();
                } else if (cursorPosition > minCursorPosition && isKeysDisabled) {
                    enableBackspaceKey();
                }
            } else if (keyCode == ENTER_KEY) {
                disableTerminal();                
                String command = extractCommand();                
                try {                
                    executeCommand(command);
                } catch (ScriptException ex) {
                    Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
                }
                showNewLine();
                showPrompt();
                enableTerminal();
            }
        }

        @Override
        public void keyReleased(KeyEvent evt) {
            int keyCode = evt.getKeyCode();
            if (keyCode == ENTER_KEY) {
                txtArea.setCaretPosition(txtArea.getCaretPosition() - 1);
                setMinCursorPosition();
            }
        }

        private void disableBackspaceKey() {
            isKeysDisabled = true;
            txtArea.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"),
                    "none");
        }

        private void enableBackspaceKey() {
            isKeysDisabled = false;
            txtArea.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"),
                    BACK_SPACE_KEY_BINDING);
        }

        private void setMinCursorPosition() {
            minCursorPosition = txtArea.getCaretPosition();
        }
        
        
    }
    
        

    public void enableTerminal() {
        txtArea.setEnabled(true);
    }

    public void disableTerminal() {
        txtArea.setEnabled(false);
    }

    public void executeCommand(String command) throws ScriptException, IOException, Exception {
        processor.processCmd(command);
    }

    private String extractCommand() {
        
        removeLastLineSeparator();
        String newCommand = stripPreviousCommands();
        return newCommand;
    }

    private void removeLastLineSeparator() {
        String terminalText = txtArea.getText();
        if (txtArea.getLineCount() == 1) {        
            terminalText = terminalText.substring(0, terminalText.length());
        }
        else {
            terminalText = terminalText.substring(0, terminalText.length()-1);
        }
        txtArea.setText(terminalText);
    }

    private String stripPreviousCommands() {
        String terminalText = txtArea.getText();
        int lastPromptIndex = terminalText.lastIndexOf('>') + 2;
        if (lastPromptIndex < 0 || lastPromptIndex >= terminalText.length())
            return "";
        else
            return terminalText.substring(lastPromptIndex);
    }

    public static Terminal getInstance() {
        return TerminalHolder.INSTANCE;
    }

    private static final class TerminalHolder {
        static final Terminal INSTANCE = new Terminal();
    }        
}
