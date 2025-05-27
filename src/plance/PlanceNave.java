package plance;

import java.util.LinkedList;
import java.util.List;

import tessere.Tessera;

public abstract class PlanceNave {
	// attributi
	private Casella[][] caselle;
	private List<Tessera> componentiPrenotati;
	// costruttore
	protected PlanceNave(int riga, int colonna) {
		if(riga < 0 || colonna < 0) {
			throw new IndexOutOfBoundsException("Non puoi inserire un numero di righe e di colonne minori di zero.");
		}
		this.setCaselle(new Casella[riga][colonna]);
		
		for(int r = 0; r < riga; r++) {
			for(int c = 0; c < colonna; c++) {
				getCaselle()[r][c] = new Casella(new Posizione(r,c));
			}
		}
		
		this.componentiPrenotati = new LinkedList<>();
	}
	
	
	// getter e setter
	public Casella[][] getCaselle() {
		return caselle;
	}

	public void setCaselle(Casella[][] caselle) {
		this.caselle = caselle;
	}
	
	
	
	// metodi 
	public abstract void creaNave();
	public abstract void stampaNave();

	public void aggiungiComponentePrenotato(Tessera componente) {
		if(this.componentiPrenotati.size() < 2) {
			this.componentiPrenotati.add(componente);
		} else {
			throw new IllegalStateException("Impossibile aggiungere un componente prenotato. Limite di 2 componenti già raggiunto.");
		}
	}
	
	
}
