package carteAvventura;

public class NaveAbbandonata extends CartaPerditaGiorniVolo {
	// attributi
	private final int equipaggioRichiesto;
	private final int CreditiCosmiciOttenuti;

	public NaveAbbandonata(int giorniDiVolo, int livello, int equipaggioRichiesto, int CreditiCosmiciOttenuti) {
		super(giorniDiVolo, livello);
		this.CreditiCosmiciOttenuti=CreditiCosmiciOttenuti;
		this.equipaggioRichiesto=equipaggioRichiesto;
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
	
}
