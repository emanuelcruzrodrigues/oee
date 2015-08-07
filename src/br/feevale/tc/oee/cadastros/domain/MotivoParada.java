package br.feevale.tc.oee.cadastros.domain;

import java.io.Serializable;

import br.feevale.tc.oee.enums.TipoParada;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@SuppressWarnings("serial")
public class MotivoParada implements Serializable{

	private Integer id;
	private String descricao;
	private TipoParada dmTipoParada;
}
