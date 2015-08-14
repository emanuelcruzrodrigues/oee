package web.oee.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 14/08/2015
 */
@Controller
@RequestMapping("/home")
public class HomeController {

	@RequestMapping("/")
	public String actionGoHome(){
		return "/home";
	}
	
}
