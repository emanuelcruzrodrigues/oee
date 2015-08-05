package br.feevale.tc.oee.validation;

public interface OEEValidationHandler {
	
	public void injectDependencies(OEEValidation validation);
	public void handle(OEEValidationResult result);

}
