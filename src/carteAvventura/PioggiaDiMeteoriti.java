package carteAvventura;
import java.util.List;
import java.util.Random;

import dadiEClessidra.Dadi;
import gameLogic.ConsoleIO;
import gameLogic.Giocatore;
import plance.Casella;
import plance.PlanceNave;
import plance.PlanceNaveLivello1;
import plance.Posizione;

public class PioggiaDiMeteoriti extends Carta {
	// attributi
	private final int numeroMeteoriti;
	private Meteorite[] meteoriti;
	private Dadi dadi;
	
	// costruttore
	public PioggiaDiMeteoriti(int livello) {
		super(livello);
		Random rand=new Random();
		numeroMeteoriti=rand.nextInt(4)+1;
		meteoriti=new Meteorite[numeroMeteoriti];
		for (int i=0;i<meteoriti.length;i++) {
			meteoriti[i]=new Meteorite(Dimensione.generaDimensione(),Provenienza.generaProvenienza());
		}
		this.dadi = new Dadi();
	}

	public int getNumeroMeteoriti() {
		return numeroMeteoriti;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Pioggia Di Meteoriti - Livello: ").append(getLivello()).append("\n");
		sb.append("La tua nave verrà colpita da ").append(getNumeroMeteoriti() + " ").append("meteoriti\n");
		
		for(int i = 0; i < meteoriti.length; i++) {
			sb.append(" - Meteorite ").append(i +1).append(": ").append(meteoriti[i].getDimensione()).append(" da ").append(meteoriti[i].getProvenienza()).append("\n");
		}
		
		
		return sb.toString();
	}
	
	//TODO - da completare
	//Ricordati di chiamare la stampa della carta.
	public void attiva(List<Giocatore> giocatori, ConsoleIO inputOutput) {
			Dimensione dimensioneMeteorite;
			Provenienza provenienzaMeteorite;
			Posizione posizioneDaColpire;
			int risultatoLancioDadi;
			Casella[][] caselle;
		for(int i = 0; i < numeroMeteoriti; i++) {
			dimensioneMeteorite = this.meteoriti[i].getDimensione();
			provenienzaMeteorite = this.meteoriti[i].getProvenienza();
			risultatoLancioDadi = this.dadi.lancia();
			//Per ogni meteorite vengono colpiti tutti i giocatori prima di passare al prossimo meteorite.
			inputOutput.lancioDeiDadi(giocatori.get(0).getColore(), risultatoLancioDadi);
			for(Giocatore giocatore : giocatori) {
				caselle = giocatore.getPlanceNave().getCaselle();
				switch(dimensioneMeteorite) {
					case GROSSO:{
						if(provenienzaMeteorite == Provenienza.SOPRA ||
						   provenienzaMeteorite == Provenienza.SOTTO) {
							posizioneDaColpire = this.colpisciComponenteColonna(giocatore.getPlanceNave(), risultatoLancioDadi);
							if(posizioneDaColpire != null) {
								//TODO - verifica se c'e' un cannone in quella direzione
								//funzione booleana
								caselle[posizioneDaColpire.getRiga()][posizioneDaColpire.getColonna()].setTessera(null);
							} else {
								inputOutput.pericoloScampato();
							}
							
							
							//se i controlli falliscono
							
						} else {
							
						}
							
						
						
						break;
					}
					case PICCOLO:{
						if(provenienzaMeteorite == Provenienza.SOPRA ||
						   	provenienzaMeteorite == Provenienza.SOTTO) {
							//TODO - verifica se lato liscio o se c'e' uno scudo
							//funzione booleana
							
							//se i controlli falliscono
							
						} else {
							
						}
						
						break;
					}
					}
			}
		}
	}
	
	//TODO - gestire il discorso isOccupata
	

}
