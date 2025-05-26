package tessere;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;



public class GeneratoreTessere {
	private static Random rand1=new Random();
	private static Random rand2=new Random();
	  private GeneratoreTessere() {
		    throw new IllegalStateException("Utility class");
		  }
	
	  private static Connettore connettoreCasuale() {
		
		// contiene tutti i valori dei connettori 
		Connettore[] valori=Connettore.values();
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
		return validi.get(rand1.nextInt(validi.size()));
		
				
		
	}
	private static Tessera generaCannoneCasuale() {
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
	private  static Tessera generaCannoneDoppioCasuale() {
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
	private  static Tessera generaMotoreCasuale() {
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
	private  static Tessera generaMotoreDoppioCasuale() {
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
	private  static Tessera generaScudoAltoDx() {
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
	private static  Tessera generaScudoBassoDx() {
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

	private static Tessera generaSupportoVitaleViola(){
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
	private static Tessera generaSupportoVitaleMarrone(){
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
	private static  Tessera generaStiva(){
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
	private static Tessera generaStivaSpeciale(){
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
	private  static Tessera generaModuliStrutturali() {
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
	private  static boolean controllaConnettoriNulli(List<Connettore> connettori) {
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
	
	
	// aggiungo un parametro che è la qta di tessere disponibili
		private static final int numeroTipiTessere=11; 
		Random rand= new Random();
		
		public static Tessera generaTessere () {
			 int indiceTessera=rand2.nextInt(numeroTipiTessere);
			 Tessera tessera=null;
			switch (indiceTessera) {
			case 0:
				tessera=generaCannoneCasuale();
				break;
			case 1:
				tessera=generaCannoneDoppioCasuale();
				break;
			case 2:
				tessera=generaModuliStrutturali();
				break;
			case 3:
				tessera=generaMotoreCasuale();
				break;
			case 4:
				tessera=generaMotoreDoppioCasuale();
				break;
			case 5:
				tessera=generaScudoAltoDx();
				break;
			case 6: 
				tessera=generaScudoBassoDx();
				break;
			case 7:
				tessera=generaStiva();
				break;
			case 8: 
				tessera=generaStivaSpeciale();
				break;
			case 9:
				tessera=generaSupportoVitaleMarrone();
				break;
			case 10:
				tessera=generaSupportoVitaleViola();
				break;
			default:
            	//nel caso venga modificato numeroTessere
                throw new IllegalStateException("Tipo di tessera non valido: " + tessera);
			}
			return tessera;
		}
}
