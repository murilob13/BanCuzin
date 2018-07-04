package menu;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Menu {

	public static void MenuPrincipal() throws Exception {
		String opcao = "X";
		Scanner input = new Scanner(System.in);

		do {
			opcao = JOptionPane.showInputDialog("**MENU PRINCIPAL**\n\nDigite um comando para prosseguir:\n\nA - Gerenciar agencias\nB - Gerenciar clientes\nC - Gerenciar contas\nD - Gerenciar empréstimos/financiamentos\nS - S para sair");
			opcao = opcao.toUpperCase();
			
			switch (opcao) {
			case "A":
				MenuAgencia.menuAgencia();
				break;

			case "B":
				MenuCliente.menuClient();
				break;
			case "C":
				MenuConta.menuConta();
				break;
			case "D":
				MenuEmprestimo.menuEmprestimo();
				break;
			case "S":
				JOptionPane.showMessageDialog(null, "Voce escolheu sair do do programa. Até mais.");
				System.exit(0);
				break;

			default:

				System.out.println("\nOpção invalida. Tente novamente. ");
				MenuPrincipal();
				break;
			}

		} while (opcao != "S");
		input.close();
	}

}
