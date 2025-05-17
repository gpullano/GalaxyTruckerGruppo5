package collezionabili;

public class Merci {
	public enum Colore {
		ROSSO, GIALLO, BLU, VERDE
	}
	
	private Colore colore;
	
	public Merci (Colore colore) {
		this.setColore(colore);
	}

	public Colore getColore() {
		return colore;
	}

	public void setColore(Colore colore) {
		this.colore = colore;
	}
	
}
