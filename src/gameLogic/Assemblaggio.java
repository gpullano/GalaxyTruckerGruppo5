package gameLogic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import carteAvventura.Mazzetto;
import plance.PlanceVolo;
import tessere.GeneratoreTessere;
import tessere.Tessera;

public class Assemblaggio extends Fase {
	private static final int N_TESSERE = 20; // TODO - da modificare con 156 per il gioco vero
    private Deque<Tessera> mucchioTessere;
    private List<Tessera> TessereViste;
    private Mazzetto[] mazzettiDiCarte;
    
    
    //costruttore
	public Assemblaggio(List<Giocatore> giocatori, Mazzetto[] mazzettiDiCarte, PlanceVolo planceVolo, ConsoleIO inputOutput) {
		super(giocatori, planceVolo, inputOutput);
		this.setMucchioTessere(creaMucchioTessere());
		this.mazzettiDiCarte = mazzettiDiCarte;
	}
	
	//getter e setter
	public Deque<Tessera> getMucchioTessere() {
		return mucchioTessere;
	}

	public void setMucchioTessere(Deque<Tessera> mucchioTessere) {
		this.mucchioTessere = mucchioTessere;
	}
	
	
	//metodi
	private Deque<Tessera> creaMucchioTessere(){
    	Deque<Tessera> mucchio = new ArrayDeque<>();
    	for(int i = 0; i < N_TESSERE; i++) {
    		mucchio.add(GeneratoreTessere.generaTessere());
    	}
		return mucchio; 	
    }


	@Override
	public void eseguiFase() {
		// TODO Auto-generated method stub
		
	}

	

}
