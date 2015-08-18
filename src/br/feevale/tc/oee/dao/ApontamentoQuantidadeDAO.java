package br.feevale.tc.oee.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalTime;
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
	public Class<ApontamentoQuantidade> getBeanClazz() {
		return ApontamentoQuantidade.class;
	}

	@Override
	protected void initialize(ApontamentoQuantidade apontamentoQuantidade) {
		dao.initialize(apontamentoQuantidade.getOrdemProducao());
	}

	@Override
	protected List<Order> getDefaultOrders() {
		return Arrays.asList(Order.asc("dtHr"));
	}
	
	@Override
	protected List<Criterion> getAdicionalFiltersAtQueryByExample(ApontamentoQuantidade example) {
		List<Criterion> result = new ArrayList<>();
		if (example.getDtInicial() != null){
			result.add(Restrictions.ge("dtHr", example.getDtInicial().toLocalDateTime(new LocalTime(0,0,0,0))));
		}
		if (example.getDtFinal() != null){
			result.add(Restrictions.le("dtHr", example.getDtFinal().toLocalDateTime(new LocalTime(23,59,59,999))));
		}
		return result;
	}

}
