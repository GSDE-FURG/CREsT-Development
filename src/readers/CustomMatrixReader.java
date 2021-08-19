/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readers;

import datastructures.CustomMatrix;
import datastructures.CustomMatrixLibrary;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

/**
 *
 * @author matheus
 */
public class CustomMatrixReader {

    private final String path;
    private final CustomMatrixLibrary cMatrixLib;    

    public CustomMatrixReader(String path) {
        this.path = path;
        cMatrixLib = new CustomMatrixLibrary();
    }

    public CustomMatrixLibrary getcMatrixLib() throws IOException {
        this.readFile();
        return cMatrixLib;
    }    
    
    
    public void readFile() throws IOException {
        String line;
        BufferedReader br = new BufferedReader(new FileReader(this.path));

            while ((line = br.readLine()) != null) {
                line = line.replaceAll(" ", "");
                String[] lineValues = line.split("\t");                
                
                cMatrixLib.addCMatrix(cMatrixFactory(lineValues));
                //CommonOps.matrixPrint(cMatrixFactory(lineValues).getcMatrix());
                
            }                                
    }
    
    public CustomMatrix cMatrixFactory(String[] lineValues) {
        CustomMatrix cMatrix = new CustomMatrix(lineValues[0]);
        
        int i = ((lineValues.length - 1)/2);
        int flag = 1;
        
        //System.out.println("Gate: " + cMatrix.getGateName());
        //System.out.println("I igual a: " + i);
        
        BigDecimal[][] values = new BigDecimal[i][2];
        
        for (int j = 0; j < values.length; j++) {
            for (int k = 0; k < values[0].length; k++) {
                values[j][k] = new BigDecimal(lineValues[flag]);
                flag += 1;
            }            
        }
        
        cMatrix.setcMatrix(values);
        
        return cMatrix;
    }
    
}
