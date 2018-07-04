package menu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import cliente.Cliente;
import cliente.ClienteDaoJdbcImpl;
import financiamento.Emprestimo;
import financiamento.EmprestimoDao;
import financiamento.EmprestimoDaoJdbcImpl;
import financiamento.ImportarEmprestimo;
import financiamento.TipoEmprestimo;

public class MenuEmprestimo {

	public static void menuEmprestimo() throws Exception {

		String opcao = "X";
		Scanner input = new Scanner(System.in);
		Emprestimo emprestimo = new Emprestimo();
		EmprestimoDaoJdbcImpl emprestimoDao = new EmprestimoDaoJdbcImpl();
		ClienteDaoJdbcImpl clienteDao = new ClienteDaoJdbcImpl();
		int valor = 0;

		do {
			opcao = JOptionPane.showInputDialog("\n**MENU EMPRESTIMOS\n\nDigite um comando para prosseguir:\n\nC - Cadastrar um empréstimo\nB - Buscar por um empréstimo específico\nA - Atualizar os dados de um empréstimo\nD - Deletar um empréstimo\nL - Listar todas os empréstimos\nI - Importar empréstimos\nV - Voltar ao menu principal");

			opcao = opcao.toUpperCase();

			switch (opcao) {
			case "C":
				emprestimo = cadastrarEmprestimo(input, emprestimo, emprestimoDao, clienteDao);
				break;
			case "B":

				while (valor != 1 && valor != 2) {
					System.out.println("Deseja realziar a busca por cpf/cnpj(1) ou por nome(2)?");
					valor = Integer.parseInt(input.nextLine());
					emprestimo = buscarEmprestimo(input, emprestimo, emprestimoDao, valor);
				}
				break;
			case "A":

				while (valor != 1 && valor != 2) {

					System.out.println(
							"Deseja realizar a atualização do emprestimo por cpf/cnpj(1) ou pelo nome(2) do cliente?");
					valor = Integer.parseInt(input.nextLine());
					emprestimo = atualizarEmprestimoPorCpfCnpjOuNome(input, emprestimo, emprestimoDao, clienteDao,
							valor);
				}
				break;
			case "D":
				while (valor != 1 && valor != 2) {
					System.out
							.println("Remover um emprestimo  pelo cpf/cnpj do cliente(1) ou pelo nome do cliente(2)?");
					valor = Integer.parseInt(input.nextLine());
					emprestimo = deletarEmprestimoPorNomeOuCpfCnpj(input, emprestimo, emprestimoDao, valor);
				}
				break;
			
			case "L":
				
				listarEmprestimos(emprestimoDao);
				break;
				
			case "I":

				importarEmprestimo(input);
				break;
			
			case "V":
				
				System.out.println("\nVoltando pro menu principal.");
				Menu.MenuPrincipal();
				break;
			
			default:
				System.out.println("\nOpção invalida. Tente novamente. ");
				menuEmprestimo();
				break;
			}

		} while (opcao != "V");

		input.close();
	}

	private static void importarEmprestimo(Scanner input) throws IOException, InterruptedException, Exception {
		System.out.println("Digite o caminho do arquivos emprestimo.");
		String caminho = input.nextLine();
		
		ImportarEmprestimo.emprestimoImporter(caminho);
		
		Menu.MenuPrincipal();
	}

	private static void listarEmprestimos(EmprestimoDaoJdbcImpl emprestimoDao) throws SQLException, Exception {
		List<Emprestimo> listEmprestimos = emprestimoDao.listarTodosEmprestimos();
		if (listEmprestimos.isEmpty()) {
			System.out.println("\nNão há nenhum emprestimo cadastrado.");
			menuEmprestimo();
		} else {
			System.out.println("Lista de todos os Emprestimos:");
			System.out.println("--------------------------------");
			for (Emprestimo emprestimos : listEmprestimos) {
				System.out.println(emprestimos);
				System.out.println("--------------------------------");
			}
		}
		Menu.MenuPrincipal();
	}

