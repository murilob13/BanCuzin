package menu;

import java.util.List;
import java.util.Scanner;

import agencia.Agencia;
import agencia.AgenciaDaoJdbcImpl;
import agencia.ImportarAgencia;

public class MenuAgencia {

	public static void menuAgencia() throws Exception {

		String opcao = "X";
		Scanner input = new Scanner(System.in);
		Agencia agencia = new Agencia();
		AgenciaDaoJdbcImpl agenciaDao = new AgenciaDaoJdbcImpl();
		int valor = 0;

		do {

			System.out.println("\nDigite um comando para prosseguir:");
			System.out.println("C - Cadastrar uma agencia");
			System.out.println("B - Buscar por uma agencia especifica");
			System.out.println("A - Atualiziar os dados de uma agencia");
			System.out.println("D - Deletar uma agência");
			System.out.println("L - Listar todas as agencias");
			System.out.println("I - Importar agencias");
			System.out.println("V - Voltar ao menu principal");

			opcao = input.nextLine();
			opcao = opcao.toUpperCase();

			switch (opcao) {
			case "C":
				System.out.println("\nDigite os dados da nova agencia:");
				try {
					System.out.print("Nome: ");
					String nome = input.nextLine();
					agencia.setNome(nome);

					System.out.print("Codigo: ");
					try {
						int codigo = Integer.parseInt(input.nextLine());
						agencia.setCodigo(codigo);
					} catch (Exception e) {
						System.err.println("\nCodigo Invalido.");
						throw e;
					}

					System.out.print("Endereco: ");
					String endereco = input.nextLine();
					agencia.setEndereco(endereco);

					System.out.print("Gerente: ");
					String gerente = input.nextLine();
					agencia.setGerente(gerente);

					System.out.println("\nInserindo agencia...");
					agencia = agenciaDao.cadastrarAgencia(agencia);
					System.out.println("Agencia " + agencia.getNome() + " inserida com sucesso!");

				} catch (Exception e) {
					System.err.println("\nHouve alguma falha ao tentar inserir a Agencia.");
					System.out.println("Vamos tentar novamente.");
				}

				Menu.MenuPrincipal();
				break;

			case "B":

				while (valor != 1 && valor != 2) {
					System.out.println("Vc deseja buscar por nome (1) ou por codigo (2)?");
					valor = Integer.parseInt(input.nextLine());

					if (valor == 1) {
						System.out.print("Digite o nome que deseja buscar: ");
						String nomeParaBuscar = input.nextLine();
						agencia = agenciaDao.encontrarPeloNome(nomeParaBuscar);

						if (agencia.getNome() == null) {
							System.out.println("Não axiste agencia com este nome.");
							menuAgencia();
						} else {
							System.out.println("Dados da agencia:");
							System.out.println("Nome: " + agencia.getNome());
							System.out.println("Codigo: " + agencia.getCodigo());
							System.out.println("Endereco: " + agencia.getEndereco());
							System.out.println("Gerente: " + agencia.getGerente());
							Menu.MenuPrincipal();
						}
					}
					if (valor == 2) {
						System.out.print("Digite o codigo que deseja buscar: ");
						int codigoParaBusca = Integer.parseInt(input.nextLine());
						agencia = agenciaDao.encontrarPeloCodigo(codigoParaBusca);

						if (agencia.getCodigo() == 0) {
							System.out.println("Não existe agencia com este codigo.");
							menuAgencia();
						} else {
							System.out.println("Dados da agencia:");
							System.out.println("Nome: " + agencia.getNome());
							System.out.println("Codigo: " + agencia.getCodigo());
							System.out.println("Endereco: " + agencia.getEndereco());
							System.out.println("Gerente: " + agencia.getGerente());
							Menu.MenuPrincipal();
						}

					} else {
						System.out.println("Valor invalido. Tente denovo");
					}
				}

				break;

			case "A":

				while (valor != 1 && valor != 2) {

					System.out.println("Para atualiziar uma agencia selecione uma das opçõpes:");
					System.out.println("Buscar por nome (1) por codigo (2)?");
					valor = Integer.parseInt(input.nextLine());

					if (valor == 1) {

						System.out.print("Digite o nome que deseja buscar: ");
						String nomeParaBuscar = input.nextLine();
						agencia = agenciaDao.encontrarPeloNome(nomeParaBuscar);

						if (agencia.getNome() == null) {
							System.out.println("Não axiste agencia com este nome.");
							menuAgencia();
						} else {
							System.out.println("Vc escolheu a agencia: " + agencia.getNome());
							int codAgencia = agencia.getCodigo();

							System.out.println("Digite os novos dados:");

							try {
								System.out.print("Nome: ");
								String nome = input.nextLine();
								agencia.setNome(nome);

								System.out.print("Codigo: ");
								try {
									int codigo = Integer.parseInt(input.nextLine());
									agencia.setCodigo(codigo);
								} catch (Exception e) {
									System.err.println("\nCodigo Invalido.");
									throw e;
								}

								System.out.print("Endereco: ");
								String endereco = input.nextLine();
								agencia.setEndereco(endereco);

								System.out.print("Gerente: ");
								String gerente = input.nextLine();
								agencia.setGerente(gerente);

								System.out.println("\nAtualizando agencia...");
								agenciaDao.atualizarAgencia(codAgencia, agencia);
								System.out.println("Agencia " + agencia.getNome() + " atualizada com sucesso!");

								Menu.MenuPrincipal();

							} catch (Exception e) {
								System.err.println("\nHouve alguma falha ao tentar atualziar a Agencia.");
								System.out.println("Vamos tentar novamente.");
							}
						}

					}
					if (valor == 2) {

						System.out.print("Digite o codigo que deseja buscar: ");
						int codigoParaBuscar = Integer.parseInt(input.nextLine());
						agencia = agenciaDao.encontrarPeloCodigo(codigoParaBuscar);

						if (agencia.getCodigo() == 0) {
							System.out.println("Não existe agencia com este codigo.");
							menuAgencia();

						} else {
							System.out.println("Vc escolheu a agencia: " + agencia.getNome());
							int codAgencia = agencia.getCodigo();

							System.out.println("Digite os novos dados:");

							try {
								System.out.print("Nome: ");
								String nome = input.nextLine();
								agencia.setNome(nome);

								System.out.print("Codigo: ");
								try {
									int codigo = Integer.parseInt(input.nextLine());
									agencia.setCodigo(codigo);
								} catch (Exception e) {
									System.err.println("\nCodigo Invalido.");
									throw e;
								}

								System.out.print("Endereco: ");
								String endereco = input.nextLine();
								agencia.setEndereco(endereco);

								System.out.print("Gerente: ");
								String gerente = input.nextLine();
								agencia.setGerente(gerente);

								System.out.println("\nAtualizando agencia...");
								agenciaDao.atualizarAgencia(codAgencia, agencia);
								System.out.println("Agencia " + agencia.getNome() + " atualizada com sucesso!");

								Menu.MenuPrincipal();

							} catch (Exception e) {
								System.err.println("\nHouve alguma falha ao tentar atualziar a Agencia.");
								System.out.println("Vamos tentar novamente.");
							}
						}
					} else {
						System.out.println("Valor invalido. Tente denovo");
					}
				}

				Menu.MenuPrincipal();
				break;

			case "D":
				while (valor != 1 && valor != 2) {
					System.out.println("Deletar uma agencia por nome(1) ou por codigo(2)?");
					valor = Integer.parseInt(input.nextLine());

					if (valor == 1) {
						System.out.println("Digite o nome da agencia:");
						String nomeParaBuscar = input.nextLine();
						agencia = agenciaDao.encontrarPeloNome(nomeParaBuscar);
						if (agencia.getNome() == null) {
							System.out.println("Não axiste agencia com este nome.");
							menuAgencia();
						} else {
							agenciaDao.removerAgencia(agencia);
							System.out.println("Agencia: " + agencia.getNome() + " removida com sucesso.");
							Menu.MenuPrincipal();
						}

					}
					if (valor == 2) {
						System.out.println("Digite o codigo da agencia:");
						int codigoParaBusca = Integer.parseInt(input.nextLine());
						agencia = agenciaDao.encontrarPeloCodigo(codigoParaBusca);
						if (agencia.getCodigo() == 0) {
							System.out.println("Não existe agencia com este codigo.");
							menuAgencia();
						} else {
							agenciaDao.removerAgencia(agencia);
							System.out.println("Agencia: " + agencia.getNome() + " removida com sucesso");
							Menu.MenuPrincipal();
						}

					} else {
						System.out.println("Valor invalido, tente novamente.");
					}
				}

				break;

			case "L":

				System.out.println("Lista das agencias cadastradas:");
				List<Agencia> listAgencias = agenciaDao.listarTodasAgencias();

				if (listAgencias.isEmpty()) {
					System.out.println("Não existe agencias cadastradas.");
					menuAgencia();
				} else {
					System.out.println("NOME | CODIGO | ENDERECO | GERENTE");
					for (Agencia objAgencia : listAgencias) {
						System.out.println(objAgencia.getNome() + " | " + objAgencia.getCodigo() + " | "
								+ objAgencia.getEndereco() + " | " + objAgencia.getGerente());
					}
					Menu.MenuPrincipal();
				}

				break;

			case "I":
				
				System.out.println("Digite o caminho do arquivo da agencia: ");
				String caminho = input.nextLine();
				try {
					ImportarAgencia.agenciaImporter(caminho);
				} catch (Exception e) {
					System.err.println("Falha ao encontrar arquivo: "+e);
				}

				break;

			case "V":
				System.out.println("\nVoltando pro menu principal.");
				Menu.MenuPrincipal();
				break;

			default:
				System.out.println("\nOpção invalida. Tente novamente. ");
				menuAgencia();
				break;
			}

		} while (opcao != "V");

		input.close();
	}

}
