<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@ taglib uri="../tlds/c.tld" prefix="c" %>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js"/>
<c:set var="css_bootstrap" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<c:set var="servlet" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>SISTEMA DE CADASTRO</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${js}/marca.js" ></script>
	</head>
	<body>
		<br/><br/>
		<div class="container">
			<div class="col-sm-12">
					<%@include file="../includes/cabecalho.jsp" %> 
				<fieldset>
					<legend>
				<B>TELA DE GERENCIAMENTO DE MARCAS DO SISTEMA</B></legend>
				<form name="form" method="post">
					<input type="hidden" name="acao" id="acao" value="">
					<table width="100%" class="table table-hover">
						<thead>
						<tr>
							<th>Nome</th>
							<th>Ações</th>
						</tr>
						</thead>
						<c:if test="${empty listaMarcas}">
							<tr align="center">
								<td colspan="6">Não existem marcas cadastradas</td>
							</tr>	
						</c:if>
						<c:if test="${not empty listaMarcas}">
							<c:forEach var="marca" items="${listaMarcas}" >
								<tr>
									<td><c:out value="${marca.nome}"/></td>
									<td>
										<a href="http://localhost:8080/Projeto_Java/servlet/MarcaController?acao=EXCLUIR&id=<c:out value='${marca.id}'/>"><span class="glyphicon glyphicon-trash"></span></a>
										&nbsp;
										<a href="http://localhost:8080/Projeto_Java/servlet/MarcaController?acao=DETALHAR_MARCA&id=<c:out value='${marca.id}'/>"><span class="glyphicon glyphicon-pencil"></span></a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
					<br /><br />
					<input type="button" name="botaoPrepararInserir" id="botaoPrepararInserir" value="INSERIR" class="btn btn-success"/>
					<input type="button" name="botaoVoltarEntrada" id="botaoVoltarEntrada" value="VOLTAR" class="btn btn-primary"/>
				</form>
				</fieldset>
			</div>
		</div>		
	</body>
</html>