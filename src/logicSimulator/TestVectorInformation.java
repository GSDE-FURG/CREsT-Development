/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicSimulator;

import datastructures.Signal;
import levelDatastructures.DepthGate;
import levelDatastructures.GateLevel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class represent an single vector test
 * @author clayton
 */
public class TestVectorInformation {
    
    private ArrayList <Integer> inputVector;
    private String outputFreeFault;
    private String faultOutput;
    private Signal faultSignals;
    private ArrayList <Signal> allfaultSignals;
    private int originalSignalValue;
    private int faultSignalValue;
    private int index;
    private long threadID;

    private float sum_sensitive_cells_area;
    private float sum_sensitive_cells_area_original;

    private boolean MTF;
    private final  ArrayList <Signal> MTF_FaultSignal_List;
    private final List <Signal> MTF_FaultSignal_List_thd = Collections.synchronizedList(new ArrayList<Signal>());
    private final List <itemnize> MTF_PERSONAL_LIST = Collections.synchronizedList(new ArrayList<itemnize>());

    public ConcurrentHashMap <String, Signal>  MTF_Fault_LIST_thd = new ConcurrentHashMap<>();

    public ArrayList <DepthGate>  help = new ArrayList<>();

    //public ArrayList <DepthGate>  gatesNewAprouch = new ArrayList<>();
    ArrayList <ArrayList <GateLevel>> gatesLevelsThreadList = new ArrayList<>();

    public ArrayList <Float>  vectorSensitiveAreaSum = new ArrayList<>();
    //private ArrayList <Signal> MTF_FaultSignal_List_Base;
    //private ArrayList <SignalExtendedProperties> MTF_FaultSignal_List_Extended;
    public ArrayList <GateDetailedInformation> gatesLogicalPath;
    public ArrayList <GateDetailedInformation> gatesSensitiveLogicalPath;

    public Float circuitSensitiveArea;

    public void printSpecs(){
        System.out.println("         -------- ");
        System.out.println("        faultSingnals: " + this.faultSignals);
        System.out.println("        allfaultSignals: " + this.allfaultSignals);
        System.out.println("        MTF_FaultSignal_List" + this.MTF_FaultSignal_List);
        System.out.println("        MTF_FaultSignal_List_thd" + this.MTF_FaultSignal_List_thd);
        System.out.println("        MTF_PERSONAL_LIST: " + this.MTF_PERSONAL_LIST.size());
        System.out.println("        -------- ");

    }

    public void setGatesLevelsThreadList(ArrayList<GateLevel> gatesLevelsThreadList) {
        final ArrayList<GateLevel> temp = gatesLevelsThreadList;
        this.gatesLevelsThreadList.add(temp);
    }

    public ArrayList <ArrayList <GateLevel>> getGatesLevelsThreadList() {
        return this.gatesLevelsThreadList;
    }

    public Signal getFaultSignals() {
        return faultSignals;
    }

    public String concatInputVector(){
            String inputConcat = "";
            for (int x: this.inputVector){
                inputConcat = inputConcat + x;
            }
            return inputConcat;
    }

    public String concatMTFFaultSignals(){
        String Concat = "";
        for (Signal x: this.MTF_FaultSignal_List_thd){
            Concat = Concat + "_" + x;
        }
        return Concat;
    }

    public String concatMTFFaultSignals(String separator){
        String Concat = "";
        for (Signal x: this.MTF_FaultSignal_List_thd){
            Concat = Concat + separator + x;
        }
        return Concat;
    }


