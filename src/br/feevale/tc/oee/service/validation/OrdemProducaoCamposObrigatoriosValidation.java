package br.feevale.tc.oee.service.validation;

import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.framework.validation.OEEValidation;
import br.feevale.tc.oee.framework.validation.OEEValidationResult;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
public class OrdemProducaoCamposObrigatoriosValidation implements OEEValidation {

	private OrdemProducao ordemProducao;

	public OrdemProducaoCamposObrigatoriosValidation(OrdemProducao ordemProducao) {
		this.ordemProducao = ordemProducao;
	}

	@Override
	public void validate(OEEValidationResult result) {
		
		result.validateNumberField(ordemProducao.getCodigo(), "CODIGO", 99999999, "codigo");
		result.validateStringField(ordemProducao.getDescricao(), "DESCRICAO", 100, "descricao");
		
		if (ordemProducao.getEquipamento() == null){
			result.addFieldNotNullError("EQUIPAMENTO", "equipamento");
		}
		if (ordemProducao.getUnidadesPorMinuto() == null){
			result.addFieldNotNullError("UNIDADES_POR_MINUTO", "unidadesPorMinuto");
		}
		if (ordemProducao.getDmSituacao() == null){
			result.addFieldNotNullError("SITUACAO", "dmSituacao");
		}
	}

}
