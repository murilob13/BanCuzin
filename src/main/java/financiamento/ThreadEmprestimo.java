package financiamento;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cliente.Cliente;
import cliente.ClienteDaoJdbcImpl;

public class ThreadEmprestimo extends Thread {

	private List<String> linhas;
	File file;
	private Emprestimo emprestimo = new Emprestimo();
	private EmprestimoDaoJdbcImpl emprestimoDao = new EmprestimoDaoJdbcImpl();
	private Cliente cliente = new Cliente();
	private ClienteDaoJdbcImpl clienteDao = new ClienteDaoJdbcImpl();

	public ThreadEmprestimo(List<String> linhas, File file) {
		this.linhas = linhas;
		this.file = file;
	}

	public ThreadEmprestimo(File file) {
		try {
			linhas = FileUtils.readLines(file, "UTF-8");
			start();
		} catch (Exception e) {
			System.out.println("Falha ao ler arquivo " + e);
		}
	}

	private void inserirNoBanco(List<String> linhas) throws SQLException {

		for (String linha : linhas) {

			if (linha != null) {
				String[] dado = linha.split("[;]");

				emprestimo.setIdEmprestimo(Integer.parseInt(dado[0]));
				cliente = clienteDao.encontrarPeloId(Integer.parseInt(dado[1]));
				emprestimo.setCliente(cliente);
				emprestimo.setTipoBem(TipoEmprestimo.valueOf(dado[2]));
				emprestimo.setValor(Double.parseDouble(dado[3]));

				try {
					emprestimoDao.cadastrarEmprestimo(emprestimo);
					System.out
							.println("Emprestimo do cliente " + emprestimo.getCliente().getNome() + " foi importado.");
				} catch (Exception e) {
					System.err.println("Falha ao importar emprestimo " + emprestimo.getIdEmprestimo());
				}
			}
		}
	}

	public void run() {

		try {
			inserirNoBanco(linhas);
		} catch (Exception e) {
			System.err.println("Falha ao inserir emprestimo " + e);
		}
	}
}
