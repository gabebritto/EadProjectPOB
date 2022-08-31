package modelo;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa {
	private String matricula;
	private List<Curso> cursos = new ArrayList<>();
		
	public Aluno(String nome, String cpf, String matricula) {
		super(nome, cpf);
		this.matricula = matricula;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public String setMatricula(String novaMatricula) {
		return matricula = novaMatricula;
	}
	public List<Curso> getCursos() {
		return cursos;
	}
	
	public List<Curso> setCursos(List<Curso> novosCursos) {
		return cursos = novosCursos;
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