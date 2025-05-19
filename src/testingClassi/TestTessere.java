package testingClassi;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import tessere.Cabina;
import tessere.Cannone;
import tessere.Connettore;
import tessere.GeneratoreScudi;
import tessere.Motore;
import tessere.Stiva;
import tessere.Tessera;

public class TestTessere {
	
	public void test() {
		List<Tessera> mazzoCarte = new LinkedList<>();
		mazzoCarte.add(new Cabina(Connettore.UNIVERSALE,Connettore.DOPPIO,Connettore.SINGOLO,Connettore.UNIVERSALE));
		mazzoCarte.add(new Cannone(Connettore.NULLO,Connettore.NULLO,Connettore.CANNONE,Connettore.UNIVERSALE));
		mazzoCarte.add(new GeneratoreScudi(Connettore.SCUDO,Connettore.DOPPIO,Connettore.NULLO,Connettore.NULLO));
		mazzoCarte.add(new Motore(Connettore.DOPPIO,Connettore.NULLO,Connettore.SINGOLO,Connettore.MOTORE));
		mazzoCarte.add(new Stiva(Connettore.UNIVERSALE,Connettore.DOPPIO,Connettore.NULLO,Connettore.SINGOLO,2));
		
		Collections.shuffle(mazzoCarte);
		for (Tessera tessera : mazzoCarte) {
		System.out.println(tessera);
			
		}
		Tessera primaTessera=mazzoCarte.remove(0);
		
		System.out.println(primaTessera);
		
	}
}
