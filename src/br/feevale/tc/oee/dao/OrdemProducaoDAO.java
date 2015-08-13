package br.feevale.tc.oee.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.framework.dao.CRUDDAOTemplateImpl;
import br.feevale.tc.oee.framework.dao.UniqueKeyDAO;

@Repository
public class OrdemProducaoDAO extends CRUDDAOTemplateImpl<OrdemProducao> implements UniqueKeyDAO<OrdemProducao>{

	@Override
	protected Class<OrdemProducao> getBeanClazz() {
		return OrdemProducao.class;
	}

	@Override
	protected void initialize(OrdemProducao t) {}

	@Override
	public Serializable queryUniqueKeyId(OrdemProducao example) {
		StringBuilder hql = new StringBuilder();
		hql.append("select orpr.id from OrdemProducao orpr where orpr.codigo = ? ");
		return dao.uniqueResult(hql.toString(), example.getCodigo());
	}

}
