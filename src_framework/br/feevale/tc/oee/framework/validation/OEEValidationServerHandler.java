package br.feevale.tc.oee.framework.validation;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import br.feevale.tc.oee.framework.exceptions.OEEException;
import br.feevale.tc.oee.framework.service.OEEServices;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@Service
public class OEEValidationServerHandler implements OEEValidationHandler{
	
	protected Logger logger;
	
	@Override
	public void injectDependencies(OEEValidation validation) {
		OEEServices.autowireBean(validation);
	}

	@Override
	public void handle(OEEValidationResult result) {
		if (result.hasErrors()){
			OEEException exception = new OEEException(result);
			throw exception;
		}
	}



}
