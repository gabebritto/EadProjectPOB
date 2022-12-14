

package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import modelo.Pessoa;

public class DAOPessoa extends DAO<Pessoa>{

	public Pessoa read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Pessoa> q = manager.createQuery("select p from Pessoa p where p.nome=:n",Pessoa.class);
			q.setParameter("n", nome);
			return q.getSingleResult();

		}catch(NoResultException e){
			return null;
		}
	}

	//  sobrescrever o metodo readAll da classe DAO 
	public List<Pessoa> readAll(){
		TypedQuery<Pessoa> q = manager.createQuery("select p from Pessoa p order by p.id", Pessoa.class);
		return  q.getResultList();
	}
	

}

