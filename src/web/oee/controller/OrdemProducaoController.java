package web.oee.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import web.oee.framework.controller.CRUDControllerImpl;
import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.enums.SituacaoOrdemProducao;
import br.feevale.tc.oee.framework.service.CRUDServiceTemplate;
import br.feevale.tc.oee.service.OrdemProducaoService;

@Controller
@RequestMapping("/ordemProducao")
public class OrdemProducaoController extends CRUDControllerImpl<OrdemProducao>{
	
	@Resource
	private OrdemProducaoService ordemProducaoService;

	@Override
	protected void updateExampleBean(OrdemProducao example) {
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
	protected CRUDServiceTemplate<OrdemProducao> getService() {
		return ordemProducaoService;
	}

}
