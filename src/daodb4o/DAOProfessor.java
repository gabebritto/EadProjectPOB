/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Professor;


public class DAOProfessor  extends DAO<Professor>{
	
	// busca pelo ID
	public Professor read (Object chave) {
		int id = (Integer) chave;
		Query q = manager.query();
		q.constrain(Professor.class);
		q.descend("id").constrain(id);
		List<Professor> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	public Professor readByCpf (Object chave) {
		String cpf = (String) chave;
		Query q = manager.query();
		q.constrain(Professor.class);
		q.descend("cpf").constrain(cpf);
		List<Professor> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	public int consultarTotalAlunos() {
		Query q = manager.query();
		q.constrain(Professor.class);
		return q.execute().size();
	}
	
	public void create(Professor professor) {
		int novoid = super.gerarId();
		professor.setId(novoid);
		super.create(professor);
	}
}

