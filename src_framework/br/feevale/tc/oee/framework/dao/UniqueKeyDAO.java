package br.feevale.tc.oee.framework.dao;

import java.io.Serializable;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 * @param <T>
 */
public interface UniqueKeyDAO<T> {

	Serializable queryUniqueKeyId(T example);
	
}
