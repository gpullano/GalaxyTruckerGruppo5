package dadiEClessidra;

import java.util.Random;

public class Dadi {
    //attribute
    int face;

    //constructor
    public Dadi(){
        this.face=0;
        
    }
    
    //methods
    public int start(){ 
        Random rn = new Random();
        this.face=rn.nextInt(6)+1;
        return this.face;
        
    }    
}
