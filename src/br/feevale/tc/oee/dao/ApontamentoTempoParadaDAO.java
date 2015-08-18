package br.feevale.tc.oee.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Order;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Repository;

import br.feevale.tc.oee.domain.ApontamentoTempoParada;
import br.feevale.tc.oee.framework.dao.CRUDDAOTemplateImpl;

@Repository
public class ApontamentoTempoParadaDAO extends CRUDDAOTemplateImpl<ApontamentoTempoParada>{
	
	@Resource
	private ApontamentoTempoDAO apontamentoTempoDAO;

	@Override
	protected List<Order> getDefaultOrders() {
		return Arrays.asList(Order.asc("dtHrEntrada"), Order.asc("dtHrSaida"));
	}

	@Override
	public Class<ApontamentoTempoParada> getBeanClazz() {
		return ApontamentoTempoParada.class;
	}
	
	@Override
	public List<ApontamentoTempoParada> queryByExample(ApontamentoTempoParada example) {
		StringBuilder hql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		
		hql.append(" select atpa from ApontamentoTempoParada atpa ");
		hql.append(" inner join atpa.ordemProducao orpr ");
		
		hql.append(" where atpa.dtHrEntrada >= ? ");
		params.add(example.getDtInicial().toLocalDateTime(new LocalTime(0,0,0,0)));
		
		hql.append(" and atpa.dtHrEntrada <= ? ");
		params.add(example.getDtFinal().toLocalDateTime(new LocalTime(23,59,59,999)));
		
		if (example.getOrdemProducao() != null){
			hql.append(" and orpr.id = ? ");
			params.add(example.getOrdemProducao().getId());
		}
		
		if (example.getEquipamento() != null){
			hql.append(" and orpr.equipamento.id = ? ");
			params.add(example.getEquipamento().getId());
		}
		
		if (example.getMotivoParada() != null){
			hql.append(" and atpa.motivoParada.id = ? ");
			params.add(example.getMotivoParada().getId());
		}
		
		List<ApontamentoTempoParada> result = dao.query(hql.toString(), params.toArray());
		for (ApontamentoTempoParada apontamento : result) {
			initialize(apontamento);
		}
		return result;
	}

	@Override
	protected void initialize(ApontamentoTempoParada apontamento) {
		apontamentoTempoDAO.initialize(apontamento);
		dao.initialize(apontamento.getMotivoParada());
	}

}
