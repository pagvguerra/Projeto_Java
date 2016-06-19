$(function() {
		
	$("#botaoAlterar").on('click', function() {
		
		var nome = $("#nome").val();

		if(nome!=null && nome!='') {
		
			 $('form').attr({
			 	action : 'http://localhost:8080/Projeto_Java/servlet/MarcaController',
			    method : 'post'
			 });
			 $("#acao").val('ALTERAR');
			 $('form').submit();
		} else {
			alert('Preencha o nome');
		}
		
	});
	
	$("#botaoInserir").on('click', function() {
		
		var nome = $("#nome").val();
		
		if(nome!=null && nome!='') {
			 $('form').attr({
			 	action : 'http://localhost:8080/Projeto_Java/servlet/MarcaController',
			    method : 'post'
			 });
			 $("#acao").val('INSERIR');
			 $('form').submit();
		} else {
			alert('Preencha o nome');
		}	 
	});
	
	$("#botaoPrepararInserir").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/Projeto_Java/servlet/MarcaController',
		    method : 'post'
		 });
		 $("#acao").val('PREPARAR_INSERIR');
		 $('form').submit();
	});
	
	$("#botaoVoltar").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/Projeto_Java/servlet/MarcaController',
		    method : 'post'
		 });
		 $("#acao").val('LISTAR_TODOS');
		 $('form').submit();
	});
	
	$("#botaoVoltarListagem").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/Projeto_Java/servlet/MarcaController',
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