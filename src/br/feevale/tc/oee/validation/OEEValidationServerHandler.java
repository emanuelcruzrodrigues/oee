package br.feevale.tc.oee.validation;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import br.feevale.tc.oee.exceptions.OEEException;

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
			OEEException exception = new OEEException(result.getErrorsAsString());
			exception.setValidationMessages(result.getErrors());
			exception.setIdsComponentesComErro(result.getIdsComponentesComErro());
			throw exception;
		}
		if (result.hasWarnings()){
			getLogger().warn(result.getWarningsAsString());
		}
		if (result.hasMessages()){
			getLogger().info(result.getMessagesAsString());
		}
	}

	private Logger getLogger() {
		if (logger != null){
			return logger;
		}
		return Logger.getLogger(getClass());
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}


}
