package plance;

public abstract class PlanceNave {
	// attributi
	private Casella caselle[][];
	
	// costruttore
	public PlanceNave(int riga, int colonna) {
		this.setCaselle(new Casella[riga][colonna]);
		
		for(int r = 0; r < riga; r++) {
			for(int c = 0; c < colonna; c++) {
				getCaselle()[r][c] = new Casella(new Posizione(r,c));
			}
		}
	}
	
	
	// getter e setter
	public Casella[][] getCaselle() {
		return caselle;
	}

	public void setCaselle(Casella caselle[][]) {
		this.caselle = caselle;
	}
	
	
	// metodi 
	public abstract void creaNave();
	public abstract void stampaNave();


	
	
	
	
}
