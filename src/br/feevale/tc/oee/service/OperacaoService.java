package br.feevale.tc.oee.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.feevale.tc.oee.dao.OperacaoDAO;
import br.feevale.tc.oee.domain.Operacao;
import br.feevale.tc.oee.framework.dao.CRUDDAOTemplate;
import br.feevale.tc.oee.framework.service.CRUDServiceTemplateImpl;
import br.feevale.tc.oee.framework.validation.OEEValidationStack;
import br.feevale.tc.oee.service.validation.OperacaoSaveValidationStack;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
@Service
public class OperacaoService extends CRUDServiceTemplateImpl<Operacao>{

	@Resource
	protected OperacaoDAO operacaoDAO;
	
	@Override
	protected CRUDDAOTemplate<Operacao> getCRUDDAO() {
		return operacaoDAO;
	}
	
	@Override
	protected OEEValidationStack getBeforeSaveValidationStack(Operacao operacao) {
		return new OperacaoSaveValidationStack(operacao, handler);
	}

	public List<Operacao> getOperacoesAtivas() {
		return operacaoDAO.queryOperacoesAtivas();
	}

}
