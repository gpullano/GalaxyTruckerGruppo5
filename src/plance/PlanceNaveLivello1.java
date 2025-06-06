package plance;

import java.util.LinkedList;
import java.util.List;
import collezionabili.Merci;
import gameLogic.Colore;
import tessere.Cabina;
import tessere.CabinaCentrale;
import tessere.Cannone;
import tessere.CannoneDoppio;
import tessere.Motore;
import tessere.MotoreDoppio;
import tessere.Tessera;

public class PlanceNaveLivello1 extends PlanceNave{
	
	private static final int NUM_RIGHE = 5;
	public static int getNumRighe() {
		return NUM_RIGHE;
	}

	private static final int NUM_COLONNE = 7;
	public static int getNumColonne() {
		return NUM_COLONNE;
	}

	private static final int NUM_TESSERE_PRENOTABILI = 2;
    
	private int potenzaFuoco;
	private int potenzaMotori;
	private int equipaggioTotale;
	private int energiaTotale;
	private int merciTotali;
	private List<Merci> merciNave;
	private boolean componenteAgganciato; // boolean, true/false
	private List<Tessera> spazioTesserePrenotate;
	private boolean haAlienoViola;
	private boolean haAlienoMarrone;

	public PlanceNaveLivello1(Colore colore) {
		super(NUM_RIGHE, NUM_COLONNE);
		this.creaNave();
		this.equipaggioTotale = 0;
		this.potenzaFuoco = 0;
		this.potenzaMotori = 0;
		this.energiaTotale = 0;
		this.setMerciTotali(0);
		this.merciNave = new LinkedList<>();
		this.componenteAgganciato = false;
		this.spazioTesserePrenotate = new LinkedList<>();
		this.caselle[2][3].setTessera(new CabinaCentrale(colore));
		this.haAlienoMarrone = false;
		this.haAlienoViola = false;
	}

	// getters e setters
	
	/**
	 * @return the haAlienoViola
	 */
	public boolean HaAlienoViola() {
		return haAlienoViola;
	}

	/**
	 * @param haAlienoViola the haAlienoViola to set
	 */
	public void setHaAlienoViola(boolean haAlienoViola) {
		this.haAlienoViola = haAlienoViola;
	}

	/**
	 * @return the haAlienoMarrone
	 */
	public boolean HaAlienoMarrone() {
		return haAlienoMarrone;
	}

	/**
	 * @param haAlienoMarrone the haAlienoMarrone to set
	 */
	public void setHaAlienoMarrone(boolean haAlienoMarrone) {
		this.haAlienoMarrone = haAlienoMarrone;
	}
	
	public List<Tessera> getTesserePrenotate(){
		return spazioTesserePrenotate;
	}
	
	public int getSpazioMerciRimasto() {
		return this.merciTotali - this.merciNave.size();
	}
	
	public void aggiungiTesseraPrenotata(Tessera t) {
		if(this.spazioTesserePrenotate.size() >= NUM_TESSERE_PRENOTABILI) {
			throw new IllegalArgumentException("Non puoi prenotare ulteriori tessere, "
					+ "il numero massimo e'" + NUM_TESSERE_PRENOTABILI);
		} 
		this.spazioTesserePrenotate.add(t);
	}
	
	
	// getter e setter
	public int getMerciTotali() {
		return merciTotali;
	}

	public void setMerciTotali(int merciTotali) {
		this.merciTotali = merciTotali;
	}
	
	public int getPotenzaFuoco() {
		return potenzaFuoco;
	}


	public void setPotenzaFuoco(int potenzaFuoco) {
		this.potenzaFuoco = potenzaFuoco;
	}


	public int getPotenzaMotori() {
		return potenzaMotori;
	}


	public void setPotenzaMotori(int potenzaMotrice) {
		this.potenzaMotori = potenzaMotrice;
	}


	
	public int getEquipaggioTotale() {
		return equipaggioTotale;
	}


	public void setEquipaggioTotale(int equipaggioTotale) {
		this.equipaggioTotale = equipaggioTotale;
	}
	
	public int getEnergiaTotale() {
		return energiaTotale;
	}


	public void setEnergiaTotale(int energiaTotale) {
		this.energiaTotale = energiaTotale;
	}

	
	public boolean isComponenteAgganciato() {
		return componenteAgganciato;
	}

	public void setComponenteAgganciato(boolean componenteAgganciato) {
		this.componenteAgganciato = componenteAgganciato;
	}
	
