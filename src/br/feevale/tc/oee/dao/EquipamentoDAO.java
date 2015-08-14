package br.feevale.tc.oee.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.enums.AtivoInativo;
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

	@Override
	protected List<Order> getDefaultOrders() {
		return Arrays.asList(Order.asc("codigo"));
	}

	public List<Equipamento> queryEquipamentosAtivos() {
		StringBuilder hql = new StringBuilder();
		hql.append("select equi from Equipamento equi ");
		hql.append(" where equi.dmSituacao = ? ");
		hql.append(" order by equi.nome ");
		return dao.query(hql.toString(), AtivoInativo.ATIVO);
	}

}
