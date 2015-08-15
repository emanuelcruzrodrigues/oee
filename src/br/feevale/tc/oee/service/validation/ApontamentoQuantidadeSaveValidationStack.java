package br.feevale.tc.oee.service.validation;

import java.util.ArrayList;
import java.util.List;

import br.feevale.tc.oee.domain.ApontamentoQuantidade;
import br.feevale.tc.oee.framework.validation.OEEValidation;
import br.feevale.tc.oee.framework.validation.OEEValidationHandler;
import br.feevale.tc.oee.framework.validation.OEEValidationStack;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
public class ApontamentoQuantidadeSaveValidationStack extends OEEValidationStack{

	private ApontamentoQuantidade apontamento;
	private OEEValidationHandler handler;

	public ApontamentoQuantidadeSaveValidationStack(ApontamentoQuantidade apontamento, OEEValidationHandler handler) {
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
		validations.add(new ApontamentoQuantidadeCamposObrigatoriosValidation(apontamento));
		return validations;
	}

}
