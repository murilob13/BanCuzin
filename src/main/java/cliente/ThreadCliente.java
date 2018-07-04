package cliente;

import java.io.File;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class ThreadCliente extends Thread {

	List<String> linhas;
	File file;
	int qntThread;

	public ThreadCliente(List<String> linhas, File file) {
		this.linhas = linhas;
		this.file = file;
	}

	public ThreadCliente(File file) {
		try {
			linhas = FileUtils.readLines(file, "UTF-8");
			start();
		} catch (Exception e) {
			System.out.println("Falha ao ler arquivo:" + e);
		}
	}

	private void inserirNoBanco(List<String> linhas) throws ParseException, SQLException {
		ClienteDaoJdbcImpl clienteDao = new ClienteDaoJdbcImpl();
		DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

		for (String linha : linhas) {
			Cliente cliente = null;
			if (linha != null) {
				String[] dado = linha.split("[;]");

				// Convertendo de String para Date
				Date dataf = formatoData.parse(dado[4]);
				// Convertendo para o tipo sql.Date (formato: yyyy-MM-dd)
				java.sql.Date dataSql = new java.sql.Date(dataf.getTime());

				cliente = new Cliente(Integer.parseInt(dado[0]), dado[1], dado[2], dado[3], dataSql, dado[5]);
			}

			try {
				clienteDao.cadastrarCliente(cliente);
				System.out.println(cliente.getNome() + " importado.");
			} catch (Exception e) {
				System.err.println("Falha ao inserir"+cliente.getNome());
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
