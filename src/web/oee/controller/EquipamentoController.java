package web.oee.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import web.oee.framework.controller.CRUDControllerImpl;
import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.enums.AtivoInativo;
import br.feevale.tc.oee.framework.service.CRUDServiceTemplate;
import br.feevale.tc.oee.service.EquipamentoService;

@Controller
@RequestMapping("/equipamento")
public class EquipamentoController extends CRUDControllerImpl<Equipamento>{
	
	@Resource
	private EquipamentoService equipamentoService;

	@Override
	protected Equipamento getNewInstance(HttpServletRequest request) {
		Equipamento equipamento = new Equipamento();
		equipamento.setDmSituacao(AtivoInativo.ATIVO);
		return equipamento;
	}

	@Override
	protected CRUDServiceTemplate<Equipamento> getService() {
		return equipamentoService;
	}

	@Override
	protected void updateExampleBean(Equipamento example) {
		if (example.getDmSituacao() == null){
			example.setDmSituacao(AtivoInativo.ATIVO);
		}
	}

}
