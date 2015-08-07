package br.feevale.tc.oee.movimentos.domain;

import java.io.Serializable;

import org.joda.time.LocalDateTime;

import br.feevale.tc.oee.cadastros.domain.Equipamento;
import br.feevale.tc.oee.cadastros.domain.OrdemProducao;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@SuppressWarnings("serial")
public class ApontamentoTempo implements Serializable{

	private Integer id;
	private OrdemProducao ordemProducao;
	private Equipamento equipamento;
	private LocalDateTime dtHrInicial;
	private LocalDateTime dtHrFinal;
	private Integer tempoMinutos;
	
}
