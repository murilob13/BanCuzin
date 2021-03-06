package agencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import connection.Conexao;
import exception.FalhaInsercaoException;
//import exception.FalhaInsercaoException;
/**
 * 
 * @author Marcos
 * @author Murilo
 * 
 * @param criarTabelaAgencia reponsavel por criar a tabela agencia se ja nao existir.
 *
 */
public class AgenciaDaoJdbcImpl implements AgenciaDao {

	public void criarTabelaAgencia() throws Exception {

		String query = "CREATE TABLE IF NOT EXISTS agencia (idAgencia SERIAL NOT NULL PRIMARY KEY, nome varchar(255) NOT NULL, codigo int NOT NULL UNIQUE, endereco varchar(255) NOT NULL, gerente varchar(255) NOT NULL);";

		try {

			Connection connection = Conexao.getConnection();

			PreparedStatement psCreate = connection.prepareStatement(query);
			psCreate.executeUpdate();

		} catch (Exception e) {
			System.err.println(e);
		}

	}

	public Agencia cadastrarAgencia(Agencia agencia) throws FalhaInsercaoException {

		Connection connection = null;
		PreparedStatement psInserir = null;
		String query = "INSERT INTO agencia (nome, codigo, endereco, gerente) VALUES (?,?,?,?)";

		try {

			connection = Conexao.getConnection();

			psInserir = connection.prepareStatement(query);

			psInserir.setString(1, agencia.getNome());
			psInserir.setInt(2, agencia.getCodigo());
			psInserir.setString(3, agencia.getEndereco());
			psInserir.setString(4, agencia.getGerente());

			psInserir.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Deu ruim na hora de inserir a agencia" + e);
			e.printStackTrace();
			throw new FalhaInsercaoException(e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(psInserir);
		}
		return agencia;
	}

	public Agencia encontrarPeloNome(String nomeParaBuscar) throws SQLException {

		Agencia agencia = new Agencia();
		Connection connection = null;
		PreparedStatement psSelect = null;
		String query = "SELECT * from agencia WHERE nome = ?";

		try {

			connection = Conexao.getConnection();
			psSelect = connection.prepareStatement(query);

			psSelect.setString(1, nomeParaBuscar);

			ResultSet rs = psSelect.executeQuery();

			while (rs.next()) {

				agencia.setNome(rs.getString("nome"));
				agencia.setCodigo(rs.getInt("codigo"));
				agencia.setEndereco(rs.getString("endereco"));
				agencia.setGerente(rs.getString("gerente"));
			}

		} catch (Exception e) {
			System.err.println(e);
		} finally {
			DbUtils.closeQuietly(psSelect);
		}

		return agencia;
	}

	public Agencia encontrarPeloCodigo(int codigoParaBusca) throws SQLException {

		Agencia agencia = new Agencia();
		Connection connection = null;
		PreparedStatement psSelect = null;
		String query = "SELECT * from agencia WHERE codigo = ?";

		try {
			connection = Conexao.getConnection();
			psSelect = connection.prepareStatement(query);

			psSelect.setInt(1, codigoParaBusca);

			ResultSet rs = psSelect.executeQuery();

			while (rs.next()) {
				agencia.setIdAgencia(rs.getInt("idagencia"));
				agencia.setNome(rs.getString("nome"));
				agencia.setCodigo(rs.getInt("codigo"));
				agencia.setEndereco(rs.getString("endereco"));
				agencia.setGerente(rs.getString("gerente"));
			}

		} catch (Exception e) {
			System.err.println(e);
		} finally {
			DbUtils.closeQuietly(psSelect);
		}

		return agencia;
	}

	public Agencia atualizarAgencia(int codigo, Agencia agencia) throws SQLException {

		Connection connection = null;
		PreparedStatement psUpdate = null;
		String query = "UPDATE agencia SET nome = ?, codigo = ?, endereco = ?, gerente = ? WHERE codigo = ?";

		try {
			connection = Conexao.getConnection();
			psUpdate = connection.prepareStatement(query);

			psUpdate.setString(1, agencia.getNome());
			psUpdate.setInt(2, agencia.getCodigo());
			psUpdate.setString(3, agencia.getEndereco());
			psUpdate.setString(4, agencia.getGerente());

			psUpdate.setInt(5, codigo);
			psUpdate.executeUpdate();
			return agencia;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			DbUtils.closeQuietly(psUpdate);
		}

	}

	public Agencia removerAgencia(Agencia agencia) throws SQLException {

		String query = "DELETE FROM agencia WHERE codigo = ?";

		Connection connection = null;
		PreparedStatement psDelete = null;

		try {
			connection = Conexao.getConnection();
			psDelete = connection.prepareStatement(query);

			psDelete.setInt(1, agencia.getCodigo());

			psDelete.executeUpdate();

		} catch (Exception e) {
			System.err.println(e);
		} finally {
			DbUtils.closeQuietly(psDelete);
		}

		return agencia;
	}

	public List<Agencia> listarTodasAgencias() throws SQLException {

		String query = "SELECT * FROM agencia ORDER by nome";
		Connection connection = null;
		PreparedStatement psList = null;

		try {

			List<Agencia> agencias = new ArrayList<Agencia>();
			connection = Conexao.getConnection();
			psList = connection.prepareStatement(query);

			ResultSet rs = psList.executeQuery();

			while (rs.next()) {
				Agencia agencia = new Agencia();
				agencia.setNome(rs.getString("nome"));
				agencia.setCodigo(rs.getInt("codigo"));
				agencia.setEndereco(rs.getString("endereco"));
				agencia.setGerente(rs.getString("gerente"));

				agencias.add(agencia);
			}
			return agencias;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		} finally {
			DbUtils.closeQuietly(psList);
		}
	}

	@Override
	public Agencia encontrarPeloId(int idAgencia) throws SQLException {

		Agencia agencia = new Agencia();
		Connection connection = null;
		PreparedStatement psSelect = null;
		String query = "SELECT * from agencia WHERE idagencia = ?";

		try {
			connection = Conexao.getConnection();
			psSelect = connection.prepareStatement(query);

			psSelect.setInt(1, idAgencia);

			ResultSet rs = psSelect.executeQuery();

			while (rs.next()) {
				agencia.setIdAgencia(rs.getInt("idagencia"));
				agencia.setNome(rs.getString("nome"));
				agencia.setCodigo(rs.getInt("codigo"));
				agencia.setEndereco(rs.getString("endereco"));
				agencia.setGerente(rs.getString("gerente"));
			}
			return agencia;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		} finally {
			DbUtils.closeQuietly(psSelect);
		}

	}

}
