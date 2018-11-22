package model;

public class Equipamento {
	private int idEquipamento;
	private int idTipoEquipamento;
	private String descricao;

	public Equipamento(String descricao, int idTipoEquipamento) {
		this.idTipoEquipamento = idTipoEquipamento;
		this.descricao = descricao;
	}

	public Equipamento(int idEquipamento, String descricao, int idTipoEquipamento) {
		this.idEquipamento = idEquipamento;
		this.idTipoEquipamento = idTipoEquipamento;
		this.descricao = descricao;
	}

	public int getIdEquipamento() {
		return idEquipamento;
	}

	public void setIdEquipamento(int idEquipamento) {
		this.idEquipamento = idEquipamento;
	}

	public int getIdTipoEquipamento() {
		return idTipoEquipamento;
	}

	public void setIdTipoEquipamento(int idTipoEquipamento) {
		this.idTipoEquipamento = idTipoEquipamento;
	}

	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return "Equipamento [idEquipamento=" + idEquipamento + ", idTipoEquipamento=" + idTipoEquipamento
				+ ", descricao=" + descricao + "]";
	}
}
