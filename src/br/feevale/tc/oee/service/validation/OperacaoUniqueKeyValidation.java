package br.feevale.tc.oee.service.validation;

import java.io.Serializable;

import javax.annotation.Resource;

import br.feevale.tc.oee.dao.OperacaoDAO;
import br.feevale.tc.oee.domain.Operacao;
import br.feevale.tc.oee.framework.dao.UniqueKeyDAO;
import br.feevale.tc.oee.framework.validation.OEEValidationMessage;
import br.feevale.tc.oee.framework.validation.UniqueKeyValidationTemplate;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
public class OperacaoUniqueKeyValidation extends UniqueKeyValidationTemplate<Operacao> {

	@Resource
	private OperacaoDAO operacaoDAO;
	
	private Operacao operacao;

	public OperacaoUniqueKeyValidation(Operacao operacao) {
		this.operacao = operacao;
	}

	@Override
	protected UniqueKeyDAO<Operacao> getBullcontroUniqueKeyDAO() {
		return operacaoDAO;
	}

	@Override
	protected Operacao getBean() {
		return operacao;
	}

	@Override
	protected Serializable getBeanId() {
		return operacao.getId();
	}

	@Override
	protected OEEValidationMessage getErrorMessage() {
		return new OEEValidationMessage("JA_EXISTE_X_COM_Y_IGUAL_A_Z", new String[]{"OPERACAO", "CODIGO", operacao.getCodigo().toString()});
	}


}
