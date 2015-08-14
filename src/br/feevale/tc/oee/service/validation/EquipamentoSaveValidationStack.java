package br.feevale.tc.oee.service.validation;

import java.util.ArrayList;
import java.util.List;

import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.framework.validation.OEEValidation;
import br.feevale.tc.oee.framework.validation.OEEValidationHandler;
import br.feevale.tc.oee.framework.validation.OEEValidationStack;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
public class EquipamentoSaveValidationStack extends OEEValidationStack {

	private Equipamento equipamento;
	private OEEValidationHandler handler;

	public EquipamentoSaveValidationStack(Equipamento equipamento, OEEValidationHandler handler) {
		this.handler = handler;
		this.equipamento = equipamento;
	}

	@Override
	public OEEValidationHandler getHandler() {
		return handler;
	}

	@Override
	public List<OEEValidation> getValidations() {
		List<OEEValidation> validations = new ArrayList<OEEValidation>();
		validations.add(new EquipamentoCamposObrigatoriosValidation(equipamento));
		validations.add(new EquipamentoUniqueKeyValidation(equipamento));
		return validations;
	}

}