	public List<Merci> getMerciNave() {
		return merciNave;
	}

	public void setMerciNave(List<Merci> merciNave) {
		this.merciNave = merciNave;
	}

	
	// metodi
	
	public boolean isSpazioTesserePrenotatePieno() {
		return spazioTesserePrenotate.size() == NUM_TESSERE_PRENOTABILI;
	}	
	
	/**
	 * Metodo che verifica se sono stati prenotati componenti
	 * viene utilizzato nella classe dedicata all'input/output (ConsoleIO)
	 * nella fase di assemblaggio per mostrare determinate opzioni
	 * ad esempio: "PRENDI TESSERA PRENOTATA".
	 * @return
	 */
	public boolean haTesserePrenotate() {
		return !this.spazioTesserePrenotate.isEmpty();
	}
	
	//TODO - verificare se serve
	public void aggiungiEnergia(int energia) {
		if(energia < 0) {
			throw new IllegalArgumentException("Non puoi inserire un'energia negativa");
		}
		this.energiaTotale += energia;
	}
	
	public void aggiungiEquipaggio(int equipaggio) {
		//TODO - opportuni controlli da aggiungere
		this.equipaggioTotale += equipaggio;
	}
	
	public void calcolaPotenzaFuoco(int energieDaSpendere) {
		for(int i = 0; i < NUM_RIGHE; i++) {
			for(int j = 0; j < NUM_COLONNE; j++) {
				if(this.caselle[i][j].getTessera() instanceof Cannone cannone) {
					this.potenzaFuoco += cannone.getSparo();
				} else if(this.caselle[i][j].getTessera() instanceof CannoneDoppio cannoneDoppio && 
						energieDaSpendere > 0 && this.energiaTotale > 0) {
					this.potenzaFuoco += cannoneDoppio.getSparo();
					this.energiaTotale--;
					energieDaSpendere--;
				}
			}
		}
	}
	
	public void calcolaPotenzaMotori(int energieDaSpendere) {
		for(int i = 0; i < NUM_RIGHE; i++) {
			for(int j = 0; j < NUM_COLONNE; j++) {
				if(this.caselle[i][j].getTessera() instanceof Motore motore) {
					this.potenzaMotori += motore.getPotenza();
				} else if(this.caselle[i][j].getTessera() instanceof MotoreDoppio motoreDoppio && 
						energieDaSpendere > 0 && this.energiaTotale > 0) {
					this.potenzaMotori += motoreDoppio.getPotenza();
					this.energiaTotale--;
					energieDaSpendere--;
				}
			}
		}
	}
	
	//TODO - valutare se serve
	public void calcolaEquipaggio() {
		for(int i = 0; i < NUM_RIGHE; i++) {
			for(int j = 0; j < NUM_COLONNE; j++) {
				if(this.caselle[i][j].getTessera() instanceof Cabina cabina) {
					this.equipaggioTotale += cabina.getEquipaggio() + cabina.getAlieni();
				} else if(this.caselle[i][j].getTessera() instanceof CabinaCentrale cabinaCentrale) {
					this.equipaggioTotale += cabinaCentrale.getEquipaggio();
				}
			}
		}
	}
	
	//TODO - valutare una funzione attiva scudo che permette di attivare lo scudo se abbiamo energia
	// e creare eventualmente un attributo "latiProtetti" che tiene traccia dei lati della nave
	// protetti dagli scudi, di modo da non dover controllare tessera per tessera. Questa funzione
	// verrà chiamata durante l'assemblaggio.
	
	@Override
	public void creaNave() {
		for(int r = 0; r <= 4; r++) {
		int c = 0;
		if (r == 0) {
			for( c = 3; c <= 3;c++) {
				getCaselle()[r][c].setUtilizzabile(true);	
		}
		}
		if (r == 1) {
			for(c = 2; c <= 4; c++) {
				getCaselle()[r][c].setUtilizzabile(true);
			}
		}
		
		if (r == 2) {
			for(c = 1; c <= 5; c++) {
				getCaselle()[r][c].setUtilizzabile(true);
			}
		}
		
		if (r == 3) {
			for(c = 1; c <= 5; c++) {
				getCaselle()[r][c].setUtilizzabile(true);
			}
		}
		
		if (r == 4) {
			for(c = 1; c <= 2; c++) {
				getCaselle()[r][c].setUtilizzabile(true);
			} 
			for(c = 4; c <= 5; c++) {
				getCaselle()[r][c].setUtilizzabile(true);
			}
		}	
		}
	}
	
}
