/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package daodb4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;

import modelo.Modulo;
import modelo.Aluno;
import modelo.Curso;

public class Util {
	public static ObjectContainer conectarDb4oLocal(){
		//---------------------------------------
		//configurar e conectar/criar banco local
		//---------------------------------------
		
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);  // mensagens na tela 0(desliga),1,2,3...
		
		// habilitar cascata na altera��o, remo��o e leitura
		config.common().objectClass(Aluno.class).cascadeOnDelete(false);;
		config.common().objectClass(Aluno.class).cascadeOnUpdate(true);;
		config.common().objectClass(Aluno.class).cascadeOnActivate(true);
		config.common().objectClass(Curso.class).cascadeOnDelete(false);;
		config.common().objectClass(Curso.class).cascadeOnUpdate(true);;
		config.common().objectClass(Curso.class).cascadeOnActivate(true);
		config.common().objectClass(Modulo.class).cascadeOnDelete(false);;
		config.common().objectClass(Modulo.class).cascadeOnUpdate(true);;
		config.common().objectClass(Modulo.class).cascadeOnActivate(true);		
		
		// nivel de profundidade do grafo para leitura e atualiza��o
		config.common().objectClass(Aluno.class).updateDepth(5);
		config.common().objectClass(Aluno.class).minimumActivationDepth(5);
		config.common().objectClass(Curso.class).updateDepth(5);
		config.common().objectClass(Curso.class).minimumActivationDepth(5);
		config.common().objectClass(Modulo.class).updateDepth(5);
		config.common().objectClass(Modulo.class).minimumActivationDepth(5);
		
		//conexao local
		ObjectContainer manager = Db4oEmbedded.openFile(config, "banco.db4o");
		return manager;
	}

	
	public static ObjectContainer conectarDb4oRemoto(){		
		//---------------------------------------
		//configurar e conectar/criar banco remoto
		//---------------------------------------

		ClientConfiguration config = Db4oClientServer.newClientConfiguration( ) ;
		config.common().messageLevel(0);  // 0,1,2,3...

		config.common().objectClass(Aluno.class).cascadeOnDelete(false);;
		config.common().objectClass(Aluno.class).cascadeOnUpdate(true);;
		config.common().objectClass(Aluno.class).cascadeOnActivate(true);
		config.common().objectClass(Curso.class).cascadeOnDelete(false);;
		config.common().objectClass(Curso.class).cascadeOnUpdate(true);;
		config.common().objectClass(Curso.class).cascadeOnActivate(true);
		config.common().objectClass(Modulo.class).cascadeOnDelete(false);;
		config.common().objectClass(Modulo.class).cascadeOnUpdate(true);;
		config.common().objectClass(Modulo.class).cascadeOnActivate(true);	

		// nivel de profundidade do grafo para leitura e atualiza��o
		config.common().objectClass(Aluno.class).updateDepth(5);
		config.common().objectClass(Aluno.class).minimumActivationDepth(5);
		config.common().objectClass(Curso.class).updateDepth(5);
		config.common().objectClass(Curso.class).minimumActivationDepth(5);
		config.common().objectClass(Modulo.class).updateDepth(5);
		config.common().objectClass(Modulo.class).minimumActivationDepth(5);
		
		//Conex�o cliente/servidor
		ObjectContainer manager = Db4oClientServer.openClient(config,"localhost",34000,"usuario1","senha1");
		//ObjectContainer manager = Db4oClientServer.openClient(config,"34.207.139.216",34000,"usuario1","senha1");
		return manager;
	}

}
