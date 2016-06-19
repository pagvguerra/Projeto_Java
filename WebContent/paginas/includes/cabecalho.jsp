<table width="100%">
	<tr>
		<td><b>Nome..:</b>    ${empregado.nome}</td>
		<td>
			<c:if test="${empregado.perfil == 'USU'}">
				<b>Perfil..:</b>   Funcionário
			</c:if>
			<c:if test="${empregado.perfil == 'ADM'}">
				<b>Perfil..:</b>   Administrador
			</c:if>
		</td>
		<td><a href="http://localhost:8080/Projeto_Java/servlet/AutenticacaoController?acao=LOGOUT" id="deslogar">SAIR</a></td>
	</tr>
</table>
<br /><br />