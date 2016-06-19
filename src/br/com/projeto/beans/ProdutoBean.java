package br.com.projeto.beans;

import java.io.Serializable;

public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = -2454846094167932075L;

	private int id;
	private String nome;
	private int quantidade;
	private int preco;
	private MarcaBean marcaBean;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getPreco() {
		return preco;
	}
	public void setPreco(int preco) {
		this.preco = preco;
	}
	public MarcaBean getMarcaBean() {
		return marcaBean;
	}
	public void setMarcaBean(MarcaBean marcaBean) {
		this.marcaBean = marcaBean;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((marcaBean == null) ? 0 : marcaBean.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + preco;
		result = prime * result + quantidade;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoBean other = (ProdutoBean) obj;
		if (id != other.id)
			return false;
		if (marcaBean == null) {
			if (other.marcaBean != null)
				return false;
		} else if (!marcaBean.equals(other.marcaBean))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (preco != other.preco)
			return false;
		if (quantidade != other.quantidade)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ProdutoBean [id=" + id + ", nome=" + nome + ", quantidade="
				+ quantidade + ", preco=" + preco + ", marcaBean=" + marcaBean
				+ "]";
	}
}