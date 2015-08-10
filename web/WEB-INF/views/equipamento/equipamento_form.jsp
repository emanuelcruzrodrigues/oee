<%@page import="br.feevale.tc.oee.framework.validation.OEEValidationMessage"%>
<%@page import="br.feevale.tc.oee.framework.validation.OEEValidationResult"%>
<%@page import="br.feevale.tc.oee.framework.i18n.DefaultMessages"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<link rel="icon" href="../resources/img/favicon.png">

    <title><spring:message code="TITULO_DA_APLICACAO" text="TITULO_DA_APLICACAO" /></title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../resources/css/form.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
	<script language="javascript">
		function afterLoad(){
			var idComponent = document.getElementById("id");
			if (idComponent.value.length > 0){
				idComponent.readOnly = true;
			}
		}
	</script>
</head>
<body onload="afterLoad();">

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="../home/"><spring:message code="INDICE_OEE" text="INDICE_OEE" /></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="../home/"><spring:message code="INICIO" text="INICIO" /></a></li>
            <li><a href="../stats/"><spring:message code="ESTATISTICAS" text="ESTATISTICAS" /></a></li>
            <li class="active"><a href="../databases/"><spring:message code="BANCO_DE_DADOS" text="BANCO_DE_DADOS" /></a></li>
            <li><a href="../webservices/"><spring:message code="WEBSERVICES" text="WEBSERVICES" /></a></li>
          </ul>
          <% 
	       	String label = DefaultMessages.get(request, "SAIR");
		    String clazz = "btn btn-info";
		    String action = "../login/sair";          		
          	out.print("<form class=\"navbar-form navbar-right\" action=\"" + action + "\">");
          	out.print("  <button type=\"submit\" class=\"" + clazz + "\">" + label + "</button>");
          	out.print("</form>");
          %>
          
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    		
	<div class="container">	

		<form:form action="salvar" modelAttribute="bean" method="POST" class="form-horizontal">
			<form:input type="hidden" path="dtUltimaAlteracao" />
	
			<div class="form-group">
				<div class="col-md-4">
					<label for="id"><spring:message code="CODIGO" text="CODIGO" /></label>
					<form:input type="text" path="id" id="id" class="form-control" />
				</div>					
				<div class="col-md-8">
					<label for="nome"><spring:message code="NOME" text="NOME" /></label>
					<form:input type="text" path="nome" id="nome" class="form-control"/>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-md-12">
					<label for="situacao"><spring:message code="SITUACAO" text="SITUACAO" /></label>
					<c:set var="selecione"><spring:message code="SELECIONE"/></c:set>
					<c:set var="enumValues" value="<%=br.feevale.tc.oee.enums.AtivoInativo.values()%>"/>
					<form:select path="dmSituacao" class="form-control">
						<form:option value="" label="${selecione}" />
						<c:forEach items="${enumValues}" var="option">
							<c:set var="optionLabel"><spring:message code="${option.meaningKey}"/></c:set>
							<form:option value="${option}" label="${optionLabel}" />
						</c:forEach>
					</form:select>
				</div>
			</div>
			
			
			<% 
			OEEValidationResult validationResult = (OEEValidationResult)request.getAttribute("errors");
			if (validationResult != null && validationResult.hasErrors()){
				for (OEEValidationMessage error : validationResult.getErrors()){
					out.print("\t\t\t<div class=\"alert alert-danger\" role=\"alert\">\n\t\t\t\t");
				  	out.print(DefaultMessages.get(request, error));
				  	out.print("\t\t\t</div>");
				}
			}
			%>
	
			<button type="submit" class="btn btn-default"><spring:message code="SALVAR" text="SALVAR" /></button>
		
		</form:form>
		
		
		<footer>
        	<p>&copy; Emanuel Cruz Rodrigues 2015</p>
      	</footer>
    </div> <!-- /container -->
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="../resources/js/jquery.min.js"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../resources/js/ie10-viewport-bug-workaround.js"></script>
	
	
</body>
</html>