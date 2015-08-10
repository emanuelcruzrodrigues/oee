package br.feevale.tc.oee.framework.validation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@SuppressWarnings("serial")
public class OEEValidationResult implements Serializable{
	
	private List<OEEValidationMessage> errors;
	
	public OEEValidationResult() {
		errors = new ArrayList<>();
	}
	
	public void addError(String error) {
		addError(new OEEValidationMessage(error));
	}
	
	public void addFieldNotNullError(String field){
		addError(new OEEValidationMessage("CAMPO_X_OBRIGATORIO", new String[]{field}));
	}
	
	public void addError(OEEValidationMessage error){
		errors.add(error);
	}
	
	public void addErrors(List<OEEValidationMessage> messages) {
		errors.addAll(messages);
	}

	public boolean hasErrors() {
		return errors.size() > 0;
	}

	public List<OEEValidationMessage> getErrors() {
		return errors;
	}

}
