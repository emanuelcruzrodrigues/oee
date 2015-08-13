<%@page import="br.feevale.tc.oee.framework.i18n.DefaultMessages"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
    <link href="../resources/css/login.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

	<div class="container">
		<form:form action="login" modelAttribute="login" method="POST" class="form-signin">
			
			<h2 class="form-signin-heading"><spring:message code="EFETUE_LOGIN" text="EFETUE_LOGIN" /></h2>
			
			<div class="form-group">
				<label for="nomeUsuario"><spring:message code="NOME" text="NOME" />:</label>
				<form:input type="text" path="nomeUsuario" id="nomeUsuario" class="form-control"/>
			</div>
	
			<div class="form-group">
				<label for="senha"><spring:message code="SENHA" text="SENHA" />:</label>
				<form:input type="password" path="senha" id="senha" class="form-control"/>
			</div>
	
			<form:errors cssClass="alert alert-danger" element="div"/>
			
			<input type="submit" value="Ok" class="btn btn-lg btn-primary btn-block"/>
			
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