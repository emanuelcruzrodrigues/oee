package br.feevale.tc.oee.service.validation;

import java.io.Serializable;

import javax.annotation.Resource;

import br.feevale.tc.oee.dao.MotivoParadaDAO;
import br.feevale.tc.oee.domain.MotivoParada;
import br.feevale.tc.oee.framework.dao.UniqueKeyDAO;
import br.feevale.tc.oee.framework.validation.OEEValidationMessage;
import br.feevale.tc.oee.framework.validation.UniqueKeyValidationTemplate;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
public class MotivoParadaUniqueKeyValidation extends UniqueKeyValidationTemplate<MotivoParada> {
	
	@Resource
	private MotivoParadaDAO motivoParadaDAO;
	private MotivoParada motivoParada;

	public MotivoParadaUniqueKeyValidation(MotivoParada motivoParada) {
		this.motivoParada = motivoParada;
	}

	@Override
	protected UniqueKeyDAO<MotivoParada> getBullcontroUniqueKeyDAO() {
		return motivoParadaDAO;
	}

	@Override
	protected MotivoParada getBean() {
		return motivoParada;
	}

	@Override
	protected Serializable getBeanId() {
		return motivoParada.getId();
	}

	@Override
	protected OEEValidationMessage getErrorMessage() {
		return new OEEValidationMessage("JA_EXISTE_X_COM_Y_IGUAL_A_Z", new String[]{"MOTIVO_PARADA", "CODIGO", motivoParada.getCodigo().toString()}, "codigo");	
	}

}
