package br.com.projeto.business;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projeto.beans.AutenticacaoBean;
import br.com.projeto.beans.EmpregadoBean;
import br.com.projeto.daos.AutenticacaoDao;
import br.com.projeto.resources.Mensagens;
import br.com.projeto.resources.URLs;
import br.com.projeto.utils.Util;

public class AutenticacaoBunisess {

	private static final int TAM_CAMPO_LOGIN = 50;
	private static final int TAM_CAMPO_SENHA = 100;

	private static final String CAMPO_LOGIN = "LOGIN";
	private static final String CAMPO_SENHA = "SENHA";
	
	private static final String TAM_CAMPO_LOGIN_STR = "50";
	private static final String TAM_CAMPO_SENHA_STR = "100";
	
	private static AutenticacaoBunisess instance = null;
	private String urlRetorno = "";

	public static AutenticacaoBunisess getInstance() {
		if ( instance == null ) {
			instance = new AutenticacaoBunisess();
		}
		return instance;
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try { 

			String acao = request.getParameter("acao");
				
			if(Util.isEmpty(acao)) {
				System.out.println("Ação nao definida");
				preencheRetorno(request, response, Mensagens.ERRO_GENERICO, URLs.URL_ERRO_GENERICO);
			} else if(acao.equalsIgnoreCase("LOGIN")) {
				login(request, response);
			} else if(acao.equalsIgnoreCase("LOGOUT")) {
				logout(request, response);
			} else if(acao.equalsIgnoreCase("ENTRADA")) {
				preencheRetorno(request, response, null, URLs.URL_ENTRADA);	
			}
			
		} catch (Exception e) {
			System.out.println("Erro. Mensagem: " + e.getMessage());
			preencheRetorno(request, response, Mensagens.ERRO_GENERICO, URLs.URL_ERRO_GENERICO);
		}	
		
		return urlRetorno;
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AutenticacaoBean autenticacaoBean =	null;

		try {
			
			String login = request.getParameter(CAMPO_LOGIN);
			if(!validar(request, response, login, Mensagens.LOGIN_VAZIO, Mensagens.MAXIMO_CARACTERES_CAMPO.replace("?", CAMPO_LOGIN).replace("#", TAM_CAMPO_LOGIN_STR), TAM_CAMPO_LOGIN))
				return;
			
			String senha = request.getParameter(CAMPO_SENHA);
			if(!validar(request, response, senha, Mensagens.SENHA_VAZIO, Mensagens.MAXIMO_CARACTERES_CAMPO.replace("?", CAMPO_SENHA).replace("#", TAM_CAMPO_SENHA_STR), TAM_CAMPO_SENHA))
				return;
			
			autenticacaoBean = new AutenticacaoBean();
			autenticacaoBean.setLogin(login);
			autenticacaoBean.setSenha(Util.criptografa(senha));
			
			EmpregadoBean empregadoBean = new AutenticacaoDao().existeUsuarioPorLoginESenhaInformados(autenticacaoBean);
			
			if(empregadoBean!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("empregado", empregadoBean);
				preencheRetorno(request, response, null, URLs.URL_LOGIN);
			} else {
				preencheRetorno(request, response, Mensagens.USUARIO_NAO_ENCONTRADO, URLs.URL_ERRO_VALIDACAO);
			}
			
		} catch (Exception e) {
			throw e;
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.getSession().setAttribute("empregado", null);
		request.getSession().invalidate();
		preencheRetorno(request, response, null, URLs.URL_LOGOUT);
	}
	
	public boolean validar(HttpServletRequest request, HttpServletResponse response, String campo, String msgCampoVazio, String msgTamanhoMinimo, int tam) throws IOException {
		if(Util.isEmpty(campo)) {
			preencheRetorno(request, response, msgCampoVazio, URLs.URL_ERRO_VALIDACAO);
			return false;
		}
		if(campo.length() >= tam) {
			preencheRetorno(request, response, msgTamanhoMinimo, URLs.URL_ERRO_VALIDACAO);
			return false;
		}
		return true;
	}

	private void preencheRetorno(HttpServletRequest request, HttpServletResponse response, String mensagem, String url) throws IOException {
		if(mensagem!=null) {
			request.setAttribute("msg", mensagem);
		}
		urlRetorno = url;
	}
	
}