package gameLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import collezionabili.Merci;
import plance.GestorePlanceNave;
import plance.PlanceVolo;
import plance.PosizioneGiocatore;

/**
*la classe FaseFineDelViaggio gestisce il calcolo del punteggio finale e la determinazione del vincitore.
*/
public class FaseFineDelViaggio extends Fase {

	/**
	*costruttore della classe FaseFineDelViaggio.
	*@param giocatori la lista dei giocatori.
	*@param inputOutput l'oggetto per l'input/output.
	*@param planceVolo la plancia di volo comune.
	*/
	public FaseFineDelViaggio(List<Giocatore> giocatori, ConsoleIO inputOutput, PlanceVolo planceVolo) {
		super(giocatori, inputOutput, planceVolo);
	}

	/**
	*esegue la logica di fine partita, calcolando i punteggi e determinando il vincitore.
	*/
	@Override
	public void eseguiFase() {
		this.getInputOutput().stampaMessaggio("----- FINE DEL VIAGGIO -----");
		
        // troviamo i giocatori che non hanno abbandonato
		List<Giocatore> giocatoriAttivi = new ArrayList<>();
        for (Giocatore g : getGiocatori()) {
            if (!g.getHaAbbandonato()) { 
                giocatoriAttivi.add(g);
            }
        }
        
        List<PosizioneGiocatore> posizioniFinali = getPlanceVolo().getPosizioneGiocatori();

        // 1. RICOMPENSA PER L'ORDINE DI ARRIVO
        assegnaRicompensaArrivo(giocatoriAttivi, posizioniFinali);

        // 2. VENDITA DELLE MERCI
        vendiMerci(getGiocatori());

        // 3. RICOMPENSA PER LA NAVE PIÙ BELLA
        assegnaRicompensaNaveBella(giocatoriAttivi);

        // 4. PERDITE PER COMPONENTI DISTRUTTI
        applicaPerditeComponenti(getGiocatori());
        
        // 5. DETERMINA E ANNUNCIA IL VINCITORE
        annunciaVincitore(getGiocatori());
    }

    /**
    *assegna i crediti bonus in base all'ordine di arrivo dei giocatori.
    *@param giocatori la lista dei giocatori attivi.
    *@param posizioni la lista delle posizioni finali sulla plancia di volo.
    */
    private void assegnaRicompensaArrivo(List<Giocatore> giocatori, List<PosizioneGiocatore> posizioni) {
        this.getInputOutput().stampaMessaggio("\n--- Ricompensa per l'Ordine di Arrivo ---");
        int[] ricompense = {4, 3, 2, 1};
        
        PosizioneGiocatore pos;
        Giocatore giocatore;
        int premio;
        for (int i = 0; i < posizioni.size(); i++) {
            pos = posizioni.get(i);
            giocatore = trovaGiocatorePerColore(giocatori, pos.getColore());
            
            if (giocatore != null && i < ricompense.length) {
                premio = ricompense[i];
                giocatore.aggiungiCrediti(premio);
                this.getInputOutput().stampaMessaggio("Giocatore " + giocatore.getColore() + " (Posizione " + (i + 1) + ") riceve " + premio + " crediti.");
            }
        }
    }
    
    /**
    *calcola il valore delle merci di ogni giocatore e lo aggiunge ai crediti.
    *@param giocatori la lista di tutti i giocatori.
    */
    private void vendiMerci(List<Giocatore> giocatori) {
        this.getInputOutput().stampaMessaggio("\n--- Vendita delle Merci ---");
        int valoreTotaleMerci;
        for (Giocatore giocatore : giocatori) {
            valoreTotaleMerci = 0;

            for (Merci merce : giocatore.getPlanceNave().getTutteLeMerciABordo()) {
                valoreTotaleMerci += merce.getValore(); 
            }
            
            //il valore viene arrotondato per difetto
            if(giocatore.getHaAbbandonato()) {
            	valoreTotaleMerci /= 2;
            }
            
            if (valoreTotaleMerci > 0) {
                giocatore.aggiungiCrediti(valoreTotaleMerci);
                this.getInputOutput().stampaMessaggio("Giocatore " + giocatore.getColore() + " vende merci per " + valoreTotaleMerci + " crediti.");
            }
        }
    }

