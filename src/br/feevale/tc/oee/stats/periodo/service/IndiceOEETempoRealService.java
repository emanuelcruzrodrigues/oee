package br.feevale.tc.oee.stats.periodo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Service;

import br.feevale.tc.oee.domain.ApontamentoQuantidade;
import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.framework.utils.DateUtils;
import br.feevale.tc.oee.service.ApontamentoTempoService;
import br.feevale.tc.oee.stats.UnidadeIndiceOEE;
import br.feevale.tc.oee.stats.periodo.dao.IndiceOEEPorPeriodoDAO;
import br.feevale.tc.oee.stats.periodo.dao.IndiceOEETempoRealDAO;
import br.feevale.tc.oee.stats.periodo.filter.IndiceOEETempoRealFilter;

@Service
public class IndiceOEETempoRealService extends IndiceOEEPorPeriodoService<IndiceOEETempoRealFilter>{

	@Resource
	protected IndiceOEETempoRealDAO indiceOEETempoRealDAO;
	
	@Resource
	protected ApontamentoTempoService apontamentoTempoService;
	
	public List<UnidadeIndiceOEE> getIndicesOEEDosEquipamentosAtivos() {
		List<UnidadeIndiceOEE> result = new ArrayList<>();
		
		IndiceOEETempoRealFilter filter = new IndiceOEETempoRealFilter();
		filter.setDtHrFinal(new LocalDateTime());
		filter.setDtHrInicial(filter.getDtHrFinal().minusMinutes(30));
		List<Equipamento> equipamentos = apontamentoTempoService.getEquipamentosEmExecucao();
		for (Equipamento equipamento : equipamentos) {
			filter.setEquipamento(equipamento);
			result.addAll(listIndicesOEE(filter));
		}
		
		return result;
	}
	
	
	
	@Override
	public UnidadeIndiceOEE gerarDetalheIndiceOEE(IndiceOEETempoRealFilter filter, ApontamentoQuantidade quantidade) {
		LocalDateTime dtHr = quantidade.getDtHr();
		
		UnidadeIndiceOEE unidade = new UnidadeIndiceOEE();
		unidade.setEquipamento(filter.getEquipamento());
		unidade.setInicio(dtHr);
		unidade.setFim(dtHr);
		updateId(unidade);
		unidade.addQuantidadeProduzida(quantidade.getQuantidade(), quantidade.getDmQualidade());
		
		return unidade;
	}
	
	

	@Override
	public List<UnidadeIndiceOEE> gerarUnidadesIndiceOEE(IndiceOEETempoRealFilter filter, LocalDateTime dtHrInicio, LocalDateTime dtHrFim) {
		if (dtHrFim == null) dtHrFim = new LocalDateTime();
		
		UnidadeIndiceOEE unidade = new UnidadeIndiceOEE();
		unidade.setEquipamento(filter.getEquipamento());
		unidade.setInicio(filter.getDtHrInicial());
		unidade.setFim(filter.getDtHrFinal());
		updateId(unidade);

		if (dtHrInicio.isBefore(filter.getDtHrInicial())){
			dtHrInicio = filter.getDtHrInicial();
		}
		
		if (dtHrFim.isAfter(filter.getDtHrFinal())){
			dtHrFim = filter.getDtHrFinal();
		}
		
		Integer tempoUtilMinutos = DateUtils.getDiferencaEmMinutos(dtHrInicio, dtHrFim);
		unidade.setTempoUtilMinutos(tempoUtilMinutos);
				
		List<UnidadeIndiceOEE> result = new ArrayList<UnidadeIndiceOEE>();
		result.add(unidade);
		return result;
	}

	@Override
	public IndiceOEEPorPeriodoDAO<IndiceOEETempoRealFilter> getIndiceOEEPorPeriodoDAO() {
		return indiceOEETempoRealDAO;
	}
	
	private void updateId(UnidadeIndiceOEE unidade) {
		unidade.setId("UNIDADE_UNICA");
	}


}
