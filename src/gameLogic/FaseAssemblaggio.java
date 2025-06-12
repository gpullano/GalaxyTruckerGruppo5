package gameLogic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import carteAvventura.Mazzetto;
import plance.PlanceVolo;
import plance.PosizioneGiocatore;
import tessere.GeneratoreTessere;
import tessere.Tessera;

public class FaseAssemblaggio extends Fase {
	private static final int N_TESSERE = 156; 
    private Deque<Tessera> mucchioTessere;
    private List<Tessera> tessereScoperte;
    private Mazzetto[] mazzettiDiCarte;
    
    //costruttore
	public FaseAssemblaggio(List<Giocatore> giocatori, PlanceVolo planceVolo,  Mazzetto[] mazzettiDiCarte, ConsoleIO inputOutput) {
		super(giocatori, inputOutput, planceVolo);
		this.setMucchioTessere(creaMucchioTessere());
		
		// Le 3 pile inferiori - La pila in alto (mazzettiDiCarteAvventura[3]) è ignota
		this.mazzettiDiCarte = new Mazzetto[]{mazzettiDiCarte[0], mazzettiDiCarte[1], mazzettiDiCarte[2]}; 
		this.tessereScoperte = new ArrayList<>();	
	}
	
	//getter e setter
	public Deque<Tessera> getMucchioTessere() {
		return mucchioTessere;
	}

	public void setMucchioTessere(Deque<Tessera> mucchioTessere) {
		this.mucchioTessere = mucchioTessere;
	}
	
	
	//metodi
	private Deque<Tessera> creaMucchioTessere(){
    	Deque<Tessera> mucchio = new ArrayDeque<>();
    	for(int i = 0; i < N_TESSERE; i++) {
    		mucchio.add(GeneratoreTessere.generaTessere());
    	}
		return mucchio; 	
    }


	@Override
	public void eseguiFase() {
		
		// valutare se introdurre l'attributo "haAgganciato" in planceVolo o in Giocatore
		// in giocatore c'è l'attributo "assemblaggioTermianto"
		
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

					//scelta tra le seguenti opzioni:
					//1 - PESCARE UNA TESSERA
					//2 - TERMINARE ASSEMBLAGGIO
					//3 - GUARDARE MAZZI DI CARTE
					//4 - PRENDI TESSERA PRENOTATA 	
					//5 - PRENDI TESSERA SCOPERTA
						
					sceltaOpzioni = this.getInputOutput().chiediAzioneAssemblaggio(giocatore.getColore(),
							giocatore.getPlanceNave().isComponenteAgganciato(),
							giocatore.getPlanceNave().haTesserePrenotate(), 
							!this.tessereScoperte.isEmpty(), !this.mucchioTessere.isEmpty());
					
					
						switch(sceltaOpzioni) {
						case PESCA_TESSERA:{
							tesseraPescata = this.mucchioTessere.pop();
							sceltaTessera = this.getInputOutput().chiediAzioneDopoPescaggio(giocatore.getColore(), 
									false, tesseraPescata, giocatore.getPlanceNave().isSpazioTesserePrenotatePieno());
							
							//Dopo aver pescato una carta, posso:
							//1 - RUOTARLA
							//2 - AGGANCIARLA
							//3 - RIMETTERLA SUL TAVOLO
							//4 - PRENOTARLA PER DOPO
							
							switch(sceltaTessera) {
								case RUOTA_TESSERA:{
									this.getInputOutput().ruotaTessera(tesseraPescata);
									//Dopo averla ruotata, chiedo di agganciarla
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
									//sistema se il giocatore ha già il massimo di tessere prenotate
									giocatore.getPlanceNave().aggiungiTesseraPrenotata(tesseraPescata);
									break;
								}
								default:
									break;
							}
							break;
						}
						case TERMINA_ASSEMBLAGGIO:{ 
							PosizioneGiocatore posGiocatore = this.getPlanceVolo().getPosizioneDi(giocatore.getColore());
							posGiocatore.setPosizione(this.getGiocatori().size() - numAssemblaggiTerminati);
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
							sceltaTessera = this.getInputOutput().chiediAzioneDopoPescaggio(giocatore.getColore(), 
									true, tesseraPescata, giocatore.getPlanceNave().isSpazioTesserePrenotatePieno());
							
							//Dopo aver pescato una carta prenotata, posso:
							//1 - RUOTARLA
							//2 - AGGANCIARLA
							//3 - RIMETTERLA A POSTO
							
							switch(sceltaTessera) {
								case RUOTA_TESSERA:{
									this.getInputOutput().ruotaTessera(tesseraPescata);
									//Dopo averla ruotata, chiedo di agganciarla
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
							sceltaTessera = this.getInputOutput().chiediAzioneDopoPescaggio(giocatore.getColore(), 
									false, tesseraPescata, giocatore.getPlanceNave().isSpazioTesserePrenotatePieno());
							
							//Dopo aver pescato una carta scoperta, posso:
							//1 - RUOTARLA
							//2 - AGGANCIARLA
							//3 - RIMETTERLA SUL TAVOLO
							//4 - PRENOTARLA PER DOPO
							switch(sceltaTessera) {
								case RUOTA_TESSERA:{
									this.getInputOutput().ruotaTessera(tesseraPescata);
									//Dopo averla ruotata, chiedo di agganciarla
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
