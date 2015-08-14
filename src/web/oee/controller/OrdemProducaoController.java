package web.oee.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import web.oee.framework.controller.CRUDControllerImpl;
import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.domain.Operacao;
import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.enums.SituacaoOrdemProducao;
import br.feevale.tc.oee.framework.service.CRUDServiceTemplate;
import br.feevale.tc.oee.service.EquipamentoService;
import br.feevale.tc.oee.service.OperacaoService;
import br.feevale.tc.oee.service.OrdemProducaoService;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
@Controller
@RequestMapping("/ordemProducao")
public class OrdemProducaoController extends CRUDControllerImpl<OrdemProducao>{
	
	@Resource
	private OrdemProducaoService ordemProducaoService;
	
	@Resource
	private EquipamentoService equipamentoService;
	
	@Resource
	private OperacaoService operacaoService;

	@Override
	protected void updateExampleBean(OrdemProducao example, HttpServletRequest request) {
		if (example.getDmSituacao() == null){
			example.setDmSituacao(SituacaoOrdemProducao.ABERTA);
		}
	}

	@Override
	protected OrdemProducao getNewInstance(HttpServletRequest request) {
		OrdemProducao ordemProducao = new OrdemProducao();
		ordemProducao.setDmSituacao(SituacaoOrdemProducao.ABERTA);
		
		return ordemProducao;
	}
	
	@Override
	protected void updateRequestBeforeGoToForm(HttpServletRequest request) {
		updateEquipamentosAtivos(request);
		updateOperacoesAtivas(request);
	}
	
	@Override
	protected void updateRequestBeforeGoToList(HttpServletRequest request) {
		updateEquipamentosAtivos(request);
		updateOperacoesAtivas(request);
	}

	private void updateOperacoesAtivas(HttpServletRequest request) {
		List<Operacao> operacoes = operacaoService.getOperacoesAtivas();
		request.setAttribute("operacoes", operacoes);
	}

	private void updateEquipamentosAtivos(HttpServletRequest request) {
		List<Equipamento> equipamentos = equipamentoService.getEquipamentosAtivos();
		request.setAttribute("equipamentos", equipamentos);
	}

	@Override
	protected CRUDServiceTemplate<OrdemProducao> getService() {
		return ordemProducaoService;
	}

}
