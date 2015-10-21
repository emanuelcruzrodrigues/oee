package br.feevale.tc.oee.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.feevale.tc.oee.dao.ApontamentoQuantidadeDAO;
import br.feevale.tc.oee.domain.ApontamentoQuantidade;
import br.feevale.tc.oee.framework.dao.CRUDDAOTemplate;
import br.feevale.tc.oee.framework.service.CRUDServiceTemplateImpl;
import br.feevale.tc.oee.framework.utils.DateUtils;
import br.feevale.tc.oee.framework.validation.OEEValidationStack;
import br.feevale.tc.oee.service.validation.ApontamentoQuantidadeSaveValidationStack;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
@Service
public class ApontamentoQuantidadeService extends CRUDServiceTemplateImpl<ApontamentoQuantidade>{
	
	@Resource
	private ApontamentoQuantidadeDAO apontamentoQuantidadeDAO;

	@Override
	public CRUDDAOTemplate<ApontamentoQuantidade> getCRUDDAO() {
		return apontamentoQuantidadeDAO;
	}
	
	@Transactional
	@Override
	public ApontamentoQuantidade save(ApontamentoQuantidade apontamento) {
		apontamento.setDtHr(DateUtils.removeSeconds(apontamento.getDtHr()));
		return super.save(apontamento);
	}
	
	@Override
	protected OEEValidationStack getBeforeSaveValidationStack(ApontamentoQuantidade apontamento) {
		return new ApontamentoQuantidadeSaveValidationStack(apontamento, handler);
	}
	
	public ApontamentoQuantidade getByCodigo(Integer codigo) {
		return apontamentoQuantidadeDAO.getByCodigo(codigo);
	}

}
