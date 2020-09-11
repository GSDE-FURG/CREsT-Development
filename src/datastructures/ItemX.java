/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.math.BigDecimal;

/**
 *
 * @author clayt
 */
public class ItemX {
   
    private final int fanoutIdx;
    private final BigDecimal MTBF;
    private final long time;
    
    public ItemX (int fanoutIdx, BigDecimal MTBF, long time){
        this.fanoutIdx = fanoutIdx;
        this.MTBF = MTBF;
        this.time = time;
    }
    
    public int getIdxFanout(){
        return this.fanoutIdx;
    }
     public String getMTBF(){
        
        return String.valueOf(this.MTBF).replace(".", ",");
    }
     public String getTime(){
        return String.valueOf(this.time);
    }
    
    public String PrintItemx(){
       // System.out.println("Fanout Idx: " + this.fanoutIdx);
       // System.out.println("MTBF: " + this.MTBF);
       // System.out.println("Time: " + this.time);
        
        String item = "Fanout (n): " + this.fanoutIdx + "        Reliability: " + this.MTBF + "        Time: " + this.time  + "  ~  " + this.time/1000 + " (s)";
        
        return (item);
    }
    
}

