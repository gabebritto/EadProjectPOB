
package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Aluno;
import modelo.Curso;

public class DAOCurso extends DAO<Curso>{

	public Curso read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Curso> q = manager.createQuery("select c from Curso c where c.nome = :n", Curso.class);
			q.setParameter("n", nome);
			Curso p =  q.getSingleResult();
			return p;
		}catch(NoResultException e){
			return null;
		}
	}

	//--------------------------------------------
		//  consultas
		//--------------------------------------------

	public List<Curso> consultarCursoNAlunos(int n){
		try{
			TypedQuery<Curso> q = manager.createQuery("select c from Curso c where SIZE(c.curso_alunos) = :n", Curso.class);
			q.setParameter("n", n);
			List<Curso> c =  q.getResultList();
			return c;
		}catch(NoResultException e){
			return null;
		}
	}
	
	@Override
	public List<Curso> readAll(){
		TypedQuery<Curso> query;
		query = manager.createQuery("select c from Curso c order by c.nome", Curso.class);
		return  query.getResultList();
	}
}

