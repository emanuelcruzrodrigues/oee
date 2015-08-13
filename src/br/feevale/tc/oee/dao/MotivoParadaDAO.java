package br.feevale.tc.oee.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.feevale.tc.oee.domain.MotivoParada;
import br.feevale.tc.oee.framework.dao.CRUDDAOTemplateImpl;
import br.feevale.tc.oee.framework.dao.UniqueKeyDAO;

@Repository
public class MotivoParadaDAO extends CRUDDAOTemplateImpl<MotivoParada> implements UniqueKeyDAO<MotivoParada>{

	@Override
	protected Class<MotivoParada> getBeanClazz() {
		return MotivoParada.class;
	}

	@Override
	protected void initialize(MotivoParada t) {}

	@Override
	public Serializable queryUniqueKeyId(MotivoParada example) {
		StringBuilder hql = new StringBuilder();
		hql.append("select mopa.id from MotivoParada mopa where mopa.codigo = ? ");
		return dao.uniqueResult(hql.toString(), example.getCodigo());
	}

}
