package ops;

import datastructures.Cell;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ops.PTMOps.PowInt;

public class JavaScriptEngineEvaluator {

    public int parseBoolExpression() {
        return 0;
    }

    public String engineEvaluator(Cell cell, String inputVector) {

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        String function = cell.getFunctions().get(0);

        function = function.split("=")[1];

        // If not contains, it not a zero/one generator
        if(!function.toLowerCase().contains("const")) {

            function = function.replaceAll("\\Q*\\E", "&&");
            function = function.replaceAll("\\Q+\\E", "||");

            try {
                for (int p = 0; p < cell.getInputs().size(); p++) {
                    int bit = Character.getNumericValue(inputVector.charAt(p));
                    String formula = "var " + cell.getInputs().get(p) + "=" + Boolean.toString(bit != 0) + ";";

                    engine.eval(formula);
                }

                return engine.eval(function + ";").toString();

            } catch (ScriptException ex) {
                Logger.getLogger(PTMOps.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
