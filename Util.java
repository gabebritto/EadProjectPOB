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

import modelo.Autor;
import modelo.Livro;

public class Util {

	public static ObjectContainer conectarDb4oAgendaLocal(){
		//---------------------------------------
		//configurar e conectar/criar banco local
		//---------------------------------------
		
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);  // mensagens na tela 0(desliga),1,2,3...
		
		// habilitar cascata na alteração, remoção e leitura
		config.common().objectClass(Livro.class).cascadeOnDelete(false);;
		config.common().objectClass(Livro.class).cascadeOnUpdate(true);;
		config.common().objectClass(Livro.class).cascadeOnActivate(true);
		config.common().objectClass(Autor.class).cascadeOnDelete(false);;
		config.common().objectClass(Autor.class).cascadeOnUpdate(true);;
		config.common().objectClass(Autor.class).cascadeOnActivate(true);
		
		// criar indices (opcional) sobre campos de busca
		config.common().objectClass(Livro.class).objectField("tirulo").indexed(true);
		config.common().objectClass(Autor.class).objectField("nome").indexed(true);
		
		// nivel de profundidade do grafo para leitura e atualização
		config.common().objectClass(Livro.class).updateDepth(5);
		config.common().objectClass(Livro.class).minimumActivationDepth(5);
		config.common().objectClass(Autor.class).updateDepth(5);
		config.common().objectClass(Autor.class).minimumActivationDepth(5);
		//conexao local
		ObjectContainer manager = Db4oEmbedded.openFile(config, "banco.db4o");
		return manager;
	}

	
	
}
