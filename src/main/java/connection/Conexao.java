package connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
/**
 * @author Marcos
 * 
 * Responsavel por carregar os dados do banco de acordo com o que foi encontrado no properties.
 * apos carregar os dados a conexao com o banco deve ser estabelecida.
 * 
 */
	static CarregarDados dadosDeAcesso = new CarregarDados();
	static Connection connection = null;

	public static Connection getConnection() throws Exception {
		try {
			dadosDeAcesso.carregarDadosDoProperties();

			connection = DriverManager.getConnection(dadosDeAcesso.url, dadosDeAcesso.usuario, dadosDeAcesso.senha);
		} catch (IOException e1) {
			System.err.println("Falha ao ler o arquivo de propriedades" + e1.getMessage());
		} catch (SQLException e) {
			System.err.println("Falha ao conectar no banco" + e);
		}
		return connection;
	}
}
