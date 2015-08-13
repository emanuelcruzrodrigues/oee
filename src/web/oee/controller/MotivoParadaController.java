package web.oee.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import web.oee.framework.controller.CRUDControllerImpl;
import br.feevale.tc.oee.domain.MotivoParada;
import br.feevale.tc.oee.enums.AtivoInativo;
import br.feevale.tc.oee.framework.service.CRUDServiceTemplate;
import br.feevale.tc.oee.service.MotivoParadaService;

@Controller
@RequestMapping("/motivoParada")
public class MotivoParadaController extends CRUDControllerImpl<MotivoParada>{
	
	@Resource
	private MotivoParadaService motivoParadaService;

	@Override
	protected MotivoParada getNewInstance(HttpServletRequest request) {
		MotivoParada motivoParada = new MotivoParada();
		motivoParada.setDmSituacao(AtivoInativo.ATIVO);
		return motivoParada;
	}

	@Override
	protected CRUDServiceTemplate<MotivoParada> getService() {
		return motivoParadaService;
	}

	@Override
	protected void updateExampleBean(MotivoParada example) {
		if (example.getDmSituacao() == null){
			example.setDmSituacao(AtivoInativo.ATIVO);
		}
	}

}