	private static Emprestimo deletarEmprestimoPorNomeOuCpfCnpj(Scanner input, Emprestimo emprestimo,
			EmprestimoDaoJdbcImpl emprestimoDao, int valor) throws SQLException, Exception {
		if (valor == 1) {
			System.out.println("Digite o CPF/CNPJ do cliente: ");
			String cpfCnpjParaBuscar = input.nextLine();
			emprestimo = emprestimoDao.encontrarPeloCpfCnpg(cpfCnpjParaBuscar);
			String cpfCnpj = emprestimo.getCliente().getCpfCnpj();
			if (emprestimo.getCliente() == null) {
				System.out.println("Não há emprestimo para este cliente.");
				menuEmprestimo();
			} else {
				System.out.println("Deletando emprestimo..");
				emprestimoDao.removerEmprestimo(cpfCnpj);
				System.out.println("Empréstimo deletado com sucesso");
				Menu.MenuPrincipal();
			}
		}
		if (valor == 2) {
			System.out.println("Digite o nome do cliente: ");
			String nomeParaBuscar = input.nextLine();
			emprestimo = emprestimoDao.encontrarPeloNome(nomeParaBuscar);
			String cpfCnpj = emprestimo.getCliente().getCpfCnpj();
			if (emprestimo.getCliente() == null) {
				System.out.println("Não há emprestimo para este cliente.");
				menuEmprestimo();
			} else {
				System.out.println("Deletando emprestimo..");
				emprestimoDao.removerEmprestimo(cpfCnpj);
				System.out.println("Empréstimo deletado com sucesso");
				Menu.MenuPrincipal();
			}
		} else {
			System.out.println("Valor invalido, tente novamente.");
		}
		return emprestimo;
	}

	private static Emprestimo atualizarEmprestimoPorCpfCnpjOuNome(Scanner input, Emprestimo emprestimo,
			EmprestimoDaoJdbcImpl emprestimoDao, ClienteDaoJdbcImpl clienteDao, int valor)
			throws SQLException, Exception {
		if (valor == 1) {

			System.out.println("Digite o CPF/CNPJ do cliente: ");
			String cpfCnpjParaBuscar = input.nextLine();
			emprestimo = emprestimoDao.encontrarPeloCpfCnpg(cpfCnpjParaBuscar);
			if (emprestimo.getCliente() == null) {
				System.out.println("Não há emprestimo para este cliente.");
				menuEmprestimo();
			} else {
				atualizarEmprestimo(input, emprestimo, emprestimoDao, clienteDao);
			}

		}
		if (valor == 2) {

			System.out.println("Digite o CPF/CNPJ do cliente: ");
			String nomeParaBuscar = input.nextLine();
			emprestimo = emprestimoDao.encontrarPeloNome(nomeParaBuscar);
			if (emprestimo.getCliente() == null) {
				System.out.println("Não há emprestimo para este cliente.");
				menuEmprestimo();
			} else {
				atualizarEmprestimo(input, emprestimo, emprestimoDao, clienteDao);
			}

		} else {
			System.out.println("Opção invalida. Tenten novamente.");
		}
		return emprestimo;
	}

	private static void atualizarEmprestimo(Scanner input, Emprestimo emprestimo, EmprestimoDaoJdbcImpl emprestimoDao,
			ClienteDaoJdbcImpl clienteDao) throws Exception {
		Cliente cliente;
		String cpfCnpj = emprestimo.getCliente().getCpfCnpj();
		System.out.println("Digite os novos dados do emprestimo:");
		try {
			System.out.println("Nome do cliente: ");
			String nomeParaBuscar = input.nextLine();

			System.out.println("CPF/CNPJ do cliente");
			String cpfCnpjParaBuscar2 = input.nextLine();

			// valida se os dados no novo cliente são validos.
			cliente = clienteDao.encontrarPeloNomeECpfCnpj(nomeParaBuscar, cpfCnpjParaBuscar2);
			emprestimo.setCliente(cliente);

			System.out.println("Tipo do emprestimo: ");
			String tipoEmprestimo = input.nextLine();
			tipoEmprestimo = tipoEmprestimo.toUpperCase();
			switch (tipoEmprestimo) {
			case "PESSOAL":
				emprestimo.setTipoBem(TipoEmprestimo.P);
				break;
			case "IMOVEL":
				emprestimo.setTipoBem(TipoEmprestimo.I);
				break;
			case "VEICULO":
				emprestimo.setTipoBem(TipoEmprestimo.V);
				break;
			default:
				System.out.println("Tipo de emprestimo invalido. Tente novamente.");
				menuEmprestimo();
				break;
			}
			System.out.println("Valor do emprestimo: ");
			emprestimo.setValor(Double.parseDouble(input.nextLine()));

			System.out.println("\nAtualizando Emprestimo...");
			emprestimo = emprestimoDao.atualizarEmprestimo(cpfCnpj, emprestimo);
			System.out
					.println("Emprestimo do cliente " + emprestimo.getCliente().getNome() + " atualizado com sucesso");
			Menu.MenuPrincipal();
		} catch (Exception e) {
			System.err.println("\nHouve alguma falha ao tentar atualziar Emprestimo.");
			System.out.println("Vamos tentar novamente.");
			menuEmprestimo();
		}
	}

