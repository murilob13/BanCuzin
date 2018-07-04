package conta;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import agencia.Agencia;
import agencia.AgenciaDaoJdbcImpl;
import cliente.Cliente;
import cliente.ClienteDaoJdbcImpl;

public class ThreadConta extends Thread {

	private List<String> linhas;
	File file;
	private Conta conta = new Conta();
	private Agencia agencia = new Agencia();
	private Cliente cliente = new Cliente();
	private AgenciaDaoJdbcImpl agenciaDao = new AgenciaDaoJdbcImpl();
	private ClienteDaoJdbcImpl clienteDao = new ClienteDaoJdbcImpl();
	private ContaDaoJdbcImpl contaDAO = new ContaDaoJdbcImpl();

	public ThreadConta(List<String> linhas, File file) {
		this.linhas = linhas;
		this.file = file;
	}

	public ThreadConta(File file) {
		try {
			linhas = FileUtils.readLines(file, "UTF-8");
			start();
		} catch (Exception e) {
			System.err.println("Falha ao ler arquivo" + e);
		}
	}

	private void inserirNoBanco(List<String> linhas) throws SQLException {

		for (String linha : linhas) {

			if (linha != null) {
				String[] dado = linha.split("[;]");

				conta.setIdConta(Integer.parseInt(dado[0]));

				int idAgencia = Integer.parseInt(dado[1]);
				agencia = agenciaDao.encontrarPeloId(idAgencia);
				conta.setAgencia(agencia);

				int idCliente = Integer.parseInt(dado[2]);
				cliente = clienteDao.encontrarPeloId(idCliente);
				conta.setCliente(cliente);

				conta.setNumConta(dado[3]);

				/*
				 * switch (dado[4]) { case "CC":
				 * conta.setTipoConta(TipoConta.CC); break; case "CS":
				 * conta.setTipoConta(TipoConta.CS); break; case "CP":
				 * conta.setTipoConta(TipoConta.CP); break; default:
				 * conta.setTipoConta(TipoConta.CC); break; }
				 */
				conta.setTipoConta(TipoConta.valueOf(dado[4]));
				conta.setPlano(Plano.valueOf(dado[5]));
				/*
				 * switch (dado[5]) { case "G": conta.setPlano(Plano.G); break;
				 * case "S": conta.setPlano(Plano.S); break; case "D":
				 * conta.setPlano(Plano.D); break; default:
				 * conta.setPlano(Plano.S); break; }
				 */

				conta.setSaldo(Double.parseDouble(dado[6]));
				conta.setLimite(Double.parseDouble(dado[7]));

				try {
					contaDAO.cadastrarConta(conta);
					System.out.println("Conta " + conta.getIdConta() + " importada.");
				} catch (Exception e) {
					System.err.println("Falha ao inserir " + conta.getNumConta() + e);
				}
			}
		}
	}

	public void run() {

		try {
			inserirNoBanco(linhas);
		} catch (Exception e) {
			System.err.println("Falha ao inserir conta" + e);
		}
	}
}
