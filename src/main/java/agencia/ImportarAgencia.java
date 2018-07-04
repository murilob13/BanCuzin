package agencia;

import java.io.File;
import java.io.IOException;
//import java.util.List;

//import org.apache.commons.io.FileUtils;

import connection.CarregarDados;

public class ImportarAgencia {

	/**
	 * Responsavel por instanciar as threads das agencias.
	 * 
	 * @param caminho local do arquivo de importacao das agencias
	 * @throws IOException exeption lancada quando alguma falha de io
	 * 
	 */
	
	public static void agenciaImporter(String caminho) throws IOException, InterruptedException {
		CarregarDados qndThread = new CarregarDados();
		qndThread.carregarDadosDoProperties();

		File file = new File(caminho);			
		
		/*ThreadAgencia thread = new ThreadAgencia(linhas, file);
		thread.start();*/

		ThreadAgencia thread[] = new ThreadAgencia[qndThread.qntThread];

		// Criando e instanciando as threads
		for (int i = 0; i < qndThread.qntThread; i++) {
			thread[i] = new ThreadAgencia(file);
		}
		// Disparando as threads
		for (int i = 0; i < qndThread.qntThread; i++) {
			thread[i].join();
		}
	}

}