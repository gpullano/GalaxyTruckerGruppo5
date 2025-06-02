package plance;

import java.util.LinkedList;
import java.util.List;

import tessere.Cabina;
import tessere.CabinaCentrale;
import tessere.Cannone;
import tessere.CannoneDoppio;
import tessere.Motore;
import tessere.MotoreDoppio;
import tessere.Tessera;

public class PlanceNaveLivello1 extends PlanceNave{
	private static final int NUM_RIGHE = 5;
	private static final int NUM_COLONNE = 7;
	private static final int NUM_TESSERE_PRENOTABILI = 2;
	
	private Casella[][] caselle;
	private int potenzaFuoco;
	private int potenzaMotori;
	private int equipaggioTotale;
	private int energiaTotale;
	private boolean componenteAgganciato; // boolean, true/false
	private List<Tessera> spazioTesserePrenotate;
	

	public PlanceNaveLivello1() {
		super(NUM_RIGHE, NUM_COLONNE);
		this.creaNave();
		this.equipaggioTotale = 0;
		this.potenzaFuoco = 0;
		this.potenzaMotori = 0;
		this.energiaTotale = 0;
		this.componenteAgganciato = false;
		this.spazioTesserePrenotate = new LinkedList<>();
	}

	// getters e setters
	
	
	/**
	 * Metodo che ritorna una tessera prenotata
	 * @param i è l'indice in cui si trova la tessera (N.B: tra 1 o 2, anziché 0 o 1)
	 * @return la tessera prenotata all'indice i
	 */
	public Tessera getTesseraPrenotata(int i) {
		
		if(i < 1 || i > NUM_TESSERE_PRENOTABILI) {
			throw new IllegalArgumentException("Puoi usare solo tessere che sono in posizione 1 o 2");
		} 
		
		//riporto agli indici utilizzati per gli array/list
		i--;
		// Controllo se l'indice calcolato è valido per la dimensione attuale della lista
        if (i < 0 || i >= this.spazioTesserePrenotate.size()) {
            throw new IndexOutOfBoundsException("Non c'è una tessera prenotata alla posizione " + i +
                                                ". Tessere prenotate attuali: " + this.spazioTesserePrenotate.size());
        }

		return this.spazioTesserePrenotate.get(i);
	}
	
	
	
	public void setTesseraPrenotata(Tessera t) {
		if(this.spazioTesserePrenotate.size() >= NUM_TESSERE_PRENOTABILI) {
			throw new IllegalArgumentException("Non puoi prenotare ulteriori tessere, "
					+ "il numero massimo e'" + NUM_TESSERE_PRENOTABILI);
		} 
		this.spazioTesserePrenotate.add(t);
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

	
	// metodi
	
	/**
	 * Metodo che verifica se sono stati prenotati componenti
	 * viene utilizzato nella classe dedicata all'input/output (ConsoleIO)
	 * nella fase di assemblaggio per mostrare determinate opzioni
	 * ad esempio: "PRENDI TESSERA PRENOTATA".
	 * @return
	 */
	public boolean haTesserePrenotate() {
		return this.spazioTesserePrenotate.size() > 0;
	}
	
	//TODO - verificare se serve
	public void aggiungiEnergia(int energia) {
		if(energia < 0) {
			throw new IllegalArgumentException("Non puoi inserire un'energia negativa");
		}
		this.energiaTotale += energia;
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
	
	public boolean posizionaTessera(Tessera tessera, int riga,int colonna) {
		/*if (riga < 0 || riga >= getCaselle().length || colonna < 0 || colonna >= getCaselle()[0].length) {
			System.out.println("Errore: posizione fuori dai limiti (" + riga + "," + colonna + ")");
		    return false;
		}*/
		
		if (!getCaselle()[riga][colonna].isUtilizzabile()) {
			System.out.println("Errore: la casella (" + riga + "," + colonna + ") non è utilizzabile");
		    return false;
		}
		
		if (getCaselle()[riga][colonna].isOccupata()) {
			System.out.println("Errore: la casella (" + riga + "," + colonna + ") è già occupata");
		    return false;
		}
		
		getCaselle()[riga][colonna].setTessera(tessera);
		System.out.println("Tessera posizionata con successo in (" + riga + "," + colonna + ")");
		return true;
	}

	@Override
	public void stampaNave() {
		System.out.println();
		for(int r = 0; r <= 4; r++) {
			for(int c = 0; c <= 6; c++) {
				if(getCaselle()[r][c].isUtilizzabile()) {
					if (getCaselle()[r][c].isOccupata()) {
						System.out.print(getCaselle()[r][c].getTessera().toString());
					} else {
						System.out.print("▢\t\t");	
					}
				}else {
			    	System.out.print("\t\t");
				}
				
			}
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}


	
}
