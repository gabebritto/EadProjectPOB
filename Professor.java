package modelo;
import java.util.ArrayList;
import java.util.List;


public class Professor extends Pessoa {   
	private int salario;
	private List<Aula> aulas_professor = new ArrayList<>();

	public Professor(String nome, String cpf, int salario) {
		super(nome, cpf);
		this.salario = salario;
	}
	
	public int getSalario() {
		return salario;
	}
	
	public int setSalario(int novoSalario) {
		return salario = novoSalario;
	}
	
	public List<Aula> getAulas() {
		return aulas_professor;
	}

	public void adicionarAulaProfessor(Aula a){
		aulas_professor.add(a);
	}
	
	public void removerAulaProfessor(Aula a){
		aulas_professor.remove(a);
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