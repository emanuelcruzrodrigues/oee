package br.feevale.tc.oee.service.validation;

import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.framework.validation.OEEValidation;
import br.feevale.tc.oee.framework.validation.OEEValidationResult;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
public class EquipamentoCamposObrigatoriosValidation implements OEEValidation {

	private Equipamento equipamento;

	public EquipamentoCamposObrigatoriosValidation(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	@Override
	public void validate(OEEValidationResult result) {
		result.validateNumberField(equipamento.getCodigo(), "CODIGO", 99999999, "codigo");
		result.validateStringField(equipamento.getNome(), "NOME", 100, "nome");
		if (equipamento.getDmSituacao() == null){
			result.addFieldNotNullError("SITUACAO", "dmSituacao");
		}
	}

}
