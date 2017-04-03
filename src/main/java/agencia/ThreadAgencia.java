package agencia;

import java.io.File;
//import java.sql.SQLException;
import java.util.List;

import exception.FalhaInsercaoException;

//import exception.FalhaInsercaoException;

public class ThreadAgencia extends Thread {

	List<String> linhas;
	File file;
	int qntThread;

	public ThreadAgencia(List<String> linhas, File file) {
		this.linhas = linhas;
		this.file = file;
	}

	public ThreadAgencia(int qntThread) {
		this.qntThread = qntThread;
	}

	public void run() {

		Agencia agencia = new Agencia();
		AgenciaDaoJdbcImpl agenciaJDBC = new AgenciaDaoJdbcImpl();

		for (String linha : linhas) {

			if (linha != null) {

				String[] dado = linha.split("[;]");

				for (String a : dado) {
					// socorro

				}
			}

			// inserindo o objeto agencia no banco
			try {
				System.out.println("Cheguei nessa porra");
				agenciaJDBC.cadastrarAgencia(agencia);
			} catch (FalhaInsercaoException e) {
				e.printStackTrace();
			}
		}

	}

}
