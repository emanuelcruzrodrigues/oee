package br.feevale.tc.oee.service.validation;

import br.feevale.tc.oee.domain.MotivoParada;
import br.feevale.tc.oee.framework.validation.OEEValidation;
import br.feevale.tc.oee.framework.validation.OEEValidationResult;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
public class MotivoParadaCamposObrigatoriosValidation implements OEEValidation {

	private MotivoParada motivoParada;

	public MotivoParadaCamposObrigatoriosValidation(MotivoParada motivoParada) {
		this.motivoParada = motivoParada;
	}

	@Override
	public void validate(OEEValidationResult result) {
		
		result.validateNumberField(motivoParada.getCodigo(), "CODIGO", 99999999, "codigo");
		result.validateStringField(motivoParada.getDescricao(), "DESCRICAO", 100, "descricao");
		
		if (motivoParada.getDmTipoParada() == null){
			result.addFieldNotNullError("TIPO_PARADA", "dmTipoParada");
		}
		if (motivoParada.getDmSituacao() == null){
			result.addFieldNotNullError("SITUACAO", "dmSituacao");
		}
	}

}
