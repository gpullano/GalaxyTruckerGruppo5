package carteAvventura;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gameLogic.ConsoleIO;
import gameLogic.Giocatore;
import plance.GestorePlanceNave;
import plance.PlanceNaveLivello1;
import plance.PlanceVolo;
import plance.Posizione;

public class Pirati extends CartaPerditaGiorniVolo {
	private final int potenzaFuoco;
	private final int creditiCosmici;
	private int numeroCannonate;
	private Cannonata[] cannonate;
	private Dadi dadi;
	
	public Pirati(int livello) {
		super(livello);
		Random rand=new Random();
		this.potenzaFuoco=rand.nextInt(2)+5;
		this.creditiCosmici=rand.nextInt(3)+5;
		numeroCannonate=rand.nextInt(4)+1;
		cannonate=new Cannonata[numeroCannonate];
		for (int i = 0;i < cannonate.length; i++) {
			cannonate[i]=new Cannonata(Dimensione.generaDimensione(),Provenienza.generaProvenienza());
		}
		this.dadi = new Dadi();
	}

	public int getPotenzaFuoco() {
		return potenzaFuoco;
	}

	public int getCreditiCosmici() {
		return creditiCosmici;
	}
	public int getNumeroCannonate() {
		return numeroCannonate;
	}

	public void setNumeroCannonate(int numeroCannonate) {
		this.numeroCannonate = numeroCannonate;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Pirati - Livello: ").append(getLivello()).append("\n");
		sb.append("I Pirati hanno una potenza di fuoco = ").append(getPotenzaFuoco()).append("\n");
		sb.append("- Se il giocatore vince ").append("\n");
		sb.append("  - Guadagna ").append(getCreditiCosmici() + " ").append("crediti cosmici\n");
		sb.append("  Effetto: perdita di giorni di volo ").append(getGiorniVoloPersi()).append("\n");
		sb.append("  Per evitare la perdita di giorni di volo, puoi rinunciare alla ricompensa").append("\n\n");
		
		sb.append("- Se il giocatore perde ").append("\n");
        sb.append("La tua nave verrà colpita da ").append(getNumeroCannonate() + " ").append("cannonate\n");
		for(int i = 0; i < cannonate.length; i++) {
			sb.append(" - Cannonata ").append(i +1).append(": ").append(cannonate[i].getDimensione()).append(" da ").append(cannonate[i].getProvenienza()).append("\n");
		}
		
		return sb.toString();
	}



