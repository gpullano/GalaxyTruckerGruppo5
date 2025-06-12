package eccezioni;


public class NumeroNonValidoException extends Exception {
	private static final long serialVersionUID = 2744490100665735769L;

	public NumeroNonValidoException(String message) {
        super(message);
    }

    public NumeroNonValidoException(String message, Throwable cause) {
        super(message, cause);
    }
}