    public TestVectorInformation(ArrayList <Integer> inputVector, Signal faultSignal, int input_pos) {
            this.faultSignals = faultSignal;
            this.inputVector = inputVector;
            //this.faultSignalValue = sigValue;
            this.index = input_pos;
            this.threadID = 0;
            this.allfaultSignals = new ArrayList<>();

            /* new */
            this.sum_sensitive_cells_area = 0;

            this.MTF = false;
            this.MTF_FaultSignal_List = new ArrayList<>();
            this.MTF_FaultSignal_List_thd.add(faultSignal);
            this.MTF_FaultSignal_List.add(faultSignal);

            //this.circuitPath = new ArrayList<>();

            this.MTF_Fault_LIST_thd.put(faultSignal.getId(), faultSignal);
            itemnize item = new itemnize(faultSignal, faultSignal.getId(), this.inputVector);
            item.setOriginalValue(faultSignal.getOriginalLogicValue());
            this.MTF_PERSONAL_LIST.add(item);
            //this.MTF_FaultSignal_List_Base = new ArrayList<>(this.MTF_FaultSignal_List);
            //this.MTF_FaultSignal_List_Extended = new ArrayList<>();
            //SignalExtendedProperties x = new SignalExtendedProperties();
            //x.setSignal(faultSignal);
            //this.MTF_FaultSignal_List_Extended.add(x);

            this.gatesLogicalPath = new ArrayList<>();
        this.gatesSensitiveLogicalPath = new ArrayList<>();
            this.circuitSensitiveArea = 0.0F;
            
    }

    public void setGatesLogicalPath(GateDetailedInformation gatex) {
        this.gatesLogicalPath.add(gatex);
    }
    public void setSensitiveGatesLogicalPath(GateDetailedInformation gatex) {
        this.gatesSensitiveLogicalPath.add(gatex);
    }

    public ArrayList<GateDetailedInformation> getGatesLogicalPath() {
        return this.gatesLogicalPath;
    }

    public ArrayList<GateDetailedInformation> getSensitiveGatesLogicalPath() {
        return this.gatesSensitiveLogicalPath;
    }

    public int searchGatecircuitGatesInPath(DepthGate gate){
        for (int i = 0; i < gatesLogicalPath.size(); i++) {
            if(gate.getGate().getId() == this.gatesLogicalPath.get(i).getGate().getGate().getId()){
                return i;
            }
        }
        return -1;
    }

    public void sum_sensitive_cells_area(float cells_sensitive_area){
        this.sum_sensitive_cells_area = this.sum_sensitive_cells_area + cells_sensitive_area;
    }

    public void sum_sensitive_cells_area_gate(float cells_sensitive_area, DepthGate x){
        //vectorSensitiveAreaSum.add(cells_sensitive_area);
        help.add(x);
        vectorSensitiveAreaSum.add(cells_sensitive_area);
    }

    public void sum_sensitive_cells_area_original(float cells_sensitive_area){
        this.sum_sensitive_cells_area_original = this.sum_sensitive_cells_area_original + cells_sensitive_area;
    }

    public Float getCircuitOriginalSensitiveArea() {

        return circuitSensitiveArea;
    }
    public String getCircuitOriginalSensitiveAreaStr() {
        float myFloat = circuitSensitiveArea;

        String formattedString = String.format("%.03f", myFloat);

        return formattedString;
    }

    public void setCircuitSensitiveArea(Float circuitSensitiveArea) {
        this.circuitSensitiveArea = circuitSensitiveArea;
    }

    public ArrayList<DepthGate> getSum_sensitive_cells_area_Gates(){
        return help;
    }
    public ArrayList<String> getSum_sensitive_cells_area_Gates_detail(){
        ArrayList s = new ArrayList();

        for (int i = 0; i < help.size(); i++) {
            int pos = searchGatecircuitGatesInPath(help.get(i));

            s.add(help.get(i) + "  " + help.get(i).getGate().getInputs().toString()
                    + " " + this.gatesLogicalPath.get(pos).getInputs()
                    + " Out: " + this.gatesLogicalPath.get(pos).getOutputs());
        }

        //Não é esse help.get(i).getGate().getInputsValuesToString()

        return s;
    }

    public ArrayList<Float> getSum_sensitive_cells_area_sum_vector(){
        return vectorSensitiveAreaSum;
    }

    public float getSum_sensitive_cells_area() {
        return this.sum_sensitive_cells_area;
    }

