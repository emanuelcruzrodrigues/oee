package br.feevale.tc.oee.framework.dao;

import java.io.Serializable;
import java.util.List;

public interface CRUDDAOTemplate<T extends Serializable> {
	public T save(T t);
	public List<T> queryByExample(T example);
	public T get(Serializable id);
	public void delete(T t);
	public T get(Serializable id, boolean initialize);

}
