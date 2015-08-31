package br.feevale.tc.oee.stats;

import java.io.Serializable;

import com.bullcontrol.util.Calculadora;

import br.feevale.tc.oee.domain.ApontamentoTempoParada;
import br.feevale.tc.oee.domain.MotivoParada;

@SuppressWarnings("serial")
public class ParadaUnidadeIndiceOEE implements Serializable{
	
	private final Integer id;
	private final MotivoParada motivoParada;
	private Integer tempoTotal;
	
	public ParadaUnidadeIndiceOEE(ApontamentoTempoParada apontamento, Integer tempo) {
		MotivoParada motivoParada = apontamento.getMotivoParada();
		this.id = motivoParada.getId();
		this.motivoParada = motivoParada;
		this.tempoTotal = tempo;
	}
	
	public Integer getId() {
		return id;
	}
	
	public MotivoParada getMotivoParada() {
		return motivoParada;
	}
	
	public Integer getTempoTotal() {
		return tempoTotal;
	}
	public void setTempoTotal(Integer tempoTotal) {
		this.tempoTotal = tempoTotal;
	}
	public void addTempo(Integer tempo) {
		tempo = Calculadora.somar(getTempoTotal(), tempo, 0).intValue();
		setTempoTotal(tempo);
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof ParadaUnidadeIndiceOEE)) {
			return false;
		}
		ParadaUnidadeIndiceOEE castedObj = (ParadaUnidadeIndiceOEE) o;
		if (this.getId() == null || castedObj.getId() == null) {
			return false;
		} else {
			return this.getId().equals(castedObj.getId());
		}
	}


}
