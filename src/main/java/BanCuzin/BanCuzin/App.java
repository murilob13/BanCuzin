package BanCuzin.BanCuzin;

import java.util.Scanner;

import agencia.Agencia;
import agencia.AgenciaDaoJdbcImpl;

public class App {

	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		
		Agencia agencia = new Agencia();
		AgenciaDaoJdbcImpl agenciaDao = new AgenciaDaoJdbcImpl();
		agenciaDao.criarTabelaAgencia();
		
		
		System.out.println("Digite os dados da nova agencia:");

		System.out.println("Nome: ");
		String nome = input.nextLine();
		agencia.setNome(nome);

		System.out.println("Endereco: ");
		String endereco = input.nextLine();
		agencia.setEndereco(endereco);

		System.out.println("Gerente: ");
		String gerente = input.nextLine();
		agencia.setGerente(gerente);
		
		System.out.println("Codigo: ");
		int codigo = input.nextInt();
		agencia.setCodigo(codigo);
		
		
		input.close();
		try {
			agenciaDao.cadastrarAgencia(agencia);

		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}

	}
}
