package modelo;

import java.util.ArrayList;
import java.util.List;

public class Curso{
	private String nome;
	private int preco;
	private List<Aluno> curso_alunos = new ArrayList<>();
	private List<Modulo> curso_modulos = new ArrayList<>();
		
	public Curso(String nome, int preco) {
		this.nome = nome;
		this.preco = preco;
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
	
	public List<Modulo> getModulos(){
		return curso_modulos;
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
	
	public void adicionarModulo(Modulo m) {
		curso_modulos.add(m);
	}
	
	public void removerModulo() {
		if (aulas_modulo.isEmpty()) {
			for (Aula a : aulas_modulo) {
				if (a.getNome() == nomeAula) {
					aulas_modulo.remove(a);
				}
			}
		}
		else {
			System.out.println("Aula não encontrada!");
		}
	}

	@Override
	public String toString() {
		String texto = " Nome: " + getNome() + " Preço: " + getPreco();
		if (curso_alunos.isEmpty())
			texto += "Sem Cursos";
		else 	
			for(Aluno a: curso_alunos) 
				texto += a.getNome() + " - " + a.getMatricula() + ", "; 
		return texto;
	}
}