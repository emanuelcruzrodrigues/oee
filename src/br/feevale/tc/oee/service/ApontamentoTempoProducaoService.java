package br.feevale.tc.oee.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.feevale.tc.oee.dao.ApontamentoTempoProducaoDAO;
import br.feevale.tc.oee.domain.ApontamentoTempoProducao;
import br.feevale.tc.oee.framework.dao.CRUDDAOTemplate;
import br.feevale.tc.oee.framework.service.CRUDServiceTemplateImpl;
import br.feevale.tc.oee.service.validation.ApontamentoTempoProducaoSaveValidationStack;

@Service
public class ApontamentoTempoProducaoService extends CRUDServiceTemplateImpl<ApontamentoTempoProducao>{

	@Resource
	protected ApontamentoTempoProducaoDAO apontamentoTempoProducaoDAO;
	
	@Resource
	protected ApontamentoTempoService apontamentoTempoService;
	
	@Override
	public CRUDDAOTemplate<ApontamentoTempoProducao> getCRUDDAO() {
		return apontamentoTempoProducaoDAO;
	}
	
	@Transactional
	@Override
	public ApontamentoTempoProducao save(ApontamentoTempoProducao apontamento) {
		apontamentoTempoService.updateDefaultValuesBeforeSave(apontamento);
		
		new ApontamentoTempoProducaoSaveValidationStack(apontamento, handler).validate();
		
		apontamentoTempoProducaoDAO.save(apontamento);
		
		apontamentoTempoService.encerrarOutrosApontamentosAbertos(apontamento);
		
		return apontamento;
	}

}
