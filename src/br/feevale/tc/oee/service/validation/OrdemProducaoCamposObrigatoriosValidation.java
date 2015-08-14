package br.feevale.tc.oee.service.validation;

import org.apache.commons.lang3.StringUtils;

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
		if (ordemProducao.getCodigo() == null){
			result.addFieldNotNullError("CODIGO");
		}
		if (StringUtils.isBlank(ordemProducao.getDescricao())){
			result.addFieldNotNullError("DESCRICAO");
		}
		if (ordemProducao.getEquipamento() == null){
			result.addFieldNotNullError("EQUIPAMENTO");
		}
		if (ordemProducao.getOperacao() == null){
			result.addFieldNotNullError("OPERACAO");
		}
		if (ordemProducao.getUnidadesPorMinuto() == null){
			result.addFieldNotNullError("UNIDADES_POR_MINUTO");
		}
		if (ordemProducao.getDmSituacao() == null){
			result.addFieldNotNullError("SITUACAO");
		}
	}

}
