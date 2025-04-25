package tessere;

public class Motore extends Tessera {
	// attributi
	private final int potenza;
	// costruttore
	public Motore(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown) {
		super(latoDx, latoSx, latoSup, latoDown);
		this.potenza=1;
	}
	//getter
	public int getPotenza() {
		return potenza;
	}
	//metodi

}
