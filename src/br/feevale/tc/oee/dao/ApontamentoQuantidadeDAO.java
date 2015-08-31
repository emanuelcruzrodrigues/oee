package br.feevale.tc.oee.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.Order;
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
	public List<ApontamentoQuantidade> queryByExample(ApontamentoQuantidade example) {
		StringBuilder hql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		hql.append("select apqu from ApontamentoQuantidade apqu ");
		hql.append(" inner join apqu.ordemProducao orpr ");
		hql.append(" inner join orpr.equipamento equi ");
		hql.append(" where 1=1 ");
		
		if (example.getDtInicial() != null){
			hql.append(" and apqu.dtHr >= ? ");
			params.add(example.getDtInicial().toLocalDateTime(new LocalTime(0,0,0,0)));
		}
		
		if (example.getDtInicial() != null){
			hql.append(" and apqu.dtHr <= ? ");
			params.add(example.getDtFinal().toLocalDateTime(new LocalTime(23,59,59,999)));
		}
		
		if (example.getOrdemProducao() != null){
			hql.append(" and orpr.id = ? ");
			params.add(example.getOrdemProducao().getId());
		}
		
		if (example.getDmQualidade() != null){
			hql.append(" and apqu.dmQualidade = ? ");
			params.add(example.getDmQualidade());
		}
		
		if (example.getEquipamento() != null){
			hql.append(" and equi.id = ? ");
			params.add(example.getEquipamento().getId());
		}
		
		hql.append(" order by apqu.dtHr ");
		
		return dao.query(hql.toString(), params.toArray());
	}

}
