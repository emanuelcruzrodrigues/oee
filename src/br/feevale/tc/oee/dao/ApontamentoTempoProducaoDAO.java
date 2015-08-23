package br.feevale.tc.oee.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Order;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Repository;

import br.feevale.tc.oee.domain.ApontamentoTempoProducao;
import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.framework.dao.CRUDDAOTemplateImpl;

@Repository
public class ApontamentoTempoProducaoDAO extends CRUDDAOTemplateImpl<ApontamentoTempoProducao>{
	
	@Resource
	private ApontamentoTempoDAO apontamentoTempoDAO;

	@Override
	protected List<Order> getDefaultOrders() {
		return Arrays.asList(Order.asc("dtHrEntrada"), Order.asc("dtHrSaida"));
	}
	
	@Override
	public List<ApontamentoTempoProducao> queryByExample(ApontamentoTempoProducao example) {
		StringBuilder hql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		
		hql.append(" select atpd from ApontamentoTempoProducao atpd ");
		hql.append(" inner join atpd.ordemProducao orpr ");
		
		hql.append(" where atpd.dtHrEntrada >= ? ");
		params.add(example.getDtInicial().toLocalDateTime(new LocalTime(0,0,0,0)));
		
		hql.append(" and atpd.dtHrEntrada <= ? ");
		params.add(example.getDtFinal().toLocalDateTime(new LocalTime(23,59,59,999)));
		
		if (example.getOrdemProducao() != null){
			hql.append(" and orpr.id = ? ");
			params.add(example.getOrdemProducao().getId());
		}
		
		if (example.getEquipamento() != null){
			hql.append(" and orpr.equipamento.id = ? ");
			params.add(example.getEquipamento().getId());
		}
		
		hql.append(" order by atpd.dtHrEntrada, atpd.dtHrSaida ");
		
		List<ApontamentoTempoProducao> result = dao.query(hql.toString(), params.toArray());
		for (ApontamentoTempoProducao apontamentoTempoProducao : result) {
			initialize(apontamentoTempoProducao);
		}
		return result;
	}

	@Override
	public Class<ApontamentoTempoProducao> getBeanClazz() {
		return ApontamentoTempoProducao.class;
	}

	@Override
	protected void initialize(ApontamentoTempoProducao apontamento) {
		apontamentoTempoDAO.initialize(apontamento);
		dao.initialize(apontamento.getOrdemProducao());
	}

	public ApontamentoTempoProducao getApontamentoAtual(OrdemProducao ordemProducao) {
		StringBuilder hql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		
		hql.append(" select atpd from ApontamentoTempoProducao atpd ");
		
		hql.append(" where atpd.ordemProducao.id = ? ");
		params.add(ordemProducao.getId());
		
		hql.append(" and atpd.dtHrSaida is null ");
		
		return dao.uniqueResult(hql.toString(), params.toArray());
	}

}
