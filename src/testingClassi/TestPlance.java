package testingClassi;

import plance.PlanceNaveLivello1;
import plance.PlanceVolo;

public class TestPlance {
	public static void main(String[] args) {
		PlanceNaveLivello1 s = new PlanceNaveLivello1();
		s.stampaNave();
		
		PlanceVolo p = new PlanceVolo(5, 8);
		p.percorso();
		p.stampaVolo();
	}

}
