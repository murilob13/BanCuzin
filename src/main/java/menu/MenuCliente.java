package menu;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import cliente.Cliente;
import cliente.ClienteDaoJdbcImpl;
import cliente.ImportarCliente;

public class MenuCliente {

	public static void menuClient() throws Exception {

		String opcao = "X";
		Scanner input = new Scanner(System.in);
		Cliente cliente = new Cliente();
		ClienteDaoJdbcImpl clienteDao = new ClienteDaoJdbcImpl();
		int valor = 0;

		do {
			opcao = JOptionPane.showInputDialog("**MENU CLIENTE**\n\nDigite um comando para prosseguir:\n\nC - Cadastrar um cliente\nB - Buscar por um cliente especifico\nA - Atualiziar os dados de um cliente\nD - Deletar um cliente\nL - Listar todos os clientes\nI - Importar clientes\nV - Voltar ao menu principal");
			opcao = opcao.toUpperCase();

			switch (opcao) {
			case "C":

				cliente = cadastrarCliente(input, cliente, clienteDao);

				break;
			case "B":

				while (valor != 1 && valor != 2) {
					System.out.println("Deseja realziar a busca por cpf/cnpj(1) ou por nome(2)?");
					valor = Integer.parseInt(input.nextLine());

					if (valor == 1) {
						buscarPorCpfCnpj(input, clienteDao);
					}
					if (valor == 2) {
						cliente = buscarPorNome(input, clienteDao);
					} else {
						System.out.println("Valor invalido. Tente denovo");
					}
				}
				break;
			case "A":

				while (valor != 1 && valor != 2) {

					System.out.println("Deseja realizar a atualização por cpf/cnpj(1) ou pelo nome(2)?");
					valor = Integer.parseInt(input.nextLine());
					if (valor == 1) {
						cliente = atualizarPorCpfCnpj(input, clienteDao);
					}
					if (valor == 2) {
						cliente = atualizarPorNome(input, clienteDao);
					} else {
						System.out.println("Opção invalida. Tenten novamente.");
						menuClient();
					}
				}
				break;

			case "D":

				while (valor != 1 && valor != 2) {

					System.out.println("Deseja deletar por cpf/cnpj(1) ou por nome(2)?");
					valor = Integer.parseInt(input.nextLine());
					if (valor == 1) {
						deletarPorCpfCnpj(input, clienteDao);
					}
					if (valor == 2) {
						cliente = deletarPorNome(input, clienteDao);
					} else {
						System.out.println("Opção invalida, tente novamente.");
					}
				}
				break;

			case "L":
				listarClientes(clienteDao);
				break;

			case "I":
				importarClientes(input);
				break;

			default:
				break;
			}

		} while (opcao != "V");

	}

	private static Cliente deletarPorNome(Scanner input, ClienteDaoJdbcImpl clienteDao) throws SQLException, Exception {
		Cliente cliente;
		System.out.println("Digite o nome do Cliente:");
		String nomeParaBuscar = input.nextLine();
		cliente = clienteDao.encontrarPeloNome(nomeParaBuscar);
		if (cliente.getCpfCnpj() == null) {
			System.out.println("Cliente não cadastrado ainda.");
			menuClient();
		} else {
			clienteDao.removerCliente(cliente);
			System.out.println(cliente.getNome() + " removido.");
			Menu.MenuPrincipal();
		}
		return cliente;
	}

	private static void deletarPorCpfCnpj(Scanner input, ClienteDaoJdbcImpl clienteDao) throws SQLException, Exception {
		Cliente cliente;
		System.out.println("Digite o CPF ou CNPJ do Cliente:");
		String cpfCnpjParaBuscar = input.nextLine();
		cliente = clienteDao.encontrarPeloCpfCnpj(cpfCnpjParaBuscar);
		if (cliente.getCpfCnpj() == null) {
			System.out.println("Cliente não cadastrado ainda.");
			menuClient();
		} else {
			clienteDao.removerCliente(cliente);
			System.out.println(cliente.getNome() + " removido.");
			Menu.MenuPrincipal();
		}
	}

