package collezionabili;
import java.util.Random;
public enum Merci {
	BLU(1), VERDE(2), GIALLA(3), ROSSA(4);

	Merci(int i) {
		}
	// generatore di merci casuale
	 public static Merci generaMerceCasuale() {
		 Random rand=new Random();
	        Merci[] valori = Merci.values();
	        int indice = rand.nextInt(valori.length);
	        return valori[indice];
	    }
}
