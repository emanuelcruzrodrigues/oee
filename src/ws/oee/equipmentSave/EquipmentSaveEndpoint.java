package ws.oee.equipmentSave;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.feevale.tc.oee.service.EquipmentService;

@Endpoint
public class EquipmentSaveEndpoint {
	
	@Resource
	protected EquipmentService equipmentService;

	@PayloadRoot(localPart="saveRequest", namespace="ws.oee.equipmentSave")
    public @ResponsePayload SaveResponse getSessoesAtivas(@RequestPayload SaveRequest request) {
		Equipment equipment = request.getEquipment();
		
		int id = equipment.getId().intValue();
		br.feevale.tc.oee.domain.Equipment storedEquipment = equipmentService.get(id);
		if (storedEquipment == null){
			storedEquipment = new br.feevale.tc.oee.domain.Equipment();
			storedEquipment.setId(id);
//			storedEquipment.setDtCreation(new Date());
		}
		
		storedEquipment.setName(equipment.getName());
		equipmentService.save(storedEquipment);
		
		SaveResponse response = new SaveResponse();
		response.setEquipment(equipment);
		return response;
    }

	
}
