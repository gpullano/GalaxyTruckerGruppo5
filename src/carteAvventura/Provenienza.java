package carteAvventura;
import java.util.Random;
public enum Provenienza {
	// attributi 
	SOPRA, SOTTO, DESTRA, SINISTRA;
	static Random rand=new Random();
	
	//metodi 
	// generatore casuale 
	public static Provenienza generaProvenienza() {
		return values()[rand.nextInt(values().length)]; // prendo casualmente o sopra sotto destra o sinistra 
	}

}
