package web.oee.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
@Controller
@RequestMapping("/databases")
public class DatabasesController {
	
	@RequestMapping("/")
	public String actionShowDatabases(HttpServletRequest request){
		if (request.getSession().getAttribute("usuarioLogado") == null) return "redirect:/login/logar";
		return "/databases";
	}

}
