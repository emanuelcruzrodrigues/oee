package br.feevale.tc.oee.service.validation;

import org.apache.commons.lang3.StringUtils;

import br.feevale.tc.oee.domain.MotivoParada;
import br.feevale.tc.oee.framework.validation.OEEValidation;
import br.feevale.tc.oee.framework.validation.OEEValidationResult;

public class MotivoParadaCamposObrigatoriosValidation implements OEEValidation {

	private MotivoParada motivoParada;

	public MotivoParadaCamposObrigatoriosValidation(MotivoParada motivoParada) {
		this.motivoParada = motivoParada;
	}

	@Override
	public void validate(OEEValidationResult result) {
		if (motivoParada.getCodigo() == null){
			result.addFieldNotNullError("CODIGO");
		}
		if (StringUtils.isBlank(motivoParada.getDescricao())){
			result.addFieldNotNullError("DESCRICAO");
		}
		if (motivoParada.getDmTipoParada() == null){
			result.addFieldNotNullError("TIPO_PARADA");
		}
		if (motivoParada.getDmSituacao() == null){
			result.addFieldNotNullError("SITUACAO");
		}
	}

}
