package eccezioni;


//TODO - aggiungere javadoc
public class InputNonValidoException extends Exception {
	private static final long serialVersionUID = 2744490100665735769L;

	public InputNonValidoException(String message) {
        super(message);
    }

    public InputNonValidoException(String message, Throwable cause) {
        super(message, cause);
    }
}
