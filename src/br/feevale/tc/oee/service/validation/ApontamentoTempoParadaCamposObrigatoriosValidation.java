package br.feevale.tc.oee.service.validation;

import br.feevale.tc.oee.domain.ApontamentoTempoParada;
import br.feevale.tc.oee.framework.validation.OEEValidationResult;

public class ApontamentoTempoParadaCamposObrigatoriosValidation extends ApontamentoTempoCamposObrigatoriosValidation<ApontamentoTempoParada>{

	public ApontamentoTempoParadaCamposObrigatoriosValidation(ApontamentoTempoParada apontamento) {
		super(apontamento);
	}
	
	@Override
	public void validate(OEEValidationResult result) {
		super.validate(result);
		
		if (apontamento.getMotivoParada() == null){
			result.addFieldNotNullError("MOTIVO_PARADA", "motivoParada");
		}
	}

}
