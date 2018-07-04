package financiamento;

import java.sql.SQLException;
import java.util.List;

public interface EmprestimoDao {

	public void criarTabelaEmprestimo() throws SQLException;

	Emprestimo cadastrarEmprestimo(Emprestimo emprestimo) throws SQLException;

	Emprestimo encontrarPeloCpfCnpg(String cpfCnpjParaBuscar) throws SQLException;

	Emprestimo encontrarPeloNome(String nomeParaBuscar) throws SQLException;

	Emprestimo atualizarEmprestimo(String cpfCnpj, Emprestimo emprestimo) throws SQLException;

	Emprestimo removerEmprestimo(String cpfCnpj) throws SQLException;

	List<Emprestimo> listarTodosEmprestimos() throws SQLException;
}
