/**********************************
 * IFPB - SI
 * Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package regras_negocio;

import java.util.List;

import daodb4o.DAO;
import daodb4o.DAOAluno;
import daodb4o.DAOCurso;
import daodb4o.DAOModulo;
import modelo.Aluno;
import modelo.Curso;
import modelo.Modulo;

public class Fachada {
	private Fachada() {}		

	private static DAOAluno daoaluno = new DAOAluno();  
	private static DAOModulo daomodulo = new DAOModulo(); 
	private static DAOCurso daocurso = new DAOCurso();  

	public static void inicializar(){
		DAO.open();
	}
	public static void finalizar(){
		DAO.close();
	}
	
	public static List<Aluno> listarAlunos() 	{
		DAO.begin();
		List<Aluno> result = daoaluno.readAll();
		DAO.commit();
		return result;
	}
	public static List<Curso> listarCursos() {
		DAO.begin();
		List<Curso> result = daocurso.readAll();
		DAO.commit();
		return result;
	}
	public static List<Modulo> listarModulos() {
		DAO.begin();
		List<Modulo> result = daomodulo.readAll();
		DAO.commit();
		return result;
	}
	public static Curso localizarCurso(String nome) {
		DAO.begin();
		Curso c = daocurso.read(nome);
		DAO.commit();
		return c;
	}
	public static Aluno localizarAluno(int id) 	{
		DAO.begin();
		Aluno a = daoaluno.read(id);
		DAO.commit();
		return a;
	}
	public static Modulo localizarModulo(String data) 	{
		DAO.begin();
		Modulo m = daomodulo.read(data);
		DAO.commit();
		return m;
	}

	public static Curso criarCurso(String nome, int preco) throws Exception {
		nome = nome.trim();


		DAO.begin();
		//localizar Curso no repositorio, usando o nome 
		Curso c = daocurso.read(nome);
		if (c!=null) {
			DAO.rollback();
			throw new Exception("Não criou! Curso " + nome + " - ja cadastrado(a)");
		}
		if (preco <0) {
			DAO.rollback();
			throw new Exception("Não criou Curso: " + nome + " - preco não pode ser negativo " + preco);
		}

		//criar objeto Curso
		c = new Curso (nome, preco);

		//criar curso no reposit�rio
		daocurso.create(c);
		DAO.commit();
		return c;	
	}	

	public static Modulo criarModulo(String nome, Curso curso) throws Exception {
		nome = nome.trim();

		DAO.begin();
		// Localizar modulo no repositorio, usando o nome 
		Modulo m = daomodulo.read(nome);
		if (m!=null) {
			DAO.rollback();
			throw new Exception("Não criou Modulo " + nome + " - ja cadastrado(a)");
		}
		// Curso é obrigatório 
		if (curso == null) {
			DAO.rollback();
			throw new Exception("Não criou Modulo " + nome + " - Curso é obrigatório");
		}
		//criar objeto Modulo
		m = new Modulo (nome, curso);

		//criar Modulo no repositório
		daomodulo.create(m);
		DAO.commit();
		return m;	
	}

	public static Aluno criarAluno (String nome, String cpf, String matricula) throws Exception {
		nome = nome.trim();
		cpf = cpf.trim();

		DAO.begin();
		//localizar Aluno no repositorio, usando cpf 
		Aluno a = daoaluno.readByCpf(cpf);
		if (a!=null) {
			DAO.rollback();
			throw new Exception("Não criou Aluno: " + nome + " - ja existe Aluno com cpf " + cpf);
		}
		a = daoaluno.consultarMatriculaAluno(matricula);
		if (a!=null) {
			DAO.rollback();
			throw new Exception("Não criou Aluno: " + nome + " - ja existe Aluno com matricula " + matricula);
		}
		//gerar id no repositorio
		a = new Aluno(nome, cpf, matricula);	

		//adicionar evento no reposit�rio
		daoaluno.create(a);
		//gravar reposit�rio
		DAO.commit();
		return a;
	}

	public static void 	adicionarAlunoCurso(String nome, int id) throws Exception {
		nome = nome.trim();

		DAO.begin();
		//localizar curso no repositorio, usando o nome 
		Curso c = daocurso.read(nome);
		if(c == null)  {
			DAO.rollback();
			throw new Exception("Não adicionou aluno. " + nome + " - inexistente");
		}

		//localizar aluno no repositorio, usando id 
		Aluno a = daoaluno.read(id);
		if(a == null)  {
			DAO.rollback();
			throw new Exception("Não adicionou aluno. "+nome+ " - Aluno " + id + " inexistente");
		}

		//localizar o aluno dentro do curso, usando o nome
		Aluno alunoBuscado = c.localizar(a.getNome());
		if(alunoBuscado != null) {
			DAO.rollback(); 
			throw new Exception("Não adicionou aluno. " + alunoBuscado.getNome() + " - já participa do Curso " + nome);
		}
		//adicionar o aluno ao Curso
		c.adicionarAluno(a);
		//adicionar o curso ao Aluno
		a.adicionarCurso(c);

		//atualizar objetos no banco
		daocurso.update(c);
		daoaluno.update(a);
		DAO.commit();
	}
	

	public static void 	removerAlunoCurso(String nome, int id) throws Exception {
		nome = nome.trim();

		DAO.begin();
		//localizar curso no repositorio, usando o nome 
		Curso c = daocurso.read(nome);
		if(c == null)  {
			DAO.rollback();
			throw new Exception("Não removeu Aluno. " + nome + " - inexistente");
		}

		//localizar aluno no repositorio, usando id 
		Aluno a = daoaluno.read(id);
		if(a == null)  {
			DAO.rollback();
			throw new Exception("Não removeu Aluno. Curso " + nome +" - Aluno " + id + " inexistente");
		}

		//localizar o aluno dentro do curso, usando o nome
		Aluno alunoARemover = c.localizar(a.getNome());
		if(alunoARemover == null)  {
			DAO.rollback();
			throw new Exception("Não removeu Aluno. " + a.getNome() + " - não participa do curso " + c.getNome());
		}
		//remover o aluno do curso
		c.removerAluno(a);
		//remover o curso do aluno
		a.removerCurso(c);

		//atualizar objetos no banco
		daocurso.update(c);
		daoaluno.update(a);
		DAO.commit();
	}
	
	public static void 	removerModuloCurso(String nome, String nomeCurso) throws Exception {
		nome = nome.trim();
		nomeCurso = nomeCurso.trim();
		
		DAO.begin();
		//localizar curso no repositorio, usando o nome 
		Curso c = daocurso.read(nomeCurso);
		if(c == null)  {
			DAO.rollback();
			throw new Exception("Não removeu Modulo. " + nomeCurso + " - inexistente");
		}

		//localizar modulo no repositorio, usando nome
		Modulo m = daomodulo.read(nome);
		if(m == null)  {
			DAO.rollback();
			throw new Exception("Não removeu modulo. Curso " + nomeCurso +" - Modulo " + nome + " inexistente");
		}

		//localizar o aluno dentro do curso, usando o nome
		Modulo moduloARemover = c.localizarModulo(m.getNome());
		if(moduloARemover == null)  {
			DAO.rollback();
			throw new Exception("Não removeu Modulo. " + m.getNome() + " - não existe no curso " + c.getNome());
		}
		//remover o Modulo do curso
		c.removerModulo(m);
		//remover o curso do modulo
		m.removerCurso();

		//atualizar objetos no banco
		daocurso.update(c);
		daomodulo.update(m);
		DAO.commit();
	}
	
	

	public static void apagarCurso(String nome) throws Exception	{
		nome = nome.trim();

		DAO.begin();
		//localizar curso no repositorio, usando nome
		Curso c = daocurso.read(nome);
		if (c == null) {
			DAO.rollback();
			throw new Exception("Não deletou evento " + nome + " - inexistente");
		}
		
		//Remover este curso de todos os alunos e modulos, além de atualizar 
		for(Aluno a : c.getAlunos()) {
			a.removerCurso(c);
			daoaluno.update(a);
		}
		
		for(Modulo m : c.getModulos()) {
			m.removerCurso();
			daomodulo.update(m);
		}
		
		//apagar curso no banco
		daocurso.delete(c);
		DAO.commit();
	}

	public static void ajustarPrecoCurso(String nome, int novoPreco) throws Exception	{
		nome = nome.trim();

		DAO.begin();
		//localizar evento no repositorio, usando data 
		Curso c = daocurso.read(nome);
		if (c == null) {
			DAO.rollback();
			throw new Exception("Não alterou o preço - " + nome +"  inexistente ");
		}
		//alterar a data do evento
		c.setPreco(novoPreco);

		//atualizar no banco
		daocurso.update(c);
		DAO.commit();
	}

	public static void 	apagarAluno(int id) throws Exception {
		DAO.begin();
		//localizar Aluno no repositorio, usando o id 
		Aluno a = daoaluno.read(id);
		if(a == null)  {
			DAO.rollback();
			throw new Exception("Não deletou aluno " + id + " - inexistente");
		}
		//Aluno nao pode ser deletado caso participe de algum Curso
		if(!a.getCursos().isEmpty())  {
			DAO.rollback();
			throw new Exception("Não deletou Aluno " + a.getNome() + " - ja tem evento");
		}
		//apagar no banco
		daoaluno.delete(a);
		DAO.commit();
	}
	
	public static void 	apagarModulo(String nome) throws Exception {
		nome = nome.trim();
		
		DAO.begin();
		//localizar Modulo no repositorio, usando nome 
		Modulo m = daomodulo.read(nome);
		if(m == null)  {
			DAO.rollback();
			throw new Exception("Não deletou Modulo " + nome + " - inexistente");
		}
		//Modulo nao pode ser deletado caso seja de algum Curso
		if(m.getCurso() == null)  {
			DAO.rollback();
			throw new Exception("Não deletou Modulo " + m.getNome() + " - pertence a um Curso");
		}
		//apagar no banco
		daomodulo.delete(m);
		DAO.commit();
	}

	
	/*
	 * CONSULTAS
	 */
	
	public static List<Modulo> consultarModulosNAulas(int n){
		return daomodulo.consultarModulosComAteNAulas(n);
	}

	public static List<Curso> consultarCursoNAlunos(int n){
		return daocurso.consultarCursoNAlunos(n);
	}
	
	public static int consultarTotalAlunos() {
		return daoaluno.consultarTotalAlunos();
	}

}
