package br.feevale.tc.oee.framework.validation;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import br.feevale.tc.oee.framework.exceptions.OEEException;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@Service
public class OEEValidationServerHandler implements OEEValidationHandler, ApplicationContextAware{
	
	protected Logger logger;
	
	private ApplicationContext applicationContext;
	
	@Override
	public void injectDependencies(OEEValidation validation) {
		applicationContext.getAutowireCapableBeanFactory().autowireBean(validation);
	}

	@Override
	public void handle(OEEValidationResult result) {
		if (result.hasErrors()){
			OEEException exception = new OEEException(result);
			throw exception;
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}


}
