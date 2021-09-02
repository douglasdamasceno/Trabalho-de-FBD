package model;

public class Endereco {
	
	private Cidade cidade;
	private Estado estado;
	private int id;
	
public Endereco(int id, Cidade cidade, Estado estado) {
		this.id = id;
		this.cidade = cidade;
		this.estado = estado;
	}
	
	public Endereco(Cidade cidade, Estado estado) {	
		this.cidade = cidade;
		this.estado = estado;
	}
	
	
	
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

	

}
