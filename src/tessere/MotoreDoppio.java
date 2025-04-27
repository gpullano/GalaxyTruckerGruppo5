package tessere;

public class MotoreDoppio extends Tessera implements Attivabile{
	// costruttore
	public MotoreDoppio(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown) {
		super(latoDx, latoSx, latoSup, latoDown);
	}
	// getter
	public int getPotenza() {
		return 2;
	}
	// metodi 
	@Override
	public void attiva(boolean energia) {
			if(energia) {
				getPotenza();
			}
	}

}
