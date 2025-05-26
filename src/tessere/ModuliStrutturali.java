package tessere;

public class ModuliStrutturali extends Tessera{

	public ModuliStrutturali(Connettore latoDx, Connettore latoSx, Connettore latoSup, Connettore latoDown) {
		super(latoDx, latoSx, latoSup, latoDown);
		
	}
	
	@Override
	public String getNomeBreve() {
    	return " ModSt ";
	}
}
