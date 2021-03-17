/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimuladorDeChaves;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author clayt
 */
public class Parser {
    private String filename;
    public Parser(String filename) throws FileNotFoundException {
    
        this.filename = filename;
        this.readNetlistFile();
    }
    
    public boolean readNetlistFile() throws FileNotFoundException{
        
        File myObj = new File(this.filename);
        Scanner myReader = new Scanner(myObj);
        if (myObj.exists()) {
            System.out.println("File name: " + myObj.getName());
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.equals("") || data.equals("\n")){
                    //System.out.println("--- line: " + data);
                    
                }else{
                    System.out.println("line: " + data);
                }
              }
            return true;
        }else{
                return false;
            }
        }
    
    public static void main(String[] args) throws FileNotFoundException {
        Parser file =  new Parser("inv_fresh.txt");
    }
    
}