    /**
    *assegna la ricompensa per la "nave più bella" al giocatore con meno connettori esposti.
    *@param giocatori la lista dei giocatori attivi.
    */
    private void assegnaRicompensaNaveBella(List<Giocatore> giocatori) {
        this.getInputOutput().stampaMessaggio("\n--- Ricompensa per la Nave più Bella ---");
        int minConnettoriEsposti = Integer.MAX_VALUE;
        List<Giocatore> vincitoriNaveBella = new ArrayList<>();

        // Fase 1: Trova il numero minimo di connettori esposti
        for (Giocatore giocatore : giocatori) {
            int esposti = GestorePlanceNave.contaConnettoriEsposti(giocatore.getPlanceNave());
            this.getInputOutput().stampaMessaggio("Info: Giocatore " + giocatore.getColore() + " ha " + esposti + " connettori esposti.");
            if (esposti < minConnettoriEsposti) {
                minConnettoriEsposti = esposti;
            }
        }

        // Fase 2: Trova tutti i giocatori in parità con il numero minimo
        for (Giocatore giocatore : giocatori) {
            if (GestorePlanceNave.contaConnettoriEsposti(giocatore.getPlanceNave()) == minConnettoriEsposti) {
                vincitoriNaveBella.add(giocatore);
            }
        }

        // Fase 3: Assegna 2 crediti a tutti i vincitori (come da regola della parità)
        int premio = 2;
        for (Giocatore vincitore : vincitoriNaveBella) {
            vincitore.aggiungiCrediti(premio);
            this.getInputOutput().stampaMessaggio("Giocatore " + vincitore.getColore() + " vince il premio Nave più Bella e riceve " + premio + " crediti.");
        }
    }

    /**
    *sottrae crediti ai giocatori in base al numero di componenti persi.
    *@param giocatori la lista di tutti i giocatori.
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
    *determina il giocatore con il punteggio più alto e annuncia il vincitore.
    *@param tuttiIGiocatori la lista completa dei giocatori per il calcolo finale.
    */
    private void annunciaVincitore(List<Giocatore> tuttiIGiocatori) {
        this.getInputOutput().stampaMessaggio("\n--- PUNTEGGIO FINALE ---");
        
        List<Giocatore> vincitori = new ArrayList<>();
        int maxCrediti = 0;

        // Fase 1: Calcola il punteggio massimo
        for (Giocatore giocatore : tuttiIGiocatori) {
            int crediti = giocatore.getCreditiStellari();
            this.getInputOutput().stampaMessaggio("Giocatore " + giocatore.getColore() + " termina con " + crediti + " crediti.");
            if (crediti > maxCrediti) {
                maxCrediti = crediti;
            }
        }

        // Fase 2: Trova tutti i giocatori che hanno raggiunto il punteggio massimo
        for (Giocatore giocatore : tuttiIGiocatori) {
            if (giocatore.getCreditiStellari() == maxCrediti) {
                vincitori.add(giocatore);
            }
        }
        
        // Fase 3: Annuncia i risultati
        if (vincitori.isEmpty() || maxCrediti <= 0) { 
             this.getInputOutput().stampaMessaggio("\nNessun vincitore! Un viaggio fallimentare per tutti.");
        } else if (vincitori.size() == 1) {
            this.getInputOutput().stampaMessaggio("\nIL VINCITORE È IL GIOCATORE " + vincitori.get(0).getColore().toString().toUpperCase() + "!");
        } else {
            // Gestione della parità
            StringBuilder sb = new StringBuilder("\nC'È UNA PARITÀ! I VINCITORI SONO: ");
            for (Giocatore vincitore : vincitori) {
                sb.append("GIOCATORE ").append(vincitore.getColore().toString().toUpperCase()).append(" ");
            }
            this.getInputOutput().stampaMessaggio(sb.toString());
        }
    }
    
    /**
    *metodo di utilità per trovare un giocatore in una lista dato il suo colore.
    *@param giocatori la lista in cui cercare.
    *@param colore il colore del giocatore da trovare.
    *@return il giocatore trovato o null se non presente.
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