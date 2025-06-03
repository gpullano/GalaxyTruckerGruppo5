package plance;

import gameLogic.Colore;

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

		
		/*for(int i = 0; i < numeroGiocatori; i++) {
			this.posizioneGiocatori[i] = new PosizioneGiocatore(0,1,0,colori[i]);
		}*/
	
		
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
	

	
	public void avanzaPosizione(Colore colore, int giorni) {
		
		for(PosizioneGiocatore giocatore: posizioneGiocatori) {
			if(colore == giocatore.getColore()) {
				giocatore.aggiornaPosizione(giorni, PlanceVolo.LUNGHEZZA_PERCORSO);
				
				
			}
				
		}
		trovaLeader();
		
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
		PosizioneGiocatore leader = this.posizioneGiocatori[0];
			for (PosizioneGiocatore giocatori : this.posizioneGiocatori) {
				if (giocatori.getGiro()>leader.getGiro()) {
					leader=giocatori;
					
				}else if(giocatori.getGiro()==leader.getGiro()) {
					if (giocatori.getPosizione()>leader.getPosizione()) {
						leader=giocatori;
					}
				}
			}
		return;
	}

}
