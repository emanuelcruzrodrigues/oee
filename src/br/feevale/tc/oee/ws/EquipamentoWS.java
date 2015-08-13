package br.feevale.tc.oee.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.framework.service.OEEServices;
import br.feevale.tc.oee.service.EquipamentoService;

@WebService
public class EquipamentoWS {

	@WebMethod
	public Equipamento save(Equipamento equipamento){
		return ((EquipamentoService)OEEServices.getService("equipamentoService")).save(equipamento);
	}
	
	@WebMethod
	public void delete(Integer id){
		Equipamento equipamento = new Equipamento();
		equipamento.setId(id);
		((EquipamentoService)OEEServices.getService("equipamentoService")).delete(equipamento);
	}
	
}
