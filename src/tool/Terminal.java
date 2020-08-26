
package tool;

import datastructures.CellLibrary;
import datastructures.Circuit;
import java.awt.AWTException;
import java.awt.Font;
import java.awt.Robot;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

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
import readers.MappedVerilogReader;
import signalProbability.ProbCircuit;

//TODO: Keep a global StringBuilder to decrease memory footprint

class TestTerminal {
    public static void main(String[] args) throws ScriptException, AWTException {   
        if(args.length > 0) {
            
            CommandLine cmdLine = new CommandLine(args);
            //<genlib_file> <verilog_file> <reliability (ex:0.9)>
            
           // Terminal term = Terminal.getInstance();
            //term.executeCommand("read_genlib " + args[0]);
            //term.executeCommand("read_verilog " + args[1]);
            //term.executeCommand("spr_float " + args[2]);
            
            
        } else {
            Terminal term = Terminal.getInstance();        
            term.open(0, 0, 700, 700);
            
            // FOR DEV
            Robot r = new Robot();
            term.executeCommand("read_genlib cadence.genlib");
            /*Commite antes da reunião */

            
            //term.executeCommand("read_genlib cadence_schiv.genlib");
            //term.executeCommand("read_genlib 22-1.genlib");
            //term.executeCommand("read_verilog c17_cadence.v");
            //term.executeCommand("read_verilog c17v3_fritz.v");
            //term.executeCommand("read_verilog c432_cadence.v");
            //term.executeCommand("read_verilog c20_cadence.v");
            //term.executeCommand("read_verilog c6288_cadence.v");
            //term.executeCommand("read_verilog twoInvOneAnd.v");
            //term.executeCommand("init_level");

            //term.executeCommand("clear");
            r.keyPress(KeyEvent.VK_ENTER);
            r.keyRelease(KeyEvent.VK_ENTER);
            // ##########
        }        
    }
}

public class Terminal {
    private final JFrame frm = new JFrame("GSDE - Circuit Benchmarker App");
    private final JTextArea txtArea = new JTextArea();
    //private final JEditorPane txtArea = new JEditorPane("text/html", "");
    private final JScrollPane scrollPane = new JScrollPane();
    private final CommandProcessor processor = CommandProcessor.getInstance();
    private final String LINE_SEPARATOR = System.lineSeparator();
    private final Font font = new Font("SansSerif", Font.BOLD, 15);
    
    private CellLibrary cellLibrary;
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
        txtArea.setText(txtArea.getText() + "GSDE_CBA> ");        
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

    public void executeCommand(String command) throws ScriptException {
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
