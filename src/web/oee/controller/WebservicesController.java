package web.oee.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/webservices")
public class WebservicesController {
	
	@RequestMapping("/")
	public String actionShowDatabases(HttpServletRequest request){
		return "/webservices";
	}

}
