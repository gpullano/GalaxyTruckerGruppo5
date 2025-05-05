package carteAvventura;

public class NaveAbbandonata extends Carta {
	// attributi
	private int equipaggioRichiesto;
	private int CreditiCosmiciOttenuti;

	public NaveAbbandonata(int giorniDiVolo, int livello, int equipaggioRichiesto, int CreditiCosmiciOttenuti) {
		super(giorniDiVolo, livello);
		this.CreditiCosmiciOttenuti=CreditiCosmiciOttenuti;
		this.equipaggioRichiesto=equipaggioRichiesto;
	}
// metodi 
// @override
	public void attiva() {
	//	TODO implementare la classe 
	}
}
