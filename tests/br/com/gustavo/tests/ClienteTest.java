package br.com.gustavo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.gustavo.dao.ClienteDAO;
import br.com.gustavo.dao.IClienteDAO;
import br.com.gustavo.domain.Cliente;

public class ClienteTest {

	@Test
	public void cadastrarTest() throws Exception {
		IClienteDAO dao = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("01");
		cliente.setNome("Gustavo Dias");
		
		Integer qtd = dao.cadastrar(cliente);
				assertTrue(qtd == 1);
				
		Cliente clienteBD = dao.consultar(cliente.getCodigo());
		assertNotNull(clienteBD);
		assertNotNull(clienteBD.getId());
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		
		Integer qtdDel = dao.excluir(clienteBD);
		assertNotNull(qtdDel);
	}
	
	@Test
	public void atualizarTest()  throws Exception {
	    ClienteDAO dao = new ClienteDAO();
	    
	    // Cadastrar um novo cliente
	    Cliente clienteUm = new Cliente();
	    clienteUm.setCodigo("10");
	    clienteUm.setNome("Gustavo Dias");
	    Integer cadastrandoCliente = dao.cadastrar(clienteUm);
	    assertTrue(cadastrandoCliente == 1);
	    
	    // Consultar o cliente cadastrado
	    Cliente clienteBD = dao.consultar("10");
	    assertNotNull(clienteBD);
	    assertNotNull(clienteBD.getId());
	    assertEquals(clienteUm.getCodigo(), clienteBD.getCodigo());
	    assertEquals(clienteUm.getNome(), clienteBD.getNome());
	    
	    // Atualizar o cliente (usando o ID do clienteBD)
	    clienteBD.setCodigo("20");
	    clienteBD.setNome("Nome2");
	    Integer countUpdate = dao.atualizar(clienteBD);
	    assertTrue(countUpdate == 1);

	    // Re-consultar para garantir que o cliente com código "10" foi atualizado
	    Cliente clienteBD1 = dao.consultar("10");
	    assertNull("O cliente com o código '10' deveria ter sido atualizado e não existir mais", clienteBD1);
	    
	    Cliente clienteBD2 = dao.consultar("20");
	    assertNotNull("O cliente com o código '20' deveria existir após o update", clienteBD2);
	    assertEquals(clienteBD.getId(), clienteBD2.getId());
	    assertEquals(clienteBD.getCodigo(), clienteBD2.getCodigo());
	    assertEquals(clienteBD.getNome(), clienteBD2.getNome());
		
		List<Cliente> list = dao.buscarTodos();
		for (Cliente cli : list) {
			dao.excluir(cli);
				
		}

	}
	
	@Test
	public void buscarTodosTest() throws Exception {
		ClienteDAO clienteDAO = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("10");
		cliente.setNome("Gustavo Dias");
		Integer countCad = clienteDAO.cadastrar(cliente);
		assertTrue(countCad == 1);
		
		Cliente clientes = new Cliente();
		clientes.setCodigo("20");
		clientes.setNome("TestandoAtroca");
		Integer countCad2 = clienteDAO.cadastrar(clientes);
		assertTrue(countCad2 == 1);
		
		List<Cliente> list = clienteDAO.buscarTodos();
		assertNotNull(list);
		assertEquals(2, list.size());
		
		int countDel = 0;
		for (Cliente cli : list) {
			clienteDAO.excluir(cli);
			countDel++;
		}
		assertEquals(list.size(), countDel);
		
		list = clienteDAO.buscarTodos();
		assertEquals(list.size(), 0);
		
	}
	

}
