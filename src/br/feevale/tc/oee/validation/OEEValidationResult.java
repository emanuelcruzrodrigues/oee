package br.feevale.tc.oee.validation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class OEEValidationResult implements Serializable{
	
	private List<OEEValidationMessage> errors;
	private List<OEEValidationMessage> warnings;
	private List<OEEValidationMessage> messages;
	
	public OEEValidationResult() {
		errors = new ArrayList<>();
		warnings = new ArrayList<>();
		messages = new ArrayList<>();
	}
	
	public void addError(String error) {
		addError(new OEEValidationMessage(error));
	}
	
	public void addError(OEEValidationMessage error){
		errors.add(error);
	}
	
	public void addErrors(List<OEEValidationMessage> messages) {
		errors.addAll(messages);
	}


	public void addWarning(String warning) {
		addWarning(new OEEValidationMessage(warning));
	}
	
	public void addWarning(OEEValidationMessage warning){
		warnings.add(warning);
	}
	
	public void addMessage(String message) {
		addMessage(new OEEValidationMessage(message));
	}
	
	public void addMessage(OEEValidationMessage message){
		messages.add(message);
	}

	public String getMessagesAsString() {
		return transformListToString(messages);
	}

	public String getWarningsAsString() {
		return transformListToString(warnings);
	}
	
	public String getErrorsAsString() {
		return transformListToString(errors);
	}
	
	private String transformListToString(List<OEEValidationMessage> messages) {
		StringBuilder result = new StringBuilder();
		for (OEEValidationMessage message : messages) {
			String errorMessage = message.toString();
			if (errorMessage == null) continue;
			
			result.append(errorMessage).append("\n");
		}
		return result.toString();
	}

	public boolean hasMessages() {
		return messages.size() > 0;
	}

	public boolean hasWarnings() {
		return warnings.size() > 0;
	}

	public boolean hasErrors() {
		return errors.size() > 0;
	}

	public void addNotNullError(String fieldLabel) {
		addError(new OEEValidationMessage("CAMPO_X_OBRIGATORIO", new String[]{fieldLabel}));
	}

	public void addFieldNotNullError(String fieldLabel, String componentId) {
		addError(new OEEValidationMessage("CAMPO_X_OBRIGATORIO", componentId, new String[]{fieldLabel}));
	}

	public List<OEEValidationMessage> getErrors() {
		return errors;
	}

	public List<OEEValidationMessage> getWarnings() {
		return warnings;
	}

	public List<OEEValidationMessage> getMessages() {
		return messages;
	}

	public List<String> getIdsComponentesComErro() {
		List<String> result = new ArrayList<>();
		for (OEEValidationMessage error : errors) {
			if (error.getComponentId() != null){
				result.add(error.getComponentId());
			}
		}
		return result;
	}

	public void addFieldNotNullWarning(String fieldLabel, String componentId) {
		addWarning(new OEEValidationMessage("X_NAO_FOI_INFORMADO", componentId, new String[]{fieldLabel}));
	}

	public boolean hasError(String errorMessage) {
		for (OEEValidationMessage error : getErrors()) {
			if (error.toString().equals(errorMessage)) return true;
		}
		return false;
	}

	
}
