<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<form:form action="listar" modelAttribute="example" method="POST">
		<fieldset>
			<label>Nome:</label>
			<form:input type="text" path="nome" id="nome" />
			<input type="submit" value="Pesquisar" />
		</fieldset>
	</form:form>

	<table>
		<thead>
			<tr>
				<th>Nome</th>
				<th>Ações</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="usuario">
				<tr class="gradeA">
					<td>${usuario.nome}</td>
					<td>
						<a href="editar?id=${usuario.id}">Editar</a> 
						<a href="excluir?id=${usuario.id}">Excluir</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a href="novo">Novo Usuário</a>
</body>
</html>