package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Aluno extends Pessoa {
	
	private String matricula;
	
	@ManyToMany(mappedBy="curso_alunos", 
			cascade={CascadeType.PERSIST,CascadeType.MERGE}) 	
	private List<Curso> cursos = new ArrayList<Curso>();
	
	@ManyToMany(mappedBy="alunos_aula", 
			cascade={CascadeType.PERSIST,CascadeType.MERGE}) 	
	private List<Aula> aulas = new ArrayList<Aula>();
	
	@Version
	private long versao;
	
	public Aluno() {}
	
	public Aluno(String nome, String cpf, String matricula) {
		super(nome, cpf);
		this.matricula = matricula;
	}
	

	public void adicionarCurso(Curso curso) {
		cursos.add(curso);
	}
	
	public void removerCurso(Curso curso) {
		cursos.remove(curso);
	}

	public Curso localizarCurso(String cursoNome) {
		if (!cursos.isEmpty()) {
			for (Curso c : cursos) {
				if (c.getNome().equals(cursoNome)) {
					return c;
				}
			}
		}
		return null;
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

	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}
	
	public void adicionarAula(Aula aula) {
		aulas.add(aula);
	}
	
	public void removerAula(String nomeModulo) {
		if (!aulas.isEmpty()) {
			for (Aula a : aulas) {
				if (a.getNome() == nomeModulo) {
					a.removerAluno(this);
					aulas.remove(a);
				}
			}
		}
		else {
			System.out.println("Curso não encontrado!");
		}
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