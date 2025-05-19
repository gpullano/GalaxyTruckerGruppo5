package testingClassi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import tessere.Cannone;
import tessere.CannoneDoppio;
import tessere.Connettore;
import tessere.GeneratoreScudi;
import tessere.ModuliStrutturali;
import tessere.Motore;
import tessere.MotoreDoppio;
import tessere.SupportoVitaleMarrone;
import tessere.SupportoVitaleViola;
import tessere.Tessera;
import tessere.Stiva;
import tessere.StivaSpeciale;
public class GeneratoreTessere {
	
	public static Connettore connettoreCasuale() {
		Random rand=new Random();
		// contiene tutti i valori dei connettori 
		Connettore valori[]=Connettore.values();
		// escludo certi tipi di connettori 
		List<Connettore> esclusi=Arrays.asList(
			Connettore.CANNONE,
			Connettore.CANNONEDOPPIO,
			Connettore.MOTORE,
			Connettore.MOTOREDOPPIO,
			Connettore.SCUDO);
		// prendo solo quelli validi, se non ci sono in connettore allora posso aggiungerlo alla lista 
		List<Connettore> validi= new ArrayList<>();
		for (Connettore connettore: valori) {
			if (!esclusi.contains(connettore)) {
				validi.add(connettore);
			}
		}
		
		// scelgo a caso dalla lista 'validi' 
		return validi.get(rand.nextInt(validi.size()));
		
				
		
	}
	public static  Tessera generaCannoneCasuale() {
		return new Cannone(
				connettoreCasuale(),
				connettoreCasuale(),
				Connettore.CANNONE,
				connettoreCasuale()
				);
				}
	public static  Tessera generaCannoneDoppioCasuale() {
		return new CannoneDoppio(
				connettoreCasuale(),
				connettoreCasuale(),
				Connettore.CANNONEDOPPIO,
				connettoreCasuale()
				);
				}
	public static  Tessera generaMotoreCasuale() {
		return new Motore(
				connettoreCasuale(),
				connettoreCasuale(),
				connettoreCasuale(),
				Connettore.MOTORE
				);
				}
	public static  Tessera generaMotoreDoppioCasuale() {
		return new MotoreDoppio(
				connettoreCasuale(),
				connettoreCasuale(),
				connettoreCasuale(),
				Connettore.MOTOREDOPPIO
				);
				}
	public static  Tessera generaScudoAltoDx() {
		return new GeneratoreScudi(
				Connettore.SCUDO,
				connettoreCasuale(),
				Connettore.SCUDO,
				connettoreCasuale()
				);
				}
	public static  Tessera generaScudoBassoDx() {
		return new GeneratoreScudi(
				Connettore.SCUDO,
				connettoreCasuale(),
				connettoreCasuale(),
				Connettore.SCUDO
				);
				}
	public static Tessera generaSupportoVitaleViola(){
		return new SupportoVitaleViola(
		connettoreCasuale(),
		connettoreCasuale(),
		connettoreCasuale(),
		connettoreCasuale(),
		"VIOLA"
	);	
	}
	public static Tessera generaSupportoVitaleMarrone(){
		return new SupportoVitaleMarrone(
		connettoreCasuale(),
		connettoreCasuale(),
		connettoreCasuale(),
		connettoreCasuale(),
		"MARRONE"
	);	
	}
	public static Tessera generaStiva(){
		return new Stiva(
		connettoreCasuale(),
		connettoreCasuale(),
		connettoreCasuale(),
		connettoreCasuale(),
		2
	);	
	}
	public static Tessera generaStivaSpeciale(){
		return new StivaSpeciale(
		connettoreCasuale(),
		connettoreCasuale(),
		connettoreCasuale(),
		connettoreCasuale(),
		4
	);	
	}
	public static Tessera generaModuliStrutturali() {
		return new ModuliStrutturali(
				connettoreCasuale(),
				connettoreCasuale(),
				connettoreCasuale(),
				connettoreCasuale()
				);
				
	}
	
}
