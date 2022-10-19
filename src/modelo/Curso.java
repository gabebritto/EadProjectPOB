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
	
	public void setNome(String novoNome) {
		nome = novoNome;
	}
	
	public int getPreco() {
		return preco;
	}
	
	public List<Modulo> getModulos(){
		return curso_modulos;
	}
	
	public void setPreco(int novoPreco) {
		preco = novoPreco;
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

	public void removerModulo(Modulo m) {
		curso_modulos.remove(m);
	}

	public void adicionarAluno(Aluno a) {
		curso_alunos.add(a);
	}

	public void removerAluno(Aluno a) {
		curso_alunos.remove(a);
	}
	
	public Modulo localizarModulo(String nomeModulo) {
		if (!curso_modulos.isEmpty()) {
			for (Modulo m : curso_modulos) {
				if (m.getNome().equals(nomeModulo)) {
					return m;
				}
			}
		}
		return null;
	}
	
	public Aluno localizar(String nome){
		for(Aluno a : curso_alunos){
			if(a.getNome().equals(nome))
				return a;
		}
		return null;
	}
	
	public int totalGanho() {
		int totalAlunos = curso_alunos.size();
		return preco * totalAlunos;
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