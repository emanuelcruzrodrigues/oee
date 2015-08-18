package br.feevale.tc.oee.service.validation;

import java.util.ArrayList;
import java.util.List;

import br.feevale.tc.oee.domain.ApontamentoTempoParada;
import br.feevale.tc.oee.framework.validation.OEEValidation;
import br.feevale.tc.oee.framework.validation.OEEValidationHandler;
import br.feevale.tc.oee.framework.validation.OEEValidationStack;

public class ApontamentoTempoParadaSaveValidationStack extends OEEValidationStack{

	private ApontamentoTempoParada apontamento;
	private OEEValidationHandler handler;
	
	public ApontamentoTempoParadaSaveValidationStack(ApontamentoTempoParada apontamento, OEEValidationHandler handler) {
		super();
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
		validations.add(new ApontamentoTempoParadaCamposObrigatoriosValidation(apontamento));
		return validations;
	}

}
