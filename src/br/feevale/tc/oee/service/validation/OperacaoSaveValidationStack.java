package br.feevale.tc.oee.service.validation;

import java.util.ArrayList;
import java.util.List;

import br.feevale.tc.oee.domain.Operacao;
import br.feevale.tc.oee.framework.validation.OEEValidation;
import br.feevale.tc.oee.framework.validation.OEEValidationHandler;
import br.feevale.tc.oee.framework.validation.OEEValidationStack;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
public class OperacaoSaveValidationStack extends OEEValidationStack {

	private Operacao operacao;
	private OEEValidationHandler handler;

	public OperacaoSaveValidationStack(Operacao operacao, OEEValidationHandler handler) {
		this.handler = handler;
		this.operacao = operacao;
	}

	@Override
	public OEEValidationHandler getHandler() {
		return handler;
	}

	@Override
	public List<OEEValidation> getValidations() {
		List<OEEValidation> validations = new ArrayList<OEEValidation>();
		validations.add(new OperacaoCamposObrigatoriosValidation(operacao));
		validations.add(new OperacaoUniqueKeyValidation(operacao));
		return validations;
	}

}
