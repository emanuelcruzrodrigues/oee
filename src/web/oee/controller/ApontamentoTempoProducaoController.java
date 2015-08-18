package web.oee.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.feevale.tc.oee.domain.ApontamentoTempoProducao;
import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.framework.service.CRUDServiceTemplate;
import br.feevale.tc.oee.service.ApontamentoTempoProducaoService;
import br.feevale.tc.oee.service.EquipamentoService;
import br.feevale.tc.oee.service.OrdemProducaoService;
import web.oee.framework.controller.CRUDControllerImpl;

@Controller
@RequestMapping("/apontamentoTempoProducao")
public class ApontamentoTempoProducaoController extends CRUDControllerImpl<ApontamentoTempoProducao>{

	@Resource
	private ApontamentoTempoProducaoService apontamentoTempoProducaoService;
	
	@Resource
	private OrdemProducaoService ordemProducaoService;
	
	@Resource
	private EquipamentoService equipamentoService;
	
	@Override
	protected void updateExampleBean(ApontamentoTempoProducao example, HttpServletRequest request) {
		if (example.getDtInicial() == null){
			example.setDtInicial(new LocalDate().minusDays(1));
		}
		if (example.getDtFinal() == null){
			example.setDtFinal(example.getDtInicial().plusDays(1));
		}
	}

	@Override
	protected ApontamentoTempoProducao getNewInstance(HttpServletRequest request) {
		ApontamentoTempoProducao apontamento = new ApontamentoTempoProducao();
		apontamento.setDtHrEntrada(new LocalDateTime());
		return apontamento;
	}

	@Override
	protected CRUDServiceTemplate<ApontamentoTempoProducao> getService() {
		return apontamentoTempoProducaoService;
	}
	
	@Override
	protected void updateRequestBeforeGoToForm(HttpServletRequest request) {
		super.updateRequestBeforeGoToForm(request);
		updateOrdensProducaoAtivas(request);
	}

	@Override
	protected void updateRequestBeforeGoToList(HttpServletRequest request) {
		super.updateRequestBeforeGoToList(request);
		updateOrdensProducaoAtivas(request);
		updateEquipamentosAtivos(request);
	}
	
	private void updateOrdensProducaoAtivas(HttpServletRequest request) {
		List<OrdemProducao> ordensProducao = ordemProducaoService.getOrdensProducaoAbertas();
		request.setAttribute("ordensProducao", ordensProducao);
	}
	
	private void updateEquipamentosAtivos(HttpServletRequest request) {
		List<Equipamento> equipamentos = equipamentoService.getEquipamentosAtivos();
		request.setAttribute("equipamentos", equipamentos);
	}

}
