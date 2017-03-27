package Conection;


import java.awt.List;

import Agencia.Agencia;

public interface AgenciaDao {
	
	Agencia cadastrarAgencia (Agencia agencia) throws Exception;
	
	Agencia encontrarPeloNome (String nome);
	
	Agencia encontrarPeloCodigo (int codigo);
	
	Agencia atualizarAgencia (int tBusca);
	
	Agencia removerAgencia (Agencia agencia);
	
	List listarTods();
	

}
