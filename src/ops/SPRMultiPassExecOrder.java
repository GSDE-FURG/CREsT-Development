package ops;

import javax.script.ScriptException;

class Teste{

    public static void main(final String[] args) {
        System.out.println("\n\nHello world, working");

        SPRMultiPassExecOrder objeto = new  SPRMultiPassExecOrder (10);
        
        objeto.teste();
        
   }
       

}

public class SPRMultiPassExecOrder{


    private final int key;

    public SPRMultiPassExecOrder(int entry) {
            this.key = entry;
    }
   
    public void teste(){
        System.out.println("beyyy " + this.key);
    }


}