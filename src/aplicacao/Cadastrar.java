package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import com.db4o.ObjectContainer;

import modelo.Autor;
import modelo.Livro;


public class Cadastrar {
	private ObjectContainer manager;

	public Cadastrar(){
		manager = Util.conectarDb4oAgendaLocal();
		cadastrar();
		manager.close();
		System.out.println("fim do programa");
	}

	public void cadastrar(){
		System.out.println("Cadastrando...");

		Livro livro1, livro2, livro3, livro4;
		Autor autor1,autor2, autor3, autor4;

		autor1= new Autor("joao");
		autor2= new Autor("maria");
		autor3= new Autor("paulo");
		autor4= new Autor("antonio");

		livro1 = new Livro("java", 10, 2016);
		livro1.adicionar(autor1);
		livro1.adicionar(autor2);

		livro2 = new Livro("c", 10, 2015);
		livro2.adicionar(autor1);
		livro2.adicionar(autor3);

		livro3 = new Livro("php", 10, 2016);
		livro3.adicionar(autor1);
		livro3.adicionar(autor4);

		livro4 = new Livro("c#", 10, 2014);		//sem autor

		autor1.adicionar(livro1);
		autor1.adicionar(livro2);
		autor1.adicionar(livro3);
		autor2.adicionar(livro2);
		autor3.adicionar(livro3);

		//persistir livro1
		manager.store(livro1);
		manager.commit();

		//persistir livro2
		manager.store(livro2);
		manager.commit();

		//persistir livro3
		manager.store(livro3);
		manager.commit();	

		//persistir livro4
		manager.store(livro4);	
		manager.commit();	
	}

	//=================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
}


