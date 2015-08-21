package web.oee.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.service.EquipamentoService;
import br.feevale.tc.oee.stats.UnidadeIndiceOEE;
import br.feevale.tc.oee.stats.horario.IndiceOEEPorHoraFilter;
import br.feevale.tc.oee.stats.horario.IndiceOEEPorHoraService;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 21/08/2015
 */
@Controller
@RequestMapping("/stats")
public class EstatisticasController {
	
	@Resource
	private IndiceOEEPorHoraService indiceOEEPorHoraService;
	
	@Resource
	private EquipamentoService equipamentoService;
	
	@RequestMapping("/")
	public String actionShowEstatisticas(HttpServletRequest request){
		return "/stats/stats";
	}
	
	@RequestMapping("/hora")
	public String actionConfigurarEstatisticasPorHora(@Valid IndiceOEEPorHoraFilter filter, BindingResult bindingResult, Model model, HttpServletRequest request){
		
		if (filter.getDt() == null){
			filter.setDt(new LocalDate());
		}
		
		List<UnidadeIndiceOEE> indices = indiceOEEPorHoraService.listIndicesOEE(filter);
		
		model.addAttribute("indices", indices);
		model.addAttribute("filter", filter);
		updateEquipamentosAtivos(request);
		
		
		return "/stats/hora";
	}
	
	private void updateEquipamentosAtivos(HttpServletRequest request) {
		List<Equipamento> equipamentos = equipamentoService.getEquipamentosAtivos();
		request.setAttribute("equipamentos", equipamentos);
	}
	
}
