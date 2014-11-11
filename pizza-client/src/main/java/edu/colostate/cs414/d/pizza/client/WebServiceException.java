package edu.colostate.cs414.d.pizza.client;

public class WebServiceException extends RuntimeException {

	/**
	 * Creates a new instance of <code>WebServiceException</code> without detail
	 * message.
	 */
	public WebServiceException() {
	}

	/**
	 * Constructs an instance of <code>WebServiceException</code> with the
	 * specified detail message.
	 *
	 * @param msg the detail message.
	 */
	public WebServiceException(String msg) {
		super(msg);
	}
}
