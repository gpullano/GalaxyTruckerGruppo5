package testingClassi;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import carteAvventura.Carta;
import carteAvventura.CartaPianeti;
import carteAvventura.Contrabbandieri;
import carteAvventura.Pirati;
import carteAvventura.Schiavisti;
//import carteAvventura.StazioneAbbandonata;

public class TestCarte {
	public void test() {
		List<Carta> mazzoDiCarte = new LinkedList<>();
		mazzoDiCarte.add(new CartaPianeti(1));
		mazzoDiCarte.add(new Contrabbandieri(1));
		mazzoDiCarte.add(new Pirati(1));
		mazzoDiCarte.add(new Schiavisti(1));
//		mazzoDiCarte.add(new StazioneAbbandonata(1));
		
		Collections.shuffle(mazzoDiCarte);
		
		for (Carta carta : mazzoDiCarte) {
	        System.out.println(carta); // Chiama il toString appropriato
        // Chiama il metodo implementato dalla classe concreta
	    }
	}
}
