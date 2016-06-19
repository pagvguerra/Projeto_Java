$(function() {
		
	$("#botaoVender").on('click', function() {
		
		var quantidadeAtual = $("#quantidade").val();
		var quantidadeAVender = $("#quantidadeAVender").val();
		
		if(quantidadeAVender == null || quantidadeAVender =='') {
			alert('Preencha a Quantidade a ser vendida');
			return false;
		} else {
			if (isNaN(quantidadeAVender)) {  
			       alert("Quantidade a ser vendida aceita apenas valores numericos");  
			       return false;  
			} 
		}
		
		if(quantidadeAtual < quantidadeAVender) {
			alert("Quantidade a ser vendida nao pode ser maior que a quantidade em estoque");  
		    return false;  
		}
		
		if(quantidadeAVender < 1) {
			alert("Preencha um valor valido");  
			return false;
		}
		
		$('form').attr({
		 	action : 'http://localhost:8080/Projeto_Java/servlet/ProdutoController',
		    method : 'post'
		 });
		 $("#acao").val('VENDER_PRODUTO');
		 $('form').submit();
		
	});
	
	
	$("#botaoAlterar").on('click', function() {
		
		var nome = $("#nome").val();
		var quantidade = $("#quantidade").val();
		var preco = $("#preco").val();
		
		if(nome == null || nome =='') {
			alert('Preencha a Nome');
			return false;
		} 
		
		if(quantidade == null || quantidade =='') {
			alert('Preencha a Quantidade');
			return false;
		} else {
			if (isNaN(quantidade)) {  
			       alert("Quantidade aceita apenas valores numericos");  
			       return false;  
			} 
		}
		
		if(preco == null || preco =='') {
			alert('Preencha o Preço');
			return false;
		} else {
			if (isNaN(preco)) {  
			       alert("Preço aceita apenas valores numericos");  
			       return false;  
			} 
		}
				
		$('form').attr({
		 	action : 'http://localhost:8080/Projeto_Java/servlet/ProdutoController',
		    method : 'post'
		 });
		 $("#acao").val('ALTERAR');
		 $('form').submit();
		
	});
	
	$("#botaoInserir").on('click', function() {
			
		var nome = $("#nome").val();
		var quantidade = $("#quantidade").val();
		var preco = $("#preco").val();
		
		if(nome == null || nome =='') {
			alert('Preencha a Nome');
			return false;
		} 
		
		if(quantidade == null || quantidade =='') {
			alert('Preencha a Quantidade');
			return false;
		} else {
			if (isNaN(quantidade)) {  
			       alert("Quantidade aceita apenas valores numericos");  
			       return false;  
			} 
		}
		
		if(preco == null || preco =='') {
			alert('Preencha o Preco');
			return false;
		} else {
			if (isNaN(preco)) {  
			       alert("Preco aceita apenas valores numericos");  
			       return false;  
			} 
		}
		
		$('form').attr({
		 	action : 'http://localhost:8080/Projeto_Java/servlet/ProdutoController',
		    method : 'post'
		 });
		 $("#acao").val('INSERIR');
		 $('form').submit();

	});
	
	$("#botaoPrepararInserir").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/Projeto_Java/servlet/ProdutoController',
		    method : 'post'
		 });
		 $("#acao").val('PREPARAR_INSERIR');
		 $('form').submit();
	});
	
	$("#botaoVoltar").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/Projeto_Java/servlet/ProdutoController',
		    method : 'post'
		 });
		 $("#acao").val('LISTAR_TODOS');
		 $('form').submit();
	});
	
	$("#botaoVoltarListagem").on('click', function() {
		$('form').attr({
		 	action : 'http://localhost:8080/Projeto_Java/servlet/ProdutoController',
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