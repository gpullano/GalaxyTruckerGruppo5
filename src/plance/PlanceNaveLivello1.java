package plance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import carteAvventura.Provenienza;
import collezionabili.Merci;
import gameLogic.Colore;
import gameLogic.ConsoleIO;
import tessere.Cabina;
import tessere.CabinaCentrale;
import tessere.Cannone;
import tessere.CannoneDoppio;
import tessere.Connettore;
import tessere.GeneratoreScudi;
import tessere.Motore;
import tessere.MotoreDoppio;
import tessere.Stiva;
import tessere.Tessera;
import tessere.VanoBatteria;

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
    
	private int pilaScarti;
	private int potenzaFuoco;
	private int potenzaMotori;
	private int equipaggioTotale;
	private int batterieTotali;
	//TODO - serve?
	private boolean componenteAgganciato; // boolean, true/false
	private List<Tessera> spazioTesserePrenotate;
	/*Gli alieni sono un attributo boolean perché se ce n'e' uno non ce ne possono essere
	*altri. In questo caso il controllo diventa piu' rapido e gestibile con un booleano.*/
	private boolean haAlienoViola;
	private boolean haAlienoMarrone;

	public PlanceNaveLivello1(Colore colore) {
		super(NUM_RIGHE, NUM_COLONNE);
		if(colore == null) {
			throw new IllegalArgumentException("Non puoi inserire un colore nullo.");
		}
		this.creaNave();
		this.pilaScarti = 0;
		this.equipaggioTotale = 0;
		this.potenzaFuoco = 0;
		this.potenzaMotori = 0;
		this.batterieTotali = 0;
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
	
//	public int getSpazioMerciRimasto() {
//		return this.merciTotali - this.merciNave.size();
//	}
	
	public void aggiungiTesseraPrenotata(Tessera t) {
		if(this.spazioTesserePrenotate.size() >= NUM_TESSERE_PRENOTABILI) {
			throw new IllegalArgumentException("Non puoi prenotare ulteriori tessere, "
					+ "il numero massimo e'" + NUM_TESSERE_PRENOTABILI);
		} 
		this.spazioTesserePrenotate.add(t);
	}
	
	
//	// getter e setter
//	public int getMerciTotali() {
//		return merciTotali;
//	}
//
//	public void setMerciTotali(int merciTotali) {
//		this.merciTotali = merciTotali;
//	}
	
	public int getPotenzaFuoco(ConsoleIO inputOutput) {
		calcolaPotenzaFuoco(inputOutput);
		return potenzaFuoco;
	}


	public void setPotenzaFuoco(int potenzaFuoco) {
		this.potenzaFuoco = potenzaFuoco;
	}


	public int getPotenzaMotori(ConsoleIO inputOutput) {
		calcolaPotenzaMotori(inputOutput);
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
	
	public int getBatterieTotali() {
		return batterieTotali;
	}


	public void setBatterieTotali(int energiaTotale) {
		this.batterieTotali = energiaTotale;
	}

	
	public boolean isComponenteAgganciato() {
		return componenteAgganciato;
	}

	public void setComponenteAgganciato(boolean componenteAgganciato) {
		this.componenteAgganciato = componenteAgganciato;
	}
	
//	public List<Merci> getMerciNave() {
//		return merciNave;
//	}
//
//	public void setMerciNave(List<Merci> merciNave) {
//		this.merciNave = merciNave;
//	}

	/**
	 * @return the pilaScarti
	 */
	public int getPilaScarti() {
		return pilaScarti;
	}
	
	// metodi
	
	public void incrementaPilaScarti() {
		this.pilaScarti++;
	}
	
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
	
	public boolean haBatterie() {
		return this.batterieTotali > 0;
	}
	
	//TODO - verificare se serve
	public void aggiungiBatterie(int batterie) {
		if(this.batterieTotali - batterie < 0) {
			throw new IllegalArgumentException("Non puoi avere un'energia negativa");
		}
		calcolaQtBatterie(-batterie);
	}
	
	public void aggiungiEquipaggio(int equipaggio) {
		//TODO - opportuni controlli da aggiungere
		this.equipaggioTotale += equipaggio;
	}
	
	public void calcolaPotenzaFuoco(ConsoleIO inputOutput) {
		boolean cannoniDoppiAttivati = false;
		//Reinizializzo per evitare un conteggio falsato
		this.potenzaFuoco = 0;
		for(int i = 0; i < NUM_RIGHE; i++) {
			for(int j = 0; j < NUM_COLONNE; j++) {
				if(this.caselle[i][j].getTessera() instanceof Cannone cannone) {
					this.potenzaFuoco += cannone.getSparo();
				} else if(this.caselle[i][j].getTessera() instanceof CannoneDoppio cannoneDoppio && this.batterieTotali > 0) {
					cannoniDoppiAttivati = inputOutput.chiediSeEseguireAzione("Vuoi attivare il cannone doppio?");
					if (cannoniDoppiAttivati) {
					this.potenzaFuoco += cannoneDoppio.getSparo();
					this.batterieTotali--;
					}
				}
			}
		}
	}
	
	public void calcolaPotenzaMotori(ConsoleIO inputOutput) {
		//Reinizializzo per evitare un conteggio falsato
		this.potenzaMotori = 0;
		boolean motoriDoppiAttivati = false;
		for(int i = 0; i < NUM_RIGHE; i++) {
			for(int j = 0; j < NUM_COLONNE; j++) {
				if(this.caselle[i][j].getTessera() instanceof Motore motore) {
					this.potenzaMotori += motore.getPotenza();
				} else if(this.caselle[i][j].getTessera() instanceof MotoreDoppio motoreDoppio && this.batterieTotali > 0) {
					motoriDoppiAttivati = inputOutput.chiediSeEseguireAzione("Vuoi attivare i motori doppi?");
					if (motoriDoppiAttivati) {
					this.potenzaMotori += motoreDoppio.getPotenza();
					this.batterieTotali--;
					}
				}
			}
		}
	}
	
	//TODO - gestirne meglio la logica
	public void calcolaEquipaggio() {
		//Reinizializzo per evitare un conteggio falsato
		this.equipaggioTotale = 0;
		this.haAlienoMarrone = false;
		this.haAlienoViola = false;
		for(int i = 0; i < NUM_RIGHE; i++) {
			for(int j = 0; j < NUM_COLONNE; j++) {
				if(this.caselle[i][j].getTessera() instanceof Cabina cabina) {
					this.equipaggioTotale += cabina.getEquipaggio();
					if(cabina.isAlienoMarrone()) {
						this.equipaggioTotale++;
						this.haAlienoMarrone = true;
					}
					if(cabina.isAlienoViola()) {
						this.equipaggioTotale++;
						this.haAlienoViola = true;
					}
				} else if(this.caselle[i][j].getTessera() instanceof CabinaCentrale cabinaCentrale) {
					this.equipaggioTotale += cabinaCentrale.getEquipaggio();
				}
			}
		}
	}
	
	
	
	public void rimuoviMembriEquipaggio(int quantitaDaRimuovere) {
	    if (quantitaDaRimuovere <= 0) {
	        return;
	    }

	    // Scansiona la nave per rimuovere i membri
	    for(int i = 0; i < NUM_RIGHE; i++) {
	        for(int j = 0; j < NUM_COLONNE; j++) {
	            
	            if (quantitaDaRimuovere == 0) {
	                break;
	            }

	            if(this.caselle[i][j].getTessera() instanceof Cabina cabina) {
	                
	                // 1: Rimuovi gli umani dalla cabina
	                int umaniInCabina = cabina.getEquipaggio();
	                if (umaniInCabina > 0) {
	                    int daRimuovere = Math.min(quantitaDaRimuovere, umaniInCabina);
	                    cabina.aggiungiEquipaggio(-daRimuovere); 
	                    quantitaDaRimuovere -= daRimuovere;
	                    System.out.println("Rimossi " + daRimuovere + " umani dalla cabina in (" + i + "," + j + ")");
	                }

	                if (quantitaDaRimuovere == 0) continue;

	                // 2: Rimuovi gli alieni
	                if (cabina.isAlienoViola()) {
	                    cabina.setAlienoViola(false);
	                    quantitaDaRimuovere--;
	                    System.out.println("Rimosso alieno viola dalla cabina in (" + i + "," + j + ")");
	                }
	                if (quantitaDaRimuovere == 0) continue;
	                if (cabina.isAlienoMarrone()) {
	                    cabina.setAlienoMarrone(false);
	                    quantitaDaRimuovere--;
	                    System.out.println("Rimosso alieno marrone dalla cabina in (" + i + "," + j + ")");
	                }
	            }
	        }
	        if (quantitaDaRimuovere == 0) break;
	    }
	    
	    // 3: Se ancora dobbiamo rimuovere membri, tocca alla cabina centrale
	    if (quantitaDaRimuovere > 0) {
	        Casella casellaCentrale = this.caselle[2][3];
	        if (casellaCentrale.getTessera() instanceof CabinaCentrale cabinaCentrale) {
	            int umaniInCabina = cabinaCentrale.getEquipaggio();
	            if (umaniInCabina > 0) {
	                int daRimuovere = Math.min(quantitaDaRimuovere, umaniInCabina);
	                cabinaCentrale.aggiungiEquipaggio(daRimuovere); // Anche CabinaCentrale deve avere questo metodo
	                quantitaDaRimuovere -= daRimuovere;
	                System.out.println("Rimossi " + daRimuovere + " umani dalla cabina centrale.");
	            }
	        }
	    }
	    
	    calcolaEquipaggio();
	}
	
	
	/**
	 * Calcola il numero di batterie totali della nave
	 * @param numBatterieDaRimuovere - indica il numero di batterie da rimuovere
	 * non fa scegliere all'utente dove rimuovere le batterie, le rimuove partendo
	 * dal vano situato alla riga e colonna di indice minore, scorrendo la nave.
	 */
	public void calcolaQtBatterie(int numBatterieDaRimuovere) {
		//Reinizializzo per evitare un conteggio falsato
		this.batterieTotali = 0;
		for(int i = 0; i < NUM_RIGHE; i++) {
			for(int j = 0; j < NUM_COLONNE; j++) {
				if(this.caselle[i][j].getTessera() instanceof VanoBatteria vanoBatteria) {
					if(vanoBatteria.getBatterie() > 0 && numBatterieDaRimuovere > 0) {
						vanoBatteria.rimuoviBatteria();
						numBatterieDaRimuovere--;
					}
					this.batterieTotali += vanoBatteria.getBatterie();
				} 
			}
		}
	}
	
	public boolean utilizzoScudo(Provenienza provenienza) {
		for(int i = 0; i < NUM_RIGHE; i++) {
			for(int j = 0; j < NUM_COLONNE; j++) {
				if(this.caselle[i][j].getTessera() instanceof GeneratoreScudi scudo) {
					if (provenienza == Provenienza.SOPRA && scudo.getLatoSup() == Connettore.SCUDO) {
						return true;
					} else if (provenienza == Provenienza.SOTTO && scudo.getLatoDown() == Connettore.SCUDO) {
						return true;
					} else if (provenienza == Provenienza.DESTRA && scudo.getLatoDx() == Connettore.SCUDO) {
						return true;
					} else if (provenienza == Provenienza.SINISTRA && scudo.getLatoSx() == Connettore.SCUDO) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	//Gestione merci
	
	/**
	 * Calcola lo spazio di carico totale ancora disponibile su tutta la nave.
	 * @return Il numero di scomparti vuoti totali.
	 */
	public int getSpazioMerciDisponibileTotale() {
	    int spazioTotale = 0;
	    for (int r = 0; r < NUM_RIGHE; r++) {
	        for (int c = 0; c < NUM_COLONNE; c++) {
	            if (this.caselle[r][c].getTessera() instanceof Stiva stiva) {
	                spazioTotale += stiva.getSpazioDisponibile();
	            }
	        }
	    }
	    return spazioTotale;
	}

	/**
	 * Aggiunge una lista di merci alla nave, riempiendo le stive una per una.
	 * Si ferma se finisce lo spazio.
	 *
	 * @param merciDaAggiungere La lista di merci da caricare.
	 * @return Il numero di merci effettivamente caricate.
	 */
	public int aggiungiMerci(List<Merci> merciDaAggiungere) {
	    int merciCaricate = 0;
	    
	    // Scansiona la nave per trovare stive con spazio
	    for (int r = 0; r < NUM_RIGHE; r++) {
	        for (int c = 0; c < NUM_COLONNE; c++) {
	            if (this.caselle[r][c].getTessera() instanceof Stiva stiva) {
	                
	                // Prova a caricare più merci possibili in questa stiva
	                while (!stiva.isPiena() && merciCaricate < merciDaAggiungere.size()) {
	                    stiva.aggiungiMerce(merciDaAggiungere.get(merciCaricate));
	                    merciCaricate++;
	                }
	                
	                // Se abbiamo caricato tutte le merci, usciamo
	                if (merciCaricate == merciDaAggiungere.size()) {
	                    System.out.println("Caricate tutte le " + merciCaricate + " merci.");
	                    return merciCaricate;
	                }
	            }
	        }
	    }

	    System.out.println("Spazio di carico esaurito. Caricate solo " + merciCaricate + " su " + merciDaAggiungere.size() + " merci.");
	    return merciCaricate;
	}


	/**
	 * Rimuove un certo numero di merci dalla nave.
	 * Scansiona le stive e rimuove le merci dalla prima stiva non vuota che trova.
	 *
	 * @param quantitaDaRimuovere Il numero di merci da rimuovere.
	 */
	public void rimuoviMerci(int quantitaDaRimuovere) {
	    if (quantitaDaRimuovere <= 0) return;

	    System.out.println("La nave deve perdere " + quantitaDaRimuovere + " merci.");
	    int merciRimasteDaRimuovere = quantitaDaRimuovere;

	    // Scansiona la nave per trovare stive da cui rimuovere le merci
	    for (int r = 0; r < NUM_RIGHE; r++) {
	        for (int c = 0; c < NUM_COLONNE; c++) {
	            if (merciRimasteDaRimuovere == 0) {
	                System.out.println("Rimozione merci completata.");
	                return; // Abbiamo finito
	            }

	            if (this.caselle[r][c].getTessera() instanceof Stiva stiva) {
	                // Rimuovi merci da questa stiva finché non è vuota o abbiamo finito
	                while (stiva.getMerciContenute() > 0 && merciRimasteDaRimuovere > 0) {
	                    Merci merceRimossa = stiva.rimuoviMerce();
	                    if (merceRimossa != null) {
	                        System.out.println("Scaricata merce di colore: " + merceRimossa.getColore() + " dalla stiva in (" + r + "," + c + ")");
	                        merciRimasteDaRimuovere--;
	                    }
	                }
	            }
	        }
	    }
	    
	    if (merciRimasteDaRimuovere > 0) {
	        System.out.println("Non c'erano abbastanza merci sulla nave per soddisfare la perdita.");
	    }
	}


	/**
	 * Restituisce una lista di TUTTE le merci presenti su TUTTE le stive della nave.
	 * Utile per il calcolo del punteggio finale.
	 * @return Una lista aggregata di tutte le merci.
	 */
	public List<Merci> getTutteLeMerciABordo() {
	    List<Merci> merciTotali = new ArrayList<>();
	    for (int r = 0; r < NUM_RIGHE; r++) {
	        for (int c = 0; c < NUM_COLONNE; c++) {
	            if (this.caselle[r][c].getTessera() instanceof Stiva stiva) {
	                merciTotali.addAll(stiva.getMerci());
	            }
	        }
	    }
	    return merciTotali;
	}
	
	
	

	
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
