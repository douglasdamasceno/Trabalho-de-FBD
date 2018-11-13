package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.Conexao;
import model.*;

public class EquipamentoDAO {

	private Connection connection;

	public EquipamentoDAO() {
	}

	public void conecte() {
		this.connection = new Conexao().getConnection();
	}

	public boolean addEquipamento(Equipamento equipamento) {
		String comandoSQL = "INSERT INTO equipamento(idTipoEquipamento,descricao) VALUES(?,?);";
		this.connection = new Conexao().getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);

			preparedStatement.setInt(1, equipamento.getIdTipoEquipamento());
			preparedStatement.setString(2, equipamento.getDescricao());

			int qtdRowsAffected = preparedStatement.executeUpdate();
			preparedStatement.close();
			if (qtdRowsAffected > 0)
				return true;

			return false;

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
		return false;
	}

	public ArrayList<Equipamento> getListEquipamento() {
		String comandoSQL = "SELECT * FROM Equipamento ORDER BY idEquipamento;";
		ArrayList<Equipamento> listaEquipamentos = new ArrayList<Equipamento>();
		this.connection = new Conexao().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int idEquipamento = rs.getInt("idEquipamento");
				int idTipoEquipamento = rs.getInt("idTipoEquipamento");
				String descricao = rs.getString("descricao");

				Equipamento equipamento = new Equipamento(idEquipamento, descricao, idTipoEquipamento);
				listaEquipamentos.add(equipamento);
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
		return listaEquipamentos;
	}

	public boolean deleteEquipamento(int idEquipamento) {
		String comandoSQL = "DELETE FROM equipamento WHERE idEquipamento = ?;";

		this.connection = new Conexao().getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);

			// seta os valores
			preparedStatement.setInt(1, idEquipamento);

			int qtdRowsAffected = preparedStatement.executeUpdate();
			preparedStatement.close();
			if (qtdRowsAffected > 0)
				return true;

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

	public Equipamento getEquipamentoByID(int idEquipamento) {
		String comandoSQL = "SELECT * FROM equipamento WHERE idEquipamento = ?;";

		this.connection = new Conexao().getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);

			preparedStatement.setInt(1, idEquipamento);

			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			Equipamento equipamento = new Equipamento(rs.getInt("idEquipamento"), rs.getString("descricao"),
					rs.getInt("idTipoEquipamento"));
			preparedStatement.close();
			return equipamento;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;

	}

	public boolean alterarEquipamento(Equipamento equipamento) {
		String comandoSQL = "UPDATE equipamento SET idTipoEquipamento=?,descricao= ? where idEquipamento = ?;";

		this.connection = new Conexao().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);
			preparedStatement.setInt(1, equipamento.getIdTipoEquipamento());
			preparedStatement.setString(2, equipamento.getDescricao());
			preparedStatement.setInt(3, equipamento.getIdEquipamento());

			int qtdRowAffected = preparedStatement.executeUpdate();
			if (qtdRowAffected > 0)
				return true;
			preparedStatement.close();

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
		return false;
	}
	
	public boolean alterarTipoDoEquipamento(int tipoEquipamento) {
		String comandoSQL = "UPDATE equipamento SET idTipoEquipamento=? WHERE idEquipamento =? ;";
		this.connection = new Conexao().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);
			preparedStatement.setInt(1, tipoEquipamento);
			
			int qtdRowAffected = preparedStatement.executeUpdate();
			preparedStatement.close();
			if(qtdRowAffected>0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean alterarDescricao(String descricao) {
		String comandoSQL = "UPDATE equipamento SET descricao = ? WHERE idEquipamento =?";
		this.connection = new Conexao().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);
			preparedStatement.setString(1, descricao);
			int qtdRowAffected = preparedStatement.executeUpdate();
			preparedStatement.close();
			if(qtdRowAffected>0)
				return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
//	public ArrayList<Equipamento> getListEquipamentosQueNaoEstaoNoPosto(int idEquipamento, int idEquipamentoDoPosto) {
//		String comandoSQL = "select * from Equipamento E, EquipamentoDoPosto EP where ? != ?";
//		ArrayList<Equipamento> equipamentosQueNaoEstaoNoPosto = new ArrayList<>();
//		conecte();
//
//		try {
//
//			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);
//
//			preparedStatement.setInt(1, idEquipamento);
//			preparedStatement.setInt(2, idEquipamentoDoPosto);
//
//			ResultSet rs = preparedStatement.executeQuery();
//			while (rs.next()) {
//
//				Equipamento equipamento = new Equipamento(rs.getInt("idEquipamento"), rs.getString("descricao"),
//						rs.getInt("idTipoEquipamento"));
//				equipamentosQueNaoEstaoNoPosto.add(equipamento);
//			}
//
//			preparedStatement.close();
//		} catch (SQLException e) {
//			System.err.println(e.getMessage());
//		} finally {
//			try {
//				this.connection.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		return equipamentosQueNaoEstaoNoPosto;
//	}

	public int getIdMax() {
		String comandoSQL = "select max(idEquipamento) from Equipamento";
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
