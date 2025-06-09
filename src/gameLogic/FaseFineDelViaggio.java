package gameLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import collezionabili.Merci;
import plance.GestorePlanceNave;
import plance.PlanceVolo;
import plance.PosizioneGiocatore;

public class FaseFineDelViaggio extends Fase {

	public FaseFineDelViaggio(List<Giocatore> giocatori, ConsoleIO inputOutput, PlanceVolo planceVolo) {
		super(giocatori, inputOutput, planceVolo);
	}

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
        
        List<PosizioneGiocatore> posizioniFinali = Arrays.asList(getPlanceVolo().getPosizioneGiocatori());

        // 1. RICOMPENSA PER L'ORDINE DI ARRIVO
        assegnaRicompensaArrivo(giocatoriAttivi, posizioniFinali);

        // 2. VENDITA DELLE MERCI
        vendiMerci(getGiocatori());

        // 3. RICOMPENSA PER LA NAVE PIÙ BELLA
        assegnaRicompensaNaveBella(giocatoriAttivi);

        // 4. PERDITE PER COMPONENTI DISTRUTTI
        applicaPerditeComponenti(getGiocatori());
        
        // 5. DETERMINA E ANNUNCIA IL VINCITORE
        annunciaVincitore(getGiocatori()); // Annunciamo il punteggio di tutti, anche di chi ha abbandonato
    }

    

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
    
    private void vendiMerci(List<Giocatore> giocatori) {
        this.getInputOutput().stampaMessaggio("\n--- Vendita delle Merci ---");
        int valoreTotaleMerci;
        for (Giocatore giocatore : giocatori) {
            valoreTotaleMerci = 0;

            //TODO
            for (Merci merce : giocatore.getPlanceNave().getMerciNave()) {
//                valoreTotaleMerci += merce.getValore(); 
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

    private void applicaPerditeComponenti(List<Giocatore> giocatori) {
        this.getInputOutput().stampaMessaggio("\n--- Penalità per Componenti Persi ---");
        for (Giocatore giocatore : giocatori) {
            int componentiPersi = giocatore.getPlanceNave().getPilaScarti();
            giocatore.aggiungiCrediti(-componentiPersi);
            this.getInputOutput().stampaMessaggio("Giocatore " + giocatore.getColore() + " perde " + componentiPersi + " crediti per i componenti distrutti.");
        }
    }

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
        
        // Fase 3: Annuncia i risultati - per vincere i giocatori devono aver accumulato almeno un credito
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
    
    // Metodo helper per trovare un giocatore nella lista
    private Giocatore trovaGiocatorePerColore(List<Giocatore> giocatori, Colore colore) {
        for (Giocatore g : giocatori) {
            if (g.getColore() == colore) {
                return g;
            }
        }
        return null;
    }
}


