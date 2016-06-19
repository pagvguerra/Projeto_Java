package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.beans.ProdutoBean;
import br.com.projeto.db.DB;
import br.com.projeto.interfaces.GenericDAO;

public class ProdutoDAO implements GenericDAO<ProdutoBean> {

	private static final String INSERIR_PRODUTO 				=	"INSERT INTO PRODUTO(NOME, QUANTIDADE, PRECO, FK_MARCA) VALUES(?, ?, ?, ?)";
	private static final String ATUALIZAR_PRODUTO	 			=	"UPDATE PRODUTO SET NOME = ?, QUANTIDADE = ?, PRECO = ?, FK_MARCA = ? WHERE ID = ?";
	private static final String EXCLUIR_PRODUTO 				=	"DELETE FROM PRODUTO WHERE ID = ?";
	private static final String BUSCA_PRODUTO_POR_ID 			=	"SELECT ID, NOME, QUANTIDADE, PRECO, FK_MARCA FROM PRODUTO WHERE ID = ?";
	private static final String LISTAR_TODOS_PRODUTOS 			=	"SELECT ID, NOME, QUANTIDADE, PRECO, FK_MARCA FROM PRODUTO";
	private static final String ATUALIZAR_QUANTIDADE_PRODUTO 	=	"UPDATE PRODUTO SET QUANTIDADE = ? WHERE ID = ?";
	
	@Override
	public boolean inserir(ProdutoBean obj) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(INSERIR_PRODUTO);
			pstmt.setString(1, obj.getNome());
			pstmt.setInt(2, obj.getQuantidade());
			pstmt.setInt(3, obj.getPreco());
			pstmt.setInt(4, obj.getMarcaBean().getId());
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
	public boolean alterar(ProdutoBean obj) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(ATUALIZAR_PRODUTO);
			pstmt.setString(1, obj.getNome());
			pstmt.setInt(2, obj.getQuantidade());
			pstmt.setInt(3, obj.getPreco());
			pstmt.setInt(4, obj.getMarcaBean().getId());
			pstmt.setInt(5, obj.getId());
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
			pstmt	=	conn.prepareStatement(EXCLUIR_PRODUTO);
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
	public ProdutoBean buscarPorId(int id) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		ProdutoBean produtoBean 	=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_PRODUTO_POR_ID);
			pstmt.setInt(1, id);
			rs		=	pstmt.executeQuery();
			
			if(rs.next()) {
				produtoBean			=	new ProdutoBean();
				produtoBean.setId(rs.getInt("ID"));
				produtoBean.setNome(rs.getString("NOME"));
				produtoBean.setPreco(rs.getInt("PRECO"));
				produtoBean.setQuantidade(rs.getInt("QUANTIDADE"));
				produtoBean.setMarcaBean(new MarcaDAO().buscarPorId(rs.getInt("FK_MARCA")));
			}
		} catch (Exception e) {
			System.out.println("Erro no metodo buscaPorId. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return produtoBean;	
	}

	@Override
	public List<ProdutoBean> listaTodos() {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		List<ProdutoBean> listaProdutoBean =	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(LISTAR_TODOS_PRODUTOS);
			rs		=	pstmt.executeQuery();
			listaProdutoBean 		=	new ArrayList<ProdutoBean>();
			
			while(rs.next()) {
				ProdutoBean produtoBean			=	new ProdutoBean();
				produtoBean.setId(rs.getInt("ID"));
				produtoBean.setNome(rs.getString("NOME"));
				produtoBean.setPreco(rs.getInt("PRECO"));
				produtoBean.setQuantidade(rs.getInt("QUANTIDADE"));
				produtoBean.setMarcaBean(new MarcaDAO().buscarPorId(rs.getInt("FK_MARCA")));
				listaProdutoBean.add(produtoBean);
			}
		} catch (Exception e) {
			System.out.println("Erro no metodo listaTodos. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return listaProdutoBean;
	}

	public void alterarQuantidade(ProdutoBean produtoBean) {
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(ATUALIZAR_QUANTIDADE_PRODUTO);
			pstmt.setInt(1, produtoBean.getQuantidade());
			pstmt.setInt(2, produtoBean.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro no metodo alterarQuantidade. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
	}

}