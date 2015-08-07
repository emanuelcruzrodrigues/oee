package br.feevale.tc.oee.validation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
public abstract class OEEValidationStack {

	public void validate() {
		List<OEEValidation> validations = getValidations();

		OEEValidationHandler handler = getHandler();

		OEEValidationResult result = new OEEValidationResult();

		if (CollectionUtils.isNotEmpty(validations)){
			for (OEEValidation validation : validations) {
				handler.injectDependencies(validation);
				validation.validate(result);
			}
		}

		handler.handle(result);
	}

	public abstract OEEValidationHandler getHandler();

	public abstract List<OEEValidation> getValidations();

	public static OEEValidationStack getInstance(final OEEValidationHandler handler, final OEEValidation... validations){
		return new OEEValidationStack() {

			@Override
			public List<OEEValidation> getValidations() {
				List<OEEValidation> result = new ArrayList<>();
				for (OEEValidation validation : validations) {
					result.add(validation);
				}
				return result;
			}

			@Override
			public OEEValidationHandler getHandler() {
				return handler;
			}
		};
	}

}
