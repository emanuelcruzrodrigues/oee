package br.feevale.tc.oee.enums;

import br.feevale.tc.oee.framework.domain.OEEEnum;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 22/08/2015
 */
public enum AnaliticoSintetico implements OEEEnum{
	ANALITICO("A", "ANALITICO"),
	SINTETICO("S", "SINTETICO"),
	;
	 
	private String value;
	private String meaningKey;
	
	private AnaliticoSintetico(String value, String meaningKey) {
		this.value = value;
		this.meaningKey = meaningKey;
	}

	public String getValue() {
		return value;
	}
	
	public String getMeaningKey() {
		return meaningKey;
	}
	
	public static AnaliticoSintetico getFromValue(String value) {
		for (AnaliticoSintetico enumValue : values()) {
			if (enumValue.getValue().equals(enumValue)) return enumValue;
		}
		return null;
	}

}
