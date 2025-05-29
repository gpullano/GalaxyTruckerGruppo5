package dadiEClessidra;

import java.util.Random;

/**
* La classe Dadi simula un dado con 12 facce. 
* Si può lanciare per ottenere un valore casuale da 1 a 12.
*/
public class Dadi {
	
    // attributi
    int faccia;
    Random rn = new Random();


    public Dadi(){
        this.faccia=0;
        
    }
    
    
    // metodi

/**
* Simula il lancio del dado.
* @ return Il valore casuale ottenuto dal lancio tra 1 e 12.
*/
    
    public int lancia(){         
        this.faccia=rn.nextInt(12)+1;
        return this.faccia;
        
    }    
}
