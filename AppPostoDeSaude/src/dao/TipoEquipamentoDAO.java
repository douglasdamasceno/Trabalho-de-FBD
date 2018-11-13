package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.Conexao;
import model.TipoEquipamento;

public class TipoEquipamentoDAO {
	private Connection connection;

	public TipoEquipamentoDAO() {
	}

	public void conecte() {
		this.connection = new Conexao().getConnection();
	}

	public boolean addTipoEquipamento(TipoEquipamento tipoEquipamento) {
		String comandoSQL = "INSERT INTO tipoequipamento(tipoEquiNome) VALUES(?);";
		this.connection = new Conexao().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);

			preparedStatement.setString(1, tipoEquipamento.getTipoEquiNome());

			int qtdRowsAffected = preparedStatement.executeUpdate();
			preparedStatement.close();
			if (qtdRowsAffected > 0)
				return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public ArrayList<TipoEquipamento> getListTipoEquipamento() {
		String comandoSQL = "SELECT * FROM tipoequipamento ORDER BY idTipoEquipamento;";
		ArrayList<TipoEquipamento> listaTipoEquipamento = new ArrayList<TipoEquipamento>();
		this.connection = new Conexao().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				String tipoEquiNome = rs.getString("tipoEquiNome");
				int idTipoEqui = rs.getInt("idTipoEquipamento");
				TipoEquipamento tipoEqui = new TipoEquipamento(idTipoEqui, tipoEquiNome);

				listaTipoEquipamento.add(tipoEqui);
			}
			preparedStatement.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaTipoEquipamento;
	}

	public boolean deleteEquipamento(int idTipoEquipamento) {
		String comandoSQL = "DELETE FROM tipoequipamento WHERE idTipoEquipamento = ?;";

		this.connection = new Conexao().getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);

			preparedStatement.setInt(1, idTipoEquipamento);

			int qtdRowsAffected = preparedStatement.executeUpdate();
			preparedStatement.close();
			if (qtdRowsAffected > 0)
				return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public TipoEquipamento getTipoEquipamentoById(int idTipoEquipamento) {
		String comandoSQL = "SELECT * FROM tipoEquipamento WHERE idTipoEquipamento = ?;";

		this.connection = new Conexao().getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);
			preparedStatement.setInt(1, idTipoEquipamento);

			ResultSet rs = preparedStatement.executeQuery();
			rs.next();

			TipoEquipamento tipoEquipamento = new TipoEquipamento(idTipoEquipamento, rs.getString("tipoEquiNome"));
			preparedStatement.close();
			return tipoEquipamento;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public boolean atualizarTipoEquipamento(TipoEquipamento tipoEquipamento) {
		String comandoSQL = "UPDATE tipoequipamento set tipoEquiNome= ? where idtipoequipamento= ?;";

		this.connection = new Conexao().getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);

			preparedStatement.setString(1, tipoEquipamento.getTipoEquiNome());
			preparedStatement.setInt(2, tipoEquipamento.getIdTipoEquipamento());

			int qtdRowAffected = preparedStatement.executeUpdate();
			preparedStatement.close();

			if (qtdRowAffected > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}

	public int getIdMax() {
		String comandoSQL = "select max(idTipoEquipamento) from TipoEquipamento";
		conecte();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);

			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			int idMaximo = rs.getInt("max");

			preparedStatement.close();

			return idMaximo;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return -1;
	}

}
