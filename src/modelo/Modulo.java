package modelo;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
public class Modulo {
	@Id
	private String nome;
	
	@ManyToOne
	private Curso curso;
	
	@OneToMany(	mappedBy="Modulo",
			cascade={CascadeType.PERSIST,CascadeType.MERGE}, 	
			orphanRemoval=true,			
			fetch=FetchType.EAGER) 	
	private List<Aula> aulas_modulo = new ArrayList<>();
	
	@Version
	private long versao;
	
	public Modulo() {}
	
	public Modulo(String nome, Curso curso) {
		this.nome = nome;
		this.curso = curso;
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String novoNome) {
		nome = novoNome;
	}
	
	
	public void adicionarAula(Aula aula) {
		aulas_modulo.add(aula);
	}
	
	public void removerAula(String nomeAula) {
		if (!aulas_modulo.isEmpty()) {
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

	public void setCurso(Curso novoCurso) {
		curso = novoCurso; 
	}
	
	public void removerCurso() {
		curso = null;
	}
	
	public List<Aula> getAulas() {
		return aulas_modulo;
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