package br.feevale.tc.oee.framework.service;

import java.io.Serializable;
import java.util.List;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 * @param <T>
 */
public interface CRUDServiceTemplate<T extends Serializable> {
	
	public T save(T t);
	public T saveAndFlush(T t);
	public List<T> getByExample(T example);
	public T get(Serializable id);
	public T get(Serializable id, boolean initialize);
	public void delete(T t);
	

}
