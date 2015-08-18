package web.oee.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import web.oee.framework.controller.CRUDControllerImpl;
import br.feevale.tc.oee.domain.ApontamentoTempoParada;
import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.domain.MotivoParada;
import br.feevale.tc.oee.domain.OrdemProducao;
import br.feevale.tc.oee.framework.service.CRUDServiceTemplate;
import br.feevale.tc.oee.service.ApontamentoTempoParadaService;
import br.feevale.tc.oee.service.EquipamentoService;
import br.feevale.tc.oee.service.MotivoParadaService;
import br.feevale.tc.oee.service.OrdemProducaoService;

@Controller
@RequestMapping("/apontamentoTempoParada")
public class ApontamentoTempoParadaController extends CRUDControllerImpl<ApontamentoTempoParada>{

	@Resource
	private ApontamentoTempoParadaService apontamentoTempoParadaService;
	
	@Resource
	private OrdemProducaoService ordemProducaoService;
	
	@Resource
	private EquipamentoService equipamentoService;
	
	@Resource
	private MotivoParadaService motivoParadaService;
	
	@Override
	protected void updateExampleBean(ApontamentoTempoParada example, HttpServletRequest request) {
		if (example.getDtInicial() == null){
			example.setDtInicial(new LocalDate().minusDays(1));
		}
		if (example.getDtFinal() == null){
			example.setDtFinal(example.getDtInicial().plusDays(1));
		}
	}

	@Override
	protected ApontamentoTempoParada getNewInstance(HttpServletRequest request) {
		ApontamentoTempoParada apontamento = new ApontamentoTempoParada();
		apontamento.setDtHrEntrada(new LocalDateTime());
		return apontamento;
	}

	@Override
	protected CRUDServiceTemplate<ApontamentoTempoParada> getService() {
		return apontamentoTempoParadaService;
	}
	
	@Override
	protected void updateRequestBeforeGoToForm(HttpServletRequest request) {
		super.updateRequestBeforeGoToForm(request);
		updateOrdensProducaoAtivas(request);
		updateMotivosParadasAtivos(request);
	}

	@Override
	protected void updateRequestBeforeGoToList(HttpServletRequest request) {
		super.updateRequestBeforeGoToList(request);
		updateOrdensProducaoAtivas(request);
		updateEquipamentosAtivos(request);
		updateMotivosParadasAtivos(request);
	}
	
	private void updateOrdensProducaoAtivas(HttpServletRequest request) {
		List<OrdemProducao> ordensProducao = ordemProducaoService.getOrdensProducaoAbertas();
		request.setAttribute("ordensProducao", ordensProducao);
	}
	
	private void updateEquipamentosAtivos(HttpServletRequest request) {
		List<Equipamento> equipamentos = equipamentoService.getEquipamentosAtivos();
		request.setAttribute("equipamentos", equipamentos);
	}
	
	private void updateMotivosParadasAtivos(HttpServletRequest request) {
		List<MotivoParada> motivosParadas = motivoParadaService.getMotivosParadasAtivos();
		request.setAttribute("motivosParadas", motivosParadas);
	}

}
