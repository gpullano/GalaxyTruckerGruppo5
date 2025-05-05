package carteAvventura;

public class Contrabbandieri extends Carta {
	// attributi
	private int fuocoNemico;
	private int merci;
	
	public Contrabbandieri(int giorniDiVolo, int livello,int fuocoNemico, int merci) {
		super(giorniDiVolo, livello);
		this.setFuocoNemico(fuocoNemico);
		this.setMerci(merci);
	}

	@Override
	public void attiva() {
		// TODO Auto-generated method stub
		
	}

	public int getFuocoNemico() {
		return fuocoNemico;
	}

	public void setFuocoNemico(int fuocoNemico) {
		this.fuocoNemico = fuocoNemico;
	}

	public int getMerci() {
		return merci;
	}

	public void setMerci(int merci) {
		this.merci = merci;
	}

}
