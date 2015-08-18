package br.feevale.tc.oee.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.feevale.tc.oee.dao.ApontamentoTempoParadaDAO;
import br.feevale.tc.oee.domain.ApontamentoTempoParada;
import br.feevale.tc.oee.framework.dao.CRUDDAOTemplate;
import br.feevale.tc.oee.framework.service.CRUDServiceTemplateImpl;
import br.feevale.tc.oee.service.validation.ApontamentoTempoParadaSaveValidationStack;

@Service
public class ApontamentoTempoParadaService extends CRUDServiceTemplateImpl<ApontamentoTempoParada>{

	@Resource
	protected ApontamentoTempoParadaDAO apontamentoTempoParadaDAO;
	
	@Resource
	protected ApontamentoTempoService apontamentoTempoService;
	
	@Override
	public CRUDDAOTemplate<ApontamentoTempoParada> getCRUDDAO() {
		return apontamentoTempoParadaDAO;
	}
	
	@Transactional
	@Override
	public ApontamentoTempoParada save(ApontamentoTempoParada apontamento) {
		apontamentoTempoService.updateDefaultValuesBeforeSave(apontamento);
		
		new ApontamentoTempoParadaSaveValidationStack(apontamento, handler).validate();
		
		apontamentoTempoParadaDAO.save(apontamento);
		
		apontamentoTempoService.encerrarOutrosApontamentosAbertos(apontamento);
		
		return apontamento;
	}

}
