package br.feevale.tc.oee.domain;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 17/08/2015
 */
@SuppressWarnings("serial")
public class ApontamentoTempoProducao extends ApontamentoTempo{
	
	private OrdemProducao ordemProducao;
	
	public OrdemProducao getOrdemProducao() {
		return ordemProducao;
	}
	public void setOrdemProducao(OrdemProducao ordemProducao) {
		this.ordemProducao = ordemProducao;
	}

}
