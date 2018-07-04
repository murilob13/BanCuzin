package connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CarregarDados {
	
	/**
	 * @author Marcos
	 * 
	 * Responsavel por carregar os dados do banco do arquivo properties.
	 * 
	 */
	
	public String url, usuario, senha;
	public int qntThread;
	
		
	public CarregarDados carregarDadosDoProperties() throws IOException {
	
		CarregarDados dadosDeAcesso = new CarregarDados();

		Properties prop = new Properties();

		InputStream in = new FileInputStream("bancuzin.properties");
		prop.load(in);

		this.url = prop.getProperty("URL");
		this.usuario = prop.getProperty("USUARIO");
		this.senha = prop.getProperty("SENHA");
		this.qntThread = Integer.parseInt(prop.getProperty("QUANTIDADE_THREAD"));

		return dadosDeAcesso;
	
	}		
	
}
