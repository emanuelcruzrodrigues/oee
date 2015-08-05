package br.feevale.tc.oee.validation;

public interface OEEValidationStackCustomizer<T extends OEEValidationStack> {
	
	public void addValidations(T stack);

}
