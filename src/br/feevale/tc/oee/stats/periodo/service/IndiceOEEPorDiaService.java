package br.feevale.tc.oee.stats.periodo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Service;

import br.feevale.tc.oee.domain.ApontamentoQuantidade;
import br.feevale.tc.oee.framework.utils.DateUtils;
import br.feevale.tc.oee.stats.UnidadeIndiceOEE;
import br.feevale.tc.oee.stats.periodo.dao.IndiceOEEPorDiaDAO;
import br.feevale.tc.oee.stats.periodo.dao.IndiceOEEPorPeriodoDAO;
import br.feevale.tc.oee.stats.periodo.filter.IndiceOEEPorDiaFilter;

@Service
public class IndiceOEEPorDiaService extends IndiceOEEPorPeriodoService<IndiceOEEPorDiaFilter>{
	
	@Resource
	protected IndiceOEEPorDiaDAO indiceOEEPorDiaDAO;
	
	@Override
	public IndiceOEEPorPeriodoDAO<IndiceOEEPorDiaFilter> getIndiceOEEPorPeriodoDAO() {
		return indiceOEEPorDiaDAO;
	}

	@Override
	public UnidadeIndiceOEE gerarDetalheIndiceOEE(IndiceOEEPorDiaFilter filter, ApontamentoQuantidade quantidade) {
		LocalDate dt = quantidade.getDtHr().toLocalDate();
		
		UnidadeIndiceOEE unidade = new UnidadeIndiceOEE();
		unidade.setInicio(dt.toLocalDateTime(new LocalTime(0,0,0,0)));
		unidade.setFim(dt.toLocalDateTime(new LocalTime(23,59,0,0)));
		updateId(unidade);
		unidade.addQuantidadeProduzida(quantidade.getQuantidade(), quantidade.getDmQualidade());
		
		return unidade;
	}

	@Override
	public List<UnidadeIndiceOEE> gerarUnidadesIndiceOEE(IndiceOEEPorDiaFilter filter, LocalDateTime dtHrInicio, LocalDateTime dtHrFim) {
		if (dtHrFim == null) dtHrFim = new LocalDateTime();
		List<UnidadeIndiceOEE> result = new ArrayList<UnidadeIndiceOEE>();
		
		int year = dtHrInicio.getYear();
		int month = dtHrInicio.getMonthOfYear();
		int day = dtHrInicio.getDayOfMonth();
		
		LocalDateTime dtHr = new LocalDateTime(year, month, day, 0, 0, 0, 0);
		
		while(dtHr.isBefore(dtHrFim)){
			if (isValidDate(filter, dtHr.toLocalDate())){
				
				UnidadeIndiceOEE unidade = new UnidadeIndiceOEE();
				unidade.setInicio(dtHr.toLocalDate().toLocalDateTime(new LocalTime(0,0,0,0)));
				unidade.setFim(dtHr.toLocalDate().toLocalDateTime(new LocalTime(23,59,0,0)));
				updateId(unidade);
				
				LocalDateTime inicio = dtHrInicio.isAfter(dtHr) ? dtHrInicio : unidade.getInicio();
				LocalDateTime fim = dtHr.toLocalDate().plusDays(1).toLocalDateTime(new LocalTime(0,0,0,0));
				fim = dtHrFim.isAfter(fim) ? fim : dtHrFim;
				Integer tempoUtilMinutos = DateUtils.getDiferencaEmMinutos(inicio, fim);
				unidade.setTempoUtilMinutos(tempoUtilMinutos);
				
				result.add(unidade);
			}
			dtHr = dtHr.plusDays(1);
		}
		
		return result;
	}

	private boolean isValidDate(IndiceOEEPorDiaFilter filter, LocalDate dt) {
		if (dt.isBefore(filter.getDtInicial())) return false;
		if (dt.isAfter(filter.getDtFinal())) return false;
		return true;
	}
	
	private void updateId(UnidadeIndiceOEE unidade) {
		String id = DateUtils.printFormatted(unidade.getInicio(), "yyyy-dd-MM");
		unidade.setId(id);
	}

}
