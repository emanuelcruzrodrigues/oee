package br.feevale.tc.oee.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import br.feevale.tc.oee.domain.ApontamentoTempo;
import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.framework.dao.DAO;

@Repository
public class ApontamentoTempoDAO{
	
	@Resource
	private DAO dao;

	public void initialize(ApontamentoTempo apontamento) {
		dao.initialize(apontamento.getEquipamento());
	}

	public List<ApontamentoTempo> queryOutrosApontamentosAbertos(ApontamentoTempo apontamento) {
		StringBuilder hql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		hql.append("select apte from ApontamentoTempo apte ");
		
		hql.append(" where apte.id <> ? ");
		params.add(apontamento.getId());
		
		hql.append(" and apte.dtHrSaida is null ");
		
		hql.append(" and apte.equipamento.id = ? ");
		params.add(apontamento.getEquipamento().getId());
		
		return dao.query(hql.toString(), params.toArray());
		
	}

	public List<ApontamentoTempo> queryApontamentosAbertos(Equipamento equipamento) {
		if (equipamento == null || equipamento.getId() == null) return Collections.emptyList();
		StringBuilder hql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		hql.append("select apte from ApontamentoTempo apte ");
		
		hql.append(" where apte.dtHrSaida is null ");
		
		hql.append(" and apte.equipamento.id = ? ");
		params.add(equipamento.getId());
		
		return dao.query(hql.toString(), params.toArray());
	}

	public List<Equipamento> queryEquipamentosEmExecucao() {
		StringBuilder hql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		hql.append("select distinct equi from ApontamentoTempo apte ");
		hql.append(" inner join apte.equipamento equi ");
		hql.append(" where apte.dtHrSaida is null ");
		hql.append(" order by equi.nome ");
		
		return dao.query(hql.toString(), params.toArray());
	}
	
}
