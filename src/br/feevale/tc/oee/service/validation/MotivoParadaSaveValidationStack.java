package br.feevale.tc.oee.service.validation;

import java.util.ArrayList;
import java.util.List;

import br.feevale.tc.oee.domain.MotivoParada;
import br.feevale.tc.oee.framework.validation.OEEValidation;
import br.feevale.tc.oee.framework.validation.OEEValidationHandler;
import br.feevale.tc.oee.framework.validation.OEEValidationStack;

public class MotivoParadaSaveValidationStack extends OEEValidationStack {

	private MotivoParada motivoParada;
	private OEEValidationHandler handler;

	public MotivoParadaSaveValidationStack(MotivoParada motivoParada, OEEValidationHandler handler) {
		this.motivoParada = motivoParada;
		this.handler = handler;
	}

	@Override
	public OEEValidationHandler getHandler() {
		return handler;
	}

	@Override
	public List<OEEValidation> getValidations() {
		List<OEEValidation> validations = new ArrayList<>();
		validations.add(new MotivoParadaCamposObrigatoriosValidation(motivoParada));
		validations.add(new MotivoParadaUniqueKeyValidation(motivoParada));
		return validations ;
	}

}
