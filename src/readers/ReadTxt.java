/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author matheus
 */
public class ReadTxt {
    
    public int[] readFile(String file, int size) throws IOException {

        BufferedReader br = null;
        String path = file;
        String line;
        int[] itm = new int[size];
        int counter = 0;

            
            br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                
                if(line.equals("1.0")) {
                    itm[counter] = 0;
                } else if (line.equals("0.0")) {
                    itm[counter] = 1;
                }
                counter++;
            }
                        
        return itm;
    }
    
}
