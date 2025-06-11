package gameLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import carteAvventura.Carta;
import carteAvventura.Mazzetto;
import carteAvventura.Pianeta;
import collezionabili.Merci;
import eccezioni.NumeroNonValidoException;
import plance.Casella;
import plance.GestorePlanceNave;
import plance.PlanceNaveLivello1;
import plance.PlanceVolo;
import plance.PosizioneGiocatore;
import tessere.Tessera;

/**
 * Gestisce tutte le interazioni di input/output con l'utente tramite la console.
 * Si occupa di stampare lo stato del gioco e di richiedere le scelte dei giocatori.
 */
public class ConsoleIO {
	//stringhe costanti
	private static final String INPUT_NON_VALIDO = "Input non valido. Per favore, inserisci un numero.";
	private static final String NUMERO_NON_VALIDO = "Numero non valido. Reinseriscilo";
	// attributi
	private final Scanner sc;

	/**
	 * Costruttore che inizializza lo scanner per l'input da console.
	 */
	public ConsoleIO() {
		this.sc = new Scanner(System.in);
	}

	/**
	 * Chiude lo scanner per liberare le risorse.
	 */
	public void chiudiScanner() {
		sc.close();
	}

	//------------------------------------------------------------------
	// STAMPA NAVE E PLANCIA DI VOLO
	
	/**
	 * Stampa una rappresentazione testuale della plancia della nave.
	 * @param planceNave La plancia della nave da visualizzare.
	 */
	public void stampaNave(PlanceNaveLivello1 planceNave) {
		// (implementazione omessa per brevità)
	}

	/**
	 * Stampa una rappresentazione testuale della plancia di volo.
	 * @param planceVolo La plancia di volo da visualizzare.
	 */
	public void stampaVolo(PlanceVolo planceVolo) {
		// (implementazione omessa per brevità)
	}
	
	//------------------------------------------------------------------
	//------- SETUP PARTITA

	/**
	 * Chiede all'utente di scegliere il livello di gioco.
	 * @return Il {@link LivelloPartita} selezionato.
	 */
	public LivelloPartita chiediLivelloGioco() {
		// (implementazione omessa per brevità)
		return null; // placeholder
	}

	/**
	 * Chiede all'utente il numero di giocatori (da 2 a 4).
	 * @return Il numero di giocatori inserito.
	 */
	public int chiediNumGiocatori() {
		// (implementazione omessa per brevità)
		return 0; // placeholder
	}

	/**
	 * Chiede a ogni giocatore di scegliere un colore unico.
	 * @param numGiocatori Il numero di giocatori per cui chiedere il colore.
	 * @return Un array di {@link Colore} con le scelte dei giocatori.
	 */
	public Colore[] chiediColoreGiocatori(int numGiocatori) {
		// (implementazione omessa per brevità)
		return null; // placeholder
	}

	//------------------------------------------------------------------
	//---- FASI DI GIOCO

	//-------------
	//Fase di assemblaggio
	//-------------

	/**
	 * Mostra lo stato iniziale del turno di un giocatore nella fase di assemblaggio.
	 * @param coloreGiocatore Colore del giocatore di turno.
	 * @param planceNave La sua plancia nave.
	 * @param tesserePrenotate Le sue tessere prenotate.
	 * @param tessereScoperte Le tessere scoperte disponibili a tutti.
	 */
	public void stampaSetupAssemblaggio(Colore coloreGiocatore, PlanceNaveLivello1 planceNave, List<Tessera> tesserePrenotate, List<Tessera> tessereScoperte) {
		// (implementazione omessa per brevità)
	}

	/**
	 * Stampa il messaggio di inizio della fase di assemblaggio.
	 */
	public void inizioAssemblaggio() {
		System.out.println("-----FASE DI ASSEMBLAGGIO DELLE NAVI-----");
	}

