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
import br.feevale.tc.oee.stats.periodo.filter.IndiceOEEPorDiaFilter;

@Repository
public class IndiceOEEPorDiaDAO implements IndiceOEEPorPeriodoDAO<IndiceOEEPorDiaFilter> {
	
	@Resource
	private DAO dao;

	@Override
	public List<ProgramacaoProducaoEquipamento> queryProgramacoes(IndiceOEEPorDiaFilter filter) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();
		
		hql.append("select ppeq from ProgramacaoProducaoEquipamento ppeq ");
		
		hql.append(" where ppeq.equipamento.id = ? ");
		params.add(filter.getEquipamento().getId());
		hql.append(" and (  ");
		
		//iniciando antes e terminando no periodo do filtro
		hql.append("     ( ppeq.dtHrInicio <= ? and ppeq.dtHrFim <= ? ) ");
		params.add(filter.getDtInicial().toLocalDateTime(new LocalTime(0,0,0)));
		params.add(filter.getDtFinal().toLocalDateTime(new LocalTime(23,59,59,999)));
		
		//iniciando e terminando no periodo do filtro
		hql.append("  or ( ppeq.dtHrInicio >= ? and ppeq.dtHrInicio <= ? ) ");
		params.add(filter.getDtInicial().toLocalDateTime(new LocalTime(0,0,0)));
		params.add(filter.getDtFinal().toLocalDateTime(new LocalTime(23,59,59,999)));
		
		//iniciando antes e terminando depois do periodo do filtro
		hql.append("  or ( ppeq.dtHrInicio <= ? and ppeq.dtHrFim >= ? ) ");
		params.add(filter.getDtInicial().toLocalDateTime(new LocalTime(0,0,0)));
		params.add(filter.getDtFinal().toLocalDateTime(new LocalTime(23,59,59,999)));
		
		hql.append(" ) ");
		
		List<ProgramacaoProducaoEquipamento> result = dao.query(hql.toString(), params.toArray());
		return result;
		
	}

	@Override
	public List<ApontamentoTempoParada> queryParadas(IndiceOEEPorDiaFilter filter) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();
		
		hql.append("select atpa from ApontamentoTempoParada atpa ");
		hql.append(" where atpa.equipamento.id = ? ");
		params.add(filter.getEquipamento().getId());
		
		hql.append(" and (  ");
		
		//iniciando antes e terminando no periodo do filtro
		hql.append("     ( atpa.dtHrEntrada <= ? and atpa.dtHrSaida <= ? ) ");
		params.add(filter.getDtInicial().toLocalDateTime(new LocalTime(0,0,0)));
		params.add(filter.getDtFinal().toLocalDateTime(new LocalTime(23,59,59,999)));
		
		//iniciando e terminando no periodo do filtro
		hql.append("  or ( atpa.dtHrEntrada >= ? and atpa.dtHrEntrada <= ? ) ");
		params.add(filter.getDtInicial().toLocalDateTime(new LocalTime(0,0,0)));
		params.add(filter.getDtFinal().toLocalDateTime(new LocalTime(23,59,59,999)));
		
		//iniciando antes e terminando depois do periodo do filtro
		hql.append("  or ( atpa.dtHrEntrada <= ? and atpa.dtHrSaida >= ? ) ");
		params.add(filter.getDtInicial().toLocalDateTime(new LocalTime(0,0,0)));
		params.add(filter.getDtFinal().toLocalDateTime(new LocalTime(23,59,59,999)));
		
		hql.append(" ) ");
		
		List<ApontamentoTempoParada> result = dao.query(hql.toString(), params.toArray());
		return result;
	}

	@Override
	public List<ApontamentoQuantidade> queryUnidadesProduzidas(IndiceOEEPorDiaFilter filter) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();
		
		hql.append("select apqt from ApontamentoQuantidade apqt ");
		hql.append(" inner join apqt.ordemProducao orpr ");
		
		hql.append(" where orpr.equipamento.id = ? ");
		params.add(filter.getEquipamento().getId());
		
		hql.append(" and apqt.dtHr >= ? and apqt.dtHr <= ? ");
		params.add(filter.getDtInicial().toLocalDateTime(new LocalTime(0,0,0)));
		params.add(filter.getDtFinal().toLocalDateTime(new LocalTime(23,59,59,999)));
		
		List<ApontamentoQuantidade> result = dao.query(hql.toString(), params.toArray());
		return result;
	}

	@Override
	public List<ApontamentoTempoProducao> queryApontamentosProducao(IndiceOEEPorDiaFilter filter) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();
		
		hql.append("select atpd from ApontamentoTempoProducao atpd ");
		
		hql.append(" where atpd.equipamento.id = ? ");
		params.add(filter.getEquipamento().getId());
		
		hql.append(" and (  ");
		
		//iniciando antes e terminando no periodo do filtro
		hql.append("     ( atpd.dtHrEntrada <= ? and atpd.dtHrSaida <= ? ) ");
		params.add(filter.getDtInicial().toLocalDateTime(new LocalTime(0,0,0)));
		params.add(filter.getDtFinal().toLocalDateTime(new LocalTime(23,59,59,999)));
		
		//iniciando e terminando no periodo do filtro
		hql.append("  or ( atpd.dtHrEntrada >= ? and atpd.dtHrEntrada <= ? ) ");
		params.add(filter.getDtInicial().toLocalDateTime(new LocalTime(0,0,0)));
		params.add(filter.getDtFinal().toLocalDateTime(new LocalTime(23,59,59,999)));
		
		//iniciando antes e terminando depois do periodo do filtro
		hql.append("  or ( atpd.dtHrEntrada <= ? and atpd.dtHrSaida >= ? ) ");
		params.add(filter.getDtInicial().toLocalDateTime(new LocalTime(0,0,0)));
		params.add(filter.getDtFinal().toLocalDateTime(new LocalTime(23,59,59,999)));
		
		hql.append(" ) ");
		
		List<ApontamentoTempoProducao> result = dao.query(hql.toString(), params.toArray());
		return result;
	}

}
