package br.feevale.tc.oee.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Repository;

import br.feevale.tc.oee.domain.ProgramacaoProducaoEquipamento;
import br.feevale.tc.oee.framework.dao.CRUDDAOTemplateImpl;

@Repository
public class ProgramacaoProducaoEquipamentoDAO extends CRUDDAOTemplateImpl<ProgramacaoProducaoEquipamento>{

	@Override
	protected List<Order> getDefaultOrders() {
		return Arrays.asList(Order.asc("dtHrInicio"), Order.asc("dtHrFim"));
	}

	@Override
	public Class<ProgramacaoProducaoEquipamento> getBeanClazz() {
		return ProgramacaoProducaoEquipamento.class;
	}

	@Override
	protected void initialize(ProgramacaoProducaoEquipamento programacao) {
		dao.initialize(programacao.getEquipamento());
	}
	
	@Override
	protected List<Criterion> getAdicionalFiltersAtQueryByExample(ProgramacaoProducaoEquipamento example) {
		List<Criterion> result = new ArrayList<>();
		if (example.getDtInicial() != null){
			result.add(Restrictions.ge("dtHrInicio", example.getDtInicial().toLocalDateTime(new LocalTime(0,0,0,0))));
		}
		if (example.getDtFinal() != null){
			result.add(Restrictions.le("dtHrInicio", example.getDtFinal().toLocalDateTime(new LocalTime(23,59,59,999))));
		}
		return result;
	}

	public ProgramacaoProducaoEquipamento getByCodigo(Integer codigo) {
		if (codigo == null) return null;
		
		String hql = "select ppeq from ProgramacaoProducaoEquipamento ppeq where ppeq.codigo = ? ";
		
		ProgramacaoProducaoEquipamento result = dao.uniqueResult(hql, codigo);
		if (result != null){
			initialize(result);
		}
		
		return result;
	}

}
