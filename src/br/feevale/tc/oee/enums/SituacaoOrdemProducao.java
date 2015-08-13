package br.feevale.tc.oee.enums;

import br.feevale.tc.oee.framework.domain.OEEEnum;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 13/08/2015
 */
public enum SituacaoOrdemProducao implements OEEEnum{
	ABERTA("A", "ABERTA"),
	ENCERRADA("E", "ENCERRADA"),
	;
	 
	private String value;
	private String meaningKey;
	
	private SituacaoOrdemProducao(String value, String meaningKey) {
		this.value = value;
		this.meaningKey = meaningKey;
	}

	public String getValue() {
		return value;
	}
	
	public String getMeaningKey() {
		return meaningKey;
	}
	
	public static SituacaoOrdemProducao getFromValue(String value) {
		if ("A".equals(value)){
			return ABERTA;
		}
		if ("I".equals(value)){
			return ENCERRADA;
		}
		return null;
	}

}
