package br.feevale.tc.oee.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
public class OEEValidationFakeStack extends OEEValidationStack{

	@Override
	public OEEValidationHandler getHandler() {
		return new OEEValidationFakeHandler();
	}

	@Override
	public List<OEEValidation> getValidations() {
		return new ArrayList<>();
	}

}
