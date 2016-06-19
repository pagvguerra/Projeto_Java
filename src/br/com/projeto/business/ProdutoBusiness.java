package br.com.projeto.business;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.beans.MarcaBean;
import br.com.projeto.beans.ProdutoBean;
import br.com.projeto.daos.MarcaDAO;
import br.com.projeto.daos.ProdutoDAO;
import br.com.projeto.resources.Mensagens;
import br.com.projeto.resources.URLs;
import br.com.projeto.utils.Util;

public class ProdutoBusiness {
	
	private static ProdutoBusiness instance = null;
	private String urlRetorno = "";

	public static ProdutoBusiness getInstance() {
		if ( instance == null ) {
			instance = new ProdutoBusiness();
		}
		return instance;
	}
		
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		try { 
			String acao = request.getParameter("acao");
			if(Util.isEmpty(acao)) {
				System.out.println("Ação nao definida");
				preencheRetorno(request, response, Mensagens.ERRO_GENERICO, URLs.URL_ERRO_GENERICO);
			} else if(acao.equalsIgnoreCase("INSERIR")) {
				inserirProduto(request, response);
			} else if(acao.equalsIgnoreCase("ALTERAR")) {
				alterarProduto(request, response);
			} else if(acao.equalsIgnoreCase("EXCLUIR")) {
				excluirProduto(request, response);
			} else if(acao.equalsIgnoreCase("DETALHAR_PRODUTO")) {
				detalharProduto(request, response);
			} else if(acao.equalsIgnoreCase("LISTAR_TODOS")) {
				listarTodosProdutos(request, response);
			} else if(acao.equalsIgnoreCase("PREPARAR_INSERIR")) {	
				prepararInserir(request, response);
			} else if(acao.equals("PREPARAR_VENDER_PRODUTO")) {
				prepararVenderProduto(request, response);
			} else if(acao.equals("VENDER_PRODUTO")) {
				venderProduto(request, response);
			}
			
		} catch (Exception e) {
			System.out.println("Erro ProdutoController. Mensagem: " + e.getMessage());
			preencheRetorno(request, response, Mensagens.ERRO_GENERICO, URLs.URL_ERRO_GENERICO);
		}
		
		return urlRetorno;
	}

	private void venderProduto(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
		try {
			
			int id = Integer.parseInt(request.getParameter("id"));
			int quantidadeAtual = Integer.parseInt(request.getParameter("quantidade"));
			int quantidadeAVender = Integer.parseInt(request.getParameter("quantidadeAVender"));
			
			ProdutoBean produtoBean = new ProdutoBean();
			produtoBean.setId(id);
			produtoBean.setQuantidade(quantidadeAtual-quantidadeAVender);
			new ProdutoDAO().alterarQuantidade(produtoBean);

			listarTodosProdutos(request, response);
			
			preencheRetorno(request, response, "Produto vendido com sucesso", URLs.URL_ERRO_VALIDACAO_PRD);
		} catch (Exception e) {
			throw e;
		}
		
	}

	private void prepararVenderProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try{
			ProdutoBean produtoBean = new ProdutoDAO().buscarPorId(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("produtoBean", produtoBean);
			preencheRetorno(request, response, null, URLs.URL_VENDA_PRD);
		} catch (Exception e) {
			throw e;
		}
	}

	private void prepararInserir(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setAttribute("listaMarcas", new MarcaDAO().listaTodos());
		preencheRetorno(request, response, null, URLs.URL_CADASTRAR_PRD);
	}

	private void detalharProduto(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			request.setAttribute("listaMarcas", new MarcaDAO().listaTodos());
			ProdutoBean produtoBean = new ProdutoDAO().buscarPorId(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("produtoBean", produtoBean);
			preencheRetorno(request, response, null, URLs.URL_DETALHE_PRD);
		} catch (Exception e) {
			throw e;
		}
	}

	private void excluirProduto(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			new ProdutoDAO().excluir(Integer.parseInt(request.getParameter("id")));
			listarTodosProdutos(request, response);		
			preencheRetorno(request, response, "Produto excluido com sucesso", URLs.URL_ERRO_VALIDACAO_PRD);
		} catch (Exception e) {
			throw e;
		}
	}

	private void listarTodosProdutos(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			List<ProdutoBean> listaProdutos = new ProdutoDAO().listaTodos();
			request.setAttribute("listaProdutos", listaProdutos);
			preencheRetorno(request, response, null, URLs.URL_ERRO_VALIDACAO_PRD);
		} catch (Exception e) {
			throw e;
		}		
	}

	private void alterarProduto(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			ProdutoBean produtoBean = retornaDadosProduto(request, true);
			new ProdutoDAO().alterar(produtoBean);
			listarTodosProdutos(request, response);
			preencheRetorno(request, response, "Produto atualizado com sucesso", URLs.URL_ERRO_VALIDACAO_PRD);
		} catch (Exception e) {
			throw e;
		}		
	}

	private void inserirProduto(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			ProdutoBean produtoBean = retornaDadosProduto(request, false);
			new ProdutoDAO().inserir(produtoBean);
			listarTodosProdutos(request, response);
			preencheRetorno(request, response, "Produto inserido com sucesso", URLs.URL_ERRO_VALIDACAO_PRD);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ProdutoBean retornaDadosProduto(HttpServletRequest request, boolean pegaId) {
		int id = 0;
		
		if(pegaId)
			id = Integer.parseInt(request.getParameter("id"));
		
		String nome = request.getParameter("nome");
		String preco = request.getParameter("preco");
		String quantidade = request.getParameter("quantidade");
		String marca = request.getParameter("marca");
		
		ProdutoBean produtoBean = new ProdutoBean();
		produtoBean.setId(id);
		produtoBean.setNome(nome);
		produtoBean.setPreco(Integer.parseInt(preco));
		produtoBean.setQuantidade(Integer.parseInt(quantidade));
		MarcaBean marcaBean = new MarcaDAO().buscarPorId(Integer.parseInt(marca));
		produtoBean.setMarcaBean(marcaBean);
		return produtoBean;
	} 

	private void preencheRetorno(HttpServletRequest request, HttpServletResponse response, String mensagem, String url) throws IOException {
		if(mensagem!=null) {
			request.setAttribute("msg", mensagem);
		}

		urlRetorno = url;
	}
	
}