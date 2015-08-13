package br.feevale.tc.oee.framework.utils;

import javax.servlet.http.HttpServletRequest;

import br.feevale.tc.oee.framework.domain.Usuario;
import br.feevale.tc.oee.framework.i18n.DefaultMessages;
import br.feevale.tc.oee.framework.validation.OEEValidationMessage;
import br.feevale.tc.oee.framework.validation.OEEValidationResult;

public class JSPUtils {
	
	public static String printCRUDJavaScripts(HttpServletRequest request){
		StringBuilder result = new StringBuilder();
		result.append("\n<script lang=\"javascript\">\n");
		
		result.append("\nfunction actionEditar(id){ \n");
		result.append(" 	var link = \"editar?id=\" + id;\n");
		result.append(" 	window.location.href = link;\n");
		result.append(" }\n");
				
		result.append("\nfunction actionExcluir(id){\n");
		result.append(" 	var question = \"").append(DefaultMessages.get(request, "CONFIRMA_EXCLUSAO")).append("\";\n");
		result.append(" 	if (confirm(question)) {\n");
		result.append(" 		var link = \"excluir?id=\" + id;\n");
		result.append(" 		window.location.href = link;\n");
		result.append(" 	}\n");
		result.append(" }\n");
		
		result.append("</script>");
		
		return result.toString();
	}
	
	public static String printEntrarSairButton(HttpServletRequest request){
		Usuario usuario = ((Usuario)request.getSession().getAttribute("usuarioLogado"));
      	String label;
       	String clazz;
       	String action;
      	if (usuario == null){
          	label = DefaultMessages.get(request, "ENTRAR");
	       	clazz = "btn btn-success";
	       	action = "../login/";          		
      	}else{
          	label = DefaultMessages.get(request, "SAIR");
	       	clazz = "btn btn-info";
	       	action = "../login/sair";          		
      	}
      	StringBuilder result = new StringBuilder();
      	result.append("<form class=\"navbar-form navbar-right\" action=\"").append(action).append("\">");
      	result.append("  <button type=\"submit\" class=\"").append(clazz).append("\">").append(label).append("</button>");
      	result.append("</form>");
      	return result.toString();
	}
	
	public static String printErrors(HttpServletRequest request){
		StringBuilder result = new StringBuilder();
		OEEValidationResult validationResult = (OEEValidationResult)request.getAttribute("errors");
		if (validationResult != null && validationResult.hasErrors()){
			for (OEEValidationMessage error : validationResult.getErrors()){
				result.append("<div class=\"alert alert-danger\" role=\"alert\">");
				result.append(DefaultMessages.get(request, error));
				result.append("</div>");
			}
		}
		return result.toString();
	}

}
