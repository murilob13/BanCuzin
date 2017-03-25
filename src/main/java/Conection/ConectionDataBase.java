package Conection;

import java.io.InputStream;
import java.util.Properties;

public class ConectionDataBase {
	
	Properties prop = new Properties();

	InputStream in = getClass().getResourceAsStream("conection.properties");
	
	
}
