package cl.ionix.user.exception;

public class NoFoundEntryException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoFoundEntryException(String message) {
		super(message);
	}
}
