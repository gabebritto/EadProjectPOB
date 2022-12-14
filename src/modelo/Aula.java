package modelo;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
public class Aula {
	@Id
	private	String nome;
	
	private int duracao;
	
	@ManyToOne
	private Professor professor;
	
	@ManyToOne
	private Modulo modulo;
	
	//*:*
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private List<Aluno> alunos_aula = new ArrayList<>();
	
	private int views_aula;
	
	@Version
	private long versao;
	
	public Aula() {}
	
	public Aula(String nome, int duracao, Professor professor, Modulo modulo) {
		this.nome = nome;
		this.duracao = duracao;
		this.professor = professor;
		this.modulo = modulo;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo novoModulo) {
		this.modulo = novoModulo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String novoNome) {
		nome = novoNome;
	}
	
	public int getDuracao() {
		return duracao;
	}
	
	public void setDuracao(int novaDuracao) {
		duracao = novaDuracao;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public void setProfessor(Professor novoProfessor) {
		professor = novoProfessor;
	}
	
	public int getViews() {
		return views_aula;
	}
	
	public void assistirAula() {
		views_aula++;
	}
	
	public List<Aluno> getAlunos() {
		return alunos_aula;
	}

	public void setAlunos_aula(List<Aluno> alunos_aula) {
		this.alunos_aula = alunos_aula;
	}
	
	public void adicionarAluno(Aluno aluno) {
		alunos_aula.add(aluno);
	}
	
	public void removerAluno(Aluno aluno) {
		alunos_aula.remove(aluno);
	}
	
	public String toString() {
		String texto="Nome: " + getNome() + " Duração: " + getDuracao() + " Views: ";
		if (views_aula == 0)
			texto += "Sem Views";
		else 	
			texto += getViews();
		return texto;

	}


}
