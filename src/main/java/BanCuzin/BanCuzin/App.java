package BanCuzin.BanCuzin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import Conection.AgenciaDaoJdbcImpl;
import Conection.CarregarDados;


public class App 
{
    

	public static void main( String[] args ) throws Exception
    {
		Scanner input = new Scanner(System.in);
    	   	   	
    	/*
    	CarregarDados dadosDeAcesso = new CarregarDados();    	
    	dadosDeAcesso.dadosDeAcesso();
    	
    	System.out.println(dadosDeAcesso.url);
    	System.out.println(dadosDeAcesso.usuario);
    	System.out.println(dadosDeAcesso.senha);
    	
    	Connection connection = null;
    	
    	try{
    		connection = AgenciaDaoJdbcImpl.getConnection();
    		if (connection != null) {
				System.out.println("Conexão com o banco estabeleida.");
			}
    	} catch (Exception e){
    		e.printStackTrace();
    	} finally {
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
    	*/
    	AgenciaDaoJdbcImpl agencia = new AgenciaDaoJdbcImpl();
    	
    	
    	
    	//System.out.println("Digite o nome para realizar a busca:");
    	//String nome = input.nextLine();
    	//agencia.encontrarPeloNome(nome);
    	
    	
    	//System.out.println("Digite o codigo da agencia que vc deseja buscar:");
    	//int codigo = input.nextInt();
    	//agencia.encontrarPeloCodigo(codigo);
    	
    	System.out.println("Deseja realizar a busca por nome(1), ou por codigo(2)?");
    	int tBusca = input.nextInt();
    	agencia.atualizarAgencia(tBusca);
    	
        //AgenciaDaoJdbcImpl.criarTabelaAgencia();
    	//AgenciaDaoJdbcImpl.cadastrarAgencia();
    	

    }
}
