package gameLogic;

import java.util.ArrayList;
import java.util.List;
import carteAvventura.Carta;
import carteAvventura.CartaPerditaGiorniVolo;
import carteAvventura.Dadi;
import carteAvventura.PioggiaDiMeteoriti;
import carteAvventura.SpazioAperto;
import plance.PlanceVolo;

/**
*la classe FaseVolo gestisce la fase di volo del gioco, durante la quale i giocatori affrontano gli eventi del mazzo avventura.
*/
public class FaseVolo extends Fase {
	private List<Carta> mazzoDiCarte;
	private Dadi dadi;

	/**
	*costruttore della classe FaseVolo.
	*@param giocatori la lista dei giocatori.
	*@param inputOutput l'oggetto per l'input/output.
	*@param planceVolo la plancia di volo comune.
	*@param mazzoDiCarte il mazzo di carte avventura per la fase.
	*/
	public FaseVolo(List<Giocatore> giocatori, ConsoleIO inputOutput, PlanceVolo planceVolo, List<Carta> mazzoDiCarte) {
		super(giocatori, inputOutput, planceVolo);
		this.mazzoDiCarte = mazzoDiCarte;
	}

	/**
	*esegue la logica della fase di volo, processando ogni carta del mazzo avventura e gestendo le azioni dei giocatori.
	*/
	@Override
	public void eseguiFase() {
		for(Carta carta: mazzoDiCarte) {
			
			//Stampo la planceVolo
			this.getInputOutput().stampaVolo(this.getPlanceVolo());
			
			List<Giocatore> giocatoriAncoraInGioco = new ArrayList<>();
			//Chiediamo se i giocatori vogliono abbandonare
			for(int i = 0; i < this.getGiocatori().size(); i++) {
				Giocatore giocatoreCorrente = this.getGiocatori().get(i);
				boolean abbandona = this.getInputOutput().chiediSeEseguireAzione("GIOCATORE " + giocatoreCorrente.getColore() + "Vuoi abbandonare la corsa?");
				if(abbandona) {
					giocatoreCorrente.abbandonaPartita();
					this.getPlanceVolo().getPosizioneGiocatori().remove(i);
				} else {
					giocatoriAncoraInGioco.add(giocatoreCorrente);
				}
				
			}
			if(carta instanceof CartaPerditaGiorniVolo cartaPerditaGiorniVolo) {
				cartaPerditaGiorniVolo.attiva(giocatoriAncoraInGioco, this.getPlanceVolo(), this.getInputOutput());
			} 
			if(carta instanceof SpazioAperto spazioAperto) {
				spazioAperto.attiva(giocatoriAncoraInGioco, getPlanceVolo(), getInputOutput());
			}
			if(carta instanceof PioggiaDiMeteoriti pioggiaDiMeteoriti) {
				pioggiaDiMeteoriti.attiva(giocatoriAncoraInGioco, getInputOutput());
			}
		}

	}

}