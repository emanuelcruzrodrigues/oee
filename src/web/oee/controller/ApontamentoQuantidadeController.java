package web.oee.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import web.oee.framework.controller.CRUDControllerImpl;
import br.feevale.tc.oee.domain.ApontamentoQuantidade;
import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.framework.service.CRUDServiceTemplate;
import br.feevale.tc.oee.service.ApontamentoQuantidadeService;
import br.feevale.tc.oee.service.OrdemProducaoService;

@Controller
@RequestMapping("/apontamentoQuantidade")
public class ApontamentoQuantidadeController extends CRUDControllerImpl<ApontamentoQuantidade>{
	
	@Resource
	private ApontamentoQuantidadeService apontamentoQuantidadeService;
	
	@Resource
	private OrdemProducaoService ordemProducaoService;

	@Override
	protected void updateExampleBean(ApontamentoQuantidade example, HttpServletRequest request) {
		example.setDtInicial(new LocalDate().minusDays(1));
		example.setDtFinal(new LocalDate());
	}

	@Override
	protected ApontamentoQuantidade getNewInstance(HttpServletRequest request) {
		ApontamentoQuantidade apontamento = new ApontamentoQuantidade();
		apontamento.setDtHr(new LocalDateTime());
		return apontamento;
	}

	@Override
	protected CRUDServiceTemplate<ApontamentoQuantidade> getService() {
		return apontamentoQuantidadeService;
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
	}
	
	private void updateOrdensProducaoAtivas(HttpServletRequest request) {
		List<OrdemProducao> ordensProducao = ordemProducaoService.getOrdensProducaoAbertas();
		request.setAttribute("ordensProducao", ordensProducao);
	}
	
}
