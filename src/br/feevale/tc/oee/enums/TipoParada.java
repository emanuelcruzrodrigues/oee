package br.feevale.tc.oee.enums;

import br.feevale.tc.oee.framework.domain.OEEEnum;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
public enum TipoParada implements OEEEnum{
	 DT_TECNICA("DTT", "DOWNTIME_TECNICA"),
	 DT_OPERACIONAL("DTO", "DOWNTIME_OPERACIONAL"),
	 DT_QUALIDADE("DTQ", "DOWNTIME_QUALIDADE"),
	 ST_OPERACIONAL("STO", "STOPTIME_OPERACIONAL"),
	 ST_INDUZIDO("STI", "STOPTIME_INDUZIDO"),
	;
	 
	private String value;
	private String meaningKey;
	
	private TipoParada(String value, String meaningKey) {
		this.value = value;
		this.meaningKey = meaningKey;
	}

	public String getValue() {
		return value;
	}
	
	public String getMeaningKey() {
		return meaningKey;
	}

	public static TipoParada getFromValue(String value) {
		for (TipoParada tipoParada : values()) {
			if (tipoParada.getValue().equals(value)) return tipoParada;
		}
		return null;
	}

}
