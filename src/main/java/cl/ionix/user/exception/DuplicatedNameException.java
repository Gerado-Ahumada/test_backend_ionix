package cl.ionix.user.exception;

public class DuplicatedNameException extends Exception {

	private static final long serialVersionUID = 1L;

	public DuplicatedNameException(String message) {
		super(message);
	}
}
