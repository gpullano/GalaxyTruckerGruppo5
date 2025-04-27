package tessere;

public class CannoneDoppio extends Tessera implements Attivabile {
	// costruttore
	public CannoneDoppio(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown) {
		super(latoDx, latoSx, latoSup, latoDown);
	}

	public int getFuoco() {
		if(this.latoSup == Connettore.CANNONE) {
			return 2;
		}
		return 1;
	}

	@Override
	public void attiva(boolean energia) {
			if(energia) {
				getFuoco();
			}
	}
	
}
