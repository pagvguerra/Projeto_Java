package br.com.projeto.business;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.beans.MarcaBean;
import br.com.projeto.daos.MarcaDAO;
import br.com.projeto.resources.Mensagens;
import br.com.projeto.resources.URLs;
import br.com.projeto.utils.Util;

public class MarcaBusiness {

	private static MarcaBusiness instance = null;
	private String urlRetorno = "";

	public static MarcaBusiness getInstance() {
		if ( instance == null ) {
			instance = new MarcaBusiness();
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
				inserirMarca(request, response);
			} else if(acao.equalsIgnoreCase("ALTERAR")) {
				alterarMarca(request, response);
			} else if(acao.equalsIgnoreCase("EXCLUIR")) {
				excluirMarca(request, response);
			} else if(acao.equalsIgnoreCase("DETALHAR_MARCA")) {
				detalharMarca(request, response);
			} else if(acao.equalsIgnoreCase("LISTAR_TODOS")) {
				listarTodasMarcas(request, response);
			} else if(acao.equalsIgnoreCase("PREPARAR_INSERIR")) {	
				prepararInserir(request, response);
			}
			
		} catch (Exception e) {
			System.out.println("Erro MarcaController. Mensagem: " + e.getMessage());
			preencheRetorno(request, response, Mensagens.ERRO_GENERICO, URLs.URL_ERRO_GENERICO);
		}
		
		return urlRetorno;
	}


	private void prepararInserir(HttpServletRequest request, HttpServletResponse response) throws IOException {
		preencheRetorno(request, response, null, URLs.URL_CADASTRAR_MARCA);
	}

	private void detalharMarca(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			
			MarcaBean marcaBean = new MarcaDAO().buscarPorId(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("marcaBean", marcaBean);
			preencheRetorno(request, response, null, URLs.URL_DETALHE_MARCA);
		} catch (Exception e) {
			throw e;
		}
	}

	private void excluirMarca(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			new MarcaDAO().excluir(Integer.parseInt(request.getParameter("id")));
			listarTodasMarcas(request, response);		
			preencheRetorno(request, response, "Marca excluido com sucesso", URLs.URL_ERRO_VALIDACAO_MARCA);
		} catch (Exception e) {
			throw e;
		}
	}

	private void listarTodasMarcas(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			List<MarcaBean> listaMarcas = new MarcaDAO().listaTodos();
			request.setAttribute("listaMarcas", listaMarcas);
			preencheRetorno(request, response, null, URLs.URL_ERRO_VALIDACAO_MARCA);
		} catch (Exception e) {
			throw e;
		}		
	}

	private void alterarMarca(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			MarcaBean marcaBean = retornaDadosMarca(request, true);
			new MarcaDAO().alterar(marcaBean);
			listarTodasMarcas(request, response);
			preencheRetorno(request, response, "Marca atualizado com sucesso", URLs.URL_ERRO_VALIDACAO_MARCA);
		} catch (Exception e) {
			throw e;
		}		
	}

	private void inserirMarca(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			MarcaBean marcaBean = retornaDadosMarca(request, false);
			new MarcaDAO().inserir(marcaBean);
			listarTodasMarcas(request, response);
			preencheRetorno(request, response, "Marca inserido com sucesso", URLs.URL_ERRO_VALIDACAO_MARCA);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public MarcaBean retornaDadosMarca(HttpServletRequest request, boolean pegaId) {
		int id = 0;
		
		if(pegaId)
			id = Integer.parseInt(request.getParameter("id"));
		
		String nome = request.getParameter("nome");
		
		MarcaBean marcaBean = new MarcaBean();
		marcaBean.setId(id);
		marcaBean.setNome(nome);
		return marcaBean;
	} 

	private void preencheRetorno(HttpServletRequest request, HttpServletResponse response, String mensagem, String url) throws IOException {
		if(mensagem!=null) {
			request.setAttribute("msg", mensagem);
		}

		urlRetorno = url;
	}
			
}