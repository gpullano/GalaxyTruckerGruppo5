package plance;

import tessere.Tessera;

public class PlanceNaveLivello1 extends PlanceNave{
	private Casella[][] caselle;
	private Tessera tessera;

	public PlanceNaveLivello1() {
		super(5, 7);
		this.creaNave();
	}

	@Override
	public void creaNave() {
		for(int r = 0; r <= 4; r++) {
		int c = 0;
		if (r == 0) {
			for( c = 3; c <= 3;c++) {
				getCaselle()[r][c].setUtilizzabile(true);	
		}
		}
		if (r == 1) {
			for(c = 2; c <= 4; c++) {
				getCaselle()[r][c].setUtilizzabile(true);
			}
		}
		
		if (r == 2) {
			for(c = 1; c <= 5; c++) {
				getCaselle()[r][c].setUtilizzabile(true);
			}
		}
		
		if (r == 3) {
			for(c = 1; c <= 5; c++) {
				getCaselle()[r][c].setUtilizzabile(true);
			}
		}
		
		if (r == 4) {
			for(c = 1; c <= 2; c++) {
				getCaselle()[r][c].setUtilizzabile(true);
			}
			for(c = 4; c <= 5; c++) {
				getCaselle()[r][c].setUtilizzabile(true);
			}
		}	
		}
	}
	
	public boolean posizionaTessera(Tessera tessera, int riga,int colonna) {
		/*if (riga < 0 || riga >= getCaselle().length || colonna < 0 || colonna >= getCaselle()[0].length) {
			System.out.println("Errore: posizione fuori dai limiti (" + riga + "," + colonna + ")");
		    return false;
		}*/
		
		if (!getCaselle()[riga][colonna].isUtilizzabile()) {
			System.out.println("Errore: la casella (" + riga + "," + colonna + ") non è utilizzabile");
		    return false;
		}
		
		if (getCaselle()[riga][colonna].isOccupata()) {
			System.out.println("Errore: la casella (" + riga + "," + colonna + ") è già occupata");
		    return false;
		}
		
		getCaselle()[riga][colonna].setTessera(tessera);
		System.out.println("Tessera posizionata con successo in (" + riga + "," + colonna + ")");
		return true;
	}

	@Override
	public void stampaNave() {
		System.out.println();
		for(int r = 0; r <= 4; r++) {
			for(int c = 0; c <= 6; c++) {
				if(getCaselle()[r][c].isUtilizzabile()) {
					if (getCaselle()[r][c].isOccupata()) {
						System.out.print(getCaselle()[r][c].getTessera().toString());
					} else {
						System.out.print("▢\t\t");	
					}
				}else {
			    	System.out.print("\t\t");
				}
				
			}
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}

	public Tessera getTessera() {
		return getTessera();
	}

	public void setTessera(Tessera tessera) {
		this.tessera = tessera;
	}

	
}
