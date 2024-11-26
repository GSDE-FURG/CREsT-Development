/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.ArrayList;

/**
 *
 * @author Matheus Pontes
 */
public class CustomMatrixLibrary {
    
    private String name;
    private ArrayList<CustomMatrix> cMatrixes;
    
    public CustomMatrixLibrary() {
        this.name = "UnamedLib";
        cMatrixes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<CustomMatrix> getcMatrixes() {
        return cMatrixes;
    }        
    
    public void addCMatrix(CustomMatrix cMatrix) {
        this.cMatrixes.add(cMatrix);
    }
    
    public void removeCMatrix(CustomMatrix cMatrix) {
        this.cMatrixes.remove(cMatrix);
    }
    
    public CustomMatrix getCMatrix(String gateName) {
        for (CustomMatrix cMatrix : this.cMatrixes) {
            if(cMatrix.getGateName().equals(gateName)) {
                return cMatrix;
            }
        }
        return null;
    }
}
