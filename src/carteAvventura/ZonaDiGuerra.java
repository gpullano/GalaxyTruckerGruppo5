package carteAvventura;

import java.util.List;

import dadiEClessidra.Dadi;
import gameLogic.ConsoleIO;
import gameLogic.Giocatore;
import plance.Casella;
import plance.GestorePlanceNave;
import plance.PlanceNaveLivello1;
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
		inputOutput.stampaMessaggio(this.toString()); // Stampa le info della carta all'inizio
		//il giocatore con meno equipaggio perde 3 gg di volo pertanto scorro la lista 
		// inizializzo il giocatore assumendo che il primo sia quello con meno equipaggio
		int indicePrimo=0;
		int giocatoreMinEquipaggio=Integer.MAX_VALUE;
		for (int i=0;i<giocatore.size();i++) {
			Giocatore giocatoreIesimo=giocatore.get(i);
			int equipaggioGiocatoreIesimo=giocatoreIesimo.getPlanceNave().getEquipaggioTotale();
			if (equipaggioGiocatoreIesimo<giocatoreMinEquipaggio) {
				indicePrimo=i;
				giocatoreMinEquipaggio = equipaggioGiocatoreIesimo;
			}
		}
		// tolgo 3gg di volo in base a 'indicePrimo' 
		planceVolo.getPosizioneGiocatori()[indicePrimo].aggiornaPosizione(-3);
		
		
		// trovare il giocatore con meno potenza motrice(perde 2 membri dell'equipaggio)
		int indiceSecondo=0;
		int giocatoreMinPotenzaMotrice=Integer.MAX_VALUE;
		for (int i=0;i<giocatore.size();i++) {
			Giocatore giocatoreAttuale=giocatore.get(i);
			int potenzaMotoriGiocatoreAttuale=giocatoreAttuale.getPlanceNave().getPotenzaMotori(inputOutput);
						
			// controllo su chi ha meno potenza motrice
			if(potenzaMotoriGiocatoreAttuale<giocatoreMinPotenzaMotrice) {
				indiceSecondo=i;	// trovato il giocatore con meno potenza motrice mi salvo la posizione 
				giocatoreMinPotenzaMotrice = potenzaMotoriGiocatoreAttuale;
			}
		}
		
		// tolgo 2 membri dell'equipaggio al giocatore sfortunato 
		inputOutput.stampaMessaggio("La nave del GIOCATORE " + giocatore.get(indiceSecondo).getColore() + " deve perdere " + 2 + " membri dell'equipaggio!");
		giocatore.get(indiceSecondo).getPlanceNave().rimuoviMembriEquipaggio(2);
		
		// trovo il giocatore con meno potenza di fuoco, verrà minacciato da una cannonata leggera e da una cannonata pesante provenienti da dietro
		int indiceTerzo=0;
		int giocatoreMinPotenzaFuoco=Integer.MAX_VALUE;
		for(int k=0;k<giocatore.size();k++) {
			Giocatore giocatoreKesimo=giocatore.get(k);
			int potenzaFuocoGiocatoreKesimo=giocatoreKesimo.getPlanceNave().getPotenzaFuoco(inputOutput);
			if (potenzaFuocoGiocatoreKesimo<giocatoreMinPotenzaFuoco) {
				indiceTerzo=k;
				giocatoreMinPotenzaFuoco = potenzaFuocoGiocatoreKesimo;
			}
		}
		
		
		int num=0;
		Casella[][] caselle;
		Posizione posizioneColpita;
		PlanceNaveLivello1 planceNaveGiocatoreColpito = giocatore.get(indiceTerzo).getPlanceNave();
		for (int j=0;j<cannonata.length;j++) {
			Dimensione dimensioneCannonata=this.cannonata[j].getDimensione();
			num=dadi.lancia();
			caselle=giocatore.get(indiceTerzo).getPlanceNave().getCaselle();
			switch(dimensioneCannonata) {
				case GROSSO:{
					posizioneColpita=GestorePlanceNave.colpisciComponenteDaSotto(giocatore.get(indiceTerzo).getPlanceNave(), num);
					if (posizioneColpita==null) {
						inputOutput.pericoloScampato();
					}else {
						caselle[posizioneColpita.getRiga()][posizioneColpita.getColonna()].setTessera(null);
						GestorePlanceNave.gestisciRimozioneOrfani(giocatore.get(indiceTerzo).getPlanceNave());
					}
					break;
				}
				case PICCOLO:{
					// la logica è la stessa l'unica cosa da controllare è l'utilizzo o meno dello scudo energia 
					posizioneColpita=GestorePlanceNave.colpisciComponenteDaSotto(giocatore.get(indiceTerzo).getPlanceNave(), num);
					if (posizioneColpita==null) {
						inputOutput.pericoloScampato();
					}else {
						if (planceNaveGiocatoreColpito.utilizzoScudo(Provenienza.SOTTO) && planceNaveGiocatoreColpito.haBatterie()) {
			                boolean vuoleUsareScudo = inputOutput.chiediSeEseguireAzione("Un meteorite piccolo sta per colpire un lato non protetto. Vuoi usare 1 batteria per attivare lo scudo?");
			                if (vuoleUsareScudo) {
			                	planceNaveGiocatoreColpito.aggiungiBatterie(-1); // Consuma la batteria
			                }
			            } else {
			            	caselle[posizioneColpita.getRiga()][posizioneColpita.getColonna()].setTessera(null);
							GestorePlanceNave.gestisciRimozioneOrfani(giocatore.get(indiceTerzo).getPlanceNave());
							break;
			            }
					}
				}		

			}

		}
	}
}
