package web.oee.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import web.oee.framework.controller.CRUDControllerImpl;
import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.domain.ProgramacaoProducaoEquipamento;
import br.feevale.tc.oee.framework.service.CRUDServiceTemplate;
import br.feevale.tc.oee.service.EquipamentoService;
import br.feevale.tc.oee.service.ProgramacaoProducaoEquipamentoService;

@Controller
@RequestMapping("/programacaoProducaoEquipamento")
public class ProgramacaoProducaoEquipamentoController extends CRUDControllerImpl<ProgramacaoProducaoEquipamento>{
	
	@Resource
	private ProgramacaoProducaoEquipamentoService programacaoProducaoEquipamentoService;
	
	@Resource
	private EquipamentoService equipamentoService;

	@Override
	protected void updateExampleBean(ProgramacaoProducaoEquipamento example, HttpServletRequest request) {
		if (example.getDtInicial() == null){
			example.setDtInicial(new LocalDate().minusDays(1));
		}
		if (example.getDtFinal() == null){
			example.setDtFinal(example.getDtInicial().plusDays(1));
		}
	}

	@Override
	protected ProgramacaoProducaoEquipamento getNewInstance(HttpServletRequest request) {
		return new ProgramacaoProducaoEquipamento();
	}

	@Override
	protected CRUDServiceTemplate<ProgramacaoProducaoEquipamento> getService() {
		return programacaoProducaoEquipamentoService;
	}
	
	@Override
	protected void updateRequestBeforeGoToForm(HttpServletRequest request) {
		super.updateRequestBeforeGoToForm(request);
		updateEquipamentosAtivos(request);
	}

	@Override
	protected void updateRequestBeforeGoToList(HttpServletRequest request) {
		super.updateRequestBeforeGoToList(request);
		updateEquipamentosAtivos(request);
	}
	
	private void updateEquipamentosAtivos(HttpServletRequest request) {
		List<Equipamento> equipamentos = equipamentoService.getEquipamentosAtivos();
		request.setAttribute("equipamentos", equipamentos);
	}
	
}
