package modelo;

public class Pessoa {
	private String cpf; 
	private String nome;
	
	public Pessoa(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		// TODO Auto-generated constructor stub
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