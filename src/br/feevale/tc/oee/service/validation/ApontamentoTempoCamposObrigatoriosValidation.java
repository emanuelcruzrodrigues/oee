package br.feevale.tc.oee.service.validation;

import br.feevale.tc.oee.domain.ApontamentoTempo;
import br.feevale.tc.oee.framework.validation.OEEValidation;
import br.feevale.tc.oee.framework.validation.OEEValidationResult;

public class ApontamentoTempoCamposObrigatoriosValidation<T extends ApontamentoTempo> implements OEEValidation {

	protected T apontamento;

	public ApontamentoTempoCamposObrigatoriosValidation(T apontamento) {
		this.apontamento = apontamento;
	}

	@Override
	public void validate(OEEValidationResult result) {
		if (apontamento.getEquipamento() == null){
			result.addFieldNotNullError("EQUIPAMENTO", "equipamento");
		}
	}

}
