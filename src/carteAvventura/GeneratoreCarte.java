package carteAvventura;
import java.util.Random;


public class GeneratoreCarte {

    private static final int NUMERO_TIPI_CARTE = 9;
    private static Random random = new Random();
    private GeneratoreCarte() {
        throw new IllegalStateException("Utility class");
      }

    /**
     * Seleziona casualmente un tipo di carta e ne crea una nuova istanza.
     * @return Una nuova istanza di una sottoclasse casuale di Carta.
     */
    public static Carta generaCartaCasuale(int livelloPartita) {
    	// scelgo un indice casuale
        int tipoCartaCasuale = random.nextInt(NUMERO_TIPI_CARTE);
        Carta cartaGenerata = null;

        switch (tipoCartaCasuale) {
            case 0:
                cartaGenerata = new Contrabbandieri(livelloPartita);
                break;
            case 1:
                cartaGenerata = new NaveAbbandonata(livelloPartita);
                break;
            case 2:
                cartaGenerata = new Pirati(livelloPartita);
                break;
            case 3:
                cartaGenerata = new PioggiaDiMeteoriti(livelloPartita);
                break;
            case 4:
                cartaGenerata = new ZonaDiGuerra(livelloPartita);
                break;
            case 5:
                cartaGenerata = new PolvereStellare(livelloPartita);
                break;
            case 6:
                cartaGenerata = new StazioneAbbandonata(livelloPartita);
                break;
            case 7:
                cartaGenerata = new Schiavisti(livelloPartita);
                break;
            case 8:
                cartaGenerata = new CartaPianeti(livelloPartita);
                break;
            default:
            	//nel caso venga modificato NUMERO_TIPI_CARTE
                throw new IllegalStateException("Tipo di carta casuale non valido: " + tipoCartaCasuale);
        }

        return cartaGenerata;
    }
    
}