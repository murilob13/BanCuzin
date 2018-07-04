package conta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import agencia.AgenciaDaoJdbcImpl;
import cliente.ClienteDaoJdbcImpl;
import connection.Conexao;

public class ContaDaoJdbcImpl implements ContaDao {

	AgenciaDaoJdbcImpl agenciaDao = new AgenciaDaoJdbcImpl();
	ClienteDaoJdbcImpl clienteDao = new ClienteDaoJdbcImpl();

	@Override
	public void criarTabelaConta() throws SQLException {
		String query = "CREATE TABLE IF NOT EXISTS conta (idConta SERIAL NOT NULL PRIMARY KEY, idagencia int NOT NULL references agencia (idagencia), numconta varchar (7) NOT NULL UNIQUE, idcliente int NOT NULL references cliente (idcliente), tipoconta varchar (20) NOT NULL, plano varchar (20), saldo numeric, limite numeric);";

		try {
			Connection connection = Conexao.getConnection();

			PreparedStatement psCreate = connection.prepareStatement(query);
			psCreate.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public Conta cadastrarConta(Conta conta) throws SQLException {

		Connection connection = null;
		PreparedStatement psInserir = null;

		String query = "INSERT INTO conta (idagencia, numconta, idcliente, tipoconta, plano, saldo, limite) VALUES (?, ?, ?, ?, ?, ?, ?);";

		try {
			connection = Conexao.getConnection();
			psInserir = connection.prepareStatement(query);

			psInserir.setInt(1, conta.agencia.getIdAgencia());
			psInserir.setString(2, conta.getNumConta());
			psInserir.setInt(3, conta.cliente.getIdCliente());
			psInserir.setString(4, conta.getTipoConta().getTpConta());
			psInserir.setString(5, conta.getPlano().getTpPlano());
			psInserir.setDouble(6, conta.getSaldo());
			psInserir.setDouble(7, conta.getLimite());

			psInserir.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Deu ruim na hora de inserir o cliente" + e);
			throw new SQLException();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			DbUtils.closeQuietly(psInserir);
		}
		return conta;
	}

	@Override
	public Conta encontrarPeloNumero(String numeroParaBuscar) throws Exception {
		Conta conta = new Conta();
		Connection connection = null;
		PreparedStatement psSelect = null;
		String query = "SELECT * FROM conta WHERE numconta = ?";

		try {
			connection = Conexao.getConnection();
			psSelect = connection.prepareStatement(query);
			psSelect.setString(1, numeroParaBuscar);

			ResultSet rs = psSelect.executeQuery();
			while (rs.next()) {
				conta.setIdConta(rs.getInt("idconta"));
				conta.setAgencia(agenciaDao.encontrarPeloId(rs.getInt("idagencia")));
				conta.setNumConta(rs.getString("numconta"));
				conta.setCliente(clienteDao.encontrarPeloId(rs.getInt("idcliente")));
				conta.setTipoConta(TipoConta.valueOf(rs.getString("tipoconta")));
				conta.setPlano(Plano.valueOf(rs.getString("plano")));
				conta.setSaldo(rs.getDouble("saldo"));
				conta.setLimite(rs.getDouble("limite"));
			}
			return conta;
		} catch (SQLException e) {
			System.err.println("Deu ruim na hora de buscar a conta" + e);
			return null;
		} finally {
			DbUtils.closeQuietly(psSelect);
		}
	}

	@Override
	public Conta encontrarPeloNome(String nomeParaBuscar) throws SQLException {
		Conta conta = new Conta();

		Connection connection = null;
		PreparedStatement psSelect = null;
		String query = "SELECT idconta, idagencia, numconta, c.idcliente, tipoconta, plano, saldo, limite FROM conta c INNER JOIN cliente clt ON  c.idcliente = clt.idcliente AND clt.nome = ?";

		try {
			connection = Conexao.getConnection();
			psSelect = connection.prepareStatement(query);
			psSelect.setString(1, nomeParaBuscar);

			ResultSet rs = psSelect.executeQuery();

			while (rs.next()) {
				conta.setIdConta(rs.getInt("idconta"));
				conta.setAgencia(agenciaDao.encontrarPeloId(rs.getInt("idagencia")));
				conta.setNumConta(rs.getString("numconta"));
				conta.setCliente(clienteDao.encontrarPeloId(rs.getInt("idcliente")));
				conta.setTipoConta(TipoConta.valueOf(rs.getString("tipoconta")));
				conta.setPlano(Plano.valueOf(rs.getString("plano")));
				conta.setSaldo(rs.getDouble("saldo"));
				conta.setLimite(rs.getDouble("limite"));
			}
			return conta;
		} catch (Exception e) {
			System.err.println("Deu ruim na hora de buscar a conta" + e);
			return null;
		} finally {
			DbUtils.closeQuietly(psSelect);
		}

	}

	@Override
	public Conta encontrarPeloNumECodigoAg(int codAgencia, String numConta) throws SQLException {
		Conta conta = new Conta();

		Connection connection = null;
		PreparedStatement psSelect = null;
		String query = "SELECT idconta, c.idagencia, numconta, c.idcliente, tipoconta, plano, saldo, limite FROM conta c INNER JOIN agencia ag ON c.idagencia = ag.idagencia WHERE ag.codigo = ? AND c.numconta = ?";

		try {
			connection = Conexao.getConnection();
			psSelect = connection.prepareStatement(query);
			psSelect.setInt(1, codAgencia);
			psSelect.setString(2, numConta);

			ResultSet rs = psSelect.executeQuery();

			while (rs.next()) {
				conta.setIdConta(rs.getInt("idconta"));
				conta.setAgencia(agenciaDao.encontrarPeloId(rs.getInt("idagencia")));
				conta.setNumConta(rs.getString("numconta"));
				conta.setCliente(clienteDao.encontrarPeloId(rs.getInt("idcliente")));
				conta.setTipoConta(TipoConta.valueOf(rs.getString("tipoconta")));
				conta.setPlano(Plano.valueOf(rs.getString("plano")));
				conta.setSaldo(rs.getDouble("saldo"));
				conta.setLimite(rs.getDouble("limite"));
			}
			return conta;
		} catch (Exception e) {
			System.err.println("Deu ruim na hora de buscar a conta" + e);
			return null;
		} finally {
			DbUtils.closeQuietly(psSelect);
		}

	}

	@Override
	public Conta atualizarConta(String numConta, int codAgencia, Conta conta) throws SQLException {

		Connection connection = null;
		PreparedStatement psUpdate = null;
		String query = "UPDATE conta SET idagencia = ?, numconta = ?, idcliente = ?, tipoconta = ?, plano = ?, saldo = ?, limite = ?  WHERE idconta IN (SELECT c.idconta FROM conta c INNER JOIN agencia ag ON c.idagencia = ag.idagencia WHERE ag.codigo = ?  AND c.numconta = ?)";

		try {
			connection = Conexao.getConnection();
			psUpdate = connection.prepareStatement(query);

			psUpdate.setInt(1, conta.getAgencia().getIdAgencia());
			psUpdate.setString(2, conta.getNumConta());
			psUpdate.setInt(3, conta.getCliente().getIdCliente());
			psUpdate.setString(4, conta.getTipoConta().getTpConta());
			psUpdate.setString(5, conta.getPlano().getTpPlano());
			psUpdate.setDouble(6, conta.getSaldo());
			psUpdate.setDouble(7, conta.getLimite());

			psUpdate.setString(8, numConta);
			psUpdate.setInt(9, codAgencia);
			psUpdate.executeUpdate();
			return conta;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			DbUtils.closeQuietly(psUpdate);
		}

	}

	@Override
	public Conta removerConta(String numConta, int codAgencia) throws SQLException {
		Conta conta = new Conta();
		Connection connection = null;
		PreparedStatement psDelete = null;
		String query = "DELETE FROM conta WHERE idconta IN ( SELECT c.idconta FROM conta c INNER JOIN agencia ag ON c.idagencia = ag.idagencia WHERE ag.codigo = ? AND c.numconta = ?)";

		try {
			connection = Conexao.getConnection();
			psDelete = connection.prepareStatement(query);

			psDelete.setInt(1, codAgencia);
			psDelete.setString(2, numConta);
			psDelete.executeUpdate();
			return conta;
		} catch (Exception e) {
			System.err.println("Falha ao deletar conta." + e);
			return null;
		} finally {
			DbUtils.closeQuietly(psDelete);
		}
	}

	@Override
	public List<Conta> listarTodasContas() throws SQLException {
		Connection connection = null;
		PreparedStatement psList = null;
		String query = "SELECT * FROM conta ORDER by idconta";

		try {
			List<Conta> contas = new ArrayList<Conta>();
			connection = Conexao.getConnection();
			psList = connection.prepareStatement(query);

			ResultSet rs = psList.executeQuery();

			while (rs.next()) {
				Conta conta = new Conta();

				conta.setIdConta(rs.getInt("idconta"));
				conta.setAgencia(agenciaDao.encontrarPeloId(rs.getInt("idagencia")));
				conta.setNumConta(rs.getString("numconta"));
				conta.setCliente(clienteDao.encontrarPeloId(rs.getInt("idcliente")));
				conta.setTipoConta(TipoConta.valueOf(rs.getString("tipoconta")));
				conta.setPlano(Plano.valueOf(rs.getString("plano")));
				conta.setSaldo(rs.getDouble("saldo"));
				conta.setLimite(rs.getDouble("limite"));

				contas.add(conta);
			}
			return contas;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		} finally {
			DbUtils.closeQuietly(psList);
		}
	}

}
