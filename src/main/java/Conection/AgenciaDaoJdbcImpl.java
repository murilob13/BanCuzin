package Conection;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import Agencia.Agencia;

public class AgenciaDaoJdbcImpl implements AgenciaDao {

	static CarregarDados dadosDeAcesso = new CarregarDados();
	static Scanner input = new Scanner(System.in);

	public static Connection getConnection() throws Exception {
		dadosDeAcesso.dadosDeAcesso();
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(dadosDeAcesso.url, dadosDeAcesso.usuario, dadosDeAcesso.senha);
			if (connection != null) {
				// System.out.println("Conexão com o banco estabeleida!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}

	public static void criarTabelaAgencia() throws Exception {

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

	}

	public static void cadastrarAgencia() throws Exception {

		String nome = null;
		int codigo;
		String endereco;
		String gerente;

		String query = "INSERT INTO agencia" + " (nome, codigo, endereco, gerente) VALUES" + "(?,?,?,?)";

		try {
			Connection connection = getConnection();
			PreparedStatement inserir = connection.prepareStatement(query);

			System.out.println("Digite os dados da nova agencia:");

			System.out.println("Nome: ");
			nome = input.nextLine();
			inserir.setString(1, nome);

			System.out.println("Endereco: ");
			endereco = input.nextLine();
			inserir.setString(3, endereco);

			System.out.println("Gerente: ");
			gerente = input.nextLine();
			inserir.setString(4, gerente);

			System.out.println("Codigo: ");
			codigo = input.nextInt();
			inserir.setInt(2, codigo);

			inserir.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Agencia " + nome + " criada com sucesso!");
		}

	}

	public Agencia encontrarPeloNome(String nomeParaBuscar) {

		String query = "SELECT * from agencia WHERE nome = ?";

		try {

			Connection connection = getConnection();
			PreparedStatement select = connection.prepareStatement(query);

			select.setString(1, nomeParaBuscar);

			ResultSet rs = select.executeQuery();

			while (rs.next()) {
				String nome = rs.getString("nome");
				int codigo = rs.getInt("codigo");
				String endereco = rs.getString("endereco");
				String gerente = rs.getString("gerente");

				System.out.println("Dados da agencia buscada: ");
				System.out.println("Nome: " + nome);
				System.out.println("Codigo: " + codigo);
				System.out.println("Endereco: " + endereco);
				System.out.println("Gerente: " + gerente);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

	public Agencia encontrarPeloCodigo(int codigoParaBusca) {

		String query = "SELECT * from agencia WHERE codigo = ?";

		try {

			Connection connection = getConnection();
			PreparedStatement select = connection.prepareStatement(query);

			select.setInt(1, codigoParaBusca);

			ResultSet rs = select.executeQuery();

			while (rs.next()) {
				String nome = rs.getString("nome");
				int codigo = rs.getInt("codigo");
				String endereco = rs.getString("endereco");
				String gerente = rs.getString("gerente");

				System.out.println("Dados da agencia buscada: ");
				System.out.println("Nome: " + nome);
				System.out.println("Codigo: " + codigo);
				System.out.println("Endereco: " + endereco);
				System.out.println("Gerente: " + gerente);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

	public Agencia atualizarAgencia(int tBusca) {

		String queryNome = "UPDATE agencia SET nome = ?, codigo = ?, endereco = ?, gerente = ? WHERE nome = ?";
		String queryCod = "UPDATE agencia SET nome = ?, codigo = ?, endereco = ?, gerente = ? WHERE codigo = ?";

		String nome = null;
		int codigo;
		String endereco;
		String gerente;
		String nomeParaBuscar;
		int codigoParaBusca;

		switch (tBusca) {
		case 1:

			System.out.println("Digite o nome que deseja atualizar os dados:");
			nomeParaBuscar = input.nextLine();
			try {

				Connection connection = getConnection();
				PreparedStatement update = connection.prepareStatement(queryNome);

				System.out.println("Digite os dados atualizados:");

				System.out.println("Nome: ");
				nome = input.nextLine();
				update.setString(1, nome);

				System.out.println("Endereco: ");
				endereco = input.nextLine();
				update.setString(3, endereco);

				System.out.println("Gerente: ");
				gerente = input.nextLine();
				update.setString(4, gerente);

				System.out.println("Codigo: ");
				codigo = input.nextInt();
				update.setInt(2, codigo);

				update.setString(5, nomeParaBuscar);

				update.executeUpdate();

			} catch (Exception e) {
				System.out.println(e);
			} finally {
				System.out.println("Dados atualziados com sucesso!");
			}

			break;

		case 2:
			System.out.println("Digite o codigio que deseja atualziar os dados:");
			codigoParaBusca = input.nextInt();
			try {

				Connection connection = getConnection();
				PreparedStatement update = connection.prepareStatement(queryCod);

				System.out.println("Digite os dados atualizados:");

				System.out.println("Nome: ");
				nome = input.nextLine();
				update.setString(1, nome);

				System.out.println("Endereco: ");
				endereco = input.nextLine();
				update.setString(3, endereco);

				System.out.println("Gerente: ");
				gerente = input.nextLine();
				update.setString(4, gerente);

				System.out.println("Codigo: ");
				codigo = input.nextInt();
				update.setInt(2, codigo);

				update.setInt(5, codigoParaBusca);

				update.executeUpdate();

			} catch (Exception e) {
				System.out.println(e);
			} finally {
				System.out.println("Dados atualziados com sucesso!");
			}

			break;

		default:

			System.out.println("Digite uma opcao valida, 1 para buscar pelo nome 2 para buscar pelo codigo.");
			break;
		}

		return null;
	}

	public Agencia cadastrarAgencia(Agencia agencia) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List listarTods() {
		// TODO Auto-generated method stub
		return null;
	}

	public Agencia removerAgencia(Agencia agencia) {
		// TODO Auto-generated method stub
		return null;
	}

}
