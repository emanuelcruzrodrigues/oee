<%@page import="br.feevale.tc.oee.framework.utils.JSPUtils"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
    <link href="../resources/css/form.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
	<script language="javascript">
		function actionLimparSenha(){
			document.getElementById("senha").value = "";
		}
	</script>
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


		<form:form action="salvar" modelAttribute="bean" method="POST" class="form-horizontal">
			<form:input type="hidden" path="id" />
			<form:input type="hidden" path="dtUltimaAlteracao" />
	
			<div class="form-group">
				<label for="nome"><spring:message code="NOME" text="NOME" />:</label>
				<form:input type="text" path="nome" id="nome" class="form-control"/>
			</div>
	
			<div class="form-group">
				<label for="senha"><spring:message code="SENHA" text="SENHA" />:</label>
				<div class="form-inline">
					<form:input type="password" path="senha" id="senha" readOnly="readOnly" class="form-control"/>
					<button id="btnLimparSenha" onclick="actionLimparSenha();" class="btn btn-danger"><spring:message code="RESETAR_SENHA" text="RESETAR_SENHA" /></button>
				</div>
			</div>
	
			<%=JSPUtils.printErrors(request)%>
	
			<button type="submit" class="btn btn-default"><spring:message code="SALVAR" text="SALVAR" /></button>
		
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