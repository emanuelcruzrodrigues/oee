package br.feevale.tc.oee.domain;


/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@SuppressWarnings("serial")
public class ApontamentoTempoParada extends ApontamentoTempo{
	
	private MotivoParada motivoParada;

	public MotivoParada getMotivoParada() {
		return motivoParada;
	}
	public void setMotivoParada(MotivoParada motivoParada) {
		this.motivoParada = motivoParada;
	}
	
}
