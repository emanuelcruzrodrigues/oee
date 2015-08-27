package br.feevale.tc.oee.service;

import java.util.List;

import javax.annotation.Resource;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.feevale.tc.oee.dao.ProgramacaoProducaoEquipamentoDAO;
import br.feevale.tc.oee.domain.Equipamento;
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

	@Transactional
	public void excluirProgramacao(Equipamento equipamento, LocalDate dt) {
		List<ProgramacaoProducaoEquipamento> programacoes = getProgramacoes(equipamento, dt);
		
		for (ProgramacaoProducaoEquipamento programacao : programacoes) {
			delete(programacao);
		}
	}

	private List<ProgramacaoProducaoEquipamento> getProgramacoes(Equipamento equipamento, LocalDate dt) {
		ProgramacaoProducaoEquipamento example = new ProgramacaoProducaoEquipamento();
		example.setEquipamento(equipamento);
		example.setDtInicial(dt);
		example.setDtFinal(dt);
		
		List<ProgramacaoProducaoEquipamento> programacoes = getByExample(example);
		return programacoes;
	}
	

}