	private static Emprestimo buscarEmprestimo(Scanner input, Emprestimo emprestimo,
			EmprestimoDaoJdbcImpl emprestimoDao, int valor) throws SQLException, Exception {
		if (valor == 1) {
			System.out.println("Digite o CPF que deseja buscar: ");
			String cpfCnpjParaBuscar = input.nextLine();
			emprestimo = emprestimoDao.encontrarPeloCpfCnpg(cpfCnpjParaBuscar);
			if (emprestimo.getCliente() == null) {
				System.out.println("Não existe cliente com este CPF/CNPJ");
				menuEmprestimo();
			} else {
				System.out.println(emprestimo);
				Menu.MenuPrincipal();
			}
		}
		if (valor == 2) {
			System.out.println("Digite o CPF que deseja buscar: ");
			String nomeParaBuscar = input.nextLine();
			emprestimo = emprestimoDao.encontrarPeloNome(nomeParaBuscar);
			if (emprestimo.getCliente() == null) {
				System.out.println("Não existe cliente com este CPF/CNPJ");
				menuEmprestimo();
			} else {
				System.out.println(emprestimo);
				Menu.MenuPrincipal();
			}
		} else {
			System.out.println("Valor invalido. Tente denovo");
			menuEmprestimo();
		}
		return emprestimo;
	}

	private static Emprestimo cadastrarEmprestimo(Scanner input, Emprestimo emprestimo, EmprestimoDao emprestimoDao,
			ClienteDaoJdbcImpl clienteDao) throws Exception {
		Cliente cliente;
		System.out.println("Digite os dados do novo emprestimo");
		try {
			System.out.println("Nome do cliente: ");
			String nomeParaBuscar = input.nextLine();

			System.out.println("CPF/CNPJ do cliente");
			String cpfCnpjParaBuscar = input.nextLine();
			cliente = clienteDao.encontrarPeloNomeECpfCnpj(nomeParaBuscar, cpfCnpjParaBuscar);
			emprestimo.setCliente(cliente);

			System.out.println("Tipo do emprestimo: ");
			String tipoEmprestimo = input.nextLine();
			tipoEmprestimo = tipoEmprestimo.toUpperCase();
			switch (tipoEmprestimo) {
			case "PESSOAL":
				emprestimo.setTipoBem(TipoEmprestimo.P);
				break;
			case "IMOVEL":
				emprestimo.setTipoBem(TipoEmprestimo.I);
				break;
			case "VEICULO":
				emprestimo.setTipoBem(TipoEmprestimo.V);
				break;
			default:
				System.out.println("Tipo de emprestimo invalido. Tente novamente.");
				menuEmprestimo();
				break;
			}
			System.out.println("Valor do emprestimo: ");
			emprestimo.setValor(Double.parseDouble(input.nextLine()));

			System.out.println("Cadastrando emprestimo...");
			emprestimo = emprestimoDao.cadastrarEmprestimo(emprestimo);
			System.out.println("Emprestimo cadastrado co sucesso!!!");
			Menu.MenuPrincipal();
		} catch (Exception e) {
			System.err.println("\nHouve alguma falha ao tentar inserir Emprestimo." + e);
			System.out.println("Vamos tentar novamente.");
			menuEmprestimo();
		}
		return emprestimo;
	}
}
