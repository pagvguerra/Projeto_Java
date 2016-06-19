package br.com.projeto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.projeto.beans.AutenticacaoBean;
import br.com.projeto.beans.EmpregadoBean;
import br.com.projeto.db.DB;

public class AutenticacaoDao {

	private static final String BUSCA_USUARIO_POR_LOGIN_E_SENHA_INFORMADOS	=	"SELECT NOME, PERFIL FROM EMPREGADO WHERE LOGIN = ? AND SENHA = ?";
	
	public EmpregadoBean existeUsuarioPorLoginESenhaInformados(AutenticacaoBean entidade) 
	{		
		Connection conn				=	null;
		PreparedStatement pstmt		=	null;
		ResultSet rs				=	null;
		
		EmpregadoBean empregadoBean =	null;
		
		try {
			conn	=	DB.getMyqslConnection();
			pstmt	=	conn.prepareStatement(BUSCA_USUARIO_POR_LOGIN_E_SENHA_INFORMADOS);
			pstmt.setString(1, entidade.getLogin());
			pstmt.setString(2, entidade.getSenha());
			rs		=	pstmt.executeQuery();
			
			if(rs.next()) {
				empregadoBean		=	new EmpregadoBean();
				empregadoBean.setNome(rs.getString("NOME"));
				empregadoBean.setSenha(entidade.getSenha());
				empregadoBean.setPerfil(rs.getString("PERFIL"));
			}
			
		} catch (Exception e) {
			System.out.println("Erro no metodo existeUsuarioPorLoginESenhaInformados. Pilha: " + e.getMessage());
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return empregadoBean;
	}
	
}