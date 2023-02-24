/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulator;

import datastructures.CellLibrary;
import datastructures.Circuit;
import datastructures.Gate;
import readers.MappedVerilogReader;
import signalProbability.ProbCircuit;

/**
 *
 * @author Matheus Pontes
 */
public class CircuitFactory {
    
    private String path;
    private CellLibrary cellLib;
    
    public CircuitFactory(CellLibrary cellLib, String path) {
        this.path = path;
        this.cellLib = cellLib;
    }
    
    public ProbCircuit getProbCircuit() throws Exception {
        Circuit circuit = new MappedVerilogReader(path, cellLib).getCircuit();
        return new ProbCircuit(circuit);
    }
    
}
