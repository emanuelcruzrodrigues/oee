package br.feevale.tc.oee.stats.periodo.filter;

import org.joda.time.LocalDateTime;

import br.feevale.tc.oee.domain.Equipamento;

@SuppressWarnings("serial")
public class IndiceOEETempoRealFilter implements IndiceOEEPorPeriodoFilter{
	
	private Equipamento equipamento;
	private Integer duracaoPeriodoMinutos;
	private LocalDateTime dtHrInicial;
	private LocalDateTime dtHrFinal;
	
	@Override
	public Equipamento getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	
	public Integer getDuracaoPeriodoMinutos() {
		return duracaoPeriodoMinutos;
	}
	public void setDuracaoPeriodoMinutos(Integer duracaoPeriodoMinutos) {
		this.duracaoPeriodoMinutos = duracaoPeriodoMinutos;
	}
	
	@Override
	public LocalDateTime getDtHrInicial() {
		return dtHrInicial;
	}
	public void setDtHrInicial(LocalDateTime dtHrInicial) {
		try {
			int year = dtHrInicial.getYear();
			int monthOfYear = dtHrInicial.getMonthOfYear();
			int dayOfMonth = dtHrInicial.getDayOfMonth();
			int hourOfDay = dtHrInicial.getHourOfDay();
			int minuteOfHour = dtHrInicial.getMinuteOfHour();
			dtHrInicial = new LocalDateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, 0, 0);
			this.dtHrInicial = dtHrInicial;
		} catch (Exception e) {
			this.dtHrInicial = null;
		}
	}
	
	@Override
	public LocalDateTime getDtHrFinal() {
		return dtHrFinal;
	}
	public void setDtHrFinal(LocalDateTime dtHrFinal) {
		try {
			int year = dtHrFinal.getYear();
			int monthOfYear = dtHrFinal.getMonthOfYear();
			int dayOfMonth = dtHrFinal.getDayOfMonth();
			int hourOfDay = dtHrFinal.getHourOfDay();
			int minuteOfHour = dtHrFinal.getMinuteOfHour();
			dtHrFinal = new LocalDateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, 0, 0);
			this.dtHrFinal = dtHrFinal;
		} catch (Exception e) {
			this.dtHrFinal = null;
		}
	}
	
}
