package carteAvventura;
import java.util.Random;

public enum Dimensione {
	PICCOLO, GROSSO;
	private static Random rand=new Random();
	
	//metodo 
	//generatore casuale
	public static Dimensione generaDimensione() {
		
		return values()[rand.nextInt(values().length)];
	}
}
