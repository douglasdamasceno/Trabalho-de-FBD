package model;

public class TipoEquipamento {
	private int idTipoEquipamento;
	private String tipoEquiNome;

	public TipoEquipamento(String tipoEquiNome) {
		this.tipoEquiNome = tipoEquiNome;
	}

	public TipoEquipamento(int idTipoEquipamento, String tipoEquiNome) {
		this.idTipoEquipamento = idTipoEquipamento;
		this.tipoEquiNome = tipoEquiNome;
	}

	public int getIdTipoEquipamento() {
		return idTipoEquipamento;
	}

	public void setIdTipoEquipamento(int idTipoEquipamento) {
		this.idTipoEquipamento = idTipoEquipamento;
	}

	public String getTipoEquiNome() {
		return tipoEquiNome;
	}

	public void setTipoEquiNome(String tipoEquiNome) {
		this.tipoEquiNome = tipoEquiNome;
	}

	@Override
	public String toString() {
		return "TipoEquipamento [idTipoEquipamento=" + idTipoEquipamento + ", tipoEquiNome=" + tipoEquiNome + "]";
	}

}
