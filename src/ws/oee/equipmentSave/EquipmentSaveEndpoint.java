package ws.oee.equipmentSave;

import javax.annotation.Resource;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.feevale.tc.oee.cadastros.domain.Equipamento;
import br.feevale.tc.oee.cadastros.service.EquipamentoService;

@Endpoint
public class EquipmentSaveEndpoint {
	
	@Resource
	protected EquipamentoService equipamentoService;

	@PayloadRoot(localPart="saveRequest", namespace="ws.oee.equipmentSave")
    public @ResponsePayload SaveResponse getSessoesAtivas(@RequestPayload SaveRequest request) {
		Equipment equipment = request.getEquipment();
		
		int id = equipment.getId().intValue();
		Equipamento storedEquipment = equipamentoService.get(id);
		if (storedEquipment == null){
			storedEquipment = new Equipamento();
			storedEquipment.setId(id);
//			storedEquipment.setDtCreation(new Date());
		}
		
		storedEquipment.setNome(equipment.getName());
		equipamentoService.save(storedEquipment);
		
		SaveResponse response = new SaveResponse();
		response.setEquipment(equipment);
		return response;
    }

	
}
