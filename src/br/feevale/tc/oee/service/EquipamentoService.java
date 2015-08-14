package br.feevale.tc.oee.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.feevale.tc.oee.dao.EquipamentoDAO;
import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.framework.dao.CRUDDAOTemplate;
import br.feevale.tc.oee.framework.service.CRUDServiceTemplateImpl;
import br.feevale.tc.oee.framework.validation.OEEValidationStack;
import br.feevale.tc.oee.service.validation.EquipamentoSaveValidationStack;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@Service
public class EquipamentoService extends CRUDServiceTemplateImpl<Equipamento>{

	@Resource
	protected EquipamentoDAO equipamentoDAO;
	
	@Override
	protected CRUDDAOTemplate<Equipamento> getCRUDDAO() {
		return equipamentoDAO;
	}
	
	@Override
	protected OEEValidationStack getBeforeSaveValidationStack(Equipamento equipamento) {
		return new EquipamentoSaveValidationStack(equipamento, handler);
	}

	public List<Equipamento> getEquipamentosAtivos() {
		return equipamentoDAO.queryEquipamentosAtivos();
	}

}
