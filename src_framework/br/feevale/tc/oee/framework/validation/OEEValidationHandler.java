package br.feevale.tc.oee.framework.validation;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
public interface OEEValidationHandler {
	
	public void injectDependencies(OEEValidation validation);
	public void handle(OEEValidationResult result);

}
