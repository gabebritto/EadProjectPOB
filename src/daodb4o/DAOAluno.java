/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Aluno;


public class DAOAluno  extends DAO<Aluno>{
	
	// busca pelo ID
	public Aluno read (Object chave) {
		int id = (Integer) chave;
		Query q = manager.query();
		q.constrain(Aluno.class);
		q.descend("id").constrain(id);
		List<Aluno> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	public Aluno readByCpf (Object chave) {
		String cpf = (String) chave;
		Query q = manager.query();
		q.constrain(Aluno.class);
		q.descend("cpf").constrain(cpf);
		List<Aluno> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	public Aluno consultarMatriculaAluno(String matricula) {
		Query q = manager.query();
		q.constrain(Aluno.class);
		q.descend("matricula").constrain(matricula);
		List<Aluno> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	public int consultarTotalAlunos() {
		Query q = manager.query();
		q.constrain(Aluno.class);
		return q.execute().size();
	}
	
	public void create(Aluno aluno) {
		int novoid = super.gerarId();
		aluno.setId(novoid);
		super.create(aluno);
	}
}

