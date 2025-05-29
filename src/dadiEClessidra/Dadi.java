package dadiEClessidra;

import java.util.Random;

/**
* La classe Dadi simula un dado con 12 facce. 
* Si può lanciare per ottenere un valore casuale da 1 a 12.
*/
public class Dadi {
    //attribute
    int faccia;
    Random rn = new Random();

/**
* Costruttore classe Dadi.
* imposta la faccia a zero.
*/
    //constructor
    public Dadi(){
        this.faccia=0;
        
    }

/**
* Simula il lancio del dado.
* @ return Il valore casuale ottenuto dal lancio tra 1 e 12.
*/
    //methods
    public int lancia(){         
        this.faccia=rn.nextInt(12)+1;
        return this.faccia;
        
    }    
}
