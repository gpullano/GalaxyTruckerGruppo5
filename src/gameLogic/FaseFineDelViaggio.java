package gameLogic;

import java.util.ArrayList;
import java.util.List;

import collezionabili.Merci;
import plance.GestorePlanceNave;
import plance.PlanceVolo;

/**
 * Gestisce la fase finale del gioco: il calcolo dei punteggi e la proclamazione del vincitore.
 * Questa fase si occupa di assegnare ricompense e penalità in base a diversi criteri.
 */
public class FaseFineDelViaggio extends Fase {

	/**
	 * Costruttore per la fase di fine viaggio.
	 * @param giocatori La lista di tutti i giocatori della partita.
	 * @param inputOutput L'oggetto per l'interazione con la console.
	 * @param planceVolo La plancia di volo (usata per i dati finali).
	 */
	public FaseFineDelViaggio(List<Giocatore> giocatori, ConsoleIO inputOutput, PlanceVolo planceVolo) {
		super(giocatori, inputOutput, planceVolo);
	}

	/**
	 * Esegue la sequenza di calcolo del punteggio finale.
	 * Chiama in ordine i metodi per assegnare ricompense e penalità,
	 * e infine annuncia il vincitore.
	 */
	@Override
	public void eseguiFase() {
		this.getInputOutput().stampaMessaggio("----- FINE DEL VIAGGIO -----");
		
		// Filtra i giocatori che non hanno abbandonato, per le ricompense che spettano solo a loro.
		List<Giocatore> giocatoriAttivi = new ArrayList<>();
        for (Giocatore g : getGiocatori()) {
            if (!g.getHaAbbandonato()) { 
                giocatoriAttivi.add(g);
            }
        }

        // 1. Ricompensa per l'ordine di arrivo
        assegnaRicompensaArrivo(giocatoriAttivi);

        // 2. Vendita delle merci
        vendiMerci(getGiocatori());

        // 3. Ricompensa per la nave più bella
        assegnaRicompensaNaveBella(giocatoriAttivi);

        // 4. Perdite per i componenti distrutti
        applicaPerditeComponenti(getGiocatori());
        
        // 5. Determina e annuncia il vincitore
        annunciaVincitore(getGiocatori());
    }

    /**
     * Assegna crediti ai giocatori in base al loro ordine di arrivo.
     * Solo i giocatori che hanno completato il viaggio ricevono questa ricompensa.
     * @param giocatoriOrdinati La lista dei giocatori attivi, già ordinata per arrivo.
     */
	private void assegnaRicompensaArrivo(List<Giocatore> giocatoriOrdinati) {
	    this.getInputOutput().stampaMessaggio("\n--- Ricompensa per l'Ordine di Arrivo ---");
	    int[] ricompense = { 4, 3, 2, 1 };
	    
	    for (int i = 0; i < giocatoriOrdinati.size(); i++) {
	        if (i < ricompense.length) {
	            Giocatore giocatoreCorrente = giocatoriOrdinati.get(i);
	            int premio = ricompense[i];
	            
	            giocatoreCorrente.aggiungiCrediti(premio);
	            this.getInputOutput().stampaMessaggio("Giocatore " + giocatoreCorrente.getColore() + " (Posizione " + (i + 1) + ") riceve " + premio + " crediti.");
	        } else {
	            break; // Premi finiti
	        }
	    }
	}
    
    /**
     * Calcola il valore delle merci di ogni giocatore e lo aggiunge ai crediti.
     * I giocatori che hanno abbandonato ricevono metà del valore.
     * @param giocatori La lista di tutti i giocatori.
     */
    private void vendiMerci(List<Giocatore> giocatori) {
        this.getInputOutput().stampaMessaggio("\n--- Vendita delle Merci ---");
        int valoreTotaleMerci;
        
        for (Giocatore giocatore : giocatori) {
            valoreTotaleMerci = 0;

            for (Merci merce : giocatore.getPlanceNave().getTutteLeMerciABordo()) {
                valoreTotaleMerci += merce.getValore(); 
            }
            
            // Il valore viene dimezzato (arrotondato per difetto) se il giocatore ha abbandonato.
            if (giocatore.getHaAbbandonato()) {
            	valoreTotaleMerci /= 2;
            }
            
            if (valoreTotaleMerci > 0) {
                giocatore.aggiungiCrediti(valoreTotaleMerci);
                this.getInputOutput().stampaMessaggio("Giocatore " + giocatore.getColore() + " vende merci per " + valoreTotaleMerci + " crediti.");
            }
        }
    }

