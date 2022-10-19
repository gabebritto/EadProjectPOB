package appconsole;


import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import modelo.*;

public class Listar {
	protected ObjectContainer manager;
	
	public Listar(){
		manager = Util.conectarDb4oAgendaLocal();
		listar();
		manager.close();
	}

	public void listar(){
		System.out.println("-------Lista de Alunos--------");
		Query q = manager.query();
		q.constrain(Aluno.class);  				
		List<Aluno> resultados = q.execute();
		for (Aluno aluno : resultados ) {
			System.out.println(aluno);
		}
		
		System.out.println("\n-------Lista de Cursos--------");
		Query q2 = manager.query();
		q2.constrain(Curso.class);  				
		List<Curso> resultados2 = q2.execute();
		for (Curso curso : resultados2 ) {
			System.out.println(curso);
		}
	}

	
	//=================================================
	public static void main(String[] args) {
		new Listar();
	}
}


