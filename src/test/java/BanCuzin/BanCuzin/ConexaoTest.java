package BanCuzin.BanCuzin;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;

import connection.Conexao;

public class ConexaoTest {

	private Connection conn;

	@Before
	public void setUp() throws SQLException{
		try {
			conn = Conexao.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(conn);
	}
}
