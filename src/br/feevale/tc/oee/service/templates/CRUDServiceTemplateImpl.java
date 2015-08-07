package br.feevale.tc.oee.service.templates;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import br.feevale.tc.oee.dao.templates.CRUDDAOTemplate;
import br.feevale.tc.oee.validation.OEEValidationFakeStack;
import br.feevale.tc.oee.validation.OEEValidationHandler;
import br.feevale.tc.oee.validation.OEEValidationStack;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 * @param <T>
 */
public abstract class CRUDServiceTemplateImpl<T extends Serializable> implements CRUDServiceTemplate<T>{

	@Resource
	protected OEEValidationHandler handler;
	
	@Override
	@Transactional
	public T save(T t) {
		OEEValidationStack beforeSaveValidationStack = getBeforeSaveValidationStack(t);
		beforeSaveValidationStack.validate();
		
		getCRUDDAO().save(t);
		
		getAfterSaveValidationStack(t).validate();
		
		return t;
	}

	protected OEEValidationStack getBeforeSaveValidationStack(T t){
		return new OEEValidationFakeStack();
	}
	
	protected OEEValidationStack getAfterSaveValidationStack(T t){
		return new OEEValidationFakeStack();
	}

	@Override
	public List<T> getByExample(T example) {
		return getCRUDDAO().queryByExample(example);
	}

	@Override
	public T get(Serializable id) {
		return get(id, true);
	}
	
	@Override
	public T get(Serializable id, boolean initialize) {
		return getCRUDDAO().get(id, initialize);
	}

	@Transactional
	@Override
	public void delete(T t) {
		OEEValidationStack beforeDeleteValidationStack = getBeforeDeleteValidationStack(t);
		
		beforeDeleteValidationStack.validate();
		getCRUDDAO().delete(t);
	}
	
	

	protected OEEValidationStack getBeforeDeleteValidationStack(T t){
		return new OEEValidationFakeStack();
	}
	
	protected abstract CRUDDAOTemplate<T> getCRUDDAO();

	public void setHandler(OEEValidationHandler handler) {
		this.handler = handler;
	}
	
}
