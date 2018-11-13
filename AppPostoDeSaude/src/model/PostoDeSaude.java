package model;

public class PostoDeSaude {
	private int idPosto;
	private int idEndereco;
	private String pNome;
	
	
	public PostoDeSaude(String pNome, int idEndereco) {
		this.pNome = pNome;
		this.idEndereco = idEndereco;
		
	}
	public PostoDeSaude(int idPosto, String pNome, int idEndereco) {
		this.idPosto = idPosto;
		this.pNome = pNome;
		this.idEndereco = idEndereco;
		
	}

	public int getIdPosto() {
		return idPosto;
	}
	public void setIdPosto(int idPosto) {
		this.idPosto = idPosto;
	}
	public int getIdEndereco() {
		return idEndereco;
	}
	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}
	public String getpNome() {
		return pNome;
	}
	public void setpNome(String pNome) {
		this.pNome = pNome;
	}
	
	
	
}
