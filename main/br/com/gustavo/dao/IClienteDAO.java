package br.com.gustavo.dao;

import java.util.List;

import br.com.gustavo.domain.Cliente;

public interface IClienteDAO {

	public Integer cadastrar (Cliente cliente) throws Exception;

	public Cliente consultar(String valor) throws Exception;

	public Integer excluir(Cliente clienteBD) throws Exception;
	
	public Integer atualizar (Cliente cliente) throws Exception;
	
	public List<Cliente> buscarTodos() throws Exception;
}
