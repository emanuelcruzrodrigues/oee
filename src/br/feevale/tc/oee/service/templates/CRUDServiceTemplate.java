package br.feevale.tc.oee.service.templates;

import java.io.Serializable;
import java.util.List;

public interface CRUDServiceTemplate<T extends Serializable> {
	
	public T save(T t);
	public List<T> getByExample(T example);
	public T get(Serializable id);
	public T get(Serializable id, boolean initialize);
	public void delete(T t);
	

}
