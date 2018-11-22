package model;

public class EquipamentoDoPosto {
	private int idEquiDoPosto;
	private int idPosto;
	private int idEquipamento;
	private int qtdEquipamento;
	private String dataDeEntrega;
	
	
	
	public EquipamentoDoPosto(int idEquiDoPosto, int idPosto, int idEquipamento, int qtdEquipamento,
			String dataDeEntrega) {
		this.idEquiDoPosto = idEquiDoPosto;
		this.idPosto = idPosto;
		this.idEquipamento = idEquipamento;
		this.qtdEquipamento = qtdEquipamento;
		this.dataDeEntrega = dataDeEntrega;
	}

	public EquipamentoDoPosto(int idEquipamento, int qtdEquipamento, String dataDeEntrega) {
		this.idEquipamento = idEquipamento;
		this.qtdEquipamento = qtdEquipamento;
		this.dataDeEntrega = dataDeEntrega;
	}
	
	public EquipamentoDoPosto(int idPosto, int idEquipamento, int qtdEquipamento, String dataDeEntrega) {
		this.idPosto = idPosto;
		this.idEquipamento = idEquipamento;
		this.qtdEquipamento = qtdEquipamento;
		this.dataDeEntrega = dataDeEntrega;
	}

	public int getIdPosto() {
		return idPosto;
	}

	public void setIdPosto(int idPosto) {
		this.idPosto = idPosto;
	}

	public int getIdEquipamento() {
		return idEquipamento;
	}

	public void setIdEquipamento(int idEquipamento) {
		this.idEquipamento = idEquipamento;
	}

	public String getDataDeEntrega() {
		return dataDeEntrega;
	}

	public void setDataDeEntrega(String dataDeEntrega) {
		this.dataDeEntrega = dataDeEntrega;
	}

	public int getQtdEquipamento() {
		return qtdEquipamento;
	}

	public void setQtdEquipamento(int qtdEquipamento) {
		this.qtdEquipamento = qtdEquipamento;
	}

	public int getIdEquiDoPosto() {
		return idEquiDoPosto;
	}

	public void setIdEquiDoPosto(int idEquiDoPosto) {
		this.idEquiDoPosto = idEquiDoPosto;
	}

	@Override
	public String toString() {
		return "EquipamentoDoPosto [idEquiDoPosto=" + idEquiDoPosto + ", idPosto=" + idPosto + ", idEquipamento="
				+ idEquipamento + ", qtdEquipamento=" + qtdEquipamento + ", dataDeEntrega=" + dataDeEntrega + "]";
	}
	
}
