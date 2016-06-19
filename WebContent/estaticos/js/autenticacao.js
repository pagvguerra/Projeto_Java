$(function() {
	
	$("#enviar").on('click', function() {
		var login = $("#login").val().trim();
		var senha = $("#senha").val().trim();
			
		if(login === undefined || login == null || login === '') {
			alert('Preencha o Login');
			$("#login").focus();
			return;
		}

		if(senha === undefined || senha == null || senha === '') {
			alert('Preencha a Senha');
			$("#senha").focus();
			return;
		}
		
		 $('form').attr({
			 action : 'http://localhost:8080/Projeto_Java/servlet/AutenticacaoController',
			 method : 'post'
		 });

		 $("#acao").val('LOGIN');
		 $('form').submit();
		 
	});
	
} );