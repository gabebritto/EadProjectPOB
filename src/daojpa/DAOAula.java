
package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Aula;

public class DAOAula extends DAO<Aula>{

	public Aula read (Object chave){
		try{
			String placa = (String) chave;
			TypedQuery<Aula> q = manager.createQuery("select a from Aula a where a.nome = :n", Aula.class);
			q.setParameter("n", placa);
			Aula p =  q.getSingleResult();
			return p;
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Aula> consultarAulaNAlunos(int n){
		try{
			TypedQuery<Aula> q = manager.createQuery("select a from Aula a where SIZE(a.alunos_aula) = :n", Aula.class);
			q.setParameter("n", n);
			List<Aula> p =  q.getResultList();
			return p;
		}catch(NoResultException e){
			return null;
		}
	}

}

