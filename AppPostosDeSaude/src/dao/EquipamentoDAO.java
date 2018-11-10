package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.Conexao;
import model.Equipamento;

public class EquipamentoDAO {
	
	private Connection connection;

	public EquipamentoDAO(){}
	
	public boolean addEquipamento(Equipamento equipamento){
		String comandoSQL = "INSERT INTO equipamento(idTipoEquipamento,descricao) VALUES(?,?);";
		this.connection = new Conexao().getConnection();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);
			
			preparedStatement.setInt(1,equipamento.getIdTipoEquipamento());
			preparedStatement.setString(2,equipamento.getDescricao());
			
			int qtdRowsAffected = preparedStatement.executeUpdate() ;
			preparedStatement.close();
			if(qtdRowsAffected > 0)
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
				int idEquipamento =  rs.getInt("idEquipamento");
				int idTipoEquipamento = rs.getInt("idTipoEquipamento");
				String descricao = rs.getString("descricao");
				
				
				
				Equipamento equipamento = new Equipamento(idEquipamento,idTipoEquipamento,descricao);
				listaEquipamentos.add(equipamento);
			}
			preparedStatement.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}finally {
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
		}finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	
	public Equipamento getEquipamentoByID(int idEquipamento){
		String comandoSQL = "SELECT * FROM equipamento WHERE idEquipamento = ?;";
		
		this.connection = new Conexao().getConnection();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);
			
			preparedStatement.setInt(1, idEquipamento);
			
			ResultSet rs = preparedStatement.executeQuery();		
			rs.next();
			
			Equipamento equipamento = new Equipamento(idEquipamento,rs.getInt("idTipoEquipamento"),rs.getString("descricao"));
			preparedStatement.close();
			return equipamento;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
		
	}
	
	public boolean atualizarEquipamento(Equipamento equipamento){
		String comandoSQL = "UPDATE equipamento SET idTipoEquipamento=?,descricao= ? where idEquipamento = ?;";
		
		this.connection = new Conexao().getConnection();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL);
			
			preparedStatement.setInt(1,equipamento.getIdTipoEquipamento());
			preparedStatement.setString(2, equipamento.getDescricao());
			preparedStatement.setInt(3, equipamento.getIdEquipamento());
			
			int qtdRowAffected = preparedStatement.executeUpdate();
			if(qtdRowAffected>0 )
				return true;
			preparedStatement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	
	
}
