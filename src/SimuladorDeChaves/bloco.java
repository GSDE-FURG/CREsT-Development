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
    private bloco output_block;
    
    private boolean status; // 0 - FALSE - OPEN                =======    1 - TRUE - CLOSE 
    
    
        /*
        M_p01 vdd a out vdd NMOS L=32n W='2*wmin'
            0  1  2  3   4   5
        */

    public bloco() {
    }
            
            
}
