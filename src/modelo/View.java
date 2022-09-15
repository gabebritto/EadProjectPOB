package modelo;


public class View {
	private String matricula_aluno;
	private String aula_nome;
	
	public View(String matricula_aluno, String aula_nome) {
		this.matricula_aluno = matricula_aluno;
		this.aula_nome = aula_nome;
	}
	
	public String getMatriculaAluno() {
		return matricula_aluno;
	}
	
	public String getAulaNome() {
		return aula_nome;
	}
	
	public String toString() {
		return getAulaNome() + " - " + getMatriculaAluno();
	}
}
