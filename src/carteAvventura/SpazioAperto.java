package carteAvventura;

public class SpazioAperto extends Carta {
// non ha attributi 
	public SpazioAperto(int livello) {
		super(livello);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attiva() {
		// TODO Auto-generated method stub

	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Spazio Aperto - Livello: ").append(getLivello()).append("\n");
		sb.append("A turno partendo dal leader ogni giocatore dichiara la sua potenza motrice");
		sb.append("Ogni giocatore guadagna un giorno di volo per ogni potenza motrice che compone la sua nave ");	
		return sb.toString();
	}

}
