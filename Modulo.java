package modelo;
import java.util.ArrayList;
import java.util.List;


public class Modulo {
	private String nome;
	private Curso curso;
	private List<Aula> aulas_modulo = new ArrayList<>();
	
	public Modulo(String nome, Curso curso) {
		this.nome = nome;
		this.curso = curso;
		// TODO Auto-generated constructor stub
	}
	
	public void adicionarAula(Aula aula) {
		aulas_modulo.add(aula);
	}
	
	public void removerAula(String nomeAula) {
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
	
	public Curso getCurso() {
		return curso;
	}
	
	@Override
	public String toString() {
		String texto=" Nome: " + nome + " Curso: " + getCurso();
		if (aulas_modulo.isEmpty())
			texto += "Sem Cursos";
		else 	
			for(Aula a: aulas_modulo) 
				texto += a.getNome() + " - " + a.getProfessor().getNome() + ", "; 
		return texto;
	}		
	
}