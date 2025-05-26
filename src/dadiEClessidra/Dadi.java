package dadiEClessidra;

import java.util.Random;

public class Dadi {
    //attribute
    int faccia;
    Random rn = new Random();

    //constructor
    public Dadi(){
        this.faccia=0;
        
    }
    
    //methods
    public int lancia(){         
        this.faccia=rn.nextInt(12)+1;
        return this.faccia;
        
    }    
}
