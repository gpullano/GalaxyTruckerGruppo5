package plance;

import gameLogic.Colore;
import gameLogic.Giocatore;

public class PlanceVolo {
	
	private Cella cella[][];
	private PosizioneGiocatore[] posizioneGiocatori;
	private static final int LUNGHEZZA_PERCORSO = 18;
	
	public PlanceVolo(int riga, int colonna, int numeroGiocatori, Colore colori[]) {
		this.setCella(new Cella[riga][colonna]);
		
		for(int r = 0; r < riga; r++) {
			for(int c = 0; c < colonna; c++) {
				getCella()[r][c] = new Cella(' ');
				
			}
		}

		
		posizioneGiocatori = new PosizioneGiocatore[numeroGiocatori];
	
		
	}
	
	// TODO - da spostare in ConsoleIO
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
	
	public PosizioneGiocatore[] getPosizioneGiocatori() {
		return posizioneGiocatori;
	}
	
	public void stampaGiocatori() {
		for (PosizioneGiocatore p : posizioneGiocatori ) {
			System.out.println(p);
		}
	}
	
	
	public void controlloDoppiaggio() {
		for(int i = posizioneGiocatori.length - 1; i >= 1; i--) {
			if(posizioneGiocatori[0].getGiro() >= posizioneGiocatori[i].getGiro() && posizioneGiocatori[0].getPosizione() >= posizioneGiocatori[i].getPosizione()) {
				
			}
		}
		return;
	}
	
	
	
	
	
	
	public Cella[][] getCella() {
		return cella;
	}

	public void setCella(Cella cella[][]) {
		this.cella = cella;
	}

	//TODO - se non è necessaria rimuoverla
	public int getLunghezzaPercorso() {
		return LUNGHEZZA_PERCORSO;
	}
	
	public void trovaLeader() {
		int lungh=this.posizioneGiocatori.length;
		PosizioneGiocatore temp;
		for (int i=0;i<lungh-1;i++) {
			for(int j=i+1;j<lungh;j++) {
				PosizioneGiocatore attuale=this.posizioneGiocatori[i];
				PosizioneGiocatore dopo=this.posizioneGiocatori[j];
				if (attuale.getGiro()<dopo.getGiro()) {
				temp=attuale;
				attuale=dopo;
				dopo=temp;
					 
				}else if(attuale.getGiro()==dopo.getGiro()) {
					if (attuale.getPosizione()<dopo.getPosizione()) {
						temp=attuale;
						attuale=dopo;
						dopo=temp;
					}
				}
				
				
			}
		}
		return;
		
	}

}
