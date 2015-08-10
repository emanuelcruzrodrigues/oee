package br.feevale.tc.oee.enums;

import br.feevale.tc.oee.framework.domain.OEEEnum;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
public enum MaiorIgualMenor implements OEEEnum{
	 MAIOR("G", ">"),
	 MAIOR_OU_IGUAL("GE", ">="),
	 IGUAL("E", "="),
	 MENOR_OU_IGUAL("LE", "<="),
	 MENOR("L", "<"),
	;
	 
	private String value;
	private String meaningKey;
	
	private MaiorIgualMenor(String value, String meaningKey) {
		this.value = value;
		this.meaningKey = meaningKey;
	}

	public String getValue() {
		return value;
	}
	
	public String getMeaningKey() {
		return meaningKey;
	}
	
}
