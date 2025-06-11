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

/**
 * Gestisce la fase di preparazione al decollo.
 * In questa fase, le navi vengono controllate, le tessere illegali rimosse,
 * e vengono posizionati alieni ed equipaggio.
 */
public class FasePreparazioneDecollo extends Fase {
	
	/**
	 * Costruttore della fase di preparazione al decollo.
	 * @param giocatori La lista dei giocatori.
	 * @param inputOutput L'oggetto per l'interazione con la console.
	 * @param planceVolo La plancia di volo.
	 */
	public FasePreparazioneDecollo(List<Giocatore> giocatori, ConsoleIO inputOutput, PlanceVolo planceVolo) {
		super(giocatori, inputOutput, planceVolo);
	}

	/**
	 * Esegue la logica della fase di preparazione per ogni giocatore.
	 * Include la verifica della legalità della nave e il posizionamento di equipaggio/alieni.
	 */
	@Override
	public void eseguiFase() {
		this.getInputOutput().inizioPreparazioneAlDecollo();

		// Ciclo per ogni giocatore.	
		for (Giocatore giocatoreDaControllare : getGiocatori()) {
			this.getInputOutput().stampaMessaggio("\nGIOCATORE " + giocatoreDaControllare.getColore());
			
			// 1. Rimuove tessere che violano le regole di posizionamento (es. cannoni/motori errati).
			GestorePlanceNave.verificaERimuoviTessereIllegali(giocatoreDaControllare.getPlanceNave());
			
			// 2. Rimuove le tessere non connesse alla struttura principale della nave.
			GestorePlanceNave.gestisciRimozioneOrfani(giocatoreDaControllare.getPlanceNave());
			
			// 3. Posiziona alieni o equipaggio nelle cabine.
			posizionaAlieniEdEquipaggio(giocatoreDaControllare);
			
			// 4. Calcola il totale delle batterie disponibili sulla nave.
			giocatoreDaControllare.getPlanceNave().calcolaQtBatterie(0);	
			
			this.getInputOutput().stampaNave(giocatoreDaControllare.getPlanceNave());
		}
	}
	
	/**
	 * Unisce i mazzetti di carte in un unico mazzo di avventura per la fase di volo.
	 * @param mazzettiDiCarte Array dei mazzetti da unire.
	 * @return Una lista di carte che rappresenta il mazzo unico.
	 */
	public List<Carta> creaMazzoUnico(Mazzetto[] mazzettiDiCarte) {
    	List<Carta> mazzoUnico = new LinkedList<>();

        if (mazzettiDiCarte != null) {
            for (Mazzetto mazzettoCorrente : mazzettiDiCarte) {
                if (mazzettoCorrente != null && mazzettoCorrente.getCarte() != null) {
                    mazzoUnico.addAll(mazzettoCorrente.getCarte());
                }
            }
        }
        return mazzoUnico;
    }
	
	/**
	 * Gestisce il posizionamento di alieni o equipaggio nelle cabine di un giocatore.
	 * Chiede al giocatore se desidera posizionare un alieno se una cabina è connessa
	 * a un supporto vitale disponibile.
	 * @param giocatore Il giocatore per cui effettuare il posizionamento.
	 */
	public void posizionaAlieniEdEquipaggio(Giocatore giocatore) {
		Casella[][] caselle = giocatore.getPlanceNave().getCaselle();
		OpzioniSupportoVitale opzioniSupportoVitale;
		boolean presenti = false;
		
		this.getInputOutput().posizionamentoAlieni(giocatore.getColore());
		
		for (int i = 0; i < PlanceNaveLivello1.getNumRighe(); i++) {
			for (int j = 0; j < PlanceNaveLivello1.getNumColonne(); j++) {
				if (caselle[i][j].getTessera() instanceof Cabina cabina) {
					presenti = true;
					opzioniSupportoVitale = supportoIntornoCabina(caselle, i, j);
					
					switch (opzioniSupportoVitale) {
						case SUPPORTO_VITALE_VIOLA:
							if (!giocatore.getPlanceNave().HaAlienoViola()) {
							    boolean vuolePiazzare = this.getInputOutput().chiediSePosizionareAlieno("Vuoi posizionare un alieno viola?");
							    if (vuolePiazzare) {
							        cabina.setAlienoViola(true);
							        giocatore.getPlanceNave().setHaAlienoViola(true);
							    } else {
							        cabina.setEquipaggio(Cabina.getNumEquipaggio());
							    }
							}
							break;
						
						case SUPPORTO_VITALE_MARRONE:
							if (!giocatore.getPlanceNave().HaAlienoMarrone()) {
							    boolean vuolePiazzare = this.getInputOutput().chiediSePosizionareAlieno("Vuoi posizionare un alieno marrone?");
							    if (vuolePiazzare) {
							        cabina.setAlienoMarrone(true); 
							        giocatore.getPlanceNave().setHaAlienoMarrone(true);
							    } else {
							        cabina.setEquipaggio(Cabina.getNumEquipaggio());
							    }
							}
							break;
						
						case NESSUN_SUPPORTO:
							cabina.setEquipaggio(Cabina.getNumEquipaggio());
							giocatore.getPlanceNave().aggiungiEquipaggio(Cabina.getNumEquipaggio());
							break;
					}
				}
			}
		}
		
		if (!presenti) {
			this.getInputOutput().stampaMessaggio("Nessuna cabina collegata ad un SUPPORTO VITALE\n" +
						"le cabine (se esistenti) verranno riempite di ASTRONAUTI.");
		}
	}
	
	/**
	 * Controlla se una cabina in una data posizione è connessa a un supporto vitale.
	 * @param caselle La griglia della plancia nave.
	 * @param riga La riga della cabina.
	 * @param colonna La colonna della cabina.
	 * @return Il tipo di supporto vitale trovato, o NESSUN_SUPPORTO se non ce ne sono.
	 */
	public OpzioniSupportoVitale supportoIntornoCabina(Casella[][] caselle, int riga, int colonna) {
	    int[] dr = { -1, 0, 1, 0 }; // NORD, EST, SUD, OVEST (righe)
	    int[] dc = { 0, 1, 0, -1 }; // NORD, EST, SUD, OVEST (colonne)

	    Tessera cabina = caselle[riga][colonna].getTessera();
	    Tessera tesseraVicina;
	    Casella casellaVicina;
	    Connettore connettoreDellaCabina;
	    Connettore connettoreDelSupporto;

	    // Itera sui 4 vicini
	    for (int i = 0; i < 4; i++) {
	        int rigaVicino = riga + dr[i];
	        int colonnaVicino = colonna + dc[i];

	        if (rigaVicino >= 0 && rigaVicino < PlanceNaveLivello1.getNumRighe() &&
	            colonnaVicino >= 0 && colonnaVicino < PlanceNaveLivello1.getNumColonne()) {
	            
	            casellaVicina = caselle[rigaVicino][colonnaVicino];

	            if (casellaVicina.getTessera() != null) {
	                tesseraVicina = casellaVicina.getTessera();

	                if (tesseraVicina instanceof SupportoVitaleViola || tesseraVicina instanceof SupportoVitaleMarrone) {
	                    connettoreDellaCabina = GestorePlanceNave.getLato(cabina, i);
	                    connettoreDelSupporto = GestorePlanceNave.getLatoOpposto(tesseraVicina, i);
	                    
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

	    return OpzioniSupportoVitale.NESSUN_SUPPORTO;
	}
}