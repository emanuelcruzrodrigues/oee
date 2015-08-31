package br.feevale.tc.oee.stats.periodo.filter;

import java.io.Serializable;

import org.joda.time.LocalDateTime;

import br.feevale.tc.oee.domain.Equipamento;

public interface IndiceOEEPorPeriodoFilter extends Serializable{
	
	public Equipamento getEquipamento();
	public LocalDateTime getDtHrInicial();
	public LocalDateTime getDtHrFinal();

}
