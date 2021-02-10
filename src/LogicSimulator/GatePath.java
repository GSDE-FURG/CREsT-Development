/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicSimulator;

import datastructures.Gate;
import datastructures.Signal;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author clayt
 */

/** Gatepah method to run this class
 * @author Clayton Farias
 * @version 1.1
 * @since 
 * Created to partial analisys of DFS output to input paths
*/
 class GatePath{
        
        private ArrayList <Object> gatesInOutPath;
        private Gate inGate;
        private final Gate outGate;
        String id;
        private ArrayList <Integer> indexes;
        
        public Gate getGate(){
            return this.outGate;
        }
        
        public GatePath(Gate outgate) {
            this.gatesInOutPath = new ArrayList<Object>();
            this.outGate = outgate;
            this.id = "";
            this.indexes = new ArrayList<Integer>();
        }
        
         public void setID(){
            this.id = "stop";
        }
         
        public void setindexStop(int index){
            this.indexes.add(index);
        }
        
        public ArrayList<Integer> getindexStop(){
            return this.indexes;
        } // Salva posições dos index
         
        public String getID(){
            return this.id;
        }
        
        public void setgateinthePatch(Object gate){
            this.gatesInOutPath.add(gate);
        }
        
        public void setsiginthePatch(Signal signal){
            this.gatesInOutPath.add(signal);
        }
        
        public ArrayList <Object> getgateinthePatch(){
           // Collections.reverse(gatesInOutPath);
            return this.gatesInOutPath;
        }
        
        public ArrayList<Object> REV_getgateinthePatch(){
           //Collections temp = new PolicyUtils.Collections();
            Collections.reverse(gatesInOutPath);
            return this.gatesInOutPath;
        }

    }
  
     
     