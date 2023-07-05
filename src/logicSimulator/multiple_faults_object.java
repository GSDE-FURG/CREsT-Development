package logicSimulator;

import java.util.ArrayList;

public class multiple_faults_object {
    private int order;
    private int counter;
    private int counter2;
    private int occurrence;
    private int original_counter;
    private ArrayList <Integer> list = new ArrayList<>();


    public multiple_faults_object(int order, int counter, int occurrence){
        this.order = order;
        this.counter = counter;
        this.counter2 = counter;
        this.occurrence = occurrence;
        this.original_counter = counter;
        this.list.add(counter);
    }

    public void updateCounter(int counter) {
        this.counter = counter;
    }

    public void updateCounterConterOriginal2(int counter) {
        this.counter2 = counter2;
    }

    public void setList(int value){
        this.list.add(value);
    }

    public void printList(){
        System.out.println("Order: " + this.order + "  Original: " + this.original_counter + "   New: " + this.counter
                + " - List: "  + this.list );
    }

    public ArrayList<Integer> getList() {
        return this.list;
    }

    public int getPosList(int value){

        //for (int i = 0; i < this.list.size() ; i++) {

          //  if(value == this.){
                // int c = this.list.get(i);
                //this.counter = value + this.original_occurrence;
              if(this.counter == value){
                return this.counter;
                }


        return -1;
    }

    public int getOccurrence() {
        return this.occurrence;
    }

    public int getOriginal_counter() {
        return this.original_counter;
    }

    public int getCounter() {
        return counter;
    }

    public int remove(){
        return this.list.get(this.list.size()-1
        );
    }

    public int getOrder() {
        return order;
    }
}
