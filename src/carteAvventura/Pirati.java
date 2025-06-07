package carteAvventura;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import dadiEClessidra.Dadi;
import gameLogic.ConsoleIO;
import gameLogic.Giocatore;
import plance.PlanceVolo;
import plance.PosizioneGiocatore;
public class Pirati extends CartaPerditaGiorniVolo {
	private final int potenzaFuoco;
	private final int creditiCosmici;
	private int numeroCannonate;
	private Cannonata[] cannonate;
	private Dadi dadi;
	
	public Pirati(int livello) {
		super(livello);
		Random rand=new Random();
		this.potenzaFuoco=rand.nextInt(2)+5;
		this.creditiCosmici=rand.nextInt(3)+5;
		numeroCannonate=rand.nextInt(4)+1;
		cannonate=new Cannonata[numeroCannonate];
		for (int i = 0;i < cannonate.length; i++) {
			cannonate[i]=new Cannonata(Dimensione.generaDimensione(),Provenienza.generaProvenienza());
		}
		this.dadi = new Dadi();
	}

	public int getPotenzaFuoco() {
		return potenzaFuoco;
	}

	public int getCreditiCosmici() {
		return creditiCosmici;
	}
	public int getNumeroCannonate() {
		return numeroCannonate;
	}

	public void setNumeroCannonate(int numeroCannonate) {
		this.numeroCannonate = numeroCannonate;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Pirati - Livello: ").append(getLivello()).append("\n");
		sb.append("I Pirati hanno una potenza di fuoco = ").append(getPotenzaFuoco()).append("\n");
		sb.append("- Se il giocatore vince ").append("\n");
		sb.append("  - Guadagna ").append(getCreditiCosmici() + " ").append("crediti cosmici\n");
		sb.append("  Effetto: perdita di giorni di volo ").append(getGiorniVoloPersi()).append("\n");
		sb.append("  Per evitare la perdita di giorni di volo, puoi rinunciare alla ricompensa").append("\n\n");
		
		sb.append("- Se il giocatore perde ").append("\n");
        sb.append("La tua nave verrà colpita da ").append(getNumeroCannonate() + " ").append("cannonate\n");
		for(int i = 0; i < cannonate.length; i++) {
			sb.append(" - Cannonata ").append(i +1).append(": ").append(cannonate[i].getDimensione()).append(" da ").append(cannonate[i].getProvenienza()).append("\n");
		}
		
		return sb.toString();
	}



	@Override
	public void attiva(List<Giocatore> giocatore, PlanceVolo planceVolo, ConsoleIO inputOutput) {
		
		int i=0;
		boolean piratisconfitti=false;
		List<Giocatore> giocatoriSconfitti=new ArrayList();
		while(i<giocatore.size()||!piratisconfitti) {
			// controllare se la potenza fuoco dei pirati è maggiore
			if (giocatore.get(i).getPlanceNave().getPotenzaFuoco()<this.potenzaFuoco) {
				// aggiungo nella lista nella posizione i-esima il giocatore sconfitto 
				giocatoriSconfitti.add(giocatore.get(i));
				
			}else {
				piratisconfitti=true;
				// ottieni tot crediti cosmici 
				giocatore.get(i).aggiungiCrediti(this.creditiCosmici);
				// perdi giorni di volo 
				planceVolo.getPosizioneGiocatori()[i].aggiornaPosizione(getGiorniVoloPersi());
				
			}
			i++;
		}
		// finito il ciclo ottengo la lista di chi è stato sconfitto per cui faccio tirare i dadi così che si sa dove spareranno i pirati 
		if(giocatoriSconfitti.isEmpty()) {
			// nessuno è stato sconfitto
		}else {
			// faccio lanciare i dadi per capire dove colpire 
			int direzione=dadi.lancia();
			// colpisco i giocatori presenti nella lista 
			for (int j=0;j<giocatoriSconfitti.size();j++) {
				// sparare al giocatore j-esimo 
		//		giocatoriSconfitti.get(j).getPlanceNave().
				
			}
			
		}
		
		
		//
		
	}

	
}
