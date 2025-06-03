package carteAvventura;
import java.util.Random;

/**
*enum che descrive la direzione della carta
*/
public enum Provenienza {
	// attributi 
	SOPRA, SOTTO, DESTRA, SINISTRA;
/**generatore di direzioni casuali*/	
	static Random rand=new Random();
	
	//metodi 
	// generatore casuale 
/** genera casualmente una delle direzioni di provenienza
*@return direzione scelta casualmente
*/
	public static Provenienza generaProvenienza() {
		return values()[rand.nextInt(values().length)]; // prendo casualmente o sopra sotto destra o sinistra 
	}

}
