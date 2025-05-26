package gameLogic;

import java.util.ArrayDeque;
import java.util.Deque;

import carteAvventura.Mazzetto;
import tessere.GeneratoreTessere;
import tessere.Tessera;

public class Assemblaggio extends Fase {
	private static final int N_TESSERE = 156;
    private Deque<Tessera> mucchioTessere;
//  TODO - implementa il mucchio di tessere viste
//  private List<Tessera> tessereViste;
    
    
	public Assemblaggio() {
		this.mucchioTessere = creaMucchioTessere();
	}
	
	private Deque<Tessera> creaMucchioTessere(){
    	Deque<Tessera> mucchio = new ArrayDeque<>();
    	for(int i = 0; i < N_TESSERE; i++) {
    		mucchio.add(GeneratoreTessere.generaTessere());
    	}
		return mucchio; 	
    }

	
	public void eseguiFase(Mazzetto[] mazzettiDiCarte) {
		// TODO Auto-generated method stub
		
	}

}
