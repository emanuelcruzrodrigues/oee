package br.feevale.tc.oee.validation;

import java.io.Serializable;

import br.feevale.tc.oee.dao.templates.UniqueKeyDAO;

public abstract class UniqueKeyValidationTemplate<T> implements OEEValidation{
	
	@Override
	public void validate(OEEValidationResult result) {
		if (result.hasErrors()) return;
		Serializable id = getBullcontroUniqueKeyDAO().queryUniqueKeyId(getBean());
		if (id != null && !id.equals(getBeanId())){
			OEEValidationMessage error = getErrorMessage();
			if (error == null){
				error = new OEEValidationMessage("X_EM_USO_POR_OUTRO_Y", getComponentId(), new String[]{getUniqueKeyLabelKey(),getBeanLabelKey()});
			}
			result.addError(error);
		}
		
	}

	protected abstract UniqueKeyDAO<T> getBullcontroUniqueKeyDAO();
	protected abstract T getBean();
	protected abstract Serializable getBeanId();
	
	protected OEEValidationMessage getErrorMessage() {
		return null;
	}
	
	protected String getUniqueKeyLabelKey(){
		return null;
	}
	
	protected String getBeanLabelKey(){
		return null;
	}
	
	protected String getComponentId(){
		return null;
	}

}
