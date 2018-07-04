package conta;

import java.sql.SQLException;
import java.util.List;

public interface ContaDao {

	public void criarTabelaConta() throws SQLException;

	Conta cadastrarConta(Conta conta) throws SQLException;

	Conta encontrarPeloNumero(String numeroParaBuscar) throws SQLException, Exception;

	Conta encontrarPeloNome(String nomeParaBuscar) throws SQLException;
	
	Conta encontrarPeloNumECodigoAg(int codAgencia, String numConta) throws SQLException;

	Conta atualizarConta(String numConta, int codAgencia, Conta conta) throws SQLException;

	Conta removerConta(String numConta, int codAgencia) throws SQLException;

	List<Conta> listarTodasContas() throws SQLException;

}
