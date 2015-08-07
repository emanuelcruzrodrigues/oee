package br.feevale.tc.oee.cadastros.dao;

import org.springframework.stereotype.Repository;

import br.feevale.tc.oee.cadastros.domain.Equipamento;
import br.feevale.tc.oee.dao.templates.CRUDDAOTemplateImpl;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@Repository
public class EquipamentoDAO extends CRUDDAOTemplateImpl<Equipamento>{

	@Override
	protected Class<Equipamento> getBeanClazz() {
		return Equipamento.class;
	}

	@Override
	protected void initialize(Equipamento t) {}

}