    public float getSum_sensitive_cells_area_original() {
        return this.sum_sensitive_cells_area_original;
    }

    public String getSum_sensitive_cells_area_str() {
        float myFloat = this.sum_sensitive_cells_area;
        String formattedString = String.format("%.03f", myFloat);
        formattedString = formattedString.replace(",",".");
        return  formattedString;
    }

    public String getSum_sensitive_cells_area_original_str() {
        float myFloat = this.sum_sensitive_cells_area_original;
        String formattedString = String.format("%.03f", myFloat);
        formattedString = formattedString.replace(",",".");
        return  formattedString;
    }

    public String getBitFlip(){
            
            String bitflip;
            String r = "";
           
            if(this.originalSignalValue == 0){
                bitflip = "1";
            }
            else{
                bitflip = "0";
            }
            
            r = "("+ this.originalSignalValue + ")to("+ bitflip + ")";
            
            return r;
            
            
        }

    public String getBitFlipMTF(){

        String bitflip;
        String r = "";

        for (int i = 0; i < this.get_MTF_FaultSignal_List_thd().size(); i++) {
            r = r + this.get_MTF_FaultSignal_List_thd().get(i).getSignalBitflip() + " " + this.get_MTF_FaultSignal_List_thd().get(i).getLogicValueBoolean() ;
        }
        /*
        if(this.originalSignalValue == 0){
            bitflip = "1";
        }
        else{
            bitflip = "0";
        }

        r = "("+ this.originalSignalValue + ")to("+ bitflip + ")";
        */


        return r;


    }

    public String getBitFlipMTFPERSONAL_LIST(){

        String bitflip;
        String r = "";


        for (int i = 0; i < this.getMTF_PERSONAL_LIST().size(); i++) {
            r = r + this.getMTF_PERSONAL_LIST().get(i).getBitflip() + "" + this.getMTF_PERSONAL_LIST().get(i).getIdentidade() + " " ;
        }
        /*
        if(this.originalSignalValue == 0){
            bitflip = "1";
        }
        else{
            bitflip = "0";
        }

        r = "("+ this.originalSignalValue + ")to("+ bitflip + ")";
        */


        return r;


    }

    public ArrayList <String> getMTFPERSONAL_LIST_Identities(){

        String bitflip;
        String r = "[";
        ArrayList<String> x = new ArrayList<>();

        for (int i = 0; i < this.getMTF_PERSONAL_LIST().size(); i++) {
           // r = r + this.getMTF_PERSONAL_LIST().get(i).getIdentidade() + ", ";
            x.add(this.getMTF_PERSONAL_LIST().get(i).getIdentidade());
        }

        /*
        if(this.originalSignalValue == 0){
            bitflip = "1";
        }
        else{
            bitflip = "0";
        }

        r = "("+ this.originalSignalValue + ")to("+ bitflip + ")";
        */


        return x;


    }

    public synchronized List<Signal> getMTF_FaultSignal_List_thd() {
        return this.MTF_FaultSignal_List_thd;
    }

    public synchronized List <itemnize> getMTF_PERSONAL_LIST()
    {
        return this.MTF_PERSONAL_LIST;
    }

    public synchronized String getMTF_PERSONAL_LIST_NODESINFO()
    {
        String x = "";

        for (int i = 0; i < this.MTF_PERSONAL_LIST.size(); i++) {
            x = x + this.MTF_PERSONAL_LIST.get(i).getIdentidade() + " , ";
        }
        return x;
    }

    public synchronized void setFAULT_MTF_PERSONAL_LIST(int pos, int originalValue, ArrayList<Integer> input)
    {
       itemnize item = new itemnize(this.get_MTF_FaultSignal_List_thd().get(pos), this.get_MTF_FaultSignal_List_thd().get(pos).getId(), input);
       item.setOriginalValue(originalValue);
       this.MTF_PERSONAL_LIST.add(item);
    }
    public ArrayList <Signal> get_MTF_FaultSignal_List (){
        return this.MTF_FaultSignal_List;
     }

