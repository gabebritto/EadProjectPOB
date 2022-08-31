package modelo;

import java.util.ArrayList;
import java.util.List;

public class Curso{
	private int id;
	private String nome;
	private int preco;
	
	private List<Aluno> curso_alunos = new ArrayList<>();
		
	public Curso(int id, String nome, int preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public int getId() {
		return id;
	}
	
	public int setId(int novoId) {
		return id = novoId;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String setNome(String novoNome) {
		return nome = novoNome;
	}
	
	public int getPreco() {
		return preco;
	}
	
	public int setPreco(int novoPreco) {
		return preco = novoPreco;
	}

	public List<Aluno> getAlunos() {
		return curso_alunos;
	}

	public void setAlunos(List<Aluno> curso_alunos) {
		this.curso_alunos = curso_alunos;
	}

	@Override
	public String toString() {
		String texto = "ID: " + getId() + " Nome: " + getNome() + " Preço: " + getPreco();
		if (curso_alunos.isEmpty())
			texto += "Sem Cursos";
		else 	
			for(Aluno a: curso_alunos) 
				texto += a.getNome() + " - " + a.getMatricula() + ", "; 
		return texto;
	}
}