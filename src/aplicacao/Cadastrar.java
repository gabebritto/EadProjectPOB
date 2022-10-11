package aplicacao;

import com.db4o.ObjectContainer;

import modelo.*;



public class Cadastrar {
	private ObjectContainer manager;

	public Cadastrar(){
		manager = Util.conectarDb4oAgendaLocal();
		cadastrar();
		manager.close();
		System.out.println("fim do programa");
	}

	public void cadastrar(){
		System.out.println("Cadastrando...");

		Aluno aluno1 = new Aluno("pedro", "123", "1000");
		Professor professor1 = new Professor("João", "150", 2000);
		
		Curso curso = new Curso("Java", 1000);
		Modulo modulo = new Modulo("Primeiro Modulo", curso);
		Aula aula = new Aula("Aula 1", 10, professor1, modulo);
		
		//Inserir curso no aluno
		aluno1.adicionarCurso(curso);
		//Inserir Aluno no Curso
		curso.adicionarAluno(aluno1);
		//Inserir modulo no Curso
		curso.adicionarModulo(modulo);
		//Inserir aula no modulo
		modulo.adicionarAula(aula);
		//Criar uma View para aula
		aula.assistirAula(aluno1.getMatricula(), aula.getNome());
		//Adicionar Aula ao professor
		professor1.adicionarAulaProfessor(aula);
		
		//persistir aluno
		manager.store(aluno1);
		manager.commit();

		//persistir professor
		manager.store(professor1);
		manager.commit();	
	
		//persistir curso
		manager.store(curso);
		manager.commit();

		//persistir modulo
		manager.store(modulo);
		manager.commit();

		//persistir aula
		manager.store(aula);
		manager.commit();

	}

	//=================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
}


