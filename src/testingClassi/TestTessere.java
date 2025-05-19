package testingClassi;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import tessere.Tessera;

public class TestTessere {
	
	public void test() {
		List<Tessera> mazzoCarte = new LinkedList<>();
		mazzoCarte.add(GeneratoreTessere.generaCannoneCasuale());
		mazzoCarte.add(GeneratoreTessere.generaCannoneDoppioCasuale());
		mazzoCarte.add(GeneratoreTessere.generaModuliStrutturali());
		mazzoCarte.add(GeneratoreTessere.generaMotoreCasuale());
		mazzoCarte.add(GeneratoreTessere.generaMotoreDoppioCasuale());
		mazzoCarte.add(GeneratoreTessere.generaScudoAltoDx());
		mazzoCarte.add(GeneratoreTessere.generaScudoBassoSx());
		mazzoCarte.add(GeneratoreTessere.generaStiva());
		mazzoCarte.add(GeneratoreTessere.generaStivaSpeciale());
		mazzoCarte.add(GeneratoreTessere.generaSupportoVitaleMarrone());
		mazzoCarte.add(GeneratoreTessere.generaSupportoVitaleViola());

		
		
		Collections.shuffle(mazzoCarte);
		for (Tessera tessera : mazzoCarte) {
		System.out.println(tessera);
			
		}
		Tessera primaTessera=mazzoCarte.remove(0);
		
		System.out.println(primaTessera);
		
	}
}
