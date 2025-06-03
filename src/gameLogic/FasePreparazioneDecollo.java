package gameLogic;

import java.util.LinkedList;
import java.util.List;

import carteAvventura.Carta;
import carteAvventura.Mazzetto;
import plance.PlanceVolo;

public class FasePreparazioneDecollo extends Fase {
	

	public FasePreparazioneDecollo(List<Giocatore> giocatori, ConsoleIO inputOutput, PlanceVolo planceVolo) {
		super(giocatori, inputOutput, planceVolo);
	}

	@Override
	public void eseguiFase() {
		// TODO Auto-generated method stub
		//controllo delle navi
		//collocazione astronauti e alieni

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

}
