package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Aluno;

public class DAOAluno  extends DAO<Aluno>{

	public Aluno read (Object chave){
		try{
			String id = (String) chave;
			TypedQuery<Aluno> q = manager.createQuery("select a from Aluno a where a.id = :n ", Aluno.class);
			q.setParameter("n", id);

			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public Aluno readByCpf (Object chave){
		try{
			String cpf = (String) chave;
			TypedQuery<Aluno> q = manager.createQuery("select a from Aluno a where a.cpf = :n ", Aluno.class);
			q.setParameter("n", cpf);

			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public Aluno consultarMatriculaAluno(String matricula) {
		try{
			String mat = (String) matricula;
			TypedQuery<Aluno> q = manager.createQuery("select a from Aluno a where a.matricula = :n ", Aluno.class);
			q.setParameter("n", mat);

			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	@Override
	public List<Aluno> readAll(){
		TypedQuery<Aluno> query;
		query = manager.createQuery("select a from Aluno a", Aluno.class);
		return query.getResultList();
	}

}
