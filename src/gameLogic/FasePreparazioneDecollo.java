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
*la classe FasePreparazioneDecollo gestisce la fase di preparazione al decollo, che include la validazione delle navi e il posizionamento di alieni ed equipaggio.
*/
public class FasePreparazioneDecollo extends Fase {
	
	/**
	*costruttore della classe FasePreparazioneDecollo.
	*@param giocatori la lista dei giocatori.
	*@param inputOutput l'oggetto per l'input/output.
	*@param planceVolo la plancia di volo comune.
	*/
	public FasePreparazioneDecollo(List<Giocatore> giocatori, ConsoleIO inputOutput, PlanceVolo planceVolo) {
		super(giocatori, inputOutput, planceVolo);
	}

	/**
	*esegue la logica della fase di preparazione, validando le navi dei giocatori e gestendo il posizionamento dell'equipaggio.
	*/
	@Override
	public void eseguiFase() {
		
		this.getInputOutput().inizioPreparazioneAlDecollo();

		//Ciclo per ogni giocatore.	
		for(Giocatore giocatoreDaControllare : getGiocatori()) {
			//1. Vengono prima rimossi tutte le tessere che violano le regole di posizionamento:
			GestorePlanceNave.verificaERimuoviTessereIllegali(giocatoreDaControllare.getPlanceNave());
			
			//2. Vengono rimosse le tessere orfane
			GestorePlanceNave.gestisciRimozioneOrfani(giocatoreDaControllare.getPlanceNave());
			
			//Conta degli alieni e dell'equipaggio
			posizionaAlieniEdEquipaggio(giocatoreDaControllare);
			
			//Calcolo delle batterie
			giocatoreDaControllare.getPlanceNave().calcolaQtBatterie(0);		
		}
	}
	
	/**
	*unisce i mazzetti di carte in un unico mazzo di avventura.
	*@param mazzettiDiCarte l'array dei mazzetti da unire.
	*@return una nuova lista contenente tutte le carte dei mazzetti.
	*/
	public List<Carta> creaMazzoUnico(Mazzetto[] mazzettiDiCarte){
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
	*gestisce il posizionamento degli alieni o dell'equipaggio nelle cabine della nave di un giocatore.
	*@param giocatore il giocatore per cui effettuare il posizionamento.
	*/
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
						}
						
						case SUPPORTO_VITALE_MARRONE:{
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
						}
						
						case NESSUN_SUPPORTO:{
							cabina.setEquipaggio(Cabina.getNumEquipaggio());
							giocatore.getPlanceNave().aggiungiEquipaggio(Cabina.getNumEquipaggio());
							break;
						}
					}
				}
			}
		}
	}
	
	/**
	*controlla le tessere adiacenti a una cabina per verificare la presenza di un supporto vitale connesso.
	*@param caselle la griglia della plancia nave.
	*@param riga la riga della cabina.
	*@param colonna la colonna della cabina.
	*@return il tipo di supporto vitale trovato o nessuno se non presente.
	*/
	public OpzioniSupportoVitale supportoIntornoCabina(Casella[][] caselle, int riga, int colonna) {
	    int[] dr = {-1, 0, 1, 0};
	    int[] dc = {0, 1, 0, -1};

	    Tessera cabina = caselle[riga][colonna].getTessera();
	    Tessera tesseraVicina;
	    Casella casellaVicina;
	    Connettore connettoreDellaCabina;
	    Connettore connettoreDelSupporto;

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