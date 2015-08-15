package br.feevale.tc.oee.service.validation;

import java.io.Serializable;

import javax.annotation.Resource;

import br.feevale.tc.oee.dao.OrdemProducaoDAO;
import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.framework.dao.UniqueKeyDAO;
import br.feevale.tc.oee.framework.validation.OEEValidationMessage;
import br.feevale.tc.oee.framework.validation.UniqueKeyValidationTemplate;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
public class OrdemProducaoUniqueKeyValidation extends UniqueKeyValidationTemplate<OrdemProducao> {

	@Resource
	private OrdemProducaoDAO ordemProducaoDAO;
	
	private OrdemProducao ordemProducao;

	public OrdemProducaoUniqueKeyValidation(OrdemProducao ordemProducao) {
		this.ordemProducao = ordemProducao;
	}

	@Override
	protected UniqueKeyDAO<OrdemProducao> getBullcontroUniqueKeyDAO() {
		return ordemProducaoDAO;
	}

	@Override
	protected OrdemProducao getBean() {
		return ordemProducao;
	}

	@Override
	protected Serializable getBeanId() {
		return ordemProducao.getId();
	}

	@Override
	protected OEEValidationMessage getErrorMessage() {
		return new OEEValidationMessage("JA_EXISTE_X_COM_Y_IGUAL_A_Z", new String[]{"ORDEM_PRODUCAO", "CODIGO", ordemProducao.getCodigo().toString()}, "codigo");	

	}


}
