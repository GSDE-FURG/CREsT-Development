/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;
import javax.script.ScriptException;

/**
 *
 * @author matheus
 */
class CommandProcessor {
    public CommandProcessor() {
    }
    
    class TimeoutTimer extends TimerTask {
    public void run() {                
        Terminal.getInstance().terminalOutput("Catita...");
        System.out.println("Catita...");

    }
}

    public void processCmd(String command) throws ScriptException, IOException, Exception {
        
        //String[] splittedCommand = command.split(" ");        
        ArrayList<String> splittedCommand = new ArrayList(Arrays.asList(command.split(" ")));
        
        String mainCommand = splittedCommand.get(0);
        String argument = "";
        String argument2 = "";
        String filename = "";
        String exportArg = "fafasffasfgghh";
        String exportPattern = "--export=";
        
        boolean help = false;
        //boolean export = false;
        Commands cmd = new Commands();
        
        
        for (int i = 0; i < splittedCommand.size(); i++) {
            String str = splittedCommand.get(i);
            if(str.matches(exportPattern+"(.*)")) {
                exportArg = str;
                filename = str.replaceFirst(exportPattern, "");
                //System.out.println(filename);
            }
        }
        
        if(splittedCommand.contains(exportArg) && splittedCommand.get(0) != exportArg) {
                splittedCommand.remove(exportArg);
        }
        
        if(splittedCommand.size() > 1) {
            
            argument = splittedCommand.get(1);
            if(splittedCommand.size() > 2) {
                argument2 = splittedCommand.get(2);
            }       
        }
        
        if(argument.equals("--help")) {
            help = true;
        }
        
        
        switch(mainCommand) {
            
            case "":
                Terminal.getInstance().doNothing();
                break;
                
            case "clear":
                if(argument.isEmpty()) {
                    Terminal.getInstance().clear();
                } else if(help) {
                    Terminal.getInstance().terminalOutput(mainCommand + ": "+cmd.getHelpDesc(mainCommand));
                } else {
                    Terminal.getInstance().terminalOutput("Please use the arg \"--help\" or just \"clear\"!!!");
                }
                break;
            /* Marcio */
            case "create-subcircuit":
                if (argument.isEmpty()) {
                    cmd.createSubCircuits();
                } else if (help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                }
                break;

            case "print_wrv":
                if (argument.isEmpty()) {
                    Terminal.getInstance().terminalOutput("Please use the arg \"--help\"");
                } else if (help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                } else {
                    cmd.getWorstReliabilityVector(argument);
                }
                break;

            case "print_ordered_gates":
                if (argument.isEmpty()) {
                    Terminal.getInstance().terminalOutput("Please use the arg \"--help\"");
                } else if (help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                } else {
                    cmd.getOrderedGates(argument, argument2);
                }
                break;

            case "execute_score_count":
                if (argument.isEmpty()) {
                    cmd.executeScoreCount();
                } else if (help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                }
                break;

            case "print_cost_area":
                if (argument.isEmpty()) {
                    Terminal.getInstance().terminalOutput("Please use the arg \"--help\"");
                } else if (help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                } else {
                    cmd.getAreaCostWithTMR(argument, argument2);
                }
                break;

            case "print_improvement_gates":
                if (argument.isEmpty()) {
                    Terminal.getInstance().terminalOutput("Please use the arg \"--help\"");
                } else if (help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                } else {
                    cmd.getReliabilityByImprovementGate(argument, argument2);
                }
                break;

            /* ---- */
            
            case "get_sonf_reliability":
                if(argument.isEmpty()) {
                    Terminal.getInstance().terminalOutput("Please use the arg \"--help\"");                    
                } else if(help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                } else {
                    //double prob = Double.parseDouble(argument);
                    cmd.getReliabilitySONF(argument);                    
                }
                break;
                
            case "help":
                if(argument.isEmpty()) {
                    cmd.Help();
                } else {
                    Terminal.getInstance().terminalOutput("Please use just \"help\"!!!");
                }     
                break;
              
            case "print_gates":
                if(argument.isEmpty()) {
                    cmd.PrintCircuitGates();
                } else if(help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                } else {
                    Terminal.getInstance().terminalOutput("Please use the arg \"--help\" or just \"print_gates\"!!!");
                }
                break;
                
            case "print_gates_level":                
                if(argument.isEmpty()) {
                    cmd.PrintCircuitGatesByLevel(false, filename);
                } else if(help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                    Terminal.getInstance().terminalOutput("usage: print_gates_level <level number> or \"--help\"");
                } else if(argument.equals("--with_wires")) {
                    cmd.PrintCircuitGatesByLevel(true, filename);
                } 
                else {
                    try {
                        int level = Integer.valueOf(splittedCommand.get(1));
                        if(argument2.equals("--with_wires")) {
                            cmd.PrintCircuitGatesByLevel(level, true, filename);
                        } else if(argument2.isEmpty()) {
                            cmd.PrintCircuitGatesByLevel(level, false, filename);
                        } else {
                            Terminal.getInstance().terminalOutput("Bad Argument <" + argument2 + ">");
                        }
                        
                    } catch (NumberFormatException e) {
                        Terminal.getInstance().terminalOutput("Bad Argument <" + argument + ">");
                    }                                        
                }
                break;
                
            case "print_gatelevels":
                if(argument.isEmpty()) {
                    cmd.PrintGateLevels();
                } else if(help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                } else {
                    Terminal.getInstance().terminalOutput("Please use the arg \"--help\" or just \"print_gatelevels\"!!!");
                }
                break;
                
            case "print_genlib":                
                if(argument.isEmpty()) {
                    cmd.PrintGenlib();
                } else if(help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                } else {
                    Terminal.getInstance().terminalOutput("Please use the arg \"--help\" or just \"print_genlib\"!!!");
                }
                break;
                
            case "print_signals":                
                if(argument.isEmpty()) {
                    cmd.PrintCircuitSignals();
                } else if(help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                } else {
                    Terminal.getInstance().terminalOutput("Please use the arg \"--help\" or just \"print_signals\"!!!");
                }
                break;
                
            case "print_types":
                if(argument.isEmpty()) {
                    for (int i = 0; i < Terminal.getInstance().getCellLibrary().getCells().size();i++) {
                        Terminal.getInstance().terminalOutput(Terminal.getInstance().getCellLibrary().getCells().get(i).getName());
                    }
                } else if(help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                } else {
                    Terminal.getInstance().terminalOutput("Please use the arg \"--help\" or just \"print_types\"!!!");
                }             
                break;
                
            case "ptm_matrix_size":
                cmd.PtmMatrixSize(filename);
                break;
                
            case "ptm":
                if(!argument.isEmpty()) {
                    Terminal.getInstance().terminalOutput("Please use the arg \"--help\"");                    
                } else if(help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                } else {
                    cmd.getReliabilityPTM("0.99999802495", "default");                    
                }
                break;    
                
            case "ptm_big_decimal":
                if(argument.isEmpty()) {
                    Terminal.getInstance().terminalOutput("Please use the arg \"--help\"");                    
                } else if(help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                } else {
                    cmd.getReliabilityPTM(argument, "big_decimal");                    
                }
                break;
            
            case "ptm_double":
                if(argument.isEmpty()) {
                    Terminal.getInstance().terminalOutput("Please use the arg \"--help\"");                    
                } else if(help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                } else {
                    cmd.getReliabilityPTM(argument, "double");                    
                }
                break;
            
            case "ptm_float":
                if(argument.isEmpty()) {
                    Terminal.getInstance().terminalOutput("Please use the arg \"--help\"");                    
                } else if(help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                } else {
                    cmd.getReliabilityPTM(argument, "float");                    
                }
                break;
            
            case "spr":
                //Timer timer = new Timer();        
                //timer.schedule(new TimeoutTimer(), 0, 1000);
                //TimeUnit.SECONDS.sleep(5);
                //System.out.println("mamamae");
                if(argument.equals("--custom_lib")) {
                    cmd.getReliabilitySPR("0.99999802495", "custom_lib");
                } else if(!argument.isEmpty()) {
                    Terminal.getInstance().terminalOutput("Please use the arg \"--help\"");                    
                } else if(help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                } else {
                    cmd.getReliabilitySPR("0.99999802495", "big_decimal");                    
                }
                //timer.cancel();
                break;                
            
            case "spr-mp":
                if(argument.equals("--custom_lib")) {
                    cmd.getReliabilitySPRMP("0.99999802495", "custom_lib");
                } else if(!argument.isEmpty()) {
                    Terminal.getInstance().terminalOutput("Please use the arg \"--help\"");                    
                } else if(help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                } else {
                    cmd.getReliabilitySPRMP("0.99999802495", "big_decimal");                    
                }
                break;                
            
            case "spr_big_decimal":
                if(argument.isEmpty()) {
                    Terminal.getInstance().terminalOutput("Please use the arg \"--help\"");                    
                } else if(help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                } else {
                    cmd.getReliabilitySPR(argument, "big_decimal");                    
                }
                break;                
            
            case "spr_float":
                if(argument.isEmpty()) {
                    Terminal.getInstance().terminalOutput("Please use the arg \"--help\"");                    
                } else if(help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                } else {
                    cmd.getReliabilitySPR(argument, "float");                    
                }
                break;                
                
            case "quit":          
                if(argument.isEmpty()) {
                    Terminal.getInstance().close();
                } else if(help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                } else {
                    Terminal.getInstance().terminalOutput("Please use the arg \"--help\" or just \"quit\"!!!");
                }
                break;
                
            case "read_custom_matrix":                
                if(argument.isEmpty()) {
                    Terminal.getInstance().terminalOutput("usage: read_custom_matrix <filename> or \"--help\"");
                } else if(help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                    Terminal.getInstance().terminalOutput("usage: read_custom_matrix <filename> or \"--help\"");
                } else {
                    boolean success = false;
                    try {
                        cmd.ReadCustomMatrix(splittedCommand.get(1));
                        success = true;
                    } catch (IOException | ScriptException e) {                        
                        Terminal.getInstance().terminalOutput("## ERRO ##");
                        Terminal.getInstance().terminalOutput(e.getMessage());
                        Terminal.getInstance().terminalOutput("## ERRO ##");
                    } finally {
                        if(success) {
                            Terminal.getInstance().terminalOutput("File \"" + splittedCommand.get(1) + "\" successfully processed!!");
                        }
                    }
                }
                break;
                
            case "read_genlib":                
                if(argument.isEmpty()) {
                    Terminal.getInstance().terminalOutput("usage: read_genlib <filename.genlib> or \"--help\"");
                } else if(help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                    Terminal.getInstance().terminalOutput("usage: read_genlib <filename.genlib> or \"--help\"");
                } else {
                    boolean success = false;
                    try {
                        cmd.ReadGenlib(splittedCommand.get(1));
                        success = true;
                    } catch (IOException | ScriptException e) {                        
                        Terminal.getInstance().terminalOutput("## ERRO ##");
                        Terminal.getInstance().terminalOutput(e.getMessage());
                        Terminal.getInstance().terminalOutput("## ERRO ##");
                    } finally {
                        if(success) {
                            Terminal.getInstance().terminalOutput("File \"" + splittedCommand.get(1) + "\" successfully processed!!");
                        }
                    }
                }
                break;
                
            case "read_verilog":                
                
                if(argument.isEmpty()) {                    
                    Terminal.getInstance().terminalOutput("usage: read_verilog <filename.v> or \"--help\"");
                } else if(help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                    Terminal.getInstance().terminalOutput("usage: read_verilog <filename.v> or \"--help\"");
                } else {                    
                    boolean success = false;
                    try {
                        cmd.ReadVerilog(splittedCommand.get(1));
                        success = true;
                    } catch (IOException e) {                        
                        Terminal.getInstance().terminalOutput("## ERRO ##");
                        Terminal.getInstance().terminalOutput(e.getMessage());
                        Terminal.getInstance().terminalOutput("## ERRO ##");
                    } catch (Exception ex) {
                         Terminal.getInstance().terminalOutput("" + ex);
            } finally {
                        if(success) {
                            if (Terminal.getInstance().getCircuit() != null) {
                                Terminal.getInstance().terminalOutput("File \"" + splittedCommand.get(1) + "\" successfully processed!!");
                            }
                        }
                    }
                }
                break;
            
            case "report":
                cmd.Report();
                break;
                
            case "write_genlib":
                if(help) {
                    Terminal.getInstance().terminalOutput("usage: write_genlib <filename.genlib>");
                    Terminal.getInstance().terminalOutput("<b>write_genlib<b>");
                } else {
                    if (argument.isEmpty()) {
                        Terminal.getInstance().terminalOutput("Please type the filename!!!");
                    } else if(Terminal.getInstance().getCellLibrary() == null) {
                        Terminal.getInstance().terminalOutput("Library is null!!!!!");
                    } else {
                        new Commands().WriteGenlib(argument, Terminal.getInstance().getCellLibrary());
                    }
                }
                break;
                
            case "write_verilog":
                if (argument.isEmpty()) {
                    Terminal.getInstance().terminalOutput("Please type the filename!!!");
                } else if(Terminal.getInstance().getCircuit() == null) {
                    Terminal.getInstance().terminalOutput("Circuit is null!!!!!");
                } else {
                    new Commands().WriteVerilog(argument, Terminal.getInstance().getCircuit());
                }
                break;
            case "print__path":
                cmd.PrintPath();
                break;
            case "foo":

                cmd.Foo();
                break;

            /* Clayton */


            //case "mc":

            case "mc_fault_injection":
                /* Testing block  */

                System.out.println("Command: " + splittedCommand);

                /*
                splittedCommand.add(1, "teste/cadence.genlib");
                splittedCommand.add(2, "teste/c.v");
                splittedCommand.add(3, "-mcmf");
                splittedCommand.add(4, "10");
                splittedCommand.add(5, "2");
                splittedCommand.add(6, "1");

                System.out.println("Command: " + splittedCommand);

                argument = "-";

                *q/


                /* */

                System.out.println("- CommandProcessor : " + splittedCommand);

                if(argument.isEmpty()) {
                    //  Terminal.getInstance().terminalOutput("usage: mc_fault_injection <filename.genlib> <verilogfile.v> <monte_carlo_sample> or \"--help\"");
                    Terminal.getInstance().terminalOutput("usage: mc_fault_injection <filename.genlib> <verilogfile.v> -mc <monte_carlo_sample> or  -mcmf <base> <order_size> <frequency> or -exaustive \"--help\"");
                } else if(help) {

                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                    Terminal.getInstance().terminalOutput("usage: mc_fault_injection <filename.genlib> <verilogfile.v> <monte_carlo_sample> or mc_fault_injection <filename.genlib> <verilogfile.v> or  -mcmf <base> <order_size> <frequency> -exaustive \"--help\"");
                } else {
                    boolean success = false;
                    try {
                        //String x = splittedCommand.get(3);
                        //System.out.println("3 position : " +
                        //       splittedCommand.get(3) + "size : " + splittedCommand.size());

                        if((splittedCommand.size() <= 4) && (splittedCommand.get(3).equals("-exaustive"))){
                            //System.out.println("Inside Exaustive");
                            cmd.Exaustive_Fault_injection(splittedCommand.get(1), splittedCommand.get(2), splittedCommand.get(3)); // Exaustive Simulation
                            success = true;
                        }else{

                            if((splittedCommand.get(3).equals("-mc"))){
                                cmd.Monte_Carlo_Fault_injection(splittedCommand.get(1), splittedCommand.get(2),splittedCommand.get(4)); // MC 20000
                                success = true;
                            }
                            if((splittedCommand.get(3).equals("-mcmtf"))){

                                System.out.println("Dev Mode  ->>>>>>>>> Inside Multiple Fault injection ..... : " + splittedCommand);

                                //cmd.Monte_Carlo_Multiple_Transient_Fault_Injection(splittedCommand.get(1), splittedCommand.get(2),splittedCommand.get(4), splittedCommand.get(5), splittedCommand.get(6)); // MC 20000

                                cmd.Monte_Carlo_Multiple_Transient_Fault_Injection_Array(splittedCommand.get(1), splittedCommand.get(2), splittedCommand); // MC 20000 - MTF SINGLES DOUBLES TRIPLES

                                System.out.println(splittedCommand);

                                success = true;
                            }
                            /*
                            if((splittedCommand.get(3).equals("-mcmtfv2x"))){

                                System.out.println("Dev Mode  ->>>>>>>>> Inside Multiple Fault injection .....");

                                cmd.Monte_Carlo_Multiple_Transient_Fault_Injection(splittedCommand.get(1), splittedCommand.get(2),splittedCommand.get(4), splittedCommand.get(5), splittedCommand.get(6)); // MC 20000

                                //cmd.Monte_Carlo_Multiple_Transient_Fault_Injection_Array(splittedCommand.get(1), splittedCommand.get(2),splittedCommand.get(4), splittedCommand.get(5), splittedCommand.get(6), new ArrayList<>(<String>)); // MC 20000 - MTF SINGLES DOUBLES TRIPLES

                                System.out.println(splittedCommand);

                                success = true;
                            }
                            */

                        }
                        success = false;
                    } catch (IOException | ScriptException e) {
                        Terminal.getInstance().terminalOutput("## ERRO ##");
                        Terminal.getInstance().terminalOutput(e.getMessage());
                        Terminal.getInstance().terminalOutput("## ERRO ##");
                    } finally {
                        if(success) {
                            Terminal.getInstance().terminalOutput("Genlib File \"" + splittedCommand.get(1) + "\" successfully processed!!");
                            Terminal.getInstance().terminalOutput("verilog File \"" + splittedCommand.get(2) + "\" successfully processed!!");
                        }
                    }
                }
                break;
            
            case "read_script":
             //case "mc_fault_injection":
                if(argument.isEmpty()) {
                    Terminal.getInstance().terminalOutput("usage: read_script <script_file.txt> \n inside script_file.txt: <filename.genlib> <monte_carlo_sample> \n <verilogfile_1.v> \n <verilogfile_n.v>");
                } else if(help) {
                    Terminal.getInstance().terminalOutput(cmd.getHelpDesc(mainCommand));
                    Terminal.getInstance().terminalOutput("usage: read_script <script_file.txt> \n inside script_file.txt: <filename.genlib> <monte_carlo_sample> \n <verilogfile_1.v> \n <verilogfile_n.v>");
             
                } else {
                    boolean success = false;
                    try {
                        cmd.Read_Scrip_Mc_Fault_injection(splittedCommand.get(1));
                        success = true;
                    } catch (IOException | ScriptException e) {                        
                        Terminal.getInstance().terminalOutput("## ERRO ##");
                        Terminal.getInstance().terminalOutput(e.getMessage());
                        Terminal.getInstance().terminalOutput("## ERRO ##");
                    } finally {
                        if(success) {
                             Terminal.getInstance().terminalOutput("Simulation successfully processed!!");
                            //Terminal.getInstance().terminalOutput("Genlib File \"" + splittedCommand.get(1) + "\" successfully processed!!");
                            //Terminal.getInstance().terminalOutput("verilog File \"" + splittedCommand.get(2) + "\" successfully processed!!");
                        }
                    }
                }
             break;
             
             
                
            case "foo2":
                cmd.Foo2();
                break;
            case "foo3":
                cmd.Foo3(argument);
                break;
            case "foo4":
                cmd.Foo4();
                break;
            case "foo5":
                cmd.Foo5();
                break;
            case "foo6":
                cmd.Foo6();
                break;
            case "foo7":
                cmd.Foo7();
                break;
            case "foo8":
                cmd.Foo8();
                break;
            case "foo9":
                cmd.Foo9();
                break;
            case "csv_spr_generator":
                cmd.CSVSprGenerator();
                break;
            default:
                Terminal.getInstance().terminalOutput("Command not found!!!");
                break;
        }

    }

    public static CommandProcessor getInstance() {
        return CommandProcessorHolder.INSTANCE;        
    }

    private static final class CommandProcessorHolder {
        static final CommandProcessor INSTANCE = new CommandProcessor();
    }
}