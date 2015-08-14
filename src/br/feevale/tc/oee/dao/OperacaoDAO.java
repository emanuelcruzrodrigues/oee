package br.feevale.tc.oee.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import br.feevale.tc.oee.domain.Operacao;
import br.feevale.tc.oee.enums.AtivoInativo;
import br.feevale.tc.oee.framework.dao.CRUDDAOTemplateImpl;
import br.feevale.tc.oee.framework.dao.UniqueKeyDAO;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
@Repository
public class OperacaoDAO extends CRUDDAOTemplateImpl<Operacao> implements UniqueKeyDAO<Operacao>{

	@Override
	protected Class<Operacao> getBeanClazz() {
		return Operacao.class;
	}

	@Override
	protected void initialize(Operacao t) {}

	@Override
	public Serializable queryUniqueKeyId(Operacao example) {
		StringBuilder hql = new StringBuilder();
		hql.append("select oper.id from Operacao oper where oper.codigo = ? ");
		return dao.uniqueResult(hql.toString(), example.getCodigo());
	}
	
	@Override
	protected List<Order> getDefaultOrders() {
		return Arrays.asList(Order.asc("codigo"));
	}

	public List<Operacao> queryOperacoesAtivas() {
		StringBuilder hql = new StringBuilder();
		hql.append("select oper from Operacao oper ");
		hql.append(" where oper.dmSituacao = ? ");
		hql.append(" order by oper.nome ");
		return dao.query(hql.toString(), AtivoInativo.ATIVO);
	}

}