    public synchronized List <Signal> get_MTF_FaultSignal_List_thd (){
        return this.MTF_FaultSignal_List_thd;
    }

    /*
    public ArrayList <Signal> get_MTF_FaultSignal_List_Base (){
        return this.MTF_FaultSignal_List_Base;
    }
    */
    public boolean get_MTF_flag(){
        return this.MTF;
     }

     /*
    public ArrayList <SignalExtendedProperties> getMTF_FaultSignal_List_Extended(){
        return this.MTF_FaultSignal_List_Extended;
    }
    */
     public void setMultipleTransientFaultInjection(Signal faultSignal){
         //SignalExtendedProperties x = new SignalExtendedProperties();
         //x.setSignal(faultSignal);
         //System.out.println(x.getSignal().getId());
        if(this.MTF == false)
        {
                this.MTF = true;
                //this.MTF_FaultSignal_List.add(this.faultSignals);
                this.MTF_FaultSignal_List.add(faultSignal);
                //this.MTF_FaultSignal_List_Extended.add(x);


        }
        else
        {
                this.MTF_FaultSignal_List.add(faultSignal);
                //this.MTF_FaultSignal_List_Extended.add(x);

        }
        //this.MTF_FaultSignal_List_Extended.add(new SignalExtendedProperties(faultSignal));

         this.MTF_FaultSignal_List_thd.add(faultSignal);
         this.MTF_Fault_LIST_thd.put(faultSignal.getId(), faultSignal);

         itemnize item = new itemnize(faultSignal, faultSignal.getId(), this.inputVector);
         item.setOriginalValue(faultSignal.getOriginalLogicValue());
         this.MTF_PERSONAL_LIST.add(item);

     }

     public void setThreadID(long id){
         this.threadID = id;
     }
    
     public void setFaultSignalValue(int FaultSigValue){
         this.faultSignalValue = FaultSigValue;
     }
     
     public void setSignalOriginalValue(int SigValue){
         this.originalSignalValue = SigValue;
         
         if(this.originalSignalValue == 0){
             this.faultSignalValue = 1;
         }
         else{
             this.faultSignalValue = 0;
         }
             
         
     }
     
     public int getSignalOriginalValue(){
        return this.originalSignalValue;
     }
      
     public int getFaultSignalValue(){
        return this.faultSignalValue;
     }
    
     public void setFaultFreeOutput(String output){
        this.outputFreeFault = output;
    }
     
     public void setFaultOutput(String output){
        this.faultOutput = output;
    }
     
     public String getFaultOutput(){
        return this.faultOutput;
    }
     
     public String getOrignalOutput(){
        return this.outputFreeFault;
    }

     public int getPositionFaultSignalInMTFList(Signal f){

        for (int i = 0; i < this.MTF_FaultSignal_List.size(); i++) {
            if(this.MTF_FaultSignal_List.get(i).getId().equals(f.getId())){
                //System.out.println("\n SIG founded: " + f.getId()  +" ~ " + this.getSimulationIndex());
                return i;
            }
        }
        return -1;
    }

     public int getPositionFaultSignalInMTFListThd(Signal f){

        for (int i = 0; i < this.get_MTF_FaultSignal_List_thd().size(); i++) {
            if(this.get_MTF_FaultSignal_List_thd().get(i).getId().equals(f.getId())){
                //System.out.println("\n SIG founded: " + f.getId()  +" ~ " + this.getSimulationIndex());
                return i;
            }

        }
        return -1;
    }
    
     public Object getItem(){
        System.out.println("This: "+ this.faultSignals);
        return this;
    }
     
     public Signal getFaultSignal(){
        return this.faultSignals;
    }
    
     public ArrayList <Integer> getinputVector(){
        return this.inputVector;
    }
    public String getinputVectorStr(){
         String str = "";

        for (int i = 0; i < this.inputVector.size() ; i++) {
            str = str + this.inputVector.get(i);
        }
        return str;
    }
     
     public int getSimulationIndex(){
        return this.index;
    }
    
}
