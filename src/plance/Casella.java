package plance;

public class Casella {
	//	Attributi
	private final Posizione posizione;
	private boolean occupato;
	//	Costruttore
	//public Casella(posizio) {
	//	this.occupato = occupato;
	//}
	
	public Casella(Posizione posizione, boolean occupato) {
		this.posizione = posizione;
		this.occupato = occupato;
	}
	
	public void setOccupato(boolean occupato) {
		this.occupato = occupato;	
	}
	 
	
	public boolean isOccupato() {
		return occupato;
	}
	
	
//	getter
	
	public Posizione getPosizione() {
		return posizione;
	}

	
	
	
}
