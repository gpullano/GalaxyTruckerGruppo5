package carteAvventura;

import java.util.Random;

public class Schiavisti extends CartaPerditaGiorniVolo {

	private final int equipaggioPerso;
	private final int potenzaFuoco;
	private final int creditiCosmici;
	
	
	public Schiavisti(int livello) {
		super(livello);
		Random rand = new Random();
		this.equipaggioPerso = rand.nextInt(3)+4;
		this.potenzaFuoco = rand.nextInt(2)+5;
		this.creditiCosmici = rand.nextInt(2)+4;
		// TODO Auto-generated constructor stub
	}


	public int getEquipaggioPerso() {
		return equipaggioPerso;
	}


	public int getPotenzaFuoco() {
		return potenzaFuoco;
	}


	public int getCreditiCosmici() {
		return creditiCosmici;
	}


	@Override
	public void attiva() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}


}
