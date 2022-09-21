package aplicacao;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.*;


public class Consultar {
	protected ObjectContainer manager;

	public Consultar(){
		manager = Util.conectarDb4oAgendaLocal();
		consultar();
		manager.close();
		
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplica��o");
	}

	public void consultar(){

		// 1º Situação
		System.out.println("\n---listar aula com professor João");
		
		Query q = manager.query();
		q.constrain(Aula.class);  
		q.descend("professor").descend("nome").constrain("João");
		
		List<Aula> resultados = q.execute();
		for(Aula aula: resultados)
			System.out.println(aula);
		
		// 2º Situação
		System.out.println("\n----Listar Aluno com aula que tenha o professor joão");

		Query q2 = manager.query();
		q2.constrain(Aluno.class);
		q2.descend("cursos").descend("curso_modulos")
							.descend("aulas_modulo")
							.descend("professor")
							.constrain("João");
		
		List<Aluno> resultados2 = q2.execute();
		for( Aluno aluno : resultados2)
			System.out.println(aluno);
		

		System.out.println("\n----Listar Professor com modulo da aula chamado Primeiro Modulo");

		Query q3 = manager.query();
		q3.constrain(Professor.class);
		q3.descend("aulas_professor").descend("modulo")
										.descend("nome")
										.constrain("Primeiro Modulo");

		List<Professor> resultados3 = q3.execute();
		for (Professor p : resultados3)
			System.out.println(p);

		
	}
	
	public static void main(String[] args) {
		new Consultar();
	}
}

//classe interna 
class Filtro implements Evaluation {
	public void evaluate(Candidate candidate) {
		//destacar o objeto que esta sendo consultado no banco
		Aula aula = (Aula) candidate.getObject();
		
		if(aula.getProfessor() == null) 
			candidate.include(true); 	//incluir objeto no resultado da consulta
		else		
			candidate.include(false);	//excluir objeto do resultado da consulta
	}
}

