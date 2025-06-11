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

/**
 * Gestisce la fase di volo del gioco.
 * Durante questa fase, vengono pescate carte avventura dal mazzo e i giocatori
 * affrontano gli eventi descritti, con la possibilità di abbandonare la corsa.
 */
public class FaseVolo extends Fase {
	
	/** Il mazzo di carte avventura per la fase di volo. */
	private List<Carta> mazzoDiCarte;

	/**
	 * Costruttore della fase di volo.
	 * @param giocatori La lista dei giocatori.
	 * @param inputOutput L'oggetto per l'interazione con la console.
	 * @param planceVolo La plancia di volo.
	 * @param mazzoDiCarte Il mazzo di carte avventura da affrontare.
	 */
	public FaseVolo(List<Giocatore> giocatori, ConsoleIO inputOutput, PlanceVolo planceVolo, List<Carta> mazzoDiCarte) {
		super(giocatori, inputOutput, planceVolo);
		this.mazzoDiCarte = mazzoDiCarte;
	}

	/**
	 * Esegue la logica principale della fase di volo.
	 * Itera attraverso le carte avventura, ordina i giocatori, gestisce gli abbandoni
	 * e attiva gli effetti di ogni carta per i giocatori rimasti in gioco.
	 */
	@Override
	public void eseguiFase() {
		for (Carta carta : mazzoDiCarte) {
			
			this.getInputOutput().stampaVolo(this.getPlanceVolo());
	        this.getInputOutput().stampaMessaggio("\n--- Prossima Carta Avventura ---");
	        
	        // Ordina i giocatori in base alla loro posizione (dal più avanzato al più arretrato).
	        this.ordinaGiocatoriAttuali();
			
			List<Giocatore> giocatoriDaRimuovere = new ArrayList<>();
			List<Giocatore> giocatoriAncoraInGioco = new ArrayList<>();

			// Chiede a ogni giocatore se vuole abbandonare prima di rivelare la carta.
	        for (Giocatore giocatoreCorrente : this.getGiocatori()) {
	            boolean abbandona = this.getInputOutput().chiediSeEseguireAzione(
	            	"GIOCATORE " + giocatoreCorrente.getColore() + ", vuoi abbandonare la corsa prima di girare la carta?"
	            );
	            if (abbandona) {
	                giocatoreCorrente.abbandonaPartita();
	                giocatoriDaRimuovere.add(giocatoreCorrente);
	                this.getInputOutput().stampaMessaggio("Giocatore " + giocatoreCorrente.getColore() + " ha abbandonato la corsa!");
	            } else {
	                giocatoriAncoraInGioco.add(giocatoreCorrente);
	            }
	        }

	        // Rimuove i giocatori che hanno abbandonato dalla lista dei partecipanti attivi.
	        if (!giocatoriDaRimuovere.isEmpty()) {
	            this.getGiocatori().removeAll(giocatoriDaRimuovere);
	            for (Giocatore rimosso : giocatoriDaRimuovere) {
	                this.getPlanceVolo().rimuoviGiocatore(rimosso.getColore());
	            }
	        }
	        
	        // Se non ci sono più giocatori, il volo termina prematuramente.
	        if (giocatoriAncoraInGioco.isEmpty()) {
	            this.getInputOutput().stampaMessaggio("Tutti i giocatori hanno abbandonato! Il volo termina.");
	            break;
	        }
			
	        // Attiva l'effetto della carta specifica per i giocatori rimasti.
			if (carta instanceof CartaPerditaGiorniVolo cartaPerditaGiorniVolo) {
				cartaPerditaGiorniVolo.attiva(giocatoriAncoraInGioco, getPlanceVolo(), getInputOutput());
			} 
			if (carta instanceof SpazioAperto spazioAperto) {
				spazioAperto.attiva(giocatoriAncoraInGioco, getPlanceVolo(), getInputOutput());
			}
			if (carta instanceof PioggiaDiMeteoriti pioggiaDiMeteoriti) {
				pioggiaDiMeteoriti.attiva(giocatoriAncoraInGioco, getInputOutput());
			}
		}
	}
	
	/**
	 * Ordina la lista dei giocatori attivi in base alla loro posizione sulla plancia di volo.
	 * L'ordinamento è decrescente: prima per numero di giri, poi per posizione sulla tratta.
	 * Il giocatore più avanti sarà il primo della lista.
	 */
	private void ordinaGiocatoriAttuali() {
	    this.getGiocatori().sort(new Comparator<Giocatore>() {
	        @Override
	        public int compare(Giocatore g1, Giocatore g2) {
	            PosizioneGiocatore pos1 = getPlanceVolo().getPosizioneDi(g1.getColore());
	            PosizioneGiocatore pos2 = getPlanceVolo().getPosizioneDi(g2.getColore());
	            
	            // 1. Confronta i giri (decrescente).
	            int confrontoGiro = Integer.compare(pos2.getGiro(), pos1.getGiro());
	            if (confrontoGiro != 0) {
	                return confrontoGiro;
	            }
	            
	            // 2. Se i giri sono uguali, confronta la posizione (decrescente).
	            return Integer.compare(pos2.getPosizione(), pos1.getPosizione());
	        }
	    });
	    
	    this.getInputOutput().stampaMessaggio("\nOrdine dei giocatori aggiornato per questo turno.\n");
	}
}