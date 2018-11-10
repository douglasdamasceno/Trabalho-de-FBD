package dao;

import java.sql.*;

import jdbc.*;
import model.*;

public class EquipamentoDoPostoDAO {
	private Connection conexao;

	public EquipamentoDoPostoDAO() {

	}

	public Connection getConexao() {
		return conexao;
	}

	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}

	public void conecte() {
		this.conexao = new Conexao().getConnection();
	}

	public boolean adicionarEquipamentoNoPosto(EquipamentoDoPosto equipDoPosto) {

		String comandoSQL = "insert into EquipamentoDoPosto (idPosto, idEndereco, qtdEquipamento, dataEntrega) values (?, ?, ?)";
		conecte();

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);

			preparedStatement.setInt(1, equipDoPosto.getIdPosto());
			preparedStatement.setInt(2, equipDoPosto.getIdEquipamento());
			preparedStatement.setString(3, equipDoPosto.getDataDeEntrega());
			preparedStatement.close();

			int qtdRowsAffected = preparedStatement.executeUpdate();

			if (qtdRowsAffected > 0) {
				return true;
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.conexao.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return false;
	}

	public boolean removerEquipamentoDoPosto(int idEquipamento) {

		String comandoSQL = "delete from EquipamentoDoPosto where idEquipamento = ?";
		conecte();

		try {

			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);

			
			preparedStatement.setInt(1, idEquipamento);

			int qtdRowsAffected = preparedStatement.executeUpdate();
			preparedStatement.close();

			if (qtdRowsAffected > 0) {
				return true;
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.conexao.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return false;
	}
	
	public EquipamentoDoPosto getEquipamentoDoPostoById(int idPosto, int idEquipamento) {		
		String comandoSQL = "select * from EquipamentoDoPosto where idEquipamento = ? and idPosto = ?";
		conecte();
		
		try {
			
			PreparedStatement preparedStatement = conexao.prepareStatement(comandoSQL);
			
			preparedStatement.setInt(1, idEquipamento);
			preparedStatement.setInt(2, idPosto);
			
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			
			EquipamentoDoPosto equiDoPosto = new EquipamentoDoPosto(idPosto, idEquipamento, rs.getInt("qtdEquipamento"), rs.getString("dataDeEntrega"));
			
			preparedStatement.close();
			
			return equiDoPosto;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.conexao.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
}
