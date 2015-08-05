package br.feevale.tc.oee.dao;

import org.springframework.stereotype.Repository;

import br.feevale.tc.oee.dao.templates.CRUDDAOTemplateImpl;
import br.feevale.tc.oee.domain.Equipment;

@Repository
public class EquipmentDAO extends CRUDDAOTemplateImpl<Equipment>{

	@Override
	protected Class<Equipment> getBeanClazz() {
		return Equipment.class;
	}

	@Override
	protected void initialize(Equipment equipment) {}

}
