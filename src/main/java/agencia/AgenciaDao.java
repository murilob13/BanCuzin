package agencia;

import java.sql.SQLException;
import java.util.List;

import exception.FalhaInsercaoException;

//import exception.FalhaInsercaoException;
/**
 * 
 * @author Marcos
 * @author Murilo
 * 
 * Interface da AgenciaDaoJdbcImpl
 *
 */
public interface AgenciaDao {
	
	public void criarTabelaAgencia() throws Exception;
	
	Agencia cadastrarAgencia (Agencia agencia) throws FalhaInsercaoException;
	
	Agencia encontrarPeloId (int idAgencia) throws SQLException;
	
	Agencia encontrarPeloNome (String nome) throws SQLException;
	
	Agencia encontrarPeloCodigo (int codigo) throws SQLException;
	
	Agencia atualizarAgencia (int codigo, Agencia agencia) throws SQLException;
	
	Agencia removerAgencia (Agencia agencia) throws SQLException;
	
	List <Agencia> listarTodasAgencias() throws SQLException;
	
}
