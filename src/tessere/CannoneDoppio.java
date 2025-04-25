package tessere;

public class CannoneDoppio extends Tessera implements Attivabile {
	// attributi
	private final int fuoco;
	
	// costruttore
	public CannoneDoppio(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown,int fuoco) {
		super(latoDx, latoSx, latoSup, latoDown);
		this.fuoco=fuoco;
	}

	// getter
	public int getFuoco() {
		return fuoco;
	}
	//metodi 

	@Override
	public void attiva() {
		// TODO implementare il consumo di energia (dalla batteria)
		
	}
	
}
