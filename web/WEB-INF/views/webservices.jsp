<%@page import="br.feevale.tc.oee.framework.utils.JSPUtils"%>
<%@page import="br.feevale.tc.oee.framework.i18n.DefaultMessages"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
    <link href="../resources/css/databases.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
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
            <li><a href="../databases/"><spring:message code="BANCO_DE_DADOS" text="BANCO_DE_DADOS" /></a></li>
            <li class="active"><a href="../webservices/"><spring:message code="WEBSERVICES" text="WEBSERVICES" /></a></li>
          </ul>
          <%=JSPUtils.printEntrarSairButton(request)%>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    
    <br/>
    	
	<div class="container">	
	
	<div class="table-responsive">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th><spring:message code="WEBSERVICE" /></th>
					<th><spring:message code="DESCRICAO" /></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
		          <td><spring:message code="EQUIPAMENTOS" /></td>
		          <td><spring:message code="DESCRICAO_WEBSERVICE_EQUIPAMENTOS" /></td>
		          <td><a class="btn btn-default" href="../ws/inserirOuAlterarEquipamentoServiceDefinition.wsdl" role="button"><spring:message code="VER_DETALHES" text="VER_DETALHES" /> &raquo;</a></td>
				</tr>
				<tr>
		          <td><spring:message code="MOTIVOS_PARADA" /></td>
		          <td><spring:message code="DESCRICAO_WEBSERVICE_MOTIVOS_PARADA" /></td>
		          <td><a class="btn btn-default" href="../ws/inserirOuAlterarMotivoParadaServiceDefinition.wsdl" role="button"><spring:message code="VER_DETALHES" text="VER_DETALHES" /> &raquo;</a></td>
				<tr>
		          <td><spring:message code="ORDENS_PRODUCAO" /></td>
		          <td><spring:message code="DESCRICAO_WEBSERVICE_ORDENS_PRODUCAO" /></td>
		          <td><a class="btn btn-default" href="../ws/inserirOuAlterarOrdemProducaoServiceDefinition.wsdl" role="button"><spring:message code="VER_DETALHES" text="VER_DETALHES" /> &raquo;</a></td>
				</tr>
				<tr>
		          <td><spring:message code="INSERIR_PROGRAMACAO_PRODUCAO_EQUIPAMENTO" /></td>
		          <td><spring:message code="DESCRICAO_WEBSERVICE_INSERIR_PROGRAMACAO_PRODUCAO_EQUIPAMENTO" /></td>
		          <td><a class="btn btn-default" href="../ws/inserirProgramacaoProducaoEquipamentoServiceDefinition.wsdl" role="button"><spring:message code="VER_DETALHES" text="VER_DETALHES" /> &raquo;</a></td>
				</tr>
				<tr>
		          <td><spring:message code="EXCLUIR_PROGRAMACAO_PRODUCAO_EQUIPAMENTO" /></td>
		          <td><spring:message code="DESCRICAO_WEBSERVICE_EXCLUIR_PROGRAMACAO_PRODUCAO_EQUIPAMENTO" /></td>
		          <td><a class="btn btn-default" href="../ws/excluirProgramacaoProducaoEquipamentoServiceDefinition.wsdl" role="button"><spring:message code="VER_DETALHES" text="VER_DETALHES" /> &raquo;</a></td>
				</tr>
				<tr>
		          <td><spring:message code="INICIAR_APONTAMENTO_PRODUCAO" /></td>
		          <td><spring:message code="DESCRICAO_WEBSERVICE_INICIAR_APONTAMENTO_PRODUCAO" /></td>
		          <td><a class="btn btn-default" href="../ws/iniciarApontamentoProducaoServiceDefinition.wsdl" role="button"><spring:message code="VER_DETALHES" text="VER_DETALHES" /> &raquo;</a></td>
				</tr>
				<tr>
		          <td><spring:message code="INICIAR_APONTAMENTO_PARADA" /></td>
		          <td><spring:message code="DESCRICAO_WEBSERVICE_INICIAR_APONTAMENTO_PARADA" /></td>
		          <td><a class="btn btn-default" href="../ws/iniciarApontamentoParadaServiceDefinition.wsdl" role="button"><spring:message code="VER_DETALHES" text="VER_DETALHES" /> &raquo;</a></td>
				</tr>
				<tr>
		          <td><spring:message code="ENCERRAR_APONTAMENTO_DO_EQUIPAMENTO" /></td>
		          <td><spring:message code="DESCRICAO_WEBSERVICE_ENCERRAR_APONTAMENTO_DO_EQUIPAMENTO" /></td>
		          <td><a class="btn btn-default" href="../ws/encerrarApontamentoEquipamentoServiceDefinition.wsdl" role="button"><spring:message code="VER_DETALHES" text="VER_DETALHES" /> &raquo;</a></td>
				</tr>
				<tr>
		          <td><spring:message code="INSERIR_APONTAMENTO_QUANTIDADE" /></td>
		          <td><spring:message code="DESCRICAO_WEBSERVICE_INSERIR_APONTAMENTO_QUANTIDADE" /></td>
		          <td><a class="btn btn-default" href="../ws/inserirApontamentoQuantidadeServiceDefinition.wsdl" role="button"><spring:message code="VER_DETALHES" text="VER_DETALHES" /> &raquo;</a></td>
				</tr>
				<tr>
		          <td><spring:message code="INSERIR_APONTAMENTO_QUANTIDADE_REFUGO" /></td>
		          <td><spring:message code="DESCRICAO_WEBSERVICE_INSERIR_APONTAMENTO_QUANTIDADE_REFUGO" /></td>
		          <td><a class="btn btn-default" href="../ws/inserirApontamentoQuantidadeRefugoServiceDefinition.wsdl" role="button"><spring:message code="VER_DETALHES" text="VER_DETALHES" /> &raquo;</a></td>
				</tr>
				<tr>
		          <td><spring:message code="INDICE_OEE_EM_TEMPO_REAL" /></td>
		          <td><spring:message code="DESCRICAO_WEBSERVICE_INDICE_OEE_EM_TEMPO_REAL" /></td>
		          <td><a class="btn btn-default" href="../ws/getIndiceOEETempoRealServiceDefinition.wsdl" role="button"><spring:message code="VER_DETALHES" text="VER_DETALHES" /> &raquo;</a></td>
				</tr>
			</tbody>
		</table>
	</div>
	

          
      


      
      

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