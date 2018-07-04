package conta;

import java.io.File;
import java.io.IOException;

import connection.CarregarDados;

public class ImportarConta {

	public static void contaImporter(String caminho) throws IOException, InterruptedException {
		CarregarDados qndThread = new CarregarDados();
		qndThread.carregarDadosDoProperties();

		File file = new File(caminho);

		ThreadConta thread[] = new ThreadConta[qndThread.qntThread];

		// Criando e instanciando as threads
		for (int i = 0; i < qndThread.qntThread; i++) {
			thread[i] = new ThreadConta(file);
		}
		// Disparando as threads
		for (int i = 0; i < qndThread.qntThread; i++) {
			thread[i].join();
		}
	}
}
