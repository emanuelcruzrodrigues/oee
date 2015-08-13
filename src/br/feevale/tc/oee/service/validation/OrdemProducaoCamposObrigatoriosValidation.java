package br.feevale.tc.oee.service.validation;

import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.framework.validation.OEEValidation;
import br.feevale.tc.oee.framework.validation.OEEValidationResult;

public class OrdemProducaoCamposObrigatoriosValidation implements OEEValidation {

	private OrdemProducao ordemProducao;

	public OrdemProducaoCamposObrigatoriosValidation(OrdemProducao ordemProducao) {
		this.ordemProducao = ordemProducao;
	}

	@Override
	public void validate(OEEValidationResult result) {
		if (ordemProducao.getCodigo() == null){
			result.addFieldNotNullError("CODIGO");
		}
		if (ordemProducao.getUnidadesPorMinuto() == null){
			result.addFieldNotNullError("UNIDADES_POR_MINUTO");
		}
		if (ordemProducao.getDmSituacao() == null){
			result.addFieldNotNullError("SITUACAO");
		}
	}

}
