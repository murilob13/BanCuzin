package Conection;

import Agencia.Agencia;

public interface AgenciaDao {
	
	Agencia cadastrarAgencia (String nome, String sobrenome);
	
	Agencia encontrarPeloNome (String nome);
	
	Agencia encontrarPeloCodigo (int codigo);
	
	Agencia atualizarAgencia (Agencia agencia);
	
	//List<Cliente> listarTodos();
	
	boolean removerAgencia (Agencia agencia);

}
