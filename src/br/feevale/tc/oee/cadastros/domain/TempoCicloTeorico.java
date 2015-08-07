package br.feevale.tc.oee.cadastros.domain;

import java.io.Serializable;

import br.feevale.tc.oee.enums.MaiorIgualMenor;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@SuppressWarnings("serial")
public class TempoCicloTeorico implements Serializable{
	
	private Integer id;
	private Equipamento equipamento;
	private Produto produto;
	private MaiorIgualMenor dmTipoComparacao;
	private Double quantidade;
	private Integer prioridade;
	private Double unidadesPorMinuto;
	

}
