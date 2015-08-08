package br.feevale.tc.oee.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.feevale.tc.oee.service.OEEServices;

@SuppressWarnings("serial")
public class FrontController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		executeController(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		executeController(req, resp);
	}
	
	private void executeController(HttpServletRequest req, HttpServletResponse resp) {
		try {
			FrontControllerParameters parameters = getFrontControllerParameters(req);
			Object controller = OEEServices.getService(parameters.getController()+"Controller");
			Method method = controller.getClass().getMethod(parameters.getAction(), HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(controller, req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private FrontControllerParameters getFrontControllerParameters(HttpServletRequest req) {
		FrontControllerParameters result = new FrontControllerParameters();
		String requestURL = req.getRequestURL().toString().replace("http://localhost:8080/oee/web/", "");
		try(Scanner scanner = new Scanner(requestURL)){
			scanner.useDelimiter("/");
			result.setController(scanner.next());
			if (scanner.hasNext()){
				result.setAction(scanner.next());
			}else{
				result.setAction("listar");
			}
		}
		return result;
	}
	
}
