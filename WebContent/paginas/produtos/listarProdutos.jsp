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
		<script src="${js}/produto.js" ></script>
	</head>
	<body>
		<br/><br/>
		<div class="container">
			<div class="col-sm-12">
					<%@include file="../includes/cabecalho.jsp" %> 
				<fieldset>
					<legend>
				<B>TELA DE GERENCIAMENTO E VENDA DE PRODUTOS DO SISTEMA</B></legend>
				<form name="form" method="post">
					<input type="hidden" name="acao" id="acao" value="">
					<table width="100%" class="table table-hover">
						<thead>
							<tr>
								<th>Nome</td>
								<th>Quantidade</td>
								<th>Preco</td>
								<th>Marca</th>
								<th>Ações</th>
							</tr>
						</thead>
						<c:if test="${empty listaProdutos}">
							<tr align="center">
								<td colspan="6">Não existem produtos cadastrados</td>
							</tr>	
						</c:if>
						<c:if test="${not empty listaProdutos}">
							<c:forEach var="produto" items="${listaProdutos}" >
								<tr>
									<td><c:out value="${produto.nome}"/></td>
									<td><c:out value="${produto.quantidade}"/></td>
									<td><c:out value="${produto.preco}"/></td>
									<td><c:out value="${produto.marcaBean.nome}"/></td>
									<td>
										<a href="http://localhost:8080/Projeto_Java/servlet/ProdutoController?acao=EXCLUIR&id=<c:out value='${produto.id}'/>"><span class="glyphicon glyphicon-trash"></span></a>
										&nbsp;
										<a href="http://localhost:8080/Projeto_Java/servlet/ProdutoController?acao=DETALHAR_PRODUTO&id=<c:out value='${produto.id}'/>"><span class="glyphicon glyphicon-pencil"></span></a>
										<c:if test="${empregado.perfil == 'USU'}">
											&nbsp;
											<a href="http://localhost:8080/Projeto_Java/servlet/ProdutoController?acao=PREPARAR_VENDER_PRODUTO&id=<c:out value='${produto.id}'/>"><span class="glyphicon glyphicon-ok"></span></a>
										</c:if>
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