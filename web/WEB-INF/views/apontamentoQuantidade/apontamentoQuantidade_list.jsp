<%@page import="br.feevale.tc.oee.framework.utils.JSPUtils"%>
<%@page import="br.feevale.tc.oee.framework.i18n.DefaultMessages"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
    <link href="../resources/css/list.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->    
    
    <%=JSPUtils.printCRUDJavaScripts(request) %>
</head>
<body>

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
          <%=JSPUtils.printEntrarSairButton(request)%>
          
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    		
	<div class="container">	
	
		<form:form action="listar" modelAttribute="example" method="POST" class="form-horizontal">
		
			<div class="row form-group">
				<div class="col-md-2">
					<label for="dtInicial"><spring:message code="DATA_INICIAL" /></label>
					<form:input type="text" path="dtInicial" id="dtInicial" class="form-control" />
				</div>
				
				<div class="col-md-2">
					<label for="dtFinal"><spring:message code="DATA_FINAL" /></label>
					<form:input type="text" path="dtFinal" id="dtFinal" class="form-control" />
				</div>
			</div>
			<div class="row form-group">
				<div class="col-md-4">
					<label for="equipamento"><spring:message code="EQUIPAMENTO"/></label>
					<c:set var="selecione"><spring:message code="SELECIONE"/></c:set>
					<form:select path="equipamento" class="form-control">
						<form:option value="" label="${selecione}" />
						<form:options items="${equipamentos}" itemLabel="nome" itemValue="id" />
					</form:select>
				</div>
				
				<div class="col-md-4">
					<label for="ordemProducao"><spring:message code="ORDEM_PRODUCAO"/></label>
					<c:set var="selecione"><spring:message code="SELECIONE"/></c:set>
					<form:select path="ordemProducao" class="form-control">
						<form:option value="" label="${selecione}" />
						<form:options items="${ordensProducao}" itemLabel="descricao" itemValue="id" />
					</form:select>
				</div>
				
				<div class="col-md-4">
					<label for="dmQualidade"><spring:message code="QUALIDADE_PRODUCAO" /></label>
					<c:set var="selecione"><spring:message code="SELECIONE"/></c:set>
					<c:set var="enumValues" value="<%=br.feevale.tc.oee.enums.QualidadeProducao.values()%>"/>
					<form:select path="dmQualidade" class="form-control">
						<form:option value="" label="${selecione}" />
						<c:forEach items="${enumValues}" var="option">
							<c:set var="optionLabel"><spring:message code="${option.meaningKey}"/></c:set>
							<form:option value="${option}" label="${optionLabel}" />
						</c:forEach>
					</form:select>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<button type="submit" class="btn btn-default"><spring:message code="PESQUISAR" text="PESQUISAR" /></button>
				</div>
			</div>
			
		</form:form>
		
		<br/>
	
		<div class="table-responsive">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th><spring:message code="DATA_HORA" /></th>
						<th><spring:message code="ORDEM_PRODUCAO" /></th>
						<th><spring:message code="QUANTIDADE" /></th>
						<th><spring:message code="QUALIDADE_PRODUCAO" /></th>
						<th><spring:message code="ACOES" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="apontamento">
						<tr>
							<td><joda:format value="${apontamento.dtHr}" style="SS" /></td>
							<td>${apontamento.ordemProducao.descricao}</td>
							<td class="numeric"><fmt:formatNumber type="number" value="${apontamento.quantidade}" minFractionDigits="3" maxFractionDigits="3"/></td>
							<td><spring:message code="${apontamento.dmQualidade.meaningKey}" /></td>
							<td>
								<button class="btn btn-default" onclick="actionEditar(${apontamento.id});"><spring:message code="EDITAR" text="EDITAR" /></button>
								<button class="btn btn-danger" onclick="actionExcluir(${apontamento.id});"><spring:message code="EXCLUIR" text="EXCLUIR" /></button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<form:form action="novo" method="GET" class="form-inline">
			<button type="submit" class="btn btn-default"><spring:message code="NOVO" text="NOVO" /></button>
		</form:form>
		
		<br/>
		
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