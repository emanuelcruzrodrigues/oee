package br.feevale.tc.oee.dao;

import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import br.feevale.tc.oee.domain.ApontamentoQuantidade;
import br.feevale.tc.oee.framework.dao.CRUDDAOTemplateImpl;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
@Repository
public class ApontamentoQuantidadeDAO extends CRUDDAOTemplateImpl<ApontamentoQuantidade>{

	@Override
	protected Class<ApontamentoQuantidade> getBeanClazz() {
		return ApontamentoQuantidade.class;
	}

	@Override
	protected void initialize(ApontamentoQuantidade apontamentoQuantidade) {}

	@Override
	protected List<Order> getDefaultOrders() {
		return Arrays.asList(Order.asc("dtHr"));
	}

}