	/**
	 * Mostra e chiede al giocatore quale azione di assemblaggio compiere.
	 * @param colore Colore del giocatore.
	 * @param haAgganciatoComponente True se il giocatore ha già agganciato un pezzo.
	 * @param haPrenotatoComponente True se ha tessere prenotate.
	 * @param esistonoTessereScoperte True se ci sono tessere scoperte.
	 * @param esistonoTessereMucchio True se si possono ancora pescare tessere.
	 * @return L'{@link AzioneAssemblaggio} scelta.
	 */
	public AzioneAssemblaggio chiediAzioneAssemblaggio(Colore colore, boolean haAgganciatoComponente, boolean haPrenotatoComponente, boolean esistonoTessereScoperte, boolean esistonoTessereMucchio) {
		// (implementazione omessa per brevità)
		return null; // placeholder
	}
	
	/**
	 * Mostra la tessera in mano e chiede al giocatore cosa farne.
	 * @param colore Colore del giocatore.
	 * @param tesseraPrenotata True se la tessera proviene dalla riserva personale.
	 * @param tesseraPescata La tessera attualmente in mano.
	 * @param spazioTesserePrenotatePieno True se gli slot di prenotazione sono pieni.
	 * @return L'{@link AzioneAssemblaggio} scelta.
	 */
	public AzioneAssemblaggio chiediAzioneSulleTessere(Colore colore, boolean tesseraPrenotata, Tessera tesseraPescata, boolean spazioTesserePrenotatePieno) {
		// (implementazione omessa per brevità)
		return null; // placeholder
	}

	/**
	 * Permette al giocatore di ruotare una tessera più volte.
	 * @param tesseraPescata La tessera da ruotare.
	 */
	public void ruotaTessera(Tessera tesseraPescata) {
		// (implementazione omessa per brevità)
	}

	/**
	 * Chiede al giocatore quale mazzetto di carte guardare e ne stampa il contenuto.
	 * @param mazzettiDiCarte I mazzetti tra cui scegliere.
	 */
	public void guardaMazzettoScelto(Mazzetto[] mazzettiDiCarte) {
		// (implementazione omessa per brevità)
	}

	/**
	 * Chiede al giocatore di scegliere una tessera tra quelle scoperte.
	 * @param tessereScoperte La lista di tessere tra cui scegliere.
	 * @return La tessera scelta, rimossa dalla lista.
	 */
	public Tessera chiediTesseraScopertaDaPescare(List<Tessera> tessereScoperte) {
		// (implementazione omessa per brevità)
		return null; // placeholder
	}

	/**
	 * Stampa una lista di tessere a schermo.
	 * @param tessereDaStampare La lista di tessere da visualizzare.
	 */
	public void stampaTessere(List<Tessera> tessereDaStampare) {
		// (implementazione omessa per brevità)
	}

	/**
	 * Chiede al giocatore di scegliere una tessera tra quelle prenotate.
	 * @param tesserePrenotate La lista di tessere prenotate del giocatore.
	 * @return La tessera scelta, rimossa dalla lista.
	 */
	public Tessera chiediTesseraPrenotata(List<Tessera> tesserePrenotate) {
		// (implementazione omessa per brevità)
		return null; // placeholder
	}

	/**
	 * Guida il giocatore nel processo di aggancio di una tessera sulla sua plancia.
	 * @param giocatore Il giocatore che sta agganciando la tessera.
	 * @param tesseraDaAgganciare La tessera da posizionare.
	 */
	public void agganciaTessera(Giocatore giocatore, Tessera tesseraDaAgganciare) {
		// (implementazione omessa per brevità)
	}

	//-------------
	// fase di preparazione al decollo
	//-------------

	/**
	 * Stampa il messaggio di inizio della fase di preparazione.
	 */
	public void inizioPreparazioneAlDecollo() {
		System.out.println("----- FASE DI PREPARAZIONE AL DECOLLO -----");
	}

