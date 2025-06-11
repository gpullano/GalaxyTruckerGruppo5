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
 * Gestisce la fase di assemblaggio delle navi.
 * In questa fase, i giocatori a turno pescano e posizionano tessere
 * per costruire la propria nave spaziale.
 */
public class FaseAssemblaggio extends Fase {
	
	private static final int N_TESSERE = 20; // TODO - da modificare con 156 per il gioco vero
    
    /** Il mazzo di tessere coperte da cui pescare. */
    private Deque<Tessera> mucchioTessere;
    
    /** La lista di tessere scoperte disponibili sul tavolo. */
    private List<Tessera> tessereScoperte;
    
    /** I mazzetti di carte avventura che i giocatori possono ispezionare. */
    private Mazzetto[] mazzettiDiCarte;
    
    /**
     * Costruttore della fase di assemblaggio.
     * Inizializza il mazzo di tessere e le altre risorse necessarie.
     * @param giocatori La lista dei giocatori partecipanti.
     * @param planceVolo La plancia di volo (per riferimento futuro).
     * @param mazzettiDiCarte I mazzetti di carte avventura.
     * @param inputOutput L'oggetto per la gestione dell'input/output.
     */
	public FaseAssemblaggio(List<Giocatore> giocatori, PlanceVolo planceVolo,  Mazzetto[] mazzettiDiCarte, ConsoleIO inputOutput) {
		super(giocatori, inputOutput, planceVolo);
		this.setMucchioTessere(creaMucchioTessere());
		
		// Le 3 pile inferiori - La pila in alto (mazzettiDiCarteAvventura[3]) è ignota
		this.mazzettiDiCarte = new Mazzetto[] { mazzettiDiCarte[0], mazzettiDiCarte[1], mazzettiDiCarte[2] }; 
		this.tessereScoperte = new ArrayList<>();	
	}
	
	/**
     * Restituisce il mazzo di tessere coperte.
     * @return Il mazzo di tessere.
     */
	public Deque<Tessera> getMucchioTessere() {
		return mucchioTessere;
	}

	/**
     * Imposta il mazzo di tessere coperte.
     * @param mucchioTessere Il nuovo mazzo di tessere.
     */
	public void setMucchioTessere(Deque<Tessera> mucchioTessere) {
		this.mucchioTessere = mucchioTessere;
	}
	
	/**
     * Crea e popola il mazzo iniziale di tessere.
     * @return Un Deque contenente le tessere generate.
     */
	private Deque<Tessera> creaMucchioTessere() {
    	Deque<Tessera> mucchio = new ArrayDeque<>();
    	for (int i = 0; i < N_TESSERE; i++) {
    		mucchio.add(GeneratoreTessere.generaTessere());
    	}
		return mucchio; 	
    }

