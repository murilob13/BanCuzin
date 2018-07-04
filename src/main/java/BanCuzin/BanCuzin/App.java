package BanCuzin.BanCuzin;

import javax.swing.JOptionPane;

import agencia.AgenciaDaoJdbcImpl;
import cliente.ClienteDaoJdbcImpl;
import conta.ContaDaoJdbcImpl;
import menu.Menu;



public class App {

	public static void main(String[] args) throws Exception {
		AgenciaDaoJdbcImpl criarTabelaAgencia = new AgenciaDaoJdbcImpl();
		criarTabelaAgencia.criarTabelaAgencia();
		ClienteDaoJdbcImpl criarTabelaCleinte = new ClienteDaoJdbcImpl();
		criarTabelaCleinte.criarTabelaCliente();
		ContaDaoJdbcImpl criarTabelaConta = new ContaDaoJdbcImpl();
		criarTabelaConta.criarTabelaConta();
		
		
		JOptionPane.showMessageDialog(null,"Olá, bem vindo ao Bancoobj!");
		Menu.MenuPrincipal();	
		
	}
}
