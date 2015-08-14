package web.oee.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import web.oee.framework.controller.CRUDControllerImpl;
import br.feevale.tc.oee.domain.Operacao;
import br.feevale.tc.oee.enums.AtivoInativo;
import br.feevale.tc.oee.framework.service.CRUDServiceTemplate;
import br.feevale.tc.oee.service.OperacaoService;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
@Controller
@RequestMapping("/operacao")
public class OperacaoController extends CRUDControllerImpl<Operacao>{
	
	@Resource
	private OperacaoService operacaoService;

	@Override
	protected Operacao getNewInstance(HttpServletRequest request) {
		Operacao operacao = new Operacao();
		operacao.setDmSituacao(AtivoInativo.ATIVO);
		return operacao;
	}

	@Override
	protected CRUDServiceTemplate<Operacao> getService() {
		return operacaoService;
	}

	@Override
	protected void updateExampleBean(Operacao example, HttpServletRequest request) {
		if (example.getDmSituacao() == null){
			example.setDmSituacao(AtivoInativo.ATIVO);
		}
	}

}
