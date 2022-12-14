

package daojpa;

import java.util.List;


import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import modelo.Modulo;

public class DAOModulo extends DAO<Modulo>{

	public Modulo read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Modulo> q = manager.createQuery("select m from Modulo m where m.nome = :n", Modulo.class);
			q.setParameter("n", nome);
			Modulo p =  q.getSingleResult();
			return p;
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Modulo> consultarModulosComAteNAulas(int n){
		try{
			TypedQuery<Modulo> q = manager.createQuery("select m from Modulo m where SIZE(m.aulas_modulo) <= :n", Modulo.class);
			q.setParameter("n", n);
			List<Modulo> c =  q.getResultList();
			return c;
		}catch(NoResultException e){
			return null;
		}
	}

}