	/**
	 * Stampa un messaggio per la fase di posizionamento alieni/equipaggio.
	 * @param coloreGiocatore Il colore del giocatore di turno.
	 */
	public void posizionamentoAlieni(Colore coloreGiocatore) {
		System.out.println("POSIZIONAMENTO ALIENI e/o EQUIPAGGIO.");
	}

	/**
	 * Pone una domanda sì/no al giocatore per il posizionamento di un alieno.
	 * @param domanda Il testo della domanda da mostrare.
	 * @return True se la risposta è "sì", false se è "no".
	 */
	public boolean chiediSePosizionareAlieno(String domanda) {
		// (implementazione omessa per brevità)
		return false; // placeholder
	}

	//-------------
	// fase di volo
	//-------------

	/**
	 * Pone una domanda generica sì/no al giocatore.
	 * @param domanda Il testo della domanda da mostrare.
	 * @return True se la risposta è "sì", false se è "no".
	 */
	public boolean chiediSeEseguireAzione(String domanda) {
		// (implementazione omessa per brevità)
		return false; // placeholder
	}

	/**
	 * Chiede a un giocatore se vuole attivare una carta avventura.
	 * @param giocatore Il giocatore a cui viene posta la domanda.
	 * @return True se il giocatore sceglie di attivare, altrimenti false.
	 */
	public boolean chiediAttivare(Giocatore giocatore) {
		// (implementazione omessa per brevità)
		return false; // placeholder
	}

	/**
	 * Gestisce la scelta delle merci quando lo spazio in stiva è insufficiente.
	 * @param merciDisponibili La lista di merci tra cui scegliere.
	 * @param spazioDisponibile Il numero di slot liberi per le merci.
	 * @return La lista di merci che il giocatore ha scelto di caricare.
	 */
	public List<Merci> chiediMerciDaPrendere(List<Merci> merciDisponibili, int spazioDisponibile) {
		// (implementazione omessa per brevità)
		return null; // placeholder
	}
	
	// PIOGGIA DI METEORITI/CANNONATE
	
	/**
	 * Comunica il risultato del lancio dei dadi.
	 * @param colore Il colore del giocatore che ha lanciato i dadi.
	 * @param risultato Il punteggio ottenuto.
	 */
	public void lancioDeiDadi(Colore colore, int risultato) {
		System.out.println("\nIL LEADER, IL GIOCATORE " + colore + " TIRA I DADI...");
		System.out.println("RISULTATO: " + risultato);
	}

	/**
	 * Stampa un messaggio rassicurante quando un pericolo viene evitato.
	 */
	public void pericoloScampato() {
		System.out.println("Hai scampato il pericolo!");
	}
	
	/**
	 * Guida il giocatore nella scelta di un pianeta da cui prendere merci.
	 * @param giocatoreCorrente Il giocatore che sta scegliendo.
	 * @param pianeti I pianeti disponibili.
	 * @param pianetiOccupati Lo stato di occupazione di ciascun pianeta.
	 * @return L'indice (0-based) del pianeta scelto.
	 */
	public int scegliPianeta(Giocatore giocatoreCorrente, Pianeta[] pianeti, boolean[] pianetiOccupati) {
		// (implementazione omessa per brevità)
		return -1; // placeholder
	}
	
	//-------------------------
	// Fine del viaggio
	//-------------------------
	
	/**
	 * Stampa un messaggio generico a schermo.
	 * @param messaggio Il messaggio da visualizzare.
	 */
	public void stampaMessaggio(String messaggio) {
		System.out.println(messaggio);
	}

	/**
	 * Annuncia il vincitore o i vincitori della partita.
	 * @param vincitori La lista di giocatori vincitori.
	 * @param maxCrediti Il punteggio ottenuto dal vincitore.
	 */
	public void annunciaVincitore(List<Giocatore> vincitori, int maxCrediti) {
		// (implementazione omessa per brevità)
	}
}