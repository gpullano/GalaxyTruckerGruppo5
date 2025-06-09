package carteAvventura;

import plance.Casella;
import plance.PlanceNaveLivello1;
import plance.Posizione;

public interface GestioneProiettili {
	// static perchè è una classe di utility
	public static  Posizione colpisciComponenteDaSopra(PlanceNaveLivello1 planceNave, int colonna) {
		Casella[][] caselle  = planceNave.getCaselle();
		//Shift della colonna per allinearla agli indici della nave
		colonna -= 5;
		//Fissata la colonna, scorro le righe per cercare componenti da colpire
		for(int i = 0; i < PlanceNaveLivello1.getNumRighe(); i++) {
			if(caselle[i][colonna].getTessera() != null) {
				return new Posizione(i, colonna);
			}
		}
		return null;
	}
	public static Posizione colpisciComponenteDaSinistra(PlanceNaveLivello1 planceNave, int riga) {
		Casella[][] caselle  = planceNave.getCaselle();
		//Shift della riga per allinearla agli indici della nave
		riga -= 5;
		//Fissata la riga, scorro le colonne per cercare componenti da colpire
		for(int j = 0; j < PlanceNaveLivello1.getNumColonne(); j++) {
			if(caselle[riga][j].getTessera() != null) {
				return new Posizione(riga, j);
			}
		}
		return null;
	}
	 public static Posizione colpisciComponenteDaSotto(PlanceNaveLivello1 planciaNave, int colonna) {
	        Casella[][] caselle = planciaNave.getCaselle();
	        // Shift della colonna per allinearla agli indici della nave
	        colonna -= 5;
	        
	        // il ciclo for parte dall'ultima riga e va verso la prima.
	        for (int i = PlanceNaveLivello1.getNumRighe() - 1; i >= 0; i--) {
	            if (caselle[i][colonna].getTessera() != null) {
	                // Trovato il primo componente lo restituisco.
	                return new Posizione(i, colonna);
	            }
	        }
	        return null; // Nessun componente trovato nella colonna.
	    }
	 public static Posizione colpisciComponenteDaDestra(PlanceNaveLivello1 planciaNave, int riga) {
	        Casella[][] caselle = planciaNave.getCaselle();
	        // Shift della riga per allinearla agli indici della nave
	        riga -= 5;
	        
	        // il ciclo for parte dall'ultima colonna e va verso la prima.
	        for (int j = PlanceNaveLivello1.getNumColonne() - 1; j >= 0; j--) {
	            if (caselle[riga][j].getTessera() != null) {
	                // Trovato il primo componente lo restituisco.
	                return new Posizione(riga, j);
	            }
	        }
	        return null; // Nessun componente trovato nella riga.
	    }
	
}
