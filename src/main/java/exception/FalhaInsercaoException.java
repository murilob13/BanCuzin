package exception;

//import java.sql.SQLException;

public class FalhaInsercaoException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FalhaInsercaoException(Exception e) {
		super(e);
		
	}
}
