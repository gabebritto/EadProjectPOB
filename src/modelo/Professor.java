package modelo;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
public class Professor extends Pessoa {

	private int salario;
	
	@OneToMany(	mappedBy="Professor",
			cascade={CascadeType.PERSIST,CascadeType.MERGE}, 	
			orphanRemoval=true,			
			fetch=FetchType.EAGER) 	
	private List<Aula> aulas_professor = new ArrayList<>();

	@Version
	private long versao;
	
	public Professor() {}
	
	public Professor(String nome, String cpf, int salario) {
		super(nome, cpf);
		this.salario = salario;
	}

	public int getSalario() {
		return salario;
	}
	
	public void setSalario(int novoSalario) {
		salario = novoSalario;
	}
	
	public List<Aula> getAulas() {
		return aulas_professor;
	}

	public void adicionarAulaProfessor(Aula a){
		aulas_professor.add(a);
	}
	
	public void removerAulaProfessor(String nomeAula){
		if (!aulas_professor.isEmpty()) {
			for (Aula a : aulas_professor) {
				if (a.getNome() == nomeAula) {
					aulas_professor.remove(a);
				}
			}
		}
		else {
			System.out.println("Aula não encontrada!");
		}
	}
	
	@Override
	public String toString() {
		String texto="CPF: " + getCpf() + " Nome: " + getNome() + " Salário: " + getSalario();
		if (aulas_professor.isEmpty())
			texto += "Sem Aulas";
		else 	
			for(Aula a: aulas_professor) 
				texto += a.getNome() + ", "; 
		return texto;

	}		
	
}