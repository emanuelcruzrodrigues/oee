package br.feevale.tc.oee.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import br.feevale.tc.oee.domain.MotivoParada;
import br.feevale.tc.oee.enums.AtivoInativo;
import br.feevale.tc.oee.framework.dao.CRUDDAOTemplateImpl;
import br.feevale.tc.oee.framework.dao.UniqueKeyDAO;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
@Repository
public class MotivoParadaDAO extends CRUDDAOTemplateImpl<MotivoParada> implements UniqueKeyDAO<MotivoParada>{

	@Override
	protected Class<MotivoParada> getBeanClazz() {
		return MotivoParada.class;
	}

	@Override
	protected void initialize(MotivoParada t) {}

	@Override
	public Serializable queryUniqueKeyId(MotivoParada example) {
		StringBuilder hql = new StringBuilder();
		hql.append("select mopa.id from MotivoParada mopa where mopa.codigo = ? ");
		return dao.uniqueResult(hql.toString(), example.getCodigo());
	}
	
	@Override
	protected List<Order> getDefaultOrders() {
		return Arrays.asList(Order.asc("codigo"));
	}
	
	public List<MotivoParada> queryEquipamentosAtivos() {
		StringBuilder hql = new StringBuilder();
		hql.append("select mopa from MotivoParada mopa ");
		hql.append(" where mopa.dmSituacao = ? ");
		hql.append(" order by mopa.descricao ");
		return dao.query(hql.toString(), AtivoInativo.ATIVO);
	}

}
