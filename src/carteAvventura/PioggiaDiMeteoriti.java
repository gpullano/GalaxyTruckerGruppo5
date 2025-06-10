package carteAvventura;
import java.util.List;
import java.util.Random;

import dadiEClessidra.Dadi;
import gameLogic.ConsoleIO;
import gameLogic.Giocatore;
import plance.Casella;
import plance.GestorePlanceNave;
import plance.PlanceNave;
import plance.PlanceNaveLivello1;
import plance.Posizione;

public class PioggiaDiMeteoriti extends Carta {
	// attributi
	private final int numeroMeteoriti;
	private Meteorite[] meteoriti;
	private Dadi dadi;
	
	// costruttore
	public PioggiaDiMeteoriti(int livello) {
		super(livello);
		Random rand=new Random();
		numeroMeteoriti=rand.nextInt(4)+1;
		meteoriti=new Meteorite[numeroMeteoriti];
		for (int i=0;i<meteoriti.length;i++) {
			meteoriti[i]=new Meteorite(Dimensione.generaDimensione(),Provenienza.generaProvenienza());
		}
		this.dadi = new Dadi();
	}

	public int getNumeroMeteoriti() {
		return numeroMeteoriti;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Pioggia Di Meteoriti - Livello: ").append(getLivello()).append("\n");
		sb.append("La tua nave verrà colpita da ").append(getNumeroMeteoriti() + " ").append("meteoriti\n");
		
		for(int i = 0; i < meteoriti.length; i++) {
			sb.append(" - Meteorite ").append(i +1).append(": ").append(meteoriti[i].getDimensione()).append(" da ").append(meteoriti[i].getProvenienza()).append("\n");
		}
		
		
		return sb.toString();
	}
	
	//TODO - da completare
	//Ricordati di chiamare la stampa della carta.
	public void attiva(List<Giocatore> giocatori, ConsoleIO inputOutput) {
	    
	    inputOutput.stampaMessaggio(this.toString()); // Stampa le info della carta all'inizio
	    
	    Meteorite meteoriteCorrente;
	    int risultatoLancioDadi;
	    // Scorro per ogni meteorite
	    for (int i = 0; i < numeroMeteoriti; i++) {
	        meteoriteCorrente = this.meteoriti[i];
	        risultatoLancioDadi = this.dadi.lancia();
	        
	        inputOutput.lancioDeiDadi(giocatori.get(0).getColore(), risultatoLancioDadi);

	        // Per ogni meteorite, colpisci tutti i giocatori
	        for (Giocatore giocatore : giocatori) {
	            inputOutput.stampaMessaggio("\n--- Turno di " + giocatore.getColore() + " ---");

	            // 1. Trova dove colpisce il meteorite
	            Posizione posColpita = GestorePlanceNave.trovaComponenteColpito(giocatore.getPlanceNave(), meteoriteCorrente.getProvenienza(), risultatoLancioDadi);

	            // 2. Delega tutta la logica di impatto e difesa al Gestore
	            RisultatoImpatto risultato = GestorePlanceNave.gestisciImpattoMeteorite(giocatore.getPlanceNave(), meteoriteCorrente, posColpita, inputOutput);

	            // 3. Stampa il risultato in base a ciò che è successo
	            switch (risultato) {
	                case MANCATO:
	                    inputOutput.pericoloScampato();
	                    break;
	                case SALVATO_DA_LATO_LISCIO:
	                    inputOutput.stampaMessaggio("METEORITE DEVIATO! Ha colpito un lato liscio.");
	                    break;
	                case SALVATO_DA_SCUDO:
	                    inputOutput.stampaMessaggio("METEORITE DEVIATO! Hai attivato uno scudo.");
	                    break;
	                case SALVATO_DA_CANNONE:
	                    inputOutput.stampaMessaggio("METEORITE DISTRUTTO! I tuoi cannoni hanno fatto centro.");
	                    break;
	                case DISTRUTTO:
	                    // Il messaggio di distruzione viene già stampato da GestorePlanceNave
	                    // Potresti aggiungere un messaggio generico qui se vuoi
	                    inputOutput.stampaMessaggio("La tua nave ha subito danni!");
	                    break;
	            }
	        }
	    }
	}

}
