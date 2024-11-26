/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus
 */
public class ReadTxt {
    
    public int[] readFile(String file, int size) throws IOException {
        //teste
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
    
    public static Map<String, BigDecimal[][]> readSchivittzCells(String pathFile) {
        BufferedReader br = null;        
        String line;
        Map<String, BigDecimal[][]> schivittzCells = new HashMap<>();

        try {
            br = new BufferedReader(new FileReader(pathFile));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadTxt.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while ((line = br.readLine()) != null) {
                String[] arrayLinha = line.split("\\s");                           
                String cellName = arrayLinha[0];
                ArrayList<ArrayList<BigDecimal>> reliabilies = new ArrayList<ArrayList<BigDecimal>>();
                
                int counter = 0;
                for (int i = 1; i < arrayLinha.length; i++) {                    
                    
                    if((i % 2) == 0) {
                        reliabilies.get(counter).add(new BigDecimal(arrayLinha[i]));
                        counter = counter + 1;
                        
                    } else {
                        reliabilies.add(new ArrayList<>());
                        reliabilies.get(counter).add(new BigDecimal(arrayLinha[i]));
                    }
                }
                
                BigDecimal[][] convertedReliabilities = new BigDecimal[reliabilies.size()][reliabilies.get(0).size()];
                
                for (int i = 0; i < reliabilies.size(); i++) {
                    for (int j = 0; j < reliabilies.get(0).size(); j++) {
                        convertedReliabilities[i][j] = reliabilies.get(i).get(j);
                    }
                }
                
//                for (int i = 0; i < convertedReliabilities.length; i++) {
//                    for (int j = 0; j < convertedReliabilities[0].length; j++) {
//                        System.out.println("[" + i + "][" + j + "] == " + convertedReliabilities[i][j]);
//                    }
//                }
                
               schivittzCells.put(cellName, convertedReliabilities);
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ReadTxt.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return schivittzCells;     
    }
    
}
