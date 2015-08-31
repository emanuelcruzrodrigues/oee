package web.oee.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.feevale.tc.oee.stats.periodo.service.IndiceOEETempoRealService;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Resource
	private IndiceOEETempoRealService indiceOEETempoRealService;

	@RequestMapping("/")
	public String actionGoHome(HttpServletRequest request){
		request.setAttribute("indices", indiceOEETempoRealService.getIndicesOEEDosEquipamentosAtivos());
		return "/home";
	}
	
}
