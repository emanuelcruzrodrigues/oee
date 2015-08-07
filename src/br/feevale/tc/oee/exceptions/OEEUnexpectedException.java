package br.feevale.tc.oee.exceptions;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@SuppressWarnings("serial")
public class OEEUnexpectedException extends RuntimeException{
	
	public OEEUnexpectedException(Throwable throwable) {
		super(throwable);
	}
	
	public OEEUnexpectedException(String message) {
		super(message);
	}
	

}
