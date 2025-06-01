package carteAvventura;

import java.util.List;

import gameLogic.Giocatore;
import plance.PosizioneGiocatore;
import tessere.Tessera;

//polvere stellare, spazio aperto, pioggia di meteoriti x dopo, zona di guerra, contrabbandieri, 
//nave abbandonata, schiavisti

public abstract class Carta {
	// attributi
	private final int livello;
	
	// costruttore
	
	protected Carta(int livello) {
		if(livello < 1 || livello > 3) {
			throw new IllegalArgumentException("Il livello della carta deve essere compreso tra 1 e 3.");
		}
		this.livello=livello;
	}
	
	public int getLivello() {
		return livello;
	}
<<<<<<< Upstream, based on branch 'main' of https://github.com/gpullano/GalaxyTruckerGruppo5.git
=======
	// metodi 
	//	metodo astratto che ogni carta implementa con @override
	public abstract void attiva(List<Giocatore>giocatori, PosizioneGiocatore posizione, List<Tessera> tessere) ;
	
	//	TODO parametro in attiva 
>>>>>>> 4143ff1 aggiunta di files

	@Override
	public abstract String toString();
	
	
}
