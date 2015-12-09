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
import br.feevale.tc.oee.stats.periodo.dao.IndiceOEEPorHoraDAO;
import br.feevale.tc.oee.stats.periodo.dao.IndiceOEEPorPeriodoDAO;
import br.feevale.tc.oee.stats.periodo.filter.IndiceOEEPorHoraFilter;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 21/08/2015
 * @see IndiceOEEPorHoraServiceTest
 */
@Service
public class IndiceOEEPorHoraService extends IndiceOEEPorPeriodoService<IndiceOEEPorHoraFilter>{
	
	@Resource
	protected IndiceOEEPorHoraDAO indiceOEEPorHoraDAO;

	@Override
	public UnidadeIndiceOEE gerarDetalheIndiceOEE(IndiceOEEPorHoraFilter filter, ApontamentoQuantidade quantidade) {
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
		unidade.addQuantidadeProduzida(quantidade.getQuantidade(), quantidade.getDmQualidade());
		
		return unidade;
	}

	@Override
	public List<UnidadeIndiceOEE> gerarUnidadesIndiceOEE(IndiceOEEPorHoraFilter filter, LocalDateTime dtHrInicio, LocalDateTime dtHrFim) {
		if (dtHrFim == null) {
			LocalTime horaAtual = new LocalTime();
			dtHrFim = new LocalDate().toLocalDateTime(new LocalTime(horaAtual.getHourOfDay(), horaAtual.getMinuteOfHour(), 0, 0));
		}
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

	@Override
	public IndiceOEEPorPeriodoDAO<IndiceOEEPorHoraFilter> getIndiceOEEPorPeriodoDAO() {
		return indiceOEEPorHoraDAO;
	}

}
