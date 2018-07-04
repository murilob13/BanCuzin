package agencia;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import exception.FalhaInsercaoException;

public class AgenciaDaoJdbcImplTest {
	
	AgenciaDaoJdbcImpl sut;
	Agencia agencia;
	@Before
	public void setUp() throws SQLException{
		sut = new AgenciaDaoJdbcImpl();
		agencia = new Agencia();
		agencia.setNome("Agencia Rua 3");
		agencia.setCodigo(66);
		agencia.setEndereco("Rua 3, 559, Setor Central");
		agencia.setGerente("João das Neves");		
	}
	
	@Test
	public void cadastrarAgenciaTest() throws SQLException {

		
		try {
			sut.cadastrarAgencia(agencia);
		} catch (FalhaInsercaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void removerAgenciaTest() throws SQLException {
		
		sut.removerAgencia(agencia);
		
	}

}