	/**
     * Esegue il ciclo principale della fase di assemblaggio.
     * I giocatori compiono azioni a turno (pescare, piazzare, prenotare tessere)
     * fino a quando tutti hanno deciso di terminare la costruzione della propria nave.
     */
	@Override
	public void eseguiFase() {
		AzioneAssemblaggio sceltaOpzioni = null;
		AzioneAssemblaggio sceltaTessera = null;
		Tessera tesseraPescata = null;
		int numAssemblaggiTerminati = 0;
		
		this.getInputOutput().inizioAssemblaggio();
		
		// Il ciclo prosegue finché tutti i giocatori non hanno terminato l'assemblaggio.
		while (numAssemblaggiTerminati < this.getGiocatori().size()) {
			
			// Scorre ogni giocatore per il suo turno
			for (Giocatore giocatore : this.getGiocatori()) {
				
				// Se il giocatore non ha ancora finito, può compiere un'azione.
				if (!giocatore.isAssemblaggioTerminato()) {
									
					getInputOutput().stampaSetupAssemblaggio(
						giocatore.getColore(), 
						giocatore.getPlanceNave(), 
						giocatore.getPlanceNave().getTesserePrenotate(), 
						tessereScoperte
					);
					
					sceltaOpzioni = this.getInputOutput().chiediAzioneAssemblaggio(
						giocatore.getColore(),
						giocatore.getPlanceNave().isComponenteAgganciato(),
						giocatore.getPlanceNave().haTesserePrenotate(), 
						!this.tessereScoperte.isEmpty(), 
						!this.mucchioTessere.isEmpty()
					);
					
					switch (sceltaOpzioni) {
						case PESCA_TESSERA: {
							tesseraPescata = this.mucchioTessere.pop();
							sceltaTessera = this.getInputOutput().chiediAzioneSulleTessere(
								giocatore.getColore(), 
								false, 
								tesseraPescata, 
								giocatore.getPlanceNave().isSpazioTesserePrenotatePieno()
							);
							
							switch (sceltaTessera) {
								case RUOTA_TESSERA:
									this.getInputOutput().ruotaTessera(tesseraPescata);
									this.getInputOutput().stampaMessaggio("\nAGGANCIA la tessera:\n");
									this.getInputOutput().agganciaTessera(giocatore, tesseraPescata);
									giocatore.getPlanceNave().setComponenteAgganciato(true);
									break;
								case AGGANCIA_TESSERA:
									this.getInputOutput().agganciaTessera(giocatore, tesseraPescata);
									giocatore.getPlanceNave().setComponenteAgganciato(true);
									break;
								case RIMETTI_TESSERA_A_POSTO:
									this.tessereScoperte.add(tesseraPescata);
									break;
								case PRENOTA_TESSERA:
									giocatore.getPlanceNave().aggiungiTesseraPrenotata(tesseraPescata);
									break;
								default:
									break;
							}
							break;
						}
						
						case TERMINA_ASSEMBLAGGIO: {
							giocatore.terminaAssemblaggio();
							numAssemblaggiTerminati++;
							break;
						}

						case GUARDA_MAZZETTI_CARTE: {
							this.getInputOutput().guardaMazzettoScelto(this.mazzettiDiCarte);
							break;
						}

						case PRENDI_TESSERA_PRENOTATA: {
							tesseraPescata = this.getInputOutput().chiediTesseraPrenotata(giocatore.getPlanceNave().getTesserePrenotate());
							sceltaTessera = this.getInputOutput().chiediAzioneSulleTessere(
								giocatore.getColore(), 
								true, 
								tesseraPescata, 
								giocatore.getPlanceNave().isSpazioTesserePrenotatePieno()
							);
							
							switch (sceltaTessera) {
								case RUOTA_TESSERA:
									this.getInputOutput().ruotaTessera(tesseraPescata);
									this.getInputOutput().stampaMessaggio("\nAGGANCIA la tessera:\n");
									this.getInputOutput().agganciaTessera(giocatore, tesseraPescata);
									giocatore.getPlanceNave().setComponenteAgganciato(true);
									break;
								case AGGANCIA_TESSERA:
									this.getInputOutput().agganciaTessera(giocatore, tesseraPescata);
									giocatore.getPlanceNave().setComponenteAgganciato(true);
									break;
								case RIMETTI_TESSERA_A_POSTO:
									giocatore.getPlanceNave().aggiungiTesseraPrenotata(tesseraPescata);
									break;
								default:
									break;
							}
							break;
						}

						case PRENDI_TESSERA_SCOPERTA: {
							tesseraPescata = this.getInputOutput().chiediTesseraScopertaDaPescare(tessereScoperte);
							sceltaTessera = this.getInputOutput().chiediAzioneSulleTessere(
								giocatore.getColore(), 
								false, 
								tesseraPescata, 
								giocatore.getPlanceNave().isSpazioTesserePrenotatePieno()
							);
							
							switch (sceltaTessera) {
								case RUOTA_TESSERA:
									this.getInputOutput().ruotaTessera(tesseraPescata);
									this.getInputOutput().stampaMessaggio("\nAGGANCIA la tessera:\n");
									this.getInputOutput().agganciaTessera(giocatore, tesseraPescata);
									giocatore.getPlanceNave().setComponenteAgganciato(true);
									break;
								case AGGANCIA_TESSERA:
									this.getInputOutput().agganciaTessera(giocatore, tesseraPescata);
									giocatore.getPlanceNave().setComponenteAgganciato(true);
									break;
								case RIMETTI_TESSERA_A_POSTO:
									this.tessereScoperte.add(tesseraPescata);
									break;
								case PRENOTA_TESSERA:
									giocatore.getPlanceNave().aggiungiTesseraPrenotata(tesseraPescata);
									break;
								default:
									break;
							}
							break;
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