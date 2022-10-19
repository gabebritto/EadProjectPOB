/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daodb4o;


import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Curso;

public class DAOCurso  extends DAO<Curso>{
	
	//nome � usado como campo unico 
	public Curso read (Object chave) {
		String nome = (String) chave;	//casting para o tipo da chave
		Query q = manager.query();
		q.constrain(Curso.class);
		q.descend("nome").constrain(nome);
		List<Curso> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	//public List<Curso> readAll(String texto){
	//	manager.ext().purge();  	//limpar cache do manager
	//	Query q = manager.query();
	//	q.constrain(Curso.class);
	//	q.descend("nome").constrain(texto).like();	//nome like texto
	//	return q.execute();
	//}


	/**********************************************************
	 * 
	 * TODAS AS CONSULTAS 
	 * 
	 **********************************************************/

	// Buscar curso por quantidade de alunos.
	
	public List<Curso> consultarCursoNAlunos(int n){
		Query q = manager.query();
		q.constrain(Curso.class);
		q.constrain(new Filtro(n));
		return q.execute();
	}
}

@SuppressWarnings("serial")
class Filtro  implements Evaluation {
	private int n;
	public Filtro (int n) {
		this.n=n;
	}

	public void evaluate(Candidate candidate) {
		Curso c = (Curso) candidate.getObject();
		candidate.include( c.getAlunos().size()== n );
	}
}