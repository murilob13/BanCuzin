package agencia;

import java.sql.SQLException;
import java.util.List;

import exception.FalhaInsercaoException;

public interface AgenciaDao {
	
	Agencia cadastrarAgencia (Agencia agencia) throws FalhaInsercaoException;
	
	Agencia criarTabelaAgencia() throws Exception;
	
	Agencia encontrarPeloNome (String nome) throws SQLException;
	
	Agencia encontrarPeloCodigo (int codigo) throws SQLException;
	
	Agencia atualizarAgencia (int codigo) throws SQLException;
	
	Agencia removerAgencia (Agencia agencia) throws SQLException;
	
	List <Agencia> listarTodasAgencias() throws SQLException;
	
}
