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
import br.feevale.tc.oee.enums.AnaliticoSintetico;
import br.feevale.tc.oee.service.EquipamentoService;
import br.feevale.tc.oee.stats.UnidadeIndiceOEE;
import br.feevale.tc.oee.stats.periodo.filter.IndiceOEEPorDiaFilter;
import br.feevale.tc.oee.stats.periodo.filter.IndiceOEEPorHoraFilter;
import br.feevale.tc.oee.stats.periodo.service.IndiceOEEPorDiaService;
import br.feevale.tc.oee.stats.periodo.service.IndiceOEEPorHoraService;

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
	private IndiceOEEPorDiaService indiceOEEPorDiaService;
	
	@Resource
	private EquipamentoService equipamentoService;
	
	@RequestMapping("/")
	public String actionShowEstatisticas(HttpServletRequest request){
		return "/stats/stats";
	}
	
	@RequestMapping("/hora")
	public String actionGetEstatisticasPorHora(@Valid IndiceOEEPorHoraFilter filter, BindingResult bindingResult, Model model, HttpServletRequest request){
		
		if (filter.getDt() == null){
			filter.setDt(new LocalDate());
		}
		
		if (filter.getDmLayout() == null){
			filter.setDmLayout(AnaliticoSintetico.SINTETICO);
		}
		
		List<UnidadeIndiceOEE> indices = indiceOEEPorHoraService.listIndicesOEE(filter);
		
		model.addAttribute("indices", indices);
		model.addAttribute("filter", filter);
		model.addAttribute("isAnalitico", AnaliticoSintetico.ANALITICO == filter.getDmLayout());
		updateEquipamentosAtivos(request);
		
		
		return "/stats/indice_por_hora";
	}
	
	@RequestMapping("/dia")
	public String actionGetEstatisticasPorHora(@Valid IndiceOEEPorDiaFilter filter, BindingResult bindingResult, Model model, HttpServletRequest request){
		
		if (filter.getDtInicial() == null){
			filter.setDtInicial(new LocalDate().minusDays(10));
		}
		
		if (filter.getDtFinal() == null){
			filter.setDtFinal(new LocalDate());
		}
		
		if (filter.getDmLayout() == null){
			filter.setDmLayout(AnaliticoSintetico.SINTETICO);
		}
		
		List<UnidadeIndiceOEE> indices = indiceOEEPorDiaService.listIndicesOEE(filter);
		
		model.addAttribute("indices", indices);
		model.addAttribute("filter", filter);
		model.addAttribute("isAnalitico", AnaliticoSintetico.ANALITICO == filter.getDmLayout());
		updateEquipamentosAtivos(request);
		
		
		return "/stats/indice_por_dia";
	}
	
	private void updateEquipamentosAtivos(HttpServletRequest request) {
		List<Equipamento> equipamentos = equipamentoService.getEquipamentosAtivos();
		request.setAttribute("equipamentos", equipamentos);
	}
	
}
