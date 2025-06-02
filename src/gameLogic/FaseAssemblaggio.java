package gameLogic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import carteAvventura.Mazzetto;
import dadiEClessidra.Clessidra;
import plance.PlanceVolo;
import tessere.GeneratoreTessere;
import tessere.Tessera;

public class FaseAssemblaggio extends Fase {
	private static final int N_TESSERE = 20; // TODO - da modificare con 156 per il gioco vero
	private static final int TEMPO_CLESSIDRA = 20;
    private Deque<Tessera> mucchioTessere;
    private List<Tessera> tessereScoperte;
    private Mazzetto[] mazzettiDiCarte;
    private Clessidra clessidra;
    private PlanceVolo planceVolo;
    
    //costruttore
	public FaseAssemblaggio(List<Giocatore> giocatori, PlanceVolo planceVolo,  Mazzetto[] mazzettiDiCarte, ConsoleIO inputOutput) {
		super(giocatori, inputOutput);
		this.planceVolo = planceVolo;
		this.setMucchioTessere(creaMucchioTessere());
		
		// Le 3 pile inferiori - La pila in alto (mazzettiDiCarteAvventura[3]) è ignota
		this.mazzettiDiCarte = new Mazzetto[]{mazzettiDiCarte[0], mazzettiDiCarte[1], mazzettiDiCarte[2]}; 
		
		this.clessidra = new Clessidra(TEMPO_CLESSIDRA);
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
					//TODO - valuta il funzionamento della clessidra
					this.clessidra.start();
					//il giocatore può continuare a svolgere azioni finché non scade il tempo della clessidra
					while(this.clessidra.isNotExpired()){
					
					//TODO - stampa la planceNave del giocatore con le tessere prenotate
					//TODO - stampa le tessere scoperte
					//TODO - gestisci il caso in cui le tessere sono finite
					//scelta tra le seguenti opzioni:
					//1 - PESCARE UNA TESSERA
					//2 - TERMINARE ASSEMBLAGGIO
					//3 - GUARDARE MAZZI DI CARTE
					//4 - PRENDI TESSERA PRENOTATA 	
					//5 - PRENDI TESSERA SCOPERTA
						
					sceltaOpzioni = this.getInputOutput().chiediAzioneAssemblaggio(giocatore.getColore(),
							giocatore.getPlanceNave().isComponenteAgganciato(),
							giocatore.getPlanceNave().haTesserePrenotate(), !this.tessereScoperte.isEmpty());
					
					
						switch(sceltaOpzioni) {
						case PESCA_TESSERA:{
							tesseraPescata = this.mucchioTessere.pop();
							sceltaTessera = this.getInputOutput().chiediAzioneSulleTessere(giocatore.getColore(), 
									false, tesseraPescata, giocatore.getPlanceNave().isSpazioTesserePrenotatePieno());
							
							//Dopo aver pescato una carta, posso:
							//1 - RUOTARLA
							//2 - AGGANCIARLA
							//3 - RIMETTERLA SUL TAVOLO
							//4 - PRENOTARLA PER DOPO
							switch(sceltaTessera) {
								case RUOTA_TESSERA:{
									this.getInputOutput().ruotaTessera(tesseraPescata);
									break;
								}
								case AGGANCIA_TESSERA:{
									//TODO
									break;
								}
								case RIMETTI_TESSERA_SUL_TAVOLO:{
									this.tessereScoperte.add(tesseraPescata);
									break;
								}
								case PRENOTA_TESSERA:{
									//sistema se il giocatore ha già il massimo di tessere prenotate
									giocatore.getPlanceNave().aggiungiTesseraPrenotata(tesseraPescata);
									break;
								}
								//TODO
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
							//TODO - gestisci la richiesta di quale tessera prenotata pescare
							//tesseraPescata = this.mucchioTessere.pop();
							sceltaTessera = this.getInputOutput().chiediAzioneSulleTessere(giocatore.getColore(), 
									true, tesseraPescata, giocatore.getPlanceNave().isSpazioTesserePrenotatePieno());
							
							//Dopo aver pescato una carta prenotata, posso:
							//1 - RUOTARLA
							//2 - AGGANCIARLA
							//3 - RIMETTERLA A POSTO
							
							switch(sceltaTessera) {
								case RUOTA_TESSERA:{
									this.getInputOutput().ruotaTessera(tesseraPescata);
									break;
								}
								case AGGANCIA_TESSERA:{
									//TODO
									break;
								}
								//TODO - sistema questo caso di modo tale che la tessera viene rimessa a posto
								//nel modo corretto
								case RIMETTI_TESSERA_SUL_TAVOLO:{
									this.tessereScoperte.add(tesseraPescata);
									break;
								}
								
								//TODO
								default:
									break;
							}
							break;
						}
						case PRENDI_TESSERA_SCOPERTA:{
							tesseraPescata = this.getInputOutput().chiediTesseraScopertaDaPescare(tessereScoperte);
							sceltaTessera = this.getInputOutput().chiediAzioneSulleTessere(giocatore.getColore(), 
									false, tesseraPescata, giocatore.getPlanceNave().isSpazioTesserePrenotatePieno());
							
							//Dopo aver pescato una carta scoperta, posso:
							//1 - RUOTARLA
							//2 - AGGANCIARLA
							//3 - RIMETTERLA SUL TAVOLO
							//4 - PRENOTARLA PER DOPO
							switch(sceltaTessera) {
								case RUOTA_TESSERA:{
									this.getInputOutput().ruotaTessera(tesseraPescata);
									break;
								}
								case AGGANCIA_TESSERA:{
									//TODO
									break;
								}
								case RIMETTI_TESSERA_SUL_TAVOLO:{
									this.tessereScoperte.add(tesseraPescata);
									break;
								}
								case PRENOTA_TESSERA:{
									giocatore.getPlanceNave().aggiungiTesseraPrenotata(tesseraPescata);
									break;
								}
								//TODO
								default:
									break;
							}
							break;
						}
						//TODO
						default:
							break;
						
						}
					}
				}
			}
				
		}

	}
	
	
}
