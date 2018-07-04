package menu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import agencia.Agencia;
import agencia.AgenciaDaoJdbcImpl;
import cliente.Cliente;
import cliente.ClienteDaoJdbcImpl;
import conta.Conta;
import conta.ContaDaoJdbcImpl;
import conta.ImportarConta;
import conta.Plano;
import conta.TipoConta;

public class MenuConta {

	public static void menuConta() throws Exception {

		String opcao = "X";
		Scanner input = new Scanner(System.in);
		Conta conta = new Conta();
		AgenciaDaoJdbcImpl agenciaDao = new AgenciaDaoJdbcImpl();
		ClienteDaoJdbcImpl clienteDao = new ClienteDaoJdbcImpl();
		ContaDaoJdbcImpl contaDao = new ContaDaoJdbcImpl();
		int valor = 0;

		do {
			opcao = JOptionPane.showInputDialog("\n**MENU CONTA**\n\nDigite um comando para prosseguir\nC - Cadastrar uma conta\nB - Buscar por uma conta específica\nA - Atualizar os dados de uma conta\nD - Deletar uma conta\nL - Listar todas as contas\nI - Importar contas\nV - Voltar ao menu principal");
			opcao = opcao.toUpperCase();

			switch (opcao) {

			case "C":

				conta = cadastrarConta(input, conta, agenciaDao, clienteDao, contaDao);
				break;

			case "B":

				while (valor != 1 && valor != 2) {
					System.out.println("Deseja realizar a busca Pelo nome do Cliente(1) ou pelo numero da Conta(2)?");
					valor = Integer.parseInt(input.nextLine());
					if (valor == 1) {
						buscarPorNomeDoCliente(input, contaDao);
					}
					if (valor == 2) {
						conta = buscarPeloNumConta(input, contaDao);
					} else {
						System.out.println("Opção invalida, tente denovo.");
					}
				}
				break;

			case "A":

				conta = atualizarConta(input, agenciaDao, clienteDao, contaDao);
				break;

			case "D":

				conta = removerConta(input, contaDao);
				break;

			case "L":
				
				listarContas(contaDao);
				break;
				
			case "I":

				importarContas(input);
				break;
			case "V":
				System.out.println("\nVoltando pro menu principal.");
				Menu.MenuPrincipal();
				break;
			default:
				System.out.println("\nOpção invalida. Tente novamente. ");
				menuConta();
				break;
			}

		} while (opcao != "V");

		input.close();
	}

	private static void listarContas(ContaDaoJdbcImpl contaDao) throws SQLException, Exception {
		List<Conta> listContas = contaDao.listarTodasContas();
		if (listContas.isEmpty()) {
			System.out.println("Não há nenhuma agencia cadastrada.");
			menuConta();
		} else {
			System.out.println("Lista de todas as contas:");
			System.out.println("--------------------------------");
			for (Conta contas : listContas) {
				System.out.println(contas);
				System.out.println("--------------------------------");
			}
		}
		Menu.MenuPrincipal();
	}

	private static Conta removerConta(Scanner input, ContaDaoJdbcImpl contaDao) throws SQLException, Exception {
		Conta conta;
		System.out.println("Para deletar uma conta, digite o codigo da agencia e numero da conta:");
		System.out.println("Codigo da agencia:");
		int codAgencia = Integer.parseInt(input.nextLine());
		System.out.println("Numero da Conta:");
		String numConta = input.nextLine();
		conta = contaDao.encontrarPeloNumECodigoAg(codAgencia, numConta);
		if (conta == null) {
			System.out.println("Esta conta ainda não foi cadastrada.");
		} else {
			contaDao.removerConta(numConta, codAgencia);
			System.out.println("Conta de numero: " + conta.getNumConta() + " removida com sucesso.");
			Menu.MenuPrincipal();
		}
		return conta;
	}

	private static void importarContas(Scanner input) throws IOException, InterruptedException {
		System.out.println("Informe o caminho do arquivo contas:");
		String caminho = input.nextLine();
		ImportarConta.contaImporter(caminho);
	}

