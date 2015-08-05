package br.feevale.tc.oee.dao.templates;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import br.feevale.tc.oee.dao.components.DAO;

public abstract class CRUDDAOTemplateImpl<T extends Serializable> implements CRUDDAOTemplate<T>{

	@Resource
	protected DAO dao;
	
	@Override
	public T save(T t) {
		dao.save(t);
		return t;
	}

	@Override
	public List<T> queryByExample(T example) {
		return dao.queryByExample(getBeanClazz(), example);
	}
	
	protected abstract Class<T> getBeanClazz();

	@Override
	public T get(Serializable id) {
		return get(id, true);
	}
	
	@Override
	public T get(Serializable id, boolean initialize) {
		if (id == null) return null;
		
		T t = dao.get(getBeanClazz(), id);
		if (t != null && initialize){
			initialize(t);
		}
		
		return t;
	}

	protected abstract void initialize(T t);

	@Override
	public void delete(T t) {
		dao.delete(t);
	}

}
