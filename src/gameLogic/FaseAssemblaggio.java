package gameLogic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import carteAvventura.Mazzetto;
import plance.PlanceVolo;
import tessere.GeneratoreTessere;
import tessere.Tessera;

/**
*la classe FaseAssemblaggio rappresenta la fase di gioco in cui i giocatori costruiscono le loro navi.
*/
public class FaseAssemblaggio extends Fase {
	private static final int N_TESSERE = 20; // TODO - da modificare con 156 per il gioco vero
    private Deque<Tessera> mucchioTessere;
    private List<Tessera> tessereScoperte;
    private Mazzetto[] mazzettiDiCarte;
    
    /**
    *costruttore della classe FaseAssemblaggio.
    *@param giocatori la lista dei giocatori che partecipano alla fase.
    *@param planceVolo la plancia di volo comune.
    *@param mazzettiDiCarte i mazzetti di carte avventura disponibili.
    *@param inputOutput l'oggetto per gestire l'input e l'output.
    */
	public FaseAssemblaggio(List<Giocatore> giocatori, PlanceVolo planceVolo,  Mazzetto[] mazzettiDiCarte, ConsoleIO inputOutput) {
		super(giocatori, inputOutput, planceVolo);
		this.setMucchioTessere(creaMucchioTessere());
		
		// Le 3 pile inferiori - La pila in alto (mazzettiDiCarteAvventura[3]) è ignota
		this.mazzettiDiCarte = new Mazzetto[]{mazzettiDiCarte[0], mazzettiDiCarte[1], mazzettiDiCarte[2]}; 
		this.tessereScoperte = new ArrayList<>();	
	}
	
	/**
	*restituisce il mucchio delle tessere.
	*@return il mucchio (deque) delle tessere.
	*/
	public Deque<Tessera> getMucchioTessere() {
		return mucchioTessere;
	}

	/**
	*imposta il mucchio delle tessere.
	*@param mucchioTessere il nuovo mucchio di tessere.
	*/
	public void setMucchioTessere(Deque<Tessera> mucchioTessere) {
		this.mucchioTessere = mucchioTessere;
	}
	
	/**
	*crea e restituisce il mazzo iniziale di tessere.
	*@return un deque contenente le tessere generate.
	*/
	private Deque<Tessera> creaMucchioTessere(){
    	Deque<Tessera> mucchio = new ArrayDeque<>();
    	for(int i = 0; i < N_TESSERE; i++) {
    		mucchio.add(GeneratoreTessere.generaTessere());
    	}
		return mucchio; 	
    }