	@Override
	public void attiva(List<Giocatore> giocatore, PlanceVolo planceVolo, ConsoleIO inputOutput) {
		inputOutput.stampaMessaggio(this.toString()); // Stampa le info della carta all'inizio
		int i=0;
		boolean piratisconfitti=false;
		List<Giocatore> giocatoriSconfitti=new ArrayList<>();
		while(i<giocatore.size()&&!piratisconfitti) {
			// controllare se la potenza fuoco dei pirati è maggiore
			Giocatore giocatoreCorrente = giocatore.get(i);
			inputOutput.stampaMessaggio("\n--- Turno del Giocatore " + giocatoreCorrente.getColore() + " ---");
			int potenzaDiFuocoGiocatoreCorrente = giocatoreCorrente.getPlanceNave().getPotenzaFuoco(inputOutput);
			if (potenzaDiFuocoGiocatoreCorrente < this.potenzaFuoco) {
				// aggiungo nella lista nella posizione i-esima il giocatore sconfitto 
				giocatoriSconfitti.add(giocatore.get(i));
				
				inputOutput.stampaMessaggio("GIOCATORE " + giocatoreCorrente.getColore() + " Hai una potenza di fuoco minore dei Pirati. TI HANNO SCONFITTO.");
				
			}else {
				piratisconfitti=true;
				// ottieni tot crediti cosmici 
				giocatore.get(i).aggiungiCrediti(this.creditiCosmici);
				// perdi giorni di volo 
				planceVolo.getPosizioneGiocatori().get(i).aggiornaPosizione(getGiorniVoloPersi());
				inputOutput.stampaMessaggio("GIOCATORE " + giocatoreCorrente.getColore() + " Hai una potenza di fuoco maggiore dei Pirati. LI HAI SCONFITTI.");

				
			}
			i++;
		}
		if (!giocatoriSconfitti.isEmpty() && !piratisconfitti) {
			StringBuilder messaggioGiocatoriSconfitti = new StringBuilder("\nI seguenti giocatori subiranno l'attacco dei Pirati: ");
			for (int j = 0; j < giocatoriSconfitti.size(); j++) {
			    Giocatore giocatoreSconfitto = giocatoriSconfitti.get(j);
			    
			    // 3. Aggiungi il colore del giocatore alla stringa.
			    messaggioGiocatoriSconfitti.append(giocatoreSconfitto.getColore().toString().toUpperCase());
			    
			    // 4. Se non è l'ultimo giocatore della lista, aggiungi una virgola e uno spazio per separare.
			    if (j < giocatoriSconfitti.size() - 1) {
			        messaggioGiocatoriSconfitti.append(", ");
			    }
			}

			// 5. Stampa la stringa completa che abbiamo costruito.
			inputOutput.stampaMessaggio(messaggioGiocatoriSconfitti.toString());
	        
	        // Ogni cannonata viene sparata contro tutti i giocatori sconfitti
	        for (Cannonata cannonataCorrente : this.cannonate) {
	            int risultatoLancioDadi = dadi.lancia();
	            inputOutput.lancioDeiDadi(planceVolo.getPosizioneGiocatori().get(0).getColore(), risultatoLancioDadi); // Dado generico
	            
	            for (Giocatore giocatoreDaColpire : giocatoriSconfitti) {
	                inputOutput.stampaMessaggio("\n--- Cannonata contro " + giocatoreDaColpire.getColore() + " ---");
	                
	                // 1. Trova la posizione colpita
	                Posizione posizioneColpita = GestorePlanceNave.trovaComponenteColpito(
	                    giocatoreDaColpire.getPlanceNave(),
	                    cannonataCorrente.getProvenienza(),
	                    risultatoLancioDadi
	                );

	                // 2. Applica il danno se un componente è stato effettivamente colpito
	                if (posizioneColpita == null) {
	                    inputOutput.pericoloScampato();
	                } else {
	                    PlanceNaveLivello1 naveColpita = giocatoreDaColpire.getPlanceNave();
	                    boolean colpoAnnullato = false; // Flag per sapere se il colpo viene fermato

	                    // Logica di difesa specifica per la cannonata
	                    switch (cannonataCorrente.getDimensione()) {
	                        case GROSSO:
	                            // Le cannonate grosse non possono essere bloccate da scudi.
	                            inputOutput.stampaMessaggio("Una cannonata pesante colpisce la nave! Impossibile difendersi.");
	                            colpoAnnullato = false;
	                            break;
	                            
	                        case PICCOLO:
	                            // Le cannonate piccole possono essere bloccate da scudi.
	                            // Controlla se c'è uno scudo che punta nella direzione giusta
	                            if (naveColpita.utilizzoScudo(cannonataCorrente.getProvenienza())) {
	                                // Se c'è uno scudo, controlla se ci sono batterie
	                                if (naveColpita.haBatterie()) {
	                                    boolean vuoleUsareScudo = inputOutput.chiediSeEseguireAzione(
	                                        "Una cannonata leggera minaccia la nave. Vuoi usare 1 batteria per attivare lo scudo?"
	                                    );
	                                    if (vuoleUsareScudo) {
	                                        naveColpita.aggiungiBatterie(-1); // Consuma la batteria
	                                        inputOutput.stampaMessaggio("SCUDO ATTIVATO! La cannonata è stata bloccata.");
	                                        colpoAnnullato = true;
	                                    } else {
	                                        inputOutput.stampaMessaggio("Hai scelto di non attivare lo scudo.");
	                                    }
	                                } else {
	                                    inputOutput.stampaMessaggio("Hai uno scudo ma non hai batterie per attivarlo!");
	                                }
	                            } else {
	                                inputOutput.stampaMessaggio("Nessuno scudo protegge da questa direzione.");
	                            }
	                            break;
	                    }

	                    // 3. Se il colpo non è stato annullato, distruggi il componente
	                    if (!colpoAnnullato) {
	                        inputOutput.stampaMessaggio("COLPITO! Il componente in posizione " + "(" + (posizioneColpita.getRiga() + 5) + ", " + (posizioneColpita.getColonna() + 4) + ")" + " è stato distrutto.");
	                        inputOutput.stampaNave(naveColpita);
	                        // Distruzione manuale come da tua richiesta
	                        naveColpita.getCaselle()[posizioneColpita.getRiga()][posizioneColpita.getColonna()].setTessera(null);
	                        naveColpita.incrementaPilaScarti();
	                        GestorePlanceNave.gestisciRimozioneOrfani(naveColpita);
	                    }
	                }
	            }
	        }
	    }
	}

	
}
