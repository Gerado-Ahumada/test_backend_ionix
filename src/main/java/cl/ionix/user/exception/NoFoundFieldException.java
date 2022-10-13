package cl.ionix.user.exception;

public class NoFoundFieldException extends Exception {

  private static final long serialVersionUID = 1L;

  public NoFoundFieldException(String message) {
    super(message);
  }
}
