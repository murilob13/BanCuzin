package cliente;

import java.io.File;
import java.io.IOException;

import connection.CarregarDados;

public class ImportarCliente {

	public static void clienteImoporter(String caminho) throws IOException, InterruptedException {
		CarregarDados qndThread = new CarregarDados();
		qndThread.carregarDadosDoProperties();
		File file = new File(caminho);

		ThreadCliente thread[] = new ThreadCliente[qndThread.qntThread];
		
		// Criando e instanciando as threads
		for (int i = 0; i < qndThread.qntThread; i++) {
			thread[i] = new ThreadCliente(file);
		}

		// Disparando as threads
		for (int i = 0; i < qndThread.qntThread; i++) {
			thread[i].join();
		}

	}

}
