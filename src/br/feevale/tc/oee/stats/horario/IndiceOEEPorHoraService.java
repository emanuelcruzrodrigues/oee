package br.feevale.tc.oee.stats.horario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Service;

import br.feevale.tc.oee.domain.ApontamentoQuantidade;
import br.feevale.tc.oee.domain.ApontamentoTempoParada;
import br.feevale.tc.oee.domain.ApontamentoTempoProducao;
import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.domain.ProgramacaoProducaoEquipamento;
import br.feevale.tc.oee.framework.utils.DateUtils;
import br.feevale.tc.oee.stats.CalculadoraOEE;
import br.feevale.tc.oee.stats.UnidadeIndiceOEE;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 21/08/2015
 * @see IndiceOEEPorHoraServiceTest
 */
@Service
public class IndiceOEEPorHoraService {
	
	@Resource
	protected IndiceOEEPorHoraDAO indiceOEEPorHoraDAO;

	public List<UnidadeIndiceOEE> listIndicesOEE(IndiceOEEPorHoraFilter filter) {
		if (filter.getEquipamento() == null || filter.getEquipamento().getId() == null) return Collections.emptyList();
		
		Map<String, UnidadeIndiceOEE> unidadesPorHora = new HashMap<>();
		
		updateTempoCarga(filter, unidadesPorHora);
		updateParadas(filter, unidadesPorHora);
		updateUnidadesProduzidas(filter, unidadesPorHora);
		updateProducao(filter, unidadesPorHora);
		
		List<UnidadeIndiceOEE> result = new ArrayList<>(unidadesPorHora.values());
		Collections.sort(result);
		CalculadoraOEE calculadoraOEE = new CalculadoraOEE();
		for (UnidadeIndiceOEE unidade : result) {
			for (UnidadeIndiceOEE detalhe : unidade.getDetalhes()) {
				calculadoraOEE.calcularDesempenho(detalhe);
				calculadoraOEE.calcularQualidade(detalhe);
			}
			unidade.refreshTempoCicloTeoricoConformeDetalhes();
			calculadoraOEE.calcularOEE(unidade);
		}
		return result;
	}

	protected void updateTempoCarga(IndiceOEEPorHoraFilter filter, Map<String, UnidadeIndiceOEE> unidadesPorHora) {
		List<ProgramacaoProducaoEquipamento> programacoes = indiceOEEPorHoraDAO.queryProgramacoes(filter);
		for (ProgramacaoProducaoEquipamento programacao : programacoes) {
			List<UnidadeIndiceOEE> unidades = gerarUnidadesIndiceOEE(filter, programacao.getDtHrInicio(), programacao.getDtHrFim());
			for (UnidadeIndiceOEE unidade : unidades) {
				unidadesPorHora.put(unidade.getId(), unidade);
				unidade.setTempoCargaMinutos(unidade.getTempoUtilMinutos());
			}
		}
	}
	
	protected void updateParadas(IndiceOEEPorHoraFilter filter, Map<String, UnidadeIndiceOEE> unidadesPorHora) {
		List<ApontamentoTempoParada> paradas = indiceOEEPorHoraDAO.queryParadas(filter);
		for (ApontamentoTempoParada apontamento : paradas) {
			List<UnidadeIndiceOEE> unidades = gerarUnidadesIndiceOEE(filter, apontamento.getDtHrEntrada(), apontamento.getDtHrSaida());
			for (UnidadeIndiceOEE novaUnidade : unidades) {
				UnidadeIndiceOEE unidade = unidadesPorHora.get(novaUnidade.getId());
				if (unidade != null){
					unidade.addTempoParada(apontamento.getMotivoParada().getDmTipoParada(), novaUnidade.getTempoUtilMinutos());
				}else{
					unidadesPorHora.put(novaUnidade.getId(), novaUnidade);
					novaUnidade.addTempoParada(apontamento.getMotivoParada().getDmTipoParada(), novaUnidade.getTempoUtilMinutos());
				}
			}
		}
	}
	
	protected void updateUnidadesProduzidas(IndiceOEEPorHoraFilter filter, Map<String, UnidadeIndiceOEE> unidadesPorHora) {
		List<ApontamentoQuantidade> quantidades = indiceOEEPorHoraDAO.queryUnidadesProduzidas(filter);
		for (ApontamentoQuantidade quantidade : quantidades) {
			
			UnidadeIndiceOEE novaUnidade = gerarUnidadeIndiceOEE(filter, quantidade);
			if (novaUnidade == null) continue;
			
			UnidadeIndiceOEE unidade = unidadesPorHora.get(novaUnidade.getId());
			if (unidade != null){
				unidade.addQuantidadeProduzida(quantidade.getQuantidade(), quantidade.getDmQualidade());
				unidade.addDetalhe(novaUnidade);
			}else{
				unidadesPorHora.put(novaUnidade.getId(), novaUnidade);
				novaUnidade.addDetalhe(novaUnidade);
			}
		}
	}
	
