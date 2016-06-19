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
		<div class="container">
			<div class="col-sm-5">
<form name="form" method="post">
		<br/><br/>
				<fieldset>
					<legend>Cadastro de Produto</legend>
<input type="hidden" name="acao" id="acao" value=""> 
<div class="form-group">
Nome..: <input class="form-control" type="text" name="nome" id="nome">
</div>
<div class="form-group">
Quantidade..: <input class="form-control" type="number" name="quantidade" id="quantidade">
</div>
<div class="form-group">
Preco..: <input class="form-control" type="text" name="preco" id="preco">
</div>
<div class="form-group">
Marca..: 
<c:if test="${not empty listaMarcas}">
<select name="marca" class="form-control">
	<c:forEach var="beanMarca" items="${listaMarcas}" >
		<option value="<c:out value="${beanMarca.id}"/>"><c:out value="${beanMarca.nome}"/></option>
	</c:forEach>
</select>
</c:if>
<c:if test="${empty listaMarcas}">
	Ainda não existem marcas cadastradas
</c:if>
</div>
<br/>
<input type="button" name="botaoInserir" id="botaoInserir" value="CADASTRAR" class="btn btn-success">
<input type="button" name="botaoVoltarListagem" id="botaoVoltarListagem" value="VOLTAR" class="btn btn-primary">
</fieldset> 
</form>
</div>
</div>
</body>
</html>