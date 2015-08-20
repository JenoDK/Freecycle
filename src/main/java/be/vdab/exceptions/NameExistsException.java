package be.vdab.exceptions;

public class NameExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NameExistsException(String message) {
		super(message);
	}

}
