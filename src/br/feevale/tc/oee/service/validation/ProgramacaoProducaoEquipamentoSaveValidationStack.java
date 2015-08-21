package br.feevale.tc.oee.service.validation;

import java.util.ArrayList;
import java.util.List;

import br.feevale.tc.oee.domain.ProgramacaoProducaoEquipamento;
import br.feevale.tc.oee.framework.validation.OEEValidation;
import br.feevale.tc.oee.framework.validation.OEEValidationHandler;
import br.feevale.tc.oee.framework.validation.OEEValidationStack;

public class ProgramacaoProducaoEquipamentoSaveValidationStack extends OEEValidationStack {

	private ProgramacaoProducaoEquipamento programacao;
	private OEEValidationHandler handler;

	public ProgramacaoProducaoEquipamentoSaveValidationStack(ProgramacaoProducaoEquipamento programacao, OEEValidationHandler handler) {
		this.programacao = programacao;
		this.handler = handler;
	}

	@Override
	public OEEValidationHandler getHandler() {
		return handler;
	}

	@Override
	public List<OEEValidation> getValidations() {
		List<OEEValidation> validations = new ArrayList<>();
		validations.add(new ProgramacaoProducaoEquipamentoCamposObrigatoriosValidation(programacao));
		return validations;
	}

}
