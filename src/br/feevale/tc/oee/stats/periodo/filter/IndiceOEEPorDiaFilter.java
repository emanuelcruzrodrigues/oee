package br.feevale.tc.oee.stats.periodo.filter;

import org.joda.time.LocalDate;

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
	
}
