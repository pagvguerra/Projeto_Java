$(function() {
	
	$("#manterProduto").on('click', function() {
		 $('form').attr({
		 	action : 'http://localhost:8080/Projeto_Java/servlet/ProdutoController',
		    method : 'post'
		 });
		 $("#acao").val('LISTAR_TODOS');
		 $('form').submit();
	});
	
	$("#manterMarca").on('click', function() {
		 $('form').attr({
		 	action : 'http://localhost:8080/Projeto_Java/servlet/MarcaController',
		    method : 'post'
		 });
		 $("#acao").val('LISTAR_TODOS');
		 $('form').submit();
	});

	$("#manterFuncionario").on('click', function() {
		 $('form').attr({
		 	action : 'http://localhost:8080/Projeto_Java/servlet/EmpregadoController',
		    method : 'post'
		 });
		 $("#acao").val('LISTAR_TODOS');
		 $('form').submit();
	});
	
} );