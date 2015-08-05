package br.feevale.tc.oee.validation;

import java.io.Serializable;

import br.feevale.tc.oee.i18n.DefaultMessages;

@SuppressWarnings("serial")
public class OEEValidationMessage implements Serializable{
	
	private String message;
	private String[] messageParameters;
	private boolean parametrosTraduzidos;
	private String componentId;
	
	public OEEValidationMessage(String message) {
		this(message, null, false, null);
	}
	
	public OEEValidationMessage(String message, String[] messageParameters) {
		this(message, null, false, messageParameters);
	}
	
	public OEEValidationMessage(String message, boolean parametrosTraduzidos, String[] messageParameters) {
		this(message, null, parametrosTraduzidos, messageParameters);
	}
	
	public OEEValidationMessage(String message, String componentId) {
		this(message, componentId, false, null);
	}
	
	public OEEValidationMessage(String message, String componentId, String[] messageParameters) {
		this(message, componentId, false, messageParameters);
	}
	
	public OEEValidationMessage(String message, String componentId, boolean parametrosTraduzidos, String[] messageParameters) {
		super();
		this.message = message;
		this.messageParameters = messageParameters;
		this.componentId = componentId;
		this.parametrosTraduzidos = parametrosTraduzidos;
	}
	

	public String getComponentId() {
		return componentId;
	}

	@Override
	public String toString() {
		if (message == null) return null;
		return DefaultMessages.get(message, parametrosTraduzidos, messageParameters);
	}


}
