package br.feevale.tc.oee.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.feevale.tc.oee.dao.EquipmentDAO;
import br.feevale.tc.oee.dao.templates.CRUDDAOTemplate;
import br.feevale.tc.oee.domain.Equipment;
import br.feevale.tc.oee.service.templates.CRUDServiceTemplateImpl;

@Service
public class EquipmentService extends CRUDServiceTemplateImpl<Equipment>{

	@Resource
	protected EquipmentDAO equipmentDAO;
	
	@Override
	protected CRUDDAOTemplate<Equipment> getCRUDDAO() {
		return equipmentDAO;
	}

}