	private static Cliente atualizarPorNome(Scanner input, ClienteDaoJdbcImpl clienteDao)
			throws SQLException, Exception {
		Cliente cliente;
		System.out.println("Digite o nome: ");
		String nomeParaBuscar = input.nextLine();
		cliente = clienteDao.encontrarPeloNome(nomeParaBuscar);
		if (cliente.getCpfCnpj() == null) {
			System.out.println("Não existe agencia com este nome.");
			menuClient();
		} else {
			System.out.println("Digite os novos dados do cliente: " + cliente.getNome());
			try {
				cliente = atualizarCliente(input, cliente, clienteDao);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cliente;
	}

	private static Cliente atualizarPorCpfCnpj(Scanner input, ClienteDaoJdbcImpl clienteDao)
			throws SQLException, Exception {
		Cliente cliente;
		System.out.println("Digite o CPF ou o CNPJ: ");
		String cpfCnpjParaBuscar = input.nextLine();
		cliente = clienteDao.encontrarPeloCpfCnpj(cpfCnpjParaBuscar);
		if (cliente.getCpfCnpj() == null) {
			System.out.println("Não existe agencia com este nome.");
			menuClient();
		} else {
			System.out.println("Digite os novos dados do cliente: " + cliente.getNome());
			try {
				cliente = atualizarCliente(input, cliente, clienteDao);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return cliente;
	}

	private static Cliente atualizarCliente(Scanner input, Cliente cliente, ClienteDaoJdbcImpl clienteDao)
			throws SQLException {
		System.out.println("Nome:");
		cliente.setNome(input.nextLine());
		System.out.println("CPF ou CNPJ: ");
		cliente.setCpfCnpj(input.nextLine());
		System.out.println("Endereco: ");
		cliente.setEndereco(input.nextLine());
		try {
			DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			System.out.println("Data nascimento(dd/MM/yyyy): ");
			String birthday = input.nextLine();

			java.util.Date dataf = formatoData.parse(birthday);
			java.sql.Date dataSql = new java.sql.Date(dataf.getTime());
			cliente.setBirthDay(dataSql);
		} catch (Exception e) {
			System.out.println("Data invalida, tente novamente.");
		}
		System.out.println("Sexo:");
		cliente.setSexo(input.nextLine());

		System.out.println("\nAtualizando cliente...");
		cliente = clienteDao.atualizarCliente(cliente.getCpfCnpj(), cliente);
		System.out.println(cliente.getNome() + " atualizado com sucesso!");
		return cliente;
	}

	private static void buscarPorCpfCnpj(Scanner input, ClienteDaoJdbcImpl clienteDao) throws SQLException, Exception {
		Cliente cliente;
		System.out.println("Digite o cpf que deseja buscar: ");
		String cpfCnpjParaBuscar = input.nextLine();
		cliente = clienteDao.encontrarPeloCpfCnpj(cpfCnpjParaBuscar);

		if (cliente.getCpfCnpj() == null) {
			System.out.println("Não existe CPF ou CNPJ com este numero");
			menuClient();
		} else {
			System.out.println("Dados do cliente:");
			System.out.println("Nome: " + cliente.getNome());
			System.out.println("Endereco: " + cliente.getEndereco());
			System.out.println("CPF/CNPJ: " + cliente.getCpfCnpj());
			System.out.println("Data de aniversario: " + cliente.getBirthDay());
			System.out.println("SEXO: " + cliente.getSexo());
			Menu.MenuPrincipal();
		}
	}

	private static Cliente buscarPorNome(Scanner input, ClienteDaoJdbcImpl clienteDao) throws SQLException, Exception {
		Cliente cliente;
		System.out.println("Digite o nome do cliente: ");
		String nomeParaBuscar = input.nextLine();
		cliente = clienteDao.encontrarPeloNome(nomeParaBuscar);

		if (cliente.getCpfCnpj() == null) {
			System.out.println("Não existe cliene com este nome. Tente novamente.");

		} else {
			System.out.println("Dados do cliente:");
			System.out.println("Nome: " + cliente.getNome());
			System.out.println("Endereco: " + cliente.getEndereco());
			System.out.println("CPF/CNPJ: " + cliente.getCpfCnpj());
			System.out.println("Data de aniversario: " + cliente.getBirthDay());
			System.out.println("SEXO: " + cliente.getSexo());
			Menu.MenuPrincipal();
		}
		return cliente;
	}

	private static Cliente cadastrarCliente(Scanner input, Cliente cliente, ClienteDaoJdbcImpl clienteDao)
			throws Exception {
		System.out.println("Digite os dados do cliente:");

		try {
			cliente = atualizarCliente(input, cliente, clienteDao);

		} catch (Exception e) {
			System.err.println("Falha ao cadastrar cliente.");
			System.out.println("Vamos tentar novamente?");
		}

		Menu.MenuPrincipal();
		return cliente;
	}

	private static void importarClientes(Scanner input) throws IOException, Exception {
		System.out.println("Digite o caminho do arquivo de clientes: ");
		String caminho = input.nextLine();

		ImportarCliente.clienteImoporter(caminho);

		Menu.MenuPrincipal();
	}

	private static void listarClientes(ClienteDaoJdbcImpl clienteDao) throws SQLException, Exception {
		List<Cliente> listClientes = clienteDao.listarTodosClientes();

		if (listClientes.isEmpty()) {
			System.out.println("Não existe agencias cadastradas.");
			menuClient();
		} else {
			System.out.println("Lista de todos os Clientes");
			System.out.println("--------------------------------");
			for (Cliente clientes : listClientes) {
				System.out.println(clientes.toString());
				System.out.println("--------------------------------");
			}
			Menu.MenuPrincipal();
		}
	}

}
