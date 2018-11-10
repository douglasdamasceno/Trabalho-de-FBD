package model;

public class Paciente {
	private int idPaciente;
	private String pacNome;
	private int idEndereco;
	private int idPosto;
	
	public Paciente(String pacNome, int idEnderenco, int idPosto) {
		this.pacNome = pacNome;
		this.idEndereco = idEnderenco;
		this.idPosto = idPosto;
	}

	public Paciente(int idPaciente, String pacNome, int idEnderenco, int idPosto) {
		this.idPaciente = idPaciente;
		this.pacNome = pacNome;
		this.idEndereco = idEnderenco;
		this.idPosto = idPosto;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getPacNome() {
		return pacNome;
	}

	public void setPacNome(String pacNome) {
		this.pacNome = pacNome;
	}

	public int getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}

	public int getIdPosto() {
		return idPosto;
	}

	public void setIdPosto(int idPosto) {
		this.idPosto = idPosto;
	}
	
	
}
