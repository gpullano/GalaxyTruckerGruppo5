package testingClassi;
import tessere.Tessera;
import java.util.Deque;
import java.util.ArrayDeque;
public class TestTessere {
	
	public void test() {
		// cambiato da 'list' a 'Deque' perchè è più leggibile, chiaro e adatto a un mazzo 
		Deque<Tessera> mazzoCarte = new ArrayDeque<>();
		mazzoCarte.push(GeneratoreTessere.generaCannoneCasuale());
		mazzoCarte.push(GeneratoreTessere.generaCannoneDoppioCasuale());
		mazzoCarte.push(GeneratoreTessere.generaModuliStrutturali());
		mazzoCarte.push(GeneratoreTessere.generaMotoreCasuale());
		mazzoCarte.push(GeneratoreTessere.generaMotoreDoppioCasuale());
		mazzoCarte.push(GeneratoreTessere.generaScudoAltoDx());
		mazzoCarte.push(GeneratoreTessere.generaScudoBassoDx());
		mazzoCarte.push(GeneratoreTessere.generaStiva());
		mazzoCarte.push(GeneratoreTessere.generaStivaSpeciale());
		mazzoCarte.push(GeneratoreTessere.generaSupportoVitaleMarrone());
		mazzoCarte.push(GeneratoreTessere.generaSupportoVitaleViola());

		
		// finchè non è vuoto il mazzo prendo e stampo la tessera 
		while (!mazzoCarte.isEmpty()) {
			Tessera t = mazzoCarte.pop();
			System.out.println(t);
			
		}
		
	}
}
