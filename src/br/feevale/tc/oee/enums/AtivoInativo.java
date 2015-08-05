package br.feevale.tc.oee.enums;

import br.feevale.tc.oee.i18n.DefaultMessages;

public enum AtivoInativo implements OEEEnum{
	 ATIVO("A", "ATIVO")
	,INATIVO("I", "INATIVO")
	;
	 
	private String value;
	private String meaningKey;
	private String meaning;
	
	private AtivoInativo(String value, String meaningKey) {
		this.value = value;
		this.meaningKey = meaningKey;
	}

	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		if (meaning == null){
			meaning = DefaultMessages.get(meaningKey);
		}
		return meaning;
	}

}
