package br.feevale.tc.oee.service.validation;

import org.apache.commons.lang3.StringUtils;

import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.framework.validation.OEEValidation;
import br.feevale.tc.oee.framework.validation.OEEValidationResult;

public class EquipamentoCamposObrigatoriosValidation implements OEEValidation {

	private Equipamento equipamento;

	public EquipamentoCamposObrigatoriosValidation(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	@Override
	public void validate(OEEValidationResult result) {
		if (equipamento.getCodigo() == null){
			result.addFieldNotNullError("CODIGO");
		}
		if (StringUtils.isBlank(equipamento.getNome())){
			result.addFieldNotNullError("NOME");
		}
		if (equipamento.getDmSituacao() == null){
			result.addFieldNotNullError("SITUACAO");
		}
	}

}
