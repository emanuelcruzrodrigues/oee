package br.feevale.tc.oee.stats.horario;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import br.feevale.tc.oee.domain.ApontamentoTempoParada;
import br.feevale.tc.oee.domain.ProgramacaoProducaoEquipamento;
import br.feevale.tc.oee.framework.dao.DAO;

@Repository
public class IndiceOEEPorHoraDAO {
	
	@Resource
	private DAO dao;

	public List<ProgramacaoProducaoEquipamento> queryProgramacoes(IndiceOEEPorHoraFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ApontamentoTempoParada> queryParadas(IndiceOEEPorHoraFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
