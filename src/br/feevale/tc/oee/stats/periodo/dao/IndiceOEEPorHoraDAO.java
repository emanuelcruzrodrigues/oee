package br.feevale.tc.oee.stats.periodo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.joda.time.LocalTime;
import org.springframework.stereotype.Repository;

import br.feevale.tc.oee.domain.ApontamentoQuantidade;
import br.feevale.tc.oee.domain.ApontamentoTempoParada;
import br.feevale.tc.oee.domain.ApontamentoTempoProducao;
import br.feevale.tc.oee.domain.ProgramacaoProducaoEquipamento;
import br.feevale.tc.oee.framework.dao.DAO;
import br.feevale.tc.oee.stats.periodo.filter.IndiceOEEPorHoraFilter;

@Repository
public class IndiceOEEPorHoraDAO implements IndiceOEEPorPeriodoDAO<IndiceOEEPorHoraFilter> {
	
	@Resource
	private DAO dao;

	@Override
	public List<ProgramacaoProducaoEquipamento> queryProgramacoes(IndiceOEEPorHoraFilter filter) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();
		
		hql.append("select ppeq from ProgramacaoProducaoEquipamento ppeq ");
		hql.append(" where ppeq.equipamento.id = ? ");
		params.add(filter.getEquipamento().getId());
		hql.append(" and (  ");
		
		hql.append("  ( ppeq.dtHrInicio >= ? and ppeq.dtHrInicio <= ? ) ");
		params.add(filter.getDt().toLocalDateTime(new LocalTime(0,0,0)));
		params.add(filter.getDt().toLocalDateTime(new LocalTime(23,59,59,999)));
		
		hql.append("  or ( ppeq.dtHrFim >= ? and ppeq.dtHrFim <= ? ) ");
		params.add(filter.getDt().toLocalDateTime(new LocalTime(0,0,0)));
		params.add(filter.getDt().toLocalDateTime(new LocalTime(23,59,59,999)));
		
		hql.append(" ) ");
		
		List<ProgramacaoProducaoEquipamento> result = dao.query(hql.toString(), params.toArray());
		return result;
		
	}

	@Override
	public List<ApontamentoTempoParada> queryParadas(IndiceOEEPorHoraFilter filter) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();
		
		hql.append("select atpa from ApontamentoTempoParada atpa ");
		hql.append(" where atpa.equipamento.id = ? ");
		params.add(filter.getEquipamento().getId());
		hql.append(" and (  ");
		
		hql.append("  ( atpa.dtHrEntrada >= ? and atpa.dtHrEntrada <= ? ) ");
		params.add(filter.getDt().toLocalDateTime(new LocalTime(0,0,0)));
		params.add(filter.getDt().toLocalDateTime(new LocalTime(23,59,59,999)));
		
		hql.append("  or ( atpa.dtHrSaida >= ? and atpa.dtHrSaida <= ? ) ");
		params.add(filter.getDt().toLocalDateTime(new LocalTime(0,0,0)));
		params.add(filter.getDt().toLocalDateTime(new LocalTime(23,59,59,999)));
		
		hql.append("  or ( atpa.dtHrEntrada <= ? and atpa.dtHrSaida is null ) ");
		params.add(filter.getDt().toLocalDateTime(new LocalTime(0,0,0)));
		
		hql.append(" ) ");
		
		List<ApontamentoTempoParada> result = dao.query(hql.toString(), params.toArray());
		return result;
	}

	@Override
	public List<ApontamentoQuantidade> queryUnidadesProduzidas(IndiceOEEPorHoraFilter filter) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();
		
		hql.append("select apqt from ApontamentoQuantidade apqt ");
		hql.append(" inner join apqt.ordemProducao orpr ");
		
		hql.append(" where orpr.equipamento.id = ? ");
		params.add(filter.getEquipamento().getId());
		
		hql.append(" and apqt.dtHr >= ? and apqt.dtHr <= ? ");
		params.add(filter.getDt().toLocalDateTime(new LocalTime(0,0,0)));
		params.add(filter.getDt().toLocalDateTime(new LocalTime(23,59,59,999)));
		
		List<ApontamentoQuantidade> result = dao.query(hql.toString(), params.toArray());
		return result;
	}

	@Override
	public List<ApontamentoTempoProducao> queryApontamentosProducao(IndiceOEEPorHoraFilter filter) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();
		
		hql.append("select atpd from ApontamentoTempoProducao atpd ");
		hql.append(" where atpd.equipamento.id = ? ");
		params.add(filter.getEquipamento().getId());
		hql.append(" and (  ");
		
		hql.append("  ( atpd.dtHrEntrada >= ? and atpd.dtHrEntrada <= ? ) ");
		params.add(filter.getDt().toLocalDateTime(new LocalTime(0,0,0)));
		params.add(filter.getDt().toLocalDateTime(new LocalTime(23,59,59,999)));
		
		hql.append("  or ( atpd.dtHrSaida >= ? and atpd.dtHrSaida <= ? ) ");
		params.add(filter.getDt().toLocalDateTime(new LocalTime(0,0,0)));
		params.add(filter.getDt().toLocalDateTime(new LocalTime(23,59,59,999)));
		
		hql.append("  or ( atpd.dtHrEntrada <= ? and atpd.dtHrSaida is null ) ");
		params.add(filter.getDt().toLocalDateTime(new LocalTime(0,0,0)));
		
		hql.append(" ) ");
		
		List<ApontamentoTempoProducao> result = dao.query(hql.toString(), params.toArray());
		return result;
	}

}
