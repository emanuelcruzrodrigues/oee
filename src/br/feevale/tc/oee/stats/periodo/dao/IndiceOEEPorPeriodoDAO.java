package br.feevale.tc.oee.stats.periodo.dao;

import java.util.List;

import br.feevale.tc.oee.domain.ApontamentoQuantidade;
import br.feevale.tc.oee.domain.ApontamentoTempoParada;
import br.feevale.tc.oee.domain.ApontamentoTempoProducao;
import br.feevale.tc.oee.domain.ProgramacaoProducaoEquipamento;
import br.feevale.tc.oee.stats.periodo.filter.IndiceOEEPorPeriodoFilter;

public interface IndiceOEEPorPeriodoDAO<T extends IndiceOEEPorPeriodoFilter> {

	public abstract List<ProgramacaoProducaoEquipamento> queryProgramacoes(T filter);

	public abstract List<ApontamentoTempoParada> queryParadas(T filter);

	public abstract List<ApontamentoQuantidade> queryUnidadesProduzidas(T filter);

	public abstract List<ApontamentoTempoProducao> queryApontamentosProducao(T filter);

}