package modelo;


import java.util.List;

public class Pessoa {
	private String cpf;  //autoincremento
	private String nome;
	
	public Pessoa(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}
	
	public String setNome(String novoNome) {
		return nome = novoNome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public String setCpf(String novoCpf) {
		return cpf = novoCpf;
	}

	@Override
	public String toString() {
		String texto = "CPF: " + cpf + " Nome:" + String.format("%8s", cpf);	
		return texto;
	}
}