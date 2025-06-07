package plance;

import tessere.Tessera;

public class Casella {
	//	Attributi
	private final Posizione posizione;
	private boolean utilizzabile;
	private Tessera tessera;
	
	
	//	Costruttore
	public Casella(Posizione posizione) {
		this.posizione = posizione;
		this.utilizzabile = false;
		this.tessera = null;
	}
	
	
//	getter e setter
	
	public Posizione getPosizione() {
		return posizione;
	}
	
	public void setUtilizzabile(boolean utilizzabile) {
		this.utilizzabile = utilizzabile;	
	}
	 
	
	public boolean isUtilizzabile() {
		return utilizzabile;
	}

	public void setTessera(Tessera nuovaTessera) {
		this.tessera = nuovaTessera;
	}
	
	public Tessera getTessera() {
		return this.tessera;
	}	
	
	
}