	private static Conta atualizarConta(Scanner input, AgenciaDaoJdbcImpl agenciaDao, ClienteDaoJdbcImpl clienteDao,
			ContaDaoJdbcImpl contaDao) throws SQLException, Exception {
		Conta conta;
		Agencia agencia;
		Cliente cliente;
		System.out.println("Para atualzar digite o no codigo da agencia e numero da conta:");
		System.out.println("Codigo da agencia:");
		int codAgencia = Integer.parseInt(input.nextLine());
		System.out.println("Numero da Conta:");
		String numConta = input.nextLine();
		conta = contaDao.encontrarPeloNumECodigoAg(codAgencia, numConta);
		if (conta == null) {
			System.out.println("Esta conta ainda não foi cadastrada.");
		} else {
			try {

				System.out.println("Codigo da agencia:");
				int codigoParaBusca = Integer.parseInt(input.nextLine());
				agencia = agenciaDao.encontrarPeloCodigo(codigoParaBusca);
				conta.setAgencia(agencia);

				System.out.println("Digite o numero da conta:");
				conta.setNumConta(input.nextLine());

				System.out.println("Digite o nome do cliente:");
				String nomeParaBuscar = input.nextLine();
				cliente = clienteDao.encontrarPeloNome(nomeParaBuscar);
				conta.setCliente(cliente);

				System.out.println("Digite o Tipo da Conta:");
				String tipoConta = input.nextLine();
				tipoConta = tipoConta.toUpperCase();
				switch (tipoConta) {
				case "CONTA CORRENTE":
					conta.setTipoConta(TipoConta.CC);
					break;
				case "CONTA SALARIO":
					conta.setTipoConta(TipoConta.CS);
					break;
				case "CONTA POUPANCA":
					conta.setTipoConta(TipoConta.CP);
					break;
				default:
					System.out.println("Tipo da conta invalido tente novamente.");
					menuConta();
					break;
				}

				System.out.println("Digite o Plano: ");
				conta.setPlano(Plano.valueOf(input.nextLine()));
				String tipoPlano = input.nextLine();
				tipoPlano = tipoPlano.toUpperCase();
				switch (tipoPlano) {
				case "GOLD":
					conta.setPlano(Plano.G);
					break;
				case "SILVER":
					conta.setPlano(Plano.S);
					break;
				case "DIAMOND":
					conta.setPlano(Plano.D);
					break;
				default:
					System.out.println("Plano invalido, tente novamente (GOLD, SILVER e DIAMOND");
					menuConta();
					break;
				}

				System.out.println("Digite o saldo:");
				conta.setSaldo(Double.parseDouble(input.nextLine()));

				System.out.println("Digite o limite: ");
				conta.setLimite(Double.parseDouble(input.nextLine()));

				System.out.println("Atualziando conta...");
				contaDao.atualizarConta(numConta, codAgencia, conta);
				System.out.println("Conta atualziada");
				Menu.MenuPrincipal();
			} catch (Exception e) {
				System.err.println("\nHouve alguma falha ao tentar inserir a Agencia.");
				System.out.println("Vamos tentar novamente.");
				menuConta();
			}
		}
		return conta;
	}

	private static Conta buscarPeloNumConta(Scanner input, ContaDaoJdbcImpl contaDao) throws SQLException, Exception {
		Conta conta;
		System.out.println("Digite o numero da Conta:");
		String numeroParaBuscar = input.nextLine();
		conta = contaDao.encontrarPeloNumero(numeroParaBuscar);
		if (conta.getIdConta() == 0) {
			System.out.println("Não existe conta com este numero.");
		} else {
			System.out.println("Dados da Conta");
			System.out.println(conta);
			Menu.MenuPrincipal();
		}
		return conta;
	}

	private static void buscarPorNomeDoCliente(Scanner input, ContaDaoJdbcImpl contaDao)
			throws SQLException, Exception {
		Conta conta;
		System.out.println("Digite o nome do Cliente:");
		String nomeParaBuscar = input.nextLine();
		conta = contaDao.encontrarPeloNome(nomeParaBuscar);
		if (conta.getCliente() == null) {
			System.out.println("Não existe conta para este cliente.");
		} else {
			System.out.println("Dados da Conta:");
			System.out.println(conta);
			Menu.MenuPrincipal();
		}
	}

	private static Conta cadastrarConta(Scanner input, Conta conta, AgenciaDaoJdbcImpl agenciaDao,
			ClienteDaoJdbcImpl clienteDao, ContaDaoJdbcImpl contaDao) throws Exception {
		Agencia agencia;
		Cliente cliente;
		System.out.println("Digite os dados da Conta");
		try {
			System.out.println("Codigo da agencia:");
			int codigoParaBusca = Integer.parseInt(input.nextLine());
			agencia = agenciaDao.encontrarPeloCodigo(codigoParaBusca);
			conta.setAgencia(agencia);

			System.out.println("Digite o numero da conta:");
			conta.setNumConta(input.nextLine());

			System.out.println("Digite o nome do cliente:");
			String nomeParaBuscar = input.nextLine();
			cliente = clienteDao.encontrarPeloNome(nomeParaBuscar);
			conta.setCliente(cliente);

			System.out.println("Digite o Tipo da Conta:");
			String tipoConta = input.nextLine();
			tipoConta = tipoConta.toUpperCase();
			switch (tipoConta) {
			case "CONTA CORRENTE":
				conta.setTipoConta(TipoConta.CC);
				break;
			case "CONTA SALARIO":
				conta.setTipoConta(TipoConta.CS);
				break;
			case "CONTA POUPANCA":
				conta.setTipoConta(TipoConta.CP);
				break;
			default:
				System.out.println("Tipo da conta invalido tente novamente.");
				menuConta();
				break;
			}

			System.out.println("Digite o Plano: ");
			conta.setPlano(Plano.valueOf(input.nextLine()));
			String tipoPlano = input.nextLine();
			tipoPlano = tipoPlano.toUpperCase();
			switch (tipoPlano) {
			case "GOLD":
				conta.setPlano(Plano.G);
				break;
			case "SILVER":
				conta.setPlano(Plano.S);
				break;
			case "DIAMOND":
				conta.setPlano(Plano.D);
				break;
			default:
				System.out.println("Plano invalido, tente novamente (GOLD, SILVER e DIAMOND");
				menuConta();
				break;
			}

			System.out.println("Digite o saldo:");
			conta.setSaldo(Double.parseDouble(input.nextLine()));

			System.out.println("Digite o limite: ");
			conta.setLimite(Double.parseDouble(input.nextLine()));

			System.out.println("Inserindo conta...");
			conta = contaDao.cadastrarConta(conta);
			System.out.println("Conta cadastrada com sucesso.");
			Menu.MenuPrincipal();
		} catch (Exception e) {
			System.err.println("\nHouve alguma falha ao tentar inserir a Agencia.");
			System.out.println("Vamos tentar novamente.");
			menuConta();
		}
		return conta;
	}
}
