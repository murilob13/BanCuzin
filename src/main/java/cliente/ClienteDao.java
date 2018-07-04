package cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDao {

	public void criarTabelaCliente() throws SQLException;

	Cliente cadastrarCliente(Cliente cliente) throws SQLException;

	Cliente encontrarPeloId(int idCliente) throws SQLException;
	
	Cliente encontrarPeloNome(String nomeParaBuscar) throws SQLException;

	Cliente encontrarPeloCpfCnpj(String cpfCnpjParaBuscar) throws SQLException;
	
	Cliente encontrarPeloNomeECpfCnpj(String nomeParaBuscar, String cpfCnpjParaBuscar) throws SQLException;

	Cliente atualizarCliente(String cpfCnpj, Cliente cliente) throws SQLException;

	Cliente removerCliente(Cliente cliente) throws SQLException;

	List<Cliente> listarTodosClientes() throws SQLException;

}
