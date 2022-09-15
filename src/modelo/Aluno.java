package modelo;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa {
	private String matricula;
	private List<Curso> cursos = new ArrayList<Curso>();
	
	
	public Aluno(String nome, String cpf, String matricula) {
		super(nome, cpf);
		this.matricula = matricula;
	}

	public void adicionarCurso(Curso curso) {
		cursos.add(curso);
	}

	public void removerCurso(String cursoNome) {
		if (!cursos.isEmpty()) {
			for (Curso c : cursos) {
				if (c.getNome() == cursoNome) {
					cursos.remove(c);
				}
			}
		}
		else {
			System.out.println("Curso não encontrado!");
		}
	}

	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String novaMatricula) {
		matricula = novaMatricula;
	}
	public List<Curso> getCursos() {
		return cursos;
	}
	
	public void setCursos(List<Curso> novosCursos) {
		cursos = novosCursos;
	}

	@Override
	public String toString() {
		String texto = "CPF: " + getCpf() + " Nome: " + getNome() + " Matricula: " + getMatricula() + " Cursos: ";
		if (cursos.isEmpty())
			texto += "Sem Cursos";
		else 	
			for(Curso c: cursos) 
				texto += c.getNome() + " - " + c.getPreco() + ", "; 
		return texto;
	}
}