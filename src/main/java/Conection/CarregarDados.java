package Conection;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class CarregarDados {
	
	public String url, usuario, senha;
	
		
	public CarregarDados dadosDeAcesso () throws Exception{
	
	CarregarDados dadosDeAcesso = new CarregarDados();
		
	Properties prop = new Properties();
	
	InputStream in = new FileInputStream ("bancuzin.properties");
	prop.load(in);
	
	this.url = prop.getProperty("URL");
	this.usuario = prop.getProperty("USUARIO");
	this.senha = prop.getProperty("SENHA");
	
	return dadosDeAcesso;
	
	}

		
	
}
