package br.feevale.tc.oee.stats.horario;

import java.io.Serializable;

import org.joda.time.LocalDate;

import br.feevale.tc.oee.domain.Equipamento;

@SuppressWarnings("serial")
public class IndiceOEEPorHoraFilter implements Serializable{
	
	private LocalDate dt;
	private Equipamento equipamento;
	
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
	
}
