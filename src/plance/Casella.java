package plance;

import tessere.Tessera;

public class Casella {
	//	Attributi
	private final Posizione posizione;
	private boolean occupato;
	private Tessera tessera;
	
	
	//	Costruttore
	public Casella(Posizione posizione) {
		this.posizione = posizione;
		this.occupato = false;
		this.tessera = null;
	}
	
	
//	getter e setter
	
	public Posizione getPosizione() {
		return posizione;
	}
	
	public void setUtilizzabile(boolean occupato) {
		this.occupato = occupato;	
	}
	 
	
	public boolean isUtilizzabile() {
		return occupato;
	}

	public void setTessera(Tessera nuovaTessera) {
		this.tessera = nuovaTessera;
		this.occupato = (nuovaTessera != null); 
	}
	
	public Tessera getTessera() {
		return this.tessera;
	}
	
//	metodi	
	
	
}
