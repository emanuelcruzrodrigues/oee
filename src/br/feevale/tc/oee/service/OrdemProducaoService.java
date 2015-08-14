package br.feevale.tc.oee.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.feevale.tc.oee.dao.OrdemProducaoDAO;
import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.framework.dao.CRUDDAOTemplate;
import br.feevale.tc.oee.framework.service.CRUDServiceTemplateImpl;
import br.feevale.tc.oee.framework.validation.OEEValidationStack;
import br.feevale.tc.oee.service.validation.OrdemProducaoSaveValidationStack;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
@Service
public class OrdemProducaoService extends CRUDServiceTemplateImpl<OrdemProducao>{
	
	@Resource
	protected OrdemProducaoDAO ordemProducaoDAO;

	@Override
	protected CRUDDAOTemplate<OrdemProducao> getCRUDDAO() {
		return ordemProducaoDAO;
	}
	
	@Override
	protected OEEValidationStack getBeforeSaveValidationStack(OrdemProducao ordemProducao) {
		return new OrdemProducaoSaveValidationStack(ordemProducao, handler);
	}

	public List<OrdemProducao> getOrdensProducaoAbertas() {
		return ordemProducaoDAO.queryOrdensProducaoAbertas();
	}

}
