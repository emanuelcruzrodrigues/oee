package br.feevale.tc.oee.service.validation;

import br.feevale.tc.oee.domain.ApontamentoTempoProducao;
import br.feevale.tc.oee.framework.validation.OEEValidationResult;

public class ApontamentoTempoProducaoCamposObrigatoriosValidation extends ApontamentoTempoCamposObrigatoriosValidation<ApontamentoTempoProducao>{

	public ApontamentoTempoProducaoCamposObrigatoriosValidation(ApontamentoTempoProducao apontamento) {
		super(apontamento);
	}
	
	@Override
	public void validate(OEEValidationResult result) {
		super.validate(result);
	}

}
