package financiamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import cliente.ClienteDaoJdbcImpl;
import connection.Conexao;

public class EmprestimoDaoJdbcImpl implements EmprestimoDao {

	ClienteDaoJdbcImpl clienteDao = new ClienteDaoJdbcImpl();

	@Override
	public void criarTabelaEmprestimo() throws SQLException {
		String query = "CREATE TABLE IF NOT EXISTS emprestimo (idemprestimo SERIAL NOT NULL PRIMARY KEY, idcliente int NOT NULL references cliente (idcliente), tipoemprestimo varchar (20) NOT NULL, limite numeric NOT NULL);";

		try {
			Connection connection = Conexao.getConnection();

			PreparedStatement psCreat = connection.prepareStatement(query);
			psCreat.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public Emprestimo cadastrarEmprestimo(Emprestimo emprestimo) throws SQLException {
		Connection connection = null;
		PreparedStatement psInserir = null;
		
		criarTabelaEmprestimo();
		
		String query = "INSERT INTO emprestimo (idcliente, tipoemprestimo, valor) VALUES (?, ?, ?);";

		try {
			connection = Conexao.getConnection();
			psInserir = connection.prepareStatement(query);

			psInserir.setInt(1, emprestimo.getCliente().getIdCliente());
			psInserir.setString(2, emprestimo.getTipoBem().getTipoBem());
			psInserir.setDouble(3, emprestimo.getValor());

			psInserir.executeUpdate();
			return emprestimo;
		} catch (Exception e) {
			System.err.println("Falha ao inserir emprestimo " + e);
			return null;
		} finally {
			DbUtils.closeQuietly(psInserir);
		}

	}

	@Override
	public Emprestimo encontrarPeloCpfCnpg(String cpfCnpjParaBuscar) throws SQLException {
		Emprestimo emprestimo = new Emprestimo();
		Connection connection = null;
		PreparedStatement psSelect = null;
		String query = "SELECT idemprestimo, e.idcliente, tipoemprestimo, valor FROM emprestimo e INNER JOIN cliente clt ON  e.idcliente = clt.idcliente AND clt.cpfcnpj = ?";

		try {
			connection = Conexao.getConnection();
			psSelect = connection.prepareStatement(query);
			psSelect.setString(1, cpfCnpjParaBuscar);

			ResultSet rs = psSelect.executeQuery();

			while (rs.next()) {
				emprestimo.setIdEmprestimo(rs.getInt("idemprestimo"));
				emprestimo.setCliente(clienteDao.encontrarPeloId(rs.getInt("idcliente")));
				emprestimo.setTipoBem(TipoEmprestimo.valueOf(rs.getString("tipoemprestimo")));
				emprestimo.setValor(rs.getDouble("valor"));
			}
			return emprestimo;

		} catch (Exception e) {
			System.err.println("Deu ruim na hora de buscar o emprestimo" + e);
			return null;
		} finally {
			DbUtils.closeQuietly(psSelect);
		}
	}

	@Override
	public Emprestimo encontrarPeloNome(String nomeParaBuscar) throws SQLException {
		Emprestimo emprestimo = new Emprestimo();
		Connection connection = null;
		PreparedStatement psSelect = null;
		String query = "SELECT idemprestimo, e.idcliente, tipoemprestimo, valor FROM emprestimo e INNER JOIN cliente clt ON  e.idcliente = clt.idcliente AND clt.nome = ?";

		try {
			connection = Conexao.getConnection();
			psSelect = connection.prepareStatement(query);
			psSelect.setString(1, nomeParaBuscar);

			ResultSet rs = psSelect.executeQuery();

			while (rs.next()) {
				emprestimo.setIdEmprestimo(rs.getInt("idemprestimo"));
				emprestimo.setCliente(clienteDao.encontrarPeloId(rs.getInt("idcliente")));
				emprestimo.setTipoBem(TipoEmprestimo.valueOf(rs.getString("tipoemprestimo")));
				emprestimo.setValor(rs.getDouble("valor"));
			}
			return emprestimo;

		} catch (Exception e) {
			System.err.println("Falha na hora de buscar o emprestimo" + e);
			return null;
		} finally {
			DbUtils.closeQuietly(psSelect);
		}
	}

	public Emprestimo atualizarEmprestimo(String cpfCnpj, Emprestimo emprestimo) throws SQLException {

		Connection connection = null;
		PreparedStatement psUpdate = null;
		String query = "UPDATE emprestimo SET idcliente = ?, tipoemprestimo = ?, valor = ? WHERE idemprestimo IN (SELECT e.idemprestimo FROM emprestimo e INNER JOIN cliente c ON e.idcliente = c.idcliente WHERE c.cpfcnpj = ?)";

		try {
			connection = Conexao.getConnection();
			psUpdate = connection.prepareStatement(query);

			psUpdate.setInt(1, emprestimo.getCliente().getIdCliente());
			psUpdate.setString(2, emprestimo.getTipoBem().getTipoBem());
			psUpdate.setDouble(3, emprestimo.getValor());

			psUpdate.setString(4, cpfCnpj);
			psUpdate.executeUpdate();
			return emprestimo;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			DbUtils.closeQuietly(psUpdate);
		}
	}

	public Emprestimo removerEmprestimo(String cpfCnpj) throws SQLException {
		Emprestimo emprestimo = new Emprestimo();
		Connection connection = null;
		PreparedStatement psDelete = null;
		String query = "DELETE FROM emprestimo WHERE idemprestimo IN (SELECT e.idemprestimo FROM emprestimo e INNER JOIN cliente c ON e.idcliente = c.idcliente WHERE c.cpfcnpj = ?)";

		try {
			connection = Conexao.getConnection();
			psDelete = connection.prepareStatement(query);

			psDelete.setString(1, cpfCnpj);
			psDelete.executeUpdate();
			return emprestimo;
		} catch (Exception e) {
			System.err.println("Falha ao remover emprestimo. " + e);
			return null;
		} finally {
			DbUtils.closeQuietly(psDelete);
		}
	}

	public List<Emprestimo> listarTodosEmprestimos() throws SQLException {
		Connection connection = null;
		PreparedStatement psList = null;
		String query = "SELECT * FROM emprestimo";

		try {
			List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
			connection = Conexao.getConnection();
			psList = connection.prepareStatement(query);

			ResultSet rs = psList.executeQuery();

			while (rs.next()) {
				Emprestimo emprestimo = new Emprestimo();

				emprestimo.setIdEmprestimo(rs.getInt("idemprestimo"));
				emprestimo.setCliente(clienteDao.encontrarPeloId(rs.getInt("idcliente")));
				emprestimo.setTipoBem(TipoEmprestimo.valueOf(rs.getString("tipoemprestimo")));
				emprestimo.setValor(rs.getDouble("valor"));
				emprestimos.add(emprestimo);
			}
			return emprestimos;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		} finally {
			DbUtils.closeQuietly(psList);
		}
	}

}
