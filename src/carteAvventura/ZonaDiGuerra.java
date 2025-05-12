package carteAvventura;

import gameLogic.Cannonata;

public class ZonaDiGuerra extends CartaPerditaGiorniVolo{
	private final int equipaggioPerso;
	private final int potenzaFuoco;
	private final Cannonata cannonata[];
	
	
	public ZonaDiGuerra(int livello) {
		super(livello);
		this.equipaggioPerso = 0;
		this.potenzaFuoco = 0;
		this.cannonata = new Cannonata[2];
		// TODO Auto-generated constructor stub
	}


	public int getEquipaggioPerso() {
		return equipaggioPerso;
	}


	public int getPotenzaFuoco() {
		return potenzaFuoco;
	}
	
	public Cannonata[] getCannonata() {
		return cannonata;
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
