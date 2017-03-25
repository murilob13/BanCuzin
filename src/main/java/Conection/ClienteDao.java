package Conection;

import java.awt.List;

import Cliente.Cliente;


public interface ClienteDao {
	
	Cliente criarCliente (String nome, String sobrenome);
	
	Cliente encontrarPeloNome (String nome);
	
	Cliente atualizarCliente (Cliente cliente);
	
	//List<Cliente> listarTodos();
	
	boolean removerCliente (Cliente cliente);

}
