package br.feevale.tc.oee.service.validation;

import java.io.Serializable;

import javax.annotation.Resource;

import br.feevale.tc.oee.dao.EquipamentoDAO;
import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.framework.dao.UniqueKeyDAO;
import br.feevale.tc.oee.framework.validation.OEEValidationMessage;
import br.feevale.tc.oee.framework.validation.UniqueKeyValidationTemplate;

public class EquipamentoUniqueKeyValidation extends UniqueKeyValidationTemplate<Equipamento> {

	@Resource
	private EquipamentoDAO equipamentoDAO;
	
	private Equipamento equipamento;

	public EquipamentoUniqueKeyValidation(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	@Override
	protected UniqueKeyDAO<Equipamento> getBullcontroUniqueKeyDAO() {
		return equipamentoDAO;
	}

	@Override
	protected Equipamento getBean() {
		return equipamento;
	}

	@Override
	protected Serializable getBeanId() {
		return equipamento.getId();
	}

	@Override
	protected OEEValidationMessage getErrorMessage() {
		return new OEEValidationMessage("JA_EXISTE_X_COM_Y_IGUAL_A_Z", new String[]{"EQUIPAMENTO", "CODIGO", equipamento.getCodigo().toString()});
	}


}
