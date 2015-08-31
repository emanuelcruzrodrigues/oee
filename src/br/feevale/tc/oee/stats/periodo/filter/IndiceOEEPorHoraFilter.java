package br.feevale.tc.oee.stats.periodo.filter;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.enums.AnaliticoSintetico;

@SuppressWarnings("serial")
public class IndiceOEEPorHoraFilter implements IndiceOEEPorPeriodoFilter{
	
	private LocalDate dt;
	private Equipamento equipamento;
	private AnaliticoSintetico dmLayout;
	
	public LocalDate getDt() {
		return dt;
	}
	public void setDt(LocalDate dt) {
		this.dt = dt;
	}
	
	public Equipamento getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	
	public AnaliticoSintetico getDmLayout() {
		return dmLayout;
	}
	public void setDmLayout(AnaliticoSintetico dmLayout) {
		this.dmLayout = dmLayout;
	}
	
	@Override
	public LocalDateTime getDtHrInicial() {
		return getDt().toLocalDateTime(new LocalTime(0,0,0));
	}
	
	@Override
	public LocalDateTime getDtHrFinal() {
		return getDt().toLocalDateTime(new LocalTime(23,59,59,999));
	}
	
}
