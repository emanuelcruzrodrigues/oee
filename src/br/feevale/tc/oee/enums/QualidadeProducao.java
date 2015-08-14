package br.feevale.tc.oee.enums;

import br.feevale.tc.oee.framework.domain.OEEEnum;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
public enum QualidadeProducao implements OEEEnum{
	 DENTRO_DOS_PADROES("P", "DENTRO_DOS_PADROES")
	,REFUGO("R", "REFUGO")
	;
	 
	private String value;
	private String meaningKey;
	
	private QualidadeProducao(String value, String meaningKey) {
		this.value = value;
		this.meaningKey = meaningKey;
	}

	public String getValue() {
		return value;
	}
	
	public String getMeaningKey() {
		return meaningKey;
	}
	
	public static QualidadeProducao getFromValue(String value) {
		for (QualidadeProducao qualidadeProducao : values()) {
			if (qualidadeProducao.getValue().equals(value)) return qualidadeProducao;
		}
		return null;
	}

}
