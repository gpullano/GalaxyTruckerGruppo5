package testingClassi;

import gameLogic.Colore;
import gameLogic.ConsoleIO;
import plance.GestorePlanceNave;
import plance.PlanceNaveLivello1;
import plance.PlanceVolo;
import tessere.GeneratoreTessere;

import tessere.Tessera;

public class TestPlance {
	public static void main(String[] args) {
		PlanceNaveLivello1 s = new PlanceNaveLivello1(Colore.ROSSO);
		Tessera tessera = GeneratoreTessere.generaTessere();
		System.out.println(tessera);
		GestorePlanceNave.agganciaTessera(s, tessera, 3, 3);
		ConsoleIO inputOutput = new ConsoleIO();
		inputOutput.stampaNave(s);
		
		Colore[] coloriGiocatori = { Colore.ROSSO, Colore.BLU, Colore.VERDE, Colore.GIALLO };
		
		PlanceVolo p = new PlanceVolo(coloriGiocatori);
		inputOutput.stampaVolo(p);
		
		
//		CartaPianeti carta0 = new CartaPianeti(1);
//		System.out.println(carta0.toString());
//		
//		NaveAbbandonata carta = new NaveAbbandonata(1);
//		System.out.println(carta.toString());
//		
//		PioggiaDiMeteoriti carta1 = new PioggiaDiMeteoriti(1);
//		System.out.println(carta1.toString());
//		
//		Pirati carta2 = new Pirati(1);
//		System.out.println(carta2.toString());
//		
//		PolvereStellare carta3 = new PolvereStellare(1);
//		System.out.println(carta3.toString());
//		
//		Schiavisti carta4 = new Schiavisti(1);
//		System.out.println(carta4.toString());
//		
//		SpazioAperto carta5 = new SpazioAperto(1);
//		System.out.println(carta5.toString());
//		
//		StazioneAbbandonata carta6 = new StazioneAbbandonata(1);
//		System.out.println(carta6.toString());
//		
//		ZonaDiGuerra carta7 = new ZonaDiGuerra(1);
//		System.out.println(carta7.toString());
//		
//		Contrabbandieri carta8 = new Contrabbandieri(1);
//		System.out.println(carta8.toString());
	}

}
