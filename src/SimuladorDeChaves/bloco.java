/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimuladorDeChaves;

import java.util.ArrayList;

/*
            Nome: FONTE/GND/ENTRADA_GATE
			Tipo: PMOS/NMOS
      Linha: SOURCE/GND GATE OUTPUT/NODO SOURCE/DRENO
			
			Conectado_A_Saida_PMOS_OU_NMOS: TRUE ou FALSE
			Entrada_bloco: NOME
      Entrada_sinal: A/B/X
 			Saida_BLOCO OU SINAL: OUTPUT/NODO
           

			Status_GATE/BLOCO: ABERTO ou FECHADO
			Se Entrada GATE -> Valor_LOGICO: 0 ou 1

			Se VDD/SSOURCE -> 1
			Se GND/VSS -> 0
            */

/**
 *
 * @author clayt
 */
public class bloco {
    
    private String name;
    private String type;
    private ArrayList <String> line_content; 
    private boolean visited;   //TRUE oR FALSE
    private boolean connect_to_output;
    
    private bloco input_block;
    private bloco input_signal;
    private bloco bulk;
    private bloco output_block;
    
    private boolean status; // 0 - FALSE - OPEN                =======    1 - TRUE - CLOSE 
    
    
        /*
        M_p01 vdd a out vdd NMOS L=32n W='2*wmin'
            0  1  2  3   4   5
        */

    public bloco(String name, String Type){
        this.name = name;
        this.type = Type;
        
    }
    
    public String getName(){
        return this.name;
    }
    
     public String getType(){
        return this.type;
    }
    
    private void setInputBlock(bloco x){
        this.input_block = x;
    }
    
    private void setInputSignal(bloco x){
        this.input_signal = x;
    }
    private void setOutputBlock(bloco outputBlock){
       this.output_block = outputBlock;
    }
     private void setBulk(bloco x){
        this.bulk = x;
    }
            
    public bloco getOutputBlock(){
        return this.output_block;
    }    
    
    public static void main(String[] args) {
        
        
        bloco MP_0 = new bloco("MP_0", "PMOS");
        
        bloco MN_0 = new bloco("MN_0", "NMOS");
         
        bloco vdd = new bloco("vdd", "SOURCE");
        
        bloco INPUT_SIGNAL = new bloco("a", "SIGNAL");
        
        bloco OUTPUT = new bloco("out", "INTERMEDIARIO");
        
        bloco BULK = new bloco("vdd", "BULK");
        
        /*
        M_p01 vdd a out vdd NMOS L=32n W='2*wmin'
            0  1  2  3   4   5
        M_n01 vss a out vss PMOS L=32n W='wmin'
        */
        
        MP_0.setInputBlock(vdd);
        MP_0.setInputSignal(INPUT_SIGNAL);
        MP_0.setOutputBlock(OUTPUT);
        MP_0.setBulk(BULK);
        
        MN_0.setInputBlock(vdd);
        
        System.out.println(vdd.getName() + " - " + vdd.getType());
        
        System.out.println(MP_0.getName() + " - " + MP_0.getType());
       
        System.out.println(MN_0.getName() + " - " + MN_0.getType());
    }
}
