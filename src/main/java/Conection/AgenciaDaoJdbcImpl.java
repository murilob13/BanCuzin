package conection;

import java.util.List;

import agencia.Agencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AgenciaDaoJdbcImpl implements AgenciaDao {

	CarregarDados dadosDeAcesso = new CarregarDados();

	public Connection getConnection() throws Exception {

		dadosDeAcesso.dadosDeAcesso();
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(dadosDeAcesso.url, dadosDeAcesso.usuario, dadosDeAcesso.senha);
		} catch (Exception e) {
			System.out.println("Falha ao conectar no banco" + e);
		}

		return connection;
	}

	@Override
	public Agencia criarTabelaAgencia() throws Exception {

		String querry = "CREATE TABLE IF NOT EXISTS agencia (nome varchar(255) NOT NULL, codigo int NOT NULL, endereco varchar(255) NOT NULL, gerente varchar(255) NOT NULL, PRIMARY KEY(codigo));";

		try {

			Connection connection = getConnection();

			PreparedStatement create = connection.prepareStatement(querry);
			create.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Tabela agencia criada com sucesso!");
		}

		return null;
	}

	@Override
	public Agencia cadastrarAgencia(Agencia agencia) throws Exception {

		Connection connection = null;
		PreparedStatement inserir = null;
		String query = "INSERT INTO agencia" + " (nome, codigo, endereco, gerente) VALUES" + "(?,?,?,?)";

		try {

			connection = getConnection();
			inserir = connection.prepareStatement(query);

			// Insere nome da agencia
			inserir.setString(1, agencia.getNome());

			// Insere codigo da agencia
			inserir.setInt(2, agencia.getCodigo());

			// Insere endereco da agencia
			inserir.setString(3, agencia.getEndereco());

			// Insere gerente da agencia
			inserir.setString(4, agencia.getGerente());

			inserir.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (inserir != null) {
				inserir.close();
			}

			if (connection != null) {
				connection.close();
			}

		}
		return agencia;
	}

	@Override
	public Agencia encontrarPeloNome(String nomeParaBuscar) throws SQLException {

		Agencia agencia = new Agencia();
		Connection connection = null;
		PreparedStatement select = null;
		String query = "SELECT * from agencia WHERE nome = ?";

		try {

			connection = getConnection();
			select = connection.prepareStatement(query);

			select.setString(1, nomeParaBuscar);

			ResultSet rs = select.executeQuery();

			while (rs.next()) {

				agencia.setNome(rs.getString("nome"));
				agencia.setCodigo(rs.getInt("codigo"));
				agencia.setEndereco(rs.getString("endereco"));
				agencia.setGerente(rs.getString("gerente"));
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (select != null) {
				select.close();
			}

			if (connection != null) {
				connection.close();
			}
		}

		return agencia;
	}

	@Override
	public Agencia encontrarPeloCodigo(int codigoParaBusca) throws SQLException {

		Agencia agencia = new Agencia();
		Connection connection = null;
		PreparedStatement select = null;
		String query = "SELECT * from agencia WHERE codigo = ?";

		try {

			connection = getConnection();
			select = connection.prepareStatement(query);

			select.setInt(1, codigoParaBusca);

			ResultSet rs = select.executeQuery();

			while (rs.next()) {
				agencia.setNome(rs.getString("nome"));
				agencia.setCodigo(rs.getInt("codigo"));
				agencia.setEndereco(rs.getString("endereco"));
				agencia.setGerente(rs.getString("gerente"));
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (select != null) {
				select.close();
			}

			if (connection != null) {
				connection.close();
			}
		}

		return agencia;
	}

	@Override
	public Agencia atualizarAgencia(int codigo) throws SQLException {

		Agencia agencia = new Agencia();
		Connection connection = null;
		PreparedStatement update = null;
		String query = "UPDATE agencia SET nome = ?, codigo = ?, endereco = ?, gerente = ? WHERE codigo = ?";

		try {

			connection = getConnection();
			update = connection.prepareStatement(query);

			// Update nome
			update.setString(1, agencia.getNome());

			// Update codigo
			update.setInt(2, agencia.getCodigo());

			// Update endereco
			update.setString(3, agencia.getEndereco());

			// Update gerente
			update.setString(4, agencia.getGerente());

			update.setInt(5, codigo);

			update.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (update != null) {
				update.close();
			}

			if (connection != null) {
				connection.close();
			}
		}

		return agencia;

	}

	@Override
	public Agencia removerAgencia(Agencia agencia) throws SQLException {

		String query = "DELETE agencia WHERE codigo = ?";

		Connection connection = null;
		PreparedStatement delete = null;

		try {
			connection = getConnection();
			delete = connection.prepareStatement(query);

			delete.setInt(1, agencia.getCodigo());

			delete.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (delete != null) {
				delete.close();
			}

			if (connection != null) {
				connection.close();
			}
		}

		return agencia;
	}

	@Override
	public List<Agencia> listarTodasAgencias() throws SQLException {

		String query = "SELECT * FROM agencia";
		Connection connection = null;
		PreparedStatement list = null;

		try {

			List<Agencia> agencias = new ArrayList<Agencia>();
			connection = getConnection();
			list = connection.prepareStatement(query);

			ResultSet rs = list.executeQuery();

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
			throw new RuntimeException(e);
		} finally {
			if (list != null) {
				list.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

}