	private void updateProducao(IndiceOEEPorHoraFilter filter,Map<String, UnidadeIndiceOEE> unidadesPorHora) {
		List<ApontamentoTempoProducao> apontamentos = indiceOEEPorHoraDAO.queryApontamentosProducao(filter);
		for (ApontamentoTempoProducao apontamento : apontamentos) {
			List<UnidadeIndiceOEE> unidades = gerarUnidadesIndiceOEE(filter, apontamento.getDtHrEntrada(), apontamento.getDtHrSaida());
			for (UnidadeIndiceOEE novaUnidade : unidades) {
				OrdemProducao ordemProducao = apontamento.getOrdemProducao();
				novaUnidade.setOrdemProducao(ordemProducao);
				novaUnidade.setRuntimeMinutos(novaUnidade.getTempoUtilMinutos());
				novaUnidade.setTempoCicloTeoricoUnidadesPorMinuto(ordemProducao.getUnidadesPorMinuto());
				UnidadeIndiceOEE unidade = unidadesPorHora.get(novaUnidade.getId());
				if (unidade != null){
					unidade.addRuntime(novaUnidade.getTempoUtilMinutos());
					unidade.addDetalhe(novaUnidade);
				}else{
					unidadesPorHora.put(novaUnidade.getId(), novaUnidade);
					novaUnidade.addDetalhe(novaUnidade);
				}
			}
		}
	}



	private UnidadeIndiceOEE gerarUnidadeIndiceOEE(IndiceOEEPorHoraFilter filter, ApontamentoQuantidade quantidade) {
		LocalDateTime dtHr = quantidade.getDtHr();
		if (!dtHr.toLocalDate().equals(filter.getDt())) return null;
		
		int year = dtHr.getYear();
		int month = dtHr.getMonthOfYear();
		int day = dtHr.getDayOfMonth();
		int hour = dtHr.getHourOfDay();
		dtHr = new LocalDateTime(year, month, day, hour, 0, 0, 0);
		
		UnidadeIndiceOEE unidade = new UnidadeIndiceOEE();
		unidade.setInicio(dtHr);
		unidade.setFim(dtHr.plusMinutes(59));
		updateId(unidade);
		unidade.setOrdemProducao(quantidade.getOrdemProducao());
		unidade.addQuantidadeProduzida(quantidade.getQuantidade(), quantidade.getDmQualidade());
		
		return unidade;
	}

	private List<UnidadeIndiceOEE> gerarUnidadesIndiceOEE(IndiceOEEPorHoraFilter filter, LocalDateTime dtHrInicio, LocalDateTime dtHrFim) {
		if (dtHrFim == null) dtHrFim = new LocalDateTime();
		List<UnidadeIndiceOEE> result = new ArrayList<UnidadeIndiceOEE>();
		
		int year = dtHrInicio.getYear();
		int month = dtHrInicio.getMonthOfYear();
		int day = dtHrInicio.getDayOfMonth();
		int hour = dtHrInicio.getHourOfDay();
		
		LocalDateTime dtHr = new LocalDateTime(year, month, day, hour, 0, 0, 0);
		
		while(dtHr.isBefore(dtHrFim)){
			if (dtHr.toLocalDate().equals(filter.getDt())){
				
				UnidadeIndiceOEE unidade = new UnidadeIndiceOEE();
				unidade.setInicio(dtHr);
				unidade.setFim(dtHr.plusMinutes(59));
				updateId(unidade);
				
				LocalDateTime inicio = dtHrInicio.isAfter(dtHr) ? dtHrInicio : unidade.getInicio();
				LocalDateTime fim = dtHr.plusHours(1);
				fim = dtHrFim.isAfter(fim) ? fim : dtHrFim;
				Integer tempoUtilMinutos = DateUtils.getDiferencaEmMinutos(inicio, fim);
				unidade.setTempoUtilMinutos(tempoUtilMinutos);
				
				result.add(unidade);
			}
			dtHr = dtHr.plusHours(1);
		}
		
		return result;
	}
	

	private void updateId(UnidadeIndiceOEE unidade) {
		String id = DateUtils.printFormatted(unidade.getInicio(), "HH");
		unidade.setId(id);
	}
	

	
	

}
