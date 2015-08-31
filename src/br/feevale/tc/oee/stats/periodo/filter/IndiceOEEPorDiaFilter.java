package br.feevale.tc.oee.stats.periodo.filter;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.enums.AnaliticoSintetico;

@SuppressWarnings("serial")
public class IndiceOEEPorDiaFilter implements IndiceOEEPorPeriodoFilter{
	
	private LocalDate dtInicial;
	private LocalDate dtFinal;
	private Equipamento equipamento;
	private AnaliticoSintetico dmLayout;
	
	public LocalDate getDtInicial() {
		return dtInicial;
	}
	public void setDtInicial(LocalDate dtInicial) {
		this.dtInicial = dtInicial;
	}
	
	public LocalDate getDtFinal() {
		return dtFinal;
	}
	public void setDtFinal(LocalDate dtFinal) {
		this.dtFinal = dtFinal;
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
		return getDtInicial().toLocalDateTime(new LocalTime(0,0,0));
	}
	
	@Override
	public LocalDateTime getDtHrFinal() {
		return getDtFinal().toLocalDateTime(new LocalTime(23,59,59,999));
	}
	
}
