package br.feevale.tc.oee.cadastros.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@SuppressWarnings("serial")
public class OrdemProducao implements Serializable{

	private Integer id;
	private Produto produto;
	private List<CaracteristicaProdutoOrdemProducao> caracteristicas;
	private TempoCicloTeorico tempoCicloTeorico;
	private Double unidadesPorMinuto;
	
}
