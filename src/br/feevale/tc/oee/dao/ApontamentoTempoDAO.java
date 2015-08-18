package br.feevale.tc.oee.dao;

import java.util.ArrayList;
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
		dao.initialize(apontamento.getOrdemProducao());
		
		Equipamento equipamento = apontamento.getOrdemProducao().getEquipamento();
		dao.initialize(equipamento);
		apontamento.setEquipamento(equipamento);
	}

	public List<ApontamentoTempo> queryOutrosApontamentosAbertos(ApontamentoTempo apontamento) {
		StringBuilder hql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		hql.append("select apte from ApontamentoTempo apte ");
		hql.append(" inner join apte.ordemProducao orpr ");
		
		hql.append(" where apte.id <> ? ");
		params.add(apontamento.getId());
		
		hql.append(" and apte.dtHrSaida is null ");
		
		hql.append(" and orpr.equipamento.id = ? ");
		params.add(apontamento.getOrdemProducao().getEquipamento().getId());
		
		return dao.query(hql.toString(), params.toArray());
		
	}

}
