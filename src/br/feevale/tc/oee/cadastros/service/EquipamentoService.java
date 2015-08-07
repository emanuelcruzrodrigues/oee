package br.feevale.tc.oee.cadastros.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.feevale.tc.oee.cadastros.dao.EquipamentoDAO;
import br.feevale.tc.oee.cadastros.domain.Equipamento;
import br.feevale.tc.oee.dao.templates.CRUDDAOTemplate;
import br.feevale.tc.oee.service.templates.CRUDServiceTemplateImpl;

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

}
