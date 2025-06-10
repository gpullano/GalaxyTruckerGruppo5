package gameLogic;

import java.util.ArrayList;
import java.util.List;
import carteAvventura.Carta;
import carteAvventura.CartaPerditaGiorniVolo;
import carteAvventura.Dadi;
import carteAvventura.PioggiaDiMeteoriti;
import carteAvventura.SpazioAperto;
import plance.PlanceVolo;

public class FaseVolo extends Fase {
	private List<Carta> mazzoDiCarte;
	private Dadi dadi;

	public FaseVolo(List<Giocatore> giocatori, ConsoleIO inputOutput, PlanceVolo planceVolo, List<Carta> mazzoDiCarte) {
		super(giocatori, inputOutput, planceVolo);
		this.mazzoDiCarte = mazzoDiCarte;
	}

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
