package modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;


@Entity 
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Pessoa {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String cpf; 
	private String nome;
	
	public Pessoa (){}
	
	public Pessoa(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	
	public void setId(int novoId) {
		id = novoId;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String novoNome) {
		nome = novoNome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String novoCpf) {
		cpf = novoCpf;
	}

	@Override
	public String toString() {
		String texto = "CPF: " + cpf + " Nome:" + String.format("%8s", cpf);	
		return texto;
	}
}