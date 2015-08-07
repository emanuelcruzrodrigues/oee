package br.feevale.tc.oee.exceptions;

import java.util.List;

import br.feevale.tc.oee.i18n.DefaultMessages;
import br.feevale.tc.oee.validation.OEEValidationMessage;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@SuppressWarnings("serial")
public class OEEException extends RuntimeException{
	
	private String messageKey;
	private String[] messageParams;
	private boolean translatedParameters;
	private List<String> idsComponentesComErro;
	private List<OEEValidationMessage> validationMessages;
	
	public OEEException() {
		this((String)null);
	}
	
	public OEEException(String message) {
		super(message);
	}

	public OEEException(Throwable cause) {
		super(cause);
	}
	
	public static OEEException getI18nInstance(String messageKey){
		return getI18nInstance(messageKey, (String[])null);
	}
	
	public static OEEException getI18nInstance(String messageKey, String... params){
		return getI18nInstance(messageKey, false, params);
	}
	
	public static OEEException getI18nInstance(String messageKey, boolean translatedParameters, String... params){
		OEEException exception = new OEEException();
		exception.messageKey = messageKey;
		exception.messageParams = params;
		exception.translatedParameters = translatedParameters;
		return exception;
	}
	
	@Override
	public String getMessage() {
		if (messageKey != null){
			return DefaultMessages.get(messageKey, translatedParameters, messageParams);
		}
		return super.getMessage();
	}
	
	@Override
	public String getLocalizedMessage() {
		if (messageKey != null){
			return getMessage();
		}
		return super.getLocalizedMessage();
	}

	public List<OEEValidationMessage> getValidationMessages() {
		return validationMessages;
	}
	
	public void setValidationMessages(List<OEEValidationMessage> validationMessages) {
		this.validationMessages = validationMessages;
	}

	public List<String> getIdsComponentesComErro() {
		return idsComponentesComErro;
	}
	public void setIdsComponentesComErro(List<String> idsComponentesComErro) {
		this.idsComponentesComErro = idsComponentesComErro;
	}

	

}
