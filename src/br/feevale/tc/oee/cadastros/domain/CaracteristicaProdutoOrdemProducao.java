package br.feevale.tc.oee.cadastros.domain;

import java.io.Serializable;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@SuppressWarnings("serial")
public class CaracteristicaProdutoOrdemProducao implements Serializable{

	private Integer id;
	private OrdemProducao ordemProducao;
	private CaracteristicaProduto caracteristica;
	private Double quantidade;
}
