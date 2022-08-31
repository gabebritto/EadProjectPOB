package modelo;
import java.util.ArrayList;
import java.util.List;


public class Modulo {
	private int id;
	private String nome;
	private Curso curso;
	
	public Modulo(int id, String nome, Curso curso) {
		this.id = id;
		this.nome = nome;
		this.curso = curso;
		// TODO Auto-generated constructor stub
	}
	
	
	public Curso getCurso() {
		return curso;
	}
	
	@Override
	public String toString() {
		String texto="ID: " + id + " Nome: " + nome + " Curso: " + getCurso()
	
		return texto;

	}		
	
}