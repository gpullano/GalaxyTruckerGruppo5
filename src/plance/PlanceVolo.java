package plance;
public class PlanceVolo {
	
	private Cella cella[][];
	
	public PlanceVolo(int riga, int colonna) {
		this.setCella(new Cella[riga][colonna]);
		
		for(int r = 0; r < riga; r++) {
			for(int c = 0; c < colonna; c++) {
				getCella()[r][c] = new Cella(' ');
				
			}
		}
		
	}

	public void percorso() {
		int righe = cella.length;
		int colonne = cella[0].length;
		for(int c = 1; c < colonne - 1; c++) {//▶
			cella[0][c] = new Cella('→');
			cella[0][1] = new Cella('4');
			cella[0][2] = new Cella('3');
			cella[0][3] = new Cella('2');
			cella[0][5] = new Cella('1');
			
		}
        for(int r = 1; r < righe - 1; r++) {//▼
			
			cella[r][colonne - 1] = new Cella('↓');
			
		}
        
        for(int c = colonne - 2; c > 0;c--) {//◀
        	cella[righe - 1][c] = new Cella('←');
        }
        
        for(int r = righe - 2; r > 0; r--) {//▲
        	cella[r][0] = new Cella('↑');
        }
		
    }
	
	public void stampaVolo() {
		for(int r = 0; r < cella.length; r++) {
			for(int c = 0; c < cella[r].length; c++) {
					System.out.print(getCella()[r][c].toString() + '\t');
				
			}
			System.out.println('\n');
		}
	}


	
	
	
	
	
	public Cella[][] getCella() {
		return cella;
	}

	public void setCella(Cella cella[][]) {
		this.cella = cella;
	}

}
