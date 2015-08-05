package br.feevale.tc.oee.dao.templates;

import java.io.Serializable;

public interface UniqueKeyDAO<T> {

	Serializable queryUniqueKeyId(T example);
	
}
