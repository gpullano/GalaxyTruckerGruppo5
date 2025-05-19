package tessere;

public class GeneratoreScudi extends Tessera implements Attivabile{ 
	public GeneratoreScudi(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown) {
		super(latoDx, latoSx, latoSup, latoDown);
	}

	@Override
	public void attiva(boolean energia) {
		System.out.println("Ti sei difeso con lo scudo!");
	}
	@Override
	public String getNomeBreve() {
    	return "GenSc";
	}

}
