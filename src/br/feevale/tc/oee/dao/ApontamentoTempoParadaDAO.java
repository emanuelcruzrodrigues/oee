package br.feevale.tc.oee.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Order;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Repository;

import br.feevale.tc.oee.domain.ApontamentoTempoParada;
import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.domain.MotivoParada;
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
		
		hql.append(" where atpa.dtHrEntrada >= ? ");
		params.add(example.getDtInicial().toLocalDateTime(new LocalTime(0,0,0,0)));
		
		hql.append(" and atpa.dtHrEntrada <= ? ");
		params.add(example.getDtFinal().toLocalDateTime(new LocalTime(23,59,59,999)));
		
		if (example.getEquipamento() != null){
			hql.append(" and atpa.equipamento.id = ? ");
			params.add(example.getEquipamento().getId());
		}
		
		if (example.getMotivoParada() != null){
			hql.append(" and atpa.motivoParada.id = ? ");
			params.add(example.getMotivoParada().getId());
		}
		
		hql.append(" order by atpa.dtHrEntrada, atpa.dtHrSaida ");
		
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

	public ApontamentoTempoParada getApontamentoAtual(Equipamento equipamento, MotivoParada motivoParada) {
		StringBuilder hql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		
		hql.append(" select atpa from ApontamentoTempoParada atpa ");
		
		hql.append(" where atpa.motivoParada.id = ? ");
		params.add(motivoParada.getId());
		
		hql.append(" and atpa.equipamento.id = ? ");
		params.add(equipamento.getId());
		
		hql.append(" and atpa.dtHrSaida is null ");
		
		return dao.uniqueResult(hql.toString(), params.toArray());
	}
	
	public ApontamentoTempoParada getByCodigo(Integer codigo) {
		if (codigo == null) return null;
		
		String hql = "select atpa from ApontamentoTempoParada atpa where atpa.codigo = ? ";
		
		ApontamentoTempoParada result = dao.uniqueResult(hql, codigo);
		if (result != null){
			initialize(result);
		}
		
		return result;
	}

}
