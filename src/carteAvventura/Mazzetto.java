package carteAvventura;

import java.util.ArrayList;
import java.util.List;

public class Mazzetto {
	private List<Carta> carte;
	
	public Mazzetto() {
		this.carte = new ArrayList<>();
	}
	
	public List<Carta> getCarte() {
		return carte;
	}

	public void setCarte(List<Carta> carte) {
		this.carte = carte;
	}
	
	public void aggiungiCarta(Carta carta) {
		this.carte.add(carta);
	}
	
	public int dimensioneMazzetto() {
		return this.carte.size();
	}
	
}
