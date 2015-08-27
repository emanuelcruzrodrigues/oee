package br.feevale.tc.oee.stats.periodo.filter;

import java.io.Serializable;

import br.feevale.tc.oee.domain.Equipamento;

public interface IndiceOEEPorPeriodoFilter extends Serializable{
	
	public Equipamento getEquipamento();

}
