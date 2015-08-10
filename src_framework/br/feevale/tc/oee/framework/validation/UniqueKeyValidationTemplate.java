package br.feevale.tc.oee.framework.validation;

import java.io.Serializable;

import br.feevale.tc.oee.framework.dao.UniqueKeyDAO;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
public abstract class UniqueKeyValidationTemplate<T> implements OEEValidation{
	
	@Override
	public void validate(OEEValidationResult result) {
		if (result.hasErrors()) return;
		Serializable id = getBullcontroUniqueKeyDAO().queryUniqueKeyId(getBean());
		if (id != null && !id.equals(getBeanId())){
			OEEValidationMessage error = getErrorMessage();
			result.addError(error);
		}
		
	}

	protected abstract UniqueKeyDAO<T> getBullcontroUniqueKeyDAO();
	protected abstract T getBean();
	protected abstract Serializable getBeanId();
	
	protected abstract OEEValidationMessage getErrorMessage();
	
}
