package org.myftp.p_productions.HomePlugin.exceptions;

public class NoHomeFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8631151076698941967L;

	public NoHomeFoundException() {
		this("No home found.");
	}

	public NoHomeFoundException(String message) {
		super(message);
	}

	public NoHomeFoundException(Throwable cause) {
		super(cause);
	}

	public NoHomeFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoHomeFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
