/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package schivittz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Schivittz
 */
public class ExecucaoEmLotes {
    BigDecimal[][] PTM_SOnF;
    
    public void executa(String portas, String confiabilidade) throws FileNotFoundException, IOException{
                LeArquivo l = new LeArquivo();
                try {
                    l.setConfiabilidade(new BigDecimal(confiabilidade));
                    String arquivo = portas +".txt";
                    l.Ler(arquivo, 1);
                    l.InsereProbEntradas("50");
                } catch (IOException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }
                l.mostraTudo();

                Grafo g = new Grafo(l.getListaTransistores(), l.getListaEntradas(), l.getListaNodos(), l.getConfiabilidade());

                g.analiseSOnF();
                //g.analiseSOF();
                g.criaArquivoSaida(g.getSaida(), portas, confiabilidade);
        
    }
    
    /*public static void main(String args[]) throws IOException {
    System.out.println(args.length);
    ExecucaoEmLotes e = new ExecucaoEmLotes();
    e.executa(args[0], args[1]);
    
    }*/
    public static void main(String args[]) throws IOException {
        System.out.println(args.length);
        
        ExecucaoEmLotes e = new ExecucaoEmLotes();
        e.executa("NAND3", "0.99");
        
        }
        
    
}
