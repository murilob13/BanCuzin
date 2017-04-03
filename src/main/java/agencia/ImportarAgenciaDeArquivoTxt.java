package agencia;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ImportarAgenciaDeArquivoTxt {
	public static ArrayList<Agencia> getListAgenciaFromTextFile(String filePath) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader bReader = null;
		ArrayList<Agencia> listResult = new ArrayList<Agencia>();
		try {
			fis = new FileInputStream(filePath);
			isr = new InputStreamReader(fis);
			bReader = new BufferedReader(isr);
			String line = null;
			String[]vetorAgencia = null;
			
			while(true) {
				line = bReader.readLine(); 
				if(line == null) {
					break;
				} else {
					vetorAgencia = line.split(",");
					listResult.add(new Agencia(Integer.parseInt(vetorAgencia[0]), vetorAgencia[1], Integer.parseInt(vetorAgencia[2]), vetorAgencia[3], vetorAgencia[4]));
				}
			}
			
		} catch (Exception e) {
			System.out.println("Falha ao ler arquivo");
			e.printStackTrace();
		} finally {
			try {
				bReader.close();
				isr.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listResult;
	}
}
