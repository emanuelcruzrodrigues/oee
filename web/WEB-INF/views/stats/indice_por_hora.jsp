<%@page import="br.feevale.tc.oee.utils.OEEJSPUtils"%>
<%@page import="br.feevale.tc.oee.stats.UnidadeIndiceOEE"%>
<%@page import="java.util.List"%>
<%@page import="br.feevale.tc.oee.framework.utils.JSPUtils"%>
<%@page import="br.feevale.tc.oee.framework.i18n.DefaultMessages"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
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
    <link href="../resources/css/indice.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script src="../resources/js/chart.min.js"></script>
    <script src="../resources/js/legend.js"></script>
    
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
            <li class="active"><a href="../stats/"><spring:message code="ESTATISTICAS" text="ESTATISTICAS" /></a></li>
            <li><a href="../databases/"><spring:message code="BANCO_DE_DADOS" text="BANCO_DE_DADOS" /></a></li>
            <li><a href="../webservices/"><spring:message code="WEBSERVICES" text="WEBSERVICES" /></a></li>
          </ul>
          <%=JSPUtils.printEntrarSairButton(request)%>
          
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    		
	<div class="container">	

		<form:form action="hora" modelAttribute="filter" method="POST" class="form-horizontal">
			<div class="row form-group">
				<div class="col-md-2">
					<label for="dt"><spring:message code="DATA" /></label>
					<form:input type="text" path="dt" id="dt" class="form-control" />
				</div>
				
				<div class="col-md-8">
					<label for="equipamento"><spring:message code="EQUIPAMENTO"/></label>
					<c:set var="selecione"><spring:message code="SELECIONE"/></c:set>
					<form:select path="equipamento" class="form-control">
						<form:option value="" label="${selecione}" />
						<form:options items="${equipamentos}" itemLabel="nome" itemValue="id" />
					</form:select>
				</div>
				
				<div class="col-md-2">
					<label for="dmLayout"><spring:message code="LAYOUT" /></label>
					<c:set var="enumValues" value="<%=br.feevale.tc.oee.enums.AnaliticoSintetico.values()%>"/>
					<form:select path="dmLayout" class="form-control">
						<c:forEach items="${enumValues}" var="option">
							<c:set var="optionLabel"><spring:message code="${option.meaningKey}"/></c:set>
							<form:option value="${option}" label="${optionLabel}" />
						</c:forEach>
					</form:select>
				</div>
				
								
			</div>
			
			<%=JSPUtils.printErrors(request)%>
			
			<div class="row">
				<div class="col-md-4">
					<button type="submit" class="btn btn-default"><spring:message code="ATUALIZAR" /></button>
				</div>
			</div>
	
		</form:form>
		
		<br/>
		
		<div class="table-responsive">
			<table class="table table-bordered table-hover stat">
				<thead>
					<tr>
						<th width="75px"><spring:message code="INICIO_PERIODO" /></th>
						<th width="75px"><spring:message code="FIM_PERIODO" /></th>
						
						<th width="100px"><spring:message code="TEMPO_DE_CARGA" /></th>
						<th width="100px"><spring:message code="TEMPO_DAS_PARADAS" /></th>
						<th width="100px"><spring:message code="DISPONIBILIDADE" /></th>
						
						<th width="100px"><spring:message code="VOLUME_TOTAL_PRODUZIDO" /></th>
						<th width="100px"><spring:message code="UNIDADES_BOAS_PRODUZIDAS" /></th>
						<th width="100px"><spring:message code="QUALIDADE" /></th>
						
						<th width="100px"><spring:message code="RUNTIME" /></th>
						<th width="100px"><spring:message code="TEMPO_CICLO_TEORICO" /></th>
						<th width="100px"><spring:message code="TEMPO_CICLO_REAL" /></th>
						<th width="100px"><spring:message code="DESEMPENHO" /></th>
						
						<th width="100px"><spring:message code="OEE" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${indices}" var="indice">
						<c:set var="detalhes" value="${indice.detalhes}"/>
						<c:set var="rowspan" value="${isAnalitico ? (fn:length(detalhes)+1) : 1}"/>
						<tr>
							<td class="numeric" rowspan="${rowspan}"><joda:format value="${indice.inicio}" style="-S" /></td>
							<td class="numeric" rowspan="${rowspan}"><joda:format value="${indice.fim}" style="-S" /></td>
							<td class="numeric"><fmt:formatNumber type="number" value="${indice.tempoCargaMinutos}" minFractionDigits="0" maxFractionDigits="0"/></td>
							<td class="numeric"><fmt:formatNumber type="number" value="${indice.dtTotalMinutos}" minFractionDigits="0" maxFractionDigits="0"/></td>
							<td class="numeric"><strong><fmt:formatNumber type="number" value="${indice.disponibilidade}" minFractionDigits="3" maxFractionDigits="3"/></strong></td>
							
							<td class="numeric"><fmt:formatNumber type="number" value="${indice.volumeTotalProduzido}" minFractionDigits="0" maxFractionDigits="0"/></td>
							<td class="numeric"><fmt:formatNumber type="number" value="${indice.quantidadeUnidadesBoasProduzidas}" minFractionDigits="0" maxFractionDigits="0"/></td>
							<td class="numeric"><strong><fmt:formatNumber type="number" value="${indice.qualidade}" minFractionDigits="3" maxFractionDigits="3"/></strong></td>
							
							<td class="numeric"><fmt:formatNumber type="number" value="${indice.runtimeMinutos}" minFractionDigits="0" maxFractionDigits="0"/></td>
							<td class="numeric"><fmt:formatNumber type="number" value="${indice.tempoCicloTeoricoUnidadesPorMinuto}" minFractionDigits="3" maxFractionDigits="3"/></td>
							<td class="numeric"><fmt:formatNumber type="number" value="${indice.tempoCicloRealUnidadesPorMinuto}" minFractionDigits="3" maxFractionDigits="3"/></td>
							<td class="numeric"><strong><fmt:formatNumber type="number" value="${indice.desempenho}" minFractionDigits="3" maxFractionDigits="3"/></strong></td>
							
							<td class="numeric"><strong><fmt:formatNumber type="number" value="${indice.oee}" minFractionDigits="3" maxFractionDigits="3"/></strong></td>
						</tr>
						<c:if test="${isAnalitico}">
							<c:forEach items="${detalhes}" var="detalhe">
								<tr>
									<td colspan="3" class="detalhe">${detalhe.ordemProducao.descricao}</td>
									
									<td class="detalhe numeric"><fmt:formatNumber type="number" value="${detalhe.volumeTotalProduzido}" minFractionDigits="0" maxFractionDigits="0"/></td>
									<td class="detalhe numeric"><fmt:formatNumber type="number" value="${detalhe.quantidadeUnidadesBoasProduzidas}" minFractionDigits="0" maxFractionDigits="0"/></td>
									<td class="detalhe numeric"><strong><fmt:formatNumber type="number" value="${detalhe.qualidade}" minFractionDigits="3" maxFractionDigits="3"/></strong></td>
									
									<td class="detalhe numeric"><fmt:formatNumber type="number" value="${detalhe.runtimeMinutos}" minFractionDigits="0" maxFractionDigits="0"/></td>
									<td class="detalhe numeric"><fmt:formatNumber type="number" value="${detalhe.tempoCicloTeoricoUnidadesPorMinuto}" minFractionDigits="3" maxFractionDigits="3"/></td>
									<td class="detalhe numeric"><fmt:formatNumber type="number" value="${detalhe.tempoCicloRealUnidadesPorMinuto}" minFractionDigits="3" maxFractionDigits="3"/></td>
									<td class="detalhe numeric"><strong><fmt:formatNumber type="number" value="${detalhe.desempenho}" minFractionDigits="3" maxFractionDigits="3"/></strong></td>
								</tr>
							</c:forEach>
						</c:if>
						
					</c:forEach>
				</tbody>
			</table>
		</div>

		
		<br/>
		
        <%=OEEJSPUtils.printGrafico(request, "HH:mm") %>
		
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