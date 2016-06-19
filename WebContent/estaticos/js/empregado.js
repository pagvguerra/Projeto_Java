$(function() {

	$("#botaoAlterar").on('click', function() {
			
		var login = $("#login").val();
		var senha = $("#senha").val();
		var nome = $("#nome").val();
		var cpf = $("#cpf").val();
		var rg = $("#rg").val();
		var email = $("#email").val();
		
		if(login == null || login =='') {
			alert('Preencha o Login');
			return false;
		} 
		
		if(senha == null || senha =='') {
			alert('Preencha a Senha');
			return false;
		} 
		
		if(nome == null || nome =='') {
			alert('Preencha o Nome');
			return false;
		} 
		
		if(cpf == null || cpf =='') {
			alert('Preencha o CPF');
			return false;
		} 
		
		if(rg == null || rg =='') {
			alert('Preencha o RG');
			return false;
		} 
		
		if(email == null || email =='') {
			alert('Preencha o Email');
			return false;
		} 
		
		$('form').attr({
		 	action : 'http://localhost:8080/Projeto_Java/servlet/EmpregadoController',
		    method : 'post'
		 });
		 $("#acao").val('ALTERAR');
		 $('form').submit();
		
	});
	
	$("#botaoInserir").on('click', function() {
			
		var login = $("#login").val();
		var senha = $("#senha").val();
		var nome = $("#nome").val();
		var cpf = $("#cpf").val();
		var rg = $("#rg").val();
		var email = $("#email").val();
		
		if(login == null || login =='') {
			alert('Preencha o Login');
			return false;
		} 
		
		if(senha == null || senha =='') {
			alert('Preencha a Senha');
			return false;
		} 
		
		if(nome == null || nome =='') {
			alert('Preencha o Nome');
			return false;
		} 
		
		if(cpf == null || cpf =='') {
			alert('Preencha o CPF');
			return false;
		} 
		
		if(rg == null || rg =='') {
			alert('Preencha o RG');
			return false;
		} 
		
		if(email == null || email =='') {
			alert('Preencha o Email');
			return false;
		} 
		
		$('form').attr({
		 	action : 'http://localhost:8080/Projeto_Java/servlet/EmpregadoController',
		    method : 'post'
		 });
		 $("#acao").val('INSERIR');
		 $('form').submit();

	});
	
	$("#botaoPrepararInserir").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/Projeto_Java/servlet/EmpregadoController',
		    method : 'post'
		 });
		 $("#acao").val('PREPARAR_INSERIR');
		 $('form').submit();
	});
	
	$("#botaoVoltar").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/Projeto_Java/servlet/EmpregadoController',
		    method : 'post'
		 });
		 $("#acao").val('LISTAR_TODOS');
		 $('form').submit();
	});
	
	$("#botaoVoltarListagem").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/Projeto_Java/servlet/EmpregadoController',
		    method : 'post'
		 });
		 $("#acao").val('LISTAR_TODOS');
		 $('form').submit();
	});

	$("#botaoVoltarEntrada").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/Projeto_Java/servlet/AutenticacaoController',
		    method : 'post'
		 });
		 $("#acao").val('ENTRADA');
		 $('form').submit();
	});
	
});