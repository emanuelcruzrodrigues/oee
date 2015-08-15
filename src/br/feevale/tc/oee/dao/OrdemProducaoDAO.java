package br.feevale.tc.oee.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.enums.SituacaoOrdemProducao;
import br.feevale.tc.oee.framework.dao.CRUDDAOTemplateImpl;
import br.feevale.tc.oee.framework.dao.UniqueKeyDAO;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
@Repository
public class OrdemProducaoDAO extends CRUDDAOTemplateImpl<OrdemProducao> implements UniqueKeyDAO<OrdemProducao>{

	@Override
	protected Class<OrdemProducao> getBeanClazz() {
		return OrdemProducao.class;
	}

	@Override
	protected void initialize(OrdemProducao ordemProducao) {
		dao.initialize(ordemProducao.getEquipamento());
	}

	@Override
	public Serializable queryUniqueKeyId(OrdemProducao example) {
		StringBuilder hql = new StringBuilder();
		hql.append("select orpr.id from OrdemProducao orpr where orpr.codigo = ? ");
		return dao.uniqueResult(hql.toString(), example.getCodigo());
	}

	@Override
	protected List<Order> getDefaultOrders() {
		return Arrays.asList(Order.asc("codigo"));
	}

	public List<OrdemProducao> queryOrdensProducaoAbertas() {
		StringBuilder hql = new StringBuilder();
		hql.append("select orpr from OrdemProducao orpr ");
		hql.append(" where orpr.dmSituacao = ? ");
		hql.append(" order by orpr.descricao ");
		return dao.query(hql.toString(), SituacaoOrdemProducao.ABERTA);
	}

}
