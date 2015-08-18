package br.feevale.tc.oee.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Service;

import br.feevale.tc.oee.dao.ApontamentoTempoDAO;
import br.feevale.tc.oee.domain.ApontamentoTempo;
import br.feevale.tc.oee.framework.dao.CRUDDAOTemplateImpl;
import br.feevale.tc.oee.framework.service.CRUDServiceTemplateImpl;
import br.feevale.tc.oee.framework.service.OEEServices;

@Service
@SuppressWarnings({"rawtypes", "unchecked"})
public class ApontamentoTempoFacade {
	
	@Resource
	protected ApontamentoTempoDAO apontamentoTempoDAO;
	
	private Map<Class, CRUDServiceTemplateImpl> servicesMap;
	
	public void encerrarOutrosApontamentosAbertos(ApontamentoTempo apontamento) {
		
		List<ApontamentoTempo> apontamentos = apontamentoTempoDAO.queryOutrosApontamentosAbertos(apontamento);
		
		for (ApontamentoTempo outroApontamento : apontamentos) {
			outroApontamento.setDtHrSaida(new LocalDateTime());
			save(outroApontamento);
		}
	}

	public void save(ApontamentoTempo apontamento){
		CRUDServiceTemplateImpl service = getService(apontamento);
		if (service == null) return;
		service.save(apontamento);
	}
	
	private CRUDServiceTemplateImpl getService(ApontamentoTempo apontamento){
		CRUDServiceTemplateImpl service = getServicesMap().get(apontamento.getClass());
		return service;
	}
	
	private Map<Class, CRUDServiceTemplateImpl> getServicesMap() {
		if (servicesMap == null) {
			servicesMap = new HashMap<>();
			Map<String, CRUDServiceTemplateImpl> services = OEEServices.getBeansOfType(CRUDServiceTemplateImpl.class);
			for (CRUDServiceTemplateImpl service : services.values()) {
				Class clazz = ((CRUDDAOTemplateImpl)service.getCRUDDAO()).getBeanClazz();
				servicesMap.put(clazz, service);
			}
		}
		return servicesMap;
	}
	

}
