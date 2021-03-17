/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimuladorDeChaves;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author clayt
 */
public class parserNetlist {
    
    private final String filename;
   
    private final ArrayList <ArrayList<String>> ItemLines = new ArrayList<>();
    
    
    public parserNetlist(String filename) throws FileNotFoundException {
    
        this.filename = filename;
        
    }
    
    public ArrayList <ArrayList<String>> readNetlistFile() throws FileNotFoundException{
        
        File myObj = new File(this.filename);
        Scanner myReader = new Scanner(myObj);
        if (myObj.exists()) {
            System.out.println("File name: " + myObj.getName());
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.equals("") || data.equals("\n")){ //Empty lines
                    //System.out.println("--- line: " + data);
                    
                }else{
                    //this.lines.add(data);
                    List<String> l = Arrays.asList(data.split(" ")); //Splite Lines
                    System.out.println("line: " + data);
                    ArrayList <String> lines = new ArrayList();
                    for (int i = 0; i < l.size() ; i++) {
                        if(!l.get(i).equals("")){ //Check if is empty
                            lines.add(l.get(i));    
                        }
                         //System.out.println(l.get(i));
                    }
                    this.ItemLines.add(lines);
                    
                }
              }
            System.out.println("---> " +  this.ItemLines);
          
          
        }
        return this.ItemLines;
    }
    
    public void processLines(){
    
        /*
        M_p01 vdd a out vdd NMOS L=32n W='2*wmin'
            0  1  2  3   4   5
        */
        
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        
        parserNetlist file =  new parserNetlist("inv_fresh.txt");
   
        ArrayList <ArrayList<String>> vectorParser = file.readNetlistFile();
        
        System.out.println(""+vectorParser);
               
    }
    
}
