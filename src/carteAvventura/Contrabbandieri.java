package carteAvventura;

public class Contrabbandieri extends CartaPerditaGiorniVolo {
	// attributi
	private final int fuocoNemico;
	private final int merciRimosse;
	
	public Contrabbandieri(int giorniDiVolo, int livello, int fuocoNemico, int merciRimosse) {
		super(giorniDiVolo, livello);
		this.fuocoNemico = fuocoNemico;
		this.merciRimosse = merciRimosse;		
	}

	@Override
	public void attiva() {
		// TODO Auto-generated method stub
		
	}

	public int getFuocoNemico() {
		return fuocoNemico;
	}

	public int getMerciRimosse() {
		return merciRimosse;
	}
}
