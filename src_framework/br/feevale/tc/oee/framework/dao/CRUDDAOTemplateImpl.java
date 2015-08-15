package br.feevale.tc.oee.framework.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 * @param <T>
 */
public abstract class CRUDDAOTemplateImpl<T extends Serializable> implements CRUDDAOTemplate<T>{

	@Resource
	protected DAO dao;
	
	@Override
	public T save(T t) {
		return save(t, false);
	}
	
	@Override
	public T save(T t, boolean flush) {
		dao.save(t);
		if (flush){
			dao.flush();
		}
		return t;
	}

	@Override
	public List<T> queryByExample(T example) {
		List<Criterion> filtrosAdicionais = getAdicionalFiltersAtQueryByExample(example);
		List<Order> ordenacoes = getDefaultOrders();
		return dao.queryByExample(getBeanClazz(), example, filtrosAdicionais, ordenacoes);
	}
	
	protected abstract List<Order> getDefaultOrders();

	protected List<Criterion> getAdicionalFiltersAtQueryByExample(T example) {
		return new ArrayList<Criterion>();
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
