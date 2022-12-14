package appconsole;

import modelo.*;
import regras_negocio.Fachada;

public class Cadastrar {
	
	public Cadastrar(){
		try {
			System.out.println("cadastrando...");
			Fachada.inicializar();
			
			Fachada.criarAluno("Pedro", "123", "0001");
			Fachada.criarCurso("Java", 100);
			Curso c = Fachada.localizarCurso("Java");
			Fachada.criarModulo("Modulo 1", c);
		} catch (Exception e) 	{
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("fim do programa");
	}

	//=================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
	
	



}


