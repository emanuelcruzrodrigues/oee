package br.feevale.tc.oee.framework.dao;

import java.io.Serializable;

public interface UniqueKeyDAO<T> {

	Serializable queryUniqueKeyId(T example);
	
}
