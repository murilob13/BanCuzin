package menu;

import java.util.Scanner;


public class Menu {

	
	public static void MenuPrincipal() throws Exception{
		String opcao = "X";
		Scanner input = new Scanner(System.in);
		
		do {
			System.out.println("\nDigite um comando para prosseguir:");
			System.out.println("A - Gerenciar agencias");
			System.out.println("B - Gerenciar clientes");
			System.out.println("C - Gerenciar contas");
			System.out.println("D - Gerenciar empréstimos/financiamentos");
			System.out.println("S - S para sair");
			
			opcao = input.nextLine();
			opcao = opcao.toUpperCase();
			
			switch (opcao) {
			case "A":
				MenuAgencia.menuAgencia();
				break;
				
			case "B":
				System.out.println("Menu clientes ainda esta em construção");
				break;
			case "C":
				System.out.println("Menu contas ainda esta em construção");
				break;
			case "D":
				System.out.println("Menu emprestimos/financiamentos ainda esta em contrução");
				break;
			case "S":
				 System.out.println("Voce escolheu saido do programa. Até mais.");
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
