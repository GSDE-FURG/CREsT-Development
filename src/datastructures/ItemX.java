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
    private final BigDecimal Reliability;
    private long time;
    private long loadTime;
    private String precision;
    private int numberFanouts;
    
    public ItemX (int fanoutIdx, BigDecimal Reliability, long time){
        this.fanoutIdx = fanoutIdx;
        this.Reliability = Reliability;
        this.time = time;
        this.loadTime = 0;
        this.numberFanouts = 0;
    }
    
    public int getIdxFanout(){
        return this.fanoutIdx;
    }
     public String getLoadTime(){
        return String.valueOf(this.loadTime);
    }
      public void setLoadTime(long LoadTime){
        this.loadTime = LoadTime;
     }
      public void setPrecision(String precision){
        this.precision = precision;
     }
     public void setNumberFanouts(int numberFanouts){
        this.numberFanouts = numberFanouts;
     }
      public String getNumberFanouts(){
         return String.valueOf(this.numberFanouts);
     }
      public String getPrecision(){
         return String.valueOf(this.precision);
     }
     public String getReliability(){
        return String.valueOf(this.Reliability).replace(".", ",");
    }
     public String getReliabilityDOTCOMMA(){
        return String.valueOf(this.Reliability).replace(".", ",");
    }
      public BigDecimal getReliabilityBigDecimal(){
        return (this.Reliability);
    }
     public String getTime(){
        return String.valueOf(this.time);
    }
      public String getTimeMin(){
        return String.valueOf((this.time)/(1000*60));
    }
    public long getTimelong(){
        return (this.time);
    }
    
    public String PrintItemx(){
       // System.out.println("Fanout Idx: " + this.fanoutIdx);
       // System.out.println("Reliability: " + this.Reliability);
       // System.out.println("Time: " + this.time);
        
        String item = "Fanout (n): " + this.fanoutIdx + "        Reliability: " + this.Reliability + "        Time: " + this.time  + "  ~  " + this.time/1000 + " (s)";
        
        return (item);
    }
    public String getLineResultTable(){
       
        
        //String item = "Fanout (n): " + this.fanoutIdx + "        Reliability: " + this.Reliability + "        Time: " + this.time  + "  ~  " + this.time/1000 + " (s)";
        
        String itemx = this.fanoutIdx + ";" + this.getReliabilityDOTCOMMA() + ";" + "=-LN(B"+(this.fanoutIdx+2)+")" + "/1E-6" + ";" + "=1/C" + (this.fanoutIdx + 2) + ";"   + this.getTime() + ";" + this.getTimeMin();
        
        return (itemx);
    }
    
}

