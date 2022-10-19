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

import modelo.Aula;

public class DAOAula  extends DAO<Aula>{
	
	//nome � usado como campo unico 
	public Aula read (Object chave) {
		String nome = (String) chave;	//casting para o tipo da chave
		Query q = manager.query();
		q.constrain(Aula.class);
		q.descend("nome").constrain(nome);
		List<Aula> resultados = q.execute();
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

	// Buscar Aula por quantidade de alunos.
	
	public List<Aula> consultarAulaNAlunos(int n){
		Query q = manager.query();
		q.constrain(Aula.class);
		q.constrain(new FiltroAula(n));
		return q.execute();
	}
}

@SuppressWarnings("serial")
class FiltroAula implements Evaluation {
	private int n;
	public FiltroAula (int n) {
		this.n=n;
	}

	public void evaluate(Candidate candidate) {
		Aula a = (Aula) candidate.getObject();
		candidate.include( a.getAlunos().size()== n );
	}
}