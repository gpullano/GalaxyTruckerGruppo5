package carteAvventura;
import java.util.Random;

public class NaveAbbandonata extends CartaPerditaGiorniVolo {
	// attributi secondo me equipaggio e crediti vanno generati casualmente 
	private final int equipaggioRichiesto;
	private final int CreditiCosmiciOttenuti;
	
	public NaveAbbandonata(int livello) {
		super(livello);
		Random rand =new Random();
		this.CreditiCosmiciOttenuti=rand.nextInt(6)+2;
		this.equipaggioRichiesto=rand.nextInt(10)+2;
	}
	
	public int getEquipaggioRichiesto() {
		return equipaggioRichiesto;
	}
	public int getCreditiCosmiciOttenuti() {
		return CreditiCosmiciOttenuti;
	}
	
// metodi 
// @override
	public void attiva() {
	//	TODO implementare la classe 
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
