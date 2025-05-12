package gameLogic;
import java.util.Random;
public enum Provenienza {
	// attributi 
	SOPRA, SOTTO, DESTRA, SINISTRA;
	//metodi 
	// generatore casuale 
	public static Provenienza generaProvenienza() {
		Random rand=new Random();
		return values()[rand.nextInt(values().length)]; // prendo casualmente o sopra sotto destra o sinistra 
	}

	
	
}
