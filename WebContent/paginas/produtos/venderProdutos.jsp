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
					<legend>Vender Produto</legend>
<input type="hidden" name="acao" id="acao" value=""> 
<input type="hidden" name="id" id="id" value="${produtoBean.id}"> 
<input type="hidden" name="quantidade" id="quantidade" value="${produtoBean.quantidade}"> 

<div class="form-group">
Nome..: ${produtoBean.nome} - ${produtoBean.marcaBean.nome}
</div>
<div class="form-group">
Quantidade..: ${produtoBean.quantidade}
</div>
<div class="form-group">
Preco..: ${produtoBean.preco}
</div>
<br/>
Quantidade a vender..:<br/>
<div class="col-sm-3">
	<div class="form-group">
		<input type="number" class="form-control" name="quantidadeAVender" id="quantidadeAVender"/>
	</div>	
</div>
<br/><br/><br/>

<input type="button" name="botaoVender" id="botaoVender" value="VENDER" class="btn btn-success">
<input type="button" name="botaoVoltarListagem" id="botaoVoltarListagem" value="VOLTAR" class="btn btn-primary">
</fieldset> 
</form>
</div>
</div>
</body>
</html>