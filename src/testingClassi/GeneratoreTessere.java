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
		List<Connettore>connettori;
		do {
			connettori = Arrays.asList(
		            connettoreCasuale(),
		            connettoreCasuale(),
		            Connettore.CANNONE,
		            connettoreCasuale()
		        );
		    } while (!controllaConnettoriNulli(connettori));
		// ora che ho controllato che la creazione casuale rispetta i vincoli
		return new Cannone(
				 connettori.get(0),
			     connettori.get(1),
			     connettori.get(2),
			     connettori.get(3)
							);
				}
	public static  Tessera generaCannoneDoppioCasuale() {
		List<Connettore>connettori;
		do {
			connettori= Arrays.asList(
					connettoreCasuale(),
					connettoreCasuale(),
					Connettore.CANNONEDOPPIO,
					connettoreCasuale()
					);
					
		}while(!controllaConnettoriNulli(connettori));
		return new CannoneDoppio(
				 connettori.get(0),
			     connettori.get(1),
			     connettori.get(2),
			     connettori.get(3)
			     );
				}
	public static  Tessera generaMotoreCasuale() {
				List<Connettore>connettori;
				do {
					connettori=Arrays.asList(
							connettoreCasuale(),
							connettoreCasuale(),
							connettoreCasuale(),
							Connettore.MOTORE
							);
							
				}while(!controllaConnettoriNulli(connettori));
				return new Motore(
				connettori.get(0),
			    connettori.get(1),
			    connettori.get(2),
			    connettori.get(3)
				);
				}
	public static  Tessera generaMotoreDoppioCasuale() {
		List<Connettore>connettori;
		do {
			connettori=Arrays.asList(
					connettoreCasuale(),
					connettoreCasuale(),
					connettoreCasuale(),
					Connettore.MOTOREDOPPIO
					);
					
		}while(!controllaConnettoriNulli(connettori));
		return new MotoreDoppio(
				connettori.get(0),
			    connettori.get(1),
			    connettori.get(2),
			    connettori.get(3)
			    );
				}
	public static  Tessera generaScudoAltoDx() {
		List<Connettore>connettori;
		do {
			connettori=Arrays.asList(
					Connettore.SCUDO,
					connettoreCasuale(),
					Connettore.SCUDO,
					connettoreCasuale()
					);
					
		}while(!controllaConnettoriNulli(connettori));
		return new GeneratoreScudi(
				connettori.get(0),
			    connettori.get(1),
			    connettori.get(2),
			    connettori.get(3)
				);
				}
	public static  Tessera generaScudoBassoDx() {
		List<Connettore>connettori;
		do {
			connettori=Arrays.asList(
					Connettore.SCUDO,
					connettoreCasuale(),
					connettoreCasuale(),
					Connettore.SCUDO
					);
		}while(!controllaConnettoriNulli(connettori));
		return new GeneratoreScudi(
				connettori.get(0),
			    connettori.get(1),
			    connettori.get(2),
			    connettori.get(3)
				);
				}

	public static Tessera generaSupportoVitaleViola(){
		List<Connettore>connettori;
		do {
			connettori=Arrays.asList(
					connettoreCasuale(),
					connettoreCasuale(),
					connettoreCasuale(),
					connettoreCasuale()
					);
		}while(!controllaConnettoriNulli(connettori));
		return new SupportoVitaleViola(
				connettori.get(0),
			    connettori.get(1),
			    connettori.get(2),
			    connettori.get(3), 
			    "VIOLA"
		
	);	
	}
	public static Tessera generaSupportoVitaleMarrone(){
		List<Connettore>connettori;
		do {
			connettori=Arrays.asList(
					connettoreCasuale(),
					connettoreCasuale(),
					connettoreCasuale(),
					connettoreCasuale()
					);
					
		}while(!controllaConnettoriNulli(connettori));
		return new SupportoVitaleMarrone(
				connettori.get(0),
			    connettori.get(1),
			    connettori.get(2),
			    connettori.get(3), 
			    "MARRONE"
	);	
	}
	public static Tessera generaStiva(){
		List<Connettore>connettori;
		do {
			connettori=Arrays.asList(
					connettoreCasuale(),
					connettoreCasuale(),
					connettoreCasuale(),
					connettoreCasuale()
					);
		}while(!controllaConnettoriNulli(connettori));
		return new Stiva(
				connettori.get(0),
			    connettori.get(1),
			    connettori.get(2),
			    connettori.get(3), 
			    2
	);	
	}
	public static Tessera generaStivaSpeciale(){
		List<Connettore>connettori;
		do {
			connettori=Arrays.asList(
					connettoreCasuale(),
					connettoreCasuale(),
					connettoreCasuale(),
					connettoreCasuale()
					);
			
		}while(!controllaConnettoriNulli(connettori));
		return new StivaSpeciale(
				connettori.get(0),
			    connettori.get(1),
			    connettori.get(2),
			    connettori.get(3),
			    4
	);	
	}
	public static Tessera generaModuliStrutturali() {
		List<Connettore>connettori;
		do {
			connettori=Arrays.asList(
					connettoreCasuale(),
					connettoreCasuale(),
					connettoreCasuale(),
					connettoreCasuale()
					);
		}while(!controllaConnettoriNulli(connettori));
		return new ModuliStrutturali(
				connettori.get(0),
			    connettori.get(1),
			    connettori.get(2),
			    connettori.get(3)
				);
				
	}
	public static boolean controllaConnettoriNulli(List<Connettore> connettori) {
		int cont=0;
		for (Connettore c:connettori) {
			if (c==Connettore.NULLO) {
				cont++;
			}
		}
		if (cont>2) {
			return false ;
		}
		return true;
	}
	
}
