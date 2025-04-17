package plance;

public class PlanceNave {
	// attributi
	
	private Casella caselle[][];
	
	// costruttore

	public PlanceNave(int riga, int colonna) {
		this.caselle = new Casella[riga][colonna];
		
		for(int r = 0; r < riga; r++) {
			for(int c = 0; c < colonna; c++) {
				caselle[r][c] = new Casella(r,c);
			}
		}
	}
	

	
	
	
	
}
