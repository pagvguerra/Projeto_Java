package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.beans.MarcaBean;
import br.com.projeto.db.DB;
import br.com.projeto.interfaces.GenericDAO;

public class MarcaDAO implements GenericDAO<MarcaBean> {

	private static final String INSERIR_MARCA 			=	"INSERT INTO MARCA(NOME) VALUES(?)";
	private static final String ATUALIZAR_MARCA	 		=	"UPDATE MARCA SET NOME = ? WHERE ID = ?";
	private static final String EXCLUIR_MARCA 			=	"DELETE FROM MARCA WHERE ID = ?";
	private static final String BUSCA_MARCA_POR_ID 		=	"SELECT ID, NOME FROM MARCA WHERE ID = ?";
	private static final String LISTAR_TODAS_MARCAS 	=	"SELECT ID, NOME FROM MARCA";


	public MarcaBean buscarPorId(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		MarcaBean marcaBean 		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_MARCA_POR_ID);
			pstmt.setInt(1, id);
			rs		=	pstmt.executeQuery();
			
			if(rs.next()) {
				marcaBean			=	new MarcaBean();
				marcaBean.setId(rs.getInt("ID"));
				marcaBean.setNome(rs.getString("NOME"));
			}
		} catch (Exception e) {
			System.out.println("Erro no metodo getMarcaPorId. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return marcaBean;	
	}

	@Override
	public boolean inserir(MarcaBean obj) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(INSERIR_MARCA);
			pstmt.setString(1, obj.getNome());
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
	public boolean alterar(MarcaBean obj) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(ATUALIZAR_MARCA);
			pstmt.setString(1, obj.getNome());
			pstmt.setInt(2, obj.getId());
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
			pstmt	=	conn.prepareStatement(EXCLUIR_MARCA);
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
	public List<MarcaBean> listaTodos() {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		List<MarcaBean> listaMarcaBean =	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(LISTAR_TODAS_MARCAS);
			rs		=	pstmt.executeQuery();
			listaMarcaBean 		=	new ArrayList<MarcaBean>();
			
			while(rs.next()) {
				MarcaBean marcaBean			=	new MarcaBean();
				marcaBean.setId(rs.getInt("ID"));
				marcaBean.setNome(rs.getString("NOME"));
				listaMarcaBean.add(marcaBean);
			}
		} catch (Exception e) {
			System.out.println("Erro no metodo listaTodos. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return listaMarcaBean;
	}

}