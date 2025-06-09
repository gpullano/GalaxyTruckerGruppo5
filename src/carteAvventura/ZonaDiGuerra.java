package carteAvventura;

import java.util.List;

import dadiEClessidra.Dadi;
import gameLogic.ConsoleIO;
import gameLogic.Giocatore;
import plance.Casella;
import plance.PlanceVolo;
import plance.Posizione;
import plance.PosizioneGiocatore;

public class ZonaDiGuerra extends CartaPerditaGiorniVolo{
	private final int equipaggioPerso;
	private final Cannonata cannonata[];
	private Dadi dadi;
	
	
	public ZonaDiGuerra(int livello) {
		super(livello);
		this.equipaggioPerso = 0;
		this.dadi = new Dadi();
		this.cannonata = new Cannonata[2];
		this.cannonata[0]=new Cannonata(Dimensione.GROSSO,Provenienza.SOTTO);
		this.cannonata[1]=new Cannonata(Dimensione.PICCOLO,Provenienza.SOTTO);
		
		
	}


	public int getEquipaggioPerso() {
		return equipaggioPerso;
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
		// inizializzo il giocatore assumendo che il primo sia quello con meno equipaggio
		int indicePrimo=0;
		int giocatoreMinEquipaggio=giocatore.get(0).getPlanceNave().getEquipaggioTotale();
		for (int i=0;i<giocatore.size();i++) {
			Giocatore giocatoreIesimo=giocatore.get(i);
			int equipaggioGiocatoreIesimo=giocatoreIesimo.getPlanceNave().getEquipaggioTotale();
			if (equipaggioGiocatoreIesimo<giocatoreMinEquipaggio) {
				indicePrimo=i;
			}
		}
		// tolgo 3gg di volo in base a 'indicePrimo' 
		planceVolo.getPosizioneGiocatori()[indicePrimo].aggiornaPosizione(-3);
		
		
		// trovare il giocatore con meno potenza motrice(perde 2 membri dell'equipaggio), ricordarsi di usare una inputOutput se necessitano usare i motori doppi
		int indiceSecondo=0;
		int GiocatoreMinPotenzaMotrice=giocatore.get(0).getPlanceNave().getPotenzaMotori();
		for (int i=0;i<giocatore.size();i++) {
			Giocatore giocatoreAttuale=giocatore.get(i);
			int potenzaMotoriGiocatoreAttuale=giocatoreAttuale.getPlanceNave().getPotenzaMotori();
			// qui bisognerebbe gestire il caso in cui non  vuole attivare tutti i motori. 
			boolean domanda=inputOutput.chiediSeAzionareMotoriDoppi("Vuoi azionare i motori doppi? Ricordati che ciò comporta uno spreco di energia");
			// TODO in base alla risposta bisognerà calcolare l'effettiva potenza motrice del giocatore.
			
			
			// controllo su chi ha meno potenza motrice
			if(potenzaMotoriGiocatoreAttuale<GiocatoreMinPotenzaMotrice) {
			indiceSecondo=i;	// trovato il giocatore con meno potenza motrice mi salvo la posizione 
			}
		}
		// tolgo 2 membri dell'equipaggio al giocatore sfortunato 
		int equipaggioGiocatoreSfortunato=giocatore.get(indiceSecondo).getPlanceNave().getEquipaggioTotale();
		giocatore.get(indiceSecondo).getPlanceNave().setEquipaggioTotale(equipaggioGiocatoreSfortunato-2);
		
		// trovo il giocatore con meno potenza di fuoco, verrà minacciato da una cannonata leggera e da una cannonata pesante provenienti da dietro
		int indiceTerzo=0;
		int GiocatoreMinPotenzaFuoco=giocatore.get(0).getPlanceNave().getPotenzaFuoco();
		for(int k=0;k<giocatore.size();k++) {
			Giocatore giocatoreKesimo=giocatore.get(k);
			int potenzaFuocoGiocatoreKesimo=giocatoreKesimo.getPlanceNave().getPotenzaFuoco();
			boolean domanda=inputOutput.chiediSeAzionareCannoniDoppi("Vuoi azionare i cannoni doppi? Ricordati che ciò comporta uno spreco di energia");
			// TODO in base alla risposta bisognerà calcolare l'effettiva potenza motrice del giocatore.
			if (potenzaFuocoGiocatoreKesimo<GiocatoreMinPotenzaFuoco) {
				indiceTerzo=k;
			}
		}
		// TODO sparare una cannonata leggera e pesante dal dietro al giocatore con indice 'indiceTerzo'
		int num=0;
		Casella[][] caselle;
		Posizione posizioneColpita;
		for (int j=0;j<cannonata.length;j++) {
			Dimensione dimensioneCannonata=this.cannonata[j].getDimensione();
	//		Provenienza provenienzaCannonata=this.cannonata[j].getProvenienza();		NON SERVE 
			num=dadi.lancia();
			caselle=giocatore.get(indiceTerzo).getPlanceNave().getCaselle();
			switch(dimensioneCannonata) {
			case GROSSO:{
				posizioneColpita=GestioneProiettili.colpisciComponenteDaSotto(giocatore.get(indiceTerzo).getPlanceNave(), num);
				if (posizioneColpita==null) {
					inputOutput.pericoloScampato();
				}else {
					caselle[posizioneColpita.getRiga()][posizioneColpita.getColonna()].setTessera(null);
				}
				break;
			}
			case PICCOLO:{
				
				
			}
			}
			
			
		}
		
		
		
		
	}

}
