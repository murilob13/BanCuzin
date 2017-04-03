package BanCuzin.BanCuzin;

import agencia.ImportarAgencia;
import menu.Menu;


public class App {

	public static void main(String[] args) throws Exception {
		
		System.out.println("Olá, bem vindo ao BanCuzin!");
		//Menu.MenuPrincipal();
		
		String caminho = "C:\\Users\\marco\\Dropbox\\OOBJ\\Curso Java\\BanCuzin\\agencia.txt";	
		
		ImportarAgencia.agenciaImporter(caminho);
		
	}
}
