package carteAvventura;

public abstract class Carta {
	// attributi
	private final int  giorniDiVolo;
	private final int livello;
	
	// costruttore
	
	public Carta(int giorniDiVolo, int livello) {
		this.giorniDiVolo=giorniDiVolo;
		this.livello=livello;
	}
	
	public int getGiorniDiVolo() {
		return giorniDiVolo;
	}
	public int getLivello() {
		return livello;
	}
	// metodi 
	//	metodo astratto che ogni carta implementa con @override
	public abstract void attiva() ;
	//	TODO parametro in attiva 
	
}
