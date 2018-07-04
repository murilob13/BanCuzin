package cliente;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;


public class ClientDaoJdbcImplTest {
	
	ClienteDaoJdbcImpl sut;
	Cliente cliente;
	@Before
	public void setUp() throws SQLException{
		sut = new ClienteDaoJdbcImpl();
		cliente = new Cliente();
		cliente.setNome("Aisten Da Silva");
		cliente.setSexo("Masculino");
		cliente.setCpfCnpj("999999999");
		cliente.setEndereco("Rua 88, 66 , Setor Sul, Goiania GO");
		try {
			cliente.setBirthDay(new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1989"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void cadastrarClienteTest() throws SQLException {
		
		sut.cadastrarCliente(cliente);
	}
	
	@Test
	public void removerClienteTest() throws SQLException {
		
		sut.removerCliente(cliente);
	}
	
	
	
	

}