	/**
	*esegue il ciclo principale della fase di assemblaggio, gestendo i turni dei giocatori.
	*/
	@Override
	public void eseguiFase() {
		
		AzioneAssemblaggio sceltaOpzioni = null;
		AzioneAssemblaggio sceltaTessera = null;
		Tessera tesseraPescata = null;
		int numAssemblaggiTerminati = 0;
		
		this.getInputOutput().inizioAssemblaggio();
		
		//ciclo che va avanti finché tutti non hanno terminato l'assemblaggio
		while(numAssemblaggiTerminati < this.getGiocatori().size()){
			
			//for per scorrere ogni giocatore
			for(Giocatore giocatore : this.getGiocatori()) {
				//se il giocatore non ha terminato l'assemblaggio gli do la possibilità di compiere azioni
				if(!giocatore.isAssemblaggioTerminato())
				{
									
					getInputOutput().stampaSetupAssemblaggio(giocatore.getColore(), giocatore.getPlanceNave(), 
							giocatore.getPlanceNave().getTesserePrenotate(), tessereScoperte);
					
					sceltaOpzioni = this.getInputOutput().chiediAzioneAssemblaggio(giocatore.getColore(),
							giocatore.getPlanceNave().isComponenteAgganciato(),
							giocatore.getPlanceNave().haTesserePrenotate(), 
							!this.tessereScoperte.isEmpty(), !this.mucchioTessere.isEmpty());
					
					
						switch(sceltaOpzioni) {
						case PESCA_TESSERA:{
							tesseraPescata = this.mucchioTessere.pop();
							sceltaTessera = this.getInputOutput().chiediAzioneSulleTessere(giocatore.getColore(), 
									false, tesseraPescata, giocatore.getPlanceNave().isSpazioTesserePrenotatePieno());
							
							switch(sceltaTessera) {
								case RUOTA_TESSERA:{
									this.getInputOutput().ruotaTessera(tesseraPescata);
									this.getInputOutput().stampaMessaggio("\nAGGANCIA la tessera:\n");
									this.getInputOutput().agganciaTessera(giocatore, tesseraPescata);
									giocatore.getPlanceNave().setComponenteAgganciato(true);
									break;
								}
								case AGGANCIA_TESSERA:{
									this.getInputOutput().agganciaTessera(giocatore, tesseraPescata);
									giocatore.getPlanceNave().setComponenteAgganciato(true);
									break;
								}
								case RIMETTI_TESSERA_A_POSTO:{
									this.tessereScoperte.add(tesseraPescata);
									break;
								}
								case PRENOTA_TESSERA:{
									giocatore.getPlanceNave().aggiungiTesseraPrenotata(tesseraPescata);
									break;
								}
								default:
									break;
							}
							break;
						}
						case TERMINA_ASSEMBLAGGIO:{
							giocatore.terminaAssemblaggio();
							numAssemblaggiTerminati++;
							break;
						}
						case GUARDA_MAZZETTI_CARTE: {
							this.getInputOutput().guardaMazzettoScelto(this.mazzettiDiCarte);
							break;
						}
						case PRENDI_TESSERA_PRENOTATA:{
							tesseraPescata = this.getInputOutput().chiediTesseraPrenotata(giocatore.getPlanceNave().getTesserePrenotate());
							sceltaTessera = this.getInputOutput().chiediAzioneSulleTessere(giocatore.getColore(), 
									true, tesseraPescata, giocatore.getPlanceNave().isSpazioTesserePrenotatePieno());
							
							switch(sceltaTessera) {
								case RUOTA_TESSERA:{
									this.getInputOutput().ruotaTessera(tesseraPescata);
									this.getInputOutput().stampaMessaggio("\nAGGANCIA la tessera:\n");
									this.getInputOutput().agganciaTessera(giocatore, tesseraPescata);
									giocatore.getPlanceNave().setComponenteAgganciato(true);
									break;
								}
								case AGGANCIA_TESSERA:{
									this.getInputOutput().agganciaTessera(giocatore, tesseraPescata);
									giocatore.getPlanceNave().setComponenteAgganciato(true);
									break;
								}
								case RIMETTI_TESSERA_A_POSTO:{
									giocatore.getPlanceNave().aggiungiTesseraPrenotata(tesseraPescata);
									break;
								}
								default:
									break;
							}
							break;
						}
						case PRENDI_TESSERA_SCOPERTA:{
							tesseraPescata = this.getInputOutput().chiediTesseraScopertaDaPescare(tessereScoperte);
							sceltaTessera = this.getInputOutput().chiediAzioneSulleTessere(giocatore.getColore(), 
									false, tesseraPescata, giocatore.getPlanceNave().isSpazioTesserePrenotatePieno());
							
							switch(sceltaTessera) {
								case RUOTA_TESSERA:{
									this.getInputOutput().ruotaTessera(tesseraPescata);
									this.getInputOutput().stampaMessaggio("\nAGGANCIA la tessera:\n");
									this.getInputOutput().agganciaTessera(giocatore, tesseraPescata);
									giocatore.getPlanceNave().setComponenteAgganciato(true);
									break;
								}
								case AGGANCIA_TESSERA:{
									this.getInputOutput().agganciaTessera(giocatore, tesseraPescata);
									giocatore.getPlanceNave().setComponenteAgganciato(true);
									break;
								}
								case RIMETTI_TESSERA_A_POSTO:{
									this.tessereScoperte.add(tesseraPescata);
									break;
								}
								case PRENOTA_TESSERA:{
									giocatore.getPlanceNave().aggiungiTesseraPrenotata(tesseraPescata);
									break;
								}
								default:
									break;
							}
						}
						default:
							break;
						}
					this.getInputOutput().stampaNave(giocatore.getPlanceNave());
				}
			}
		}
	}
}
