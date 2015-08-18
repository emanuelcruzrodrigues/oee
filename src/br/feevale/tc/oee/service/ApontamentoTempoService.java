package br.feevale.tc.oee.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.feevale.tc.oee.dao.ApontamentoTempoDAO;
import br.feevale.tc.oee.domain.ApontamentoTempo;
import br.feevale.tc.oee.framework.utils.DateUtils;

@Service
public class ApontamentoTempoService {
	
	@Resource
	protected ApontamentoTempoDAO apontamentoTempoDAO;
	
	@Resource
	protected ApontamentoTempoFacade apontamentoTempoFacade;
	
	public void updateDefaultValuesBeforeSave(ApontamentoTempo apontamento) {
		if (apontamento.getDtHrEntrada() == null){
			apontamento.setDtHrEntrada(DateUtils.newLocalDateTimeWithoutSeconds());
		}
		
		apontamento.setDtHrEntrada(DateUtils.removeSeconds(apontamento.getDtHrEntrada()));
		apontamento.setDtHrSaida(DateUtils.removeSeconds(apontamento.getDtHrSaida()));
		
		Integer tempoMinutos = DateUtils.getDiferencaEmMinutos(apontamento.getDtHrEntrada(), apontamento.getDtHrSaida());
		apontamento.setTempoMinutos(tempoMinutos);
	}


	public void encerrarOutrosApontamentosAbertos(ApontamentoTempo apontamento) {
		if (apontamento.getDtHrSaida() == null){
			apontamentoTempoFacade.encerrarOutrosApontamentosAbertos(apontamento);
		}
	}

}
