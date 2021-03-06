package br.feevale.tc.oee.service.validation;

import java.util.ArrayList;
import java.util.List;

import br.feevale.tc.oee.domain.ApontamentoTempoProducao;
import br.feevale.tc.oee.framework.validation.OEEValidation;
import br.feevale.tc.oee.framework.validation.OEEValidationHandler;
import br.feevale.tc.oee.framework.validation.OEEValidationStack;

public class ApontamentoTempoProducaoSaveValidationStack extends OEEValidationStack {

	private ApontamentoTempoProducao apontamento;
	private OEEValidationHandler handler;

	public ApontamentoTempoProducaoSaveValidationStack(ApontamentoTempoProducao apontamento, OEEValidationHandler handler) {
		this.apontamento = apontamento;
		this.handler = handler;
	}

	@Override
	public OEEValidationHandler getHandler() {
		return handler;
	}

	@Override
	public List<OEEValidation> getValidations() {
		List<OEEValidation> validations = new ArrayList<>();
		validations.add(new ApontamentoTempoProducaoCamposObrigatoriosValidation(apontamento));
		return validations;
	}

}
