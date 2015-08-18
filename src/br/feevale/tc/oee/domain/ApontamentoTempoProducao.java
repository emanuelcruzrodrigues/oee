package br.feevale.tc.oee.domain;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 17/08/2015
 */
@SuppressWarnings("serial")
public class ApontamentoTempoProducao extends ApontamentoTempo{
	
	private Double desempenho;

	public Double getDesempenho() {
		return desempenho;
	}
	public void setDesempenho(Double desempenho) {
		this.desempenho = desempenho;
	}

}
