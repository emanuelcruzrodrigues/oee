package br.feevale.tc.oee.framework.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 * @param <T>
 */
public interface CRUDDAOTemplate<T extends Serializable> {
	public T save(T t);
	public List<T> queryByExample(T example);
	public T get(Serializable id);
	public void delete(T t);
	public T get(Serializable id, boolean initialize);

}
