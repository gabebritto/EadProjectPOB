package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import modelo.Autor;
import modelo.Livro;


public class Deletar {
	protected ObjectContainer manager;

	public Deletar(){
		manager = Util.conectarDb4oAgendaLocal();
		apagar();
		manager.close();
		
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
	}

	public void apagar(){
		Query q = manager.query();
		q.constrain(Livro.class);  				
		q.descend("titulo").constrain("java");		 
		List<Livro> resultados = q.execute(); 
	
		if(resultados.size()>0) {
			Livro livro =  resultados.get(0);
			
			//remover manualmente o relacionamento dos autores para livro java
			for(Autor a : livro.getAutores()) {
				a.remover(livro);
				manager.store(a);
				if(a.getLivros().isEmpty())
					manager.delete(a);	//apagar o autor caso esteja orfão
			}
			
			manager.delete(livro);
			manager.commit();
			System.out.println("apagou livro");
		}
		else
			System.out.println("maria inexistente");
	}



	//=================================================
	public static void main(String[] args) {
		new Deletar();
	}
}

