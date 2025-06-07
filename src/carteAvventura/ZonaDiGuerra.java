package carteAvventura;

import java.util.List;

import dadiEClessidra.Dadi;
import gameLogic.ConsoleIO;
import gameLogic.Giocatore;
import plance.PlanceVolo;
import plance.PosizioneGiocatore;

public class ZonaDiGuerra extends CartaPerditaGiorniVolo{
	private final int equipaggioPerso;
	private final int potenzaFuoco;
	private final Cannonata cannonata[];
	private Dadi dadi;
	
	
	public ZonaDiGuerra(int livello) {
		super(livello);
		this.equipaggioPerso = 0;
		this.dadi = new Dadi();
		this.potenzaFuoco = 0;
		this.cannonata = new Cannonata[2];
		// TODO Auto-generated constructor stub
	}


	public int getEquipaggioPerso() {
		return equipaggioPerso;
	}


	public int getPotenzaFuoco() {
		return potenzaFuoco;
	}
	
	public Cannonata[] getCannonata() {
		return cannonata;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Zona Di Guerra - Livello: ").append(getLivello()).append("\n");
		sb.append("- Prima linea:\n");
		sb.append("  Il giocatore con meno equipaggio perde 3 giorni di volo\n");
		sb.append("- Seconda linea:\n");
		sb.append("  Il giocatore con meno potenza motrice perde 2 membri dell'equipaggio\n");
		sb.append("- Terza linea:\n");
		sb.append("  Il giocatore con meno potenza di fuoco riceve una cannonata leggera e una cannonata pesante proveniente da dietro\n");
		
		return sb.toString();
	}


	@Override
	public void attiva(List<Giocatore> giocatore, PlanceVolo planceVolo, ConsoleIO inputOutput) {
		//il giocatore con meno equipaggio perde 3 gg di volo pertanto scorro la lista 
		Giocatore temp;
		for (int i=0;i<giocatore.size();i++) {
			for (int j=i+1;j<giocatore.size();j++) {
				Giocatore giocatorei=giocatore.get(i);
				Giocatore giocatorej=giocatore.get(j);
				if (giocatorej.getPlanceNave().getEquipaggioTotale()<giocatorei.getPlanceNave().getEquipaggioTotale()) {
					// scambiare
					temp = giocatore.get(i);
					giocatore.set(i, giocatore.get(j));
					giocatore.set(j, temp);
				}
				
			}
		}
		// tolgo 3gg di volo 
		planceVolo.getPosizioneGiocatori()[0].aggiornaPosizione(3);
		
		
		
		
	}

}
