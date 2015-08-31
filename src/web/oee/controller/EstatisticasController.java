package web.oee.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.feevale.tc.oee.domain.Equipamento;
import br.feevale.tc.oee.enums.AnaliticoSintetico;
import br.feevale.tc.oee.service.EquipamentoService;
import br.feevale.tc.oee.stats.UnidadeIndiceOEE;
import br.feevale.tc.oee.stats.periodo.filter.IndiceOEEPorDiaFilter;
import br.feevale.tc.oee.stats.periodo.filter.IndiceOEEPorHoraFilter;
import br.feevale.tc.oee.stats.periodo.filter.IndiceOEETempoRealFilter;
import br.feevale.tc.oee.stats.periodo.service.IndiceOEEPorDiaService;
import br.feevale.tc.oee.stats.periodo.service.IndiceOEEPorHoraService;
import br.feevale.tc.oee.stats.periodo.service.IndiceOEETempoRealService;

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
	private IndiceOEETempoRealService indiceOEETempoRealService;
	
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
	
	@RequestMapping("/real")
	public String actionGetEstatisticasTempoReal(@Valid IndiceOEETempoRealFilter filter, BindingResult bindingResult, Model model, HttpServletRequest request){
		
		if (filter.getDuracaoPeriodoMinutos() == null || filter.getDuracaoPeriodoMinutos().equals(0)){
			filter.setDuracaoPeriodoMinutos(30);
		}
		
		LocalDateTime dtHrFinal = new LocalDateTime();
		filter.setDtHrFinal(dtHrFinal);
		
		LocalDateTime dtHrInicial = dtHrFinal.minusMinutes(filter.getDuracaoPeriodoMinutos());
		filter.setDtHrInicial(dtHrInicial);
		
		
		List<UnidadeIndiceOEE> indices = indiceOEETempoRealService.listIndicesOEE(filter);
		
		model.addAttribute("indice", CollectionUtils.isNotEmpty(indices) ? indices.get(0) : null);
		model.addAttribute("filter", filter);
		updateEquipamentosAtivos(request);
		
		
		return "/stats/indice_tempo_real";
	}
	
	@RequestMapping("/equipamento")
	public String acaoEdicao(@RequestParam(value = "id") int id, Model model, HttpServletRequest request){
		Equipamento equipamento = equipamentoService.get(id);
		
		IndiceOEETempoRealFilter filter = new IndiceOEETempoRealFilter();
		filter.setDuracaoPeriodoMinutos(30);
		filter.setEquipamento(equipamento);
		filter.setDtHrFinal(new LocalDateTime());
		filter.setDtHrInicial(filter.getDtHrFinal().minusMinutes(30));
		List<UnidadeIndiceOEE> indices = indiceOEETempoRealService.listIndicesOEE(filter);
		
		model.addAttribute("indice", CollectionUtils.isNotEmpty(indices) ? indices.get(0) : null);
		model.addAttribute("filter", filter);
		updateEquipamentosAtivos(request);
		
		return "/stats/indice_tempo_real";
	}
	
	private void updateEquipamentosAtivos(HttpServletRequest request) {
		List<Equipamento> equipamentos = equipamentoService.getEquipamentosAtivos();
		request.setAttribute("equipamentos", equipamentos);
	}
	
}
