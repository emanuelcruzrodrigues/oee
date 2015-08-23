package br.feevale.tc.oee.stats.horario;

import java.io.Serializable;

import org.joda.time.LocalDate;

import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.enums.AnaliticoSintetico;

@SuppressWarnings("serial")
public class IndiceOEEPorHoraFilter implements Serializable{
	
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
	
}
