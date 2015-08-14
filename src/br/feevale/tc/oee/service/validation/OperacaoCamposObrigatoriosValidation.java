package br.feevale.tc.oee.service.validation;

import org.apache.commons.lang3.StringUtils;

import br.feevale.tc.oee.domain.Operacao;
import br.feevale.tc.oee.framework.validation.OEEValidation;
import br.feevale.tc.oee.framework.validation.OEEValidationResult;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
public class OperacaoCamposObrigatoriosValidation implements OEEValidation {

	private Operacao operacao;

	public OperacaoCamposObrigatoriosValidation(Operacao operacao) {
		this.operacao = operacao;
	}

	@Override
	public void validate(OEEValidationResult result) {
		if (operacao.getCodigo() == null){
			result.addFieldNotNullError("CODIGO");
		}
		if (StringUtils.isBlank(operacao.getNome())){
			result.addFieldNotNullError("NOME");
		}
		if (operacao.getDmSituacao() == null){
			result.addFieldNotNullError("SITUACAO");
		}
	}

}
