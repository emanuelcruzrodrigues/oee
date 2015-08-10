package br.feevale.tc.oee.framework.validation;

import java.io.Serializable;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@SuppressWarnings("serial")
public class OEEValidationMessage implements Serializable{
	
	private String message;
	private String[] parameters;
	private String componentId;
	
	public OEEValidationMessage(String message) {
		this(message, new String[]{}, null);
	}
	
	public OEEValidationMessage(String message, String[] parameters) {
		this(message, parameters, null);
	}
	
	public OEEValidationMessage(String message, String[] parameters, String componentId) {
		super();
		this.message = message;
		this.parameters = parameters;
		this.componentId = componentId;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String[] getParameters() {
		return parameters;
	}

	public String getComponentId() {
		return componentId;
	}

	@Override
	public String toString() {
		if (message == null) return null;
		return message;
	}


}
