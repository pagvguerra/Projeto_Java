package br.com.projeto.business;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.beans.EmpregadoBean;
import br.com.projeto.daos.EmpregadoDAO;
import br.com.projeto.resources.Mensagens;
import br.com.projeto.resources.URLs;
import br.com.projeto.utils.Util;

public class EmpregadoBusiness {

	private static EmpregadoBusiness instance = null;
	private String urlRetorno = "";

	public static EmpregadoBusiness getInstance() {
		if ( instance == null ) {
			instance = new EmpregadoBusiness();
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
				inserirEmpregado(request, response);
			} else if(acao.equalsIgnoreCase("ALTERAR")) {
				alterarEmpregado(request, response);
			} else if(acao.equalsIgnoreCase("EXCLUIR")) {
				excluirEmpregado(request, response);
			} else if(acao.equalsIgnoreCase("DETALHAR_EMPREGADO")) {
				detalharEmpregado(request, response);
			} else if(acao.equalsIgnoreCase("LISTAR_TODOS")) {
				listarTodosEmpregados(request, response);
				preencheRetorno(request, response, null, URLs.URL_ERRO_VALIDACAO_EMP);
			} else if(acao.equalsIgnoreCase("PREPARAR_INSERIR")) {	
				prepararInserir(request, response);
			}
		
		} catch (Exception e) {
			System.out.println("Erro. Mensagem: " + e.getMessage());
			preencheRetorno(request, response, Mensagens.ERRO_GENERICO, URLs.URL_ERRO_GENERICO);
		}	

		return urlRetorno;
	}
	
	private void prepararInserir(HttpServletRequest request, HttpServletResponse response) throws IOException {
		preencheRetorno(request, response, null, URLs.URL_CADASTRAR_EMP);
	}
	
	private void detalharEmpregado(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			EmpregadoBean empregadoBean = new EmpregadoDAO().buscarPorId(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("empregadoBean", empregadoBean);
			preencheRetorno(request, response, null, "/paginas/empregados/detalheEmpregado.jsp");
		} catch (Exception e) {
			throw e;
		}
	}

	private void excluirEmpregado(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			new EmpregadoDAO().excluir(Integer.parseInt(request.getParameter("id")));
			preencheRetorno(request, response, "Empregado excluido com sucesso", URLs.URL_ERRO_VALIDACAO_EMP);
			listarTodosEmpregados(request, response);		
		} catch (Exception e) {
			throw e;
		}
	}

	private void listarTodosEmpregados(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			List<EmpregadoBean> listaEmpregados = new EmpregadoDAO().listaTodos();
			request.setAttribute("listaEmpregados", listaEmpregados);
		} catch (Exception e) {
			throw e;
		}		
	}

	private void alterarEmpregado(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			EmpregadoBean empregadoBean = retornaDadosEmpregado(request, true);
			empregadoBean.setSenha(Util.criptografa(empregadoBean.getSenha()));
			new EmpregadoDAO().alterar(empregadoBean);
			preencheRetorno(request, response, "Empregado atualizado com sucesso", URLs.URL_ERRO_VALIDACAO_EMP);
			listarTodosEmpregados(request, response);
		} catch (Exception e) {
			throw e;
		}		
	}

	private void inserirEmpregado(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			EmpregadoBean empregadoBean = retornaDadosEmpregado(request, false);
			empregadoBean.setSenha(Util.criptografa(empregadoBean.getSenha()));
			new EmpregadoDAO().inserir(empregadoBean);
			preencheRetorno(request, response, "Empregado inserido com sucesso", URLs.URL_ERRO_VALIDACAO_EMP);
			listarTodosEmpregados(request, response);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public EmpregadoBean retornaDadosEmpregado(HttpServletRequest request, boolean pegaId) {
		int id = 0;
		
		if(pegaId)
			id = Integer.parseInt(request.getParameter("id"));
		
		String cpf = request.getParameter("cpf");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String nome = request.getParameter("nome");
		String perfil = request.getParameter("perfil");
		String rg= request.getParameter("rg");
		String senha = request.getParameter("senha");
		String sexo = request.getParameter("sexo");
		
		EmpregadoBean empregadoBean = new EmpregadoBean();
		empregadoBean.setId(id);
		empregadoBean.setCpf(cpf);
		empregadoBean.setEmail(email);
		empregadoBean.setLogin(login);
		empregadoBean.setNome(nome);
		empregadoBean.setPerfil(Util.isEmpty(perfil)?"USU":"ADM");
		empregadoBean.setRg(rg);
		empregadoBean.setSenha(senha);
		empregadoBean.setSexo(sexo);
		
		return empregadoBean;
	} 

	private void preencheRetorno(HttpServletRequest request, HttpServletResponse response, String mensagem, String url) throws IOException {
		if(mensagem!=null) {
			request.setAttribute("msg", mensagem);
		}

		urlRetorno = url;
	}
	
}