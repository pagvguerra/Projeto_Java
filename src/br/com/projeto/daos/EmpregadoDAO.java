package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.beans.EmpregadoBean;
import br.com.projeto.db.DB;
import br.com.projeto.interfaces.GenericDAO;

public class EmpregadoDAO implements GenericDAO<EmpregadoBean> {

	private static final String INSERIR_EMPREGADO 		=	"INSERT INTO EMPREGADO(CPF, EMAIL, LOGIN, NOME, PERFIL, RG, SENHA, SEXO) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String ATUALIZAR_EMPREGADO 	=	"UPDATE EMPREGADO SET CPF = ?, EMAIL = ?, LOGIN = ?, NOME = ?, PERFIL = ?, RG = ?, SEXO = ?, SENHA = ? WHERE ID = ?";
	private static final String EXCLUIR_EMPREGADO 		=	"DELETE FROM EMPREGADO WHERE ID = ?";
	private static final String BUSCA_USUARIO_POR_ID 	=	"SELECT ID, CPF, EMAIL, LOGIN, NOME, PERFIL, RG, SENHA, SEXO FROM EMPREGADO WHERE ID = ?";
	private static final String LISTAR_TODOS_USUARIOS 	=	"SELECT ID, CPF, EMAIL, LOGIN, NOME, PERFIL, RG, SENHA, SEXO FROM EMPREGADO";
	
	@Override
	public boolean inserir(EmpregadoBean obj) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(INSERIR_EMPREGADO);
			pstmt.setString(1, obj.getCpf());
			pstmt.setString(2, obj.getEmail());
			pstmt.setString(3, obj.getLogin());					
			pstmt.setString(4, obj.getNome());
			pstmt.setString(5, obj.getPerfil());
			pstmt.setString(6, obj.getRg());
			pstmt.setString(7, obj.getSenha());
			pstmt.setString(8, obj.getSexo());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro no metodo inserir. Pilha: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			DB.close(conn, pstmt, null);
		}
		return true;
	}

	@Override
	public boolean alterar(EmpregadoBean obj) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(ATUALIZAR_EMPREGADO);
			pstmt.setString(1, obj.getCpf());
			pstmt.setString(2, obj.getEmail());
			pstmt.setString(3, obj.getLogin());					
			pstmt.setString(4, obj.getNome());
			pstmt.setString(5, obj.getPerfil());
			pstmt.setString(6, obj.getRg());
			pstmt.setString(7, obj.getSexo());
			pstmt.setString(8, obj.getSenha());
			pstmt.setInt(9, obj.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro no metodo alterar. Pilha: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return true;
	}

	@Override
	public boolean excluir(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(EXCLUIR_EMPREGADO);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro no metodo excluir. Pilha: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			DB.close(conn, pstmt, null);
		}
		return true;	
	}

	@Override
	public EmpregadoBean buscarPorId(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		EmpregadoBean empregadoBean =	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_USUARIO_POR_ID);
			pstmt.setInt(1, id);
			rs		=	pstmt.executeQuery();
			
			if(rs.next()) {
				empregadoBean		=	new EmpregadoBean();
				empregadoBean.setId(rs.getInt("ID"));
				empregadoBean.setCpf(rs.getString("CPF"));
				empregadoBean.setNome(rs.getString("NOME"));
				empregadoBean.setLogin(rs.getString("LOGIN"));
				empregadoBean.setSenha(rs.getString("SENHA"));
				empregadoBean.setEmail(rs.getString("EMAIL"));
				empregadoBean.setSexo(rs.getString("SEXO"));
				empregadoBean.setPerfil(rs.getString("PERFIL"));
				empregadoBean.setRg(rs.getString("RG"));
			}
		} catch (Exception e) {
			System.out.println("Erro no metodo buscaPorId. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return empregadoBean;	
	}

	@Override
	public List<EmpregadoBean> listaTodos() {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		List<EmpregadoBean> listaEmpregadoBean =	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(LISTAR_TODOS_USUARIOS);
			rs		=	pstmt.executeQuery();
			listaEmpregadoBean 		=	new ArrayList<EmpregadoBean>();
			
			while(rs.next()) {
				EmpregadoBean empregadoBean		=	new EmpregadoBean();
				empregadoBean.setId(rs.getInt("ID"));
				empregadoBean.setCpf(rs.getString("CPF"));
				empregadoBean.setNome(rs.getString("NOME"));
				empregadoBean.setLogin(rs.getString("LOGIN"));
				empregadoBean.setSenha(rs.getString("SENHA"));
				empregadoBean.setEmail(rs.getString("EMAIL"));
				empregadoBean.setSexo(rs.getString("SEXO"));
				empregadoBean.setPerfil(rs.getString("PERFIL"));
				empregadoBean.setRg(rs.getString("RG"));
				listaEmpregadoBean.add(empregadoBean);
			}
		} catch (Exception e) {
			System.out.println("Erro no metodo listaTodos. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return listaEmpregadoBean;
	}

}