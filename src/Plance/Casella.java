package Plance;

public class Casella {
	//	Attributi
	private final int colonna;
	private final int riga;
	
	//	Costruttore
	public Casella(int colonna, int riga) {
		this.colonna = colonna;
		this.riga = riga;
	}

	//	getter e setter
	public int getColonna() {
		return colonna;
	}

	public int getRiga() {
		return riga;
	}
	
}
