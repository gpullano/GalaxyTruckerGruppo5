package dadiEClessidra;

import java.util.Random;

public class Dadi {
    //attribute
    int faccia;

    //constructor
    public Dadi(){
        this.faccia=0;
        
    }
    
    //methods
    public int lancia(){ 
        Random rn = new Random();
        this.faccia=rn.nextInt(12)+1;
        return this.faccia;
        
    }    
}
