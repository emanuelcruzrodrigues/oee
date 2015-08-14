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
            <li class="active"><a href="../databases/"><spring:message code="BANCO_DE_DADOS" text="BANCO_DE_DADOS" /></a></li>
            <li><a href="../webservices/"><spring:message code="WEBSERVICES" text="WEBSERVICES" /></a></li>
          </ul>
         <%=JSPUtils.printEntrarSairButton(request)%>
          
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    		
	<div class="container">	
	
	  <div class="title">
        <div class="title_img">
          <img src="../resources/img/database.png">
        </div>
        <div class="title_text">
          <h2><spring:message code="DADOS_PARA_GERACAO_DO_INDICE" text="DADOS_PARA_GERACAO_DO_INDICE" /></h2>
        </div>
		<hr>
      </div>
      
      <br/>
	
      <div class="row">
      
        <div class="col-md-4">
          <h2><spring:message code="EQUIPAMENTOS" text="EQUIPAMENTOS" /></h2>
          <p><spring:message code="DESCRICAO_TABELA_EQUIPAMENTOS" /></p>
          <p><a class="btn btn-default" href="../equipamento/" role="button"><spring:message code="VER_DETALHES" /> &raquo;</a></p>
        </div>
        
        <div class="col-md-4">
          <h2><spring:message code="OPERACOES" text="OPERACOES" /></h2>
          <p><spring:message code="DESCRICAO_TABELA_OPERACOES" /></p>
          <p><a class="btn btn-default" href="../operacao/" role="button"><spring:message code="VER_DETALHES" /> &raquo;</a></p>
        </div>
        
        <div class="col-md-4">
          <h2><spring:message code="MOTIVOS_PARADA" text="MOTIVOS_PARADA" /></h2>
          <p><spring:message code="DESCRICAO_TABELA_MOTIVOS_PARADA" /></p>
          <p><a class="btn btn-default" href="../motivoParada/" role="button"><spring:message code="VER_DETALHES" /> &raquo;</a></p>
        </div>
        
        
      </div>
      
      <div class="row">
        <div class="col-md-4">
          <h2><spring:message code="ORDENS_PRODUCAO" /></h2>
          <p><spring:message code="DESCRICAO_TABELA_ORDENS_PRODUCAO" /></p>
          <p><a class="btn btn-default" href="../ordemProducao/" role="button"><spring:message code="VER_DETALHES" /> &raquo;</a></p>
        </div>
      </div>
      
      <div class="row">
        <div class="col-md-4">
          <h2><spring:message code="APONTAMENTOS_QUANTIDADE" /></h2>
          <p><spring:message code="DESCRICAO_TABELA_APONTAMENTOS_QUANTIDADE" /></p>
          <p><a class="btn btn-default" href="../apontamentoQuantidade/" role="button"><spring:message code="VER_DETALHES" /> &raquo;</a></p>
        </div>
      </div>
      
      
      <br/>
	
      <div class="title">
        <div class="title_img">
          <img src="../resources/img/settings.png">
        </div>
        <div class="title_text">
          <h2><spring:message code="DADOS_DE_SISTEMA" text="DADOS_DE_SISTEMA" /></h2>
        </div>
		<hr>
      </div>
      
      <br/>
      
      <div class="row">
        <div class="col-md-4">
          <h2><spring:message code="USUARIOS" text="USUARIOS" /></h2>
          <p><spring:message code="DESCRICAO_TABELA_USUARIOS" /></p>
          <p><a class="btn btn-default" href="../usuario/" role="button"><spring:message code="VER_DETALHES" text="VER_DETALHES" /> &raquo;</a></p>
        </div>
      </div>

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