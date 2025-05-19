package tessere;

public class Motore extends Tessera {

	// costruttore
	public Motore(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown) {
		super(latoDx, latoSx, latoSup, latoDown);
	}
	//getter
	public int getPotenza() {
		return 1;
	}
	//metodi
	@Override
	public String getNomeBreve() {
    	return "Motor";
	}

}
