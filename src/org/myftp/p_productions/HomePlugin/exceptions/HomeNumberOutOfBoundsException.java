package org.myftp.p_productions.HomePlugin.exceptions;

public class HomeNumberOutOfBoundsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2315490561372112824L;

	public HomeNumberOutOfBoundsException() {
		this("The specified home number is too large.");
	}

	public HomeNumberOutOfBoundsException(String message) {
		super(message);
	}

	public HomeNumberOutOfBoundsException(Throwable cause) {
		super(cause);
	}

	public HomeNumberOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
	}

	public HomeNumberOutOfBoundsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
