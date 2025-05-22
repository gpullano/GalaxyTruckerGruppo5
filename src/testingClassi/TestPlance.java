package testingClassi;

import carteAvventura.CartaPianeti;
import carteAvventura.Contrabbandieri;
import plance.PlanceNaveLivello1;
import plance.PlanceVolo;
import tessere.Tessera;

public class TestPlance {
	public static void main(String[] args) {
		PlanceNaveLivello1 s = new PlanceNaveLivello1();
		Tessera tessera = GeneratoreTessere.generaStiva();
		System.out.println(tessera);
		s.posizionaTessera(tessera, 2, 3);
		s.stampaNave();
		
		
	//	PlanceVolo p = new PlanceVolo(5, 8);
		//p.percorso();
		//p.stampaVolo();
		
		
		//CartaPianeti carta = new CartaPianeti(1);
		//System.out.println(carta.toString());
		
		//Contrabbandieri carta1 = new Contrabbandieri(1);
		//System.out.println(carta1.toString());
	}

}
