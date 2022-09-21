package aplicacao;


import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import modelo.*;


public class Deletar {
	protected ObjectContainer manager;

	public Deletar(){
		manager = Util.conectarDb4oAgendaLocal();
		apagar();
		manager.close();
		
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplica��o");
	}

	public void apagar(){
		Query q = manager.query();
		q.constrain(Aluno.class);  				
		q.descend("matricula").constrain("1000");		 
		List<Aluno> resultados = q.execute(); 
	
		if(resultados.size()>0) {
			Aluno aluno =  resultados.get(0);
			
			for(Curso c : aluno.getCursos()) {
				c.removerAluno(aluno);
				manager.store(c);
			}
			
			manager.delete(aluno);
			manager.commit();
			System.out.println("apagou Aluno");
		}
		else
			System.out.println("Aluno de matricula 1000 inexistente.");
	}



	//=================================================
	public static void main(String[] args) {
		new Deletar();
	}
}

