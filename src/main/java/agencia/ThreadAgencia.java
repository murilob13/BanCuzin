package agencia;

import java.io.File;
//import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import exception.FalhaInsercaoException;

//import exception.FalhaInsercaoException;

public class ThreadAgencia extends Thread {

	List<String> linhas;
	File file;
	
	public ThreadAgencia(List<String> linhas, File file) {
		this.linhas = linhas;
		this.file = file;
	}

	public ThreadAgencia(File file) {
		try {
			linhas = FileUtils.readLines(file, "UTF-8");
			start();
		} catch (Exception e) {
			System.err.println("Falha ao ler arquivo:" + e);
		}
	}

	private void inserirNoBanco(List<String> linhas) {
		AgenciaDaoJdbcImpl agenciaDAO = new AgenciaDaoJdbcImpl();

		for (String linha : linhas) {
			Agencia agencia = null;
			if (linha != null) {
				String[] dado = linha.split("[;]");
				agencia = new Agencia(Integer.parseInt(dado[0]), dado[1], Integer.parseInt(dado[2]), dado[3], dado[4]);
			}

			// inserindo o objeto agencia no banco
			try {
				agenciaDAO.cadastrarAgencia(agencia);
				System.out.println("A Agencia " + agencia.getNome() + " foi importada!");
			} catch (FalhaInsercaoException e) {
				System.out.println("Falha ao inserir "+agencia.getNome()+e);
			}
		}
	}

	public void run() {

		try {
			inserirNoBanco(linhas);
		} catch (Exception e) {
			System.err.println("Falha ao inserir no banco." + e);
		}

	}

}
