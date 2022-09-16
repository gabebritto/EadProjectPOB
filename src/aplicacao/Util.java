/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package aplicacao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import modelo.*;

public class Util {

	public static ObjectContainer conectarDb4oAgendaLocal(){
		//---------------------------------------
		//configurar e conectar/criar banco local
		//---------------------------------------
		
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);  // mensagens na tela 0(desliga),1,2,3...
		
		// habilitar cascata na altera��o, remo��o e leitura
		config.common().objectClass(Curso.class).cascadeOnDelete(false);;
		config.common().objectClass(Curso.class).cascadeOnUpdate(true);;
		config.common().objectClass(Curso.class).cascadeOnActivate(true);
		config.common().objectClass(Aluno.class).cascadeOnDelete(false);;
		config.common().objectClass(Aluno.class).cascadeOnUpdate(true);;
		config.common().objectClass(Aluno.class).cascadeOnActivate(true);
		
		// criar indices (opcional) sobre campos de busca
		config.common().objectClass(Curso.class).objectField("nome").indexed(true);
		config.common().objectClass(Aluno.class).objectField("nome").indexed(true);
		
		// nivel de profundidade do grafo para leitura e atualiza��o
		config.common().objectClass(Curso.class).updateDepth(5);
		config.common().objectClass(Curso.class).minimumActivationDepth(5);
		config.common().objectClass(Aluno.class).updateDepth(5);
		config.common().objectClass(Aluno.class).minimumActivationDepth(5);
		//conexao local
		ObjectContainer manager = Db4oEmbedded.openFile(config, "banco.db4o");
		return manager;
	}

	
	
}
