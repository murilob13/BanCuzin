package financiamento;

import java.io.File;
import java.io.IOException;

import connection.CarregarDados;

public class ImportarEmprestimo {

	public static void emprestimoImporter(String caminho) throws IOException, InterruptedException {
		CarregarDados qndThread = new CarregarDados();
		qndThread.carregarDadosDoProperties();

		File file = new File(caminho);

		ThreadEmprestimo thread[] = new ThreadEmprestimo[qndThread.qntThread];

		// Criando e instanciando as threads
		for (int i = 0; i < qndThread.qntThread; i++) {
			thread[i] = new ThreadEmprestimo(file);
		}
		// Disparando as threads
		for (int i = 0; i < qndThread.qntThread; i++) {
			thread[i].join();
		}

	}
}
