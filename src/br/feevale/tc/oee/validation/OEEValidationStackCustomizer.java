package br.feevale.tc.oee.validation;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
public interface OEEValidationStackCustomizer<T extends OEEValidationStack> {
	
	public void addValidations(T stack);

}
