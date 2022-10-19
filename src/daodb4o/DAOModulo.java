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

import modelo.Modulo;

public class DAOModulo  extends DAO<Modulo>{

	//nome � usado como campo unico 
	public Modulo read (Object chave) {
		String nome = (String) chave;	//casting para o tipo da chave
		Query q = manager.query();
		q.constrain(Modulo.class);
		q.descend("nome").constrain(nome);
		List<Modulo> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}

	/**********************************************************
	 * 
	 * TODAS AS CONSULTAS
	 * 
	 **********************************************************/

	public List<Modulo> consultarModulosComAteNAulas(int n) {
		Query q = manager.query();
		q.constrain(Modulo.class);
		q.constrain(new FiltroModulo(n));
		return q.execute();
	}
}

@SuppressWarnings("serial")
class FiltroModulo  implements Evaluation {
	private int n;
	public FiltroModulo (int n) {
		this.n = n;
	}

	public void evaluate(Candidate candidate) {
		Modulo m = (Modulo) candidate.getObject();
		candidate.include( m.getAulas().size() < n );
	}
}


