package br.feevale.tc.oee.stats.periodo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDateTime;

import br.feevale.tc.oee.domain.ApontamentoQuantidade;
import br.feevale.tc.oee.domain.ApontamentoTempoParada;
import br.feevale.tc.oee.domain.ApontamentoTempoProducao;
import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.domain.ProgramacaoProducaoEquipamento;
import br.feevale.tc.oee.stats.CalculadoraOEE;
import br.feevale.tc.oee.stats.DetalheUnidadeIndiceOEE;
import br.feevale.tc.oee.stats.UnidadeIndiceOEE;
import br.feevale.tc.oee.stats.periodo.dao.IndiceOEEPorPeriodoDAO;
import br.feevale.tc.oee.stats.periodo.filter.IndiceOEEPorPeriodoFilter;

public abstract class IndiceOEEPorPeriodoService<T extends IndiceOEEPorPeriodoFilter> {
	
	public List<UnidadeIndiceOEE> listIndicesOEE(T filter) {
		if (filter.getEquipamento() == null || filter.getEquipamento().getId() == null) return Collections.emptyList();
		
		Map<String, UnidadeIndiceOEE> unidadesPorCorte = new HashMap<>();
		
		updateProducao(filter, unidadesPorCorte);
		updateTempoCarga(filter, unidadesPorCorte);
		updateParadas(filter, unidadesPorCorte);
		updateUnidadesProduzidas(filter, unidadesPorCorte);
		
		List<UnidadeIndiceOEE> result = new ArrayList<>(unidadesPorCorte.values());
		Collections.sort(result);
		CalculadoraOEE calculadoraOEE = new CalculadoraOEE();
		for (UnidadeIndiceOEE unidade : result) {
			for (DetalheUnidadeIndiceOEE detalhe : unidade.getDetalhes()) {
				calculadoraOEE.calcularQualidade(detalhe);
			}
			calculadoraOEE.calcularOEE(unidade);
		}
		return result;
	}
	
	public void updateProducao(T filter, Map<String, UnidadeIndiceOEE> unidadesPorCorte) {
		List<ApontamentoTempoProducao> apontamentos = getIndiceOEEPorPeriodoDAO().queryApontamentosProducao(filter);
		for (ApontamentoTempoProducao apontamento : apontamentos) {
			List<UnidadeIndiceOEE> unidades = gerarUnidadesIndiceOEE(filter, apontamento.getDtHrEntrada(), apontamento.getDtHrSaida());
			for (UnidadeIndiceOEE novaUnidade : unidades) {
				OrdemProducao ordemProducao = apontamento.getOrdemProducao();
				novaUnidade.setRuntimeMinutos(novaUnidade.getTempoUtilMinutos());
				UnidadeIndiceOEE unidade = unidadesPorCorte.get(novaUnidade.getId());
				if (unidade != null){
					unidade.setTempoCicloTeoricoUnidadesPorMinuto(ordemProducao.getUnidadesPorMinuto());
					unidade.addRuntime(novaUnidade.getTempoUtilMinutos());
					unidade.addDetalhe(novaUnidade, ordemProducao);
				}else{
					unidadesPorCorte.put(novaUnidade.getId(), novaUnidade);
					novaUnidade.setTempoCicloTeoricoUnidadesPorMinuto(ordemProducao.getUnidadesPorMinuto());
					novaUnidade.addDetalhe(novaUnidade, ordemProducao);
				}
			}
		}
	}

	public void updateTempoCarga(T filter, Map<String, UnidadeIndiceOEE> unidadesPorCorte) {
		List<ProgramacaoProducaoEquipamento> programacoes = getIndiceOEEPorPeriodoDAO().queryProgramacoes(filter);
		for (ProgramacaoProducaoEquipamento programacao : programacoes) {
			List<UnidadeIndiceOEE> unidades = gerarUnidadesIndiceOEE(filter, programacao.getDtHrInicio(), programacao.getDtHrFim());
			for (UnidadeIndiceOEE novaUnidade : unidades) {
				UnidadeIndiceOEE unidade = unidadesPorCorte.get(novaUnidade.getId());
				if (unidade != null){
					unidade.setTempoCargaMinutos(novaUnidade.getTempoUtilMinutos());
				}else{
					unidadesPorCorte.put(novaUnidade.getId(), novaUnidade);
					novaUnidade.setTempoCargaMinutos(novaUnidade.getTempoUtilMinutos());
				}
			}
		}
	}
	
	public void updateParadas(T filter, Map<String, UnidadeIndiceOEE> unidadesPorCorte) {
		List<ApontamentoTempoParada> paradas = getIndiceOEEPorPeriodoDAO().queryParadas(filter);
		for (ApontamentoTempoParada apontamento : paradas) {
			List<UnidadeIndiceOEE> unidades = gerarUnidadesIndiceOEE(filter, apontamento.getDtHrEntrada(), apontamento.getDtHrSaida());
			for (UnidadeIndiceOEE novaUnidade : unidades) {
				UnidadeIndiceOEE unidade = unidadesPorCorte.get(novaUnidade.getId());
				if (unidade != null){
					unidade.addTempoParada(apontamento.getMotivoParada().getDmTipoParada(), novaUnidade.getTempoUtilMinutos());
				}else{
					unidadesPorCorte.put(novaUnidade.getId(), novaUnidade);
					novaUnidade.addTempoParada(apontamento.getMotivoParada().getDmTipoParada(), novaUnidade.getTempoUtilMinutos());
				}
			}
		}
	}
	
	public void updateUnidadesProduzidas(T filter, Map<String, UnidadeIndiceOEE> unidadesPorCorte) {
		List<ApontamentoQuantidade> quantidades = getIndiceOEEPorPeriodoDAO().queryUnidadesProduzidas(filter);
		for (ApontamentoQuantidade quantidade : quantidades) {
			
			UnidadeIndiceOEE novaUnidade = gerarDetalheIndiceOEE(filter, quantidade);
			if (novaUnidade == null) continue;
			
			UnidadeIndiceOEE unidade = unidadesPorCorte.get(novaUnidade.getId());
			if (unidade != null){
				unidade.addQuantidadeProduzida(quantidade.getQuantidade(), quantidade.getDmQualidade());
				unidade.addDetalhe(novaUnidade, quantidade.getOrdemProducao());
			}else{
				unidadesPorCorte.put(novaUnidade.getId(), novaUnidade);
				novaUnidade.addDetalhe(novaUnidade, quantidade.getOrdemProducao());
			}
		}
	}
	
	public abstract UnidadeIndiceOEE gerarDetalheIndiceOEE(T filter, ApontamentoQuantidade quantidade);

	public abstract List<UnidadeIndiceOEE> gerarUnidadesIndiceOEE(T filter, LocalDateTime dtHrInicio, LocalDateTime dtHrFim);
	
	public abstract IndiceOEEPorPeriodoDAO<T> getIndiceOEEPorPeriodoDAO();

}
