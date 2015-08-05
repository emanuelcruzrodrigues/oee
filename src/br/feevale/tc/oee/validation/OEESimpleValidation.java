package br.feevale.tc.oee.validation;

import java.util.ArrayList;
import java.util.List;

public abstract class OEESimpleValidation extends OEEValidationStack{

	private OEEValidationHandler handler;
	
	public OEESimpleValidation(OEEValidationHandler handler) {
		super();
		this.handler = handler;
	}

	@Override
	public OEEValidationHandler getHandler() {
		return handler;
	}

	@Override
	public List<OEEValidation> getValidations() {
		List<OEEValidation> validations = new ArrayList<>();
		validations.add(getSimpleValidation());
		return validations;
	}

	public OEEValidation getSimpleValidation(){
		return new OEEValidation() {
			@Override
			public void validate(OEEValidationResult result) {
				executeSimpleValidation(result);
			}
		}; 
	}

	public abstract void executeSimpleValidation(OEEValidationResult result);
}
