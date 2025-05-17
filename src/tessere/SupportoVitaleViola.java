package tessere;

public class SupportoVitaleViola extends Tessera {
	// attributo unico che è il colore
	private final String colore;
	// costruttore ereditato dalla superclasse 'Tessera'
	public SupportoVitaleViola(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown,String colore) {
		super(latoDx, latoSx, latoSup, latoDown);
		this.colore=colore;
	}

	public String getColore() {
		return colore;
	}
	@Override
	public String getNomeBreve() {
    	return "STVV";
	}

}
