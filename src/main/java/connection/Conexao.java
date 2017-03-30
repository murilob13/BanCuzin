package connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	static CarregarDados dadosDeAcesso = new CarregarDados();
	static Connection connection = null;

	public static Connection getConnection() throws Exception {
		try {
			dadosDeAcesso.dadosDeAcesso();
			
			connection = DriverManager.getConnection(dadosDeAcesso.url, dadosDeAcesso.usuario, dadosDeAcesso.senha);
		} catch (IOException e1) {
			System.err.println("Falha ao ler o arquivo de propriedades" + e1.getMessage());
		} catch (SQLException e) {
			System.err.println("Falha ao conectar no banco" + e);
		}
		return connection;
	}

}
