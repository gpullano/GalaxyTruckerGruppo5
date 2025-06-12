package gameLogic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import carteAvventura.Carta;
import carteAvventura.CartaPerditaGiorniVolo;
import carteAvventura.PioggiaDiMeteoriti;
import carteAvventura.SpazioAperto;
import plance.PlanceVolo;
import plance.PosizioneGiocatore;

public class FaseVolo extends Fase {
	private List<Carta> mazzoDiCarte;

	public FaseVolo(List<Giocatore> giocatori, ConsoleIO inputOutput, PlanceVolo planceVolo, List<Carta> mazzoDiCarte) {
		super(giocatori, inputOutput, planceVolo);
		this.mazzoDiCarte = mazzoDiCarte;
	}

	@Override
	public void eseguiFase() {
		for(Carta carta: mazzoDiCarte) {
			
			//Stampo la planceVolo
			this.getInputOutput().stampaVolo(this.getPlanceVolo());
	        this.getInputOutput().stampaMessaggio("\n--- Prossima Carta Avventura ---");
	        
	        //Ordiniamo la lista dei giocatori
	        this.ordinaGiocatoriAttuali();
			
			List<Giocatore> giocatoriDaRimuovere = new ArrayList<>();
			List<Giocatore> giocatoriAncoraInGioco = new ArrayList<>();

			//Identifico chi abbandona e chi no
	        for (Giocatore giocatoreCorrente : this.getGiocatori()) {
	            boolean abbandona = this.getInputOutput().chiediSeEseguireAzione("GIOCATORE " + giocatoreCorrente.getColore() + ", vuoi abbandonare la corsa prima di girare la carta?");
	            if (abbandona) {
	                giocatoreCorrente.abbandonaPartita();
	                giocatoriDaRimuovere.add(giocatoreCorrente);
	                this.getInputOutput().stampaMessaggio("Giocatore " + giocatoreCorrente.getColore() + " ha abbandonato la corsa!");
	            } else {
	                giocatoriAncoraInGioco.add(giocatoreCorrente);
	            }
	        }

	        //Rimuovo i giocatori che hanno abbandonato dalle liste attive
	        if (!giocatoriDaRimuovere.isEmpty()) {
	            this.getGiocatori().removeAll(giocatoriDaRimuovere);
	            for (Giocatore rimosso : giocatoriDaRimuovere) {
	                this.getPlanceVolo().rimuoviGiocatore(rimosso.getColore());
	            }
	        }
	        
	        // Se non ci sono più giocatori, il volo finisce
	        if (giocatoriAncoraInGioco.isEmpty()) {
	            this.getInputOutput().stampaMessaggio("Tutti i giocatori hanno abbandonato! Il volo termina.");
	            break; // Esci dal loop delle carte
	        }
	        
	        this.getInputOutput().stampaMessaggio("\n-------------------------------------------");
	        this.getInputOutput().stampaMessaggio("----------- Rivelazione Carta -------------");
	        this.getInputOutput().stampaMessaggio("-------------------------------------------");
			
			if(carta instanceof CartaPerditaGiorniVolo cartaPerditaGiorniVolo) {
				cartaPerditaGiorniVolo.attiva(giocatoriAncoraInGioco, getPlanceVolo(), getInputOutput());
			} 
			if(carta instanceof SpazioAperto spazioAperto) {
				spazioAperto.attiva(giocatoriAncoraInGioco, getPlanceVolo(), getInputOutput());
			}
			if(carta instanceof PioggiaDiMeteoriti pioggiaDiMeteoriti) {
				pioggiaDiMeteoriti.attiva(giocatoriAncoraInGioco, getInputOutput());
			}
		}

	}
	
	private void ordinaGiocatoriAttuali() {
	    // Usiamo il metodo sort della lista, passandogli un nuovo oggetto Comparator
	    this.getGiocatori().sort(new Comparator<Giocatore>() {
	        
	        @Override
	        public int compare(Giocatore g1, Giocatore g2) {
	            // Per ogni coppia di giocatori (g1, g2), recuperiamo la loro posizione
	            PosizioneGiocatore pos1 = getPlanceVolo().getPosizioneDi(g1.getColore());
	            PosizioneGiocatore pos2 = getPlanceVolo().getPosizioneDi(g2.getColore());
	            
	            // Logica di confronto per l'ordine decrescente:
	            
	            // 1. Confronta i giri. Chi ha più giri, viene prima.
	            // L'ordine è p2 vs p1 per ottenere un ordinamento decrescente.
	            int confrontoGiro = Integer.compare(pos2.getGiro(), pos1.getGiro());
	            if (confrontoGiro != 0) {
	                return confrontoGiro;
	            }
	            
	            // 2. Se i giri sono uguali, confronta la posizione. Chi ha la posizione più alta, viene prima.
	            return Integer.compare(pos2.getPosizione(), pos1.getPosizione());
	        }
	    });
	    
	    this.getInputOutput().stampaMessaggio("\nOrdine dei giocatori aggiornato per questo turno.\n");
	}

}
