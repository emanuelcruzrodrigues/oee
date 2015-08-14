package web.oee.framework.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import br.feevale.tc.oee.framework.domain.Login;
import br.feevale.tc.oee.framework.domain.Usuario;
import br.feevale.tc.oee.framework.i18n.DefaultMessages;
import br.feevale.tc.oee.framework.service.LoginService;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Resource
	private LoginService loginService;
	
	@RequestMapping("/")
	public String actionDefault(Model model){
		return actionLogar(model);
	}
	
	@RequestMapping("/logar")
	public String actionLogar(Model model){
		Login login = new Login();
		model.addAttribute("login", login);
		return "/login";
		
	}
	
	@RequestMapping("/login")
	public String actionLogin(@Valid Login login, BindingResult result, HttpServletRequest request){
		
		Usuario usuario = loginService.executeLogin(login);
		
		if (usuario != null){
			request.getSession().setAttribute("usuarioLogado", usuario);
		}else{
			result.addError(new ObjectError("nomeUsuario", DefaultMessages.get(request, "USUARIO_OU_SENHA_INCORRETOS")));
		}
		
		if(result.hasErrors()) {
			return "/login";
		}
		
		return "redirect:/home/";	
	}
	
	@RequestMapping("/sair")
	public String actionSair(HttpServletRequest request){
		request.getSession().removeAttribute("usuarioLogado");
		return "redirect:/home/";	
	}

}
