package br.feevale.tc.oee.framework.exceptions;

import br.feevale.tc.oee.framework.validation.OEEValidationResult;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@SuppressWarnings("serial")
public class OEEException extends RuntimeException{
	
	private OEEValidationResult validationResult;

	public OEEException(OEEValidationResult validationResult) {
		this.validationResult = validationResult;
	}

	public OEEValidationResult getValidationResult() {
		return validationResult;
	}
	

}
