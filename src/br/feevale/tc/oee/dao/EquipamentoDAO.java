package br.feevale.tc.oee.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.framework.dao.CRUDDAOTemplateImpl;
import br.feevale.tc.oee.framework.dao.UniqueKeyDAO;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
@Repository
public class EquipamentoDAO extends CRUDDAOTemplateImpl<Equipamento> implements UniqueKeyDAO<Equipamento>{

	@Override
	protected Class<Equipamento> getBeanClazz() {
		return Equipamento.class;
	}

	@Override
	protected void initialize(Equipamento t) {}

	@Override
	public Serializable queryUniqueKeyId(Equipamento example) {
		StringBuilder hql = new StringBuilder();
		hql.append("select equi.id from Equipamento equi where equi.codigo = ? ");
		return dao.uniqueResult(hql.toString(), example.getCodigo());
	}

}
