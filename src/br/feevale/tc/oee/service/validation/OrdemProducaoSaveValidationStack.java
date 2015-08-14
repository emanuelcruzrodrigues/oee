package br.feevale.tc.oee.service.validation;

import java.util.ArrayList;
import java.util.List;

import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.framework.validation.OEEValidation;
import br.feevale.tc.oee.framework.validation.OEEValidationHandler;
import br.feevale.tc.oee.framework.validation.OEEValidationStack;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
public class OrdemProducaoSaveValidationStack extends OEEValidationStack {

	private OrdemProducao ordemProducao;
	private OEEValidationHandler handler;

	public OrdemProducaoSaveValidationStack(OrdemProducao ordemProducao, OEEValidationHandler handler) {
		this.ordemProducao = ordemProducao;
		this.handler = handler;
	}

	@Override
	public OEEValidationHandler getHandler() {
		return handler;
	}

	@Override
	public List<OEEValidation> getValidations() {
		List<OEEValidation> validations = new ArrayList<OEEValidation>();
		validations.add(new OrdemProducaoCamposObrigatoriosValidation(ordemProducao));
		validations.add(new OrdemProducaoUniqueKeyValidation(ordemProducao));
		return validations;
	}

}
