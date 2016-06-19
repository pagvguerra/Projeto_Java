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
		<script src="${js}/empregado.js" ></script>
	</head>
<body>
		<div class="container">
			<div class="col-sm-5">
<form name="form" method="post">
		<br/><br/>
				<fieldset>
					<legend>Cadastro de Empregado</legend>
<input type="hidden" name="acao" id="acao" value=""> 
<div class="checkbox">
É Administrador? &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="checkbox" name="perfil" value="ADM">
</div>
<br/>

<div class="form-group">
Login..: <input class="form-control" type="text" name="login" id="login">
</div>
<div class="form-group">
Senha..: <input class="form-control" type="password" name="senha" id="senha">
</div>
<div class="form-group">
Nome..: <input class="form-control" type="text" name="nome" id="nome">
</div>
<div class="form-group">
CPF..: <input class="form-control" type="text" name="cpf" id="cpf">
</div>
<div class="form-group">
RG..: <input class="form-control" type="text" name="rg" id="rg">
</div>
<div class="form-group">
Email..: <input class="form-control" type="text" name="email" id="email">
</div>
<div class="form-group">
Sexo..: <select name="sexo" class="form-control" >
<option value="M">Masculino</option>
<option value="F">Feminino</option>
</select>
</div>
<br/><br/>
<input type="button" name="botaoInserir" id="botaoInserir" value="CADASTRAR" class="btn btn-success">
<input type="button" name="botaoVoltarListagem" id="botaoVoltarListagem" value="VOLTAR" class="btn btn-primary">
</fieldset> 
</form>
</div>
</div>
</body>
</html>	