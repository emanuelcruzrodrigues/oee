package br.feevale.tc.oee.stats.periodo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import br.feevale.tc.oee.domain.ApontamentoQuantidade;
import br.feevale.tc.oee.domain.ApontamentoTempoParada;
import br.feevale.tc.oee.domain.ApontamentoTempoProducao;
import br.feevale.tc.oee.domain.ProgramacaoProducaoEquipamento;
import br.feevale.tc.oee.framework.dao.DAO;
import br.feevale.tc.oee.stats.periodo.filter.IndiceOEEPorPeriodoFilter;

public abstract class IndiceOEEPorPeriodoDAO<T extends IndiceOEEPorPeriodoFilter> {

	@Resource
	private DAO dao;

	public List<ProgramacaoProducaoEquipamento> queryProgramacoes(T filter) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();
		
		hql.append("select ppeq from ProgramacaoProducaoEquipamento ppeq ");
		
		hql.append(" where ppeq.equipamento.id = ? ");
		params.add(filter.getEquipamento().getId());
		hql.append(" and (  ");
		
		//iniciando antes e terminando no periodo do filtro
		hql.append("     ( ppeq.dtHrInicio <= ? and ppeq.dtHrFim >= ? and ppeq.dtHrFim <= ? ) ");
		params.add(filter.getDtHrInicial());
		params.add(filter.getDtHrInicial());
		params.add(filter.getDtHrFinal());
		
		//iniciando e terminando no periodo do filtro
		hql.append("  or ( ppeq.dtHrInicio >= ? and ppeq.dtHrInicio <= ? ) ");
		params.add(filter.getDtHrInicial());
		params.add(filter.getDtHrFinal());
		
		//iniciando antes e terminando depois do periodo do filtro
		hql.append("  or ( ppeq.dtHrInicio <= ? and ppeq.dtHrFim >= ? ) ");
		params.add(filter.getDtHrInicial());
		params.add(filter.getDtHrFinal());
		
		hql.append(" ) ");
		
		List<ProgramacaoProducaoEquipamento> result = dao.query(hql.toString(), params.toArray());
		return result;
		
	}

	public List<ApontamentoTempoParada> queryParadas(T filter) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();
		
		hql.append("select atpa from ApontamentoTempoParada atpa ");
		hql.append(" where atpa.equipamento.id = ? ");
		params.add(filter.getEquipamento().getId());
		
		hql.append(" and (  ");
		
		//iniciando antes e terminando no periodo do filtro
		hql.append("     ( atpa.dtHrEntrada <= ? and atpa.dtHrSaida >= ? and atpa.dtHrSaida <= ? ) ");
		params.add(filter.getDtHrInicial());
		params.add(filter.getDtHrInicial());
		params.add(filter.getDtHrFinal());
		
		//iniciando e terminando no periodo do filtro
		hql.append("  or ( atpa.dtHrEntrada >= ? and atpa.dtHrEntrada <= ? ) ");
		params.add(filter.getDtHrInicial());
		params.add(filter.getDtHrFinal());
		
		//iniciando antes e terminando depois do periodo do filtro
		hql.append("  or ( atpa.dtHrEntrada <= ? and atpa.dtHrSaida >= ? ) ");
		params.add(filter.getDtHrInicial());
		params.add(filter.getDtHrFinal());
		
		// ainda em andamento
		hql.append("  or ( atpa.dtHrSaida is null ) ");

		
		hql.append(" ) ");
		
		List<ApontamentoTempoParada> result = dao.query(hql.toString(), params.toArray());
		return result;
	}

	public List<ApontamentoQuantidade> queryUnidadesProduzidas(T filter) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();
		
		hql.append("select apqt from ApontamentoQuantidade apqt ");
		hql.append(" inner join apqt.ordemProducao orpr ");
		
		hql.append(" where orpr.equipamento.id = ? ");
		params.add(filter.getEquipamento().getId());
		
		hql.append(" and apqt.dtHr >= ? and apqt.dtHr <= ? ");
		params.add(filter.getDtHrInicial());
		params.add(filter.getDtHrFinal());
		
		List<ApontamentoQuantidade> result = dao.query(hql.toString(), params.toArray());
		return result;
	}

	public List<ApontamentoTempoProducao> queryApontamentosProducao(T filter) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();
		
		hql.append("select atpd from ApontamentoTempoProducao atpd ");
		
		hql.append(" where atpd.equipamento.id = ? ");
		params.add(filter.getEquipamento().getId());
		
		hql.append(" and (  ");
		
		//iniciando antes e terminando no periodo do filtro
		hql.append("     ( atpd.dtHrEntrada <= ? and atpd.dtHrSaida >= ? and atpd.dtHrSaida <= ? ) ");
		params.add(filter.getDtHrInicial());
		params.add(filter.getDtHrInicial());
		params.add(filter.getDtHrFinal());
		
		//iniciando e terminando no periodo do filtro
		hql.append("  or ( atpd.dtHrEntrada >= ? and atpd.dtHrEntrada <= ? ) ");
		params.add(filter.getDtHrInicial());
		params.add(filter.getDtHrFinal());
		
		//iniciando antes e terminando depois do periodo do filtro
		hql.append("  or ( atpd.dtHrEntrada <= ? and atpd.dtHrSaida >= ? ) ");
		params.add(filter.getDtHrInicial());
		params.add(filter.getDtHrFinal());
		
		// ainda em andamento
		hql.append("  or ( atpd.dtHrSaida is null ) ");
		
		hql.append(" ) ");
		
		List<ApontamentoTempoProducao> result = dao.query(hql.toString(), params.toArray());
		return result;
	}

}