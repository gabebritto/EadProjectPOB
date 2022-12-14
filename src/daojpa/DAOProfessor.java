

package daojpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import modelo.Professor;

public class DAOProfessor extends DAO<Professor>{

	public Professor read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Professor> q = manager.createQuery("select p from Professor p where p.nome = :n", Professor.class);
			q.setParameter("n", nome);
			Professor p =  q.getSingleResult();
			return p;
		}catch(NoResultException e){
			return null;
		}
	}
	

}

