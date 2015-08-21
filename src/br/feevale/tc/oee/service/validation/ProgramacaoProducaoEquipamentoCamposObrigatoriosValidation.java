package br.feevale.tc.oee.service.validation;

import br.feevale.tc.oee.domain.ProgramacaoProducaoEquipamento;
import br.feevale.tc.oee.framework.validation.OEEValidation;
import br.feevale.tc.oee.framework.validation.OEEValidationResult;

public class ProgramacaoProducaoEquipamentoCamposObrigatoriosValidation implements OEEValidation {

	private ProgramacaoProducaoEquipamento programacao;

	public ProgramacaoProducaoEquipamentoCamposObrigatoriosValidation(ProgramacaoProducaoEquipamento programacao) {
		this.programacao = programacao;
	}

	@Override
	public void validate(OEEValidationResult result) {
		if (programacao.getDtHrInicio() == null){
			result.addFieldNotNullError("DATA_HORA_ENTRADA", "dtHrInicio");
		}
		if (programacao.getDtHrFim() == null){
			result.addFieldNotNullError("DATA_HORA_ENCERRAMENTO", "dtHrFim");
		}
		if (programacao.getEquipamento() == null){
			result.addFieldNotNullError("EQUIPAMENTO", "equipamento");
		}
	}

}
