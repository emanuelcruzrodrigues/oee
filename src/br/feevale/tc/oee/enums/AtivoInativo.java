package br.feevale.tc.oee.enums;

import br.feevale.tc.oee.framework.domain.OEEEnum;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
public enum AtivoInativo implements OEEEnum{
	ATIVO("A", "ATIVO"),
	INATIVO("I", "INATIVO"),
	;
	 
	private String value;
	private String meaningKey;
	
	private AtivoInativo(String value, String meaningKey) {
		this.value = value;
		this.meaningKey = meaningKey;
	}

	public String getValue() {
		return value;
	}
	
	public String getMeaningKey() {
		return meaningKey;
	}
	
	public static AtivoInativo getFromValue(String value) {
		if ("A".equals(value)){
			return ATIVO;
		}
		if ("I".equals(value)){
			return INATIVO;
		}
		return null;
	}

}
