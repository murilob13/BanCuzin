package agencia;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import connection.CarregarDados;

public class ImportarAgencia {

	public static void agenciaImporter(String caminho) throws IOException {
		CarregarDados qndThread = new CarregarDados();

		try {
			File file = new File(caminho);
			List<String> linhas = FileUtils.readLines(file, "UTF-8");

			 ThreadAgencia thread = new ThreadAgencia(linhas, file);
			 thread.start();
			
			/*
			ThreadAgencia thread[] = new ThreadAgencia[qndThread.qntThread];

			// Criando e instanciando as threads
			for (int i = 0; i < qndThread.qntThread; i++) {
				 thread[i] = new ThreadAgencia(i);
				 thread[i] = new ThreadAgencia(linhas, file);
			}

			// Disparando as threads
			for (int i = 0; i < qndThread.qntThread; i++) {
				thread[i].start();
			}*/

		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