    /**
     * Assegna un bonus al giocatore (o ai giocatori) con il minor numero di connettori esposti.
     * @param giocatori La lista dei giocatori che non hanno abbandonato.
     */
    private void assegnaRicompensaNaveBella(List<Giocatore> giocatori) {
        this.getInputOutput().stampaMessaggio("\n--- Ricompensa per la Nave più Bella ---");
        int minConnettoriEsposti = Integer.MAX_VALUE;
        List<Giocatore> vincitoriNaveBella = new ArrayList<>();

        // Trova il numero minimo di connettori esposti.
        for (Giocatore giocatore : giocatori) {
            int esposti = GestorePlanceNave.contaConnettoriEsposti(giocatore.getPlanceNave());
            this.getInputOutput().stampaMessaggio("Info: Giocatore " + giocatore.getColore() + " ha " + esposti + " connettori esposti.");
            if (esposti < minConnettoriEsposti) {
                minConnettoriEsposti = esposti;
            }
        }

        // Trova tutti i giocatori in parità con il numero minimo.
        for (Giocatore giocatore : giocatori) {
            if (GestorePlanceNave.contaConnettoriEsposti(giocatore.getPlanceNave()) == minConnettoriEsposti) {
                vincitoriNaveBella.add(giocatore);
            }
        }

        // Assegna 2 crediti a tutti i vincitori (come da regola della parità).
        int premio = 2;
        for (Giocatore vincitore : vincitoriNaveBella) {
            vincitore.aggiungiCrediti(premio);
            this.getInputOutput().stampaMessaggio("Giocatore " + vincitore.getColore() + " vince il premio Nave più Bella e riceve " + premio + " crediti.");
        }
    }

    /**
     * Sottrae un credito per ogni componente della nave perso durante il viaggio.
     * @param giocatori La lista di tutti i giocatori.
     */
    private void applicaPerditeComponenti(List<Giocatore> giocatori) {
        this.getInputOutput().stampaMessaggio("\n--- Penalità per Componenti Persi ---");
        for (Giocatore giocatore : giocatori) {
            int componentiPersi = giocatore.getPlanceNave().getPilaScarti();
            giocatore.aggiungiCrediti(-componentiPersi);
            this.getInputOutput().stampaMessaggio("Giocatore " + giocatore.getColore() + " perde " + componentiPersi + " crediti per i componenti distrutti.");
        }
    }

    /**
     * Calcola i punteggi finali, determina il vincitore e ne annuncia il risultato.
     * @param tuttiIGiocatori La lista completa di tutti i giocatori.
     */
    private void annunciaVincitore(List<Giocatore> tuttiIGiocatori) {
        this.getInputOutput().stampaMessaggio("\n--- PUNTEGGIO FINALE ---");
        
        List<Giocatore> vincitori = new ArrayList<>();
        int maxCrediti = 0;

        // Calcola il punteggio massimo tra tutti i giocatori.
        for (Giocatore giocatore : tuttiIGiocatori) {
            int crediti = giocatore.getCreditiStellari();
            this.getInputOutput().stampaMessaggio("Giocatore " + giocatore.getColore() + " termina con " + crediti + " crediti.");
            if (crediti > maxCrediti) {
                maxCrediti = crediti;
            }
        }

        // Trova tutti i giocatori che hanno raggiunto il punteggio massimo.
        for (Giocatore giocatore : tuttiIGiocatori) {
            if (giocatore.getCreditiStellari() == maxCrediti) {
                vincitori.add(giocatore);
            }
        }
        
        // Annuncia i risultati - per vincere, i giocatori devono aver accumulato almeno un credito.
        if (vincitori.isEmpty() || maxCrediti <= 0) { 
             this.getInputOutput().stampaMessaggio("\nNessun vincitore! Un viaggio fallimentare per tutti.");
        } else if (vincitori.size() == 1) {
            this.getInputOutput().stampaMessaggio("\nIL VINCITORE È IL GIOCATORE " + vincitori.get(0).getColore().toString().toUpperCase() + "!");
        } else {
            // Gestione della parità.
            StringBuilder sb = new StringBuilder("\nC'È UNA PARITÀ! I VINCITORI SONO: ");
            for (Giocatore vincitore : vincitori) {
                sb.append("GIOCATORE ").append(vincitore.getColore().toString().toUpperCase()).append(" ");
            }
            this.getInputOutput().stampaMessaggio(sb.toString());
        }
    }
    
    /**
     * Metodo helper per trovare un giocatore nella lista tramite il suo colore.
     * @param giocatori La lista in cui cercare.
     * @param colore Il colore del giocatore da trovare.
     * @return Il giocatore trovato, o null se non presente.
     */
    private Giocatore trovaGiocatorePerColore(List<Giocatore> giocatori, Colore colore) {
        for (Giocatore g : giocatori) {
            if (g.getColore() == colore) {
                return g;
            }
        }
        return null;
    }
}