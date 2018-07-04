package cliente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import connection.Conexao;

public class ClienteDaoJdbcImpl implements ClienteDao {

	public void criarTabelaCliente() throws SQLException {

		String query = "CREATE TABLE IF NOT EXISTS cliente (idCliente SERIAL NOT NULL PRIMARY KEY, nome varchar(255) NOT NULL, cpfCnpj varchar(255) NOT NULL UNIQUE, endereco varchar(255) NOT NULL, birthday date NOT NULL, sexo varchar (10));";

		try {

			Connection connection = Conexao.getConnection();

			PreparedStatement psCreate = connection.prepareStatement(query);
			psCreate.executeUpdate();

		} catch (Exception e) {
			System.err.println(e);
		}

	}

	public Cliente cadastrarCliente(Cliente cliente) throws SQLException {

		Connection connection = null;
		PreparedStatement psInserir = null;
		String query = "INSERT INTO cliente (nome, cpfCnpj, endereco, birthday, sexo) VALUES (?, ?, ?, ?, ?);";

		try {
			connection = Conexao.getConnection();
			psInserir = connection.prepareStatement(query);

			psInserir.setString(1, cliente.getNome());
			psInserir.setString(2, cliente.getCpfCnpj());
			psInserir.setString(3, cliente.getEndereco());
			psInserir.setDate(4, new Date(cliente.getBirthDay().getTime()));
			psInserir.setString(5, cliente.getSexo());

			psInserir.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Deu ruim na hora de inserir o cliente" + e);
			throw new SQLException();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			DbUtils.closeQuietly(psInserir);
		}

		return cliente;
	}

	public Cliente encontrarPeloNome(String nomeParaBuscar) throws SQLException {

		Cliente cliente = new Cliente();
		Connection connection = null;
		PreparedStatement psSelect = null;
		String query = "SELECT * FROM cliente WHERE nome = ?";

		try {
			connection = Conexao.getConnection();
			psSelect = connection.prepareStatement(query);
			psSelect.setString(1, nomeParaBuscar);

			ResultSet rs = psSelect.executeQuery();

			while (rs.next()) {
				cliente.setIdCliente(rs.getInt("idcliente"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpfCnpj(rs.getString("cpfCnpj"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setBirthDay(rs.getDate("birthday"));
				cliente.setSexo(rs.getString("sexo"));
			}

		} catch (Exception e) {
			System.err.println(e);
		} finally {
			DbUtils.closeQuietly(psSelect);
		}

		return cliente;
	}

	public Cliente encontrarPeloCpfCnpj(String cpfCnpjParaBuscar) throws SQLException {

		Cliente cliente = new Cliente();
		Connection connection = null;
		PreparedStatement psSelect = null;
		String query = "SELECT * from cliente WHERE cpfCnpj = ?;";

		try {
			connection = Conexao.getConnection();
			psSelect = connection.prepareStatement(query);

			psSelect.setString(1, cpfCnpjParaBuscar);

			ResultSet rs = psSelect.executeQuery();

			while (rs.next()) {
				cliente.setIdCliente(rs.getInt("idcliente"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpfCnpj(rs.getString("cpfcnpj"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setBirthDay(rs.getDate("birthday"));
				cliente.setSexo(rs.getString("sexo"));
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			DbUtils.closeQuietly(psSelect);
		}

		return cliente;
	}

	@Override
	public Cliente encontrarPeloId(int idCliente) throws SQLException {

		Cliente cliente = new Cliente();
		Connection connection = null;
		PreparedStatement psSelect = null;
		String query = "SELECT * from cliente WHERE idcliente = ?;";

		try {
			connection = Conexao.getConnection();
			psSelect = connection.prepareStatement(query);

			psSelect.setInt(1, idCliente);

			ResultSet rs = psSelect.executeQuery();

			while (rs.next()) {
				cliente.setIdCliente(rs.getInt("idcliente"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpfCnpj(rs.getString("cpfcnpj"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setBirthDay(rs.getDate("birthday"));
				cliente.setSexo(rs.getString("sexo"));
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			DbUtils.closeQuietly(psSelect);
		}

		return cliente;
	}

	@Override
	public Cliente encontrarPeloNomeECpfCnpj(String nomeParaBuscar, String cpfCnpjParaBuscar) throws SQLException {

		Cliente cliente = new Cliente();
		Connection connection = null;
		PreparedStatement psSelect = null;
		String query = "select * from cliente where nome = ? and cpfcnpj= ?";

		try {
			connection = Conexao.getConnection();
			psSelect = connection.prepareStatement(query);

			psSelect.setString(1, nomeParaBuscar);
			psSelect.setString(2, cpfCnpjParaBuscar);

			ResultSet rs = psSelect.executeQuery();

			while (rs.next()) {
				cliente.setIdCliente(rs.getInt("idcliente"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpfCnpj(rs.getString("cpfcnpj"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setBirthDay(rs.getDate("birthday"));
				cliente.setSexo(rs.getString("sexo"));
			}
			return cliente;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		} finally {
			DbUtils.closeQuietly(psSelect);
		}
	}

	public Cliente atualizarCliente(String cpfCnpj, Cliente cliente) throws SQLException {

		Connection connection = null;
		PreparedStatement psUpdate = null;
		String query = "UPDATE cliente SET nome = ?, cpfCnpj = ?, endereco = ?, birthday = ?, sexo = ? WHERE cpfcnpj = ?;";

		try {
			connection = Conexao.getConnection();
			psUpdate = connection.prepareStatement(query);

			psUpdate.setString(1, cliente.getNome());
			psUpdate.setString(2, cliente.getCpfCnpj());
			psUpdate.setString(3, cliente.getEndereco());
			psUpdate.setDate(4, new Date(cliente.getBirthDay().getTime()));
			psUpdate.setString(6, cliente.getSexo());

			psUpdate.setString(5, cpfCnpj);
			psUpdate.executeUpdate();
			return cliente;

		} catch (Exception e) {
			System.err.println(e);
			return null;
		} finally {
			DbUtils.closeQuietly(psUpdate);
		}
	}

	public Cliente removerCliente(Cliente cliente) throws SQLException {
		Connection connection = null;
		PreparedStatement psDelete = null;
		String query = "DELETE FROM cliente WHERE cpfCnpj = ?;";

		try {
			connection = Conexao.getConnection();
			psDelete = connection.prepareStatement(query);

			psDelete.setString(1, cliente.getCpfCnpj());
			psDelete.executeUpdate();
			return cliente;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		} finally {
			DbUtils.closeQuietly(psDelete);
		}
	}

	public List<Cliente> listarTodosClientes() throws SQLException {
		Connection connection = null;
		PreparedStatement psList = null;
		String query = "SELECT * FROM cliente ORDER by nome;";

		try {
			List<Cliente> clientes = new ArrayList<Cliente>();
			connection = Conexao.getConnection();
			psList = connection.prepareStatement(query);

			ResultSet rs = psList.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setNome(rs.getString("nome"));
				cliente.setCpfCnpj(rs.getString("cpfcnpj"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setBirthDay(rs.getDate("birthday"));
				cliente.setSexo(rs.getString("sexo"));

				clientes.add(cliente);
			}
			return clientes;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		} finally {
			DbUtils.closeQuietly(psList);
		}
	}

}
