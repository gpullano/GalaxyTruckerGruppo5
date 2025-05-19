package carteAvventura;

//polvere stellare, spazio aperto, pioggia di meteoriti x dopo, zona di guerra, contrabbandieri, 
//nave abbandonata, schiavisti

public abstract class Carta {
	// attributi
	private final int livello;
	
	// costruttore
	
	public Carta(int livello) {
		if(livello < 1 || livello > 3) {
			throw new IllegalArgumentException("Il livello della carta deve essere compreso tra 1 e 3.");
		}
		this.livello=livello;
	}
	
	public int getLivello() {
		return livello;
	}
	// metodi 
	//	metodo astratto che ogni carta implementa con @override
	public abstract void attiva() ;
	//	TODO parametro in attiva 

	@Override
	public abstract String toString();
	
	
}
