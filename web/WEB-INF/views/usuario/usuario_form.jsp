<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script language="javascript">
		function actionLimparSenha(){
			document.getElementById("senha").value = "";
		}
	</script>
</head>
<body>
	<form:form action="salvar" modelAttribute="bean" method="POST">
		<fieldset>
			<form:input type="hidden" path="id" />
			<form:errors path="id" />
			
			<form:input type="hidden" path="dtUltimaAlteracao" />
			<form:errors path="dtUltimaAlteracao" />

			<label>Nome:</label>
			<form:input type="text" path="nome" id="nome" />
			<form:errors path="nome" />

			<label>Senha:</label>
			<form:input type="password" path="senha" id="senha" readOnly="readOnly"/>
			<button id="btnLimparSenha" onclick="actionLimparSenha();">Resetar senha</button>
			<form:errors path="senha" />

			<input type="submit" value="Salvar" />
		</fieldset>
	</form:form>
</body>
</html>