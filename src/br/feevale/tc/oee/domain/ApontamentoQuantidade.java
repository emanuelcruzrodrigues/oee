package br.feevale.tc.oee.domain;

import java.io.Serializable;

import br.feevale.tc.oee.enums.QualidadeProducao;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@SuppressWarnings("serial")
public class ApontamentoQuantidade implements Serializable{
	
	private Integer id;
	private OrdemProducao ordemProducao;
	private Equipamento equipamento;
	private Double quantidade;
	private QualidadeProducao dmQualidade;


}