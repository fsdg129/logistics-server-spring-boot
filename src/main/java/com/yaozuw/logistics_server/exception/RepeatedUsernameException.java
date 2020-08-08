package com.yaozuw.logistics_server.exception;

public class RepeatedUsernameException extends UserException {

	public RepeatedUsernameException() {
		super();
	}

	public RepeatedUsernameException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepeatedUsernameException(String message) {
		super(message);
	}

	public RepeatedUsernameException(Throwable cause) {
		super(cause);
	}

	public RepeatedUsernameException(String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
