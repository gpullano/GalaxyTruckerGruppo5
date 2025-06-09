package gameLogic;

import java.util.LinkedList;
import java.util.List;

import carteAvventura.Carta;
import carteAvventura.Mazzetto;
import plance.Casella;
import plance.GestorePlanceNave;
import plance.OpzioniSupportoVitale;
import plance.PlanceNaveLivello1;
import plance.PlanceVolo;
import tessere.Cabina;
import tessere.Connettore;
import tessere.SupportoVitaleMarrone;
import tessere.SupportoVitaleViola;
import tessere.Tessera;

public class FasePreparazioneDecollo extends Fase {
	

	public FasePreparazioneDecollo(List<Giocatore> giocatori, ConsoleIO inputOutput, PlanceVolo planceVolo) {
		super(giocatori, inputOutput, planceVolo);
	}

	@Override
	public void eseguiFase() {
		
		this.getInputOutput().inizioPreparazioneAlDecollo();

		
		//Ciclo per ogni giocatore.	
		for(Giocatore giocatoreDaControllare : getGiocatori()) {
			//1. Vengono prima rimossi tutte le tessere che violano le regole di posizionamento:
			//- cannoni che puntano verso altre tessere
			//- motori che non puntano verso "dietro"
			//...
			GestorePlanceNave.verificaERimuoviTessereIllegali(giocatoreDaControllare.getPlanceNave());
			
			//2. Vengono rimosse le tessere orfane - non si verifica che tutti i lati siano connessi, ma
			//solo che le connessioni siano legali
			GestorePlanceNave.gestisciRimozioneOrfani(giocatoreDaControllare.getPlanceNave());
			
			//Conta degli alieni e dell'equipaggio
			posizionaAlieniEdEquipaggio(giocatoreDaControllare);
			
			//Calcolo delle batterie
			giocatoreDaControllare.getPlanceNave().calcolaQtBatterie();		
		}
	}
	
	/**
	 * Unisce i 4 mazzetti disponibili durante l'assemblaggio a formare il mazzo avventura completo.
	 * @param mazzettiDiCarte
	 * @return Una nuova lista di carte che rappresenta il mazzo unico.
	 */
	public List<Carta> creaMazzoUnico(Mazzetto[] mazzettiDiCarte){
    	List<Carta> mazzoUnico = new LinkedList<>(); // Inizializza il nuovo mazzo unico

        if (mazzettiDiCarte != null) {
            for (Mazzetto mazzettoCorrente : mazzettiDiCarte) {
                if (mazzettoCorrente != null && mazzettoCorrente.getCarte() != null) {
                    // Aggiunge tutte le carte del mazzettoCorrente al mazzoUnico
                    mazzoUnico.addAll(mazzettoCorrente.getCarte());
                }
            }
        }
        return mazzoUnico;
    }
	
	
	public void posizionaAlieniEdEquipaggio(Giocatore giocatore) {
		Casella[][] caselle = giocatore.getPlanceNave().getCaselle();
		OpzioniSupportoVitale opzioniSupportoVitale;
		this.getInputOutput().posizionamentoAlieni();
		for(int i = 0; i < PlanceNaveLivello1.getNumRighe(); i++) {
			for(int j = 0; j < PlanceNaveLivello1.getNumColonne(); j++) {
				if(caselle[i][j].getTessera() instanceof Cabina cabina) {
					opzioniSupportoVitale = supportoIntornoCabina(caselle, i, j);
					
					switch(opzioniSupportoVitale) {
						case SUPPORTO_VITALE_VIOLA:{
							//Il giocatore non ha nessun alieno viola?
							if (!giocatore.getPlanceNave().HaAlienoViola()) {
							    boolean vuolePiazzare = this.getInputOutput().chiediSePosizionareAlieno("Vuoi posizionare un alieno viola?");
							    if (vuolePiazzare) {
							        cabina.setAlienoViola(true);
							        giocatore.getPlanceNave().setHaAlienoViola(true);
							    } else {
							        cabina.setEquipaggio();
							    }
							}
							
							break;
						}
						
						case SUPPORTO_VITALE_MARRONE:{
							//Il giocatore non ha nessun alieno marrone?
							if (!giocatore.getPlanceNave().HaAlienoMarrone()) {
							    boolean vuolePiazzare = this.getInputOutput().chiediSePosizionareAlieno("Vuoi posizionare un alieno marrone?");
							    if (vuolePiazzare) {
							        cabina.setAlienoMarrone(true); 
							        giocatore.getPlanceNave().setHaAlienoMarrone(true);
							    } else {
							        cabina.setEquipaggio();
							    }
							}
							break;
						}
						
						case NESSUN_SUPPORTO:{
							cabina.setEquipaggio();
							giocatore.getPlanceNave().aggiungiEquipaggio(Cabina.getNumEquipaggio());
							break;
						}
					}
				}
			}
		}
	}
	
	public OpzioniSupportoVitale supportoIntornoCabina(Casella[][] caselle, int riga, int colonna) {
	    // Array per iterare sui 4 vicini: NORD, EST, SUD, OVEST
	    int[] dr = {-1, 0, 1, 0};
	    int[] dc = {0, 1, 0, -1};

	    // inizializzo le variabili necessarie
	    Tessera cabina = caselle[riga][colonna].getTessera();
	    Tessera tesseraVicina;
	    Casella casellaVicina;
	    Connettore connettoreDellaCabina;
	    Connettore connettoreDelSupporto;

	    // Itera su tutti e 4 i possibili vicini
	    for (int i = 0; i < 4; i++) {
	        int rigaVicino = riga + dr[i];
	        int colonnaVicino = colonna + dc[i];

	        // 1. Controlla se il vicino è dentro i limiti della plancia
	        if (rigaVicino >= 0 && rigaVicino < PlanceNaveLivello1.getNumRighe() &&
	            colonnaVicino >= 0 && colonnaVicino < PlanceNaveLivello1.getNumColonne()) {
	            
	            casellaVicina = caselle[rigaVicino][colonnaVicino];

	            // 2. Controlla se il vicino è occupato
	            if (casellaVicina.getTessera() != null) {
	                tesseraVicina = casellaVicina.getTessera();

	                // 3. Controlla se il vicino è effettivamente un supporto vitale
	                if (tesseraVicina instanceof SupportoVitaleViola || tesseraVicina instanceof SupportoVitaleMarrone) {
	                                      
	                    // Ottieni i connettori corretti da confrontare usando gli helper di GestorePlanceNave
	                    connettoreDellaCabina = GestorePlanceNave.getLato(cabina, i);
	                    connettoreDelSupporto = GestorePlanceNave.getLatoOpposto(tesseraVicina, i);
	                    
	                    // 4. Usa il gestore per verificare se la connessione è valida
	                    if (GestorePlanceNave.possonoConnettersi(connettoreDellaCabina, connettoreDelSupporto)) {
	                        if (tesseraVicina instanceof SupportoVitaleViola) {
	                            return OpzioniSupportoVitale.SUPPORTO_VITALE_VIOLA;
	                        } else {
	                            return OpzioniSupportoVitale.SUPPORTO_VITALE_MARRONE;
	                        }
	                    }
	                }
	            }
	        }
	    }

	    // Non è stato trovato nessun supporto vitale validamente connesso.
	    return OpzioniSupportoVitale.NESSUN_SUPPORTO;
	}

}
