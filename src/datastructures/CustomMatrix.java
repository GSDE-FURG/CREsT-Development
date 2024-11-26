/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.math.BigDecimal;
import ops.CommonOps;

/**
 *
 * @author Matheus Pontes
 */
public class CustomMatrix {

    private String gateName;
    private BigDecimal[][] cMatrix;

    public CustomMatrix(String gateName) {
        this.gateName = gateName;
    }

    public String getGateName() {
        return gateName;
    }

    public void setGateName(String gateName) {
        this.gateName = gateName;
    }

    public BigDecimal[][] getcMatrix() {
        return cMatrix;
    }

    public void setcMatrix(BigDecimal[][] cMatrix) {
        this.cMatrix = cMatrix;
    }
    
    public void print() {
        CommonOps.matrixPrint(cMatrix);
    }
    
    public String toString() {
        return gateName;
    }

}
