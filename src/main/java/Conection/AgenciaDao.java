package conection;


import java.util.List;

import agencia.Agencia;

import java.sql.SQLException;

public interface AgenciaDao {
	
	Agencia cadastrarAgencia (Agencia agencia) throws Exception;
	
	Agencia criarTabelaAgencia() throws Exception;
	
	Agencia encontrarPeloNome (String nome) throws SQLException;
	
	Agencia encontrarPeloCodigo (int codigo) throws SQLException;
	
	Agencia atualizarAgencia (int codigo) throws SQLException;
	
	Agencia removerAgencia (Agencia agencia) throws SQLException;
	
	List <Agencia> listarTodasAgencias() throws SQLException;
	
}
