package model;

public class Cidade {
	
	private int numero;
	private String rua;
	private String bairro;
	
	public Cidade(int numero, String rua, String bairro) {
		this.numero = numero;
		this.rua = rua;
		this.bairro = bairro;
	}
	
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	
	
	

}
