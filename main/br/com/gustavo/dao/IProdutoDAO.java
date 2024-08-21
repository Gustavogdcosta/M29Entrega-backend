package br.com.gustavo.dao;

import java.util.List;

import br.com.gustavo.domain.Produto;

public interface IProdutoDAO {

	public Integer cadastrar (Produto produto) throws Exception;

	public Produto consultar(String codigo) throws Exception;

	public Integer excluir(Produto produtoBD) throws Exception;
	
	public Integer atualizar (Produto produto) throws Exception;
	
	public List<Produto> buscarTodos() throws Exception;


}
