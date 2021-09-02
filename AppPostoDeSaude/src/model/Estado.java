package model;

public class Estado {
	
	
	private String cep;
	private String nome;
	
	public Estado(String cep, String nome) {
		this.cep = cep;
		this.nome = nome;
	}
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
