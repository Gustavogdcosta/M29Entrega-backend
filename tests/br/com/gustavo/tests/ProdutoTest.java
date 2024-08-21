package br.com.gustavo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.gustavo.dao.IProdutoDAO;
import br.com.gustavo.dao.ProdutoDAO;
import br.com.gustavo.domain.Produto;

public class ProdutoTest {

	@Test
	public void cadastrarTest() throws Exception {
		IProdutoDAO dao = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setValor("100");
		produto.setItem("Camisa Polo");
		
		Integer qtd = dao.cadastrar(produto);
				assertTrue(qtd == 1);
				
		Produto produtoBD = dao.consultar(produto.getValor());
		assertNotNull(produtoBD);
		assertNotNull(produtoBD.getId());
		assertEquals(produto.getValor(), produtoBD.getValor());
		assertEquals(produto.getItem(), produtoBD.getItem());
		
		Integer qtdDel = dao.excluir(produtoBD);
		assertNotNull(qtdDel);
	}
	
	@Test
	public void atualizarTest()  throws Exception {
	    ProdutoDAO dao = new ProdutoDAO();
	    
	    // Cadastrar um novo cliente
	    Produto produtoUm = new Produto();
	    produtoUm.setValor("100");
	    produtoUm.setItem("Camisa polo");
	    Integer cadastrandoProduto = dao.cadastrar(produtoUm);
	    assertTrue(cadastrandoProduto == 1);
	    
	    // Consultar o cliente cadastrado
	    Produto produtoBD = dao.consultar("100");
	    assertNotNull(produtoBD);
	    assertNotNull(produtoBD.getId());
	    assertEquals(produtoUm.getValor(), produtoBD.getValor());
	    assertEquals(produtoUm.getItem(), produtoBD.getItem());
	    
	    // Atualizar o cliente (usando o ID do clienteBD)
	    produtoBD.setValor("200");
	    produtoBD.setItem("Calca Jeans");
	    Integer countUpdate = dao.atualizar(produtoBD);
	    assertTrue(countUpdate == 1);

	    // Re-consultar para garantir que o cliente com código "10" foi atualizado
	    Produto produtoBD1 = dao.consultar("100");
	    assertNull("O cliente com o código '100' deveria ter sido atualizado e não existir mais", produtoBD1);
	    
	    Produto produtoBD2 = dao.consultar("200");
	    assertNotNull("O cliente com o código '200' deveria existir após o update", produtoBD2);
	    assertEquals(produtoBD.getId(), produtoBD2.getId());
	    assertEquals(produtoBD.getValor(), produtoBD2.getValor());
	    assertEquals(produtoBD.getItem(), produtoBD2.getItem());
		
		List<Produto> list = dao.buscarTodos();
		for (Produto cli : list) {
			dao.excluir(cli);
				
		}

	}
	
	@Test
	public void buscarTodosTest() throws Exception {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setValor("100");
		produto.setItem("Camisa Polo");
		Integer countCad = produtoDAO.cadastrar(produto);
		assertTrue(countCad == 1);
		
		Produto produtos = new Produto();
		produtos.setValor("200");
		produtos.setItem("Trocando para Calça Jeans");
		Integer countCad2 = produtoDAO.cadastrar(produtos);
		assertTrue(countCad2 == 1);
		
		List<Produto> list = produtoDAO.buscarTodos();
		assertNotNull(list);
		assertEquals(2, list.size());
		
		int countDel = 0;
		for (Produto cli : list) {
			produtoDAO.excluir(cli);
			countDel++;
		}
		assertEquals(list.size(), countDel);
		
		list = produtoDAO.buscarTodos();
		assertEquals(list.size(), 0);
		
	}	

}
