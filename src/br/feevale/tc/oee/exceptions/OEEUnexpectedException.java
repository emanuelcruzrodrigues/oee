package br.feevale.tc.oee.exceptions;

@SuppressWarnings("serial")
public class OEEUnexpectedException extends RuntimeException{
	
	public OEEUnexpectedException(Throwable throwable) {
		super(throwable);
	}
	
	public OEEUnexpectedException(String message) {
		super(message);
	}
	

}
