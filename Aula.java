package modelo;

import java.util.ArrayList;
import java.util.List;

public class Aula {
	private	String nome;
	private int duracao;
	private Professor professor;
	private List<View> views_aula = new ArrayList<>();
	
	public Aula(String nome, int duracao, Professor professor) {
		this.nome = nome;
		this.duracao = duracao;
		this.professor = professor;
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
	
	public List<View> getViews() {
		return views_aula;
	}
	
	public void assistirAula(String matricula_aluno, String aula_nome) {
		View novaView = new View(matricula_aluno, aula_nome);
		views_aula.add(novaView);
	}
	
	public String toString() {
		String texto="Nome: " + getNome() + " Duração: " + getDuracao() + " Views: " + getViews();
		if (views_aula.isEmpty())
			texto += "Sem Views";
		else 	
			for(View v: views_aula) 
				texto += v.getAulaNome() + " - " + v.getMatriculaAluno() + ", "; 
		return texto;

	}	


}
