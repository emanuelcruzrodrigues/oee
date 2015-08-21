package br.feevale.tc.oee.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.feevale.tc.oee.dao.ProgramacaoProducaoEquipamentoDAO;
import br.feevale.tc.oee.domain.ProgramacaoProducaoEquipamento;
import br.feevale.tc.oee.framework.dao.CRUDDAOTemplate;
import br.feevale.tc.oee.framework.service.CRUDServiceTemplateImpl;
import br.feevale.tc.oee.framework.utils.DateUtils;
import br.feevale.tc.oee.service.validation.ProgramacaoProducaoEquipamentoSaveValidationStack;

@Service
public class ProgramacaoProducaoEquipamentoService extends CRUDServiceTemplateImpl<ProgramacaoProducaoEquipamento>{
	
	@Resource
	protected ProgramacaoProducaoEquipamentoDAO programacaoProducaoEquipamentoDAO;

	@Override
	public CRUDDAOTemplate<ProgramacaoProducaoEquipamento> getCRUDDAO() {
		return programacaoProducaoEquipamentoDAO;
	}
	
	@Transactional
	@Override
	public ProgramacaoProducaoEquipamento save(ProgramacaoProducaoEquipamento programacao) {
		
		new ProgramacaoProducaoEquipamentoSaveValidationStack(programacao, handler).validate();
		
		Integer tempoMinutos = DateUtils.getDiferencaEmMinutos(programacao.getDtHrInicio(), programacao.getDtHrFim());
		programacao.setTempoMinutos(tempoMinutos);
		
		
		return programacaoProducaoEquipamentoDAO.save(programacao);
	}
	

}
