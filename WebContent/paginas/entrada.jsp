<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@ taglib uri="tlds/c.tld" prefix="c" %>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js"/>
<c:set var="css_bootstrap" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>SISTEMA DE VENDAS DE CALÇADOS</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${js}/entrada.js" ></script>
	</head>
	<body>
		<br/><br/>
		<div class="container">
			<div class="col-sm-12">
					<%@include file="includes/cabecalho.jsp" %>
				<fieldset>
				<legend><B>TELA DE ENTRADA DO SISTEMA</B></legend>
				<form name="entrada" method="post">
					<input type="hidden" name="acao" id="acao" value=""> 
					<strong>
					<ul>
					<c:if test="${empregado.perfil == 'ADM'}">
						<li><a href="#" id="manterFuncionario">MANTER FUNCIONARIOS</a></li>
					</c:if>
					<li><a href="#" id="manterMarca">MANTER MARCAS</a></li>
					<li><a href="#" id="manterProduto">
						<c:if test="${empregado.perfil == 'ADM'}">
							MANTER PRODUTOS
						</c:if>
						<c:if test="${empregado.perfil == 'USU'}">
							MANTER/VENDER PRODUTOS
						</c:if>			
					</a>
					</li>
					</ul>
					</strong>
				</form>
				</fieldset>
			</div>
		</div>		
	</body>
</html>