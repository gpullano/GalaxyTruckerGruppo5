package plance;

public class Posizione {
	private final int colonna;
	private final int riga;
	
	public Posizione(int colonna, int riga) {
		this.colonna = colonna;
		this.riga = riga;
		
	}

	public int getColonna() {
		return colonna;
	}

	public int getRiga() {
		return riga;
	}

}
