package tessere;

public class MotoreDoppio extends Tessera {
	// attributi
	private final int potenza;
	// costruttore
	public MotoreDoppio(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown) {
		super(latoDx, latoSx, latoSup, latoDown);
		this.potenza=2;
	}
	// getter
	public int getPotenza() {
		return potenza;
	}
	// metodi 

}
