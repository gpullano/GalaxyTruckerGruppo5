package plance;

public class Casella {
	//	Attributi
	private final Posizione posizione;
	private boolean occupato;
	//	Costruttore
	
	
	public Casella(Posizione posizione, boolean occupato) {
		this.posizione = posizione;
		this.occupato = occupato;
	}
	
	public void setUtilizzabile(boolean occupato) {
		this.occupato = occupato;	
	}
	 
	
	public boolean isUtilizzabile() {
		return occupato;
	}
	
	
//	getter
	
	public Posizione getPosizione() {
		return posizione;
	}

	
	
	
}
