package br.feevale.tc.oee.service.validation;

import br.feevale.tc.oee.domain.ApontamentoQuantidade;
import br.feevale.tc.oee.framework.validation.OEEValidation;
import br.feevale.tc.oee.framework.validation.OEEValidationResult;

public class ApontamentoQuantidadeCamposObrigatoriosValidation implements OEEValidation {

	private ApontamentoQuantidade apontamento;

	public ApontamentoQuantidadeCamposObrigatoriosValidation(ApontamentoQuantidade apontamento) {
		this.apontamento = apontamento;
	}

	@Override
	public void validate(OEEValidationResult result) {
		if (apontamento.getOrdemProducao() == null){
			result.addFieldNotNullError("ORDEM_PRODUCAO", "ordemProducao");
		}
		if (apontamento.getDtHr() == null){
			result.addFieldNotNullError("DATA_HORA", "dtHr");
		}
		
		result.validateNumberField(apontamento.getQuantidade(), "QUANTIDADE", 9999999999.999999, "quantidade");
		
		if (apontamento.getDmQualidade() == null){
			result.addFieldNotNullError("QUALIDADE_PRODUCAO", "dmQualidade");
		}
	}

}
