package gameLogic;
import java.util.Random;

public enum Dimensione {
	PICCOLO, GROSSO;
	
	
	//metodo 
	//generatore casuale
	public static Dimensione generaDimensione() {
		Random rand=new Random();
		return values()[rand.nextInt(values().length)];
	}
}
